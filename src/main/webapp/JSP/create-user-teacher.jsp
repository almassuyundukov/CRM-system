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
            <h2 class="log_title">Создание пользователя (Учитель)</h2>
            <form action="/create-user-teacher" method="post">
                <div class="line">
                    <div>Логин</div>
                    <label>
                        <input type="text" name="login" autocomplete="off">
                    </label>
                </div>
                <div class="line">
                    <div>Пароль</div>
                    <label>
                        <input type="text" name="password" autocomplete="off">
                    </label>
                </div>

                <div class="line">
                    <div>Выберите дисципину</div>
                    <label>
                        <select name="idDiscipline">
                            <c:forEach items="${disciplines}" var="disc">
                            <option value="${disc.id}">${disc.discipline}</option>
                            </c:forEach>
                        </select>
                    </label>
                </div>

                <div class="block1">
                    <button type="submit" class="buttonSelectUser">Создать</button>
                </div>
            </form>
        </section>
    </main>

    <c:if test="${error == '1'}">
        <h4>Пользователь с таким логином уже существует!</h4>
    </c:if>

    <footer>
        <div>&copy; 2022 Almas Suyundukov</div>
    </footer>
</div>
</body>
</html>
