// This line specifies the package name where this Java class belongs.
package com.ministryoftesting.integration.examples;

// Import statements are used to include external libraries or classes for use in this Java file.
import io.restassured.response.Response; // Import for handling HTTP response data.
import org.junit.jupiter.api.Test; // Import for using JUnit test annotations.

// These are static imports that bring in specific methods or properties from external classes.
import static io.restassured.RestAssured.given; // Importing the 'given' method from the RestAssured library.
import static org.junit.jupiter.api.Assertions.assertEquals; // Importing the 'assertEquals' method from JUnit.

// This line defines a Java class called 'LoginApiTest'.
public class LoginApiTest {

    // This is a JUnit test method, indicated by the @Test annotation.
    @Test
    public void testCheckLoginReturnsPositiveResult(){

        // Create an instance of the 'AuthPayload' class with email and password.
        AuthPayload authPayload = new AuthPayload("admin@test.com", "password123");

        // Send an HTTP POST request to the specified URL with the 'authPayload' data.
        Response response = given()
                .body(authPayload) // Set the request body to 'authPayload'.
                .contentType("application/json") // Set the content type of the request to JSON.
                .post("http://localhost:8080/v1/auth/login"); // The URL to send the request to.

        // Check if the HTTP response status code is equal to 200.
        assertEquals(200, response.getStatusCode());
    }
}