<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Login</title>
</head>
<body>
  <h1>Login</h1>

  <!-- Optional feedback messages -->
  <c:if test="${param.error eq 'true'}">
    <p>Invalid username or password.</p>
  </c:if>
  <c:if test="${param.logout eq 'true'}">
    <p>You have been logged out.</p>
  </c:if>
  <c:if test="${not empty param.registered}">
    <p>Registration successful. Please log in.</p>
  </c:if>

  <form method="post" action="<c:url value='/login'/>">
    <div>
      <label for="username">Username</label>
      <input id="username" name="username" type="text" required />
    </div>

    <div>
      <label for="password">Password</label>
      <input id="password" name="password" type="password" required />
    </div>

    <!-- CSRF token -->
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

    <button type="submit">Sign in</button>
  </form>

  <p><a href="<c:url value='/signup'/>">Sign up</a></p>
</body>
</html>