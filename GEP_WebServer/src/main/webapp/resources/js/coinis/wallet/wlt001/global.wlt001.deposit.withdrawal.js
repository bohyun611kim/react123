///////////////////////////////////////////////////////////////////////////
// Global 변수 선언
// 거래소 기초통화
var __G_Default_Coin_Type;
// 이전 선택한 코인을 가지고있는 전역변수 (기본은 BTC)
var __G_Prev_Selected_CoinNo = 20;
var __G_Prev_Selected_CoinName = 'BTC';
// LNB 영역 리스트 아이템을 가지고있는 전역변수
var __G_LNB_Item_List;
var __G_LNB_User_Possession_List;
// 코인관련 기본적인 입/출금 정보를 가지고있는 전역변수
var __G_CoinDWInfo;
// SMS 인증여부를 갖고있는 전역변수
var __G_Bool_Auth_Request = false;
// 사용자 인증방법을 갖고있는 전역변수
var __G_User_Auth_Mean_Code = 0;

//코인관리기준정보를 Map으로 들고있는 전역변수
var __G_CoinMgtRef_Map = new simpleMap();
/*
__G_CoinMgtRef_Map :: simpleMap형태로 key는 coin_no 이며 value는 아래와 같은 정보가 담긴다.
** 사용법 : __G_CoinMgtRef_Map.get(20).COIN_SYMBOLIC_NM => BTC
AUTO_DEPOSIT_YN: 1
AUTO_WTDRW_YN: 0
COIN_ADDR_CHECK_YN: 1
COIN_ADDR_LEN: 0
COIN_DEPOSIT_ADDR_CNT: 1
COIN_NO: 20
COIN_SYMBOLIC_NM: "BTC"
COIN_WTDRW_ADDR_CNT: 1
CONFIRMATIONS: 3
CONFIRM_DELAY_TM: 30
DEPOSIT_PAGE_SHOW_YN: 1
MIN_DEPOSIT_QTY: 0
ADDR_ETC1_NM: Destination Tag
ADDR_ETC2_NM:
MIN_WTDRW_QTY: 0.001
SYSTEM_AUTO_WITHDRAW_YN: 0
TRADE_STAT_CD: 1
WTDRW_DECIMAL_PNT: 8
WTDRW_FEE: 0
WTDRW_PAGE_SHOW_YN: 1
*/

///////////////////////////////////////////////////////////////////////////////////
//                         LNB 영역 Table 그리기 Start
///////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////
// WALLET-LNB-01 LNB 영역 Coin List 그려주기
function drawLnbList(exchange_Coin) {
	//console.log(exchange_Coin);
	var lnbExchangeCoinJson = JSON.parse(exchange_Coin);
	var lnbTemplate = document.getElementById("template-wallet-lnb-coin-list-tr");
	var lnbTemplateHtml = lnbTemplate.innerHTML;
	var lnbHtml = "";
    
    __G_LNB_Item_List = lnbExchangeCoinJson;       // 전역변수에 저장

    // 거래소 상장 코인 리스트
    if(lnbExchangeCoinJson.length > 0) {
        $(lnbExchangeCoinJson).each(function(data_item) {
            var value				        = lnbExchangeCoinJson[data_item];
            var coinNo                      = getParam(value, 'COIN_NO');
            var coinSymbolicName            = getParam(value, 'COIN_SYMBOLIC_NM');
            var itemEnglishName             = getParam(value, 'ITEM_ENG_NM');
            var depositPageShowYn           = getParam(value, 'DEPOSIT_PAGE_SHOW_YN');
            var withdrawPageShowYn          = getParam(value, 'WTDRW_PAGE_SHOW_YN');
            var balanceQty                  = 0;

            var isActive                    = (coinNo == 20) ? 'active' : '';

            lnbHtml += lnbTemplateHtml.replace(/{{CoinSymbolicName}}/g, coinSymbolicName)
                                    .replace(/{{CoinNo}}/g, coinNo)
                                    .replace(/{{CoinEngName}}/g, itemEnglishName)
                                    .replace(/{{BalanceQty}}/g, balanceQty)
                                    .replace(/{{DepositPageShowYn}}/g, depositPageShowYn)
                                    .replace(/{{WithDrawPageShowYn}}/g, withdrawPageShowYn)
                                    .replace(/{{IsActive}}/g, isActive)
                                    ;
        });
        $('#wallet-lnb-coin-list-tbody').html(lnbHtml);

    } else 
        $('#wallet-lnb-coin-list-tbody').html($('#template-list-empty').html().replace(/{{ColSpan}}/g, 3));
}

