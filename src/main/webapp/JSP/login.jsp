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
    <title>Login page</title>
</head>
<body>
<div id="container">
    <header>
        <h1>Система управления студентами и их успеваемостью</h1>
    </header>
    <main>
        <section class="form_log">
            <h2 class="log_title">Вход в систему</h2>
            <form action="/login" method="post">
                <div class="line">
                    <div>Логин</div>
                    <label>
                        <input type="text" name="login">
                    </label>
                </div>
                <div class="line">
                    <div>Пароль</div>
                    <label>
                        <input type="text" name="password">
                    </label>
                </div>
                <div class="line">
                    <div>Выберите роль</div>
                    <label>
                        <select name="role">
                            <option value="3">Студент</option>
                            <option value="2">Преподаватель</option>
                            <option value="1">Администратор</option>
                        </select>
                    </label>
                </div>

                <input class="button_enter" type="submit" value="Войти">
            </form>
        </section>
    </main>

    <c:if test="${error == '1'}">
        <h4>Неверный логин или пароль или роль!!!</h4>
    </c:if>

    <footer>
        <div>&copy; 2022 Alex Black</div>
    </footer>
</div>
</body>
</html>