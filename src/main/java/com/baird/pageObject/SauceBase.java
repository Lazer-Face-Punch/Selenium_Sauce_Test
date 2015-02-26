package com.baird.pageObject;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.saucelabs.common.SauceOnDemandAuthentication;
import com.saucelabs.common.SauceOnDemandSessionIdProvider;
import com.saucelabs.testng.SauceOnDemandAuthenticationProvider;
import com.saucelabs.testng.SauceOnDemandTestListener;
import com.thoughtworks.selenium.CommandProcessor;
import com.thoughtworks.selenium.DefaultSelenium;


@Listeners({SauceOnDemandTestListener.class})
public abstract class SauceBase implements SauceOnDemandSessionIdProvider, SauceOnDemandAuthenticationProvider {

    protected SauceOnDemandAuthentication authentication;

    protected DefaultSelenium selenium;
    protected WebDriver driver;
	protected AccountLoginTest objLogin;

    /**
     * If the tests can rely on the username/key to be supplied by environment variables or the existence
     * of a ~/.sauce-ondemand file, then we don't need to specify them as parameters, just create a new instance
     * of {@link SauceOnDemandAuthentication} using the no-arg constructor.
     * @param username
     * @param key
     * @param os
     * @param browser
     * @param browserVersion
     * @param method
     * @param baseUrl
     * @throws Exception
     */
    @Parameters({"username", "key", "os", "browser", "browserVersion"/*, "baseUrl"*/})
    @BeforeMethod
    public void setUp(@Optional("Lazerfacepunch") String username,
                      @Optional("7e9aec3a-a1be-4ddc-89be-98f984145ea5") String key,
                      @Optional("XP") String os,
                      @Optional("firefox") String browser,
                      @Optional("32.0") String browserVersion,
                      /*@Optional("") String baseUrl,*/
                      Method method) throws Exception {

        if (StringUtils.isNotEmpty(username) && StringUtils.isNotEmpty(key)) {
           authentication = new SauceOnDemandAuthentication(username, key);
        } else {
           authentication = new SauceOnDemandAuthentication();
        }
        
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(browser);
        capabilities.setCapability("version", browserVersion);
        capabilities.setCapability("platform", Platform.valueOf(os));
        capabilities.setCapability("name", method.getName());
        this.driver = new RemoteWebDriver(
            new URL("http://" + authentication.getUsername() + ":" + authentication.getAccessKey() + "@ondemand.saucelabs.com:80/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://www.bairdwarner.com");
    }

    /*    this.selenium = new DefaultSelenium(
                "ondemand.saucelabs.com",
                80,
                "{\"username\": \"" + username + "\"," +
                        "\"access-key\": \"" + key + "\"," +
                        "\"os\": \"" + os + "\"," +
                        "\"browser\": \"" + browser + "\"," +
                        "\"browser-version\": \"" + browserVersion + "\"," +
                        "\"name\": \"BairdTest\"}",
                        "http://www.bairdwarner.com/");
        selenium.start();
    }*/

  
   public String getSessionId() {
        try {
            Field commandProcessorField = DefaultSelenium.class.getDeclaredField("commandProcessor");
            commandProcessorField.setAccessible(true);
            CommandProcessor commandProcessor = (CommandProcessor) commandProcessorField.get(selenium);
            Field f = commandProcessor.getClass().getDeclaredField("sessionId");
            f.setAccessible(true);
            Object id = f.get(commandProcessor);
            if (id != null) {
                return id.toString();
            }
        } catch (NoSuchFieldException e) {

        } catch (IllegalAccessException e) {

        }
        return null;

    }


	@AfterMethod
	public void tearDown() throws Exception {
		System.out.println("\nBrower close");
		driver.quit();
	}
	
    /**
     * {@inheritDoc}
     * @return
     */
    
    public SauceOnDemandAuthentication getAuthentication() {
        return authentication;
    }

}
