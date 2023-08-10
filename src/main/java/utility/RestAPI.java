package utility;


import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class RestAPI {
	
	
	public String getWeatherData() {
			
		
		   RestAssured.baseURI = "https://weather.visualcrossing.com";
		
		   RequestSpecification httpRequest = RestAssured.given();
			
		   Response response = httpRequest.get("/VisualCrossingWebServices/rest/services/timeline/Chandigarh?unitGroup=metric&key=CX6TH29WTEFXZVWXZXANYTVDU&contentType=json");
			
           String weatherData = response.getBody().asString();
   
		   return weatherData;
		    	
		}
	
	public int getWeatherDataAPIStatusCode() {
		
		
		   RestAssured.baseURI = "https://weather.visualcrossing.com";
		
		   RequestSpecification httpRequest = RestAssured.given();
			
		   Response response = httpRequest.get("/VisualCrossingWebServices/rest/services/timeline/Chandigarh?unitGroup=metric&key=CX6TH29WTEFXZVWXZXANYTVDU&contentType=json");
			
		   int status = response.getStatusCode();
		   System.out.println(status); 
		   return status;
		    	
		}
	
	public String getSpecificDataFromJsonResponse(String parameter)
	{
		RestAssured.baseURI = "https://weather.visualcrossing.com";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("/VisualCrossingWebServices/rest/services/timeline/Chandigarh?unitGroup=metric&key=CX6TH29WTEFXZVWXZXANYTVDU&contentType=json");

		JsonPath jsonPathEvaluator = response.jsonPath();
		String city = jsonPathEvaluator.get(parameter);
		return city;

	}
	
	public int getNoDaysDataFromJsonResponse()
	{
		RestAssured.baseURI = "https://weather.visualcrossing.com";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("/VisualCrossingWebServices/rest/services/timeline/Chandigarh?unitGroup=metric&key=CX6TH29WTEFXZVWXZXANYTVDU&contentType=json");

		JsonPath jsonPathEvaluator = response.jsonPath();
		int temp = jsonPathEvaluator.getList("days.temp").size();
		return temp;

	}
	
	public String getDaysDataFromJsonResponse()
	{
		RestAssured.baseURI = "https://weather.visualcrossing.com";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("/VisualCrossingWebServices/rest/services/timeline/Chandigarh?unitGroup=metric&key=CX6TH29WTEFXZVWXZXANYTVDU&contentType=json");

		JsonPath jsonPathEvaluator = response.jsonPath();
		String daysData = jsonPathEvaluator.getList("days.temp").toString();
		return daysData;

	}
	
	
}
