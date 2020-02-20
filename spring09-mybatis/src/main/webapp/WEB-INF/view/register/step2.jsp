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
	<!-- 스프링이 제공하는 errors태그를 사용해 에러메시지를 출력 . 
		 지정된 프로퍼티에 에러토그다 존재하면 에러코드에 해당하는 메시지를 출력-->
	<form:form action="step3" commandName="formData">
		<p>
			<label><spring:message code="email"/><br>
				<form:input path="email"/>
				<form:errors path="email"/>
			</label>
		</p>
		<p>
			<label><spring:message code="name"/><br> 
				<form:input path="name"/>
				<form:errors path="name"/>
			</label>
		</p>
		<p>
			<label><spring:message code="password"/><br> 
				<form:input path="password"/>
				<form:errors path="password"/>
			</label>
		</p>
		<p>
			<label><spring:message code="password.confirm"/><br> 
				<form:input path="confirmPassword"/>
				<form:errors path="confirmPassword"/>
			</label>
		</p>
		<input type="submit" value="<spring:message code="register.btn"/>">
	<!--</form>-->
	</form:form>
</body>
</html>