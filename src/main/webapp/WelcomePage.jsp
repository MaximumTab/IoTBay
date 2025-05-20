<<<<<<< HEAD
<%@ page import="com.iotbay.model.Customer" %>
<%--
  Created by IntelliJ IDEA.
  com.iotbay.model.Customer: maksy
  Date: 18/03/2025
  Time: 7:26 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<html>
<head>
    <link rel="stylesheet" href="StyleSheet.css">
    <title>Welcome Page</title>
    <style>
        a {
            background-color: #405ACD;
            color: white;
            cursor: pointer;
            text-decoration: none;
            display: inline-block;
            margin-top: 20px;
        }

        a button {
            background-color: #405ACD;
            color: white;
            padding: 15px 25px;
            font-size: 18px;
            border-radius: 5px;
            border: none;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        a button:hover {
            background-color: #3649A3;
        }

        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: 'Arial', sans-serif;
        }

        body, html {
            height: 100%;
            width: 100%;
            display: flex;
            justify-content: flex-start;
            align-items: center;
            color: #333;
            padding-left: 30px;
        }

        .coolbackground{
            background-image: url("images/welcome.jpg");
        }

        .header {
            width: 100%;
            background-color: #405ACD; /* Blue */
            color: white;
            text-align: center;
            padding: 20px 0;
            position: fixed;
            top: 0;
            left: 0;
            z-index: 10; /* Make sure it's above other content */
        }

        .header h1 {
            font-size: 32px;
            margin: 0;
        }

        .welcome-message {
            font-size: 60px;
            font-weight: bold;
            color: #000000;
            margin-bottom: 20px;
        }

        .welcome-message p {
            font-size: 20px;
            color: #555;
            margin-bottom: 30px;
        }

    </style>

</head>
<body class="coolbackground">
<div class="header">
    <h1>IoTBay</h1>
</div>
<div class="welcome-message">
<h1>Welcome!</h1>

<%
    session = request.getSession();
    Customer customer = (Customer) session.getAttribute("customer");

    Boolean loggedInUser = true;

    String fname =(String) session.getAttribute("fname");
    String lname =(String) session.getAttribute("lname");
    String address =(String) session.getAttribute("address");
    String phone =(String) session.getAttribute("phone");
    String email = request.getParameter("email");
    //String email =(String) session.getAttribute("email");
    String username = request.getParameter("username");
    //String username =(String) session.getAttribute("username");
    String password =(String) session.getAttribute("password");
    String displayedName = customer.getEmail().split("@")[0];

    if(customer.getEmail() == null){ //I added this to check if the user is logged in or not
        loggedInUser = false;
    }

    if(loggedInUser) {
        session.setAttribute("customer", customer);
%>
<p>Welcome, <strong><%= displayedName %></strong>! You are logged in.</p>
    <a href="MainPage.jsp"><button>Go to Main Page</button></a>
    <p style="padding-top: 50px">There are currently <%=customer.getNumOnlineUsers()%> Online users!</p>
<%
} else { //this part works if its first time registered also if you are reading these, Ilker sends you a cookie :)
        customer.setId(1);
        customer.setFname(fname);
        customer.setLname(lname);
        customer.setAddress(address);
        customer.setPostalCode(1);
        customer.setPhone(1);
        customer.setBsb(1);
        customer.setAccNum(1);
%>
    <p>Welcome, New User <strong><%= displayedName %></strong>! You are logged in.</p>
    <a href="MainPage.jsp"><button>Go to Main Page</button></a>
    <p style="padding-top: 50px">There are currently <%=customer.getNumOnlineUsers()%> Online users!</p>
<%
    }
%>
=======
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
>>>>>>> a21e133 (Initial commit from IntelliJ)
</div>

</body>
</html>
