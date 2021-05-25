<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <title>Your queues</title>
</head>
<body class="w3-light-grey">
<div class="w3-container w3-blue-grey w3-opacity">
    <h1>Good morning, <c:out value="${sessionScope.username}"/>!</h1>
</div>
<form action="patientApply" method="Post" class="w3-selection w3-light-grey w3-padding">
    <c:choose>
        <c:when test="${sessionScope.patientList.size() == 0}">
            <br><br>
            <div class="w3-orange w3-round w3-card"><h3>You have not gotten in any line yet!</h3></div>
        </c:when>
        <c:otherwise>
            <h3>Your queues:</h3>
            <table class="w3-table w3-striped w3-border w3-card">
                <tr>
                    <th>Doctor name</th>
                    <th>Specialisation</th>
                    <th>Room</th>
                    <th>Your number in queue</th>
                </tr>
                <c:forEach var="pqueue" items="${sessionScope.patientList}">
                    <tr>
                        <td><c:out value="${pqueue.doctorName}"/></td>
                        <td><c:out value="${pqueue.specialisation}"/></td>
                        <td><c:out value="${pqueue.cabinet}"/></td>
                        <td><c:out value="${pqueue.getNumInQueue(sessionScope.username)}"/></td>
                    </tr>
                </c:forEach>
            </table>
        </c:otherwise>
    </c:choose>

    <c:choose>
        <c:when test="${sessionScope.noPatientList.size() == 0}">
            <br><br>
            <h3>You do not have any available queues :(</h3>
        </c:when>
        <c:otherwise>
            <h3>Available queues for today:</h3>

            <table class="w3-table w3-striped w3-border w3-card">
                <tr>
                    <th>Choose</th>
                    <th>Doctor name</th>
                    <th>Specialisation</th>
                    <th>Room</th>
                    <th>Queue</th>
                    <th>Now in queue №</th>
                </tr>
                <c:forEach var="npqueue" items="${sessionScope.noPatientList}">
                    <c:if test="${npqueue.open}">
                        <tr>
                            <td><input type="checkbox" name="id" value="${npqueue.doctorName}"/></td>
                            <td><c:out value="${npqueue.doctorName}"/></td>
                            <td><c:out value="${npqueue.specialisation}"/></td>
                            <td><c:out value="${npqueue.cabinet}"/></td>
                            <td><c:out value="${npqueue.currentNum}"/>/<c:out value="${npqueue.maxLength}"/></td>
                            <c:choose>
                                <c:when test="${npqueue.servedPatient.numInQueue == null}">
                                    <td>Appointment is not started yet</td>
                                </c:when>
                                <c:otherwise>
                                    <td><c:out value="${npqueue.servedPatient.numInQueue}"/></td>
                                </c:otherwise>
                            </c:choose>
                        </tr>
                    </c:if>
                </c:forEach>
            </table>
            <input type="submit" value="Choose queue"
                   class="w3-btn w3-green w3-round-large w3-margin-bottom"/>
        </c:otherwise>
    </c:choose>
</form>
<div class="w3-container w3-grey w3-opacity w3-right-align w3-padding">
    <form action="index.jsp">
        <button class="w3-btn w3-round-large w3-light-grey" type="submit">Return to main page</button>
    </form>
</div>
</body>
</html>