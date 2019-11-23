<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
    <nav>
            <sec:authentication var="user" property="principal"/>
            <sec:authorize access="hasRole('ROLE_USER') and isAuthenticated()">
                    <button>Witaj ${user.name} ${user.surname}</button> |
                <a href="/logged">Menu</a> |
                <a href="/logout">Wyloguj</a> |
            </sec:authorize>

            <sec:authorize access="!isAuthenticated()">
                <a href="/login">Zaloguj</a> |
                <a href="/register">Załóż konto</a> |
            </sec:authorize>

     <a href="/#">Start</a>
    </nav>