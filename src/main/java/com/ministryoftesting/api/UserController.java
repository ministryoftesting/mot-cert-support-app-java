package com.ministryoftesting.api;

import com.ministryoftesting.models.user.User;
import com.ministryoftesting.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/v1/user", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@Valid @RequestBody User user){
        return userService.createUser(user);
    }

    @RequestMapping(value = "/v1/user/{userid:[0-9]*}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUser(@PathVariable(value = "userid") int userId){
        return userService.deleteUser(userId);
    }

    @RequestMapping(value = "/v1/user/{userid:[0-9]*}", method = RequestMethod.GET)
    public ResponseEntity<User> getUserProfile(@PathVariable(value = "userid") int userId) {
        return userService.getUserProfile(userId);
    }

    @RequestMapping(value = "/v1/user/{userid:[0-9]*}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateUserProfile(@PathVariable(value = "userid") int userId, @Valid @RequestBody User user){
        return userService.updateUser(user);
    }

    @RequestMapping(value = "/v1/user", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getUsers(){
        List<User> users = List.of(
                new User(1, "Mark", "mark@test.com", "password", "Admin"),
                new User(2, "Richard", "richard@test.com", "password", "User")
        );

        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

}
