// 거래소별 기본 기축통화 설정 (Tomcat 환경설정에 설정된것을 WAS에서 읽음)
var __G_Default_Coin_Type = 10;
// LNB 영역 코인 ITEM List를 가지고 있는 전역변수
var __G_LNB_Money_Item_List;
// LNB 영역 코인 ITEM List를 가지고 있는 전역변수
var __G_LNB_Coin_Item_List;
// LNB 영역 회원 보유잔고를 가지고 있는 전역변수
var __G_LNB_User_Possession_List;
// LNB 리스트 클릭한 COIN_NO, COIN/MONEY를 저장한 전역변수
var __G_Prev_Selected_CoinNo = 0;
var __G_Prev_Selected_CoinKind = 'MONEY';
// 코인관리기준정보를 Map으로 들고있는 전역변수
var __G_CoinMgtRef_Map = new simpleMap();
// 회원의 인증레벨
var __G_User_Auth_Level = 0;
// 회원의 인증수단      (1: OTP, 2: SMS, 3: Email, 0: 미사용)
var __G_User_Auth_Mean_Code = 0;
// 은행코드 정보
var _G_Bank_Code_List;
var _G_Bank_Code_Map = new simpleMap();
// 거래소 입금/출금 은행 정보
var __G_Exchange_Bank_Accnt_Info;


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

//////////////////////////////////////////////////////////////////////////////
//                           LNB 영역 리스트 처리 START
//////////////////////////////////////////////////////////////////////////////
// LNB영역의 사용자 코인 보유현황 정보를 받아온다.
function getLnbPossessionInfo() {
    $.ajax({
    	url : "/site/wallet/wlt002/selectUserPossessionInfo"
    //   , data : {
    // 	    ItemCode : itemCode
	//    }
      , type : 'POST'
      , dataType : 'json'
      , error : function(data,status,error) {
          	//console.log("에러 >> " + JSON.stringify(data));
        }
      , success : function(data, textStatus) {
            //console.log(data);
            writeUserPossessionInfo(data);
        }
    });
}

function drawLnbList(default_Money, exchange_Coin) {
	var lnbDefaultMoneyJson = JSON.parse(default_Money);
	var lnbExchangeCoinJson = JSON.parse(exchange_Coin);
	var lnbTemplate = document.getElementById("template-wallet-lnb-coin-list");
	var lnbTemplateHtml = lnbTemplate.innerHTML;
    var lnbHtml = "";

    // 초성검색을 위해 초성을 발라냄
    lnbExchangeCoinJson.forEach(function(item) {
        try {
            var dis = Hangul.disassemble(item.ITEM_KOR_NM, true);
            var cho = dis.reduce(function(prev, elem) {
                elem = elem[0] ? elem[0] : elem;
                return prev + elem;
            }, '');
            item.disassembled = cho;
        } catch(error) {}
    });

    __G_LNB_Money_Item_List = lnbDefaultMoneyJson;      // 전역변수에 저장 (Money)
    __G_LNB_Coin_Item_List = lnbExchangeCoinJson;       // 전역변수에 저장 (Coin)

    if(lnbDefaultMoneyJson.length > 0) {
        $(lnbDefaultMoneyJson).each(function(data_item) {
            var value				        = lnbDefaultMoneyJson[data_item];
            var coinNo                      = getParam(value, 'coin_NO');
            var coinSymbolicName            = getParam(value, 'coin_SYMBOLIC_NM');
            var itemDemesticName            = coinSymbolicName;
            var possessPercent              = 0;
            var possessQty                  = 0;
            var estimateQty                 = 0;
            var baseEstimateSymbolicName    = __G_CoinMgtRef_Map.get(__G_Default_Coin_Type).COIN_SYMBOLIC_NM;

            var isActive                    = (coinNo == 10) ? 'active' : '';

            lnbHtml += lnbTemplateHtml.replace(/{{CoinSymbolicName}}/g, coinSymbolicName)
                                    .replace(/{{CoinNo}}/g, coinNo)
                                    .replace(/{{ItemDemesticName}}/g, itemDemesticName)
                                    .replace(/{{PossessPercent}}/g, possessPercent)
                                    .replace(/{{PossessQty}}/g, possessQty)
                                    .replace(/{{EstimateQty}}/g, estimateQty)
                                    .replace(/{{BaseEstimateSymbolicName}}/g, baseEstimateSymbolicName)
                                    .replace(/{{CoinKind}}/g, 'MONEY')
                                    .replace(/{{IsActive}}/g, isActive)
                                    ;
        });
        //$('#wallet-lnb-coin-list-tbody').html(lnbHtml);
    }

    // 거래소 상장 코인 리스트
    if(lnbExchangeCoinJson.length > 0) {
        $(lnbExchangeCoinJson).each(function(data_item) {
            var value				        = lnbExchangeCoinJson[data_item];
            var coinNo                      = getParam(value, 'COIN_NO');
            var coinSymbolicName            = getParam(value, 'COIN_SYMBOLIC_NM');
            var itemDemesticName            = getParam(value, 'ITEM_DOMESTIC_NM');
            var coinSymbolChosung           = getParam(value, 'disassembled');
            var possessPercent              = 0;
            var possessQty                  = 0;
            var estimateQty                 = 0;
            var baseEstimateSymbolicName    = __G_CoinMgtRef_Map.get(__G_Default_Coin_Type).COIN_SYMBOLIC_NM;

            var isActive                    = (coinNo == 10) ? 'active' : '';

            lnbHtml += lnbTemplateHtml.replace(/{{CoinSymbolicName}}/g, coinSymbolicName)
                                    .replace(/{{CoinNo}}/g, coinNo)
                                    .replace(/{{ItemDemesticName}}/g, itemDemesticName)
                                    .replace(/{{PossessPercent}}/g, possessPercent)
                                    .replace(/{{PossessQty}}/g, possessQty)
                                    .replace(/{{EstimateQty}}/g, estimateQty)
                                    .replace(/{{BaseEstimateSymbolicName}}/g, baseEstimateSymbolicName)
                                    .replace(/{{CoinKind}}/g, 'COIN')
                                    .replace(/{{IsActive}}/g, isActive)
                                    .replace(/{{CoinSymbolChosung}}/g, coinSymbolChosung)
                                    ;
        });
        $('#wallet-lnb-coin-list-tbody').html(lnbHtml);
    } else
        $('#wallet-lnb-coin-list-tbody').html($('#template-list-empty').html().replace(/{{ColSpan}}/g, 3));
}

function writeUserPossessionInfo(data) {
    __G_LNB_User_Possession_List = data;       // 전역변수에 저장

    $(data).each(function(idx) {
        var value                           = data[idx];
        var coinNo                          = getParam(value, 'COIN_NO');
        var balanceQty                      = getParam(value, 'USABLE_QTY');
        var estimatedQty                    = getParam(value, 'TOTAL_USABLE_BY_BC');
        var possessPercent                  = getParam(value, 'POSS_PERCENT_BY_BC');
        var grandTotalPoss                  = getParam(value, 'GRAND_TOTAL_POSS_BY_BC');

        if(idx == 0) $('#total_estimated_qty').text( nu.cm(parseFloat(grandTotalPoss), 0) );

        $('#possession-percent-' + coinNo).attr('style', 'width: ' + parseFloat(possessPercent).toFixed(0) + '%;');
        $('#possession-qty-' + coinNo).text( nu.cm(balanceQty, __G_CoinMgtRef_Map.get(coinNo).WTDRW_DECIMAL_PNT) );
        $('#estimate-qty-' + coinNo).text( nu.cm(estimatedQty,  __G_CoinMgtRef_Map.get(__G_Default_Coin_Type).WTDRW_DECIMAL_PNT) );
    });
}

// 보유코인 체크박스 클릭 처리함수
function showOnlyPossessedCoin(checked) {
    // Active 상태 class를 없앤다.
    $('#wallet-lnb-coin-list-tbody tr').each(function(idx) {
        $(this).attr('class', '');
    });
    var only_possess_seq = 0;
    $('#wallet-lnb-coin-list-tbody tr').each(function(idx) {
        var possessQty = $(this).find('td').eq(2).find('p').eq(0).find('span').eq(0).text();
        //console.log(possessQty);
        if(checked) {
            if(parseFloat(possessQty) == 0) {
                $(this).hide();
            } else {
                // 첫번째 TR Click 이벤트를 날린다.
                if(only_possess_seq == 0) $(this).trigger('click');
                $(this).show();
                only_possess_seq ++;
            }
        } else {
            // 첫번째 TR Click 이벤트를 날린다.
            if(only_possess_seq == 0) $(this).trigger('click');
            $(this).show();
            only_possess_seq ++;
        }
    });
}
// 코인명/심볼 검색 처리함수
function searchCoinSymbolOrName(name) {
    // Active 상태 class를 없앤다.
    $('#wallet-lnb-coin-list-tbody tr').each(function(idx) {
        $(this).attr('class', '');
    });
    var checked = $("input:checkbox[id='chk_box_possess_coin']").is(":checked");

    var only_possess_seq = 0;
    $('#wallet-lnb-coin-list-tbody tr').each(function() {
        var symbolChosung   = $(this).find('td').eq(0).find('.coinSymbolChosung').eq(0).val();
        var symbolName      = $(this).find('td').eq(2).find('p').eq(0).find('span').eq(1).text();
        var domesticName    = $(this).find('td').eq(1).find('p').eq(0).text();
        var possessQty      = $(this).find('td').eq(2).find('p').eq(0).find('span').eq(0).text();

        if(symbolName.toLowerCase().indexOf(name.toLowerCase()) > -1 || domesticName.toLowerCase().indexOf(name.toLowerCase()) > -1 || symbolChosung.indexOf(Hangul.disassemble(name).join('')) > -1 ) {
            if(checked && parseFloat(possessQty) == 0) {
                $(this).hide();
            } else {
                // 첫번째 TR Click 이벤트를 날린다.
                if(only_possess_seq == 0) $(this).trigger('click');
                $(this).show();
                only_possess_seq ++;
            }
        } else {
            $(this).hide();
        }
    });
}

// LNB Table TR Click 이벤트 처리
/*
$('#wallet-lnb-coin-list-tbody').delegate('tr', 'click', function (event) {
    var coinKind                = $(this).find('td').eq(0).find('.coinKind').eq(0).val();
    var coinNo                  = $(this).find('td').eq(0).find('.coinNo').eq(0).val();
    var coinDomesticName        = $(this).find('td').eq(1).find('p').eq(0).text();
    var coinSymbol              = $(this).find('td').eq(2).find('p').eq(0).find('span').eq(1).text();

    // 이전에 선택한 TR일 경우 아무런 처리없이 skip 한다.
    if(coinNo == __G_Prev_Selected_CoinNo || coinNo == undefined) return false;
    __G_Prev_Selected_CoinNo = coinNo;
    __G_Prev_Selected_CoinKind = coinKind;

    // 다른 TR의 active class를 없애고
    $('#wallet-lnb-coin-list-tbody tr').each(function() {
        $(this).attr('class', '');
    });
    // 현재 TR의 active class를 설정한다.
    $(this).attr('class', 'active');

    if(coinKind == 'MONEY') {       // Money Tab 보이고 Coin Tab 감춘다.
        $('#tab-group-1').show();
        $('#tab-group-2').hide();
    } else {
        $('#tab-group-1').hide();
        $('#tab-group-2').show();
    }

    // 다른 코인이 선택되었으므로 사용자가 입력한 출금수량을 clear한다
    $('#coin-tab-2-target-address').val('');
    $('#coin-tab-2-withdraw-request-qty').val('');
    $('#coin-tab-2-real-withdraw-qty').text('0');

    doProcessRFrame(coinKind, coinNo, coinSymbol, coinDomesticName);
});
*/

