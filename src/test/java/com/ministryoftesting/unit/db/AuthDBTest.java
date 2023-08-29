package com.ministryoftesting.unit.db;

import com.ministryoftesting.db.AuthDB;
import com.ministryoftesting.models.auth.Credentials;
import com.ministryoftesting.models.auth.LoginResult;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AuthDBTest {

    private static AuthDB authDB;

    @BeforeAll
    public static void initialiseDB() throws SQLException, IOException {
        authDB = new AuthDB();
    }

    @Test
    public void testCheckLoginReturnsPositiveResult() throws SQLException {
        LoginResult result = authDB.checkLogin("admin@test.com", "password123");

        Approvals.verify(result.toString());
    }

    @Test
    public void testCheckLoginReturnsNegativeResult() throws SQLException {
        LoginResult result = authDB.checkLogin("fake@fake.com", "password123");

        assertFalse(result.isUser());
    }

    @Test
    public void testSessionCanBeCreated() throws SQLException {
        Credentials credentials = authDB.generateSession("abc123","admin", LocalDate.now());

        Approvals.verify(credentials.toString());
    }

    @Test
    public void testDeletingExistingSessionReturnsPositiveResult() throws SQLException {
        authDB.generateSession("321cde","admin", LocalDate.now());

        boolean result = authDB.deleteSession("321cde");

        assertTrue(result);
    }

    @Test
    public void testDeletingNonExistingSessionReturnsNegativeResult() throws SQLException {
        boolean result = authDB.deleteSession("fakjdhf");

        assertFalse(result);
    }

    @Test
    public void testValidatingInDateTokenReturnsPositiveResult() throws SQLException {
        LocalDate date = LocalDate.now();
        date = date.plusDays(1);
        authDB.generateSession("lope","admin", date);

        boolean result = authDB.checkSession("lope", LocalDate.now());

        assertTrue(result);
    }

    @Test
    public void testValidatingOutOfDateTokenReturnsNegativeResult() throws SQLException {
        LocalDate date = LocalDate.now();
        date = date.minusDays(1);
        authDB.generateSession("1234","admin", date);

        boolean result = authDB.checkSession("1234", LocalDate.now());

        assertFalse(result);
    }

    @Test
    public void testInvalidTokenReturnsNegativeResult() throws SQLException {
        boolean result = authDB.checkSession("sdoajfdf", LocalDate.now());

        assertFalse(result);
    }

}
