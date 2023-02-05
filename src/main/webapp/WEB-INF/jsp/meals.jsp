<!DOCTYPE HTML>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/styles.css">
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<p><a href="meals?action=add">Add User</a></p>
<table>
    <thead>
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
        <th colspan=2>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="meal" items="${meals}">
        <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.MealTo"/>
        <fmt:parseDate type="both" value="${meal.dateTime}" var="parsedDateTime" pattern="yyyy-MM-dd'T'HH:mm"/>
        <fmt:formatDate pattern="dd-MM-yyyy HH:mm" value="${parsedDateTime}" var="date"/>
        <tr style="color: ${meal.excess ? 'red' : 'green'}">
            <td>${date}</td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
            <td><a href="meals?id=${meal.id}&action=edit">Update</a></td>
            <td><a href="meals?id=${meal.id}&action=delete">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>