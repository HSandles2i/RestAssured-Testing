package API.stepdefintions;

import API.helpers.JsonParsing;
import API.helpers.Person;
import API.requests.ApiBasePage;
import com.google.gson.Gson;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import net.serenitybdd.core.Serenity;
import org.json.simple.parser.ParseException;
import org.junit.Assert;

public class APISteps {

    ApiBasePage apiBasePage;
    Gson gson = new Gson();

    JsonParsing jsonParsing = new JsonParsing();

    @Given("User is added to the system")
    public void newPersonIsAdded() {
        Person person = new Person();
        Serenity.setSessionVariable("person").to(person);
        apiBasePage.createNewPerson(gson.toJson(person), 201);
    }

    @Then("is assigned an ID")
    public void personIdSaved() throws ParseException {
        String personId = jsonParsing.extractPropertyFromResponse("insertedId",  apiBasePage.getResponseBody());
        System.out.println("personId: " + personId);
        Serenity.setSessionVariable("personId").to(personId);
    }

    @Then("User's data is retrieved")
    public void getPersonData() {
        apiBasePage.getPersonById(Serenity.sessionVariableCalled("personId"), 200);  // Sends the GET request and validates the status code
    }

    @Then("the response status code should be {int}")
    public void validateStatusCode(int expectedStatusCode) {
        int actualStatusCode = apiBasePage.getResponseStatusCode();  // Get the actual status code from the response
        Assert.assertEquals(expectedStatusCode, actualStatusCode);   // Compare the actual status code with the expected status code
    }

    @And("matches what was added")
    public void validateData() throws ParseException {
        String responseBody = apiBasePage.getResponseBody();  // Get the response body
        Person person = Serenity.sessionVariableCalled("person");
        Assert.assertEquals(person.getFirstName(), jsonParsing.extractPropertyFromResponse("firstName", responseBody));
        Assert.assertEquals(person.getLastName(), jsonParsing.extractPropertyFromResponse("lastName", responseBody));
        Assert.assertEquals(person.getDob(), jsonParsing.extractPropertyFromResponse("dob", responseBody));
        Assert.assertEquals(person.getEmail(), jsonParsing.extractPropertyFromResponse("email", responseBody));
        Assert.assertEquals(person.getLocation(), jsonParsing.extractPropertyFromResponse("location", responseBody));
        }


    @Then("User's location is changed to {string}")
    public void locationUpdated(String newProperty) {
        Person person = Serenity.sessionVariableCalled("person");
        person.setLocation(newProperty);
        Serenity.setSessionVariable("person").to(person);
        apiBasePage.updatePerson(gson.toJson(person), Serenity.sessionVariableCalled("personId"), 200);
    }

    @And("User's new location is correct")
    public void validateChanges() throws ParseException {
        apiBasePage.getPersonById(Serenity.sessionVariableCalled("personId"), 200);
        Person person = Serenity.sessionVariableCalled("person");
        String responseBody = apiBasePage.getResponseBody();
        Assert.assertEquals(person.getLocation(), jsonParsing.extractPropertyFromResponse("location", responseBody));
    }

    @Then("we remove User from the system")
    public void removeUser() {
        apiBasePage.removePerson(Serenity.sessionVariableCalled("personId"), 200);
    }
}
