//////////////////////////////////////////////////////////////////////////////
/*북마크*/
$('.bookmark').click(function() {
    $(this).toggleClass('mark-on');
});

/*슬릭스 슬라이더*/
$(".regular").slick({
    dots: true,
    infinite: true,
    slidesToShow: 1,
    slidesToScroll: 1,
    autoplay: false,
    autoplaySpeed: 4000
});

//팝업 열고 닫기   
function openForm() {
    document.getElementById("myForm").style.display = "block";
}

function closeForm() {
    document.getElementById("myForm").style.display = "none";
}

//레이어팝업닫기
$(document).ready(function() {
    $('.top-close').click(function() {
        $('.security-pop').hide();
    });
});

/*아코디언*/
$(document).ready(function(){
    $( "#tab-group" ).tabs();

    $( "#accordion1" ).accordion();
    $( "#accordion2" ).accordion();
    $( "#accordion3" ).accordion();
    $( "#accordion4" ).accordion();
    $( "#accordion5" ).accordion();
    $( "#accordion6" ).accordion();
});
//////////////////////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////////////////////
//                           공지사항 리스트 처리
//////////////////////////////////////////////////////////////////////////////
var __G_Notice_Cont;
var __G_Notice_window;

function toggleNoticeList(seq, listshow) {
	if(seq == undefined || seq == null) return false;
    if(listshow == true) {
        $("#notice-list").css({ display: 'block' });
        $("#notice-conts").css({ display: 'none' });
    } else {
        var noticeData          = getNoticeData(seq);
		var NoticeTitle 		= getParam(noticeData, 'title');
        var createDateTime		= getCstmFrmt(getParam(noticeData, 'reg_DT'), "yyyyMMddHHmmss", "yyyy-MM-dd HH:mm:ss");

        var noticeContent		= getParam(noticeData, 'contents').replace(/\r/gi, '<br>');
        $('#notice-conts-title').text(NoticeTitle);
        $('#notice-conts-title').append($('<span>' + createDateTime + '</span>'));
        $('#notice-detail').empty();
        $('#notice-detail').append($('<div><pre style="font-family: nanumsrr,noto sans kr,sans-serif; white-space:pre-wrap;">' + noticeContent + '</pre></div>'));

        $("#notice-list").css({ display: 'none' });
        $("#notice-conts").css({ display: 'block' });
    }
}

function getNoticeList() {
   	// Paging
   	__G_Notice_window = Pu.init({
        count: 20,
        PageCount: 5,
        draw: doSearch,
        button: '#notice-search',
        paginate: '#notice-pagination',
        initCllBck: true,
        type: 'search',
        initEventFun: initData
    });
}

function getNoticeData(seqNo) {
    if(__G_Notice_Cont == undefined || __G_Notice_Cont.length == 0) return null;

    for(var i=0; i<__G_Notice_Cont.length; i++) {
        var noticeSeq			= getParam(__G_Notice_Cont[i], 'rec_SEQ_NO');
        if(noticeSeq == seqNo) return __G_Notice_Cont[i];
    }
}

function initData(page, callback) {
	var p = {};
		
	p['exchangeId'] = 'YAHOBIT';
	p['pageNum'] = page;
	p['searchQry'] = $('#notice-search-text').val();
	
    ajaxOption({
        url: '/alibit/support/spt001/selectNoticeInitData',
        data: p,
        success: callback,
        //done: callback
    });
}
function doSearch(i, done) {
	var p = {};
		
	p['exchangeId'] = 'YAHOBIT';
	p['pageNum'] = i;
	p['searchQry'] = $('#notice-search-text').val();

    ajaxOption({
        url: '/alibit/support/spt001/selectNoticeList',
        data: p,
        success: draw,
        done: done
    });
}

function draw(data) {
	var noticeListJson = data;
	var noticeTemplate = document.getElementById("template-notice-list-table-tr");
	var noticeTemplateHtml = noticeTemplate.innerHTML;
	var noticeHtml = "";
    __G_Notice_Cont = data;       // 전역변수에 저장

    if(noticeListJson.length > 0) {
        $(noticeListJson).each(function(data_item) {
            var value				= noticeListJson[data_item];
            var noticeSeq			= getParam(value, 'rec_SEQ_NO');
            var noticeContent		= getParam(value, 'contents').replace(/\r/gi, '<br>');
            var NoticeTitle 		= getParam(value, 'title');
            var createDateTime		= getCstmFrmt(getParam(value, 'reg_DT'), "yyyyMMddHHmmss", "yyyy-MM-dd HH:mm:ss");
            var topDisplayYN		= getParam(value, 'top_DISP_YN');
            var urgentYN			= getParam(value, 'urgent_YN');
            var allYN				= getParam(value, 'target_ALL_YN');
    
            noticeHtml += noticeTemplateHtml.replace(/{{NoticeSeq}}/g, noticeSeq)
                                    .replace(/{{NoticeTitle}}/g, NoticeTitle)
                                    .replace(/{{NoticeDate}}/g, createDateTime)
                                    .replace(/{{ClassNew}}/g, '')
                                    .replace(/{{ClassER}}/g, urgentYN ? 'er' : '')
                                    .replace(/{{ClassTop}}/g, topDisplayYN == 1 ? 'bold' : '')
                                    ;
        });
        $('#notice_list_table_tbody').html(noticeHtml);
        
        if(noticeSeq != undefined) {
        	$('input[name=notice-seq][value=' + noticeSeq + ']').closest('tr').trigger('click');
        	noticeSeq = undefined;
        }
    } else 
        $('#notice_list_table_tbody').html($('#template-list-empty').html().replace(/{{ColSpan}}/g, 2) );
}
// 공지사항 TR 클릭이벤트 처리
$('#notice_list_table_tbody').delegate('tr', 'click', function (event) {
    var seqNo = $(this).find('td').eq(0).find('input').eq(0).val();
    toggleNoticeList(seqNo, false);
});

