package com.ministryoftesting.unit;

import com.ministryoftesting.db.ReportDB;
import com.ministryoftesting.models.project.Entry;
import com.ministryoftesting.models.project.ProjectDetails;
import com.ministryoftesting.models.report.Project;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ReportServiceTest {

    @Mock
    private ReportDB reportDB;

    @Autowired
    @InjectMocks
    private ReportService reportService;

    @BeforeEach
    public void initialiseMocks() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetReport() {
        ArrayList<Project> projects = new ArrayList<>();
        projects.add(new Project(1, "Project 1", 24));
        projects.add(new Project(2, "Project 2", 16));
        projects.add(new Project(3, "Project 3", 8));

        when(reportDB.getEntries()).thenReturn(projects);

        ResponseEntity<Report> report = reportService.getReport();

        assertEquals(report.getStatusCode(), HttpStatus.OK);
        Approvals.verify(report.getBody().toString());
    }

}
