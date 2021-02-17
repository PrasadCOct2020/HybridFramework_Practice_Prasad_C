package pages;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.PredefinedActions;
import test_POJO.Product_POJO;

public class ProductDetailsPage extends PredefinedActions {

	public String getPageTitle() throws InterruptedException {
		Thread.sleep(5000);
		return driver.getTitle();
	}

	public HashMap<String, String> validateProductDetails() {
		HashMap<String, String> productDetails = new HashMap<String, String>();
		WebDriverWait wait = new WebDriverWait(driver, 10);

		String prodName = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div[@class = 'pb-center-column col-xs-12 col-sm-4']/h1")))
				.getText();
		productDetails.put("Product Name", prodName);
		String desc = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='short_description_content']/p")))
				.getText();
		productDetails.put("Description", desc);
		String moreInfo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class = 'rte']/p")))
				.getText();
		productDetails.put("MoreInfo", moreInfo);
		String compo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr[@class = 'odd']/td[2]")))
				.getText();
		productDetails.put("Composition", compo);
		String style = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr[@class = 'even']/td[2]")))
				.getText();
		productDetails.put("Styles", style);
		String prop = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr[@class = 'odd'][2]/td[2]")))
				.getText();
		productDetails.put("Properties", prop);
		String price = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id = 'our_price_display']")))
				.getText();
		productDetails.put("Price", price);
		return productDetails;
	}

	public void setOrderDetails(Product_POJO product) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		System.out.println("Entering Quantity");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id = 'quantity_wanted']"))).clear();
		driver.findElement(By.xpath("//input[@id = 'quantity_wanted']")).sendKeys(product.getQuantity());
		System.out.println("Entered Quantity: "+ product.getQuantity());
		System.out.println("Selecting Size");
		WebElement element = wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//select[@class = 'form-control attribute_select no-print']")));
		Select oselect = new Select(element);
		oselect.selectByVisibleText(product.getSize());
		System.out.println("Selected Size: "+ product.getSize());
		System.out.println("Selecting Colour");
		String colour = product.getColour();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//ul[@id = 'color_to_pick_list']//a[@title='"+colour+"']"))).click();
		System.out.println("Selected Colour: "+ colour);
	}
	
	public OrderSummeryPopUp clickonAddToCartButton(){
		WebDriverWait wait = new WebDriverWait(driver, 10);
		System.out.println("Click on Add to Cart button");
		WebElement buttonElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class = 'exclusive']")));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", buttonElement);
		buttonElement.click();
		
		return new OrderSummeryPopUp();
	}
	
		

}
