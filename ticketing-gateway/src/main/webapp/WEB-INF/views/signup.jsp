<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Sign Up</title>
</head>
<body>
<h2>Create a New Account</h2>

<c:if test="${not empty message}">
    <div style="color:red">${message}</div>
</c:if>

<form action="<c:url value='/signup' />" method="post">
    <table>
        <tr>
            <td>Email:</td>
            <td><input type="email" name="email" required/></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type="password" name="password" required minlength="6"/></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Register"/>
            </td>
        </tr>
    </table>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>

<p>Already have an account? <a href="<c:url value='/login'/>">Login</a></p>

</body>
</html>