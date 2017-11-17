package LetsTrySelenium.Rainbow_Selenium_framework.TmsLoginPage;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.collections.Lists;

import com.sun.jna.platform.unix.X11.XClientMessageEvent.Data;

import LetsTrySelenium.Rainbow_Selenium_framework.testBase.TestBase;
import LetsTrySelenium.Rainbow_Selenium_framework.uiActions.RainbowHomePage;

public class Tc00_VerifyLogin extends TestBase {
	
	public static final Logger log = Logger.getLogger(Tc00_VerifyLogin.class.getName());
	RainbowHomePage homepage;
	
	
   
	@DataProvider(name="testData")
	public String[][] getTestData() throws IOException
	{
		
		String[][] Data = getData("testData", "loginsheet");
		
//		ArrayList<String[][]> listof2Darray = new ArrayList<String[][]>();    
//
//		listof2Darray.add(getData("testData", "loginsheet"));
////			result.add(0, getData("testData", "loginsheet"));
////			result.add(1, getData("testData", "AddCardDetails"));
//			
		
//			
		return Data;
		
		
		
	}
	
//	@DataProvider(name="testData")
//	public String[][] getTestData()
//	{
//		try {
//			
//			String browserDetails[][] = getData("testData", "loginsheet");
//			return browserDetails;
//			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return null;
//		}
//		
//		
//	}
	
	@Test(dataProvider="testData")
	public void verifyLogin(String username,String pwd,String runmode,String ProductName ,String CardName)
	{
		if (runmode.equalsIgnoreCase("n")) {
			log.info(" =======using skip exception to skip test where run mode is n=========");
			throw new SkipException("run mode is n");
			
		}
		log.info("========starting verifyLogin test =======");
		homepage = new RainbowHomePage(driver);
		boolean successLogin = homepage.loginApplication(username, pwd);
		assertEquals(successLogin, true);
		log.info("======== verifyLogin test finished  =======");
		
	}
	
	
	@Parameters({"browser","loginURL"})
	@BeforeTest
    public void setup(String sbrowser,String sloginURL)
	{
	   init(sbrowser, sloginURL);
	   //dataProvider="testData"
	
	}
	
	
	
	@AfterClass
	public void endLogin()
	{
		
		driver.close();
	}

}
