package tools;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import utilities.TestProperties;
import utilities.TestSessionHelper;
import utilities.TestSessionInformation;

import com.baird.pageObject.SauceAccountLoginTest;
import com.saucelabs.common.SauceOnDemandAuthentication;
import com.saucelabs.common.SauceOnDemandSessionIdProvider;

public class SaucelabsSeleniumTest implements SauceOnDemandSessionIdProvider {
	protected SauceAccountLoginTest objLogin;
    public TestProperties testProperties;
    
    public SauceOnDemandAuthentication authentication = new SauceOnDemandAuthentication(
			"Lazerfacepunch", "7e9aec3a-a1be-4ddc-89be-98f984145ea5");

   /* @Rule
    public SauceOnDemandTestWatcher resultReportingTestWatcher = new SauceOnDemandTestWatcher(this, authentication);*/

   /* @Rule
    public TestName testName = new TestName();*/

    public WebDriver driver;
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
    }
	
    @AfterMethod
    public void tearDown() throws Exception {
    	driver.quit();
    }
}
