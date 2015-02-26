package com.baird.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountLogin {
	WebDriver driver;
	By loginPopup = By.linkText("Log In");
	/*By popupBox = By.id("account_login_form");*/
	By email = By.cssSelector("div.fancybox-inner > #account_login_form > #account_login_ajax > fieldset.clearfix > div.form-group > #wua-email");
	By password = By.cssSelector("div.fancybox-inner > #account_login_form > #account_login_ajax > fieldset.clearfix > div.form-group > #wua-pass");
	By login = By.cssSelector("div.fancybox-inner > #account_login_form > #account_login_ajax > fieldset.clearfix > input[name=\"Submit\"]");
	
	

public AccountLogin(WebDriver driver){
	this.driver = driver;
}
//Open Login page popup
public void clickLoginPopup(){
	driver.findElement(loginPopup).click();
}


//Set user name into Text Box
public void setUserName(String strUserName){
	driver.findElement(email).sendKeys(strUserName);
}

//Set Password
public void setPassword(String strPassword){
	driver.findElement(password).sendKeys(strPassword);
}
//Click Login Button
public void clickLogin(){
	driver.findElement(login).click();
}

/**

 * This POM method will be exposed in test case to login in the application

 * @param strUserName

 * @param strPasword

 * @return

 */

public void loginToBairdAccount(String strUserName, String strPassword){
	//Click account menu item
	this.clickLoginPopup();
	
	//Target Popup
	/*this.targetPopup();*/
	
	//Fill user name
	this.setUserName(strUserName);
	
	//Fill password
	this.setPassword(strPassword);
	//Click Login Button
	this.clickLogin();
	
}
}