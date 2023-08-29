package com.ministryoftesting.service;

import com.ministryoftesting.db.AuthDB;
import com.ministryoftesting.models.auth.Credentials;
import com.ministryoftesting.models.auth.LoginResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class AuthService {

    @Autowired
    private AuthDB authDB;

    public ResponseEntity<Credentials> login(String email, String password) throws SQLException {
        LoginResult loginResult = authDB.checkLogin(email, password);

        if(loginResult.isUser()){
            String tokenString = new RandomString(16, ThreadLocalRandom.current()).nextString();

            LocalDate date = LocalDate.now();
            date = date.plusDays(1);

            Credentials credentials = authDB.generateSession(tokenString, loginResult.getUserType(), date);
            credentials.setId(loginResult.getId());

            return new ResponseEntity<>(credentials, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }

    public ResponseEntity<String> logout(String token) throws SQLException {
        if(authDB.deleteSession(token))
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public boolean validate(String token, LocalDate date) throws SQLException {
        return authDB.checkSession(token, date);
    }
}
