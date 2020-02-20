<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><spring:message code="member.register"/></title>
</head>
<body>
	<!--${registerRequest.name}Coomand이름 변경전-->
	<p><<spring:message code="register.done" argument=${formData.name }/></p>
			<!--Command객체로 지정된 RegisterRequest클래스의 첫글자만 소문자로 바꾸면
				커맨드 객체를 참조할수 있게 된다.   -->
	<p><a href="<c:url value='/main'/>">[<spring:message code="go.main"/>]</a></p>
</body>
</html>