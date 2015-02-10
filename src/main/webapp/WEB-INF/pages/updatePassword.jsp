<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec"
           uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><spring:message code="message.updatePassword"/></title>
</head>
<body>
<sec:authorize access="hasRole('ROLE_USER')">
    <div class="container">
        <div class="span12">
            <H1>
                <spring:message code="message.updatePassword"/>
            </H1>

            <form action="<c:url value="/savePassword"/>" method="POST" enctype="utf8">
                <br>
                <tr>
                    <td><label><spring:message code="label.user.password"/></label></td>
                    <td><input name="password" type="password" value=""/></td>
                </tr>
                <button type="submit">
                    <spring:message code="message.updatePassword"/>
                </button>
                <input type="hidden" name="${_csrf.parameterName}"
                       value="${_csrf.token}"/>
            </form>
        </div>
    </div>
</sec:authorize>

</body>

</html>