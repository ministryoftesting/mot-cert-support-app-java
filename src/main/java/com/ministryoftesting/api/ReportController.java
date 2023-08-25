package com.ministryoftesting.api;

import com.ministryoftesting.models.report.Project;
import com.ministryoftesting.models.report.Report;
import com.ministryoftesting.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ReportController {

    @Autowired
    private ReportService reportService;

    @RequestMapping(value = "/v1/report", method = RequestMethod.GET)
    public ResponseEntity<Report> getReport() {
        System.out.println("ReportController.getReport");
        return reportService.getReport();
    }

}
