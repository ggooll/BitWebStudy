<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<style>
.request-top-layer {
	height: 400px;
	width: 500px !important;
	background-color: #B2A65B;
	text-align: center;
	margin: 0 auto;
}

#friendTableDiv {
	height: 200px;
	overflow: auto;
}

.request-top-layer * {
	font-family: "hanna";
}

#main-logo {
	font-size: 22pt;
	font-weight: 400;
	color: black;
	margin: 30px auto 40px auto;
}

input[type=number]::-webkit-inner-spin-button, input[type=number]::-webkit-outer-spin-button{
	-webkit-appearance: none;
	margin: 0;
}

.table th{
	background-color : #DDD;
	text-align: center;
}

.table td{
	background-color : white;
	text-align: center !important;
}
</style>

<script>

$(document).ready(function(){
	$('#addGroup_submit').click(function(){
		
		var groupName = $('#groupName').val();
		if(groupName == ""){
			alert('그룹 이름을 입력하세요');
			return;
		}
		
		if(!$('#memberList').checked){
			alert('선택된 그룹원이 없습니다.');
			return;
		}
		
		var formData = $("#addGroupForm").serialize();
	 	$.ajax({
		 	type : "POST",
		  	url : "${context}/insertGroup.do",
		  	data : formData,
		  	async : true,
		  	success : function(data){
		  		flag = data.trim();
		  		if(flag == '1'){
		  			alert("새 그룹을 생성했습니다.");
		  			// 모달이 닫히고 새로고침
		  			$('#addGroupModal').modal('toggle');
		  			$('#addGroupModal').hide();
		  			location.href="${context}/selectGroup.do"
		  		} else {
		  			alert('그룹을 생성하는데 실패했습니다.');
		  		}
		  	},
	  		error : function(request,status,error){
	  		    alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	  	   }
 		});
	}); 
});

</script>

<div class="modal fade" id="addGroupModal" role="dialog" tabindex="-1" style="background: #B2A65B;">
	<div class="request-top-layer">
			<h1 id="main-logo"><span id="debtorName"></span>그룹 만들기</h1>
			<form id="addGroupForm" name="addGroupForm" method="post">
				<div title="groupComents">
					<span style="font-size:20px"> 그룹 이름 </span> 
					<input name="groupName" id="groupName" type="text" />
				</div>
				<div class="clearfix" style="margin-top : 5px; margin-bottom :5px"></div>
				<div id="friendTableDiv">
					<table class="table table-bordered">
						<thead>
							<tr>
								<th>친구아이디</th>
								<th>친구이름</th>
								<th>친구 선택</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="friend" items="${friendList}">
							<tr>
								<td class="friend_id">${friend.id}
								<input type="hidden" class="friend_no" value="${friend.no}"/></td>
								<td class="friend_name">${friend.name}</td>
								<td class="center"><input type ="checkbox" id="memberList" name="memberList" value="${ friend.no }"></td>
							</tr>
								
							</c:forEach>
						</tbody>
				</table>
				</div>
			<div class="clearfix" style="margin-top : 5px; margin-bottom :5px"></div>
				<input type="button" class = "btn btn-large btn-primary" id="addGroup_submit" value="만들기">
				&nbsp;
				<input type="button"  data-dismiss="modal" class="btn btn-large btn-primary" value="취 소">
				<div class="clearfix"></div>
			</form>
	</div>
</div>