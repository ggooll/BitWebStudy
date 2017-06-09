$(function() {

	$('#textDiv').attr("spellcheck", false);
	$('#textDiv').keydown(function(e) {
		if (e.keyCode === 13) {
			document.execCommand('insertHTML', false, '<br><br>');
			return false;
		}
	});

	$('#textDiv').focus(function() {
		$('#textDiv').css('border', '3px solid steelblue');
	});

	$('#textDiv').blur(function() {
		$('#textDiv').css('border', '2px solid gray');
	});

	$('#btnSelectAll').click(function() {
		$('#textDiv').focus();
		document.execCommand('selectAll', false, null);
	});

	$('#btnclearAll').click(function() {
		$('#textDiv').empty();
		$('#textDiv').focus();
	});

	$('#btnclearAttr').click(function() {
		$('#textDiv').html(makePureText());
	});
});

function makePureText() {
	// $('#textDiv *').css('background', '');
	$('#textDiv *').removeAttr('style');
	var regex = /(<([^(br)^>]+)>)/ig;
	var htmlText = $('#textDiv').html();
	var pureStr = htmlText.replace(regex, "");
	return pureStr;
}

/**
 * font style
 */

$(function() {
	$('#btnBold').click(function() {
		fontStyle("bold");
	});

	$('#btnItalic').click(function() {
		fontStyle("italic");
	})

	$('#btnUnderline').click(function() {
		fontStyle("underline");
	});
});

/**
 * textArea align
 */

$(function() {
	$('#btnAlignCenter').click(function() {
		$('#textDiv').attr('align', 'center');
	});

	$('#btnAlignLeft').click(function() {
		$('#textDiv').attr('align', 'left');
	});

	$('#btnAlignRight').click(function() {
		$('#textDiv').attr('align', 'right');
	});

});

/**
 * select Option event
 */

$(function() {
	$('#fontFamily').change(function() {
		var fontFamily = $('#fontFamily option:selected').val();
		if (fontFamily !== "") {
			fontStyle('fontFamily', fontFamily);
		}
		$("#fontFamily").val("").attr("selected", "selected");
	});

	$('#fontSize').change(function() {
		var size = $('#fontSize option:selected').val();
		if (size !== "") {
			fontStyle('size', size);
		}
		$("#fontSize").val("").attr("selected", "selected");
	});

	$('#fontColor').change(function() {
		var color = $('#fontColor option:selected').val();
		if (color !== "") {
			fontStyle('color', color);
		}
		$("#fontColor").val("").attr("selected", "selected");
	});

	$('#fontBackground').change(function() {
		var background = $('#fontBackground option:selected').val();
		if (background !== "") {
			fontStyle('background', background);
		}
		$("#fontBackground").val("").attr("selected", "selected");
	});
});

function fontStyle(type, value) {
	var range = window.getSelection().getRangeAt(0);
	if (range === "")
		return;
	var span = document.createElement('span');

	if (type === 'bold') {
		span.style.fontWeight = 'bold';
	} else if (type === 'italic') {
		span.style.fontStyle = 'italic';
	} else if (type === 'underline') {
		span.style.textDecoration = 'underline';
	} else if (type === 'size') {
		span.style.fontSize = value + 'pt';
	} else if (type === 'color') {
		span.style.color = value;
	} else if (type === 'background') {
		span.style.backgroundColor = value;
	} else if (type === 'fontFamily') {
		span.style.fontFamily = value;
	}

	// extractContents();
	span.appendChild(range.extractContents());
	range.insertNode(span);
}
