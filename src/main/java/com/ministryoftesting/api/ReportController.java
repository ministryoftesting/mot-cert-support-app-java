package com.ministryoftesting.api;

import com.ministryoftesting.models.report.Project;
import com.ministryoftesting.models.report.Report;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ReportController {

    @RequestMapping(value = "/v1/report/", method = RequestMethod.GET)
    public ResponseEntity<Report> getReport() {
        List<Project> projects = new ArrayList<>();
        projects.add(new Project("Project 1", 24));
        projects.add(new Project("Project 2", 16));
        projects.add(new Project("Project 3", 8));

        Report report = new Report(48, projects);

        return ResponseEntity.status(HttpStatus.OK).body(report);
    }

}
