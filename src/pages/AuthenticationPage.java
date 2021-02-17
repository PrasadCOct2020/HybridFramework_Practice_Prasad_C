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
	
	public void enterEmailForLogin(String email) {
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#email"))).sendKeys(email);
	}
	
	public void enterPassword(String password) {
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#passwd"))).sendKeys(password);
		
	}
	
	public MyAccountPage clickSignIn() {
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#SubmitLogin"))).click();
		return new MyAccountPage();
	}
	
	
	

}
