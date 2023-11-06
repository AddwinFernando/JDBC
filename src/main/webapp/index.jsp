<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<html>
<body>
<h2>Login</h2>
<form action ="login" method="POST">
    <p>Username: <input type="text" name ="username"/></p>
    <p>Password: <input type="text" name ="password"/></p>
    <%
        if(request.getAttribute("error")!=null){
            out.print("<p>Invalid</p>");
            }
    %>
    <input type = "submit" value="Login"/>
</form>
<form action ="register" method="GET">
    <input type="submit" href="/register.jsp" value="register"/>
</form>
</body>
</html>
