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
</body>
</html>
