<%@page import="kr.co.bit.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	MemberVO member = (MemberVO) session.getAttribute("member");
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<%-- 	
	<c:if test="${not empty sessionScope.member}">
		아이디 : <%=member.getId()%><br>
		패스워드 : <%=member.getPassword()%><br>
		<hr>
		아이디 : ${sessionScope.member.id}<br>
		패스워드 : ${sessionScope.member.password}<br>
		<br>
	</c:if>
	<c:if test="${empty sessionScope.member}">
		설정된 세션내용이 없습니다.<br>
	</c:if> 
--%>

	<c:choose>
		<c:when test="${not empty sessionScope.member}">
		아이디 : <%=member.getId()%><br>
		패스워드 : <%=member.getPassword()%><br>
			<hr>
		아이디 : ${sessionScope.member.id}<br>
		패스워드 : ${sessionScope.member.password}<br>
			<br>
			<a href="remove.jsp">세션삭제</a>
		</c:when>
		<c:otherwise>
			<h2>설정된 세션내용이 없습니다.</h2>
			<a href="makeForm.jsp">다시등록</a>
		</c:otherwise>
	</c:choose>


</body>
</html>