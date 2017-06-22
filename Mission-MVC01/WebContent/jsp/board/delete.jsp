<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>
	<c:if test="${result == 1}">
	alert('${param.no}번 게시글을 삭제하였습니다..');
	</c:if>
	<c:if test="${result == 0}">
	alert('삭제하지 못했습니다.');
	</c:if>
	location.href = "list.do";
</script>