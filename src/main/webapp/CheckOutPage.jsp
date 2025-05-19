<%@ page import="com.iotbay.model.dao.DAO" %>
<%@ page import="com.iotbay.model.CreditCards" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>


<%
    DAO db = (DAO)session.getAttribute("db");
    if (db == null) {
        db = new DAO(); // or however your DAO is constructed
        session.setAttribute("db", db);
    }
%>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Checkout</title>
    <style>
        body {
            background-color: #f3f4f6;
            font-family: Arial, sans-serif;
            padding: 40px;
            margin: 0;
        }

        .checkout-container {
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

        input[type="number"],
        input[type="text"]{
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

        /* Saved Payment Section */
        details {
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

        .saved-card {
            padding: 10px 0;
            border-bottom: 1px solid #e5e7eb;
            font-size: 0.95rem;
            color: #444;
        }

        .saved-card:last-child {
            border-bottom: none;
        }
    </style>
</head>
<body>
<div class="checkout-container">
    <h1>Checkout</h1>
    <h2>$999.99</h2>
    <!-- Saved Payment Dropdown -->
    <details>
        <summary>View saved payment methods</summary>
        <%
            List<CreditCards> savedCards = db.CreditCards().getAllCards();
            System.out.println("Cards retrieved: " + (savedCards != null ? savedCards.size() : "null"));

            if (savedCards != null && !savedCards.isEmpty()) {
                for (CreditCards card : savedCards) {
        %>
        <div class="saved-card">
            **** **** **** <%= card.getCreditCardNumber().substring(card.getCreditCardNumber().length() - 4) %>

            <form method="POST" action="DeleteCardServlet" style="display:inline;">
                <input type="hidden" name="card_id" value="<%= card.getCardId() %>" />
                <button type="submit">Delete</button>
            </form>
            <form method="POST" action="SetCardServlet" style="display:inline;">
                <input type="hidden" name="card_id" value="<%= card.getCardId() %>" />
                <button type="submit">Edit</button>
            </form>
            <form method="GET" action="MainPage.jsp" style="display:inline;">
                <input type="hidden" name="card_id" value="<%= card.getCardId() %>" />
                <button type="submit">Use Card</button>
            </form>
        </div>
        <%
            }
        } else {
        %>
        <div class="saved-card">No saved cards available.</div>
        <%
            }
        %>
    </details>

    <!-- Payment Form -->
    <form method="POST" action="SaveCardServlet">
        <section>
            <h2>Payment Details</h2>

            <input type="number" name="card_number" placeholder="Card Number" min="1000000000000000" max="9999999999999999" required />
            <input type="number" name="ccv" placeholder="CCV" min="100" max="999" required />
            <input type="number" name="bsb" placeholder="BSB" min="10000000" max="99999999" required />

            <label>
                <input type="checkbox" name="save_payment" />
                Save payment details for future use
            </label>
        </section>

        <button type="submit">Complete Purchase</button>
    </form>
</div>
</body>
</html>