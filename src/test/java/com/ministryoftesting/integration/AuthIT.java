package com.ministryoftesting.integration;

import com.ministryoftesting.integration.requests.AuthRequests;
import com.ministryoftesting.models.auth.Credentials;
import com.ministryoftesting.models.auth.Login;
import com.ministryoftesting.models.auth.Token;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthIT extends IntegrationSetup {

    @Test
    public void testValidateReturnsPositiveResponse(){
        Login loginPayload = new Login("admin@test.com", "password123");
        Credentials credentials = AuthRequests.postLogin(loginPayload)
                                    .as(Credentials.class);

        Token tokenPayload = new Token(credentials.getToken());
        Response response = AuthRequests.postValidate(tokenPayload);

        assertEquals(200, response.getStatusCode());
    }

    @Test
    public void testValidateReturnsNegativeResponse(){
        Token tokenPayload = new Token("321cba");
        Response response = AuthRequests.postValidate(tokenPayload);

        assertEquals(401, response.getStatusCode());
    }

    @Test
    public void testLoginReturnsPositiveResponse(){
        Login loginPayload = new Login("admin@test.com", "password123");
        Response response = AuthRequests.postLogin(loginPayload);

        assertEquals(200, response.getStatusCode());
    }

    @Test
    public void testLoginReturnsNegativeResponse() {
        Login loginPayload = new Login("incorrect@test.com", "password123");
        Response response = AuthRequests.postLogin(loginPayload);

        assertEquals(401, response.getStatusCode());
    }

    @Test
    public void testLogoutReturnPositiveResponse(){
        Login loginPayload = new Login("admin@test.com", "password123");
        Credentials credentials = AuthRequests.postLogin(loginPayload)
                .as(Credentials.class);

        Token tokenPayload = new Token(credentials.getToken());
        Response response = AuthRequests.postLogout(tokenPayload);

        assertEquals(202, response.getStatusCode());
    }

}
