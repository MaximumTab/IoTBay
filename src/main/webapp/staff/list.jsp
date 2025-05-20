<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, com.iotbay.model.Staff" %>


<%
  String error = (String) request.getAttribute("error");
  String contextPath = request.getContextPath();
%>

<html>
<head>
  <meta charset="UTF-8">
  <title>Staff List</title>
  <link rel="stylesheet"
        type="text/css"
        href="<%= contextPath %>/staff/style.css"/>
  <style>
    table {
      width: 100%;
      border-collapse: collapse;
      margin-top: 20px;
    }
    th, td {
      padding: 10px;
      border: 1px solid #ddd;
      text-align: left;
    }
    th {
      background-color: #f5f5f5;
    }
    .action {
      margin-right: 10px;
      text-decoration: none;
      color: #0066cc;
    }
    .action:hover {
      text-decoration: underline;
    }
    .search-form {
      margin: 20px 0;
    }
    .search-form input, .search-form select {
      padding: 5px;
      margin-right: 10px;
    }
    .search-form button {
      padding: 5px 15px;
      background-color: #0066cc;
      color: white;
      border: none;
      cursor: pointer;
    }
    .search-form button:hover {
      background-color: #0052a3;
    }
    .add-button {
      display: inline-block;
      padding: 10px 20px;
      background-color: #28a745;
      color: white;
      text-decoration: none;
      border-radius: 5px;
      margin-bottom: 20px;
    }
    .add-button:hover {
      background-color: #218838;
    }
    .header {
      display: flex;
      justify-content: space-between;
      align-items: center;
      margin-bottom: 20px;
    }
  </style>
</head>
<body>
<nav>
  <a href="<%= contextPath %>/index.jsp">Home</a> |
  <a href="<%= contextPath %>/staff?action=list">Staff</a>
</nav>

<div class="header">
  <h2>Staff List</h2>
  <a href="<%= contextPath %>/staff?action=addForm" class="add-button">Add New Staff</a>
</div>

<% if (error != null) { %>
<div class="error-messages">
    <div class="error"><%= error %></div>
</div>
<% } %>

<div class="search-form">
  <form action="<%= contextPath %>/staff" method="get">
    <input type="hidden" name="action" value="list"/>
    <input type="text" name="name" placeholder="Search by name" value="<%= request.getParameter("name")==null?"":request.getParameter("name") %>"/>
    <select name="position">
      <option value="">All Positions</option>
      <option value="Manager" <%= request.getParameter("position")!=null && request.getParameter("position").equals("Manager") ? "selected" : "" %>>Manager</option>
      <option value="Salesperson" <%= request.getParameter("position")!=null && request.getParameter("position").equals("Salesperson") ? "selected" : "" %>>Salesperson</option>
      <option value="IT Support" <%= request.getParameter("position")!=null && request.getParameter("position").equals("IT Support") ? "selected" : "" %>>IT Support</option>
    </select>
    <button class="btn" type="submit">Search</button>
  </form>
</div>

<table>
  <tr>
    <th>ID</th>
    <th>Name</th>
    <th>Email</th>
    <th>Position</th>
    <th>Status</th>
    <th>Actions</th>
  </tr>
  <%
    List<Staff> list = (List<Staff>)request.getAttribute("staffList");
    if (list != null) {
      for (Staff s : list) {
  %>
  <tr>
    <td><%=s.getId()%></td>
    <td><%=s.getName()%></td>
    <td><%=s.getEmail()%></td>
    <td><%=s.getPosition()%></td>
    <td><%=s.getStatus()%></td>
    <td>
      <a class="action" href="<%= contextPath %>/staff?action=editForm&id=<%=s.getId()%>">Edit</a>
      <a class="action" href="<%= contextPath %>/staff?action=delete&id=<%=s.getId()%>" onclick="return confirm('Delete?');">Delete</a>
      <a class="action" href="<%= contextPath %>/staff?action=toggle&id=<%=s.getId()%>">
        <%= "ACTIVE".equals(s.getStatus()) ? "Deactivate" : "Activate" %>
      </a>
    </td>
  </tr>
  <%
      }
    }
  %>
</table>
</body>
</html> 