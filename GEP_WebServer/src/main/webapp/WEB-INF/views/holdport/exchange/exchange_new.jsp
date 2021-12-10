<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/taglib/taglib.jsp" %>




<script>
const REAL_SIZE = 80;
const DAILY_SIZE = 80;
const CONTRACT_SIZE = 80;

var mktSellHoga = [];
var mktBuyHoga = [];
var realPush = [];
var privatePush = [];
var lastHoga = null;

var coin = '${coin}';
var itemCode = '${itemCode}';
var grpList = '${list}';


createServerSentEvents(publicLister, 'HOLDPORT/public');
function publicLister(data) {
	data = JSON.parse(data);
	
	//console.log("HOLDPORT/public", data);
	
	if(data.msgCode == 999) {
		if(document.visibilityState == 'visible') {
			showNotice(data.body);
		}
	} else if(data.msgCode == 111) {
		if(data.body.curDt.substr(8) == '00') {
			selected.prevClosePrc = selected.closePrc;
			let prevClosePrc = selected.prevClosePrc;
			
			$('#closePrc').text(nu.cm(prevClosePrc, selected.prcDspDecPnt, true));
			$('#closePrc').closest('td').attr('class', '');
			$('#daebiRate').text('0.00%');
			$('#daebiPrc').text(nu.cm(0, selected.prcDspDecPnt, true)).prev().attr('class', 'rate');
			$('#highPrc').text(nu.cm(prevClosePrc, selected.prcDspDecPnt, true)).attr('class', '');
			$('#lowPrc').text(nu.cm(prevClosePrc, selected.prcDspDecPnt, true)).attr('class', '');
			
			$('#prevClosePrc').text(nu.cm(prevClosePrc, selected.prcDspDecPnt, true));
			$('#todayHigh').text(nu.cm(prevClosePrc, selected.prcDspDecPnt, true)).attr('class', '')
				.next().text('0.00%').attr('class', '');
			$('#todayLow').text(nu.cm(prevClosePrc, selected.prcDspDecPnt, true)).attr('class', '')
				.next().text('0.00%').attr('class', '');

			/*
			let prcUnit;
			$('.tradeVol').text(nu.cm(0, selected.volDspDecPnt, true));
			$('.tradeAmt').text(nu.cm(0, selected.prcDspDecPnt, true));

			if(selected.basicCoinNo === 10) {
				prcUnit = getKrwUnit(0);
			} else {
				prcUnit.price = nu.cm(0, selected.amtDecPnt, true);
				prcUnit.unit = selected.fromUnit;
			}
			$('.tradeAmtUnit').text(prcUnit.unit);
			*/
			createAreaChart(selected.itemCode, selected.mktGrpId);
				
			$('.list1').children('tr').each(function() {
				let $td = $(this).children('td:eq(2)')
					, sellHoga = $td.text().replace(/,/g, '');
				
				if(sellHoga != '') {
					sellHoga = parseFloat(sellHoga);
					
					let color = sellHoga - prevClosePrc === 0 ? '':sellHoga - prevClosePrc > 0 ? 'red':'blue'
						, mark = color === '' ? '':color === 'red' ? '+':'-'
						, percent = getCalPer(sellHoga, prevClosePrc);
					
					$td.attr('class', 'bgblue ' + color)
					.next().attr('class', 'bgblue ' + color).children('span')
						.eq(0).text(mark)
						.next().text(nu.cm(Math.abs(percent), 2, true));
				}
			});
			
			$('.list2').children('tr').each(function(i) {
				let index = 0, $td, buyHoga;
				
				if(i == 0) {
					index = 1;
				}
				
				$td = $(this).children('td:eq(' + index + ')');
				buyHoga = $td.text().replace(/,/g, '');
				
				if(buyHoga != '') {
					buyHoga = parseFloat(buyHoga);
					
					let color = buyHoga - prevClosePrc === 0 ? '':buyHoga - prevClosePrc > 0 ? 'red':'blue'
						, mark = color === '' ? '':color === 'red' ? '+':'-'
						, percent = getCalPer(buyHoga, prevClosePrc);
					
					$td.attr('class', 'bgred ' + color)
					.next().attr('class', 'bgred ' + color).children('span')
						.eq(0).text(mark)
						.next().text(nu.cm(Math.abs(percent), 2, true));
				}
			});
			
			$('#tab-group-1').find('div').not('#mainHolding').find('tr').each(function() {
				let mktgrp = $(this).closest('tbody').data('name');
				let $tds = $(this).children('td');

				//console.log(mktgrp + ', coin=' + $(this).closest('tbody').data('coinno'));

				$tds.eq(2).children('p').attr('class', 'border');
				$tds.eq(3).children('p')
					.eq(0).text('0.00%').attr('class', '')
					.next().text(nu.cm(0, selected.prcDspDecPnt, true)).attr('class', 'daebiPrc');

				/*
				if(mktgrp === "KRW") {
					$tds.eq(4).children('span')
					.eq(0).text(nu.cm(0, selected.prcDspDecPnt, true))
					.next().text(prcUnit.unit);
				} else {
					$tds.eq(4).children('p')
						.eq(0).text(nu.cm(0, selected.prcDspDecPnt, true));
					$tds.eq(4).children('p')
						.eq(1).children('span')
						.eq(0).text(nu.cm(0, selected.prcDspDecPnt, true))
						.next().text(prcUnit.unit);
				}
				*/
			});
			
			/*
			$('#tab-group-7').children('div').not('#freeHolding').find('tr').each(function() {
				let $tds = $(this).children('td');
				$tds.eq(2).children('p').attr('class', 'border');
				$tds.eq(3).children('p')
				.eq(0).text('0.00%').attr('class', '')
				.next().text(nu.cm(0, selected.prcDspDecPnt, true)).attr('class', 'daebiPrc');
			});
			*/
		}

		setTimeout(function() {
			updateHourlyTradeVolAmt();
		}, 500);

	}
}

