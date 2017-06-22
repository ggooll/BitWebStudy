<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<!-- 여러 조건들을 처리할 수 있음 - 다중조건 처리  -->
	<c:choose>
		<c:when test="${param.type == 'S'}">
			<h2>관리자입니다.</h2>
		</c:when>

		<c:when test="${param.type == 'U'}">
			<h2>일반사용자입니다.</h2>
		</c:when>

		<!-- 모든 when절이 거짓인경우 otherwise가 수행된다. -->
		<c:otherwise>
			<h2>TYPE파라미터가 설정되지 않았거나, 파라미터 정보가 잘못 지정되었습니다.</h2>
		</c:otherwise>
	</c:choose>

</body>
</html>