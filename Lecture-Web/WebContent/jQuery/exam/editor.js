$(function() {
	$('#btnSelectAll').click(function() {
		getSelectOffset();
	});

	$('#btnBold').click(function() {
		var offsets = getSelectOffset();
		var text = $('#textdiv').html();
		// substring 전후에 태그를 넣음
		var repStr = "메롱";
		text = text.replace(text.substring(offsets[0], offsets[1]), repStr);
		// [0]부터 ~까지,  [1]부터 마지막까지
		// 그 사이의 문자를 태그로감싸서 다시 집어넣는다.,..
		$('#textdiv').html(text);
	});

});

function getSelectOffset() {
	var selected = window.getSelection();
	var offsets = [ selected.focusOffset, selected.baseOffset ];
	// 시작과 끝(긁기 시작한 순간이 focus임 오름차순 정렬해야함)
	offsets.sort(function(left, right) {
		return left - right;
	});
	alert(offsets);
	return offsets;
}

//http://pritomkumar.blogspot.kr/2013/06/how-to-get-selecteduser-highlighted.html