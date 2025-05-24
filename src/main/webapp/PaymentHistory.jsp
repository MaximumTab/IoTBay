<%@ page import="com.iotbay.model.PaymentHistory" %>
<%@ page import="com.iotbay.model.dao.DAO" %>
<%@ page import="com.iotbay.model.CreditCards" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>

<%
    DAO db = (DAO)session.getAttribute("db");
    if (db == null) {
        db = new DAO();
        session.setAttribute("db", db);
    }
%>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Payment History</title>
    <style>
        body {
            background-color: #f3f4f6;
            font-family: Arial, sans-serif;
            padding: 40px;
            margin: 0;
        }

        .History-container {
            max-width: 600px;
            margin: 0 auto;
            background: #ffffff;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
        }

        h1 {
            font-size: 2rem;
            text-align: center;
            color: #333;
            margin-bottom: 30px;
        }

        h2 {
            font-size: 1.2rem;
            margin-bottom: 15px;
            color: #444;
        }

        input[type="text"],
        input[type="number"]{
            width: 100%;
            padding: 12px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 6px;
            font-size: 1rem;
            box-sizing: border-box;
        }

        label {
            display: flex;
            align-items: center;
            font-size: 0.95rem;
            color: #555;
            margin-bottom: 20px;
        }

        input[type="checkbox"] {
            margin-right: 10px;
            width: 16px;
            height: 16px;
        }

        button {
            width: 100%;
            background-color: #2563eb;
            color: white;
            padding: 14px;
            font-size: 1rem;
            font-weight: bold;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        button:hover {
            background-color: #1d4ed8;
        }

        /* Saved history Section */
        .receipt-details {
            margin-bottom: 25px;
            background-color: #f9fafb;
            padding: 15px;
            border: 1px solid #d1d5db;
            border-radius: 8px;
        }

        summary {
            cursor: pointer;
            font-size: 1rem;
            font-weight: bold;
            color: #1f2937;
            outline: none;
        }

        .saved-receipt {
            padding: 10px 0;
            border-bottom: 1px solid #e5e7eb;
            font-size: 0.95rem;
            color: #444;
        }

        .saved-receipt:last-child {
            border-bottom: none;
        }
    </style>
</head>
<body>

<form method="GET" action="PaymentHistory.jsp">
    <input type="text" name="searchCard" placeholder="Enter last 4 digits of card number" />
    <button type="submit">Search</button>
</form>

<div class = History-container>
<div class = receipt-details>
<%
    String searchCard = request.getParameter("searchCard");
    List<PaymentHistory> receipts = db.PaymentHistory().getAllReceipts();
    System.out.println("Receipts retrieved: " + (receipts != null ? receipts.size() : "null"));

    boolean found = false;
    if (receipts != null && !receipts.isEmpty()) {
        for (PaymentHistory receipt : receipts) {
            String cardLast4 = receipt.getPaymentCardNumber().substring(receipt.getPaymentCardNumber().length() - 4);
            if (searchCard == null || searchCard.isEmpty() || cardLast4.equals(searchCard)) {
                found = true;
%>
<div class="saved-receipt">
    <div><strong>Card:</strong> **** **** **** <%= receipt.getPaymentCardNumber().substring(receipt.getPaymentCardNumber().length() - 4) %></div>
    <div><strong>Amount:</strong> $<%= receipt.getPaymentAmount() %></div>
    <div><strong>Date:</strong> <%= receipt.getPaymentDate() %></div>
    <div><strong>Receipt ID:</strong> <%= receipt.getPaymentId() %></div>
</div>
<%
    }
    }
    if (!found && searchCard != null && !searchCard.isEmpty()) {
%>
    <div class="saved-receipt">No receipts found for card ending in <%= searchCard %>.</div>
    <%
        }
} else {
%>
<div class="saved-receipt">No receipts available.</div>
<%
    }
%>


</div>
    <form method="POST" action="MainPage.jsp">
        <button type="submit">Return to MainPage</button>

    </form>
    </div>
</body>
</html>