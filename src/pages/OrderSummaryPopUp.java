package pages;

import java.io.FileNotFoundException;

import base.PredefinedActions;
import configuration.PropertyFilesPathConstants;
import utility.ReadPropertyFile;

public class OrderSummaryPopUp extends PredefinedActions {

	private ReadPropertyFile orderSummaryPopUpPropfile;
	private static OrderSummaryPopUp orderSummaryPopup;

	private OrderSummaryPopUp() {
		try {
			orderSummaryPopUpPropfile = new ReadPropertyFile(PropertyFilesPathConstants.ORDER_SUMMARY_POPUP_PROP_FILE_PATH);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static OrderSummaryPopUp getInstance() {
		if (orderSummaryPopup == null) {
			orderSummaryPopup = new OrderSummaryPopUp();
		}

		return orderSummaryPopup;
	}

	public double validateTotalProductPrice() {

		String tempTotalPrice = getElementText(orderSummaryPopUpPropfile.getLocator("totalPrice"),true);
		String totalPrice = tempTotalPrice.substring(1);
		double totalPriceNum = Double.parseDouble(totalPrice);

		return totalPriceNum;
	}

	public double validateShippingCost() {

		String shippingCost = getElementText(orderSummaryPopUpPropfile.getLocator("shippingCost"),false);
		String shippingTempCost = shippingCost.substring(1);
		double shippingCostNum = Double.parseDouble(shippingTempCost);

		return shippingCostNum;
	}

	public double validateTotalCost() {

		String totalCost = getElementText(orderSummaryPopUpPropfile.getLocator("totalCost"),false);
		String totalCostTemp = totalCost.substring(1);
		double totalCostNum = Double.parseDouble(totalCostTemp);
		return totalCostNum;
	}

	public ShoppingCartSummaryPage clickOnProceedToCheckoutPopUp() {

		clickOnElement(orderSummaryPopUpPropfile.getLocator("clickOnProceedToCheckoutButton"),false);

		return ShoppingCartSummaryPage.getInstance();
	}

}
