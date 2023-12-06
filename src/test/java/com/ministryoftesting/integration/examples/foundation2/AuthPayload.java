// This line declares that the code is part of a Java package called "com.ministryoftesting.integration.examples."
package com.ministryoftesting.integration.examples.foundation2;

// This line imports a class named "JsonProperty" from the "com.fasterxml.jackson" package.
import com.fasterxml.jackson.annotation.JsonProperty;

// This line defines a Java class named "AuthPayload."
public class AuthPayload {

    // This line marks the "email" field to be serialized/deserialized by the Jackson JSON library.
    @JsonProperty
    // This line declares a private string variable named "email" to store the user's email.
    private String email;

    // This line marks the "password" field to be serialized/deserialized by the Jackson JSON library.
    @JsonProperty
    // This line declares a private string variable named "password" to store the user's password.
    private String password;

    // This is the constructor of the "AuthPayload" class.
    // It takes two parameters, an email and a password, and initializes the "email" and "password" fields.
    public AuthPayload(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // This is a getter method for the "email" field.
    // It allows other classes to access the value of the "email" field.
    public String getEmail() {
        return email;
    }

    // This is a getter method for the "password" field.
    // It allows other classes to access the value of the "password" field.
    public String getPassword() {
        return password;
    }
}