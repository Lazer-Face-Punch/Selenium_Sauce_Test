package com.baird.bairdWarnerMainTests;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.baird.pageObject.AccountLogin;

public class TestAccountLogin {
	WebDriver driver;
	AccountLogin objLogin;
	
	
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
  @Test
  public void test_Account_Login_Page_Appear_Correct() {
	  //Create login page object
	  objLogin = new AccountLogin(driver);
	  
	  objLogin.loginToBairdAccount("brenden@activewebsite.com", "active");
	  
  }
}


