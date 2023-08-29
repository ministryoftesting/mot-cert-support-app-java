package com.ministryoftesting.service;

import com.ministryoftesting.db.ProjectDB;
import com.ministryoftesting.models.project.Entry;
import com.ministryoftesting.models.project.Project;
import com.ministryoftesting.models.report.ProjectSummary;
import com.ministryoftesting.models.report.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReportService {

    @Autowired
    private ProjectDB projectDB;

    public ResponseEntity<Report> getReport() throws SQLException {
        int timesheetTotal = 0;
        List<Project> projects = projectDB.getProjects();
        List<ProjectSummary> projectDetails = new ArrayList<>();

        for (Project project : projects) {
            List<Entry> entries = projectDB.getProject(project.getId()).getEntries();
            int projectTotal = 0;

            for(Entry entry : entries) {
                timesheetTotal += entry.getHours();
                projectTotal += entry.getHours();
            }
            projectDetails.add(new ProjectSummary(project.getId(), project.getName(), projectTotal));
        }

        return new ResponseEntity<>(new Report(timesheetTotal, projectDetails), HttpStatus.OK);
    }
}
