package pages;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.PredefinedActions;

public class AccountCreation extends PredefinedActions {
	WebDriverWait wait;
	WebElement element;

	public String validatePage() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement element = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id = 'noSlide']/h1")));

		return element.getText();
	}

	public void populateGender(String gender) {
		wait = new WebDriverWait(driver, 10);
		if (gender.equals("M")) {
			wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#id_gender1"))).click();
		} else 
			wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#id_gender2"))).click();
	}

	public void populateFirstName(String fName) {
		if (fName != null) {
			driver.findElement(By.cssSelector("#customer_firstname")).sendKeys(fName);
		}
	}

	public void populateLastName(String lName) {
		if (lName != null) {
			driver.findElement(By.cssSelector("#customer_lastname")).sendKeys(lName);
		}
	}

	public void populatePassword(String password) {
		if (password != null) {
			driver.findElement(By.cssSelector("#passwd")).sendKeys(password);
		}
	}

	public void populateBirthDay(String day) {
		if (day != null) {
			element = driver.findElement(By.cssSelector("#days"));
			Select oSelectDay = new Select(element);
			oSelectDay.selectByValue(day);
		}
	}

	public void populateBirthMonth(String month) {
		if (month != null) {
			element = driver.findElement(By.cssSelector("#months"));
			Select oSelectMonth = new Select(element);
			oSelectMonth.selectByValue(month);
		}
	}

	public void populateBirthYear(String year) {
		if (year != null) {
			element = driver.findElement(By.cssSelector("#years"));
			Select oSelectYear = new Select(element);
			oSelectYear.selectByValue(year);
		}
	}

	public void populateAddress(String address) {

		if (address != null) {
			driver.findElement(By.xpath("//input[@id = 'address1']")).sendKeys(address);
		}
	}

	public void populateCity(String city) {
		if (city != null) {
			driver.findElement(By.xpath("//input[@id = 'city']")).sendKeys(city);
		}
	}

	public void populateState(String state) {
		if (state != null) {
			element = driver.findElement(By.xpath("//select[@id = 'id_state']"));
			Select oSelectState = new Select(element);
			oSelectState.selectByVisibleText(state);
		}
	}

	public void populateZip(String zip) {
		if (zip != null) {
			driver.findElement(By.xpath("//input[@id = 'postcode']")).sendKeys(zip);
		}
	}

	public void populateMobile(String mobile) {
		if (mobile != null) {
			driver.findElement(By.cssSelector("#phone_mobile")).sendKeys(mobile);
		}
	}

	public MyAccountPage clickOnRegisterButton() {

		driver.findElement(By.id("submitAccount")).click();

		return new MyAccountPage();
	}
	
	public ArrayList<String> getValidationErrorDetails() {
		WebDriverWait wait = new WebDriverWait(driver,10);
		List<WebElement> listOfErrorElements = new ArrayList<WebElement>();
		WebElement element = driver.findElement(By.xpath("//div[@class = 'alert alert-danger']/p"));
		String str = element.getText();
		ArrayList<String> actualErrorDetails = new ArrayList<String>();
		actualErrorDetails.add(str);
		listOfErrorElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@class = 'alert alert-danger']//ol/li")));
		for(WebElement errorElement : listOfErrorElements) {
			String errorMsg = errorElement.getText();
			actualErrorDetails.add(errorMsg);
		}
		
		return actualErrorDetails;
	}

}
