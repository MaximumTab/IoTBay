<%--
  Created by IntelliJ IDEA.
  Customer: maksy
  Date: 18/03/2025
  Time: 7:26 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String email = request.getParameter("email");
    String fullname = request.getParameter("fullname");
    String password = request.getParameter("password");
    String gender = request.getParameter("gender");
    String favouriteColour = request.getParameter("favouriteColour");
    String agreeTOS = request.getParameter("agreeTOS");

    String errorMessage = "";

    if (email != null && fullname != null && password != null && gender != null) {
        if (agreeTOS == null) {
            errorMessage = "You must agree to the Terms and Conditions!";
        } else {
            // Store values directly in the session
            session.setAttribute("email", email);
            session.setAttribute("fullname", fullname);
            session.setAttribute("password", password);
            session.setAttribute("gender", gender);
            session.setAttribute("favouriteColour", favouriteColour);

            response.sendRedirect("WelcomePage.jsp");
            return;
        }
    }
%>
<html>
<head>
    <link rel="stylesheet" href="StyleSheet.css">
    <title>Register</title>
</head>
<body>
<div class="login-container">
    <h2>Register</h2>
    <form action="RegisterPage.jsp" method="POST">
        <input type="email" name="email" placeholder="Enter email" required>
        <input type="text" name="fullname" placeholder="Enter full name" required>
        <input type="password" name="password" placeholder="Enter password" required>

        <select name="gender" required>
            <option value="">Select gender</option>
            <option>Male</option>
            <option>Female</option>
            <option>Other</option>
        </select>

        <input type="text" name="favouriteColour" placeholder="Favourite colour (optional)">

        <label>
            <input type="checkbox" name="agreeTOS" required>
            I agree to the
            <a href="terms.jsp" style="color: blue; text-decoration: underline;" target="_blank">
                Terms and Conditions
            </a>
        </label>

        <% if (!errorMessage.isEmpty()) { %>
        <p class="error-message"><%= errorMessage %></p>
        <% } %>

        <button type="submit">Register</button>
    </form>
</div>
</body>
</html>