//////////////////////////////////////////////////////////////////////////////
//                           공지사항 리스트 처리 END
//////////////////////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////////////////////
//                           이벤트 리스트 처리
//////////////////////////////////////////////////////////////////////////////
var __G_Event_Cont;
var __G_Event_window;

function toggleEventList(seq, listshow) {
    if(listshow == true) {
        $("#event-list").css({ display: 'block' });
        $("#event-conts").css({ display: 'none' });
    } else {
        var eventData           = getEventData(seq);
		var eventTitle 		    = getParam(eventData, 'title');
        var createDateTime		= getCstmFrmt(getParam(eventData, 'reg_DT'), "yyyyMMddHHmmss", "yyyy-MM-dd HH:mm:ss");

        var eventContent		= getParam(eventData, 'EVENT_PAGE_CONTENTS');
        $('#event-conts-title').text(eventTitle);
        $('#event-conts-title').append($('<span>' + createDateTime + '</span>'));
        $('#event-detail').empty();
        $('#event-detail').append($('<div>' + eventContent + '</div>'));

        $("#event-list").css({ display: 'none' });
        $("#event-conts").css({ display: 'block' });
    }
}

function getEventList() {
   	// Paging
   	__G_Event_window = Pu.init({
        count: 20,
        PageCount: 5,
        draw: doSearchEvent,
        button: '#event-search',
        paginate: '#event-pagination',
        initCllBck: true,
        type: 'search',
        initEventFun: initDataEvent
    });
}

function getEventData(seqNo) {
    if(__G_Event_Cont.length == 0) return null;

    for(var i=0; i<__G_Event_Cont.length; i++) {
        var eventSeq = getParam(__G_Event_Cont[i], 'REC_SEQ_NO');
        if(eventSeq == seqNo) return __G_Event_Cont[i];
    }
}

function initDataEvent(page, callback) {
	var p = {};
		
	p['exchangeId'] = 'YAHOBIT';
	
    ajaxOption({
        url: '/alibit/support/spt001/selectEventInitData',
        data: p,
        success: callback,
        //done: done
    });
}
function doSearchEvent(i, done) {
	var p = {};
		
	p['exchangeId'] = 'YAHOBIT';
	p['pageNum'] = i;
	
    ajaxOption({
        url: '/alibit/support/spt001/selectEventList',
        data: p,
        success: drawEvent,
        done: done
    });
}

function drawEvent(data) {
	var eventListJson = data;
	var eventTemplate = document.getElementById("template-event-list-table-tr");
	var eventTemplateHtml = eventTemplate.innerHTML;
	var eventHtml = "";
    __G_Event_Cont = data;       // 전역변수에 저장

    if(eventListJson.length > 0) {
        $(eventListJson).each(function(data_item) {
            var value				= eventListJson[data_item];
            var eventSeq			= getParam(value, 'REC_SEQ_NO');
            var eventContent		= getParam(value, 'EVENT_PAGE_CONTENTS');
            var eventTitle 		    = getParam(value, 'TITLE');
            var createDateTime		= getCstmFrmt(getParam(value, 'REG_DT'), "yyyyMMddHHmmss", "yyyy-MM-dd HH:mm:ss");
    
            var eventStartDt        = du.getCstmFrmt(getParam(value, 'EVENT_START_DT'), 'yyyyMMddHHmmss', 'yyyy-MM-dd HH:mm:ss');
            var eventEndDt          = du.getCstmFrmt(getParam(value, 'EVENT_END_DT'), 'yyyyMMddHHmmss', 'yyyy-MM-dd HH:mm:ss');
            var curDt               = new Date();
            var onClose_class       = '';
            var onClose_Txt         = '종료';
            if(curDt > new Date(eventStartDt) && curDt < new Date(eventEndDt)) {
                onClose_class = 'on';
                onClose_Txt = '진행중';
            } else if(curDt < new Date(eventStartDt)) {
                onClose_class = 'on';
                onClose_Txt = '진행예정';
            }

            eventHtml += eventTemplateHtml.replace(/{{EventSeq}}/g, eventSeq)
                                    .replace(/{{EventTitle}}/g, eventTitle)
                                    .replace(/{{EventDate}}/g, createDateTime)
                                    .replace(/{{ClassOnClose}}/g, onClose_class)
                                    .replace(/{{OnCloseTxt}}/g, onClose_Txt)
                                    ;
        });
        $('#event_list_table_tbody').html(eventHtml);
        
        if(eventSeq != undefined) {
        	$('input[name=event-seq][value=' + eventSeq + ']').closest('tr').trigger('click');
        	eventSeq = undefined;
        }
    } else 
        $('#event_list_table_tbody').html($('#template-list-empty').html().replace(/{{ColSpan}}/g, 3) );
}
// 이벤트 TR 클릭이벤트 처리
$('#event_list_table_tbody').delegate('tr', 'click', function (event) {
    var seqNo = $(this).find('td').eq(0).find('input').eq(0).val();
    toggleEventList(seqNo, false);
});

