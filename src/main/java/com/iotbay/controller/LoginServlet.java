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

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            String hashedPassword = PasswordUtils.hashPassword(password);
            Class.forName("org.sqlite.JDBC");
            String dbPath = System.getProperty("user.dir") + "/IotBay.db";
            String dbURL = "jdbc:sqlite:" + dbPath + "?busy_timeout=5000";

            User user = null;

            try (
                    Connection conn = DriverManager.getConnection(dbURL);
                    PreparedStatement stmt = conn.prepareStatement(
                            "SELECT * FROM Users WHERE email = ? AND password_hash = ? AND is_active = 1")
            ) {
                stmt.setString(1, email);
                stmt.setString(2, hashedPassword);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    user = new User(
                            rs.getInt("user_id"),
                            rs.getInt("card_id"),
                            rs.getString("full_name"),
                            rs.getString("email"),
                            rs.getString("password_hash"),
                            rs.getString("phone"),
                            rs.getString("user_type"),
                            rs.getString("is_active")
                    );
                }
                rs.close();
            }

            if (user != null) {

                // Block staff from logging in here
                if ("staff".equalsIgnoreCase(user.getUserType())) {
                    request.setAttribute("error", "Staff must log in via the staff portal.");
                    request.getRequestDispatcher("StaffLogin.jsp").forward(request, response);
                    return;
                }

                HttpSession session = request.getSession();
                session.setAttribute("user", user);

                // Log login time
                try (
                        Connection conn = DriverManager.getConnection(dbURL);
                        PreparedStatement logStmt = conn.prepareStatement(
                                "INSERT INTO AccessLogs (user_id, login_time) VALUES (?, datetime('now'))")
                ) {
                    logStmt.setInt(1, user.getId());
                    logStmt.executeUpdate();
                }

                response.sendRedirect("DevicesServlet");

            } else {
                request.setAttribute("error", "Invalid email or password.");
                request.getRequestDispatcher("LoginPage.jsp").forward(request, response);
            }

        } catch (Exception e) {
            request.setAttribute("error", "Login failed: " + e.getMessage());
            request.getRequestDispatcher("LoginPage.jsp").forward(request, response);
        }
    }
}

