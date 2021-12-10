<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/taglib/taglib.jsp" %>

<!--컨텐츠 내용 영역 시작-->
<section>
	<div class="joinWrap">
		<h1>Register</h1>
		<form name="Register">
			<div>						
				<div class="select-box">
					<label for="type">type</label>
					<select id="type">
						<option></option>
						<option value="1">개인</option>
						<option value="2">법인</option>
					</select>
				</div>
			</div>
			<div>
				<label for="email"></label>
				<input type="email" name="email" id="email" required placeholder="Email" maxlength="40">
			</div>
			<div>
				<label for="pw"></label>
				<input type="password" name="pw" id="pw" placeholder="Password" maxlength="25">
			</div>
			<div>
				<label for="pw"></label>
				<input type="password" name="rePw" id="rePw" placeholder="Confirm Password" maxlength="25">
			</div>
			<div>
				<label for="name"></label>
				<input type="text" name="name" id="name" placeholder="Name" maxlength="40">
			</div>
			<div>
				<label for="Date"></label>
				<input type="text" name="calendar" id="birthday" placeholder="Date of Birth" readonly="readonly">
			</div>
			<div>						
				<div class="select-box">
					<label for="country">Country</label>
					<select id="country">
						<option></option>
						<c:forEach var="data" items="${code}">
						<option value="${data.getCountryCd()}">${data.getEngNm()}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div>
				<label for="address"></label>
				<input type="text" name="address" id="address" placeholder="Address" maxlength="150">
			</div>

			<div class="agreeUse">
				<label><input type="checkbox" id="agree"><em></em></label>
				I Agree to
				<a onclick="$('#termsConditions').show();">Terms of use</a>
				<a onclick="$('#privacyStatements').show();">Privacy Policy Consent</a>
			</div>
			<div class="agreeUse">
				<label>
					<input type="checkbox" id="mktAgree"><em></em>
				</label>
				I Agree to Receive Marketing
			</div>
			<button type="button" class="large bgblue" id="btnRegist">Register</button>

		</form>
		<div class="gotoRegist">
			<p> Already Registered? <a href="/login" target="_parent"> Log in</a></p>
		</div>
	</div>

</section>
<!--컨텐츠 내용 영역 끝-->

