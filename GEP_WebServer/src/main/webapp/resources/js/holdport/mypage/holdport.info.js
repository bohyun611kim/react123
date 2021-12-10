var privateIp;
getUserIP(function(ip){
	privateIp = ip;
});

var $infoBtn  = $('#vtab1').find('button');

$.each($infoBtn, function(i, e) {
    $(this).data('i', i);
})

$infoBtn.on('click', function() {
    let $this = $(this);
    switch($this.data('i')) {
    case 0:
        // 휴대폰 번호 변경
    	$('ul.vtab li a:eq(1)').trigger('click');
        break;
    case 1:
    	// 비밀번호 변경
        let pwForm = $('#pwChange');
        pwForm.find('input').val('');
        pwForm.show();
        break;
    case 2:
    	// 추천 코드 복사
        su.copy($this.prev().text());
        lrt.client('복사되었습니다');
        break;
    case 3:
        // 가입링크 복사
        su.copy($this.prev().text());
        lrt.client('복사되었습니다');
        break;
    default:
        break;
    }
});

$('input[name=rdo]').on('change', function(e) {
	e.preventDefault();
	let $this = $(this);

	if($this.attr('id') == 'smsAuth') {
		$('#otpAuth').prop('checked', true);

		$('#smsAuthCode').val('');
		$('#smsConfirm').val('');
		$('#smsChange').show();
	} else if($this.attr('id') == 'otpAuth') {
		$('#smsAuth').prop('checked', true);

		$('#otpAuthCode').val('');
		$('#otpChange').show();
	}
})

$('#smsAuthCode, #otpAuthCode').on('keyup', function() {
	let $this = $(this);
	$this.val( $this.val().replace(/[^0-9]/g, '') );
})

$('input[name=agreement]').on('change', function(e) {
	e.preventDefault();
	let $this = $(this);

	if($this.attr('id') == 'ex_rd3') {
		$('#ex_rd4').prop('checked', true);
	} else if($this.attr('id') == 'ex_rd4') {
		$('#ex_rd3').prop('checked', true);
	}

	ajaxOption({
		url:'/site/info/marketing',
		data:{agree:$this.val()},
		success:function(data) {
			if(data.rtnCd === 0) {
				lrt.client('변경되었습니다', '마케팅 수신동의');
				$this.prop('checked', true);
			} else {
				lrt.client(wordbook[data.rtnCd], '마케팅 수신동의');
			}
		}
	})
})

$('#monthlyTradeCnt, #monthlyTradeAmt').on('keyup', function() {
	let $this = $(this);
	$this.val(nu.cm($this.val().replace(/[^0-9]/g, '')));
});

function changeAuthToSms() {
	let authCode = $('#smsAuthCode');

	if(authCode.val().length != 6) {
		lrt.client('인증 번호 6자리를 입력하세요', '본인 인증 수단 변경');
	} else {
		ajaxOption({
			url:'/site/info/changeAuthTypeToSms',
			data:{
				authCode:authCode.val(),
				os:getOS(),
				browser:getClientInfo(),
				privateIp:privateIp
			},
			success:function(data) {
				if(data.rtnCd == 0) {
					clearInterval(__G_SMS_Auth_Request_btn_timer);
					$('#btnAuthCode').text('인증번호 요청').prop("disabled", false);
					authCode.val('');
					$('#smsAuth').prop('checked', true);
					$('#smsChange').hide();
					lrt.client('보안 인증 수단을 변경하였습니다', '알림');

					let innerHtml = '<tr><th>휴대폰 SMS인증</th><td>';
					innerHtml += '<input type="text" id="authCode" name="authCode" placeholder="SMS 인증번호를 입력하세요" maxlength="6">';
					innerHtml += '<button type="button" id="btnReqSmsCode">인증번호 요청</button>';
					innerHtml += '<p class="info-text">인증번호 요청 후, 휴대폰으로 전송된 인증번호 6자리를 입력하세요</p>';
					innerHtml += '</td></tr>';

					$('#apiAuthType').html(innerHtml);
					$('#btnReqSmsCode').on('click', function() {
						let $this = $(this);

						ajaxOption({
							url:'/site/api/reqSmsCodeByApi',
							success:function(data) {
								if(data.rtnCd == 0) {
									lrt.client('회원님의 등록하신 휴대폰으로 인증번호가 발송되었습니다.<br>3분 이내로 인증절차를 진행해 주세요.', 'SMS인증요청');

									 var timeout = 180;

									 apiAuthTimer = setInterval(function() {
						                 minutes = parseInt(timeout / 60 % 60, 10);
						                 seconds = parseInt(timeout % 60, 10);

						                 minutes = minutes < 10 ? "0" + minutes : minutes;
						                 seconds = seconds < 10 ? "0" + seconds : seconds;
						                 $this.text(minutes + ' : ' + seconds);

						                 if(--timeout < 0) {
						                	 timeout = 0;
						                     clearInterval(apiAuthTimer);
						                     $this.text('인증번호 요청');
						                     // button 활성화시킴
						                     $this.prop("disabled", false);
						                 }
					                }, 1000);
								} else {
									lrt.client(wordbook[data.rtnCd], '알림');
								}
							}
						})
					});

				} else {
					lrt.client(wordbook[data.rtnCd], '알림');
				}
			}
		});
	}
}

