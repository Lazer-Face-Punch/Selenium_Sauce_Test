package pageFactoryObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PFAccountLogin {

	   /**
	 
     * All WebElements are identified by @FindBy annotation
 
     */
	
	/*By loginPopup = By.linkText("Log In");
	By popupBox = By.id("account_login_form");
	By email = By.cssSelector("div.fancybox-inner > #account_login_form > #account_login_ajax > fieldset.clearfix > div.form-group > #wua-email");
	By password = By.cssSelector("div.fancybox-inner > #account_login_form > #account_login_ajax > fieldset.clearfix > div.form-group > #wua-pass");
	By login = By.cssSelector("div.fancybox-inner > #account_login_form > #account_login_ajax > fieldset.clearfix > input[name=\"Submit\"]");*/
 
	WebDriver driver;
	
	@FindBy(linkText= "Log In")
	WebElement AccountLoginButton;
	
	@FindBy(id= "account_login_form")
	WebElement popupBox;
	
	@FindBy(css= "div.fancybox-inner > #account_login_form > #account_login_ajax > fieldset.clearfix > div.form-group > #wua-email")
	WebElement email;
	
	@FindBy(css= "div.fancybox-inner > #account_login_form > #account_login_ajax > fieldset.clearfix > div.form-group > #wua-pass")
	WebElement password;
	
	@FindBy(css= "div.fancybox-inner > #account_login_form > #account_login_ajax > fieldset.clearfix > input[name=\"Submit\"]")
	WebElement login;
	
	@FindBy(className="account-user-name")
	WebElement welcomeText;
	
	
	public PFAccountLogin(WebDriver driver){
		this.driver = driver;
		
		//This initElements method will create all WebElements
		
		PageFactory.initElements(driver, this);
		
	}
	//Click Top Nav Account Login Button
	public void clickAccountLogin(){
		AccountLoginButton.click();
	}

	//Set user name into Text Box
	public void setUserName(String strUserName){
		email.sendKeys(strUserName);
	}

	//Set Password
	public void setPassword(String strPassword){
		password.sendKeys(strPassword);
	}
	//Click Login Button
	public void clickLogin(){
		login.click();
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
	
	public void loginToBairdAccount(String strUserName,String strPassword){
	
		//Click account link
		this.clickAccountLogin();
		
		//Fill Username
		this.setUserName(strUserName);
		
		//Fill Password
		this.setPassword(strPassword);
		
		//Click submit
		this.clickLogin();
		
		//Verify Welcome Text
		this.getLoginWelcome();
		
	}
}
