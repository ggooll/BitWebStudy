<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<link href="${context}/css/requestMoneyModal.css" rel="stylesheet">
<script>
// 서버로 보냄
// 해당 번호의 고객과 거래내역 생성(deal table)
// sender = 친구번호 receiver가 내가 됨
$(document).ready(function(){
	
	$('#request_submit').click(function(){
		// alert($('#debtorNo').val());
		var money = $('#requestMoney').val();
		var money = $('#money').val();
		if(money >= 100000000 || money <= 0){
			alert('충전금액을 제대로 입력하세요');
			return;
		}
		var senderNo = $('#debtorNo').val();
		var comments = $('#requestComments').val();
		var dealData = 'senderNo='+senderNo+'&comments='+comments+'&money='+money;

	 	$.ajax({
		 	type : "POST",
		  	url : "${context}/requestMoney.do",
		  	data : dealData,
		  	async : false,
		  	success : function(data){
		  		flag = data.trim();
		  		if(flag == '1'){
		  			alert(" 성공적으로 요청했습니다. ");
		  			$('#requestMoneyModal').modal('toggle');
		  			$('#requestMoneyModal').hide();
		  		} else {
		  			alert('요청에 실패했습니다. 관리자에게 문의하세요');
		  		}
		  	},
	  		error : function(request,status,error){
	  		    alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	  	   }
 		});
	});
});

</script>

<div class="modal fade" id="requestMoneyModal" role="dialog" tabindex="-1" style="background: #B2A65B;">
	<div class="request-top-layer">
		<input type="hidden" id="debtorNo" value="">
			<h1 id="main-logo"><span id="debtorName"></span>(<span id="debtorId"></span>)님께 돈 쪼기</h1>
			<form id="requestMoneyForm" method="post">
				<div title="requestComments">
					<span class="add-on"><i class="halflings-icon white ok"></i>내 용</span> 
					<input name="requestComments" id="requestComments" type="text" />
				</div>
				<div class="clearfix" style="margin-top : 5px; margin-bottom :5px"></div>
				
				<div title="requestMoney">
					<span class="add-on"><i class="halflings-icon white ok"></i>금 액</span> 
					<input name="requestMoney" id="requestMoney" type="number" placeholder="금액"/>
				</div>
				<div class="clearfix" style="margin-top : 5px; margin-bottom :5px"></div>
				
				<div class="clearfix" style="margin-top : 5px; margin-bottom :5px"></div>
				<input type="button" class = "btn btn-large btn-primary" id="request_submit" value="보내기">
				&nbsp;
				<input type="button"  data-dismiss="modal" class="btn btn-large btn-primary" value="취 소">
				<div class="clearfix"></div>
			</form>
	</div>
</div>

