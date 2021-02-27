package testscripts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.PredefinedActions;
import pages.AccountCreation;
import pages.AuthenticationPage;
import pages.ConfirmOrderPage;
import pages.HomePage;
import pages.MyAccountPage;
import pages.OrderConfirmationPage;
import pages.OrderSummaryPopUp;
import pages.ProductDetailsPage;
import pages.ProductsPage;
import pages.ShoppingCartSummaryPage;
import pages.ShoppingPaymentSummaryPage;
import pages.ShoppingShippingSummaryPage;
import pages.ShoppingSummaryAddressPage;
import test_POJO.CreateAccount_Pojo;
import test_POJO.Product_POJO;
import utility.ReadTestDataXLS;

public class AccountCreationTestCase {
	@BeforeMethod
	void setupBrowser() {
		System.out.println("Step 1: Launch chrome browser and open link");
		PredefinedActions.start("http://automationpractice.com");
	}

	@Test(dataProvider = "testData")
	void createNewAccount(CreateAccount_Pojo createAccountPojo) throws InterruptedException {
		HomePage homepage = HomePage.getInstance();
		System.out.println("Step 2: Click on Sign-in button");
		AuthenticationPage authPage = homepage.clickOnSignIn();
		String pageTitle = authPage.getPageTitle();
		System.out.println("Step 5: Validate if user has navigated successfully to AUTHENTICATION page");
		Assert.assertEquals(pageTitle, "Login - My Store");
		CreateAccount_Pojo pojo = new CreateAccount_Pojo();
		System.out.println("Step 3: Entered e-mail in e-mail address field");
		pojo.setEmail("automation_7@gmail.com");
		authPage.enterEmail(pojo.getEmail());
		System.out.println("Step 4: Click on Create Account button");
		AccountCreation accountCreation = authPage.clickOnCreateAccountButton();
		Thread.sleep(5000);
		String label = accountCreation.validatePage();
		System.out.println("Step 5: Validate if user has successfully navigated to Create An Account page");
		Assert.assertEquals(label, "CREATE AN ACCOUNT");
		System.out.println("Step 6: Populate Title");
		pojo.setGender("M");
		accountCreation.populateGender(pojo.getGender());
		System.out.println("Step 7: Populate First Name");
		pojo.setFirstName("James");
		accountCreation.populateFirstName(pojo.getFirstName());
		System.out.println("Step 8: Populate Last Name");
		pojo.setLastName("Anderson");
		accountCreation.populateLastName(pojo.getLastName());
		System.out.println("Step 9: Populate Password");
		pojo.setPassword("Amara_12345");
		accountCreation.populatePassword(pojo.getPassword());
		System.out.println("Step 10: Populate Birth-Day");
		pojo.setBirthDay("30");
		accountCreation.populateBirthDay(pojo.getBirthDay());
		System.out.println("Step 10: Populate Birth-Month");
		pojo.setBirthMonth("10");
		accountCreation.populateBirthMonth(pojo.getBirthMonth());
		System.out.println("Step 10: Populate Birth-Year");
		pojo.setBirthYear("1979");
		accountCreation.populateBirthYear(pojo.getBirthYear());
		System.out.println("Step 11: Populate address");
		pojo.setAddress("12th Main Street Lakeshore Ave");
		accountCreation.populateAddress(pojo.getAddress());
		System.out.println("Step 12: Populate City");
		pojo.setCity("Willington");
		accountCreation.populateCity(pojo.getCity());
		System.out.println("Step 13: Populate State");
		pojo.setState("California");
		accountCreation.populateState(pojo.getState());
		System.out.println("Step 14: Populate Zip");
		pojo.setZip("12345");
		accountCreation.populateZip(pojo.getZip());
		System.out.println("Step 15: Populate Mobile Phone");
		pojo.setmPhone("9768574899");
		accountCreation.populateMobile(pojo.getmPhone());
		System.out.println("Step 16: Click on register button");
		MyAccountPage myaccount = accountCreation.clickOnRegisterButton();
		String accountName = myaccount.validateAccountName();
		System.out.println("Step 17: Validate if correct user name is being displayed besides sign-out button");
		String expectedAccountName = pojo.getFirstName() + " " + pojo.getLastName();
		Assert.assertEquals(accountName, expectedAccountName);
	}

