<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Дисциплины</title>
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
                            <a href="/logout" class="buttonLogin"><span>${login}, logout</span></a>
                        </c:when>
                        <c:otherwise>
                            <a href="/login" class="buttonLogin"><span>Login</span></a>
                        </c:otherwise>
                    </c:choose>
                </li>
            </ul>
            <ul class="adminButtons">
                <li class="block1"><a href="./disciplineCreating.html" class="buttonRed">Создать дисциплину</a></li>
                <li class="block1"><a href="./disciplineMod.html" class="buttonRed">Модифицировать выбранную дисциплину</a>
                </li>
                <li class="block1"><a href="" class="buttonRed">Удалить выбранную дисциплину</a></li>
            </ul>
        </div>
    </nav>
</header>
<div class="disciplinesDiv">
    <p>Список дисциплин</p>
    <table class="disciplinesTable">
        <tr>
            <th></th>
            <th>Наименование дисциплины</th>
        </tr>
<c:forEach items="${disciplines}" var="st">
        <tr>
            <td><input type="checkbox" name="" id="${st.id}"></td>
            <td><label for="${st.id}">${st.discipline}</label></td>
        </tr>
</c:forEach>
    </table>
</div>
</body>
</html>
