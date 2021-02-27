package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadPropertyFile {

	private Properties prop;

	public ReadPropertyFile(String path) throws FileNotFoundException {
		File file = new File(path);
		FileInputStream inputStream = new FileInputStream(file);
		prop = new Properties();
		try {
			prop.load(inputStream);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public String getLocator(String key) {

		return prop.getProperty(key);
	}

}
