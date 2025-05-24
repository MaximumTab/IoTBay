<%@ page import="com.iotbay.model.Customer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<%
    String errorMessage = "";

    String fname = request.getParameter("fname");
    String lname = request.getParameter("lname");
    String address = request.getParameter("address");
    String postalCodeStr = request.getParameter("postalCode");
    String phoneStr = request.getParameter("phone");
    String email = request.getParameter("email");
    String password = request.getParameter("password");

    if (fname != null && lname != null && email != null && password != null &&
            address != null && postalCodeStr != null && phoneStr != null) {

        try {
            int postalCode = Integer.parseInt(postalCodeStr);

            Customer newCustomer = new Customer(0, fname, lname, address, postalCode, phoneStr, 0, 0, email, password);
            Customer.addOnlineUser(newCustomer);
            session.setAttribute("customer", newCustomer);

            session.setAttribute("fname", fname);
            session.setAttribute("lname", lname);
            session.setAttribute("address", address);
            session.setAttribute("phone", phoneStr);
            session.setAttribute("email", email);
            session.setAttribute("username", email);
            session.setAttribute("password", password);

            response.sendRedirect("WelcomePage.jsp");
            return;

        } catch (NumberFormatException e) {
            errorMessage = "Postal code must be a number.";
        }
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Register</title>
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
            width: 400px;
            border-radius: 8px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
            text-align: center;
        }

        .login-container h2 {
            margin-bottom: 20px;
            font-size: 24px;
        }

        input[type="text"],
        input[type="number"],
        input[type="email"],
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

        .action-button {
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

        .action-button:hover {
            background-color: #2e45b5;
        }

        .return-button {
            background-color: #aaa;
        }

        .return-button:hover {
            background-color: #888;
        }
    </style>
</head>
<body class="login-body">
<div class="login-container">
    <h2>Register</h2>
    <form action="RegisterPage.jsp" method="POST">
        <input type="text" name="fname" placeholder="First name" required>
        <input type="text" name="lname" placeholder="Last name" required>
        <input type="text" name="address" placeholder="Address" required>
        <input type="number" name="postalCode" placeholder="Postal Code" required>
        <input type="text" name="phone" placeholder="Phone Number" required>
        <input type="email" name="email" placeholder="Email" required>
        <input type="password" name="password" placeholder="Password" required>

        <% if (!errorMessage.isEmpty()) { %>
        <p class="error-message"><%= errorMessage %></p>
        <% } %>

        <button type="submit" class="action-button">Register</button>
        <button type="button" onclick="history.back();" class="action-button return-button">Return</button>
    </form>
</div>
</body>
</html>
