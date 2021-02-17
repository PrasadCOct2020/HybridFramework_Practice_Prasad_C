package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.PredefinedActions;

public class OrderSummeryPopUp extends PredefinedActions{
	
	public double validateTotalProductPrice() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		
		String tempTotalPrice = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class = 'ajax_block_products_total']"))).getText();
		String totalPrice = tempTotalPrice.substring(1);
		double totalPriceNum = Double.parseDouble(totalPrice);
		
		return totalPriceNum;
	}
	
	public double validateShippingCost() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		String shippingCost = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class = 'ajax_cart_shipping_cost']"))).getText();
		String shippingTempCost = shippingCost.substring(1);
		double shippingCostNum = Double.parseDouble(shippingTempCost);
		
		return shippingCostNum;
	}
	
	public double validateTotalCost() {
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		String totalCost = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class = 'ajax_block_cart_total']"))).getText();
		String totalCostTemp = totalCost.substring(1);
		double totalCostNum = Double.parseDouble(totalCostTemp);
		return totalCostNum;
	}
	
	public ShoppingCartSummaryPage clickOnProceedToCheckoutPopUp() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class = 'btn btn-default button button-medium']"))).click();
		
		return new ShoppingCartSummaryPage();
	}
	


}
