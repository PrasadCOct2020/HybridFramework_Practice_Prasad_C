package pages;

import java.io.FileNotFoundException;

import base.PredefinedActions;
import configuration.PropertyFilesPathConstants;
import utility.ReadPropertyFile;

public class AuthenticationPage extends PredefinedActions {
	private ReadPropertyFile authpropFile;
	private static AuthenticationPage authPage;

	private AuthenticationPage() {
		try {
			authpropFile = new ReadPropertyFile(PropertyFilesPathConstants.AUTH_PAGE_PROP_FILE_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static AuthenticationPage getInstance() {
		if (authPage == null) {
			authPage = new AuthenticationPage();
		}

		return authPage;
	}

	public void enterEmail(String email) {

		sendKeysToElement(authpropFile.getLocator("createAccEmail"), email,true);
	}

	public AccountCreation clickOnCreateAccountButton() {

		clickOnElement(authpropFile.getLocator("createAccButton"),false);

		return  AccountCreation.getInstance();
	}

	public String getPageTitle() {
		String pageTitle = getTitle();

		return pageTitle;
	}

	public void enterEmailForLogin(String email) {
		sendKeysToElement(authpropFile.getLocator("loginEmail"), email,true);
	}

	public void enterPassword(String password) {
		sendKeysToElement(authpropFile.getLocator("loginPswd"),password,false);

	}

	public MyAccountPage clickSignIn() {
		clickOnElement(authpropFile.getLocator("signInButton"),false);
		return MyAccountPage.getInstance();
	}


}
