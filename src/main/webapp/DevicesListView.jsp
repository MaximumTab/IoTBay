<%@ page import="com.iotbay.model.Devices" %>
<%@ page import="com.iotbay.model.dao.DAO" %>
<%@ page import="java.util.LinkedList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    DAO db = (DAO) session.getAttribute("db");
%>

<html>
<head>
    <title>IoT Device Catalogue</title>
    <link rel="stylesheet" href="StyleSheet.css">
</head>
<body>

<h2 class="heading">IoT Device Catalogue</h2>

<table class="device-table">
    <% for (Devices device : db.Devices().allDevices()) { %>
    <tbody>
        <tr>
            <td><%=device.getDeviceName()%></td>
            <td><%=device.getDevicePrice()%></td>
            <td><%=device.getDeviceType()%></td>
            <td><%=device.getDeviceQuantity()%></td>
        </tr>
    </tbody>
    <% } %>
</table>

</body>
</html>
