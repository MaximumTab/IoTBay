<%@ page import="java.sql.*, java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String userId = request.getParameter("id");
    String selectedId = userId;
    String email = "", phone = "", status = "1";
    List<String[]> staffList = new ArrayList<>();

    try {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection("jdbc:sqlite:C:/Users/kevin/.SmartTomcat/IoTBay/IOTBayWebsite/IotBay.db");

        // Fetch staff list for dropdown
        PreparedStatement listStaff = conn.prepareStatement("SELECT user_id, full_name FROM Users WHERE user_type = 'staff'");
        ResultSet rsStaff = listStaff.executeQuery();
        while (rsStaff.next()) {
            staffList.add(new String[]{rsStaff.getString("user_id"), rsStaff.getString("full_name")});
        }

        // check selected staff details
        if (userId != null) {
            PreparedStatement ps = conn.prepareStatement(
                    "SELECT U.email, U.phone, U.is_active " +
                            "FROM Users U WHERE U.user_id = ?"
            );
            ps.setString(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                email = rs.getString("email");
                phone = rs.getString("phone");
                status = rs.getString("is_active");
            }
        }

        conn.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
%>
<html>
<head>
    <title>Update Staff Details</title>
    <style>
        body { font-family: Arial; background: #f4f4f4; padding: 20px; }
        .container { max-width: 600px; margin: auto; background: white; padding: 20px; border-radius: 8px; box-shadow: 0 0 10px rgba(0,0,0,0.1); }
        h2 { text-align: center; }
        input, select, button { width: 100%; padding: 10px; margin: 10px 0; font-size: 16px; }
        button { background-color: #405ACD; color: white; border: none; border-radius: 5px; }
    </style>
    <script>
        function goToSelectedStaff(sel) {
            var selectedId = sel.value;
            if (selectedId) {
                window.location.href = 'UpdateStaff.jsp?id=' + selectedId;
            }
        }
    </script>
</head>
<body>
<div class="container">
    <h2>Update Staff Member</h2>
    <form action="ValidateUpdateStaff.jsp" method="post">
        <input type="hidden" name="user_id" value="<%= selectedId %>">

        <label>Full Name:</label>
        <select name="staffDropdown" onchange="goToSelectedStaff(this)">
            <option value="">-- Select Staff --</option>
            <% for (String[] staff : staffList) { %>
            <option value="<%= staff[0] %>" <%= staff[0].equals(selectedId) ? "selected" : "" %>><%= staff[1] %></option>
            <% } %>
        </select>

        <label>Email:</label>
        <input type="email" name="email" value="<%= email %>" required>

        <label>Phone:</label>
        <input type="text" name="phone" value="<%= phone %>" required>

        <label>Status:</label>
        <label>Status:</label>
        <select name="is_active">
            <option value="1" <%= "1".equals(status) ? "selected" : "" %>>Active</option>
            <option value="0" <%= "0".equals(status) ? "selected" : "" %>>Inactive</option>
        </select>

        <button type="submit">Update Staff</button>
        <a href="SystemAdmin.jsp"><button type="button">Cancel</button></a>
    </form>
</div>
</body>
</html>