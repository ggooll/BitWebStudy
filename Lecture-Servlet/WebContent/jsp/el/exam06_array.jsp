<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String[] names = {"홍길동", "박길동", "강길동"};
	pageContext.setAttribute("names", names);
	
	ArrayList<String> nameList = new ArrayList<>();
	nameList.add("홍길동");
	nameList.add("박길동");
	nameList.add("강길동");
	pageContext.setAttribute("nameList", nameList);
	
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>EL의 Array 접근</title>
</head>
<body>

	<h2> array </h2>
	<hr>
	첫번째 이름 : <%= names[0] %><br>
	두번째 이름 : <%= names[1] %><br>
	세번째 이름 : <%= names[2] %><br>
	<hr>
	첫번째 이름 : ${names[0]}<br>
	두번째 이름 : ${names[1]}<br>
	세번째 이름 : ${names[2]}<br>
	<hr>

	<h2> ArrayList </h2>
	<hr>
	첫번째 이름 : <%= nameList.get(0) %><br>
	두번째 이름 : <%= nameList.get(1) %><br>
	세번째 이름 : <%= nameList.get(2) %><br>
	<hr>
	첫번째 이름 : ${nameList[0]}<br>
	두번째 이름 : ${nameList[1]}<br>
	세번째 이름 : ${nameList[2]}<br>
	<hr>

</body>
</html>