package pages;

import java.io.FileNotFoundException;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.PredefinedActions;
import configuration.PropertyFilesPathConstants;
import test_POJO.Product_POJO;
import utility.ReadPropertyFile;

public class ProductDetailsPage extends PredefinedActions {
	
	private ReadPropertyFile productPropFile;
	private static ProductDetailsPage prodDetailsPage;
	
	private ProductDetailsPage() {
		
		try {
			productPropFile = new ReadPropertyFile(PropertyFilesPathConstants.PRODUCT_DETAILS_PAGE_PROP_FILE_PATH);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static ProductDetailsPage getInstance() {
		if(prodDetailsPage==null) {
			prodDetailsPage = new ProductDetailsPage();
		}
		
		return prodDetailsPage;
	}

	public String getPageTitle() throws InterruptedException {
		Thread.sleep(3000);
		
		return getTitle();
	}

	public HashMap<String, String> validateProductDetails() {
		HashMap<String, String> productDetails = new HashMap<String, String>();

		String prodName = getElementText(productPropFile.getLocator("productName"),true);
		productDetails.put("Product Name", prodName);
		String desc = getElementText(productPropFile.getLocator("productDescription"),false);
		productDetails.put("Description", desc);
		String moreInfo = getElementText(productPropFile.getLocator("productMoreInfo"),false);
		productDetails.put("MoreInfo", moreInfo);
		String compo = getElementText(productPropFile.getLocator("productComposition"),false);
		productDetails.put("Composition", compo);
		String style = getElementText(productPropFile.getLocator("productStyle"),false);
		productDetails.put("Styles", style);
		String prop = getElementText(productPropFile.getLocator("productProperties"),false);
		productDetails.put("Properties", prop);
		String price = getElementText(productPropFile.getLocator("productPrice"),false);
		productDetails.put("Price", price);
		return productDetails;
	}

	public void setOrderDetails(Product_POJO product) {
		System.out.println("Entering Quantity");
		sendKeysToElement(productPropFile.getLocator("productQuantityInput"),product.getQuantity(),false);
		System.out.println("Entered Quantity: "+ product.getQuantity());
		System.out.println("Selecting Size");
		String selectedValue= selectValueFromDropDown(productPropFile.getLocator("productSizeInput"),"VisibleText",product.getSize(),false);
		System.out.println("Selected Size: "+ selectedValue);
		System.out.println("Selecting Colour");
		String colour = product.getColour();
		clickOnElement(productPropFile.getLocator("productColourInputA")+colour+productPropFile.getLocator("productColourInputB"),false);
		System.out.println("Selected Colour: "+ colour);
	}
	
	public OrderSummaryPopUp clickonAddToCartButton(){
		System.out.println("Click on Add to Cart button");
		scrollToElement(productPropFile.getLocator("addToCartButton"),true);
		clickOnElement(productPropFile.getLocator("addToCartButton"),false);
		
		return OrderSummaryPopUp.getInstance();
	}
	
		

}