function requestSmsAuthNumber(btn) {
	let $this = $(btn);

	ajaxOption({
		url:'/site/info/requestSmsAuthNumber',
		data:{
			os:getOS(),
			browser:getClientInfo(),
			privateIp:privateIp
		},
		success:function(data) {
			if(data.rtnCd == 0) {
				lrt.client('회원님의 등록하신 휴대폰으로 인증번호가 발송되었습니다.<br>3분 이내로 인증절차를 진행해 주세요.', 'SMS인증요청');

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
				lrt.client(wordbook[data.rtnCd], '알림');
			}
		}
	});
}

function changeAuthToOtp() {
	let authCode = $('#otpAuthCode');

	if(authCode.val().length != 6) {
		lrt.client('인증 번호 6자리를 입력하세요', '본인 인증 수단 변경');
	} else {
		ajaxOption({
			url:'/site/info/changeAuthTypeToOtp',
			data:{
				authCode:authCode.val(),
				os:getOS(),
				browser:getClientInfo(),
				privateIp:privateIp
			},
			success:function(data) {
				if(data.rtnCd == 0) {
					authCode.val('');
					$('#otpAuth').prop('checked', true);
					$('#otpChange').hide();
					lrt.client('보안 인증 수단을 변경하였습니다', '알림');

					$('#apiAuthType').html('<tr><th>OTP</th><td><input type="text" id="authCode" name="authCode" value="" maxlength="6"></td></tr>');
				} else {
					lrt.client(wordbook[data.rtnCd], '알림');
				}
			}
		});
	}
}

function openPage(pageName, elmnt, color) {
	try {
	    var i, tabcontent, tablinks;
	    tabcontent = document.getElementsByClassName("leveinfo");
	    for (i = 0; i < tabcontent.length; i++) {
	        tabcontent[i].style.display = "none";
	    }
	    tablinks = document.getElementsByClassName("levellink");
	    for (i = 0; i < tablinks.length; i++) {
	        tablinks[i].style.backgroundColor = "";
	    }
	    document.getElementById(pageName).style.display = "block";
	} catch(err) {
	    console.log(err);
	}
}

function getRecInfo() {
	ajaxOption({
		url:'/site/info/recommend',
		success:function(data) {
			var $b = $('#recommand').find('b');

			//$b.eq(0).text(0);
			$b.eq(0).text(data.totalCnt);
			$b.eq(1).text(data.recommandCd);
			$b.eq(2).text(data.recommandLink.replace("www.holdport.com", "holdport.com"));
		}
	})
}

function getAccessLog(i, done) {
	ajaxOption({
		url:'/site/info/getAccessLog',
		data:{
			pageNum:i,
			startDate:$('#startDate').val().replace(/-/g, ''),
			endDate:$('#endDate').val().replace(/-/g, '')
		},
		success:function(data) {
			let list = data.list, length = list.length, template = $('#ACCESS_LOG').html(), innereHtml = '';

			for(let i=0; i<length; i++) {
				let log = list[i] ;
				innereHtml += template.replace(/{{dateTime}}/g, getCstmFrmt(log.dateTime, 'yyyyMMddHHmmss', 'yyyy-MM-dd HH:mm:ss'))
										.replace(/{{os}}/g, log.os)
										.replace(/{{browser}}/g, log.browser)
										.replace(/{{ipAddr}}/g, log.publicIp);
			}

			$('#accessLog').html(innereHtml);
		},
		done:done
	})
}

