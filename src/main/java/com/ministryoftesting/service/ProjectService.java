package com.ministryoftesting.service;

import com.ministryoftesting.db.ProjectDB;
import com.ministryoftesting.models.project.Entry;
import com.ministryoftesting.models.project.Project;
import com.ministryoftesting.models.project.ProjectDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProjectService {

    @Autowired
    private ProjectDB projectDB;

    public ResponseEntity<?> createProject(Project projectToCreate) {
        boolean projectCreated = projectDB.createProject(projectToCreate);

        if(projectCreated) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> deleteProject(int projectId) {
        boolean projectDeleted = projectDB.deleteProject(projectId);

        if(projectDeleted) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public ResponseEntity<?> deleteEntry(int projectId, int entryId) {
        boolean entryDeleted = projectDB.deleteEntry(projectId, entryId);

        if(entryDeleted) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public ResponseEntity<ProjectDetails> getProject(int projectId) {
        ProjectDetails project = projectDB.getProject(projectId);

        if(project != null) {
            return ResponseEntity.status(HttpStatus.OK).body(project);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public ResponseEntity<ArrayList<Project>> getProjects() {
        ArrayList<Project> projects = projectDB.getProjects();

        return ResponseEntity.status(HttpStatus.OK).body(projects);
    }

    public ResponseEntity<?> createEntry(int projectId, Entry entry) {
        boolean entryCreated = projectDB.createEntry(projectId, entry);

        if(entryCreated) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
