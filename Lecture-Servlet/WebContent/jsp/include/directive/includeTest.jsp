<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="common.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>

<!-- 
	Directive Include는 포함된 jsp파일이 서블릿으로 변환하기 전
	페이지.jsp에 합쳐져 한개의 서블릿파일이 생성됨 
-->

<body>
	msg : <%= msg %><br>
	msg : ${msg}<br>
	<h2>인클루드전</h2>
	<%@ include file="one.jsp" %>	
	<h2>인클루드후</h2>
</body>
</html>
