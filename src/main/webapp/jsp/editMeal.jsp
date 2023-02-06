<!DOCTYPE HTML>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Meals</title>
</head>
<body>
<jsp:useBean id="meal" type="ru.javawebinar.topjava.model.Meal" scope="request"/>
<h3><a href="index.html">Home</a></h3>
<hr>
<h3>${meal.id gt 0 ? "Edit meal" : "Add meal"}</h3>
<form method="post" action="meals" enctype="application/x-www-form-urlencoded">
    <input type="hidden" name="id" value="${meal.id}"/>
    <label for="dateTime">DateTime:</label>
    <fmt:parseDate type="both" value="${meal.dateTime}" var="parsedDateTime" pattern="yyyy-MM-dd'T'HH:mm"/>
    <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${parsedDateTime}" var="date"/>
    <input id="dateTime" type="datetime-local" name="dateTime" value="${date}"/><br/>
    <label for="description">Description:</label>
    <input id="description" type="text" name="description" value="${meal.description}" required/><br/>
    <label for="calories">Calories</label>
    <input id="calories" type="number" name="calories" value="${meal.calories}" required/><br/>
    <button type="submit">Submit</button>
    <button type="button" onclick="window.history.back()">Cancel</button>
</form>
</body>
</html>