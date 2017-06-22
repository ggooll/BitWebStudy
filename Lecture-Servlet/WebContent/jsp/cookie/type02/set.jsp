<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("utf-8");
	String cookieName = request.getParameter("cookieName");
	String cookieValue = request.getParameter("cookieValue");
	String cookieAge = request.getParameter("cookieAge");

	/* 한글값까지 처리하기위한 인코딩 */
	cookieName = URLEncoder.encode(cookieName, "utf-8");
	cookieValue = URLEncoder.encode(cookieValue, "utf-8");

	Cookie cookie = new Cookie(cookieName, cookieValue);

	// 쿠키의 유효시간: 0 - 삭제, -1 브라우저 닫을때 삭제, 그외 숫자 = 초 단위
	// 쿠키값에 원하는 제약조건은 알맞게..
	if (cookieAge != null && cookieAge.trim().length() > 0) {
		cookie.setMaxAge(Integer.parseInt(cookieAge) * 60);
	}

	// 서버가 만들지만 저장은 클라이언트가 함
	response.addCookie(cookie);
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>쿠키설정완료</h1>
	<a href="get.jsp">설정된 쿠키 확인</a>

</body>
</html>