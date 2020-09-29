package _API.step_definition;

import _API.api_utils.APIutils;
import _UI.step_definition.ScenarioContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class UserRegistrationTest {
    ScenarioContext context;

    public UserRegistrationTest(ScenarioContext scenarioContext){
        context = scenarioContext;
    }

    @Given("Resource {string} is up and running")
    public void resourceIsUpAndRunning(String resource) {
        context.request = RestAssured.given().request().basePath(resource);
    }

    @When("I send POST request with a pojo {string}")
    public void iSendPOSTRequestWithAPojo(String pojoClassName) {
        context.response = context.request
                .contentType(ContentType.JSON)
                .body(APIutils.pojoToJsonString(pojoClassName))
                .when()
                .post();
    }


    @Then("Response code should be {int}")
    public void responseCodeShouldBe(int statusCode) {
        context.response.prettyPrint();
        assertThat(context.getStatusCode(),is(statusCode));
    }

    @Then("Print out and log the response body")
    public void printOutAndLogTheResponseBody() {
        context.response
                .body().prettyPrint();
        context.scenario.log(context.getResponseBody());
    }
}
