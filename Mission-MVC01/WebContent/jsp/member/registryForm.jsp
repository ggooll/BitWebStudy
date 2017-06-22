<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>회원가입</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:set var="context" value="${pageContext.request.contextPath}" />
<script src="${context}/js/member.js"></script>
<script src="http://code.jquery.com/jquery-3.2.1.min.js"></script>
<link rel="stylesheet" href="${context}/css/layout.css">
<link rel="stylesheet" href="${context}/css/board.css">

<script>
	$(document).ready(function() {
		$('#checkDupId').click(function() {

			var id = $('#inputId').val();			
			$.ajax({
				url : 'checkDupId.do',
				data : 'id='+id,
				success : callback,
				error : function(){
					alert('error');
				}
			});

		});
	});

	function callback(data){
		var exist = data.trim();
		if(exist === '1'){
			$('#resultCheckId').text("이미 존재하는 아이디입니다");
			$('#resultCheckId').css({'color':'red'});
		} else {
			$('#resultCheckId').text("사용할 수 있습니다.");
			$('#resultCheckId').css({'color':'green'});
		}
	}
</script>
<style>
	#resultCheckId{
		font-size: 12pt;
		margin-left : 5px;
	}
</style>


</head>
<body>

	<!-- xml, include, forward에서 절대경로의 /는 프로젝트 이후부터 -->
	<header>
		<jsp:include page="/jsp/include/topMenu.jsp" />
	</header>

	<section>
		<!-- // 회원 리스트 보여줌 -->
		<div id="container">
			<div id="header" align="center">
				<hr class="boldHr">
				<h1>회원가입</h1>
				<hr class="boldHr">
			</div>

			<div id="formDiv">
				<form action="${context}/registryProcess.do" method="post" name="registryForm" onsubmit="return checkForm()">
					<hr>
					<div>
						<span class="infoLogo">아이디 : </span><input type="text" name="id" id="inputId"> <input type="button" id="checkDupId" value="중복체크"><span id="resultCheckId"></span>
					</div>
					<hr>
					<div>
						<span class="infoLogo">이름 : </span><input type="text" name="name">
					</div>
					<hr>
					<div>
						<span class="infoLogo">비밀번호 : </span><input type="password" name="password">
					</div>
					<hr>
					<div>
						<span class="infoLogo">비밀번호확인 : </span><input type="password" name="passwordChk">
					</div>
					<hr>
					<div>
						<span class="infoLogo">이메일 : </span><input type="text" name="email_id">@<input type="text" name="email_domain">
					</div>
					<hr>
					<div>
						<span class="infoLogo">전화번호 : </span><input type="text" name="tel1" size=3 maxlength="3"> - <input type="text" name="tel2" size=4
							maxlength="4"> - <input type="text" name="tel3" size=4 maxlength="4">
					</div>
					<hr>
					<div>
						<span class="infoLogo">우편번호 : </span><input type="text" name="post">
					</div>
					<hr>
					<div>
						<span class="infoLogo">주소 : </span><input type="text" name="basic_addr" size=65>
					</div>
					<hr>
					<div>
						<span class="infoLogo">상세주소 : </span><input type="text" name="detail_addr" size=65>
					</div>
					<hr>
					<br>
					<hr class="boldHr">
					<br>
					<div id="submitDiv" align="center">
						<input type="submit" value="등록">&nbsp;&nbsp;<input type="button" value="목록으로" onclick="goMemberList()">
					</div>
				</form>
			</div>
		</div>

	</section>

	<footer>
		<%@ include file="/jsp/include/bottom.jsp"%>
	</footer>
</body>
</html>