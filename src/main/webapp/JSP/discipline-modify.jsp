<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Изменение дисциплины</title>
    <link rel="stylesheet" href="../resources/css/style.css">

</head>
<body>
<header>
    <div class="titleDiv">
        <span>Система управления студентами и их успеваемостью</span>
    </div>
    <nav>
        <div class="navigation">
            <ul>
                <li class="block">
                    <a href="/" class="buttonRed">На главную</a></li>
                </li>
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
            </ul>
        </div>
    </nav>
</header>
<div class="stModform">
    <form method="post" action="/discipline-modify">
    <span>Для изменения семестра, введите новые значения и нажмите кнопку "Применить"</span><br>
        <input type="hidden" value="${discipline.id}" name="idDisc">
    <label for="${discipline.id}">Название: <input type="text" name="discipline" value="${discipline.discipline}" id="${discipline.id}">
    </label>
    <br>
    <button type="submit" class="btn btn1">Применить</button>
    </form>
</div>
</body>
</html>
