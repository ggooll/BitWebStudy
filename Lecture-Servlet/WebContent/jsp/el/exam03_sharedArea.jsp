<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	// 자바에서 표현하는 공유영역 : pageContext, request, session, application
	// 공유영역.setAttribute(이름, 값);

	pageContext.setAttribute("name", "페이지컨텍스트영역");
	request.setAttribute("name", "리퀘스트영역");
	session.setAttribute("name", "세션영역");
	application.setAttribute("name", "어플리케이션영역");
	
	String pageName = (String)pageContext.getAttribute("name");
	String requestName = (String)request.getAttribute("name");
	String sessionName = (String)session.getAttribute("name");
	String applicationName = (String)application.getAttribute("name");
	
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공유 영역</title>
</head>
<body>

	<!-- scriptlet으로 조회 -->
	pageName : <%= pageName %><br>
	requestName : <%= requestName %><br>
	sessionName : <%= sessionName %><br>
	applicationName : <%= applicationName %><br>
	
	<hr>
	
	<!-- EL로 스코프 접근 -->
	pageScope : ${name}<br>
	requestScope : ${requestScope.name}<br>
	sessionScope : ${sessionScope.name}<br>
	applicationScope : ${applicationScope.name}<br>

</body>
</html>