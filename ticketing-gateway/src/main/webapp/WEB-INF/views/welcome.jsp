<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head><title>Dashboard</title></head>
<body>
  <h2>Welcome to your dashboard</h2>
  <p>Logged in as: ${userEmail}</p>

  <form method="post" action="<c:url value='/logout'/>">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <button type="submit">Logout</button>
  </form>
</body>
</html>