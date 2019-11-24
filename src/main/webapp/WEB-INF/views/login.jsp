<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>MySql FileSaver - Login</title>
    
    <script src="<c:url value="resources/js/jquery-3.4.1.js"/>"></script>
</head>
<body>
<%@ include file="header.jsp" %> 
<h2>Zaloguj się</h2>
        <form action="/login" method="post">
            <div>
                <input type="email" name="username" placeholder="Email"/>
            </div>
            <div>
                <input type="password" name="password" placeholder="Hasło"/>
            </div>

            <div>
                <button type="submit">Zaloguj się</button>
            </div>
        </form>
</body>
</html>