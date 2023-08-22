package com.ministryoftesting.models.auth;

public class Credentials {

    private String token;
    private boolean admin;
    private int id;

    public Credentials() {
        // Jackson deserialization
    }

    public Credentials(String token, boolean admin, int id) {
        this.token = token;
        this.admin = admin;
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public boolean getAdmin() {
        return admin;
    }

    public int getId() {
        return id;
    }

}
