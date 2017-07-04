<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>로그인</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:set var="context" value="${pageContext.request.contextPath}" />
<%-- <link rel="stylesheet" href="${context}/css/layout.css">
<link rel="stylesheet" href="${context}/css/board.css">
<script src="${context}/js/login.js"></script>
<script src="${context}/js/checkinput.js"></script> --%>
</head>
<body>
	<!-- xml, include, forward에서 절대경로의 /는 프로젝트 이후부터 -->

	<c:if test="${not empty msg}">
		<script>alert('${msg}');</script>
	</c:if>

	<section>	
		<div id="login-container" align="center">
			<hr>
			<h2>로그인</h2>
			<hr>
			<br>
			<form action="${context}/login/login.do" method="post" onsubmit="return checkLoginForm()" name="loginForm">
				<table style="width: 40%">
					<tr>
						<th>ID</th>
						<td>
							<input type="text" name="id" style="width: 98%" 
							<c:if test="${not empty saveId}">value="${saveId}"</c:if>>
						</td>
					</tr>
					<tr>
						<th>PASSWORD</th>
						<td><input type="password" name="password" style="width: 98%"></td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="checkbox" name="saveId" <c:if test="${not empty saveId}">checked="checked"</c:if>/> 아이디 저장
						</td>
					</tr>
				</table>
				<br>
				<input type="submit" value="로그인">
			</form>
		</div>
	</section>

</body>
</html>
