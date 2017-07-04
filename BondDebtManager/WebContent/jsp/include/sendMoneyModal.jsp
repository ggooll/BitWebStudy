<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<link href="${context}/css/sendMoneyModal.css" rel="stylesheet">
<script>
$(document).ready(function(){
	$('#send_submit').click(function(){
		// alert($('#debtorNo').val());
		var money = $('#sendMoney').val();
		var money = $('#money').val();
		if(money >= 100000000 || money <= 0){
			alert('충전금액을 제대로 입력하세요');
			return;
		}
		var receiverNo = $('#bonderNo').val();
		var comments = $('#sendComments').val();
		var dealData = 'receiverNo='+receiverNo+'&comments='+comments+'&money='+money;

	 	$.ajax({
		 	type : "POST",
		  	url : "${context}/sendMoney.do",
		  	data : dealData,
		  	async : false,
		  	success : function(data){
		  		flag = data.trim();
		  		if(flag == '1'){
		  			alert(" 성공적으로 보냈습니다. ");
		  			$('#sendMoneyModal').modal('toggle');
		  			$('#sendMoneyModal').hide();
		  		} else {
		  			alert(flag);
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

    
<div class="modal fade" id="sendMoneyModal" role="dialog" tabindex="-1" style="background: #B2A65B;">
	<div class="send-top-layer">
		<input type="hidden" id="bonderNo" value="">
			<h1 id="main-logo"><span id="bonderName"></span>(<span id="bonderId"></span>)님께 돈 송금</h1>
			<form id="sendMoneyForm" method="post">
				<div title="sendComments">
					<span class="add-on"><i class="halflings-icon white user"></i>내 용</span> 
					<input name="sendComments" id="sendComments" type="text" />
				</div>
				<div class="clearfix" style="margin-top : 5px; margin-bottom :5px"></div>
				
				<div title="sendMoney">
					<span class="add-on"><i class="halflings-icon white user"></i>금 액</span> 
					<input name="sendMoney" id="sendMoney" type="number" placeholder="금액"/>
				</div>
				<div class="clearfix" style="margin-top : 5px; margin-bottom :5px"></div>
				
				<div class="clearfix" style="margin-top : 5px; margin-bottom :5px"></div>
				<input type="button" class = "btn btn-large btn-primary" id="send_submit" value="보내기">
				&nbsp;
				<input type="button"  data-dismiss="modal" class="btn btn-large btn-primary" value="취 소">
				<div class="clearfix"></div>
			</form>
	</div>
</div>

    