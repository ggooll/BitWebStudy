<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.InputStreamReader"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 서버에서 파일을 업로드 받았다고 가정 -->
	<h2>전송된 정보 출력</h2>
	<%
		// request의 인풋스트림
		InputStreamReader isr = new InputStreamReader(request.getInputStream(), "utf-8");
		BufferedReader br = new BufferedReader(isr);
	
		while (true) {
			String str = br.readLine();
			if (str == null)
				break;
			out.println(str + "<br>");
		}
		// 형태가 하는 일
		// id=ssdd&uploadFile=member.txt
		// requestParameter가 이를 파싱하는 것
		
		
	%>


</body>
</html>