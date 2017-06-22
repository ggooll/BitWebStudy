<%@page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	// 클라이언트의 브라우저에 존재하는 Cookie를 모두 가져옴
	Cookie[] cookies = request.getCookies();
	StringBuilder sb = new StringBuilder();

	if (cookies != null) {
		for (Cookie cookie : cookies) {
			String cookieName = cookie.getName();
			String cookieValue = cookie.getValue();

			/* 인코딩 된 값을 디코딩 */
			cookieName = URLDecoder.decode(cookieName, "utf-8");
			cookieValue = URLDecoder.decode(cookieValue, "utf-8");

			sb.append("쿠키이름 : " + cookieName + ", 쿠키값 : " + cookieValue + "<br>");
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
	${cookieInfo}
	<br>
	<a href="remove.jsp">쿠키삭제</a>
</body>
</html>