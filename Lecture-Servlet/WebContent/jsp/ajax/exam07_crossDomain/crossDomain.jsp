<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>크로스 도메인</title>
<script src="http://code.jquery.com/jquery-3.2.1.min.js"></script>
<script>
	$(document).ready(function() {
		$(':button').click(function() {
			var query = $('#query').val();
			
			// 크로스 도메인 정책에의해, ajax는 내 사이트내의 것만 접근가능하다.
			// 따라서 내 웹app에서 기본적으로 daum것을 가져올 수 없다.
			$.ajax({
				url : 'https://apis.daum.net/search/blog',
				type : 'get',
				data : {
					apikey : '7f816929ac818e4e5c03b34a660323e3',
					q : query,
					output : 'json',
					result : 6,
					sort : 'accu'
				},
				
				// 다른 웹 서버의 타입을 사용하기 위해서 jsonp를 사용해야함
				dataType : 'jsonp',
				success : callback,
				error : function(){
					alert('error');
				}	
			});
		});
	});

	function callback(data){
		$('#searchResult').html("");
		var items = data.channel.item;
		for(var i = 0; i<items.length; i++){
			var item = items[i];
			var title = item.title;
			var link = item.link;
			var description = item.description;

			$('#searchResult').append('<hr>');
			$('#searchResult').append('<a href=' + link + ' target="_blank">' + title + '</a><br>');
			$('#searchResult').append('<div>' + description + '</div>');
			$('#searchResult').append('<hr>');
		}
	}
</script>
<style>
#searchResult {
	border: 1px solid red;
	width: 70%;
	height: 800px;
}
</style>

</head>
<body>
	<h2>블로그 검색</h2>
	검색어 :
	<input type="text" name="query" id="query">
	<input type="button" value="검 색">
	<br>

	<h4>검색결과</h4>
	<div id="searchResult"></div>
</body>
</html>