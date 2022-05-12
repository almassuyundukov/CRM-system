<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <title>Текущий прогресс</title>
    <link rel="stylesheet" href="../resources/css/style.css">

    <link rel="stylesheet" href="//code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
    <script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
    <script src="../resources/js/function.js"></script>
    <script>
        $(document).ready(function () {
            $('#selectTerm option[value=${idTerm}]').prop('selected', true);
            $('#exampleFormControlSelect2').change(function () {
                <c:forEach items="${weeks}" var="w">
                $('#week${w}')[$(this).val() == 'week${w}' ? 'show' : 'hide']();
                </c:forEach>
            });
        });
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
            <td><label>${student.surname}</label></td>
            <td><label>${student.name}</label></td>
            <td><label>${student.group}</label></td>
            <td><label><fmt:formatDate pattern="dd/MM/yyyy" value="${student.date}"/></label></td>
        </tr>
    </table>

    <label for="selectTerm">Выбрать семестр: </label>
    <select name="" id="selectTerm">
        <c:forEach items="${terms}" var="t">
            <option value="${t.id}">Семестр ${t.numTerm}</option>
        </c:forEach>
    </select>

    <div class="block1 timetableSelect">
        <button type="submit" class="buttonRed" onclick="getStudentPerfForTerm()">Выбрать</button>
    </div>

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
                    <th>Оценка</th>
                    <th class="emptyColumn"></th>
                    <th></th>
                    <th class="dayOfWeek">Четверг</th>
                    <th>Оценка</th>
                </tr>
                <c:forEach items="${list}" var="i">
                    <tr>
                        <td>${i}</td>
                        <td>
                            <c:out value="${timetable.get(week).get('Monday').get(i).discipline}"/>
                        </td>
                        <td>
                            <c:if test="${marks.containsKey(timetable.get(week).get('Monday').get(i).id)}">
                                <c:out value="${marks.get(timetable.get(week).get('Monday').get(i).id)}"/>
                            </c:if>
                        </td>
                        <td class="emptyColumn"></td>
                        <td>${i}</td>
                        <td>
                            <c:out value="${timetable.get(week).get('Thursday').get(i).discipline}"/>
                        </td>
                        <td>
                            <c:if test="${marks.containsKey(timetable.get(week).get('Thursday').get(i).id)}">
                                <c:out value="${marks.get(timetable.get(week).get('Thursday').get(i).id)}"/>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                <tr>
                    <th></th>
                    <th class="dayOfWeek">Вторник</th>
                    <th>Оценка</th>
                    <th class="emptyColumn"></th>
                    <th></th>
                    <th class="dayOfWeek">Пятница</th>
                    <th>Оценка</th>
                </tr>
                <c:forEach items="${list}" var="i">
                    <tr>
                        <td>${i}</td>
                        <td>
                            <c:out value="${timetable.get(week).get('Tuesday').get(i).discipline}"/>
                        </td>
                        <td>
                            <c:if test="${marks.containsKey(timetable.get(week).get('Tuesday').get(i).id)}">
                                <c:out value="${marks.get(timetable.get(week).get('Tuesday').get(i).id)}"/>
                            </c:if>
                        </td>
                        <td class="emptyColumn"></td>
                        <td>${i}</td>
                        <td>
                            <c:out value="${timetable.get(week).get('Friday').get(i).discipline}"/>
                        </td>
                        <td>
                            <c:if test="${marks.containsKey(timetable.get(week).get('Friday').get(i).id)}">
                                <c:out value="${marks.get(timetable.get(week).get('Friday').get(i).id)}"/>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                <tr>
                    <th></th>
                    <th class="dayOfWeek">Среда</th>
                    <th>Оценка</th>
                    <th class="emptyColumn"></th>
                    <th></th>
                    <th class="dayOfWeek">Суббота</th>
                    <th>Оценка</th>
                </tr>
                <c:forEach items="${list}" var="i">
                    <tr>
                        <td>${i}</td>
                        <td>
                            <c:out value="${timetable.get(week).get('Wednesday').get(i).discipline}"/>
                        </td>
                        <td>
                            <c:if test="${marks.containsKey(timetable.get(week).get('Wednesday').get(i).id)}">
                                <c:out value="${marks.get(timetable.get(week).get('Wednesday').get(i).id)}"/>
                            </c:if>
                        </td>
                        <td class="emptyColumn"></td>
                        <td>${i}</td>
                        <td>
                            <c:out value="${timetable.get(week).get('Saturday').get(i).discipline}"/>
                        </td>
                        <td>
                            <c:if test="${marks.containsKey(timetable.get(week).get('Saturday').get(i).id)}">
                                <c:out value="${marks.get(timetable.get(week).get('Saturday').get(i).id)}"/>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:forEach>
    </div>
</div>
</body>

<form action="/student-progress-to-day" method="get" id="progressForStudentForm">
    <input type="hidden" id="termHidden" name="termHidden">
    <input type="hidden" name="progressToDayHidden" id="progressToDayHidden" value="${student.id}">
    <input type="hidden" id="downloadTimetableForStudentHidden" name="downloadTimetableForStudentHidden" value="0">
</form>

</html>
