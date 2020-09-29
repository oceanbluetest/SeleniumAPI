package _UI.step_definition;

import _UI.pages.UserDatabasePage;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class UserDatabasePageTest {
    ScenarioContext context;

    public UserDatabasePageTest(ScenarioContext scenarioContext){
        context = scenarioContext;
    }

    @Then("I Verify following fields of response are displayed on User Database table:")
    public void iVerifyFollowingFieldsOfResponseAreDisplayedOnTable(List<String> list) {
        int rowCount = 0;

        for (WebElement element: context.userDatabasePage.tableRows){
            int count = list.size();
            for (String fieldName: list){
                String fieldValue = "";

                switch (fieldName.toLowerCase()){
                    case "firstname": fieldValue = context.response.jsonPath().getString("firstName");
                        break;
                    case "lastname": fieldValue = context.response.jsonPath().getString("lastName");
                        break;
                    case "email": fieldValue = context.response.jsonPath().getString("email");
                        break;
                }

                if (element.getText().contains(fieldValue))
                    count--;
            }
            if (count == 0) {
                rowCount++;
                context.seleniumUtils.takeScreenshot(element);
            }
        }

        Assert.assertTrue(rowCount > 0);
    }
}
