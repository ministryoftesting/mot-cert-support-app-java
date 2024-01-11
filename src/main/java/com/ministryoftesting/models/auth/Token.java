package com.ministryoftesting.models.auth;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Token {

    @JsonProperty
    private String token;

    public Token() {
        // Jackson deserialization
    }
    public Token(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
