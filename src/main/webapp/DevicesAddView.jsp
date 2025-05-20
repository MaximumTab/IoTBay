<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Add New Device</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/StyleSheet.css">
</head>
<body>

<h2>Add New IoT Device</h2>

<form method="post" action="<%= request.getContextPath() %>/DeviceAddServlet">
    <label>Device Name:</label>
    <input type="text" name="deviceName" required><br>

    <label>Device Type:</label>
    <input type="text" name="deviceType" required><br>

    <label>Price:</label>
    <input type="number" step="0.01" name="devicePrice" required><br>

    <label>Quantity:</label>
    <input type="number" name="deviceQuantity" required><br>

    <button type="submit">Confirm</button>
</form>

</body>
</html>
