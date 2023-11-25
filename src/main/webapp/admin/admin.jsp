<!DOCTYPE html>
<html>
<head>
    <title>ADMIN</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }

        .menu {
            list-style-type: none;
            margin: 0;
            padding: 0;
        }

        .menu li {
            display: inline-block;
            position: relative;
        }

        .menu a {
            display: block;
            padding: 10px 15px;
            text-decoration: none;
            color: #333;
        }

        .menu ul.submenu {
            display: none;
            position: absolute;
            top: 100%;
            left: 0;
            background-color: #f9f9f9;
            border: 1px solid #ccc;
            z-index: 1;
        }

        .menu li:hover > ul.submenu {
            display: block;
        }

        .home-link {
            display: block;
            padding: 10px 15px;
            text-decoration: none;
            color: #333;
            background-color: #f0f0f0;
        }

        .menu a:hover,
        .home-link:hover {
            background-color: #ddd;
        }

    </style>
</head>
<body>
<ul class="menu">
    <li>
        <a href="#">LIST</a>
        <ul class="submenu">
            <li><a href="cus/CustomerListing.html">List Customer</a></li>
            <li><a href="emp/EmployeeListing.html">List Employee</a></li>
            <li><a href="product/productListing.html">List Product</a></li>
            <li><a href="order/ordersListing.html">List Orders</a></li>
        </ul>
    </li>
</ul>
<a href="http://localhost:8080/" class="home-link">HOME</a>
<ul class="menu">
    <li>
        <a href="#">Statistics</a>
        <ul class="submenu">
            <li><a href="statistic/byDate.html">By Date</a></li>
            <li><a href="statistic/byRange.html">By Range</a></li>
            <li><a href="statistic/byEmp.html">By Emp</a></li>
        </ul>
    </li>
</ul>
</body>
</html>
