<%@ page import="java.util.LinkedList" %>
<%@ page import="com.iotbay.model.Devices" %>
<%@ page import="com.iotbay.model.User" %>

<%
    LinkedList<Devices> deviceList = (LinkedList<Devices>) request.getAttribute("deviceList");
    LinkedList<String> deviceTypes = (LinkedList<String>) request.getAttribute("deviceTypes");
    String searchName = request.getAttribute("searchName") != null ? (String) request.getAttribute("searchName") : "";
    String[] selectedTypes = (String[]) request.getAttribute("selectedTypes");

    boolean isStaff = (Boolean) request.getAttribute("isStaff");
    User currentUser = (User) session.getAttribute("user");
%>

<link rel="stylesheet" href="<%= request.getContextPath() %>/StyleSheet.css">


<div style="background-color: #405acd; padding: 10px; display: flex; justify-content: space-between; align-items: center;">
    <div>
        <a href="<%= request.getContextPath() %>/index.jsp" style="color: white; margin-right: 20px; text-decoration: none;">Home</a>
        <a href="<%= request.getContextPath() %>/LoginPage.jsp" style="color: white; margin-right: 20px; text-decoration: none;">Login</a>
        <% if (currentUser != null)
        { %>
        <a href="<%= request.getContextPath() %>/logout" style="color: white; text-decoration: none;">Logout</a>
        <% } %>
        <a href="<%= request.getContextPath() %>/ProfileEditor.jsp" style="color: white; margin-right: 20px; text-decoration: none;">edit profile</a>
    </div>
    <% if (currentUser != null)
    { %>
    <div style="color: white;">
        Logged in as <strong><%= currentUser.getFullName() != null ? currentUser.getFullName() : currentUser.getEmail() %></strong>
        (<%= currentUser.getUserType() %>)
    </div>
    <% } %>
</div>

<h2 class="heading">IoT Device Catalogue</h2>


<form method="get" action="<%= request.getContextPath() %>/DevicesServlet">
    <label for="searchName">Search by Name:</label>
    <input type="text" name="searchName" id="searchName" value="<%= searchName %>">

    <fieldset>
        <legend>Filter by Type:</legend>
        <% if (deviceTypes != null)
        {
            for (String type : deviceTypes)
            {
                boolean checked = selectedTypes != null && java.util.Arrays.asList(selectedTypes).contains(type); %>
        <label>
            <input type="checkbox" name="type" value="<%= type %>" <%= checked ? "checked" : "" %>> <%= type %>
        </label>
        <%  }
        } %>
    </fieldset>

    <button type="submit">Search</button>
</form>


<% if (isStaff)
{ %>
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
<% } %>

<!-- 📋 Device Table -->
<table class="device-table">
    <thead>
    <tr>
        <% if (isStaff) { %><th>Select</th><% } %>
        <th>Name</th>
        <th>Price</th>
        <th>Type</th>
        <th>Quantity</th>
    </tr>
    </thead>
    <tbody>
    <% for (Devices device : deviceList)
    { %>
    <tr>
        <% if (isStaff)
        { %>
        <td><input type="radio" name="selectedDeviceIdRadio" value="<%= device.getDeviceId() %>"></td>
        <% } %>
        <td><%= device.getDeviceName() %></td>
        <td><%= device.getDevicePrice() %></td>
        <td><%= device.getDeviceType() %></td>
        <td><%= device.getDeviceQuantity() %></td>
    </tr>
    <% } %>
    </tbody>
</table>


<% if (isStaff) { %>
<script>
    function confirmSelection() {
        const selected = document.querySelector('input[name="selectedDeviceIdRadio"]:checked');
        if (!selected)
        {
            alert("Please select a device.");
            return false;
        }
        document.getElementById("editDeviceId").value = selected.value;
        document.getElementById("deleteDeviceId").value = selected.value;
        return true;
    }
</script>
<% } %>
