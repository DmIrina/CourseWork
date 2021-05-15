<%--
  Created by IntelliJ IDEA.
  User: 7
  Date: 11.05.2021
  Time: 23:18
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib
        prefix="c"
        uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <title>Managing queue</title>
</head>
<body class="w3-light-grey">
<div class="w3-container w3-blue-grey w3-opacity w3-right-align">
    <h1>Управління чергою</h1>
</div>
<div class="w3-container w3-padding">
    Лікар: <c:out value="${sessionScope.username}"/><br>
    Спеціалізація: <c:out value="${sessionScope.specialisation}"/> <br>
Кабінет: <c:out value="${sessionScope.cabinet}"/> <br>
</div>
<c:choose>
    <c:when test="${empty sessionScope.queue.servedPatient}">
        Прийом ще не розпочато!<br>
    </c:when>
    <c:otherwise>
        Зараз у кабінеті:
        пацієнт: <c:out value="${sessionScope.queue.servedPatient.name}"/>
        номер черги: <c:out value="${sessionScope.queue.servedPatient.numInQueue}"/><br>
    </c:otherwise>
</c:choose>
<hr>
<c:choose>
    <c:when test="${empty sessionScope.queue.list.size()}">
        У черзі немає пацієнтів!
    </c:when>
    <c:otherwise>
        У черзі <c:out value="${sessionScope.queue.list.size()}"/> пацієнтів
        <form action="removePatient" method="Post" class="w3-selection w3-light-grey w3-padding">
            <table>
                <c:forEach items="${sessionScope.queue.list}" var="patient">
                    <tr>
                        <td><input type="checkbox" name="id" value="${patient.numInQueue}"/></td>
                        <td>${patient.numInQueue}</td>
                        <td>${patient.name}</td>
                        <td>${patient.phone}</td>
                    </tr>
                </c:forEach>
            </table>
            <input type="submit" value="Видалити відмічених пацієнтів з черги" class="w3-btn w3-green w3-round-large w3-margin-bottom"/>
        </form>

        <form action="queueService" method="Post">
            <c:if test="${sessionScope.queue.list.size() != 0}">
                <input type="submit" name="next" value="Наступний пацієнт" class="w3-btn w3-blue w3-round-large w3-margin-bottom"/>
            </c:if>
            <br>
            <c:choose>
                <c:when test="${sessionScope.queue.list.size() < sessionScope.queue.maxLength}">
                    <br><input type="submit" name="close" value="Закрити чергу" class="w3-btn w3-green w3-round-large w3-margin-bottom"/>
                </c:when>
                <c:otherwise>
                    <h4> Черга закрита!</h4>
                </c:otherwise>
            </c:choose>
        </form>
    </c:otherwise>
</c:choose>
<%--<ul>--%>
<%--    <c:forEach var="patient" items="${sessionScope.queue.list}">--%>
<%--        <li><c:out value="${patient.numInQueue}"/> <c:out value="${patient.name}"/> <c:out--%>
<%--                value="${patient.phone}"/></li>--%>
<%--    </c:forEach>--%>
<%--</ul>--%>

<%--<form action="removePatient" method="Post" class="w3-selection w3-light-grey w3-padding">--%>
<%--    <table>--%>
<%--        <c:forEach items="${sessionScope.queue.list}" var="patient">--%>
<%--            <tr>--%>
<%--                <td><input type="checkbox" name="id" value="${patient.numInQueue}"/></td>--%>
<%--                <td>${patient.numInQueue}</td>--%>
<%--                <td>${patient.name}</td>--%>
<%--                <td>${patient.phone}</td>--%>
<%--            </tr>--%>
<%--        </c:forEach>--%>
<%--    </table>--%>
<%--    <input type="submit" value="Видалити відмічених пацієнтів з черги" class="w3-btn w3-green w3-round-large w3-margin-bottom"/>--%>
<%--</form>--%>

<%--<form action="queueService" method="Post">--%>
<%--    <c:if test="${sessionScope.queue.list.size() != 0}">--%>
<%--        <input type="submit" name="next" value="Наступний пацієнт" class="w3-btn w3-blue w3-round-large w3-margin-bottom"/>--%>
<%--    </c:if>--%>
<%--    <br>--%>
<%--    <c:choose>--%>
<%--        <c:when test="${sessionScope.queue.list.size() < sessionScope.queue.maxLength}">--%>
<%--            <br><input type="submit" name="close" value="Закрити чергу" class="w3-btn w3-green w3-round-large w3-margin-bottom"/>--%>
<%--        </c:when>--%>
<%--        <c:otherwise>--%>
<%--            <h4> Черга закрита!</h4>--%>
<%--        </c:otherwise>--%>
<%--    </c:choose>--%>
<%--</form>--%>

<div class="w3-container w3-grey w3-opacity w3-right-align w3-padding">
    <button class="w3-btn w3-round-large" onclick="location.href='../'">Повернутися до головної сторінки</button>
</div>

</body>
</html>
