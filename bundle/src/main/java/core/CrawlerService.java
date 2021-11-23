package core;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

import javax.net.ssl.SSLException;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.aventstack.extentreports.utils.ExceptionUtil;

import utils.LoggerLog4j;

/**
 * @author amohan31
 *
 */
public class CrawlerService extends Base implements Runnable {
	static final int CONNECTION_TIMEOUT = 120000;
	static final int MAX_LIMIT_FOR_HYPERLINKS = 2000;
	
	static ConcurrentHashMap<String, String> WUT = new ConcurrentHashMap<>();
	Thread t = null;
	static ThreadLocal<Boolean> CONNECTION_STATUS = new ThreadLocal<>();
	static ThreadLocal<ConcurrentSkipListSet<String>> unknownHostLinks = new ThreadLocal<>();
	static ThreadLocal<ConcurrentSkipListSet<String>> componentPool = new ThreadLocal<>();
	static ThreadLocal<ConcurrentSkipListSet<String>> nonWorkingLinksPool = new ThreadLocal<>();
	static ThreadLocal<ConcurrentSkipListSet<String>> linksWithSslException = new ThreadLocal<>();
	static ThreadLocal<String> pageUnderScanPool = new ThreadLocal<>();
	static ThreadLocal<Document> domPool = new ThreadLocal<>();
	static ThreadLocal<ArrayList<String>> hyperlinkPool = new ThreadLocal<ArrayList<String>>();
	public static ThreadLocal<String> domainPool = new ThreadLocal<>();
	static ThreadLocal<String> websitesPool = new ThreadLocal<>();
	static ThreadLocal<ConcurrentSkipListSet<String>> visitedLinksPool = new ThreadLocal<>();
	static ThreadLocal<Integer> iteratorCount = new ThreadLocal<>();
	static ThreadLocal<String> pageUrlForFetchingComponentPool = new ThreadLocal<>();

	/**
	 * @param website
	 *            to be scanned or crawled
	 */
	public void crawl(String website) {
		componentPool.set(new ConcurrentSkipListSet<>());
		iteratorCount.set(0);
		pageUnderScanPool.set(website);
		domainPool.set(getDomainName(website));
		hyperlinkPool.set(new ArrayList<String>());
		visitedLinksPool.set(new ConcurrentSkipListSet<>());
		unknownHostLinks.set(new ConcurrentSkipListSet<>());
		linksWithSslException.set(new ConcurrentSkipListSet<>());
		LoggerLog4j.startLoggerPool(String.valueOf(Thread.currentThread().getName()));
		websitesPool.set(website);
		nonWorkingLinksPool.set(new ConcurrentSkipListSet<>());
		CONNECTION_STATUS.set(false);
		connect(website);
		fetchHyperlinks();
		while (iteratorCount.get() < hyperlinkPool.get().size()) {

			visitedLinksPool.get().add(hyperlinkPool.get().get(iteratorCount.get()));

			connect(hyperlinkPool.get().get(iteratorCount.get()));

			domPool.get().getElementsByAttribute(qaHandleAttribute).forEach(component -> {
				componentPool.get().add(component.attr(qaHandleAttribute));
			});
			fetchComponentName(hyperlinkPool.get().get(iteratorCount.get()));
			if (hyperlinkPool.get().size() < MAX_LIMIT_FOR_HYPERLINKS) {
				fetchHyperlinks();
			} else {
				loggerPool.get().info("HyperLinks till now => " + hyperlinkPool.get().size() + " <==> "
						+ visitedLinksPool.get().size());
			}
			iteratorCount.set(iteratorCount.get() + 1);
		}
		loggerPool.get().debug("Links not found ==> " + nonWorkingLinksPool.get());
		loggerPool.get().debug("Unknown hosts ==> " + unknownHostLinks.get());
		loggerPool.get().debug("Links with ssl exception ==> " + linksWithSslException.get());
		loggerPool.get().info(componentData);

	}

