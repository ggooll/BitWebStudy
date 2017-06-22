<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>EL에서 객체찾기</title>
</head>
<body>
	<!-- 아래와 같이 접속했을 때 -->
	<!-- http://localhost:8000/Lecture-Servlet/jsp/el/exam02_object.jsp?id=aaa&name=bbb&hobby=reading&hobby=music -->

	<!-- 표현식은 값이 없으면 null로 찍힘 -->
	id : <%= request.getParameter("id") %><br>
	name : <%= request.getParameter("name") %><br>

	<!-- paramValues null인경우 번지접근 불가, NullPointerException 발생 -->
	<% 
		String[] hobbys = request.getParameterValues("hobby");
		if(hobbys == null){
			// hobbys = new String[2];
			hobbys = new String[]{"", ""};
		}
	%>
	hobbys[0] : <%= hobbys[0] %><br>
	hobbys[1] : <%= hobbys[1] %><br>

	<hr>
	<!-- el은 값이 없으면 ""로 나옴 -->
	id : ${param.id}<br>
	name : ${param.name}<br>

	<!-- paramValues, null인경우 그냥 빈칸 -->
	paramValues : ${paramValues.hobby}<br>
	hobby[0] : ${paramValues.hobby[0]}<br>
	hobby[1] : ${paramValues.hobby[1]}<br>

</body>
</html>
