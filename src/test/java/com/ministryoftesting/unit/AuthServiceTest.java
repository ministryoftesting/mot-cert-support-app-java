package com.ministryoftesting.unit;

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

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    public void testCorrectLoginForAdmin() {
        when(authDB.checkLogin("admin@test.com", "password123")).thenReturn(new LoginResult(true , "admin"));
        when(authDB.generateSession("admin")).thenReturn(new Credentials("1234", true, 1));

        ResponseEntity<Credentials> response = authService.login("admin@test.com", "password123");

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        Approvals.verify(response.getBody().toString());
    }

    @Test
    public void testCorrectLoginForUser(){
        when(authDB.checkLogin("mark@test.com", "password")).thenReturn(new LoginResult(true, "user"));
        when(authDB.generateSession("user")).thenReturn(new Credentials("5678", false, 2));

        ResponseEntity<Credentials> response = authService.login("mark@test.com", "password");

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        Approvals.verify(response.getBody().toString());
    }

    @Test
    public void testIncorrectLogin() {
        when(authDB.checkLogin("fake@test.com", "password")).thenReturn(new LoginResult(false, null));

        ResponseEntity<Credentials> response = authService.login("fake@test.com", "password");

        assertEquals(response.getStatusCode(), HttpStatus.UNAUTHORIZED);
    }

    @Test
    public void testCorrectTokenForLogout() {
        when(authDB.deleteSession("1234")).thenReturn(true);

        ResponseEntity<String> response = authService.logout("1234");

        assertEquals(response.getStatusCode(), HttpStatus.ACCEPTED);
    }

    @Test
    public void testIncorrectTokenForLogout(){
        when(authDB.deleteSession("abcd")).thenReturn(false);

        ResponseEntity<String> response = authService.logout("abcd");

        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void testValidTokenReturnsOk(){
        when(authDB.checkSession("abc123", LocalDate.of(3001, 1, 1))).thenReturn(true);

        ResponseEntity response = authService.validate("abc123", LocalDate.of(3001, 1, 1));

        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testInValidTokenReturnsUnauhorized(){
        when(authDB.checkSession("lkjhg", LocalDate.of(3001, 1, 1))).thenReturn(false);

        ResponseEntity response = authService.validate("lkjhg", LocalDate.of(3001, 1, 1));

        assertEquals(response.getStatusCode(), HttpStatus.UNAUTHORIZED);
    }

    @Test
    public void testExpiredTokenReturnsUnauhorized(){
        when(authDB.checkSession("abc123", LocalDate.of(1999, 1, 1))).thenReturn(false);

        ResponseEntity response = authService.validate("abc123", LocalDate.of(1999, 1, 1));

        assertEquals(response.getStatusCode(), HttpStatus.UNAUTHORIZED);
    }
}
