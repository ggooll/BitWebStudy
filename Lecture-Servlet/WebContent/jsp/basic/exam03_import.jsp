<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Random"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h2>1부터 10사이의 임의의 정수 추출</h2>
	<%
		//java.util.Random rand = new java.util.Random();
		Random rand = new Random();
		int num = rand.nextInt(10) + 1;
	%>
	추출된 정수 :
	<%=num%><br>

</body>
</html>