	@Test
	void testValidationRule() throws InterruptedException {
		System.out.println(
				"Verify validation rule on create an account page and all required fields are not populated and user clicks on register button");
		HomePage homepage = HomePage.getInstance();
		System.out.println("Step 2: Click on Sign-in button");
		AuthenticationPage authPage = homepage.clickOnSignIn();
		String pageTitle = authPage.getPageTitle();
		System.out.println("Step 5: Validate if user has navigated successfully to AUTHENTICATION page");
		Assert.assertEquals(pageTitle, "Login - My Store");
		CreateAccount_Pojo pojo = new CreateAccount_Pojo();
		System.out.println("Step 3: Entered e-mail in e-mail address field");
		pojo.setEmail("automation_80@gmail.com");
		authPage.enterEmail(pojo.getEmail());
		System.out.println("Step 4: Click on Create Account button");
		AccountCreation accountCreation = authPage.clickOnCreateAccountButton();
		Thread.sleep(5000);
		String label = accountCreation.validatePage();
		System.out.println("Step 5: Validate if user has successfully navigated to Create An Account page");
		Assert.assertEquals(label, "CREATE AN ACCOUNT");
		System.out.println("Step 6: Click on register button without populating any required fields");
		accountCreation.clickOnRegisterButton();

		List<String> expectedErrorDetails = new ArrayList<String>();
		expectedErrorDetails.add("There are 8 errors");
		expectedErrorDetails.add("You must register at least one phone number.");
		expectedErrorDetails.add("lastname is required.");
		expectedErrorDetails.add("firstname is required.");
		expectedErrorDetails.add("passwd is required.");
		expectedErrorDetails.add("address1 is required.");
		expectedErrorDetails.add("city is required.");
		expectedErrorDetails.add("The Zip/Postal code you've entered is invalid. It must follow this format: 00000");
		expectedErrorDetails.add("This country requires you to choose a State.");

		ArrayList<String> actualErrorDetails = accountCreation.getValidationErrorDetails();
		System.out.println("Step 7: Validate error message displayed on the page");
		Assert.assertEquals(actualErrorDetails, expectedErrorDetails);
		System.out.println("Actual Error: " + actualErrorDetails);
		System.out.println("Expected Error: " + expectedErrorDetails);
	}

	@Test
	void userLogin() {
		HomePage homepage = HomePage.getInstance();
		System.out.println("Step 2: Click on sign-in button");
		AuthenticationPage authenticationPage = homepage.clickOnSignIn();
		System.out.println("Step 3: Populate email");
		authenticationPage.enterEmailForLogin("automation_framework23@gmail.com");
		System.out.println("Step 4: Populate password");
		authenticationPage.enterPassword("Test_5454");
		System.out.println("Step 5: Click on sign-in button");
		MyAccountPage myAccount = authenticationPage.clickSignIn();
		String actualAccountName = myAccount.validateAccountName();
		String expectedAccountName = "Anna Hathway";
		Assert.assertEquals(actualAccountName, expectedAccountName);
		System.out.println("Step 6: Validate visible tabs on My Account page");
		LinkedList<String> expectedTabNames = new LinkedList<String>();
		expectedTabNames.add("WOMEN");
		expectedTabNames.add("DRESSES");
		expectedTabNames.add("T-SHIRTS");
		LinkedList<String> actualTabNames = myAccount.validateTabs();
		System.out.println("Printing visible tab names");
		System.out.println(actualTabNames);
		Assert.assertEquals(actualTabNames, expectedTabNames);
		System.out.println("Step 7: Validate options under given tab");
		String tabName = "Women";
		System.out.println("Hover over " + tabName + " tab");
		LinkedList<String> actualOptionsUnderTab = myAccount.validateOptionsUnderTab(tabName);
		LinkedList<String> expectedOptionsUnderTab = new LinkedList<String>();
		expectedOptionsUnderTab.add("TOPS");
		expectedOptionsUnderTab.add("DRESSES");
		System.out.println("And validate options available");
		System.out.println(actualOptionsUnderTab);
		Assert.assertEquals(actualOptionsUnderTab, expectedOptionsUnderTab);
	}

