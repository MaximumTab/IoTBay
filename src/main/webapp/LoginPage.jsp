<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/CSS/DevicesCss.css">
</head>
<body>
<div class="add-device-container">
<div class="profile-container">
    <h2 class="section-title">User Login</h2>
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
    <p>Go back to home page. <a href="index.jsp">Home Page here</a>.</p>
</div>

</div>
</body>
</html>
