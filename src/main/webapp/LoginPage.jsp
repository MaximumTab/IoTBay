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

</body>
</html>
