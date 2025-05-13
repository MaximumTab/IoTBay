package com.iotbay.controller;

import com.iotbay.model.User;
import com.iotbay.model.dao.DBConnector;
import com.iotbay.model.dao.UserDBManager;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    // Hash password using SHA-256
    private String hashPassword(String password) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hash = md.digest(password.getBytes(StandardCharsets.UTF_8));
        StringBuilder hex = new StringBuilder();
        for (byte b : hash) {
            hex.append(String.format("%02x", b));
        }
        return hex.toString();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Collect form data
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String userType = request.getParameter("userType");

        try {
            System.out.println("Starting registration process for: " + email);

            // Hash the password
            String hashedPassword = hashPassword(password);
            System.out.println("Password hashed successfully");

            // Create DB connection
            Connection conn = new DBConnector().getConnection();
            System.out.println("Database connection established");

            // Insert user into Users table
            UserDBManager userManager = new UserDBManager(conn);
            User user = new User(0, fullName, email, hashedPassword, phone, userType, "1");
            userManager.addUser(user);
            System.out.println("User added to Users table");

            // If user is staff, add to StaffDetails
            if ("staff".equalsIgnoreCase(userType)) {
                int userId = userManager.getUserIdByEmail(email);
                if (userId != -1) {
                    addStaffDetails(conn, userId);
                    System.out.println("StaffDetails added for user ID: " + userId);
                } else {
                    System.out.println("Failed to retrieve user ID for staff");
                }
            }

            conn.close();
            System.out.println("Database connection closed");
            response.sendRedirect("index.jsp");

        } catch (Exception e) {
            System.out.println("Exception during registration: " + e.getMessage());
            e.printStackTrace();
            request.setAttribute("errorMessage", "Registration failed: " + e.getMessage());
            request.getRequestDispatcher("RegisterPage.jsp").forward(request, response);
        }
    }

    // Separate method for adding staff details
    private void addStaffDetails(Connection conn, int userId) throws SQLException {
        String sql = "INSERT INTO StaffDetails(staff_id, position, address) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setString(2, "Unassigned");
            stmt.setString(3, "Unknown");
            stmt.executeUpdate();
        }
    }
}
