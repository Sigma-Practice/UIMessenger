<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec"
           uri="http://www.springframework.org/security/tags" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page session="true" %>
<html>
<head>
    <title></title>
</head>
<body>
<h1>
    <div class="alert alert-error">
        ${param.message}
</h1>
<br>
<a href="<c:url value="/registration" />"><spring:message code="label.registration"/></a>

<c:if test="${param.expired}">
    <a href="<c:url value="/resendRegistrationToken">
			<c:param name="token" value="${param.token}"/>
		 </c:url>">
        <spring:message code="label.resendRegToken"/>
    </a>
</c:if>

</body>
</html>
