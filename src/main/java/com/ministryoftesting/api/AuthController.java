package com.ministryoftesting.api;

import com.ministryoftesting.models.auth.Credentials;
import com.ministryoftesting.models.auth.Login;
import com.ministryoftesting.models.auth.Token;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @RequestMapping(value = "/v1/auth/validate", method = RequestMethod.POST)
    public ResponseEntity<String> validate(@RequestBody Token token) {
        if(token.getToken().equals("abc123"))
            return new ResponseEntity<>("{}", HttpStatus.OK);
        else
            return new ResponseEntity<>("{}", HttpStatus.UNAUTHORIZED);
    }

    @RequestMapping(value = "/v1/auth/login", method = RequestMethod.POST)
    public ResponseEntity<Credentials> login(@RequestBody Login login){
        if(login.getEmail().equals("admin@test.com") && login.getPassword().equals("password123"))
            return new ResponseEntity<>(new Credentials("abc123", true, 1), HttpStatus.OK);
        else
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
    }

    @RequestMapping(value = "/v1/auth/logout", method = RequestMethod.POST)
    public ResponseEntity<String> logout(){
        return new ResponseEntity<>("{}", HttpStatus.ACCEPTED);
    }

}