<!--이용약관 팝업 시작-->
<div class="popup use" id="termsConditions">
	<form action="">
		<div class="pop-title"><span> Terms of Use </span>
			<button type="button" class="cancel" onclick="$('#termsConditions').hide();"><img src="/resources/img/btn-alert-close.png" alt="닫기버튼"></button>
		</div>
	</form>

	<div class="popup-cont">
		<h3>Terms of Use</h3>
		<div class="TermsBox">
			<div>
				<h3>DEFINITIONS</h3>
				These Terms and Conditions (the “Terms”) gives explicit idea of the conditions under which Bytegarden (hereinafter referred to as, “Bytegarden”) offer you services of the Bytegarden Website at https://Bytegarden.io (the “Site” or “Website”) and grant you access to the Bytegarden Platform (the “Platform”). you will be deemed to agree to become bound by all the terms and conditions of this Agreement once you use and access the services. you shouldn’t be and aren’t permitted to use the Services if you do not agree to all the terms and conditions of this Agreement,
				<br>
				<br>
				“You,” “your,” and “yours”, or “client” refers to customers or users who use our website by accepting the company’s terms and conditions. “Company,” “we,” and “our” refers to Bytegarden. “Party”, “Parties”, or “Us”, refers to both the Client and ourselves, or either the Client or ourselves. All terms refer to the offer, acceptance and consideration of payment or fees necessary to undertake the services provided by Bytegarden and its’ associated Platform.<br>
				By downloading or running our mobile application, creating our wallet, or visiting our website, you are agreeing to our Terms.<br>
				Should you have any questions or queries related to this Agreement, please do not hesitate to contact us on our website at Bytegarden.io or contact us through any SNS account.


				<h3>ELIGIBILITY</h3>
				Your accepting these terms will be regarded as proof that you are at least 18 years old or above and that you have the full capacity to accept these Terms and enter into a transaction using Bytegarden Platform. <br>
				That you accept the terms also shows that:<br>
				<ol>
					<li>1. You agree to only trade with legally obtained funds that belong to you.</li>
					<li>2. You agree to take full responsibility for your use of Bytegarden Platform.</li>
					<li>3. You agree that the details provided upon registration are true and accurate.</li>
					<li>4. You agree to abide by any relevant laws in local jurisdiction, including reporting any trading profits for taxation purposes.</li>
				</ol>

				<h3>TERMS OF USAGE</h3>
				You should never and aren’t permitted to use Bytegarden Platform and Bytegarden Wallet in any illegal way against the law, including international law, province, state country of residence and any existing law that binds you and us. By accepting our services, you are deemed to agree not to serve any illegal purposes whether intentionally or not, or not to try any attempt to support any illegal activity, including but not limited to: pornographic, illegal trading, illegal gambling, money laundering, terrorism, human trafficking, etc. If you intentionally or unintentionally breach activities mentioned below, you will be held responsible to the law and Bytegarden is not liable for your action, or any result that comes from your actions
				<br>
				<br>
				Distributing malicious code used to sabotage the Bytegarden Platform as well as Bytegarden system is not allowed in any way. Any action that is detected ensure restriction to your access to Bytegarden system and recorded evidence of such actions will be presented to the local law enforcement.

				<h3>LIMITATION OF LIABILITY & DISCLAIMER OF WARRANTIES</h3>
				Once you use any service of Bytegarden Platform, you understand and agree that we have no obligation to take any action regarding: <br>
				Errors, interruptions, errors or delays in transaction processing due to hardware, software, or Internet connectivity, or risk that third parties may have due to unauthorized access to information stored in your Bytegarden Wallet, including but not limited to Bytegarden Wallet, personal keys and memo (backup).
				<br>
				<br>

				We are not responsible for any losses or damage related to:
				<br>
				<br>
				Liabilities from users such as forgotten passwords, incorrectly built transactions, or incorrectly entered wallet addresses, server error or data loss, or unauthorized access to the application.; or<br>
				Any unauthorized activity by third party, including, but not limited to, the use of viruses, scams, coercion or means of attack against Bytegarden Platform. Any Third Party Content contained in or accessed through our Services are not represented by us, therefore not giving any liability to us. Any terms, conditions, warranties or other representations in connection with such content, are the sole and exclusive relationship between you and such organizations and/or individuals.
				<br><br>
				With your understanding, be reminded that we cannot and do not guarantee or warrant that files as available for downloading from the internet or Bytegarden Platforms will be free of viruses or other malicious code. It’s your responsibility to implement appropriate and sufficient procedures or means, and checkpoints to meet your particular requirements for anti-virus protection and correctness, or accuracy of data input and output, and for maintaining means outside of our site for any reconstruction of any lost data.
				<br><br>
				Bytegarden won’t be held liable for any destruction, loss or damage resulted and caused by viruses, attacks, or other technologically harmful material that may corrupt your computer equipment, computer programs, data or other proprietary material due to your use of Bytegarden Platforms or any services or items obtained through Bytegarden Platforms or to downloading of any material posted on it, or on any websites linked to it.
				<br><br>
				Using Bytegarden Platform, its content and any services or items obtained through Bytegarden Plaform is at client’s own risk. Bytegarden Platform, its content and any services or items obtained through Bytegarden Platforms are provided based on its availability and conditions, without any warranties of any kind, either express or implied. the foundation or any person or its alliances associated with the foundation doesn’t makes any warranty or represent anything with respect to the completeness, security, reliability, quality, accuracy or availability of Bytegarden Platforms. without limiting the foregoing, neither the foundation nor anyone associated with the foundation represents or warrants that Bytegarden Platform, its content or any services or items obtained through Bytegarden Platforms. Bytegarden Platform, its content or any services or items obtained through Bytegarden Platforms as well as the server that makes it available are accurate, reliable, error-free or uninterrupted, or free of viruses or any other harmful components.
				<br><br>
				IN NO EVENT SHALL WE, OUR PARENT, THE OFFICERS, DIRECTORS, AGENTS, JOINT VENTURERS, EMPLOYEES AND SUPPLIERS OF Bytegarde OR OUR PARENT, AFIILIATES, ALLIANCES, OUR LICENSORS, OR SERVICE PROVIDERS BE LIABLE FOR LOST PROFITS OR ANY SPECIAL, INCIDENTAL OR CONSEQUENTIAL DAMAGES ARISING OUT OF OR IN CONNECTION WITH OUR WEBSITE, Bytegarden SERVICES, OR THIS AGREEMENT (HOWEVER ARISING, INCLUDING NEGLIGENCE). Some states do not allow the exclusion or limitation of incidental or consequential damages so the above limitation or exclusion may not apply to you. so the above disclaimer may not apply to you in their entireties, but will apply to the maximum extent permitted by applicable law.
				<br><br>
				In no event shall we, our parent, the officers, directors, agents, joint venture, employees and suppliers of Bytegarden or our parent, affiliates, alliances, our licensors, or service provider be liable for damages of any kind, under any legal theory, arising out of or in connection with your use, or inability to use, Bytegarden Platform, any websites linked to it, any content on Bytegarden Platforms or such other websites or any services or items obtained through Bytegarden Platforms or such other websites, including any direct, indirect, special, incidental, consequential or punitive damages, including but not limited to, personal injury, pain and suffering, emotional distress, loss of revenue, loss of profits, loss of business or anticipated savings, loss of use, loss of goodwill, loss of data, and whether caused by tort (including negligence), breach of contract or otherwise, even if foreseeable. The foregoing does not affect any liability which cannot be excluded or limited under applicable law which may include fraud.

				<h3>CHANGES TO THE TERMS OF USE</h3>
				Bytegarden may make some improvement or any changes to these Terms of use from time to time in our sole discretion by revising or securing the content. All changes or revise are effective immediately upon being written here.<br>
				Please, always check the terms of use here because your continued use of Bytegarden Platform following the updating of revised Terms of Use means that you accept and agree to the changes. It is your responsibility to review the amended Terms. Your continued use of Bytegarden Platform and Platform following the updating of changes will mean that you accept and agree to the changes and you agree that all subsequent transactions by you will be subject to these Terms.

				<h3>PRIVACY STATEMENT</h3>
				We do our best to protect your privacy. Officers in charge of privacy matters are authorized only within company to use any information collected from clients. We will not sell, share, or rent your personal information to any third party or use your e-mail address for unsolicited mail. We constantly review our systems and data to ensure the best possible service to our clients.
			</div>
		</div>
		<button type="button" class="base bggray" onclick="$('#termsConditions').hide();">Confirm </button>
	</div>
