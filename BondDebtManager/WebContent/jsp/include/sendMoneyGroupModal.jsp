<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<link href="${context}/css/sendMoneyGroupModal.css" rel="stylesheet">
    
<div class="modal fade" id="sendMoneyGroupModal" role="dialog" tabindex="-1" style="background: #B2A65B;">
	<div class="send-top-layer">
			<h1 id="main-logo">그룹에게 돈 송금</h1>
			<form id="sendMoneyGroupForm" method="post">
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
				<input type="button" class = "btn btn-large btn-primary" id="send_group_submit" value="보내기">
				&nbsp;
				<input type="button"  data-dismiss="modal" class="btn btn-large btn-primary" value="취 소">
				<div class="clearfix"></div>
			</form>
	</div>
</div>
