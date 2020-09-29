package _UI.step_definition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.Assert;


import java.util.List;
import java.util.Map;

public class UserMgtPageTest{
    ScenarioContext context;

    public UserMgtPageTest(ScenarioContext scenarioContext){
        this.context = scenarioContext;
    }

    @Given("I open {string}")
    public void iOpen(String websiteName) {
        switch (websiteName){
            case "youtube": context.driver.get("https://youtube.com");
                break;
            case "amazon": context.driver.get("https://amazon.com");
                break;
            case "chase bank": context.driver.get("https://chase.com");
                break;
            default:
                context.driver.get(websiteName);
        }
        context.seleniumUtils.logInfo(" opened website: " + websiteName, true);
    }

    @Given("I enter following data:")
    public void i_enter_following_data(Map<String, String> dataTable) {
        fillRegistrationForm(dataTable);
    }

    @When("I click a button {string}")
    public void i_click_a_button(String buttonName) {
        String mainWindowID = context.driver.getWindowHandle();

        switch (buttonName.toLowerCase()){
            case "submit": context.seleniumUtils.click(context.userMgtPage.submitBtn);
                break;
            case "login": context.seleniumUtils.click(context.userMgtPage.logInBtn);
                context.seleniumUtils.switchToWindow(mainWindowID);
                break;
            case "access db": context.seleniumUtils.click(context.userMgtPage.accessDbBtn);
                context.seleniumUtils.switchToWindow(mainWindowID);
                break;
            default:
                System.out.println("Invalid button name");
        }
    }

    @Then("I verify title is {string}")
    public void iVerifyTitleIs(String title) {
        Assert.assertEquals(title, context.driver.getTitle());
        context.seleniumUtils.logInfo("Actual title is: " + context.driver.getTitle(), true);
    }


    //NOTE: IMPLEMENTATIONS

    public void fillRegistrationForm(Map<String, String> map){
        for(String key: map.keySet()) {
            switch (key.toLowerCase()) {
                case "firstname":
                    context.seleniumUtils.sendKeys(context.userMgtPage.firstnameField, map.get(key));
                    break;
                case "lastname":
                    context.seleniumUtils.sendKeys(context.userMgtPage.lastnameField, map.get(key));
                    break;
                case "phone number":
                    context.seleniumUtils.sendKeys(context.userMgtPage.phoneField, map.get(key));
                    break;
                case "email":
                    context.seleniumUtils.sendKeys(context.userMgtPage.emailField, map.get(key));
                    break;
                case "role":
                    context.userMgtPage.selectRole(map.get(key));
                default:
                    System.out.println("Invalid field type");
            }
            context.seleniumUtils.logInfo("Entered data: " + map.get(key), false);
        }
        context.seleniumUtils.logInfo("", true);
    }


}
