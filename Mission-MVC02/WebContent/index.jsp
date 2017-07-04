<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE>
<html>
<head>
<c:set var="context" value="${ pageContext.request.contextPath }" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<!-- xml, include, forward에서 절대경로의 /는 프로젝트 이후부터 -->
	<%-- <jsp:include page="/jsp/include/topMenu.jsp"></jsp:include> --%>

	<!-- include시엔 do패턴 사용하지 않음..(풀패스로 가능하긴하나..)  -->
	<c:import url="/jsp/include/topMenu.jsp"></c:import>

	<section>
		<h3>메인페이지</h3>
		<h3>메인페이지</h3>
		<h3>메인페이지</h3>
		<h3>메인페이지</h3>
		<h3>메인페이지</h3>
	</section>

</body>
</html>