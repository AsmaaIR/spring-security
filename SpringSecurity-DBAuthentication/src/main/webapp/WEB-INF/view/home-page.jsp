<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>
	<head>
		<title>Home</title>
	</head>
<body>

	<h2>Hello from Spring Security Home Page</h2>
	<hr>
	
	Spring Security Demo
	
	<hr>
	 <!-- display username and roles -->
	<p>
	  User: <security:authentication property="principal.username"/>
	  <br><br>
	  Role(s): <security:authentication property="principal.authorities"/>
	</p> 
	
	<security:authorize access="hasRole('MANAGER')"> 
		<p>
			<a href="${pageContext.request.contextPath}/managers" >Accessed by Managers Only</a>
		</p>
	</security:authorize>
	
	<security:authorize access="hasRole('ADMIN')"> 
		<p>
			<a href="${pageContext.request.contextPath}/system" >Accessed by Admins Only</a>
		</p>
	</security:authorize>
	
   <security:authorize access="hasRole('LEADER')"> 
		<p>
			<a href="${pageContext.request.contextPath}/leaders" >Accessed by Leaders Only</a>
		</p>
	</security:authorize>
	
	
	<!-- Add a logout button -->
	<form:form action="${pageContext.request.contextPath}/logout"  method="POST">
		<input type="submit" value="Logout" />
	</form:form>

</body>
</html>