//////////////////////////////////////////////////////////////////////////////
//                           이벤트 리스트 처리 END
//////////////////////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////////////////////
//                           이용안내 리스트 처리
//////////////////////////////////////////////////////////////////////////////
var __G_MinOrderAmt_KRW_window;
var __G_MinOrderAmt_BTC_window;

function getMinOrderAmtList() {
    // Paging
    __G_MinOrderAmt_KRW_window = Pu.init({
        count: 20,
        PageCount: 5,
        draw: doMinOrdAmt_KRW,
        paginate: '#min-order-amount-krw-pagination',
        initCllBck: true,
        type: 'search',
        initEventFun: initDataMinOrdAmtKRW
    });
    __G_MinOrderAmt_BTC_window = Pu.init({
        count: 20,
        PageCount: 5,
        draw: doMinOrdAmt_BTC,
        paginate: '#min-order-amount-btc-pagination',
        initCllBck: true,
        type: 'search',
        initEventFun: initDataMinOrdAmtBTC
    });
}

function initDataMinOrdAmtKRW(page, callback) {
	var p = {};
		
	p['basicAssetCoinNo'] = 10;
	
    ajaxOption({
        url: '/alibit/support/spt001/selectCoinCodeMasterInitData',
        data: p,
        success: callback,
    });
}
function initDataMinOrdAmtBTC(page, callback) {
	var p = {};
		
	p['basicAssetCoinNo'] = 430;
	
    ajaxOption({
        url: '/alibit/support/spt001/selectCoinCodeMasterInitData',
        data: p,
        success: callback,
    });
}
function doMinOrdAmt_KRW(i, done) {
	var p = {};
		
	p['basicAssetCoinNo'] = 10;
	p['pageNum'] = i;
	
    ajaxOption({
        url: '/alibit/support/spt001/selectCoinCodeMasterList',
        data: p,
        success: drawMinOrdAmt_KRW,
        done: done
    });
}
function doMinOrdAmt_BTC(i, done) {
	var p = {};
		
	p['basicAssetCoinNo'] = 430;
	p['pageNum'] = i;
	
    ajaxOption({
        url: '/alibit/support/spt001/selectCoinCodeMasterList',
        data: p,
        success: drawMinOrdAmt_BTC,
        done: done
    });
}

function drawMinOrdAmt_KRW(data) {
	var minOrdAmtListJson = data;
	var minOrdAmtTemplate = document.getElementById("template-min-order-amount-table-tr");
	var minOrdAmtTemplateHtml = minOrdAmtTemplate.innerHTML;
	var minOrdAmtHtml = "";

    if(minOrdAmtListJson.length > 0) {
        $(minOrdAmtListJson).each(function(data_item) {
            var value				= minOrdAmtListJson[data_item];
            var itemName			= getParam(value, 'item_NM');
            var itemEngName 		= getParam(value, 'item_ENG_NM');
            var minOrdAmount 		= getParam(value, 'min_ORD_AMT');
    
            minOrdAmtHtml += minOrdAmtTemplateHtml.replace(/{{ItmeName}}/g, itemName)
                                    .replace(/{{ItemEngName}}/g, itemEngName)
                                    .replace(/{{MinOrdAmt}}/g, minOrdAmount)
                                    .replace(/{{ItemBaseCoinName}}/g, 'KRW')
                                    ;
        });
        $('#min-order-amount-krw-tbody').html(minOrdAmtHtml);
    } else 
        $('#min-order-amount-krw-tbody').html($('#template-list-empty').html());
}

function drawMinOrdAmt_BTC(data) {
	var minOrdAmtListJson = data;
	var minOrdAmtTemplate = document.getElementById("template-min-order-amount-table-tr");
	var minOrdAmtTemplateHtml = minOrdAmtTemplate.innerHTML;
	var minOrdAmtHtml = "";

    if(minOrdAmtListJson.length > 0) {
        $(minOrdAmtListJson).each(function(data_item) {
            var value				= minOrdAmtListJson[data_item];
            var itemName			= getParam(value, 'item_NM');
            var itemEngName 		= getParam(value, 'item_ENG_NM');
            var minOrdAmount 		= getParam(value, 'min_ORD_AMT');
    
            minOrdAmtHtml += minOrdAmtTemplateHtml.replace(/{{ItmeName}}/g, itemName)
                                    .replace(/{{ItemEngName}}/g, itemEngName)
                                    .replace(/{{MinOrdAmt}}/g, minOrdAmount)
                                    .replace(/{{ItemBaseCoinName}}/g, 'YEP')
                                    ;
        });
        $('#min-order-amount-btc-tbody').html(minOrdAmtHtml);
    } else 
        $('#min-order-amount-btc-tbody').html($('#template-list-empty').html());
}

//////////////////////////////////////////////////////////////////////////////
//                           이용안내 리스트 처리 END
//////////////////////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////////////////////
//                           수수료안내 리스트 처리
//////////////////////////////////////////////////////////////////////////////
var __G_Trading_Fee_window;
var __G_DW_Fee_window;

function getFeeList() {
    // Paging
    __G_Trading_Fee_window = Pu.init({
        count: 20,
        PageCount: 5,
        draw: doTradingFee,
        paginate: '#trading-fee-pagination',
        initCllBck: true,
        type: 'search',
        initEventFun: initDataTradingFee
    });
    __G_DW_Fee_window = Pu.init({
        count: 20,
        PageCount: 5,
        draw: doDepositWithdrawFee,
        paginate: '#deposit-withdraw-fee-pagination',
        initCllBck: true,
        type: 'search',
        initEventFun: initDataDepositWithdrawFee
    });
}

