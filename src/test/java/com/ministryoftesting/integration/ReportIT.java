package com.ministryoftesting.integration;

import com.ministryoftesting.models.auth.Credentials;
import com.ministryoftesting.models.report.Report;
import io.restassured.response.Response;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class ReportIT extends IntegrationSetup {

    @Test
    public void getReportReturnsValidReport() {
        Credentials credentials = given()
                .body("{\"email\":\"admin@test.com\",\"password\":\"password123\"}")
                .contentType("application/json")
                .post("/v1/auth/login")
                .as(Credentials.class);

        Report response = given()
                                .header("Cookie", "token=" + credentials.getToken())
                                .get("/v1/report")
                                .as(Report.class);

        System.out.println(response.toString());

        assertFalse(response.getProjects().isEmpty());
    }

}
