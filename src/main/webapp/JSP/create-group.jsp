<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Создание группы</title>
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
                    <a href="./titlePage.html" class="buttonRed">На главную</a></li>
                </li>
                <li class="block login">
                    <a href="" class="buttonLogin"><span>logout</span></a>
                </li>
            </ul>
        </div>
    </nav>
</header>
<div class="stModform">
    <span>Для создания группы, заполните все поля и нажмите кнопку "Создать"</span><br>
    <form action="/create-group" method="post">
        <table>
            <tr>
                <td class="labelStudents">Название группы</td>
                <td><input type="text" name="groupName" maxlength="50" size="20"></td>
            </tr>
            <tr>
                <td class="labelStudents">Выберите курс</td>
                <td>
                    <select class="selectCourse" name="course">
                        <option value="1">1 курс</option>
                        <option value="2">2 курс</option>
                        <option value="3">3 курс</option>
                        <option value="4">4 курс</option>
                        <option value="5">5 курс</option>
                    </select>
                </td>
            </tr>
        </table>
        <button type="submit" class="btn btn1">Создать</button>
    </form>
</div>


<c:if test="${error == '1'}">
    <h4>Поля не должны быть пустыми!!!</h4>
</c:if>
</body>
</html>
