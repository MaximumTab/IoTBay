<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    boolean emailExists = Boolean.parseBoolean(request.getParameter("emailExists"));
    boolean phoneExists = Boolean.parseBoolean(request.getParameter("phoneExists"));
%>
<html>
<head>
    <title>User Already Exists</title>
    <style>
        body { font-family: Arial; background: #f4f4f4; text-align: center; padding: 50px; }
        .box {
            display: inline-block;
            background: white;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        a {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 20px;
            background: #405ACD;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }
    </style>
</head>
<body>
<div class="box">
    <h2>Account Creation Error</h2>
    <% if (emailExists) { %>
    <p style="color: red;">The email you entered already exists.</p>
    <% } %>
    <% if (phoneExists) { %>
    <p style="color: red;">The phone number you entered already exists.</p>
    <% } %>
    <a href="CreateCustomer.jsp">Go Back</a>
</div>
</body>
</html>