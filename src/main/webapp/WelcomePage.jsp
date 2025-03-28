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
    String fullname = (String) session.getAttribute("fullname");
    String fname =(String) session.getAttribute("fname");
    String lname =(String) session.getAttribute("lname");
    String address =(String) session.getAttribute("address");
    String phone =(String) session.getAttribute("phone");
    String email =(String) session.getAttribute("email");
    String username =(String) session.getAttribute("username");
    String password =(String) session.getAttribute("password");

    if(username != null) {
        Customer customer = new Customer(1, fname, lname, address, 1, 1, 1, 1, email, password);
        session.setAttribute("customer", customer);
%>
<p>Welcome, <strong><%= (String) session.getAttribute("username") %></strong>! You are logged in.</p>
    <a href="MainPage.jsp"><button>Go to Main Page</button></a>
<%
} else {
%>
<p>You are not logged in. Please <a href="LoginPage.jsp">log in</a>.</p>
<%
    }
%>
</div>

</body>
</html>
