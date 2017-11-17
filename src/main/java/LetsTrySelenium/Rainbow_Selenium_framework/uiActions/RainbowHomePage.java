package LetsTrySelenium.Rainbow_Selenium_framework.uiActions;

import java.awt.Desktop.Action;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.TimeoutException;

import LetsTrySelenium.Rainbow_Selenium_framework.testBase.TestBase;
import LetsTrySelenium.Rainbow_Selenium_framework.testBase.testBaseInterFace;


public class RainbowHomePage extends TestBase implements testBaseInterFace   {

	public static final Logger log = Logger.getLogger(RainbowHomePage.class.getName());
	WebDriver driver;
	
	@FindBy(how=How.XPATH,using=".//*[@id='emailAddressGhost']")
	public WebElement emailGhost;
	@FindBy(how=How.XPATH,using=".//*[@name='pass_temp']")
	public WebElement pwdghost;
	@FindBy(how=How.XPATH,using=".//*[@id='emailAddress']")
	public WebElement email;
	@FindBy(how=How.XPATH,using=".//*[@id='password']")
	public WebElement pwd;
	@FindBy(how=How.XPATH,using=".//*[@id='rbCompanyLogo']")
	public WebElement companyLogo;
	@FindBy(how=How.XPATH,using=".//*[@id='signIn']")
	public WebElement loginButton;
	@FindBy(how=How.ID,using="addingnewcard-btn")
	public WebElement addCardsButton;
	@FindBy(how=How.XPATH,using=".//*[@id='addingCard-0']/a")
	public WebElement firstCard;
	@FindBy(how=How.XPATH,using=".//*[@id='addingCardchild-0']/a")
	public WebElement cardtilelink;
	@FindBy(how=How.XPATH,using=".//span[contains(text(),'Add')]")
	public WebElement addcard;
	@FindBy(how=How.XPATH,using=".//*[@id='tmsProfilePic']")
	public WebElement tmsProfile;
	@FindBy(how=How.XPATH,using=".//*[@id='rb-branding-logout']")
	public WebElement logout;
	@FindBy(how=How.XPATH,using=".//*[@id='card-wrapper']/li[1]/.//*[@class='rb-cd-header-right ng-binding']/span")
	 public WebElement moreoptions;
	@FindBy(how=How.XPATH,using=".//*[@id='card-wrapper']/li[1]/.//span[contains(text(),'Discard')]")
	 WebElement discard;
	@FindBy(how=How.XPATH,using=".//span[contains(text(),'Yes, Discard')]")
	public  WebElement yesdiscard;
	@FindBy(how=How.XPATH,using=".//*[@id='card-wrapper']/li[1]/.//span[contains(text(),'Duplicate')]")
	 WebElement duplicate;
	@FindBy(how=How.XPATH,using=".//*[@id='card-wrapper']/li[1]/.//a[1]")
	 public WebElement firstcardName;
	@FindBy(how=How.XPATH,using=".//*[@id='addCardPreviewCardHolder']/.//a[1]")
	 WebElement previewcardName;
	
	
	
	public RainbowHomePage(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
		this.driver=driver;
		
		// TODO Auto-generated constructor stub
	}
	
	
	public boolean loginApplication(String username,String password)
	{
		emailGhost.click();
		log.info("entering correct email id");
		email.sendKeys(username);
		pwdghost.click();
		log.info("entering correct password");
		pwd.sendKeys(password);
		log.info("clicking on login button");
		loginButton.click();
		
		return companyLogo.isDisplayed();
		
		
	}
	
	 
	public String addcard(String productName,String cardName)
	{
		
	
		addCardsButton.click();
		log.info("clicking on add card button and object is:"+addCardsButton.toString());
		driver.findElement(By.xpath(".//span[contains(text(),'"+productName+"')]")).click();
		//firstCard.click();
		log.info("clicking on first card and object is:"+firstCard.toString());
		
		//cardtilelink.click();
		driver.findElement(By.xpath(".//span[contains(text(),'"+cardName+"')]/parent::a")).click();
		log.info("clicking on tile_card name and object is:"+cardtilelink.toString());
		WebDriverWait waitForAddButton = new WebDriverWait(driver, 5);
		waitForAddButton.until(ExpectedConditions.visibilityOf(addcard));
		String previewCardname = previewcardName.getText();
		addcard.click();
		log.info("adding card to home page and objcet is:"+addcard.toString());
		
		return previewCardname;
			
		
	}
	
	public void duplicate()
	{
		
		moreoptions.click();
		log.info("clicked on moreoptions of crad:"+moreoptions.toString());
		Actions act = new Actions(driver);
		act.moveToElement(duplicate).build().perform();
		duplicate.click();
		log.info("clicked on discard of crad:"+duplicate.toString());
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		log.info("waited for given time");
		
		
		
	}
	
	
	public void discard()
	{
		try{
		moreoptions.click();
		log.info("clicked on moreoptions of crad:"+moreoptions.toString());
		Actions act = new Actions(driver);
		act.moveToElement(discard).build().perform();
		discard.click();
		log.info("clicked on discard of crad:"+discard.toString());
		waitForElement(yesdiscard,5);
		log.info("waited till object visible");
		yesdiscard.click();
		log.info("clicked on yes discard of flipped crad:"+discard.toString());
		}catch (TimeoutException e) {
			waitForElement(yesdiscard,5);
			// TODO: handle exception
		}
		
		
	}
	public void logout()
	{
		
		tmsProfile.click();
           logout.click();
		
		
	}


	public void waitForElement(WebElement element,int timeout) {
		

		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.visibilityOf(element));
		
          }


	

}
