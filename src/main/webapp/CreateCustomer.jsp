<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Create Customer</title>
  <style>
    body {
      font-family: Arial;
      background: #f4f4f4;
      padding: 20px;
    }
    h2 {
      text-align: center;
    }
    form {
      max-width: 500px;
      margin: auto;
      background: white;
      padding: 20px;
      border-radius: 5px;
      box-shadow: 0 0 10px rgba(0,0,0,0.1);
    }
    input {
      width: 100%;
      padding: 10px;
      margin: 10px 0;
    }
    button {
      background: #405ACD;
      color: white;
      border: none;
      padding: 10px;
      width: 100%;
      font-size: 16px;
      margin-top: 10px;
    }
    .back-button {
      background: #aaa;
    }
  </style>
</head>
<body>
<h2>Create New Customer</h2>
<form action="CreateCustomerServlet" method="post">
  <label>Full Name:</label>
  <input type="text" name="full_name" required pattern="[A-Za-z\s]+" title="Full name should contain only letters and spaces"><br>

  <label>Email:</label>
  <input type="email" name="email" required title="Please enter a valid email address"><br>

  <label>Password:</label>
  <input type="text" name="password_hash" required><br>

  <label>Phone:</label>
  <input type="text" name="phone" required pattern="[0-9]{7,15}" title="Phone number should contain only digits (7-15 digits)"><br>

  <label>Address:</label>
  <input type="text" name="address"><br>

  <!-- User type fixed as customer -->
  <input type="hidden" name="user_type" value="customer">

  <button type="submit">Create</button>
  <a href="SystemAdmin.jsp"><button type="button">Cancel</button></a>
</form>
</body>
</html>