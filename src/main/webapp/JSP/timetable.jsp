<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Расписание</title>
    <link rel="stylesheet" href="../resources/css/style.css">
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script>
        $(document).ready(function () {
            $('#formControlSelect2').change(function () {
                $('#1')[$(this).val() == '1' ? 'show' : 'hide']();
                $('#2')[$(this).val() == '2' ? 'show' : 'hide']();
            });
        });
    </script>
    <style>
        .disciplines .emptyColumn{
            width: 100px;
            background-color: transparent;
            border-color: transparent;
        }

        .dayOfWeek{
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
                    <a href="./titlePage.html" class="buttonRed">На главную</a></li>
                <li class="block login">
                    <a href="" class="buttonLogin"><span>logout</span></a>
                </li>
            </ul>

            <ul class="adminButtons">
                <li class="block1"><a href="/create-timetable" class="buttonRed">Создать расписание</a></li>
                <li class="block1"><a href="./modTimetable.html" class="buttonRed">Модифицировать выбранное расписание</a></li>
                <li class="block1"><button type="submit" class="buttonRed" onclick="deleteStudents()">Удалить расписание</button></li>
                <li class="block1"><a href="/create-calendar" class="buttonRed">Создать год</a></li>
            </ul>

            <label for="group">Выбрать группу:&nbsp;&nbsp;</label><select name="" id="group">
            <option>Группа 1</option>
            <option>Группа 2</option>
        </select>

            <label for="course">Выбрать курс:&nbsp;&nbsp;</label><select name="" id="course">
            <option>1</option>
            <option>2</option>
        </select>

            <label for="term">Выбрать семестр:&nbsp;&nbsp;</label><select name="" id="term">
            <option>Семестр 1</option>
            <option>Семестр 2</option>
        </select>
            <div class="block1 timetableSelect">
                <button type="submit" class="buttonRed" onclick="deleteStudents()">Выбрать</button>
            </div>
        </div>
    </nav>
</header>

<div class="form-group">
    <label for="formControlSelect2">Неделя:&nbsp;&nbsp;</label>
    <select class="form-control" id="formControlSelect2" name="view">
        <option></option>
        <option value="1">1 неделя</option>
        <option value="2">2 неделя</option>
        <option value="3">3 неделя</option>
        <option value="4">4 неделя</option>
    </select>
</div>
<div>
    <table class="disciplinesStudent disciplines" id="1" hidden="hidden">
        <tr>
            <th></th>
            <th class="dayOfWeek">Понедельник, 21</th>
            <th class="emptyColumn"></th>
            <th></th>
            <th class="dayOfWeek">Четверг, 21</th>
        </tr>
        <tr>
            <td>1</td>
            <td>Информатика</td>
            <td class="emptyColumn"></td>
            <td>1</td>
            <td>Информатика</td>
        </tr>
        <tr>
            <th></th>
            <th class="dayOfWeek">Вторник, 21</th>
            <th class="emptyColumn"></th>
            <th></th>
            <th class="dayOfWeek">Пятница, 21</th>
        </tr>
        <tr>
            <td>1</td>
            <td>Информатика</td>
            <td class="emptyColumn"></td>
            <td>1</td>
            <td>Информатика</td>
        </tr>
        <tr>
            <th></th>
            <th class="dayOfWeek">Среда, 21</th>
            <th class="emptyColumn"></th>
            <th></th>
            <th class="dayOfWeek">Суббота, 21</th>
        </tr>
        <tr>
            <td>1</td>
            <td>Информатика и информационные технологии</td>
            <td class="emptyColumn"></td>
            <td>1</td>
            <td>Информатика</td>
        </tr>
        <tr>
            <td></td>
            <td></td>
            <td class="emptyColumn"></td>
            <td></td>
            <td></td>
        </tr>
    </table>

    <table class="disciplinesStudent disciplines" id="2" hidden="hidden">
        <tr>
            <th></th>
            <th class="dayOfWeek">Понедельник, 22</th>
            <th class="emptyColumn"></th>
            <th></th>
            <th class="dayOfWeek">Четверг, 21</th>
        </tr>
        <tr>
            <td>1</td>
            <td>Информатика</td>
            <td class="emptyColumn"></td>
            <td>1</td>
            <td>Информатика</td>
        </tr>
        <tr>
            <th></th>
            <th class="dayOfWeek">Вторник, 21</th>
            <th class="emptyColumn"></th>
            <th></th>
            <th class="dayOfWeek">Пятница, 21</th>
        </tr>
        <tr>
            <td>1</td>
            <td>Информатика</td>
            <td class="emptyColumn"></td>
            <td>1</td>
            <td>Информатика</td>
        </tr>
        <tr>
            <th></th>
            <th class="dayOfWeek">Среда, 21</th>
            <th class="emptyColumn"></th>
            <th></th>
            <th class="dayOfWeek">Суббота, 21</th>
        </tr>
        <tr>
            <td>1</td>
            <td>Информатика и информационные технологии</td>
            <td class="emptyColumn"></td>
            <td>1</td>
            <td>Информатика</td>
        </tr>
        <tr>
            <td></td>
            <td></td>
            <td class="emptyColumn"></td>
            <td></td>
            <td></td>
        </tr>
    </table>

</div>

</body>
</html>
