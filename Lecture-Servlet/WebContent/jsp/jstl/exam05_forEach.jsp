<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	String[] names = {"홍길동", "강길동", "박길동", "한길동", "최길동"};
	pageContext.setAttribute("names", names);
	pageContext.setAttribute("length", names.length);
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<c:forEach var="i" begin="1" end="10">
		${i}&nbsp;
	</c:forEach>
	<br>

	년도 선택 :
	<select>
		<c:forEach var="year" begin="1990" end="2017" step="2">
			<option>${year}</option>
		</c:forEach>
	</select>
	<br>
	<hr>

	<!-- 배열 혹은 콜렉션의 사이즈를 담을수도 있으나 -->
	<c:forEach var="i" begin="0" end="${length-1}">
		${names[i]}
 		<c:if test="${i != length-1}">
		,&nbsp;
		</c:if>		
	</c:forEach>
	<br>

	<!-- items로 자바의 forEach문처럼 사용 가능 -->
	<c:set var="idx" value="0"/>
	<c:forEach var="name" items="${names}">
	 	${name}
	 	<c:if test="${length-1 != idx}">
		,&nbsp;
		</c:if>
		<c:set var="idx" value="${idx+1}"/>		
	</c:forEach>
	<br>
	
	<hr>
	<!-- varStatus - first, last, index, count -->
	<c:forEach var="name" items="${names}" varStatus="loop">
		<!-- 인덱스 : 번지 -->
		<!-- 카운트 : 1.2.3.4.. -->
		<!-- first : true/false -->
		<!-- last : true/false -->
	 	${loop.index} : ${loop.count} : ${loop.first} : ${loop.last} : ${name}<br>
	</c:forEach>
	<br>

	<c:forEach var="name" items="${names}" varStatus="loop">
	 	${name}
	 	<c:if test="${!loop.last}">
		,&nbsp;
		</c:if>
	</c:forEach>
	<br>


</body>
</html>