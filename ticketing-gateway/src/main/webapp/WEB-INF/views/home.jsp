<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>Home</title>
</head>
<body>
<h2>Welcome Home</h2>

<p>Hello, <sec:authentication property="name"/>!</p>

<sec:authorize access="hasAuthority('ADMIN')">
    <p>You are an admin. You can access <a href="<c:url value='/userForm'/>">User Form</a>.</p>
</sec:authorize>

<p><a href="<c:url value='/logout' />">Logout</a></p>

</body>
</html>