////////////////////////////////////////////////////
// WALLET-LNB-02 LNB영역의 사용자 코인 보유현황 정보를 받아온다.
function getLnbPossessionInfo() {
    $.ajax({
		url : "/coinis/wallet/wlt001/selectUserPossessionInfo",
		type : 'POST',
		dataType : 'json',
		error : function(data,status,error) {
			console.log("에러 >> " + JSON.stringify(data));     
		},
		success : function(data, textStatus) {
			//console.log(data);
			writeUserPossessionInfo(data);
		}
    });
}

////////////////////////////////////////////////////
// WALLET-LNB-03 LNB 영역 사용자 Possession 정보 기록
function writeUserPossessionInfo(data) {
    __G_LNB_User_Possession_List = data;       // 전역변수에 저장

    $(data).each(function(idx) {
        var value                           = data[idx];
        var coinNo                          = getParam(value, 'COIN_NO');
        var balanceQty                      = getParam(value, 'USABLE_QTY');
        var estimatedQty                    = getParam(value, 'TOTAL_USABLE_BY_BC');
        var possessPercent                  = getParam(value, 'POSS_PERCENT_BY_BC');
        var grandTotalPoss                  = getParam(value, 'GRAND_TOTAL_POSS_BY_BC');

        $('#coin-tr-' + coinNo).attr('data-qty', balanceQty);
        $('#possession-qty-' + coinNo).text( nu.cm(balanceQty, __G_CoinMgtRef_Map.get(coinNo).WTDRW_DECIMAL_PNT) );
    });
}

////////////////////////////////////////////////////
// WALLET-LNB-04 balance checkbox Click Event 처리
$('#only-balance-show-check-box').click(function() {
	if( $(this).prop('checked') ) {
		$('#wallet-lnb-coin-list-tbody > tr').each(function() {
			if(parseFloat($(this).data('qty')) == 0) $(this).hide();
		});
	} else 
		$('tbody[id="wallet-lnb-coin-list-tbody"] > tr').show();
});

////////////////////////////////////////////////////
// WALLET-LNB-04 LNB Table TR Click 이벤트 처리
$('#wallet-lnb-coin-list-tbody').delegate('tr', 'click', function (event) {
    var coinNo                  = $(this).data('no');
    var coinDomesticName        = $(this).data('eng-nm');
    var coinSymbol              = $(this).data('nm');

    // 이전에 선택한 TR일 경우 아무런 처리없이 skip 한다.
    if(coinNo == __G_Prev_Selected_CoinNo || coinNo == undefined) return false;
	__G_Prev_Selected_CoinNo = coinNo;
	__G_Prev_Selected_CoinName = coinSymbol;

    // 다른 TR의 active class를 없애고
    $('#wallet-lnb-coin-list-tbody tr').each(function() {
        $(this).attr('class', '');
    });
    // 현재 TR의 active class를 설정한다.
    $(this).attr('class', 'active');

    doProcessRFrame(coinNo, coinSymbol, coinDomesticName);
});

////////////////////////////////////////////////////
// WALLET-LNB-05 Right Frame 데이터 처리
function doProcessRFrame(coinNo, coinSymbol, coinDoemsticName) {
	// 선택된 Tab 계산 (사용처...?)
    var selected_tab;
    for(var i = 1; i <= 2; i++) {
        if($('#tabs-' + i).is(':visible')) selected_tab = i;
    }

	$( ".coin-name-domestic" ).each(function( index ) { $(this).text(coinDoemsticName); });
	$( ".coin-symbolic-name" ).each(function( index ) { $(this).text(coinSymbol); });
	// LNB Item이 바뀌면 TAB은 항상 1번탭으로...
	$('#wallet-tab-a-1').trigger('click');
	// 코인 입금주소 정보 보여주기
	getCoinDepositInfo(coinNo);
}
///////////////////////////////////////////////////////////////////////////////////
//                         LNB 영역 Table 그리기 END
///////////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////////
//                         Right Frame 영역 처리 Start
///////////////////////////////////////////////////////////////////////////////////
// Tab click event 처리
var __G_Prev_Selected_WalletTab = 1;
$('#wallet-tab-ul').click(function() {
    // 선택된 Tab 알아오기 (Deposit : 1, Withdrawal: 2)
    var selected_tab;
    for(var i = 1; i <= 2; i++) {
        if($('#tabs-' + i).is(':visible')) selected_tab = i;
    }
    if(__G_Prev_Selected_WalletTab == selected_tab) return false;     // 변경된 Tab 이 없으므로 skip

    __G_Prev_Selected_WalletTab = selected_tab;

    if(__G_Prev_Selected_WalletTab == 2) {
        getWithdrawInfo(__G_Prev_Selected_CoinNo);
    }
});

