package pages;
import java.io.FileNotFoundException;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.WebElement;

import base.PredefinedActions;
import configuration.PropertyFilesPathConstants;
import utility.ReadPropertyFile;

public class ShoppingCartSummaryPage extends PredefinedActions {

	private static ShoppingCartSummaryPage shoppingCartSummary;
	private ReadPropertyFile shoppingCartPropFile;

	private ShoppingCartSummaryPage() {

		try {
			shoppingCartPropFile = new ReadPropertyFile(PropertyFilesPathConstants.CART_SUMMARY_PROP_FILE_PATH);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static ShoppingCartSummaryPage getInstance() {
		if (shoppingCartSummary == null) {
			shoppingCartSummary = new ShoppingCartSummaryPage();
		}

		return shoppingCartSummary;
	}

	public String getPageTitle() throws InterruptedException {
		Thread.sleep(3000);
		return getTitle();
	}

	public LinkedList<String> validateChevrons() {

		List<WebElement> chevronElements = getElements(shoppingCartPropFile.getLocator("chevrons"),true);
		LinkedList<String> chevrons = new LinkedList<String>();
		for (WebElement element : chevronElements) {
			String str = getElementText(element);
			chevrons.add(str);
		}

		return chevrons;
	}

	public HashMap<String, String> validateOrderSummery() {
		HashMap<String, String> orderSummary = new HashMap<String, String>();
		String prodName = getElementText(shoppingCartPropFile.getLocator("productName"),false);
		orderSummary.put("ProductName", prodName);
		String colourSizeTemp = getElementText(shoppingCartPropFile.getLocator("colourSize"),false);
		// Color : Orange, Size : S
		// Color : Orange
		String[] array = colourSizeTemp.split(",");
		String colStr1 = array[0];
		String sizStr2 = array[1];
		String colour = colStr1.substring(8);
		String size = sizStr2.substring(8);
		orderSummary.put("Colour", colour);
		orderSummary.put("Size", size);

		String prodPricing = getElementText(shoppingCartPropFile.getLocator("productPricing"),false);
		orderSummary.put("Total Product Pricing", prodPricing);
		String totalPrice = getElementText(shoppingCartPropFile.getLocator("totalPrice"),false);
		orderSummary.put("Total Price", totalPrice);
		String quantity = getElementAttribute(shoppingCartPropFile.getLocator("productQuantity"),"value",false);
		orderSummary.put("Quantity", quantity);

		return orderSummary;

	}

	public void scrollDown() {
		scrollByAxis("0","500");
	}

	public ShoppingSummaryAddressPage clickOnProceedToCheckOut() {
		clickOnElementJs(shoppingCartPropFile.getLocator("clickOnProceedToCheckOutButton"),true);

		return ShoppingSummaryAddressPage.getInstance();
	}
}
