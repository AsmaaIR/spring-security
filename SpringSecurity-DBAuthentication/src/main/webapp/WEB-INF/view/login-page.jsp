<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>  
<html>  
<head>  
    <title>Login Form</title> 
    <style>
		.failed {
			color: red;
		}
	</style> 
</head>  
<h3>Login Form</h3>  
<body>  
    <form:form action="${pageContext.request.contextPath}/authenticateTheUser" method="POST">  
       
       	<c:if test="${param.error != null}">
		
			<i class="failed">Sorry! You entered invalid username/password.</i>
			
		</c:if>
		
       	<p>
       User name: <input type="text" name="username" />     
      	</p>
      	<p>
       Password: <input type="password" name="password" />
        </p>
       <input type="submit" value="Login" />    
    </form:form>  
</body>  
</html>  