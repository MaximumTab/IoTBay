package com.iotbay.controller;

import com.iotbay.model.User;
import com.iotbay.util.PasswordUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("LoginPage.jsp");
            return;
        }

        String fullName = request.getParameter("fullName");
        String phone = request.getParameter("phone");
        String newPassword = request.getParameter("password");

        try {
            String hashedPassword = user.getPasswordHash();
            if (newPassword != null && !newPassword.trim().isEmpty()) {
                hashedPassword = PasswordUtils.hashPassword(newPassword);
            }

            Class.forName("org.sqlite.JDBC");
            String dbPath = System.getProperty("user.dir") + "/IotBay.db";
            String dbURL = "jdbc:sqlite:" + dbPath + "?busy_timeout=5000";

            try (
                    Connection conn = DriverManager.getConnection(dbURL);
                    PreparedStatement stmt = conn.prepareStatement(
                            "UPDATE Users SET full_name = ?, phone = ?, password_hash = ? WHERE user_id = ?")
            ) {
                stmt.setString(1, fullName);
                stmt.setString(2, phone);
                stmt.setString(3, hashedPassword);
                stmt.setInt(4, user.getId());
                stmt.executeUpdate();
            }

            // Update user session info
            user.setFullName(fullName);
            user.setPhone(phone);
            user.setPasswordHash(hashedPassword);
            session.setAttribute("user", user);

            request.setAttribute("success", "Profile updated successfully.");

        } catch (Exception e) {
            request.setAttribute("error", "Failed to update profile: " + e.getMessage());
        }

        // Fetch logs
        try {
            List<String[]> logs = new ArrayList<>();
            Class.forName("org.sqlite.JDBC");
            String dbPath = System.getProperty("user.dir") + "/IotBay.db";
            String dbURL = "jdbc:sqlite:" + dbPath + "?busy_timeout=5000";

            try (
                    Connection conn = DriverManager.getConnection(dbURL);
                    PreparedStatement stmt = conn.prepareStatement(
                            "SELECT login_time, logout_time FROM AccessLogs WHERE user_id = ?")
            ) {
                stmt.setInt(1, user.getId());
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    logs.add(new String[]{
                            rs.getString("login_time"),
                            rs.getString("logout_time")
                    });
                }
                request.setAttribute("logs", logs);
            }

        } catch (Exception e) {
            request.setAttribute("error", "Error loading access logs: " + e.getMessage());
        }

        request.getRequestDispatcher("ProfileEditor.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user != null) {
            request.setAttribute("user", user);
            request.getRequestDispatcher("profile.jsp").forward(request, response);
        } else {
            response.sendRedirect("loginPage.jsp");
        }
    }
}
