package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.PredefinedActions;

public class MyAccountPage extends PredefinedActions{
	
	public String validateAccountName() {
		WebDriverWait wait = new WebDriverWait(driver,10);
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@class = 'account']/span")));
		String accountName =element.getText();
		return accountName;
	}
	

}
