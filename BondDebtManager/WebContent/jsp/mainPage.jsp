<%@page import="kr.co.bit.bonddebt.vo.Member"%>
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


<link id="bootstrap-style" href="${context}/bootstrap/css/bootstrap.css" rel="stylesheet">
<link id="base-style" href="${context}/bootstrap/css/style.css" rel="stylesheet">
<link href="${context}/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
<link id="base-style-responsive" href="${context}/bootstrap/css/style-responsive.css" rel="stylesheet">
<link
	href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800&subset=latin,cyrillic-ext,latin-ext'
	rel='stylesheet' type='text/css'>
<link href="${context}/css/custom.css" rel="stylesheet">
<link href='https://fonts.googleapis.com/earlyaccess/hanna.css' rel='stylesheet' type='text/css'>
</head>

<body>
	<div id="custom-container">
		<header>
			<%@ include file="include/topMenu.jsp"%>
		</header>
		<c:choose>
			<c:when test="${empty sessionScope.loginUser}">
				<div id="rookie" style="background-color: #FFFFF7">
					<img src="${context}/image/whoemain2.jpg" style="width:1200px; height:1000px">
				</div>
			</c:when>
			<c:otherwise>
				<div id="custom-section">
					<%@ include file="include/leftMenu.jsp"%>
					<!-- 내용이 들어가야할 곳 -->
					<div id="content" style="background-color: #FFFFF7">
						<div class="span10">
							<img src="${context}/image/main.png" style="margin-left:25px; margin-top:35px">
						</div>
					</div>
				</div>
			</c:otherwise>
		</c:choose>
	</div>

	<div id="custom-footer">
		<%@ include file="include/footer.jsp"%>
	</div>

</body>
</html>