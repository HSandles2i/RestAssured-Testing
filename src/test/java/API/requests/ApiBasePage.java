package API.requests;

import io.restassured.response.Response;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.core.pages.PageObject;
import static io.restassured.RestAssured.given;
import org.json.simple.JSONObject;


public class ApiBasePage extends PageObject {
String endpoint = "http://localhost:5002/api/test/";
private Response response;
    @Step
    public void getPersonById(String personId, int code) {
        response = given().header("Content-Type", "application/json")
                .log().all()
                .when()
                .get(endpoint + personId)
                .then()
                .statusCode(code)
                .extract()
                .response();
    }

    @Step
    public void createNewPerson(String person, int code) {
        response = given().header("Content-Type", "application/json")
                .log().all()
                .when()
                .body(person)
                .post(endpoint)
                .then()
                .statusCode(code)
                .extract()
                .response();
    }
    @Step
    public void updatePerson(String person, String personId, int code) {
        response = given().header("Content-Type", "application/json")
                .log().all()
                .when()
                .body(person)
                .put(endpoint + personId)
                .then()
                .statusCode(code)
                .extract()
                .response();
    }

    @Step
    public void removePerson(String personId, int code) {
        response = given().header("Content-Type", "application/json")
                .log().all()
                .when()
                .delete(endpoint + personId)
                .then()
                .statusCode(code)
                .extract()
                .response();
    }

    @Step
    public String getResponseBody() {
        System.out.println(response.asString());
        return response.asString();
    }

    public int getResponseStatusCode() {
        return response.getStatusCode();  // Return the status code from the response
    }
}
