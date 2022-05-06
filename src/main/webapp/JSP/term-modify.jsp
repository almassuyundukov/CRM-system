<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
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
                <li class="block login">
                    <c:choose>
                        <c:when test="${isLogin eq 1}">
                            <a href="/logout" class="buttonLogin"><span>${login}, logout</span></a>
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
<div action="" class="stModform">
    <span>Для изменения выбранного семестра, отредактируйте данные и нажмите кнопку "Применить"</span><br>
    <span>Группа: </span>
    <span><c:out value="${term.group}"/></span>

    <span>Семестр: </span>
    <span><c:out value="${term.numTerm}"/></span>
    <label>Длительность (в неделях): <input type="text" name="" value="${term.duration}"></label><br>
    <div class="termsMod">
        <span>Список дисциплин:</span>
        <div class="termsList">
            <c:forEach items="${allActiveDisciplines}" var="allDisc">
            <div class="termWithCheckbox">
                <input type="checkbox" name="" id="${disc.id}">
                <div class="term">
                    <label for="${disc.id}">${allDisc.discipline}</label>
                </div>
            </div>
            </c:forEach>
        </div>
        <button class="btn btn3">Применить</button>
    </div>
</div>
</body>
</html>
