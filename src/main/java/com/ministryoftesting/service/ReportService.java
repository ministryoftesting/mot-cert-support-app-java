package com.ministryoftesting.service;

import com.ministryoftesting.db.ReportDB;
import com.ministryoftesting.models.project.Entry;
import com.ministryoftesting.models.report.Project;
import com.ministryoftesting.models.report.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ReportService {

    @Autowired
    private ReportDB reportDB;

    public ResponseEntity<Report> getReport() {
        int total = 0;
        ArrayList<Project> projects = reportDB.getEntries();

        for (Project project : projects) {
            total += project.getHours();
        }

        return new ResponseEntity<>(new Report(total, projects), HttpStatus.OK);
    }
}
