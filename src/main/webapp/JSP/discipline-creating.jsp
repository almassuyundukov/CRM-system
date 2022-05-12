<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Создание дисциплины</title>
  <link rel="stylesheet" href="../resources/css/style.css">
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
    </div>
  </nav>
</header>
<div class="stModform">
  <form method="post" action="/discipline-create">
  <span>Для создания дисциплины, заполните все поля и нажмите кнопку "Создать"</span><br>
  <table width="100%" cellspacing="0" cellpadding="4">
    <tr>
      <td align="right" width="100">Название</td>
      <td><input type="text" name="discipline" maxlength="50" size="20"></td>
    </tr>
  </table>
  <button type="submit" class="btn btn1">Создать</button>
  </form>
</div>
</body>
</html>

