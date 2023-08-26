package com.ministryoftesting.integration;

import com.ministryoftesting.models.project.Entry;
import com.ministryoftesting.models.project.Project;
import io.restassured.response.Response;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProjectsIT extends IntegrationSetup {

    @Test
    public void returnPositiveResponseWhenCreatingProject() {
        Response response = given()
                .header("Content-Type", "application/json")
                .body(new Project("Project 1", "Ate Cake"))
                .when()
                .post("/v1/project");

        assertEquals(201, response.getStatusCode());
    }

    @Test
    public void returnNegativeResponseWhenCreatingBadProject(){
        Response response = given()
                .header("Content-Type", "application/json")
                .body(new Project(null, null))
                .when()
                .post("/v1/project");

        assertEquals(400, response.getStatusCode());
    }

    @Test
    public void returnPositiveResponseDeletingEntry(){
        Response response = given()
                .delete("/v1/project/1/entry/1");

        assertEquals(202, response.getStatusCode());
    }

    @Test
    public void returnNegativeResponseDeletingEntry(){
        Response response = given()
                .delete("/v1/project/1/entry/2");

        assertEquals(404, response.getStatusCode());
    }

    @Test
    public void returnPositiveResponseDeletingProject(){
        Response response = given()
                .delete("/v1/project/1");

        assertEquals(202, response.getStatusCode());
    }

    @Test
    public void returnNegativeResponseDeletingProject(){
        Response response = given()
                .delete("/v1/project/2");

        assertEquals(404, response.getStatusCode());
    }

    @Test
    public void returnPositiveResponseWhenGettingProjectTimesheet(){
        Response response = given()
                .get("/v1/project/1");

        Approvals.verify(response.getBody().prettyPrint());
    }

    @Test
    public void returnPositiveResponseGettingProjects(){
        Response response = given()
                .get("/v1/project");

        Approvals.verify(response.getBody().prettyPrint());
    }

    @Test
    public void returnPositiveResponseSubmittingTimesheet(){
        Response response = given()
                .header("Content-Type", "application/json")
                .body(new Entry(1, LocalDate.of(2050, 1, 1), 8, "Ate cake"))
                .when()
                .post("/v1/project/1/entry");

        assertEquals(201, response.getStatusCode());
    }

    @Test
    public void returnNegativeResponseSubmittingTimesheet(){
        Response response = given()
                .header("Content-Type", "application/json")
                .body(new Entry(1, null, 0, null))
                .when()
                .post("/v1/project/1/entry");

        assertEquals(400, response.getStatusCode());
    }

}
