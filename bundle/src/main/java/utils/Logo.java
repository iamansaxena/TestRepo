package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author amohan31
 *
 */
public class Logo {
	private static Logger logger = LogManager.getLogger(Logo.class);
	
	static int logoCount = 0;
public Logo() {
	System.out.println();
	System.out.println();
	System.out.println();
	System.out.println("  \t\t\t\t "
			+ "oooo   pppp  ttttttt  u    u  m       m       cccc   aaaa   rrrr    eeee         \r\n" + 
			"\t\t\t\to    o  p   p    t     u    u  m  m m  m      c      a    a  r   r  e    \r\n" + 
			"\t\t\t\to    o  pppp     t     u    u  m   m   m     c       aaaaaa  rrrr   eeeee  \r\n" + 
			"\t\t\t\to    o  p        t     u    u  m       m      c      a    a  r  r   e                 \r\n" + 
			"\t\t\t\t oooo   p        t      uuuu   m       m       cccc  a    a  r   r   eeee");
	
	System.out.println(" \n                                                                    &&                                                                  \r\n" + 
			"				                                   &  &                                                                        			\r\n" + 
			"                                                                   &&&   &                                                                     \r\n" + 
			"                                                                 &    &&&                                                                                                                                                                                        \r\n" + 
			"                                                                 &    & &                                                                      \r\n" + 
			"                                                                  & &    &    \r\n" + 
			"                                                                                                                                                                                                                                                                            \r\n" + 
			"                                                       cccccc   oooo   rrrr     eeee                                                                               \r\n" + 
			"                                                      c        o    o  r   r   e                                                                       \r\n" + 
			"                                                     c         o    o  r   r   e                                                                       \r\n" + 
			"                                                     c         o    o  rrrr    eeee                                                                  \r\n" + 
			"                                                     c         o    o  r   r   e                                                                           \r\n" + 
			"                                                      c        o    o  r    r  e                                                                            \r\n" + 
			"                                                       cccccc   oooo   r     r  eeee                                                                  \r\n" + 
			"                                                                                                                 \r\n" + 
			"                                                                                                                 \r\n" + 
			"                                                                                                                 \r\n" +  
			"                                                                                                                ");
	System.out.println("______________________________________________________________________________________________________________________________________________________________");
	System.out.println("Automation Test Project - OPTUM CARE AND CORE");
	System.out.println("______________________________________________________________________________________________________________________________________________________________ \n\n");
}

public static void init() {
	if (logoCount == 0) {
		new Logo();
		logger.info("\n\n\n\n \t\t\t\t\tFresh Suite Execution");
		logoCount++;
	}
	
}
}
