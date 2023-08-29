package com.ministryoftesting.models.project;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

@Entity
public class Entry {

    private int id;
    @NotNull
    private LocalDate date;
    @NotNull
    @Positive
    private int hours;
    @NotNull
    private String description;

    public Entry() {

    }

    public Entry(int id, LocalDate date, int hours, String description) {
        this.id = id;
        this.date = date;
        this.hours = hours;
        this.description = description;
    }

    public Entry(LocalDate date, int hours, String description) {
        this.date = date;
        this.hours = hours;
        this.description = description;
    }

    public Entry(ResultSet resultSet) throws SQLException {
        this.id = resultSet.getInt("entryid");
        this.date = resultSet.getDate("date").toLocalDate();
        this.hours = resultSet.getInt("hours");
        this.description = resultSet.getString("description");
    }

    public int getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getHours() {
        return hours;
    }

    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Entry{" +
                "id=" + id +
                ", date=" + date +
                ", hours=" + hours +
                ", description='" + description + '\'' +
                '}';
    }
}
