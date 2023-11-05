<%@ page import="java.util.List" %>
<%@ page import="vn.edu.iuh.fit.wwwlab02.services.CustomerService" %>
<%@ page import="vn.edu.iuh.fit.wwwlab02.entities.Customer" %><%--
  Created by IntelliJ IDEA.
  User: Student
  Date: 9/23/2023
  Time: 10:11 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Customer List</title>
</head>
<body>
<%
    CustomerService services =new CustomerService();

    List<Customer> lst = services.getAll();

//        List<Customer> lst1 = session.getAttribute("customers");
%>
<table width="80%" align="center">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Address</th>
        <th>Phone</th>
        <th>Email</th>
    </tr>
    <% for(Customer customer:lst){%>
    <tr>
        <td><%=customer.getId()%></td>
        <td><%=customer.getName()%></td>
        <td><%=customer.getAddress()%></td>
        <td><%=customer.getPhone()%></td>
        <td><%=customer.getEmail()%></td>
        <td>
            <a href="">Update</a>
            <a href="">Delete</a>
        </td>
    </tr>
    <%}%>
</table>
</body>
</html>