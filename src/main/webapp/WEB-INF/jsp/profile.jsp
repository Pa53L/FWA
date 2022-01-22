<%@ page import="edu.school21.cinema.models.User" %>
<%@ page import="java.io.File" %>
<%@ page import="edu.school21.cinema.models.AuthHistory" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: u19054696
  Date: 21.12.2021
  Time: 21:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
.card {
    box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
    max-width: 300px;
    margin: auto;
    text-align: center;
}

.title {
    color: grey;
    font-size: 18px;
}

button {
    border: none;
    outline: 0;
    display: inline-block;
    padding: 8px;
    color: white;
    background-color: #000;
    text-align: center;
    cursor: pointer;
    width: 100%;
    font-size: 18px;
}

a {
    text-decoration: none;
    font-size: 22px;
    color: black;
}

button:hover, a:hover {
    opacity: 0.7;
}
</style>
<head>
    <title>Title</title>
</head>
<body>

<div class="card">
    <%
        User user = (User)session.getAttribute("USER");
    %>
    <img src="https://www.serietotaal.nl/images/nieuws/t/qal91uii1wor-full.jpg" alt="John" style="width:100%">
    <h1><%= user.getFirstName() %> <%= user.getLastName() %></h1>
    <p class="title"><%= user.getPhoneNumber() %></p>
    <form enctype="multipart/form-data" method="post" action="/profile">
        <p><input type="file" name="image">
            <input type="submit" value="Отправить"></p>
    </form>
</div>
<div class="row">
    <%
        ArrayList<AuthHistory> authHistories = (ArrayList<AuthHistory>)request.getSession().getAttribute("auth");
        request.setAttribute("auths", authHistories);
        request.setAttribute("files", new File((String) request.getAttribute("uploadPath")).listFiles());
    %>
    <div class="col-sm-4">
        <h1>История входов</h1>
        <table style="width: 100%">
            <th>Операция</th>
            <th>Время</th>
            <th>IP-Адрес</th>
            <c:forEach items="${requestScope.auths}" var="auth">
                <tbody>
                <td>
                    <c:if test="${auth.type == 'sign_up'}">Регистрация</c:if>
                    <c:if test="${auth.type == 'sign_in'}">Вход с устройства</c:if>
                </td>
                <td>${auth.time}</td>
                <td>
                    <c:if test="${auth.address == '0:0:0:0:0:0:0:1'}">127.0.0.1</c:if>
                    <c:if test="${auth.address != '0:0:0:0:0:0:0:1'}">${auth.address}</c:if>
                </td>
                </tbody>
            </c:forEach>
        </table>
    </div>
    <div class="col-sm-8">
        <h1>История загрузок</h1>
        <table style="width: 100%">
            <th>Имя файла</th>
            <th>Размер</th>
            <th>MIME</th>
            <c:forEach items="${requestScope.files}" var="file">
                <tbody>
                <td>
                    <a href="images/${file.getName()}" target="_blank">${file.getName()}</a>
                </td>
                <td>
                        ${file.length()} kb
                </td>
                <td>
                    image/jpg
                </td>
                </tbody>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
