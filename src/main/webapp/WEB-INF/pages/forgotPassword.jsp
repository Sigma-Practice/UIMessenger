<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec"
           uri="http://www.springframework.org/security/tags" %>
<%@ page session="false" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><spring:message code="message.resetPassword"/></title>
</head>
<body>
<div>
    <div>
        <H1>
            <spring:message code="message.resetPassword"/>
        </H1>

        <form action="<c:url value="/resetPassword"/>" method="POST" enctype="utf8">
            <br>

            <tr>
                <td><label><spring:message code="label.user.email"/></label></td>
                <td><input name="email" type="email" value=""/></td>
            </tr>

            <button type="submit">
                <spring:message code="message.resetPassword"/>
            </button>
            <input type="hidden" name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>
        </form>
        <br> <a href="<c:url value="/registration"/>"><spring:message
            code="label.registration"/></a>
    </div>
</div>
</body>

</html>