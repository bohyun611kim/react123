<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/taglib/taglib.jsp" %>

 <section>
    <!--보안설정 전체 내용 시작-->
    <article class="bgwhite">

        <h1 class="sub-title">
            <ul>
                <li>My page</li>
                <li>Security Authentication</li>
            </ul>
        </h1>
        <div class="content-item">
            <div id="tab-group" class="secure">
                <ul class="tabs-2li">
                    <li class="tabs-gray"><a href="#tab1">SMS Authentication </a></li>
                    <li class="tabs-gray"><a href="#tab2">OTP Authentication</a></li>
                </ul>
                
                <div id="tab1">
                    <h1> SMS 인증 설정</h1>
                    <ul class="disc">
                        <li>SMS 인증을 설정하시면 로그인, 출금신청시 SMS 추가인증을 통해 보안이 강화됩니다.</li>
                    </ul>
                    <table class="tblType01 mgt20">
                        <colgroup>
                            <col style="width: 200px">
                            <col>
                        </colgroup>
                        <tbody>
                            <tr>
                                <th>Phone Number</th>
                                <td>
                                    <dl id="sample" class="dropdown">
                                        <dt>
                                            <a>
                                            	<span id="tel_cd">+ 82</span>
                                            </a>
                                        </dt>
                                        <dd>
                                            <ul>
                                             <c:forEach var="countryInfo" items="${countryInfoList}">
                                             	<li>
                                                     <a>${countryInfo.eng_nm}
                                                     	<span>+${countryInfo.tel_cd}</span>
                                                        <span class="value" value="${countryInfo.tel_cd}">+ ${countryInfo.tel_cd}</span>
                                                      </a>
                                                  </li>
                                             </c:forEach>
                                            </ul>
                                        </dd>
                                        <dd><input id="mobile" onkeyup="inputOnlyNum(this)" type="text"></dd>
                                    </dl>

                                </td>
                            </tr>

                            <tr>
                                <th>SMS Authentication Code</th>
                                <td>
                                    <span class="btn-mini fl-left">
                                        <button onclick="sendSMS('0')" class="small bggray">Send SMS</button>
                                    </span>
                                    <span>
                                        <input type="text" onkeyup="inputOnlyNum(this)" id="mobile-code" name="" value="" placeholder="Enter SMS Authentication Code" class="basic">
                                    </span>
                                </td>
                            </tr>
                        </tbody>
                    </table>

                    <div class="btn-wrap">
                        <button id="sms-setup" class="big bgblue">Setup</button>
                    </div>
                </div>
                
             <div id="tab2">
                 
                	<c:if test="${securitySetStatus.otp_yn eq '0'}">
                     <h1> OTP 설정</h1>
                     <ul class="disc">
                         <li>OTP 인증을 설정하시면 로그인, 출금신청시 OTP 추가인증을 통해 보안이 강화됩니다.</li>
                     </ul>

                     <div class="mgt20">
                         <h1> 1. OTP 앱 설치</h1>
                         <ul class="mgb20">
                             <li>스마트폰의 Play스토어(안드로이드 폰) 또는 App스토어(아이폰) 에서 Google OTP 앱을 검색 후 설치 합니다.</li>
                         </ul>
                         <div>
                             <img src="/resources/img/img-google.jpg">
                             <img src="/resources/img/img-apple.jpg">
                         </div>
                     </div>

                     <div class="mgt20">
                         <h1> 2. OTP 앱 실행 및 키 값 설정</h1>
                         <ul class="mgb20">
                             <li>Google OTP 앱을 실행 하신 후 아래의 바코드를 스캔 하거나 키를 입력 하십시오.</li>
                         </ul>
                         <div class="my-key">
                             <span class="my-key-qr" id="my-key-qr">
                                 <!-- <img src="/resources/img/temp-qrimg.png" alt="qr code"> -->
                             </span>
                             <span class="my-key-txt" data-key="${newOtpKey}">
                                 <span>바코드 스캔을 할 수 없는 경우에는 아래의 키를 OTP 앱에 직접 입력 하세요<br><span>${newOtpKey}</span>
                                 </span>
                             </span>
                         </div>
                     </div>


                	
                		 <div class="mgt20">
                        <h1>3. OTP 인증 하기</h1>
                        <ul class="mgb20">
                            <li>OTP 앱 화면에 표시된 인증번호 여섯자리를 입력 하세요.</li>
                        </ul>
                        <table class="tblType01 mgt20">
                            <colgroup>
                                <col style="width: 200px">
                                <col>
                            </colgroup>
                            <tbody>
                                <tr>
                                    <th>로그인 비밀번호</th>
                                    <td>
                                        <input type="password" id="user-pwd" name="" value="" class="basic">
                                    </td>
                                </tr>

                                <tr>
                                    <th>OTP Authentication Code</th>
                                    <td>
                                        <input type="text" onkeyup="inputOnlyNum(this)" id="otp-auth-code" name="" value="" placeholder="Enter OTP Authentication Code" class="basic">
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                         <div class="btn-wrap mgb20">
                            <button id="setOTP" class="big bgblue">Setup</button>
                        </div>
                    </div>
                	
                	</c:if>
                   
                    
                    <c:if test="${securitySetStatus.otp_yn eq '1'}">
