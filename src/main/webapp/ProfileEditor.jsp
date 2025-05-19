<%@ page import="com.iotbay.model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="java.lang.String" %>

<%
  User user = (User) session.getAttribute("user");
  if (user == null) {
    response.sendRedirect("login.jsp");
    return;
  }
%>

<!DOCTYPE html>
<html>
<head>
  <title>Profile Editor</title>
  <link rel="stylesheet" href="StyleSheet.css">
</head>
<body class="profile-page">

<div class="profile-container">
  <h2 class="section-title">Edit Your Profile</h2>
  <p class="welcome-text">Welcome, <%= user.getFullName() %> (<%= user.getUserType() %>)</p>

  <!-- Feedback -->
  <%
    String success = (String) request.getAttribute("success");
    String error = (String) request.getAttribute("error");
    if (success != null) {
  %>
  <p class="success-message"><%= success %></p>
  <%
  } else if (error != null) {
  %>
  <p class="error-message"><%= error %></p>
  <%
    }
  %>

  <!-- Profile Form -->
  <form method="post" action="profile" class="profile-form">
    <label for="fullName">Full Name:</label><br>
    <input type="text" name="fullName" id="fullName" value="<%= user.getFullName() %>" required><br><br>

    <label for="phone">Phone:</label><br>
    <input type="text" name="phone" id="phone" value="<%= user.getPhone() %>"><br><br>

    <label for="password">New Password (leave blank to keep current):</label><br>
    <input type="password" name="password" id="password"><br><br>

    <button type="submit" class="btn btn-update">Update Profile</button>
  </form>

  <!-- Cancel Registration -->
  <hr class="divider">
  <form method="post" action="DeactivateAccountServlet"
        onsubmit="return confirm('Are you sure you want to cancel your registration?');">
    <button type="submit" class="btn btn-deactivate">Cancel Registration</button>
  </form>

  <hr class="divider">
  <form method="get" action="logout">
    <button type="submit" class="btn btn-cancel">Logout</button>
  </form>

  <!-- View Logs -->
  <hr class="divider">
  <h3>Access Logs</h3>

  <%
    List<String[]> logs = (List<String[]>) request.getAttribute("logs");
    if (logs != null && !logs.isEmpty()) {
  %>
  <table border="1" class="access-log-table">
    <tr>
      <th>Login Time</th>
      <th>Logout Time</th>
    </tr>
    <%
      for (String[] log : logs) {
    %>
    <tr>
      <td><%= log[0] %></td>
      <td><%= log[1] != null ? log[1] : "Still logged in" %></td>
    </tr>
    <%
      }
    %>
  </table>
  <%
  } else if (logs != null) {
  %>
  <p>No logs found.</p>
  <%
    }
  %>

</div>

</body>
</html>

