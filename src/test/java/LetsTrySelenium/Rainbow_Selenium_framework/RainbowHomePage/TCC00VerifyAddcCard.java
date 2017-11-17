package LetsTrySelenium.Rainbow_Selenium_framework.RainbowHomePage;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import LetsTrySelenium.Rainbow_Selenium_framework.testBase.TestBase;
import LetsTrySelenium.Rainbow_Selenium_framework.uiActions.RainbowHomePage;

public class TCC00VerifyAddcCard extends TestBase {

	public static final Logger log = Logger.getLogger(TCC00VerifyAddcCard.class.getName());
	RainbowHomePage homepage;
	
	@Parameters({"browser","loginURL"})
	@BeforeTest
	public void setup(String browser,String LoginURL)
	{
		init(browser,LoginURL);
		
	}
	
	@DataProvider(name="testDataAddCard")
	public String[][] getTestData() throws IOException
	{
		
		String[][] Data = getData("testData", "loginsheet");
		return Data;
		
//		ArrayList<String[][]> listof2Darray = new ArrayList<String[][]>();    
//
//		listof2Darray.add(getData("testData", "loginsheet"));
////			result.add(0, getData("testData", "loginsheet"));
////			result.add(1, getData("testData", "AddCardDetails"));
//			
		
//			
	}
    @Test(dataProvider="testDataAddCard")
	public void TCC00VerifyAddcCard(String username,String pwd,String runmode,String ProductName ,String CardName)
	{
    	if (runmode.equalsIgnoreCase("n")) {
    		throw new SkipException("runmode is n");
			
		}
    	homepage = new RainbowHomePage(driver);
    	log.info("========starting verifyLogin test =======");
		boolean successLogin = homepage.loginApplication(username, pwd);
		assertEquals(successLogin, true);
		log.info("======== verifyLogin test finished  =======");
    	log.info("==========starting testcase TCC00VerifyAddcCard============");
    	String expectedCardName = homepage.addcard(ProductName,CardName);
    	takeScreenshot("addcard");
    	String cardName = homepage.firstcardName.getText();
    	Assert.assertEquals(cardName, expectedCardName);
    	log.info("==========finished testcase TCC00VerifyAddcCard============");
    	waitForElement(5, homepage.tmsProfile);
    	homepage.logout();
		
		
	}
    
//    @Test
//	public void verifyDiscardCard()
//	{
//		homepage= new RainbowHomePage(driver);
//		log.info("=========Starting TC001_verifyDiscardCard=========");
//		log.info("capturing First card name ");
//		String cardName = homepage.firstcardName.getText();
//		System.out.println(cardName);
//		waitForElement(5, homepage.moreoptions);
//		homepage.discard();
//	    Assert.assertFalse(homepage.firstCard.getText().equalsIgnoreCase(cardName));
//		
//		log.info("=========Finished TC001_verifyDiscardCard=========");
//		
//		
//	}
	
    @AfterClass
    public void endTest()
    {
    	
    	driver.quit();
    }

}
