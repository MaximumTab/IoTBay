<%@ page import="java.util.LinkedList" %>
<%@ page import="com.iotbay.model.Devices" %>

<%
    LinkedList<Devices> deviceList = (LinkedList<Devices>) request.getAttribute("deviceList");
    LinkedList<String> deviceTypes = (LinkedList<String>) request.getAttribute("deviceTypes");
    String searchName = request.getAttribute("searchName") != null ? (String) request.getAttribute("searchName") : "";
    String[] selectedTypes = (String[]) request.getAttribute("selectedTypes");
%>

<link rel="stylesheet" href="<%= request.getContextPath() %>/StyleSheet.css">
<h2 class="heading">IoT Device Catalogue</h2>


<form method="get" action="<%= request.getContextPath() %>/DevicesServlet">
    <label for="searchName">Search by Name:</label>
    <input type="text" name="searchName" id="searchName" value="<%= searchName %>">

    <fieldset>
        <legend>Filter by Type:</legend>
        <% if (deviceTypes != null) {
            for (String type : deviceTypes) {
                boolean checked = selectedTypes != null && java.util.Arrays.asList(selectedTypes).contains(type); %>
        <label>
            <input type="checkbox" name="type" value="<%= type %>" <%= checked ? "checked" : "" %>> <%= type %>
        </label>
        <%  }} %>
    </fieldset>

    <button type="submit">Search</button>
</form>


<div style="display: flex; gap: 10px; margin: 1em 0;">
    <form method="get" action="<%= request.getContextPath() %>/DevicesAddView.jsp">
        <button type="submit">Add Device</button>
    </form>

    <form method="get" action="<%= request.getContextPath() %>/DevicesEditView.jsp" onsubmit="return confirmSelection()">
    <input type="hidden" name="deviceId" id="editDeviceId">
        <button type="submit">Edit Device</button>
    </form>

    <form method="post" action="<%= request.getContextPath() %>/DeviceDeleteServlet" onsubmit="return confirmSelection()">
        <input type="hidden" name="selectedDeviceId" id="deleteDeviceId">
        <button type="submit">Delete Device</button>
    </form>
</div>



<table class="device-table">
    <thead>
    <tr>
        <th>Select</th>
        <th>Name</th>
        <th>Price</th>
        <th>Type</th>
        <th>Quantity</th>
    </tr>
    </thead>
    <tbody>
    <% for (Devices device : deviceList) { %>
    <tr>
        <td><input type="radio" name="selectedDeviceIdRadio" value="<%= device.getDeviceId() %>"></td>
        <td><%= device.getDeviceName() %></td>
        <td><%= device.getDevicePrice() %></td>
        <td><%= device.getDeviceType() %></td>
        <td><%= device.getDeviceQuantity() %></td>
    </tr>
    <% } %>
    </tbody>
</table>


<script>
    function confirmSelection() {
        const selected = document.querySelector('input[name="selectedDeviceIdRadio"]:checked');
        if (!selected) {
            alert("Please select a device.");
            return false;
        }
        document.getElementById("editDeviceId").value = selected.value;
        document.getElementById("deleteDeviceId").value = selected.value;
        return true;
    }
</script>
