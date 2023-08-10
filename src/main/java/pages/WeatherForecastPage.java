package pages;

import java.io.IOException;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;



import objectRepository.TestObjectRepository;
import utility.ElementActions;

public class WeatherForecastPage extends TestObjectRepository{
	
	Logger log;
	WebDriver driver;
	ElementActions element_Actions;
	
	
	By weather_Forecast_Page_Heading;
	By weather_Forecast_Btn;
	By current_Weather_Summary;
	By three_Days_Outlook_Data;
	
	
	
	public WeatherForecastPage(WebDriver driver, Logger log) throws IOException {
		super("WeatherForecastPage.properties");
		// TODO Auto-generated constructor stub
		this.log = log;
		this.driver=driver;
		element_Actions=new ElementActions(driver);
		weather_Forecast_Page_Heading = By.xpath(config.getProperty("weather_Forecast_Page_Heading"));
		weather_Forecast_Btn = By.xpath(config.getProperty("weather_Forecast_Btn"));
		current_Weather_Summary = By.xpath(config.getProperty("current_Weather_Summary"));
		three_Days_Outlook_Data = By.xpath(config.getProperty("three_Days_Outlook_Data"));
		
		
	}
	
	
	public void waitForWeatherForecastPageToLoad() {
		element_Actions.waitUntilVisibilityLocated(weather_Forecast_Page_Heading);
		
	}
	
	 public String getWeatherForecastPageHeading() {
	    	
	    	String weather_Data_Heading = "";
	    	
	    	if(element_Actions.checkElementPresence(weather_Forecast_Page_Heading))
	    	{
	    		element_Actions.waitUntilVisibilityLocated(weather_Forecast_Page_Heading);
	    		element_Actions.findElement(weather_Forecast_Page_Heading);
	    		weather_Data_Heading = element_Actions.getElementText();
	    	}
	    	
	    	return weather_Data_Heading;
			
		}
	 
	 public boolean check15DayForecastBtn() {
			
			return element_Actions.checkElementPresence(weather_Forecast_Btn);
		}
	 
	 public String getCurrentWeatherSummary() {
	    	
	    	
	    	element_Actions.waitUntilVisibilityLocated(current_Weather_Summary);
	    	element_Actions.findElement(current_Weather_Summary);
	    	String current_Weather = element_Actions.getElementText();
	    	return current_Weather;
			
		}
	 
	 public boolean checkCurrentWeatherData(String value) throws InterruptedException{
	     	
	     	String subtitle = config.getProperty("current_Weather_Content").replace("info",value);
	    	By current_Data = By.xpath(subtitle);
	     	element_Actions.waitUntilVisibilityLocated(current_Data);
	  		element_Actions.findElement(current_Data);
	  		return element_Actions.checkElementPresence(current_Data);
	  		
	 }
	 
	 public String getCurrentWeatherValue(String subtitle) throws InterruptedException{
	     	
	     	String value = config.getProperty("current_Weather_Value").replace("info",subtitle);
	    	By current_Data = By.xpath(value);
	     	element_Actions.waitUntilVisibilityLocated(current_Data);
	  		element_Actions.findElement(current_Data);
	  		return element_Actions.getElementText();
	  		
	 }
	 
	 public String getWeatherForecastDataHeadings(String dataHeading) {
	    	
	    	String header = "";
	    	String heading = config.getProperty("weather_Forecast_Data_Headings").replace("DataHeading",dataHeading);
	    	By data_Heading = By.xpath(heading);
	    	
	    	if(element_Actions.checkElementPresence(data_Heading))
	    	{
	    		element_Actions.waitUntilVisibilityLocated(data_Heading);
	    		element_Actions.findElement(data_Heading);
	    		header = element_Actions.getElementText();
	    	}
	    	
	    	return header;
			
		}
	 
	 public boolean checkThreeDaysOutlookData() throws InterruptedException{
	     	
	    
	     	element_Actions.waitUntilVisibilityLocated(three_Days_Outlook_Data);
	  		element_Actions.findElement(three_Days_Outlook_Data);
	  		return element_Actions.checkElementPresence(three_Days_Outlook_Data);
	  		
	 }
	 
	 public String getThreeDaysOutlookData() {
	    	
	    	
	    	element_Actions.waitUntilVisibilityLocated(three_Days_Outlook_Data);
	    	element_Actions.findElement(three_Days_Outlook_Data);
	    	String current_Weather = element_Actions.getElementText();
	    	return current_Weather;
			
		}
	 
	 public String getWeatherCharts(String subtitle) throws InterruptedException{
	     	
	     	String charts = config.getProperty("weather_Charts").replace("DataHeading",subtitle);
	    	By chartData = By.xpath(charts);
	     	element_Actions.waitUntilVisibilityLocated(chartData);
	  		element_Actions.findElement(chartData);
	  		return element_Actions.getElementText();
	  		
	 }
	 
	 
	 

}