////////////////////////////////////////////////////
// WALLET-DEPOSIT-01 Deposit 데이터 처리
function getCoinDepositInfo(coin_no) {
	ajax('/coinis/wallet/wlt001/selectUserWalletInfo', {coin_no: coin_no}, function(coinDWInfo) {
		// 선택 코인 정보 전역변수 셋팅
		__G_CoinDWInfo = coinDWInfo;

		// 입금 중단 여부
		if(coinDWInfo.deposit_page_show_yn != 1) {
			$('#wallet-deposit-banned-dw-status').show();
			$('#wallet-deposit-coin-address-generate').hide();
			$('#wallet-deposit-coin-address-users').hide();
		} 
		else {
			$('#wallet-deposit-banned-dw-status').hide();
			// 주소 발급 여부
			if(coinDWInfo.deposit_wallet_addr === "" || coinDWInfo.deposit_wallet_addr === null || coinDWInfo.deposit_wallet_addr.length === 0) {
				$('#wallet-deposit-coin-address-generate').show();
				$('#wallet-deposit-coin-address-users').hide();
			}
			else {
				// 입금 주소 표시
				$('#wallet-deposit-coin-user-address').text(coinDWInfo.deposit_wallet_addr);
				
				// 입금 주소 QR 표시
				showQRCode($('#wallet-deposit-coin-user-address').text());
				
				// 추가 주소 없음
				if(coinDWInfo.addr_etc1 === "" || coinDWInfo.addr_etc1 === null || coinDWInfo.addr_etc1.length === 0) {
					$('#wallet-deposit-coin-address-extra').hide();
				} else {
					// 추가 주소 1
					$('.wallet-deposit-coin-address-extra-1-title').text(__G_CoinMgtRef_Map.get(coinDWInfo.coin_no).ADDR_ETC1_NM);
					$('#wallet-deposit-coin-address-extra-1-address').text(coinDWInfo.addr_etc1);
					$('#wallet-deposit-coin-address-extra').show();
				}
				$('#wallet-deposit-coin-address-generate').hide();
				$('#wallet-deposit-coin-address-users').show();
			}
		}

		// 출금 중단 여부
		if(coinDWInfo.wtdrw_page_show_yn != 1) {
			$('#wallet-withdraw-banned-dw-status').show();
			$('#wallet-withdraw-security-auth').hide();
			$('#wallet-withdraw-input-table').hide();
			$('#wallet-withdraw-button').hide();
		}
	});
}

////////////////////////////////////////////////////
// WALLET-DEPOSIT-02 Coin 주소 신규 발급
function createDepositAddr() {
	var p = {};

	p["COINKIND"] = __G_Prev_Selected_CoinName;
	p["COINNO"] = __G_Prev_Selected_CoinNo;
	
	$.ajax({
		url: '/coinis/wallet/wlt001/createNewCoinAddress'
	  , data: p
	  , type: 'POST'
	  , dataType: 'json'
	  , error: function(data, status, error) {
		  console.log('Coin 주소 신규발급 에러 >> ' + error);
	  }
	  , success: function(data, textStatus) {
			console.log(data);
			
			// 주소발급 숨기기
			// 주소 보이기
			$('#wallet-deposit-coin-address-generate').hide();
			$('#wallet-deposit-coin-address-users').show();
			
			// 입금 주소 표시
			var address = getParam(JSON.parse(data.body), 'newCoinAccount');
			$('#wallet-deposit-coin-user-address').text(address);
			// 입금 주소 QR 표시
	        showQRCode(address);
			
			// 추가 주소 없음
			if( __G_CoinDWInfo.coin_deposit_addr_cnt == 1) {
				$('#wallet-deposit-coin-address-extra').hide();
			} else if( __G_CoinDWInfo.coin_deposit_addr_cnt > 1) {
				// 추가 주소 1
				$('#wallet-deposit-coin-address-extra-1-address').text(getParam(JSON.parse(data.body), 'newCoinAccount1'));
				$('.wallet-deposit-coin-address-extra-1-title').text(__G_CoinMgtRef_Map.get(__G_Prev_Selected_CoinNo).ADDR_ETC1_NM);
			}
		}
	});
}

