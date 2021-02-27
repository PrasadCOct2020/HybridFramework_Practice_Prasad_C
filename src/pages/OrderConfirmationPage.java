package pages;

import java.io.FileNotFoundException;

import base.PredefinedActions;
import configuration.PropertyFilesPathConstants;
import utility.ReadPropertyFile;

public class OrderConfirmationPage extends PredefinedActions {

	private ReadPropertyFile orderConfirmationPropFile;
	private static OrderConfirmationPage orderConfirmation;

	private OrderConfirmationPage() {
		try {
			orderConfirmationPropFile = new ReadPropertyFile(
					PropertyFilesPathConstants.ORDER_CONFIRMATION_PROP_FILE_PATH);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static OrderConfirmationPage getInstance() {
		if (orderConfirmation == null) {
			orderConfirmation = new OrderConfirmationPage();
		}

		return orderConfirmation;
	}

	public String getPageHeader() {

		return getElementText(orderConfirmationPropFile.getLocator("pageHeader"),true);
	}
	
	public String getOrderConfirmationMsg() {
		return getElementText(orderConfirmationPropFile.getLocator("orderConfirmationText"),false);
	}

}