/*
grpList = JSON.parse(grpList);
for(let i=0; i<grpList.length; i++) {
	createServerSentEvents(realContractLister, grpList[i] + '/contract');
}
*/

//++coben(2021/07/25)
createServerSentEvents(realContractLister, 'HOLDPORT/contract');
function realContractLister(data, flag) {

	if(document.visibilityState == 'visible') {
		data = JSON.parse(data);
		//console.log("realContractLister+++", data);
		
		if(data.msgCode === 106) {
			
			data = data.body;
			if(data.mktGrpId === selected.mktGrpId
					&& data.mktId === selected.mktId) {
				
				if(data.itemCode === selected.itemCode) {
					if(parseFloat(data.autoMiningYn) === 1) data.contractRecogCd = 1;
					realContract(data, flag);
				}
				
				//window.askingTable.drawMarket(data);
				let $tr = $('#tab-group-3').find('tr[data-item=' + data.itemCode + ']');
				if($tr.length > 0) {
					$tr.data('prc', data.closePrice);
					reDrawBalance($tr.data('no'));
				}
			}

			//++coben(2021/07/25)
			// 오른쪽 메뉴 데이터 변경
			reDrawRightMenu(data, flag);			
		}
	} else {
		realPush.push(data);
	}
}


</script>

<c:if test="${sessionScope.userInfo.loginYn == 1}">
<script>
toastr.options = {
	"closeButton": true,
	"debug": false,
	"newestOnTop": false,
	"progressBar": true,
	"positionClass": "toast-top-right",
	"preventDuplicates": false,
	"showDuration": "300",
	"hideDuration": "1000",
	"timeOut": "3000",
	"extendedTimeOut": "1000",
	"showEasing": "swing",
	"hideEasing": "linear",
	"showMethod": "fadeIn",
	"hideMethod": "fadeOut",
};

createServerSentEvents(privateLister, 'HOLDPORT/${sessionScope.userInfo.userId}');
function privateLister(data, flag) {
	
}
</script>
</c:if>
 
<div id="like_exchange_content"></div>
 
 


<div class="popup-wrap" id="noticeTrading" tabindex="0"> 
</div>

<div class="popup-wrap" id="lrtSell" tabindex="0">
</div>

<div class="popup-wrap" id="lrtBuy" tabindex="0">
</div>


<script type="module" src="/resources/js/react/exchange_bundle.js"></script>