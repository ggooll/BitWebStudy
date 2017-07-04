<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<hr/>
	<h2>게시글 조회 서비스</h2>
	<hr/>
	<br/>
	
	<table width="80%" border="1">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>글쓴이</th>
			<th>등록일</th>
		</tr>
		<c:forEach var="board" items="${ list }">
			<tr>
				<td>${ board.no }</td>
				<td><c:out value="${ board.title }" /></td>
				<td><c:out value="${ board.writer }" /></td>
				<td>${ board.regDate }</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>














