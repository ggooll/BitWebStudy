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
	out에 대한(요청한사람이 응답받을 페이지에 대한)
	참조가 아예 forward한 페이지로 넘어감
	따라서 결과화면과 결과화면종료가 출력되지 않음
	(출력하려 했던 것이 전송되지 않음?)
	
	forward와 include했을 때, 출력결과는 다르지만 URL은 같다.
	forward는 admin.jsp와 user.jsp가 응답한 것이지만
	
	include는 해당 jsp들의 일을 수행한 후 다시 돌아와 forwardSet.jsp가 응답한 것
 -->

	<h1>결과 화면</h1>
	<c:if test="${param.id == 'admin'}">
		<jsp:include page="admin.jsp" />
	</c:if>

	<c:if test="${param.id ne 'admin'}">
		<c:set var="id" value="${param.id}" scope="request" />
		<jsp:forward page="user.jsp">
			<jsp:param name="id" value="${param.id}" />
		</jsp:forward>
	</c:if>

	<h1>결과 화면 종료</h1>
</body>
</html>