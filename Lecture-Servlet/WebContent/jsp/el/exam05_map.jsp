<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	Map<String, String> map = new HashMap<>();
	map.put("no", "1");
	map.put("title", "제목");
	map.put("writer", "작성자");
	map.put("content", "내용");
	map.put("regDate", "등록일");
	pageContext.setAttribute("map", map);
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>


	<%
		@SuppressWarnings("unchecked")
		Map<String, String> m = (Map<String, String>) pageContext.getAttribute("map");
	%>
	no :
	<%=m.get("no")%><br> title :
	<%=m.get("title")%><br> writer :
	<%=m.get("writer")%><br> content :
	<%=m.get("content")%><br> regDate :
	<%=m.get("regDate")%><br>

	<hr>

	<!-- el -->
	el no : ${map.no}
	<br> el writer : ${map.writer}
	<br> el content : ${map.content}
	<br> el regDate : ${map.regDate}
	<br>

	<!-- 없는값을 조회 한경우 empty처리 ("") -->
	el aaa : ${map.aaa}
	<br> empty aaa : ${empty map.aaa}
	<br>

</body>
</html>