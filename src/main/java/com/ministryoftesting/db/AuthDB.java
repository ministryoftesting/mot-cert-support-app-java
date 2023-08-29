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
public class AuthDB {

    private Connection connection;

    private final String SELECT_BY_CREDENTIALS = "SELECT * FROM USERS WHERE email = ? AND password = ?";
    private final String CREATE_TOKEN = "INSERT INTO TOKENS (token, admin, expiry) VALUES (?, ?, ?)";
    private final String DELETE_TOKEN = "DELETE  FROM TOKENS WHERE token = ?";
    private final String SELECT_TOKEN = "SELECT * FROM TOKENS WHERE token = ?";

    public AuthDB() throws SQLException, IOException {
        JdbcDataSource ds = new JdbcDataSource();
        ds.setURL("jdbc:h2:mem:timesheet;MODE=MySQL");
        ds.setUser("user");
        ds.setPassword("password");
        connection = ds.getConnection();

        executeSqlFile("db.sql");
        executeSqlFile("seed.sql");
    }

    public LoginResult checkLogin(String username, String password) throws SQLException {
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

        PreparedStatement ps = connection.prepareStatement(CREATE_TOKEN);
        ps.setString(1, token);
        ps.setString(2, Boolean.toString(isAdmin));
        ps.setDate(3, java.sql.Date.valueOf(expiryDate));

        ps.executeUpdate();

        return new Credentials(token, isAdmin);
    }

    public boolean deleteSession(String token) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(DELETE_TOKEN);
        ps.setString(1, token);

        return ps.executeUpdate() > 0;
    }

    public boolean checkSession(String token, LocalDate date) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(SELECT_TOKEN);
        ps.setString(1, token);

        ResultSet result = ps.executeQuery();

        if(result.next()){
            LocalDate expiryDate = result.getDate("expiry").toLocalDate();

            return expiryDate.isAfter(date);
        } else {
            return false;
        }
    }

    private void executeSqlFile(String filename) throws IOException, SQLException {
        Reader reader = new InputStreamReader( new ClassPathResource(filename).getInputStream());
        Scanner sc = new Scanner(reader);

        StringBuffer sb = new StringBuffer();
        while(sc.hasNext()){
            sb.append(sc.nextLine());
        }

        connection.prepareStatement(sb.toString()).executeUpdate();
    }

}
