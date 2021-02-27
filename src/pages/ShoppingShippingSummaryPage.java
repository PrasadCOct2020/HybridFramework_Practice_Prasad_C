package pages;

import java.io.FileNotFoundException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.PredefinedActions;
import configuration.PropertyFilesPathConstants;
import utility.ReadPropertyFile;

public class ShoppingShippingSummaryPage extends PredefinedActions {

	private ReadPropertyFile shippingSummaryPropFile;
	private static ShoppingShippingSummaryPage shippingSummary;

	private ShoppingShippingSummaryPage() {
		try {
			shippingSummaryPropFile = new ReadPropertyFile(PropertyFilesPathConstants.SHIPPING_SUMMARY_PROP_FILE_PATH);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static ShoppingShippingSummaryPage getInstance() {
		if (shippingSummary == null) {
			shippingSummary = new ShoppingShippingSummaryPage();
		}

		return shippingSummary;
	}

	public String getPageTitle() {
		
		String pageHeader = getElementText(shippingSummaryPropFile.getLocator("pageHeading"),true);

		return pageHeader;
	}

	public boolean isAgreeTermsChecked() {
		boolean flag = false;
		String checkboxStatus = getElementAttribute(shippingSummaryPropFile.getLocator("agreeTermsCheckbox"),"class",false);

		if (checkboxStatus.equals("checked")) {
			flag = true;
		}

		return flag;
	}
	
	public void agreeTermsOfService() {
		
		clickOnElement(shippingSummaryPropFile.getLocator("agreeTermsCheckbox"),false);
	}

	public ShoppingPaymentSummaryPage clickOnProceedToCheckOut() {
		clickOnElementJs(shippingSummaryPropFile.getLocator("clickOnProceedToCheckOutButton"),false);

		return ShoppingPaymentSummaryPage.getInstance();
	}
	
	public String getPopUpText() {
		String text = getElementText(shippingSummaryPropFile.getLocator("popUpError"),true);
		
		return text;
	}
	
	public void closeErrorPopUp() {
		clickOnElement(shippingSummaryPropFile.getLocator("popUpCloseButton"),true);
	}

}