function initDataTradingFee(page, callback) {
	var p = {};
		
	p['exchangeId'] = 'YAHOBIT';
	
    ajaxOption({
        url: '/alibit/support/spt001/selectExchangeMarketInfoInitData',
        data: p,
        success: callback,
    });
}
function doTradingFee(i, done) {
	var p = {};
		
	p['exchangeId'] = 'YAHOBIT';
	p['pageNum'] = i;
	
    ajaxOption({
        url: '/alibit/support/spt001/selectExchangeMarketInfoList',
        data: p,
        success: drawTradingFee,
        done: done
    });
}

function drawTradingFee(data) {
	var tradingFeeListJson = data;
	var tradingFeeTemplate = document.getElementById("template-trading-fee-table-tr");
	var tradingFeeTemplateHtml = tradingFeeTemplate.innerHTML;
	var tradingFeeHtml = "";

    if(tradingFeeListJson.length > 0) {
        $(tradingFeeListJson).each(function(data_item) {
            var value				= tradingFeeListJson[data_item];
            var marketName			= getParam(value, 'mkt_NM');
            var sellFeeVal 		    = getParam(value, 'sell_FEE_VAL');
            var buyFeeVal 		    = getParam(value, 'buy_FEE_VAL');

			marketName = (marketName == 'KRW') ? '메이저마켓(KRW 기축)' : ((marketName == 'YEP') ? '프리마켓(YEP 기축)' : marketName + ' 마켓');
            tradingFeeHtml += tradingFeeTemplateHtml.replace(/{{MarketName}}/g, marketName)
                                    .replace(/{{SellFee}}/g, sellFeeVal)
                                    .replace(/{{BuyFee}}/g, buyFeeVal)
                                    ;
        });
        $('#trading-fee-tbody').html(tradingFeeHtml);
    } else 
        $('#trading-fee-tbody').html($('#template-list-empty').html());
}

function initDataDepositWithdrawFee(page, callback) {
	var p = {};
		
	p['pageNum'] = page;
	
    ajaxOption({
        url: '/alibit/support/spt001/selectCoinMgtRefInfoListInitData',
        data: p,
        success: callback,
    });
}
function doDepositWithdrawFee(i, done) {
	var p = {};
		
	p['pageNum'] = i;
	
    ajaxOption({
        url: '/alibit/support/spt001/selectCoinMgtRefInfoList',
        data: p,
        success: drawDepositWithdrawFee,
        done: done
    });
}

function drawDepositWithdrawFee(data) {
	var depositWithdrawFeeListJson = data;
	var depositWithdrawFeeTemplate = document.getElementById("template-deposit-withdraw-fee-table-tr");
	var depositWithdrawFeeTemplateHtml = depositWithdrawFeeTemplate.innerHTML;
	var depositWithdrawFeeHtml = "";

    if(depositWithdrawFeeListJson.length > 0) {
        $(depositWithdrawFeeListJson).each(function(data_item) {
            var value				= depositWithdrawFeeListJson[data_item];
            var coinSymbolName		= getParam(value, 'coin_SYMBOLIC_NM');
            var minDepositQty	    = getParam(value, 'min_DEPOSIT_QTY');
            var minWtdrwQty		    = getParam(value, 'min_WTDRW_QTY');
            var WtdrwFee 		    = getParam(value, 'wtdrw_FEE');
    
            if(coinSymbolName !== 'YEP') {
				minDepositQty = (minDepositQty == '0') ? '제한없음' : nu.cm(minDepositQty, 4);
            	depositWithdrawFeeHtml += depositWithdrawFeeTemplateHtml.replace(/{{CoinSymbolName}}/g, coinSymbolName)
                .replace(/{{MinDepositQty}}/g, minDepositQty )
                .replace(/{{WtdrwFee}}/g, nu.cm(WtdrwFee, 4) )
                .replace(/{{MinWtdrwQty}}/g, nu.cm(minWtdrwQty, 4) )
                ;
            } else {
            	depositWithdrawFeeHtml += '<tr><td>' + coinSymbolName + '</td><td colspan="3">입출금준비중</td></tr>'; 
            }
        });
        $('#deposit-withdraw-fee-tbody').html(depositWithdrawFeeHtml);
    } else 
        $('#deposit-withdraw-fee-tbody').html($('#template-list-empty').html());
}

//////////////////////////////////////////////////////////////////////////////
//                           수수료안내 리스트 처리 END
//////////////////////////////////////////////////////////////////////////////


//////////////////////////////////////////////////////////////////////////////
//1:1 문의 처리
//////////////////////////////////////////////////////////////////////////////
var __G_Inquiry_Main_Cate;
var __G_Inquiry_Main_Cate_Txt;
var __G_Inquiry_Sub_Cate;
var __G_Inquiry_Sub_Cate_Txt;

