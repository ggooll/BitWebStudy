<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String id = (String) session.getAttribute("id");
	String password = (String) session.getAttribute("password");
	// session과 sessionScope
	
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	아이디 : <%= id %><br>
	패스워드 : <%= password%><br>
	<hr>
	아이디 : ${sessionScope.id}<br>
	패스워드 : ${sessionScope.password}<br>
	<br>
	<a href="makeForm.jsp">다시등록</a><br>
	<a href="remove.jsp">세션삭제</a>

</body>
</html>