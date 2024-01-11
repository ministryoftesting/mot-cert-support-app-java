package com.ministryoftesting.models.report;

import java.util.List;

public class Report {

    private int total;
    private List<ProjectSummary> projects;

    public Report() {
    }

    public Report(int total, List<ProjectSummary> projects) {
        this.total = total;
        this.projects = projects;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<ProjectSummary> getProjects() {
        return projects;
    }

    public void setProjects(List<ProjectSummary> projects) {
        this.projects = projects;
    }

    @Override
    public String toString() {
        return "Report{" +
                "total=" + total +
                ", projects=" + projects.toString() +
                '}';
    }
}
