package com.baird.bairdWarnerMainTests;

import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageFactoryObject.PFAccountLogin;




public class TestSpoon {
	
	WebDriver driver;
	PFAccountLogin objLogin;
	
	
@BeforeTest

	public void setup() throws Exception {
	DesiredCapabilities caps = DesiredCapabilities.chrome();
	caps.setCapability("version", "33");
	caps.setCapability("name", "baird");
	driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),caps);
	/*driver = new FirefoxDriver();*/
	/*driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);*/
	/*driver.get("http://www.bairdwarner.com/");*/
}

@AfterTest
public void tearDown() throws Exception {
	System.out.println("\nBrower close");
	driver.quit();
}


	@Test(priority=0)
	
	public void test_Account_Login_Page_Appear_Correct() throws Exception {
		
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
