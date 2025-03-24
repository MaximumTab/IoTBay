<!DOCTYPE html>
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
            background-color: #f2f2f2;
            padding: 10px 20px;
        }
        .logo {
            font-size: 1.5em;
            font-weight: bold;
        }
        nav a {
            margin: 0 10px;
            text-decoration: none;
            color: #333;
        }
        nav a:hover {
            text-decoration: underline;
        }


        .category-bar {
            display: flex;
            justify-content: space-around;
            background-color: #ddd;
            padding: 10px 0;
        }
        .category-bar a {
            text-decoration: none;
            color: #333;
        }
        .category-bar a:hover {
            text-decoration: underline;
        }


        .main-container {
            display: grid;
            grid-template-columns: 200px 1fr;
            gap: 20px;
            padding: 20px;
        }

        .subcategories {
            list-style-type: none;
        }
        .subcategories li {
            margin-bottom: 10px;
            cursor: pointer;
        }
        .subcategories li:hover {
            text-decoration: underline;
        }

        .products {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
            gap: 30px;
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
    <div class="logo">IoTBay</div>
    <nav>
        <a href="#">Home</a>
        <a href="#">Account</a>
        <a href="#">My Cart</a>
    </nav>
</header>

<!-- Categories Navbar -->
<div class="category-bar">
    <a href="#">Category 1</a>
    <a href="#">Category 2</a>
    <a href="#">Category 3</a>
    <a href="#">Category 4</a>
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
            <img src="product-image.png" alt="Product image">
            <div class="product-name">Product Name</div>
            <div class="product-price">$XXX</div>
        </div>
        <div class="product">
            <img src="product-image.png" alt="Product image">
            <div class="product-name">Product Name</div>
            <div class="product-price">$XXX</div>
        </div>
        <div class="product">
            <img src="product-image.png" alt="Product image">
            <div class="product-name">Product Name</div>
            <div class="product-price">$XXX</div>
        </div>
        <div class="product">
            <img src="product-image.png" alt="Product image">
            <div class="product-name">Product Name</div>
            <div class="product-price">$XXX</div>
        </div>
        <div class="product">
            <img src="product-image.png" alt="Product image">
            <div class="product-name">Product Name</div>
            <div class="product-price">$XXX</div>
        </div>
        <div class="product">
            <img src="product-image.png" alt="Product image">
            <div class="product-name">Product Name</div>
            <div class="product-price">$XXX</div>
        </div>
        <div class="product">
            <img src="product-image.png" alt="Product image">
            <div class="product-name">Product Name</div>
            <div class="product-price">$XXX</div>
        </div>
        <div class="product">
            <img src="product-image.png" alt="Product image">
            <div class="product-name">Product Name</div>
            <div class="product-price">$XXX</div>
        </div>
        <div class="product">
            <img src="product-image.png" alt="Product image">
            <div class="product-name">Product Name</div>
            <div class="product-price">$XXX</div>
        </div>
    </div>
</div>

<!-- Footer -->
<footer>
    Footer
</footer>

</body>
</html>
