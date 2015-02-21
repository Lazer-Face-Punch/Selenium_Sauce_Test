package com.baird.pageObject;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


public abstract class BaseTestSuperr {
	protected WebDriver driver;
	protected AccountLoginTest objLogin;

	/* protected String baseUrl = "http://www.bairdwarner.com"; */


@BeforeMethod
	public void setUpBeforeTestClass()throws Exception{
	
	Driver.Initialize();
}
			
		/*driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://www.bairdwarner.com");*/
	

	/*
	 * @BeforeTest public void setUp() throws Exception{ driver = new
	 * FirefoxDriver(); driver.manage().timeouts().implicitlyWait(10,
	 * TimeUnit.SECONDS); driver.get(baseUrl);
	 * 
	 * }
	 * 
	 * @AfterTest public void closebrowser() throws Exception {
	 * System.out.println("\nBrower close"); driver.quit(); }
	 */

	/*
	 * void setUpBeforeTestMethod(){ driver.get(baseUrl); }
	 */

	/* abstract void tearDownAfterTestMethod(); */

	/* abstract void tearDownAfterTestClass(); */

	/*
	 * @BeforeTest public void baseSetup() throws Exception {
	 * 
	 * DesiredCapabilities caps = DesiredCapabilities.firefox();
	 * caps.setCapability("version", "33"); caps.setCapability("name", "baird");
	 * driver = new RemoteWebDriver(new
	 * URL("http://localhost:4444/wd/hub"),caps); }
	 */

	@AfterMethod
	public void tearDownAfterTestMethod() throws Exception {
		Driver.tearDown();
	}
}
