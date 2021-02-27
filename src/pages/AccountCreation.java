package pages;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

import base.PredefinedActions;
import configuration.PropertyFilesPathConstants;
import utility.ReadPropertyFile;

public class AccountCreation extends PredefinedActions {

	private ReadPropertyFile createAccountPropFile;
	private static AccountCreation createAccount;

	private AccountCreation() {
		try {
			createAccountPropFile = new ReadPropertyFile(PropertyFilesPathConstants.ACCOUNT_CREATION_PAGE_PROP_FILE_PATH);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static AccountCreation getInstance() {
		if (createAccount == null) {
			createAccount = new AccountCreation();
		}

		return createAccount;
	}

	public String validatePage() {
		return getElementText(createAccountPropFile.getLocator("pageHeader"),true);
	}

	public void populateGender(String gender) {

		if (gender.equals("M")) {
			clickOnElement(createAccountPropFile.getLocator("genderMale"),false);
		} else
			clickOnElement(createAccountPropFile.getLocator("genderFemale"),false);

	}

	public void populateFirstName(String fName) {
		if (fName != null) {
			sendKeysToElement(createAccountPropFile.getLocator("firstName"), fName,false);
		}
	}

	public void populateLastName(String lName) {
		if (lName != null) {
			sendKeysToElement(createAccountPropFile.getLocator("lastName"), lName,false);
		}
	}

	public void populatePassword(String password) {
		if (password != null) {
			sendKeysToElement(createAccountPropFile.getLocator("password"), password,false);
		}
	}

	public void populateBirthDay(String day) {
		if (day != null) {
			selectValueFromDropDown(createAccountPropFile.getLocator("birthDay"), "value", day,false);
		}
	}

	public void populateBirthMonth(String month) {
		if (month != null) {
			selectValueFromDropDown(createAccountPropFile.getLocator("birthMonth"), "value", month,false);
		}
	}

	public void populateBirthYear(String year) {
		if (year != null) {
			selectValueFromDropDown(createAccountPropFile.getLocator("birthYear"), "value", year,false);
		}
	}

	public void populateAddress(String address) {

		if (address != null) {
			sendKeysToElement(createAccountPropFile.getLocator("address"), address,false);
		}
	}

	public void populateCity(String city) {
		if (city != null) {
			sendKeysToElement(createAccountPropFile.getLocator("city"), city,false);
		}
	}

	public void populateState(String state) {
		if (state != null) {
			selectValueFromDropDown(createAccountPropFile.getLocator("state"), "visibletext", state,false);
		}
	}

	public void populateZip(String zip) {
		if (zip != null) {
			sendKeysToElement(createAccountPropFile.getLocator("postalCode"), zip,false);
		}
	}

	public void populateMobile(String mobile) {
		if (mobile != null) {
			sendKeysToElement(createAccountPropFile.getLocator("mobilePhone"), mobile,false);
		}
	}

	public MyAccountPage clickOnRegisterButton() {

		clickOnElement(createAccountPropFile.getLocator("registerButton"),false);

		return MyAccountPage.getInstance();
	}

	public ArrayList<String> getValidationErrorDetails() {
		List<WebElement> listOfErrorElements = new ArrayList<WebElement>();
		String str = getElementText(createAccountPropFile.getLocator("error"),true);
		ArrayList<String> actualErrorDetails = new ArrayList<String>();
		actualErrorDetails.add(str);
		listOfErrorElements = getElements(createAccountPropFile.getLocator("validationErrors"),true);
		for (WebElement errorElement : listOfErrorElements) {
			String errorMsg = getElementText(errorElement);
			actualErrorDetails.add(errorMsg);
		}

		return actualErrorDetails;
	}

}
