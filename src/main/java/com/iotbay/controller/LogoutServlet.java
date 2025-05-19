package com.iotbay.controller;

import com.iotbay.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.*;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("user");

            if (user != null) {
                try {
                    Class.forName("org.sqlite.JDBC");
                    String dbPath = System.getProperty("user.dir") + "/IotBay.db";
                    String dbURL = "jdbc:sqlite:" + dbPath + "?busy_timeout=5000";

                    try (Connection conn = DriverManager.getConnection(dbURL)) {

                        // Step 1: Get latest log_id with NULL logout_time
                        int logId = -1;
                        try (PreparedStatement selectStmt = conn.prepareStatement(
                                "SELECT log_id FROM AccessLogs " +
                                        "WHERE user_id = ? AND logout_time IS NULL " +
                                        "ORDER BY login_time DESC LIMIT 1")) {
                            selectStmt.setInt(1, user.getId());
                            ResultSet rs = selectStmt.executeQuery();
                            if (rs.next()) {
                                logId = rs.getInt("log_id");
                            }
                            rs.close();
                        }

                        // Step 2: If found, update that record
                        if (logId != -1) {
                            try (PreparedStatement updateStmt = conn.prepareStatement(
                                    "UPDATE AccessLogs SET logout_time = datetime('now') WHERE log_id = ?")) {
                                updateStmt.setInt(1, logId);
                                updateStmt.executeUpdate();
                            }
                        }
                    }

                } catch (Exception e) {
                    System.err.println("Logout logging failed: " + e.getMessage());
                }
            }

            session.invalidate();
        }

        response.sendRedirect("LoginPage.jsp");
    }
}
