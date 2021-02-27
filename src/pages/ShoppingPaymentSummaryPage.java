package pages;

import java.io.FileNotFoundException;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.PredefinedActions;
import configuration.PropertyFilesPathConstants;
import utility.ReadPropertyFile;

public class ShoppingPaymentSummaryPage extends PredefinedActions {

	private ReadPropertyFile paymentSummaryPropFile;
	private static ShoppingPaymentSummaryPage shoppingPaymentSummeryPage;

	private ShoppingPaymentSummaryPage() {
		try {
			paymentSummaryPropFile = new ReadPropertyFile(PropertyFilesPathConstants.PAYMENT_SUMMARY_PROP_FILE_PATH);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static ShoppingPaymentSummaryPage getInstance() {
		if (shoppingPaymentSummeryPage == null) {
			shoppingPaymentSummeryPage = new ShoppingPaymentSummaryPage();
		}
		return shoppingPaymentSummeryPage;
	}

	public String getPageTitle() throws InterruptedException {
		Thread.sleep(5000);

		String pageHeader = getElementText(paymentSummaryPropFile.getLocator("pageHeading"),true);

		return pageHeader;
	}

	public ConfirmOrderPage selectPaymentOption(String option) {

		clickOnElement(paymentSummaryPropFile.getLocator("paymentOptionA")+option+paymentSummaryPropFile.getLocator("paymentOptionB"),false);
		
		return ConfirmOrderPage.getInstance();
	}

}
