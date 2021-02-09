package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.PredefinedActions;

public class HomePage extends PredefinedActions {
	
	public AuthenticationPage clickOnSignIn() {
		WebDriverWait wait = new WebDriverWait(driver,10);
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".header_user_info>a")));
		element.click();
		
		return new AuthenticationPage();
	}
	

}
