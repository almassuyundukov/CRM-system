<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Дисциплины</title>
    <link rel="stylesheet" href="../resources/css/style.css">
    <script src="../resources/js/function.js"></script>
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
            <ul class="adminButtons">
                <li class="block1"><a href="/discipline-create" class="buttonRed">Создать дисциплину</a></li>
                <li class="block1">
                    <button type="submit" class="buttonRed" onclick="modifyDiscipline()">Модифицировать выбранную дисциплину</button>
                </li>
                <li class="block1">
                    <button type="submit" class="buttonRed" onclick="deleteDisciplines()">Удалить выбранную дисциплину</button>
                </li>
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
<c:forEach items="${disciplines}" var="disc">
        <tr>
            <td><input type="checkbox" name="disciplineId" id="${disc.id}" value="${disc.id}"></td>
            <td><label for="${disc.id}">${disc.discipline}</label></td>
        </tr>
</c:forEach>
    </table>
</div>
</body>

<form action="/discipline-modify" method="get" id="discModifyForm">
    <input type="hidden" id="discModifyHidden" name="discModifyHidden">
</form>

<form action="/discipline-delete" method="post" id="discDeleteForm">
    <input type="hidden" id="discDeleteHidden" name="discDeleteHidden">
</form>

</html>
