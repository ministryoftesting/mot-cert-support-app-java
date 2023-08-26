package com.ministryoftesting.api;

import com.ministryoftesting.models.project.Entry;
import com.ministryoftesting.models.project.Project;
import com.ministryoftesting.models.project.ProjectDetails;
import com.ministryoftesting.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @RequestMapping(value = "/v1/project", method = RequestMethod.POST)
    public ResponseEntity<?> createProject(@Valid @RequestBody Project project) {
        return projectService.createProject(project);
    }

    @RequestMapping(value = "/v1/project/{projectid:[0-9]*}/entry/{entryid:[0-9]*}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteEntry(@PathVariable(value = "projectid") int projectid, @PathVariable(value = "entryid") int entryid) {
        return projectService.deleteEntry(projectid, entryid);
    }

    @RequestMapping(value = "/v1/project/{projectid:[0-9]*}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteProject(@PathVariable(value = "projectid") int projectid) {
        return projectService.deleteProject(projectid);
    }

    @RequestMapping(value = "/v1/project/{projectid:[0-9]*}", method = RequestMethod.GET)
    public ResponseEntity<ProjectDetails> getProject(@PathVariable(value = "projectid") int projectid) {
        return projectService.getProject(projectid);
    }

    @RequestMapping(value = "/v1/project", method = RequestMethod.GET)
    public ResponseEntity<ArrayList<Project>> getProjects() {
        return projectService.getProjects();
    }

    @RequestMapping(value = "/v1/project/{projectid:[0-9]*}/entry", method = RequestMethod.POST)
    public ResponseEntity<?> createEntry(@PathVariable(value = "projectid") int projectid, @Valid @RequestBody Entry entry) {
        return projectService.createEntry(projectid, entry);
    }

}
