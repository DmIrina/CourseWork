<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <title>Login page</title>
</head>
<body class="w3-light-grey">
<div class="w3-container w3-padding">
    <form action="login" method="POST" class="w3-selection w3-light-grey w3-padding">
        <div class="w3-container w3-blue-grey w3-opacity">
            <h1>Fill required fields!</h1>
        </div>
        <h5><input class="w3-radio" type="radio" name="role" value="doctor">Doctor</h5>
        <h5><input class="w3-radio" type="radio" name="role" value="patient" checked>Patient</h5>
        <br>
        Your name: <input type="text" name="username"/>
        <br><br>
        <input type="submit" value="Log in" class="w3-btn w3-green w3-round-large w3-margin-bottom"/>
    </form>
    <c:if test="${sessionScope.username == null}">
        <div class="w3-panel w3-orange w3-display-container w3-card-4 w3-round">
            Enter your name!
        </div>
    </c:if>
</div>
</body>
</html>