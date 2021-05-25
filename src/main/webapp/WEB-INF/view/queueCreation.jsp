<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <title>New queue</title>
</head>

<body class="w3-light-grey">
<div class="w3-container w3-blue-grey w3-opacity w3-right-align">
    <h1>You do not have queues</h1>
</div>

<c:if test="${sessionScope.repeatCreation}">
    <div class="w3-panel w3-orange w3-display-container w3-card-4 w3-round">
        Queue was not made. Check if room is not occupied!
    </div>
</c:if>

<div class="w3-container w3-padding">
    Doctor: <c:out value="${sessionScope.username}"/><br>
</div>

<form action="createQueue" method="Post" class="w3-selection w3-light-grey w3-padding">
    <div class="w3-container w3-padding">
        <label>Max queue length: </label>
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
        <label>Room №: </label> <input type="text" name="cabinet"><br>
        Specialisation: <input type="text" list="specialisation" name="specialisation"
                              class="w3-selection w3-light-grey w3-padding"/>
        <datalist id="specialisation">
            <option value="therapist">therapist</option>
            <option value="dentist">dentist</option>
            <option value="surgeon">surgeon</option>
            <option value="ophthalmologist">ophthalmologist</option>
            <option value="neurologist">neurologist</option>
            <option value="family doctor">family doctor</option>
            <option value="gynecologist">gynecologist</option>
            <option value="orthodontist">orthodontist</option>
            <option value="psychologist">psychologist</option>


        </datalist>
        <input type="submit" name="create" value="Create a queue"
               class="w3-btn w3-blue w3-round-large w3-margin-bottom">
    </div>
</form>
<div class="w3-container w3-grey w3-opacity w3-right-align w3-padding">
    <form action="index.jsp">
        <button class="w3-btn w3-round-large w3-light-grey" type="submit">Return to main page</button>
    </form>
</div>
</body>
</html>
