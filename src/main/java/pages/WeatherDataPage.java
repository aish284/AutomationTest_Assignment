package pages;

import java.io.IOException;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;



import objectRepository.TestObjectRepository;
import utility.ElementActions;

public class WeatherDataPage extends TestObjectRepository{
	
	Logger log;
	WebDriver driver;
	ElementActions element_Actions;
	
	
	By weather_Data_Page_Heading;
	By location_Field;
	By search_Btn;
	By history_Forecast_Dropdown;
	By forecast_Option;
	
	
	
	public WeatherDataPage(WebDriver driver, Logger log) throws IOException {
		super("WeatherDataPage.properties");
		// TODO Auto-generated constructor stub
		this.log = log;
		this.driver=driver;
		element_Actions=new ElementActions(driver);
		weather_Data_Page_Heading = By.xpath(config.getProperty("weather_Data_Page_Heading"));
		location_Field = By.xpath(config.getProperty("location_Field"));
		search_Btn = By.xpath(config.getProperty("search_Btn"));
		history_Forecast_Dropdown =  By.xpath(config.getProperty("history_Forecast_Dropdown"));
		forecast_Option = By.xpath(config.getProperty("forecast_Option"));
	}
	
	
	public void waitForWeatherDataPageToLoad() {
		element_Actions.waitUntilVisibilityLocated(weather_Data_Page_Heading);
		
	}
	
	 public String getWeatherDataPageHeading() {
	    	
	    	String weather_Data_Heading = "";
	    	
	    	if(element_Actions.checkElementPresence(weather_Data_Page_Heading))
	    	{
	    		element_Actions.waitUntilVisibilityLocated(weather_Data_Page_Heading);
	    		element_Actions.findElement(weather_Data_Page_Heading);
	    		weather_Data_Heading = element_Actions.getElementText();
	    	}
	    	
	    	return weather_Data_Heading;
			
		}
	 
	 public boolean checkEnterLocationField() {
			
			log.info("Verifying if Enter a location field is visible");
			return element_Actions.checkElementPresence(location_Field);
		}
	
	 public void enterLocation(String location) {
			
			element_Actions.waitUntilVisibilityLocated(location_Field);
			element_Actions.findElement(location_Field);
			element_Actions.Click();
			element_Actions.SendKeys(location);
			
		}
	 
	 public void clickOnSearchBtn() throws InterruptedException {
			
			element_Actions.waitUntilVisibilityLocated(search_Btn);
			element_Actions.findElement(search_Btn);
			element_Actions.Click();
			Thread.sleep(2000);
		}
	 
	 public boolean checkHistoryForecastDrpdown() {
			
			log.info("Verifying if History or forecast? drop-down is visible");
			return element_Actions.checkElementPresence(history_Forecast_Dropdown);
		}
	 
	 public void selectForecastOption() {
			
			element_Actions.waitUntilVisibilityLocated(history_Forecast_Dropdown);
			element_Actions.findElement(history_Forecast_Dropdown);
			element_Actions.Click();
			element_Actions.findElement(forecast_Option);
			element_Actions.Click();
			
		}
	 
	 
	 

}