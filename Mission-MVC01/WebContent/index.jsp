<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hello</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/layout.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/board.css">
</head>
<body>

	<!-- xml, include, forward에서 절대경로의 /는 프로젝트 이후부터 -->
	<header>
		<jsp:include page="/jsp/include/topMenu.jsp" />
	</header>

	<section>메인페이지</section>

	<footer>
		<%@ include file ="/jsp/include/bottom.jsp" %>
	</footer>

</body>
</html>