	/**
	 * @param to
	 *            which url components needed to be mapped
	 */
	public static void fetchComponentName(String pageUrl) {

		synchronized (pageUrl) {
			pageUrlForFetchingComponentPool.set(pageUrl);
		}

		componentPool.get().forEach(element -> {

			try {

				if (componentData.get(element) == null || componentData.get(element).isEmpty()) {
					/*
					 * If we need to store url for components not available in our component list
					 * then uncomment the following line of code
					 * 
					 */
					if (componentData.keySet().contains(element)) {
						componentData.put(element, "\"" + pageUrlForFetchingComponentPool.get() + "\"");
						loggerPool.get().info("Found New Component: '" + element + "' on page => "
								+ pageUrlForFetchingComponentPool.get());
					}

				} else if (!componentData.get(element).isEmpty()
						&& !componentData.get(element).contains(getDomainName(pageUrlForFetchingComponentPool.get()))) {

					if (componentData.get(element).equals("-")) {
						componentData.replace(element, "-", "\"" + pageUrlForFetchingComponentPool.get() + "\"");
					} else {
						componentData.replace(element,
								componentData.get(element) + "," + "\"" + pageUrlForFetchingComponentPool.get() + "\"");
					}
					loggerPool.get()
							.info("Found '" + element + "' on page => " + pageUrlForFetchingComponentPool.get());

				}
			} catch (NullPointerException e) {
				loggerPool.get().info("Component '" + element + "' found but is is not covered in suite yet!");
			}
		});
	}

	/**
	 * Finds all the unique hyperlinks within the current page based on few
	 * conditions
	 */
	public static void fetchHyperlinks() {
		if (domPool.get() != null) {
			domPool.get().select("a[href]").forEach(link -> {
				if (!link.absUrl("href").toLowerCase().contains("mailto") && !link.absUrl("href").isEmpty()
						&& !link.absUrl("href").trim().toLowerCase().contains("tel:")
						&& !link.absUrl("href").trim().toLowerCase().endsWith(".pdf")
						&& !link.absUrl("href").trim().toLowerCase().endsWith(".png")
						&& !link.absUrl("href").trim().toLowerCase().endsWith(".mp3")
						&& !link.absUrl("href").trim().toLowerCase().endsWith(".mp4")
						&& !link.absUrl("href").trim().toLowerCase().endsWith(".crdownload")
						&& !link.absUrl("href").trim().toLowerCase().endsWith(".jpg")) {

					if (domainPool.get().equals(getDomainName(link.absUrl("href").split("#")[0].trim()))) {
						if (!hyperlinkPool.get().contains(link.absUrl("href").trim())) {
							hyperlinkPool.get().add(link.absUrl("href").trim());
						}
					}

				}

			});
		}
		loggerPool.get().info(
				"HyperLinks till now => " + hyperlinkPool.get().size() + " <==> " + visitedLinksPool.get().size());
	}

	/**
	 * @param url
	 *            to which connection to be made
	 * @return DOM of the connected url
	 */
	public static Document connect(String url) {

		synchronized (url) {
			pageUnderScanPool.set(url);
		}

		while (CONNECTION_STATUS.get() == false) {
			try {
				domPool.set(Jsoup.connect(pageUnderScanPool.get().trim()).followRedirects(true).timeout(CONNECTION_TIMEOUT)
						.ignoreContentType(true).maxBodySize(48000000).get());
				CONNECTION_STATUS.set(true);

			} catch (ConnectException e) {
				loggerPool.get().fatal("Connection Timeout ==> " + pageUnderScanPool.get());
			} catch (SocketTimeoutException e) {
				loggerPool.get().fatal("Socket Timeout ==> " + pageUnderScanPool.get());
			} catch (SSLException e) {
				loggerPool.get().fatal("SSL Exception==> " + pageUnderScanPool.get());
				linksWithSslException.get().add(pageUnderScanPool.get());
				break;
			} catch (HttpStatusException e) {
				loggerPool.get().error("Page not found: " + pageUnderScanPool.get());
				nonWorkingLinksPool.get().add(pageUnderScanPool.get());
				break;
			} catch (UnknownHostException e) {
				loggerPool.get().fatal("IO Exception==> " + pageUnderScanPool.get());
				unknownHostLinks.get().add(pageUnderScanPool.get());
				break;
			} catch (IOException e) {
				loggerPool.get().fatal("IO Exception==> " + pageUnderScanPool.get());
				loggerPool.get().fatal(ExceptionUtil.getStackTrace(e));
				break;
			}
		}
		CONNECTION_STATUS.set(false);

		return domPool.get();

	}

	public static ArrayList<Thread> threadPool = new ArrayList<>();

	public CrawlerService(String url) {
		synchronized (url) {
			WUT.put(url, url);
			synchronized (url) {

				t = new Thread(this);
				threadPool.add(t);
				t.setName(url);
				t.start();
			}
		}
	}

	@Override
	public void run() {
		crawl(Thread.currentThread().getName());
	}
}
