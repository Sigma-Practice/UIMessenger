<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page session="true" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/login.css"/>"/>
</head>
<body>
<div class="container content">
    <div class="col-md-3 col-md-offset-4">
        <c:if test="${not empty error}">
            <div class="error">${error}</div>
        </c:if>
        <div class="account-box">
            <c:if test="${not empty msg}">
                <div class="msg">${msg}</div>
            </c:if>
            <form class="form-signin" name="loginForm"
                  action="<c:url value='/j_spring_security_check' />" method='POST'>
                <h1>Online chat</h1>

                <div class="form-group">
                    <input name="username" type="text" class="form-control" placeholder="Email"
                           required autofocus/>
                </div>
                <div class="form-group">
                    <input name="password" type="password" class="form-control" placeholder="Password"
                           required/>
                </div>
                <button class="btn btn-lg btn-block btn-primary" name="submit" type="submit" value="submit">
                    <spring:message code="label.login"/>
                </button>
                <input type="hidden" name="${_csrf.parameterName}"
                       value="${_csrf.token}"/>
            </form>
            <a href="">Forgot your password?</a>

            <div>
                <a href="" class="btn btn-primary btn-block">Create New Account</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>