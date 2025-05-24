package com.iotbay.controller;

import com.iotbay.model.User;
import com.iotbay.model.dao.UserDBManager;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            HttpSession session = request.getSession();
            Connection conn = (Connection) session.getAttribute("conn");
            if (conn == null) {
                throw new Exception("Database connection not found in session.");
            }

            UserDBManager userManager = new UserDBManager(conn);

            System.out.println("DEBUG: Email = " + email);
            System.out.println("DEBUG: Password = " + password);

            // No hashing, compare directly
            User user = userManager.findUserByEmailAndPassword(email, password);

            if (user != null && user.getIsActive().equals("1")) {
                System.out.println("DEBUG: User found = " + user.getEmail());

                session.setAttribute("user", user);

                if (user.getEmail().equalsIgnoreCase("123@gmail.com")) {
                    List<User> allUsers = userManager.getAllUsers();
                    System.out.println("DEBUG: All users fetched = " + allUsers.size());

                    session.setAttribute("allUsers", allUsers);
                    response.sendRedirect("SystemAdmin.jsp");
                } else {
                    response.sendRedirect("WelcomePage.jsp");
                }

            } else {
                System.out.println("DEBUG: Invalid login or inactive user");
                request.setAttribute("errorMessage", "Invalid login credentials.");
                request.getRequestDispatcher("LoginPage.jsp").forward(request, response);
            }

        } catch (Exception e) {
            System.out.println("DEBUG: Exception = " + e.getMessage());
            request.setAttribute("errorMessage", "Error: " + e.getMessage());
            request.getRequestDispatcher("LoginPage.jsp").forward(request, response);
        }
    }
}
