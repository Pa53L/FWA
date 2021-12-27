<%@ page import="edu.school21.cinema.models.User" %><%--
  Created by IntelliJ IDEA.
  User: u19054696
  Date: 21.12.2021
  Time: 21:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="col-md-6" id="user-tools-right">
    <ul>
        <li>
            <a href="profile">
                <%
                    User user = (User)session.getAttribute("USER");
                %>

                <%= user.getFirstName() %>
            </a>
        </li>
        <!-- other tags -->
    </ul>
</div>

</body>
</html>
