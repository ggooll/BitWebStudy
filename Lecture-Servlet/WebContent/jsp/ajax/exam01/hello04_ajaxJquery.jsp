<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-3.2.1.min.js"></script>
<script>
	$(document).ready(function() {

		$('#requestBtn').click(function() {

			/* 
				ajax가 가지고있는
				url : 요청 주소(문자열)
				type : 메소드(문자열) - default get
				data : 파라미터(문자열, 객체) 
				success : 수신 성공(함수) (data = responseText)
				error : 수신 실패(함수)
				async : 동기(false) / 비동기(true)
			 */
			$.ajax({
				url : 'hello.jsp',

				// 성공시 파라미터 data가 responseText임
				success : function(data) {
					//$('#msgView').html(data);
					//$('#msgView').append(data);
					$(data).appendTo('#msgView');
				},
				error : function() {
					alert('실패');
				}

			});

		});

	});
</script>
<style>
#msgView {
	border: 1px solid red;
	width: 500px;
	height: 300px;
}
</style>
</head>
<body>
	<h2>서버에서 받은 메시지</h2>
	<div id="msgView"></div>
	<input type="button" value="요 청" id="requestBtn">
</body>
</html>