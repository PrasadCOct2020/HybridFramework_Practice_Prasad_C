package pages;

import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.PredefinedActions;

public class ShoppingSummaryAddressPage extends PredefinedActions {

	public String getPageTitle() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		String pageHeader = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector(".page-heading")))
				.getText();

		return pageHeader;
	}

	public boolean areAddressSame() {

		WebDriverWait wait = new WebDriverWait(driver, 10);
		boolean flag = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#addressesAreEquals")))
				.isSelected();

		return flag;
	}

	public LinkedList<String> validateDeliveryAddress() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		List<WebElement> addressElements = wait
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#address_delivery>li")));
		LinkedList<String> deliveryAddress = new LinkedList<String>();
		for (WebElement element : addressElements) {
				String str = element.getText();
				if(!str.equals("YOUR DELIVERY ADDRESS") && !str.equals("Update")) {
					deliveryAddress.add(element.getText());
				}
		}

		return deliveryAddress;
	}
	
	public LinkedList<String> validateBillingAddress() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		List<WebElement> addressElements = wait
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#address_invoice>li")));
		LinkedList<String> billingAddress = new LinkedList<String>();
		for (WebElement element : addressElements) {
			String str = element.getText();
			if(!str.equals("YOUR BILLING ADDRESS") && !str.equals("Update")) {
				billingAddress.add(element.getText());
			}
	}

		return billingAddress;
	}
	
	public ShoppingShippingSummeryPage clickOnProceedToCheckOut() {
		WebDriverWait wait = new WebDriverWait(driver,10);
		
		/*wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".cart_navigation.clearfix>a.button.btn.btn-default.standard-checkout.button-medium"))).click();*/
		WebElement checkoutElement = driver.findElement(By.cssSelector(".cart_navigation.clearfix .button.btn.btn-default.button-medium>span"));

		JavascriptExecutor je = (JavascriptExecutor)driver;
		je.executeScript("arguments[0].click();", checkoutElement);
		
		return new ShoppingShippingSummeryPage();
	}

}
