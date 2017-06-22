<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>새글 등록</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:set var="context" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" href="${context}/css/layout.css">
<link rel="stylesheet" href="${context}/css/board.css">
<script src="${context}/js/board.js"></script>
<script src="${context}/js/checkinput.js"></script>
</head>
<body>

	<!-- xml, include, forward에서 절대경로의 /는 프로젝트 이후부터 -->
	<header>
		<jsp:include page="/jsp/include/topMenu.jsp" />
	</header>

	<section>
		<div align="center">
			<br>
			<hr width="99%">
			<h2>새 게시글 등록</h2>
			<hr width="99%">
			<br>

			<form action="${context}/writeProcess.do" method="post" onsubmit="return doWrite()" name="writeForm" enctype="multipart/form-data">
				<table border="1" style="width: 99%">
					<tr>
						<th>제목</th>
						<td><input type="text" name="title" style="width: 95%"></td>
					</tr>
					<tr>
						<th>작성자</th>
						<td>${sessionScope.loginUser.id}
						<input type="hidden" name="writer" value="${sessionScope.loginUser.id}">
						</td>
					</tr>
					<tr>
						<th>내용</th>
						<td><textarea name="content" style="width: 95%; min-height: 300px; overflow: auto"></textarea></td>
					</tr>
					<tr>
						<th>첨부파일</th>
						<td>
							<input type="file" name="attachFile1" size="40"><br>
							<input type="file" name="attachFile2" size="40">
						</td>
					</tr>
				</table>
				<br> <input type="submit" value="등록"> &nbsp;&nbsp; <input type="button" value="목록" onclick="goList()">
			</form>
		</div>

	</section>

	<footer>
		<%@ include file="/jsp/include/bottom.jsp"%>
	</footer>

</body>
</html>