<%@ page import="com.iotbay.model.Customer" %>
<%@ page session="true" %>
<%
    Customer loggedIn = (Customer) session.getAttribute("loggedInCustomer");
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
</div>

<div class="landingbody">
    <div class="landing-text">
        <b>have</b>
        <b>a</b>
        <b>lovely</b>
        <b>day</b>
        <div class="current">
            <% if (loggedIn != null) { %>
            <p><strong><%= loggedIn.getFname() %></strong></p>
            <% } else { %>
            <p></p>
            <% } %>
        </div>
    </div>
    <div class="landing-image">
        <img src="images/shopping.png" alt="Shopping" />
    </div>

</div>
</body>
</html>