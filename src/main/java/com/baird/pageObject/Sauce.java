package com.baird.pageObject;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;

import com.saucelabs.common.SauceOnDemandAuthentication;
import com.saucelabs.common.SauceOnDemandSessionIdProvider;
import com.saucelabs.testng.SauceOnDemandAuthenticationProvider;
import com.saucelabs.testng.SauceOnDemandTestListener;


@Listeners({SauceOnDemandTestListener.class})
public class Sauce implements SauceOnDemandSessionIdProvider, SauceOnDemandAuthenticationProvider {
	
	private Sauce(){
		
	}
	
	
   /* public static WebDriver getInstance() {
		return Instance;
	}

	public static void setInstance(WebDriver instance) {
		Instance = instance;
	}*/

	private static Sauce instance = new Sauce();
	
	public static Sauce getInstance()
	{
		return instance;
	}
	/**
     * Constructs a {@link com.saucelabs.common.SauceOnDemandAuthentication} instance using the supplied user name/access key.  To use the authentication
     * supplied by environment variables or from an external file, use the no-arg {@link com.saucelabs.common.SauceOnDemandAuthentication} constructor.
     */
    public static SauceOnDemandAuthentication authentication = new SauceOnDemandAuthentication(System.getenv("SAUCE_USERNAME"), System.getenv("SAUCE_ACCESS_KEY"));

    /**
     * ThreadLocal variable which contains the  {@link WebDriver} instance which is used to perform browser interactions with.
     */
    /*public static ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();*/

    /**
     * ThreadLocal variable which contains the Sauce Job Id.
     */
    private static ThreadLocal<String> sessionId = new ThreadLocal<String>();

    /**
     * DataProvider that explicitly sets the browser combinations to be used.
     *
     * @param testMethod
     * @return
     */
    
    @DataProvider(name = "hardCodedBrowsers", parallel = true)
    public static Object[][] sauceBrowserDataProvider(Method testMethod) {
        return new Object[][]{
                new Object[]{"internet explorer", "11", "Windows 8.1"},
                new Object[]{"safari", "6", "OSX 10.8"},
        };
    }

    /**
     * /**
     * Constructs a new {@link RemoteWebDriver} instance which is configured to use the capabilities defined by the browser,
     * version and os parameters, and which is configured to run against ondemand.saucelabs.com, using
     * the username and access key populated by the {@link #authentication} instance.
     *
     * @param browser Represents the browser to be used as part of the test run.
     * @param version Represents the version of the browser to be used as part of the test run.
     * @param os Represents the operating system to be used as part of the test run.
     * @return
     * @throws MalformedURLException if an error occurs parsing the url
     */


	public WebDriver createDriver(String browser, String version, String os) throws MalformedURLException{
		WebDriver webDriver = null;
		DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.BROWSER_NAME, browser);
        if (version != null) {
            capabilities.setCapability(CapabilityType.VERSION, version);
        }
        capabilities.setCapability(CapabilityType.PLATFORM, os);
        capabilities.setCapability("name", "Sauce Sample Test");
        webDriver.set(new RemoteWebDriver(
                new URL("http://" + authentication.getUsername() + ":" + authentication.getAccessKey() + "@ondemand.saucelabs.com:80/wd/hub"),
                capabilities));
        sessionId.set(((RemoteWebDriver) getDriver()).getSessionId().toString());
        return webDriver.get();
	}
	
	 public static WebDriver getDriver() {
	        System.out.println("WebDriver" + webDriver.get());
	        return webDriver.get();
	    }

	 public SauceOnDemandAuthentication getAuthentication() {
	        return authentication;
	}

	public String getSessionId() {
        return sessionId.get();
    }
	public static void tearDown() throws Exception{
		System.out.println("\nBrowser close");
		Driver.Instance.quit();
	}
}
