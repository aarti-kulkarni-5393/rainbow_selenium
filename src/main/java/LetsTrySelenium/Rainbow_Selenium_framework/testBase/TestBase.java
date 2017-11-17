package LetsTrySelenium.Rainbow_Selenium_framework.testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Calendar.Builder;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.CopyUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.sun.jna.platform.FileUtils;
import com.sun.jna.platform.win32.Netapi32Util.User;

import LetsTrySelenium.Rainbow_Selenium_framework.customListner.Listner;
import LetsTrySelenium.Rainbow_Selenium_framework.excelRead.ExcelRead;

public class TestBase {
	
	public static final Logger log = Logger.getLogger(TestBase.class.getName());
	
    public static WebDriver driver;
     //String browser= "chrome";
     //String LoginURL = "https://smtlogin.zycus.net/sso/login";
     ExcelRead excel;
     
     
    public void init(String browser,String LoginURL)
    {
    	selectBrowser(browser);
    	getUrl(LoginURL);
    	String log4jconfPath = "log4j.properties";
    	PropertyConfigurator.configure(log4jconfPath);
    	//Listner lis = new Listner(driver);
    	
    	
    }
	
	public void selectBrowser(String browser)
	{
		if (browser.equalsIgnoreCase("chrome")) 
		{
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\aarti.kulkarni\\workspace\\Rainbow_Selenium_framework\\chromedriver.exe");
			Map<String, Object> settings = new HashMap<String, Object>();
			settings.put("profile.default_content_setting_values.notifications", 2);
		    ChromeOptions options = new ChromeOptions();
		    options.addArguments("no-sandbox");
		    options.addArguments("disable-extensions");
		    options.addArguments("--start-maximized");
		    options.setExperimentalOption("prefs", settings);
		    log.info("creating browser object "+browser);
		    driver = new ChromeDriver(options);
			
		}
		else
		{
			
			System.setProperty("webdriver.gecko.driver","C:\\Users\\aarti.kulkarni\\workspace\\Rainbow_Selenium_framework\\drivers\\geckodriver.exe");
//			Map<String, Object> settings = new HashMap<String, Object>();
//			settings.put("profile.default_content_setting_values.notifications", 2);
//			FirefoxProfile profile = new FirefoxProfile();
			driver = new FirefoxDriver();
		}
		
		
		
	}
	
	public void getUrl(String URL)
	{  
		
		log.info("navigating to"+URL);
		driver.get(URL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	     
		
	}
	
	
	public String[][] getData(String excelName,String sheetName) throws IOException
	{
		String currentUsersHomeDir = System.getProperty("user.dir");
		System.out.println(currentUsersHomeDir);
		excel = new ExcelRead(currentUsersHomeDir+"\\src\\main\\java\\LetsTrySelenium\\Rainbow_Selenium_framework\\data\\"+excelName+".xlsx");
		String data[][] = excel.getData(sheetName);
		
		return data;
	}
	 
	
	public void waitForElement(int timeout,WebElement element)
	{
		
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.visibilityOf(element));
		
		
	}
	
	public void takeScreenshot( String name)
	{
		// creating calendar instance 
		Calendar cal = Calendar.getInstance();
		// simple formatter used to format date
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		//String path = ;
		String currentUsersHomeDir = System.getProperty("user.dir");
	
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		log.info("screenshot has been taken for:"+name);
		// Destination file having name that we desired ,here it is date format so it will be unique 
		File destFile = new File(currentUsersHomeDir+"\\src\\main\\java\\LetsTrySelenium\\Rainbow_Selenium_framework\\screenshot\\"+name+"_"+formater.format(cal.getTime())+".png");
		
		try {
			org.apache.commons.io.FileUtils.copyFile(srcFile, destFile);
			// using Reporter log will create link of images which will used in reporting
			Reporter.log("<a href='"+destFile.getAbsolutePath()+"'> <img src='"+destFile.getAbsolutePath()+"'height='100' width ='100'/> </a>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		
		
	}
	

}
