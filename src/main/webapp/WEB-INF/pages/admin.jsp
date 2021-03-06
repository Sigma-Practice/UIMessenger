<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<head>
    <title></title>
</head>
<body>
  Admin page
  <c:url value="/j_spring_security_logout" var="logoutUrl" />
  <form action="${logoutUrl}" method="post" id="logoutForm">
      <input type="hidden" name="${_csrf.parameterName}"
             value="${_csrf.token}" />
  </form>
  </form>
  <script>
      function formSubmit() {
          document.getElementById("logoutForm").submit();
      }
  </script>

  <c:if test="${pageContext.request.userPrincipal.name != null}">
      <h2>
          Welcome : ${pageContext.request.userPrincipal.name} | <a
              href="javascript:formSubmit()"> Logout</a>
      </h2>
  </c:if>
</body>
</html>
