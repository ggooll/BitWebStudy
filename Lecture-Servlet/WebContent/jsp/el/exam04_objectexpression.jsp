<%@page import="kr.co.bit.vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
	// EL에서의 객체 표현은
	// Map 객체이거나, 자바빈즈 클래스(VO형태)만 가능하다?
	BoardVO b = new BoardVO();
	b.setNo(1);
	b.setTitle("제목");
	b.setWriter("작성자");
	b.setContent("내용");
	b.setRegDate("20170613");
	
	// VO를 공유영역들에 등록
	pageContext.setAttribute("board", b);	
	request.setAttribute("board", b);	
	session.setAttribute("board", b);	
	application.setAttribute("board", b);	
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>EL 객체 표현</title>
</head>
<body>

	<!-- 스크립틀릿으로 뽑아내는 경우 -->
	<h2> scriptlet </h2>
	<% BoardVO board = (BoardVO)pageContext.getAttribute("board"); %>
	<%= board.getNo() %><br>
	<%= board.getTitle() %><br>
	<%= board.getWriter() %><br>
	<%= board.getContent() %><br>
	<%= board.getRegDate() %><br>
	
	<!-- EL으로 등록된 VO 접근 -->
	<h2>pageScope</h2>
	${board.no}<br>
	${board.title}<br>
	${board.writer}<br>
	${board.content}<br>
	${board.regDate}<br>
	<hr>

	<h2>requestScope</h2>
	${requestScope.board.no}<br>
	${requestScope.board.title}<br>
	${requestScope.board.writer}<br>
	${requestScope.board.content}<br>
	${requestScope.board.regDate}<br>
	<hr>

	<h2>sessionScope</h2>
	${sessionScope.board.no}<br>
	${sessionScope.board.title}<br>
	${sessionScope.board.writer}<br>
	${sessionScope.board.content}<br>
	${sessionScope.board.regDate}<br>
	<hr>
	
	<h2>applicationScope</h2>
	${applicationScope.board.no}<br>
	${applicationScope.board.title}<br>
	${applicationScope.board.writer}<br>
	${applicationScope.board.content}<br>
	${applicationScope.board.regDate}<br>
	<hr>
</body>
</html>