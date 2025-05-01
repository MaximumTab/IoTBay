<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.iotbay.model.Customer"%>
<%@ page session="true" %>
<jsp:useBean id="customer" class="com.iotbay.model.Customer" scope="session"/>
<%
    String email = request.getParameter("email");
    String password = request.getParameter("password");
    String errorMessage = "";

    if (email != null && password != null)
    {
        customer.setEmail(email);
        customer.setPassword(password);

        if (customer.authenticateUser(email,password))
        {
            session.setAttribute("customer", customer);
            Customer.addOnlineUser(customer);
            //System.out.println(Customer.getNumOnlineUsers());
            response.sendRedirect("WelcomePage.jsp");

            return;
        }
        else
        {
            errorMessage = "Email or password don't match!";
        }
    }
%>
