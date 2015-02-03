<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Registration</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/registration.css"/>">
</head>
<body>
<div id="content-body" class="content">
    <div class="languageselect">
        <a href="?lang=en">
            <button class="btn btn-primary langbutton">ENG</button>
        </a>
        <a href="?lang=ua">
            <button class="btn btn-primary langbutton">UA</button>
        </a>
    </div>
    <h1>Registration</h1>

    <form modelAttribute="user" method="POST" enctype="utf8">
        <div class="form-group">
            <label><spring:message code="label.user.fullName"/></label>
            <form:input path="fullName" cssClass="form-control input-lg" tabindex="1"/>
            <form:errors path="fullName" element="div"/>
        </div>
        <div class="form-group">
            <label><spring:message code="label.user.login"/></label>
            <form:input path="login" cssClass="form-control input-lg" tabindex="2"/>
            <form:errors path="login" element="div"/>
        </div>
        <div class="form-group">
            <label><spring:message code="label.user.email"/></label>
            <form:input path="email" cssClass="form-control input-lg" tabindex="3"/>
            <form:errors path="email" element="div"/>
        </div>
        <div class="form-group">
            <label><spring:message code="label.user.phone"/></label>
            <form:input path="phone" cssClass="form-control input-lg" tabindex="4"/>
            <form:errors path="phone" element="div"/>
        </div>
        <div class="row">
            <div class="col-xs-12 col-sm-6 col-md-6">
                <div class="form-group">
                    <label><spring:message code="label.user.password"/></label>
                    <form:input path="password" type="password" cssClass="form-control input-lg" tabindex="5"/>
                    <form:errors path="password" element="div"/>
                </div>
            </div>
            <div class="col-xs-12 col-sm-6 col-md-6">
                <div class="form-group">
                    <label><spring:message code="label.user.confirmPassword"/></label>
                    <form:input path="matchingPassword" type="password" cssClass="form-control input-lg" tabindex="6"/>
                    <form:errors element="div"/>
                </div>
            </div>
        </div>
        <div class="form-group">
            <label><spring:message code="label.user.info"/></label>
            <form:textarea path="info" cssClass="form-control input-lg" rows="3" tabindex="7"/>
        </div>
        <button class="btn btn-lg btn-block btn-primary" type="submit">
            <spring:message code="label.createAcc"/>
        </button>
    </form>
</div>

</body>
</html>