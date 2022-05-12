<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Добавление студента</title>
    <link rel="stylesheet" href="../resources/css/style.css">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">

    <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
    <script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
    <script>
        $( function() {
            $( "#datepicker" ).datepicker();
        } );
    </script>
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
<form action="/student-create" method="post" class="stModform">
    <span>Для создания студента, заполните все поля и нажмите кнопку "Создать"</span><br>
    <table>
        <tr>
            <td class="labelStudents">Фамилия</td>
            <td><input type="text" name="surname" maxlength="50" size="20"></td>
        </tr>
        <tr>
            <td class="labelStudents">Имя</td>
            <td><input type="text" name="name" maxlength="50" size="20"></td>
        </tr>
        <tr>
            <td class="labelStudents"><label for="groups">Выберите группу:&nbsp;&nbsp;</label></td>
            <td>
                <select name="group" id="groups">
                    <c:forEach items="${groups}" var="gr">
                    <option value="${gr.id}">${gr.name_group}-${gr.course}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td class="labelStudents">Дата поступления:</td>
            <td><input type="text" name="date" id="datepicker" maxlength="50" size="20"></td>
        </tr>
    </table>
    <button type="submit" class="btn btn1">Создать</button>
</form>

<c:if test="${error == '1'}">
    <h4>Поля не должны быть пустыми!!!</h4>
</c:if>

</body>
</html>
