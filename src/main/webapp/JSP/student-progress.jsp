<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Прогресс студента</title>
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
                    <a href="/" class="buttonRed">На главную</a>
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
    <span>Отображена успеваемость для следующего студента</span><br>
    <table class="listStudent progress">
        <tr>
            <th>Фамилия</th>
            <th>Имя</th>
            <th>Группа</th>
            <th>Дата поступления</th>
        </tr>
        <tr>
            <td><label>Сидоров</label></td>
            <td><label>Сидор</label></td>
            <td><label>КТ-21</label></td>
            <td><label>1/09/2011</label></td>
        </tr>
    </table>
</div>
<div class="stModform">
    <table class="disciplinesStudent disciplines">
        <tr>
            <th>Дисциплина</th>
            <th>Оценка</th>
        </tr>
        <tr>
            <td>Информатика</td>
            <td>5</td>
        </tr>
    </table>
    <div class="selectTerm">
        <span>Выбрать семестр: </span>
        <select name="" id="">
            <option>Семестр 1</option>
            <option>Семестр 2</option>
        </select>
        <button type="submit" class="btn btn1">Выбрать</button><br>
        <span>Средняя оценка за семестр: </span><label>4,9</label>
    </div>
</div>
</body>
</html>
