<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:set var="context" value="${pageContext.request.contextPath}" />
<link rel="shortcut icon" href="${context}/image/credit-card-2.png">
<title>오늘은내가총무</title>
<script src="${context}/bootstrap/js/jquery-1.9.1.min.js"></script>
<script src="${context}/bootstrap/js/jquery-migrate-1.0.0.min.js"></script>
<script src="${context}/bootstrap/js/modernizr.js"></script>
<script src="${context}/bootstrap/js/bootstrap.min.js"></script>
<script src='${context}/bootstrap/js/jquery.dataTables.min.js'></script>
<script src="${context}/bootstrap/js/excanvas.js"></script>
<script src="${context}/bootstrap/js/counter.js"></script>

<!-- start: CSS -->
<link id="bootstrap-style" href="${context}/bootstrap/css/bootstrap.css" rel="stylesheet">
<link id="base-style" href="${context}/bootstrap/css/style.css" rel="stylesheet">
<link href="${context}/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
<link id="base-style-responsive" href="${context}/bootstrap/css/style-responsive.css" rel="stylesheet">
<link
	href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800&subset=latin,cyrillic-ext,latin-ext'
	rel='stylesheet' type='text/css'>
<link href="${context}/css/custom.css" rel="stylesheet">
<link href='https://fonts.googleapis.com/earlyaccess/hanna.css' rel='stylesheet' type='text/css'>
<!-- end: CSS -->

<style>
.myPageDiv{
	height: 60px;
	font-size : 17pt;
	line-height : 60px;
}

.myPageTitle{
	font-size : 16px !important;
}

.wrapSpan{
	margin-left : 30px;
	display: inline-block;
}

.titleSpan{
	display: inline-block;
	min-width: 100px;
	height: 60px;
}

.contSpan{
	margin-left : 50px;
	min-width: 200px;
	height: 60px;
}

</style>

</head>
<body>

	<div id="custom-container">
		<header>
			<%@ include file="include/topMenu.jsp"%>
		</header>

		<div id="custom-section"> 
			<%@ include file="include/leftMenu.jsp"%>
		

		<!-- 내용이 들어가야할 곳 -->
			<div id="content">
				
				<!-- 사이즈 명시할 것 -->
				<div class="span10">
					<hr>
				 	<h1>&nbsp;&nbsp;마이페이지<small> My Page</small></h1>
				 	<hr>
					<div class="priority high"> 
						<span class="myPageTitle">필수정보</span>
					</div>
					<div class="task high myPageDiv">
						<span class="wrapSpan"> 
							<span class="titleSpan">아이디</span> 
							<span class="contSpan"> ${selectMember.id}</span>
						</span>
					</div>
					<div class="task high myPageDiv">
						<span class="wrapSpan"> 
							<span class="titleSpan">이름</span>
							<span class="contSpan">${selectMember.name}</span>
						</span>
					</div>
					<div class="priority medium"> 
						<span class="myPageTitle">연락처</span>
					</div>
					
					<div class="task medium myPageDiv">
						<span class="wrapSpan">
							<span class="titleSpan">이메일</span>
							<span class="contSpan">${selectMember.email}</span>
						</span>
					</div>
					<div class="task medium myPageDiv">
						<span class="wrapSpan">
							<span class="titleSpan">전화번호</span>
							<span class="contSpan">
								<c:choose>
									<c:when test="${not empty selectMember.tel}">
										${selectMember.tel}
									</c:when>
									<c:otherwise>
										전화번호를 등록하세요
									</c:otherwise>
								</c:choose>
							</span>
						</span>
					</div>
					<div class="priority low"> 
						<span class="myPageTitle"> 추가정보 </span>
					</div>
					<div class="task low myPageDiv">
						<span class="wrapSpan">
							<span class="titleSpan">보유금액</span>
							<span class="contSpan">
								<c:choose>
									<c:when test="${not empty selectMember.balance}">
										${selectMember.balance}
									</c:when>
									<c:otherwise>
										0
									</c:otherwise>
								</c:choose>
								원
							</span>
						</span>
						
					</div>
					<div class="task low myPageDiv">
						<span class="wrapSpan" style="color:#AAA">포인트 충전은 우측 상단을 이용하세요</span>
					</div>
					
				</div>

			</div>
		</div>
	</div>

	<div id="custom-footer">
		<%@ include file="include/footer.jsp" %>
	</div>

</body>
</html>   
