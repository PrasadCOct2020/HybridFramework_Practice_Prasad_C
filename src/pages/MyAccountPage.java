package pages;

import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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
	
	public LinkedList<String> validateTabs() {
		
		WebDriverWait wait = new WebDriverWait(driver,10);
		List<WebElement> menuTabs = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//ul[@class = 'sf-menu clearfix menu-content sf-js-enabled sf-arrows']/li/a")));
		LinkedList<String> tabNames = new LinkedList<String>();
		for(WebElement element: menuTabs) {
			String str = element.getText();
			tabNames.add(str.toUpperCase());
		}
		return tabNames;
	}
	
	public LinkedList<String> validateOptionsUnderTab(String tabName) {
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//ul[@class = 'sf-menu clearfix menu-content sf-js-enabled sf-arrows']/li/a[text() = '"+tabName+"']"))).build().perform();
		WebDriverWait wait = new WebDriverWait(driver,20);
		List<WebElement> optionsUnderTabElements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//ul[@class = 'sf-menu clearfix menu-content sf-js-enabled sf-arrows']//a[text() = '"+tabName+"']//parent::li/ul[@class = 'submenu-container clearfix first-in-line-xs']/li/a")));
		LinkedList<String> optionsUnderTab = new LinkedList<String>();
		for(WebElement element: optionsUnderTabElements) {
			String str = element.getText();
			optionsUnderTab.add(str.toUpperCase());
		}
		
		return optionsUnderTab;
	}
	
	public ProductsPage clickOnTab(String tabName) {
		
		
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class = 'sf-menu clearfix menu-content sf-js-enabled sf-arrows']/li/a[text() = '"+tabName+"']"))).click();
		
		return new ProductsPage();
	}
	

}
