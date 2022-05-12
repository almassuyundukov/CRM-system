<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <head>
        <meta charset="UTF-8">
        <title>Создание календаря</title>
        <link rel="stylesheet" href="../resources/css/style.css">
        <script src="../resources/js/function.js"></script>

        <link rel="stylesheet" href="//code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">
        <script src="i18n/datepicker-ru.js"></script>
        <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
        <script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
        <script>
            $(function () {
                $.datepicker.setDefaults( $.datepicker.regional[ "fr" ] );
                var dateFormat = "mm/dd/yyyy",
                    from = $("#from").datepicker({
                            defaultDate: "+1w",
                            changeMonth: true,
                            numberOfMonths: 3,
                            firstDay: 1,
                        dayNamesShort: [ "Dim", "Lun", "Mar", "Mer", "Jeu", "Ven", "Sam" ]
                        })
                        .on("change", function () {
                            to.datepicker("option", "minDate", getDate(this));
                        }),
                    to = $("#to").datepicker({
                        defaultDate: "+1w",
                        changeMonth: true,
                        numberOfMonths: 3,
                        dayNamesShort: [ "Вс", "Пн", "Вт", "Ср", "Чт", "Пт", "Сб" ],
                        firstDay: 1

                    }),
                    firstWeek = $("#firstWeek").datepicker({
                        defaultDate: "+1w",
                        changeMonth: true,
                        numberOfMonths: 3,
                        firstDay: 1,
                        dayNamesShort: [ "Вс", "Пн", "Вт", "Ср", "Чт", "Пт", "Сб" ]

                    })
                        .on("change", function () {
                            to.datepicker("option", "minDate", getDate(this));
                        });

                function getDate(element) {
                    var date;
                    try {
                        date = $.datepicker.parseDate(dateFormat, element.value);
                    } catch (error) {
                        date = null;
                    }

                    return date;
                }
            });
        </script>
    </head>
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

            <ul class="adminButtons">
                <li class="block1">
                    <button type="submit" class="buttonRed" onclick="deleteStudents()">Удалить расписание</button>
                </li>
            </ul>
        </div>
    </nav>
</header>

<form action="/create-calendar" method="post">
    <label for="from">Выбрать начало уч. года:&nbsp;&nbsp;</label>
    <input type="text" id="from" name="from" autocomplete="off">

    <label for="to">Выбрать конец учебного года</label>
    <input type="text" id="to" name="to" autocomplete="off">

    <label for="firstWeek">Выберите понедельник первой недели:&nbsp;&nbsp;</label>
    <input type="text" id="firstWeek" name="firstWeek" autocomplete="off">

    <button type="submit" class="btn btn1">Создать</button>
</form>

<c:if test="${error == '1'}">
    <h4>Поля не должны быть пустыми!!!</h4>
</c:if>

<c:if test="${errorWeek == '1'}">
    <h4>Выберите первую неделю в пределах конца и начала учебного года!!!</h4>
</c:if>

<c:if test="${errorWeek == '2'}">
    <h4>Выберите понедельник в первой неделе!!!</h4>
</c:if>

</body>
</html>
