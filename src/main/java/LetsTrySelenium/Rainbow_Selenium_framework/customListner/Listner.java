package LetsTrySelenium.Rainbow_Selenium_framework.customListner;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import LetsTrySelenium.Rainbow_Selenium_framework.testBase.TestBase;
import LetsTrySelenium.Rainbow_Selenium_framework.uiActions.RainbowHomePage;



public class Listner extends TestBase implements ITestListener{
 
	
	public static final Logger log = Logger.getLogger(Listner.class.getName());
	

	public void onTestStart(ITestResult result) {
		
	
		Reporter.log(" Test Started"+result.toString());
	}

	public void onTestSuccess(ITestResult result) {
		Calendar cal = Calendar.getInstance();
		// simple formatter used to format date
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		String methodName = result.getName();
       
        	
        	File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        	String currentUsersHomeDir = System.getProperty("user.dir");
        	File destFile = new File(currentUsersHomeDir+"\\src\\main\\java\\LetsTrySelenium\\Rainbow_Selenium_framework\\customListner\\"+methodName+"Success_"+formater.format(cal.getTime())+".png");
        	try {
    			org.apache.commons.io.FileUtils.copyFile(srcFile, destFile);
    			// using Reporter log will create link of images which will used in reporting
    			Reporter.log("<a href='"+destFile.getAbsolutePath()+"'> <img src='"+destFile.getAbsolutePath()+"'height='100' width ='100'/> </a>");
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        	
        	log.info("screenshot has been succesfuly taken for failur method: "+methodName);
        	Reporter.log("screenshot has been taken for "+methodName);
		
		
		
	}

	public void onTestFailure(ITestResult result) {
		
		Calendar cal = Calendar.getInstance();
		// simple formatter used to format date
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		String methodName = result.getName();
        if (!result.isSuccess()) {
        	
        	File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        	String currentUsersHomeDir = System.getProperty("user.dir");
        	File destFile = new File(currentUsersHomeDir+"\\src\\main\\java\\LetsTrySelenium\\Rainbow_Selenium_framework\\customListner\\"+methodName+"failuer_"+formater.format(cal.getTime())+".png");
        	try {
    			org.apache.commons.io.FileUtils.copyFile(srcFile, destFile);
    			// using Reporter log will create link of images which will used in reporting
    			Reporter.log("<a href='"+destFile.getAbsolutePath()+"'> <img src='"+destFile.getAbsolutePath()+"'height='100' width ='100'/> </a>");
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        	
        	log.info("screenshot has been succesfuly taken for failur method: "+methodName);
        	Reporter.log("screenshot has been taken for "+methodName);
		}
		
	
        
		// Destination file having name that we desired ,here it is date format so it will be unique 
		
		
		
		
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		Reporter.log(" Test skipped"+result.toString());
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		Reporter.log(" Test Started"+ context.toString());

	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		Reporter.log(" Test Started"+ context.toString());
		
	}

}
