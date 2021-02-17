package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.PredefinedActions;

public class OrderConfirmationPage extends PredefinedActions {
	
	public String getPageTitle() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		String pageHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".page-heading")))
				.getText();

		return pageHeader;
	}

}
