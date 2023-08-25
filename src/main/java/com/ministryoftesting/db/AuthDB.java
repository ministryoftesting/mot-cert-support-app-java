package com.ministryoftesting.db;

import com.ministryoftesting.models.auth.Credentials;
import com.ministryoftesting.models.auth.LoginResult;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class AuthDB {

    public LoginResult checkLogin(String username, String password) {
        if(username.equals("admin@test.com") && password.equals("password123")){
            return new LoginResult(true, "admin");
        } else if(username.equals("mark@test.com") && password.equals("password")){
            return new LoginResult(true, "user");
        } else {
            return new LoginResult(false, null);
        }
    }

    public Credentials generateSession(String userType) {
        if(userType.equals("admin"))
            return new Credentials("abc123", true, 1);
        else
            return new Credentials("abc123", false, 1);
    }

    public Boolean deleteSession(String token) {
        return token.equals("abc123");
    }

    public boolean checkSession(String token, LocalDate date) {
        return token.equals("abc123") && date.isAfter(LocalDate.now());
    }

}
