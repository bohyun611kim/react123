//코인관리기준정보를 Map으로 들고있는 전역변수
var __G_CoinMgtRef_Map = new simpleMap();

////////////////////////////////////
// WALLET-TRANSACTION.01 코인 입출금내역 리스트 페이징 처리
var __G_Coin_DW_List_window;
var __G_Coin_DW_List_Item;
function getDepositWithdrawalList(coinNo) {
    // Paging
    __G_Coin_DW_List_window = Pu.init({
        count: 10,
        PageCount: 5,
        draw: getDepositWithdrawalListData,
        button: '#wallet-transaction-search-btn',
        paginate: '#wallet-transaction-dw-list-pagination',
        totalText: '#wallet-transaction-dw-list-total-count',
        initCllBck: true,
        type: 'search',
        initEventFun: initDepositWithdrawalListData
    });
}

function initDepositWithdrawalListData(page, callback) {
	var p = {};

    var d_checked = $("input:checkbox[id='wallet-transaction-category-checkbox-deposit']").is(":checked");
    var w_checked = $("input:checkbox[id='wallet-transaction-category-checkbox-withdrawal']").is(":checked");
    var reqTypeCd = ((!d_checked && !w_checked) || (d_checked && w_checked)) ? 9999 : ( (d_checked && !w_checked) ? 1 : 2 );

	p['coinNo'] = $('#wallet-transaction-coin-list-select').val();
	p['dwReqTypeCd'] = reqTypeCd;
	p['startDate'] = $("#wallet-transaction-datepicker-startDate").val();
	p['endDate'] = $("#wallet-transaction-datepicker-endDate").val();
	p['pageNum'] = page;
    p['srchOpt'] = 0b111;     // 조회옵션 (추후 완료/진행중/대기 건 검색을 위해 옵션추가)

    ajaxOption({
        url: '/coinis/wallet/wlt002/selectCoinDepositWithdrawalListCount',
        data: p,
        success: callback,
        //done: done
    });
}
function getDepositWithdrawalListData(i, done) {
    var p = {};
    
    var d_checked = $("input:checkbox[id='wallet-transaction-category-checkbox-deposit']").is(":checked");
    var w_checked = $("input:checkbox[id='wallet-transaction-category-checkbox-withdrawal']").is(":checked");
    var reqTypeCd = ((!d_checked && !w_checked) || (d_checked && w_checked)) ? 9999 : ( (d_checked && !w_checked) ? 1 : 2 );

	p['coinNo'] = $('#wallet-transaction-coin-list-select').val();
	p['dwReqTypeCd'] = reqTypeCd;
	p['startDate'] = $("#wallet-transaction-datepicker-startDate").val();
	p['endDate'] = $("#wallet-transaction-datepicker-endDate").val();
	p['pageNum'] = i;
    p['srchOpt'] = 0b111;     // 조회옵션 (추후 완료/진행중/대기 건 검색을 위해 옵션추가)

    ajaxOption({
        url: '/coinis/wallet/wlt002/selectCoinDepositWithdrawalList',
        data: p,
        success: drawDepositWithdrawalList,
        done: done
    });
}

