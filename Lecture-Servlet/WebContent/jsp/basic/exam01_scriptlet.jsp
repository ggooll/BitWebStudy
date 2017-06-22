<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>scriptlet</title>
</head>
<body>

	<h2>1-10까지의 정수를 출력</h2>

	<%-- Java의 반복문을 이용, 내장객체 out이 존재함(서블릿의 service에 받아놓음) --%>
	<%
		for (int i = 1; i <= 10; i++) {
			out.println(i + "<br>");
		}
	%>


</body>
</html>