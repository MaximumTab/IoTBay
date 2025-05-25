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
  <!-- load the CSS from the staff folder -->
  <link
          rel="stylesheet"
          type="text/css"
          href="<%= contextPath %>/staff/style.css"
  />
</head>


<body>
<nav>
  <a href="<%= contextPath %>/index.jsp">Home</a>
  <a href="<%= contextPath %>/staff?action=list">Staff</a>
</nav>

<h2>Staff List <a class="btn" href="<%= contextPath %>/staff?action=addForm">+ Add</a></h2>

<!-- Display error if any -->
<% if (error != null) { %>
<div class="error-messages">
    <div class="error"><%= error %></div>
</div>
<% } %>

<form action="<%= contextPath %>/staff" method="get">
  <input type="hidden" name="action" value="list"/>
  Name: <input name="name" value="<%= request.getParameter("name")==null?"":request.getParameter("name") %>"/>
  Position: <input name="position" value="<%= request.getParameter("position")==null?"":request.getParameter("position") %>"/>
  <button class="btn" type="submit">Search</button>
</form>

<table>
  <tr>
    <th>Name</th><th>Email</th><th>Position</th><th>Status</th><th>Actions</th>
  </tr>
  <%
    List<Staff> list = (List<Staff>)request.getAttribute("staffList");
    if (list != null) {
      for (Staff s : list) {
  %>
  <tr>
    <td><%=s.getName()%></td>
    <td><%=s.getEmail()%></td>
    <td><%=s.getPosition()%></td>
    <td><%=s.getStatus()%></td>
    <td>
      <a class="action" href="<%= contextPath %>/staff?action=editForm&id=<%=s.getStaffId()%>">Edit</a>
      <a class="action" href="<%= contextPath %>/staff?action=delete&id=<%=s.getStaffId()%>" onclick="return confirm('Delete?');">Delete</a>
      <a class="action" href="<%= contextPath %>/staff?action=toggle&id=<%=s.getStaffId()%>">
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
