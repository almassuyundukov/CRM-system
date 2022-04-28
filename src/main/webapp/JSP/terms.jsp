<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Семестры</title>
    <link rel="stylesheet" href="../resources/css/style.css">
    <script src="../resources/js/function.js"></script>

    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>

    <script>
        $(document).ready(function(){
        $('#group option[value=${nameGroup}]').prop('selected', true);
        $('#selectDisc option[value=${idTerm}]').prop('selected', true);
            $('#termHidden option[value=${nameGroup}]');
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
                    <a href="/home" class="buttonRed">На главную</a></li>
                <li class="block login">
                    <a href="" class="buttonLogin"><span>logout</span></a>
                </li>
            </ul>
            <ul class="adminButtons">
                <li class="block1"><a href="/create-term" class="buttonRed">Создать семестр</a></li>
                <li class="block1"><button type="submit" class="buttonRed" onclick="selectTermForModify()">Модифицировать выбранный семестр</button></li>
                <li class="block1"><a href="/term-modify" class="buttonRed">Удалить выбранный семестр</a></li>
            </ul>
        </div>
    </nav>
</header>
<div class="termsDiv">

    <label for="group">Выбрать группу: </label><select name="group" id="group" onchange="selectGroup()">
            <c:forEach items="${termsKeySet}" var="groups">
                <option value="${groups}">${groups}</option>
            </c:forEach>
        </select>


    <label for="selectDisc">Выбрать семестр: </label><select name="" id="selectDisc">
    <c:forEach items="${terms}" var="t">
                <option value="${t.id}">Семестр ${t.numTerm}</option>
    </c:forEach>
            </select>

        <button class="btn btn3" onclick="selectTerm()">Выбрать семестр</button>
    <br>
    <span>Длительность семестра: </span>
    <span>${duration} недели</span>
    <br>
    <span>Список дисциплин семестра</span>
        <table class="disciplinesTable">
            <tr>
                <th></th>
                <th>Наименование дисциплины</th>
            </tr>
                <c:forEach items="${disciplines}" var="disc">
                        <tr>
                            <td><input type="checkbox" name="" id="${disc.id}"></td>
                            <td><label for="${disc.id}">${disc.discipline}</label></td>
                        </tr>
                </c:forEach>
        </table>
</div>
</body>


<form action="/terms" method="post" id="termForm">
    <input type="hidden" id="termHidden" name="termHidden">
    <input type="hidden" id="disciplinesHidden" name="disciplinesHidden">
</form>

<form action="/term-modify" method="get" id="modifyTermForm">
    <input type="hidden" id="termModifyHidden" name="termModifyHidden">
</form>

</html>
