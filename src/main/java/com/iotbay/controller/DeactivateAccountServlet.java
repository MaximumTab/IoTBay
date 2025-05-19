package com.iotbay.controller;

import com.iotbay.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@WebServlet("/DeactivateAccountServlet")
public class DeactivateAccountServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("LoginPage.jsp");
            return;
        }

        try {
            Class.forName("org.sqlite.JDBC");
            String dbPath = System.getProperty("user.dir") + "/IotBay.db";
            String dbURL = "jdbc:sqlite:" + dbPath + "?busy_timeout=5000";

            synchronized (DeactivateAccountServlet.class) {
                try (
                        Connection conn = DriverManager.getConnection(dbURL);
                        PreparedStatement stmt = conn.prepareStatement(
                                "UPDATE Users SET is_active = 0 WHERE user_id = ?")
                ) {
                    stmt.setInt(1, user.getId());
                    stmt.executeUpdate();
                }
            }

            // Clear session and redirect to login
            session.invalidate();
            response.sendRedirect("LoginPage.jsp");

        } catch (Exception e) {
            // Optionally log error and show fallback
            request.setAttribute("error", "Failed to deactivate account: " + e.getMessage());
            request.getRequestDispatcher("ProfileEditor.jsp").forward(request, response);
        }
    }
}
