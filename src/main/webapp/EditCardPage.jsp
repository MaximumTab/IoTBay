<%@ page import="com.iotbay.model.CreditCards" %>
<%
    CreditCards card = (CreditCards) session.getAttribute("card");
%>

<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Edit Card</title>
    <style>
        body {
            background-color: #f3f4f6;
            font-family: Arial, sans-serif;
            padding: 40px;
            margin: 0;
        }

        .Edit-container {
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
<div class = Edit-container>
<form method="POST" action="EditCardServlet">
    <section>
        <h2>Payment Details</h2>

        <input  id="card_number" type="text" name="card_number" placeholder="Card Number" value="<%=card.getCreditCardNumber()%>" required />
        <input id="ccv" type="number" name="ccv" placeholder="CCV" value="<%=card.getCcv()%>" min="100" max="999" required />
        <input id="bsb" type="text" name="bsb" placeholder="BSB" value="<%=card.getBsb()%>" required />

    </section>

    <button type="submit">Finalise edit</button>
</form>
</div>
</body>
</html>
</html>
