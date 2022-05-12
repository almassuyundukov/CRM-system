<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Изменение студента</title>
    <link rel="stylesheet" href="../resources/css/style.css">

    <link rel="stylesheet" href="//code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">

    <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
    <script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
    <script>
        $(document).ready(function () {
            $('#groups option[value=${student.groupId}]').prop('selected', true);
        });
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
            <td>
                <select name="groups" id="groups">
                    <c:forEach items="${groups}" var="gr">
                        <option value="${gr.id}">${gr.name_group}-${gr.course}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td class="labelStudents">Дата поступления:</td>
            <td><input type="text" name="date" id="datepicker" value="<fmt:formatDate pattern="dd/MM/yyyy" value="${student.date}"/>" maxlength="50" size="20"></td>
        </tr>
    </table>

    <button type="submit" class="btn btn1">Применить</button>
    </form>
</div>
</body>
</html>
