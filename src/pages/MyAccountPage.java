package pages;


import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.WebElement;

import base.PredefinedActions;
import configuration.PropertyFilesPathConstants;
import utility.ReadPropertyFile;

public class MyAccountPage extends PredefinedActions {

	private ReadPropertyFile myAccountLocators;
	private static MyAccountPage myAccount;

	private MyAccountPage() {
		try {
			myAccountLocators = new ReadPropertyFile(PropertyFilesPathConstants.MY_ACCOUNT_PROP_FILE_PATH);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static MyAccountPage getInstance() {
		if (myAccount == null) {
			myAccount = new MyAccountPage();
		}

		return myAccount;
	}

	public String validateAccountName() {
		String accountName = getElementText(myAccountLocators.getLocator("accountName"),true);
		return accountName;
	}

	public LinkedList<String> validateTabs() {
		List<WebElement> menuTabs = getElements(myAccountLocators.getLocator("tabList"),false);
		LinkedList<String> tabNames = new LinkedList<String>();
		for (WebElement element : menuTabs) {
			String str = getElementText(element);
			tabNames.add(str.toUpperCase());
		}
		return tabNames;
	}


	public LinkedList<String> validateOptionsUnderTab(String tabName) {
		mouseHover(myAccountLocators.getLocator("optionsUnderTabPartA")+tabName+myAccountLocators.getLocator("optionsUnderTabPartB"),true); 
		List<WebElement> optionsUnderTabElements = getElements(myAccountLocators.getLocator("optionsUnderTabElementsA")+tabName+myAccountLocators.getLocator("optionsUnderTabElementsB"),true);
		LinkedList<String> optionsUnderTab = new LinkedList<String>();
		for (WebElement element : optionsUnderTabElements) {
			String str = getElementText(element);
			optionsUnderTab.add(str.toUpperCase());
		}

		return optionsUnderTab;
	}

	public ProductsPage clickOnTab(String tabName) {

		clickOnElement(myAccountLocators.getLocator("tabClickA")+tabName+myAccountLocators.getLocator("tabClickB"),false);

		return ProductsPage.getInstance();
	}

}