// Right Frame 데이터 처리
/*
function doProcessRFrame(coinKind, coinNo, coinSymbol, coinDoemsticName) {
    var selected_tab;
    for(var i = 1; i <= 3; i++) {
        if($('#' + coinKind.toLowerCase() + '-tab-' + i).is(':visible')) selected_tab = i;
    }

    // LNB 선택종류 및 Right Frame의 Tab 종류에 따른 분기 처리
    switch(coinKind) {
        case 'MONEY' :
            switch(selected_tab) {
                case 1 :    // MONEY - 입금신청

                    break;
                case 2 :    // MONEY - 출금신청

                    break;
                case 3 :    // MONEY - 입출금 내역

                    break;
            }
            // LNB Item이 바뀌면 TAB은 항상 1번탭으로...
            $('#money-tab-a-1').trigger('click');
            break;

        case 'COIN' :
            $( ".coin-name-domestic" ).each(function( index ) { $(this).text(coinDoemsticName); });
            $( ".coin-symbolic-name" ).each(function( index ) { $(this).text(coinSymbol); });
            // LNB Item이 바뀌면 TAB은 항상 1번탭으로...
            $('#coin-tab-a-1').trigger('click');
            // 코인 입금주소 정보 보여주기
            getDepositWalletInfo(coinNo);
            break;

        default :
            break;
    }
}
*/
//////////////////////////////////////////////////////////////////////////////
//                           LNB 영역 리스트 처리 END
//////////////////////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////////////////////
//                           Right Frame 영역 처리 START
//////////////////////////////////////////////////////////////////////////////

////////////////////////////////////
// Money 선택시 Tab click event 처리
var __G_Prev_Selected_MoneyTab = 1;
var __G_Prev_Selected_CoinTab = 1;
/*
$('#money-tab-ulxxxxxxxxxxxxxxxxxxxxxxxxxxxx').click(function() {
    // 선택된 Tab 알아오기 (입금주소 : 1, 출금신청: 2, 입출금내역: 3)
    var selected_tab;
    for(var i = 1; i <= 3; i++) {
        if($('#' + __G_Prev_Selected_CoinKind.toLowerCase() + '-tab-' + i).is(':visible')) selected_tab = i;
    }
    if(__G_Prev_Selected_MoneyTab == selected_tab) return false;     // 변경된 Tab 이 없으므로 skip

    __G_Prev_Selected_MoneyTab = selected_tab;

    if(__G_Prev_Selected_MoneyTab == 2) {
    	let user_bank_accnt_number = $('#money-tab-2-sender-account-number').text();
    	if(user_bank_accnt_number == '') {
            lrt.client('계좌등록이 되지않았습니다.', '계좌등록', null, null);
            $('#money-tab-a-1').trigger('click');
            return false;
    	}
        requestMoneyWithdraw(__G_Prev_Selected_CoinNo);
    }
    if(__G_Prev_Selected_MoneyTab == 3) {
        getMondyDWMgrList(__G_Prev_Selected_CoinNo);
    }
});
*/
// Coin 선택시 Tab click event 처리
/*
$('#coin-tab-ulxxxxxxxxxxxxxxxxxxxxxxx').click(function() {
    // 선택된 Tab 알아오기 (입금주소 : 1, 출금신청: 2, 입출금내역: 3)
    var selected_tab;
    for(var i = 1; i <= 3; i++) {
        if($('#' + __G_Prev_Selected_CoinKind.toLowerCase() + '-tab-' + i).is(':visible')) selected_tab = i;
    }
    if(__G_Prev_Selected_CoinTab == selected_tab) return false;     // 변경된 Tab 이 없으므로 skip

    __G_Prev_Selected_CoinTab = selected_tab;

    if(__G_Prev_Selected_CoinTab == 2) {
        getWithdrawApply(__G_Prev_Selected_CoinNo);
    }
    if(__G_Prev_Selected_CoinTab == 3) {
        getDWMgrList(__G_Prev_Selected_CoinNo);
    }
});
*/
////////////////////////////////////

////////////////////////////////////
// COMMON 입출금 결과 PUSH 메시지 리스너 함수 (msgCode = 401)
function __Push_Listener_Wallet_DW_Result(msg) {
    // console.log(msg);
    var msgBody = msg.body;
    var messageTxt;
    var messageTitle;
    if(msgBody.dwReqTypeCd == 2) {             // 출금요청 출금완료 메시지
        messageTxt = '회원님께서 ' + du.getCstmFrmt(msgBody.reqDt, 'yyyyMMddHHmmssSSSSSS', 'yyyy-MM-dd HH:mm:ss') + '에 요청하신 <br>' + msgBody.reqQty + ' (' + __G_CoinMgtRef_Map.get(msgBody.coinNo).COIN_SYMBOLIC_NM + ') 가 출금될 예정입니다.';
        messageTitle = '출금 알림';
    } else if(msgBody.dwReqTypeCd == 1) {      // 입금 메시지
        messageTxt = '회원님께 ' + msgBody.reqQty + ' (' + __G_CoinMgtRef_Map.get(msgBody.coinNo).COIN_SYMBOLIC_NM + ') 가 입금 되었습니다.';
        messageTitle = '입금 알림';
    }

    lrt.client(messageTxt, messageTitle, function() {
        if(__G_Prev_Selected_CoinKind == 'MONEY' && __G_Prev_Selected_MoneyTab == 3) {
            var cur_page = $('#money-tab-3-dw-list-pagination').find('a.active').text();
            getMondyDWMgrList(cur_page, null);
        }
        if(__G_Prev_Selected_CoinKind == 'COIN' && __G_Prev_Selected_CoinTab == 3) {
            var cur_page = $('#coin-tab-3-dw-list-pagination').find('a.active').text();
            getListData(cur_page, null);
        }
    }, null);
}
// COMMON 잔고변경 결과 PUSH 메시지 리스너 함수 (msgCode = 501)
function __Push_Listener_Wallet_Change_Balance(msg) {
    // console.log(msg);
    var msgBody = msg.body;
    var messageTxt;
    var messageTitle;
    if(msgBody.possChgReasCd == 3) {             // 입금 최종 확인으로 인한 잔고변경일때
        messageTxt = '회원님께 ' + du.getCstmFrmt(msg.sndDt, 'yyyyMMddHHmmssSSSSSS', 'yyyy-MM-dd HH:mm:ss') + '에 입금 요청된 <br>' + msgBody.chgQty + ' (' + __G_CoinMgtRef_Map.get(msgBody.coinNo).COIN_SYMBOLIC_NM + ') 가 입금완료 처리 되었습니다.';
        messageTitle = '입금 확인';
        lrt.client(messageTxt, messageTitle, function() {
            if(__G_Prev_Selected_CoinKind == 'MONEY' && __G_Prev_Selected_MoneyTab == 3) {
                var cur_page = $('#money-tab-3-dw-list-pagination').find('a.active').text();
                getMondyDWMgrList(cur_page, null);
            }
            if(__G_Prev_Selected_CoinKind == 'COIN' && __G_Prev_Selected_CoinTab == 3) {
                var cur_page = $('#coin-tab-3-dw-list-pagination').find('a.active').text();
                getListData(cur_page, null);
            }
        }, null);
    }

    // LNB 영역 잔고변경 결과값으로 refresh
    getLnbPossessionInfo();
    __G_LNB_User_Possession_List.forEach(function(item){
        if(parseInt(item.COIN_NO) == parseInt(msgBody.coinNo) ) {
            item.TOTAL_QTY = msgBody.possQty;
        }
    });
}

////////////////////////////////////
// MONEY.T.01 입금신청 탭처리
function getUserBankAccntInfo() {
    $.ajax({
    	url : "/site/wallet/wlt001/selectMemberBankAccntInfo"
      , type : 'POST'
      , dataType : 'json'
      , error : function(data,status,error) {
          	//console.log("에러 >> " + JSON.stringify(data));
            $('#money-tab-1-insert-new-bank-account-div').show();
            $('#money-tab-1-request-new-deposit-div').hide();
          }
      , success : function(data, textStatus) {
            //console.log(data);
            if(getParam(data, 'REC_SEQ_NO', '') != '') {
                $('#money-tab-1-insert-new-bank-account-div').hide();
                $('#money-tab-1-request-new-deposit-div').show();

                // 입금신청 Tab 정보 setting
                $('#money-tab-1-sender-account-number').text(data.BANK_ACCNT_NO);
                $('#money-tab-1-sender-bank-name').text( _G_Bank_Code_Map.get(data.BANK_CD) );
                $('#money-tab-1-sender-bank-code').val(data.BANK_CD );
                $('#money-tab-1-sender-account-holder-name').text(data.ACCNT_HOLDER_NM);
                // 입금신청 Popup 정보 setting
                $('#popup-deposit-code-sender-account-number').text(data.BANK_ACCNT_NO);
                $('#popup-deposit-code-sender-bank-name').text( _G_Bank_Code_Map.get(data.BANK_CD) );
                $('#popup-deposit-code-sender-account-holder-name').text(data.ACCNT_HOLDER_NM);

                // 출금신청 Tab 정보 setting
                $('#money-tab-2-sender-account-number').text(data.BANK_ACCNT_NO);
                $('#money-tab-2-sender-bank-name').text( _G_Bank_Code_Map.get(data.BANK_CD) );
                $('#money-tab-2-sender-account-holder-name').text(data.ACCNT_HOLDER_NM);
            } else {
                $('#money-tab-1-insert-new-bank-account-div').show();
                $('#money-tab-1-request-new-deposit-div').hide();
            }
        }
    });
}

