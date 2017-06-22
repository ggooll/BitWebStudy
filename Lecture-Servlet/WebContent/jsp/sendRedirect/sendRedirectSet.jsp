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
	SendRedirect의 경우
	일단 현 페이지가 응답하고, 해당 url을 다시 요청하게끔 한다.
	따라서 url상에서 sendRedirectSet.jsp는 보이지않게 되지만
	
	http://localhost:8000/Lecture-Servlet/jsp/sendRedirect/sendRedirectSet.jsp?id=admin
	접속시에 admin.jsp로 옮겨지는 것을 확인하며, 응답 후 재요청을 확인할 수 있음
	요청이 다시 발생하므로 request영역에 있던 것들이 유지되지 않음		 
 -->

	<h1>결과 화면</h1>
	<c:set var="id" value="${param.id}" scope="request" />
	<c:set var="id" value="${param.id}" scope="session" />
	<c:if test="${param.id == 'admin'}">
	
		<%-- <%
			response.sendRedirect("admin.jsp");
		%> --%>
		
		<script>
			location.href = "admin.jsp";
		</script>
		
	</c:if>

	<c:if test="${param.id ne 'admin'}">
		
		<%
			// id를 넘기기위해서, param을 넘기거나, 공유영역에 올려야함
				// param을 받으면 주소로 지정이가능하지만 이는 위험함
				// session영역에 올려서.,,. 숨기는 것이 좋음
				response.sendRedirect("user.jsp?id=" + request.getParameter("id"));
		%>
	</c:if>
</body>
</html>