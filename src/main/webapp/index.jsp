<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Система управления студентами и их успеваемостью</title>
    <link rel="stylesheet" href="/resources/css/style.css">
</head>
<body>
<div class="titleDiv">
    <span>Система управления студентами и их успеваемостью</span>
</div>
<header>
    <nav>

        <div class="navigation">
            <ul>
                <li class="block">
                    <a href="/students" class="buttonRed">Студенты</a></li>
                <li class="block">
                    <a href="/disciplines" class="buttonRed">Дисциплины</a></li>
                <li class="block">
                    <a href="/terms" class="buttonRed">Семестры</a></li>
                <li class="block login">
                    <c:choose>
                        <c:when test="${isLogin eq 1}">
                            <a href="/logout" class="buttonLogout"><span>${login}, logout</span></a>
                        </c:when>
                        <c:otherwise>
                            <a href="/login" class="buttonLogin"><span>Login</span></a>
                        </c:otherwise>
                    </c:choose>
                </li>
                <li class="block">
                    <a href="/timetable" class="buttonRed">Расписание</a>
                </li>
                <li class="block">
                    <a href="/progress-for-teacher" class="buttonRed">Электронный журнал</a>
                </li>
            </ul>

            <c:if test="${role eq 1}">
                <ul class="adminButtons">
                    <li class="block1"><a href="/select-role" class="buttonRed">Создать роль</a></li>
                </ul>
            </c:if>

        </div>
    </nav>
</header>
</body>
</html>
