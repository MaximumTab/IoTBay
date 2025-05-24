package com.iotbay.model.dao;

import com.iotbay.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDBManager extends DBManager<User> {

    public UserDBManager(Connection connection) throws SQLException {
        super(connection);
    }

    @Override
    protected User add(User object) throws SQLException {
        return null;
    }

    @Override
    protected User get(User object) throws SQLException {
        return null;
    }

    @Override
    protected void update(User oldObject, User newObject) throws SQLException {
    }

    @Override
    protected void delete(User object) throws SQLException {
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