////////////////////////////////////////////////////
// WALLET-WITHDRAW-01 Withdrawal 데이터 처리
function getWithdrawInfo(coin_no) {
	if(__G_CoinDWInfo.wtdrw_page_show_yn != 1) return false;	// 출금페이지 show yn = 0이면 페이지 데이터 skip

	// Tab Data 초기화
	initTabData();

	ajax('/coinis/wallet/wlt001/selectUserBalanceInfo',  {coinNo: coin_no , reqQty : __G_CoinMgtRef_Map.get(coin_no).MIN_WTDRW_QTY }, 
		function(userWithdrawalInfo) {
			console.log(userWithdrawalInfo);
			__G_User_Auth_Mean_Code = userWithdrawalInfo.AUTH_MEANS_CD;
			// 보안인증수단 설정 :: SMS_USE_YN != 0 || OTP_YN != 0
			if(userWithdrawalInfo.SMS_USE_YN == 0 && userWithdrawalInfo.OTP_YN == 0) {
				$('#wallet-withdraw-banned-dw-status').hide();
				$('#wallet-withdraw-security-auth').show();
				$('#wallet-withdraw-input-table').hide();
				$('#wallet-withdraw-button').hide();
			} else {
				$('#wallet-withdraw-banned-dw-status').hide();
				$('#wallet-withdraw-security-auth').hide();
				$('#wallet-withdraw-input-table').show();
				$('#wallet-withdraw-button').show();
			}

			// 사용자 보유코인, 일출금한도, 잔여한도 표출
			var userPossessQty	= nu.cm(userWithdrawalInfo.WTDRW_POSS_QTY,  __G_CoinMgtRef_Map.get(coin_no).WTDRW_DECIMAL_PNT);
			var dailyLeftLimit	= nu.cm(userWithdrawalInfo.DAILIY_LIMIT_LEFT_QTY,  __G_CoinMgtRef_Map.get(coin_no).WTDRW_DECIMAL_PNT);
			var dailyMaxLimit	= nu.cm(userWithdrawalInfo.DAILIY_LIMIT_MAX_QTY,  __G_CoinMgtRef_Map.get(coin_no).WTDRW_DECIMAL_PNT);
			$('#wallet-withdraw-user-possess-qty').text(userPossessQty);
			$('#wallet-withdraw-daily-left-qty').text(dailyLeftLimit);
			$('#wallet-withdraw-daily-max-qty').text(dailyMaxLimit);

			// 코인주소 갯수에따른 표출
			if(__G_CoinDWInfo.coin_deposit_addr_cnt > 1) {
				$('#wallet-withdraw-target-address-extra-1-tr').show();
				$('#wallet-withdraw-target-address-extra-1-title').text(__G_CoinMgtRef_Map.get(coin_no).ADDR_ETC1_NM);
			} else $('#wallet-withdraw-target-address-extra-1-tr').hide();

			// 최소출금수량 셋팅
			var minWithdrawQty	= nu.cm(__G_CoinDWInfo.min_wtdrw_qty,  __G_CoinMgtRef_Map.get(coin_no).WTDRW_DECIMAL_PNT);
			$('#wallet-withdraw-min-qty').text(minWithdrawQty);
			// 출금 Fee 셋팅
			var withdrawFee		= nu.cm(__G_CoinDWInfo.wtdrw_fee,  __G_CoinMgtRef_Map.get(coin_no).WTDRW_DECIMAL_PNT);
			$('#wallet-withdraw-fee').text(withdrawFee);

			if(userWithdrawalInfo.AUTH_MEANS_CD == 2) {			// SMS 인증
				$('#wallet-withdraw-auth-sms-tr').show();
				$('#wallet-withdraw-auth-otp-tr').hide();
			} else if(userWithdrawalInfo.AUTH_MEANS_CD == 3){	// OTP 인증
				$('#wallet-withdraw-auth-sms-tr').hide();
				$('#wallet-withdraw-auth-otp-tr').show();
			}
	});
}

