<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>로그인</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:set var="context" value="${pageContext.request.contextPath}" />
</head>
<body>
	<!-- xml, include, forward에서 절대경로의 /는 프로젝트 이후부터 -->
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
							<c:if test="${ not empty cookie.saveUserId }">
								value="${cookie.saveUserId.value}"
							</c:if>>
						</td>
					</tr>
					<tr>
						<th>PASSWORD</th>
						<td><input type="password" name="password" style="width: 98%"></td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="checkbox" name="saveId" <c:if test="${ not empty cookie.saveUserId }">checked="checked"</c:if>/> 아이디 저장
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
