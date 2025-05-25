<%@ page import="com.iotbay.model.User" %>
<%@ page import="com.iotbay.model.dao.DAO" %>
<%@ page session="true" %>

<%
    User loggedIn =  (User) session.getAttribute("LoggedInUser");
    DAO db = (DAO)session.getAttribute("db");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>IoTBay</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/StyleSheet.css">

    <style>
        /* Top Navigation Styles */
        .topnav {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 10px 20px;
            background-color: #9fc0e8;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.3);
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            z-index: 1000;
            box-sizing: border-box;
        }

        .nav-left {
            height: 50px;
            width: 60px;
            display: flex;
            align-items: center;
            justify-content: center;
            overflow: hidden;
        }

        .nav-left img {
            max-height: 100%;
            max-width: 100%;
            object-fit: contain;
            display: block;
        }

        .nav-center {
            position: absolute;
            left: 50%;
            transform: translateX(-50%);
        }

        .welcome-text {
            font-size: 24px;
            font-weight: bold;
            color: #333;
            white-space: nowrap;
        }

        .nav-right {
            display: flex;
            align-items: center;
            gap: 8px;
            flex-shrink: 0;
            overflow: hidden;
        }

        .nav-right button {
            background-color: #405ACD;
            color: white;
            border: none;
            padding: 6px 12px;
            font-size: 13px;
            border-radius: 4px;
            cursor: pointer;
            white-space: nowrap;
        }

        .nav-right button:hover {
            background-color: #B9B9B9;
        }

        /* Landing Content */
        .landingbody {
            display: flex;
            height: calc(100vh - 80px);
            margin-top: 80px;
        }

        .landing-text {
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            font-size: 70px;
            gap: 10px;
            color: #333;
            padding-left: 100px;
        }

        .landing-image {
            flex: 1;
            height: calc(100vh - 80px);
            display: flex;
            justify-content: flex-end;
            align-items: flex-end;
            padding-left: 180px;
        }

        .landing-image img {
            width: 100%;
            height: 100%;
            object-fit: contain;
            display: block;
        }

        /* Responsive Adjustments */
        @media (max-width: 768px) {
            .welcome-text {
                font-size: 18px;
            }

            .nav-right {
                gap: 4px;
            }

            .nav-right button {
                padding: 4px 8px;
                font-size: 12px;
            }

            .landing-text {
                font-size: 40px;
                padding-left: 20px;
                text-align: center;
            }

            .landingbody {
                flex-direction: column;
                align-items: center;
                justify-content: center;
            }

            .landing-image {
                padding: 0;
                justify-content: center;
                align-items: center;
            }
        }
    </style>
</head>
<body>

<div class="topnav">
    <div class="nav-left">
        <img src="images/iotbay.png" alt="logo" />
    </div>

    <div class="nav-center">
        <span class="welcome-text">Welcome to IoTBAY</span>
    </div>

    <div class="nav-right">
        <button onclick="location.href='LoginPage.jsp'">Log in</button>
        <button onclick="location.href='RegisterPage.jsp'">Register</button>
        <button onclick="location.href='MainPage.jsp'">Main Page</button>
        <button onclick="location.href='DevicesServlet'">View Devices</button>
        <button onclick="location.href='staff?action=list'">Staff Management</button>
    </div>
</div>

<div class="landingbody">
    <div class="landing-text">
        <b>Shop</b>
        <b>Anywhere</b>
        <b>Anytime</b>
        <p style="padding-top: 20px; font-size: 14px;">
            There are currently ---- registered users!
        </p>

    </div>

    <div class="landing-image">
        <img src="images/shopping.png" alt="Shopping" />
    </div>

    <div class="current">
        <% if (loggedIn != null) { %>
        <p>Currently logged-in user: <strong><%= loggedIn.getFullName() %></strong></p>
        <% } else { %>
        <p></p>
        <% } %>
    </div>
</div>

</body>
</html>
