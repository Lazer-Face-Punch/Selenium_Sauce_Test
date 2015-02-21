package drivers;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.saucelabs.common.SauceOnDemandAuthentication;

public class DriverHelper {
	
	
	public enum browserOsCombinations{
		FF15XP,
		FF14LINUX,
		IE7XP,
		IE8XP,
		IE9VISTA,
		SAFARI5OSX,
		ANDROID4,
		IPHONE5,
		IPAD5,
		FF14VISTA,
		OPERA12VISTA
	}

    public static WebDriver getRemoteWebDriver(Method testName,
                                               SauceOnDemandAuthentication authentication, String browserAndOs) throws MalformedURLException {
    	DesiredCapabilities capability = setBrowserAndOs(browserAndOs);
    	capability.setCapability("name", testName.getName());
        return new RemoteWebDriver(
                new URL("http://" + authentication.getUsername() + ":" + authentication.getAccessKey() + "@ondemand.saucelabs.com:80/wd/hub"),
                capability);
    }

    public static WebDriver getLocalWebDriver() {
    	return new FirefoxDriver();
    }
    
    private static DesiredCapabilities setBrowserAndOs(String browserAndOs) {
    	// Defaults to Firefox if no string matches are found
    	DesiredCapabilities capability = DesiredCapabilities.firefox();
    	if (browserAndOs == null)
    		return capability;
    	
    	if (browserAndOs.equals(browserOsCombinations.FF15XP.toString())) {
    		capability = DesiredCapabilities.firefox();
    		capability.setVersion("15");
    		capability.setPlatform(Platform.XP);
    	} else if (browserAndOs.equals(browserOsCombinations.FF14LINUX.toString())) {
    		capability = DesiredCapabilities.firefox();
    		capability.setPlatform(Platform.LINUX);
    		capability.setCapability("version", "14");
    	} else if (browserAndOs.equals(browserOsCombinations.IE7XP.toString())) {
    		capability = DesiredCapabilities.internetExplorer();
    		capability.setPlatform(Platform.XP);
    		capability.setCapability("version", "7");
    	} else if (browserAndOs.equals(browserOsCombinations.IE8XP.toString())) {
    		capability = DesiredCapabilities.internetExplorer();
    		capability.setPlatform(Platform.XP);
    		capability.setCapability("version", "8");
    	} else if (browserAndOs.equals(browserOsCombinations.IE9VISTA.toString())) {
    		capability = DesiredCapabilities.internetExplorer();
    		capability.setPlatform(Platform.VISTA);
    		capability.setCapability("version", "9");
    	} else if (browserAndOs.equals(browserOsCombinations.SAFARI5OSX.toString())) {
    		capability = DesiredCapabilities.safari();
    		capability.setPlatform(Platform.MAC);
    		capability.setCapability("version", "5");
    	} else if (browserAndOs.equals(browserOsCombinations.ANDROID4.toString())) {
    		capability = DesiredCapabilities.android();
    		capability.setCapability("version", "4");
    	} else if (browserAndOs.equals(browserOsCombinations.IPHONE5.toString())) {
    		capability = DesiredCapabilities.iphone();
    		capability.setCapability("version", "5");
    	} else if (browserAndOs.equals(browserOsCombinations.IPAD5.toString())) {
    		capability = DesiredCapabilities.ipad();
    		capability.setCapability("version", "5");
    	} else if (browserAndOs.equals(browserOsCombinations.FF14VISTA.toString())) {
    		capability = DesiredCapabilities.firefox();
    		capability.setPlatform(Platform.VISTA);
    		capability.setCapability("version", "14");
    	} else if (browserAndOs.equals(browserOsCombinations.OPERA12VISTA.toString())) {
    		capability = DesiredCapabilities.opera();
    		capability.setPlatform(Platform.VISTA);
    		capability.setCapability("version", "12");
    	}
    	
        return capability;
    }

}