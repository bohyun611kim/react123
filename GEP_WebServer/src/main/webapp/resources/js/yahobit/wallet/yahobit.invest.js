// 거래소별 기본 기축통화 설정 (Tomcat 환경설정에 설정된것을 WAS에서 읽음)
var __G_Default_Coin_Type = 10;
// LNB 영역 코인 ITEM List를 가지고 있는 전역변수
var __G_LNB_Item_List;
// LNB 영역 회원 보유잔고를 가지고 있는 전역변수
var __G_LNB_User_Possession_List;
// LNB 리스트 클릭한 COIN_NO, COIN/MONEY를 저장한 전역변수
var __G_Prev_Selected_CoinNo = 0;
var __G_Prev_Selected_CoinKind = 'MONEY';
// 회원의 보유코인별 평가금액/평가손익 데이터
var __G_User_Possess_Coin_Data;
// 코인관리기준정보를 Map으로 들고있는 전역변수
var __G_CoinMgtRef_Map = new simpleMap();
// 회원의 인증레벨
var __G_User_Auth_Level = 0;
// 회원의 인증수단      (1: OTP, 2: SMS, 3: Email, 0: 미사용)
var __G_User_Auth_Mean_Code = 0;

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
    	url : "/alibit/wallet/wlt002/selectUserPossessionInfo"
    //   , data : { 
    // 	    ItemCode : itemCode
	//    }
      , type : 'POST'
      , dataType : 'json'
      , error : function(data,status,error) {
          	console.log("에러 >> " + JSON.stringify(data));     
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
    __G_LNB_Item_List = lnbExchangeCoinJson;       // 전역변수에 저장

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

            var bc_decimal_point            = __G_CoinMgtRef_Map.get(__G_Default_Coin_Type).WTDRW_DECIMAL_PNT;
            var coin_decimal_point          = __G_CoinMgtRef_Map.get(coinNo).WTDRW_DECIMAL_PNT;

            lnbHtml += lnbTemplateHtml.replace(/{{CoinSymbolicName}}/g, coinSymbolicName)
                                    .replace(/{{CoinNo}}/g, coinNo)
                                    .replace(/{{ItemDemesticName}}/g, itemDemesticName)
                                    .replace(/{{PossessPercent}}/g, possessPercent)
                                    .replace(/{{PossessQty}}/g, possessQty)
                                    .replace(/{{EstimateQty}}/g, estimateQty)
                                    .replace(/{{BaseEstimateSymbolicName}}/g, baseEstimateSymbolicName)
                                    .replace(/{{CoinKind}}/g, 'MONEY')
                                    .replace(/{{IsActive}}/g, isActive)
                                    .replace(/{{ShowHideOption}}/g, 'none')
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
                                    .replace(/{{ShowHideOption}}/g, 'block')
                                    ;
        });
        $('#wallet-lnb-coin-list-tbody').html(lnbHtml);

        // coin list selectbox 데이터 설정
        setSelectBoxCoinData();
    } else 
        $('#wallet-lnb-coin-list-tbody').html($('#template-list-empty').html().replace(/{{ColSpan}}/g, 3));
}

