package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestPropertiesHelper {

    public static Properties getProperties() throws IOException {
        Properties properties = new Properties();
        //properties.load(new FileInputStream("test.properties"));
        properties.load(ClassLoader.getSystemClassLoader().getResourceAsStream("testSauce.properties"));
        return properties;
    }

    public static boolean testOnSauce(Properties properties) {
        return Boolean.valueOf(properties.getProperty("run.on.sauce"));
    }
    
    public static boolean browserAndOs (Properties properties) {
    	return Boolean.valueOf(properties.getProperty("browserAndOs"));
    }
}
