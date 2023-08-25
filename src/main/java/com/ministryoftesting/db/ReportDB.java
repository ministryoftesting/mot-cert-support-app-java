package com.ministryoftesting.db;

import com.ministryoftesting.models.report.Project;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ReportDB {

    public ArrayList<Project> getEntries() {
        ArrayList<Project> projects = new ArrayList<>();
        projects.add(new Project(1, "Project 1", 24));
        projects.add(new Project(2, "Project 2", 16));
        projects.add(new Project(3, "Project 3", 8));

        return projects;
    }
}
