<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
	#msgView {
		border: 1px solid red;
		height: 200px;
		width: 500px;
	}
</style>
<script>
	var httpRequest = null;
	
	function getXMLHttpRequest() {
		if(window.ActiveXObject)
			return new ActiveXObject("Mircrosoft.XMLHTTP");
		if(window.XMLHttpRequest)
			return new XMLHttpRequest();
		return null;
	}
	
	function sendRequest(method, url, param, callback) {
		httpRequest.onreadystatechange = callback;
		httpRequest.open(method, url, true);
		httpRequest.send(param);
	}
	
	function requestMsg() {
		httpRequest = getXMLHttpRequest();
		sendRequest("GET", "hello.jsp", null, responseMsg);
	}
	
	function responseMsg() {
		if(httpRequest.readyState == 4) {
			if(httpRequest.status == 200) {
				var msgView = document.querySelector("#msgView");
				msgView.innerHTML = httpRequest.responseText;
			}
		}
	}
</script>
</head>
<body>
	<h2>서버에서 받은 메세지</h2>
	<div id="msgView">
	</div>
	<input type="button" value="서버에 자료 전송" onclick="requestMsg()"/>
</body>
</html>




