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

	public class Verify_Weather_Forecast_API_Response extends TestBase{
	
		VisualCrossingHomePage Vp;
		WeatherDataPage Wdp;
		WeatherForecastPage Wfp;
		RestAPI  Ra;
		
		public Logger log;
		
		public void Loader() throws IOException {
			
			log = LogManager.getLogger(Verify_Weather_Forecast_API_Response.class.getName());
			Vp = new VisualCrossingHomePage(driver, log);
			Wdp =  new WeatherDataPage(driver,log);
			Wfp = new WeatherForecastPage(driver,log);		
			Ra = new RestAPI();
		
		}
		
		@Test (dataProvider = "WeatherAPI_TestData")
		public void VerifyWeatherForecastAPIResponse(String location,String resolvedAdd,String description) throws InterruptedException, IOException 
		{
			
			test = extent.createTest("Verifying the correctness of data received in response of weather forecast API");
			log.info("Started == Verifying the correctness of data received in response of weather forecast API");
			/*----------------Response Code validation------------------*/
			test.log(Status.INFO,"Verifying the response code received from the API");
			int code = Ra.getWeatherDataAPIStatusCode();
			Assert.assertEquals(code,200,"Success is not received in API response");
			test.log(Status.PASS,"Respone code - "+code+ " is received successfully");
			/*----------------Getting API data------------------*/
			test.log(Status.INFO, "Getting the complete data from the API response");
			log.info("Getting the complete data from the API response");
			String weather_Data = Ra.getWeatherData();
			test.log(Status.PASS,"Data - "+weather_Data+ " is received successfully from response");
			log.info("Data - "+weather_Data+ " is received successfully from response");
			/*----------------City validation------------------*/
			test.log(Status.INFO, "Verifying that the city passed in the GET request is received under Address parameter in response");
			log.info("Verifying that the city passed in the GET request is received under Address parameter in response");
			Assert.assertEquals(Ra.getSpecificDataFromJsonResponse("address"),location, "Correct city name is not received in the Response");
			test.log(Status.PASS, "Correct city name - " +Ra.getSpecificDataFromJsonResponse("address")+ " is received under Address parameter in response");
			log.info("Correct city name - " +Ra.getSpecificDataFromJsonResponse("address")+ " is received under Address parameter in response");
			/*----------------Resolved Address validation------------------*/
			test.log(Status.INFO, "Verifying that correct value for resolvedAddress is received in response");
			log.info("Verifying that correct value for resolvedAddress is received in response");
			Assert.assertEquals(Ra.getSpecificDataFromJsonResponse("resolvedAddress"),resolvedAdd, "Correct resolvedAddress name is not received in the Response");
			test.log(Status.PASS, "Correct value - " +Ra.getSpecificDataFromJsonResponse("resolvedAddress")+ " is received in response for resolvedAddress");
			log.info("Correct value - " +Ra.getSpecificDataFromJsonResponse("resolvedAddress")+ " is received in response for resolvedAddress");
			/*----------------Description validation------------------*/
			test.log(Status.INFO, "Verifying that correct value for description is received in response");
			log.info("Verifying that correct value for description is received in response");
			Assert.assertEquals(Ra.getSpecificDataFromJsonResponse("description"),description, "Correct description is not received in the Response");
			test.log(Status.PASS, "Correct value - " +Ra.getSpecificDataFromJsonResponse("description")+ " is received in response for description");
			log.info("Correct value - " +Ra.getSpecificDataFromJsonResponse("description")+ " is received in response for description");
			/*----------------No. of Days data validation------------------*/
			test.log(Status.INFO, "Verifying the weather data received in response should be for 15 Days");
			log.info("Verifying the weather data received in response should be for 15 Days");
			Assert.assertEquals(Ra.getNoDaysDataFromJsonResponse(),15, "Correct number of days is not received in the Response");
			test.log(Status.PASS, "15 days weather data is received in response successfully with temperature values as - "+Ra.getDaysDataFromJsonResponse());
			log.info("15 days weather data is received in response successfully with temperature values as - "+Ra.getDaysDataFromJsonResponse());	
		}
		
		
		@DataProvider(name = "WeatherAPI_TestData")
	  	public Object[][] verifyWeatherForecastAPI() throws IOException, ParserConfigurationException, SAXException {
	  		XmlReader reader = new XmlReader();
	  		return reader.testData("TestData.xml", "TC2_Weather_Data");
	  	}
	
	}
	
	
	
	
	
	
	