</div>
<!--이용약관 팝업 끝-->

<!--개인정보취금방침 팝업 시작-->
<div class="popup use" id="privacyStatements">
	<form action="">
		<div class="pop-title"><span> Privacy Policy Consent </span>
			<button type="button" class="cancel" onclick="$('#privacyStatements').hide();"><img src="/resources/img/btn-alert-close.png" alt="닫기버튼"></button>
		</div>
	</form>

	<div class="popup-cont">
		<h3>Terms of Use</h3>
		<div class="TermsBox">
			<div style="height: auto;">
				<h3>DEFINITIONS</h3>
				These Terms and Conditions (the “Terms”) gives explicit idea of the conditions under which Bytegarden (hereinafter referred to as, “Bytegarden”) offer you services of the Bytegarden Website at https://Bytegarden.io (the “Site” or “Website”) and grant you access to the Bytegarden Platform (the “Platform”). you will be deemed to agree to become bound by all the terms and conditions of this Agreement once you use and access the services. you shouldn’t be and aren’t permitted to use the Services if you do not agree to all the terms and conditions of this Agreement,
				<br>
				<br>
				“You,” “your,” and “yours”, or “client” refers to customers or users who use our website by accepting the company’s terms and conditions. “Company,” “we,” and “our” refers to Bytegarden. “Party”, “Parties”, or “Us”, refers to both the Client and ourselves, or either the Client or ourselves. All terms refer to the offer, acceptance and consideration of payment or fees necessary to undertake the services provided by Bytegarden and its’ associated Platform.<br>
				By downloading or running our mobile application, creating our wallet, or visiting our website, you are agreeing to our Terms.<br>
				Should you have any questions or queries related to this Agreement, please do not hesitate to contact us on our website at Bytegarden.io or contact us through any SNS account.


				<h3>ELIGIBILITY</h3>
				Your accepting these terms will be regarded as proof that you are at least 18 years old or above and that you have the full capacity to accept these Terms and enter into a transaction using Bytegarden Platform. <br>
				That you accept the terms also shows that:<br>
				<ol>
					<li>1. You agree to only trade with legally obtained funds that belong to you.</li>
					<li>2. You agree to take full responsibility for your use of Bytegarden Platform.</li>
					<li>3. You agree that the details provided upon registration are true and accurate.</li>
					<li>4. You agree to abide by any relevant laws in local jurisdiction, including reporting any trading profits for taxation purposes.</li>
				</ol>

				<h3>TERMS OF USAGE</h3>
				You should never and aren’t permitted to use Bytegarden Platform and Bytegarden Wallet in any illegal way against the law, including international law, province, state country of residence and any existing law that binds you and us. By accepting our services, you are deemed to agree not to serve any illegal purposes whether intentionally or not, or not to try any attempt to support any illegal activity, including but not limited to: pornographic, illegal trading, illegal gambling, money laundering, terrorism, human trafficking, etc. If you intentionally or unintentionally breach activities mentioned below, you will be held responsible to the law and Bytegarden is not liable for your action, or any result that comes from your actions
				<br>
				<br>
				Distributing malicious code used to sabotage the Bytegarden Platform as well as Bytegarden system is not allowed in any way. Any action that is detected ensure restriction to your access to Bytegarden system and recorded evidence of such actions will be presented to the local law enforcement.

				<h3>LIMITATION OF LIABILITY & DISCLAIMER OF WARRANTIES</h3>
				Once you use any service of Bytegarden Platform, you understand and agree that we have no obligation to take any action regarding: <br>
				Errors, interruptions, errors or delays in transaction processing due to hardware, software, or Internet connectivity, or risk that third parties may have due to unauthorized access to information stored in your Bytegarden Wallet, including but not limited to Bytegarden Wallet, personal keys and memo (backup).
				<br>
				<br>

				We are not responsible for any losses or damage related to:
				<br>
				<br>
				Liabilities from users such as forgotten passwords, incorrectly built transactions, or incorrectly entered wallet addresses, server error or data loss, or unauthorized access to the application.; or<br>
				Any unauthorized activity by third party, including, but not limited to, the use of viruses, scams, coercion or means of attack against Bytegarden Platform. Any Third Party Content contained in or accessed through our Services are not represented by us, therefore not giving any liability to us. Any terms, conditions, warranties or other representations in connection with such content, are the sole and exclusive relationship between you and such organizations and/or individuals.
				<br><br>
				With your understanding, be reminded that we cannot and do not guarantee or warrant that files as available for downloading from the internet or Bytegarden Platforms will be free of viruses or other malicious code. It’s your responsibility to implement appropriate and sufficient procedures or means, and checkpoints to meet your particular requirements for anti-virus protection and correctness, or accuracy of data input and output, and for maintaining means outside of our site for any reconstruction of any lost data.
				<br><br>
				Bytegarden won’t be held liable for any destruction, loss or damage resulted and caused by viruses, attacks, or other technologically harmful material that may corrupt your computer equipment, computer programs, data or other proprietary material due to your use of Bytegarden Platforms or any services or items obtained through Bytegarden Platforms or to downloading of any material posted on it, or on any websites linked to it.
				<br><br>
				Using Bytegarden Platform, its content and any services or items obtained through Bytegarden Plaform is at client’s own risk. Bytegarden Platform, its content and any services or items obtained through Bytegarden Platforms are provided based on its availability and conditions, without any warranties of any kind, either express or implied. the foundation or any person or its alliances associated with the foundation doesn’t makes any warranty or represent anything with respect to the completeness, security, reliability, quality, accuracy or availability of Bytegarden Platforms. without limiting the foregoing, neither the foundation nor anyone associated with the foundation represents or warrants that Bytegarden Platform, its content or any services or items obtained through Bytegarden Platforms. Bytegarden Platform, its content or any services or items obtained through Bytegarden Platforms as well as the server that makes it available are accurate, reliable, error-free or uninterrupted, or free of viruses or any other harmful components.
				<br><br>
				IN NO EVENT SHALL WE, OUR PARENT, THE OFFICERS, DIRECTORS, AGENTS, JOINT VENTURERS, EMPLOYEES AND SUPPLIERS OF Bytegarde OR OUR PARENT, AFIILIATES, ALLIANCES, OUR LICENSORS, OR SERVICE PROVIDERS BE LIABLE FOR LOST PROFITS OR ANY SPECIAL, INCIDENTAL OR CONSEQUENTIAL DAMAGES ARISING OUT OF OR IN CONNECTION WITH OUR WEBSITE, Bytegarden SERVICES, OR THIS AGREEMENT (HOWEVER ARISING, INCLUDING NEGLIGENCE). Some states do not allow the exclusion or limitation of incidental or consequential damages so the above limitation or exclusion may not apply to you. so the above disclaimer may not apply to you in their entireties, but will apply to the maximum extent permitted by applicable law.
				<br><br>
				In no event shall we, our parent, the officers, directors, agents, joint venture, employees and suppliers of Bytegarden or our parent, affiliates, alliances, our licensors, or service provider be liable for damages of any kind, under any legal theory, arising out of or in connection with your use, or inability to use, Bytegarden Platform, any websites linked to it, any content on Bytegarden Platforms or such other websites or any services or items obtained through Bytegarden Platforms or such other websites, including any direct, indirect, special, incidental, consequential or punitive damages, including but not limited to, personal injury, pain and suffering, emotional distress, loss of revenue, loss of profits, loss of business or anticipated savings, loss of use, loss of goodwill, loss of data, and whether caused by tort (including negligence), breach of contract or otherwise, even if foreseeable. The foregoing does not affect any liability which cannot be excluded or limited under applicable law which may include fraud.

				<h3>CHANGES TO THE TERMS OF USE</h3>
				Bytegarden may make some improvement or any changes to these Terms of use from time to time in our sole discretion by revising or securing the content. All changes or revise are effective immediately upon being written here.<br>
				Please, always check the terms of use here because your continued use of Bytegarden Platform following the updating of revised Terms of Use means that you accept and agree to the changes. It is your responsibility to review the amended Terms. Your continued use of Bytegarden Platform and Platform following the updating of changes will mean that you accept and agree to the changes and you agree that all subsequent transactions by you will be subject to these Terms.

				<h3>PRIVACY STATEMENT</h3>
				We do our best to protect your privacy. Officers in charge of privacy matters are authorized only within company to use any information collected from clients. We will not sell, share, or rent your personal information to any third party or use your e-mail address for unsolicited mail. We constantly review our systems and data to ensure the best possible service to our clients.
			</div>
		</div>
		<button type="submit" class="base bggray" onclick="$('#privacyStatements').hide();">Confirm </button>
	</div>
