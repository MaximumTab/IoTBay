<<<<<<< HEAD
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
                Customer.addOnlineUser(newCustomer); // add the same customer to the onlineUsers List
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
=======
<<<<<<< Updated upstream
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<h2>User Registration</h2>


<c:if test="${not empty errorMessage}">
    <p style="color:red">${errorMessage}</p>
</c:if>

<form action="register" method="post">
    <label>Full Name:</label><br>
    <input type="text" name="fullName" required><br><br>

    <label>Email:</label><br>
    <input type="email" name="email" required><br><br>

    <label>Password:</label><br>
    <input type="password" name="password" required><br><br>

    <label>Phone:</label><br>
    <input type="text" name="phone"><br><br>

    <label>User Type:</label><br>
    <select name="userType" required>
        <option value="customer">Customer</option>
        <option value="staff">Staff</option>
    </select><br><br>

    <input type="submit" value="Register">
</form>

<p>Already have an account? <a href="LoginPage.jsp">Login here</a>.</p>
=======
<!DOCTYPE html>
<html>
<head>
    <title>User Registration</title>
    <link rel="stylesheet" href="StyleSheet.css">
</head>
<body class="profile-page">

<div class="profile-container">
    <h2 class="section-title">Create an Account</h2>

    <!-- Display error message if passed from RegisterServlet -->
    <%
        String error = (String) request.getAttribute("error");
        if (error != null) {
    %>
    <p class="error-message"><%= error %></p>
    <%
        }
    %>

    <form method="post" action="register" class="profile-form">
        <label for="fullName">Full Name:</label><br>
        <input type="text" name="fullName" id="fullName" required><br><br>

        <label for="email">Email:</label><br>
        <input type="email" name="email" id="email" required><br><br>

        <label for="password">Password:</label><br>
        <input type="password" name="password" id="password" required><br><br>

        <label for="phone">Phone Number:</label><br>
        <input type="text" name="phone" id="phone"><br><br>

        <label for="userType">Registering As:</label><br>
        <select name="userType" id="userType" required>
            <option value="customer">Customer</option>
            <option value="staff">Staff</option>
        </select><br><br>

        <button type="submit" class="btn btn-update">Register</button>
    </form>

    <hr class="divider">

    <p>Already have an account? <a href="LoginPage.jsp">Login here</a>.</p>
</div>

>>>>>>> Stashed changes
>>>>>>> a21e133 (Initial commit from IntelliJ)
</body>
</html>
