package com.ministryoftesting.unit.service;

import com.ministryoftesting.db.ProjectDB;
import com.ministryoftesting.models.project.Entry;
import com.ministryoftesting.models.project.Project;
import com.ministryoftesting.models.report.Report;
import com.ministryoftesting.service.ReportService;
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
import static org.mockito.Mockito.when;

public class ReportServiceTest {

    @Mock
    private ProjectDB projectDB;

    @Autowired
    @InjectMocks
    private ReportService reportService;

    @BeforeEach
    public void initialiseMocks() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetReport() throws SQLException {
        List<Project> projects = new ArrayList<>();
        projects.add(new Project(1, "Project 1", "Project 1 description"));
        projects.add(new Project(2, "Project 2", "Project 2 description"));

        List<Entry> project1Entries = new ArrayList<>();
        project1Entries.add(new Entry(1, LocalDate.of(2021, 1, 1), 8, "Test 1"));
        project1Entries.add(new Entry(2, LocalDate.of(2021, 1, 2), 8, "Test 2"));

        List<Entry> project2Entries = new ArrayList<>();
        project2Entries.add(new Entry(3, LocalDate.of(2021, 1, 1), 8, "Test 3"));
        project2Entries.add(new Entry(4, LocalDate.of(2021, 1, 2), 8, "Test 4"));

        when(projectDB.getProjects()).thenReturn(projects);
        when(projectDB.getProject(1)).thenReturn(new com.ministryoftesting.models.project.ProjectDetails("Project 1", "Project 1 Description", 16, project1Entries));
        when(projectDB.getProject(2)).thenReturn(new com.ministryoftesting.models.project.ProjectDetails("Project 2", "Project 2 Description", 16, project2Entries));

        ResponseEntity<Report> report = reportService.getReport();

        assertEquals(report.getStatusCode(), HttpStatus.OK);
        Approvals.verify(report.getBody().toString());
    }

}
