<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h2>User Login</h2>


<% if (request.getAttribute("errorMessage") != null) { %>
<p style="color:red"><%= request.getAttribute("errorMessage") %></p>
<% } %>

<form action="login" method="post">
    <label>Email:</label><br>
    <input type="email" name="email" required><br><br>

    <label>Password:</label><br>
    <input type="password" name="password" required><br><br>

    <input type="submit" value="Login">
</form>

<p>Don't have an account? <a href="RegisterPage.jsp">Register here</a>.</p>
<p>Staff? Login Here. <a href="StaffLoginPage.jsp">Staff Login</a></p>
</body>
</html>
