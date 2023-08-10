package utility;


import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementActions {

	WebDriver driver;
	JavascriptExecutor executor;
	WebDriverWait wait;
	WebElement element;
	List<WebElement> elements;


	@SuppressWarnings("deprecation")
	public ElementActions(WebDriver driver) {

		this.driver = driver;
		wait = new WebDriverWait(driver,60);

	}

	public WebElement findElement(By by) {

		element = driver.findElement(by);
		return element;

	}

	public List<WebElement> findElements(By by) {

		elements = driver.findElements(by);
		return elements;
	}

	public void waitUntilVisibilityLocated(By by) {
		wait.until(ExpectedConditions.elementToBeClickable(wait.until(ExpectedConditions.visibilityOfElementLocated(by))));
	}




	public void waitUntilInVisibilityLocated(By by) {

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
	}




	public boolean checkElementPresence(By by) {

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		if (driver.findElements(by).size() > 0) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
			return true;
		}

		else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
			return false;
		}
	}

	public boolean checkElementVisibility(By by) {
		boolean visibility = true;

		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		}

		catch (Exception e) {
			visibility = false;
		}

		return visibility;
	}

	public void SendKeys(String text) {
		element.sendKeys(text);

	}

	public void pressEnter() {
		element.sendKeys(Keys.ENTER);

	}


	public void Click() {
		element.click();

	}

	public void click_JS() {
		executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}

	public void doubleClick(By by) {

		Actions actions = new Actions(driver);
		WebElement elementLocator = driver.findElement(by);
		actions.doubleClick(elementLocator).perform();
	}
	

	public String getElementText() {

		return element.getText();

	}

	public void selectValueFromDropdown(String dropdown_Value) {

		Select dropdown = new Select(element);
		dropdown.selectByVisibleText(dropdown_Value);
	}


	public void clearField() {

		element.clear();
	}

	public void mouseHover() {
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();

	}
	
	public void mouseClick() {
		Actions action = new Actions(driver);
		action.moveToElement(element).click().build().perform();

	}

	
	
	public String getAttributeValue() {

		return element.getAttribute("value");
	}

	public String getTextOfSelectedOptionInDropdown(By by) {
		Select select = new Select(driver.findElement(by));
		WebElement option = select.getFirstSelectedOption();
		String selected_Item = option.getText();
		return selected_Item;
	}

	

	public void scrollElementIntoView() {
		executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].scrollIntoView(true);", element);
	}


	

	public void SendKeys(Keys arrowDown) {
		// TODO Auto-generated method stub
		element.sendKeys(arrowDown);
	}

	
}