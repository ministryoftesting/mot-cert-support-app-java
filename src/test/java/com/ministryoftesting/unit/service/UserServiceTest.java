package com.ministryoftesting.unit.service;

import com.ministryoftesting.db.UserDB;
import com.ministryoftesting.models.CreatedID;
import com.ministryoftesting.models.user.User;
import com.ministryoftesting.service.UserService;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    @Mock
    private UserDB userDB;

    @Autowired
    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void initialiseMocks() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void creatingUserReturnPositiveResult() throws SQLException {
        User user = new User(1, "Jon", "test@email.com", "password123", "user");
        when(userDB.createUser(user)).thenReturn(user);

        ResponseEntity<User> response = userService.createUser(user);

        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
        assertEquals(response.getBody().getId(), 1);
    }

    @Test
    public void deletingExistingUserReturnsPositiveResult() throws SQLException {
        when(userDB.deleteUser(1)).thenReturn(true);

        ResponseEntity<Void> response = userService.deleteUser(1);

        assertEquals(response.getStatusCode(), HttpStatus.ACCEPTED);
    }

    @Test
    public void deletingNonExistingUserReturnsNegativeResult() throws SQLException {
        when(userDB.deleteUser(2)).thenReturn(false);

        ResponseEntity<Void> response = userService.deleteUser(2);

        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void requestingExistingUserReturnsPositiveResult() throws SQLException {
        User user = new User("Jon", "test@email.com", "password", "user");
        when(userDB.getUserProfile(1)).thenReturn(user);

        ResponseEntity<User> response = userService.getUserProfile(1);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void requestingNonExistingUserReturnsNegativeResult() throws SQLException {
        when(userDB.getUserProfile(2)).thenReturn(null);

        ResponseEntity<User> response = userService.getUserProfile(2);

        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void updatingUserReturnsPositiveResult() throws SQLException {
        User user = new User( "Ben", "new@email.com", "newpassword", "user");
        when(userDB.updateUser(1, user)).thenReturn(true);

        ResponseEntity<?> response = userService.updateUser(1, user);

        assertEquals(response.getStatusCode(), HttpStatus.ACCEPTED);
    }

    @Test
    public void updatingNonExistingUserReturnsNegativeResult() throws SQLException {
        User user = new User( "Ben", "newemail.com", "newpassword", "user");
        when(userDB.updateUser(2, user)).thenReturn(false);

        ResponseEntity<?> response = userService.updateUser(2, user);

        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void gettingUsersReturnsPositiveResult() throws SQLException {
        List<User> users = List.of(
                new User(1, "Mark", "mark@test.com", "password", "Admin"),
                new User(2, "Richard", "richard@test.com", "password", "User")
        );

        when(userDB.getUsers()).thenReturn(users);

        ResponseEntity<List<User>> response = userService.getUsers();

        Approvals.verify(response.getBody().toString());
    }
}
