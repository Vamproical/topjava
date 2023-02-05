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
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Edit meal</h2>
<form method="post" action="meals" enctype="application/x-www-form-urlencoded">
    <input type="hidden" name="id" value="${meal.id}"/>
    <label for="dateTime">DateTime:</label>
    <input id="dateTime" type="datetime-local" name="dateTime" value="${meal.dateTime}" required/><br/>
    <label for="description">Description:</label>
    <input id="description" type="text" name="description" value="${meal.description}" required/><br/>
    <label for="calories">Calories</label>
    <input id="calories" type="number" name="calories" value="${meal.calories}" required/><br/>
    <button type="submit">Submit</button>
    <button type="button" onclick="window.history.back()">Cancel</button>
</form>
</body>
</html>