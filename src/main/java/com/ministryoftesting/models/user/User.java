package com.ministryoftesting.models.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User {

    private int id;
    @NotNull
    private String username;
    @NotNull
    @Email
    private String email;
    @NotNull
    private String password;
    @NotNull
    private String role;

    public User() {
    }

    public User(ResultSet resultSet) throws SQLException {
        this.id = resultSet.getInt("userid");
        this.username = resultSet.getString("username");
        this.email = resultSet.getString("email");
        this.password = resultSet.getString("password");
        this.role = resultSet.getString("role");
    }

    public User(int id, String username, String email, String password, String role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User(String username, String email, String password, String role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
