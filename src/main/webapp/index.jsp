<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login page</title>
</head>
<body>
<form action = "login" method = "POST">
    <h1>Заповніть будь-ласка потрібні поля!</h1>
    <h5><input type="radio" name="role" value="doctor" > Лікар</h5>
    <h5><input type="radio" name="role" value="patient" checked> Пацієнт</h5>
    <br>
    Ваше ім'я: <input type="text" name="username"/>

    <br><br>
    <input type = "submit" value="Увійти"/>
</form>
</body>
</html>