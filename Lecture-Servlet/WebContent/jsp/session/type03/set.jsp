<%@page import="kr.co.bit.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<% 
	request.setCharacterEncoding("utf-8");
	String id = request.getParameter("id");
	String password = request.getParameter("password");
	MemberVO member = new MemberVO(id, password);
	
	session.setAttribute("member", member);
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h2>세션 설정 완료</h2>
	<a href="get.jsp">세션 확인</a>

</body>
</html>