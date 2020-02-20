<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>설문조사</title>
</head>
<body>
	<%--
		Question q1 = new Question("당신의 역할은?", 
							Arrays.asList("서버", "프론트", "풀스택"));

		Question q2 = new Question("주로 사용하는 개발 도구는?", 
							Arrays.asList("이클립스", "인텔리j", "서브라임"));
		

		Question q3 = new Question("하고싶은말");
		
		return Arrays.asList(q1, q2, q3);
		
		-----------------------------------
		
		List<Question> questions = createQuestions();
		model.addAttribute("questions", questions);
		
		
	 --%>
	
	<h2>설문조사</h2>
	<form method= "post">
	<c:forEach var = "q" items = "${questions}" varStatus = "status">
	<!--  for문에서 사용할 변수 : q/ 받아올 배열이름 :  items / varStatus : 상태용변수 -->
	<!--  questions배열에서 하나씩 뽑아냄 q =>q1, q2, q3 -->
	<p>
		${status.index +1}.${q.title}<br/>
		
		<c:if test = "${q.choice}">
		<!--  q가 null이거나 비지 않았으면-->
			<c:forEach var="option" items = "${q.options}">
			<!--  q(title, options(list<String> 배열)) q.options배열에서 하나씩 option String값뽑아냄-->
			<label>
				<input type = "radio" name = "responses[${status.index}]"
					value = "${option}">
					${option}
			</label>
			</c:forEach>
		</c:if>	
		<c:if test="${!q.choice }">
			<input type="text" name="responses[${status.index }]">
		</c:if>
	<p>
	</c:forEach>
	<p>
		<label>응답자 위치 : <br/>
		<input type = "text" name = "res.location">
		</label>
	</p>
	<p>
		<label>응답자 나이 : <br/>
		<input type = "text" name = "res.age">
		</label>
	</p>
	<input type="submit" value = "전송">
	</form>
</body>
</html>