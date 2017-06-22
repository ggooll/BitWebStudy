<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<!-- 
		out에 사용될 수 있는 조건 
		1. value 출력될 값
		2. default 값이 empty인경우 출력될 값
		3. escapeXml html 태그포함여부(true=태그씹음, false=태그로 해석해줘)
		   - 조건을 걸지않는경우 true가 디폴트로 잡힌다.
	-->

	<c:set var="msg" value="하이JSTL"/>
	<c:out value="JSTL"/><br>
	<c:out value="${ msg }" default="설정된 msg 변수 없음"/><br>
	<c:out value="<hr/>" default="값 없음" /><br>
	<c:out value="<hr/>" escapeXml="false" default="값 없음" /><br>

</body>
</html>