////////////////////////////////////
// COIN.T.03-1 코인 입출금내역 >> 입출금 리스트 그려주는 메서드
function drawDepositWithdrawalList(depositWithdrawListData) {
	var depositWithdrawListJson = depositWithdrawListData;
	var depositWithdrawListTemplate = document.getElementById("template-wallet-transaction-dw-list-tr");
	var depositWithdrawListTemplateHtml = depositWithdrawListTemplate.innerHTML;
	var depositWithdrawListHtml = "";
    __G_Coin_DW_List_Item = depositWithdrawListData;       // 전역변수에 저장

    if(depositWithdrawListJson.length > 0) {
        $(depositWithdrawListJson).each(function(data_item) {
            var value				        = depositWithdrawListJson[data_item];
            var reqSeqNo                    = getParam(value, 'REQ_SEQ_NO');
            var reqTypeCd                   = '' + getParam(value, 'DW_REQ_TYPE_CD', '1');
            var reqDateTime                 = du.getCstmFrmt(getParam(value, 'REQ_STAT_PROC_DT'), 'yyyyMMddHHmmssSSSSSS', 'yyyy-MM-dd HH:mm:ss');

            var CoinSymbolicName            = getParam(value, 'COIN_SYMBOLIC_NM', '');
            var reqQty                      = getParam(value, 'REQ_QTY');
            var fee                         = (reqTypeCd == '1') ? '0' : nu.js(parseFloat(getParam(value, 'WTDRW_FEE', '0')), 6); // 입금 : 0, 출금 : WTDRW_FEE
            var address                     = getParam(value, 'TARGET_ADDR');
            var reqStatProcCd               = getParam(value, 'REQ_STAT_PROC_CD');
    
            var gubunText                   = (reqTypeCd == '1') ? 'Deposit' : 'Withdrawal';
            var gubunColor                  = (reqTypeCd == '1') ? 'red' : 'blue';

            var procStatCd                  = getParam(value, 'DW_PROC_STAT_CD');
            var procStatTxt = '';
            switch(reqStatProcCd) {
                case 1: procStatTxt = 'Deposit Request'; break;
                case 2: procStatTxt = 'Confirmation Checking'; break;
                case 3: procStatTxt = 'Waiting withdrawal auth'; break;
                case 4: procStatTxt = 'Waiting ' + ((reqTypeCd == '1') ? 'Deposit':'Withdrawal') + ' approval'; break;
                case 5: procStatTxt = 'Request Canceled'; break;
            }
            if(reqStatProcCd == 4 && procStatCd == 1) {
                procStatTxt = 'Finished';
            }

            var cancelButtonHTML            = '';
            // 출금이면서 출금 인증 대기중인 것만 취소버튼 보이도록 처리함
            if(reqTypeCd == '2' && reqStatProcCd == '3') cancelButtonHTML = '<button class="bggray" onClick="cancelRequest(' + "'" + reqSeqNo + "'" + ')">Cancel</button>';

            depositWithdrawListHtml += depositWithdrawListTemplateHtml.replace(/{{DwGubun}}/g, gubunText)
                                    .replace(/{{ReqSeqNo}}/g, reqSeqNo)
                                    .replace(/{{DwGubunColor}}/g, gubunColor)
                                    .replace(/{{ReqDateTime}}/g, reqDateTime)
                                    .replace(/{{CoinSymbolicName}}/g, CoinSymbolicName)
                                    .replace(/{{Address}}/g, address)
                                    .replace(/{{ReqQty}}/g, nu.cm(reqQty, 8) )
                                    .replace(/{{Fee}}/g, fee)
                                    .replace(/{{ReqStatProcText}}/g, procStatTxt)
                                    .replace(/{{CancelButtonHTML}}/g, cancelButtonHTML)
                                    ;
        });
        $('#wallet-transaction-dw-list-tbody').html(depositWithdrawListHtml);
    } else 
        $('#wallet-transaction-dw-list-tbody').html($('#template-dw-list-empty').html().replace(/{{ColSpan}}/g, 9));
}
////////////////////////////////////
// COIN.T.03-2 코인 입출금내역 >> 상세정보 팝업창 데이터 표출
function viewDetailPopup(reqSeqNo) {
    clearPopup();
    $(__G_Coin_DW_List_Item).each(function(item){
        var value       = __G_Coin_DW_List_Item[item];
        var ReqSeqNo    = getParam(value, 'REQ_SEQ_NO', '0');
        if(('' + reqSeqNo) == ReqSeqNo) {
            var coinNo                      = getParam(value, 'COIN_NO', '');
            var coinSymbolicName            = getParam(value, 'COIN_SYMBOLIC_NM', '');
            var reqTypeCd                   = '' + getParam(value, 'DW_REQ_TYPE_CD', '1');
            var gubunText                   = (reqTypeCd == '1') ? 'Deposit' : 'Withdrawal';
            var reqQty                      = getParam(value, 'REQ_QTY');
            var fee                         = (reqTypeCd == '1') ? '0' : nu.js(parseFloat(getParam(value, 'WTDRW_FEE', '0')), 6); // 입금 : 0, 출금 : WTDRW_FEE
            var reqDateTime                 = du.getCstmFrmt(getParam(value, 'REQ_STAT_PROC_DT'), 'yyyyMMddHHmmssSSSSSS', 'yyyy-MM-dd HH:mm:ss');
            var approvalDateTime            = du.getCstmFrmt(getParam(value, 'APPROVAL_PROC_DT'), 'yyyyMMddHHmmssSSSSSS', 'yyyy-MM-dd HH:mm:ss');
            var frAddress                   = getParam(value, 'FROM_ADDR', '');
            var tgtAddress                  = getParam(value, 'TARGET_ADDR', '');
            var transactionId               = getParam(value, 'TXID', '');
            var reqChannelCd                = getParam(value, 'REQ_CHNL_CD', '0');
            var reqStatProcCd               = getParam(value, 'REQ_STAT_PROC_CD');
            var procStatCd                  = getParam(value, 'DW_PROC_STAT_CD', '0');
            var procStatTxt = '';
            switch(reqStatProcCd) {
                case 1: procStatTxt = 'Deposit Request'; break;
                case 2: procStatTxt = 'Confirmation Checking'; break;
                case 3: procStatTxt = 'Waiting withdrawal auth'; break;
                case 4: procStatTxt = 'Waiting ' + ((reqTypeCd == '1') ? 'Deposit':'Withdrawal') + ' approval'; break;
                case 5: procStatTxt = 'Request Canceled'; break;
            }
            if(reqStatProcCd == 4 && procStatCd == 1) {
                procStatTxt = 'Finished';
            }

            var decimalPnt = 0;
            Object.keys(__G_CoinMgtRef_Map.value).forEach(function(keys) {
                if(__G_CoinMgtRef_Map.get(keys.replace(/k_/g,'')).COIN_SYMBOLIC_NM == coinSymbolicName) decimalPnt = __G_CoinMgtRef_Map.get(keys.replace(/k_/g,'')).WTDRW_DECIMAL_PNT;
            });
            $( ".coin-symbolic-name" ).each(function( index ) { $(this).text(coinSymbolicName); });
            $( ".coin-eng-name" ).each(function( index ) { $(this).text(coinSymbolicName); });

            $('#wallet-transaction-detail-dw-gubun').text(gubunText);
            $('#wallet-transaction-detail-quantity').text(nu.cm(reqQty, decimalPnt));
            $('#wallet-transaction-detail-fee').text(nu.cm(fee, decimalPnt));
            $('#wallet-transaction-detail-dt').text(reqDateTime);
            $('#wallet-transaction-detail-channel').text(reqChannelCd);
            $('#wallet-transaction-detail-confirm-dt').text(approvalDateTime);
            $('#wallet-transaction-detail-status').text(procStatTxt);
            $('#wallet-transaction-detail-address').text(tgtAddress);
            $('#wallet-transaction-detail-txid').text(transactionId);
        }
    });
}
function clearPopup() {
    $( ".coin-symbolic-name" ).each(function( index ) { $(this).text(''); });
    $( ".coin-eng-name" ).each(function( index ) { $(this).text(''); });
    $('#wallet-transaction-detail-dw-gubun').text('');
    $('#wallet-transaction-detail-quantity').text('');
    $('#wallet-transaction-detail-fee').text('');
    $('#wallet-transaction-detail-dt').text('');
    $('#wallet-transaction-detail-channel').text('');
    $('#wallet-transaction-detail-confirm-dt').text('');
    $('#wallet-transaction-detail-status').text('');
    $('#wallet-transaction-detail-txid').text('');
}