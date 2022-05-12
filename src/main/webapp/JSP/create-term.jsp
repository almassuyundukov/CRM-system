<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Создание семестра</title>
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
<form action="/create-term" method="post" id="selectForm">
    <div class="stModform">
        <span>Для создания семестра, заполните все поля и нажмите кнопку "Создать"</span><br>
        <label for="group">Выбрать группу: </label>
        <select name="group" id="group">
            <c:forEach items="${groups}" var="gr">
                <option value="${gr.id}" name="groups${gr.id}">${gr.name_group}-${gr.course}</option>
            </c:forEach>
        </select>

        <label for="terms">Выбрать семестр: </label>
        <select name="terms" id="terms" class="termsInCreate">
            <option value="1">Семестр 1</option>
            <option value="2">Семестр 2</option>
            <option value="3">Семестр 3</option>
            <option value="4">Семестр 4</option>
            <option value="5">Семестр 5</option>
            <option value="6">Семестр 6</option>
            <option value="7">Семестр 7</option>
            <option value="8">Семестр 8</option>
            <option value="9">Семестр 9</option>
            <option value="10">Семестр 10</option>
        </select>

        <label for="duration">Длительность (в неделях): <input type="text" name="duration" id="duration"></label><br>
        <div class="termsMod">
            <span>Список дисциплин:</span>
            <div class="termsList">
                <c:forEach items="${disciplines}" var="disc">
                    <div class="termWithCheckbox">
                        <input type="checkbox" name="disciplinesId" id="${disc.id}" value="${disc.id}">
                        <div class="term">
                            <label for="${disc.id}">${disc.discipline}</label>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <button class="btn btn3" onclick="selectDisciplines()">Создать</button>
        </div>
    </div>
    <input type="hidden" id="selectHidden" name="selectHidden">
</form>
</body>
</html>
