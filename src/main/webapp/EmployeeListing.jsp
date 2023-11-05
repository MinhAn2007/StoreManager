<%@ page import="java.util.List" %>
<%@ page import="vn.edu.iuh.fit.wwwlab02.services.CustomerService" %>
<%@ page import="vn.edu.iuh.fit.wwwlab02.entities.Customer" %>
<%@ page import="vn.edu.iuh.fit.wwwlab02.services.EmployeeService" %>
<%@ page import="vn.edu.iuh.fit.wwwlab02.entities.Employee" %><%--
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
    EmployeeService services =new EmployeeService();

    List<Employee> lst = services.getAllEmployee();

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
    <% for(Employee employee:lst){%>
    <tr>
        <td><%=employee.getId()%></td>
        <td><%=employee.getFullname()%></td>
        <td><%=employee.getAddress()%></td>
        <td><%=employee.getPhone()%></td>
        <td><%=employee.getEmail()%></td>
        <td>
            <a href="">Update</a>
            <a href="">Delete</a>
        </td>
    </tr>
    <%}%>
</table>
</body>
</html>