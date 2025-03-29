<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<%@ page import="com.iotbay.model.Customer"%>
<jsp:useBean id="customer" class="com.iotbay.model.Customer" scope="session"/>
<%
    String email = request.getParameter("email");
    String password = request.getParameter("password");
    String errorMessage = "";

    //testingCustomer
    Customer testCustomer1 = new Customer(1, "Maxim", "Tabachuk", "ABC Street", 1231, 11111, 111, 111, "maximumtab@outlook.com", "123");
    Customer.addUser(testCustomer1);

    if (email != null && password != null)
    {
        customer.setEmail(email);
        customer.setPassword(password);

        if (customer.authenticateUser(email,password))
        {
            session.setAttribute("customer", customer);
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

        <% if (!errorMessage.isEmpty()) { %>
        <p class="error-message"><%= errorMessage %></p>
        <% } %>

        <button type="submit">Confirm</button>
        <button type="button" onclick="history.back();">Return</button>
    </form>
</div>
</body>
</html>
