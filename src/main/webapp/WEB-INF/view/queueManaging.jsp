<%@ taglib
        prefix="c"
        uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <title>Managing queue</title>
</head>
<body class="w3-light-grey">
<div class="w3-container w3-blue-grey w3-opacity w3-right-align">
    <h1>Queue managing</h1>
</div>
<div class="w3-container w3-padding">
    Doctor: <c:out value="${sessionScope.username}"/><br>
    Specialisation: <c:out value="${sessionScope.queue.specialisation}"/> <br>
    Room: <c:out value="${sessionScope.queue.cabinet}"/> <br>

    <c:choose>
        <c:when test="${empty sessionScope.queue.servedPatient}">
            Appointment is not started yet<br>
        </c:when>
        <c:otherwise>
            Now in room №<c:out value="${sessionScope.queue.servedPatient.numInQueue}"/>:
            <c:out value="${sessionScope.queue.servedPatient.name}"/>
        </c:otherwise>
    </c:choose>
    <hr>
    <c:choose>
        <c:when test="${sessionScope.queue.list.size() == 0}">
            There are no patients in the queue!
        </c:when>
        <c:otherwise>
            There are <c:out value="${sessionScope.queue.list.size()}"/> patient(-s) in queue
            <form action="removePatient" method="Post" class="w3-selection w3-light-grey w3-padding">
                <table>
                    <c:forEach items="${sessionScope.queue.list}" var="patient">
                        <tr>
                            <td><input type="checkbox" name="id" value="${patient.numInQueue}"/></td>
                            <td>№${patient.numInQueue}</td>
                            <td>${patient.name}</td>
                        </tr>
                    </c:forEach>
                </table>
                <input type="submit" value="Remove chosen patients"
                       class="w3-btn w3-green w3-round-large w3-margin-bottom"/>
            </form>

            <form action="queueService" method="Post">
                <c:if test="${sessionScope.queue.list.size() != 0}">
                    <input type="submit" name="next" value="Next patient"
                           class="w3-btn w3-light-green w3-round-large w3-margin-bottom"/>
                </c:if>
                <br>
                <c:choose>
                    <c:when test="${sessionScope.queue.list.size() < sessionScope.queue.maxLength
                                   || sessionScope.queue.open && sessionScope.queue.list.size() == 0
                                   || sessionScope.queue.list.size() == 1}">
                        <br><input type="submit" name="close" value="Close the queue"
                                   class="w3-btn w3-light-green w3-round-large w3-margin-bottom"/>
                    </c:when>
                    <c:otherwise>
                        <h4>The queue is closed!</h4>
                    </c:otherwise>
                </c:choose>
            </form>
        </c:otherwise>
    </c:choose>
</div>
<div class="w3-container w3-grey w3-opacity w3-right-align w3-padding">
    <form action="index.jsp">
        <button class="w3-btn w3-round-large w3-light-grey" type="submit">Return to main page</button>
    </form>
</div>
</body>
</html>
