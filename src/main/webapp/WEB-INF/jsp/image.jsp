<%--
  Created by IntelliJ IDEA.
  User: u19054696
  Date: 16.01.2022
  Time: 23:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%request.setCharacterEncoding("UTF-8");%>
<html>
<head>
    <title>Просмотр фото</title>
</head>
<body>
<img src="data:image/png;base64,<%=request.getAttribute("IMAGE")%>" style="height: 30%; width: 30%;">
</body>
</html>
