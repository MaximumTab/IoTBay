package com.iotbay.controller;

import com.iotbay.util.PasswordUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String userType = request.getParameter("userType"); // "customer" or "staff"

        try {
            String hashedPassword = PasswordUtils.hashPassword(password);

            Class.forName("org.sqlite.JDBC");
            String dbPath = System.getProperty("user.dir") + "/IotBay.db";
            String dbURL = "jdbc:sqlite:" + dbPath + "?busy_timeout=5000";

            try (
                    Connection conn = DriverManager.getConnection(dbURL);
                    PreparedStatement stmt = conn.prepareStatement(
                            "INSERT INTO Users (card_id, full_name, email, password_hash, phone, user_type, is_active) " +
                                    "VALUES (NULL, ?, ?, ?, ?, ?, 1)")
            ) {
                stmt.setString(1, fullName);
                stmt.setString(2, email);
                stmt.setString(3, hashedPassword);
                stmt.setString(4, phone);
                stmt.setString(5, userType);
                stmt.executeUpdate();
            }

            response.sendRedirect("LoginPage.jsp");

        } catch (Exception e) {
            request.setAttribute("error", "Registration failed: " + e.getMessage());
            request.getRequestDispatcher("RegisterPage.jsp").forward(request, response);
        }
    }
}
