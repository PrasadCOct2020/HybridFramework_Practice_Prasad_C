package pages;

import java.io.FileNotFoundException;

import base.PredefinedActions;
import configuration.PropertyFilesPathConstants;
import utility.ReadPropertyFile;

public class ConfirmOrderPage extends PredefinedActions {
	
	private ReadPropertyFile confirmOrderPropFile;
	private static ConfirmOrderPage confirmOrder;
	
	private ConfirmOrderPage() {
		try {
			confirmOrderPropFile = new ReadPropertyFile(PropertyFilesPathConstants.CONFIRM_ORDER_PAGE_PROP_FILE_PATH);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static ConfirmOrderPage getInstance() {
		if(confirmOrder==null) {
			confirmOrder = new ConfirmOrderPage();
		}
		return confirmOrder;
	}
	
	
	public String getPageTitle() {
		
		String pageHeader = getElementText(confirmOrderPropFile.getLocator("pageHeader"),true);
		return pageHeader;
	}
	
	public OrderConfirmationPage clickOnConfirmMyOrder() {
		
		clickOnElementJs(confirmOrderPropFile.getLocator("clickOnConfirmMyOrderButton"),false);
		
		return OrderConfirmationPage.getInstance();
	}

}
