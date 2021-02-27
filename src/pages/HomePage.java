package pages;

import java.io.FileNotFoundException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.PredefinedActions;
import configuration.PropertyFilesPathConstants;
import utility.ReadPropertyFile;

public class HomePage extends PredefinedActions {
	private ReadPropertyFile homeLocators;
	private static HomePage homePage;
	private HomePage() {
		try {
			homeLocators = new ReadPropertyFile(PropertyFilesPathConstants.HOME_PAGE_PROP_FILE_PATH);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static HomePage getInstance() {
		if(homePage==null) {
			homePage = new HomePage();
		}
		
		return homePage;
	}
	
	public AuthenticationPage clickOnSignIn() {
		clickOnElement(homeLocators.getLocator("signInButton"), true);
		
		return AuthenticationPage.getInstance();
	}
	

}
