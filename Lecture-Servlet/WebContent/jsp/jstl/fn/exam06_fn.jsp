<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%
	String[] names = {"홍길동", "강길동", "박길동", "한길동", "최길동"};
	pageContext.setAttribute("names", names);
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSTL FN</title>
</head>
<body>

	<c:set var="msg" value="hello JSTL" />
	<!-- EL안에 다시 EL을 넣지 않아도 된다 -->
	${fn:length(msg)}
	<br>
	${fn:length("hello JSTL")}

	<!-- 그 외 함수들 많음, 찾아서 사용 -->

</body>
</html>