	@Test(dataProvider = "productDetails")
	void placeOrder(Product_POJO products) throws InterruptedException {
		HomePage homepage = HomePage.getInstance();
		System.out.println("Step 2: Click on sign-in button");
		AuthenticationPage authenticationPage = homepage.clickOnSignIn();
		System.out.println("Step 3: Populate email");
		authenticationPage.enterEmailForLogin("automation_framework23@gmail.com");
		System.out.println("Step 4: Populate password");
		authenticationPage.enterPassword("Test_5454");
		System.out.println("Step 5: Click on sign-in button");
		MyAccountPage myAccount = authenticationPage.clickSignIn();
		System.out.println("Step 6: Click on tab: " + products.getTabName());
		ProductsPage productsPage = myAccount.clickOnTab(products.getTabName());
		int numberOfProducts = productsPage.findNumberOfProducts();
		System.out.println("Number of products displayed on the page: " + numberOfProducts);
		Assert.assertTrue(numberOfProducts >= 1);
		ProductDetailsPage detailspage = productsPage.selectionOfProduct(products.getProductName());
		String pageName = detailspage.getPageTitle();
		System.out.println("Page name: " + pageName);
		Assert.assertTrue(pageName.contains(products.getProductName()));
		System.out.println("Step 7: Validate details on product page");
		HashMap<String, String> productDetails = detailspage.validateProductDetails();
		SoftAssert sa = new SoftAssert();
		System.out.println("Product Name: " + productDetails.get("Product Name"));
		sa.assertEquals(productDetails.get("Product Name"), products.getProductName());
		System.out.println("Product Description: " + productDetails.get("Description"));
		sa.assertEquals(productDetails.get("Description"), products.getDescription());
		System.out.println("Product More Information: " + productDetails.get("MoreInfo"));
		sa.assertEquals(productDetails.get("MoreInfo"), products.getMoreInfo());
		System.out.println("Product Composition: " + productDetails.get("Composition"));
		sa.assertEquals(productDetails.get("Composition"), products.getCompositions());
		System.out.println("Product Style: " + productDetails.get("Styles"));
		sa.assertEquals(productDetails.get("Styles"), products.getStyles());
		System.out.println("Product Properties: " + productDetails.get("Properties"));
		sa.assertEquals(productDetails.get("Properties"), products.getProperties());
		System.out.println("Product Price: " + productDetails.get("Price"));
		sa.assertEquals(productDetails.get("Price"), products.getPrice());
		sa.assertAll();
		System.out.println("Step 8: Populating order details");
		detailspage.setOrderDetails(products);
		System.out.println("Step 9: Click on add to cart button");
		detailspage.clickonAddToCartButton();
		OrderSummaryPopUp summeryPopUp = OrderSummaryPopUp.getInstance();
		System.out.println("Step 10: Validate total products cost on order summery");
		double actualTotalProductPrice = summeryPopUp.validateTotalProductPrice();
		System.out.println("Actual Total Price: " + "$" + actualTotalProductPrice);
		String expectedTempProductPrice = products.getPrice();
		String expectedProductPrice = expectedTempProductPrice.substring(1);
		double expectedProductPriceNum = Double.parseDouble(expectedProductPrice);
		String expectedQuantityTemp = products.getQuantity();
		double expectedQuantity = Double.parseDouble(expectedQuantityTemp);
		double expectedTotalProductPriceNum = expectedProductPriceNum * expectedQuantity;
		sa.assertEquals(actualTotalProductPrice, expectedTotalProductPriceNum);
		System.out.println("Step 11: Validate shipping cost on order summery");
		double actualShippingCost = summeryPopUp.validateShippingCost();
		System.out.println("Actual Shipping Cost: " + "$" + actualShippingCost);
		String expectedShippingCostTemp = products.getShippingCost();
		String expectedShippingCost = expectedShippingCostTemp.substring(1);
		double expectedShippingCostNum = Double.parseDouble(expectedShippingCost);
		sa.assertEquals(actualShippingCost, expectedShippingCostNum);
		System.out.println("Step 12: Validate grand total cost on order summery");
		double actualTotalCost = summeryPopUp.validateTotalCost();
		System.out.println("Actual Total Cost: " + "$" + actualTotalCost);
		double expectedTotalCost = expectedProductPriceNum * expectedQuantity + expectedShippingCostNum;
		sa.assertEquals(actualTotalCost, expectedTotalCost);
		sa.assertAll();
		System.out.println("Step 13: Click on proceed to checkout button");
		ShoppingCartSummaryPage cartSummary = summeryPopUp.clickOnProceedToCheckoutPopUp();
		String pageTitle = cartSummary.getPageTitle();
		System.out.println("Step 14: Validate if user has successfully navigated to Order page");
		Assert.assertTrue(pageTitle.contains("Order"));
		System.out.println("Step 15: Validate order summary page elements");
		LinkedList<String> actualChevronsDisplayed = cartSummary.validateChevrons();
		LinkedList<String> expectedChevrons = new LinkedList<String>();
		expectedChevrons.add("01. Summary");
		expectedChevrons.add("02. Sign in");
		expectedChevrons.add("03. Address");
		expectedChevrons.add("04. Shipping");
		expectedChevrons.add("05. Payment");
		System.out.println("Validate chevrons displayed on order page");
		System.out.println(actualChevronsDisplayed);
		Assert.assertEquals(actualChevronsDisplayed, expectedChevrons);
		System.out.println("Validate product and pricing details on summer details page");

		HashMap<String, String> expectedOrderDetails = new HashMap<String, String>();
		expectedOrderDetails.put("ProductName", products.getProductName());
		expectedOrderDetails.put("Colour", products.getColour());
		expectedOrderDetails.put("Size", products.getSize());
		String totalProductsPricing = "$" + Double.toString(expectedTotalProductPriceNum);
		expectedOrderDetails.put("Total Product Pricing", totalProductsPricing);
		String totalPrice = "$" + Double.toString(expectedTotalCost);
		expectedOrderDetails.put("Total Price", totalPrice);
		expectedOrderDetails.put("Quantity", products.getQuantity());

		HashMap<String, String> actualOrderDetails = cartSummary.validateOrderSummery();
		Assert.assertEquals(actualOrderDetails, expectedOrderDetails);
		System.out.println("Step 16: Navigate to Address Summary page");
		cartSummary.scrollDown();
		ShoppingSummaryAddressPage addressSummery = cartSummary.clickOnProceedToCheckOut();
		String pageHeading = addressSummery.getPageTitle();
		Assert.assertEquals(pageHeading, "ADDRESSES");
		System.out.println(
				"Validate if- Use the delivery address as the billing address. checkbox is checked by default");
		boolean flag = addressSummery.areAddressSame();
		Assert.assertTrue(flag);
		System.out.println("Validate if delivery and billing address are valid and are same");
		LinkedList<String> actualDeliveryAddress = addressSummery.validateDeliveryAddress();
		LinkedList<String> actualBillingAddress = addressSummery.validateBillingAddress();
		LinkedList<String> expectedAddress = new LinkedList<String>();
		expectedAddress.add("Anna Hathway");
		expectedAddress.add("122st Main Street");
		expectedAddress.add("Wellington, California 67557");
		expectedAddress.add("United States");
		expectedAddress.add("2147483647");
		sa.assertEquals(actualDeliveryAddress, expectedAddress);
		sa.assertTrue(actualDeliveryAddress.equals(actualBillingAddress));
		sa.assertAll();
		System.out.println("Step 17: Navigate to Shipping Summary page");
		ShoppingShippingSummaryPage shippingSummary = addressSummery.clickOnProceedToCheckOut();
		pageHeading = shippingSummary.getPageTitle();
		Assert.assertEquals(pageHeading, "SHIPPING");
		System.out.println("Step 18: Check terms of service status and accept");
		boolean checkboxStatus = shippingSummary.isAgreeTermsChecked();
		Assert.assertFalse(checkboxStatus);
		System.out.println("Terms of service checkbox NOT checked by default");
		System.out.println("Step 18.1: Click on Proceed to checkout without accepting terms");
		shippingSummary.clickOnProceedToCheckOut();
		String popUpText = shippingSummary.getPopUpText();
		Assert.assertEquals(popUpText, products.getPopUpError());
		System.out.println("Pop-up is displayed with following error message:- " + popUpText);
		System.out.println("Closing error pop-up");
		shippingSummary.closeErrorPopUp();
		System.out.println("Accepting Terms of service agreement");
		shippingSummary.agreeTermsOfService();
		System.out.println("Step 19: Navigate to Payment Summery page");
		ShoppingPaymentSummaryPage paymentSummery = shippingSummary.clickOnProceedToCheckOut();
		pageHeading = paymentSummery.getPageTitle();
		Assert.assertEquals(pageHeading, "PLEASE CHOOSE YOUR PAYMENT METHOD");
		System.out.println("Step 20: Select payment option");
		String paymentOption = "Pay by bank wire";
		ConfirmOrderPage confirmOrder = paymentSummery.selectPaymentOption(paymentOption);
		pageHeading = confirmOrder.getPageTitle();
		Assert.assertEquals(pageHeading, "ORDER SUMMARY");
		System.out.println("Step 21: Click on Confirm Order Button");
		OrderConfirmationPage completeOrder = confirmOrder.clickOnConfirmMyOrder();
		pageHeading = completeOrder.getPageHeader();
		Assert.assertEquals(pageHeading, "ORDER CONFIRMATION");
		String text = completeOrder.getOrderConfirmationMsg();
		Assert.assertEquals(text, "Your order on My Store is complete.");
		System.out.println("Order placed successfully!");
	}

