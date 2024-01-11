package com.ministryoftesting.models.auth;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginResult {

    private boolean isUser;
    private String userType;

    private int id;

    public LoginResult(boolean isUser, String userType, int id) {
        this.isUser = isUser;
        this.userType = userType;
        this.id = id;
    }

    public LoginResult(ResultSet resultSet) throws SQLException {
        this.isUser = true;
        this.userType = resultSet.getString("role");
        this.id = resultSet.getInt("userid");
    }

    public boolean isUser() {
        return isUser;
    }

    public String getUserType() {
        return userType;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "LoginResult{" +
                "isUser=" + isUser +
                ", userType='" + userType + '\'' +
                ", id=" + id +
                '}';
    }
}
