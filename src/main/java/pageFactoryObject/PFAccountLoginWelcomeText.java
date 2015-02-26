package pageFactoryObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PFAccountLoginWelcomeText {
WebDriver driver;

@FindBy(className="account-user-name")
WebElement welcomeText;



public PFAccountLoginWelcomeText(WebDriver driver){
	this.driver = driver;
	
	//This intElement method will create all WebElements
	
	PageFactory.initElements(driver, this);
}
	
	//Verify logged in Welcome/Name
		public String getLoginWelcome(){
			new WebDriverWait(driver, 10)
			.until(ExpectedConditions.visibilityOf(welcomeText));
			return welcomeText.getText();
		}
		
		/**
		 
	     * This POM method will be exposed in test case to login in the application
	 
	     * @param strUserName
	 
	     * @param strPasword
	 
	     * @return
	 
	     */
		
		public void verifyLoginToBairdAccount(){
			
			this.getLoginWelcome();
		}
}