	@DataProvider(name = "testData")
	Object[][] readExcel() throws IOException {
		String[][] testData = ReadTestDataXLS.readXls("Test_Data.xlsx", "AccountCreation");
		int numberofRows = testData.length;
		Object[][] objectArray = new Object[numberofRows][1];
		for (int index = 0; index < numberofRows; index++) {
			CreateAccount_Pojo createAccountPojo = new CreateAccount_Pojo();
			createAccountPojo.setEmail(testData[index][0]);
			createAccountPojo.setGender(testData[index][1]);
			createAccountPojo.setFirstName(testData[index][2]);
			createAccountPojo.setLastName(testData[index][3]);
			createAccountPojo.setPassword(testData[index][4]);
			createAccountPojo.setBirthDay(testData[index][5]);
			createAccountPojo.setBirthMonth(testData[index][6]);
			createAccountPojo.setBirthYear(testData[index][7]);
			createAccountPojo.setCompany(testData[index][8]);
			createAccountPojo.setAddress(testData[index][9]);
			createAccountPojo.setCity(testData[index][10]);
			createAccountPojo.setState(testData[index][11]);
			createAccountPojo.setZip(testData[index][12]);
			createAccountPojo.setmPhone(testData[index][13]);

			objectArray[index][0] = createAccountPojo;
		}

		return objectArray;
	}

