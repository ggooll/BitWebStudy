<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="httpRequest.js"></script>
<script>

	function clearOnClick(){
		var debug = document.getElementById("debug");
		debug.value = "";
	}

	function sendOnClick() {
		sendProcess('POST', 'sample.xml', null, callback);
	}

	// 서버의 응답이 왔을 경우 하고싶은 일
	function callback() {
		var msg = "";
		// 모든 데이터 전송 완료 ( 0 ~ 4까지 값 )
		if (httpRequest.readyState == 4) {
			// 정상적인 요청
			if (httpRequest.status == 200) {
				var xmlDoc = httpRequest.responseXML;
				var memberList = xmlDoc.getElementsByTagName("member");
				msg += "회원수 : " + memberList.length + '명\n';

				for (var i = 0; i < memberList.length; i++) {
					var mem = memberList[i];
					var id = mem.getElementsByTagName("id")[0].firstChild.nodeValue;
					var name = mem.getElementsByTagName("name")[0].firstChild.nodeValue;
					msg += id + "(" + name + ")\n";
				}
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
		<br> <input type="button" value="자료요청" onclick="sendOnClick()">
		<input type="button" value="clear" onclick="clearOnClick()">
	</form>

</body>
</html>