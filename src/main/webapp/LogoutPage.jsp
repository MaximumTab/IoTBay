<%@ page import="com.iotbay.model.Customer" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    Customer customer = (Customer) session.getAttribute("customer");

    Customer.removeOnlineUser(customer);

    //System.out.println(Customer.getNumOnlineUsers());


    session.invalidate();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Logout - IoTBay</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
            background: #f5f6fa;
        }
        .logout-container {
            width: 400px;
            max-width: 90%;
            margin: 80px auto;
            background-color: #fff;
            border-radius: 8px;
            padding: 40px;
            box-shadow: 0 4px 15px rgba(0,0,0,0.1);
            text-align: center;
        }
        .logout-container h1 {
            margin-bottom: 20px;
            font-size: 2rem;
            font-weight: 600;
            color: #333;
        }
        .logout-container p {
            font-size: 1rem;
            margin-bottom: 30px;
            color: #666;
        }
        .logout-button {
            background-color: #007BFF;
            color: #fff;
            border: none;
            border-radius: 6px;
            padding: 12px 24px;
            font-size: 1rem;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        .logout-button:hover {
            background-color: #0056b3;
        }
        .logout-footer {
            margin-top: 20px;
            font-size: 0.9rem;
            color: #999;
        }
        .logout-footer a {
            color: #007BFF;
            text-decoration: none;
        }
        .logout-footer a:hover {
            text-decoration: underline;
        }

        /* Modal Styles */
        .modal {
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.4);
            display: flex;
            align-items: center;
            justify-content: center;
            z-index: 9999;
        }
        .modal-content {
            background-color: #fff;
            width: 350px;
            border-radius: 10px;
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.3);
            position: relative;
            cursor: move;
        }
        .modal-header {
            padding: 10px 15px;
            background-color: #007BFF;
            color: white;
            font-weight: bold;
            border-top-left-radius: 10px;
            border-top-right-radius: 10px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .modal-body {
            padding: 20px;
            text-align: center;
            color: #333;
        }
        .modal-footer {
            padding: 10px;
            text-align: center;
        }
        .close-btn {
            background: transparent;
            border: none;
            color: white;
            font-size: 20px;
            cursor: pointer;
        }
        .confirm-btn {
            background-color: #007BFF;
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            font-size: 1rem;
            cursor: pointer;
        }
        .confirm-btn:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>

<!-- Custom Modal -->
<div class="modal" id="customModal">
    <div class="modal-content" id="modalBox">
        <div class="modal-header" id="modalHeader">
            Notice from IoTBay
            <button class="close-btn" onclick="closeModal()">&times;</button>
        </div>
        <div class="modal-body">
            You have successfully logged out.
        </div>
        <div class="modal-footer">
            <button class="confirm-btn" onclick="closeModal()">Confirm</button>
        </div>
    </div>
</div>

<div class="logout-container">
    <h1>You have logged out</h1>
    <p>Thank you for visiting IoTBay! We hope to see you again soon.</p>
    <button class="logout-button" onclick="window.location.href='index.jsp'">
        Go Back
    </button>
    <div class="logout-footer">
        <p><a href="LoginPage.jsp">Go to Login</a></p>
    </div>
</div>

<script>
    // Close modal and redirect
    function closeModal() {
        document.getElementById("customModal").style.display = "none";
    }

    // Drag modal functionality
    const modal = document.getElementById("modalBox");
    const header = document.getElementById("modalHeader");
    let offsetX, offsetY;

    header.onmousedown = function(e) {
        offsetX = e.clientX - modal.offsetLeft;
        offsetY = e.clientY - modal.offsetTop;
        document.onmousemove = function(e) {
            modal.style.left = (e.clientX - offsetX) + 'px';
            modal.style.top = (e.clientY - offsetY) + 'px';
            modal.style.position = 'absolute';
        };
        document.onmouseup = function() {
            document.onmousemove = null;
            document.onmouseup = null;
        };
    };
</script>
</body>
</html>