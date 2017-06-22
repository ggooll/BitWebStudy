<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	// session.removeAttribute("member");	
	session.invalidate();
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>


	<%-- 
	JSTL로도 삭제가능 
	<c:remove var="member" scope="session"/> 
	--%>

	<h2>세션 삭제 완료</h2>
	<a href="get.jsp"> 세션 확인 </a>

</body>
</html>