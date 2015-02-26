package com.baird.bairdWarnerMainTests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageFactoryObject.PFAccountLogin;



public class TestAccountLoginFactory {

	WebDriver driver;
	PFAccountLogin objLogin;

	
@BeforeTest

	public void setup(){
	driver = new FirefoxDriver();
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	driver.get("http://www.bairdwarner.com/");
}

@AfterTest
public void closebrowser() {
	System.out.println("\nBrower close");
	driver.quit();
}
	@Test(priority=0)
	
public void test_Account_Login_Page_Appear_Correct(){
		
		driver.get("http://www.bairdwarner.com/");
		
		//Creates login page object
		objLogin = new PFAccountLogin(driver);
		//Log's into account
		objLogin.loginToBairdAccount("brenden@activewebsite.com", "active");
	
		//Returns welcome title and asserts true
		String loginWelcomeTitle = objLogin.getLoginWelcome();
		System.out.println(loginWelcomeTitle);
		Assert.assertTrue(loginWelcomeTitle.equals("Welcome, brenden thornsberry"));
		
		}
}
