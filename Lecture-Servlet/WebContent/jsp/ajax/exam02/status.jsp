<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>
	var httpRequest = null;
	
	function getXMLHttpRequest() {
		if(window.ActiveXObject)
			return new ActiveXObject("Mircrosoft.XMLHTTP");
		if(window.XMLHttpRequest)
			return new XMLHttpRequest();
		return null;
	}
	
	function sendProcess() {
		httpRequest = getXMLHttpRequest();
		
		httpRequest.onreadystatechange = callbackFunction;
		httpRequest.open("GET", "hello.jsp", true);
		httpRequest.send(null);
	}
	
	function callbackFunction() {
		/*
			Status(상태코드)
			200 : OK(처리완료)
			403 : 액세스 거부
			404 : FILE NOT FOUND
			405 : 서블릿에러
			500 : 내부서버에러
		*/
		
		var debug = document.getElementById("debug");
		switch(httpRequest.readyState) {
		case 1 : 
			debug.value += "Loading...\n";
			break;
		case 2 :
			debug.value += "Loaded...\n";
			break;
		case 3 : 
			debug.value += "Interactive...\n";
			break;
		case 4 : 
			debug.value += "Complete...\n"; 
			// 서버 응답 후 상태코드 표시
			if(httpRequest.status == 200)
				debug.value += '웹서버에서 정상적으로 수행...\n';
			else {
				debug.value += '웹서버에서 오류발생...\n';
				debug.value += '오류코드 : ' + httpRequest.status + '\n';
				debug.value += '오류내용 : ' + httpRequest.statusText + '\n';
			}
			break;
		}
	}
	
	function clearOnClick() {
		var debug = document.getElementById("debug");
		debug.value = "";
	}
	
</script>
</head>
<body>
	<textarea rows="10" cols="80" id="debug"></textarea><br/>
	<input type="button" value="서버호출" onclick="sendProcess()" />
	<input type="button" value="초기화" onclick="clearOnClick()"/>
</body>
</html>






