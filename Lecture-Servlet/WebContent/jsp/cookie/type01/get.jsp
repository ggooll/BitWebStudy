<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	Cookie[] cookies = request.getCookies();
	StringBuilder sb = new StringBuilder();

	if (cookies != null) {
		for (Cookie cookie : cookies) {
			String cName = cookie.getName();
			String cValue = cookie.getValue();

			sb.append("쿠키이름 : " + cName + ", 쿠키값 : " + cValue + "<br>");
		}
	} else {
		sb.append("설정된 쿠키 없음 <br>");
	}

	pageContext.setAttribute("cookieInfo", sb.toString());
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>설정된 쿠키 정보</h2>
	${cookieInfo}<br>
	<a href="makeForm.jsp">쿠키설정 이름</a>
</body>
</html>