//문의에 대한 제목과 내용을 담고있는 템플릿
var __G_Inquire_Category_Template_Map = new simpleMap();
//계정 문의
__G_Inquire_Category_Template_Map.put('sub-accnt-01', {'attachfile': true, 'title': '휴대전화 번호 초기화 요청', 'content': '\no 휴대폰번호 : \n\no 생년월일 6자리: '});
__G_Inquire_Category_Template_Map.put('sub-accnt-02', {'attachfile': true, 'title': '계정복구 요청', 'content': '\no 휴대폰번호 : \n\no 생년월일 6자리: '});
__G_Inquire_Category_Template_Map.put('sub-accnt-03', {'attachfile': true, 'title': '이름 변경 요청', 'content': '\no 이름 : \n\no 생년월일 6자리: \n\no 사유 : '});
__G_Inquire_Category_Template_Map.put('sub-accnt-04', {'attachfile': true, 'title': '회원탈퇴 문의', 'content': '\no 내용 : '});
__G_Inquire_Category_Template_Map.put('sub-accnt-05', {'attachfile': true, 'title': 'APT문의', 'content': '\no 내용 : '});
//알림톡 문의
__G_Inquire_Category_Template_Map.put('sub-alimtok-01', {'attachfile': true, 'title': '알림톡 문의', 'content': '\no 내용 : '});
//카카오페이 문의
__G_Inquire_Category_Template_Map.put('sub-cacaopay-01', {'attachfile': true, 'title': '카카오페이 문의', 'content': '\no 내용 : '});
//입출금 문의
__G_Inquire_Category_Template_Map.put('depwitd', {'attachfile': true, 'title': '입출금 문의', 'content': '\no 내용 : '});
//매매장애 문의
__G_Inquire_Category_Template_Map.put('sbprob', {'attachfile': true, 'title': '매매장애 문의', 'content': '\no 내용 : '});
//제안
__G_Inquire_Category_Template_Map.put('sub-request-01', {'attachfile': true, 'title': '서비스 개선 제안', 'content': '\no 내용 : '});
__G_Inquire_Category_Template_Map.put('sub-request-02', {'attachfile': true, 'title': '투자 제안', 'content': '\no 내용 : '});
__G_Inquire_Category_Template_Map.put('sub-request-03', {'attachfile': true, 'title': '제휴 문의', 'content': '\no 내용 : '});
//일반
__G_Inquire_Category_Template_Map.put('sub-general-01', {'attachfile': true, 'title': '암호화폐 기타 문의', 'content': '\no 내용 : '});
__G_Inquire_Category_Template_Map.put('sub-general-02', {'attachfile': true, 'title': '기타 문의', 'content': '\no 내용 : '});
__G_Inquire_Category_Template_Map.put('sub-general-03', {'attachfile': true, 'title': '출금정지 이의 신청', 'content': '\no 내용 : '});
__G_Inquire_Category_Template_Map.put('sub-general-04', {'attachfile': true, 'title': '이벤트 지급관련 문의', 'content': '\no 내용 : '});
//금융사고
__G_Inquire_Category_Template_Map.put('sub-bankaccd-01', {'attachfile': true, 'title': '수사/금융/공공기관 문의', 'content': '\no 내용 : '});
__G_Inquire_Category_Template_Map.put('sub-bankaccd-02', {'attachfile': true, 'title': '해킹/명의도용/보이스피싱 문의', 'content': '\no 내용 : '});
__G_Inquire_Category_Template_Map.put('sub-bankaccd-03', {'attachfile': true, 'title': '다단계 신고/불량사용자 신고', 'content': '\no 내용 : '});
__G_Inquire_Category_Template_Map.put('sub-bankaccd-04', {'attachfile': true, 'title': '금융사기계정 거래 정지 해제 문의', 'content': '\no 내용 : '});
//오입금
__G_Inquire_Category_Template_Map.put('sub-misdeposit-01', {'attachfile': true, 'title': 'BTC계열 오입금', 'content': '\no 내용 : '});
__G_Inquire_Category_Template_Map.put('sub-misdeposit-02', {'attachfile': true, 'title': 'ERC20(상장)을 ETH지갑주소로 오입금', 'content': '\no 내용 : '});
__G_Inquire_Category_Template_Map.put('sub-misdeposit-03', {'attachfile': true, 'title': 'ERC20(상장)을 ETC지갑주소로 오입금', 'content': '\no 내용 : '});
__G_Inquire_Category_Template_Map.put('sub-misdeposit-04', {'attachfile': true, 'title': 'ERC20(상장)을 ERC20지갑주소로 오입금', 'content': '\no 내용 : '});
__G_Inquire_Category_Template_Map.put('sub-misdeposit-05', {'attachfile': true, 'title': 'ERC20(비상장)을 ETH지갑주소로 오입금', 'content': '\no 내용 : '});
__G_Inquire_Category_Template_Map.put('sub-misdeposit-06', {'attachfile': true, 'title': 'ERC20(비상장)을 ETC지갑주소로 오입금', 'content': '\no 내용 : '});
__G_Inquire_Category_Template_Map.put('sub-misdeposit-07', {'attachfile': true, 'title': 'ERC20(비상장)을 ERC20지갑주소로 오입금', 'content': '\no 내용 : '});
__G_Inquire_Category_Template_Map.put('sub-misdeposit-08', {'attachfile': true, 'title': 'ETC를 ETH지갑주소로 오입금', 'content': '\no 내용 : '});
__G_Inquire_Category_Template_Map.put('sub-misdeposit-09', {'attachfile': true, 'title': 'ETH를 ETC지갑주소로 오입금', 'content': '\no 내용 : '});
__G_Inquire_Category_Template_Map.put('sub-misdeposit-10', {'attachfile': true, 'title': 'ICO코인을 ETH지갑주소로 오입금', 'content': '\no 내용 : '});
__G_Inquire_Category_Template_Map.put('sub-misdeposit-11', {'attachfile': true, 'title': '수수료납부', 'content': '\no 내용 : '});
//이벤트
__G_Inquire_Category_Template_Map.put('sub-event-01', {'attachfile': true, 'title': '이벤트 문의', 'content': '\no 내용 : '});
__G_Inquire_Category_Template_Map.put('sub-event-02', {'attachfile': true, 'title': '이벤트 문의', 'content': '\no 내용 : '});
//출금신청
__G_Inquire_Category_Template_Map.put('sub-withdrawrequest-01', {'attachfile': true, 'title': '인증자료 제출', 'content': '\no 내용 : '});
//바로출금
__G_Inquire_Category_Template_Map.put('sub-withdrawrightnow-01', {'attachfile': true, 'title': '기타', 'content': '\no 내용 : '});

