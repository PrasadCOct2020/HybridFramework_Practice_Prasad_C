package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.PredefinedActions;

public class ProductsPage extends PredefinedActions{
	
	public int findNumberOfProducts() {
		WebDriverWait wait = new WebDriverWait(driver,20);
		List<WebElement> productList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//ul[@class = 'product_list grid row']/li")));
		int numberOfProducts = productList.size();
		return numberOfProducts;
	}
	
	public ProductDetailsPage selectionOfProduct(String productName) {
		WebDriverWait wait = new WebDriverWait(driver,10);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class = 'product_list grid row']//a[@title = '"+productName+"'][@class = 'product-name']")));
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", element);
		element.click();
		
		
		return new ProductDetailsPage();
	}

}
