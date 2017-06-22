<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}" />
<%-- include용 --%>

<script>
	function addFavorite() {
		try {
			window.external.AddFavorite('http://localhost:8000/Mission-MVC01',
					'첫번째나의웹');
		} catch (e) {
			alert('현재 브라우저는 즐겨찾기를 지원하지 않습니다. \n 크롬이라면 ctrl+d를 사용해주세요');
		}
	}
</script>

<table border="1" style="width: 100%">
	<tr>
		<td rowspan="2" style="width: 140px; height: 48px">
			<a href="${context}">
			<img src="http://static.naver.com/weather/images/bi_naver.gif"style="width: 100%; height: 100%"></a></td>
		<td align="right">
			<a href="javascript:void()" onclick="addFavorite()">즐겨찾기</a>
			<c:if test="${not empty sessionScope.loginUser}">
				<span>[${sessionScope.loginUser.id}님 접속중]</span>
			</c:if>
		</td>
	</tr>

	<tr>
		<td>
		<nav>
		<!-- 로그인 여부에 따라 보이는 메뉴가 다름 -->
		<a href="${context}/list.do">게시판</a> ||
		<c:choose>
			<c:when test="${not empty sessionScope.loginUser}">
				<a href="${context}/memberDetail.do">마이페이지</a>	||

				<c:if test="${sessionScope.loginUser.type == 'S' }">
					<a href="${context}/memberList.do">회원관리</a> ||
				</c:if>

				<a href="${context}/logout.do">로그아웃</a>
			</c:when>
			<c:otherwise>
				<a href="${context}/registry.do">회원가입</a> ||
				<a href="${context}/login.do">로그인</a>
			</c:otherwise>
		</c:choose>
		</nav>
		</td>
	</tr>

</table>