//1:1문의 유형 TR 클릭이벤트 처리
$('#inquriy-sub-category').delegate('li', 'click', function (event) {
	__G_Inquiry_Sub_Cate = $(this).attr('val');
	__G_Inquiry_Sub_Cate_Txt = $(this).text();
	inputRequiry();
});
$('.coin-category').delegate('li', 'click', function (event) {
	__G_Inquiry_Sub_Cate_Txt = $(this).text();
	inputRequiry(__G_Inquiry_Sub_Cate_Txt);
});

$('.main-category').delegate('li', 'click', function (event) {
	$('.main-category li ').each(function(){
		$(this).attr('class', '');
		$(this).show();
	});
	$(this).attr('class', 'on');

	$('#inquriy-main-category-title').show();
	$('#inquriy-sub-category').show();
	$('#inquriy-coin-category').hide();
	$('#inquiry-input-div').hide();
	
	$('#inquriy-sub-category .sub-category').each(function(){
		$(this).hide();
	});
	$('.' + $(this).attr('val')).each(function(){
		$(this).show();
	});

	__G_Inquiry_Main_Cate = $(this).attr('val');
	__G_Inquiry_Main_Cate_Txt = $(this).text();
	__G_Inquiry_Sub_Cate = '';
	__G_Inquiry_Sub_Cate_Txt = '';
	
	if(__G_Inquiry_Main_Cate == 'depwitd' || __G_Inquiry_Main_Cate == 'sbprob' ) {
		__G_Inquiry_Sub_Cate = __G_Inquiry_Main_Cate;
		$('#inquriy-sub-category').hide();
		$('#inquriy-coin-category').show();
	}
});
//상황에 따른 div show/hide
function inputRequiry(title) {
	$('#inquriy-main-category-title').hide();
	$('#inquriy-main-category').hide();
	$('#inquriy-sub-category').hide();
	$('#inquriy-coin-category').hide();
	$('#inquiry-input-div').show();
	
	$('#inquiry-input-main-cate').text(__G_Inquiry_Main_Cate_Txt);
	$('#inquiry-input-sub-cate').text(__G_Inquiry_Sub_Cate_Txt);
	
	let template = __G_Inquire_Category_Template_Map.get(__G_Inquiry_Sub_Cate);
	if(template.attachfile) $('#inquiry-input-attach-file').show();
	else $('#inquiry-input-attach-file').hide();
	
	if(title === undefined) {
		$('#inquiry-input-title').val(template.title);
	} else {
		$('#inquiry-input-title').val(title);
	}
	
	$('#inquiry-input-content').val(template.content);
	
	if(__G_Inquiry_Sub_Cate === 'sub-accnt-03') {
		$('.sub-accnt-03').show();
	} else {
		$('.sub-accnt-03').hide();
	}
}

//1:1 문의 취소
function cancelInquiry() {
	$('#inquriy-main-category-title').show();
	$('#inquriy-main-category').show();
	$('#inquriy-sub-category').hide();
	$('#inquriy-coin-category').hide();
	$('#inquiry-input-div').hide();
	$('#inquiry-input-title').val('');
	$('#inquiry-input-content').val('');
	$('#inquriy-coin-category-search').val('');
	$('.coin-category li').each(function(){$(this).attr('style', 'display: block !important;');});
	$('.main-category li ').each(function(idx){ $(this).attr('class', ''); });
	$('.main-category li ').each(function(){$(this).attr('style', 'display: block !important;');});
	
	$('.filebox-item').data('index', 0).html('');
	$('.filebox').children('input').remove();
	$('.filebox').append('<input type="file" id="ex_filename" class="upload-hidden">');
	$('#attachFile').attr('for', 'ex_filename');
	$('#agree').prop('checked', false);
}

//1:1 문의 등록 확인 confirm function
function requestInquiry() {
	var inquiryTitle	= $('#inquiry-input-title').val();
	var inquiryContent	= $('#inquiry-input-content').val();
	
	if(inquiryTitle == '') {
		lrt.client('문의 제목을 입력하세요.', '오류', function() {
			$('#inquiry-input-title').focus();
		}, null);
		return false;
	}
	if(inquiryContent == '') {
		lrt.client('문의 내용을 입력하세요.', '오류', function() {
			$('#inquiry-input-content').focus();
		}, null);
		return false;
	}
	if(__G_Inquiry_Sub_Cate === 'sub-accnt-03' && !$('#agree').is(':checked')) {
		lrt.client('개인정보 수집 및 이용에 동의하셔야 합니다.', '오류');
		return false;
	}
	
	if($('#inquiry-input-attach-file').css('display') === 'none') {
		var paramMap = {
		inquiryTitle : inquiryTitle
		, inquiryContent : inquiryContent
		, mainCategory : __G_Inquiry_Main_Cate
		, subCategory : __G_Inquiry_Sub_Cate
		};
		lrt.confirm('1:1 문의를 등록 하시겠습니까?', '1:1 문의 등록', processInquiry, paramMap);
	} else {
		var formData = new FormData();
		formData.append('inquiryTitle', inquiryTitle);
		formData.append('inquiryContent', inquiryContent);
		formData.append('mainCategory', __G_Inquiry_Main_Cate);
		formData.append('subCategory', __G_Inquiry_Sub_Cate);

		$('.filebox').children('input').each(function() {
			if(this.files[0] != undefined) {
				formData.append('file', this.files[0]);
			}
		});
	
		lrt.confirm('1:1 문의를 등록 하시겠습니까?', '1:1 문의 등록', processInquiryFile, formData);
	}
}

