package com.ministryoftesting.integration.examples.intermediate1;

import com.ministryoftesting.api.TimesheetManagerApplication;
import com.ministryoftesting.models.auth.Credentials;
import io.qameta.allure.Allure;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import org.apache.commons.io.output.WriterOutputStream;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = TimesheetManagerApplication.class)
@ActiveProfiles("dev")
public class ProjectApiTest {

    @BeforeEach
    public void setupLogging(TestInfo testInfo) throws IOException {
        FileWriter fileWriter = new FileWriter("target/" + testInfo.getDisplayName() + ".log");
        PrintStream printStream = new PrintStream(new WriterOutputStream(fileWriter), true);

        RestAssured.filters(new RequestLoggingFilter(printStream), new ResponseLoggingFilter(printStream));
    }

    @AfterEach
    public void addLogToAllure(TestInfo testInfo) throws IOException {
        String log = new String(Files.readAllBytes(Paths.get("target/" + testInfo.getDisplayName() + ".log")));

        Allure.addAttachment(testInfo.getDisplayName(), log);
    }

    @Test
    @DisplayName("Test getting a project")
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
