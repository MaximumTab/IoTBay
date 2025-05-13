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

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

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

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try (DBConnector connector = new DBConnector()) {
            Connection conn = connector.getConnection();

            if (conn == null) {
                throw new Exception("Database connection not found.");
            }

            String hashedPassword = hashPassword(password);
            UserDBManager userManager = new UserDBManager(conn);
            User user = userManager.findUserByEmailAndPassword(email, hashedPassword);

            if (user != null && "1".equals(user.getIsActive())) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                userManager.logUserLogin(user.getId());
                response.sendRedirect("MainPage.jsp");
            } else {
                request.setAttribute("errorMessage", "Invalid credentials or inactive account.");
                request.getRequestDispatcher("LoginPage.jsp").forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Login failed: " + e.getMessage());
            request.getRequestDispatcher("LoginPage.jsp").forward(request, response);
        }
    }
}
