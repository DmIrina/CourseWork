<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login page</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<a href="hello-servlet">Hello Servlet</a>
<br>
<form action = "login" method = "POST">
    <h2>Заповніть будь-ласка потрібні поля!</h2>
    <h5><input type="radio" name="role" value="doctor" > Лікар</h5>
    <h5><input type="radio" name="role" value="patient"> Пацієнт</h5>
    <br>
    Ваше ім'я: <input type="text" name="username"/>

    <br><br>
    <input type = "submit" value="Увійти"/>
</form>
</body>
</html>