<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/taglib/taglib.jsp" %>

<section class="main">

    <div class="joinWrap">
        <h1>인증 메일 재발송</h1>

        <p class="txt-gray mgt20">가입 시 입력하신 이메일로 가입 인증메일을 재발송 합니다</p>

        <form name="login">
        	<div>
                <input type="text" name="id" id="id" required="" placeholder="이메일">
            </div>
            <button type="button" class="join">인증메일 발송하기</button>
        </form>
	</div>
</section>

<script>
$(document).ready(function() {
	$('.join').on('click', function() {
		var email = $('input').val();
		
		if(email.length == 0) {
			lrt.client('이메일 주소를 입력하세요');
		}
		
		if(mailCheck(email)) {
			ajaxOption({
				url:'/alibit/joining/reSendMail',
				data:{address:email},
				success:function(data) {
					if(data.rtnCd == 0) {
						lrt.client('회원가입 인증메일이 발송되었습니다', '', function() {
							location.href='/alibit/login';
						});
					}
					else lrt.client(wordbook[data.rtnCd], '', function() {location.href='/alibit/login';});
				}
			})
		} else {
			lrt.client(wordbook[-5006], '');
		}
	})
})

function mailCheck(v) {
	var regEmail = /[\w\-]{2,}[@][\w\-]{2,}([.]([\w\-]{2,})){1,3}$/;
	if(!regEmail.test(v)) {return false;}
	return true;
}
</script>