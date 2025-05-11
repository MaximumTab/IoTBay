package com.iotbay.controller;

import com.iotbay.model.User;
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

        try {
            String hashedPassword = hashPassword(password);

            HttpSession session = request.getSession();
            Connection conn = (Connection) session.getAttribute("conn");
            if (conn == null) throw new Exception("Database connection not found.");

            UserDBManager userManager = new UserDBManager(conn);
            User user = userManager.findUserByEmailAndPassword(email, hashedPassword);

            if (user != null && user.getIsActive().equals("1")) {
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
