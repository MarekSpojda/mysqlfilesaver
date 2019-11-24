<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>MySqlFileSaver - Register</title>
    
    <script src="<c:url value="resources/js/jquery-3.4.1.js"/>"></script>
</head>
<body>
<%@ include file="header.jsp" %> 
        <h2>Załóż konto</h2>
        <form:form action="/register" method="post" modelAttribute="userDTO">
            <div>
                <input type="text" name="name" placeholder="Imię"/>
            </div>
            <div>
                <input type="text" name="surname" placeholder="Nazwisko"/>
            </div>
            <div>
                <input type="email" name="email" placeholder="Email"/>
            </div>
            <div>
                <input type="password" name="password" placeholder="Hasło"/>
            </div>
            <div>
                <input type="password" name="password2" placeholder="Powtórz hasło"/>
            </div>

            <div>
                <button type="submit">Załóż konto</button>
            </div>
        </form:form>
</body>
</html>