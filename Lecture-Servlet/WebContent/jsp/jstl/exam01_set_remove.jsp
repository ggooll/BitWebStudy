<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSTL</title>
</head>
<body>

	<!-- 	JSP공유영역		EL공유영역			JSTL공유영역 	-->
	<!-- 	pageContext,	pageScope, 			page			-->
	<!-- 	request, 		requestScope, 		request 		-->
	<!-- 	session, 		sessionScope, 		session 		-->
	<!-- 	application, 	applicationScope,	application 	-->

	<!-- 참조객체가 없다면 page, request, session, application 순서대로 찾음 -->
	<!-- 명시하지 않더라도 네 영역을 순서대로 뒤짐 -->

	<h3>1값이 가지는 변수 cnt를 페이지 공유영역에 등록</h3>
	<c:set var="cnt" value="1" scope="page" />
	공유영역에 등록된 cnt 변수값 출력<br>
	cnt : ${cnt}<br>
	<hr>

	<!-- 각 영역을 구분함 -->
	<h3>cnt의 값을 증가시키기 (set태그에 EL태그 사용)</h3>
	<c:set var="cnt" value="${cnt+1}" scope="request"/>
	cnt : ${cnt}<br>
	requestScope.cnt : ${requestScope.cnt}<br>
	<c:set var="cnt" value="${requestScope.cnt + cnt}" scope="session"/>
	sessionScope.cnt : ${sessionScope.cnt}<br>	
	<hr>

	<!-- 지울때 scope를 명시하지 않으면 네 곳을 다 지움! -->
	<h3> scope를 명시하지 않고 cnt를 remove 한 후</h3>
	<%-- <c:remove var ="cnt" scope="request"/> --%>

	<c:remove var ="cnt" />
	cnt : ${cnt}<br>
	requestScope.cnt : ${requestScope.cnt}<br>
	sessionScope.cnt : ${sessionScope.cnt}<br>

</body>
</html>