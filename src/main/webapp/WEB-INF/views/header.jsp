<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
    <nav>
            <sec:authentication var="user" property="principal"/>
            <sec:authorize access="hasRole('ROLE_USER') and !hasRole('ROLE_ADMIN') and isAuthenticated()">
                    <button>Witaj ${user.name} ${user.surname}</button> |
                <a href="/user">Menu</a> |
                <a href="/logout">Log out</a> |
            </sec:authorize>
            
            <sec:authorize access="hasRole('ROLE_ADMIN') and isAuthenticated()">
                    <button>Witaj ${user.name} ${user.surname}</button> |
                <a href="/user">Menu</a> |
                <a href="/admin">Administrator panel</a> |
                <a href="/logout">Log out</a> |
            </sec:authorize>

            <sec:authorize access="!isAuthenticated()">
                <a href="/login">Log in</a> |
                <a href="/register">Create account</a> |
            </sec:authorize>

     <a href="/#">Start</a>
    </nav>