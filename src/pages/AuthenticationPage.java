package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.PredefinedActions;

public class AuthenticationPage extends PredefinedActions{
	
	public void enterEmail(String email) {
		
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#email_create"))).sendKeys(email);
	}
	
	public AccountCreation clickOnCreateAccountButton() {
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#SubmitCreate"))).click();
		
		return new AccountCreation();	
	}
	
	public String getPageTitle() {
		String pageTitle = driver.getTitle();
		
		return pageTitle;
	}
	
	

}
