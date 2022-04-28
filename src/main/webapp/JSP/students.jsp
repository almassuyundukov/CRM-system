<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Студенты</title>
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
                    <a href="/home" class="buttonRed">На главную</a></li>
                <li class="block">
                    <a href="/student-progress" class="buttonRed" onclick="progressStudents()">Посмотреть успеваемость выбранных студентов</a></li>
                <li class="block login">
                    <a href="" class="buttonLogin"><span>logout</span></a>
                </li>
            </ul>
            <ul class="adminButtons">
                <li class="block1"><a href="/student-create" class="buttonRed">Создать студента</a></li>
                <li class="block1"><a href="/create-group" class="buttonRed">Создать группу</a></li>
                <li class="block1"><button type="submit" class="buttonRed" onclick="modifyStudents()">Модифицировать выбранного
                    студента</button></li>
                <li class="block1"><button type="submit" class="buttonRed" onclick="deleteStudents()">Удалить выбранного студента</button></li>
            </ul>
        </div>
    </nav>
</header>
<table class="listStudents">
    <tr>
        <th>
        </th>
        <th>Фамилия</th>
        <th>Имя</th>
        <th>Группа</th>
        <th>Курс</th>
        <th>Дата поступления</th>
    </tr>
    <c:forEach items="${students}" var="st">
        <tr>
            <td><input type="checkbox" name="idStudent" class="checkbox" id="${st.id}" value="${st.id}"></td>
            <td><label for="${st.id}">${st.surname}</label></td>
            <td><label for="${st.id}">${st.name}</label></td>
            <td><label for="${st.id}">${st.group}</label></td>
            <td><label for="${st.id}">${st.group}</label></td>
            <td><label for="${st.id}"><fmt:formatDate pattern="dd/MM/yyyy" value="${st.date}"/></label></td>
        </tr>
    </c:forEach>
</table>
</body>
<form action="/student-delete" method="post" id="deleteForm">
    <input type="hidden" id="deleteHidden" name="deleteHidden">
</form>

<form action="/student-modify" method="get" id="modifyForm">
    <input type="hidden" id="modifyHidden" name="modifyHidden">
</form>

<form action="/student-progress" method="get" id="progressForm">
    <input type="hidden" id="progressHidden" name="progressHidden">
</form>

</html>
