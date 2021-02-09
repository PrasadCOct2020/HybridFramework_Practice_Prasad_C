package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PredefinedActions {
	protected static WebDriver driver; 
	
	public static void start(String uRl) {
		System.setProperty("webdriver.chrome.driver", "./resources/windows/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(uRl);
	}
	
	public static void tearDown() {
		driver.close();
	}

}
