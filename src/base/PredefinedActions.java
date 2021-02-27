package base;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PredefinedActions {
	private static WebDriver driver;
	private static WebDriverWait wait;

	public static void start(String uRl) {
		System.setProperty("webdriver.chrome.driver", "./resources/windows/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(uRl);
		wait = new WebDriverWait(driver, 10);
	}

	public static void tearDown() {
		driver.close();
	}

	public static WebElement getElement(String locator, boolean waitFlag) {
		WebElement element = null;
		String locatorType = locator.split(":-")[0];
		String locatorValue = locator.split(":-")[1];
		switch (locatorType.toUpperCase()) {
		case "CSS":
			try {
				if (waitFlag)
					element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locatorValue)));
				else
					element = driver.findElement(By.cssSelector(locatorValue));

			} catch (TimeoutException te) {
				if (waitFlag)
					element = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(locatorValue)));
				else
					element = driver.findElement(By.cssSelector(locatorValue));
			}
			break;
		case "XPATH":
			if (waitFlag)
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));
			else
				element = driver.findElement(By.xpath(locatorValue));
			break;
		case "ID":
			if (waitFlag)
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue)));
			else
				element = driver.findElement(By.id(locatorValue));
			break;
		case "LINKTEXT":
			if (waitFlag)
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(locatorValue)));
			else
				element = driver.findElement(By.linkText(locatorValue));
			break;

		}

		return element;

	}

	public static List<WebElement> getElements(String locator, boolean waitFlag) {
		List<WebElement> elements = null;

		String locatorType = locator.split(":-")[0];
		String locatorValue = locator.split(":-")[1];
		switch (locatorType.toUpperCase()) {
		case "CSS":
			if(waitFlag)
				elements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(locatorValue)));
			else 
				elements = driver.findElements(By.cssSelector(locatorValue));
			break;
		case "XPATH":
			if(waitFlag)
				elements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(locatorValue)));
			else 
				elements = driver.findElements(By.xpath(locatorValue));
			break;
		case "ID":
			if(waitFlag)
				elements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id(locatorValue)));
			else
				elements = driver.findElements(By.id(locatorValue));
			break;
		case "LINKTEXT":
			if(waitFlag)
				elements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.linkText(locatorValue)));
			else
				elements = driver.findElements(By.linkText(locatorValue));
			break;

		}

		return elements;

	}

	public static String getElementAttribute(String locator, String valueType , boolean waitFlag) {
		WebElement element = getElement(locator, waitFlag);
		String attributeValue = null;

		switch (valueType.toUpperCase()) {

		case "VALUE":
			attributeValue = element.getAttribute("value");
			break;
		case "NAME":
			attributeValue = element.getAttribute("name");
			break;
		case "CLASS":
			attributeValue = element.getAttribute("class");
			break;

		}

		return attributeValue;

	}

	public static void scrollByAxis(String xAxis, String yAxis) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(" + xAxis + "," + yAxis + ")");
	}

	public static void clickOnElement(String locator,boolean waitFlag) {
		WebElement element = getElement(locator,waitFlag);
		element.click();

	}

	public static void clickOnElementJs(String locator,boolean waitFlag) {
		WebElement element = getElement(locator,waitFlag);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}

	public static void scrollToElement(String locator,boolean waitFlag) {
		WebElement element = getElement(locator,waitFlag);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", element);
	}

	public static void sendKeysToElement(String locator, String text, boolean waitFlag ) {
		WebElement element = getElement(locator,waitFlag);
		if (element.isEnabled()) {
			element.clear();
			element.sendKeys(text);
		}
	}

	public static String getTitle() {
		String pageTitle = driver.getTitle();

		return pageTitle;
	}

	public static void mouseHover(String locator,boolean waitFlag) {
		WebElement element = getElement(locator,waitFlag);
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
	}

	public static String getElementText(String locator,boolean waitFlag) {
		WebElement element = getElement(locator,waitFlag);
		String text = element.getText();

		return text;
	}

	public static String getElementText(WebElement element) {

		return element.getText();
	}

	public static boolean isElementSelected(String locator, boolean waitFlag) {
		boolean flag = false;
		WebElement element = getElement(locator,waitFlag);
		if (element.isSelected()) {
			flag = true;
		}

		return flag;
	}

	public static String selectValueFromDropDown(String locator, String selectBy, String inputValue,boolean waitFlag) {
		WebElement element = getElement(locator,waitFlag);
		Select oselect = new Select(element);

		switch (selectBy.toUpperCase()) {
		case "VALUE":
			oselect.selectByValue(inputValue);
			break;
		case "INDEX":
			int index = Integer.parseInt(inputValue);
			oselect.selectByIndex(index);
			break;
		case "VISIBLETEXT":
			oselect.selectByVisibleText(inputValue);
			break;

		}
		WebElement selectedValueElement = oselect.getFirstSelectedOption();
		String selectedValue = selectedValueElement.getText();

		return selectedValue;

	}
}
