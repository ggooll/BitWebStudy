<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>회원목록</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:set var="context" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" href="${context}/css/member.css">
<link rel="stylesheet" href="${context}/css/layout.css">
<link rel="stylesheet" href="${context}/css/board.css">
</head>
<body>

	<!-- xml, include, forward에서 절대경로의 /는 프로젝트 이후부터 -->
	<header>
		<jsp:include page="/jsp/include/topMenu.jsp" />
	</header>

	<section>
		<div id="main-container">
			<hr>
			<h1>회원 목록</h1>
			<hr>
			<table id="memberTable" border="1">
				<tr>
					<th width="10%">아이디</th>
					<th width="10%">이름</th>
					<th width="15%">이메일</th>
					<th width="15%">전화번호</th>
					<th width="10%">우편번호</th>
					<th width="">주소</th>
				</tr>
				<c:forEach var="member" items="${memberList}">
					<tr>
						<td><a href="memberDetail.do?id=${member.id}">${member.id}</a></td>
						<td>${member.name}</td>
						<td>
							<c:if test="${not empty member.emailId}">
								${member.emailId}@${member.emailDomain}
							</c:if>
						</td>
						<td>
						<c:if test="${not empty member.emailId}">
								${member.tel1}-${member.tel2}-${member.tel3}
							</c:if>
						</td>
						<td>${member.post}</td>
						<td>${member.basicAddr}&nbsp;${member.detailAddr}</td>
					</tr>
				</c:forEach>
			</table>
			<br>
		</div>
	</section>

	<footer>
		<%@ include file="/jsp/include/bottom.jsp"%>
	</footer>

</body>
</html>

