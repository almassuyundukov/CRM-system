<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<String> list = new ArrayList<>();
    list.add("1");
    list.add("2");
    list.add("3");
    list.add("4");
    list.add("5");
    list.add("6");
    list.add("7");
    list.add("8");
    request.setAttribute("list", list);
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Расписание</title>
    <link rel="stylesheet" href="../resources/css/style.css">
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="../resources/js/function.js"></script>
    <script>
        $(document).ready(function () {
            $('#group option[value=${nameGroup}]').prop('selected', true);
            $('#selectDisc option[value=${idTerm}]').prop('selected', true);
            $('#termHidden option[value=${nameGroup}]');
            $('#exampleFormControlSelect2').change(function () {
                <c:forEach items="${weeks}" var="w">
                $('#week${w}')[$(this).val() == 'week${w}' ? 'show' : 'hide']();
                </c:forEach>
            });
        });
    </script>
    <style>
        .disciplines .emptyColumn {
            width: 100px;
            background-color: transparent;
            border-color: transparent;
        }

        .dayOfWeek {
            width: 300px;
        }
    </style>
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

            <ul class="adminButtons">
                <li class="block1"><a href="/create-timetable" class="buttonRed">Создать расписание</a></li>
                <li class="block1"><a href="./modTimetable.html" class="buttonRed">Модифицировать выбранное
                    расписание</a></li>
                <li class="block1">
                    <button type="submit" class="buttonRed" onclick="deleteStudents()">Удалить расписание</button>
                </li>
                <li class="block1"><a href="/create-calendar" class="buttonRed">Создать год</a></li>
            </ul>

            <label for="group">Выбрать группу: </label>
            <select name="group" id="group" onchange="selectGroup()">
                <c:forEach items="${termsKeySet}" var="groups">
                    <option value="${groups}">${groups}</option>
                </c:forEach>
            </select>

            <label for="selectTerm">Выбрать семестр: </label>
            <select name="" id="selectTerm">
                <c:forEach items="${terms}" var="t">
                    <option value="${t.id}">Семестр ${t.numTerm}</option>
                </c:forEach>
            </select>
            <div class="block1 timetableSelect">
                <button type="submit" class="buttonRed" onclick="selectTermOfTimetable()">Выбрать</button>
            </div>

        </div>
    </nav>
</header>


<div class="form-group">
    <label for="exampleFormControlSelect2">Неделя:&nbsp;&nbsp;</label>
    <select class="form-control" id="exampleFormControlSelect2" name="view">
        <option value="0"></option>
        <c:forEach items="${weeks}" var="w">
            <option value="week${w}">${w} неделя</option>
        </c:forEach>
    </select>
</div>

<div>
    <c:forEach items="${weeks}" var="week">
        <table class="disciplinesStudent disciplines" id="week${week}" hidden="hidden">
            <tr>
                <th></th>
                <th class="dayOfWeek">Понедельник</th>
                <th class="emptyColumn"></th>
                <th></th>
                <th class="dayOfWeek">Четверг</th>
            </tr>
            <c:forEach items="${list}" var="i">
                <tr>
                    <td>${i}</td>
                    <td id="${timetable.get(week).get('Saturday').get(i).id}">
                        <c:out value="${timetable.get(week).get('Monday').get(i).discipline}"/>
                    </td>
                    <td class="emptyColumn"></td>
                    <td>${i}</td>
                    <td id="${timetable.get(week).get('Saturday').get(i).id}">
                        <c:out value="${timetable.get(week).get('Thursday').get(i).discipline}"/>
                    </td>
                </tr>
            </c:forEach>
            <tr>
                <th></th>
                <th class="dayOfWeek">Вторник</th>
                <th class="emptyColumn"></th>
                <th></th>
                <th class="dayOfWeek">Пятница</th>
            </tr>
            <c:forEach items="${list}" var="i">
                <tr>
                    <td>${i}</td>
                    <td id="${timetable.get(week).get('Saturday').get(i).id}">
                        <c:out value="${timetable.get(week).get('Tuesday').get(i).discipline}"/>
                    </td>
                    <td class="emptyColumn"></td>
                    <td>${i}</td>
                    <td id="${timetable.get(week).get('Saturday').get(i).id}">
                        <c:out value="${timetable.get(week).get('Friday').get(i).discipline}"/>
                    </td>
                </tr>
            </c:forEach>
            <tr>
                <th></th>
                <th class="dayOfWeek">Среда</th>
                <th class="emptyColumn"></th>
                <th></th>
                <th class="dayOfWeek">Суббота</th>
            </tr>
            <c:forEach items="${list}" var="i">
                <tr>
                    <td>${i}</td>
                    <td id="${timetable.get(week).get('Saturday').get(i).id}">
                        <c:out value="${timetable.get(week).get('Wednesday').get(i).discipline}"/>
                    </td>
                    <td class="emptyColumn"></td>
                    <td>${i}</td>
                    <td id="${timetable.get(week).get('Saturday').get(i).id}">
                        <c:out value="${timetable.get(week).get('Saturday').get(i).discipline}"/>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:forEach>
</div>

</body>

<form action="/timetable" method="post" id="termForm">
    <input type="hidden" id="termHidden" name="termHidden">
    <input type="hidden" id="selectTermHidden" name="selectTermHidden">
</form>

</html>
