<%@ page import="java.net.Inet4Address" %>
<%@ page import="Util.MessageRepository" %><%--
  Created by IntelliJ IDEA.
  User: Tnecesoc
  Date: 2016/9/26
  Time: 12:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All system go</title>
</head>
<body style="text-align: center">

    <%

        String password = request.getParameter("password");

        if (MessageRepository.setPassword(password)) {

            request.setAttribute("isPasswordCorrect", "");
            request.removeAttribute("isPasswordIncorrect");

        } else {

            request.setAttribute("isPasswordIncorrect", "");

        }

        request.getRequestDispatcher("index.jsp").forward(request, response);

    %>

</body>
</html>
