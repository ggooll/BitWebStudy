$(document).ready(function() {
	Kakao.init('d63d0f6322c8842d5d1626d9f09d9c19');
});

function createKakaotalkLogin() {
	$("#kakao-logged-group .kakao-logout-btn,#kakao-logged-group .kakao-login-btn").remove();
	var loginBtn = $("<a/>", {
		"id" : "kakao-login-btn",
		"class" : "kakao-login-btn"
	});
	$("#kakao-logged-group").prepend(loginBtn);
	Kakao.Auth.login({
		persistAccessToken : true,
		persistRefreshToken : true,
		success : function(authObj) {
			// 로그인 성공시, API를 호출합니다.
			Kakao.API.request({
				url : '/v1/user/me',
				success : function(res) {
					// alert(JSON.stringify(res));
					var kakaoID = res.kaccount_email;
					var kakaoNickName = res.properties.nickname;
					kakaoLogin(kakaoID, kakaoNickName);
				},
				fail : function(error) {
					alert(JSON.stringify(error));
				}
			});
		},
		fail : function(err) {
			alert(JSON.stringify(err));
		}
	});
}

function kakaoLogin(kakaoId, kakaoNickName){
 	$.ajax({
	 	type : "POST",
	  	url : "/BondDebtManager/apiLogin.do",
	  	data : 'id='+kakaoId+'&name='+kakaoNickName,
	  	async : true,
	  	success : function(data){
	  		flag = data.trim();
	  		if(flag == '1'){
	  			alert('안녕하세요')
	  			location.href="/BondDebtManager/hello.do";
	  		} else {
	  			alert('아이디 또는 비밀번호가 올바르지 않습니다.');
	  		}
	  	},
  		error : function(request,status,error){
  		    location.href="/BondDebtManager/hello.do";
  	   }
	});
}

function checkToken() {
	var accessToken = Kakao.Auth.getAccessToken();
	var refreshToken = Kakao.Auth.getRefreshToken();
	// alert("accessToken : " + accessToken + "\nrefreshToken : " + refreshToken);
}
