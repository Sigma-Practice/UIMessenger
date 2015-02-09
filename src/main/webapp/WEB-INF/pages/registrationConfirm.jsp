<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec"
           uri="http://www.springframework.org/security/tags" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page session="true" %>
<c:if test="${param.token != null}">
    <spring:message code="token.message"><c:out value="${param.token}"></c:out></spring:message>
</c:if>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ГЕА-8">
    <title></title>
</head>
<body>

<spring:message code="message.regSucc"/>
<a href="<c:url value="/login" />"><spring:message code="label.login"/></a>
</body>
</html>