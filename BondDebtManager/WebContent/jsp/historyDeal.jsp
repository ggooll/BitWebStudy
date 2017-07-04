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

<!-- start: CSS -->
<link id="bootstrap-style" href="${context}/bootstrap/css/bootstrap.css"
	rel="stylesheet">
<link id="base-style" href="${context}/bootstrap/css/style.css"
	rel="stylesheet">
<link href="${context}/bootstrap/css/bootstrap-responsive.css"
	rel="stylesheet">
<link id="base-style-responsive"
	href="${context}/bootstrap/css/style-responsive.css" rel="stylesheet">
<link
	href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800&subset=latin,cyrillic-ext,latin-ext'
	rel='stylesheet' type='text/css'>
<link href="${context}/css/custom.css" rel="stylesheet">
<link href='https://fonts.googleapis.com/earlyaccess/hanna.css'
	rel='stylesheet' type='text/css'>
<!-- end: CSS -->
<script>
function pageBack(currentPage) {
	if (currentPage <= 1) {
		return;
	}
	location.href = "dealHistoryList.do?page=" + (currentPage - 1);
}
function pageNext(currentPage, maxPage) {
	if (currentPage == maxPage) {
		return;
	}
	location.href = "dealHistoryList.do?page=" + (currentPage + 1);
}
</script>
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

				<div class="row-fluid">
					<div class="box span12" style="min-height: 1000px">
						<div class="box-header" data-original-title>
							<h2>
								<i class="halflings-icon white user"></i><span class="break"></span>
								완료된 거래
							</h2>
							<div class="box-icon">
								<a href="#" class="btn-setting"><i
									class="halflings-icon white wrench"></i></a> <a href="#"
									class="btn-minimize"><i
									class="halflings-icon white chevron-up"></i></a> <a href="#"
									class="btn-close"><i class="halflings-icon white remove"></i></a>
							</div>
						</div>
						<div class="box-content">
							<table class="table table-striped table-bordered">
								<thead>
									<tr>
										<th>번호</th>
										<th>내용</th>
										<th>갚은 사람</th>
										<th>받은 사람</th>
										<th>금액</th>
										<th>시작일시</th>
										<th>완료일시</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="dealHistory" items="${ dealHistories }">
										<tr>
											<td>${ dealHistory.no }</td>
											<td>${ dealHistory.comments }</td>
											<td class="center" <c:if test="${loginUser.id eq dealHistory.senderId}"> style="color:#097300" </c:if>>${ dealHistory.senderId }</td>
											<td class="center" <c:if test="${loginUser.id eq dealHistory.receiverId}"> style="color:#097300" </c:if>>${ dealHistory.receiverId }</td>
											<td class="center">${ dealHistory.money }원</td>
											<td class="center"><span style="color : #AAA">${ dealHistory.startDate }</span></td>
											<td class="center"><span style="color : #AAA">${ dealHistory.endDate }</span></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>

							<input type="hidden" value="${currentPage}" name="currentPage" />
							<input type="hidden" value="${maxPage}" name="maxPage" />
							<div class="pagination pagination-centered">
								<ul>
									<li><a href="javascript: pageBack(${currentPage})">Prev</a></li>
									<c:forEach var="page" begin="${startPage}" end="${lastPage}">
										<li><a href="dealHistoryList.do?page=${page}" <c:if test="${page == currentPage}">style="color:#5f7bb6"</c:if>>${page}</a></li>
									</c:forEach>
									<li><a href="javascript: pageNext(${currentPage}, ${maxPage})">Next</a></li>
								</ul>
							</div>

						</div>
					</div>
					<!--/span-->
				</div>
				<!--/row-->


			</div>
			<!--/.fluid-container-->

	</div>
</div>
	<div id="custom-footer">
		<%@ include file="include/footer.jsp"%>
	</div>

</body>
</html>
