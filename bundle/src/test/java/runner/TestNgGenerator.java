package runner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;
public class TestNgGenerator {
 public void runTestNGTest(Map<String,String> testngParams)
 {   //Create an instance on TestNG 
     TestNG myTestNG = new TestNG();   
     
     //Create an instance of XML Suite and assign a name for it. 
      XmlSuite mySuite = new XmlSuite(); 
      mySuite.setName("MySuite");
      mySuite.addListener("runner.TestListener");
      mySuite.setParallel(XmlSuite.PARALLEL_CLASSES);   

     //Create an instance of XmlTest and assign a name for it.  
     XmlTest myTest = new XmlTest(mySuite); 
     myTest.setName("MyTest");   
  
     //Add any parameters that you want to set to the Test. 
     myTest.setParameters(testngParams); 
   
     //Create a list which can contain the classes that you want to run.
     List<XmlClass> myClasses = new ArrayList<XmlClass>();
     myClasses.add(new XmlClass(componentStepDef.ColorBlock_StepDefinition.class));   

     //Assign that to the XmlTest Object created earlier. 
     myTest.setXmlClasses(myClasses);   

     //Create a list of XmlTests and add the Xmltest you created earlier to it.
     List<XmlTest> myTests = new ArrayList<XmlTest>(); 
     myTests.add(myTest);   

     //add the list of tests to your Suite. 
     mySuite.setTests(myTests);   

     //Add the suite to the list of suites. 
     List<XmlSuite> mySuites = new ArrayList<XmlSuite>(); 
     mySuites.add(mySuite);   
     
     //Set the list of Suites to the testNG object you created earlier. 
     myTestNG.setXmlSuites(mySuites);
     mySuite.setFileName("myTemp.xml"); 
     mySuite.setThreadCount(3);   
     myTestNG.run();

     //Create physical XML file based on the virtual XML content 
     for(XmlSuite suite : mySuites) 
     {  
         createXmlFile(suite); 
     }   
     System.out.println("Filerated successfully.");   
 
     //Print the parameter values 
     Map<String,String> params = myTest.getParameters(); 
     for(Entry<String,String> entry : params.entrySet()) 
     { 
       System.out.println(entry.getKey() + " => " + entry.getValue()); 
     }
    }

    //This method will create an Xml file based on the XmlSuite data 
    public void createXmlFile(XmlSuite mSuite) 
    { 
       FileWriter writer; 
       try { 
            writer = new FileWriter(new File("myTemp.xml")); 
            writer.write(mSuite.toXml()); 
            writer.flush(); 
            writer.close(); 
            System.out.println(new File("myTemp.xml").getAbsolutePath());          } catch (IOException e)
            {
              e.printStackTrace(); 
            }
    }
   //Main Method
   public static void main (String args[]) 
   { 
       TestNgGenerator dt = new TestNgGenerator(); 

      //This Map can hold your testng Parameters. 
      Map<String,String> testngParams = new HashMap<String,String> ();
//      testngParams.put("device1esktop"); 
//      testngParams.put("device2obile"); 
//      testngParams.put("device3ablet");   
      dt.runTestNGTest(testngParams); 
   }
 }