function processInquiry(paramMap) {
	ajaxOption({
		url: '/alibit/about/abt001/insertInquiry',
		data: paramMap,
		beforeSend : function() {
			loading.show();
		},
		error: function(error) {
			console.log('1:1문의 등록 Error >> ' + JSON.stringify(error, null, 2));
		},
		success: function(data) {
			console.log(data);
			lrt.client('성공적으로 등록되었습니다.', '1:1문의 등록 성공', function() {
				cancelInquiry();
				// 문의내역 리스트 갱신
				getInquiryList();
			}, null);
			return false;
		},
		complete : function(data) {
			loading.hide();
		}
	});
}
//coin category 검색 keyup 이벤트 처리
$('#inquriy-coin-category-search').on('keyup', function (event) {
	let search_word = $(this).val();
	console.log($('.contact li').css('display'));
	$('.coin-category li').each(function(){
		if( $(this).text().toLowerCase().indexOf(search_word) > -1 || $(this).data('cho').indexOf(Hangul.disassemble(search_word).join('')) > -1 ) {
			$(this).attr('style', 'display: block !important;');
		} else {
			$(this).attr('style', 'display: none !important;');
		}
	});
});

function processInquiryFile(paramMap) {
	ajaxOption({
		url: '/alibit/about/abt001/insertInquiryFile',
		data: paramMap,
		processData: false,
		contentType: false,
		error: function(error) {
			console.log('1:1문의 등록 Error >> ' + JSON.stringify(error, null, 2));
		},
		success: function(data) {
			console.log(data);
			lrt.client('성공적으로 등록되었습니다.', '1:1문의 등록 성공', function() {
				cancelInquiry();
				// 문의내역 리스트 갱신
				getInquiryList();
			}, null);
			return false;
		},
	});
}

function isPossibleSize() {
	let size = 0;
	$('.filebox').children('input').each(function() {
		if(this.files[0] != undefined) {
			size += this.files[0].size;
		}
	});
	
	if( (size/1024/1024) > 50 ) {
		return false;
	} else {
		return true;
	}
}
//////////////////////////////////////////////////////////////////////////////
//1:1 문의 처리 END
//////////////////////////////////////////////////////////////////////////////


//////////////////////////////////////////////////////////////////////////////
//1:1 문의내역 리스트 처리
//////////////////////////////////////////////////////////////////////////////
var __G_Inquiry_Cont;
var __G_Inquiry_window;

//문의내용분류코드에 따른 코드값 Map
var __G_Inquire_CategoryMap = new simpleMap();
__G_Inquire_CategoryMap.put('1', 'API관리');
__G_Inquire_CategoryMap.put('2', '휴대폰번호변경');
__G_Inquire_CategoryMap.put('3', '회원가입/탈퇴');
__G_Inquire_CategoryMap.put('4', '비밀번호변경');
__G_Inquire_CategoryMap.put('5', '수수료');
__G_Inquire_CategoryMap.put('6', '인증레벨설정');
__G_Inquire_CategoryMap.put('7', '인증자료제출');
__G_Inquire_CategoryMap.put('8', '코인매수/매도');
__G_Inquire_CategoryMap.put('9', '코인입출금');
__G_Inquire_CategoryMap.put('99','기타');

function toggleInquiryList(seq) {
	var inquiryData			= getInquiryData(seq);
	var inquiryTitle		= getParam(inquiryData, 'INQUIRY_TITLE', '');
	var createDateTime		= getCstmFrmt(getParam(inquiryData, 'REG_DT'), "yyyyMMddHHmmss", "yyyy-MM-dd HH:mm:ss");
	var inquireCategory		= parseInt(getParam(inquiryData, 'INQUIRY_TYPE_CD', '1'));
	var categoryTxt			= __G_Inquire_CategoryMap.get('' + inquireCategory);
	var inquiryContent		= getParam(inquiryData, 'INQUIRY_CONTENTS', '').replace(/(?:\r\n|\r|\n)/g, '<br>');
	
	var p = {};
	
	p['iInquiryNo'] = seq;
	
	ajaxOption({
		url: '/alibit/about/abt001/selectInquiryReplyList',
		data: p,
		success: function(data) {
			$('#inquire-category').text(categoryTxt);
			$('#inquiry-conts-title').text(inquiryTitle);
			$('#inquiry-conts').html($('<span>' + inquiryContent + '</span>'));
			
			if(data != undefined && data.length > 0) {
				var replyData			= data[0];
				var replyContent		= getParam(replyData, 'REPLY_CONTENTS', '').replace(/(?:\r\n|\r|\n)/g, '<br>');
				$('#reply-conts').html($('<span>' + replyContent + '</span>'));
			}
			
			$('#inquire-reply-dont-select').hide();
		}
		//done: callback
	});

}

