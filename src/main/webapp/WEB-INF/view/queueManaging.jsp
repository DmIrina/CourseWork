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
    <title>Managing queue</title>
</head>
<body>
<h1>Управління чергою</h1>
Лікар: <c:out value="${sessionScope.username}"/><br>
Спеціалізація: <c:out value="${sessionScope.queue.specialisation}"/> <br>
Кабінет: <c:out value="${sessionScope.queue.cabinet}"/> <br>

<c:choose>
    <c:when test="${empty sessionScope.queue.servedPatient}">
        Прийом ще не розпочато!<br>
    </c:when>
    <c:otherwise>
        Зараз у кабінеті пацієнт <c:out value="${sessionScope.queue.servedPatient.name}"/> з номером черги
        <c:out value="${sessionScope.queue.servedPatient.numInQueue}"/><br>
    </c:otherwise>
</c:choose>
<hr>
<c:choose>
    <c:when test="${sessionScope.queue.list.size() == 0}">
        У черзі немає пацієнтів!
    </c:when>
    <c:otherwise>
        У черзі <c:out value="${sessionScope.queue.list.size()}"/> пацієнтів
    </c:otherwise>
</c:choose>
<ul>
    <c:forEach var="patient" items="${sessionScope.queue.list}">
        <li><c:out value="${patient.numInQueue}"/> <c:out value="${patient.name}"/> <c:out
                value="${patient.phone}"/></li>
    </c:forEach>
</ul>

<form action="queueService" method="Post">
    <br><br>
    <input type="submit" name="next" value="Наступний пацієнт">
    <input type="submit" name="close" value="Закрити чергу">
</form>

</body>
</html>
