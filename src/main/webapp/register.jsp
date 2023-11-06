<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<html>
<body>
<h2>Login</h2>
<form action ="registeration" method="POST">
    <p>Username: <input type="text" name ="username"/></p>
    <p>Password: <input type="text" name ="password"/></p>
    <p>ConfirmPassword: <input type = "text" name ="cpassword"/></p>
    <%
        if(request.getAttribute("error")!=null){
            out.print("<p>Invalid</p>");
            }
    %>
    <input type = "submit" value="Register"/>
</form>
<form action ="signin" method="POST">
    <p>Already a user ?</p>
    <input type="submit" value="signin" hr/>
</form>
</body>
</html>