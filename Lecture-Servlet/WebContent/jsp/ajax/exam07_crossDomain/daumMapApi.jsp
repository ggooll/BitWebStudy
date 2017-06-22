<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="//apis.daum.net/maps/maps3.js?apikey=7f816929ac818e4e5c03b34a660323e3&libraries=services,clusterer,drawing"></script>
<script>
	$(document).ready(
			function() {
				var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
				mapOption = {
					center : new daum.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
					level : 3
				// 지도의 확대 레벨
				};

				var map = new daum.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

				// 비트 위치 맵에 찍기
				$('#mapBtn').click(function(){
					var ps = new daum.maps.services.Places();
					var query = $('#query').val();
					ps.keywordSearch(query, placesSearchCB); 
				});

				function placesSearchCB (status, data, pagination) {
				    if (status === daum.maps.services.Status.OK) {

				        // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
				        // LatLngBounds 객체에 좌표를 추가합니다
				        var bounds = new daum.maps.LatLngBounds();

				        for (var i=0; i<data.places.length; i++) {
				            displayMarker(data.places[i]);    
				            bounds.extend(new daum.maps.LatLng(data.places[i].latitude, data.places[i].longitude));
				        }       

				        // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다.
				        map.setBounds(bounds);
				    } 
				}

				
				function displayMarker(place) {				    
				    // 마커를 생성하고 지도에 표시합니다
				    var marker = new daum.maps.Marker({
				        map: map,
				        position: new daum.maps.LatLng(place.latitude, place.longitude)
				    });

/* 				    // 마커에 클릭이벤트를 등록합니다
				    daum.maps.event.addListener(marker, 'click', function() {
				        // 마커를 클릭하면 장소명이 인포윈도우에 표출됩니다
				        infowindow.setContent('<div style="padding:5px;font-size:12px;">' + place.title + '</div>');
				        infowindow.open(map, marker);
				    }); */
				}

			});
</script>

</head>
<body>
	<div id="map" style="width: 500px; height: 400px;"></div>
	<input type="text" name="query" id="query">
	<button id="mapBtn">위치보기</button>

</body>
</html>