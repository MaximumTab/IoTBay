<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    //  Once on the page, the user exits (destroys session)
    session.invalidate();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Logout - IoTBay</title>
    <!--  -->
    <!-- <link rel="stylesheet" href="StyleSheet.css"> -->

    <style>
        /* ======== Inline CSS======== */
        body {
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
            background: #f5f6fa;
        }
        .logout-container {
            width: 400px;
            max-width: 90%;
            margin: 80px auto;
            background-color: #fff;
            border-radius: 8px;
            padding: 40px;
            box-shadow: 0 4px 15px rgba(0,0,0,0.1);
            text-align: center;
        }
        .logout-container h1 {
            margin-bottom: 20px;
            font-size: 2rem;
            font-weight: 600;
            color: #333;
        }
        .logout-container p {
            font-size: 1rem;
            margin-bottom: 30px;
            color: #666;
        }
        .logout-button {
            background-color: #007BFF;
            color: #fff;
            border: none;
            border-radius: 6px;
            padding: 12px 24px;
            font-size: 1rem;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        .logout-button:hover {
            background-color: #0056b3;
        }
        .logout-footer {
            margin-top: 20px;
            font-size: 0.9rem;
            color: #999;
        }
        .logout-footer a {
            color: #007BFF;
            text-decoration: none;
        }
        .logout-footer a:hover {
            text-decoration: underline;
        }
        /* ======== Inline Css ======== */
    </style>
</head>
<body>
<div class="logout-container">
    <h1>You have logged out</h1>
    <p>Thank you for visiting IoTBay! We hope to see you again soon.</p>
    <!-- Click this button to return to the login page -->
    <button class="logout-button" onclick="window.location.href='index.jsp'">
        Go Back
    </button>

    <!-- Give some additional links or instructions at the bottom of the page -->
    <div class="logout-footer">
        <p><a href="LoginPage.jsp">Go to Login</a></p>
    </div>
</div>
</body>
</html>
