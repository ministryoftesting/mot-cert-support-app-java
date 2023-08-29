package com.ministryoftesting.api;

import com.ministryoftesting.models.report.Report;
import com.ministryoftesting.service.AuthService;
import com.ministryoftesting.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.time.LocalDate;

@RestController
public class ReportController {

    @Autowired
    private ReportService reportService;

    @Autowired
    private AuthService authService;

    @RequestMapping(value = "/v1/report", method = RequestMethod.GET)
    public ResponseEntity<Report> getReport(@RequestHeader("Authorization") String token) throws SQLException {
        if(authService.validate(token.replace("Bearer ", ""), LocalDate.now())) {
            return reportService.getReport();
        } else {
            return ResponseEntity.status(401).build();
        }
    }

}
