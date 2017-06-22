<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>EL 연산자</title>
</head>
<body>
	<%-- 톰캣에 el-api라이브러리가 있음 --%>
	
	<!-- 기존 -->
	5 + 5 : <%= 5 + 5 %><br>
	<!-- EL -->
	5 + 5 : ${ 5 + 5 }<br>
	<hr>
	<!-- 키워드 사용가능  -->	
	9 % 5 : ${ 9 % 5 }<br>
	9 mod 5 : ${ 9 mod 5 }<br>
	<hr>
	9 > 5 : ${ 9 > 5 }<br>
	9 gt 5 : ${ 9 gt 5 }<br>
	<hr>
	g == 5 : %{9 == 5}<br>
	g eq 5 : ${9 eq 5}<br>
	<hr>
	9 != 5 : ${9 != 5}<br>
	<%-- 9 ne 5 : ${9 ne 5}<br> --%>
	<hr>
	g < 5 : ${ 9 < 5 }<br>
	g lt 5 : ${ 9 lt 5}<br>
	<hr>
	(5 == 5) && (6 >= 5) : ${(5 == 5) && (6 >= 5)}<br>
	(5 eq 5) and (6 ge 5) : ${(5 eq 5) and (6 ge 5)}<br>
	<hr>
	<!-- empty 연산자 -->
	empty str : ${empty str}<br>
	not empty str : ${not empty str}<br>
	! empty str : ${!empty str }<br>
	<hr>
	<!-- 삼항연산자 -->
	5의 짝/홀수 결과 : ${5 % 2 == 0 ? "짝수" : "홀수" }<br>
	5의 짝/홀수 결과 : ${5 mod 2 eq 0 ? "짝수" : "홀수" }<br>
	<hr>
	
	
</body>
</html>