////////////////////////////////////////////////////
// WALLET-WITHDRAW-02 Withdrawal Tab Data Clear & init 함수
function initTabData() {
	$('#wallet-withdraw-target-address').val('');
	$('#wallet-withdraw-target-address-extra-1').val('');
	$('#wallet-withdraw-qty').val('');
	$('#wallet-withdraw-auth-sms-code').val('');
	$('#wallet-withdraw-auth-otp-code').val('');
	$('#wallet-withdraw-actual-qty').text('0.0');
}

////////////////////////////////////////////////////
// WALLET-WITHDRAW-03 Coin 주소 Copy button 이벤트처리
$('#wallet-deposit-copy-coin-address').on('click', function() {
	copy2Clipboard($('#wallet-deposit-coin-user-address').text().trim());
});

////////////////////////////////////////////////////
// WALLET-WITHDRAW-04 출금수량 입력처리 함수
$('#wallet-withdraw-qty').on('keydown', function(e) {
	evntKeyDown(e, this, __G_CoinMgtRef_Map.get(__G_Prev_Selected_CoinNo).WTDRW_DECIMAL_PN);
	checkWithdrawMaxMoney();
});
////////////////////////////////////////////////////
// WALLET-WITHDRAW-04-1 요청수량이 최대수량을 넘지않았나 체크하는 함수
function checkWithdrawMaxMoney() {
    var user_input = $('#wallet-withdraw-qty').val().replace(/,/g,'');
    var min_withdraw_qty = parseFloat($('#wallet-withdraw-min-qty').val().replace(/,/g,''));
    if(user_input == '') {
        $('#wallet-withdraw-actual-qty').text('0');
        return false;
    }
    
    // 요청 수량이 원화 WTDRW_DECIMAL_PNT 자릿수를 넘었을때 처리 
    var coin_dcm_pnt = __G_CoinMgtRef_Map.get(__G_Prev_Selected_CoinNo).WTDRW_DECIMAL_PNT;
    if(user_input.indexOf('.') > -1) {
        var parts = user_input.toString().split(".");
        if(parts[1].length > coin_dcm_pnt) {
            user_input = parts[0] + '.' + parts[1].substr(0, coin_dcm_pnt);
            $('#wallet-withdraw-qty').val(user_input);
        }
    }

    var user_input_qty = parseFloat(user_input);
    var qty = getCoinMaxReqQty();
    var maxQty  = parseFloat(qty.split('|')[0]);
    var realQty = parseFloat(qty.split('|')[1]);
    if(user_input_qty > maxQty) {
        $('#wallet-withdraw-qty').val( nu.cm(maxQty, coin_dcm_pnt) );
        $('#wallet-withdraw-actual-qty').text(nu.cm(realQty, coin_dcm_pnt));
        return false;
    } else if(user_input_qty == 0) {
        $('#wallet-withdraw-actual-qty').text('0');
        return false;
    } else if(user_input_qty < min_withdraw_qty) {
        return false;
    } else {
        var withdraw_fee    = parseFloat($('#wallet-withdraw-fee').text());
        $('#wallet-withdraw-actual-qty').text(''+ nu.cm((user_input_qty + withdraw_fee), coin_dcm_pnt) );
        return true;
    }
}
////////////////////////////////////
// WALLET-WITHDRAW-04-2 최대 출금 버튼 이벤트 처리
function setMaxWithdrawQty() {
    var qty = getCoinMaxReqQty();
    var maxQty = qty.split('|')[0];
    var realQty = qty.split('|')[1];
    $('#wallet-withdraw-qty').val(maxQty);
    $('#wallet-withdraw-actual-qty').text(realQty);
}

////////////////////////////////////
// WALLET-WITHDRAW-04-3 최대 요청수량 계산 함수
function getCoinMaxReqQty() {
    var available_qty   = parseFloat($('#wallet-withdraw-user-possess-qty').text().replace(/,/gi,''));
    var daily_max_qty   = parseFloat($('#wallet-withdraw-daily-max-qty').text().replace(/,/gi,''));
    var daily_left_qty  = parseFloat($('#wallet-withdraw-daily-left-qty').text().replace(/,/gi,''));
    var min_wd_qty      = parseFloat($('#wallet-withdraw-min-qty').text().replace(/,/gi,''));
    var withdraw_fee    = parseFloat($('#wallet-withdraw-fee').text().replace(/,/gi,''));
    var max_withdraw_qty = ((available_qty - withdraw_fee) > (daily_left_qty)) ? (daily_left_qty) : (available_qty - withdraw_fee);
    max_withdraw_qty = (max_withdraw_qty >= withdraw_fee) ? max_withdraw_qty : 0;
    max_withdraw_qty = (max_withdraw_qty >= min_wd_qty) ? max_withdraw_qty : 0;

	var real_withdraw_qty = (available_qty > 0) ? (max_withdraw_qty + withdraw_fee) : 0;
	if(real_withdraw_qty > available_qty) real_withdraw_qty = available_qty;
	if(real_withdraw_qty > max_withdraw_qty) real_withdraw_qty = max_withdraw_qty;
    return nu.js(max_withdraw_qty, 8) + '|' + nu.js(real_withdraw_qty, 8);
}

