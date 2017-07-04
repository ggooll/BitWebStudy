$(function() {
	
	$('.onlyAlphabetAndNumber').keyup(function(event) {
		if (!(event.keyCode >= 37 && event.keyCode <= 40)) {
			var inputVal = $(this).val();
			$(this).val($(this).val().replace(/[^_a-z0-9]/gi, '')); //_(underscore), 영어, 숫자만 가능
		}
	});

	$(".onlyHangul").keyup(function(event) {
		if (!(event.keyCode >= 37 && event.keyCode <= 40)) {
			var inputVal = $(this).val();
			$(this).val(inputVal.replace(/[a-z0-9]/gi, ''));
		}
	});

	//------- 적을때마다 검사
	$('#id').keyup(function(event) {
		var divId = $('#divId');
		
//		if ($('#id').val() == "") {
//			$('#id').css("color", "green");
//		} else {
//			$('#id').css('color', 'red');
//		}
	});

	$('#password').keyup(function(event) {

		var divPassword = $('#divPassword');

		if ($('#password').val() == "") {
			divPassword.removeClass("has-success");
			divPassword.addClass("has-error");
		} else {
			divPassword.removeClass("has-error");
			divPassword.addClass("has-success");
		}
	});

	$('#passwordCheck').keyup(function(event) {
		var passwordCheck = $('#passwordCheck').val();
		var password = $('#password').val();
		var divPasswordCheck = $('#divPasswordCheck');

		if ((passwordCheck == "") || (password != passwordCheck)) {
			divPasswordCheck.removeClass("has-success");
			divPasswordCheck.addClass("has-error");
		} else {
			divPasswordCheck.removeClass("has-error");
			divPasswordCheck.addClass("has-success");
		}
	});

	$('#name').keyup(function(event) {
		var divName = $('#divName');
		if ($.trim($('#name').val()) == "") {
			divName.removeClass("has-success");
			divName.addClass("has-error");
		} else {
			divName.removeClass("has-error");
			divName.addClass("has-success");
		}
	});

	$('#nickname').keyup(function(event) {

		var divNickname = $('#divNickname');

		if ($.trim($('#nickname').val()) == "") {
			divNickname.removeClass("has-success");
			divNickname.addClass("has-error");
		} else {
			divNickname.removeClass("has-error");
			divNickname.addClass("has-success");
		}
	});

	$('#email').keyup(function(event) {
		var divEmail = $('#divEmail');
		if ($.trim($('#email').val()) == "") {
			divEmail.removeClass("has-success");
			divEmail.addClass("has-error");
		} else {
			divEmail.removeClass("has-error");
			divEmail.addClass("has-success");
		}
	});

	$('#tel').keyup(function(event) {
		var divPhoneNumber = $('#divPhoneNumber');
		if ($.trim($('#tel').val()) == "") {
			divPhoneNumber.removeClass("has-success");
			divPhoneNumber.addClass("has-error");
		} else {
			divPhoneNumber.removeClass("has-error");
			divPhoneNumber.addClass("has-success");
		}
	});

	//------- validation 검사
});

function checkSignUpForm(){
	var divId = $('#divId');
	var divPassword = $('#divPassword');
	var divPasswordCheck = $('#divPasswordCheck');
	var divName = $('#divName');
	var divNickname = $('#divNickname');
	var divEmail = $('#divEmail');
	var divPhoneNumber = $('#divPhoneNumber');

	// 아이디 검사
	if ($('#id').val() == "") {
		alert('아이디를 입력하여 주시기 바랍니다.');
		divId.removeClass("has-success");
		divId.addClass("has-error");
		$('#id').focus();
		return false;
	} else {
		divId.removeClass("has-error");
		divId.addClass("has-success");
	}

	// 패스워드 검사
	if ($('#pwd').val() == "") {
		alert("패스워드를 입력하여 주시기 바랍니다.");
		divPassword.removeClass("has-success");
		divPassword.addClass("has-error");
		$('#pwd').focus();
		return false;
	} else {
		divPassword.removeClass("has-error");
		divPassword.addClass("has-success");
	}

	// 패스워드 확인
	if ($('#pwdCheck').val() == "") {
		alert("패스워드 확인을 입력하여 주시기 바랍니다.");
		divPasswordCheck.removeClass("has-success");
		divPasswordCheck.addClass("has-error");
		$('#pwdCheck').focus();
		return false;
	} else {
		divPasswordCheck.removeClass("has-error");
		divPasswordCheck.addClass("has-success");
	}

	// 패스워드 비교
	if ($('#pwd').val() != $('#pwdCheck').val()
			|| $('#pwdCheck').val() == "") {
		alert("패스워드가 일치하지 않습니다.");
		divPasswordCheck.removeClass("has-success");
		divPasswordCheck.addClass("has-error");
		$('#pwdCheck').focus();
		return false;
	} else {
		divPasswordCheck.removeClass("has-error");
		divPasswordCheck.addClass("has-success");
	}

	// 이름
	if ($('#name').val() == "") {
		alert("이름을 입력하여 주시기 바랍니다.");
		divName.removeClass("has-success");
		divName.addClass("has-error");
		$('#name').focus();
		return false;
	} else {
		divName.removeClass("has-error");
		divName.addClass("has-success");
	}

	// 이메일
	if ($('#email').val() == "") {
		alert("이메일을 입력하여 주시기 바랍니다.");
		divEmail.removeClass("has-success");
		divEmail.addClass("has-error");
		$('#email').focus();
		return false;
	} else {
		var regExp = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
		if (!regExp.test($('#email').val())) {
			alert("이메일을 제대로 입력하세요")
			$('#email').focus();
			return false;
		}
		divEmail.removeClass("has-error");
		divEmail.addClass("has-success");
	}

	// 휴대폰 번호
	if ($('#tel').val() == "") {
		alert("휴대폰 번호를 입력하여 주시기 바랍니다.");
		divPhoneNumber.removeClass("has-success");
		divPhoneNumber.addClass("has-error");
		$('#tel').focus();
		return false;
	} else {
		var regExp = /^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})$/;
		if (!regExp.test($('#tel').val())) {
			alert("핸드폰번호를 제대로 입력하세요")
			$('#tel').focus();
			return false;
		}
		divPhoneNumber.removeClass("has-error");
		divPhoneNumber.addClass("has-success");
	}

}
