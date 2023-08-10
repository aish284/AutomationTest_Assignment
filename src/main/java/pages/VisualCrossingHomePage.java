package pages;

import java.io.IOException;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;



import objectRepository.TestObjectRepository;
import utility.ElementActions;

public class VisualCrossingHomePage extends TestObjectRepository{
	
	Logger log;
	WebDriver driver;
	ElementActions element_Actions;
	
	
	By visual_Crossing_Icon;
	By navigation_Bar;
	By weather_Data_Link;
	By accept_Cookies_Btn;
	
	
	
	public VisualCrossingHomePage(WebDriver driver, Logger log) throws IOException {
		super("HomePage.properties");
		// TODO Auto-generated constructor stub
		this.log = log;
		this.driver=driver;
		element_Actions=new ElementActions(driver);
		visual_Crossing_Icon = By.xpath(config.getProperty("visual_Crossing_Icon"));
		navigation_Bar = By.xpath(config.getProperty("navigation_Bar"));
		weather_Data_Link = By.xpath(config.getProperty("weather_Data_Link"));
		accept_Cookies_Btn = By.xpath(config.getProperty("accept_Cookies_Btn"));
		
	}
	
	
	public boolean checkHomePage() {
		
		log.info("Verifying if Visual Crossing home page is displayed");
		return element_Actions.checkElementPresence(visual_Crossing_Icon)&&element_Actions.checkElementPresence(navigation_Bar);
	}
	
	
	public boolean checkWeatherDataLink() {
		
		log.info("Verifying if Weather Data link is available on navigation bar");
		return element_Actions.checkElementPresence(weather_Data_Link);
	}
	
	public void clickOnWeatherData() {
		
		element_Actions.waitUntilVisibilityLocated(weather_Data_Link);
		element_Actions.findElement(weather_Data_Link);
		element_Actions.Click();
		
	}
	
	public void acceptCookies() {
		
		element_Actions.waitUntilVisibilityLocated(accept_Cookies_Btn);
		element_Actions.findElement(accept_Cookies_Btn);
		element_Actions.Click();
		
	}
	

	
	
	
}