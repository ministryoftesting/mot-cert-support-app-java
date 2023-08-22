package com.ministryoftesting.models.report;

public class Project {

    private String name;

    private int hours;

    public Project() {
    }

    public Project(String name, int hours) {
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
}
