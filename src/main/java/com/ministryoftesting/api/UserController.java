package com.ministryoftesting.api;

import com.ministryoftesting.models.user.User;
import com.ministryoftesting.service.AuthService;
import com.ministryoftesting.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @RequestMapping(value = "/v1/user", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@Valid @RequestBody User user, @RequestHeader("Authorization") String token) throws SQLException {
        if(authService.validate(token.replace("Bearer ", ""), LocalDate.now())){
            return userService.createUser(user);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @RequestMapping(value = "/v1/user/{userid:[0-9]*}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUser(@PathVariable(value = "userid") int userId, @RequestHeader("Authorization") String token) throws SQLException {
        if(authService.validate(token.replace("Bearer ", ""), LocalDate.now())){
            return userService.deleteUser(userId);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @RequestMapping(value = "/v1/user/{userid:[0-9]*}", method = RequestMethod.GET)
    public ResponseEntity<User> getUserProfile(@PathVariable(value = "userid") int userId, @RequestHeader("Authorization") String token) throws SQLException {
        if(authService.validate(token.replace("Bearer ", ""), LocalDate.now())){
            return userService.getUserProfile(userId);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @RequestMapping(value = "/v1/user/{userid:[0-9]*}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateUserProfile(@PathVariable(value = "userid") int userId, @Valid @RequestBody User user, @RequestHeader("Authorization") String token) throws SQLException {
        if(authService.validate(token.replace("Bearer ", ""), LocalDate.now())){
            return userService.updateUser(userId, user);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @RequestMapping(value = "/v1/user", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getUsers(@RequestHeader("Authorization") String token) throws SQLException {
        if(authService.validate(token.replace("Bearer ", ""), LocalDate.now())){
            return userService.getUsers();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
