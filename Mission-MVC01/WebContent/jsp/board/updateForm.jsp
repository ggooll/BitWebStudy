<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>게시글 수정</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<c:set var="context" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" href="${context}/css/layout.css">
<link rel="stylesheet" href="${context}/css/board.css">
<script src="${context}/js/board.js"></script>
</head>
<body>

	<!-- xml, include, forward에서 절대경로의 /는 프로젝트 이후부터 -->
	<header>
		<jsp:include page="/jsp/include/topMenu.jsp" />
	</header>

	<section>
		<div align="center">
			<hr>
			<h2>수정페이지</h2>
			<hr>
			<br>

			<form action="${context}/updateProcess.do" method="post" onsubmit="return doUpdate()" name="updateForm">
				<input type="hidden" name="no" value="${param.no}">
				<table border="1" style="width: 99%">
					<tr>
						<th width="25%">글번호</th>
						<td>${param.no}</td>
					</tr>
					<tr>
						<th>작성자</th>
						<td>${board.writer}</td>
					</tr>
					<tr>
						<!-- 링크를 타고들어온 값 -->
						<th>제목</th>
						<td><input type="text" name="title" style="width: 100%" value="<c:out value="${board.title}"/>"></td>
					</tr>
					<tr>
						<th>내용</th>
						<td><textarea name="content" style="width: 100%; min-height: 300px; overflow: auto"><c:out value="${board.content}" /></textarea></td>
					</tr>
				</table>
				<br> <input type="submit" value="수정완료">&nbsp;&nbsp; <input type="button" value="목록으로" onclick="goList()"> <br> <br>
			</form>
		</div>
	</section>

	<footer>
		<%@ include file="/jsp/include/bottom.jsp"%>
	</footer>

</body>
</html>