////////////////////////////////////
// MONEY.T.01-1 입금신청 탭 >> 계좌인증 버튼 클릭 이벤트 처리
function insertUserBankAccntInfo() {
    var bankAccntHolderName = $('#money-tab-1-bank-account-holder-name').val();
    var bankAccntHolderBirthDay = $('#money-tab-1-bank-account-holder-birthday').val();
    var bankCode = $('#money-tab-1-bank-list').val();
    var bankAccountNumber = $('#money-tab-1-bank-account-number').val();

    if(bankAccntHolderName == '') {
        lrt.client('계좌의 명의자 이름을 입력하세요.', '오류', function() {
            $('#money-tab-1-bank-account-holder-name').focus();
        }, null);
        return false;
    }
    if(bankAccntHolderBirthDay == '' || bankAccntHolderBirthDay.length != 6) {
        lrt.client('생년월일 6자리를 정확히 입력하세요.', '오류', function() {
            $('#money-tab-1-bank-account-holder-birthday').focus();
        }, null);
        return false;
    }
    if(bankCode == 9999) {
        lrt.client('은행을 선택하세요.', '오류', function() {
            $('#money-tab-1-bank-list').focus();
        }, null);
        return false;
    }
    if(bankAccountNumber == '') {
        lrt.client('은행계좌번호를 입력하세요.', '오류', function() {
            $('#money-tab-1-bank-account-number').focus();
        }, null);
        return false;
    }

    var paramMap = {
        bankCd : bankCode
      , bankAccntNo : bankAccountNumber.replace(/-/gi, '').replace(/\./gi, '')
      , bankAccntHldrNm : bankAccntHolderName
      , bankAccntHldrBthDay : bankAccntHolderBirthDay
    };
    lrt.confirm('계좌인증을 하시겠습니까?', '계좌인증', doProcessInsertUserBankAccntInfo, paramMap);
}
function doProcessInsertUserBankAccntInfo(paramMap) {
    $.ajax({
          url : "/site/wallet/wlt001/insertMemberBankAccntInfo"
        , type : 'POST'
        , data : paramMap
        , dataType : 'json'
        , error : function(data,status,error) {
            console.log("에러 >> " + JSON.stringify(data));
        }
        , success : function(data, textStatus) {
            //console.log(data);
        	if(data.rtnCd != 0) {lrt.client(wordbook[data.rtnCd], '');}
        	if(data.rtnCd == 0) {
                lrt.client('은행계좌가 등록되었습니다.', '계좌등록', function() {
                    getUserBankAccntInfo();
                }, null);
            }
        }
    });
}
////////////////////////////////////
// MONEY.T.01-2 코입 입금주소 탭 >> 입금계좌 충전요청 버튼 클릭이벤트 처리
$("#moneyChargingRequest").on("click", function(){
    var chargeAmountRequested = $("#money-tab-1-request-amount").val().replace(/,/g, '');
    var chk = /^(?:-?\d+|-?\d{1,3}(?:,\d{3})+)?(?:\.\d+)?$/;
    var result = true;

    if(__G_User_Auth_Level < 3) {
    	lrt.client('보안 등급이 레벨 3 이상일 경우 이용 가능합니다', '입금신청', function() {
            location.href = '/site/info?tab=1';
        }, null);
        return false;
    }

    if (  parseInt(chargeAmountRequested) < 1 || chargeAmountRequested.length < 1 ) {
        lrt.client('입금 신청 금액을 입력하세요.', '신청금액', function() {
            $("#money-tab-1-request-amount").focus();
        }, null);
        return false;
    }

    if(!chk.test(chargeAmountRequested)) {
        lrt.client('오직 숫자만 입력 가능합니다.', '신청금액', function() {
            $("#money-tab-1-request-amount").val('');
            $("#money-tab-1-request-amount").focus();
        }, null);
        return;
    }

    let minDepositQty = __G_CoinMgtRef_Map.get(10).MIN_DEPOSIT_QTY;
    if(parseFloat(chargeAmountRequested) < minDepositQty) {
    	lrt.client('최소 입금 금액은 ' + nu.cm(minDepositQty) + '원 입니다.', '신청금액', function() {
            $("#money-tab-1-request-amount").focus();
        }, null);
        return;
    }

    $('#agreeChk').find('input').each(function() {
        if( !$(this).is(':checked') ) {
            lrt.client('모든 사항에 동의가 필요합니다.', '동의 요청', null, null);
            result = false;
            return false;
        }
    });

    if(result) {
        lrt.confirm('충전 요청을 하시겠습니까?', '충전요청', function() {
            $('.deposit-code').show();
            $('#popup-deposit-code-sender-deposit-code').text(randomString(6));
            $('#popup-deposit-code-sender-amount').text(nu.cm(parseFloat(chargeAmountRequested), 0) + ' ' + __G_CoinMgtRef_Map.get(__G_Default_Coin_Type).COIN_SYMBOLIC_NM);
        }, null);
    }
});
////////////////////////////////////
// MONEY.T.01-3 Money 입금주소 탭 >> Coin Address 복사하기 버튼 클릭 처리
function copyToClipBoardDepositCode() {
    su.copy($('#popup-deposit-code-sender-deposit-code').text());
    lrt.client('입금코드가 클립보드로 복사되었습니다.', '입금코드 복사 성공', null, null);
}
////////////////////////////////////
// MONEY.T.01-4 Money 입금신청 탭 >> 사용자의 충전요청 최종 처리함수
function moneyChargingRequest() {
    $.ajax({
        url : "/site/wallet/wlt001/requestMoneyDeposit"
        , type : 'POST'
        , data : {
            finCd: $('#popup-deposit-code-sender-deposit-code').text(),
            bankCd: $('#money-tab-1-sender-bank-code').val(),
            accntNo: $('#popup-deposit-code-sender-account-number').text(),
            holderNm: $('#popup-deposit-code-sender-account-holder-name').text(),
            reqAmt: $('#money-tab-1-request-amount').val().replace(/,/gi,'').replace(/\./gi,''),
        }
        , dataType : 'json'
        , error : function(data,status,error) {
          	console.log("에러 >> " + JSON.stringify(data));
            lrt.client('입금신청이 실패하였습니다.<br>관리자에 문의 바랍니다.', '입금신청 실패', function() {
                $('.deposit-code').hide();
            }, null);
        }
        , success : function(data, textStatus) {
            // console.log(data);
        	if(data.rtnCd != 0) {lrt.client(wordbook[data.rtnCd], '');}
            if(data.rtnCd == 0) {
                lrt.client('입금신청이 전송되었습니다.', '입금신청 성공', function() {
                    $('.deposit-code').hide();
                    initSetting();
                }, null);
            }
        }
    });
}
////////////////////////////////////
// MONEY.T.01-5 Money 입금신청 탭 >> 충전요청 초기화
function initSetting() {
	$('#money-tab-1-request-amount').val('');
	$('.agree-box').find('input[type=checkbox]').prop('checked', false);
}
// 랜덤코드 생성함수
function randomString(length) {
    var chars = "ABCDEFGHIJKLMNOPQRSTUVWXTZ";
    var randomstring = '';
    for (var i=0; i<length; i++) {
    var rnum = Math.floor(Math.random() * chars.length);
        randomstring += chars.substring(rnum,rnum+1);
    }
    return randomstring;
}

////////////////////////////////////
//MONEY.T.01-6 Money 입금신청 탭 >> 입금금액 입력시 숫자 처리 이벤트 함수
$('#money-tab-1-request-amount').on('keydown', function(e) {
	evntKeyDown(e, this, 0);
});

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

////////////////////////////////////
// MONEY.T.02 원화 출금신청 탭
// 사용자가 인증번호 요청을 하였는지 여부 처리
var __G_Bool_Money_Auth_Request = false;
var __G_Money_SMS_Auth_Request_btn_timer = 0;
/*
function presetMoneyWithdraw(coinNo) {
    // 인증레벨 셋팅
    $('.coin-user-level').text(__G_User_Auth_Level);
    // Money symbol setting
    $('.money-symbolic-name').text(__G_CoinMgtRef_Map.get(__G_Default_Coin_Type).COIN_SYMBOLIC_NM );

    // 인증수단에 따른 인증방법 보이기 셋팅 (1: OTP, 2: SMS, 3: Email, 0: 사용안함)
    // 인증수단 미설정이면 최대, 출금신청, 취소 버튼 막기
    $('#money-tab-2-max-qty-btn').prop("disabled", false);
    $('#money-tab-2-request-btn').prop("disabled", false);
    $('#money-tab-2-cancel-btn').prop("disabled", false);
    if(__G_User_Auth_Mean_Code == 1) {
        $('#money-tab-2-auth-sms-tr').hide();
        $('#money-tab-2-auth-otp-tr').show();
    } else if(__G_User_Auth_Mean_Code == 2) {
        $('#money-tab-2-auth-sms-tr').show();
        $('#money-tab-2-auth-otp-tr').hide();
    } else {
        $('#money-tab-2-auth-sms-tr').hide();
        $('#money-tab-2-auth-otp-tr').hide();
        $('#money-tab-2-max-qty-btn').prop("disabled", true);
        $('#money-tab-2-request-btn').prop("disabled", true);
        $('#money-tab-2-cancel-btn').prop("disabled", true);
    }
    // 최소출금수량 셋팅
    var minWtdQty = __G_CoinMgtRef_Map.get(__G_Default_Coin_Type).MIN_WTDRW_QTY;
    $('#money-tab-2-min-withdraw-qty').text(nu.cm(minWtdQty));
    // 출금수수료 셋팅
    var wtdFee = __G_CoinMgtRef_Map.get(__G_Default_Coin_Type).WTDRW_FEE;
    $('#money-tab-2-withdraw-fee').text(nu.cm(wtdFee));
    // 출금가능수량 셋팅
    getMoneyWithDrawQty(__G_Default_Coin_Type);
}
*/

////////////////////////////////////
// MONEY.T.02-1 원화 출금신청 탭 >> 출금가능수량, 1일출금한도, 1일잔여한도 가져오기
function getMoneyWithDrawQty(coinNo) {
    $.ajax({
    	url : "/site/wallet/wlt001/checkWithdrawQty"
      , data : {
    	      coinNo : coinNo
    	    , reqQty : __G_CoinMgtRef_Map.get(coinNo).MIN_WTDRW_QTY
	   }
      , type : 'POST'
      , dataType : 'json'
      , error : function(data,status,error) {
          	//console.log("에러 >> " + JSON.stringify(data));
            // $('#money-tab-1-create-new-address').show();
            // $('#money-tab-1-user-wallet-address-info').hide();
        }
      , success : function(data, textStatus) {
            //console.log(data);
            var withdrawPossQty     = data.V_WTDRW_POSS_QTY;
            var dailyLimitMaxQty    = data.V_DAILIY_LIMIT_MAX_QTY;
            var dailyLimitLeftQty   = data.V_DAILIY_LIMIT_LEFT_QTY;
            var montylyLimitLeftQty = data.V_MONTHLY_LIMIT_LEFT_QTY;
            $('#money-tab-2-user-poss-qty').text(nu.cm(withdrawPossQty, 0));
         //   $('#money-tab-2-daily-limit-qty').text(nu.cm(dailyLimitMaxQty, 0));
            $('#money-tab-2-daily-limit-left-qty').text(nu.cm(dailyLimitLeftQty, 0));
        }
    });

    $.ajax({
    	url : "/site/wallet/wlt001/checkOnceWthrwQty"
      , data : {
    	      coinNo : coinNo
	   }
      , type : 'POST'
      , dataType : 'json'
      , error : function(data,status,error) {
          	//console.log("에러 >> " + JSON.stringify(data));
        }
      , success : function(data, textStatus) {
            //console.log(data);
            var maxOnceWthrwQty = getParam(data, 'MAX_ONCE_WTHRW_QTY');
            $('#money-tab-2-once-wthrw-qty').text(nu.cm(maxOnceWthrwQty, 0));
        }
    });
}

