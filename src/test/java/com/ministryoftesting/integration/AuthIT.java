package com.ministryoftesting.integration;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthIT extends IntegrationSetup {

    @Test
    public void testValidateReturnsPositiveResponse(){
        Response response = given()
                .body("{\"token\":\"abc123\"}")
                .contentType("application/json")
                .post("/v1/auth/validate");

        assertEquals(200, response.getStatusCode());
    }

    @Test
    public void testValidateReturnsNegativeResponse(){
        Response response = given()
                .body("{\"token\":\"321cba\"}")
                .contentType("application/json")
                .post("/v1/auth/validate");

        assertEquals(401, response.getStatusCode());
    }

    @Test
    public void testLoginReturnsPositiveResponse(){
        Response response = given()
                .body("{\"email\":\"admin@test.com\",\"password\":\"password123\"}")
                .contentType("application/json")
                .post("/v1/auth/login");

        assertEquals(200, response.getStatusCode());
    }

    @Test
    public void testLoginReturnsNegativeResponse() {
        Response response = given()
                .body("{\"email\":\"incorrect@test.com\",\"password\":\"password123\"}")
                .contentType("application/json")
                .post("/v1/auth/login");

        assertEquals(401, response.getStatusCode());
    }

    @Test
    public void testLogoutReturnPositiveResponse(){
        Response response = given()
                .body("{\"token\":\"abc123\"}")
                .contentType("application/json")
                .post("/v1/auth/logout");

        assertEquals(202, response.getStatusCode());
    }

}
