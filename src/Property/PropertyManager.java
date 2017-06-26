package Property;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class PropertyManager {
	private InputStream FIS;
	private final String configName = "config.properties";
	private Properties prop;
	
	public PropertyManager(){
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		FIS = loader.getResourceAsStream(configName);
		System.out.println("Reading from: "+ configName);
	}
	
	public Properties getProperties(){
		prop = new Properties();
		try {
			prop.load(FIS);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
}
