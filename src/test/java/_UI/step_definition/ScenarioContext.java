package _UI.step_definition;

import _UI.pages.UserDatabasePage;
import _UI.pages.UserMgtPage;
import _UI.ui_utils.Selenium_utils;
import common_util.ConfigReader;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class ScenarioContext {
     public Scenario scenario;
     public WebDriver driver;

     //Classes
     public UserMgtPage userMgtPage;
     public UserDatabasePage userDatabasePage;
     public Selenium_utils seleniumUtils;


     //API
     public RequestSpecification request;
     public Response response;
     public Headers headers;
     public JSONObject jsonObject;


     //NOTE: Selenium methods
     public void initializeClasses(ScenarioContext scenarioContext){
          userMgtPage = new UserMgtPage(driver);
          seleniumUtils = new Selenium_utils(scenarioContext);
          userDatabasePage = new UserDatabasePage(driver);
     }

     public void initializeDriver() {
          if (driver == null) {
               switch (ConfigReader.readProperty("browser", "src/test/resources/properties/configuration.properties")) {
                    case "chrome":
                         WebDriverManager.chromedriver().setup();
                         driver = new ChromeDriver();
                         break;
                    case "firefox":
                         WebDriverManager.firefoxdriver().setup();
                         driver = new FirefoxDriver();
                         break;
                    default:
                         System.out.println("Invalid browser type");
               }
          }
          driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
          driver.manage().window().maximize();
     }

     //NOTE: API methods
     public String getResponseBody(){
          return response.getBody().asString();
     }

     public int getStatusCode(){
          return response
                  .getStatusCode();
     }

     public String getResponseID(){
          return response.body().jsonPath().getString("_id");
     }


}