</div>
<!--개인정보취급방침 팝업 끝-->

<!--마케팅이용 팝업 시작-->
<div class="popup use" id="myForm3">
	<form action="">
		<div class="pop-title"><span> Marketing </span>
			<button type="button" class="cancel" onclick="closeForm3()"><img src="/resources/img/btn-alert-close.png" alt="닫기버튼"></button>
		</div>
	</form>

	<div class="popup-cont">
		<h3>Marketing</h3>
		<div class="TermsBox">
			<div style="height: auto;">
				<h3>DEFINITIONS</h3>
			</div>
		</div>
		<button type="submit" class="base bggray">Confirm </button>
	</div>
</div>
<!--마케팅이용 팝업 끝-->

<script>
// page 처음 로드 됫을 때 바로 실행
$(document).ready(function() {
	$("#birthday").datepicker();
	
	$('.select-box select').on('change', function(e) {
		var $this = $(this);
        var select_name = $this.children('option:selected').text();
        $this.siblings('label').text(select_name).addClass('on');
    });
	
	// register button
	$('#btnRegist').on('click', function() {
		var p = {
				userTypeCd:$('#type').val(),
				userId:$('#email').val(),
				code:$('#code').val(),
				password:$('#pw').val(),
				rePw:$('#rePw').val(),
				fullName:$('#name').val(),
				birthday:$('#birthday').val(),
				contryCd:$('#country').val(),
				address:$('#address').val(),
				mktAgree:$('#mktAgree').is(':checked') ? 1:0,
		}
		
		if(!mailCheck(p.userId)) {
			lrt.client('잘못된 이메일 주소입니다.');
			return false;
		} else if(p.password != p.rePw) {
			lrt.client('비밀번호가 일치하지 않습니다');
			return false;
		} else if(p.fullName == '') {
			lrt.client('이름을 입력하세요');
			return false;
		} else if(p.date == '') {
			lrt.client('생년월일을 입력하세요');
			return false;
		} else if(p.contryCd == '') {
			lrt.client('국가를 선택하세요');
			return false;
		} else if(p.address == '') {
			lrt.client('주소를 입력하세요');
			return false;
		} else if(!$('#agree').is(':checked')) {
			lrt.client('이용약관과 개인정보취급방침에 동의하여야 합니다');
			return false;
		} else {
			ajax('/coinis/register/doRegist', p, function(data) {
				if(data.rtnCd == 0) {
					lrt.client('회원가입이 완료되었습니다<br>로그인 페이지로 이동합니다', '', function() {
						location.href = '/login';
					});
				} else {
					lrt.client(data.rtnMsg, '');
				}
			});
		}
	});
	
})

function mailCheck(v) {
	var regEmail = /[\w\-]{2,}[@][\w\-]{2,}([.]([\w\-]{2,})){1,3}$/;
	if(!regEmail.test(v)) {
		return false;
	}

	return true;
}
</script>