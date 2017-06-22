<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<!-- 
		http://localhost:8000/Lecture-Web/jsp/el/core/exam03_if.jsp
	 -->
	 
	<c:if test="${empty param.type}">
		type파라미터가 전송되지 않았습니다.<br>
	</c:if>

	<!-- 
		http://localhost:8000/Lecture-Web/jsp/el/core/exam03_if.jsp?type=S|U
	 -->
	<c:if test="${param.type == 'S'}">
		<h2>관리자입니다.</h2>
	</c:if>

	<c:if test="${param.type eq 'U'}">
		<h2>일반사용자입니다.</h2>
	</c:if>

</body>
</html>