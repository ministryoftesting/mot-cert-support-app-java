package com.ministryoftesting.unit.service;

import com.ministryoftesting.db.AuthDB;
import com.ministryoftesting.models.auth.Credentials;
import com.ministryoftesting.models.auth.LoginResult;
import com.ministryoftesting.service.AuthService;
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
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public class AuthServiceTest {

    @Mock
    private AuthDB authDB;

    @Autowired
    @InjectMocks
    private AuthService authService;

    @BeforeEach
    public void initialiseMocks() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCorrectLoginForAdmin() throws SQLException {
        when(authDB.checkLogin("admin@test.com", "password123")).thenReturn(new LoginResult(true , "admin", 1));
        when(authDB.generateSession(any(), eq("admin"), any())).thenReturn(new Credentials("1234", true));

        ResponseEntity<Credentials> response = authService.login("admin@test.com", "password123");

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        Approvals.verify(response.getBody().toString());
    }

    @Test
    public void testCorrectLoginForUser() throws SQLException {
        when(authDB.checkLogin("mark@test.com", "password")).thenReturn(new LoginResult(true, "user", 2));
        when(authDB.generateSession(any(), eq("user"), any())).thenReturn(new Credentials("5678", false));

        ResponseEntity<Credentials> response = authService.login("mark@test.com", "password");

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        Approvals.verify(response.getBody().toString());
    }

    @Test
    public void testIncorrectLogin() throws SQLException {
        when(authDB.checkLogin("fake@test.com", "password")).thenReturn(new LoginResult(false, null, 0));

        ResponseEntity<Credentials> response = authService.login("fake@test.com", "password");

        assertEquals(response.getStatusCode(), HttpStatus.UNAUTHORIZED);
    }

    @Test
    public void testCorrectTokenForLogout() throws SQLException {
        when(authDB.deleteSession("1234")).thenReturn(true);

        ResponseEntity<String> response = authService.logout("1234");

        assertEquals(response.getStatusCode(), HttpStatus.ACCEPTED);
    }

    @Test
    public void testIncorrectTokenForLogout() throws SQLException {
        when(authDB.deleteSession("abcd")).thenReturn(false);

        ResponseEntity<String> response = authService.logout("abcd");

        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void testValidTokenReturnsOk() throws SQLException {
        when(authDB.checkSession("abc123", LocalDate.of(3001, 1, 1))).thenReturn(true);

        ResponseEntity response = authService.validate("abc123", LocalDate.of(3001, 1, 1));

        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testInValidTokenReturnsUnauhorized() throws SQLException {
        when(authDB.checkSession("lkjhg", LocalDate.of(3001, 1, 1))).thenReturn(false);

        ResponseEntity response = authService.validate("lkjhg", LocalDate.of(3001, 1, 1));

        assertEquals(response.getStatusCode(), HttpStatus.UNAUTHORIZED);
    }

    @Test
    public void testExpiredTokenReturnsUnauhorized() throws SQLException {
        when(authDB.checkSession("abc123", LocalDate.of(1999, 1, 1))).thenReturn(false);

        ResponseEntity response = authService.validate("abc123", LocalDate.of(1999, 1, 1));

        assertEquals(response.getStatusCode(), HttpStatus.UNAUTHORIZED);
    }
}