////////////////////////////////////
// MONEY.T.02-2 원화 출금신청 탭 >> 인증번호 요청 버튼 이벤트 처리
function requestMoneySmsAuthNumber() {
	var userPossessQty = 0, userDailyLeftQty = 0;
	try {
    	userPossessQty	= parseFloat($('#money-tab-2-user-poss-qty').text().replace(/,/g, ''));
    	userDailyLeftQty= parseFloat($('#money-tab-2-daily-limit-left-qty').text().replace(/,/g, ''));
	} catch(err) {};

    var reqQty          = $('#money-tab-2-withdraw-request-qty').val();
    var targetAddr      = $('#money-tab-2-sender-account-number').text();
    var targetAddrEtc1  = $('#money-tab-1-sender-bank-code').val();
    var targetAddrEtc2  = $('#money-tab-2-sender-account-holder-name').text();

    var paramJson = {
          coinNo            : __G_Default_Coin_Type
        , reqQty            : reqQty == '' ? '' : reqQty.replace(/,/gi, '')
        , targetAddr        : targetAddr.trim()
        , targetAddrEtc1    : targetAddrEtc1.trim()
        , targetAddrEtc2    : targetAddrEtc2.trim()
        , reqMemo           : ''
    }

    if(userPossessQty == 0 || userDailyLeftQty == 0) {
        lrt.client('출금가능 잔고가 부족합니다.', '잔고 부족', null, null);
        return false;
    }
    if(reqQty == '' || parseFloat(reqQty) == 0) {
        lrt.client('요청수량을 넣으세요.', '요청수량 오류', function() {
            $('#money-tab-2-withdraw-request-qty').focus();
        }, null);
        return false;
    }

    // SMS 인증 요청 프로세스 진행
    doMoneySmsAuthRequest(paramJson);
}
// SMS 인증요청 프로세스 실행
function doMoneySmsAuthRequest(paramJson) {
    $.ajax({
        url : "/site/wallet/wlt001/requestSmsAuthNumber"
        , data : paramJson
        , type : 'POST'
        , dataType : 'json'
        , error : function(data,status,error) {
            //console.log("에러 >> " + JSON.stringify(data));
            // $('#coin-tab-1-create-new-address').show();
            // $('#coin-tab-1-user-wallet-address-info').hide();
        }
        , success : function(data, textStatus) {
            // console.log(data);
            if(data.rtnCd == 0) {
                // message box
                lrt.client('회원님의 등록하신 휴대폰으로 인증번호가 발송되었습니다.<br>3분 이내로 인증절차를 진행해 주세요.', 'SMS인증요청', null, null);
                __G_Bool_Money_Auth_Request = true;
                // 인증번호 요청 Timer Button 설정
                // button 비활성화시킴
                $('#money-tab-2-request-sms-auth-number-btn').prop("disabled", true);
                var timeout = 180;
                __G_Money_SMS_Auth_Request_btn_timer = setInterval(function() {
                    minutes = parseInt(timeout / 60 % 60, 10);
                    seconds = parseInt(timeout % 60, 10);

                    minutes = minutes < 10 ? "0" + minutes : minutes;
                    seconds = seconds < 10 ? "0" + seconds : seconds;
                    $('#money-tab-2-request-sms-auth-number-btn').text(minutes + ' : ' + seconds);

                    if (--timeout < 0) {
                        timeout = 0;
                        clearInterval(__G_Money_SMS_Auth_Request_btn_timer);
                        $('#money-tab-2-request-sms-auth-number-btn').text('인증번호 요청');
                        // button 활성화시킴
                        $('#money-tab-2-request-sms-auth-number-btn').prop("disabled", false);
                    }
                }, 1000);
            } else {
                lrt.client(wordbook[data.rtnCd], 'SMS인증요청 오류', function() {
                    // button 활성화시킴
                    $('#money-tab-2-request-sms-auth-number-btn').prop("disabled", false);
                }, null);
            }
        }
    });
}

////////////////////////////////////
// MONEY.T.02-3 원화 출금신청 탭 >> 최대 출금 버튼 이벤트 처리
function setMaxMoneyWithdrawQty() {
    var qty = getMondyMaxReqQty();
    var maxQty = qty.split('|')[0];
    var realQty = qty.split('|')[1];
    $('#money-tab-2-withdraw-request-qty').val(maxQty);
    $('#money-tab-2-real-withdraw-qty').text(realQty);
}

////////////////////////////////////
// MONEY.T.02-4 원화 출금신청 탭 >> 요청수량 keyup 이벤트 처리
$('#money-tab-2-withdraw-request-qty').keydown(function(event) {
	evntKeyDown(event, this, 0);
    checkWithdrawMaxMoney();
});

function checkWithdrawMaxMoney() {
    var user_input = $('#money-tab-2-withdraw-request-qty').val().replace(/,/g,'');
    if(user_input == '') {
        $('#money-tab-2-real-withdraw-qty').text('0');
        return false;
    }

    var user_input_qty = parseFloat(user_input);
    var qty = getMondyMaxReqQty();
    var maxQty  = parseFloat(qty.split('|')[0]);
    var realQty = parseFloat(qty.split('|')[1]);
    console.log("checkWithdrawMaxMoney:", user_input_qty, ", maxqty:", maxQty);
    if(user_input_qty > maxQty) {
        return false;
    } else if(user_input_qty == 0) {
        return false;
    } else {
        return true;
    }
};

////////////////////////////////////
// MONEY.T.02-4-1 원화 출금신청 탭 >> 최대 요청수량 계산 함수
function getMondyMaxReqQty() {
    var available_qty   = parseFloat($('#money-tab-2-user-poss-qty').text().replace(/,/gi,''));
    // var daily_max_qty   = parseFloat($('#money-tab-2-daily-limit-qty').text().replace(/,/gi,''));
    var max_once_wthrw_qty   = parseFloat($('#money-tab-2-once-wthrw-qty').text().replace(/,/gi,''));
    var daily_left_qty  = parseFloat($('#money-tab-2-daily-limit-left-qty').text().replace(/,/gi,'')); //
    var min_wd_qty      = parseFloat($('#money-tab-2-min-withdraw-qty').text().replace(/,/gi,''));
    var withdraw_fee    = parseFloat($('#money-tab-2-withdraw-fee').text().replace(/,/gi,''));
    var max_withdraw_qty = ((available_qty - withdraw_fee) > (daily_left_qty)) ? (daily_left_qty) : (available_qty - withdraw_fee);
    max_withdraw_qty = ((max_withdraw_qty) > (max_once_wthrw_qty)) ? (max_once_wthrw_qty) : (max_withdraw_qty);
    max_withdraw_qty = (max_withdraw_qty >= withdraw_fee) ? max_withdraw_qty : 0;
    max_withdraw_qty = (max_withdraw_qty >= min_wd_qty) ? max_withdraw_qty : 0;

    if(max_withdraw_qty != 0){
    	max_withdraw_qty = ((max_withdraw_qty) > (max_once_wthrw_qty)) ? (max_once_wthrw_qty) : (max_withdraw_qty);
    }

    var real_withdraw_qty = (available_qty > 0) ? (max_withdraw_qty + withdraw_fee) : 0;
    return nu.js(max_withdraw_qty, 8) + '|' + nu.js(real_withdraw_qty, 8);
}

////////////////////////////////////
// MONEY.T.02-5 원화 출금신청 탭 >> 출금신청 버튼 이벤트 처리
function requestMoneyWithDraw({reqQty, authOtpCode, authSmsCode, posses, dailleft, targetAddr, targetAddrEtc1, targetAddrEtc2}) {
    //var authSmsCode = $('#money-tab-2-sms-auth-number').val();
    //var authOtpCode = $('#money-tab-2-otp-auth-number').val();

    console.log("requestMoneyWithDraw===>", reqQty);
    var paramJson = {};
    if(__G_User_Auth_Mean_Code == 2) {
        paramJson = {
            authCode : authSmsCode
        }
    // OTP 인증일때는 모든항목 체크
    } else if(__G_User_Auth_Mean_Code == 1) {
    	var userPossessQty = 0, userDailyLeftQty = 0;
    	try {
	    	userPossessQty	= parseFloat(posses.replace(/,/g, ''));
	    	userDailyLeftQty= parseFloat(dailleft.replace(/,/g, ''));
    	} catch(err) {};

        //var reqQty          = $('#money-tab-2-withdraw-request-qty').val();
        //var targetAddr      = $('#money-tab-2-sender-account-number').text();
        //var targetAddrEtc1  = $('#money-tab-1-sender-bank-code').val();
        //var targetAddrEtc2  = $('#money-tab-2-sender-account-holder-name').text();

        paramJson = {
              coinNo            : __G_Default_Coin_Type
            , authCode          : authOtpCode
            , reqQty            : reqQty == '' ? '' : reqQty.replace(/,/gi, '')
            , targetAddr        : targetAddr.trim()
            , targetAddrEtc1    : targetAddrEtc1.trim()
            , targetAddrEtc2    : targetAddrEtc2.trim()
            , reqMemo           : ''
        };

        if(userPossessQty == 0 || userDailyLeftQty == 0) {
            lrt.client('출금가능 잔고가 부족합니다.', '잔고 부족', null, null);
            return false;
        }
        if(reqQty == '' || parseFloat(reqQty) == 0) {
        	lrt.client('요청수량을 넣으세요.', '요청수량 오류', function() {
        		$('#coin-tab-2-withdraw-request-qty').focus();
        	}, null);
        	return false;
        }
    }

    if(__G_User_Auth_Mean_Code == 2 && __G_Bool_Money_Auth_Request == false) {
        lrt.client('인증요청을 하지 않으셨습니다.', '인증 오류', function() {
            $('#money-tab-2-sms-auth-number').focus();
        }, null);
        return false;
    }
    if(__G_User_Auth_Mean_Code == 2 && authSmsCode == '') {
        lrt.client('인증번호를 넣으세요.', '인증번호 오류', function() {
            $('#money-tab-2-sms-auth-number').focus();
        }, null);
        return false;
    }
    if(__G_User_Auth_Mean_Code == 1 && authOtpCode == '') {
        lrt.client('인증번호를 넣으세요.', '인증번호 오류', function() {
            $('#money-tab-2-otp-auth-number').focus();
        }, null);
        return false;
    }

    doRequestMoneyWithdraw(paramJson);
}
// 출금요청 프로세스 실행
function doRequestMoneyWithdraw(paramJson) {
    $.ajax({
        url : "/site/wallet/wlt001/doRequestWithDraw"
        , data : paramJson
        , type : 'POST'
        , dataType : 'json'
        , error : function(data,status,error) {
            //console.log("에러 >> " + JSON.stringify(data));
            // $('#coin-tab-1-create-new-address').show();
            // $('#coin-tab-1-user-wallet-address-info').hide();
        }
        , success : function(data, textStatus) {
            // console.log(data);
            if(data.rtnCd == 0) {
                // message box
                lrt.client('정상적으로 출금신청이 요청되었습니다.', '출금신청', function() {
                    window.withdrawCurrency.clearValues();
                    window.withdrawCurrency.updateInfo();
                    } , null);
                //window.withdrawCurrency.clearValues();
                //cancelRequestMondyWithdraw();                // 출금신청 화면 초기화
                //$('#money-tab-a-3').trigger('click');    // 입출금내역 화면으로 전환
            } else {
                lrt.client(wordbook[data.rtnCd], '출금신청 오류', function() {
                    // button 활성화시킴
                    $('#money-tab-2-request-sms-auth-number-btn').prop("disabled", false);
                }, null);
            }
        }
    });
}

