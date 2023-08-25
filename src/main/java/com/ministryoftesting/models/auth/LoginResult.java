package com.ministryoftesting.models.auth;

public class LoginResult {

    private boolean isUser;
    private String userType;

    public LoginResult(boolean isUser, String userType) {
        this.isUser = isUser;
        this.userType = userType;
    }

    public boolean isUser() {
        return isUser;
    }

    public String getUserType() {
        return userType;
    }
}
