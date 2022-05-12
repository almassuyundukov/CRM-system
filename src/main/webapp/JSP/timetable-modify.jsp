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
    <title>Изменение расписание</title>
    <link rel="stylesheet" href="../resources/css/style.css">
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="../resources/js/function.js"></script>
    <script>
        $(document).ready(function () {
            $('#group option[value=${nameGroup}]').prop('selected', true);
            $('#selectTerm option[value=${idTerm}]').prop('selected', true);
            $('#termHidden option[value=${nameGroup}]');
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
                <li><span>Текущий год: 2021-2022</span></li>
            </ul>

            <ul class="adminButtons">
                <li class="block1">
                    <button type="submit" class="buttonRed" onclick="modifyTimetable()" id="click">Применить</button>
                </li>
            </ul>

            <label for="group">Группа: </label>
            <span id="group">${nameGroup}</span>

            <label for="selectTerm">Выбрать семестр: </label>
            <span id="selectTerm" value="${idTerm}">Семестр ${numTerm}</span>

        </div>
    </nav>
</header>


<div class="form-group">
    <label for="exampleFormControlSelect2">Неделя:&nbsp;&nbsp;</label>
    <select class="form-control" id="exampleFormControlSelect2" name="view">
        <option value="0"></option>
        <c:forEach items="${weeks}" var="weeks">
            <option value="week${weeks}">${weeks} неделя</option>
        </c:forEach>
    </select>
</div>

<form action="/timetable-modify" method="post" id="termModifyForm">
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
                        <td id="${timetable.get(week).get('Monday').get(i).id}">
                            <select id="${timetable.get(week).get('Monday').get(i).id}"
                                    name="${timetable.get(week).get('Monday').get(i).id}">
                                <option value="${timetable.get(week).get('Monday').get(i).idDiscipline}">
                                        ${timetable.get(week).get('Monday').get(i).discipline}
                                </option>
                                <c:forEach items="${disciplines}" var="disc">
                                    <c:choose>
                                        <c:when test="${disc.id eq timetable.get(week).get('Monday').get(i).idDiscipline}">
                                            <option value="9">-</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${disc.id}">${disc.discipline}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                        </td>
                        <td class="emptyColumn"></td>
                        <td>${i}</td>
                        <td id="${timetable.get(week).get('Thursday').get(i).id}">
                            <select id="${timetable.get(week).get('Thursday').get(i).id}"
                                    name="${timetable.get(week).get('Thursday').get(i).id}">
                                <option value="${timetable.get(week).get('Thursday').get(i).idDiscipline}">
                                        ${timetable.get(week).get('Thursday').get(i).discipline}
                                </option>
                                <c:forEach items="${disciplines}" var="disc">
                                    <c:choose>
                                        <c:when test="${disc.id eq timetable.get(week).get('Thursday').get(i).idDiscipline}">
                                            <option value="9">-</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${disc.id}">${disc.discipline}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
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
                        <td id="${timetable.get(week).get('Tuesday').get(i).id}">
                            <select id="${timetable.get(week).get('Tuesday').get(i).id}"
                                    name="${timetable.get(week).get('Tuesday').get(i).id}">
                                <option value="${timetable.get(week).get('Tuesday').get(i).idDiscipline}">
                                        ${timetable.get(week).get('Tuesday').get(i).discipline}
                                </option>
                                <c:forEach items="${disciplines}" var="disc">
                                    <c:choose>
                                        <c:when test="${disc.id eq timetable.get(week).get('Tuesday').get(i).idDiscipline}">
                                            <option value="9">-</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${disc.id}">${disc.discipline}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                        </td>
                        <td class="emptyColumn"></td>
                        <td>${i}</td>
                        <td id="${timetable.get(week).get('Friday').get(i).id}">
                            <select id="${timetable.get(week).get('Friday').get(i).id}"
                                    name="${timetable.get(week).get('Friday').get(i).id}">
                                <option value="${timetable.get(week).get('Friday').get(i).idDiscipline}">
                                        ${timetable.get(week).get('Friday').get(i).discipline}
                                </option>
                                <c:forEach items="${disciplines}" var="disc">
                                    <c:choose>
                                        <c:when test="${disc.id eq timetable.get(week).get('Friday').get(i).idDiscipline}">
                                            <option value="9">-</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${disc.id}">${disc.discipline}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
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
                        <td id="${timetable.get(week).get('Wednesday').get(i).id}">
                            <select id="${timetable.get(week).get('Wednesday').get(i).id}"
                                    name="${timetable.get(week).get('Wednesday').get(i).id}">
                                <option value="${timetable.get(week).get('Wednesday').get(i).idDiscipline}">
                                        ${timetable.get(week).get('Wednesday').get(i).discipline}
                                </option>
                                <c:forEach items="${disciplines}" var="disc">
                                    <c:choose>
                                        <c:when test="${disc.id eq timetable.get(week).get('Wednesday').get(i).idDiscipline}">
                                            <option value="9">-</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${disc.id}">${disc.discipline}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                        </td>
                        <td class="emptyColumn"></td>
                        <td>${i}</td>
                        <td id="${timetable.get(week).get('Saturday').get(i).id}">
                            <select id="${timetable.get(week).get('Saturday').get(i).id}"
                                    name="${timetable.get(week).get('Saturday').get(i).id}">
                                <option value="${timetable.get(week).get('Saturday').get(i).idDiscipline}">
                                        ${timetable.get(week).get('Saturday').get(i).discipline}
                                </option>
                                <c:forEach items="${disciplines}" var="disc">
                                    <c:choose>
                                        <c:when test="${disc.id eq timetable.get(week).get('Saturday').get(i).idDiscipline}">
                                            <option value="9">-</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${disc.id}">${disc.discipline}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:forEach>
    </div>

    <input type="hidden" id="timetableHidden" name="timetableHidden" value="${timetable}">

</form>
</body>

<form action="/create-timetable" method="post" id="termForm">
    <input type="hidden" id="termHidden" name="termHidden">
    <input type="hidden" id="disciplinesHidden" name="disciplinesHidden">
    <input type="hidden" id="yearsHidden" name="yearsHidden">
    <input type="hidden" id="firstWeekHidden" name="firstWeekHidden">
    <input type="hidden" id="durationHidden" name="durationHidden" value="${duration}">
</form>

</html>

