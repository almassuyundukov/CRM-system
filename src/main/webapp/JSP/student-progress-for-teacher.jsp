<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Электронный журнал</title>
    <link rel="stylesheet" href="../resources/css/style.css">

    <link rel="stylesheet" href="//code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
    <script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
    <script src="../resources/js/function.js"></script>
    <script>
        $(document).ready(function () {
            $('#group option[value=${nameGroup}]').prop('selected', true);
            $('#selectTerm option[value=${idTerm}]').prop('selected', true);
            $('#selectDisc option[value=${idDiscipline}]').prop('selected', true);
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

            <label for="selectDisc">Выбрать дисциплину: </label>
            <select name="selectDisc" id="selectDisc" onchange="selectDiscForTeacher()">
                <c:forEach items="${disciplines}" var="disc">
                    <option value="${disc.id}">${disc.discipline}</option>
                </c:forEach>
            </select>

            <label for="group">Выбрать группу: </label>
            <select name="group" id="group" onchange="selectDiscForTeacher()">
                <c:forEach items="${nameGroups}" var="groups">
                    <option value="${groups}">${groups}</option>
                </c:forEach>
            </select>

            <label for="selectTerm">Выбрать семестр: </label>
            <select name="" id="selectTerm" onchange="selectDiscForTeacher()">
                <c:forEach items="${terms}" var="t">
                    <option value="${t.id}">Семестр ${t.numTerm}</option>
                </c:forEach>
            </select>

            <div class="block1 timetableSelect">
                <button type="submit" class="buttonRed" onclick="selectTimetableByTerm()">Загрузить журнал</button>
            </div>

            <div class="block1 timetableSelect">
            <button type="submit" class="buttonRed" onclick="downloadStPerformanceForDB()">Подтвердить</button>
            </div>

        </div>
    </nav>
</header>

<div class="stModform">
    <div class="form-group">
        <label for="exampleFormControlSelect2">Неделя:&nbsp;&nbsp;</label>
        <select class="form-control" id="exampleFormControlSelect2" name="view">
            <option value="0"></option>
            <c:forEach items="${weeks}" var="weeks">
                <option value="week${weeks}">${weeks} неделя</option>
            </c:forEach>
        </select>
    </div>
    <form action="/progress-for-teacher" method="post" id="studentsPerformance">
    <c:forEach items="${weeks}" var="w">
    <table class="disciplinesStudent disciplines" id="week${w}" hidden="hidden">
        <tr>
            <th></th>
            <th></th>
            <c:forEach items="${timetable.get(w)}" var="calendar" varStatus="loop">
            <th class="dayOfWeek"><fmt:formatDate pattern="dd MMMM ,yyyy" value="${calendar.date}" />
                    (${calendar.posDisc} пара)</th>
            </c:forEach>
        </tr>
        <c:forEach items="${students}" var="st" varStatus="loop">
        <tr>
            <td>${loop.index+1}</td>
            <td>${st.surname} ${st.name}</td>
            <c:forEach items="${timetable.get(w)}" var="calendar" varStatus="loop">
            <td><select id="${st.id}${calendar.id}" name="${st.id}${calendar.id}">
                <c:set var="ids" scope="session" value="${st.id}${calendar.id}" />
                <c:if test="${marksInTerm.containsKey(ids)}">
                <c:choose>
                    <c:when test="${marksInTerm.get(ids) == 1}">
                        <option value="0"></option>
                        <option value="1" selected="selected">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                    </c:when>
                    <c:when test="${marksInTerm.get(ids) == 2}">
                        <option value="0"></option>
                        <option value="1">1</option>
                        <option value="2" selected="selected">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                    </c:when>
                    <c:when test="${marksInTerm.get(ids) == 3}">
                        <option value="0"></option>
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3" selected="selected">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                    </c:when>
                    <c:when test="${marksInTerm.get(ids) == 4}">
                        <option value="0"></option>
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4" selected="selected">4</option>
                        <option value="5">5</option>
                    </c:when>
                    <c:when test="${marksInTerm.get(ids) == 5}">
                        <option value="0"></option>
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5" selected="selected">5</option>
                    </c:when>
                    <c:otherwise>
                        <option value="0"></option>
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                    </c:otherwise>
                </c:choose>
                </c:if>
                <option value="0"></option>
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5">5</option>
            </select></td>
            </c:forEach>
        </tr>
        </c:forEach>
    </table>
    </c:forEach>

        <input type="hidden" id="studentsHidden" name="studentsHidden" value="${students}">
        <input type="hidden" id="timetable" name="timetable" value="${timetable}">
    </form>
</div>
</body>

<form action="/progress-for-teacher" method="get" id="progressForTeacherForm">
    <input type="hidden" id="termHidden" name="termHidden">
    <input type="hidden" id="disciplineHidden" name="disciplineHidden">
    <input type="hidden" id="groupHidden" name="groupHidden">
    <input type="hidden" id="downloadTimetableHidden" name="downloadTimetableHidden" value="0">
</form>


</html>