package com.iotbay.controller;

import com.iotbay.model.User;
import com.iotbay.model.dao.UserDBManager;
import com.iotbay.model.dao.DBConnector;

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
        String userType = request.getParameter("userType"); // "customer" or "staff"

        try {
            // Hash the password
            String hashedPassword = hashPassword(password);

            // 🔥 Create DB connection directly (Option 1)
            Connection conn = new DBConnector().getConnection();

            // Insert user
            UserDBManager userManager = new UserDBManager(conn);
            User user = new User(0, fullName, email, hashedPassword, phone, userType, "1");
            userManager.addUser(user);

            // If registering as staff, add entry in StaffDetails
            if ("staff".equalsIgnoreCase(userType)) {
                String sql = "INSERT INTO StaffDetails(staff_id, position, address) VALUES (?, ?, ?)";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    int userId = userManager.getUserIdByEmail(email);
                    stmt.setInt(1, userId);
                    stmt.setString(2, "Unassigned");
                    stmt.setString(3, "Unknown");
                    stmt.executeUpdate();
                }
            }

            conn.close(); // Close connection after use
            response.sendRedirect("index.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Registration failed: " + e.getMessage());
            request.getRequestDispatcher("RegisterPage.jsp").forward(request, response);
        }
    }
}
