<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/taglib/taglib.jsp" %>
<script src="https://www.google.com/recaptcha/api.js" async defer></script>

<section class="main">
    <div class="joinWrap">
        <h1>로그인</h1>
        <form name="login">
            <div>
                <input type="text" name="id" id="id" required="" placeholder="이메일" maxlength="40">
            </div>
            <div>
                <input type="password" name="pw" id="pw" required="" placeholder="비밀번호" maxlength="25">
            </div>
            <div class="g-recaptcha" id="g-recaptcha" data-sitekey="6LcrnrwaAAAAAFr29QiC_-9KU0t0A42c3oeJIVY2" style="margin-left:28px;"></div>
            <button type="button" class="join">로그인</button>

        	<input type="hidden" id="response" name="response">
            <input type="hidden" id="appInfo" name="appInfo">
        	<input type="hidden" id="os" name="os">
        	<input type="hidden" id="privateIp" name="privateIp">
        </form>
        <div class="gotoRegist">
            <p><a href="/alibit/register"> 회원가입</a> | <a href="/alibit/pwSearch">비밀번호 재설정</a></p>
        </div>
    </div>
</section>

<script>
var privateIp;

$('.gnb-link li:eq(0)').addClass('on');

$(document).ready(function() {

	getUserIP(function(ip){
		privateIp = ip;
	});

	$('#appInfo').val(getClientInfo());
	$('#os').val(getOS());

	$('#pw').on('keyup', function(e) {
		if(e.keyCode == 13) login();
	})
	$('.join').on('click', login);
})

function login() {
	$('#privateIp').val(privateIp);
	$('#response').val(grecaptcha.getResponse());

	$('#id').val($.trim($('#id').val()));
	var p = $('form[name=login] input');

	if(p.eq(0).val() == '') {
		lrt.client('이메일을 입력하세요', '');
	} else if(p.eq(1).val() == '') {
		lrt.client('비밀번호를 입력하세요', '');
	} else if(p.eq(2).val() == '') {
		lrt.client('로봇이 아님을 증명하세요', '');
	} else {
		ajax('/alibit/login/login', p.serialize(), function(data) {
			if(data.rtnCd == 0) {
				ajax('/login/signin', $('form[name=login] input').serialize(), function(data) {
					if(!data.error) {
						location.reload();
					} else {
						lrt.client('로그인에 실패하였습니다', '');
					}
				});
			} else if(data.rtnCd === 1 || data.rtnCd === 2) {
				location.reload();
			} else if(data.rtnCd == -5011) {
				lrt.confirm('가입 진행중인 계정입니다<br>인증메일을 재전송 하시겠습니까?', '', function() {
					location.href='/alibit/joining';
				});
			} else if(data.rtnCd == -5095) {
				lrt.client(wordbook[data.rtnCd], '', function() {
					location.href='/alibit/pwSearch';
				});
			} else {
				lrt.client(wordbook[data.rtnCd], '');
			}
		});
	}
}
</script>