////////////////////////////////////
// WALLET-WITHDRAW-05-1 SMS 인증번호 요청을 위한 value check 함수
function checkAndRequestSmsAuthCode() {
	var max = Number( $('#wallet-withdraw-user-possess-qty').text() ) < Number( $('#wallet-withdraw-daily-left-qty').text() )
	? $('#wallet-withdraw-user-possess-qty').text() : $('#wallet-withdraw-daily-left-qty').text();
	
	if ( Number( $('#wallet-withdraw-actual-qty').text() ) > max ) {
		lrt.client('Please check available withdrawal quantity and input correctly.');
		return false;
	} else {
		var p = {};

		p["coinNo"] = __G_Prev_Selected_CoinNo;
		p["coinSymbol"] = __G_Prev_Selected_CoinName;
		p["reqQty"] = $('#wallet-withdraw-qty').val();
		p["targetAddr"] = $('#wallet-withdraw-target-address').val();
		p["targetAddrEtc1"] = $('#wallet-withdraw-target-address-extra-1').val();
		p["targetAddrEtc2"] = '';
		
		if( $('#wallet-withdraw-qty').val() == '') {
			lrt.client('Withdrawal Qty is empty');
			return false;
		} else if( p.arg_target_addr == '' ) {
			lrt.client('Target address is empty');
			return false;
		} else if(__G_CoinDWInfo.coin_deposit_addr_cnt > 1 && p.arg_target_addr_etc1 == '') {
			var extraAddrName1 = __G_CoinMgtRef_Map.get(__G_Prev_Selected_CoinNo).ADDR_ETC1_NM;
			lrt.confirm( extraAddrName1 + ' is empty, ignore and continue?', extraAddrName1 + ' Address Error', function() {
				requestSmsAuthCode(p);
		    }, null);
			return false;
	    } else {
			requestSmsAuthCode(p);
		}
	}
}

////////////////////////////////////
// WALLET-WITHDRAW-05-2 SMS 인증번호 요청
var __G_SMS_Auth_Request_btn_timer = 0;
function requestSmsAuthCode(p) {
	ajax('/coinis/wallet/wlt001/requestSmsAuthNumber', p, function(data) {
		console.log(data);
		if(data.rtnCd == 0) {
			// message box
			lrt.client('Verify code is sent to your mobile phone<br>Please process verification in 3 minutes.', 'Send SMS', null, null);
			__G_Bool_Auth_Request = true;
			// 인증번호 요청 Timer Button 설정
			// button 비활성화시킴
			$('#wallet-withdraw-auth-sms-code-request-btn').prop("disabled", true);
			var timeout = 180;
			__G_SMS_Auth_Request_btn_timer = setInterval(function() {
				minutes = parseInt(timeout / 60 % 60, 10);
				seconds = parseInt(timeout % 60, 10);

				minutes = minutes < 10 ? "0" + minutes : minutes;
				seconds = seconds < 10 ? "0" + seconds : seconds;
				$('#wallet-withdraw-auth-sms-code-request-btn').text(minutes + ' : ' + seconds);

				if (--timeout < 0) {
					timeout = 0;
					clearInterval(__G_SMS_Auth_Request_btn_timer);
					$('#wallet-withdraw-auth-sms-code-request-btn').text('Send SMS');
					// button 활성화시킴
					$('#wallet-withdraw-auth-sms-code-request-btn').prop("disabled", false);
				}
			}, 1000);
		} else {
			lrt.client(wordbook[data.rtnCd], 'Fail SMS', function() {
				// button 활성화시킴
				$('#wallet-withdraw-auth-sms-code-request-btn').prop("disabled", false);
			}, null);
		}
	});
}

