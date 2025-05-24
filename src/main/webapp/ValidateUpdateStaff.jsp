<%@ page import="java.sql.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String userId = request.getParameter("user_id");
    String email = request.getParameter("email");
    String phone = request.getParameter("phone");
    String isActive = request.getParameter("is_active");

    boolean valid = true;
    String errorMessage = "";

    if (!phone.matches("\\d{1,10}")) {
        valid = false;
        errorMessage = "Invalid phone number. Only digits allowed and must be up to 10 digits (e.g. 0412345678).";
    }

    if (!valid) {
%>
<html>
<head>
    <title>Validation Error</title>
    <style>
        body {
            font-family: Arial;
            background: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 600px;
            margin: 80px auto;
            padding: 20px;
            background: white;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            text-align: center;
        }

        .error {
            background-color: #ffe6e6;
            border: 2px solid #cc0000;
            color: #cc0000;
            padding: 15px;
            border-radius: 5px;
            font-size: 18px;
            margin-bottom: 20px;
        }

        .btn {
            padding: 10px 20px;
            background-color: #405ACD;
            color: white;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
            text-decoration: none;
        }

        .btn:hover {
            background-color: #2e46a1;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="error">
         <%= errorMessage %>
    </div>
    <a href="UpdateStaff.jsp?id=<%= userId %>" class="btn">Go Back</a>
</div>
</body>
</html>
<%
    } else {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:C:/Users/kevin/.SmartTomcat/IoTBay/IOTBayWebsite/IotBay.db");

            PreparedStatement updateUser = conn.prepareStatement(
                    "UPDATE Users SET email = ?, phone = ?, is_active = ? WHERE user_id = ?"
            );
            updateUser.setString(1, email);
            updateUser.setString(2, phone);
            updateUser.setString(3, isActive);
            updateUser.setString(4, userId);
            updateUser.executeUpdate();

            conn.close();
            response.sendRedirect("SystemAdmin.jsp?type=staff");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
%>
