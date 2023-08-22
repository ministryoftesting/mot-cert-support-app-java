package com.ministryoftesting.integration;

import com.ministryoftesting.models.user.User;
import io.restassured.response.Response;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UsersIT extends IntegrationSetup {

    @Test
    public void positiveResponseWhenCreatingUser(){
        Response response = given()
                .body(new User("Jon", "test@email.com", "password123", "user"))
                .contentType("application/json")
                .post("/v1/user");

        assertEquals(201, response.getStatusCode());
    }

    @Test
    public void negativeResponseWhenCreatingUser(){
        Response response = given()
                .body(new User(null, null, null, null))
                .contentType("application/json")
                .post("/v1/user");

        assertEquals(400, response.getStatusCode());
    }

    @Test
    public void positiveResponseWhenDeletingUser(){
        Response response = given()
                .delete("/v1/user/1");

        assertEquals(202, response.getStatusCode());
    }

    @Test
    public void positiveResponseWhenGettingUserProfile(){
        Response response = given()
                .get("/v1/user/1");

        assertEquals(200, response.getStatusCode());
    }

    @Test
    public void negativeResponseWhenGettingUserProfile(){
        Response response = given()
                .get("/v1/user/2");

        assertEquals(404, response.getStatusCode());
    }

    @Test
    public void positiveResponseWhenUpdateUserProfile(){
        Response response = given()
                .body(new User("Ben", "new@email.com", "newpass", "admin"))
                .contentType("application/json")
                .put("/v1/user/1");

        assertEquals(202, response.getStatusCode());
    }

    @Test
    public void negativeResponseWhenUpdateUserProfile(){
        Response response = given()
                .body(new User("Ben", null, null, null))
                .contentType("application/json")
                .put("/v1/user/1");

        assertEquals(400, response.getStatusCode());
    }

    @Test
    public void positiveResponseWhenGettingUsers(){
        Response response = given()
                .get("/v1/user");

        Approvals.verify(response.getBody().prettyPrint());
    }
}
