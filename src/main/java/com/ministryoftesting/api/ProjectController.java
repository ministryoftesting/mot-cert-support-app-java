package com.ministryoftesting.api;

import com.ministryoftesting.models.project.Entry;
import com.ministryoftesting.models.project.Project;
import com.ministryoftesting.models.project.ProjectDetails;
import com.ministryoftesting.service.AuthService;
import com.ministryoftesting.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private AuthService authService;

    @RequestMapping(value = "/v1/project", method = RequestMethod.POST)
    public ResponseEntity<?> createProject(@Valid @RequestBody Project project, @RequestHeader("Authorization") String token) throws SQLException {
        if(authService.validate(token.replace("Bearer ", ""), LocalDate.now())){
            return projectService.createProject(project);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @RequestMapping(value = "/v1/project/{projectid:[0-9]*}/entry/{entryid:[0-9]*}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteEntry(@PathVariable(value = "projectid") int projectid, @PathVariable(value = "entryid") int entryid, @RequestHeader("Authorization") String token) throws SQLException {
        if(authService.validate(token.replace("Bearer ", ""), LocalDate.now())) {
            return projectService.deleteEntry(projectid, entryid);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @RequestMapping(value = "/v1/project/{projectid:[0-9]*}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteProject(@PathVariable(value = "projectid") int projectid, @RequestHeader("Authorization") String token) throws SQLException {
        if(authService.validate(token.replace("Bearer ", ""), LocalDate.now())){
            return projectService.deleteProject(projectid);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @RequestMapping(value = "/v1/project/{projectid:[0-9]*}", method = RequestMethod.GET)
    public ResponseEntity<ProjectDetails> getProject(@PathVariable(value = "projectid") int projectid, @RequestHeader("Authorization") String token) throws SQLException {
        if(authService.validate(token.replace("Bearer ", ""), LocalDate.now())) {
            return projectService.getProject(projectid);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @RequestMapping(value = "/v1/project", method = RequestMethod.GET)
    public ResponseEntity<List<Project>> getProjects(@RequestHeader("Authorization") String token) throws SQLException {
        if(authService.validate(token.replace("Bearer ", ""), LocalDate.now())){
            return projectService.getProjects();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @RequestMapping(value = "/v1/project/{projectid:[0-9]*}/entry", method = RequestMethod.POST)
    public ResponseEntity<?> createEntry(@PathVariable(value = "projectid") int projectid, @Valid @RequestBody Entry entry, @RequestHeader("Authorization") String token) throws SQLException {
        if(authService.validate(token.replace("Bearer ", ""), LocalDate.now())){
            return projectService.createEntry(projectid, entry);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
