<%@ page import="com.iotbay.model.User" %>
<%@ page session="true" %>
<!DOCTYPE html>
<html>
<head>
    <title>Welcome to IoTBay</title>
    <link rel="stylesheet" href="StyleSheet.css">
</head>
<body class="profile-page">

<%
    User user = (User) session.getAttribute("user");

    if (user == null) {
        response.sendRedirect("LoginPage.jsp");
        return;
    }

    String name = user.getFullName();
    String userType = user.getUserType(); // "customer" or "staff"
%>

<div class="profile-container">
    <h2 class="section-title">Welcome, <%= name %>!</h2>

    <%
        if ("staff".equalsIgnoreCase(userType)) {
    %>
    <p class="welcome-text">You are logged in as <strong>staff</strong>.</p>
    <a href="SystemAdmin.jsp" class="btn btn-update">Go to Admin Dashboard</a>
    <%
    } else {
    %>
    <p class="welcome-text">Thanks for joining us as a <strong>customer</strong>.</p>
    <button onclick="location.href='DevicesServlet'">View Devices</button>
    <%
        }
    %>

    <a href="ProfileEditor.jsp" class="btn btn-update" style="margin-top: 20px;">Edit My Profile</a>

    <hr class="divider">
    <a href="logout" class="btn btn-cancel">Logout</a>
</div>

</body>
</html>
