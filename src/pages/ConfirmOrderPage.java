package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.PredefinedActions;

public class ConfirmOrderPage extends PredefinedActions {
	
	public String getPageTitle() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		String pageHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".page-heading")))
				.getText();

		return pageHeader;
	}
	
	public OrderConfirmationPage clickOnConfirmOrder() {
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='button btn btn-default button-medium']")));
		JavascriptExecutor je = (JavascriptExecutor)driver;
		je.executeScript("arguments[0].click();", element);
		
		return new OrderConfirmationPage();
	}

}
