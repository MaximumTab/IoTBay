<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String[][] users = {{"MaximumTab", "123"}, {"user2", "pass2"}, {"admin", "admin123"}, {"test", "1234"}};

    String username = request.getParameter("username");
    String password = request.getParameter("password");
    String errorMessage = "";

    if (username != null && password != null) {
        boolean validUser = false;

        // Check credentials
        for (String[] user : users) {
            if (user[0].equals(username) && user[1].equals(password)) {
                validUser = true;
                break;
            }
        }

        if (validUser) {
            response.sendRedirect("WelcomePage.jsp");
            return;
        } else {
            errorMessage = "Username or password don't match!";
        }
    }
%>
<html>
<head>
    <link rel="stylesheet" href="StyleSheet.css">
    <title>Title</title>
</head>
<body>
<div class="login-container">
    <h2>Login</h2>
    <form action="LoginPage.jsp" method="POST">

        <input type="text" id="username" name="username" placeholder="Enter username" required>
        <input type="password" id="password" name="password" placeholder="Enter password" required>

        <% if (!errorMessage.isEmpty()) { %>
        <p class="error-message"><%= errorMessage %></p>
        <% } %>

        <button type="submit">Confirm</button>
    </form>
</div>
</body>
</html>
