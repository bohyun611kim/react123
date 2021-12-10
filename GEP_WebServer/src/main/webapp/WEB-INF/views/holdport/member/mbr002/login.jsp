<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/taglib/taglib.jsp" %>

<%!
	boolean localeMapValid = false;
	HashMap<String, String> localeMap = null;
%>

<%
	try {

		String locale = (String) request.getAttribute("locale");
		localeMap = new HashMap<String,String>();

		if(locale.equalsIgnoreCase("ko")) {

			localeMap.put("_L_0001","로그인");
            localeMap.put("_L_0002","이메일");
            localeMap.put("_L_0003","비밀번호");
            localeMap.put("_L_0004","회원가입");
            localeMap.put("_L_0005","비밀번호 재설정");
			localeMap.put("_L_0006","로그인 하시는 사이트의 주소가 아래와 동일한지 확인하세요");

			localeMapValid = true;

		} else if(locale.equalsIgnoreCase("en")) {

            localeMap.put("_L_0001","Login");
            localeMap.put("_L_0002","Email");
            localeMap.put("_L_0003","Password");
            localeMap.put("_L_0004","Sign up");
            localeMap.put("_L_0005","Reset password");
			localeMap.put("_L_0006","Check that the address of the site you are logging in to is the same as below.");

			localeMapValid = true;
		} else{

        }
	} catch(Exception e) {

	}
%>

<%!
	public String getLocaleString(String key)
	{
		if(localeMapValid == false || localeMap == null) {
			return key;
		} else {
			String value = localeMap.get(key);
			if(value != null) {
				return value;
			} else {
				return key;
			}
		}
	}
%>
<script src="https://www.google.com/recaptcha/api.js" async defer></script>

<main id="content-log" role="main" class="main">
    <div class="joinWrap">
    <div class="login_form">
		<img src="../../../../../resources/img/newbita/logo_footer.png" alt="Logo" width="100px" height="auto">
        <h1><%=getLocaleString("_L_0001")%></h1>
		<h3><%=getLocaleString("_L_0006")%></h3>
		<div class="alCenter">
			<a href="https://bita500.com/" class="bita_link">https://bita500.com</a>
		</div>
		<form name="login">
            <div>
                <input type="text" name="id" id="id" required="" placeholder="<%=getLocaleString("_L_0002")%>" maxlength="40">
            </div>
            <div>
                <input type="password" name="pw" id="pw" required="" placeholder="<%=getLocaleString("_L_0003")%>" maxlength="25">
            </div>
            <div class="g-recaptcha" id="g-recaptcha" data-sitekey="6LcrnrwaAAAAAFr29QiC_-9KU0t0A42c3oeJIVY2" style="display:flex;justify-content:center;margin:-10px 0 15px"></div>
            <br>
            <button type="button" class="join"><%=getLocaleString("_L_0001")%></button>

        	<input type="hidden" id="response" name="response">
            <input type="hidden" id="appInfo" name="appInfo">
        	<input type="hidden" id="os" name="os">
        	<input type="hidden" id="privateIp" name="privateIp">
        </form>
        <div class="gotoRegist">
            <p><a href="/site/register"> <%=getLocaleString("_L_0004")%></a> | <a href="/site/pwSearch"><%=getLocaleString("_L_0005")%></a></p>
        </div>
		<div class="gotoOtp" onclick="$('#otpInitInfo').show();">OTP초기화 안내(OTP 재등록이 필요한경우)</div>
        </div>
    </div>
</main>
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
						<li>BITA500 OTP 초기화 요청</li>
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
			<p><img src="/resources/img/site/img_otp_info1.png" alt="img"></p>
		  	<p>※ 개인정보 보호를 위해 주민등록번호 뒷 7자리(①), 주소(②)는 반드시 가려서 사진 촬영하세요.(포토샵 수정된 파일은 접수 불가)</p>
			<p class="bold mgt10">2. 메모 부착한 신분증을 들고 촬영한 본인 사진</p>
			<p><img src="/resources/img/site/img_otp_info2.png" alt="img"></p>
			<p>※ 1번에서 준비한 메모붙인 신분증 내용과 본인 얼굴이 모두 선명하게 나와야 합니다.</p>
			<p class="mgt20">
					자동차운전면허증 ①-면허번호, ②-주민등록번호 뒷 7자리, ③ - 주소 가려주세요<br>
				<img src="/resources/img/site/img_otp_info3.png" alt="img">
				<img src="/resources/img/site/img_otp_info33.png" alt="img">
			</p>
			<p class="mgt20">
					여권 ①-여권번호, ②-기계 판독 영역 가려주세요<br>
				<img src="/resources/img/site/img_otp_info4.png" alt="img">
				<img src="/resources/img/site/img_otp_info44.png" alt="img">
			</p>
		</div>
		<!-- //팝업내용 : End -->
		<div class="btn-wrap mgt20">
			<button class="blue big" onclick="$('#otpInitInfo').hide();">확인 </button>
		</div>
	</div>
</div>
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
	}
	/*
	else if(p.eq(2).val() == '') {
		lrt.client('로봇이 아님을 증명하세요', '');
	}
	*/

	else {
		ajax('/site/login/login', p.serialize(), function(data) {
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
					location.href='/site/joining?email='+p.eq(0).val();
				});
			} else if(data.rtnCd == -5095) {
				lrt.client(wordbook[data.rtnCd], '', function() {
					location.href='/site/pwSearch';
				});
			} else {
				lrt.client(wordbook[data.rtnCd], '');
			}
		});
	}
}
</script>