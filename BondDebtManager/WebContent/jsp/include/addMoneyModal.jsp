 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<link href="${context}/css/addMoneyModal.css" rel="stylesheet">
<script>
$(document).ready(function() {
	 $("#addMoney_submit").click(function() {
		var money = $('#money').val();
		if(money >= 100000000 || money <= 0){
			alert('충전금액을 제대로 입력하세요');
			return;
		}
		
	 	var formData = $("#addMoneyForm").serialize();
	 	$.ajax({
		 	type : "POST",
		  	url : "${context}/addMoney.do",
		  	data : formData,
		  	async : true,
		  	success : function(data){
		  		flag = data.trim();
		  		if(flag == '1'){
		  			alert("충전에 성공했습니다.");
		  			$('#money').val("");
		  			$('#addMoneyModal').modal('toggle');
		  			$('#addMoneyModal').hide();
		  		} else {
		  			alert('충전에 실패했습니다. 관리자에게 문의하세요');
		  		}
		  	},
	  		error : function(request,status,error){
	  		    alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	  	   }
 		});
	});
});
</script>
 
<div class="modal fade" id="addMoneyModal" role="dialog" tabindex="-1">
	<div class="addMoney-top-layer">
		<div style="margin-top : 50px">
			<span id="money-logo">포인트 충전</span>
		</div>
		<br>
		<div class="">
			<form id="addMoneyForm" method="post">
				<span id="currentBalance">현재 가용금액 : <span id="available"></span>원</span>
				<div class="" title="">
					<input name="money" id="money" type="number" placeholder="충전 금액" />
				</div>
				<div class="clearfix" style="margin-top : 5px; margin-bottom :5px"></div>

				<div class="clearfix" style="margin-top : 5px; margin-bottom :5px"></div>
				<input type="button" class="btn btn-large btn-primary" id="addMoney_submit" value="충 전">
				&nbsp;
				<input type="button"  data-dismiss="modal" class="btn btn-large btn-primary" value="취 소">
				<div class="clearfix"></div>
			</form>
		</div>
	</div>
</div>
 