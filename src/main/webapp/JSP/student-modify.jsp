<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Изменение студента</title>
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
                    <a href="/home" class="buttonRed">На главную</a></li>
                </li>
                <li class="block login">
                    <a href="" class="buttonLogin"><span>logout</span></a>
                </li>
            </ul>
        </div>
    </nav>
</header>
<div class="stModform">
    <span>Для изменения студента, введите новые значения и нажмите кнопку "Применить"</span><br>
    <form action="/student-modify" method="post">
    <input type="hidden" name="id" value="${student.id}">
        <table>
        <tr>
            <td class="labelStudents">Фамилия</td>
            <td><input type="text" name="surname" maxlength="50" size="20" value="${student.surname}"></td>
        </tr>
        <tr>
            <td class="labelStudents">Имя</td>
            <td><input type="text" name="name" maxlength="50" size="20" value="${student.name}"></td>
        </tr>
        <tr>
            <td class="labelStudents">Группа</td>
            <td><input type="text" name="group" value="${student.group}" maxlength="50" size="20"></td>
        </tr>
        <tr>
            <td class="labelStudents">Дата поступления:</td>
            <td><input type="date" name="date" value="<fmt:formatDate pattern="dd/MM/yyyy" value="${student.date}"/>" maxlength="50" size="20"></td>
        </tr>
    </table>

    <button type="submit" class="btn btn1">Применить</button>
    </form>
</div>
</body>
</html>
