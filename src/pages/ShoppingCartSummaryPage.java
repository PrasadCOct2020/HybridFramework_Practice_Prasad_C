package pages;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.PredefinedActions;

public class ShoppingCartSummaryPage extends PredefinedActions {

	public String getPageTitle() throws InterruptedException {
		Thread.sleep(5000);
		return driver.getTitle();
	}

	public LinkedList<String> validateChevrons() {

		WebDriverWait wait = new WebDriverWait(driver, 10);
		List<WebElement> chevronElements = wait.until(ExpectedConditions
				.visibilityOfAllElementsLocatedBy(By.xpath("//ul[@class = 'step clearfix']/li/span")));
		LinkedList<String> chevrons = new LinkedList<String>();
		for (WebElement element : chevronElements) {
			String str = element.getText();
			chevrons.add(str);
		}

		return chevrons;
	}
	
	public HashMap<String, String> validateOrderSummery() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		HashMap<String,String> orderSummary = new HashMap<String,String>();
		String prodName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cart_description>p.product-name>a"))).getText();
		orderSummary.put("ProductName", prodName);
		String colourSizeTemp = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@class = 'cart_description']/p//following-sibling::small/a"))).getText();
		//Color : Orange, Size : S
		//Color : Orange
		String[] array = colourSizeTemp.split(",");
		String colStr1 = array[0];
		String sizStr2 = array[1];
		String colour = colStr1.substring(8);
		String size = sizStr2.substring(8);
		orderSummary.put("Colour", colour);
		orderSummary.put("Size", size);
		
		String prodPricing = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cart_total_price>td#total_product"))).getText();
		orderSummary.put("Total Product Pricing", prodPricing);
		String totalPrice = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cart_total_price>td#total_price_container>span"))).getText();
		orderSummary.put("Total Price", totalPrice);
		String quantity = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cart_quantity.text-center>input.cart_quantity_input.form-control.grey"))).getAttribute("value");
		orderSummary.put("Quantity", quantity);
		
		return orderSummary;
		
	}

	public void scrollDown() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
	}


	public ShoppingSummaryAddressPage clickOnProceedToCheckOut() {
		WebDriverWait wait = new WebDriverWait(driver,10);
		
		/*wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".cart_navigation.clearfix>a.button.btn.btn-default.standard-checkout.button-medium"))).click();*/
		WebElement checkoutElement = driver.findElement(By.cssSelector(".cart_navigation.clearfix .button.btn.btn-default.button-medium>span"));

		JavascriptExecutor je = (JavascriptExecutor)driver;
		je.executeScript("arguments[0].click();", checkoutElement);
		
		return new ShoppingSummaryAddressPage();
	}
}
