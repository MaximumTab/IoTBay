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

<form method="get" action="DevicesServlet">
    <label for="searchName">Search by Name:</label>
    <input type="text" name="searchName" value="<%= searchName %>">

    <fieldset>
        <legend>Filter by Type:</legend>
        <% for (String type : deviceTypes) {
            boolean checked = selectedTypes != null && java.util.Arrays.asList(selectedTypes).contains(type); %>
        <label>
            <input type="checkbox" name="type" value="<%= type %>" <%= checked ? "checked" : "" %>> <%= type %>
        </label>
        <% } %>
    </fieldset>

    <button type="submit">Search</button>
</form>

<table class="device-table">
    <thead>
    <tr>
        <th>Name</th>
        <th>Price</th>
        <th>Type</th>
        <th>Quantity</th>
    </tr>
    </thead>
    <tbody>
    <% for (Devices device : deviceList) { %>
    <tr>
        <td><%= device.getDeviceName() %></td>
        <td><%= device.getDevicePrice() %></td>
        <td><%= device.getDeviceType() %></td>
        <td><%= device.getDeviceQuantity() %></td>
    </tr>
    <% } %>
    </tbody>
</table>
