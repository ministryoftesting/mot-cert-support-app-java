package com.ministryoftesting.api;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.ministryoftesting")
@OpenAPIDefinition
public class TimesheetManagerApplication {

        public static void main(String[] args) { SpringApplication.run(TimesheetManagerApplication.class, args); }

}
