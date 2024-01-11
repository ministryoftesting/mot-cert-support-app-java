package com.ministryoftesting.db;

import com.ministryoftesting.models.user.User;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDB extends BaseDB {

    private final String CREATE_USER = "INSERT INTO USERS (username, email, password, role) VALUES (?, ?, ?, ?)";
    private final String DELETE_USER_BY_ID = "DELETE FROM USERS WHERE userid = ?";
    private final String SELECT_USER_BY_ID = "SELECT * FROM USERS WHERE userid = ?";
    private final String UPDATE_USER_BY_ID = "UPDATE USERS SET username = ?, email = ?, password = ?, role = ? WHERE userid = ?";

    public User createUser(User user) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement ps = connection.prepareStatement(CREATE_USER);
        ps.setString(1, user.getUsername());
        ps.setString(2, user.getEmail());
        ps.setString(3, user.getPassword());
        ps.setString(4, user.getRole());

        if(ps.executeUpdate() > 0){
            ResultSet lastInsertId = connection.prepareStatement("SELECT LAST_INSERT_ID()").executeQuery();
            lastInsertId.next();

            return new User(lastInsertId.getInt("LAST_INSERT_ID()"), user.getUsername(), user.getEmail(), user.getPassword(), user.getRole());
        } else {
            return null;
        }
    }

    public Boolean deleteUser(int userId) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement ps = connection.prepareStatement(DELETE_USER_BY_ID);
        ps.setInt(1, userId);

        int result = ps.executeUpdate();

        return result == 1;
    }

    public User getUserProfile(int userId) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement ps = connection.prepareStatement(SELECT_USER_BY_ID);
        ps.setInt(1, userId);

        ResultSet userResults = ps.executeQuery();

        if(userResults.next()){
            return new User(userResults);
        } else {
            return null;
        }
    }

    public boolean updateUser(int userId, User user) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement ps = connection.prepareStatement(UPDATE_USER_BY_ID);
        ps.setString(1, user.getUsername());
        ps.setString(2, user.getEmail());
        ps.setString(3, user.getPassword());
        ps.setString(4, user.getRole());
        ps.setInt(5, userId);

        int result = ps.executeUpdate();

        return result == 1;
    }

    public List<User> getUsers() throws SQLException {
        Connection connection = getConnection();
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM USERS");

        ResultSet results = ps.executeQuery();

        List<User> users = new ArrayList<>();

        while(results.next()){
            users.add(new User(results));
        }

        return users;
    }
}
