package pages;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.WebElement;

import base.PredefinedActions;
import configuration.PropertyFilesPathConstants;
import utility.ReadPropertyFile;

public class ShoppingSummaryAddressPage extends PredefinedActions {

	private static ShoppingSummaryAddressPage addressSummary;
	private ReadPropertyFile addressSummaryPagePropFile;

	private ShoppingSummaryAddressPage() {
		try {
			addressSummaryPagePropFile = new ReadPropertyFile(PropertyFilesPathConstants.ADDRESS_SUMMARY_PROP_FILE_PATH);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static ShoppingSummaryAddressPage getInstance() {
		if (addressSummary == null) {
			addressSummary = new ShoppingSummaryAddressPage();
		}

		return addressSummary;
	}

	public String getPageTitle() {

		String pageHeader = getElementText(addressSummaryPagePropFile.getLocator("pageHeading"),true);

		return pageHeader;
	}

	public boolean areAddressSame() {

		boolean flag = false;

		String attributeValue = getElementAttribute(addressSummaryPagePropFile.getLocator("areAddressSameCheckBox"),
				"class",false);
		if (attributeValue.equals("checked")) {
			flag = true;
		}

		return flag;
	}

	public LinkedList<String> validateDeliveryAddress() {

		List<WebElement> addressElements = getElements(addressSummaryPagePropFile.getLocator("deliveryAddress"),false);
		LinkedList<String> deliveryAddress = new LinkedList<String>();
		for (WebElement element : addressElements) {
			String str = element.getText();
			if (!str.equals("YOUR DELIVERY ADDRESS") && !str.equals("Update")) {
				deliveryAddress.add(element.getText());
			}
		}

		return deliveryAddress;
	}

	public LinkedList<String> validateBillingAddress() {

		List<WebElement> addressElements = getElements(addressSummaryPagePropFile.getLocator("billingAddress"),false);
		LinkedList<String> billingAddress = new LinkedList<String>();
		for (WebElement element : addressElements) {
			String str = element.getText();
			if (!str.equals("YOUR BILLING ADDRESS") && !str.equals("Update")) {
				billingAddress.add(element.getText());
			}
		}

		return billingAddress;
	}

	public ShoppingShippingSummaryPage clickOnProceedToCheckOut() {

		clickOnElementJs(addressSummaryPagePropFile.getLocator("clickOnProceedToCheckOutButton"),true);

		return ShoppingShippingSummaryPage.getInstance();
	}

}
