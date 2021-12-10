<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/taglib/taglib.jsp" %>


<section class="main" id="content-log">
	<div class="joinWrap">
	<div class="join_form">
		<h1>OTP 인증</h1>
		<div>
			<input type="text" maxlength="6" placeholder="OTP 인증번호">
		</div>
		<button type="button" class="join">인증하기</button>
		<div class="gotoRegist" style="margin-top:20px;">
			<p><a style="cursor:pointer;" onclick="$('#otpInitInfo').show();">OTP 초기화</a></p>
		</div>
	</div>
	</div>
</section>

<div class="popup-wrap" id="otpInitInfo">
	<div class="popup otp_info"> <!--alert : 가로400 big :가로 680-->
		<!-- 팝업내용 : Start -->
		<div class="alert-header">
			<p class="alert-title">OTP 초기화 가이드</p><!-- 팝업 타이틀 -->
			<a class="top-close" onclick="$('#otpInitInfo').hide();">
				<img src="/resources/img/btn-alert-close.png" alt="닫기버튼">
			</a><!-- 팝업 닫기 버튼 -->
		</div>
		<div class="alert-content">
			<p>OTP 초기화는 본인인증을 거친 후에 처리 되며, 관리자 확인 후 처리되므로 시간이 소요될 수 있습니다.</p>
			<p>OTP 초기화를 위해 아래 본인인증 서류 접수가 필요합니다.</p>
			<p>아래 방법을 참고하시어 인증서류를 정확히 접수하시면 빠른 처리가 가능합니다.</p>
			<p>※ 접수 메일주소 : <a href="mailto:help@bita500.com" class="blue">help@bita500.com</a></p>
			<p>※ 접수 양식(아래 내용을 정확히 기재하여 첨부파일과 함께 메일 접수하시기 바랍니다.)</p>
			<ul class="decimal">
				<li>이메일 제목 : OTP 초기화 요청</li>
				<li class="mgt10">이메일 기재 내용
					<p>1) 이름 :</p>
					<p>2) 가입 이메일(로그인 ID) :</p>
					<p>3) 본인인증 한 휴대폰 번호 :</p>
				</li>
				<li class="mgt10">첨부파일
					<p>1) 요청사항 메모부착 신분증 사진 (아래 사진 참조)</p>
					<p>2) 메모부착 신분증을 들고 찍은 정면 사진 (아래 사진 참조)</p>
					<p>※ 신분증은 주민등록증, 운전면허증, 여권 3가지 유효기간내의 신분증만 해당됩니다.</p>
					<p>※ 신분증 사진 촬영시 포스트잇 등으로 개인정보를 가리고 사진촬영하세요 .<br>(아래 사진 참조, 포토샵 등 이미지 편집프로그램으로 수정금지)</p>
					<p class="mgt10">※ 메모 기입 필수 내용</p>
					<ul class="decimal" style="margin-top: 0">
						<li>Holdport OTP 초기화 요청</li>
						<li>가입 이메일(로그인 ID)</li>
						<li>접수 날짜</li>
					</ul>
				</li>
			</ul>

			<p class="bolder blue">첨부 파일 안내</p>
			<ul>
				<li>※ 아래 그림과 같이 메모와 함께 사진촬영하여 첨부파일을 첨부하세요.</li>
				<li>※ 요청 제출일 이전 촬영한 사진은 인정되지 않습니다.</li>
				<li>※ 첨부파일은 jpg/png 이미지 파일만 첨부가 가능합니다.</li>
			</ul>
			<p class="bold mgt10">1. 요청사항 메모 부착한 신분증 사진</p>
			<p><img src="/resources/img/yaho/img_otp_info1.jpg" alt="img"></p>
		  	<p>※ 개인정보 보호를 위해 주민등록번호 뒷 7자리(①), 주소(②)는 반드시 가려서 사진 촬영하세요.(포토샵 수정된 파일은 접수 불가)</p>
			<p class="bold mgt10">2. 메모 부착한 신분증을 들고 촬영한 본인 사진</p>
			<p><img src="/resources/img/yaho/img_otp_info2.jpg" alt="img"></p>
			<p>※ 1번에서 준비한 메모붙인 신분증 내용과 본인 얼굴이 모두 선명하게 나와야 합니다.</p>
			<p class="mgt20">
					자동차운전면허증 ①-면허번호, ②-주민등록번호 뒷 7자리, ③ - 주소 가려주세요<br>
				<img src="/resources/img/yaho/img_otp_info3.jpg" alt="img">
				<img src="/resources/img/yaho/img_otp_info33.jpg" alt="img">
			</p>
			<p class="mgt20">
					여권 ①-여권번호, ②-기계 판독 영역 가려주세요<br>
				<img src="/resources/img/yaho/img_otp_info4.jpg" alt="img">
				<img src="/resources/img/yaho/img_otp_info44.jpg" alt="img">
			</p>
		</div>
		<!-- //팝업내용 : End -->
		<div class="btn-wrap mgt20">
			<button class="blue big" onclick="$('#otpInitInfo').hide();">확인 </button>
		</div>
	</div>
</div>

<script>
$('input').on('keyup', function(e) {
	if(e.keyCode == 13) {
		$('.join').trigger('click');
	}
})

$('.join').on('click', function() {
	ajaxOption({
		url:'/site/login/confirmOtpAuthCode',
		data:{code:$('input').val()},
		success:function(data) {
			if(data.rtnCd == 0) {
				ajax('/login/signin', $('form[name=login] input').serialize(), function(data) {
					if(!data.error) {
						location.reload();
					} else {
						lrt.client(data.message, '');
					}
				});
			} else {
				lrt.client(wordbook[data.rtnCd], '알림');
			}
		}
	});
});
</script>