<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/taglib/taglib.jsp" %>

<!--컨텐츠 내용 영역 시작-->
<section>

	<div class="joinWrap">
		<h1>Log In</h1>
		<form name="login">
			<div>
				<label for="id"></label>
				<input type="text" name="id" id="id" required="" placeholder="Email" maxlength="40">
			</div>
			<div>
				<label for="pw"></label>
				<input type="password" name="pw" id="pw" required="" placeholder="Password" maxlength="25">
			</div>
			<button type="button" class="large bgblue" >Log in</button>
		</form>
		<div class="gotoRegist">
			<p>Don't have an account? <a href="/regist" target="_parent"> Register</a></p>
			<p><a href="GLOBAL-WEB--MBR-M003.html" target="_blank">Forgot password?</a></p>
		</div>
	</div>

</section>
<!--컨텐츠 내용 영역 끝-->

<!--보안인증 설정했을 경우 인증진행 팝업창-->
<div class="security-pop">
	<div class="popup-back"></div>

	<!--SMS 보안인증-->
	<div class="popup sms" style="display: none">
		<div class="alert-header">
			<p class="pop-title">SMS Authentication</p>
			<a class="top-close"><img src="/resources/img/btn-alert-close.png" alt="닫기버튼"></a>
		</div>
		<form class="alert-content">
			<input type="text" name="phone" id="phone" placeholder="010********" disabled>
			<span><button class="base bggray">Send SMS</button></span>
			<label><input type="text" placeholder="SMS Authenticaion Code"></label>
		</form>

		<div class="alert-btn">
			<button class="base bgblue">Confirm </button>
		</div>
	</div>

	<!--OPT 보안인증-->
	<div class="popup otp">
		<div class="alert-header">
			<p class="pop-title">OPT Authentication</p>
			<a class="top-close"><img src="/resources/img/btn-alert-close.png" alt="닫기버튼"></a>
		</div>
		<form class="alert-content">
			<input type="text" name="" id="" placeholder="OPT Authentication Code ">
		</form>

		<div class="alert-btn">
			<button class="base bgblue">Confirm </button>
		</div>
	</div>
</div>

<script>
$(document).ready(function() {
	$('#pw').on('keyup', function(e) {
		if(e.keyCode == 13) login();
	})
	$('.large.bgblue').on('click', login);
})

function login() {
	var p = $('form[name=login] input');

	if(p.eq(0).val() == '') {
		lrt.client('이메일을 입력하세요', '');
	} else if(p.eq(0).val() == '') {
		lrt.client('비밀번호를 입력하세요', '');
	} else {
		ajax('/site/login/login', p.serialize(), function(data) {
			if(data.rtnCd == 0) {
				doLogin();
			} else {
				lrt.client(data.rtnMsg, '');
			}
		});
	}
}

function doLogin() {
	ajax('/login/signin', $('form[name=login] input').serialize(), function(data) {
		if(!data.error) {
			location.reload();
		} else {
			lrt.client(data.message, '');
		}
	});
}
</script>