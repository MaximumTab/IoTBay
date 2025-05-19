<!DOCTYPE html>
<html>
<head>
  <title>Staff Login</title>
  <link rel="stylesheet" href="StyleSheet.css">
</head>
<body class="profile-page">

<div class="profile-container">
  <h2 class="section-title">Staff Login</h2>

  <!-- Display error if login fails -->
  <%
    String error = (String) request.getAttribute("error");
    if (error != null) {
  %>
  <p class="error-message"><%= error %></p>
  <%
    }
  %>

  <form method="post" action="stafflogin">
    <label for="email">Email:</label>
    <input type="email" name="email" required><br><br>

    <label for="password">Password:</label>
    <input type="password" name="password" required><br><br>

    <button type="submit" class="btn btn-update">Login</button>
  </form>

  <hr class="divider">

  <p>Not staff? <a href="LoginPage.jsp">Go to customer login</a></p>
</div>

</body>
</html>
