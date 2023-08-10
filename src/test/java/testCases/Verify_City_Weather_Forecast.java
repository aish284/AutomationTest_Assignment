package testCases;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import com.aventstack.extentreports.Status;

import pages.VisualCrossingHomePage;
import pages.WeatherDataPage;
import pages.WeatherForecastPage;
import testBase.TestBase;
import utility.RestAPI;
import utility.XmlReader;

	public class Verify_City_Weather_Forecast extends TestBase{
	
		VisualCrossingHomePage Vp;
		WeatherDataPage Wdp;
		WeatherForecastPage Wfp;
		RestAPI  Ra;
		
		public Logger log;
		
		public void Loader() throws IOException {
			
			log = LogManager.getLogger(Verify_City_Weather_Forecast.class.getName());
			Vp = new VisualCrossingHomePage(driver, log);
			Wdp =  new WeatherDataPage(driver,log);
			Wfp = new WeatherForecastPage(driver,log);		
			Ra = new RestAPI();
		
		}
		
		@Test (dataProvider = "WeatherForecast_TestData")
		public void VerifyCityWeatherForecast(String location,String current_Subtitle1,String current_Subtitle2,String forecast_Heading1,String forecast_Heading2,String forecast_Heading3,String forecast_Heading4) throws InterruptedException, IOException 
		{
			
			test = extent.createTest("Verfify that weather forecast for the searched city should get displayed");
			log.info("Started == Verfify that weather forecast for the searched city should get displayed");
			test.log(Status.INFO,"Entering the URL of the application");
			driver.get(URL);
			test.log(Status.PASS,"URL of the application is entered successfully");
			test.log(Status.INFO, "Verifying that Visual Crossing Corporation Page is displayed to the user");
			Vp.acceptCookies();
			Assert.assertEquals(Vp.checkHomePage(), true, "Home Page is not displayed to the user");
			test.log(Status.PASS, "Visual Crossing Corporation Page is displayed to the user successfully");
			log.info("Visual Crossing Corporation Page is displayed successfully");
			test.log(Status.INFO, "Verifying that Weather Data link is available on navigation bar of page");
			Assert.assertEquals(Vp.checkWeatherDataLink(), true, " Weather Data link is not displayed to the user");
			test.log(Status.PASS, "Weather Data link is displayed to the user successfully");
			log.info("Weather Data link is displayed successfully");	
			log.info("Verifying that user gets navigated to Weather Data page on clicking on the Weather Data link");
			test.log(Status.INFO, "Verifying that user gets navigated to Weather Data page on clicking on the Weather Data link");
			Vp.clickOnWeatherData();	
			Wdp.waitForWeatherDataPageToLoad();
			Assert.assertEquals(Wdp.getWeatherDataPageHeading(), "Total Weather Data", "Weather Data page is not displayed to the user");
			test.log(Status.PASS, "User is navigated to Weather Data page successfully with heading - "+Wdp.getWeatherDataPageHeading());
			log.info( "User is navigated to Weather Data page successfully with heading - "+Wdp.getWeatherDataPageHeading());
			
			test.log(Status.INFO, "Verifying that 'Enter a location' field is available on Weather Data page");
			Assert.assertEquals(Wdp.checkEnterLocationField(), true, "'Enter a location' field is not displayed to the user");
			test.log(Status.PASS, "'Enter a location' field is displayed to the user successfully");
			log.info("'Enter a location' field is displayed successfully");
			
			log.info("Entering location - " +location+ " in the location field and selecting forecast option");
			test.log(Status.INFO, "Entering location - " +location+ " in the location field and selecting forecast option");
			Wdp.enterLocation(location);
			Wdp.selectForecastOption();
			log.info("Location is entered and forecast option is selected successfully");
			test.log(Status.PASS, "Location is entered and forecast option is selected successfully");
			
			log.info("Clicking on Search button and verifying that user gets navigated to weather forecast page");
			test.log(Status.INFO, "Clicking on Search button and verifying that user gets navigated to weather forecast page");
			Wdp.clickOnSearchBtn();
			Wfp.waitForWeatherForecastPageToLoad();
			Assert.assertEquals(Wfp.getWeatherForecastPageHeading().contains(location),true, "Weather Forecast page is not displayed to the user");
			test.log(Status.PASS, "Weather Forecast page for entered city is displayed to the user successfully with heading as - "+Wfp.getWeatherForecastPageHeading());
			log.info("Weather Forecast page for entered city is displayed to the user successfully with heading as - "+Wfp.getWeatherForecastPageHeading());
			
			log.info("Verifying that 15-Day Forecast option is active by default on navigation to Weather Forecast page");
			test.log(Status.INFO, "Verifying that 15-Day Forecast option is active by default on navigation to Weather Forecast page");
			Assert.assertEquals(Wfp.check15DayForecastBtn(), true, "15-Day Forecast option is not displayed to the user");
			test.log(Status.PASS, "15-Day Forecast option is selected on default when user gets navigated to Weather Forecast page");
			log.info("15-Day Forecast option is selected on default when user gets navigated to Weather Forecast page");
			
			log.info("Verifying the data headers displayed on Weather Forecast page");
			test.log(Status.INFO, "Verifying the data headers displayed on Weather Forecast page");
			Assert.assertEquals(Wfp.getWeatherForecastDataHeadings(forecast_Heading1),forecast_Heading1, "Currently data header is not displayed on the page");
			Assert.assertEquals(Wfp.getWeatherForecastDataHeadings(forecast_Heading2),forecast_Heading2, "3 Day outlook data header is not displayed on the page");
			Assert.assertEquals(Wfp.getWeatherForecastDataHeadings(forecast_Heading3),forecast_Heading3, "Hourly forecast data header is not displayed on the page");
			Assert.assertEquals(Wfp.getWeatherForecastDataHeadings(forecast_Heading4),forecast_Heading4, "Long term outlook data header is not displayed on the page");
			test.log(Status.PASS, "All data headers - " +forecast_Heading1+ " , " +forecast_Heading2+ " , "+forecast_Heading3+ " and "+forecast_Heading4+ " are displayed successfully on Weather Forecast page");
			log.info("All data headers - " +forecast_Heading1+ " , " +forecast_Heading2+ " , "+forecast_Heading3+ " and "+forecast_Heading4+ " are displayed successfully on Weather Forecast page");
			
		
			log.info("Verifying that current weather summary should be displayed on Weather Forecast page");
			test.log(Status.INFO, "Verifying that current weather summary should be displayed on Weather Forecast page");
			Assert.assertEquals(Wfp.getCurrentWeatherSummary().isEmpty(),false, "Current weather data is not displayed to the user");
			test.log(Status.PASS, "Current Weather summary is displayed as - "+Wfp.getCurrentWeatherSummary());
			log.info("Current Weather summary is displayed as - "+Wfp.getCurrentWeatherSummary());
			
			log.info("Verifying the Temperature information displayed in the current weather summary section");
			test.log(Status.INFO, "Verifying the Temperature information displayed in the current weather summary section");
			Assert.assertEquals(Wfp.checkCurrentWeatherData(current_Subtitle1),true, "Temperature data is not displayed to the user");
			test.log(Status.PASS, "Temperature - "+Wfp.getCurrentWeatherValue(current_Subtitle1)+ " is displayed in the current weather summary section");
			log.info("Temperature - "+Wfp.getCurrentWeatherValue(current_Subtitle1)+ " is displayed in the current weather summary section");
			
			log.info("Verifying the Rain information displayed in the current weather summary section");
			test.log(Status.INFO, "Verifying the Rain information displayed in the current weather summary section");
			Assert.assertEquals(Wfp.checkCurrentWeatherData(current_Subtitle2),true, "Rain data is not displayed to the user");
			test.log(Status.PASS, "Rain - "+Wfp.getCurrentWeatherValue(current_Subtitle2)+ " is displayed in the current weather summary section");
			log.info("Rain - "+Wfp.getCurrentWeatherValue(current_Subtitle2)+ " is displayed in the current weather summary section");
			
			log.info("Verifying that 3 Day outlook summary should be displayed on Weather Forecast page under 3 widgets");
			test.log(Status.INFO, "Verifying that 3 Day outlook summary should be displayed on Weather Forecast page under 3 widgets");
			Assert.assertEquals(Wfp.checkThreeDaysOutlookData(),true, "3 Day outlook data is not displayed to the user");
			test.log(Status.PASS, "3 Day outlook summary is displayed successfully on Weather Forecast page under 3 widgets with data - "+Wfp.getThreeDaysOutlookData());
			log.info("3 Day outlook summary is displayed successfully on Weather Forecast page under 3 widgets with data - "+Wfp.getThreeDaysOutlookData());
		
			log.info("Verifying that Hourly forecast summary should be displayed on Weather Forecast page");
			test.log(Status.INFO, "Verifying that Hourly forecast summary should be displayed on Weather Forecast page");
			Assert.assertEquals(Wfp.getWeatherCharts(forecast_Heading3).isEmpty(),false, "Hourly forecast summary is not displayed to the user");
			test.log(Status.PASS, "Hourly forecast summary is displayed successfully on Weather Forecast page with chart data as - "+Wfp.getWeatherCharts(forecast_Heading3));
			log.info("Hourly forecast summary is displayed successfully on Weather Forecast page with chart data as - "+Wfp.getWeatherCharts(forecast_Heading3));
	
			log.info("Verifying that Long term outlook summary should be displayed on Weather Forecast page");
			test.log(Status.INFO, "Verifying that Long term outlook summary should be displayed on Weather Forecast page");
			Assert.assertEquals(Wfp.getWeatherCharts(forecast_Heading4).isEmpty(),false, "Long term outlook summary is not displayed to the user");
			test.log(Status.PASS, "Long term outlook summary is displayed successfully on Weather Forecast page with chart data as - "+Wfp.getWeatherCharts(forecast_Heading4));
			log.info("Long term outlook summary is displayed successfully on Weather Forecast page with chart data as - "+Wfp.getWeatherCharts(forecast_Heading4));
	
			
		}
		
		
		@DataProvider(name = "WeatherForecast_TestData")
	  	public Object[][] verifyWeatherForecast() throws IOException, ParserConfigurationException, SAXException {
	  		XmlReader reader = new XmlReader();
	  		return reader.testData("TestData.xml", "TC1_Weather_Data");
	  	}
	
	}
	
	
	
	
	
	
	