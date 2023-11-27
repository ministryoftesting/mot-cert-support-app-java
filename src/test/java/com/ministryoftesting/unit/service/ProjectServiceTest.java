package com.ministryoftesting.unit.service;

import com.ministryoftesting.db.AuthDB;
import com.ministryoftesting.db.ProjectDB;
import com.ministryoftesting.models.CreatedID;
import com.ministryoftesting.models.project.Entry;
import com.ministryoftesting.models.project.Project;
import com.ministryoftesting.models.project.ProjectDetails;
import com.ministryoftesting.service.ProjectService;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ProjectServiceTest {

    @Mock
    private ProjectDB projectDB;

    @Mock
    private AuthDB authDB;

    @Autowired
    @InjectMocks
    private ProjectService projectService;

    @BeforeEach
    public void initialiseMocks() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void returnPositiveResponseWhenProjectIsCreated() throws SQLException {
        Project project = new Project("Project 1", "Project description goes here");

        when(projectDB.createProject(project)).thenReturn(1);
        when(authDB.checkSession(any(), any())).thenReturn(true);

        ResponseEntity<CreatedID> response = projectService.createProject(project);

        assertEquals(1, response.getBody().getId());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void returnPositiveWhenProjectIsDeleted() throws SQLException {
        List<Entry> entries = new ArrayList<>();
        entries.add(new Entry(1, LocalDate.of(2023,1,1), 8, "Ate cake"));

        when(projectDB.getProject(1)).thenReturn(new ProjectDetails("Project 1", "This is a brief description of Project 1", 21, entries));
        when(projectDB.deleteEntry(1,1)).thenReturn(true);
        when(projectDB.deleteProject(1)).thenReturn(true);

        ResponseEntity<?> response = projectService.deleteProject(1);

        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
    }

    @Test
    public void returnNegativeWhenProjectIsNotFound() throws SQLException {
        when(projectDB.deleteProject(1)).thenReturn(false);

        ResponseEntity<?> response = projectService.deleteProject(1);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void returnPositiveWhenEntryIsDeleted() throws SQLException {
        when(projectDB.deleteEntry(1, 1)).thenReturn(true);

        ResponseEntity<?> response = projectService.deleteEntry(1, 1);

        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
    }

    @Test
    public void returnNegativeWhenEntryIsNotFound() throws SQLException {
        when(projectDB.deleteEntry(1, 2)).thenReturn(false);

        ResponseEntity<?> response = projectService.deleteEntry(1, 1);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void returnProjectDetailsWhenProjectIsFound() throws SQLException {
        List<Entry> entries = new ArrayList<>();
        entries.add(new Entry(1, LocalDate.of(2023,1,1), 8, "Ate cake"));
        entries.add(new Entry(2, LocalDate.of(2023,1,2), 7, "Ate pie"));
        entries.add(new Entry(3, LocalDate.of(2023, 1, 3), 6, "Ate pizza"));

        ProjectDetails projectDetails = new ProjectDetails("Project 1", "This is a brief description of Project 1", 21, entries);
        when(projectDB.getProject(1)).thenReturn(projectDetails);

        ResponseEntity<ProjectDetails> response = projectService.getProject(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        Approvals.verify(response.getBody().toString());
    }

    @Test
    public void returnProjectNotFoundWhenProjectIsNotFound() throws SQLException {
        when(projectDB.getProject(2)).thenReturn(null);

        ResponseEntity<ProjectDetails> response = projectService.getProject(2);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void returnProjectsWhenRequested() throws SQLException {
        ArrayList<Project> projects = new ArrayList<>();
        projects.add(new Project(1, "Project 1", "This is a brief description of Project 1"));
        projects.add(new Project(2, "Project 2", "This is a brief description of Project 2"));
        projects.add(new Project(3, "Project 3", "This is a brief description of Project 3"));

        when(projectDB.getProjects()).thenReturn(projects);

        ResponseEntity<List<Project>> response = projectService.getProjects();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        Approvals.verify(response.getBody().toString());
    }

    @Test
    public void returnEmptyProjectListWhenRequested() throws SQLException {
        ArrayList<Project> projects = new ArrayList<>();

        when(projectDB.getProjects()).thenReturn(projects);

        ResponseEntity<List<Project>> response = projectService.getProjects();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        Approvals.verify(response.getBody().toString());
    }

    @Test
    public void returnPositiveWhenEntryCreated() throws SQLException {
        Entry entry = new Entry(LocalDate.of(2023,1,1), 8, "Ate cake");

        when(projectDB.storeEntry(1, entry)).thenReturn(1);

        ResponseEntity<?> response = projectService.createEntry(1, entry);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }
}
