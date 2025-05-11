<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Staff Login</title>
</head>
<body>
<h2>Staff Login</h2>

<% if (request.getAttribute("errorMessage") != null) { %>
<p style="color:red"><%= request.getAttribute("errorMessage") %></p>
<% } %>

<form action="staffLogin" method="post">
  <label>Staff ID:</label><br>
  <input type="number" name="staffId" required><br><br>

  <label>Password:</label><br>
  <input type="password" name="password" required><br><br>

  <input type="submit" value="Login">
</form>

<p>Return to <a href="LoginPage.jsp">Customer Login</a></p>
</body>
</html>
