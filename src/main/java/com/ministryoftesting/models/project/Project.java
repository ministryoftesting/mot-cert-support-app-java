package com.ministryoftesting.models.project;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;

@Entity
public class Project {

    private int id;

    @NotNull(message = "Name must be set")
    private String name;

    @NotNull(message = "Description must be set")
    private String description;

    public Project() {

    }

    public Project(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Project(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
