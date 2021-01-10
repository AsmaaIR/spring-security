<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>
<body>

<h2>Managers Page</h2>
<p>Hello Manager: <security:authentication property="principal.username"/></p>

<a href="${pageContext.request.contextPath}/">back to home page</a>

</body>
</html>