
<%@ page import="com.iotbay.model.User" %>
<%@ page import="com.iotbay.model.dao.DAO" %>
<%@ page import="com.iotbay.model.CreditCards" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>


<%--<%--%>
<%--    DAO db = (DAO)session.getAttribute("db");--%>
<%--    if (request.getParameter("save_payment") != null) {--%>
<%--        String cardNumber = request.getParameter("card_number");--%>
<%--        String ccv = request.getParameter("ccv");--%>
<%--        String bsb = request.getParameter("bsb");--%>
<%--        CreditCards newCard = new CreditCards(cardNumber,ccv,bsb);--%>
<%--        db.CreditCards().add(newCard);--%>
<%--}--%>
<%--%>--%>

<html>
<head>
    <meta charset="UTF-8">
    <title>IoTBay</title>
    <style>

        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        body {
            font-family: Arial, sans-serif;
        }

        header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            background-color: #e3e3e3;
            padding: 10px 20px;
        }
        .logo {
            display: block;
            width: 100px;
            height: auto;
            margin-left: 20px;
        }

        .logo img {
            width: 200px;
            height: auto;
        }


        nav a {
            margin: 0 10px;
            text-decoration: none;
            color: #333;
            font-weight: bold;
        }

        }
        nav a:hover {
            text-decoration: underline;
        }


        .category-bar {
            display: flex;
            justify-content: space-around;
            background-color: #9fc0e8;
            padding: 10px 0;


        }
        .category-bar a {
            text-decoration: none;
            font-weight: bold;
            font-size: 20px;
            color: #333;
        }
        .category-bar a:hover {
            text-decoration: underline;
        }


        .main-container {
            display: grid;
            grid-template-columns: 200px 1fr;
            margin-top: 50px
        ;

        }

        .subcategories {
            list-style-type: none;
            margin-left: 20px;
            background-color: FFFFFF;
        }
        .subcategories li {
            margin-bottom: 30px;
            cursor: pointer;
            font-weight: bold;
        }
        .subcategories li:hover {
            text-decoration: underline;
        }

        .products {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
            gap: 40px;
            background-color: FFFFFF;
            margin-right: 20px;
        }
        .product {
            border: 1px solid #ccc;
            padding: 10px;
            text-align: center;
        }
        .product img {
            max-width: 100%;
            height: auto;
        }
        .product-name {
            margin-top: 10px;
            font-weight: bold;
        }
        .product-price {
            color: #888;
        }

        footer {
            text-align: left;
            padding: 20px;
            background-color: #f2f2f2;
            margin-top: 20px;
        }
    </style>
</head>
<body>

<!-- Header -->

<header>
    <div class="logo">
        <img src="images/iotbay.png" alt="logo"></div>
    <nav>
        <a href="wip.jsp">Account</a>
        <a href="LogoutPage.jsp">Log Out</a>
        <a href="wip.jsp">My Cart</a>
        <a href="PaymentHistory.jsp">Payment History</a>
    </nav>
</header>

<!-- Categories Navbar -->
<div class="category-bar">
    <a href="wip.jsp">Category 1</a>
    <a href="wip.jsp">Category 2</a>
    <a href="wip.jsp">Category 3</a>
    <a href="wip.jsp">Category 4</a>
</div>

<!-- Main Content -->
<div class="main-container">

    <!-- Subcategories -->
    <ul class="subcategories">
        <li>Sub Category 1</li>
        <li>Sub Category 2</li>
        <li>Sub Category 3</li>
        <li>Sub Category 4</li>
        <li>Sub Category 5</li>
    </ul>

    <!-- Products Grid -->

    <div class="products">

        <div class="product">
            <img src="images/product-image.webp" alt="com.iotbay.model.Devices image">
            <div class="product-name">com.iotbay.model.Devices Name</div>
            <div class="product-price">$999.99</div>
            <a href="CheckOutPage.jsp">Purchase</a>
        </div>
        <div class="product">
            <img src="images/product-image.webp" alt="com.iotbay.model.Devices image">
            <div class="product-name">com.iotbay.model.Devices Name</div>
            <div class="product-price">$999.99</div>
        </div>
        <div class="product">
            <img src="images/product-image.webp" alt="com.iotbay.model.Devices image">
            <div class="product-name">com.iotbay.model.Devices Name</div>
            <div class="product-price">$999.99</div>
        </div>
        <div class="product">
            <img src="images/product-image.webp" alt="com.iotbay.model.Devices image">
            <div class="product-name">com.iotbay.model.Devices Name</div>
            <div class="product-price">$X999.99</div>
        </div>
        <div class="product">
            <img src="images/product-image.webp" alt="com.iotbay.model.Devices image">
            <div class="product-name">com.iotbay.model.Devices Name</div>
            <div class="product-price">$999.99</div>
        </div>
        <div class="product">
            <img src="images/product-image.webp" alt="com.iotbay.model.Devices image">
            <div class="product-name">com.iotbay.model.Devices Name</div>
            <div class="product-price">$999.99</div>
        </div>
        <div class="product">
            <img src="images/product-image.webp" alt="com.iotbay.model.Devices image">
            <div class="product-name">com.iotbay.model.Devices Name</div>
            <div class="product-price">$999.99</div>
        </div>
        <div class="product">
            <img src="images/product-image.webp" alt="com.iotbay.model.Devices image">
            <div class="product-name">com.iotbay.model.Devices Name</div>
            <div class="product-price">$999.99</div>
        </div>
        <div class="product">
            <img src="images/product-image.webp" alt="com.iotbay.model.Devices image">
            <div class="product-name">com.iotbay.model.Devices Name</div>
            <div class="product-price">$999.99</div>
        </div>
    </div>
</div>

<!-- Footer -->

<footer>

    <p style="padding-top: 20px">
        There are currently <%= Customer.getNumOnlineUsers() %> Online users!

        There are currently ---- Online users!
    </p>
    <br>
    <p>© 2025 IoTBay - All rights reserved.</p>
    <br>
    <nav>
        <a href="index.jsp">Index</a>
        <a href="LoginPage.jsp">Login</a>
        <a href="RegisterPage.jsp">Register</a>
        <a href="LogoutPage.jsp">Logout</a>
        <a href="WelcomePage.jsp">Welcome</a>
        <a href="Terms.jsp">Terms & Conditions</a>
    </nav>

</footer>

</body>
</html>
