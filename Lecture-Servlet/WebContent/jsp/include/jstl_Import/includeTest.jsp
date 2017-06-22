<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="common.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>

<!-- 
 	JSTL의 import는
 	다른 서버의 페이지도 include할 수 있다는 점에서 
 	Action include와 차이가 있다.
-->
<body>

	<h1>인클루드전</h1>
	파라미터 미설정 호출 <br>	
	<c:import url="one.jsp"/>
	파라미터 설정 호출 <br>	
	<c:import url="one.jsp">
		<c:param name="id" value="hong"/>
		<c:param name="age" value="23"/>
	</c:import>
	<h1>인클루드후</h1>

	<c:import url="http://www.daum.net"/>

</body>
</html>






