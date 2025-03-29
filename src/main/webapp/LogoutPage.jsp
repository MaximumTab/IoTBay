<%@ page import="com.iotbay.model.Customer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<%
    Customer loggedOutUser = (Customer) session.getAttribute("loggedInCustomer");
    session.removeAttribute("loggedInCustomer");

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
    <div class="logout-message" style="text-align: center; margin-top: 100px;">
        <h2>
            <% if (loggedOutUser != null) { %>
            Have a lovely day, <%= loggedOutUser.getFname() %>! 👋
            <% } else { %>
            have a beautiful day 👋
            <% } %>
        </h2>

        <a href="index.jsp" style="text-align: center; display: block; margin-top: 20px;">
            Click here if you want to go back to the home page
        </a>
    </div>
    <div class="landing-image">
        <img src="images/shopping.png" alt="Shopping" />
    </div>

</div>
</body>
</html>