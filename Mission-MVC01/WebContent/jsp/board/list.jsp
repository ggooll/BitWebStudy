<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<title>게시글 목록</title>
<c:set var="context" value="${pageContext.request.contextPath}" />
<script src="http://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="${context}/js/board.js"></script>
<link rel="stylesheet" href="${context}/css/layout.css">
<link rel="stylesheet" href="${context}/css/board.css">

<script>
	function doDetailAction(no) {
		<c:choose>
		<c:when test="${not empty sessionScope.loginUser}">
		location.href = "detail.do?no=" + no;
		</c:when>
		<c:otherwise>
		alert('로그인 후 이용할 수 있습니다');
		location.href = "login.do";
		</c:otherwise>
		</c:choose>
	}

	function goWriteForm() {
		<c:choose>
		<c:when test="${not empty sessionScope.loginUser}">
		location.href = "write.do"; // 절대경로
		</c:when>
		<c:otherwise>
		alert('로그인 후 이용할 수 있습니다');
		location.href = "login.do";
		</c:otherwise>
		</c:choose>
	}

	function pageBack(currentPage) {
		if (currentPage <= 1) {
			return;
		}
		location.href = "list.do?pageNo=" + (currentPage - 1);
	}

	function pageNext(currentPage, maxPage) {
		if (currentPage == maxPage) {
			return;
		}
		location.href = "list.do?pageNo=" + (currentPage + 1);
	}
</script>

<style>
#pageDiv ul {
	height: 35px;
	line-height: 35px;
	display: inline-block;
	*display: inline;
	zoom: 1;
	list-style: none;
	padding: 0;
}

#pageDiv ul li {
	float: left;
	margin: auto 8px;
	z-index: 1;
}
</style>
</head>
<body>

	<!-- xml, include, forward에서 절대경로의 /는 프로젝트 이후부터 -->
	<header>
		<jsp:include page="/jsp/include/topMenu.jsp" />
	</header>
	<input type="hidden" value="${currentPage}" name="currentPage" />
	<input type="hidden" value="${maxPage}" name="maxPage" />

	<section>
		<div align="center">
			<br>
			<hr width="99%">
			<h2>게시판 목록</h2>
			<hr width="99%">
			<br>
			<table border="1" style="width: 99%">
				<tr>
					<th width="7%">번호</th>
					<th width="50%">제목</th>
					<th width="13%">작성자</th>
					<th width="20%">작성일</th>
				</tr>
				<!-- 태그에도 jstl 사용가능함 -->
				<c:forEach var="board" items="${boardList}" varStatus="loop">
					<tr <c:if test="${loop.count % 2 == 0}">class="even"</c:if>>
						<td>${board.no}</td>
						<%-- 링크가 걸린부분의 out은 jstl out으로 하는편(escapeXML) --%>
						<td><a href="javascript:doDetailAction(${board.no})"><c:out value="${board.title}" /></a></td>

						<td>${board.writer}</td>
						<td>${board.regDate}</td>
					</tr>
				</c:forEach>
			</table>

			<!-- 페이징 (5개) 10개만 긁어오게 -->
			<div id="pageDiv">
				<ul>
					<li><a href="javascript:pageBack(${currentPage})">Back</a></li>
					<c:forEach var="page" begin="${startPage}" end="${lastPage}">
						<li><a href="list.do?pageNo=${page}" <c:if test="${page == currentPage}">style="color:#5f7bb6"</c:if>>${page}</a></li>
					</c:forEach>
					<li><a href="javascript:pageNext(${currentPage}, ${maxPage})">Next</a></li>
				</ul>
			</div>
			<button id="newBoard" onclick="goWriteForm()">새글등록</button>
		</div>

	</section>

	<footer>
		<%@ include file="/jsp/include/bottom.jsp"%>
	</footer>

</body>
</html>