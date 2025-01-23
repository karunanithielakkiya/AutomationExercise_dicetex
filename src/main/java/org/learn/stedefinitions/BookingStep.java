package org.learn.stedefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.learn.specs.RequestSpecs.requestSpec;
import static org.learn.specs.ResponseSpecs.*;

public class BookingStep {
    private Response response;
    private String bookingId;


    @Given("I add a new booking with valid details")
    public void iAddANewBookingWithValidDetails() {
        String requestBody = "{\n" +
                "    \"firstname\" : \"testFirstName\",\n" +
                "    \"lastname\" : \"lastName\",\n" +
                "    \"totalprice\" : 10.11,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2022-01-01\",\n" +
                "        \"checkout\" : \"2024-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"testAdd\"\n" +
                "}";

        response = given()
                .spec(requestSpec())
                .body(requestBody)
                .when()
                .post("/booking")
                .then()
                .statusCode(200)
                .extract()
                .response();

        // Extract the booking ID from the response
        bookingId = response.jsonPath().getString("bookingid");
    }

    @When("I get the booking details using the booking ID")
    public void iGetTheBookingDetailsUsingTheBookingID() {
        response = given()
                .spec(requestSpec())
                .pathParam("id", bookingId)
                .when()
                .get("/booking/{id}")
                .then()
                .extract()
                .response();
    }

    @Then("I should see the booking details match the expected values")
    public void iShouldSeeTheBookingDetailsMatchTheExpectedValues() {
        response.then()
                .body("firstname", equalTo("testFirstName"))
                .body("lastname", equalTo("lastName"))
                .body("totalprice", equalTo(10))
                .body("depositpaid", equalTo(true))
                .body("bookingdates.checkin", equalTo("2022-01-01"))
                .body("bookingdates.checkout", equalTo("2024-01-01"))
                .body("additionalneeds", equalTo("testAdd"));
    }

    @Then("I should receive a 200 success")
    public void iShouldReceiveA200success() {
        response.then().spec(validResponseSpec());
    }

    @Given("I have an invalid booking ID {string}")
    public void i_have_an_invalid_booking_id(String id) {
        bookingId = id;
    }

    @Given("I do not provide a booking ID")
    public void i_do_not_provide_booking_id() {
        bookingId = "";
    }


    @Then("I should receive a 404 error")
    public void iShouldReceiveA404Error() {
        response.then().spec(notFoundResponseSpec());
    }

    @Then("I should receive a 500 error")
    public void iShouldReceiveA500Error() {
        response.then()
                .spec(internalServerErrorSpec());
    }

    @Given("I add a new booking with no firstName")
    public void iAddANewBookingWithInValidDetails() {
        String requestBody = "{\n" +
                "    \"lastname\" : \"lastName\",\n" +
                "    \"totalprice\" : 10.11,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2022-01-01\",\n" +
                "        \"checkout\" : \"2024-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"testAdd\"\n" +
                "}";

        response = given()
                .spec(requestSpec())
                .body(requestBody)
                .when()
                .post("/booking")
                .then()
                .extract()
                .response();

        // Extract the booking ID from the response
    }
}
