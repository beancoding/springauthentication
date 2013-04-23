<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<html>
	<head>
		<title>Home</title>
	</head>
	
	<body>
		<h1>
			Hello world!  
		</h1>
		
		<spring:hasBindErrors name="customer">
		  <div>
		    <c:forEach var="error" items="${errors.globalErrors}">
		      <spring:message message="${error}"></spring:message>
		    </c:forEach>
		  </div>
		</spring:hasBindErrors>
	
		<form:form modelAttribute="customer" action="register" method="POST">
			<form:input path="userName"/>
			<form:errors path="userName" element="div" />
			<form:password path="password"/>
			<form:errors path="password" element="div" />
			<form:button>Hit me</form:button>
		</form:form>
	</body>
</html>