function updatePassword() {
	let $pop = $('#pwChange').find('input')
		, p = {
			pw:$pop.eq(0).val(),
			newPw:$pop.eq(1).val(),
			newPwCheck:$pop.eq(2).val(),
			os:getOS(),
			browser:getClientInfo(),
			privateIp:privateIp
		};

	if(p.pw === p.newPw) {
		lrt.client('기존 비밀번호와 신규 비밀번호는 달라야 합니다.', '비밀번호 변경');
		$pop.eq(1).val('');
		$pop.eq(2).val('');
		return;
	} else if(!passwordCheck(p.newPw)) {
		$pop.eq(1).val('');
		$pop.eq(2).val('');
		return;
	} else if(p.newPw !== p.newPwCheck) {
		lrt.client('신규 비밀번호와 신규 비밀번호 확인의 값이 일치하지 않습니다.', '비밀번호 변경');
		$pop.eq(2).val('');
		return;
	}

	ajaxOption({
		url:'/site/info/pwChange',
		data:p,
		success:function(data) {
			if(data.rtnCd == 0) {

				lrt.client('비밀번호가 변경되었습니다. 다시 로그인 하시기 바랍니다', '비밀번호 변경', function() {
					location.reload();
				});
			} else {
				lrt.client(wordbook[data.rtnCd]);
			}
		}
	});
}

