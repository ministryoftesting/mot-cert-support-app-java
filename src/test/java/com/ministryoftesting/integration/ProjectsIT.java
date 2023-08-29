package com.ministryoftesting.integration;

import com.ministryoftesting.models.CreatedID;
import com.ministryoftesting.models.auth.Credentials;
import com.ministryoftesting.models.project.Entry;
import com.ministryoftesting.models.project.Project;
import io.restassured.response.Response;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProjectsIT extends IntegrationSetup {

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
    public void returnPositiveResponseWhenCreatingProject() {
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Cookie", "token=" + token)
                .body(new Project("Project 1", "Ate Cake"))
                .when()
                .post("/v1/project");

        assertEquals(201, response.getStatusCode());
    }

    @Test
    public void returnNegativeResponseWhenCreatingBadProject(){
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Cookie", "token=" + token)
                .body(new Project(null, null))
                .when()
                .post("/v1/project");

        assertEquals(400, response.getStatusCode());
    }

    @Test
    public void returnPositiveResponseDeletingEntry(){
        CreatedID projectID = given()
                .header("Content-Type", "application/json")
                .header("Cookie", "token=" + token)
                .body(new Project("Project 1", "Ate Cake"))
                .when()
                .post("/v1/project")
                .as(CreatedID.class);

        CreatedID entryID = given()
                .header("Content-Type", "application/json")
                .header("Cookie", "token=" + token)
                .body(new Entry(projectID.getId(), LocalDate.of(2050, 1, 1), 8, "Ate cake"))
                .when()
                .post("/v1/project/" + projectID.getId() + "/entry")
                .as(CreatedID.class);

        Response response = given()
                .header("Cookie", "token=" + token)
                .delete("/v1/project/" + projectID.getId() + "/entry/" + entryID.getId());

        assertEquals(202, response.getStatusCode());
    }

    @Test
    public void returnNegativeResponseDeletingEntry(){
        Response response = given()
                .header("Cookie", "token=" + token)
                .delete("/v1/project/1/entry/2");

        assertEquals(404, response.getStatusCode());
    }

    @Test
    public void returnPositiveResponseDeletingProject(){
        Response response = given()
                .header("Cookie", "token=" + token)
                .delete("/v1/project/1");

        assertEquals(202, response.getStatusCode());
    }

    @Test
    public void returnNegativeResponseDeletingProject(){
        Response response = given()
                .header("Cookie", "token=" + token)
                .delete("/v1/project/99");

        assertEquals(404, response.getStatusCode());
    }

    @Test
    public void returnPositiveResponseWhenGettingProjectTimesheet(){
        CreatedID createdID = given()
                .header("Content-Type", "application/json")
                .header("Cookie", "token=" + token)
                .body(new Project("Project 1", "Ate Cake"))
                .when()
                .post("/v1/project")
                .as(CreatedID.class);

        Response response = given()
                .header("Cookie", "token=" + token)
                .get("/v1/project/" + createdID.getId());

        Approvals.verify(response.getBody().prettyPrint());
    }

    @Test
    public void returnPositiveResponseGettingProjects(){
        Response response = given()
                .header("Cookie", "token=" + token)
                .get("/v1/project");

        Approvals.verify(response.getBody().prettyPrint());
    }

    @Test
    public void returnPositiveResponseSubmittingTimesheet(){
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Cookie", "token=" + token)
                .body(new Entry(1, LocalDate.of(2050, 1, 1), 8, "Ate cake"))
                .when()
                .post("/v1/project/1/entry");

        assertEquals(201, response.getStatusCode());
    }

    @Test
    public void returnNegativeResponseSubmittingTimesheet(){
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Cookie", "token=" + token)
                .body(new Entry(1, null, 0, null))
                .when()
                .post("/v1/project/1/entry");

        assertEquals(400, response.getStatusCode());
    }

}
