package com.ministryoftesting.api;

import com.ministryoftesting.models.report.Report;
import com.ministryoftesting.service.AuthService;
import com.ministryoftesting.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.time.LocalDate;

@RestController
public class ReportController {

    @Autowired
    private ReportService reportService;

    @Autowired
    private AuthService authService;

    @RequestMapping(value = "/v1/report", method = RequestMethod.GET)
    public ResponseEntity<Report> getReport(@CookieValue(value ="token", required = true) String token) throws SQLException {
        if(authService.validate(token, LocalDate.now())) {
            return reportService.getReport();
        } else {
            return ResponseEntity.status(401).build();
        }
    }

}
