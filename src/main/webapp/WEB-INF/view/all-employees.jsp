<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: pavel123
  Date: 18.01.2022
  Time: 15:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List</title>
</head>
<body>
<h2>Все работники</h2>
<br>
<table>
    <tr>
        <th>Name</th>
        <th>Surname</th>
        <th>Department</th>
        <security:authorize access="hasRole('HR')">
        <th>Salary</th>
        <th>Operations</th>
        </security:authorize>
    </tr>

    <c:forEach var="emp" items="${allEmps}">

        <c:url var="updateButton" value="/crud/updateInfo">
            <c:param name = "empId" value="${emp.id}"/>
        </c:url>

        <c:url var="deleteButton" value="/crud//deleteEmp">
            <c:param name = "empId" value="${emp.id}"/>
        </c:url>

        <tr>
            <td>${emp.name}</td>
            <td>${emp.surname}</td>
            <td>${emp.department}</td>
            <security:authorize access="hasRole('HR')">
            <td>${emp.salary}</td>
            <td>
                <input type="button" value="Обновить"
                       onclick="window.location.href = '${updateButton}'"/>
                <input type="button" value="Удалить"
                       onclick="window.location.href = '${deleteButton}'"/>
            </td>
            </security:authorize>
        </tr>

    </c:forEach>

</table>

<br><br>

<security:authorize access="hasRole('HR')">
<input type="button" value="ADD"
       onclick="window.location.href = '/crud/addNewEmployee'"/>
</security:authorize>
</body>
</html>
