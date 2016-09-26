<%@ page import="java.net.Inet4Address" %>
<%@ page import="Util.MessageRepository" %><%--
  Created by IntelliJ IDEA.
  User: Tnecesoc
  Date: 2016/9/6
  Time: 9:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Initialization</title>
        <style type="text/css">

            .Absolute-Center {
                text-align: center;
                width: 50%;
                height: 50%;
                overflow: auto;
                margin: auto;
                position: absolute;
                top: 0; left: 0; bottom: 0; right: 0;
            }

        </style>
    </head>
    <body>

        <%
            request.setAttribute("option", "hidden=\"\"");

            if (request.getAttribute("isPasswordIncorrect") != null) {
                request.setAttribute("option", "");
            }

            if (request.getAttribute("isPasswordCorrect") != null || MessageRepository.isPasswordConfirmed()) {
                request.setAttribute("isInitializing", "hidden=\"\"");
                request.setAttribute("isOnAir", "");
            } else {
                request.setAttribute("isInitializing", "");
                request.setAttribute("isOnAir", "hidden=\"\"");
            }

        %>

        <div ${isInitializing} class="Absolute-Center">
            <h1>Database interface server is on initializing</h1>
            <br/>
            <br/>
            Please confirm the database password first.
            <form method="post" action="password-confirmed.jsp">
                <br/>
                <br/>
                <input type="password" title="password" name="password"><br/>
                <br/>
                <input type="submit">
            </form>
            <p ${option} style="color: red;">Password is incorrect.</p>
        </div>

        <div ${isOnAir} class="Absolute-Center">
            <h1>Database interface server is on air</h1>
            <br/>
            <p>Visit it at <%= Inet4Address.getLocalHost().getHostAddress() %></p>
        </div>

    </body>
</html>
