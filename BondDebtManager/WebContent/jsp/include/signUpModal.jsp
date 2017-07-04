<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}" />
<link href="${context}/css/signUpModal.css" rel="stylesheet">
<script src="${context}/js/signUp.js"></script>
<div class="modal fade" role="dialog" id="signUpModal" tabindex="-1">
	<div id="signUp-top-layer">
		<h1 id="signUplogo"> 회원 가입</h1>
		<!-- 본문 들어가는 부분 -->
		<form class="form-horizontal" name="signUpForm" role="form" method="post" onsubmit="return checkSignUpForm()" action="${context}/signUp.do">
			<div class="form-group" id="divId">
				<label for="inputId" class="col-lg-2 control-label">  </label>
				<div class="col-lg-10">
					<input type="text" class="form-control onlyAlphabetAndNumber"
						id="id" name="id" data-rule-required="true"
						placeholder="아이디(영문 및 숫자 혼합)" maxlength="30">
				</div>
			</div>
			
			<div class="form-group" id="divPassword">
				<label for="inputPassword" class="col-lg-2 control-label">  </label>
				<div class="col-lg-10">
					<input type="password" class="form-control" id="pwd" name="password" data-rule-required="true" placeholder="패스워드" maxlength="20">
				</div>
			</div>
			<div class="form-group" id="divPasswordCheck">
				<label for="inputPasswordCheck" class="col-lg-2 control-label">  </label>
				<div class="col-lg-10">
					<input type="password" class="form-control" id="pwdCheck" data-rule-required="true" placeholder="패스워드 확인"
						maxlength="20">
				</div>
			</div>
			<div class="form-group" id="divName">
				<label for="inputName" class="col-lg-2 control-label">  </label>
				<div class="col-lg-10">
					<input type="text" class="form-control onlyHangul" id="name"
						name="name" data-rule-required="true" placeholder="이름" maxlength="15">
				</div>
			</div>
	
			<div class="form-group" id="divEmail">
				<label for="inputEmail" class="col-lg-2 control-label">  </label>
				<div class="col-lg-10">
					<input type="email" class="form-control" id="email" name="email"
						data-rule-required="true" placeholder="이메일" maxlength="30">
				</div>
			</div>
			<div class="form-group" id="divPhoneNumber">
				<label for="inputPhoneNumber" class="col-lg-2 control-label">  </label>
				<div class="col-lg-10">
					<input type="text" class="form-control" id="tel"
						name="tel" placeholder="-를 포함하여 입력하세요." maxlength="13">
				</div>
			</div>
			<br>
			<div class="form-group">
				<div class="col-lg-offset-2 col-lg-10" style="text-align: center">
					<button type="submit" class="btn btn-info">가입하기</button>
					<button type="button" data-dismiss="modal" class="btn btn-danger">취소하기</button>
				</div>
			</div>
		</form>
	</div>
</div>