<!-- 	                        <div class="mgt20"> -->
                         <h1>OTP 인증 해제</h1>
                         <ul class="disc">
                             <li>회원님께서는 현재 보안강화를 위한 OTP인증을 사용하고 계십니다.</li>
                             <li>사용을 해제 하시더라도 언제든지 다시 사용 설정 하실 수 있습니다.</li>
                         </ul>
                         <table class="tblType01 mgt20">
                             <colgroup>
                                 <col style="width: 200px">
                                 <col>
                             </colgroup>
                             <tbody>
                                 <tr>
                                     <th>로그인 비밀번호</th>
                                     <td>
                                         <input type="password" id="user-pwd-unset" name="" value="" class="basic">
                                     </td>
                                 </tr>
                                 <tr>
                                     <th>SMS Authentication Code</th>
                                     <td>
                                        <input type="text" onkeyup="inputOnlyNum(this)" id="mobile-code-unset" name="" value="" placeholder="Enter SMS Authentication Code" class="basic">
										<span class="btn-mini fl-left">
                                        	<button onclick="sendSMS('-1')" class="small bggray">Send SMS</button>
                                    	</span>
                                     </td>
                                 </tr>
                                 <tr>
                                     <th>OTP Authentication Code</th>
                                     <td>
                                         <input type="text" onkeyup="inputOnlyNum(this)" id="otp-auth-code-unset" name="" value="" placeholder="Enter OTP Authentication Code" class="basic">

                                     </td>
                                 </tr>
                             </tbody>
                         </table>
                          <div class="btn-wrap mgb20">
                              <button id="unsetOTP" class="big bgblue">Unset</button>
                         </div>
                     </div>
                 </c:if>
                </div>
            </div>
        </div>

    </article>
    <!--보안설정 전체 내용 끝-->
</section>

<script src="/resources/js/qrcode/qrcode.min.js"></script>
    
<script>


 $(document).ready(function() {
	 
	 //jquery 탭
     $("#tab-group").tabs();

     $("#datepicker").datepicker({
         inline: true
     });

 	//드롭다운 셀렉트박스
     $(".dropdown img.flag").addClass("flagvisibility");

     $(".dropdown dt a").click(function() {
         $(".dropdown dd ul").toggle();
     });

     $(".dropdown dd ul li a").click(function() {
         var text = $(this).html();
         $(".dropdown dt a span").html(text);
         $(".dropdown dd ul").hide();
         $("#tel_cd").html("" + getSelectedValue("sample"));
     });

     function getSelectedValue(id) {
         return $("#" + id).find("dt a span.value").html();
     }

     $(document).bind('click', function(e) {
         var $clicked = $(e.target);
         if (!$clicked.parents().hasClass("dropdown"))
             $(".dropdown dd ul").hide();
     });

     $("#flagSwitcher").click(function() {
         $(".dropdown img.flag").toggleClass("flagvisibility");
     });
     

     //팝업 열고 닫기   
     function openForm() {
         document.getElementById("myForm").style.display = "block";
     }

     function closeForm() {
         document.getElementById("myForm").style.display = "none";
     }
     
     // 핸드폰 번호 앞 0 자르기
     $('#mobile').on('keyup', function() {
    	 var mobile_no = $('#mobile').val();
    	 if(mobile_no.indexOf('0') == 0)
    		 mobile_no = mobile_no.substring(1, mobile_no.length);
     })
     
     if ( '${otpYn}' == 0 ) {
         // txt -> otp
         function showQRCode(key) {
            $('#my-key-qr').empty();
            var qrcode = new QRCode(document.getElementById("my-key-qr"), {
                text: key,
                width: 100,
                height: 100,
                colorDark : "#000000",
                colorLight : "#ffffff",
                correctLevel : QRCode.CorrectLevel.H
            });
        }
         
        // Google Otp QR Code 보여주기
        showQRCode('${googleOtpKey}');
     }

 });
 
