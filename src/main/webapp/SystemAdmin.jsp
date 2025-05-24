<%@ page import="java.sql.*, java.util.*, com.iotbay.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  List<User> allUsers = new ArrayList<>();
  String filterType = request.getParameter("type");
  String searchName = request.getParameter("searchName");
  String[] deletedIds = request.getParameterValues("deleteIds");
  String undo = request.getParameter("undo");
  Connection conn = null;
  List<Integer> recentlyDeleted = new ArrayList<>();

  try {
    Class.forName("org.sqlite.JDBC");
    conn = DriverManager.getConnection("jdbc:sqlite:C:/Users/kevin/.SmartTomcat/IoTBay/IOTBayWebsite/IotBay.db");

    // UNDO check delete user then undo
    if ("true".equals(undo) && session.getAttribute("lastDeleted") != null) {
      List<Integer> ids = (List<Integer>) session.getAttribute("lastDeleted");

      for (Integer id : ids) {
        // Check if user doesn't already exist in Users table
        PreparedStatement checkExist = conn.prepareStatement("SELECT COUNT(*) FROM Users WHERE user_id = ?");
        checkExist.setInt(1, id);
        ResultSet rsCheck = checkExist.executeQuery();

        if (rsCheck.next() && rsCheck.getInt(1) == 0) {
          // Restore from database
          PreparedStatement undoPs = conn.prepareStatement(
                  "INSERT INTO Users (user_id, card_id, full_name, email, password_hash, phone, user_type, is_active) " +
                          "SELECT user_id, card_id, full_name, email, password_hash, phone, user_type, is_active " +
                          "FROM temp_deleted_users WHERE user_id = ? AND user_type IN ('staff', 'customer')"
          );

          undoPs.setInt(1, id);
          undoPs.executeUpdate();

          PreparedStatement cleanup = conn.prepareStatement("DELETE FROM temp_deleted_users WHERE user_id = ?");
          cleanup.setInt(1, id);
          cleanup.executeUpdate();
        }
      }
      session.removeAttribute("lastDeleted");
    }

    // DELETE logic
    if (deletedIds != null && "staff".equals(filterType)) {
      for (String idStr : deletedIds) {
        int id = Integer.parseInt(idStr);
        if (id != 21) {
          PreparedStatement copy = conn.prepareStatement(
                  "INSERT INTO temp_deleted_users (user_id, card_id, full_name, email, password_hash, phone, user_type, is_active) " +
                          "SELECT user_id, card_id, full_name, email, password_hash, phone, user_type, is_active " +
                          "FROM Users WHERE user_id = ? AND user_type IN ('staff', 'customer')"
          );
          copy.setInt(1, id);
          copy.executeUpdate();

          PreparedStatement delete = conn.prepareStatement("DELETE FROM Users WHERE user_id = ?");
          delete.setInt(1, id);
          delete.executeUpdate();

          recentlyDeleted.add(id);
        }
      }
      session.setAttribute("lastDeleted", recentlyDeleted);
    }

    // SELECT users
    StringBuilder sql = new StringBuilder("SELECT U.* FROM Users U LEFT JOIN Suppliers S ON U.email = S.email WHERE 1=1");

    if ("individual".equals(filterType) || "company".equals(filterType)) {
      sql.append(" AND U.user_type = 'customer' AND S.type = ?");
    } else if ("staff".equals(filterType)) {
      sql.append(" AND U.user_type = 'staff'");
    } else if ("customer".equals(filterType)) {
      sql.append(" AND U.user_type = 'customer'");
    }

    if (searchName != null && !searchName.trim().isEmpty()) {
      sql.append(" AND U.full_name LIKE ?");
    }

    PreparedStatement ps = conn.prepareStatement(sql.toString());
    int index = 1;
    if (filterType != null && ("individual".equals(filterType) || "company".equals(filterType))) {
      ps.setString(index++, filterType);
    }
    if (searchName != null && !searchName.trim().isEmpty()) {
      ps.setString(index++, searchName + "%");
    }

    ResultSet rs = ps.executeQuery();
    while (rs.next()) {
      allUsers.add(new User(
              rs.getInt("user_id"),
              rs.getInt("card_id"),
              rs.getString("full_name"),
              rs.getString("email"),
              rs.getString("password_hash"),
              rs.getString("phone"),
              rs.getString("user_type"),
              String.valueOf(rs.getInt("is_active"))
      ));
    }
    conn.close();
  } catch (Exception e) {
    e.printStackTrace();
  }
%>

<html>
<head>
  <title>IoTBay Admin Dashboard</title>
  <style>
    body { font-family: Arial; background: #f4f4f4; margin: 0; }
    .header { background: #405ACD; color: white; padding: 20px; text-align: center; font-size: 28px; }
    .actions { text-align: center; margin: 20px; }
    .actions input, .actions select, .actions button { padding: 10px; margin: 5px; font-size: 16px; }
    .table-container { width: 95%; margin: 0 auto; }
    table { width: 100%; border-collapse: collapse; }
    th, td { padding: 10px; border: 1px solid #ccc; text-align: center; }
    th { background: #405ACD; color: white; }
  </style>
</head>
<body>
<div class="header">IoTBay Admin Dashboard</div>

<div class="actions">
  <form method="get" action="SystemAdmin.jsp" style="display: inline;">
    <input type="text" name="searchName" placeholder="Search by name">
    <select name="type">
      <option value="all">All Types</option>
      <option value="individual">Individual</option>
      <option value="company">Company</option>
      <option value="staff">Staff</option>
    </select>
    <button type="submit">Search</button>
  </form>
  <a href="SystemAdmin.jsp?type=customer"><button>Customer Only</button></a>
  <a href="SystemAdmin.jsp?type=staff"><button>Staff Only</button></a>
  <a href="UpdateStaff.jsp"><button>Update Staff</button></a>
  <a href="CreateCustomer.jsp" target="_blank"><button>Create Customer</button></a>
  <a href="LogoutPage.jsp"><button>Logout</button></a>
</div>

<% if ("staff".equals(filterType)) { %>
<form method="post" action="SystemAdmin.jsp">
  <input type="hidden" name="type" value="staff">
  <button type="submit" name="undo" value="true">Undo Delete</button>
  <button type="submit">Delete Selected</button>
  <% } %>

  <div class="table-container">
      <% if (!allUsers.isEmpty()) { %>
    <table>
      <thead>
      <tr>
        <% if ("staff".equals(filterType)) { %><th>Select</th><% } %>
        <th>ID</th>
        <th>Full Name</th>
        <th>Email</th>
        <th>Password Hash</th>
        <th>Phone</th>
        <th>User Type</th>
        <th>Active</th>
      </tr>
      </thead>
      <tbody>
      <% for (User u : allUsers) { %>
      <tr>
        <% if ("staff".equals(filterType)) { %>
        <td><% if (u.getUserId() != 21) { %><input type="checkbox" name="deleteIds" value="<%= u.getUserId() %>" /><% } %></td>
        <% } %>
        <td><%= u.getUserId() %></td>
        <td><%= u.getFullName() %></td>
        <td><%= u.getEmail() %></td>
        <td><%= u.getPasswordHash() %></td>
        <td><%= u.getPhone() %></td>
        <td><%= u.getUserType() %></td>
        <td><%= u.getIsActive().equals("1") ? "Active" : "Inactive" %></td>
      </tr>
      <% } %>
      </tbody>
    </table>
  <% if ("staff".equals(filterType)) { %></form><% } %>
<% } else { %>
<p style="text-align: center; color: red;">No users found in the database.</p>
<% } %>
</div>
</body>
</html>