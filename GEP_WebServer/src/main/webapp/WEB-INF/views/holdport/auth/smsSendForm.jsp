<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/taglib/taglib.jsp" %>

<main id="content-log" role="main" class="main">
<fieldset style="width:300">
    <div class="joinWrap">
    <div class="login_form">
        <h1>Mobile 본인인증</h1>
        <form name="auth" >
            <div>
                <input type="text" name="phoneNo" id="phoneNo" required="" placeholder="전화번호" maxlength="40">
                <button type="button" id="smsbutton" class="join">인증번호받기</button>
            </div>
            <div>
                <input type="text" name="authNum" id="authNum" required="" placeholder="인증번호" maxlength="40">
                <button type="button" id="authbutton" class="join">인증하기</button>
            </div>

            <input type="hidden" id="browser" name="browser">
        	<input type="hidden" id="os" name="os">
        	<input type="hidden" id="privateIp" name="privateIp">
        </form>

        </div>
    </div>
  </fieldset>
</main>
<script>


var privateIp;

$(document).ready(function() {

	getUserIP(function(ip){
		privateIp = ip;
	});

	$('#browser').val(getClientInfo());
	$('#os').val(getOS());

	$('#phoneNo').focus();

	$("#authNum").attr("disabled", true);
	 $('#authbutton').attr('disabled',true);

     $('#smsbutton').on('click', sendSms);
	$('#authbutton').on('click', requestAuth);

})

function sendSms() {

	$('#privateIp').val(privateIp);

	$('#phoneNo').val($.trim($('#phoneNo').val()));

	var p = $('form[name=auth] input');

	if(p.eq(0).val() == '') {
		lrt.client('전화번호를 입력하세요', '');
	}else {
		ajax('/site/auth/sureMobileAuthSms', p.serialize(), function(data) {
			if(data.rtnCd == 0) {
				lrt.client('인증번호를 성공적으로 발송하였습니다.', '');

				 $("#authNum").attr("disabled", false);
				 $('#authbutton').attr('disabled',false);

				 $('#authNum').focus();

			}else {
				lrt.client('메시지 발송에 실패하였습니다. 잠시후 다시 시도해주십시요'', '');
				//lrt.client(wordbook[data.rtnCd], '');
			}
		});
	}
}

function requestAuth() {

	$('#privateIp').val(privateIp);
	$('#phoneNo').val($.trim($('#phoneNo').val()));

	var p = $('form[name=auth] input');

	if(p.eq(0).val() == '') {
		lrt.client('전화번호를 입력하세요', '');
	}if(p.eq(1).val() == '') {
		lrt.client('인증번호를 입력하세요', '');
	}else {
		ajax('/site/auth/sureMobileAuthReq', p.serialize(), function(data) {
			if(data.rtnCd == 0) {
				lrt.client('성공적으로 인증되었습니다.', '');
				window.close();
				opener.parent.location.reload();
				//location.reload();
			}else {
				lrt.client(wordbook[data.rtnCd], '');
			}
		});
	}

}


</script>

