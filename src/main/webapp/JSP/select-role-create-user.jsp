<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="../resources/css/style.css">
    <title>Создание пользователя</title>
</head>
<body>
<div id="container">
    <header>
        <h1>Система управления студентами и их успеваемостью</h1>
    </header>
            <h2 class="log_title">Выбор роли для создания пользователя</h2>
            <form action="/select-role" method="post">
                <div class="stModform">
                    <div>Выберите роль</div>
                    <label>
                        <select name="role">
                            <option value="3">Студент</option>
                            <option value="2">Преподаватель</option>
                            <option value="1">Администратор</option>
                        </select>
                    </label>
                </div>

                <div class="block1">
                <button type="submit" class="buttonSelectUser">Выбрать</button>
                </div>
            </form>
</div>
</body>
</html>
