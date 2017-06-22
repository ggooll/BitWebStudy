<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="httpRequest.js"></script>
<script src="http://code.jquery.com/jquery-3.2.1.min.js"></script>
<script>
	function clearOnClick() {
		var debug = document.getElementById("debug");
		debug.value = "";
	}

	function sendOnClick() {
		sendProcess('POST', 'sample.xml', null, callback);
	}

	// 서버의 응답이 왔을 경우 하고싶은 일
	function callback() {
		var msg = "";
		if (httpRequest.readyState == 4) {
			if (httpRequest.status == 200) {
				// responseXML 과 똑같이 동작
				var xmlDoc = $.parseXML(httpRequest.responseText);
				var memberList = $(xmlDoc).find("member");
				msg += "회원수 : " + memberList.length + '명\n';

				// forEach.....
				memberList.each(function(index) {
					var id = $(this).find("id").text();
					var name = $(this).find("name").text();
					msg += id + "(" + name + ")\n";
				});
				msg += '\n';
			}
		}
		debugTrace(msg);
	}

	function debugTrace(msg) {
		var debug = document.getElementById("debug");
		debug.value += msg;
	}
</script>
</head>
<body>

	<h2>XML Data 응답 예제</h2>
	<form>
		<textarea rows="10" cols="80" id="debug"></textarea>
		<br> <input type="button" value="자료요청" onclick="sendOnClick()"> <input type="button" value="clear" onclick="clearOnClick()">
	</form>

</body>
</html>