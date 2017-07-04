<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}" />


<script>
	// 각 페이지 이동시 세션 검사
	function checkSession(url){
		<c:choose>
			<c:when test="${empty sessionScope.loginUser}">
				alert('로그인이 필요합니다.');
			</c:when>
			<c:otherwise>
				location.href = url;
			</c:otherwise>
		</c:choose>
	}

</script>

<div id="sidebar-left" style="width: 180px !important; height: 1000px !important; padding: 0">
	<div class="sidebar-nav">
		<ul class="nav nav-tabs nav-stacked main-menu">
			<li><a><i></i><span class="hidden-tablet"></span></a></li>
			<li><a href="javascript:checkSession('${context}/mypage.do')"><i class="icon-star"></i><span
					class="hidden-tablet"> 마이 페이지 </span></a></li>
			<li><a href="javascript:checkSession('${context}/friendList.do')"><i class="icon-heart"></i><span
					class="hidden-tablet"> 내 친구목록 </span></a></li>
			<li><a href="${context}/dealCurrentList.do"><i class="icon-tasks"></i><span
					class="hidden-tablet"> 진행중인 거래 </span></a></li>
			<li><a href="${context}/dealHistoryList.do"><i class="icon-align-justify"></i><span
					class="hidden-tablet"> 지난 거래내역 </span></a></li>					
			<li><a href="${context}/selectGroup.do"><i class="icon-group"></i><span
					class="hidden-tablet"> 내 그룹 </span></a></li>
		</ul>
	</div>
</div>


<!-- 포인트충전 modal -->


