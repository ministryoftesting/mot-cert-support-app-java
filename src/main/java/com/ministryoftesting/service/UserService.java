package com.ministryoftesting.service;

import com.ministryoftesting.db.UserDB;
import com.ministryoftesting.models.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDB userDB;

    public ResponseEntity<?> createUser(User user) {
        if(userDB.createUser(user)){
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity<Void> deleteUser(int userId) {
        if(userDB.deleteUser(userId)){
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public ResponseEntity<User> getUserProfile(int userId) {
        User user = userDB.getUserProfile(userId);

        if(user != null){
            return ResponseEntity.status(HttpStatus.OK).body(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public ResponseEntity<?> updateUser(User user) {
        if(userDB.updateUser(user)){
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userDB.getUsers();

        return ResponseEntity.status(HttpStatus.OK).body(users);
    }
}
