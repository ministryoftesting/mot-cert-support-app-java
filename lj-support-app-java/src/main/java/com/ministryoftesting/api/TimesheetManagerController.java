package com.ministryoftesting.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TimesheetManagerController {

    @RequestMapping(value = "/ping", method = RequestMethod.GET)
    public ResponseEntity<String> testPing(){
        return ResponseEntity.status(HttpStatus.OK).body("pong");
    }

}