function writeUserPossessionInfo(data) {
    __G_LNB_User_Possession_List = data;       // 전역변수에 저장

    $(data).each(function(idx) {
        var value                           = data[idx];
        var coinNo                          = getParam(value, 'COIN_NO');
        var balanceQty                      = getParam(value, 'TOTAL_QTY'); //getParam(value, 'USABLE_QTY');    // 보유량으로 표시하도록 변경 (2019.06.24)
        var estimatedQty                    = getParam(value, 'TOTAL_BY_BC'); //getParam(value, 'TOTAL_USABLE_BY_BC');    // 보유량으로 표시하도록 변경 (2019.06.24)
        var possessPercent                  = getParam(value, 'POSS_PERCENT_BY_BC');
        var grandTotalPoss                  = getParam(value, 'GRAND_ALL_TOTAL_POSS_BY_BC'); //getParam(value, 'GRAND_TOTAL_POSS_BY_BC');    // 보유량으로 표시하도록 변경 (2019.06.24)

        if(idx == 0) $('#total_estimated_qty').text( nu.cm(parseFloat(grandTotalPoss), 0) );

        $('#possession-percent-' + coinNo).attr('style', 'width: ' + parseFloat(possessPercent).toFixed(0) + '%;');
        $('#possession-qty-' + coinNo).text( nu.cm(balanceQty, __G_CoinMgtRef_Map.get(coinNo).WTDRW_DECIMAL_PNT) );
        $('#estimate-qty-' + coinNo).text( nu.cm(estimatedQty,  __G_CoinMgtRef_Map.get(__G_Default_Coin_Type).WTDRW_DECIMAL_PNT) );
    });
    
    $.each($('#wallet-lnb-coin-list-tbody').children('tr'), function() {
    	let $this = $(this), $val = $this.find('.vertual-coin-type').text().replace(/,/g, '');
    	
    	if(parseFloat($val) === 0) {
    		$this.remove();
    	}
    })
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
$('#wallet-lnb-coin-list-tbody').delegate('tr', 'click', function (event) {
    var coinKind                = $(this).find('td').eq(0).find('.coinKind').eq(0).val();
    var coinNo                  = $(this).find('td').eq(0).find('.coinNo').eq(0).val();
    var coinDomesticName        = $(this).find('td').eq(1).find('p').eq(0).text();
    var coinSymbol              = $(this).find('td').eq(2).find('p').eq(0).find('span').eq(1).text();

    // 이전에 선택한 TR일 경우 아무런 처리없이 skip 한다.
    if(coinNo == __G_Prev_Selected_CoinNo || coinNo == undefined) return false;
    /* console.log('coinKind=' + coinKind);
    console.log('이전 선택 coin no=' + __G_Prev_Selected_CoinNo);
    console.log('현재 선택 coin no=' + coinNo); */
    __G_Prev_Selected_CoinNo = coinNo;
    __G_Prev_Selected_CoinKind = coinKind;

    // 다른 TR의 active class를 없애고
    $('#wallet-lnb-coin-list-tbody tr').each(function() {
        $(this).attr('class', '');
    });
    // 현재 TR의 active class를 설정한다.
    $(this).attr('class', 'active');

    // 현재 선택된 Tab이 거래내역 or 미체결내역 일때 select box를 변경후 재조회 한다.
    if(__G_Prev_Selected_Tab == 2 || __G_Prev_Selected_Tab == 3 || __G_Prev_Selected_Tab == 5) {
        if(__G_Prev_Selected_CoinKind == 'MONEY') {
            $('#invest-tabs-2-coin-select').val(9999);
            $('#invest-tabs-3-coin-select').val(9999);
            $('#invest-tabs-5-coin-select').val(9999);
        } else {
            $('#invest-tabs-2-coin-select').val(__G_Prev_Selected_CoinNo);
            $('#invest-tabs-3-coin-select').val(__G_Prev_Selected_CoinNo);
            $('#invest-tabs-5-coin-select').val(__G_Prev_Selected_CoinNo);
        }
        if(__G_Prev_Selected_Tab == 2) getUsrTradingHistoryList();
        if(__G_Prev_Selected_Tab == 3) getUsrNonContractList(true);
        if(__G_Prev_Selected_Tab == 5) getBalanceChangeHistoryList();
    }
});

// 거래내역/미체결내역/잔고변경이력 Tab의 종목 select box 코인리스트를 넣는다.
function setSelectBoxCoinData() {
    $('#invest-tabs-2-coin-select').empty();
    $('#invest-tabs-2-coin-select').append($('<option value="9999" selected>암호화폐 선택</option>'));
    $('#invest-tabs-3-coin-select').empty();
    $('#invest-tabs-3-coin-select').append($('<option value="9999" selected>암호화폐 선택</option>'));
    $('#invest-tabs-5-coin-select').empty();
    $('#invest-tabs-5-coin-select').append($('<option value="9999" selected>암호화폐 선택</option>'));

    $(__G_LNB_Item_List).each(function(idx) {
        var iCoinNo             = getParam(__G_LNB_Item_List[idx], 'COIN_NO', '20');
        var itemCode            = getParam(__G_LNB_Item_List[idx], 'ITEM_CODE', '');
        var itemDomesticName    = getParam(__G_LNB_Item_List[idx], 'ITEM_DOMESTIC_NM', '');
        $('#invest-tabs-2-coin-select').append($('<option value="' + iCoinNo + '">' + itemDomesticName + ' (' + itemCode + ')</option>'));
        $('#invest-tabs-3-coin-select').append($('<option value="' + iCoinNo + '">' + itemDomesticName + ' (' + itemCode + ')</option>'));
        $('#invest-tabs-5-coin-select').append($('<option value="' + iCoinNo + '">' + itemDomesticName + ' (' + itemCode + ')</option>'));
    });

    // 잔고변경이력 Tab의 변경사유를 코드값 가져와 넣는다.
	ajaxOption({
		url:'/alibit/wallet/wlt002/selectCodeInfo',
		data:{codeId:'C0004', langCd: 'ko'},
		success:function(data) {
			let length = data != null ? data.length:0;
			if(length > 0) {
                $('#invest-tabs-5-change-reason-select').empty();
                $('#invest-tabs-5-change-reason-select').append($('<option value="9999" selected>변경사유</option>'));
				for(let i=0; i<length; i++) {
					let temp = data[i], codeValNm = getParam(temp, 'CODE_VAL_NM', ''), codeNumVal = getParam(temp, 'CODE_NUM_VAL', '');
					$('#invest-tabs-5-change-reason-select').append($('<option value="' + codeNumVal + '">' + codeValNm + '</option>'));
				}
			}
		}
	});
}
//////////////////////////////////////////////////////////////////////////////
//                           LNB 영역 리스트 처리 END
//////////////////////////////////////////////////////////////////////////////


//////////////////////////////////////////////////////////////////////////////
//                           Right Frame 영역 처리 START
//////////////////////////////////////////////////////////////////////////////
// Tab click event 처리
var __G_Prev_Selected_Tab = 1;
$('#invest-tab-ul').click(function() {
    // 선택된 Tab 알아오기 (보유코인 : 1, 거래내역: 2, 미체결: 3, 입출금대기: 4, 잔고변경이력: 5)
    var selected_tab;
    for(var i = 1; i <= 5; i++) {
        if($('#tabs-' + i).is(':visible')) selected_tab = i;
    }
    if(__G_Prev_Selected_Tab == selected_tab) return false;     // 변경된 Tab 이 없으므로 skip

    __G_Prev_Selected_Tab = selected_tab;
    // console.log('Coin Tab UL click :: ' + selected_tab);

    // 거래내역 갱신
    if(__G_Prev_Selected_Tab == 2) {
        getUsrTradingHistoryList();
    }
    // 미체결 내역 갱신
    if(__G_Prev_Selected_Tab == 3) {
        getUsrNonContractList(true);
    }
    // 입출금대기 내역 갱신
    if(__G_Prev_Selected_Tab == 4) {
        getDWMgrList(9999);
    }
    // 잔고변경이력 갱신
    if(__G_Prev_Selected_Tab == 5) {
        getBalanceChangeHistoryList();
    }
});

////////////////////////////////////
// INVEST.T.01 보유코인 탭 >> 헤더 Summary 값 가져오기
// 회원의 보유코인의 종합 Summary 평가금액/평가손익을 계산하여 가져온다.
function getUserEstimatedPossessionInfo() {
    // __G_Default_Coin_Type :: 거래소 기초통화 타입 심볼명으로 치환한다.
    $('.base-currency-symbol').each(function( index ) { $(this).text(__G_CoinMgtRef_Map.get(__G_Default_Coin_Type).COIN_SYMBOLIC_NM); });
    $.ajax({
    	url : "/alibit/wallet/wlt002/selectUserEstimatedPossessionInfo"
      , type : 'POST'
      , dataType : 'json'
      , error : function(data,status,error) {
          	//console.log("에러 >> " + JSON.stringify(data));     
        }
      , success : function(data, textStatus) {
            //console.log(data);
            var tot_buy_amt         = getParam(data, 'TOTAL_BUY_AMT', '0');             // 총 매수금액
            var tot_bc_amt          = getParam(data, 'TOTAL_BC_AMT', '0');              // 보유 KRW
            var tot_estmate_amt     = getParam(data, 'TOTAL_ESTMATE_AMT', '0');         // 총 평가금액
            var tot_profit_loss_amt = getParam(data, 'TOTAL_PROFIT_OR_LOSS_AMT', '0');  // 평가 손익
            var tot_profit_loss_per = getParam(data, 'TOTAL_PROFIT_OR_LOSS_PER', '0');  // 평가 손익
            var tot_possess_amt     = tot_estmate_amt + tot_bc_amt;                     // 총 보유자산

            // 손익에 따른 color 설정
            var table_color         = (tot_profit_loss_amt > 0) ? 'red' : 'blue';

            $('#invest-tabs-1-header-total-buy-amt').text( nu.cm(tot_buy_amt, 0) + ' ');
            $('#invest-tabs-1-header-total-bc-amt').text( nu.cm(tot_bc_amt, 0) + ' ');
            $('#invest-tabs-1-header-total-estimate-amt').text( nu.cm(tot_estmate_amt, 0) + ' ');
            $('#invest-tabs-1-header-total-profit-or-loss-amt').text( nu.cm(tot_profit_loss_amt, 0) + ' ');
            $('#invest-tabs-1-header-total-profit-or-loss-amt').attr( 'class', table_color + ' bold');
            $('#invest-tabs-1-header-total-profit-or-loss-per').text( nu.cm(tot_profit_loss_per, 2, true) + ' ');
            $('#invest-tabs-1-header-total-profit-or-loss-per').attr( 'class', table_color + ' bold');
            $('#invest-tabs-1-header-total-possess-amt').text( nu.cm(tot_possess_amt, 0) + ' ');
        }
    });
}

//////////////////////////////////////////////////////////////////////////////
//                           Right Frame 영역 처리 START
//////////////////////////////////////////////////////////////////////////////

////////////////////////////////////
// INVEST.T.01-1 보유코인 탭 >> 회원 보유코인 Balance 정보 가져오기
// 회원의 보유코인별 평가금액/평가손익을 계산하여 가져온다.
function getUserPossessionCoinList() {
    // Paging
    var user_Poss_Coin_List_window = Pu.init({
        count: 10,
        PageCount: 5,
        draw: getUsrPossCoinListData,
        button: '#coin-tab-3-dw-list-btn',
        paginate: '#invest-tabs-1-pagination',
        totalText: '#invest-tabs-1-list-total-count',
        initCllBck: true,
        type: 'search',
        initEventFun: initUsrPossCoinListData
    });
}
// Controller에 회원 잔고내역을 요청한다.
function getUsrPossCoinListData(i, done) {
	var p = {};
	p['pageNum'] = i;
    ajaxOption({
        url: '/alibit/wallet/wlt002/selectUserPossessionCoinList',
        data: p,
        success: drawUserPossessCoinList,
        done: done
    });
}
// 회원의 보유코인별 평가금액/평가손익 리스트 갯수
function initUsrPossCoinListData(page, callback) {
    ajaxOption({
        url: '/alibit/wallet/wlt002/selectUserPossessionCoinListCount',
        success: callback,
    });
}
// 회원의 보유코인별 평가금액/평가손익을 계산하여 리스트에 그려준다.
function drawUserPossessCoinList(data) {
	var tab1UserPossessCoinJson             = (data);
	var tab1UserPossessCoinTemplate         = document.getElementById("template-wallet-tab-1-possess-coin-list");
	var tab1UserPossessCoinTemplateHtml     = tab1UserPossessCoinTemplate.innerHTML;
	var tab1UserPossessCoinHtml             = "";
    __G_User_Possess_Coin_Data              = data;       // 전역변수에 저장

    // 거래소 상장 코인 리스트
    if(tab1UserPossessCoinJson.length > 0) {
        $(tab1UserPossessCoinJson).each(function(data_item) {
            var value				        = tab1UserPossessCoinJson[data_item];
            var tot_buy_amt                 = getParam(value, 'TOTAL_BUY_AMT', '0');                // 매수금액
            var tot_estmate_amt             = getParam(value, 'TOTAL_ESTMATE_AMT', '0');            // 평가금액
            var avg_price_by_bc             = getParam(value, 'AVG_PRICE_BY_BC', '0');              // 매수 평균가
            var profit_loss_per             = (tot_buy_amt == 0) ? '0' : ((tot_estmate_amt - tot_buy_amt) / tot_buy_amt * 100) ;  // 평가 손익 %
            var profit_loss_amt             = (tot_estmate_amt - tot_buy_amt);                      // 평가 손익 금액
            var possessQty                  = getParam(value, 'BALANCE_QTY');

            var coinNo                      = getParam(value, 'COIN_NO');
            var coinSymbolicName            = __G_CoinMgtRef_Map.get(coinNo).COIN_SYMBOLIC_NM;
            var itemDemesticName            = getParam(value, 'COIN_KOR_NM');
            var baseEstimateSymbolicName    = __G_CoinMgtRef_Map.get(__G_Default_Coin_Type).COIN_SYMBOLIC_NM;

            var profit_color                = (profit_loss_per == 0) ? 'gray' : ((profit_loss_per > 0) ? 'red' : 'blue');

            var bc_decimal_point            = __G_CoinMgtRef_Map.get(__G_Default_Coin_Type).WTDRW_DECIMAL_PNT;
            var coin_decimal_point          = __G_CoinMgtRef_Map.get(coinNo).WTDRW_DECIMAL_PNT;
            
            if(parseFloat(nu.js(possessQty, coin_decimal_point)) === 0) {
            	return true;
            }
            
            tab1UserPossessCoinHtml += tab1UserPossessCoinTemplateHtml.replace(/{{CoinSymbolicName}}/g, coinSymbolicName)
                                    .replace(/{{CoinNo}}/g, coinNo)
                                    .replace(/{{ItemDemesticName}}/g, itemDemesticName)
                                    .replace(/{{PossessQty}}/g, nu.cm(possessQty, coin_decimal_point) )
                                    .replace(/{{AvgPriceBc}}/g, nu.cm(avg_price_by_bc, bc_decimal_point) )
                                    .replace(/{{BaseEstimateSymbolicName}}/g, baseEstimateSymbolicName)
                                    .replace(/{{BuyAmt}}/g, nu.cm(tot_buy_amt, bc_decimal_point) )
                                    .replace(/{{EstimatedAmt}}/g, nu.cm(tot_estmate_amt, bc_decimal_point) )
                                    .replace(/{{ProfitLossPer}}/g, nu.cm(profit_loss_per, 2, true) )
                                    .replace(/{{ProfitLossAmt}}/g, nu.cm(profit_loss_amt, bc_decimal_point) )
                                    .replace(/{{ProfitColor}}/g, profit_color)
                                    ;
        });
        $('#invest-tabs-1-user-possess-coin-list-tbody').html(tab1UserPossessCoinHtml);
    } else 
        $('#invest-tabs-1-user-possess-coin-list-tbody').html($('#template-list-empty').html().replace(/{{ColSpan}}/g, 8));
}

////////////////////////////////////
// INVEST.T.02-1 거래내역 탭 >> 회원 거래내역 정보 가져오기
// 회원의 거래내역 정보를 가져온다.
function getUsrTradingHistoryList() {
    // 현재 선택된 Tab이 거래내역이 아니면 무시한다.
    if(__G_Prev_Selected_Tab != 2) return false;
    // Paging
    var user_Trading_History_List_window = Pu.init({
        count: 10,
        PageCount: 5,
        draw: getUsrTradingHistoryListData,
        button: '#coin-tab-3-dw-list-btn',
        paginate: '#invest-tabs-2-pagination',
        totalText: '#invest-tabs-2-list-total-count',
        initCllBck: true,
        type: 'search',
        initEventFun: initUsrTradingHistoryListData
    });
}
// Controller에 회원 거래내역을 요청한다.
function getUsrTradingHistoryListData(i, done) {
    var p = {};
    var buyRecogCd  = $("input:checkbox[id='invest-tabs-2-checkbox-buy']").is(":checked");
    var sellRecogCd = $("input:checkbox[id='invest-tabs-2-checkbox-sell']").is(":checked");
    var sellBuyRecogCd = (buyRecogCd) ? 1 : ((sellRecogCd) ? 2 : -1);
    if( (buyRecogCd == true && sellRecogCd == true) || (buyRecogCd == false && sellRecogCd == false) ) sellBuyRecogCd = 9999;

    p['coinNo'] = $("#invest-tabs-2-coin-select").val();
	p['sellBuyRecogCd'] = sellBuyRecogCd;
	p['startDate'] = $("#invest-tabs-2-startDate").val();
	p['endDate'] = $("#invest-tabs-2-endDate").val();
    p['pageNum'] = i;

    ajaxOption({
        url: '/alibit/wallet/wlt002/selectUserTradingHistoryList',
        data: p,
        success: drawUsrTradingHistoryList,
        done: done
    });
}
// 회원의 거래내역 리스트 갯수
function initUsrTradingHistoryListData(page, callback) {
    var p = {};
    var buyRecogCd  = $("input:checkbox[id='invest-tabs-2-checkbox-buy']").is(":checked");
    var sellRecogCd = $("input:checkbox[id='invest-tabs-2-checkbox-sell']").is(":checked");
    var sellBuyRecogCd = (buyRecogCd) ? 1 : ((sellRecogCd) ? 2 : -1);
    if( (buyRecogCd == true && sellRecogCd == true) || (buyRecogCd == false && sellRecogCd == false) ) sellBuyRecogCd = 9999;

	p['coinNo'] = $("#invest-tabs-2-coin-select").val();
	p['sellBuyRecogCd'] = sellBuyRecogCd;
	p['startDate'] = $("#invest-tabs-2-startDate").val();
	p['endDate'] = $("#invest-tabs-2-endDate").val();
    p['pageNum'] = page;
    ajaxOption({
        url: '/alibit/wallet/wlt002/selectUserTradingHistoryListCount',
        data: p,
        success: callback,
    });
}
// 회원의 거래내역 데이터를 리스트에 그려준다.
function drawUsrTradingHistoryList(data) {
	var tab2UsrTradingHistoryJson             = (data);
	var tab2UsrTradingHistoryTemplate         = document.getElementById("template-wallet-tab-2-trading-history-list");
	var tab2UsrTradingHistoryTemplateHtml     = tab2UsrTradingHistoryTemplate.innerHTML;
	var tab2UsrTradingHistoryHtml             = "";
    // console.log(data);
    // 거래내역 리스트
    if(tab2UsrTradingHistoryJson.length > 0) {
        $(tab2UsrTradingHistoryJson).each(function(data_item) {
            var value				        = tab2UsrTradingHistoryJson[data_item];
            var orderDateTime               = du.getCstmFrmt(getParam(value, 'ORD_DT', '0'), 'yyyyMMddHHmmssSSSSSS', 'yyyy-MM-dd HH:mm:ss');    // 주문 접수시간
            var itemName                    = getParam(value, 'ITEM_CODE', '');                             // 평가금액
            var sellBuyGubun                = getParam(value, 'SELL_BUY_RECOG_CD', '1');                    // 매수 / 매도 구분
            var orderPrice                  = getParam(value, 'ORD_PRICE', '0');                            // 주문 가격
            var orderQty                    = getParam(value, 'ORD_QTY', '0');                              // 주문 수량
            var contractPrice               = getParam(value, 'CONTRACT_PRICE', '0');                       // 체결 가격
            var contractQty                 = getParam(value, 'CONTRACT_QTY', '0');                         // 체결 수량
            var contractAmt                 = (parseFloat(contractPrice) * parseFloat(contractQty));        // 체결 금액

            var sellBuyGubunTxt             = (sellBuyGubun == 1) ? '매수' : '매도';
            var sellBuyGubunColor           = (sellBuyGubun == 1) ? 'red' : 'blue';

            var bc_decimal_point            = __G_CoinMgtRef_Map.get(__G_Default_Coin_Type).WTDRW_DECIMAL_PNT;
            //var coin_decimal_point          = __G_CoinMgtRef_Map.get(itemName).WTDRW_DECIMAL_PNT;

            tab2UsrTradingHistoryHtml += tab2UsrTradingHistoryTemplateHtml.replace(/{{OrderDateTime}}/g, orderDateTime)
                                    .replace(/{{ItemName}}/g, itemName)
                                    .replace(/{{SellBuyGubun}}/g, sellBuyGubunTxt)
                                    .replace(/{{SellBuyGubunColor}}/g, sellBuyGubunColor )
                                    .replace(/{{OrderPrice}}/g, nu.cm(orderPrice, bc_decimal_point) )
                                    .replace(/{{OrderQty}}/g, nu.cm(orderQty, 8))
                                    .replace(/{{ContractPrice}}/g, nu.cm(contractPrice, bc_decimal_point))
                                    .replace(/{{ContractQty}}/g, nu.cm(contractQty, 8))
                                    .replace(/{{ContractAmt}}/g, nu.cm(contractAmt, bc_decimal_point))
                                    ;
        });
        $('#invest-tabs-2-trading-history-list-tbody').html(tab2UsrTradingHistoryHtml);
    } else 
        $('#invest-tabs-2-trading-history-list-tbody').html($('#template-list-empty').html().replace(/{{ColSpan}}/g, 8));
}
////////////////////////////////////
// INVEST.T.02-2 거래내역 탭 >> 매도/매수 체크박스 클릭이벤트 처리
// 매수 체크박스 클릭이벤트 처리
$('#invest-tabs-2-checkbox-buy').click(function() {
    getUsrTradingHistoryList();
});
// 매도 체크박스 클릭이벤트 처리
$('#invest-tabs-2-checkbox-sell').click(function() {
    getUsrTradingHistoryList();
});

////////////////////////////////////
// INVEST.T.03-1 미체결내역 탭 >> 회원 미체결내역 정보 가져오기
// 회원의 미체결내역 정보를 가져온다.
$(document).ready(function() {
	$('#invest-tabs-3-non-contract-history-list-div').mCustomScrollbar({
		callbacks:{
			onTotalScroll:function() {
				getUsrNonContractList(false);
			}
		}
	});
});
var user_non_contract_list_page = 0;
var user_non_contract_list_count = 0;
// Controller에 회원 미체결내역을 요청한다.
function getUsrNonContractList(isFirstLoading) {
    // 현재 선택된 Tab이 미체결내역이 아니면 무시한다.
    if(__G_Prev_Selected_Tab != 3) return false;

    // 초기 로딩시 초기화 (coin 변경시에도 초기화)
    if(isFirstLoading) {
        $('#invest-tabs-3-non-contract-history-list-tbody').empty();
        user_non_contract_list_page = 0;
    }

    ajaxOption({
        url:'/alibit/wallet/wlt002/selectUsrNonContractListCount',
        data:{
            coinNo: $("#invest-tabs-3-coin-select").val(),
            sellBuyRecogCd: 9999,
        },
        success:function(data) {
            $('#invest-tabs-3-list-total-count').text(data.size);
            user_non_contract_list_count = parseInt('' + data.size);
            if(user_non_contract_list_count == 0) {
                $('#invest-tabs-3-non-contract-history-list-tbody').append($('<tr><td colspan="9">You have no data</td></tr>'));
            } else if( (user_non_contract_list_count - (user_non_contract_list_page * 12)) > 0) {
                ajaxOption({
                    url:'/alibit/wallet/wlt002/selectUsrNonContractList',
                    data:{
                        coinNo: $("#invest-tabs-3-coin-select").val(),
                        sellBuyRecogCd: 9999,
                        pageNum: ++user_non_contract_list_page
                    },
                    success:function(data) {
                        drawUsrNonContractList(data);
                    }
                });
            }
        }
    });
}

// 회원의 미체결내역 데이터를 리스트에 그려준다.
var __G_NonContract_List_Data;
function drawUsrNonContractList(data, flag) {
	let length = data.length;
	let listHtml = '', listTemplate = $('#template-wallet-tab-3-non-contract-history-list').html();
    __G_NonContract_List_Data = data;
    
	for(let i=0; i<length; i++) {
        var value				        = data[i];
        var orderDateTime               = du.getCstmFrmt(getParam(value, 'ORD_DT', '0'), 'yyyyMMddHHmmssSSSSSS', 'MM-dd HH:mm:ss');    // 주문 접수시간
        var nonContractOrderNo          = getParam(value, 'NON_CONTRACT_ORD_NO', '');                   // 미체결 주문번호
        var orderNo                     = getParam(value, 'EXCHGSVR_ORD_NO', '');                       // 주문번호
        var itemName                    = getParam(value, 'ITEM_CODE', '');                             // ITEM Code
        var sellBuyGubun                = getParam(value, 'SELL_BUY_RECOG_CD', '1');                    // 매수 / 매도 구분
        var orderPrice                  = getParam(value, 'ORD_PRICE', '0');                            // 주문 가격
        var orderQty                    = getParam(value, 'ORD_QTY', '0');                              // 주문 수량
        var contractQty                 = getParam(value, 'CONTRACT_QTY', '0');                         // 체결 수량
        var nonContractQty              = getParam(value, 'NON_CONTRACT_QTY', '0');                     // 미체결 수량

        var sellBuyGubunTxt             = (sellBuyGubun == 1) ? '매수' : '매도';
        var sellBuyGubunColor           = (sellBuyGubun == 1) ? 'red' : 'blue';
        var bc_decimal_point            = __G_CoinMgtRef_Map.get(__G_Default_Coin_Type).WTDRW_DECIMAL_PNT;
    
        listHtml += listTemplate
            .replace(/{{OrderDateTime}}/g, orderDateTime)
            .replace(/{{OrderNo}}/g, orderNo)
            .replace(/{{ItemName}}/g, itemName)
            .replace(/{{SellBuyGubun}}/g, sellBuyGubunTxt)
            .replace(/{{SellBuyGubunColor}}/g, sellBuyGubunColor )
            .replace(/{{OrderPrice}}/g, nu.cm(orderPrice, bc_decimal_point) )
            .replace(/{{OrderQty}}/g, nu.cm(orderQty, 8))
            .replace(/{{NonContractQty}}/g, nu.cm(nonContractQty, 8))
            .replace(/{{ContractQty}}/g, nu.cm(contractQty, 8))
            .replace(/{{NonContractOrderNo}}/g, nonContractOrderNo)
            ;
    }
	$('#invest-tabs-3-non-contract-history-list-tbody').append(listHtml);
}

////////////////////////////////////
// INVEST.T.03-2 미체결내역 탭 >> 미체결 주문 취소 버튼 클릭 이벤트 처리
function cancelNonContractOrder(nonContractOrderNo) {
    lrt.confirm( '해당 주문을 취소 하시겠습니까?', '주문 취소', function() {
        doNonContractOrder(nonContractOrderNo);
    }, null);
    return false;
}
function doNonContractOrder(nonContractOrderNo) {
    $.ajax({
    	url : "/alibit/wallet/wlt002/cancelNonContract"
      , data : { 
            nonContractOrderNo : nonContractOrderNo
	   }
      , type : 'POST'
      , dataType : 'json'
      , error : function(data,status,error) {
          	console.log("에러 >> " + JSON.stringify(data));     
        }
      , success : function(data, textStatus) {
            //console.log(data);
            // writeUserPossessionInfo(data);
    	  if(data.rtnCd != 0) {lrt.client(wordbook[data.rtnCd], '');} 
        }
    });
}
// 미체결주문 취소결과 PUSH 메시지 리스너 함수
function __Push_Listener_Invest_Cancel_NonContract(data) {
    console.log('Cancel NonContract');
    console.log(data);
    lrt.client('주문이 취소되었습니다.', '주문 취소 성공', null, null);
    // 현재 페이지를 갱신한다.
    let exchgsvrOrgOrdNo = data.body.exchgsvrOrgOrdNo;
    $('#invest-tabs-3-non-contract-history-list-tbody tr').each(function(idx) {
        let orderNo = $(this).data('orderno');
        if(exchgsvrOrgOrdNo == orderNo) {
            let cnt = parseInt($('#invest-tabs-3-list-total-count').text());
            user_non_contract_list_count = cnt - 1;
            $('#invest-tabs-3-list-total-count').text(user_non_contract_list_count);
            $(this).remove();
        }
    });
}

////////////////////////////////////
// INVEST.T.04-1 입출금 대기 탭 >> 입출금 대기 Tab 처리
var __G_DW_List_window;
// 전역변수에 입출금 대기 리스트 데이터 저장
var __G_DW_List_Item_Data;
function getDWMgrList(coinNo) {
    // Paging
    __G_DW_List_window = Pu.init({
        count: 10,
        PageCount: 5,
        draw: getListData,
        button: '#coin-tab-3-dw-list-btn',
        paginate: '#invest-tabs-4-pagination',
        totalText: '#invest-tabs-4-list-total-count',
        initCllBck: true,
        type: 'search',
        initEventFun: initListData
    });
}

function initListData(page, callback) {
	let p = {};
    
    let o_request   = $("input:checkbox[id='invest-tabs-4-checkbox-request']").is(":checked");
    let o_wait      = $("input:checkbox[id='invest-tabs-4-checkbox-wait']").is(":checked");
    let o_finish    = $("input:checkbox[id='invest-tabs-4-checkbox-finish']").is(":checked");
    let options     = o_request << 2 | o_wait << 1 | o_finish << 0; // (0b111 형태 option value)

	p['coinNo'] = 9999;     // 모든코인 선택 (9999)
	p['dwReqTypeCd'] = $("#invest-tabs-4-gubun-select").val();
	p['startDate'] = $("#invest-tabs-4-startDate").val();
	p['endDate'] = $("#invest-tabs-4-endDate").val();
	p['srchOpt'] = options;     // 조회옵션
	p['pageNum'] = page;
	
    ajaxOption({
        url: '/alibit/wallet/wlt001/selectCoinBalanceHistListCount',
        data: p,
        success: callback,
        //done: done
    });
}
function getListData(i, done) {
	let p = {};
    
    let o_request   = $("input:checkbox[id='invest-tabs-4-checkbox-request']").is(":checked");
    let o_wait      = $("input:checkbox[id='invest-tabs-4-checkbox-wait']").is(":checked");
    let o_finish    = $("input:checkbox[id='invest-tabs-4-checkbox-finish']").is(":checked");
    let options     = o_request << 2 | o_wait << 1 | o_finish << 0; // (0b111 형태 option value)
		
	p['coinNo'] = 9999;     // 모든코인 선택 (9999)
	p['dwReqTypeCd'] = $("#invest-tabs-4-gubun-select").val();
	p['startDate'] = $("#invest-tabs-4-startDate").val();
	p['endDate'] = $("#invest-tabs-4-endDate").val();
	p['srchOpt'] = options;     // 조회옵션
	p['pageNum'] = i;
	
    ajaxOption({
        url: '/alibit/wallet/wlt001/selectCoinBalanceHistList',
        data: p,
        success: drawDwList,
        done: done
    });
}

////////////////////////////////////
// INVEST.T.04-2 입출금 대기 탭 >> 입출금 대기 리스트 그려주는 메서드
function drawDwList(depositWithdrawListData) {
	var depositWithdrawListJson = depositWithdrawListData;
	var depositWithdrawListTemplate = document.getElementById("template-wallet-tab-4-deposit-withdraw-waiting-history-list");
	var depositWithdrawListTemplateHtml = depositWithdrawListTemplate.innerHTML;
	var depositWithdrawListHtml = "";
    __G_DW_List_Item_Data = depositWithdrawListData;       // 전역변수에 저장

    if(depositWithdrawListJson.length > 0) {
        $(depositWithdrawListJson).each(function(data_item) {
            var value				        = depositWithdrawListJson[data_item];
            var reqSeqNo                    = getParam(value, 'REQ_SEQ_NO', '0');
            var reqTypeCd                   = '' + getParam(value, 'DW_REQ_TYPE_CD', '1');
            var reqDateTime                 = du.getCstmFrmt(getParam(value, 'REQ_STAT_PROC_DT'), 'yyyyMMddHHmmssSSSSSS', 'yyyy-MM-dd HH:mm:ss');

            var bc_decimal_point            = __G_CoinMgtRef_Map.get(__G_Default_Coin_Type).WTDRW_DECIMAL_PNT;
            var coin_decimal_point          = __G_CoinMgtRef_Map.get(getParam(value, 'COIN_NO', '')).WTDRW_DECIMAL_PNT;

            var coinNo						= parseInt(getParam(value, 'COIN_NO', '10'));
            var reqQty                      = nu.cm(getParam(value, 'REQ_QTY', '0'), coin_decimal_point);
            var fee                         = nu.js(parseFloat(getParam(value, 'NW_FEE_AMT', '0')), 6);
            var address                     = getParam(value, 'TARGET_ADDR', '');
            var reqStatProcCd               = getParam(value, 'REQ_STAT_PROC_CD', '');
            var approvalStatCd              = getParam(value, 'APPROVAL_STAT_CD');

            var coinSymbolicName            = __G_CoinMgtRef_Map.get(getParam(value, 'COIN_NO', '')).COIN_SYMBOLIC_NM; //getParam(value, 'COIN_SYMBOLIC_NM', '');

            var gubunText                   = (reqTypeCd == '1') ? '입금' : '출금';
            var gubunColor                  = (reqTypeCd == '1') ? 'red' : 'blue';
            if(coinNo == 10) {
            	gubunText                   = (reqTypeCd == '3') ? '입금' : '출금';
            	gubunColor                  = (reqTypeCd == '3') ? 'red' : 'blue';
            }

            var procStatCd                  = getParam(value, 'DW_PROC_STAT_CD', '1');
            var procStatTxt = '';
            switch('' + reqStatProcCd) {
                case '1': procStatTxt = '입금 요청'; break;
                case '2': procStatTxt = '컨펌 확인중'; break;
                case '3': procStatTxt = '출금 인증 대기중'; break;
                case '4': 
                	let procTxt = '';
                	if(approvalStatCd == 0) {
                		procTxt = '승인 대기중';
                	} else if(approvalStatCd == 1) {
                		procTxt = ' 처리 대기중';
                	}
                	procStatTxt = (( (coinNo != 10 && reqTypeCd == '1') || (coinNo == 10 && reqTypeCd == '3')) ? '입금':'출금') + procTxt; break;
                case '5': procStatTxt = '요청 취소'; break;
            }
            if('' + reqStatProcCd == '4' && '' + procStatCd == '1') {
                procStatTxt = '처리 완료';
            }
            if(approvalStatCd == -2) {
            	procStatTxt = '환급 완료';
            }
            if(approvalStatCd == -1) {
            	procStatTxt = '승인 취소';
            }

            var cancelButtonHTML            = '';
            // 출금이면서 출금 인증 대기중인 것만 취소버튼 보이도록 처리함
            if('' + reqTypeCd == '2' && '' + reqStatProcCd == '3') cancelButtonHTML = '<button onClick="cancelRequest(' + "'" + reqSeqNo + "'" + ')">취소</button>';

            depositWithdrawListHtml += depositWithdrawListTemplateHtml.replace(/{{DwGubun}}/g, gubunText)
                                    .replace(/{{ReqSeqNo}}/g, reqSeqNo)
                                    .replace(/{{DwGubunColor}}/g, gubunColor)
                                    .replace(/{{ReqDateTime}}/g, reqDateTime)
                                    .replace(/{{CoinSymbolicName}}/g, coinSymbolicName)
                                    .replace(/{{ReqQty}}/g, reqQty)
                                    .replace(/{{ReqStatProcText}}/g, procStatTxt)
                                    .replace(/{{Fee}}/g, fee)
                                    .replace(/{{CancelButtonHTML}}/g, cancelButtonHTML)
                                    ;
        });
        $('#invest-tabs-4-deposit-withdraw-waiting-history-list-tbody').html(depositWithdrawListHtml);
    } else 
        $('#invest-tabs-4-deposit-withdraw-waiting-history-list-tbody').html($('#template-list-empty').html().replace(/{{ColSpan}}/g, 5));
}

////////////////////////////////////
// INVEST.T.04-3 코인 입출금내역 >> 입출금 리스트 TR Click 이벤트 처리
// $('#invest-tabs-4-deposit-withdraw-waiting-history-list-tbody').delegate('tr', 'click', function (event) {
function doActionClickDwListTr(reqSeqNo) {
    return false;
    $('.detail').show();
    $(__G_Coin_DW_List_Item).each(function(idx) {
        if(__G_Coin_DW_List_Item[idx].REQ_SEQ_NO == reqSeqNo) {

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
// INVEST.T.04-4 코인 입출금내역 >> 출금 취소 버튼 이벤트 처리
function cancelRequest(reqSeqNo) {
    $.ajax({
        url : "/alibit/wallet/wlt001/cancelWithdrawItem"
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
	                var cur_page = $('#coin-tab-3-dw-list-pagination').find('a.active').text();
	                getListData(cur_page, null);
	                //$('#coin-tab-2-target-address').focus();
	            }, null);
        	}
        }
    });
}


////////////////////////////////////
// INVEST.T.05-1 잔고변경 이력 탭 >> 잔고변경 이력 Tab 처리
var __G_BalChange_List_window;
// 전역변수에 잔고변경 이력 리스트 데이터 저장
var __G_Bal_Chg_List_Item_Data;
function getBalanceChangeHistoryList() {
    // Paging
    __G_BalChange_List_window = Pu.init({
        count: 10,
        PageCount: 5,
        draw: getBalChgListData,
        paginate: '#invest-tabs-5-pagination',
        totalText: '#invest-tabs-5-list-total-count',
        initCllBck: true,
        type: 'search',
        initEventFun: initBalChgListData
    });
}

function initBalChgListData(page, callback) {
	let p = {};

	p['coinNo'] = $("#invest-tabs-5-coin-select").val();
	p['chgRsnCd'] = $("#invest-tabs-5-change-reason-select").val();
	p['startDate'] = $("#invest-tabs-5-startDate").val();
	p['endDate'] = $("#invest-tabs-5-endDate").val();
	p['pageNum'] = page;
	
    ajaxOption({
        url: '/alibit/wallet/wlt002/selectCoinBalanceChangeHistoryListCount',
        data: p,
        success: callback,
        //done: done
    });
}
function getBalChgListData(i, done) {
	let p = {};

	p['coinNo'] = $("#invest-tabs-5-coin-select").val();
	p['chgRsnCd'] = $("#invest-tabs-5-change-reason-select").val();
	p['startDate'] = $("#invest-tabs-5-startDate").val();
	p['endDate'] = $("#invest-tabs-5-endDate").val();
	p['pageNum'] = i;
	
    ajaxOption({
        url: '/alibit/wallet/wlt002/selectCoinBalanceChangeHistoryList',
        data: p,
        success: drawChgHistList,
        done: done
    });
}

////////////////////////////////////
// INVEST.T.05-2 잔고변경 이력 탭 >> 잔고변경 이력 리스트 그려주는 메서드
function drawChgHistList(balanceChangeHistoryListData) {
	var balanceChangeHistoryListJson = balanceChangeHistoryListData;
	var balanceChangeHistoryListTemplate = document.getElementById("template-wallet-tab-5-balance-change-history-list");
	var balanceChangeHistoryListTemplateHtml = balanceChangeHistoryListTemplate.innerHTML;
	var balanceChangeHistoryListHtml = "";
    __G_Bal_Chg_List_Item_Data = balanceChangeHistoryListData;       // 전역변수에 저장

    if(balanceChangeHistoryListJson.length > 0) {
        $(balanceChangeHistoryListJson).each(function(data_item) {
            var value				        = balanceChangeHistoryListJson[data_item];
            var reqSeqNo                    = getParam(value, 'REQ_SEQ_NO', '0');
            var coinNo                      = getParam(value, 'COIN_NO', '20');
            var coinSymbolicNm              = getParam(value, 'COIN_SYMBOLIC_NM', '20');
            var itemCode					= getParam(value, 'ITEM_CODE', '');
            var itemNm						= getParam(value, 'ITEM_NM', '');
            var tradeDt                     = du.getCstmFrmt(getParam(value, 'TRADE_DT'), 'yyyyMMddHHmmssSSSSSS', 'yyyy-MM-dd HH:mm:ss');
            var chgDt                       = du.getCstmFrmt(getParam(value, 'CHG_DT'), 'yyyyMMddHHmmssSSSSSS', 'yyyy-MM-dd HH:mm:ss');
            
            var bc_decimal_point            = __G_CoinMgtRef_Map.get(__G_Default_Coin_Type).WTDRW_DECIMAL_PNT;
            var coin_decimal_point          = __G_CoinMgtRef_Map.get(coinNo).WTDRW_DECIMAL_PNT;
            var chgReasCd                   = parseInt(getParam(value, 'CHG_REAS_CD', '1'));
            var chgReasTxt                  = getParam(value, 'CODE_VAL_NM', '');

            var minusOrPlusColor			= '';
            if(chgReasCd == 1 || chgReasCd == 3 || chgReasCd == 5 || chgReasCd == 7 || chgReasCd == 10 || chgReasCd == 11 || chgReasCd == 12 || chgReasCd == 13) {
            	minusOrPlusColor = 'red'
            } else if(chgReasCd == 2 || chgReasCd == 4 || chgReasCd == 6 || chgReasCd == 8 || chgReasCd == 9) {
            	minusOrPlusColor = 'blue'
            }
            
            var balQty                      = parseFloat(getParam(value, 'BALANCE_QTY', '0'));
            var chgQty                      = parseFloat(getParam(value, 'CHG_QTY', '0'));
            var feeAmt                      = parseFloat(getParam(value, 'FEE_AMT', '0'));
            var chgQtyWithFee               = parseFloat(getParam(value, 'CHG_QTY_WITH_FEE', '0'));


            balanceChangeHistoryListHtml += balanceChangeHistoryListTemplateHtml.replace(/{{ChangeDateTime}}/g, chgDt)
                                            .replace(/{{CoinSymbolicName}}/g, coinSymbolicNm + ((itemNm != '') ? ' (' + itemNm + ')' : '') )
                                            .replace(/{{ChangeReason}}/g, chgReasTxt)
                                            .replace(/{{ChangeQtyWithFee}}/g, nu.cm('' + chgQtyWithFee, coin_decimal_point))
                                            .replace(/{{Fee}}/g, nu.cm('' + feeAmt, coin_decimal_point))
                                            .replace(/{{ChangeQty}}/g, nu.cm('' + chgQty, coin_decimal_point))
                                            .replace(/{{PlusOrMinusColor}}/g, minusOrPlusColor)
                                            ;
        });
        $('#invest-tabs-5-balance-change-history-list-tbody').html(balanceChangeHistoryListHtml);
    } else 
        $('#invest-tabs-5-balance-change-history-list-tbody').html($('#template-list-empty').html().replace(/{{ColSpan}}/g, 6));
}

//////////////////////////////////////////////////////////////////////////////
//                           Right Frame 영역 처리 END
//////////////////////////////////////////////////////////////////////////////
