<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: 7
  Date: 15.05.2021
  Time: 14:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Your queues</title>
</head>
<body>
<h1>Доброго дня, <c:out value="${sessionScope.username}"/>!</h1>
<form action="patientApply" method="Post" class="w3-selection w3-light-grey w3-padding">
    Заповніть, будь ласка, свій номер телефону для зв'язку з вами: <input type="text" name="phone"/>
    <c:choose>
        <c:when test="${sessionScope.patientList.size() == 0}">
            <br><br>
            Ви не зайняли поки що жодної черги!
        </c:when>
        <c:otherwise>
            <br><br>
            Ваші черги:
            <table>
                <c:forEach var="pqueue" items="${sessionScope.patientList}">
                    <li><c:out value="${pqueue.doctorName}"/>
                        <c:out value="${pqueue.specialisation}"/>
                        <c:out value="${pqueue.cabinet}"/>
                        <c:out value="${pqueue.getNumInQueue(sessionScope.username)}"/></li>
                </c:forEach>
            </table>
        </c:otherwise>
    </c:choose>

    <c:choose>
        <c:when test="${sessionScope.noPatientList.size() == 0}">
            <br><br>
            <h3>У вас немає доступних черг :(</h3>
        </c:when>
        <c:otherwise>
            <h3>Доступні черги на сьогодні:</h3>
            <table>
                <c:forEach var="npqueue" items="${sessionScope.noPatientList}">
                    <c:if test="${npqueue.open}">
                        <tr>
                            <th>    Ім'я доктора</th>
                            <th>    Фах</th>
                            <th>    Кабінет</th>
                            <th>    Максимальна кількість пацієнтів</th>
                            <th>    Зараз у кабінеті №</th>
                        </tr>
                        <tr>
                            <td><input type="checkbox" name="id" value="${npqueue.doctorName}"/></td>
                            <td><c:out value="${npqueue.doctorName}"/></td>
                            <td><c:out value="${npqueue.specialisation}"/></td>
                            <td><c:out value="${npqueue.cabinet}"/></td>
                            <td><c:out value="${npqueue.maxLength}"/></td>
                            <c:choose>
                                <c:when test="${npqueue.servedPatient.numInQueue == 0}">
                                    <td>-</td>
                                </c:when>
                                <c:otherwise>
                                    <td><c:out value="${npqueue.servedPatient.numInQueue}"/></td>
                                </c:otherwise>
                            </c:choose>
                        </tr>
                    </c:if>
                </c:forEach>
            </table>
            <input type="submit" value="Обрати чергу"
                   class="w3-btn w3-green w3-round-large w3-margin-bottom"/>
        </c:otherwise>
    </c:choose>
</form>
<div class="w3-container w3-grey w3-opacity w3-right-align w3-padding">
    <form action="index.jsp">
        <button class="w3-btn w3-round-large w3-light-grey" type="submit">Повернутися до головної сторінки</button>
    </form>
</div>
</body>
</html>