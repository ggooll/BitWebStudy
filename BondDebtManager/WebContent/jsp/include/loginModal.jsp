<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<link href="${context}/css/loginModal.css" rel="stylesheet">
<script>
$(document).ready(function() {
	 $("#login_submit").click(function() {
	 	var formData = $("#loginForm").serialize();
	 	$.ajax({
		 	type : "POST",
		  	url : "${context}/login.do",
		  	data : formData,
		  	async : true,
		  	success : function(data){
		  		flag = data.trim().substring(0,1);
		  		if(flag == '1'){
		  			location.href="${context}/hello.do";
		  		} else {
		  			alert('아이디 또는 비밀번호가 올바르지 않습니다.');
		  		}
		  	}
 		});
	});
});

</script>

<div class="modal fade" id="loginModal" role="dialog" tabindex="-1" style="background: #2B3752;">
	<div class="main-top-layer">
		<div>
			<img id="main-image" src="${context}/image/credit-card-2.png">
			<!-- <img id="main-image" src="http://d1841mjet2hm8m.cloudfront.net/thumb-900/fb_1135/1620/11/cb4e8ea0770f388c3c598aca6425c25c.jpg"> -->
		</div>
		<div>
			<span id="main-logo">오늘은 내가 총무</span>
		</div>
		<br>
		<div class="">
			<form id="loginForm" method="post">
<%-- 			<form action="${context}/login.do" method="post" onsubmit="return checkLoginForm()"> --%>
				<div class="" title="Username">
					<span class="add-on"><i class="halflings-icon white user"></i></span> 
					<input class="" name="username" id="username" type="text" placeholder="username" />
				</div>
				<div class="clearfix" style="margin-top : 5px; margin-bottom :5px"></div>

				<div class="" title="Password">
					<span class="add-on" style="color:white !important"><i class="halflings-icon white lock"></i></span>
					<input class="" name="password" id="password" type="password" placeholder="password" />
				</div>
				<div class="clearfix" style="margin-top : 5px; margin-bottom :5px"></div>
				<input type="button" class = "btn btn-large btn-primary" id="login_submit" value="로그인">
				&nbsp;
				<button data-dismiss="modal" class="btn btn-large btn-primary">취 소</button>
				<div class="clearfix"></div>
			</form>
		</div>
	</div>
</div>


    