////////////////////////////////////
// MONEY.T.02-5-1 원화 출금신청 탭 >> 취소 버튼 이벤트 처리
function cancelRequestMondyWithdraw() {
    $('#money-tab-2-withdraw-request-qty').val('');
    $('#money-tab-2-real-withdraw-qty').text('0');
    $('#money-tab-2-sms-auth-number').val('');
    $('#money-tab-2-otp-auth-number').val('');
    $('#money-tab-2-request-sms-auth-number-btn').text('인증번호 요청');
    $('#money-tab-2-request-sms-auth-number-btn').prop("disabled", false);
    if(__G_Money_SMS_Auth_Request_btn_timer > 0) clearInterval(__G_Money_SMS_Auth_Request_btn_timer); // 인증요청 버튼 timer 기동중일때 timer 중지
}

////////////////////////////////////
// MONEY.T.03 원화 입출금내역 탭
var __G_Money_DW_List_window;
var __G_Money_DW_List_Item;
function getMondyDWMgrList(coinNo) {
    // Paging
    __G_Money_DW_List_window = Pu.init({
        count: 10,
        PageCount: 5,
        draw: getMoneyDwListData,
        button: '#money-tab-3-dw-list-btn',
        paginate: '#money-tab-3-dw-list-pagination',
        totalText: '#money-tab-3-dw-list-total-count',
        initCllBck: true,
        type: 'search',
        initEventFun: initMoneyDwListData
    });
}

function initMoneyDwListData(page, callback) {
	var p = {};

	p['coinNo'] = __G_Default_Coin_Type;
	p['dwReqTypeCd'] = $("#money-tab-3-select-dw-req-type").val();
	p['startDate'] = $("#money-tab-3-dw-list-startDate").val();
	p['endDate'] = $("#money-tab-3-dw-list-endDate").val();
	p['pageNum'] = page;

    ajaxOption({
        url: '/site/wallet/wlt001/selectCoinBalanceHistListCount',
        data: p,
        success: callback,
        //done: done
    });
}
function getMoneyDwListData(i, done) {
	var p = {};

	p['coinNo'] = __G_Default_Coin_Type;
	p['dwReqTypeCd'] = $("#money-tab-3-select-dw-req-type").val();
	p['startDate'] = $("#money-tab-3-dw-list-startDate").val();
	p['endDate'] = $("#money-tab-3-dw-list-endDate").val();
	p['pageNum'] = i;

    ajaxOption({
        url: '/site/wallet/wlt001/selectCoinBalanceHistList',
        data: p,
        success: drawMondyDwList,
        done: done
    });
}

////////////////////////////////////
// MONEY.T.03-1 원화 입출금내역 >> 입출금 리스트 그려주는 메서드
function drawMondyDwList(moneyDWListData) {
	var moneyDWListJson = moneyDWListData;
	var moneyDWListTemplate = document.getElementById("template-wallet-money-deposit-withdraw-list");
	var moneyDWListTemplateHtml = moneyDWListTemplate.innerHTML;
	var moneyDWListHtml = "";
    __G_Money_DW_List_Item = moneyDWListData;       // 전역변수에 저장

    if(moneyDWListJson.length > 0) {
        $(moneyDWListJson).each(function(data_item) {
            var value				        = moneyDWListJson[data_item];
            var reqSeqNo                    = getParam(value, 'REQ_SEQ_NO');
            var reqTypeCd                   = '' + getParam(value, 'DW_REQ_TYPE_CD', '1');
            var reqDateTime                 = du.getCstmFrmt(getParam(value, 'REQ_STAT_PROC_DT'), 'yyyyMMddHHmmssSSSSSS', 'yyyy-MM-dd HH:mm:ss');

            var reqQty                      = getParam(value, 'REQ_QTY', '');
            var fee                         = (reqTypeCd == '3') ? '0' : nu.js(parseFloat(getParam(value, 'WTDRW_FEE', '0')), 6); // 입금 : 0, 출금 : WTDRW_FEE
            var finCode                     = getParam(value, 'PIN_CD', '');
            var address                     = getParam(value, 'TARGET_ADDR');
            var bankCode                    = getParam(value, 'TARGET_ADDR_ETC1');
            var bankName                    = _G_Bank_Code_Map.get(bankCode);
            var reqStatProcCd               = getParam(value, 'REQ_STAT_PROC_CD');
            var approvalStatCd              = getParam(value, 'APPROVAL_STAT_CD');

            var gubunText                   = (reqTypeCd == '3') ? '입금' : '출금';
            var gubunColor                  = (reqTypeCd == '3') ? 'red' : 'blue';

            var procStatCd                  = getParam(value, 'DW_PROC_STAT_CD');
            var procStatTxt = '';

            let typeText = reqTypeCd == '1' ? '입금':reqTypeCd == '3' ? '입금':'출금';

            switch(reqStatProcCd) {
                case 1: procStatTxt = '입금 요청'; break;
                case 2: procStatTxt = '컨펌 확인중'; break;
                case 3: procStatTxt = '출금 인증 대기중'; break;
                case 4:
                	if(approvalStatCd == 0) {
                		procStatTxt = typeText + '승인 대기중';
                	} else if(approvalStatCd == 1) {
                		procStatTxt = typeText + ' 처리 대기중';
                	}
                	break;
                case 5: procStatTxt = '요청 취소'; break;
            }
            if(reqStatProcCd == 4 && procStatCd == 1) {
                procStatTxt = '처리 완료';
            }
            if(approvalStatCd == -2) {
            	procStatTxt = '환급 완료';
            }
            if(approvalStatCd == -1) {
            	procStatTxt = '승인 취소';
            }

            var cancelButtonHTML            = '';
            // 원화 출금이면서 출금 인증 대기중인 것만 취소버튼 보이도록 처리함
            if(reqTypeCd == '4' && (reqStatProcCd == '3' || reqStatProcCd == '4') && approvalStatCd == 0) cancelButtonHTML = '<button onClick="cancelRequest(' + "'" + reqSeqNo + "'" + ')">취소</button>';

            moneyDWListHtml += moneyDWListTemplateHtml.replace(/{{DwGubun}}/g, gubunText)
                                    .replace(/{{ReqSeqNo}}/g, reqSeqNo)
                                    .replace(/{{DwGubunColor}}/g, gubunColor)
                                    .replace(/{{ReqDateTime}}/g, reqDateTime)
                                    .replace(/{{Address}}/g, address)
                                    .replace(/{{ReqQty}}/g, nu.cm(reqQty, 0))
                                    .replace(/{{Fee}}/g, fee)
                                    .replace(/{{ReqStatProcText}}/g, procStatTxt)
                                    .replace(/{{FinCode}}/g, finCode)
                                    .replace(/{{BankName}}/g, bankName)
                                    .replace(/{{CancelButtonHTML}}/g, cancelButtonHTML)
                                    ;
        });
        $('#money-tab-3-dw-list-tbody').html(moneyDWListHtml);
    } else
        $('#money-tab-3-dw-list-tbody').html($('#template-dw-list-empty').html().replace(/{{ColSpan}}/g, 9));
}

////////////////////////////////////
// COIN.T.01 코입 입금주소 탭처리
/*
function getDepositWalletInfo(coinNo) {
    $.ajax({
    	url : "/site/wallet/wlt001/selectCoinDepositWalletInfoByUserIdAndCoinNo"
      , data : {
    	    coinNo : coinNo
	   }
      , type : 'POST'
      , dataType : 'json'
      , error : function(data,status,error) {
          	//console.log("에러 >> " + JSON.stringify(data));
            $('#coin-tab-1-create-new-address').show();
            $('#coin-tab-1-user-wallet-address-info').hide();
          }
      , success : function(data, textStatus) {
            // console.log(data);
            $('#coin-tab-1-create-new-address').hide();
            $('#coin-tab-1-user-wallet-address-info').show();

            var address = getParam(data, 'DEPOSIT_WALLET_ADDR');
            $('#coin-tab-1-user-wallet-address').text(address);
            showQRCode(address);

            // 추가 입금주소 여부
            var extra_addr_cnt = getParam(data, 'COIN_DEPOSIT_ADDR_CNT');
            if(extra_addr_cnt > 1) {
                $('#coin-tab-1-user-wallet-address-extra1').show();
                $('#coin-tab-1-user-wallet-address-extra1-name').text(getParam(data, 'ADDR_ETC1_NM'));
                $('#coin-tab-1-user-wallet-address-extra1-address').text(getParam(data, 'ADDR_ETC1'));
            } else $('#coin-tab-1-user-wallet-address-extra1').hide();
        }
    });
}
*/

////////////////////////////////////
// COIN.T.01-1 코입 입금주소 탭 >> 신규 Coin 주소 생성 버튼 클릭 이벤트 처리
$('#coin-tab-1-user-wallet-address-create').click(function() {
    $.ajax({
    	url : "/site/wallet/wlt001/createNewCoinAddress"
      , data : {
    	      coinNo : __G_Prev_Selected_CoinNo
    	    , coinSymbolicNm : getCoinSymbolByCoinNo(__G_Prev_Selected_CoinNo)
	   }
      , type : 'POST'
      , dataType : 'json'
      , error : function(data,status,error) {
          	//console.log("에러 >> " + JSON.stringify(data));
            // $('#coin-tab-1-create-new-address').show();
            // $('#coin-tab-1-user-wallet-address-info').hide();
        }
      , success : function(data, textStatus) {
            // console.log(data);
            var addrCnt = __G_CoinMgtRef_Map.get(__G_Prev_Selected_CoinNo).COIN_DEPOSIT_ADDR_CNT;
            $('#coin-tab-1-create-new-address').hide();
            $('#coin-tab-1-user-wallet-address-info').show();
            var address = getParam(JSON.parse(data.body), 'newCoinAccount');
            $('#coin-tab-1-user-wallet-address').text(address);
            showQRCode(address);

            if(addrCnt > 1) {
                $('#coin-tab-1-user-wallet-address-extra1').show();
                $('#coin-tab-1-user-wallet-address-extra1-name').text(__G_CoinMgtRef_Map.get(__G_Prev_Selected_CoinNo).ADDR_ETC1_NM);
                $('#coin-tab-1-user-wallet-address-extra1-address').text(getParam(JSON.parse(data.body), 'newCoinAccount1'));
            } else $('#coin-tab-1-user-wallet-address-extra1').hide();
        }
    });
});
////////////////////////////////////
// COIN.T.01-2 코입 입금주소 탭 >> Coin Address 복사하기 버튼 클릭 처리
function copyToClipBoardAddress() {
    su.copy($('#coin-tab-1-user-wallet-address').text());
    lrt.client('암호화폐 주소가 클립보드로 복사되었습니다.', '주소 복사 성공', null, null);
}
function copyToClipBoardAddress1(address) {
    su.copy(address);
    lrt.client('암호화폐 주소가 클립보드로 복사되었습니다.', '주소 복사 성공', null, null);
}
////////////////////////////////////
// COIN.T.01-3 코입 입금주소 탭 >> 코인주소 QRCode 보여주기
function showQRCode(address) {
    $('#coin-tab-1-user-wallet-address-qrcode').empty();
    var qrcode = new QRCode(document.getElementById("coin-tab-1-user-wallet-address-qrcode"), {
        text: address,
        width: 120,
        height: 120,
        colorDark : "#000000",
        colorLight : "#ffffff",
        correctLevel : QRCode.CorrectLevel.H
    });
}

