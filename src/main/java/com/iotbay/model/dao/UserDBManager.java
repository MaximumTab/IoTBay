package com.iotbay.model.dao;

import com.iotbay.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDBManager {
    private Connection conn;

    public UserDBManager(Connection conn) {
        this.conn = conn;
    }

    public void addUser(User user) throws SQLException {
        String sql = "INSERT INTO Users(full_name, email, password_hash, phone, user_type, is_active) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getFullName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPasswordHash());
            stmt.setString(4, user.getPhone());
            stmt.setString(5, user.getUserType());
            stmt.setInt(6, user.getIsActive().equals("1") ? 1 : 0); // Convert string to int
            stmt.executeUpdate();
        }
    }

    public int getUserIdByEmail(String email) throws SQLException {
        String query = "SELECT user_id FROM Users WHERE email = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("user_id");
            } else {
                throw new SQLException("User not found with email: " + email);
            }
        }
    }
    public User findUserByEmailAndPassword(String email, String passwordHash) throws SQLException {
        String sql = "SELECT * FROM Users WHERE email = ? AND password_hash = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, passwordHash);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt("user_id"),
                        rs.getString("full_name"),
                        rs.getString("email"),
                        rs.getString("password_hash"),
                        rs.getString("phone"),
                        rs.getString("user_type"),
                        String.valueOf(rs.getInt("is_active"))
                );
            }
        }
        return null;
    }

    public void logUserLogin(int userId) throws SQLException {
        String sql = "INSERT INTO AccessLogs(user_id, login_time) VALUES (?, datetime('now'))";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.executeUpdate();
        }
    }
    public User findStaffByIdAndPassword(int staffId, String passwordHash) throws SQLException {
        String sql = "SELECT * FROM Users WHERE user_id = ? AND password_hash = ? AND user_type = 'staff'";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, staffId);
            stmt.setString(2, passwordHash);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt("user_id"),
                        rs.getString("full_name"),
                        rs.getString("email"),
                        rs.getString("password_hash"),
                        rs.getString("phone"),
                        rs.getString("user_type"),
                        String.valueOf(rs.getInt("is_active"))
                );
            }
        }
        return null;
    }

}
