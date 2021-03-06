<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Integer> list = new ArrayList<>();
    list.add(1);
    list.add(2);
    list.add(3);
    list.add(4);
    list.add(5);
    list.add(6);
    list.add(7);
    list.add(8);
    request.setAttribute("list", list);
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Создание расписание</title>
    <link rel="stylesheet" href="../resources/css/style.css">
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="../resources/js/function.js"></script>
    <script>
    $(document).ready(function () {
        $('#group option[value=${nameGroup}]').prop('selected', true);
        $('#selectDisc option[value=${idTerm}]').prop('selected', true);
        $('#years option[value=${idYears}]').prop('selected', true);
        $('#firstWeek option[value=${firstWeek}]').prop('selected', true);
        $('#termHidden option[value=${nameGroup}]');
        $('#exampleFormControlSelect2').change(function () {
            <c:forEach items="${weeksInTermWithDays}" var="week">
            $('#week${week.numberWeek}')[$(this).val() == 'week${week.numberWeek}' ? 'show' : 'hide']();
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
                    <button type="submit" class="buttonRed" onclick="createTimetable()" id="click">Создать</button>
                </li>
                <li class="block1">
                    <button type="submit" class="buttonRed" onclick="deleteStudents()">Удалить расписание</button>
                </li>
            </ul>

            <label for="group">Выбрать группу: </label>
            <select name="group" id="group" onchange="selectGroup()">
                <c:forEach items="${termsKeySet}" var="groups">
                    <option value="${groups}">${groups}</option>
                </c:forEach>
            </select>

            <label for="selectDisc">Выбрать семестр: </label>
            <select name="" id="selectDisc">
            <c:forEach items="${terms}" var="t">
                <option value="${t.id}">Семестр ${t.numTerm}</option>
            </c:forEach>
            </select>

            <label for="years">Выбрать год:&nbsp;&nbsp;</label>
            <select name="" id="years" onchange="selectYears()">
                <c:forEach items="${years}" var="year">
                    <option value="${year.id}">${year.years}</option>
                </c:forEach>
            </select>

            <label for="firstWeek">Первая неделя семестра:&nbsp;&nbsp;</label>
            <select name="" id="firstWeek">
                <c:forEach items="${weeks}" var="weeks">
                    <option value="${weeks}">${weeks} неделя</option>
                </c:forEach>
            </select>
            <div class="block1 timetableSelect">
                <button type="submit" class="buttonRed" onclick="selectFirstWeek()">Выбрать</button>
            </div>
        </div>
    </nav>
</header>


<div class="form-group">
    <label for="exampleFormControlSelect2">Неделя:&nbsp;&nbsp;</label>
    <select class="form-control" id="exampleFormControlSelect2" name="view">
        <option value="0"></option>
        <c:forEach items="${weeksInTerm}" var="weeks">
            <option value="week${weeks}">${weeks} неделя</option>
        </c:forEach>
    </select>
</div>

<form action="/create-time-all" method="post" id="allParamsWithoutDisciplinesForm">
<div>
    <c:forEach items="${weeksInTermWithDays}" var="week">
        <table class="disciplinesStudent disciplines" id="week${week.numberWeek}" hidden="hidden">
            <tr>
                <th></th>
                <th class="dayOfWeek" id="${week.getDaysOfWeek().get(0).id}">Понедельник, <c:out
                        value="${week.getDaysOfWeek().get(0).date}"/></th>
                <th class="emptyColumn"></th>
                <th></th>
                <th class="dayOfWeek" id="${week.getDaysOfWeek().get(3).id}">Четверг, <c:out
                        value="${week.getDaysOfWeek().get(3).date}"/></th>
            </tr>
            <c:forEach items="${list}" var="list">
                <tr>
                    <td>${list}</td>
                    <td>
                        <select id="${week.getDaysOfWeek().get(0).id}${list}" name="${week.getDaysOfWeek().get(0).id}${list}">
                            <option value="9"></option>
                            <c:forEach items="${disciplines}" var="disc">
                                <option value="${disc.id}" id="${list}${disc.id}">${disc.discipline}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td class="emptyColumn"></td>
                    <td>${list}</td>
                    <td>
                        <select id="${week.getDaysOfWeek().get(3).id}${list}" name="${week.getDaysOfWeek().get(3).id}${list}">
                            <option value="9"></option>
                            <c:forEach items="${disciplines}" var="disc">
                                <option value="${disc.id}">${disc.discipline}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
            </c:forEach>
            <tr>
                <th></th>
                <th class="dayOfWeek" id="${week.getDaysOfWeek().get(1).id}">Вторник, <c:out
                        value="${week.getDaysOfWeek().get(1).date}"/></th>
                <th class="emptyColumn"></th>
                <th></th>
                <th class="dayOfWeek" id="${week.getDaysOfWeek().get(4).id}">Пятница, <c:out
                        value="${week.getDaysOfWeek().get(4).date}"/></th>
            </tr>
            <c:forEach items="${list}" var="list">
                <tr>
                    <td>${list}</td>
                    <td>
                        <select id="${week.getDaysOfWeek().get(1).id}${list}" name="${week.getDaysOfWeek().get(1).id}${list}">
                            <option value="9"></option>
                            <c:forEach items="${disciplines}" var="disc">
                                <option value="${disc.id}">${disc.discipline}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td class="emptyColumn"></td>
                    <td>${list}</td>
                    <td>
                        <select id="${week.getDaysOfWeek().get(4).id}${list}" name="${week.getDaysOfWeek().get(4).id}${list}">
                            <option value="9"></option>
                            <c:forEach items="${disciplines}" var="disc">
                                <option value="${disc.id}">${disc.discipline}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
            </c:forEach>
            <tr>
                <th></th>
                <th class="dayOfWeek" id="${week.getDaysOfWeek().get(2).id}">Среда, <c:out
                        value="${week.getDaysOfWeek().get(2).date}"/></th>
                <th class="emptyColumn"></th>
                <th></th>
                <th class="dayOfWeek" id="${week.getDaysOfWeek().get(5).id}">Суббота, <c:out
                        value="${week.getDaysOfWeek().get(5).date}"/></th>
            </tr>
            <c:forEach items="${list}" var="list">
                <tr>
                    <td>${list}</td>
                    <td>
                        <select id="${week.getDaysOfWeek().get(2).id}${list}" name="${week.getDaysOfWeek().get(2).id}${list}">
                            <option value="9"></option>
                            <c:forEach items="${disciplines}" var="disc">
                                <option value="${disc.id}">${disc.discipline}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td class="emptyColumn"></td>
                    <td>${list}</td>
                    <td>
                        <select id="${week.getDaysOfWeek().get(5).id}${list}" name="${week.getDaysOfWeek().get(5).id}${list}">
                            <option value="9"></option>
                            <c:forEach items="${disciplines}" var="disc">
                                <option value="${disc.id}">${disc.discipline}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <c:forEach items="${list}" var="list">
            <input type="hidden" id="${week.getDaysOfWeek().get(6).id}${list}" name="${week.getDaysOfWeek().get(6).id}${list}" value="9">
        </c:forEach>
    </c:forEach>
</div>

    <input type="hidden" id="daysHidden" name="daysHidden" value="${weeksInTermWithDays}">
    <input type="hidden" id="idTermHidden" name="idTermHidden" value="${idTerm}">

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
