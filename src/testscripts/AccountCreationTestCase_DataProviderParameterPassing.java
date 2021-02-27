package testscripts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.PredefinedActions;
import pages.AccountCreation;
import pages.AuthenticationPage;
import pages.HomePage;
import pages.MyAccountPage;
import test_POJO.CreateAccount_Pojo;
import utility.ReadTestDataXLS;

public class AccountCreationTestCase_DataProviderParameterPassing {
	@BeforeMethod
	void setupBrowser() {
		System.out.println("Step 1: Launch chrome browser and open link");
		PredefinedActions.start("http://automationpractice.com");
	}

	@Test(dataProvider = "testData")
	void createNewAccount(String email, String title, String firstName, String lastName, String password,
			String birthDay, String birthMonth, String birthYear, String company, String address, String city,
			String state, String postalCode, String mobilePhone) throws InterruptedException {
		HomePage homepage =HomePage.getInstance();
		System.out.println("Step 2: Click on Sign-in button");
		AuthenticationPage authPage = homepage.clickOnSignIn();
		String pageTitle = authPage.getPageTitle();
		System.out.println("Step 5: Validate if user has navigated successfully to AUTHENTICATION page");
		Assert.assertEquals(pageTitle, "Login - My Store");
		CreateAccount_Pojo pojo = new CreateAccount_Pojo();
		System.out.println("Step 3: Entered e-mail in e-mail address field");
		pojo.setEmail(email);
		authPage.enterEmail(pojo.getEmail());
		System.out.println("Step 4: Click on Create Account button");
		AccountCreation accountCreation = authPage.clickOnCreateAccountButton();
		Thread.sleep(5000);
		String label = accountCreation.validatePage();
		System.out.println("Step 5: Validate if user has successfully navigated to Create An Account page");
		Assert.assertEquals(label, "CREATE AN ACCOUNT");
		System.out.println("Step 6: Populate Title");
		pojo.setGender(title);
		accountCreation.populateGender(pojo.getGender());
		System.out.println("Step 7: Populate First Name");
		pojo.setFirstName(firstName);
		accountCreation.populateFirstName(pojo.getFirstName());
		System.out.println("Step 8: Populate Last Name");
		pojo.setLastName(lastName);
		accountCreation.populateLastName(pojo.getLastName());
		System.out.println("Step 9: Populate Password");
		pojo.setPassword(password);
		accountCreation.populatePassword(pojo.getPassword());
		System.out.println("Step 10: Populate Birth-Day");
		pojo.setBirthDay(birthDay);
		accountCreation.populateBirthDay(pojo.getBirthDay());
		System.out.println("Step 11: Populate Birth-Month");
		pojo.setBirthMonth(birthMonth);
		accountCreation.populateBirthMonth(pojo.getBirthMonth());
		System.out.println("Step 12: Populate Birth-Year");
		pojo.setBirthYear(birthYear);
		accountCreation.populateBirthYear(pojo.getBirthYear());
		System.out.println("Step 13: Populate address");
		pojo.setAddress(address);
		accountCreation.populateAddress(pojo.getAddress());
		System.out.println("Step 14: Populate City");
		pojo.setCity(city);
		accountCreation.populateCity(pojo.getCity());
		System.out.println("Step 15: Populate State");
		pojo.setState(state);
		accountCreation.populateState(pojo.getState());
		System.out.println("Step 16: Populate Zip");
		pojo.setZip(postalCode);
		accountCreation.populateZip(pojo.getZip());
		System.out.println("Step 17: Populate Mobile Phone");
		pojo.setmPhone(mobilePhone);
		accountCreation.populateMobile(pojo.getmPhone());
		System.out.println("Step 18: Click on register button");
		MyAccountPage myaccount = accountCreation.clickOnRegisterButton();
		String accountName = myaccount.validateAccountName();
		System.out.println("Step 19: Validate if correct user name is being displayed besides sign-out button");
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

	@DataProvider(name = "testData")
	 String[] [] readExcel() throws IOException {
		String[][] testData = ReadTestDataXLS.readXls("Test_Data.xlsx", "AccountCreation");
		
		return testData;
	}

	@AfterMethod
	void tearDown() {
		PredefinedActions.tearDown();
	}

}
