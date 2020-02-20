<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><spring:message code="member.register"/></title>
</head>
<body>
	<h2><spring:message code="member.info"/></h2>
	<!-- value="${formData.email }를 삽입하게될경우,
		 잘못된 입력으로 다시 setp2로 돌아올경우 Command객체에 저장된 값을 다시 보여지게 한다.-->
	<!-- <form action="step3" method="POST"> -->
	
	<!-- 스프링 MVC가 제공해주는 커스텀 form태그를 이용 -->
	<form:form action="step3" commandName="formData">
		<p>
			<label><spring:message code="email"/><br>
				<form:input path="email"/>
			</label>
		</p>
		<p>
			<label><spring:message code="name"/><br> 
				<form:input path="name"/>
			</label>
		</p>
		<p>
			<label><spring:message code="password"/><br> 
				<form:input path="password"/>
			</label>
		</p>
		<p>
			<label><spring:message code="password.confirm"/><br> 
				<form:input path="confirmPassword"/>
			</label>
		</p>
		<input type="submit" value="<spring:message code="register.btn"/>">
	<!--</form>-->
	</form:form>
</body>
</html>