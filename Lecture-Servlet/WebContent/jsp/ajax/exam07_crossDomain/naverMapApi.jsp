<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?clientId=L0U61cXtqaHKiAuSXXni&submodules=geocoder"></script>
<script src="http://code.jquery.com/jquery-3.2.1.min.js"></script>
<title>Insert title here</title>
<script>
	$(document).ready(function() {

		
		var map = new naver.maps.Map('map');
	    $('#search').on('click', function(e) {
	        e.preventDefault();
	        var address = $('#addressQuery').val();
	        if(address == ""){
		        return
		    }
	        searchAddressToCoordinate(address);
	    });

	    
	    function searchAddressToCoordinate(myaddress) {
			naver.maps.Service.geocode({address: myaddress}, function(status, response) {

		        if (status !== naver.maps.Service.Status.OK) {
		            return alert(myaddress + '의 검색 결과가 없습니다');
		        }

		        var result = response.result;
		        // 검색 결과 갯수: result.total
		        // 첫번째 결과 결과 주소: result.items[0].address
		        // 첫번째 검색 결과 좌표: result.items[0].point.y, result.items[0].point.x
		        var myaddr = new naver.maps.Point(result.items[0].point.x, result.items[0].point.y);
		        map.setCenter(myaddr); // 검색된 좌표로 지도 이동
		        // 마커 표시
		        var marker = new naver.maps.Marker({
		          position: myaddr,
		          map: map
		        });
		        // 마커 클릭 이벤트 처리
		        naver.maps.Event.addListener(marker, "click", function(e) {
		          if (infowindow.getMap()) {
		              infowindow.close();
		          } else {
		              infowindow.open(map, marker);
		          }
		        });
		        // 마크 클릭시 인포윈도우 오픈
		        var infowindow = new naver.maps.InfoWindow({
		            content: '<h4> [네이버 개발자센터]</h4><a href="https://developers.naver.com" target="_blank"><img src="https://developers.naver.com/inc/devcenter/images/nd_img.png"></a>'
		        });
		    });	  
		}  

		
	});

	

	

	
</script>
</head>
<body>

	<div id="map" style="width: 500px; height: 400px; border: 1px solid red"></div>
	<br>
	<input type="text" name="address" id="addressQuery">
	<input type="button" value="검색" id="search">

</body>
</html>