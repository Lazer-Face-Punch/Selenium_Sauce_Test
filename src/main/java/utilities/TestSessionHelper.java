package utilities;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.saucelabs.common.SauceOnDemandAuthentication;

import drivers.DriverHelper;

public class TestSessionHelper {

    public static TestSessionInformation getTestSessionInformation(Method testName, SauceOnDemandAuthentication authentication) throws IOException {

        WebDriver driver;
        String sessionId = null;

        Properties testProperties = getTestProperties();
        if (TestPropertiesHelper.testOnSauce(testProperties)) {
            driver = DriverHelper.getRemoteWebDriver(testName, authentication, System.getProperty("browserAndOs"));
            sessionId = ((RemoteWebDriver) driver).getSessionId().toString();
        } else {
            driver = new FirefoxDriver();
        }
        driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);

        TestSessionInformation testSessionInformation = new TestSessionInformation();
        testSessionInformation.setDriver(driver);
        testSessionInformation.setSessionId(sessionId);
        testSessionInformation.setTestProperties(new TestProperties(testProperties));

        return testSessionInformation;
    }

    private static Properties getTestProperties() throws IOException {
        return TestPropertiesHelper.getProperties();
    }
}
