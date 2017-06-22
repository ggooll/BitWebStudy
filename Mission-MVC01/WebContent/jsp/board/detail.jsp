<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>게시글 상세보기</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<c:set var="context" value="${pageContext.request.contextPath}" />
<script src="${context}/js/board.js"></script>
<link rel="stylesheet" href="${context}/css/layout.css">
<link rel="stylesheet" href="${context}/css/board.css">

<script>
	//작성자만 수정할 수 있도록
	function modifyBoard(boardNo, writer) {
		var type = "${sessionScope.loginUser.type}";
		if (type !== 'S') {
			var id = "${sessionScope.loginUser.id}";
			if (id !== writer) {
				alert("작성자만 수정할 수 있습니다.");
				return;
			}
		}
		location.href = "update.do?no=" + boardNo;
	}

	//작성자만 삭제할 수 있도록
	function deleteBoard(boardNo, writer) {
		var type = "${sessionScope.loginUser.type}";
		if (type !== 'S') {
			var id = "${sessionScope.loginUser.id}";
			if (id !== writer) {
				alert("작성자만 수정할 수 있습니다.");
				return;
			}
		}

		if (confirm(boardNo + "번 게시글을 삭제하시겠습니까?")) {
			location.href = "delete.do?no=" + boardNo;
		}
	}

	// 다운로드
	function downloadFile(no, fileSaveName, fileOriName){
		// 다운로드 페이지로,.?
		location.href = "download.do?no=" + no +"&file=" + fileSaveName +"&ori=" + fileOriName;
	}
	
</script>
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
			<h2>상세 보기</h2>
			<hr width="99%">
			<br>

			<table border="1" style="width: 99%">
				<tr>
					<th width="25%">글번호</th>
					<td>${board.no}</td>
				</tr>
				<tr>
					<th>작성일</th>
					<td>${board.regDate}</td>
				</tr>
				<tr>
					<th>조회수</th>
					<td>${board.viewCnt}</td>
				</tr>
				<tr>
					<th>작성자</th>
					<td>${board.writer}</td>
				</tr>
				<tr>
					<th>제목</th>
					<td><c:out value="${board.title}" /></td>
				</tr>
				<tr>
					<th>내용</th>
					<td style="height: 150px; min-height: 150px"><c:out value="${board.content}" /></td>
				</tr>
				<!-- 첨부파일 내용 -->
				<tr>
					<th>첨부파일</th>
					<td style="height: 70px; min-height: 70px"><c:forEach var="boardFile" items="${boardFileList}">
							<a href="${context}/upload/${boardFile.fileSaveName}">${boardFile.fileOriName}</a> ( ${boardFile.fileSize} Bytes ) 
							<button onclick="downloadFile('${board.no}','${boardFile.fileSaveName}', '${boardFile.fileOriName}')">다운로드</button>
							<br>
						</c:forEach></td>
				</tr>
			</table>
			<br> <br>

			<%-- 로그인한 사용자와 작성자가 같은지 검사 --%>
			<button id="modifyBoard" onclick="modifyBoard('${board.no}', '${board.writer}')">수정</button>
			<button id="deleteBoard" onclick="deleteBoard('${board.no}', '${board.writer}')">삭제</button>
			<button id="goList" onclick="goList()">목록으로</button>
		</div>
	</section>

	<footer>
		<%@ include file="/jsp/include/bottom.jsp"%>
	</footer>

</body>
</html>