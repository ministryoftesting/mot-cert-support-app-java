package com.ministryoftesting.integration;

import com.ministryoftesting.models.CreatedID;
import com.ministryoftesting.models.auth.Credentials;
import com.ministryoftesting.models.user.User;
import io.restassured.response.Response;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UsersIT extends IntegrationSetup {

    private String token;
    @BeforeEach
    public void getToken() {
        Credentials credentials = given()
                .body("{\"email\":\"admin@test.com\",\"password\":\"password123\"}")
                .contentType("application/json")
                .post("/v1/auth/login")
                .as(Credentials.class);

        token = credentials.getToken();
    }
    @Test
    public void positiveResponseWhenCreatingUser(){
        Response response = given()
                .body(new User("Jon", "test@email.com", "password123", "user"))
                .contentType("application/json")
                .header("Authorization", "Bearer " + token)
                .post("/v1/user");

        assertEquals(201, response.getStatusCode());
    }

    @Test
    public void negativeResponseWhenCreatingUser(){
        Response response = given()
                .body(new User(null, null, null, null))
                .contentType("application/json")
                .header("Authorization", "Bearer " + token)
                .post("/v1/user");

        assertEquals(400, response.getStatusCode());
    }

    @Test
    public void positiveResponseWhenDeletingUser(){
        User user = given()
                .body(new User("delete-me", "delete@me.com", "password123", "user"))
                .contentType("application/json")
                .header("Authorization", "Bearer " + token)
                .post("/v1/user")
                .as(User.class);

        Response response = given()
                .header("Authorization", "Bearer " + token)
                .delete("/v1/user/" + user.getId());

        assertEquals(202, response.getStatusCode());
    }

    @Test
    public void positiveResponseWhenGettingUserProfile(){
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .get("/v1/user/1");

        assertEquals(200, response.getStatusCode());
    }

    @Test
    public void negativeResponseWhenGettingUserProfile(){
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .get("/v1/user/20");

        assertEquals(404, response.getStatusCode());
    }

    @Test
    public void positiveResponseWhenUpdateUserProfile(){
        User user = given()
                .body(new User("update-me", "update@me.com", "password123", "user"))
                .contentType("application/json")
                .header("Authorization", "Bearer " + token)
                .post("/v1/user")
                .as(User.class);

        Response response = given()
                .body(new User("Ben", "new@email.com", "newpass", "admin"))
                .contentType("application/json")
                .header("Authorization", "Bearer " + token)
                .put("/v1/user/" + user.getId());

        assertEquals(202, response.getStatusCode());
    }

    @Test
    public void negativeResponseWhenUpdateUserProfile(){
        Response response = given()
                .body(new User("Ben", null, null, null))
                .contentType("application/json")
                .header("Authorization", "Bearer " + token)
                .put("/v1/user/1");

        assertEquals(400, response.getStatusCode());
    }

    @Test
    public void positiveResponseWhenGettingUsers(){
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .get("/v1/user");

        Approvals.verify(response.getBody().prettyPrint());
    }
}
