package stepDefinations;

import helperClasses.PostMapHelper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import resources.APIResources;
import resources.Utils;

import java.io.FileNotFoundException;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class StepDefination extends Utils{
    RequestSpecification request;
    Response response;
    static String place_id;
    JsonPath jsp;
    PostMapHelper pmHelper = new PostMapHelper();
    @Given("Add Place Payload with {string} {string} {string}")
    public void add_place_payload_with(String name, String language, String address) throws FileNotFoundException {
        request = given().spec(givenReq()).body(pmHelper.setPostMap(name,language,address));
    }
    @When("User calls {string} with {string} Request")
    public void user_calls_with_request(String api, String httpRequest) {
        if(httpRequest.equalsIgnoreCase("post")){
            response = request.when().post(String.valueOf(APIResources.valueOf(api).getResource())).then().extract().response();
        } else if(httpRequest.equalsIgnoreCase("get")){
            response = request.when().get(String.valueOf(APIResources.valueOf(api).getResource())).then().extract().response();
        } else if(httpRequest.equalsIgnoreCase("delete")){
            response = request.when().delete(String.valueOf(APIResources.valueOf(api).getResource())).then().extract().response();
        }
    }
    @Then("API call success with status code {int}")
    public void api_call_success_with_status_code(Integer int1) {
        assertEquals(response.getStatusCode(),200);
    }
    @Then("{string} is {string}")
    public void is(String key, String expectedValue) {
        Assert.assertEquals(getJsonValue(response,key),expectedValue);
    }

    @Then("Verify Place id created maps to {string} using {string}")
    public void verify_place_id_created_maps_to_using(String string, String string2) throws FileNotFoundException {
        place_id = getJsonValue(response,"place_id");
        request = given().spec(givenReq()).queryParam("place_id",place_id);
        user_calls_with_request(string2,"get");
        Assert.assertEquals(getJsonValue(response,"name"),string);
    }

    @Given("Delete Place Payload")
    public void delete_place_payload() throws FileNotFoundException {
        request = given().spec(givenReq()).body("{\n" +
                "    \"place_id\": \""+place_id+"\"\n" +
                "}");
    }
}
