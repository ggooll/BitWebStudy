<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<link href="${context}/css/addFriendModal.css" rel="stylesheet">
<script>
$(document).ready(function() {
	$('#resultQuery').hide();
	$('#addFriend_submit').hide();
	
	 $("#addFriend_submit").click(function() {
	 	$.ajax({
		 	type : "POST",
		  	url : "${context}/insertFriend.do",
		  	data : {"friendNo": $('#resultFriendNo').val()},
		  	async : true,
		  	success : function(data){
		  		flag = data.trim();
		  		if(flag == '1'){
		  			alert("친구 추가하였습니다.");
		  			$('#addFriendModal').modal('toggle');
		  			$('#addFriendModal').hide();
		  			location.href="${context}/friendList.do";
		  		} else {
		  			alert('친구 추가에 실패했습니다.');
		  		}
		  	},
	  		error : function(request,status,error){
	  		    alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	  	   }
 		});
	});
	 
	$('#closeModal').click(function(){
		$('#resultFriendId').text("");
		$('#resultFriendName').text("");
		$('#friendId').val("");
		$('#addFriend_submit').hide();
	});
	
});

$(document).ready(function(){
	$('#selectBtn').click(function(){
		$.ajax({
			url: "${context}/selectFriend.do",
			type: "post",
			data: {"friendId": $('#friendId').val()},
			success: callback
		})
	})
})

function callback(data){
	var friend = JSON.parse(data);
	if(friend.friendId == ""){
		alert('해당 ID를 가진 회원이 없습니다.');
		return;
	}
	$('#resultFriendId').text(friend.friendId);
	$('#resultFriendName').text(friend.friendName);
	$('#resultFriendNo').val(friend.friendNo);
	$('#resultQuery').show();
	$('#addFriend_submit').show();
}
</script>

<div class="modal fade" id="addFriendModal" role="dialog" tabindex="-1">
	<div class="addFriend-top-layer">
		<div style="margin-top : 50px">
			<span id="main-logo">친구 검색</span>
		</div>
		<br>
		<div class="">
			<form id="addFriendForm" method="post">
				<div class="queryDiv">
					<input name="friendId" id="friendId" type="text" placeholder="친구 아이디" style="margin-bottom: 0"/>
					<input type="button" id="selectBtn" class="btn" value="검색"/>
				</div>
				<div class="clearfix" style="margin-top : 5px; margin-bottom :5px"></div>

				<div class="queryDiv" style="margin : 20px auto;">
					<span id="resultFriendId" class="resultSpan"></span>&nbsp;
					<span id="resultFriendName" class="resultSpan"></span>&nbsp;
					<input type="hidden" id="resultFriendNo" name="resultFriendNo"/>
					<input type="button" class="btn btn-primary" id="addFriend_submit" value="친구추가">&nbsp;
				</div>

				<div class="clearfix" style="margin-top : 5px; margin-bottom :5px"></div>
				<input type="button" id="closeModal" data-dismiss="modal" class="btn btn-primary" value="닫 기">
				<div class="clearfix"></div>
			</form>
		</div>
	</div>
</div>
 