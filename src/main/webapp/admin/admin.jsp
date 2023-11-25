<!DOCTYPE html>
<html>
<head>
    <title>ADMIN</title>
    <style>
        body {
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 100vh;
            margin: 0;
        }

        ul.menu {
            list-style: none;
            padding: 0;
            background-color: #333;
            display: flex;
            margin-bottom: 20px;
        }

        ul.menu li {
            display: inline-block;
            margin-right: 20px;
            position: relative;
        }

        ul.menu li a {
            text-decoration: none;
            color: #fff;
            padding: 15px;
            display: block;
            transition: all 0.3s ease;
        }

        ul.menu li a:hover {
            text-decoration: underline;
            background-color: #555;
        }

        ul.menu li:hover ul.submenu {
            display: block;
        }

        ul.submenu {
            display: none;
            position: absolute;
            background-color: #333;
            padding: 10px;
            list-style: none;
            border: 1px solid #fff;
            width: 120px;
        }

        ul.submenu li {
            margin-right: 0;
        }

        ul.submenu li a {
            padding: 8px;
        }

        a.home-link {
            text-decoration: none;
            color: #fff;
            padding: 15px;
            display: block;
            transition: all 0.3s ease;
        }

        a.home-link:hover {
            text-decoration: underline;
            background-color: #555;
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
        </ul>
    </li>
</ul>
</body>
</html>
