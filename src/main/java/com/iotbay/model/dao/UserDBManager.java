package com.iotbay.model.dao;

import com.iotbay.model.User;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDBManager extends DBManager<User> {
    public int getUserCount() throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM users");
        resultSet.next();
        return resultSet.getInt(1);
    }

    public UserDBManager(Connection connection) throws SQLException {
        super(connection);
    }

    //CREATE
    public User add(User user) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO USERS (Email, password_hash) VALUES (?, ?)");
        preparedStatement.setString(1, user.getEmail());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.executeUpdate();

        preparedStatement = connection.prepareStatement("SELECT MAX(user_id) FROM USERS");
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        int userId = resultSet.getInt(1);
        user.setId(userId);
        return user;
    }

    public User get(User user) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM USERS WHERE user_id = ?");
        preparedStatement.setInt(1, user.getId());
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return new User(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getString(8));
    }

    //UPDATE
    public void update(User oldUser, User newUser) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE USERS SET Email = ?, password_hash = ? WHERE user_id = ?");
        preparedStatement.setString(1, newUser.getEmail());
        preparedStatement.setString(2, newUser.getPassword());
        preparedStatement.setInt(3, oldUser.getId());
        preparedStatement.executeUpdate();
    }

    //DELETE
    public void delete(User user) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM USERS WHERE UserId = ?");
        preparedStatement.setInt(1, user.getId());
        preparedStatement.executeUpdate();
    }

    public User findUserByEmailAndPassword(String email, String password) throws SQLException {
        String sql = "SELECT * FROM Users WHERE email = ? AND password_hash = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, email);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return new User(
                    rs.getInt("user_id"),
                    rs.getInt("card_id"),
                    rs.getString("full_name"),
                    rs.getString("email"),
                    rs.getString("password_hash"),
                    rs.getString("phone"),
                    rs.getString("user_type"),
                    String.valueOf(rs.getInt("is_active"))
            );
        }
        return null;
    }

    public void logUserLogin(int userId) throws SQLException {
        String sql = "INSERT INTO AccessLogs (user_id, login_time) VALUES (?, datetime('now'))";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, userId);
        ps.executeUpdate();
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM Users";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            users.add(new User(
                    rs.getInt("user_id"),
                    rs.getInt("card_id"),
                    rs.getString("full_name"),
                    rs.getString("email"),
                    rs.getString("password_hash"),
                    rs.getString("phone"),
                    rs.getString("user_type"),
                    String.valueOf(rs.getInt("is_active"))
            ));
        }
        return users;
    }

}