/*  $('#mobile').on('keyup', function() {
	 if ( isNaN( $(this).val() ))
		 $(this).val('');
 })
 
$('#mobile-code').on('keyup', function() {
	 if ( isNaN( $(this).val() ))
		 $(this).val('');
 }) */

 function inputOnlyNum(param) {
 	 if ( isNaN( $(param).val() ))
		 $(param).val('');
}

 // send SMS btn
function sendSMS(param) {
	 
	var p = {};
	
	// 모바일 최초 등록용 SMS 발송
	if ( param == '0') {

		var mobile_no = $('#mobile').val();

		if( mobile_no == '') {
			lrt.client('휴대폰 번호를 입력하세요.');
			return false;
		}
		 	
		if(mobile_no.indexOf('0') == 0)
			mobile_no = mobile_no.substring(1, mobile_no.length);

		p["mobile"] = $('#tel_cd').text().split('+ ')[1] + $('#mobile').val();
		
	} // OTP 해제 인증용 SMS 발송
	else if( param == '-1' )
		p["mobile"] = param;

	ajax('/coinis/sendSMS', p, function(result) {
		
		if(result.res_cd == 0 && param == '0')
			 $('#mobile').attr("readonly", true);
		 
		 lrt.client(result.res_msg);
	 })
};

// SMS setup btn
 $('#sms-setup').on('click', function() {
	 
	var mobile_no = $('#mobile').val();
	var auth_code = $('#mobile-code').val();
	
	if( mobile_no == '' ) {
		 lrt.client('휴대폰 번호를 입력하세요.');
		 return false;
	} else if ( auth_code == '') {
		 lrt.client('인증번호를 입력하세요.');
		 return false;
	}
	
	if(mobile_no.indexOf('0') == 0)
	 mobile_no = mobile_no.substring(1, mobile_no.length);
	
	var p = {};
	p["mobile"] = mobile_no;
	p["auth_code"] = auth_code;
	// p["tel_cd"] = $('#tel_cd').text().substring(1, $('#tel_cd').text().length);
	p["tel_cd"] = $('#tel_cd').text().split('+ ')[1];

	ajax('/coinis/setupSMS', p, function(result) {
		
		if( result.res_cd == 0 ) {
			lrt.client(result.res_msg, '설정완료', function() {
				location.reload();
			});
		} else 
			lrt.client(result.res_msg);
	})
 });
 
 $('#otp-auth-code').on('keyup', function() {
	 if ( isNaN( $(this).val() ))
		 $(this).val('');
 });

 $('#setOTP').on('click', function() {
	 
	var loginPW = $('#user-pwd').val();
	var otpkey = $('.my-key-txt').data('key');
	var otp_auth_code = $('#otp-auth-code').val();
	 
	if( loginPW == '' ) {
		lrt.client('Enter the login password');
		return false;
	} else if( otpkey == '') {
		lrt.client('Enter OTP Code');
		return false;
	}
	 
	var p = {};
	p["loginPW"] = loginPW;
	p["otpkey"] = otpkey;
	p["otp_auth_code"] = otp_auth_code;
	 
	console.log(p);
	 
	ajax('/coinis/setupOTP', p, function(result) {
		 
		if( result.res_cd == 0 ) {
			lrt.client(result.res_msg, '설정완료', function() {
				location.reload();
			});
		} else 
			lrt.client(result.res_msg);
	})
	 
 });
 
 $('#unsetOTP').on('click', function() {
	 
	var loginPW = $('#user-pwd-unset').val();
	var sms_auth_code = $('#mobile-code-unset').val();
	var otp_auth_code = $('#otp-auth-code-unset').val();
	 
	if( loginPW == '' ) {
		lrt.client('Enter the login password');
		return false;
	} else if( sms_auth_code == '') {
		lrt.client('Enter SMS Code');
		return false;
	} else if( otp_auth_code == '') {
		lrt.client('Enter OTP Code');
		return false;
	}
	 
	var p = {};
	p["loginPW"] = loginPW;
	p["sms_auth_code"] = sms_auth_code;
	p["otp_auth_code"] = otp_auth_code;
	 
	console.log(p);
	 
	ajax('/coinis/unSetOTP', p, function(result) {
		 
		if( result.res_cd == 0 ) {
			lrt.client(result.res_msg, '설정완료', function() {
				location.reload();
			});
		} else 
			lrt.client(result.res_msg);
	})
	 
	 
 })

</script>