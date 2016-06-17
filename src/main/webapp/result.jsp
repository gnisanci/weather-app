<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>

<c:if test="${not empty error}">
    <h2>Error</h2>
    <font color="red">
        <c:out value="${error}"/>
    </font>
</c:if>
<c:if test="${not empty weatherData}">
    <h2>Result</h2>
    <br>
    <c:if test="${weatherData.getCod() == 200}">
        <c:out value="${weatherData.getResult()}"/>
    </c:if>

    <c:if test="${weatherData.getCod() != 200}">
        <c:out value="${weatherData.getError()}"/>
    </c:if>

</c:if>
<br><br>
<a href="index.jsp"><< See weather info for another city</a>
</body>
</html>