function getInquiryList() {
	// Paging
	__G_Inquiry_window = Pu.init({
		count: 20,
		PageCount: 5,
		draw: doSearchInquiry,
		button: '#inquiry-search',
		paginate: '#inquiry-pagination',
		initCllBck: true,
		type: 'search',
		initEventFun: initDataInquiry
	});
}

function getInquiryData(seqNo) {
	if(__G_Inquiry_Cont.length == 0) return null;
	
	for(var i=0; i<__G_Inquiry_Cont.length; i++) {
		var inquirySeq	= getParam(__G_Inquiry_Cont[i], 'INQUIRY_NO');
		if(inquirySeq == seqNo) return __G_Inquiry_Cont[i];
	}
}

function initDataInquiry(page, callback) {
	var p = {};
	
	p['exchangeId'] = 'YAHOBIT';
	p['pageNum'] = page;
	
	ajaxOption({
		url: '/alibit/about/abt001/selectInquiryListCount',
		data: p,
		success: callback,
		//done: callback
	});
}
function doSearchInquiry(i, done) {
	var p = {};
	
	p['exchangeId'] = 'YAHOBIT';
	p['pageNum'] = i;
	
	ajaxOption({
		url: '/alibit/about/abt001/selectInquiryList',
		data: p,
		success: drawInquiry,
		done: done
	});
}

function drawInquiry(data) {
	var inquiryListJson = data;
	var inquiryTemplate = document.getElementById("template-inquiry-list-table-tr");
	var inquiryTemplateHtml = inquiryTemplate.innerHTML;
	var inquiryHtml = "";
	__G_Inquiry_Cont = data;       // 전역변수에 저장
	
	var __first_tr_seq = 0;
	if(inquiryListJson.length > 0) {
		$(inquiryListJson).each(function(data_item) {
			var value				= inquiryListJson[data_item];
			var rowNum				= getParam(value, 'ROWNUM');
			var inquirySeq			= getParam(value, 'INQUIRY_NO');
			var inquiryContent		= getParam(value, 'INQUIRY_CONTENTS', '').replace(/(?:\r\n|\r|\n)/g, '<br>');
			var replyContent		= getParam(value, 'REPLY_CONTENTS', '').replace(/(?:\r\n|\r|\n)/g, '<br>');
			var InquiryTitle 		= getParam(value, 'INQUIRY_TITLE', '');
			var createDateTime		= getCstmFrmt(getParam(value, 'REG_DT'), "yyyyMMddHHmmss", "yyyy-MM-dd HH:mm:ss");
			var replyDateTime		= getCstmFrmt(getParam(value, 'REPLY_DT', ''), "yyyyMMddHHmmss", "yyyy-MM-dd HH:mm:ss");
			
			var classActive			= '';
			var replyStatus			= ( replyContent == undefined || replyContent == '' ) ? '대기중' : '답변완료';
			
			if(data_item == 0) __first_tr_seq = inquirySeq;
			
			inquiryHtml += inquiryTemplateHtml.replace(/{{InquirySeq}}/g, inquirySeq)
												.replace(/{{RowNum}}/g, rowNum)
												.replace(/{{InquiryTitle}}/g, InquiryTitle)
												.replace(/{{RequestDT}}/g, createDateTime)
												.replace(/{{ReplyDT}}/g, replyDateTime)
												.replace(/{{ClassActive}}/g, classActive)
												.replace(/{{ReplyStatus}}/g, replyStatus)
												;
		});
		$('#inquiry_list_table_tbody').html(inquiryHtml);
		toggleInquiryList(__first_tr_seq);
	} else {
		$('#inquiry_list_table_tbody').html($('#template-list-empty').html().replace(/{{ColSpan}}/g, 5) );
	}
	$('#inquiry_list_table_tbody').find('tr').eq(0).attr('class', 'active');
}
//공지사항 TR 클릭이벤트 처리
$('#inquiry_list_table_tbody').delegate('tr', 'click', function (event) {
	$('#inquiry_list_table_tbody tr ').each(function(){
		$(this).attr('class', '');
	});
	$(this).attr('class', 'active');
	var seqNo = $(this).data('seq');
	toggleInquiryList(seqNo);
});

//////////////////////////////////////////////////////////////////////////////
//1:1 문의내역 리스트 처리 END
//////////////////////////////////////////////////////////////////////////////
function getOutLimit() {
	ajaxOption({
		url: '/alibit/support/spt001/getOutLimit',
		success: function(data) {
			if(data.length > 0) {
				for(let i=0; i<data.length; i++) {
					if(data[i].authLevel == 2 
							|| data[i].authLevel == 3
							|| data[i].authLevel == 4) {
						
						if(data[i].dailyLimitQty > 10000) {
							$('#level' + data[i].authLevel + '_once').text(nu.cm(data[i].maxOnceWthrwQty/10000, data[i].pnt) + '만원')
							$('#level' + data[i].authLevel + '_day').text(nu.cm(data[i].dailyLimitQty/10000, data[i].pnt) + '만원')
						} else {
							$('#level' + data[i].authLevel + '_once').text(nu.cm(data[i].maxOnceWthrwQty, data[i].pnt) + '원')
							$('#level' + data[i].authLevel + '_day').text(nu.cm(data[i].dailyLimitQty, data[i].pnt) + '원')
						}
					}
				}

				$('#level1_once').text('-');
				$('#level5_once').text('별도 심사');
				$('#level1_day').text('-');
				$('#level5_day').text('별도 심사');
			}
		}
	});
}
