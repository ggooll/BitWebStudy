<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>expression</title>
</head>
<body>
	<h2>1-10까지의 정수를 출력</h2>

	<%-- 표현식은 Scriptlet안에 들어갈 수 없다. 따로 분리해줘야댐 ㅡㅡ;; --%>
	<%
		int total = 0;
		for (int i = 1; i <= 10; i++) {
	%>

	<%=i + "<br>"%>

	<%
		total += i;
		}
	%>

	1부터 10사이의 총합은 :
	<%=total%><br>

</body>
</html>