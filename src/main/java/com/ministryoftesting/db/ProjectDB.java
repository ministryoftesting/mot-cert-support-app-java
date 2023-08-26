package com.ministryoftesting.db;

import com.ministryoftesting.models.project.Entry;
import com.ministryoftesting.models.project.Project;
import com.ministryoftesting.models.project.ProjectDetails;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProjectDB {

    public boolean createProject(Project projectToCreate) {
        return projectToCreate.getName().equals("Project 1");
    }

    public boolean deleteProject(int projectId) {
        return projectId == 1;
    }

    public Boolean deleteEntry(int projectId, int entryId) {
        return projectId == 1 && entryId == 1;
    }

    public ProjectDetails getProject(int projectId) {
        if(projectId == 1){
            List<Entry> entries = new ArrayList<>();
            entries.add(new Entry(1, LocalDate.of(2023,1,1), 8, "Ate cake"));
            entries.add(new Entry(2, LocalDate.of(2023,1,2), 7, "Ate pie"));
            entries.add(new Entry(3, LocalDate.of(2023, 1, 3), 6, "Ate pizza"));

            ProjectDetails projectDetails = new ProjectDetails("Project 1", "This is a brief description of Project 1", 21, entries);

            return projectDetails;
        } else {
            return null;
        }

    }

    public ArrayList<Project> getProjects() {
        ArrayList<Project> projects = new ArrayList<>();
        projects.add(new Project(1, "Project 1", "This is a brief description of Project 1"));
        projects.add(new Project(2, "Project 2", "This is a brief description of Project 2"));
        projects.add(new Project(3, "Project 3", "This is a brief description of Project 3"));

        return projects;
    }

    public Boolean createEntry(int projectId, Entry entry) {
        return projectId == 1 && entry.getDescription().equals("Ate cake");
    }
}
