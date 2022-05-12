<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="../resources/css/style.css">
    <script src="../resources/js/function.js"></script>
    <title>Login page</title>
</head>
<body>
<div id="container">
    <header>
        <h1>Система управления студентами и их успеваемостью</h1>
    </header>
    <main>
        <section class="form_log">
            <h2 class="log_title">Создание пользователя (Студент)</h2>
            <form action="/create-user-student" method="post" id="createStudentForm">
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

                <table class="listStudents">
                    <tr>
                        <th>
                        </th>
                        <th>Фамилия</th>
                        <th>Имя</th>
                        <th>Группа</th>
                        <th>Дата поступления</th>
                    </tr>
                    <c:forEach items="${students}" var="st">
                        <tr>
                            <td><input type="checkbox" name="idStudent" class="checkbox" id="${st.id}" value="${st.id}"></td>
                            <td><label for="${st.id}">${st.surname}</label></td>
                            <td><label for="${st.id}">${st.name}</label></td>
                            <td><label for="${st.id}">${st.group}</label></td>
                            <td><label for="${st.id}"><fmt:formatDate pattern="dd/MM/yyyy" value="${st.date}"/></label></td>
                        </tr>
                        <input type="hidden" name="groupId" value="${st.groupId}">
                    </c:forEach>
                </table>

                <input type="hidden" id="studentIdHidden" name="studentIdHidden">
            </form>
            <button type="submit" class="buttonRed" onclick="createStudent()">Создать</button>
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
