package com.ministryoftesting.e2e.examples.module5;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TimesheetCredential {

//    {
//        "id": 1,
//            "username": "administrator",
//            "email": "admin@test.com",
//            "password": "password123",
//            "role": "admin"
//    }

    @JsonProperty
    private String id;
    @JsonProperty
    private String username;
    @JsonProperty
    private String email;
    @JsonProperty
    private String password;
    @JsonProperty
    private String role;

    public TimesheetCredential(String username, String email, String password, String role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }
}
