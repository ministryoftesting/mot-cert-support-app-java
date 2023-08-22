package com.ministryoftesting.api;

import com.ministryoftesting.models.user.User;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @RequestMapping(value = "/v1/user", method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(@Valid @RequestBody User user){
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @RequestMapping(value = "/v1/user/{userid:[0-9]*}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteUser(@PathVariable(value = "userid") int userId){
        if(userId == 1)
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @RequestMapping(value = "/v1/user/{userid:[0-9]*}", method = RequestMethod.GET)
    public ResponseEntity<User> getUserProfile(@PathVariable(value = "userid") int userId) {
        if (userId == 1)
            return ResponseEntity.status(HttpStatus.OK).body(new User("Jon", "test@email.com", "password", "user"));
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @RequestMapping(value = "/v1/user/{userid:[0-9]*}", method = RequestMethod.PUT)
    public ResponseEntity<Void> updateUserProfile(@PathVariable(value = "userid") int userId, @Valid @RequestBody User user){
        if(user.getUsername().equals("Ben"))
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
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
