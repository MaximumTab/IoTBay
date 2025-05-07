<%@ page import="com.iotbay.model.Devices" %>
<%@ page import="com.iotbay.model.dao.DAO" %>
<%@ page import="java.util.LinkedList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    DAO db = (DAO) session.getAttribute("db");
    LinkedList<Devices> deviceList = db.Devices().allDevices();
%>

<html>
<head>
    <title>IoT Device Catalogue</title>
    <link rel="stylesheet" href="StyleSheet.css">
</head>
<body>

<h2 class="heading">IoT Device Catalogue</h2>

<% if (deviceList != null && !deviceList.isEmpty()) { %>
<table class="device-table">
    <thead>
    <tr>
        <th>Device Name</th>
        <th>Type</th>
        <th>Unit Price</th>
        <th>Quantity</th>
        <th>Created By</th>
    </tr>
    </thead>
    <tbody>
    <% for (Devices device : deviceList) { %>
    <tr>
        <td>Example Device</td>
        <td>Sensor</td>
        <td>$15.99</td>
        <td>10</td>
        <td>admin</td>
    </tr>

    <% } %>
    </tbody>
</table>
<% } else { %>
<p>No devices found.</p>
<% } %>

</body>
</html>
