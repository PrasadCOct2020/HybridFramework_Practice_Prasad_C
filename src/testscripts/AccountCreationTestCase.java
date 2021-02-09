package testscripts;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.PredefinedActions;
import pages.AccountCreation;
import pages.AuthenticationPage;
import pages.HomePage;
import pages.MyAccountPage;
import test_POJO.CreateAccount_Pojo;

public class AccountCreationTestCase {
	@BeforeMethod
	void setupBrowser() {
		System.out.println("Step 1: Launch chrome browser and open link");
		PredefinedActions.start("http://automationpractice.com");
	}

	@Test
	void createNewAccount() throws InterruptedException {
		HomePage homepage = new HomePage();
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
		HomePage homepage = new HomePage();
		System.out.println("Step 2: Click on Sign-in button");
		AuthenticationPage authPage = homepage.clickOnSignIn();
		String pageTitle = authPage.getPageTitle();
		System.out.println("Step 5: Validate if user has navigated successfully to AUTHENTICATION page");
		Assert.assertEquals(pageTitle, "Login - My Store");
		CreateAccount_Pojo pojo = new CreateAccount_Pojo();
		System.out.println("Step 3: Entered e-mail in e-mail address field");
		pojo.setEmail("automation_8@gmail.com");
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

	@AfterMethod
	void tearDown() {
		PredefinedActions.tearDown();
	}

}
