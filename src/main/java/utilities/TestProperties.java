package utilities;

import java.util.Properties;

public class TestProperties {

    private Properties properties;

    public TestProperties(Properties properties) {
        this.properties = properties;
    }

    public String getString(String key) {
        return (String) this.properties.get(key);
    }

    public String getDomain() {
        return (String) this.properties.get("domain.url");
    }
}
