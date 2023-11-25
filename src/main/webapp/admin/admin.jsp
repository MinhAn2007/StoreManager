<!DOCTYPE html>
<html>
<head>
    <title>ADMIN</title>
    <style>
        ul.menu {
            list-style: none;
            padding: 0;
            background-color: #333;
            width: 40px;
        }

        ul.menu li {
            display: inline;
            margin-right: 20px;
            position: relative;
        }

        ul.menu li a {
            text-decoration: none;
            color: #fff;
            width: 100px;
        }

        ul.menu li a:hover {
            text-decoration: underline;
        }

        /* Hiển thị danh sách con khi di chuột qua */
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
        }

        ul.submenu li {
            margin-right: 0;
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
<a href="http://localhost:8080/">HOME</a>
<ul class="menu">
    <li>
        <a href="#">Statistics</a>
        <ul class="submenu">
            <li><a href="statistic/byDate.html">List Customer</a></li>
            <li><a href="statistic/byRange.html">List Customer</a></li>
        </ul>
    </li>
</ul></body>
</html>
