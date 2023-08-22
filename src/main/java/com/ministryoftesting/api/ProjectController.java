package com.ministryoftesting.api;

import com.ministryoftesting.models.project.Entry;
import com.ministryoftesting.models.project.Project;
import com.ministryoftesting.models.project.ProjectDetails;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ProjectController {

    @RequestMapping(value = "/v1/project", method = RequestMethod.POST)
    public ResponseEntity<Void> createProject(@Valid @RequestBody Project project) {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @RequestMapping(value = "/v1/project/{projectid:[0-9]*}/entry/{entryid:[0-9]*}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteEntry(@PathVariable(value = "projectid") int projectid, @PathVariable(value = "entryid") int entryid) {
        if(projectid == 1 && entryid == 1){
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @RequestMapping(value = "/v1/project/{projectid:[0-9]*}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteProject(@PathVariable(value = "projectid") int projectid) {
        if(projectid == 1){
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @RequestMapping(value = "/v1/project/{projectid:[0-9]*}", method = RequestMethod.GET)
    public ResponseEntity<ProjectDetails> getProject(@PathVariable(value = "projectid") int projectid) {
        if(projectid == 1){
            List<Entry> entries = new ArrayList<>();
            entries.add(new Entry(1, LocalDate.of(2023,1,1), 8, "Ate cake"));
            entries.add(new Entry(2, LocalDate.of(2023,1,2), 7, "Ate pie"));
            entries.add(new Entry(3, LocalDate.of(2023, 1, 3), 6, "Ate pizza"));

            ProjectDetails projectDetails = new ProjectDetails("Project 1", "This is a brief description of Project 1", 21, entries);
            return ResponseEntity.status(HttpStatus.OK).body(projectDetails);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @RequestMapping(value = "/v1/project", method = RequestMethod.GET)
    public ResponseEntity<ArrayList<Project>> getProjects() {
        ArrayList<Project> projects = new ArrayList<>();
        projects.add(new Project(1, "Project 1", "This is a brief description of Project 1"));
        projects.add(new Project(2, "Project 2", "This is a brief description of Project 2"));
        projects.add(new Project(3, "Project 3", "This is a brief description of Project 3"));
        return ResponseEntity.status(HttpStatus.OK).body(projects);
    }

    @RequestMapping(value = "/v1/project/{projectid:[0-9]*}/entry", method = RequestMethod.POST)
    public ResponseEntity<Void> createEntry(@PathVariable(value = "projectid") int projectid, @Valid @RequestBody Entry entry) {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