////////////////////////////////////
// COIN.T.02 코인 출금신청 탭 처리
// 사용자가 인증번호 요청을 하였는지 여부 처리
var __G_Bool_Auth_Request = false;
var __G_SMS_Auth_Request_btn_timer = 0;

function getWithdrawApply(coinNo) {
    // 탭관련된 input 창 초기화 실행 (타이머 초기화포함)
    cancelRequestWithdraw();

    // 추가주소 갯수에 따른 보여주기 셋팅
    var wtdAddrCnt = parseInt(__G_CoinMgtRef_Map.get(coinNo).COIN_WTDRW_ADDR_CNT);
    if(wtdAddrCnt == 1) {
        $('#coin-tab-2-etc-address-1').hide();
        $('#coin-tab-2-etc-address-2').hide();
    } else if(wtdAddrCnt == 2) {
        $('#coin-tab-2-etc-address-1').show();
        var etcAddrName1 = __G_CoinMgtRef_Map.get(coinNo).ADDR_ETC1_NM;
        $('#coin-tab-2-etc-address-1-name').text(etcAddrName1);
        $('#coin-tab-2-etc-address-2').hide();
    } else {
        $('#coin-tab-2-etc-address-1').show();
        $('#coin-tab-2-etc-address-2').show();
        var etcAddrName1 = __G_CoinMgtRef_Map.get(coinNo).ADDR_ETC1_NM;
        $('#coin-tab-2-etc-address-1-name').text(etcAddrName1);
        var etcAddrName2 = __G_CoinMgtRef_Map.get(coinNo).ADDR_ETC2_NM;
        $('#coin-tab-2-etc-address-1-name').text(etcAddrName2);
    }

    // 인증레벨 셋팅
    $('.coin-user-level').text(__G_User_Auth_Level);
    // 인증수단에 따른 인증방법 보이기 셋팅 (1: OTP, 2: SMS, 3: Email, 0: 사용안함)
    if(__G_User_Auth_Mean_Code == 1) {
        $('#coin-tab-2-auth-sms-tr').hide();
        $('#coin-tab-2-auth-otp-tr').show();
    } else if(__G_User_Auth_Mean_Code == 2) {
        $('#coin-tab-2-auth-sms-tr').show();
        $('#coin-tab-2-auth-otp-tr').hide();
    } else {
        $('#coin-tab-2-auth-sms-tr').hide();
        $('#coin-tab-2-auth-otp-tr').hide();
    }
    // 최소출금수량 셋팅
    var minWtdQty = __G_CoinMgtRef_Map.get(coinNo).MIN_WTDRW_QTY;
    $('#coin-tab-2-min-withdraw-qty').text(minWtdQty);
    // 출금수수료 셋팅
    var wtdFee = __G_CoinMgtRef_Map.get(coinNo).WTDRW_FEE;
    $('#coin-tab-2-withdraw-fee').text(wtdFee);
    // 출금가능수량 셋팅
    getCoinWithDrawQty(coinNo);
}

////////////////////////////////////
// COIN.T.02-1 코인 출금신청 탭 >> 출금가능수량, 1일출금한도, 1일잔여한도 가져오기
function getCoinWithDrawQty(coinNo) {
    $.ajax({
    	url : "/site/wallet/wlt001/checkWithdrawQty"
      , data : {
    	      coinNo : coinNo
    	    , reqQty : __G_CoinMgtRef_Map.get(coinNo).MIN_WTDRW_QTY
	   }
      , type : 'POST'
      , dataType : 'json'
      , error : function(data,status,error) {
          	console.log("에러 >> " + JSON.stringify(data));
            // $('#coin-tab-1-create-new-address').show();
            // $('#coin-tab-1-user-wallet-address-info').hide();
        }
      , success : function(data, textStatus) {
            console.log(data);
            var withdrawPossQty     = data.V_WTDRW_POSS_QTY;
            var dailyLimitMaxQty    = data.V_DAILIY_LIMIT_MAX_QTY;
            var dailyLimitLeftQty   = data.V_DAILIY_LIMIT_LEFT_QTY;
            var montylyLimitLeftQty = data.V_MONTHLY_LIMIT_LEFT_QTY;
            $('#coin-tab-2-user-poss-qty').text(nu.cm(withdrawPossQty, 3));
            $('#coin-tab-2-daily-limit-qty').text(nu.cm(dailyLimitMaxQty, 3));
            $('#coin-tab-2-daily-limit-left-qty').text(nu.cm(dailyLimitLeftQty, 3));
        }
    });
}

////////////////////////////////////
// COIN.T.02-2 코인 출금신청 탭 >> 인증번호 요청 버튼 이벤트 처리
function requestSmsAuthNumber() {
	var userPossessQty = 0, userDailyLeftQty = 0;
	try {
    	userPossessQty	= parseFloat($('#coin-tab-2-user-poss-qty').text().replace(/,/g, ''));
    	userDailyLeftQty= parseFloat($('#coin-tab-2-daily-limit-left-qty').text().replace(/,/g, ''));
	} catch(err) {};

    var reqQty          = $('#coin-tab-2-withdraw-request-qty').val();
    var targetAddr      = $('#coin-tab-2-target-address').val();
    var targetAddrEtc1  = $('#coin-tab-2-etc-address-1-addr').val();
    var targetAddrEtc2  = $('#coin-tab-2-etc-address-2-addr').val();
    var reqMemo         = $('#coin-tab-2-memo').val();

    var paramJson = {
          coinNo            : __G_Prev_Selected_CoinNo
        , coinSymbol        : getCoinSymbolByCoinNo(__G_Prev_Selected_CoinNo)
        , reqQty            : reqQty
        , targetAddr        : targetAddr
        , targetAddrEtc1    : targetAddrEtc1
        , targetAddrEtc2    : targetAddrEtc2
        , reqMemo           : reqMemo
    }

    if(userPossessQty == 0 || userDailyLeftQty == 0) {
        lrt.client('출금가능 잔고가 부족합니다.', '잔고 부족', null, null);
        return false;
    }
    if(targetAddr == '') {
        lrt.client('출금주소를 넣으세요.', '출금주소 오류', function() {
            $('#coin-tab-2-target-address').focus();
        }, null);
        return false;
    }
    if(reqQty == '' || parseFloat(reqQty) == 0) {
        lrt.client('요청수량을 넣으세요.', '요청수량 오류', function() {
            $('#coin-tab-2-withdraw-request-qty').focus();
        }, null);
        return false;
    }
    if(!checkValidationRequestAmt()) {
        lrt.client('요청수량을 확인하세요. 요청수량은 최소출금수량보다는 많고 출금가능수량보다는 적어야합니다.', '요청수량 오류', function() {
            $('#coin-tab-2-withdraw-request-qty').focus();
        }, null);
        return false;
    }
    // 추가주소 갯수 가져오기
    var wtdAddrCnt = parseInt(__G_CoinMgtRef_Map.get(__G_Prev_Selected_CoinNo).COIN_WTDRW_ADDR_CNT);
    var etcAddrName1 = __G_CoinMgtRef_Map.get(__G_Prev_Selected_CoinNo).ADDR_ETC1_NM;
    if(wtdAddrCnt > 1 && targetAddrEtc1 == '') {
        lrt.confirm( etcAddrName1 + '를 입력하지 않으셨습니다. 계속 진행하시겠습니까?', etcAddrName1 + ' 주소 오류', function() {
            doSmsAuthRequest(paramJson);
        }, null);
        return false;
    }

    // SMS 인증 요청 프로세스 진행
    doSmsAuthRequest(paramJson);
}
// SMS 인증요청 프로세스 실행
function doSmsAuthRequest(paramJson) {
    $.ajax({
        url : "/site/wallet/wlt001/requestSmsAuthNumber"
        , data : paramJson
        , type : 'POST'
        , dataType : 'json'
        , error : function(data,status,error) {
            //console.log("에러 >> " + JSON.stringify(data));
            // $('#coin-tab-1-create-new-address').show();
            // $('#coin-tab-1-user-wallet-address-info').hide();
        }
        , success : function(data, textStatus) {
            console.log(data);
            if(data.rtnCd == 0) {
                // message box
                lrt.client('회원님의 등록하신 휴대폰으로 인증번호가 발송되었습니다.<br>3분 이내로 인증절차를 진행해 주세요.', 'SMS인증요청', null, null);
                __G_Bool_Auth_Request = true;
                // 인증번호 요청 Timer Button 설정
                // button 비활성화시킴
                $('#coin-tab-2-request-sms-auth-number-btn').prop("disabled", true);
                var timeout = 180;
                __G_SMS_Auth_Request_btn_timer = setInterval(function() {
                    minutes = parseInt(timeout / 60 % 60, 10);
                    seconds = parseInt(timeout % 60, 10);

                    minutes = minutes < 10 ? "0" + minutes : minutes;
                    seconds = seconds < 10 ? "0" + seconds : seconds;
                    $('#coin-tab-2-request-sms-auth-number-btn').text(minutes + ' : ' + seconds);

                    if (--timeout < 0) {
                        timeout = 0;
                        clearInterval(__G_SMS_Auth_Request_btn_timer);
                        $('#coin-tab-2-request-sms-auth-number-btn').text('인증번호 요청');
                        // button 활성화시킴
                        $('#coin-tab-2-request-sms-auth-number-btn').prop("disabled", false);
                    }
                }, 1000);
            } else {
                lrt.client(wordbook[data.rtnCd], 'SMS인증요청 오류', function() {
                    // button 활성화시킴
                    $('#coin-tab-2-request-sms-auth-number-btn').prop("disabled", false);
                }, null);
            }
        }
    });
}

////////////////////////////////////
// COIN.T.02-3 코인 출금신청 탭 >> 최대 출금 버튼 이벤트 처리
function setMaxWithdrawQty() {
    var qty = getMaxReqQty();
    var maxQty = qty.split('|')[0];
    var realQty = qty.split('|')[1];
    $('#coin-tab-2-withdraw-request-qty').val(maxQty);
    $('#coin-tab-2-real-withdraw-qty').text(realQty);
}

