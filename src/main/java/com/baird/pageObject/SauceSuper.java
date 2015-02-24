package com.baird.pageObject;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import utilities.TestProperties;
import utilities.TestSessionHelper;
import utilities.TestSessionInformation;

import com.saucelabs.common.SauceOnDemandAuthentication;
import com.saucelabs.common.SauceOnDemandSessionIdProvider;
import com.saucelabs.testng.SauceOnDemandAuthenticationProvider;

/*@Listeners ({SauceOnDemandTestListener.class})*/
public abstract class SauceSuper implements SauceOnDemandSessionIdProvider, SauceOnDemandAuthenticationProvider {
	protected WebDriver driver;
	protected SauceAccountLoginTest objLogin;
	

	
	  public TestProperties testProperties;
	    
	    public SauceOnDemandAuthentication authentication = new SauceOnDemandAuthentication(
				"Lazerfacepunch", "7e9aec3a-a1be-4ddc-89be-98f984145ea5");


	  
	    public String sessionId;	
		
	    public String getSessionId() {
	        return this.sessionId;
	    }
	    
	    @BeforeMethod
	    public void setupTest(Method testName) throws Exception {
	        TestSessionInformation testSessionInformation = TestSessionHelper.getTestSessionInformation(testName, authentication);
	        sessionId = testSessionInformation.getSessionId();
	        driver = testSessionInformation.getDriver();
	        testProperties = testSessionInformation.getTestProperties();
	        driver.get(this.testProperties.getDomain() + "/trdt");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    }
		
	    @AfterMethod
	    public void tearDown() throws Exception {
	    	driver.quit();
	    }
	    
	    
	    public SauceOnDemandAuthentication getAuthentication(){
	    	return authentication;
	    }
	}

	/* protected String baseUrl = "http://www.bairdwarner.com"; */

	 /*  @DataProvider(name = "hardCodedBrowsers", parallel = true)
	    public static Object[][] sauceBrowserDataProvider(Method testMethod) {
	        return new Object[][]{
	                new Object[]{"internet explorer", "11", "Windows 8.1"},
	                new Object[]{"safari", "6", "OSX 10.8"},
	        };
	    }
@BeforeMethod
	public void setUpBeforeTestClass()throws Exception{
	Sauce.webDriver();
}*/

/*@AfterMethod
	public void tearDownAfterTestClass()throws Exception{
Sauce.tearDown();
	}*/

