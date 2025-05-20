<<<<<<< HEAD
<%@ page import="com.iotbay.model.Customer" %>
<%@ page session="true" %>
<%
    Customer loggedIn = (Customer) session.getAttribute("loggedInCustomer");
=======
<%@ page import="com.iotbay.model.User" %>
<%@ page import="com.iotbay.model.dao.DAO" %>
<%@ page session="true" %>

<%
    User loggedIn =  (User) session.getAttribute("LoggedInUser");
    DAO db = (DAO)session.getAttribute("db");
>>>>>>> a21e133 (Initial commit from IntelliJ)
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>IoTBay</title>
    <link rel="stylesheet" href="StyleSheet.css">
</head>
<body>
<div class="topnav">
    <div class="nav-left">
        <img src="images/iotbay.png" alt="logo" width="60" height="60" />
    </div>
    <b class="welcome-text">Welcome to IoTBAY</b>
    <div class="nav-right">
        <button onclick="location.href='LoginPage.jsp'">Log in</button>
        <button onclick="location.href='RegisterPage.jsp'">Register</button>
<<<<<<< HEAD
=======
        <button onclick="location.href='MainPage.jsp'">Main Page</button>
        <button onclick="location.href='DevicesServlet'">View Devices</button>
        <button onclick="location.href='staff?action=list'">Staff Management</button>
>>>>>>> a21e133 (Initial commit from IntelliJ)
    </div>
</div>

<div class="landingbody">
    <div class="landing-text">

        <b>Shop</b>
        <b>Anywhere</b>
        <b>Anytime</b>
        <p style="padding-top: 20px; font-size: 14px;">
<<<<<<< HEAD
            There are currently <%= Customer.getNumUsers() %> registered users!
=======
            There are currently ---- registered users!
>>>>>>> a21e133 (Initial commit from IntelliJ)
        </p>

    </div>
    <div class="landing-image">
        <img src="images/shopping.png" alt="Shopping" />
    </div>

    <div class="current">
        <% if (loggedIn != null) { %>
<<<<<<< HEAD
        <p>Currently logged-in user: <strong><%= loggedIn.getFname() %></strong></p>
=======
        <p>Currently logged-in user: <strong><%= loggedIn.getFullName() %></strong></p>
>>>>>>> a21e133 (Initial commit from IntelliJ)
        <% } else { %>
        <p></p>
        <% } %>
    </div>
</div>
</body>
</html>
