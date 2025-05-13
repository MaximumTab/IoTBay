package com.iotbay.model.dao;

import com.iotbay.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserDBManager extends DBManager<User> {

    public UserDBManager(Connection connection) throws SQLException {
        super(connection);
    }

    // Method to find user by email and password
    public User findUserByEmailAndPassword(String email, String hashedPassword) throws SQLException {
        String query = "SELECT * FROM Users WHERE email = ? AND password_hash = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, email);
        stmt.setString(2, hashedPassword);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return new User(
                    rs.getInt("user_id"),
                    rs.getString("full_name"),
                    rs.getString("email"),
                    rs.getString("password_hash"),
                    rs.getString("phone"),
                    rs.getString("user_type"),
                    rs.getString("is_active")
            );
        }
        return null;
    }

    // Method to find staff by ID and password
    public User findStaffByIdAndPassword(int staffId, String hashedPassword) throws SQLException {
        String query = "SELECT * FROM Users WHERE user_id = ? AND password_hash = ? AND user_type = 'staff'";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, staffId);
        stmt.setString(2, hashedPassword);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return new User(
                    rs.getInt("user_id"),
                    rs.getString("full_name"),
                    rs.getString("email"),
                    rs.getString("password_hash"),
                    rs.getString("phone"),
                    rs.getString("user_type"),
                    rs.getString("is_active")
            );
        }
        return null;
    }

    // Method to log user login
    public void logUserLogin(int userId) throws SQLException {
        String query = "INSERT INTO AccessLogs (user_id, login_time) VALUES (?, datetime('now'))";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, userId);
        stmt.executeUpdate();
    }

    // Method to add a new user
    public void addUser(User user) throws SQLException {
        String query = "INSERT INTO Users (full_name, email, password_hash, phone, user_type, is_active) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, user.getFullName());
        stmt.setString(2, user.getEmail());
        stmt.setString(3, user.getPasswordHash());
        stmt.setString(4, user.getPhone());
        stmt.setString(5, user.getUserType());
        stmt.setString(6, user.getIsActive());
        stmt.executeUpdate();
    }

    // Method to get user ID by email
    public int getUserIdByEmail(String email) throws SQLException {
        String query = "SELECT user_id FROM Users WHERE email = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, email);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getInt("user_id");
        }
        return -1;
    }

    @Override
    protected User add(User object) throws SQLException { return null; }

    @Override
    protected User get(User object) throws SQLException { return null; }

    @Override
    protected void update(User oldObject, User newObject) throws SQLException { }

    @Override
    protected void delete(User object) throws SQLException { }
}
