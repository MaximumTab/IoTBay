<%@ page import="com.iotbay.model.Devices" %>
<%@ page import="com.iotbay.model.dao.DAO" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="java.sql.SQLException" %>

<%
    String idParam = request.getParameter("deviceId");
    if (idParam == null) {
%>
<p style="color: red;">No device selected for editing.</p>
<a href="<%= request.getContextPath() %>/DevicesServlet">Back to Device List</a>
<%
        return;
    }

    int deviceId = Integer.parseInt(idParam);
    DAO dao = (DAO) session.getAttribute("db");
    Devices device = null;

    try {
        device = dao.Devices().get(new Devices(deviceId, null, null, 0.0, 0, null));
    } catch (SQLException e) {
%>
<p style="color: red;">Error loading device from database.</p>
<a href="<%= request.getContextPath() %>/DevicesServlet">Back</a>
<%
        return;
    }

    if (device == null) {
%>
<p style="color: red;">Device not found.</p>
<a href="<%= request.getContextPath() %>/DevicesServlet">Back</a>
<%
        return;
    }
%>

<html>
<head>
    <title>Edit Device</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/CSS/DevicesCss.css">
</head>
<body>

<div class="add-device-container">
    <h2>Edit IoT Device</h2>

    <form method="post" action="<%= request.getContextPath() %>/DeviceEditServlet">
        <input type="hidden" name="deviceId" value="<%= device.getDeviceId() %>">

        <label for="deviceName">Device Name:</label>
        <input type="text" name="deviceName" id="deviceName" value="<%= device.getDeviceName() %>" required>

        <label for="deviceType">Device Type:</label>
        <input type="text" name="deviceType" id="deviceType" value="<%= device.getDeviceType() %>" required>

        <label for="devicePrice">Price:</label>
        <input type="number" step="0.01" name="devicePrice" id="devicePrice" value="<%= device.getDevicePrice() %>" required>

        <label for="deviceQuantity">Quantity:</label>
        <input type="number" name="deviceQuantity" id="deviceQuantity" value="<%= device.getDeviceQuantity() %>" required>

        <button type="submit">Save Changes</button>
    </form>

    <form method="get" action="<%= request.getContextPath() %>/DevicesServlet">
        <button type="submit">Return to Catalogue</button>
    </form>
</div>

</body>
</html>