function passwordCheck(value) {
	if(value.search(/[<\('>\)]/g) != -1) {
		lrt.client('특수문자 <, >, (, ), \' 는 비밀번호로 사용하실 수 없습니다', '회원가입');
		return false;
	} else if(value.search(/[a-z]/g) < 0 || value.search(/[A-Z]/g) < 0 || value.search(/[0-9]/g) < 0 || value.length < 8) {
		lrt.client('비밀번호는 대소문자, 숫자, 특수문자 포함 8자 이상으로 설정하세요', '비밀번호 변경');
		return false;
	} else {
		return true;
	}
}

//////////////////////////////////////////////////////////////////////////////
// 휴대폰 본인인증 or 번호 변경
$('.btnMobile').on('click', function() {
	let param = '?os=' + getOS() + '&browser=' + getClientInfo() + '&privateIp=' + privateIp;
	//window.open('/site/auth/smsAuthForm' + param, 'auth_popup','height=300,width=500');
	window.open('/site/auth/kcp' + param, 'auth_popup','height=500,width=400');
})

// 휴대폰 본인인증 or 번호 변경
//$('.btnMobile').on('click', function() {
//	let param = '?os=' + getOS() + '&browser=' + getClientInfo() + '&privateIp=' + privateIp;
//
//	window.open('/alibit/auth/kcp' + param, 'auth_popup','height=500,width=400');
//})



////////////////////////////////////////////////////////////////////////////////
// MYPAGE-AUTH-LEVEL4-OTP-01 >> VTAP2(보안인증) > 레벨4 (추가인증사용) Tab :: OTP QR Code 보여주기
// SMS 인증요청 버튼을 클릭했는지 여부 저장 전역변수
__G_Bool_SMS_Auth_Request = false;
__G_SMS_Auth_Request_btn_timer = 0;
// showQRCode('otpauth://totp/YAHOBIT(kangnaru@naver.com)?secret=LA4UCVKSIM3TSRCWLBHEKVKRJNKDSTCL');
function showQRCode(key) {
	if($('#vtab2-level4-otp-qrcode').length > 0) {
		$('#vtab2-level4-otp-qrcode').empty();
	    var qrcode = new QRCode(document.getElementById("vtab2-level4-otp-qrcode"), {
	        text: key,
	        width: 100,
	        height: 100,
	        colorDark : "#000000",
	        colorLight : "#ffffff",
	        correctLevel : QRCode.CorrectLevel.H
	    });
	}
}
///////////////////////////////////////////////
// MYPAGE-AUTH-LEVEL4-OTP-02 >> VTAP2(보안인증) > 레벨4 (추가인증사용) Tab :: SMS 인증요청 버튼 클릭이벤트 처리
$('#vtab2-level4-sms-auth-code-request-btn').click(function() {
    $.ajax({
    	url : "mypage/mpg002/requestSmsAuthNumber"
      , type : 'POST'
      , dataType : 'json'
      , error : function(data,status,error) {
          	console.log("에러 >> " + JSON.stringify(data));
            // $('#coin-tab-1-create-new-address').show();
            // $('#coin-tab-1-user-wallet-address-info').hide();
        }
      , success : function(data, textStatus) {
            if(data.rtnCd == 0) {
                lrt.client('회원님의 등록하신 휴대폰으로 인증번호가 발송되었습니다.<br>3분 이내로 인증절차를 진행해 주세요.', 'SMS인증요청', null, null);
                __G_Bool_SMS_Auth_Request = true;
                // button 비활성화시킴
                $('#vtab2-level4-sms-auth-code-request-btn').prop("disabled", true);
                var timeout = 180;
                __G_SMS_Auth_Request_btn_timer = setInterval(function() {
                    minutes = parseInt(timeout / 60 % 60, 10);
                    seconds = parseInt(timeout % 60, 10);

                    minutes = minutes < 10 ? "0" + minutes : minutes;
                    seconds = seconds < 10 ? "0" + seconds : seconds;
                    $('#vtab2-level4-sms-auth-code-request-btn').text(minutes + ' : ' + seconds);

                    if (--timeout < 0) {
                        timeout = 0;
                        clearInterval(__G_SMS_Auth_Request_btn_timer);
                        $('#vtab2-level4-sms-auth-code-request-btn').text('인증번호 요청');
                        // button 활성화시킴
                        $('#vtab2-level4-sms-auth-code-request-btn').prop("disabled", false);
                    }
                }, 1000);
            } else {
                lrt.client('' + data.rtnMsg, 'SMS인증요청 오류', function() {
                    // button 활성화시킴
                    $('#vtab2-level4-sms-auth-code-request-btn').prop("disabled", false);
                }, null);
            }
        }
    });
});
///////////////////////////////////////////////
// MYPAGE-AUTH-LEVEL4-OTP-02 >> VTAP2(보안인증) > 레벨4 (추가인증사용) Tab :: SMS 인증요청 버튼 클릭이벤트 처리
$('#vtab2-level4-auth-request-btn').click(function() {
    var loginPwd = $('#vtab2-level4-login-password').val();
    var smsAuthCd = $('#vtab2-level4-sms-auth-code').val();
    var otpAuthCd = $('#vtab2-level4-otp-auth-code').val();
    var otpAuthKey = $('#vtab2-level4-otp-auth-key').text();

    if(__G_Bool_SMS_Auth_Request == false) {
        lrt.client('SMS 인증요청이 되지않았습니다.', 'SMS 인증요청 오류', null, null);
        return false;
    }
    if(loginPwd == '') {
        lrt.client('로그인 비밀번호를 입력하세요.', '입력 오류', function() {
            $('#vtab2-level4-login-password').focus();
        }, null);
        return false;
    }
    if(smsAuthCd == '') {
        lrt.client('SMS 인증코드를 입력하세요.', '입력 오류', function() {
            $('#vtab2-level4-sms-auth-code').focus();
        }, null);
        return false;
    }
    if(otpAuthCd == '') {
        lrt.client('OTP 인증코드를 입력하세요.', '입력 오류', function() {
            $('#vtab2-level4-otp-auth-code').focus();
        }, null);
        return false;
    }

    var param = {
        loginPwd : loginPwd,
        smsAuthCd : smsAuthCd,
        otpAuthCd : otpAuthCd,
        otpAuthKey : otpAuthKey,
        os:getOS(),
		browser:getClientInfo(),
		privateIp:privateIp
    }
    $.ajax({
          url : "mypage/mpg002/requestOtpAuth"
        , data : param
        , type : 'POST'
        , dataType : 'json'
        , error : function(data,status,error) {
            console.log("에러 >> " + JSON.stringify(data));
            // $('#coin-tab-1-create-new-address').show();
            // $('#coin-tab-1-user-wallet-address-info').hide();
        }
        , success : function(data, textStatus) {
            if(data.rtnCd == 0) {
                lrt.client('성공적으로 OTP 정보가 등록되었습니다.', 'OTP Key등록', function() {
                    __G_Bool_SMS_Auth_Request = false;
                    clearInterval(__G_SMS_Auth_Request_btn_timer);
                    __G_SMS_Auth_Request_btn_timer = 0;
                    $('#vtab2-level4-sms-auth-code-request-btn').prop("disabled", true);
                    $('#vtab2-level4-sms-auth-code-request-btn').text('인증번호 요청');
                    $('#vtab2-level4-auth-request-btn').prop("disabled", true);
                    $('#vtab2-level4-login-password').val('');
                    $('#vtab2-level4-sms-auth-code').val('');
                    $('#vtab2-level4-otp-auth-code').val('');
                    $('#vtab2-level4-otp-auth-div').hide();

                    location.href='/site/info?tab=1';
                }, null);
            } else {
                lrt.client('OTP KEY 등록에 실패하였습니다.', 'OTP Key등록 오류', function() {
                    // button 활성화시킴
                    // $('#vtab2-level4-sms-auth-code-request-btn').prop("disabled", false);
                }, null);
            }
        }
    });
});

function uploadPrivateIdCard() {
	let form = $('#authSubmit')
		, file1 = form.find('#imgInput'), file2 = form.find('#imgInput1');
	let formData = new FormData(form[0]);
	formData.append('os', getOS());
	formData.append('browser', getClientInfo());
	formData.append('privateIp', privateIp);

	if(form.find('input[name=userName]').val() == '') {
		lrt.client('성명을 입력하셔야 합니다', '신분증 인증');
		return false;
	} else if(form.find('input[name=calendar]').val() == '') {
		lrt.client('생년월일 입력하셔야 합니다', '신분증 인증');
		return false;
	} /*else if(form.find('input[name=address]').val() == '') {
		lrt.client('주소를 입력하셔야 합니다', '신분증 인증');
		return false;
	} else if(form.find('input[name=postal]').val() == '') {
		lrt.client('우편번호를 입력하셔야 합니다', '신분증 인증');
		return false;
	} */else if(file1.val() == '') {
		lrt.client('첨부파일을 첨부하여야 합니다', '신분증 인증');
		return false;
	} else if(!checkExtension(file1)) {
		lrt.client('jpg, png 파일만 가능합니다', '신분증 인증');
		return false;
	} else if(!checkFileSize(file1)) {
		lrt.client('최대 파일 크기는 6MB 입니다', '신분증 인증');
		return false;
	} else if(file2.val() == '') {
		lrt.client('첨부파일을 첨부하여야 합니다', '신분증 인증');
		return false;
	} else if(!checkExtension(file2)) {
		lrt.client('jpg, png 파일만 가능합니다', '신분증 인증');
		return false;
	} else if(!checkFileSize(file2)) {
		lrt.client('최대 파일 크기는 6MB 입니다', '신분증 인증');
		return false;
	} else {
		ajaxOption({
			url:'/site/mypage/mpg002/submitIdcard',
			type: 'POST',
			data:formData,
			processData: false,
	        contentType: false,
	        loading:true,
	        success: function(data){
	        	if(data.rtnCd === 0) {
	        		lrt.client('신분증 인증 자료를 제출하였습니다.', '신분증 인증', function() {
	        			location.href='/site/info?tab=1';
	        		});
	        	} else {
	        		lrt.client(wordbook[data.rtnCd], '신분증 인증');
	        	}
	        }
		});
	}
}

function uploadCorpIdCard() {
	let form = $('#authSubmit')
		, file1 = form.find('#imgInput'), file2 = form.find('#imgInput1'), file3 = form.find('#imgInput2');
	let formData = new FormData(form[0]);
	formData.append('os', getOS());
	formData.append('browser', getClientInfo());
	formData.append('privateIp', privateIp);

	if(form.find('input[name=userName]').val() == '') {
		lrt.client('회사명을 입력하셔야 합니다', '신분증 인증');
		return false;
	} else if(form.find('input[name=representName]').val() == '') {
		lrt.client('대표자 성명을 입력하셔야 합니다', '신분증 인증');
		return false;
	} else if(form.find('input[name=corpNo]').val() == '') {
		lrt.client('사업자 등록번호를 입력하셔야 합니다', '신분증 인증');
		return false;
	} /*else if(form.find('input[name=address]').val() == '') {
		lrt.client('회사 주소를 입력하셔야 합니다', '신분증 인증');
		return false;
	} else if(form.find('input[name=postal]').val() == '') {
		lrt.client('우편번호를 입력하셔야 합니다', '신분증 인증');
		return false;
	} */if(form.find('input[name=userName]').val() == '') {
		lrt.client('담당자명을 입력하셔야 합니다', '신분증 인증');
		return false;
	} else if(file1.val() == '') {
		lrt.client('첨부파일을 첨부하여야 합니다', '신분증 인증');
		return false;
	} else if(!checkFileSize(file1)) {
		lrt.client('최대 파일 크기는 6MB 입니다', '신분증 인증');
		return false;
	} else if(!checkExtension(file1)) {
		lrt.client('jpg, png 파일만 가능합니다', '신분증 인증');
		return false;
	} else if(file2.val() == '') {
		lrt.client('첨부파일을 첨부하여야 합니다', '신분증 인증');
		return false;
	} else if(!checkFileSize(file2)) {
		lrt.client('최대 파일 크기는 6MB 입니다', '신분증 인증');
		return false;
	} else if(!checkExtension(file2)) {
		lrt.client('jpg, png 파일만 가능합니다', '신분증 인증');
		return false;
	} else if(file3.val() == '') {
		lrt.client('첨부파일을 첨부하여야 합니다', '신분증 인증');
		return false;
	} else if(!checkFileSize(file3)) {
		lrt.client('최대 파일 크기는 6MB 입니다', '신분증 인증');
		return false;
	} else if(!checkExtension(file3)) {
		lrt.client('jpg, png 파일만 가능합니다', '신분증 인증');
		return false;
	} else {
		ajaxOption({
			url:'/site/mypage/mpg002/submitCorpIdcard',
			type: 'POST',
			data:formData,
			processData: false,
	        contentType: false,
	        loading:true,
	        success: function(data){
	        	if(data.rtnCd === 0) {
	        		lrt.client('신분증 인증 자료를 제출하였습니다.', '신분증 인증', function() {
	        			location.href='/site/info?tab=1';
	        		});
	        	} else {
	        		lrt.client(wordbook[data.rtnCd], '신분증 인증');
	        	}
	        }
		});
	}
}

function uploadProveResidence() {
	let form = $('#proveResidence')
		, file = form.find('#imgInput5')
		, file2 = form.find('#imgInput6')
		, formData = new FormData(form[0])
		, addr = $('#address').val()
		, type = $('#workType').val()
		, purpose = $('#tradePurpose').val()
		, origin = $('#moneyOrigin').val()
		, tradeCnt = $('#monthlyTradeCnt').val().replace(/[^0-9]/g, '')
		, tradeAmt = $('#monthlyTradeAmt').val().replace(/[^0-9]/g, '');

	if(addr == '') {
		lrt.client(wordbook[-5031], '거주지 인증');
		return false;
	} else if(type == '') {
		lrt.client(wordbook[-5074], '거주지 인증');
		return false;
	} else if(purpose == '') {
		lrt.client(wordbook[-5075], '거주지 인증');
		return false;
	} else if(origin == '') {
		lrt.client(wordbook[-5076], '거주지 인증');
		return false;
	} else if(tradeCnt == '') {
		lrt.client(wordbook[-5077], '거주지 인증');
		return false;
	} else if(tradeAmt == '') {
		lrt.client(wordbook[-5078], '거주지 인증');
		return false;
	} else if(!$('#ex_rd9').is(':checked')) {
		lrt.client('보안등급5 확인 사항에 동의해야 합니다', '거주지 인증');
		return false;
	} else if(!$('#ex_rd10').is(':checked')) {
		lrt.client('개인정보 수집 및 이용동의에 동의해야 합니다', '거주지 인증');
		return false;
	} else if(file.val() == '') {
		lrt.client('첨부파일을 첨부하여야 합니다', '거주지 인증');
		return false;
	} else if(!checkFileSize(file)) {
		lrt.client(wordbook[-5035], '거주지 인증');
		return false;
	} else if(!checkExtension(file)) {
		lrt.client(wordbook[-5034], '거주지 인증');
		return false;
	} else if(file2.val() == '') {
		lrt.client('첨부파일을 첨부하여야 합니다', '거주지 인증');
		return false;
	} else if(!checkFileSize(file2)) {
		lrt.client(wordbook[-5035], '거주지 인증');
		return false;
	} else if(!checkExtension(file2)) {
		lrt.client(wordbook[-5034], '거주지 인증');
		return false;
	} else {
		formData.append('addr', addr);
		formData.append('type', type);
		formData.append('purpose', purpose);
		formData.append('origin', origin);
		formData.append('tradeCnt', tradeCnt);
		formData.append('tradeAmt', tradeAmt);
		formData.append('os', getOS());
		formData.append('browser', getClientInfo());
		formData.append('privateIp', privateIp);

		ajaxOption({
			url:'/site/mypage/mpg002/submitProveResidence',
			type: 'POST',
			data:formData,
			processData: false,
	        contentType: false,
	        loading:true,
	        success: function(data){
	        	if(data.rtnCd === 0) {
	        		lrt.client('거주지 인증 자료를 제출하였습니다.', '거주지 인증', function() {
	        			location.href='/site/info?tab=1';
	        		});
	        	} else {
	        		lrt.client(wordbook[data.rtnCd], '거주지 인증');
	        	}
	        }
		});
	}
}

function checkFileSize(selector) {
	let max = 6*1024*1024;
	if(selector[0].files[0].size > max) {
		return false;
	} else {
		return true;
	}
}

function checkExtension(selector) {
	let fileName = selector[0].files[0].name;
	let index = fileName.lastIndexOf('.');

	if(index != -1) {
		let ext = fileName.substr(index+1).toLocaleLowerCase();

		if(ext === 'jpg' || ext === 'png') {
			return true;
		} else {
			return false;
		}
	}
}

$('input[type=file]').on('change', function() {
	readURL(this);
})

function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function(e) {
            $(input.previousElementSibling.previousElementSibling).attr('src', e.target.result);
        }
        reader.readAsDataURL(input.files[0]);
    }
}

$('#allAgree').on('click', function() {
	if($(this).is(':checked') === true) {
		$('#apiType').find('.switch-button').attr('class', 'switch-button active').children('input[type=checkbox]').prop('checked', true);
	} else {
		$('#apiType').find('.switch-button').attr('class', 'switch-button').children('input[type=checkbox]').prop('checked', false);
	}
})

$('#apiType').on('click', '.switch-button', function(){
	let $this = $(this);
	if($this.children().is(':checked')){
		$this.attr('class', 'switch-button active');
	}
	else{
		$this.attr('class', 'switch-button');
	}
});

var apiAuthTimer
$('#btnReqSmsCode').on('click', function() {
	let $this = $(this);

	ajaxOption({
		url:'/site/api/reqSmsCodeByApi',
		success:function(data) {
			if(data.rtnCd == 0) {
				lrt.client('회원님의 등록하신 휴대폰으로 인증번호가 발송되었습니다.<br>3분 이내로 인증절차를 진행해 주세요.', 'SMS인증요청');

				 var timeout = 180;

				 apiAuthTimer = setInterval(function() {
	                 minutes = parseInt(timeout / 60 % 60, 10);
	                 seconds = parseInt(timeout % 60, 10);

	                 minutes = minutes < 10 ? "0" + minutes : minutes;
	                 seconds = seconds < 10 ? "0" + seconds : seconds;
	                 $this.text(minutes + ' : ' + seconds);

	                 if(--timeout < 0) {
	                	 timeout = 0;
	                     clearInterval(apiAuthTimer);
	                     $this.text('인증번호 요청');
	                     // button 활성화시킴
	                     $this.prop("disabled", false);
	                 }
                }, 1000);
			} else {
				lrt.client(wordbook[data.rtnCd], '알림');
			}
		}
	})
});

$('#setApi').on('click', function() {
	let $apiType = $('#apiType').find('.switch-button').children('input[type=checkbox]:checked');
	let length = $apiType.length;
	let apiNo = '';

	$.each($apiType, function(i, d) {
		apiNo += d.dataset.no;
	});

	let p = {
			apiNo : apiNo,
			accessIp : $('#accessIp').val(),
			authCode : $('#authCode').val()
	};

	if($('#agreeApi').is(':checked') === false) {
		lrt.client('이용약관에 동의해야 합니다.', 'API 설정');
	} else if(length == 0) {
		lrt.client('최소 하나의 API를 활성화 해야 합니다.', 'API 설정');
	} else if(p.accessIp !== '' && !checkIp(p.accessIp)) {
		lrt.client('잘못된 IP 주소 입니다.', 'API 설정');
	} else if(p.authCode.length != 6) {
		lrt.client('인증번호 6자리를 입력하세요.', 'API 설정');
	} else {
		ajaxOption({
			url:'/site/api/createApi',
			data: p,
			success:function(data) {
				if(data.rtnCd == 0) {
					clearInterval(apiAuthTimer);
					$('#btnReqSmsCode').text('인증번호 요청');

					let $body = $('#apiList');
					let template = $('#API_LIST').html()
										.replace(/{{seqNo}}/g, data.seqNo)
										.replace(/{{clientId}}/g, data.clientId)
										.replace(/{{secretKey}}/g, data.secretKey)
										.replace(/{{createDt}}/g, du.getCstmFrmt(data.createDt, 'yyyyMMddHHmmss', 'yyyy-MM-dd HH:mm:ss'))
										.replace(/{{apiNo}}/g, data.userApiNo);

					if($body.children('tr').length == 1 && $body.find('td').length == 1) {
						$body.html(template);
					} else {
						$body.append(template);
					}

					$('#agreeApi').prop('checked', false);
					$('#allAgree').prop('checked', false);
					$('#apiType').find('.switch-button').attr('class', 'switch-button').children('input[type=checkbox]').prop('checked', false);
					$('#accessIp').val('');
					$('#authCode').val('');

					lrt.client('등록되었습니다.', '알림');
				} else {
					lrt.client(wordbook[data.rtnCd], '알림');
				}
			}
		});
	}
})

$('#apiList').on('click', 'button', function(e) {
	e.preventDefault();
	let $this = $(this);
	let $class = $this.attr('class');

	if($class === 'copy') {
		copyData($.trim($this.prev().text()));
		lrt.client('복사 되었습니다', '복사');
	} else if($class === 'showApiDetail') {
		let apiType = $this.closest('td').data('data');
		let length = apiType.length / 3;
		let $body = $('#apiDetailBody');

		$.each($body.children('tr'), function() {
			$(this).children('td:eq(1)').text('비활성화');
		})

		for(let i=0; i<length; i++) {
			$body.find('td[data-no=' + apiType.substr(i*3, 3) + ']').text('활성화');
		}

		$('#apiDetail').show();
	} else if($class === 'delApi') {
		let $tr = $this.closest('tr');
		let p = {
				seqNo:$tr.data('seq'),
				userYn:$this.children('input').is(':checked') === true ? 0:1,
				clientId:$.trim($tr.children('td:eq(0)').find('span').text()),
				secretKey:$.trim($tr.children('td:eq(1)').find('span').text())
		}
		lrt.confirm('선택하신 API를 삭제 하시겠습니까?', 'Open API', function() {
			ajaxOption({
				url:'/site/api/deleteApi',
				data: p,
				success:function(data) {
					if(data.rtnCd == 0) {
						$tr.remove();

						let $body = $('#apiList');
						if($body.children('tr').length == 0) {
							$body.html('<tr><td colspan="6">등록된 API가 없습니다</td></tr>');
						}

						lrt.client('삭제 되었습니다.', '알림');
					} else {
						lrt.client(wordbook[data.rtnCd], '알림');
					}
				}
			});
		});
	}
})

$('#apiList').on('click', '.switch-button', function(e) {
	e.preventDefault();

	let $this = $(this);
	let $tr = $this.closest('tr');
	let p = {
			seqNo:$tr.data('seq'),
			useYn:$this.children('input').is(':checked') === true ? 0:1,
			clientId:$.trim($tr.children('td:eq(0)').find('span').text()),
			secretKey:$.trim($tr.children('td:eq(1)').find('span').text())
	}

	ajaxOption({
		url:'/site/api/updateApiStatus',
		data: p,
		success:function(data) {
			if(data.rtnCd == 0) {
				if(p.useYn == 0) {
					$this.attr('class', 'switch-button').children('input').prop('checked', false);
				} else {
					$this.attr('class', 'switch-button active').children('input').prop('checked', true);
				}

				lrt.client('변경 되었습니다', '알림');
			} else {
				lrt.client(wordbook[data.rtnCd], '알림');
			}
		}
	});
})

function checkIp(ip) {
	var regx = /^([1-9]?[0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])(\.([1-9]?[0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])){3}$/;

	if(!regx.test(ip)) {
		return false;
	}

	return true;
}

function copyData(text) {
	var $copy = $("<input>");
	$("body").append($copy);

	$copy.val(text).select();

	document.execCommand("copy");

	$copy.remove();
}