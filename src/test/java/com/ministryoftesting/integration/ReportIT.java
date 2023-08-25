package com.ministryoftesting.integration;

import io.restassured.response.Response;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class ReportIT extends IntegrationSetup {

    @Test
    public void getReportReturnsValidReport() {
        Response response = given()
                                .get("/v1/report");

        Approvals.verify(response.getBody().asString());
    }

}
