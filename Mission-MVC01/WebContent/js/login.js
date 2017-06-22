function checkLoginForm() {
	var form = document.loginForm;

	if (isEmptyInput(form.id, "아이디를 입력하세요")) {
		return false;
	}

	if (isEmptyInput(form.password, "비밀번호를 입력하세요")) {
		return false;
	}

	return true;
}