////////////////////////////////////
// COIN.T.02-4 코인 출금신청 탭 >> 요청수량 체크함수
function checkValidationRequestAmt() {
    var user_input = $('#coin-tab-2-withdraw-request-qty').val().replace(/,/g,'');
    var min_wd_qty = ($('#coin-tab-2-min-withdraw-qty').text());
    if(user_input == '') {
        $('#coin-tab-2-real-withdraw-qty').text('0');
        return false;
    }

    // 요청 수량이 코인 WTDRW_DECIMAL_PNT 자릿수를 넘었을때 처리
    var coin_dcm_pnt = __G_CoinMgtRef_Map.get(__G_Prev_Selected_CoinNo).WTDRW_DECIMAL_PNT;
    if(user_input.indexOf('.') > -1) {
        var parts = user_input.toString().split(".");
        if(parts[1].length > coin_dcm_pnt) {
            user_input = parts[0] + '.' + parts[1].substr(0, coin_dcm_pnt);
            $('#coin-tab-2-withdraw-request-qty').val(user_input);
        }
    }

    var user_input_qty = parseFloat(user_input);
    var qty = getMaxReqQty();
    var maxQty  = parseFloat(qty.split('|')[0]);
    var realQty = nu.cm(parseFloat(qty.split('|')[1]), 6);

    if( (parseFloat(user_input_qty) >= 1 && parseFloat(min_wd_qty) > 1) && parseFloat(user_input_qty) < parseFloat(min_wd_qty) ) {$('#coin-tab-2-real-withdraw-qty').text('0'); return false;}
    if( (parseFloat(user_input_qty) >= 1 && maxQty > 1) && (parseFloat(user_input_qty) > maxQty && parseFloat(user_input_qty) < parseFloat(min_wd_qty)) ) {$('#coin-tab-2-real-withdraw-qty').text('0'); return false;}
    //if( (parseFloat(user_input_qty) >= 1 && maxQty > 1) && (parseFloat(user_input_qty) < maxQty && parseFloat(user_input_qty) > parseFloat(min_wd_qty)) ) return true;

    if(parseFloat(user_input_qty) > maxQty) {
        $('#coin-tab-2-withdraw-request-qty').val(maxQty);
        $('#coin-tab-2-real-withdraw-qty').text(realQty);
        return true;
    } else if(parseFloat(user_input_qty) == 0) {
        $('#coin-tab-2-real-withdraw-qty').text('0');
        return false;
    } else if(parseFloat(user_input_qty) < parseFloat(min_wd_qty) ) {
        $('#coin-tab-2-withdraw-request-qty').val(min_wd_qty);
        return false;
    } else {
        var withdraw_fee    = parseFloat($('#coin-tab-2-withdraw-fee').text());
        $('#coin-tab-2-real-withdraw-qty').text(''+nu.cm(user_input_qty + withdraw_fee, 8));
        return true;
    }

}
// COIN.T.02-4 코인 출금신청 탭 >> 요청수량 keyup 이벤트 처리
$('#coin-tab-2-withdraw-request-qty').keydown(function(event) {
	evntKeyDown(event, this, __G_CoinMgtRef_Map.get(__G_Prev_Selected_CoinNo).WTDRW_DECIMAL_PN);
    checkValidationRequestAmt();
});

////////////////////////////////////
// COIN.T.02-4-1 코인 출금신청 탭 >> 최대 요청수량 계산 함수
function getMaxReqQty() {
    var available_qty   = parseFloat($('#coin-tab-2-user-poss-qty').text().replace(/,/gi,''));
    var daily_max_qty   = parseFloat($('#coin-tab-2-daily-limit-qty').text().replace(/,/gi,''));
    var daily_left_qty  = parseFloat($('#coin-tab-2-daily-limit-left-qty').text().replace(/,/gi,''));
    var min_wd_qty      = parseFloat($('#coin-tab-2-min-withdraw-qty').text().replace(/,/gi,''));
    var withdraw_fee    = parseFloat($('#coin-tab-2-withdraw-fee').text().replace(/,/gi,''));

    // 3개중 fee를 뺀 최소값이 출금가능 금액임
    var tmpAry = [(available_qty - withdraw_fee), (daily_max_qty - withdraw_fee), (daily_left_qty - withdraw_fee)];
    var max_withdraw_qty  = Math.min.apply(null, tmpAry);
    var real_withdraw_qty = max_withdraw_qty + withdraw_fee;

    // Fee를 차감한 출금가능 금액이 0이거나, 0보다 작거나, 최소출금수량보다 작으면 출금 불가
    if(max_withdraw_qty == 0 || max_withdraw_qty < 0 || max_withdraw_qty < min_wd_qty) {
        max_withdraw_qty = 0; real_withdraw_qty = 0;
    }
    return nu.js(max_withdraw_qty, 8) + '|' + nu.js(real_withdraw_qty, 8);
}

