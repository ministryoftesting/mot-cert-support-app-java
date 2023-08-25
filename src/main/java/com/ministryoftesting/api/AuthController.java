package com.ministryoftesting.api;

import com.ministryoftesting.models.auth.Credentials;
import com.ministryoftesting.models.auth.Login;
import com.ministryoftesting.models.auth.Token;
import com.ministryoftesting.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @RequestMapping(value = "/v1/auth/validate", method = RequestMethod.POST)
    public ResponseEntity<String> validate(@RequestBody Token token) {
        LocalDate date = LocalDate.now();
        date = date.plusDays(1);

        return authService.validate(token.getToken(), date);
    }

    @RequestMapping(value = "/v1/auth/login", method = RequestMethod.POST)
    public ResponseEntity<Credentials> login(@RequestBody Login login){
        return authService.login(login.getEmail(), login.getPassword());
    }

    @RequestMapping(value = "/v1/auth/logout", method = RequestMethod.POST)
    public ResponseEntity logout(@RequestBody Token token){
        return authService.logout(token.getToken());
    }

}
