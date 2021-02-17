package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.PredefinedActions;

public class ShoppingShippingSummeryPage extends PredefinedActions {

	public String getPageTitle() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		String pageHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".page-heading")))
				.getText();

		return pageHeader;
	}

	public boolean agreeTerms() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".checkbox>#uniform-cgv"))).click();
		boolean flag = false;
		String checkboxStatus = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".checkbox>#uniform-cgv>span")))
				.getAttribute("class");

		if (checkboxStatus.equals("checked")) {
			flag = true;
		}

		return flag;
	}

	public ShoppingPaymentSummeryPage clickOnProceedToCheckOut() {
		WebDriverWait wait = new WebDriverWait(driver, 10);

		/*
		 * wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(
		 * ".cart_navigation.clearfix>a.button.btn.btn-default.standard-checkout.button-medium"
		 * ))).click();
		 */
		WebElement checkoutElement = driver
				.findElement(By.cssSelector(".cart_navigation.clearfix .button.btn.btn-default.button-medium>span"));

		JavascriptExecutor je = (JavascriptExecutor) driver;
		je.executeScript("arguments[0].click();", checkoutElement);

		return new ShoppingPaymentSummeryPage();
	}

}
