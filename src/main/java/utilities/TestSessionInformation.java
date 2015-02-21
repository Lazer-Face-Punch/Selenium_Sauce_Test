package utilities;

import org.openqa.selenium.WebDriver;

import java.util.Properties;

public class TestSessionInformation {

    private WebDriver driver;
    private String sessionId;
    private TestProperties testProperties;

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public TestProperties getTestProperties() {
        return testProperties;
    }

    public void setTestProperties(TestProperties testProperties) {
        this.testProperties = testProperties;
    }
}
