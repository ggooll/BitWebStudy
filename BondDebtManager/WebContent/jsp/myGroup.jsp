<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
<link id="base-style" href="${context}/bootstrap/css/style-forms.css" rel="stylesheet">
<link href="${context}/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
<link id="base-style-responsive" href="${context}/bootstrap/css/style-responsive.css" rel="stylesheet">
<link
	href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800&subset=latin,cyrillic-ext,latin-ext'
	rel='stylesheet' type='text/css'>
<link href="${context}/css/custom.css" rel="stylesheet">
<link href="${context}/css/groupList.css" rel="stylesheet">
<link href='https://fonts.googleapis.com/earlyaccess/hanna.css' rel='stylesheet' type='text/css'>


<!-- list -->
<script>
$(document).ready(function(){
	$('#addGroupModal').hide();
	$('#sendMoneyGroupModal').hide();
	
	$('#addGroupLink').click(function(){
		$('#addGroupModal').modal();
	});
	
	$('.eachGroup').click(function(){
		var groupNo = $(this).find('.eachGroupNo').val();
		$('#currentGroup').val(groupNo);
		//$('#groupChatFrame').attr('src','http://192.168.1.61:8080/BondDebtManager/jsp/groupChat.jsp?groupNo='+groupNo);
		$('#groupChatFrame').attr('src','http://172.20.10.2:8080/BondDebtManager/jsp/groupChat.jsp?groupNo='+groupNo);
	});	
});

$(document).ready(function(){
	$('#send_group_submit').click(function(){
		var money = $('#sendMoney').val();
		var money = $('#money').val();
		if(money >= 100000000 || money <= 0){
			alert('충전금액을 제대로 입력하세요');
			return;
		}
		var groupNo = $('#currentGroup').val();
		var comments = $('#sendComments').val();
		var dealData = 'groupNo='+groupNo+'&comments='+comments+'&money='+money;
 
	 	$.ajax({
		 	type : "POST",
		  	url : "${context}/sendMoneyGroup.do",
		  	data : dealData,
		  	async : false,
		  	success : function(data){
		  		flag = data.trim();
		  		if(flag == '1'){
		  			alert(" 성공적으로 요청했습니다. ");
		  			
		  			var setMessage = comments + '에 대한 금액 ' + money + '원을 요청했습니다.';
		  			document.getElementById("groupChatFrame").contentWindow.sendMessage(setMessage);
		  			
		  			$('#sendComments').val("");
		  			$('#sendMoney').val("");
		  			$('#sendMoneyGroupModal').modal('toggle');
		  			$('#sendMoneyGroupModal').hide();
		  			
		  		} else {
		  			alert(flag);
		  			alert('요청에 실패했습니다. 관리자에게 문의하세요');
		  		}
		  	},
	  		error : function(request,status,error){
	  		    alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	  	   }
 		});

	});
});

</script>

<style>
#groupChatFrame{
	width: 620px;
	height: 1000px;
	background-color: #FFFFF7;
	border : 0;
}
#groupChatDiv{
	width: 620px !important;
	float: right;
	background-color: #FFFFF7;
	height: 1000px;
}
</style>

</head>
<body>

	<div id="custom-container">
		<header>
			<%@ include file="include/topMenu.jsp"%>
		</header>
		<c:choose>
			<c:when test="${empty sessionScope.loginUser}">
				<div id="rookie" style="background-color: #FFFFF7">
					
				</div>
			</c:when>
			<c:otherwise>
				<div id="custom-section">
					<%@ include file="include/leftMenu.jsp"%>
					<!-- 내용이 들어가야할 곳 -->
					<div id="content" style="background-color: #FFFFF7">
						<!-- 좌측메뉴 --> 

						<div id="groupListDiv">
							<hr>
							<div style="height:40px; line-height:40px">
								<span style="margin-left: 10px; font-size:20px">내 그룹 리스트</span>
								<a class="btn btn-primary" id="addGroupLink" style="float: right; margin-right: 10px">그룹생성</a>
							</div>
							<hr>
							<c:choose>
								<c:when test="${groupCount eq 0}">
									<h1>참여그룹이 없습니다.</h1>
								</c:when>
								<c:otherwise>
									<c:forEach var="group" items="${groupList}">
										<div class="eachGroup">
											<input type="hidden" class="eachGroupNo" value="${group.no}">
											<div class="eachGroupTitle">
												<span class="titleSpan">${group.name}</span><span class="numSpan">${group.memberCount}명 </span>
											</div>
											<div class="participant" style="overflow:hidden">
												<span class="partSpan">${group.membersList}.</span>
											</div>
										</div>
									</c:forEach>
								</c:otherwise>
							</c:choose>
						</div>
						<%@ include file="include/addGroupModal.jsp" %>
						<!-- 우측메뉴 -->
						
						<div id="groupChatDiv">
							<input type="hidden" id="currentGroup" value="">
							<iframe id="groupChatFrame" src=""></iframe>
						</div>
						<%@ include file="include/sendMoneyGroupModal.jsp" %>
						
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