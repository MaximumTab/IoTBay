<<<<<<< HEAD
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.iotbay.model.Customer"%>
<%@ page session="true" %>
<jsp:useBean id="customer" class="com.iotbay.model.Customer" scope="session"/>
<%
    String email = request.getParameter("email");
    String password = request.getParameter("password");
    String errorMessage = "";

    if (email != null && password != null)
    {
        customer.setEmail(email);
        customer.setPassword(password);

        if (customer.authenticateUser(email,password))
        {
            session.setAttribute("customer", customer);
            Customer.addOnlineUser(customer);
            //System.out.println(Customer.getNumOnlineUsers());
            response.sendRedirect("WelcomePage.jsp");

            return;
        }
        else
        {
            errorMessage = "Email or password don't match!";
        }
    }
%>
<html>
<head>
    <link rel="stylesheet" href="StyleSheet.css">
    <title>Login</title>
</head>
<body class="login-body">
<div class="login-container">
    <h2>Login</h2>
    <form action="LoginPage.jsp" method="POST">
        <input type="text" id="email" name="email" placeholder="Enter email" required>
        <input type="password" id="password" name="password" placeholder="Enter password" required>

        <p class="error-message"><%= errorMessage %></p>

        <button type="submit">Confirm</button>
        <button type="button" onclick="history.back();">Return</button>
    </form>
</div>
=======
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="StyleSheet.css">
</head>
<body class="profile-page">

<div class="profile-container">
    <h2 class="section-title">User Login</h2>

    <!-- Show error message if set -->
    <%
        String error = (String) request.getAttribute("error");
        if (error != null) {
    %>
    <p class="error-message"><%= error %></p>
    <%
        }
    %>

    <form method="post" action="login" class="profile-form">
        <label for="email">Email:</label><br>
        <input type="email" name="email" id="email" required><br><br>

        <label for="password">Password:</label><br>
        <input type="password" name="password" id="password" required><br><br>

        <button type="submit" class="btn btn-update">Login</button>
    </form>

    <hr class="divider">

    <p>Don't have an account? <a href="RegisterPage.jsp">Register here</a></p>
    <p>Staff? <a href="StaffLogin.jsp">Staff login</a></p>
</div>

>>>>>>> a21e133 (Initial commit from IntelliJ)
</body>
</html>
