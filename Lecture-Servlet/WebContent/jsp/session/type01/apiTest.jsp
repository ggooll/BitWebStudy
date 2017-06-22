<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
	HttpSession session01 = request.getSession();
	// 같은객체임
	// System.out.println(session01 == session);
	
	String id = session01.getId();
	int interval = session.getMaxInactiveInterval();
	
	session.setMaxInactiveInterval(2);
	int interval2 = session.getMaxInactiveInterval();
	
	// 마지막 접근시간
	long lastAccessedTime = session.getLastAccessedTime();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	String accessTime = sdf.format(new Date(lastAccessedTime));
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	세션아이디 : <%=id %> <br>
	기존유효시간 : <%=interval %>초 <br>
	변경유효시간 : <%=interval2 %>초 <br>
	마지막접근시간 : <%=accessTime %><br>

</body>
</html>