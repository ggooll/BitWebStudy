<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:set var="context" value="${pageContext.request.contextPath}" />
<link id="bootstrap-style" href="${context}/bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="${context}/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
<link id="base-style" href="${context}/bootstrap/css/style.css" rel="stylesheet">
<link id="base-style-responsive" href="${context}/bootstrap/css/style-responsive.css" rel="stylesheet">
<link href='https://fonts.googleapis.com/earlyaccess/hanna.css' rel='stylesheet' type='text/css'>
<script src="http://code.jquery.com/jquery-3.2.1.min.js"></script>
<style>
*{
	color:black;
	font-family: hanna !important;
}

html{
	width: 620px;
	height: 1000px;
	margin: 0 auto;
}
body{
	margin-top : 50px;
	background-color : #FFFFF7;
}

.box-header{
	width: 596px !important;
}

.box-content{
	height: 600px !important;
	width: 596px !important;
}

ul.chat.metro{
	height: 600px !important;
	width: 596px !important;
	overflow: auto;
}

.span6{
	width: 616px;
	margin: 0 auto;
}

</style>
</head>
<body>
	<div class="box black span6">
		<div class="box-header">
			<h2><i class="halflings-icon white comment"></i><span class="break"></span>Chatting</h2>
			<div class="box-icon"></div>
		</div>
		<div class="box-content">
			<ul class="chat metro navy" id="groupChatBox">
				
			</ul>
			<div class="chat-form black" style="border:2px solid black">
				<textarea id="inputTextArea"></textarea>
				<span style="float:right; margin-bottom: 10px">
					<a class="btn btn-primary" onclick="sendMessage()">전 송</a>
					<a class="btn btn-primary" onclick="sendMoneyGroup()">돈 쪼기</a>
				</span>
			</div>	
		</div>
	</div>
</body>
<script>
	var textarea = document.getElementById("messageWindow");
//	var webSocket = new WebSocket('ws://192.168.1.61:8080/BondDebtManager/broadcasting');
	var webSocket = new WebSocket('ws://172.20.10.2:8080/BondDebtManager/broadcasting');
	var inputMessage = document.getElementById('inputTextArea');
	
	window.onbeforeunload = function (event) {
		var msg = {
				"type" : "2",
				"memberNo" : "${loginUser.no}",
				"memberName" : "${loginUser.name}",
				"groupNo" : "${param.groupNo}",
				"message" : ""
			}
		webSocket.send(JSON.stringify(msg));
    };
    
	webSocket.onerror = function(event) {
		onError(event)
	};
	
	webSocket.onopen = function(event) {
		onOpen(event);
		var firstMsg = {
			"type" : "0",
			"memberNo" : "${loginUser.no}",
			"memberName" : "${loginUser.name}",
			"groupNo" : "${param.groupNo}", //"${groupNo}",
			"message" : ""
		}
		webSocket.send(JSON.stringify(firstMsg));
	};
	
	webSocket.onmessage = function(event) {
		onMessage(event)
	};
	
	function onOpen(event) {
		// textarea.value += "연결 성공\n";
		$.ajax({
			type: "POST",
//			url: "http://192.168.1.61:8080/BondDebtManager/getChat.do",
			url: "http://172.20.10.2:8080/BondDebtManager/getChat.do",
			data : {
				groupNo : "${param.groupNo}"
			},
			success: function(data) {
				var jsonMsg = JSON.parse(data);
				$.each(jsonMsg, function(key, value) {
					if(value.memberName == '${loginUser.name}'){
						makeChatli(value.memberName, value.message, "right");
					} else {
						makeChatli(value.memberName, value.message, "left");
					}
				})
				scrolling();
			},
			error: function(error) {
				alert('채팅을 가져오는데 실패했습니다.');
			}
		});
	}
	
	function onError(event) {
		alert(event.data);
	}
	
	function sendMessage(setMessage) {
		var message = inputMessage.value;	
		
		if(setMessage != null){
			message = setMessage;
		}
		
		message = message.split("\n").join("<br/>");
		
		var msg = {
			"type" : "1",
			"memberNo" : "${loginUser.no}",
			"memberName" : "${loginUser.name}",
			"groupNo" : "${param.groupNo}", //"${groupNo}",
			"message" : message
		}
		makeChatli('${loginUser.name}', message, "right");
		webSocket.send(JSON.stringify(msg));
		inputMessage.value = "";
	}
	
	function onMessage(event) {
		var message = JSON.parse(event.data);
		makeChatli(message.name, message.message, "left");
	}
	
	function scrolling(){
		$('#groupChatBox').scrollTop($('#groupChatBox')[0].scrollHeight);
	}

	function makeChatli(name, message, dir){
		var imgsrc = "/BondDebtManager/image/chatme.png";
		if(dir == "left"){
			imgsrc = "/BondDebtManager/image/chatyou.png";
		}
		
		var str = '<li class="'+ dir +'">';
		str += '<img class="avatar" src="'+ imgsrc +'">';
		str += '<span class="message">';
		str += '<span class="arrow"></span>';
		str += '<span class="from"><strong>'+ name +'</strong></span>';
		str += '<span class="text">'+ message +'</span>';
		str += '</span></li>'
		$('#groupChatBox').append(str);
		scrolling();
	}
	
	function sendMoneyGroup(){
		window.parent.$('#sendMoneyGroupModal').modal();
	}

</script>
</html>


