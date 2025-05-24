<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.iotbay.model.Customer"%>
<%@ page session="true" %>
<jsp:useBean id="customer" class="com.iotbay.model.Customer" scope="session" />

<%
    String email = request.getParameter("email");
    String password = request.getParameter("password");
    String errorMessage = "";

    if (email != null && password != null) {
        customer.setEmail(email);
        customer.setPassword(password);

        if (customer.authenticateUser(email, password)) {
            session.setAttribute("customer", customer);
            Customer.addOnlineUser(customer);
            response.sendRedirect("WelcomePage.jsp");
            return;
        } else {
            errorMessage = "Email or password don't match!";
        }
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            background-color: #f4f4f4;
            font-family: Arial, sans-serif;
        }

        .login-container {
            background-color: #fff;
            padding: 40px 30px;
            width: 350px;
            border-radius: 8px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.15);
            text-align: center;
        }

        .login-container h2 {
            margin-bottom: 20px;
            font-size: 24px;
        }

        input[type="text"],
        input[type="password"] {
            width: 100%;
            padding: 12px;
            margin: 10px 0;
            font-size: 14px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }

        .error-message {
            color: red;
            font-size: 14px;
            margin: 10px 0;
        }

        .confirm-button,
        .return-button {
            width: 100%;
            padding: 10px;
            background-color: #405ACD;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 14px;
            margin-top: 10px;
        }

        .return-button {
            background-color: #aaa;
        }

        .confirm-button:hover {
            background-color: #2e45b5;
        }

        .return-button:hover {
            background-color: #888;
        }
    </style>
</head>
<body>
<div class="login-container">
    <h2>Login</h2>
    <form action="LoginPage.jsp" method="POST">

        <input type="text" id="email" name="email" placeholder="Enter email" required>
        <input type="password" id="password" name="password" placeholder="Enter password" required>

        <p class="error-message"><%= errorMessage %></p>

        <button type="submit" class="confirm-button">Confirm</button>
        <button type="button" onclick="history.back();" class="return-button">Return</button>

    </form>
</div>
</body>
</html>
