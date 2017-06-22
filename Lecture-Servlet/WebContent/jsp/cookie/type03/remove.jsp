<%@page import="java.net.URL"%>
<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
	// 쿠키를 다시 덮어쓰기하여
	Cookie cookie = new Cookie("type1", URLEncoder.encode("/에 path를 설정", "utf-8"));
	cookie.setPath("/");
	cookie.setMaxAge(0);

	Cookie cookie2 = new Cookie("type2", URLEncoder.encode("/Lecture-Servlet/jsp/cookie밑에 설정", "utf-8"));
	cookie2.setPath("/Lecture-Servlet/jsp/cookie");
	cookie2.setMaxAge(0);
	
	Cookie cookie3 = new Cookie("type3", URLEncoder.encode("/Lecture-Servlet/jsp/cookie/type03 밑에 설정", "utf-8"));
	cookie3.setPath("/Lecture-Servlet/jsp/cookie/type03");
	cookie3.setMaxAge(0);
	
	Cookie cookie4 = new Cookie("type4", URLEncoder.encode("/Lecture-Servlet/jsp/cookie/type02밑에 설정", "utf-8"));
	cookie4.setPath("/Lecture-Servlet/jsp/cookie/type02");
	cookie4.setMaxAge(0);
	
	response.addCookie(cookie);
	response.addCookie(cookie2);
	response.addCookie(cookie3);
	response.addCookie(cookie4);
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>쿠키삭제완료</h1>
	<a href="get.jsp">쿠키 확인</a>

</body>
</html>