	@DataProvider(name = "productDetails")
	Object[][] readProductDetails() throws IOException {
		String[][] productDetails = ReadTestDataXLS.readXls("Test_Data.xlsx", "ProductDetails");
		int numberOfRows = productDetails.length;
		System.out.println("Number of rows in test data xls: " + numberOfRows);
		Object[][] productPOJOArray = new Object[numberOfRows][1];
		for (int index = 0; index < numberOfRows; index++) {
			Product_POJO products = new Product_POJO();
			products.setTabName(productDetails[index][0]);
			products.setProductName(productDetails[index][1]);
			products.setDescription(productDetails[index][2]);
			products.setMoreInfo(productDetails[index][3]);
			products.setCompositions(productDetails[index][4]);
			products.setStyles(productDetails[index][5]);
			products.setProperties(productDetails[index][6]);
			products.setPrice(productDetails[index][7]);
			products.setQuantity(productDetails[index][8]);
			products.setSize(productDetails[index][9]);
			products.setColour(productDetails[index][10]);
			products.setShippingCost(productDetails[index][11]);
			products.setPopUpError(productDetails[index][12]);

			productPOJOArray[index][0] = products;
		}

		return productPOJOArray;
	}

	@AfterMethod
	void tearDown() {
		PredefinedActions.tearDown();
	}

}
