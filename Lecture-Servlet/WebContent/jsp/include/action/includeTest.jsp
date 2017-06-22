<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="common.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>

<!-- 
	Action include는 Directive Include와 다르게 
	class파일이 실행되는 중간에 포함된다(참조)
	모든 서블릿파일(java), class파일이 생성되며, 
	실행시 class파일을 읽으며 동적으로 호출
	
	Action Include를 사용하게되면, 서로 다른페이지처럼 취급되나
	한 요청흐름이기때문에 request 공유영역을 주로 사용하게 된다.
-->
<body>
	<%
		// action Include는 page영역 공유할 수 없다.
		pageContext.setAttribute("id", "hong");
		request.setAttribute("name", "kildong");
	%>

	id : ${id}<br>
	name : ${name}<br>
	<h2>인클루드전</h2>

	<hr>파라미터 미설정 <hr>
	<jsp:include page="one.jsp" />	
	
	<!-- action include page -->
	<hr>include시 파라미터 설정해서 보내기<hr>
	<jsp:include page="one.jsp" >	
		<jsp:param value="${id}" name="id"/>
		<jsp:param value="23" name="age"/>
	</jsp:include>	
	
	<h2>인클루드후</h2>
	<!-- 페이지영역 탐색 후 리퀘스트를 자동 참조하기 때문에 찍힌다. -->
	phone : ${phone}<br>

</body>
</html>






