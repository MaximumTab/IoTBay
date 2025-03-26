<%--
  Created by IntelliJ IDEA.
  com.iotbay.model.Customer: maksy
  Date: 18/03/2025
  Time: 7:26 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<jsp:useBean id="customer" class="com.iotbay.model.Customer" scope="session"/>
<%
    String[][] users = {
            {"MaximumTab", "123"},
            {"user2", "pass2"},
            {"admin", "admin123"},
            {"test", "1234"}
    };

    String username = request.getParameter("username");
    String password = request.getParameter("password");
    String errorMessage = "";

    if (username != null && password != null) {
        customer.setUsername(username);
        customer.setPassword(password);

        if (customer.isValidUser(users)) {
            session.setAttribute("customer", customer);
            response.sendRedirect("WelcomePage.jsp");
            return;
        } else {
            errorMessage = "Username or password don't match!";
        }
    }
%>
<html>
<head>
    <link rel="stylesheet" href="StyleSheet.css">
    <title>Register</title>
</head>
<body class="login-body">
<div class="login-container">
    <h2>Register</h2>
    <form action="RegisterPage.jsp" method="POST">
        <input type="email" name="email" placeholder="Enter email" required>
        <input type="text" name="fullname" placeholder="Enter full name" required>
        <input type="password" name="password" placeholder="Enter password" required>

        <select name="gender" required>
            <option value="">Select gender</option>
            <option>Male</option>
            <option>Female</option>
            <option>Other</option>
        </select>

        <input type="text" name="favouriteColour" placeholder="Favourite colour (optional)">

        <label>
            <input type="checkbox" name="agreeTOS" required>
            I agree to the
            <a href="terms.jsp" style="color: blue; text-decoration: underline;" target="_blank">
                Terms and Conditions
            </a>
        </label>

        <% if (!errorMessage.isEmpty()) { %>
        <p class="error-message"><%= errorMessage %></p>
        <% } %>

        <button type="submit">Register</button>
    </form>
</div>
</body>
</html>

