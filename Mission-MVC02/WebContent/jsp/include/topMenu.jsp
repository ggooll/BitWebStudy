<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${ pageContext.request.contextPath }" />
<a href="${context}/board/list.do">게시판</a><br/>
<c:choose>
	<c:when test="${ empty loginUser }">
		<a href="${context}/login/loginForm.do">로그인</a><br/>
	</c:when>
	<c:otherwise>
		<a href="${context}/login/logout.do">로그아웃</a><br/>
	</c:otherwise>
</c:choose>