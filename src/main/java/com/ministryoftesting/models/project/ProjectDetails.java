package com.ministryoftesting.models.project;

import java.util.List;

public class ProjectDetails {

    private String name;
    private String description;
    private int total;
    private List<Entry> entries;

    public ProjectDetails() {

    }

    public ProjectDetails(String name, String description, int total, List<Entry> entries) {
        this.name = name;
        this.description = description;
        this.total = total;
        this.entries = entries;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Entry> getEntries() {
        return entries;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }

    @Override
    public String toString() {
        return "ProjectDetails{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", total=" + total +
                ", entries=" + entries +
                '}';
    }
}
