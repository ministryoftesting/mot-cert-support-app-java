package com.ministryoftesting.service;

import com.ministryoftesting.db.ProjectDB;
import com.ministryoftesting.models.CreatedID;
import com.ministryoftesting.models.project.Entry;
import com.ministryoftesting.models.project.Project;
import com.ministryoftesting.models.project.ProjectDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectDB projectDB;

    public ResponseEntity<CreatedID> createProject(Project projectToCreate) throws SQLException {
        int projectCreated = projectDB.createProject(projectToCreate);

        if(projectCreated > 0) {
            CreatedID createdID = new CreatedID(projectCreated);

            return new ResponseEntity<>(createdID, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> deleteProject(int projectId) throws SQLException {
        ProjectDetails project = projectDB.getProject(projectId);

        if(project != null){
            for(Entry entry : project.getEntries()){
                projectDB.deleteEntry(projectId, entry.getId());
            }

            projectDB.deleteProject(projectId);

            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public ResponseEntity<?> deleteEntry(int projectId, int entryId) throws SQLException {
        boolean entryDeleted = projectDB.deleteEntry(projectId, entryId);

        if(entryDeleted) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public ResponseEntity<ProjectDetails> getProject(int projectId) throws SQLException {
        ProjectDetails project = projectDB.getProject(projectId);

        if(project != null) {
            return ResponseEntity.status(HttpStatus.OK).body(project);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public ResponseEntity<List<Project>> getProjects() throws SQLException {
        List<Project> projects = projectDB.getProjects();

        return ResponseEntity.status(HttpStatus.OK).body(projects);
    }

    public ResponseEntity<?> createEntry(int projectId, Entry entry) throws SQLException {
        int entryCreated = projectDB.storeEntry(projectId, entry);

        if(entryCreated > 0) {
            CreatedID createdID = new CreatedID(entryCreated);

            return ResponseEntity.status(HttpStatus.CREATED).body(createdID);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
