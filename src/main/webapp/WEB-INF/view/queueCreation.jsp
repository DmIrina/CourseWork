<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 7
  Date: 11.05.2021
  Time: 23:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <title>New queue</title>
</head>

<body class="w3-light-grey">
<div class="w3-container w3-blue-grey w3-opacity w3-right-align">
    <h1>У вас немає черг на сьогодні!</h1>
</div>

<c:if test="${sessionScope.repeatCreation}">
    <div class="w3-panel w3-orange w3-display-container w3-card-4 w3-round">
        Черга не створена. Перевірте, чи не зайнятий кабінет!
    </div>
</c:if>

<div class="w3-container w3-padding">
    Лікар: <c:out value="${sessionScope.username}"/><br>
</div>

<form action="createQueue" method="Post" class="w3-selection w3-light-grey w3-padding">
    <div class="w3-container w3-padding">
        <label>Максимальна кількість пацієнтів у черзі: </label>
        <select name="maxLength">
            <option value="5">5</option>
            <option value="6">6</option>
            <option value="7">7</option>
            <option value="8">8</option>
            <option value="9">9</option>
            <option value="10">10</option>
            <option value="11">11</option>
            <option value="12">12</option>
            <option value="13">13</option>
            <option value="14">14</option>
            <option value="15">15</option>
        </select> <br>
        <label>Номер кабінету: </label> <input type="text" name="cabinet"><br>
        Спеціальність: <input type="text" list="specialisation" name="specialisation"
                              class="w3-selection w3-light-grey w3-padding"/>
        <datalist id="specialisation">
            <option value="therapist">терапевт</option>
            <option value="dentist">стоматолог</option>
            <option value="surgeon">хірург</option>
            <option value="ophthalmologist">офтальмолог</option>
            <option value="neurologist">невропатолог</option>
            <option value="family doctor">сімейний лікар</option>
            <option value="gynecologist">гінеколог</option>
            <option value="ортодонт">ортодонт</option>
            <option value="психолог">психолог</option>


        </datalist>
        <input type="submit" name="create" value="Створити чергу"
               class="w3-btn w3-blue w3-round-large w3-margin-bottom">
    </div>
</form>
<div class="w3-container w3-grey w3-opacity w3-right-align w3-padding">
    <form action="index.jsp">
        <button class="w3-btn w3-round-large w3-light-grey" type="submit">Повернутися до головної сторінки</button>
    </form>
</div>
</body>
</html>
