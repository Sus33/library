<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
Add user
<spring:form action="/addAuthor" modelAttribute="user" method="post" enctype="multipart/form-data">
    <spring:input path="name"></spring:input>
    <spring:input path="surname"></spring:input>
    <spring:input path="age"></spring:input>
    <input type="file" name="image">
    <input type="submit" value="add">
</spring:form>
<a href="/authorHome">Author Home</a>
</body>
</html>
