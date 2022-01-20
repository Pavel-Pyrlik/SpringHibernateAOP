<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: pavel123
  Date: 18.01.2022
  Time: 15:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New Employee</title>
</head>
<body>
<form:form action="saveEmployee" modelAttribute="newEmployee">

    <form:hidden path="id"/>

    Имя <form:input path="name"/>
    <form:errors path="name"/>
    <br><br>
    Фамилия <form:input path="surname"/>
    <form:errors path="surname"/>
    <br><br>
    Департамент <form:input path="department"/>
    <form:errors path="department"/>
    <br><br>
    Зарплата <form:input path="salary"/>
    <form:errors path="salary"/>
    <br><br>
    <input type="submit" value="OK">
</form:form>
</body>
</html>