////////////////////////////////////
// COIN.T.02-5 코인 출금신청 탭 >> 출금신청 버튼 이벤트 처리
function requestWithDraw() {
    var authSmsCode = $('#coin-tab-2-sms-auth-number').val();
    var authOtpCode = $('#coin-tab-2-otp-auth-number').val();

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
        	userPossessQty	= parseFloat($('#coin-tab-2-user-poss-qty').text().replace(/,/g, ''));
        	userDailyLeftQty= parseFloat($('#coin-tab-2-daily-limit-left-qty').text().replace(/,/g, ''));
    	} catch(err) {};

		var reqQtyTmp       = $('#coin-tab-2-withdraw-request-qty').val();
		var reqQty = reqQtyTmp.replace(/,/g, '');
        var targetAddr      = $('#coin-tab-2-target-address').val();
        var targetAddrEtc1  = $('#coin-tab-2-etc-address-1-addr').val();
        var targetAddrEtc2  = $('#coin-tab-2-etc-address-2-addr').val();
        var reqMemo         = $('#coin-tab-2-memo').val();

        if(userPossessQty == 0 || userDailyLeftQty == 0) {
            lrt.client('출금가능 잔고가 부족합니다.', '잔고 부족', null, null);
            return false;
        }
        if(targetAddr == '') {
            lrt.client('출금주소를 넣으세요.', '출금주소 오류', function() {
                $('#coin-tab-2-target-address').focus();
            }, null);
            return false;
        }
        if(reqQty == '' || parseFloat(reqQty) == 0) {
            lrt.client('요청수량을 넣으세요.', '요청수량 오류', function() {
                $('#coin-tab-2-withdraw-request-qty').focus();
            }, null);
            return false;
        }
        if(!checkValidationRequestAmt()) {
            lrt.client('요청수량을 확인하세요. 요청수량은 최소출금수량보다는 많고 출금가능수량보다는 적어야합니다.', '요청수량 오류', function() {
                $('#coin-tab-2-withdraw-request-qty').focus();
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
        lrt.client('인증요청을 하지 않으셨습니다.', '인증 오류', function() {
            $('#coin-tab-2-sms-auth-number').focus();
        }, null);
        return false;
    }
    if(__G_User_Auth_Mean_Code == 2 && authSmsCode == '') {
        lrt.client('인증번호를 넣으세요.', '인증번호 오류', function() {
            $('#coin-tab-2-sms-auth-number').focus();
        }, null);
        return false;
    }
    if(__G_User_Auth_Mean_Code == 1 && authOtpCode == '') {
        lrt.client('인증번호를 넣으세요.', '인증번호 오류', function() {
            $('#coin-tab-2-otp-auth-number').focus();
        }, null);
        return false;
    }
    // 추가주소 갯수 가져오기
    var wtdAddrCnt = parseInt(__G_CoinMgtRef_Map.get(__G_Prev_Selected_CoinNo).COIN_WTDRW_ADDR_CNT);
    var etcAddrName1 = __G_CoinMgtRef_Map.get(__G_Prev_Selected_CoinNo).ADDR_ETC1_NM;
    if(wtdAddrCnt > 1 && targetAddrEtc1 == '') {
        lrt.confirm( etcAddrName1 + '를 입력하지 않으셨습니다. 계속 진행하시겠습니까?', etcAddrName1 + ' 주소 오류', function() {
        	doRequestWithdraw(paramJson);
        }, null);
        return false;
    }

    doRequestWithdraw(paramJson);
}
// 출금요청 프로세스 실행
function doRequestWithdraw(paramJson) {
    $.ajax({
        url : "/site/wallet/wlt001/doRequestWithDraw"
        , data : paramJson
        , type : 'POST'
        , dataType : 'json'
        , error : function(data,status,error) {
            //console.log("에러 >> " + JSON.stringify(data));
            // $('#coin-tab-1-create-new-address').show();
            // $('#coin-tab-1-user-wallet-address-info').hide();
        }
        , success : function(data, textStatus) {
            // console.log(data);
            if(data.rtnCd == 0) {
                // message box
                lrt.client('정상적으로 출금신청이 요청되었습니다.', '출금신청', null, null);
                console.log("doRequestWithdraw++++", window.withdrawCoinTab)
                window.withdrawCoinTab.clearValues();
                cancelRequestMondyWithdraw();                // 출금신청 화면 초기화
                $('#coin-tab-a-3').trigger('click');    // 입출금내역 화면으로 전환
            } else {
                lrt.client(wordbook[data.rtnCd], '출금신청 오류', function() {
                    // button 활성화시킴
                    $('#coin-tab-2-request-sms-auth-number-btn').prop("disabled", false);
                }, null);
            }
        }
    });
}

////////////////////////////////////
// COIN.T.02-5-1 코인 출금신청 탭 >> 취소 버튼 이벤트 처리
function cancelRequestWithdraw() {
    $('#coin-tab-2-withdraw-request-qty').val('');
    $('#coin-tab-2-real-withdraw-qty').text('0');
    $('#coin-tab-2-target-address').val('');
    $('#coin-tab-2-etc-address-1-addr').val('');
    $('#coin-tab-2-etc-address-2-addr').val('');
    $('#coin-tab-2-memo').val('');
    $('#coin-tab-2-sms-auth-number').val('');
    $('#coin-tab-2-otp-auth-number').val('');
    $('#coin-tab-2-request-sms-auth-number-btn').text('인증번호 요청');
    $('#coin-tab-2-request-sms-auth-number-btn').prop("disabled", false);
    if(__G_SMS_Auth_Request_btn_timer > 0) clearInterval(__G_SMS_Auth_Request_btn_timer); // 인증요청 버튼 timer 기동중일때 timer 중지
}

////////////////////////////////////
// COIN.T.03 코인 입출금내역 탭
var __G_Coin_DW_List_window;
var __G_Coin_DW_List_Item;
function getDWMgrList(coinNo) {
    // Paging
    __G_Coin_DW_List_window = Pu.init({
        count: 10,
        PageCount: 5,
        draw: getListData,
        button: '#coin-tab-3-dw-list-btn',
        paginate: '#coin-tab-3-dw-list-pagination',
        totalText: '#coin-tab-3-dw-list-total-count',
        initCllBck: true,
        type: 'search',
        initEventFun: initListData
    });
}

function initListData(page, callback) {
	var p = {};

	p['coinNo'] = __G_Prev_Selected_CoinNo;
	p['dwReqTypeCd'] = $("#coin-tab-3-select-dw-req-type").val();
	p['startDate'] = $("#coin-tab-3-dw-list-startDate").val();
	p['endDate'] = $("#coin-tab-3-dw-list-endDate").val();
	p['pageNum'] = page;

    ajaxOption({
        url: '/site/wallet/wlt001/selectCoinBalanceHistListCount',
        data: p,
        success: callback,
        //done: done
    });
}
function getListData(i, done) {
	var p = {};

	p['coinNo'] = __G_Prev_Selected_CoinNo;
	p['dwReqTypeCd'] = $("#coin-tab-3-select-dw-req-type").val();
	p['startDate'] = $("#coin-tab-3-dw-list-startDate").val();
	p['endDate'] = $("#coin-tab-3-dw-list-endDate").val();
	p['pageNum'] = i;

    ajaxOption({
        url: '/site/wallet/wlt001/selectCoinBalanceHistList',
        data: p,
        success: drawDwList,
        done: done
    });
}

////////////////////////////////////
// COIN.T.03-1 코인 입출금내역 >> 입출금 리스트 그려주는 메서드
function drawDwList(depositWithdrawListData) {
	var depositWithdrawListJson = depositWithdrawListData;
	var depositWithdrawListTemplate = document.getElementById("template-wallet-deposit-withdraw-list");
	var depositWithdrawListTemplateHtml = depositWithdrawListTemplate.innerHTML;
	var depositWithdrawListHtml = "";
    __G_Coin_DW_List_Item = depositWithdrawListData;       // 전역변수에 저장

    if(depositWithdrawListJson.length > 0) {
        $(depositWithdrawListJson).each(function(data_item) {
            var value				        = depositWithdrawListJson[data_item];
            var reqSeqNo                    = getParam(value, 'REQ_SEQ_NO');
            var reqTypeCd                   = '' + getParam(value, 'DW_REQ_TYPE_CD', '1');
            var reqDateTime                 = du.getCstmFrmt(getParam(value, 'REQ_STAT_PROC_DT'), 'yyyyMMddHHmmssSSSSSS', 'yyyy-MM-dd HH:mm:ss');

            var reqQty                      = getParam(value, 'REQ_QTY');
            var fee                         = (reqTypeCd == '1') ? '0' : nu.js(parseFloat(getParam(value, 'WTDRW_FEE', '0')), 6); // 입금 : 0, 출금 : WTDRW_FEE
            var address                     = getParam(value, 'TARGET_ADDR');
            var reqStatProcCd               = getParam(value, 'REQ_STAT_PROC_CD');
            var approvalStatCd              = getParam(value, 'APPROVAL_STAT_CD');

            var gubunText                   = (reqTypeCd == '1') ? '입금' : '출금';
            var gubunColor                  = (reqTypeCd == '1') ? 'red' : 'blue';

            var procStatCd                  = getParam(value, 'DW_PROC_STAT_CD');
            var procStatTxt = '';
            switch(reqStatProcCd) {
                case 1: procStatTxt = '입금 요청'; break;
                case 2: procStatTxt = '컨펌 확인중'; break;
                case 3: procStatTxt = '출금 인증 대기중'; break;
                case 4: procStatTxt = ((reqTypeCd == '1') ? '입금':'출금') + '승인 대기중'; break;
                case 5: procStatTxt = '요청 취소'; break;
            }
            if(reqStatProcCd == 4 && procStatCd == 1) {
                procStatTxt = '처리 완료';
            }
            if(approvalStatCd == -1) {
            	procStatTxt = '승인 취소';
            }

            var cancelButtonHTML            = '';
            // 출금이면서 출금 인증 대기중인 것만 취소버튼 보이도록 처리함
            if(reqTypeCd == '2' && (reqStatProcCd == '3' || reqStatProcCd == '4') && approvalStatCd == 0) cancelButtonHTML = '<button onClick="cancelRequest(' + "'" + reqSeqNo + "'" + ')">취소</button>';

            depositWithdrawListHtml += depositWithdrawListTemplateHtml.replace(/{{DwGubun}}/g, gubunText)
                                    .replace(/{{ReqSeqNo}}/g, reqSeqNo)
                                    .replace(/{{DwGubunColor}}/g, gubunColor)
                                    .replace(/{{ReqDateTime}}/g, reqDateTime)
                                    .replace(/{{Address}}/g, address)
                                    .replace(/{{ReqQty}}/g, nu.cm(reqQty, 8) )
                                    .replace(/{{Fee}}/g, fee)
                                    .replace(/{{ReqStatProcText}}/g, procStatTxt)
                                    .replace(/{{CancelButtonHTML}}/g, cancelButtonHTML)
                                    ;
        });
        $('#coin-tab-3-dw-list-tbody').html(depositWithdrawListHtml);
    } else
        $('#coin-tab-3-dw-list-tbody').html($('#template-dw-list-empty').html().replace(/{{ColSpan}}/g, 7));
}

////////////////////////////////////
// COIN.T.03-2 코인 입출금내역 >> 입출금 리스트 TR Click 이벤트 처리
// $('#coin-tab-3-dw-list-tbody').delegate('tr', 'click', function (event) {
function doActionClickDwListTr(reqSeqNo) {
    $(__G_Coin_DW_List_Item).each(function(idx) {
        if(__G_Coin_DW_List_Item[idx].REQ_SEQ_NO == reqSeqNo) {
            // 출금 인증 대기중 (REQ_STAT_PROC_CD == 3) 일때는 출금신청 Tab으로 가서 다시 출금인증을 받도록 한다.
            if(parseInt(__G_Coin_DW_List_Item[idx].REQ_STAT_PROC_CD) == 3) {
                lrt.client('본 건은 인증이 필요한 출금인증 대기중 상태입니다.<br>출금신청에서 인증을 진행합니다.', '인증필요', function() {
                    var list_item = __G_Coin_DW_List_Item[idx];
                    $('#coin-tab-a-2').trigger('click');
                    $('#coin-tab-2-withdraw-request-qty').val(list_item.REQ_QTY);
                    $('#coin-tab-2-target-address').val(list_item.TARGET_ADDR);
                    $('#coin-tab-2-etc-address-1-addr').val(list_item.TARGET_ADDR_ETC1);
                    $('#coin-tab-2-real-withdraw-qty').text(list_item.REQ_QTY + list_item.WTDRW_FEE);
                    $('#coin-tab-2-sms-auth-number').focus();
                    __G_Bool_Auth_Request = true;
                }, null);
                return false;
            }

            $('.detail').show();
            var reqTypeCd = __G_Coin_DW_List_Item[idx].DW_REQ_TYPE_CD;
            var reqChannelCd = __G_Coin_DW_List_Item[idx].REQ_CHNL_CD;
            var reqChannelTxt = '';
            switch(reqChannelCd) {
                case 'API': reqChannelTxt = 'API'; break;
                case 'LP' : reqChannelTxt = 'LP'; break;
                case 'MWB': reqChannelTxt = '모바일웹'; break;
                case 'WEB': reqChannelTxt = '웹'; break;
                case 'WLL': reqChannelTxt = '월렛서버'; break;
            }
            var reqStatCd = __G_Coin_DW_List_Item[idx].REQ_STAT_PROC_CD;
            var procStatCd = __G_Coin_DW_List_Item[idx].DW_PROC_STAT_CD;
            var procStatTxt = '';
            switch(reqStatCd) {
                case 1: procStatTxt = '입금 요청'; break;
                case 2: procStatTxt = '컨펌 확인중'; break;
                case 3: procStatTxt = '출금 인증 대기중'; break;
                case 4: procStatTxt = ((reqTypeCd == '1') ? '입금':'출금') + '승인 대기중'; break;
                case 5: procStatTxt = '요청 취소'; break;
            }
            if(reqStatCd == 4 && procStatCd == 1) {
                procStatTxt = '처리 완료';
            }
            $('.coin-dw-detail-popup-coin-name').each(function( index ) { $(this).text(getCoinSymbolByCoinNo(__G_Coin_DW_List_Item[idx].COIN_NO)); });
            $('.coin-dw-detail-popup-coin-symbol').each(function( index ) { $(this).text(getCoinSymbolByCoinNo(__G_Coin_DW_List_Item[idx].COIN_NO)); });
            $('#coin-dw-detail-popup-req-qty').text(__G_Coin_DW_List_Item[idx].DW_COIN_QTY);
            $('#coin-dw-detail-popup-dw-req-type-code').text((reqTypeCd == '1') ? '입금':'출금');
            $('#coin-dw-detail-popup-fee-qty').text(__G_Coin_DW_List_Item[idx].NW_FEE_AMT);
            $('#coin-dw-detail-popup-req-channel').text(reqChannelTxt);
            $('#coin-dw-detail-popup-req-datetime').text(du.getCstmFrmt(__G_Coin_DW_List_Item[idx].REQ_DT, 'yyyyMMddHHmmssSSSSSS', 'yyyy-MM-dd HH:mm:ss') );
            $('#coin-dw-detail-popup-appr-datetime').text(du.getCstmFrmt(getParam(__G_Coin_DW_List_Item[idx],'APPROVAL_PROC_DT','00000000000000000000'), 'yyyyMMddHHmmssSSSSSS', 'yyyy-MM-dd HH:mm:ss') );
            $('#coin-dw-detail-popup-proc-status').text(procStatTxt);
            $('#coin-dw-detail-popup-account-info').text(__G_Coin_DW_List_Item[idx].USER_ID);
            $('#coin-dw-detail-popup-coin-address').text(__G_Coin_DW_List_Item[idx].TARGET_ADDR);
            $('#coin-dw-detail-popup-txid').text(__G_Coin_DW_List_Item[idx].TXID == undefined ? '' : __G_Coin_DW_List_Item[idx].TXID);
            return false;
        }
   });
}

////////////////////////////////////
// COIN.T.03-3 코인 입출금내역 >> 출금 취소 버튼 이벤트 처리
function cancelRequest(reqSeqNo) {
    $.ajax({
        url : "/site/wallet/wlt001/cancelWithdrawItem"
        , data : {
            reqSeqNo : reqSeqNo
        }
        , type : 'POST'
        , dataType : 'json'
        , error : function(data,status,error) {
            //console.log("에러 >> " + JSON.stringify(data));
        }
        , success : function(data, textStatus) {
            // console.log(data);
        	if(data.rtnCd != 0) {lrt.client(wordbook[data.rtnCd], '');}
        	else {
	            lrt.client('취소되었습니다.', '출금신청 취소', function() {
	                //var cur_page = $('#coin-tab-3-dw-list-pagination').find('a.active').text();
	                //getListData(cur_page, null);
	                //$('#coin-tab-2-target-address').focus();
	                getMondyDWMgrList();
	            }, null);
        	}
        }
    });
}


////////////////////////////////////
// COMMON.01 coinNo를 이용하여 코인 심볼명 가져옴
function getCoinSymbolByCoinNo(coinNo) {
    for(var i=0; i<__G_LNB_Coin_Item_List.length; i++) {
        var _coin_no = "" + getParam(__G_LNB_Coin_Item_List[i], 'COIN_NO');
        var _coin_symbolic_name = getParam(__G_LNB_Coin_Item_List[i], 'COIN_SYMBOLIC_NM');
        if(_coin_no == ("" + coinNo) ) {
            return _coin_symbolic_name;
        }
    };
    return '';
}
//////////////////////////////////////////////////////////////////////////////
//                           Right Frame 영역 처리 END
//////////////////////////////////////////////////////////////////////////////