////////////////////////////////////
// WALLET-WITHDRAW-06-1 출금신청 버튼 이벤트 처리
function requestWithDraw() {
    var authSmsCode = $('#wallet-withdraw-auth-sms-code').val();
    var authOtpCode = $('#wallet-withdraw-auth-otp-code').val();

    var paramJson = {
        authCode : (__G_User_Auth_Mean_Code == 2) ? authSmsCode : authOtpCode
    }

    if(__G_User_Auth_Mean_Code == 2) {
        paramJson = {
            authCode : authSmsCode
        }
    // OTP 인증일때는 모든항목 체크
    } else if(__G_User_Auth_Mean_Code == 1) {
    	var userPossessQty = 0, userDailyLeftQty = 0;
    	try {
        	userPossessQty	= parseFloat($('#wallet-withdraw-user-possess-qty').text().replace(/,/g, ''));
        	userDailyLeftQty= parseFloat($('#wallet-withdraw-daily-left-qty').text().replace(/,/g, ''));
    	} catch(err) {};

        var reqQty          = $('#wallet-withdraw-qty').val();
        var targetAddr      = $('#wallet-withdraw-target-address').val();
        var targetAddrEtc1  = $('#wallet-withdraw-target-address-extra-1').val();
        var targetAddrEtc2  = $('#wallet-withdraw-target-address-extra-1').val();
        var reqMemo         = '';

        if(userPossessQty == 0 || userDailyLeftQty == 0) {
            lrt.client('Not enough withdrawable balance.', 'Not Enough Balance', null, null);
            return false;
        }
        if(targetAddr == '') {
            lrt.client('Please enter target address.', 'Target Address', function() {
                $('#wallet-withdraw-target-address').focus();
            }, null);
            return false;
        }
        if(reqQty == '' || parseFloat(reqQty) == 0) {
            lrt.client('Please enter request quantity.', 'Request Quantity', function() {
                $('#wallet-withdraw-qty').focus();
            }, null);
            return false;
        }
        if(!checkWithdrawMaxMoney()) {
            lrt.client('Check request quantity. Request quantity must be greater than minimum withdrawal quantity and less than withdrawable quantity.', 'Request Quantity', function() {
                $('#wallet-withdraw-qty').focus();
            }, null);
            return false;
        }
        var paramJson = {
              coinNo            : __G_Prev_Selected_CoinNo
            , coinSymbol        : getCoinSymbolByCoinNo(__G_Prev_Selected_CoinNo)
            , reqQty            : reqQty
            , targetAddr        : targetAddr
            , targetAddrEtc1    : targetAddrEtc1
            , targetAddrEtc2    : targetAddrEtc2
            , reqMemo           : reqMemo
            , authCode          : authOtpCode
        }
    }

    if(__G_User_Auth_Mean_Code == 2 && __G_Bool_Auth_Request == false) {
        lrt.client('You did not request verification.', 'Verification', function() {
            $('#wallet-withdraw-auth-sms-code').focus();
        }, null);
        return false;
    }
    if(__G_User_Auth_Mean_Code == 2 && authSmsCode == '') {
        lrt.client('Please enter your verification code.', 'Verify Error', function() {
            $('#wallet-withdraw-auth-sms-code').focus();
        }, null);
        return false;
    }
    if(__G_User_Auth_Mean_Code == 1 && authOtpCode == '') {
        lrt.client('Please enter your verification code.', 'Verify Error', function() {
            $('#wallet-withdraw-auth-otp-code').focus();
        }, null);
        return false;
    }
    // 추가주소 갯수 가져오기
    var wtdAddrCnt = parseInt(__G_CoinMgtRef_Map.get(__G_Prev_Selected_CoinNo).COIN_WTDRW_ADDR_CNT);
    var etcAddrName1 = __G_CoinMgtRef_Map.get(__G_Prev_Selected_CoinNo).ADDR_ETC1_NM;
    if(wtdAddrCnt > 1 && targetAddrEtc1 == '') {
        lrt.confirm( etcAddrName1 + ' is empty, ignore and continue?', etcAddrName1 + ' Address Error', function() {
        	doRequestWithdraw(paramJson);
        }, null);
        return false;
    }

    doRequestWithdraw(paramJson);
}
////////////////////////////////////
// WALLET-WITHDRAW-06-2 출금요청 프로세스 실행
function doRequestWithdraw(paramJson) {
    $.ajax({
        url : "/coinis/wallet/wlt001/doRequestWithDraw"
        , data : paramJson
        , type : 'POST'
        , dataType : 'json'
        , error : function(data,status,error) {
            //console.log("에러 >> " + JSON.stringify(data));
        }
        , success : function(data, textStatus) {
            // console.log(data);
            if(data.rtnCd == 0) {
                // message box
                lrt.client('Withdrawal has been requested.', 'Withdrawal', null, null);
                initTabData();                			// 출금신청 화면 초기화
                //$('#coin-tab-a-3').trigger('click');    // 입출금내역 화면으로 전환
            } else {
                lrt.client(wordbook[data.rtnCd], 'Withdrawal Fail', function() {
                    // button 활성화시킴
                    $('#wallet-withdraw-auth-sms-code-request-btn').prop("disabled", false);
                }, null);
            }
        }
    });
}
///////////////////////////////////////////////////////////////////////////////////
//                         Right Frame 영역 처리 END
///////////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////////
//                             COMMON Function
///////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////
// WALLET-COMMON-01 QRCODE Image 생성함수
function showQRCode(address) {
	$('.my-addr-qr').empty();
    var qrcode = new QRCode(document.getElementById("my-addr-qr"), {
        text: address,
        width: 100,
        height: 100,
        colorDark : "#000000",
        colorLight : "#ffffff",
        correctLevel : QRCode.CorrectLevel.H
    });
}

