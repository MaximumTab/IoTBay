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
    String bsbStr = request.getParameter("bsb");
    String accNumStr = request.getParameter("accNum");
    String email = request.getParameter("email");
    String password = request.getParameter("password");
    String confirmPassword = request.getParameter("confirmPassword");

    if (fname != null && lname != null && email != null && password != null &&
            address != null && postalCodeStr != null && phoneStr != null &&
            bsbStr != null && accNumStr != null && confirmPassword != null) {

        if (!password.equals(confirmPassword)) {
            errorMessage = "Passwords do not match.";
        } else {
            try {
                int postalCode = Integer.parseInt(postalCodeStr);
                int phone = Integer.parseInt(phoneStr);
                int bsb = Integer.parseInt(bsbStr);
                int accNum = Integer.parseInt(accNumStr);

                Customer newCustomer = new Customer(0, fname, lname, address, postalCode, phone, bsb, accNum, email, password);

                Customer.addUser(newCustomer);
                session.setAttribute("customer", newCustomer);

           
                session.setAttribute("fname", fname);
                session.setAttribute("lname", lname);
                session.setAttribute("address", address);
                session.setAttribute("phone", phoneStr);
                session.setAttribute("email", email);
                session.setAttribute("username", email); // reused as username
                session.setAttribute("password", password);

                response.sendRedirect("WelcomePage.jsp");
                return;
            } catch (NumberFormatException e) {
                errorMessage = "Please enter valid numbers for postal code, phone, BSB, and account number.";
            }
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
        <input type="text" name="fname" placeholder="First name" required>
        <input type="text" name="lname" placeholder="Last name" required>
        <input type="text" name="address" placeholder="Address" required>
        <input type="number" name="postalCode" placeholder="Postal Code" required>
        <input type="number" name="phone" placeholder="Phone Number" required>
        <input type="number" name="bsb" placeholder="BSB" required>
        <input type="number" name="accNum" placeholder="Account Number" required>
        <input type="email" name="email" placeholder="Email" required>
        <input type="password" name="password" placeholder="Password" required>
        <input type="password" name="confirmPassword" placeholder="Confirm Password" required>

        <label>
            <input type="checkbox" name="agreeTOS" required>
            I agree to the
            <a href="Terms.jsp" target="_blank" style="color: blue; text-decoration: underline;">
                Terms and Conditions
            </a>
        </label>

        <% if (!errorMessage.isEmpty()) { %>
        <p class="error-message" style="color: red;"><%= errorMessage %></p>
        <% } %>

        <button type="submit">Register</button>
        <button type="button" onclick="history.back();">Return</button>
    </form>
</div>
</body>
</html>
