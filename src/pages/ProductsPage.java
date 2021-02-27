package pages;


import java.io.FileNotFoundException;
import java.util.List;

import org.openqa.selenium.WebElement;

import base.PredefinedActions;
import configuration.PropertyFilesPathConstants;
import utility.ReadPropertyFile;

public class ProductsPage extends PredefinedActions{
	private ReadPropertyFile productLocators;
	private static ProductsPage productsPage;
	
	private ProductsPage() {
		try {
			productLocators = new ReadPropertyFile(PropertyFilesPathConstants.PRODUCTS_PAGE_PROP_FILE_PATH);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static ProductsPage getInstance() {
		if(productsPage==null) {
			productsPage= new ProductsPage();
		}
		
		return productsPage;
	}
	
	public int findNumberOfProducts() {
		List<WebElement> productList = getElements(productLocators.getLocator("listOfProductsOnPage"),true);
		int numberOfProducts = productList.size();
		return numberOfProducts;
	}
	
	public ProductDetailsPage selectionOfProduct(String productName) {
		
		scrollToElement(productLocators.getLocator("productLocPartA")+productName+productLocators.getLocator("productLocPartB"),false);
		clickOnElement(productLocators.getLocator("productLocPartA")+productName+productLocators.getLocator("productLocPartB"),false);
		
		return ProductDetailsPage.getInstance();
	}

}
