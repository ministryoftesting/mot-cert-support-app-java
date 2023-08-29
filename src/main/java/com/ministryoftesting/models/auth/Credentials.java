package com.ministryoftesting.models.auth;

public class Credentials {

    private String token;
    private boolean admin;
    private int id;

    public Credentials() {
        // Jackson deserialization
    }

    public Credentials(String token, boolean admin) {
        this.token = token;
        this.admin = admin;
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

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Credentials{" +
                "token='" + token + '\'' +
                ", admin=" + admin +
                ", id=" + id +
                '}';
    }
}
