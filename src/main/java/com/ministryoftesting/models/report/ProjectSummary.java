package com.ministryoftesting.models.report;

public class ProjectSummary {

    private int id;

    private String name;

    private int hours;

    public ProjectSummary() {
    }

    public ProjectSummary(int id, String name, int hours) {
        this.id = id;
        this.name = name;
        this.hours = hours;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", hours=" + hours +
                '}';
    }
}
