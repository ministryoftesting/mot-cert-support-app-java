package com.ministryoftesting.db;

import com.ministryoftesting.models.auth.Credentials;
import com.ministryoftesting.models.auth.LoginResult;
import org.h2.jdbcx.JdbcDataSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

@Component
public class AuthDB extends BaseDB {

    private final String SELECT_BY_CREDENTIALS = "SELECT * FROM USERS WHERE email = ? AND password = ?";
    private final String CREATE_TOKEN = "INSERT INTO TOKENS (token, admin, expiry) VALUES (?, ?, ?)";
    private final String DELETE_TOKEN = "DELETE  FROM TOKENS WHERE token = ?";
    private final String SELECT_TOKEN = "SELECT * FROM TOKENS WHERE token = ?";

    public LoginResult checkLogin(String username, String password) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement ps = connection.prepareStatement(SELECT_BY_CREDENTIALS);
        ps.setString(1, username);
        ps.setString(2, password);

        ResultSet result = ps.executeQuery();

        if(result.next()) {
            return new LoginResult(result);
        } else {
            return new LoginResult(false, null, 0);
        }
    }

    public Credentials generateSession(String token, String userType, LocalDate expiryDate) throws SQLException {
        boolean isAdmin = userType.equals("admin");

        Connection connection = getConnection();
        PreparedStatement ps = connection.prepareStatement(CREATE_TOKEN);
        ps.setString(1, token);
        ps.setString(2, Boolean.toString(isAdmin));
        ps.setDate(3, java.sql.Date.valueOf(expiryDate));

        ps.executeUpdate();

        return new Credentials(token, isAdmin);
    }

    public boolean deleteSession(String token) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement ps = connection.prepareStatement(DELETE_TOKEN);
        ps.setString(1, token);

        return ps.executeUpdate() > 0;
    }

    public boolean checkSession(String token, LocalDate date) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement ps = connection.prepareStatement(SELECT_TOKEN);
        ps.setString(1, token);

        ResultSet result = ps.executeQuery();

        if(result.next()){
            if(result.getBoolean("admin")){
                LocalDate expiryDate = result.getDate("expiry").toLocalDate();

                return expiryDate.isAfter(date);
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

}
