<%@ page contentType="text/html;charset=UTF-8" language="java"
         import="com.iotbay.model.Staff,java.util.List" %>
<%
  // Must come before any HTML so 'edit' is in scope
  Staff s = (Staff) request.getAttribute("staff");
  boolean edit = (s != null);
  
  // Get error messages if any
  List<String> errors = (List<String>) request.getAttribute("errors");
  String error = (String) request.getAttribute("error");
  String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title><%= edit ? "Edit Staff" : "Add New Staff" %></title>
  <!-- Link to the shared CSS in this folder -->
  <link rel="stylesheet"
        href="<%= contextPath %>/staff/style.css"
        type="text/css"/>
</head>
<body>
<nav>
  <a href="<%= contextPath %>/index.jsp">Home</a> |
  <a href="<%= contextPath %>/staff?action=list">Staff</a>
</nav>

<h2><%= edit ? "Edit Staff" : "Add New Staff" %></h2>

<!-- Display validation errors if any -->
<% if (errors != null && !errors.isEmpty()) { %>
<div class="error-messages">
    <% for (String err : errors) { %>
    <div class="error"><%= err %></div>
    <% } %>
</div>
<% } %>

<!-- Display single error if any -->
<% if (error != null) { %>
<div class="error-messages">
    <div class="error"><%= error %></div>
</div>
<% } %>

<form action="<%= contextPath %>/staff" method="post">
  <input type="hidden" name="action" value="<%= edit ? "edit" : "add" %>"/>
  <% if (edit) { %>
  <input type="hidden" name="staffId" value="<%= s.getStaffId() %>"/>
  <% } %>

  <label>Name</label><br/>
  <input type="text" name="name"
         value="<%= edit ? s.getName() : request.getParameter("name")==null ? "" : request.getParameter("name") %>" required/><br/>

  <label>Email</label><br/>
  <input type="email" name="email"
         value="<%= edit ? s.getEmail() : request.getParameter("email")==null ? "" : request.getParameter("email") %>" required/><br/>

  <label>Position</label><br/>
  <input type="text" name="position"
         value="<%= edit ? s.getPosition() : request.getParameter("position")==null ? "" : request.getParameter("position") %>" required/><br/>

  <label>Address</label><br/>
  <input type="text" name="address"
         value="<%= edit ? s.getAddress() : request.getParameter("address")==null ? "" : request.getParameter("address") %>" required/><br/>

  <label>Status</label><br/>
  <label>
    <input type="radio" name="status" value="ACTIVE"
            <%= (!edit || "ACTIVE".equals(s.getStatus())) ? "checked" : "" %>/>
    Active
  </label>
  <label>
    <input type="radio" name="status" value="INACTIVE"
            <%= (edit && "INACTIVE".equals(s.getStatus())) ? "checked" : "" %>/>
    Inactive
  </label>
  <br/><br/>

  <button type="submit" class="btn"><%= edit ? "Update" : "Add" %> Staff</button>
  <a href="<%= contextPath %>/staff?action=list" class="btn">Cancel</a>
</form>
</body>
</html>
