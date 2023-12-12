package com.ministryoftesting.integration.examples.intermediate1;

import com.ministryoftesting.api.TimesheetManagerApplication;
import com.ministryoftesting.models.auth.Credentials;
import io.restassured.response.Response;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static io.restassured.RestAssured.given;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = TimesheetManagerApplication.class)
@ActiveProfiles("dev")
public class ProjectApiTest {

    @Test
    public void testGettingProject2(){
        Credentials credentials = given()
                .body("{\"email\":\"admin@test.com\",\"password\":\"password123\"}")
                .contentType("application/json")
                .post("/v1/auth/login")
                .as(Credentials.class);

        String token = credentials.getToken();

        Response response = given()
                .header("Authorization", "Bearer " + token)
                .get("/v1/project/2");

        Approvals.verify(response.getBody().prettyPrint());
    }

}
