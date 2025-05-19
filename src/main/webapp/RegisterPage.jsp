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
</body>
</html>
