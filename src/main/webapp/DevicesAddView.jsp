<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add New Device</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/CSS/DevicesCss.css">
</head>
<body>

<div class="add-device-container">
    <h2>Add New IoT Device</h2>

    <form method="post" action="<%= request.getContextPath() %>/DeviceAddServlet">
        <label>Device Name:</label>
        <input type="text" name="deviceName" required>

        <label>Device Type:</label>
        <input type="text" name="deviceType" required>

        <label>Price:</label>
        <input type="number" step="0.01" name="devicePrice" required>

        <label>Quantity:</label>
        <input type="number" name="deviceQuantity" required>

        <button type="submit">Confirm</button>
    </form>

    <form method="get" action="<%= request.getContextPath() %>/DevicesServlet">
        <button type="submit">Return to Catalogue</button>
    </form>
</div>

</body>
</html>
