package com.ministryoftesting.service;

import com.ministryoftesting.db.AuthDB;
import com.ministryoftesting.models.auth.Credentials;
import com.ministryoftesting.models.auth.LoginResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AuthService {

    @Autowired
    private AuthDB authDB;

    public ResponseEntity<Credentials> login(String email, String password) {
        LoginResult loginResult = authDB.checkLogin(email, password);

        if(loginResult.isUser())
            return new ResponseEntity<>(authDB.generateSession(loginResult.getUserType()), HttpStatus.OK);
        else
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
    }

    public ResponseEntity<String> logout(String token) {
        if(authDB.deleteSession(token))
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity validate(String token, LocalDate date) {
        if(authDB.checkSession(token, date)){
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
    }
}
