function goList() {
	location.href = "list.do";
}

function doUpdate() {
	var form = document.updateForm;

	if (isEmptyInput(form.title, '제목을 입력하세요')) {
		return false;
	}

	if (isEmptyInput(form.content, '내용을 입력하세요')) {
		return false;
	}
	return true;
}

function doWrite() {
	var form = document.writeForm;

	if (isEmptyInput(form.title, '제목을 입력하세요')) {
		return false;
	}
	if (isEmptyInput(form.writer, '작성자를 입력하세요')) {
		return false;
	}
	if (isEmptyInput(form.content, '내용을 입력하세요')) {
		return false;
	}

	if (checkExt(form.attachFile1)) {
		return false;
	}

	if (checkExt(form.attachFile2)) {
		return false;
	}

	return true;
}

// 파일 확장자 체크
// 파일첨부시 value로 파일명을 알 수 있음
function checkExt(obj) {
	var forbidName = [ 'exe', 'java', 'jsp', 'js', 'class', 'css' ];
	var fileName = obj.value;
	var position = fileName.lastIndexOf(".");
	var extName = fileName.substring(position + 1);

	for (var i = 0; i < forbidName.length; i++) {
		if (forbidName[i] == extName) {
			alert("해당 확장자 파일은 업로드할 수 없습니다.");
			return true;
		}
	}
	return false;
}
