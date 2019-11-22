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
    <title>MySqlFileSaver - register</title>
</head>
<body>

<header>
    <nav>
        <ul>
            <sec:authentication var="user" property="principal"/>
            <sec:authorize access="hasRole('ROLE_USER') and isAuthenticated()">
                <li>
                    <button>Witaj ${user.name} ${user.surname}</button>
                </li>
                <li><a href="/logged">Menu</a></li>
                <li><a href="/logout">Wyloguj</a></li>
            </sec:authorize>

            <sec:authorize access="!isAuthenticated()">
                <li><a href="/login">Zaloguj</a></li>
                <li><a href="/register">Załóż konto</a></li>
            </sec:authorize>
        </ul>

        <ul>
            <li><a href="/#">Start</a></li>
    </nav>

    <section>
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
                <a href="/login">Zaloguj się</a>
                <button type="submit">Załóż konto</button>
            </div>
        </form:form>
    </section>
</header>
</body>
</html>