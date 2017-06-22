<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:set var="context" value="${pageContext.request.contextPath}" />
<script src="${context}/js/member.js"></script>
<script src="http://code.jquery.com/jquery-3.2.1.min.js"></script>
<link rel="stylesheet" href="${context}/css/layout.css">
<link rel="stylesheet" href="${context}/css/board.css">
<title>회원 상세정보</title>
</head>
<body>
	<header>
		<jsp:include page="/jsp/include/topMenu.jsp" />
	</header>

	<section>
		<div align="center">
			<br>
			<hr width="99%">
			<h2>회원 상세 정보</h2>
			<hr width="99%">
			<br>
			<table border="1" style="width: 99%">
				<tr>
					<th>아이디</th>
					<td>${member.id}</td>
				</tr>
				<tr>
					<th>이름</th>
					<td>${member.name}</td>
				</tr>
				<tr>
					<th>이메일</th>
					<td>${member.emailId}@ ${member.emailDomain}</td>
				</tr>
				<tr>
					<th>전화번호</th>
					<td>${member.tel1}- ${member.tel2} - ${member.tel3}</td>
				</tr>
				<tr>
					<th>주소</th>
					<td>${member.basicAddr} ${member.detailAddr}</td>
				</tr>
				<tr>
					<th>가입일자</th>
					<td>${member.regDate}</td>
				</tr>
			</table>
		</div>
	</section>

	<footer>
		<%@ include file="/jsp/include/bottom.jsp"%>
	</footer>
</body>
</html>