package pageFactoryObject;

import java.lang.reflect.Method;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.saucelabs.common.SauceOnDemandAuthentication;
import com.saucelabs.testng.SauceOnDemandTestListener;


@Listeners({SauceOnDemandTestListener.class})
public class Testing {
	public SauceOnDemandAuthentication authentication = new SauceOnDemandAuthentication();
	WebDriver driver;
	PFAccountLogin objLogin;
	
	
	   @Parameters({"username", "key", "os", "browser", "browserVersion"})
	    @BeforeMethod
	    public void setUp(@Optional("Lazerfacepunch") String username,
	                      @Optional("7e9aec3a-a1be-4ddc-89be-98f984145ea5") String key,
	                      @Optional("XP") String os,
	                      @Optional("firefox") String browser,
	                      @Optional("32") String browserVersion,
	                      Method method) throws Exception {

	        // Choose the browser, version, and platform to test
	        DesiredCapabilities capabilities = new DesiredCapabilities();
	        capabilities.setBrowserName(browser);
	        capabilities.setCapability("version", browserVersion);
	        capabilities.setCapability("platform", Platform.valueOf(os));
	        capabilities.setCapability("name", method.getName());
	        // Create the connection to Sauce Labs to run the tests
	        this.driver = new RemoteWebDriver(
	                new URL("http://" + username + ":" + key + "@ondemand.saucelabs.com:80/wd/hub"),
	                capabilities);
	   }
/*@BeforeTest

	public void setup(){
	driver = new FirefoxDriver();
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	driver.get("http://www.bairdwarner.com/");
}*/


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

	@AfterTest
	public void closebrowser() {
		System.out.println("\nBrower close");
		driver.quit();
	}
}
