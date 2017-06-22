<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("utf-8");
	String cookieName = request.getParameter("cookieName");
	String cookieValue = request.getParameter("cookieValue");

	Cookie cookie = new Cookie(cookieName, cookieValue);

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