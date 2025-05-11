<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.iotbay.model.User" %>
<%
    HttpSession session = request.getSession(false);
    User user = (session != null) ? (User) session.getAttribute("user") : null;
%>

<html>
<head>
    <title>IoTBay - Home</title>
</head>
<body>
<h1>Welcome to IoTBay</h1>

<%
    if (user != null) {
%>
<p>Hello, <strong><%= user.getFullName() %></strong>! (<%= user.getUserType() %>)</p>

<ul>
    <li><a href="ViewProfile.jsp">View Registration Details</a></li>
    <li><a href="UpdateProfile.jsp">Update Registration Details</a></li>
    <li><a href="AccessLogs.jsp">View Access Logs</a></li>
    <li><a href="CancelAccount.jsp">Cancel Registration</a></li>
    <li><a href="LogoutServlet">Logout</a></li>
</ul>
<%
} else {
%>
<p>Please log in or register to access your account.</p>
<ul>
    <li><a href="LoginPage.jsp">Customer Login</a></li>
    <li><a href="StaffLoginPage.jsp">Staff Login</a></li>
    <li><a href="RegisterPage.jsp">Register</a></li>
</ul>
<%
    }
%>
</body>
</html>

