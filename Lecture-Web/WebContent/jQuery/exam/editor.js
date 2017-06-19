$(function() {

	$('#textDiv').attr("spellcheck", false);

	$('#textDiv').keydown(function(e) {
		if (e.keyCode === 13) {
			document.execCommand('insertHTML', false, '<br><br>');
			return false;
		}
	});

	$('#textDiv').keypress(function(e) {
		if ((e.which == 90 || e.keyCode == 90) && e.ctrlKey) {
			document.execCommand('undo');
		}
	});

	$('#textDiv').focus(function() {
		$('#textDiv').css('border', '2px solid #F06D65');
	});

	$('#textDiv').blur(function() {
		$('#textDiv').css('border', '2px solid gray');
	});
});

/**
 * 전체선택, 전체지우기, 속성제거 실행취소, 다시실행
 */
$(function() {

	$('#selectAll').click(function() {
		$('#textDiv').focus();
		document.execCommand('selectAll');
	});

	$('#delete').click(function() {
		$('#textDiv').focus();
		document.execCommand('selectAll');
		document.execCommand('delete');
		$('#textDiv').focus();
	});

	$('#removeFormat').click(function() {
		document.execCommand('selectAll');
		document.execCommand('removeFormat');
		$('#textDiv').focus();
	});

	$('#undo').click(function() {
		document.execCommand('undo');
	});

	$('#redo').click(function() {
		document.execCommand('redo');
	});

})

/**
 * font 스타일 버튼
 */
$(function() {

	$('.decoration, .justify').click(function() {
		var command = $(this).attr('id');
		document.execCommand(command);
	})

	$('.fontAttr').change(function() {
		var attr = $(this).attr('id');
		var val = $(this).val();
		document.execCommand(attr, false, val);
		$(this).val("").attr("selected", "selected");
	});

});
