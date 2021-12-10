<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/taglib/taglib.jsp" %>

<main id="content" role="main">
<section class="reset_pass">

    <div class="joinWrap form_reset">
        <h1>비밀번호 재설정</h1>

        <p class="txt-gray mgt20">보안 목적을 위해 보안 방법 수정 후 24 시간 동안 인출을 허용하지 않습니다.</p>

        <form name="login">
            <div>
                <input type="text" name="id" id="id" required="" placeholder="Email">
            </div>
            <button type="button" class="join">인증메일 발송하기</button>
        </form>
	</div>
</section>
</main>

<script>
$(document).ready(function() {
	$('.join').on('click', function() {
		var email = $('input').val();

		if(email.length == 0) {
			lrt.client('이메일 주소를 입력하세요');
		}

		if(mailCheck(email)) {
			ajaxOption({
				url:'/site/pwSearch/sendMail',
				data:{address:email},
				success:function(data) {
					if(data.rtnCd == 0) {
						lrt.client('입력하신 주소로 임시 비밀번호가 전송되었습니다', '', function() {
							location.href='/site/login';
						});
					} else {
						lrt.client(wordbook[data.rtnCd]);
					}
				}
			})
		} else {
			lrt.client(wordbook[-5003]);
		}
	})
})

function mailCheck(v) {
	var regEmail = /[\w\-]{2,}[@][\w\-]{2,}([.]([\w\-]{2,})){1,3}$/;
	if(!regEmail.test(v)) {return false;}
	return true;
}
</script>