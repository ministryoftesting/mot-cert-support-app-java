package com.ministryoftesting.models.report;

import java.util.List;

public class Report {

    private int total;
    private List<Project> projects;

    public Report() {
    }

    public Report(int total, List<Project> projects) {
        this.total = total;
        this.projects = projects;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

}
