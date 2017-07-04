<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, width=device-width"/>
<c:set var="context" value="${pageContext.request.contextPath}" />
<link href="${context}/bootstrap/css/style-forms.css" rel="stylesheet" type="text/css">
<link href='https://fonts.googleapis.com/earlyaccess/hanna.css' rel='stylesheet' type='text/css'>
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
<script src="${context}/js/kakaoLogin.js"></script>
<meta name="google-signin-scope" content="profile email">
<meta name="google-signin-client_id" content="493262486597-11e01tu7nvkemubri32n8303ls4tkl4q.apps.googleusercontent.com">
<script src="https://apis.google.com/js/platform.js" async defer></script>
<script src="https://www.gstatic.com/firebasejs/4.1.3/firebase.js"></script>
<script src="${context}/js/googleLogin.js"></script>

<script>
$(document).ready(function(){
	$('#loginModal').hide();
	$('#signUpModal').hide();
	$('#addMoneyModal').hide();
	
	<c:if test="${not empty loginUser}">	
		loadAlarm();
	</c:if>
});

$('.notifications').empty();

function loadAlarm(){
	$.ajax({
		type: "POST",
		url : "${context}/selectTopDeal.do",
		success : function(data){
			var json = JSON.parse(data);
			console.log(json);

			if(json.length > 0){
				$('#top5Badge').text(json.length);
			}

			var title = '<li class="dropdown-menu-title"><span>You have ';
			title = title + json.length + ' notifications</span><a href="#"><i class="icon-repeat"></i></a></li>';
			
			$('.notifications').append(title);
			
			for(var i=0; i<json.length; i++){
				console.log(json[i].comments);
				var content = '<li><a><span class="icon red"><i class="icon-user"></i></span>';
				content = content + '<span class="message">';
				content = content + json[i].receiverId + '님 : ' + json[i].comments + '</span></a></li>';
				$('.notifications').append(content);
			}

			/* var repeat = '<li><a href="#"><i class="icon-repeat"></i></a></li>';
			$('.notifications').append(repeat); */
		},
		error : function(request,status,error){
			alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});
}

$(document).ready(function(){
	$('#addMoneyLink').click(function(){
		var availableMoney;
	 	$.ajax({
		 	type : "POST",
		  	url : "${context}/selectMoney.do",
		  	async : false,
		  	success : function(data){
		  		availableMoney = data.trim();
		  	},
	  		error : function(request,status,error){
	  		    alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	  	   }
 		});
		
		$('#available').html(availableMoney);
		$('#addMoneyModal').modal();
	});
});
</script>

<div class="navbar">
    <div class="navbar-inner">
        <div class="container-fluid">
			<a class="brand" href="${context}/hello.do">
				<span id="brandLogo" style="font-size : 25px">
				<img id="top-img-logo" src="${context}/image/top-img-logo.png" style="width:25px; height: 25px">
				오늘은 내가 총무</span>
			</a>
            <!-- start: Header Menu -->
            <div class="nav-no-collapse header-nav">
                <ul class="nav pull-right">   
                
                	<c:choose>
	                	<c:when test="${empty sessionScope.loginUser}">
		                    <li>
		                    	<a class="btn" href="#" data-toggle="modal" data-target="#signUpModal"> 회원가입 </a>
		                    </li>
		                    <li class="dropdown">
		                        <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
		                            <i class="halflings-icon white user"></i> ${sessionScope.loginUser.no}<span class="caret"></span>
		                        </a>
		                        <ul class="dropdown-menu">
		                            <li class="dropdown-menu-title">
		                                <span>Account Settings</span>
		                            </li>
		                            
		                            <!-- 세션검사 후 토글 -->
		                            <li><a href="#" data-toggle="modal" data-target="#loginModal">
		                            	<i class="halflings-icon off" style="width:20px; height:20px"></i> Login</a>
		                            </li>
		                                    		
			                		<!-- kakao로 로그인 -->
			                		<li>
										<div id="kakao-logged-group">
											<a id="kakao-login-btn"
												href="javascript:createKakaotalkLogin()"> <img
												src="https://lh5.ggpht.com/yVfPv-yLjIuBjpKj41NLkLXmuVv8XzH0m2hf-_sz9lQDv9WB9SX0McB8Jn4bQe4w5Q=w300"
												style="width:20px; height:20px; padding:0;" />
												Kakao Login
											</a>
										</div>
									</li>
		                        	<li>
			                			<div>
				                			<a href="javascript:createGoogleLogin()">
				                				<img src="${context}/image/google.png" 
				                				style="width:20px; height:20px;"/>
				                				Google Login
				                			</a>
			                			</div>
									</li>		                        
		                        </ul>
		                    </li>
	                    </c:when>
	                    
	                    <c:otherwise>
		                    <li class="dropdown hidden-phone top5">
		                        <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
		                            <i class="icon-bell"></i>
		                            <span id="top5Badge" class="badge red"></span>
		                        </a>
		                        <ul class="dropdown-menu notifications">
	                                
		                        </ul>
		                    </li>
		                    
		                    <li>
		                    	<a class="btn" id="addMoneyLink" ><i class="halflings-icon white plus-sign"></i>포인트충전 </a>
		                    </li>
		                    
		                    <!-- start: User Dropdown -->
		                    <li class="dropdown">
		                        <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
		                            <i class="halflings-icon white user"></i> ${sessionScope.loginUser.name}<span class="caret"></span>
		                        </a>
		                        <ul class="dropdown-menu">
		                            <li class="dropdown-menu-title">
		                                <span>Account Settings</span>
		                            </li>
		                            
		                            <!-- 세션내리기 -->
		                            <li><a href="${context}/logout.do"><i class="halflings-icon off"></i>Logout</a></li>
		                        </ul>
		                    </li>
	                    </c:otherwise>
                    </c:choose>
                    
                </ul>
            </div>
        </div>
    </div>
</div>


<%@ include file="loginModal.jsp" %>
<%@ include file="signUpModal.jsp" %>
<%@ include file="addMoneyModal.jsp" %>