////////////////////////////////////////////////////
// WALLET-COMMON-02 Copy to Clipboard 함수
function copy2Clipboard(address) {
	su.copy(address);
	lrt.client('Address is copied to clipboard');
}

////////////////////////////////////////////////////
// WALLET-COMMON-03 Text input창 keydown 처리 함수
function evntKeyDown(e, target, point) {
	let regex = /^4[8-9]$|^5[0-7]{1}$|^9[6-9]{1}$|^10[0-5]{1}$|^110$|^190$/gm
		, key = JSON.parse(JSON.stringify(e.keyCode))
		, tbody = $(target).closest('tbody')
		, type = tbody.data('type');
	//console.log(key);
	if(regex.test(key)) {
		e.preventDefault();
		
		let $this = $(target);
		let value = $this.val();
		let first = value.substr(0, target.selectionStart);
		let last = value.substr(target.selectionEnd);
		let index = -1;
		let position = value.length;
		
		index = value.indexOf('.');
		if(index != -1 
				&& value.substr(index+1).length >= point 
				&& target.selectionStart == target.selectionEnd
				&& target.selectionEnd > value.indexOf('.')) {return false;}
		if(value.length > $this.attr('maxlength')
				&& target.selectionStart == target.selectionEnd) {return false;}
		
		value = first + e.key + last;
		
		index = value.indexOf('.');
		if(index > -1) {
			var integer = parseFloat(value.substr(0, index).replace(/[^0-9]/g, ''));
			var decimalPoint = value.substr(index+1, point).replace(/[^0-9]/g, '');
			value = nu.cm(integer) + '.'  + decimalPoint;
		} else {
			value = nu.cm(value.replace(/[^0-9.]/g, ''), point);
		}
		
		position = value.length - last.length;
		
		$this.val(value);
		target.setSelectionRange(position, position);
		
		return false;
	} else {
		if(e.ctrlKey && (key == 65 || key == 67)) {
			return true;
		} else if(e.ctrlKey && key == 86) {
			return true;
		} else  if(key == 9 || key == 36 || key == 35 || key == 37 || key == 38 || key == 39 || key == 40 || key == 116) {
			return true;
		} else  if(key == 46 || key == 8) {
			e.preventDefault();
			
			var $this = $(target);
			var value = $this.val();
			var first, last, position;
			var start = target.selectionStart
			var end = target.selectionEnd;
			
			key = start == end ? key:0;
			 
			switch(key) {
			case 8:
				if(value.substring(start - 1, end) == ',') { start--; }
				
				first = value.substr(0, start - 1);
				last = value.substr(end);
				break;
			case 46:
				first = value.substr(0, start);
				last = value.substr(end + 1);
				break;
			default:
				first = value.substr(0, start);
				last = value.substr(end);
				break;
			}

			value = nu.cm((first + last).replace(/[^0-9.]/g, ''), point);
			position = value.length - last.length;
			position = position < 0 ? 0:position;
			
			$this.val(value);
			target.setSelectionRange(position, position);
			
			return false;
		} else {
			e.preventDefault();
			return false;				
		}
	}
}
