

function goMemberList() {
	location.href = "memberlist.do";
}

function checkForm() {
	var form = document.registryForm;

	// 아이디 중복여부	
	
	// 비밀번호 포맷(영어 + 숫자 혼합)

	if (isEmptyInput(form.id, '아이디를 입력하세요')) {
		return false;
	}

	if (isEmptyInput(form.name, '이름을 입력하세요')) {
		return false;
	}

	if (isEmptyInput(form.password, '비밀번호를 입력하세요')) {
		return false;
	}

	if (isEmptyInput(form.passwordChk, '비밀번호확인을 입력하세요')) {
		return false;
	}

	if (form.password.value !== form.passwordChk.value) {
		alert('비밀번호가 일치하지 않습니다.');
		form.password.focus();
		return false;
	}

	// 이메일 형식
	if (form.email_id.value != "" || form.email_domain.value != "") {
		var wholeEmail = form.email_id.value + "@" + form.email_domain.value;
		// 이메일을 입력했다면, 도메인도 포맷에 맞아야함
		var regExp = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
		if (!regExp.test(wholeEmail)) {
			alert("이메일을 제대로 입력하세요")
			form.email_id.focus();
			return false;
		}
	}

	// 핸드폰번호 정규식
	if (form.tel1.value != "" || form.tel2.value != "" || form.tel3.value != "") {
		// tel1, tel2와 tel3도 제대로 입력해야댐
		var fullTel = form.tel1.value + "-" + form.tel2.value + "-"
				+ form.tel3.value;
		var regExp = /^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})$/;
		if (!regExp.test(fullTel)) {
			alert("핸드폰번호를 제대로 입력하세요")
			form.tel1.focus();
			return false;
		}
	}

	// 우편번호 입력시(숫자6개)
	if (post != "") {
		var regExp = /^\d{6}$/;
		if (!regExp.test(post)) {
			alert("우편번호를 제대로 입력하세요")
			form.post.focus();
			return false;
		}
	}
	return true;
}
