package com.ministryoftesting.db;

import com.ministryoftesting.models.user.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDB {
    public Boolean createUser(User user) {
        return user.getUsername().equals("Jon");
    }

    public Boolean deleteUser(int userId) {
        return userId == 1;
    }

    public User getUserProfile(int userId) {
        if(userId == 1)
            return new User("Jon", "test@email.com", "password", "user");
        else
            return null;
    }

    public Boolean updateUser(User user) {
        return user.getUsername().equals("Ben");
    }

    public List<User> getUsers() {
        List<User> users = List.of(
                new User(1, "Mark", "mark@test.com", "password", "Admin"),
                new User(2, "Richard", "richard@test.com", "password", "User")
        );

        return users;
    }
}
