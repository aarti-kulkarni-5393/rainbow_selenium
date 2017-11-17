package LetsTrySelenium.Rainbow_Selenium_framework.RainbowHomePage;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import LetsTrySelenium.Rainbow_Selenium_framework.testBase.TestBase;
import LetsTrySelenium.Rainbow_Selenium_framework.uiActions.RainbowHomePage;

public class TC001_verifyDiscardCard extends TestBase {
	
	public static final Logger log = Logger.getLogger(TC001_verifyDiscardCard.class.getName());
	
	RainbowHomePage homepage;
	@Parameters({"browser","loginURL"})
	@BeforeTest
	public void setup(String browser,String loginURL)
	{
		
		init(browser, loginURL);
		
	}
	
	@Test(dataProvider="tesdata")
	public void verifyDiscardCard(String username,String pwd,String runmode,String productname,String cardtitle)
	{
		
		if (runmode.equalsIgnoreCase("n")) {
    		throw new SkipException("runmode is n");
			
		}
			homepage = new RainbowHomePage(driver);
	    	log.info("========starting verifyLogin test =======");
			boolean successLogin = homepage.loginApplication(username, pwd);
			assertEquals(successLogin, true);
			log.info("======== verifyLogin test finished  =======");
			homepage= new RainbowHomePage(driver);
			log.info("=========Starting TC001_verifyDiscardCard=========");
			log.info("capturing first card name of Rainbow home");
			String cardName = homepage.firstcardName.getText();
			homepage.discard();
		    Assert.assertFalse(homepage.firstCard.getText().equalsIgnoreCase(cardName));
			
			log.info("=========Finished TC001_verifyDiscardCard=========");
			
		

		
		
	}
	
	@DataProvider(name="tesdata")
	
	public String[][] getdata()
	{
		
		String data[][];
		try {
			data = getData("testData", "loginsheet");
			return data;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
	}
	public void endTest()
	{
		
		driver.quit();
	}

}
