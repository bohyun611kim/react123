<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/taglib/taglib.jsp" %>


<script src="/resources/js/holdport/mypage/holdport.info.js"></script>

<main id="content" >
    <br>
	<h2 class="subtitle">휴대폰 본인인증</h2>
	<div class="main">
		<div>
			<input type="text" name="phoneNo" id="phoneNo" style="width:69%;" maxlength="20" placeholder="핸드폰번호">
			<button type="button" style="width:29%;height:37px;float:right;" id="reqAuth" onclick="requestHttpSmsAuthNumber(this);">인증번호 요청</button>
		</div>
		<br>
		<div>
			<input type="text" name="authNum" id="authNum" style="width:69%;" maxlength="6" placeholder="SMS 인증번호">
		</div>

		<button type="button" class="join" id="authConfirm" onclick="requestSmsAuthStep(this);">인증하기</button>
	</div>
</div>
</main>

<script>

var privateIp;

$('#authNum').on('keyup', function(e) {
	if(e.keyCode == 13) {
		$('#authConfirm').trigger('click');
	}
});

$(document).ready(function() {

	getUserIP(function(ip){
		privateIp = ip;
	});

	$("#authNum").attr("disabled", true);
	 $('#authConfirm').attr('disabled',true);

});

function requestHttpSmsAuthNumber(btn) {

	let $this = $(btn);

	if($('#phoneNo').val()==''){
		//lrt.client('핸드폰번호를 입력해야합니다.', '');
		alert('핸드폰번호를 입력해야합니다.');
		$('#phoneNo').focus();
		return false;
	}

	ajaxOption({
		url:'/site/auth/sureMobileAuthSms',
		data:{
			phoneNo : $('#phoneNo').val(),
			os:getOS(),
			privateIp:privateIp,
			browser:getClientInfo()
		},
		success:function(data) {

			if(data.rtnCd == 0) {
				//lrt.client('회원님의 등록하신 휴대폰으로 인증번호가 발송되었습니다.<br>3분 이내로 인증절차를 진행해 주세요.', 'SMS인증요청');
				alert('회원님의 등록하신 휴대폰으로 인증번호가 발송되었습니다. 3분 이내로 인증절차를 진행해 주세요.');

				$("#phoneNo").attr("disabled", true);
				$("#authNum").attr("disabled", false);
				 $('#authConfirm').attr('disabled',false);

				 var timeout = 180;

				 __G_SMS_Auth_Request_btn_timer = setInterval(function() {
	                 minutes = parseInt(timeout / 60 % 60, 10);
	                 seconds = parseInt(timeout % 60, 10);

	                 minutes = minutes < 10 ? "0" + minutes : minutes;
	                 seconds = seconds < 10 ? "0" + seconds : seconds;
	                 $this.text(minutes + ' : ' + seconds);

	                 if(--timeout < 0) {
	                	 timeout = 0;
	                     clearInterval(__G_SMS_Auth_Request_btn_timer);
	                     $this.text('인증번호 요청');
	                     // button 활성화시킴
	                     $this.prop("disabled", false);
	                 }
                }, 1000);
			} else {
				alert('SMS발송이 실패되었습니다. 잠시후 다시 시도해주십시요.');
			}
		}
	});
}

function requestSmsAuthStep(btn) {

	if($('#authNum').val()==''){
		//lrt.client('인증번호를 입력해야합니다.', '');
		alert('인증번호를 입력해야합니다.');
		$('#authNum').focus();
		return false;
	}

	ajaxOption({
		url:'/site/auth/sureMobileAuthReq',
		data:{
			phoneNo : $('#phoneNo').val(),
			authNum : $('#authNum').val(),
			os:getOS(),
			browser:getClientInfo(),
			privateIp:privateIp
		},
		success:function(data) {

			if(data.rtnCd == 0) {

				alert('정상적으로 인증되었습니다.');
				opener.location.href='/site/info?tab=1';
				window.close();

			} else {
				alert('본인인증에 실패되었습니다. 잠시후 다시 시도해주십시요.');
			}

		}
	});
}

</script>