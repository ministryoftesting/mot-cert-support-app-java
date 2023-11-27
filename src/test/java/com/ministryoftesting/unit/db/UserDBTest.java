package com.ministryoftesting.unit.db;

import com.ministryoftesting.db.UserDB;
import com.ministryoftesting.models.user.User;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserDBTest {

    private static UserDB userDB;

    @BeforeAll
    public static void setup() {
        userDB = new UserDB();
    }

    @Test
    public void returnsPositiveResultWhenCreatingUser() throws SQLException {
        User user = new User("Jon", "test@email.com", "password", "user");
        User result = userDB.createUser(user);

        assertEquals(User.class, result.getClass());
    }

    @Test
    public void returnsPositiveResultWhenDeletingUser() throws SQLException {
        int userId = userDB.createUser(new User("Jon", "test@email.com", "password", "user")).getId();
        boolean result = userDB.deleteUser(userId);

        assertTrue(result);
    }

    @Test
    public void returnsNegativeResultWhenDeletingNonUser() throws SQLException {
        boolean result = userDB.deleteUser(122);

        assertFalse(result);
    }

    @Test
    public void returnsUserWhenGettingUserProfile() throws SQLException {
        User user = userDB.getUserProfile(1);

        Approvals.verify(user);
    }

    @Test
    public void returnsNegativeResponseWhenGettingNonUserProfile() throws SQLException {
        User user = userDB.getUserProfile(10);

        assertNull(user);
    }

    @Test
    public void returnsPositiveResultWhenUpdatingUser() throws SQLException {
        User user = new User("Sam", "fake@fake.com", "hello123", "admin");
        int userId = userDB.createUser(user).getId();

        User updatedUser = new User("Sam", "update@update.com", "hello123", "admin");
        boolean result = userDB.updateUser(userId, updatedUser);

        assertTrue(result);
    }

    @Test
    public void returnsUsersWhenRequested() throws SQLException {
        List<User> users = userDB.getUsers();

        assertTrue(users.size() > 0);
    }
}
