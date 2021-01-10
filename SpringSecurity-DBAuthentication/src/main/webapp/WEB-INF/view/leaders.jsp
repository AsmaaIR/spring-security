<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>
<body>

<h2>Leaders Page</h2>
<p>Hello Leader : <security:authentication property="principal.username"/></p>

<a href="${pageContext.request.contextPath}/">back to home page</a>

</body>
</html>