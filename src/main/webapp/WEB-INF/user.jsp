<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<spring:form action="/addBook" method="post" modelAttribute="book">
    <spring:input type="text" path="title">
    </spring:input>

    <spring:textarea path="text">
    </spring:textarea>

    <spring:input path="user">
    </spring:input>
</spring:form>

</body>
</html>
