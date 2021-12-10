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
	
			localeMap.put("_L_0001", "주문 일시 ");
			localeMap.put("_L_0002", "종목명");
			localeMap.put("_L_0003", "수량");
			localeMap.put("_L_0004", "가격");
			localeMap.put("_L_0005", "지정가 주문에 성공하였습니다");
			localeMap.put("_L_0006", "주문알림");
			localeMap.put("_L_0007", "주문 일시");
			localeMap.put("_L_0008", "총액");
			localeMap.put("_L_0009", "시장가 주문에 성공하였습니다");
			localeMap.put("_L_0010", "시장가 매수 주문 중");
			localeMap.put("_L_0011", "시장가 매도 주문 중");
			localeMap.put("_L_0012", "이(가) 체결되지 않았습니다");
			localeMap.put("_L_0013", "체결 일시");
			localeMap.put("_L_0014", "주문이 체결 되었습니다");
			localeMap.put("_L_0015", "체결알림");
			localeMap.put("_L_0016", "심볼");
			localeMap.put("_L_0017", "개발자 정보");
			localeMap.put("_L_0018", "법인(재단)명");
			localeMap.put("_L_0019", "법인소재지");
			localeMap.put("_L_0020", "대표인물");
			localeMap.put("_L_0021", "대표홈페이지");
			localeMap.put("_L_0022", "최초발행");
			localeMap.put("_L_0023", "총 발행한도");
			localeMap.put("_L_0024", "합의 프로토콜");
			localeMap.put("_L_0025", "백서");
			localeMap.put("_L_0026", "암호화폐 소개");
			localeMap.put("_L_0027", "암호화폐");
			localeMap.put("_L_0028", "현재가");
			localeMap.put("_L_0029", "전일대비");
			localeMap.put("_L_0030", "거래대금(24H)");
			localeMap.put("_L_0031", "아이콘");
			localeMap.put("_L_0032", "시세");
			localeMap.put("_L_0033", "정보");
			localeMap.put("_L_0034", "달");
			localeMap.put("_L_0035", "해");
			localeMap.put("_L_0036", "고가");
			localeMap.put("_L_0037", "저가");
			localeMap.put("_L_0038", "거래량(24H)");
			localeMap.put("_L_0039", "매도잔량");
			localeMap.put("_L_0040", "호가");
			localeMap.put("_L_0041", "매수잔량");
			localeMap.put("_L_0042", "52주 최고");
			localeMap.put("_L_0043", "52주 최저");
			localeMap.put("_L_0044", "전일종가");
			localeMap.put("_L_0045", "당일고가");
			localeMap.put("_L_0046", "당일저가");
			localeMap.put("_L_0047", "체결가");
			localeMap.put("_L_0048", "체결량");
			localeMap.put("_L_0049", "일괄");
			localeMap.put("_L_0050", "매도취소");
			localeMap.put("_L_0051", "주문잔량합계");
			localeMap.put("_L_0052", "매수취소");
			localeMap.put("_L_0053", "지정가");
			localeMap.put("_L_0054", "시장가");
			localeMap.put("_L_0055", "자동채굴");
			localeMap.put("_L_0056", "주문가능");
			localeMap.put("_L_0057", "매수가격");
			localeMap.put("_L_0058", "매수수량");
			localeMap.put("_L_0059", "주문총액");
			localeMap.put("_L_0060", "거래수수료");
			localeMap.put("_L_0061", "초기화");
			localeMap.put("_L_0062", "회원가입");
			localeMap.put("_L_0063", "매수하기");
			localeMap.put("_L_0064", "로그인");
			localeMap.put("_L_0065", "매도가격");
			localeMap.put("_L_0066", "매도수량");
			localeMap.put("_L_0067", "매도하기");
			localeMap.put("_L_0068", "매도");
			localeMap.put("_L_0069", "매수");
			localeMap.put("_L_0070", "거래하기");
			localeMap.put("_L_0071", "※ 자동채굴 거래는 현재가로 매수, 매도 한번의 거래량만을 즉시 발생시키는 주문입니다.");
			localeMap.put("_L_0072", "※ 대량의 자동채굴을 원하는 경우 YEP RP - Mining 탭의 Auto Mining 기능을 이용 바랍니다.");
			localeMap.put("_L_0073", "※ 호가창에 주문은 들어가지 않으며, 거래완료 내역은 '체결내역' 및 '회원체결' 창에서 확인할 수 있습니다.");
			localeMap.put("_L_0074", "※ 자동채굴 특성상 호가창에 주문이 안들어가므로 호가와 시장가의 차이 및 미체결 거래가 발생할 수 있습니다.");
			localeMap.put("_L_0075", "체결내역");
			localeMap.put("_L_0076", "일별시세");
			localeMap.put("_L_0077", "체결시간");
			localeMap.put("_L_0078", "체결가격");
			localeMap.put("_L_0079", "대비");
			localeMap.put("_L_0080", "체결수량");
			localeMap.put("_L_0081", "체결금액");
			localeMap.put("_L_0082", "매수/매도");
			localeMap.put("_L_0083", "일자");
			localeMap.put("_L_0084", "종가");
			localeMap.put("_L_0085", "변동률");
			localeMap.put("_L_0086", "거래량");
			localeMap.put("_L_0087", "거래대금");
			localeMap.put("_L_0088", "회원체결");
			localeMap.put("_L_0089", "회원미체결");
			localeMap.put("_L_0090", "체결일시");
			localeMap.put("_L_0091", "구분");
			localeMap.put("_L_0092", "주문가");
			localeMap.put("_L_0093", "체결가");
			localeMap.put("_L_0094", "미체결수량");
			localeMap.put("_L_0095", "주문일시");
			localeMap.put("_L_0096", "주문취소");
			localeMap.put("_L_0097", "마켓");
			localeMap.put("_L_0098", "프리마켓");
			localeMap.put("_L_0099", "보유 암호화폐");
			localeMap.put("_L_0100", "거래가능수량");
			localeMap.put("_L_0101", "마켓 암호화폐/심볼 검색");
			localeMap.put("_L_0102", "화면설정");
			localeMap.put("_L_0103", "화면 설정");
			localeMap.put("_L_0104", "전일대비 등락 가격 표시");
			localeMap.put("_L_0105", "거래대금 KRW 환산 가격 표시");
			localeMap.put("_L_0106", "프리마켓 암호화폐/심볼 검색");
			localeMap.put("_L_0107", "※ 미체결 수량을 제외한 거래 가능한 수량으로 실제 보유수량과 차이가 있습니다.");
			localeMap.put("_L_0108", "※ 실제 보유수량은 투자내역 메뉴에서 확인 가능합니다.");
			localeMap.put("_L_0109", "매수평균가");
			localeMap.put("_L_0110", "수익률");
			localeMap.put("_L_0111", "암호화폐 거래 위험고지");
			localeMap.put("_L_0112", "투자자 피해를 예방하고 안전하게 암호화폐를 거래할 수 있도록 다음과 같이 안내드립니다.");
			localeMap.put("_L_0113", "▪ 암호화폐는 정부나 공공기관이 보증하는 법정화폐가 아닙니다.");
			localeMap.put("_L_0114", "▪ 암호화폐는 규제나 시장 환경 변화에 따라 부정적인 영향을 받을 수 있습니다.");
			localeMap.put("_L_0115", "▪ 암호화폐는 기술적 흠결 혹은 재단의 상황에 따라 상장폐지의 위험성을 가지고 있습니다.");
			localeMap.put("_L_0116", "▪ 24시간 365일 동안 전 세계에서 거래가 이루어지기 때문에 시세가 급변할 수 있습니다.");
			localeMap.put("_L_0117", "▪ 가치 변동폭에 제한이 없기 때문에 시세 급변에 따라 막대한 손실이 발생할 수 있습니다.");
			localeMap.put("_L_0118", "▪ 투자에 대한 결과는 본인의 책임이므로 무리한 투자는 지양하시고, 신중한 투자를 부탁드립니다.");
			localeMap.put("_L_0119", "▪ 19세 미만 미성년자와 외국인은 서비스를 이용할 수 없습니다.");
			localeMap.put("_L_0120", "본인은 위 암호화폐 거래 위험고지를 충분히 숙지하였으며, 본인의 판단에 따라 투자 합니다.");
			localeMap.put("_L_0121", "오늘 하루동안 보지 않기");
			localeMap.put("_L_0122", "확인");
			localeMap.put("_L_0123", "예");
			localeMap.put("_L_0124", "아니오");
			localeMap.put("_L_0125", "암호화폐 개요");
			localeMap.put("_L_0126", "키보드 상하방향키로 설정 가능");
			localeMap.put("_L_0127", "매수총액");
			localeMap.put("_L_0128", "거래수량");
			localeMap.put("_L_0129", "평가액");
			localeMap.put("_L_0130", "최소");
			
			localeMap.put("_L_0131", "주문");
			localeMap.put("_L_0132", "호가창");
			localeMap.put("_L_0133", "차트");
			localeMap.put("_L_0134", "거래내역");
			localeMap.put("_L_0135", "주문내역");
			
			localeMapValid = true;
	
		} else if(locale.equalsIgnoreCase("en")) {

			localeMap.put("_L_0001", "Order Date");
			localeMap.put("_L_0002", "Item");
			localeMap.put("_L_0003", "Amount");
			localeMap.put("_L_0004", "Price");
			localeMap.put("_L_0130", "Minimum");

			localeMapValid = true;
		} else {





			
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


<c:choose>
    <c:when test="${sessionScope.userInfo.loginYn == 1}">
		createServerSentEvents(eventDispatcher, 'HOLDPORT/${sessionScope.userInfo.userId}');
    </c:when>
    <c:otherwise>
		createServerSentEvents(eventDispatcher, 'HOLDPORT/anonymous');
    </c:otherwise>
</c:choose>

function eventDispatcher(data) {

	data = JSON.parse(data);

	//console.log("eventDispatcher+++", data);
	//console.log("eventDispatcher+++ type:", _data.msgType);

	//publicListener: 'HOLDPORT/public'
	//realContractListener: 'HOLDPORT/contract'
	//privateListener: 'HOLDPORT/'


	if(data.msgType == 'public') {
		publicListener(data);
	} else if(data.msgType == 'contract') {
		if(typeof realContractListener == 'function') {
			realContractListener(data);
		}
	} else if(data.msgType == 'user') {
		if(typeof privateListener == 'function') {
			privateListener(data);
		}
	}
}


//createServerSentEvents(publicListener, 'HOLDPORT/public');
function publicListener(data) {
	//data = JSON.parse(data);
	
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
	createServerSentEvents(realContractListener, grpList[i] + '/contract');
}
*/

//++coben(2021/07/25)
//createServerSentEvents(realContractListener, 'HOLDPORT/contract');


function realContractListener(data, flag) {

	if(document.visibilityState == 'visible') {
		//data = JSON.parse(data);
		//console.log("realContractListener+++", data);
		
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


document.onvisibilitychange = function() {
	if(realPush.length > 0) {
		let length = realPush.length;
		for(let i=0; i<length; i++) {
			realContractListener(realPush[i], false);
		}
		realPush = [];
	}
	
	if(privatePush.length > 0 && (typeof privateListener === 'function')) {
		let length = privatePush.length;
		for(let i=0; i<length; i++) {
			privateListener(privatePush[i], false);
		}
		privatePush = [];
	}
	
	if(lastHoga != undefined && lastHoga != null) {
		drawHogaData(lastHoga);
		lastHoga = null;
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

//createServerSentEvents(privateListener, 'HOLDPORT/${sessionScope.userInfo.userId}');
function privateListener(data, flag) {
	
	//console.log("privateListener+++", data, flag);

	if(document.visibilityState == 'visible') {
		//data = JSON.parse(data);
		
		if(parseFloat(data.body.autoMiningYn) === 1 && data.msgCode != 501 && data.msgCode != 108) {return;}
		
		switch(data.msgCode) {
		case 103:
			lrt.client(wordbook[data.body.ResCode]);
			break;
		case 104:
			var op = Number(data.body.ordPrice).toFixed(2);
			var op_id = "op_" + op;
			//console.log("104 search------",op_id, $(op_id))
			//$("[id='"+op_id+"']").fadeOut(100).fadeIn(100).fadeOut(100).fadeIn(100).fadeOut(100).fadeIn(100);


			if(parseInt(data.body.ordPriceTypeCd) == 1) {
				if(data.body.itemCode == selected.itemCode) {
					addNonContract(data);
				}
				
				if(flag === undefined && parseFloat(data.body.autoMiningYn) !== 1) {
					let message = '<%=getLocaleString("_L_0001")%> : ' + du.getCstmFrmt(data.body.ordDt, 'yyyyMMddHHmmss', 'yyyy-MM-dd HH:mm:ss') + '<br>'
						+ '<%=getLocaleString("_L_0002")%> : ' + data.body.itemCode + '<br>'
						+ '<%=getLocaleString("_L_0003")%> : ' + nu.cm(data.body.ordQty, selected.qtyDspDecPnt, true) + '<br>'
						+ '<%=getLocaleString("_L_0004")%> : ' + nu.cm(data.body.ordPrice, selected.prcDspDecPnt, true) + '<br>'
						+ '<%=getLocaleString("_L_0005")%>';
					
					toastr['success'](message, '<%=getLocaleString("_L_0006")%>');
				}
			} else {
				if(flag === undefined && parseFloat(data.body.autoMiningYn) !== 1) {
					let message = '<%=getLocaleString("_L_0007")%> : ' + du.getCstmFrmt(data.body.ordDt, 'yyyyMMddHHmmss', 'yyyy-MM-dd HH:mm:ss') + '<br>'
						+ '<%=getLocaleString("_L_0002")%> : ' + data.body.itemCode + '<br>';
						if(parseInt(data.body.sellBuyRecogCd) == 1) {	
							message  = message + '<%=getLocaleString("_L_0008")%> : ' + nu.cm(data.body.ordPrice, selected.prcDspDecPnt, true) + '<br>';
						} else {
							message  = message + '<%=getLocaleString("_L_0003")%> : ' + nu.cm(data.body.ordQty, selected.qtyDspDecPnt, true) + '<br>'
						}
						message = message + '<%=getLocaleString("_L_0009")%>';
				
					toastr['success'](message, '<%=getLocaleString("_L_0006")%>');
				}
			}
			break;
		case 105:
			//console.log('>>>>> 105 noti: ' + data.body.ResCode);
			if(data.body.ordPriceTypeCd == 2) {
				if(data.body.sellBuyRecogCd == 1 && parseFloat(data.body.nonContractAmt) != 0) {
					lrt.client('<%=getLocaleString("_L_0010")%> ' + nu.cm(data.body.nonContractAmt, selected.prcDspDecPnt, true) + ' ' + selected.fromUnit + '이(가) 체결되지 않았습니다');
				} else if(data.body.sellBuyRecogCd == 2 && parseFloat(data.body.nonContractQty) != 0) {
					lrt.client('<%=getLocaleString("_L_0011")%> ' + data.body.nonContractQty + ' ' + selected.toUnit + '<%=getLocaleString("_L_0012")%>');
				} else {
					lrt.client(wordbook[data.body.ResCode]);
				}
			} else {
				lrt.client(wordbook[data.body.ResCode]);
			}
			break;
		case 108:
			var op = Number(data.body.ordPrice).toFixed(2);
			var op_id = "op_" + op;
			//console.log("108 search------", op_id, $(op_id))

			//window.askingTable.drawAsking(op_id);
			if(data.body.itemCode == selected.itemCode) {
				if(parseFloat(data.body.autoMiningYn) == 0) {
					if(parseFloat(data.body.nonContractQty) === 0
							&& parseInt(data.body.ordPriceTypeCd) == 1) {
						removeNonCotnract(data.body);
					} else {
						modNonCotnract(data.body);
					}
				}
				
				addContract(data.body);
			}
			
			if(flag === undefined) {
				let message = '<%=getLocaleString("_L_0013")%> : ' + du.getCstmFrmt(data.body.contractDt, 'yyyyMMddHHmmss', 'yyyy-MM-dd HH:mm:ss') + '<br>'
					+ '<%=getLocaleString("_L_0002")%> : ' + data.body.itemCode + '<br>'
					+ '<%=getLocaleString("_L_0003")%> : ' + nu.cm(data.body.contractQty, selected.qtyDspDecPnt, true) + '<br>'
					+ '<%=getLocaleString("_L_0004")%> : ' + nu.cm(data.body.contractPrice, selected.prcDspDecPnt, true) + '<br>'
					+ '<%=getLocaleString("_L_0014")%>';
				
				toastr['info'](message, '<%=getLocaleString("_L_0015")%>');
			}
			
			break;
		case 203:
			lrt.client(wordbook[data.body.ResCode]);
			break;
		case 205:
			lrt.client(wordbook[data.body.ResCode]);
			break;
		case 206:
			cancelNonCotnract(data.body);
			break;
		case 501:
			setBalance(data.body);
			break;
		}
	} else {
		privatePush.push(data);
	}
}
</script>
</c:if>
 
<script id="ITEMCODE_INFO" type="template/text">
	<div class="coin-info-wrap">
	<div class="coin-info-symbol">
		<p><img src="/resources/img/coin-symbol/{{symbol}}.png" alt="img"></p>
		<p class="info-title">{{itemCodeNm}}</p>
		<p>{{itemCodeEng}}</p>
		<p><%=getLocaleString("_L_0016")%><span>{{symbol}}</span></p>
	</div>
	<div class="coin-info-txt">
		<div class="coin-info-detail">
			<h2 class="tabletitle"><%=getLocaleString("_L_0017")%></h2>
			<table>
				<colgroup>
					<col style="width: 150px;">
					<col>
				</colgroup>
				<tbody>
					<tr><th><%=getLocaleString("_L_0018")%></th><td>{{corpNm}}</td></tr>
					<tr><th><%=getLocaleString("_L_0019")%></th><td>{{corpLoc}}</td></tr>
					<tr><th><%=getLocaleString("_L_0020")%></th><td>{{corpRep}}</td></tr>
					<tr><th><%=getLocaleString("_L_0021")%></th><td><a href="{{corpSite}}" class="blue-link" target="_blank">{{corpSite}}</a></td></tr>
				</tbody>
			</table>
		</div>
		<div class="coin-info-detail">
			<h2 class="tabletitle"><%=getLocaleString("_L_0125")%></h2>
			<table>
				<colgroup>
					<col style="width: 150px;">
					<col>
				</colgroup>
				<tbody>
					<tr><th><%=getLocaleString("_L_0022")%></th><td>{{first}}</td></tr>
					<tr><th><%=getLocaleString("_L_0023")%></th><td>{{total}}</td></tr>
					<tr><th><%=getLocaleString("_L_0024")%></th><td>{{protocol}}</td></tr>
					<tr><th><%=getLocaleString("_L_0025")%></th><td><a href="{{paper}}" class="blue-link" target="_blank">White Paper</a></td></tr>
				</tbody>
			</table>
		</div>
		<div>
			<h2 class="tabletitle"><%=getLocaleString("_L_0026")%></h2>
			<div class="para-data">{{intro}}</div>
		</div>
	</div>
</div>
</script>

<script id="MKT_TEMPLATE" type="template/text">
<div id="{{id}}" class="">
	<div class="stocks-list">
		<ul>
			<li><a href="javascript:void(0);" class="sort-toggle"><%=getLocaleString("_L_0027")%></a></li>
			<li><a href="javascript:void(0);" class="sort-toggle"><%=getLocaleString("_L_0028")%></a></li>
			<li><a href="javascript:void(0);" class="sort-toggle"><%=getLocaleString("_L_0029")%></a></li>
			<li><a href="javascript:void(0);" class="sort-toggle"><%=getLocaleString("_L_0030")%></a></li>
		</ul>
		<table class="rightScroll">
			<colgroup>
				<col style="width: 30px">
				<col style="width: 75px">
				<col style="width: 90px">
				<col style="width: 80px">
				<col style="width: 92px">
			</colgroup>
			<tbody data-coinno={{coinNo}} data-name="{{mktNm}}">{{body}}</tbody>
		</table>
	</div>
</div>
</script>

<script id="MKT_ITEM_KRW_TEMPLATE" type="template/text">
<tr class="{{isActive}}" data-code="{{itemCode}}" data-mkt="{{mktId}}" data-grp="{{mktGrpId}}" data-prc={{prcPnt}} data-amt={{amtPnt}} data-chosung="{{coinSymbolChosung}}">
	<td data-sort="{{index}}"><span class="bookmark {{markOn}}"></span></td>
	<td data-sort="{{coinNm}}">
		<p class="coin-name">{{coinNm}}</p>
		<p>
			<span>{{toUnit}}</span>
			<span>/</span>
			<span>{{fromUnit}}</span>
		</p>
	</td>
	<td data-sort="{{originClosePrc}}">
		<p class="border {{color}}">
			{{closePrc}}
		</p>
	</td>
	<td data-sort="{{originDaebiRate}}">
		<p class="{{color}}">{{daebiRate}}</p>
		<p class="daebiPrc {{color}}">{{daebiPrc}}</p>
	</td>
	<td data-sort="{{originTradeAmt}}">
		<span>{{krwAmt}}</span>
		<span>{{prcUnit}}</span>
	</td>
</tr>
</script>

<script id="MKT_ITEM_TEMPLATE" type="template/text">
<tr class="{{isActive}}" data-code="{{itemCode}}" data-mkt="{{mktId}}" data-grp="{{mktGrpId}}" data-prc={{prcPnt}} data-amt={{amtPnt}} data-chosung="{{coinSymbolChosung}}">
	<td data-sort="{{index}}"><span class="bookmark {{markOn}}"></span></td>
	<td data-sort="{{coinNm}}">
		<p class="coin-name">{{coinNm}}</p>
		<p>
			<span>{{toUnit}}</span>
			<span>/</span>
			<span>{{fromUnit}}</span>
		</p>
	</td>
	<td data-sort="{{originClosePrc}}">
		<p class="border {{color}}">
			{{closePrc}}
		</p>
	</td>
	<td data-sort="{{originDaebiRate}}">
		<p class="{{color}}">{{daebiRate}}</p>
		<p class="daebiPrc {{color}}">{{daebiPrc}}</p>
	</td>
	<td data-sort="{{originTradeAmt}}">
		<p>{{tradeAmt}}</p>
		<p class="krwAmt" style="display:none;">
			<span>{{krwAmt}}</span>
			<span>{{prcUnit}}</span>
		</p>
	</td>
</tr>
</script>

<script id="HOLDING_ITEM_TEMPLATE" type="template/text">
<tr data-no="{{coinNo}}" data-prc="{{closePrc}}" data-qty="{{originQty}}" data-avg="{{originAvg}}" data-prcpnt="{{prcPnt}}" data-qtypnt="{{qtyPnt}}" data-item="{{itemCode}}" data-chosung="{{coinSymbolChosung}}" data-code="{{itemCode}}">
	<td data-sort="{{index}}"><img src="/resources/img/coin-symbol/{{symbol}}.png" alt=""></td>
	<td data-sort="{{coinKorNm}}">
		<p class="coin-name">{{coinKorNm}}</p>
		<p>{{coinNm}}</p>
	</td>
	<td data-sort="{{originBcPrc}}">
		<p>{{qty}}</p>
		<p><span>{{bcPrc}}</span> <span>{{bcUnit}}</span></p>
	</td>
	<td data-sort="{{originAvgPrc}}"><p>{{avgPrc}}</p><p><span>{{bcUnit}}</span></p></td>
	<td data-sort="{{originProfitRate}}">
		<p class="{{color}}">{{profitRate}}</p><p><span class="{{color}}">{{profitPrc}}</span></p>
	</td>
</tr>
</script>

<script id="REAL_CONTRACT_TEMPLATE" type="template/text">
<tr>
	<td style="text-overflow: ellipsis;white-space: nowrap;overflow: hidden;" title="{{prc}}">{{prc}}</td>
	<td class="{{color}}" style="letter-spacing:{{size}}px;text-overflow: ellipsis;white-space: nowrap;overflow: hidden;" title="{{qty}}">{{qty}}</td>
</tr>
</script>

<script id="REAL_CONTRACT_LIST_TEMPLATE" type="template/text">
<tr data-index="{{index}}">
	<td>{{dt}}</td>
	<td>{{prc}}</td>
	<td class="{{color}}">{{daebiRate}}</td>
	<td class="{{color}}">
		<span class="rate {{icon}}"><%=getLocaleString("_L_0031")%></span>
		<span>{{daebiPrc}}</span>
	</td>
	<td class="{{sellBuyColor}}">{{qty}}<br>({{toUnit}})</td>
	<td>{{contractAmt}}<br>({{fromUnit}})</td>
	<!-- <td class="{{sellBuyColor}}">{{sellBuy}}</td> -->
</tr>
</script>

<script id="DAILY_TEMPLATE" type="template/text">
<tr data-index="{{index}}">
	<td>{{dt}}</td>
	<td>{{prc}}</td>
	<td class="{{color}}">{{daebiRate}}</td>
	<td class="{{color}}">
		<span class="rate {{icon}}"><%=getLocaleString("_L_0031")%></span>
		<span>{{daebiPrc}}</span>
	</td>
	<td class="{{color}}">{{tradeVol}}</td>
	<td>{{tradeAmt}}</td>
</tr>
</script>

<script id="CONTRACT_TEMPLATE" type="template/text">
<tr data-index="{{index}}">
	<td>{{contractDt}}</td>
	<td class="{{color}}">{{sellBuy}}</td>
	<td class="{{color}}">{{contractQty}}</td>
	<td class="bold">{{contractPrc}}</td>
	<td>{{contractAmt}}</td>
</tr>
</script>

<script id="NON_CONTRACT_TEMPLATE" type="template/text">
<tr data-tran="{{tranNo}}" data-ord="{{ordNo}}" data-exc="{{excNo}}">
	<td class="txt-ellips" title="{{itemCode}}">{{itemCode}}</td>
	<td class="{{color}}">{{sellBuy}}</td>
	<td>{{ordPrc}}</td>
	<td>{{contractPrc}}</td>
	<td>{{nonQty}}</td>
	<td class="{{color}}">{{qty}}</td>
	<td>{{ordDt}}</td>
	<td><button>취소</button></td>
</tr>
</script>

<section class="main">
	<div class="inner">
		<article class="layoutA">
			<div class="content">
				<div id="tab-group-6" style="display:none;">
					<div class="title-wrap">
						<h2 class="full-title">
							<span class="coin-symbol normal">
								<img src="/resources/img/coin-symbol/BTC.png" alt="coin">
							</span>
							<span class="coin-title" id="coinTitle"></span>
							<!-- <span>
								<select id="coinList"></select>
							</span> -->
						</h2>
						
						<div class="slider_notice_wrap">
							<div class="slider_notice slider" style="display:none;">
								<c:forEach var="item" items="${notice}">
								<div class="event-box"><a href="/site/support?tab=1&no=${item.getRecSeqNo()}" target="_blank">${item.getTitle()}</a></div>
								</c:forEach>
							</div>
						</div>
						
						<ul class="li2 btntab tab_exchange">
							<li><a href="#tabs6-1"><%=getLocaleString("_L_0032")%></a></li>
							<li><a href="#tabs6-2"><%=getLocaleString("_L_0033")%></a></li>
						</ul>
						<div class="lightdark">
							<button class="dark"><%=getLocaleString("_L_0034")%></button>
							<button class="light"><%=getLocaleString("_L_0035")%></button>
						</div>
						<div class="title-line"></div>
					</div>

					<!-- 시세 냅 내용: Start -->
					<div class="content-item btntabpanel" id="tabs6-1">
						<div class="totalinfo">
							<div class="total-table">
								<table>
									<colgroup>
										<col style="width: 445px">
										<col style="width: 170px">
										<col>
										<col>
									</colgroup>
									<tbody>
										<tr>
											<td class="">
												<p class="analysis-total" >
													<span id="closePrc"></span>
													<span class="gray amtUnit"></span>
												</p>
												<p class="analysis-sub">
													<span class="gray"></span>
													<span class="bold" id="daebiRate"></span>
													<span class="rate"><%=getLocaleString("_L_0031")%></span>
													<span class="bold" id="daebiPrc"></span>
												</p>
											</td>
											<td class="chartchild">
												<div class="minichart" id="areaChart"></div>
											</td>
											<td>
												<dl class="analysis-price">
													<dt><%=getLocaleString("_L_0036")%></dt>
													<dd class="" id="highPrc"></dd>
												</dl>
												<dl class="analysis-price">
													<dt><%=getLocaleString("_L_0037")%></dt>
													<dd class="" id="lowPrc"></dd>
												</dl>
											</td>
											<td>
												<dl class="analysis-price">
													<dt><%=getLocaleString("_L_0038")%></dt>
													<dd>
														<span class="tradeVol"></span>
														<span class="gray volUnit"></span>
													</dd>
												</dl>
												<dl class="analysis-price">
													<dt><%=getLocaleString("_L_0030")%></dt>
													<dd>
														<span class="tradeAmt"></span>
														<span class="gray tradeAmtUnit"></span>
													</dd>
												</dl>
											</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>

					<!-- 정보 탭 내용 : Start -->
					<div class="content-item btntabpanel" id="tabs6-2">
						<div class="coin-info-tab" id="itemCodeInfo"></div>
					</div>
					<!-- //정보 탭 내용 : End -->
				</div>
				<!-- tab mobile -->
				<ul class="m_ex_tab">
					<li class="item_m on" onclick="tabMenuMobile(event,'exchange')"><%=getLocaleString("_L_0131")%></li>
					<li class="item_m" onclick="tabMenuMobile(event,'price')"><%=getLocaleString("_L_0132")%></li>
					<li class="item_m" onclick="tabMenuMobile(event,'chart1')"><%=getLocaleString("_L_0133")%></li>
					<li class="item_m" onclick="tabMenuMobile(event,'history1')"><%=getLocaleString("_L_0134")%></li>
					<li class="item_m" onclick="tabMenuMobile(event,'order')"><%=getLocaleString("_L_0135")%></li>
				</ul>
				<!-- end -->
				<!-- 거래주문 및 차트영역 : Start -->
				<div class="content-item">

					<!--호가주문테이블 시작-->
					<div class="askingtable tab-content" id="price" style="display:none;">
						<div class="table-market">
							<table>
								<colgroup>
									<col style="width: 30px">
									<col style="width: 100px">
									<col style="width: 90px">
									<col style="width: 60px">
									<col style="width: 120px">
									<col style="width: 50px">
								</colgroup>
								<thead>
									<tr>
										<th colspan="2"><%=getLocaleString("_L_0039")%></th>
										<th colspan="2"><%=getLocaleString("_L_0040")%></th>
										<th colspan="2"><%=getLocaleString("_L_0041")%></th>
									</tr>
								</thead>
								<tbody class="list1">
									<tr>
										<td class="hprice alCenter"></td>
										<td class="bar-down">
											<a>
												<div style="width: 0%;">-</div>
												<p></p>
											</a>
										</td>
										<td class="bgblue red"></td>
										<td class="bgblue red">
											<span></span>
											<span></span>
											<span></span>
										</td>
										<td colspan="2" rowspan="10" class="bgbox">
											<!--거래량 거래대금 최고 최저 등 시작 -->
											<div class="inner-data-wrap dl-wrap">
												<div class="inner-data-group">
													<dl>
														<dt><%=getLocaleString("_L_0038")%></dt>
														<dd><span class="tradeVol"></span></dd>
														<dd><span class="gray volUnit"></span></dd>
													</dl>
													<dl>
														<dt><%=getLocaleString("_L_0030")%></dt>
														<dd><span class="tradeAmt"></span></dd>
														<dd><span class="gray tradeAmtUnit"></span></dd>
													</dl>
												</div>
												<!-- div class="inner-data-group">
													<dl>
														<dt><%=getLocaleString("_L_0042")%></dt>
														<dd class="" id="week52high"></dd>
														<dd class="gray"></dd>
													</dl>
													<dl>
														<dt><%=getLocaleString("_L_0043")%></dt>
														<dd class="" id="week52low"></dd>
														<dd class="gray"></dd>
													</dl>
												</div -->
												<div class="inner-data-group">
													<dl>
														<dt><%=getLocaleString("_L_0044")%></dt>
														<dd id="prevClosePrc"></dd>
													</dl>
													<dl>
														<dt><%=getLocaleString("_L_0045")%></dt>
														<dd class="" id="todayHigh"></dd>
														<dd class=""></dd>
													</dl>
													<dl>
														<dt><%=getLocaleString("_L_0046")%></dt>
														<dd class="" id="todayLow"></dd>
														<dd class=""></dd>
													</dl>
												</div>
											</div>
											<!--거래량 거래대금 최고 최저 등 끝-->
										</td>
									</tr>
									<c:forEach begin="1" end="9">
									<tr>
										<td class="hprice alCenter"></td>
										<td class="bar-down">
											<a>
												<div style="width: 0%;">-</div>
												<p></p>
											</a>
										</td>
										<td class="bgblue red"></td>
										<td class="bgblue red">
											<span></span>
											<span></span>
											<span></span>
										</td>
									</tr>
									</c:forEach>
								</tbody>
								<tbody class="list2">
									<tr>
										<td colspan="2" rowspan="10" class="bgbox">
											<!-- 체결가, 체결량 : Start -->
											<div class="inner-data-wrap">
												<table>
													<colgroup>
														<col style="width: 65px">
														<col>
													</colgroup>
													<thead>
														<tr>
															<th><%=getLocaleString("_L_0047")%></th>
															<th><%=getLocaleString("_L_0048")%></th>
														</tr>
													</thead>
													<tbody id="realContract">
														<c:forEach begin="0" end="20">
														<tr>
															<td></td>
															<td></td>
														</tr>
														</c:forEach>
													</tbody>
												</table>
											</div>
											<!-- //체결가, 체결량 : End -->
										</td>
										<td class="bgred blue"></td>
										<td class="bgred blue">
											<span></span>
											<span></span>
											<span></span>
										</td>
										<td class="bar-up">
											<a>
												<div style="width: 0%;">-</div>
												<p></p>
											</a>
										</td>
										<td class="hprice alCenter"></td>
									</tr>
									<c:forEach begin="1" end="9">
									<tr>
										<td class="bgred blue"></td>
										<td class="bgred blue">
											<span></span>
											<span></span>
											<span></span>
										</td>
										<td class="bar-up">
											<a>
												<div style="width: 0%;"></div>
												<p></p>
											</a>
										</td>
										<td class="hprice alCenter"></td>
									</tr>
									</c:forEach>
								</tbody>
							</table>

							<!-- 버튼 + 총잔량 : End -->
							<div class="bottom-group">
								<div class="btn-group">
									<button class="blue" id="canSellAll"><%=getLocaleString("_L_0049")%><br><%=getLocaleString("_L_0050")%></a>
								</div>

								<div class="total-price-group">
									<span class="total-price" id="sellTotal"></span>
									<span>
										<p><%=getLocaleString("_L_0051")%></p>
										<p>(<span id="symbol"></span>)</p>
									</span>
									<span class="total-price" id="buyTotal"></span>
								</div>
								
								<div class="btn-group">
									<button class="red" id="canBuyAll"><%=getLocaleString("_L_0049")%><br><%=getLocaleString("_L_0052")%></a>
								</div>
							</div>
							<!-- //버튼 + 총잔량 : End -->
						</div>
					</div>
					<!--호가주문 테이블 끝-->

					<div class="chart-wrap" style="display:none;">
						<!-- 하단 차트영역 : start -->
						<div class="tab-content" id="chart1">
							<div class="chart-area" id="candleChart" style="height:472px;"></div>
						</div>
						<!--지정가 시장가 매수 매도 영역 시작-->
						<div class="tab-content" id="exchange" style="display:inline-block;">
							<div id="tab-group-2" class="buysell-area">
								<ul class="li1 tab_exchange">
									<li><a href="#tabs2-1"><%=getLocaleString("_L_0053")%></a></li>
									<!-- <li><a href="#tabs2-2"><%=getLocaleString("_L_0054")%></a></li> -->
									<!-- li><a href="#tabs2-3"><%=getLocaleString("_L_0055")%></a></li-->
								</ul>

								<!--지정가 매수매도 시작-->
								<div id="tabs2-1"> </div>
								<script type="module" src="/resources/js/react/exchange_buysell_bundle.js"></script>
								<!--지정가 매수매도 끝-->

								<!--시장가 매수매도 시작-->
								<div id="tabs2-2" style="display:none;">
									<!--매수매도영역 시작-->

									<!--시장가 매수하기 시작-->
									<div class="market-buy">
									<div class="buysell-wrap">
											<table>
												<tbody data-type="buy">
													<tr>
														<th><%=getLocaleString("_L_0056")%></th>
														<td colspan="3">
															<div class="readonly">
																<span class="fromQty"></span>
																<span class="amtUnit"></span>
															</div>
														</td>
													</tr>
													<tr>
														<th><%=getLocaleString("_L_0127")%></th>
														<td colspan="3">
															<div class="data-input">
																<input type="text" class="tip-open qty" maxlength="17" data-type="buy" id="mktBuyQty">
																<span class="amtUnit"></span>
															</div>
															<i class="tip">
																<%=getLocaleString("_L_0126")%>
															</i>
														</td>
													</tr>
													<tr>
														<th></th>
														<td colspan="3">
															<div class="buy-percent" data-type="mkt" id="mktBuyPeccent">
																<div>25%</div>
																<div>50%</div>
																<div>75%</div>
																<div>100%</div>
															</div>
														</td>
													</tr>
													<!-- <tr>
														<th colspan="2" class="bold">
															<%=getLocaleString("_L_0059")%>
														</th>
														<td colspan="2" class="totalorder red">
															<span id="mktBuyTotOrder">0</span>
															<span class="amtUnit"></span>
														</td>
													</tr> -->
													<tr>
														<th colspan="2" class="min_krw">
															<span class="minAmt"></span>
														</th>
														<td colspan="2" class="fee">
															<span><%=getLocaleString("_L_0060")%></span>
															<span class="buyFee"></span> %
														</td>
													</tr>
												</tbody>
											</table>
											<div class="btn-buy-sell">
												<a style="cursor:pointer;">
													<c:choose>
														<c:when test="${sessionScope.userInfo.loginYn == 1}">
													<button class="init"><%=getLocaleString("_L_0061")%></button>
														</c:when>
														<c:otherwise>
													<button onclick="doRegister();"><%=getLocaleString("_L_0062")%></button>
														</c:otherwise>
													</c:choose>
												</a>
												<a style="cursor:pointer;">
													<c:choose>
														<c:when test="${sessionScope.userInfo.loginYn == 1}">
													<button class="red" onclick="doMktBuy(this);"><%=getLocaleString("_L_0063")%></button>
														</c:when>
														<c:otherwise>
													<button class="red" onclick="doLogin();"><%=getLocaleString("_L_0064")%></button>
														</c:otherwise>
													</c:choose>
												</a>
											</div>
										</div>
									</div>
									<!--시장가 매수하기 끝-->

									<!--시장가 매도하기 시작-->
									<div class="market-sell">
										<div class="buysell-wrap">
											<table>
												<tbody data-type="sell">
													<tr>
														<th><%=getLocaleString("_L_0056")%></th>
														<td colspan="3">
															<div class="readonly">
																<span class="toQty"></span>
																<span class="volUnit"></span>
															</div>
														</td>
													</tr>
													<tr>
														<th><%=getLocaleString("_L_0066")%></th>
														<td colspan="3">
															<div class="data-input">
																<input type="text" class="tip-open qty" maxlength="17" data-type="sell" id="mktSellQty">
																<span class="volUnit"></span>
															</div>
															<i class="tip">
																<%=getLocaleString("_L_0126")%>
															</i>
														</td>
													</tr>
													<tr>
														<th></th>
														<td colspan="3">
															<div class="buy-percent" data-type="mkt" id="mktSellPeccent">
																<div>25%</div>
																<div>50%</div>
																<div>75%</div>
																<div>100%</div>
															</div>
														</td>
													</tr>
													<!-- <tr>
														<th colspan="2" class="bold">
															<%=getLocaleString("_L_0059")%>
														</th>
														<td colspan="2" class="totalorder blue">
															<span id="mktSellTotOrder">0</span>
															<span class="amtUnit"></span>
														</td>
													</tr> -->
													<tr>
														<th colspan="2" class="min_krw">
															<span class="minAmt"></span>
														</th>
														<td colspan="2" class="fee">
															<span><%=getLocaleString("_L_0060")%></span>
															<span class="sellFee"></span> %
														</td>
													</tr>
												</tbody>
											</table>
											<div class="btn-buy-sell">
												<a style="cursor:pointer;">
													<c:choose>
														<c:when test="${sessionScope.userInfo.loginYn == 1}">
													<button class="init"><%=getLocaleString("_L_0061")%></button>
														</c:when>
														<c:otherwise>
													<button onclick="doRegister();"><%=getLocaleString("_L_0062")%></button>
														</c:otherwise>
													</c:choose>
												</a>
												<a style="cursor:pointer;">
													<c:choose>
														<c:when test="${sessionScope.userInfo.loginYn == 1}">
													<button class="blue" onclick="doMktSell(this);"><%=getLocaleString("_L_0067")%></button>
														</c:when>
														<c:otherwise>
													<button class="blue" onclick="doLogin();"><%=getLocaleString("_L_0064")%></button>
														</c:otherwise>
													</c:choose>
												</a>
											</div>
										</div>
									</div>
									<!--시장가 매도 하기 끝-->

									<!--매수매도영역 끝-->
								</div>
								<!--시장가 매수매도 끝-->

								<!--자동채굴 시작-->
								<div id="tabs2-3" style="display:none">
									<div class="market-auto">
										<div class="buysell-wrap">
										<table>
											<colgroup>
												<col style="width: 70px">
												<col>
												<col>
											</colgroup>
											<tbody>
												<tr>
													<th><%=getLocaleString("_L_0056")%></th>
													<td colspan="2">
														<div class="readonly">
															<span id="autoTo"></span>
															<span class="volUnit"></span>
														</div>
														<div class="auto_unit">
															<span id="autoFrom"></span>
															<span class="amtUnit"></span>
														</div>
													</td>
												</tr>
												<tr>
													<th><%=getLocaleString("_L_0128")%></th>
													<td colspan="2">
														<div class="data-input">
															<input type="text" class="tip-open qty" id="autoQty">
															<span class="volUnit"></span>
														</div>
														<i class="tip">
															<%=getLocaleString("_L_0126")%>
														</i>
													</td>
												</tr>
												<tr>
													<th></th>
													<td colspan="2">
														<div class="buy-percent calPercent" data-type="auto">
															<div>25%</div>
															<div>50%</div>
															<div>75%</div>
															<div>100%</div>
														</div>
													</td>
												</tr>
												<tr>
													<th class="bold">
														<%=getLocaleString("_L_0059")%>
													</th>
													<td colspan="2" class="totalorder red">
														<span id="autoOrdAmt">0</span>
														<span class="amtUnit"></span>
													</td>
												</tr>
												<tr>
													<th colspan="2" class="min_krw">
														<span class="minAmt"></span>
													</th>
													<td class="fee">
														<%=getLocaleString("_L_0060")%>(
														<span><%=getLocaleString("_L_0068")%></span>
														<span class="sellFee"></span> % |
														<span><%=getLocaleString("_L_0069")%> </span>
														<span class="buyFee"></span> %)
													</td>
												</tr>
											</tbody>
										</table>
										<div class="btn-buy-sell">
											<a style="cursor:pointer;">
												<c:choose>
													<c:when test="${sessionScope.userInfo.loginYn == 1}">
												<button class="init"><%=getLocaleString("_L_0061")%></button>
													</c:when>
													<c:otherwise>
												<button onclick="doRegister();"><%=getLocaleString("_L_0062")%></button>
													</c:otherwise>
												</c:choose>
											</a>
											<a style="cursor:pointer;">
												<c:choose>
													<c:when test="${sessionScope.userInfo.loginYn == 1}">
												<button class="red" onclick="doAutoMining(this);"><%=getLocaleString("_L_0070")%></button>
													</c:when>
													<c:otherwise>
												<button class="red"><%=getLocaleString("_L_0064")%></button>
													</c:otherwise>
												</c:choose>
											</a>
											<div class="market_auto_info">
												<p style="font-size:11px;"><%=getLocaleString("_L_0071")%></p>
												<p style="font-size:11px;"><%=getLocaleString("_L_0072")%></p>
												<p style="font-size:11px;"><%=getLocaleString("_L_0073")%></p>
												<p style="font-size:11px; color:red;"><%=getLocaleString("_L_0074")%></p>
											</div>
										</div>

									</div>
									</div>
								</div>
								<!--자동채굴 끝-->

							</div>
						</div>
						<!--지정가 시장가 매수 매도 영역 끝-->

					</div>
				</div>
				<!-- //거래주문 및  차트영역 : Start -->

				<!--체결내역 일별시세 회원체결내역 회원미체결내역등-->
				<!-- 체결내역, 일별시세 탭 : Start -->
				<div class="content-item">
					<div class="tab-content" id="history1">
						<div id="tab-group-5" class="box1">
							<ul class="li2 tab_exchange">
								<li><a href="#tabs5-1"><%=getLocaleString("_L_0075")%></a></li>
								<li><a href="#tabs5-2"><%=getLocaleString("_L_0076")%></a></li>
							</ul>
							<!-- 체결내역 탭 컨텐츠 : Start -->
							<div id="tabs5-1">
								<!-- 체결내역 리스트 : Start -->
								<div class="list-tbl" id="realContractDiv">
									<table class="type01">
										<thead>
											<tr>
												<th><%=getLocaleString("_L_0077")%></th>
												<th><%=getLocaleString("_L_0078")%></th>
												<th><%=getLocaleString("_L_0085")%></th>
												<th><%=getLocaleString("_L_0079")%></th>
												<th><%=getLocaleString("_L_0080")%></th>
												<th><%=getLocaleString("_L_0081")%></th>
												<!-- <th><%=getLocaleString("_L_0082")%></th> -->
											</tr>
										</thead>
										<tbody id="realContractList">
										</tbody>
									</table>
								</div>
								<!-- //체결내역 리스트 : End -->
							</div>
							<!-- //체결내역 탭 컨텐츠 : End -->

							<!-- 일별시세 탭 컨텐츠 : Start -->
							<div id="tabs5-2">
								<!-- 일별시세 리스트 : Start -->
								<!-- <dl class="dl-tit2">
									<dt><%=getLocaleString("_L_0083")%></dt>
									<dd><%=getLocaleString("_L_0084")%></dd>
									<dd><%=getLocaleString("_L_0085")%></dd>
									<dd><%=getLocaleString("_L_0079")%></dd>
									<dd><%=getLocaleString("_L_0086")%></dd>
									<dd><%=getLocaleString("_L_0087")%></dd>
								</dl> -->
								<div class="list-tbl" id="dailyDiv">
									<table class="type02">
										<thead>
											<tr>
												<th><%=getLocaleString("_L_0083")%></th>
												<th><%=getLocaleString("_L_0084")%></th>
												<th><%=getLocaleString("_L_0085")%></th>
												<th><%=getLocaleString("_L_0079")%></th>
												<th><%=getLocaleString("_L_0086")%></th>
												<th><%=getLocaleString("_L_0087")%></th>
											</tr>
										</thead>
										<tbody id="daily">
										</tbody>
									</table>
								</div>
								<!-- //일별시세 리스트 : End -->
							</div>
							<!-- //일별시세 탭 컨텐츠 : End -->
						</div>
					</div>
					<!-- //체결내역, 일별시세 탭 : End -->

					<!--회원체결 미체결 시작-->
					<div class="tab-content" id="order">
						<div id="tab-group-4" class="box2">
							<ul class="li2 tab_exchange">
								<li><a href="#tabs4-1"><%=getLocaleString("_L_0088")%></a></li>
								<li><a href="#tabs4-2"><%=getLocaleString("_L_0089")%></a></li>
							</ul>
							<div id="tabs4-1">
								<!--회원체결 시작-->
								<!-- <dl class="dl-tit3">
									<dt><%=getLocaleString("_L_0090")%></dt>
									<dd><%=getLocaleString("_L_0091")%></dd>
									<dd><%=getLocaleString("_L_0080")%></dd>
									<dd><%=getLocaleString("_L_0078")%></dd>
									<dd><%=getLocaleString("_L_0081")%></dd>
								</dl> -->
								<div class="list-tbl" id="contractDiv">
									<table class="type01">
										<thead>
											<tr>
												<th><%=getLocaleString("_L_0090")%></th>
												<th><%=getLocaleString("_L_0091")%></th>
												<th><%=getLocaleString("_L_0080")%></th>
												<th><%=getLocaleString("_L_0078")%></th>
												<th><%=getLocaleString("_L_0081")%></th>
											</tr>
										</thead>
										<tbody id="contractList">
										</tbody>
									</table>
								</div>
								<!--회원체결 끝-->
							</div>

							<div id="tabs4-2">
								<!-- <dl class="dl-tit4">
									<dt><%=getLocaleString("_L_0002")%></dd>
									<dd><%=getLocaleString("_L_0091")%></dd>
									<dd><%=getLocaleString("_L_0092")%></dd>
									<dd><%=getLocaleString("_L_0093")%></dd>
									<dd><%=getLocaleString("_L_0094")%></dd>
									<dd><%=getLocaleString("_L_0080")%></dd>
									<dd><%=getLocaleString("_L_0095")%></dd>
									<dd><%=getLocaleString("_L_0096")%></dd>
								</dl> -->
								<div class="list-tbl mCustomScrollbar">
									<table class="type02">
										<thead>
											<tr>
												<th><%=getLocaleString("_L_0002")%></th>
												<th><%=getLocaleString("_L_0091")%></th>
												<th><%=getLocaleString("_L_0092")%></th>
												<th><%=getLocaleString("_L_0093")%></th>
												<th><%=getLocaleString("_L_0094")%></th>
												<th><%=getLocaleString("_L_0080")%></th>
												<th><%=getLocaleString("_L_0095")%></th>
												<th><%=getLocaleString("_L_0096")%></th>
											</tr>
										</thead>
										<tbody id="nonContractList">
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
					<!--회윈 체결 미체결 끝-->
				</div>
			</div>
			<!--거래소 차트 및 거래영역 끝-->

			<!-- right side : Start -->
			<aside class="side-info-wrap">
				<!--메인마켓 프리마켓 시작-->
				<div id="tab-group-3" style="display:none;">
					<ul class="li3 tab_exchange">
						<li style="width:50%;"><a href="#tabs3-1"><%=getLocaleString("_L_0097")%></a></li>
						<!-- <li><a href="#tabs3-2"><%=getLocaleString("_L_0098")%></a></li> -->
						<li style="width:50%;line-height:23px;"><a href="#tabs3-3"><%=getLocaleString("_L_0099")%><p class="unit red" style="margin-top: -6px;">(<%=getLocaleString("_L_0100")%>)</p></a></li>
					</ul>

					<!--메인마켓 시작-->
					<ul id="tabs3-1" class="main-market">
						<li>
							<div class="sch-coin-group">
								<p class="sch-coin-input">
									<input type="text" id="mainSearch" name="" placeholder="<%=getLocaleString("_L_0101")%>" class="sch-input">
								</p>
								<span class="markwrap">
									<span class="bookmark filter"></span>
								</span>
								<span class="markwrap">
									<button class="list-set-btn"><%=getLocaleString("_L_0102")%></button>
								</span>
								<div class="list-set">
									<div class="tit"><%=getLocaleString("_L_0103")%></div>
									<ul>
										<li>
											<label>
												<input type="checkbox" id="mainDaebi" checked>
												<em></em>
												<%=getLocaleString("_L_0104")%>
											</label>
										</li>
										<li>
											<label>
												<input type="checkbox" id="mainAmt">
												<em></em>
												<%=getLocaleString("_L_0105")%>
											</label>
										</li>
									</ul>
								</div>
							</div>
						</li>
						<li>
							<div id="tab-group-1">
								<ul class="li3 linetab" id="mainMarket"></ul>
							</div>
						</li>
					</ul>
					<!--메인마켓 끝-->
					<%--
					<!--프리마켓 시작-->
					<ul id="tabs3-2" class="free-market">
						<li>
							<div class="sch-coin-group">
								<p class="sch-coin-input">
									<input type="text" id="freeSearch" name="" placeholder="<%=getLocaleString("_L_0106")%>" class="sch-input">
								</p>
								<span class="markwrap">
									<span class="bookmark filter"></span>
								</span>
								<span class="markwrap">
									<button class="list-set-btn"><%=getLocaleString("_L_0102")%></button>
								</span>
								<div class="list-set">
									<div class="tit"><%=getLocaleString("_L_0103")%></div>
									<ul>
										<li>
											<label>
												<input type="checkbox" id="freeDaebi" checked>
												<em></em>
												<%=getLocaleString("_L_0104")%>
											</label>
										</li>
										<li>
											<label>
												<input type="checkbox" id="freeAmt">
												<em></em>
												<%=getLocaleString("_L_0105")%>
											</label>
										</li>
									</ul>
								</div>
							</div>
						</li>
						<div id="tab-group-7">
							<ul class="li4 linetab" id="freeMarket"></ul>
						</div>
					</ul>
					--%>
					
					<div id="tabs3-3" class="my_coins">
						<p class="unit red">
							<%=getLocaleString("_L_0107")%><br>
							<%=getLocaleString("_L_0108")%>
						</p>
						<div class="stocks-list">
							<ul>
								<li><a href="javascript:void(0);" class="sort-toggle"><%=getLocaleString("_L_0027")%></a></li>
								<li><a href="javascript:void(0);" class="sort-toggle"><%=getLocaleString("_L_0003")%><br><%=getLocaleString("_L_0129")%></a></li>
								<li><a href="javascript:void(0);" class="sort-toggle"><%=getLocaleString("_L_0109")%></a></li>
								<li><a href="javascript:void(0);" class="sort-toggle"><%=getLocaleString("_L_0110")%></a></li>
							</ul>
							<table class="mCustomScrollbar">
								<colgroup>
									<col>
									<col style="width: 50px">
									<col style="width: 120px">
									<col style="width: 110px">
									<col style="width: 150px">
								</colgroup>
								<tbody id="balanceList"></tbody>
							</table>
						</div>
					</div>
				</div>
			</aside>
		</article>
	</div>
</section>

<div class="popup-wrap" id="noticeTrading" tabindex="0"> 
	<div class="pop-content" >
		<h2><%=getLocaleString("_L_0111")%></h2>
		<div class="info-txt">
			<p><%=getLocaleString("_L_0112")%></p>
			<p>
				<%=getLocaleString("_L_0113")%>
				<br/><%=getLocaleString("_L_0114")%>
				<br/><%=getLocaleString("_L_0115")%>
				<br/><%=getLocaleString("_L_0116")%>
				<br/><%=getLocaleString("_L_0117")%>
				<br/><%=getLocaleString("_L_0118")%>
				<br/><%=getLocaleString("_L_0119")%>
			</p>
		</div>
		<!-- 체크박스 : Start -->
		<div class="popup-img-func">
			<p>
				<label><input type="checkbox" checked><em></em><%=getLocaleString("_L_0120")%></label>
			</p>
			<p><label><input type="checkbox" checked><em></em><%=getLocaleString("_L_0121")%></label></p>
		</div>
		<!-- 체크박스 : end -->
		
		<!-- 확인 버튼 : Start -->
		<div class="btn-wrap">
			<button class="big" id="notiAgree"><%=getLocaleString("_L_0122")%></button>
		</div>
		<!-- //확인 버튼 : End -->
	</div>
</div>

<div class="popup-wrap" id="lrtSell" tabindex="0">
	<div class="popup alert">
		<div class="alert-header">
			<p class="alert-title"></p>
			<a class="top-close"><img src="/resources/img/btn-alert-close.png"></a>
		</div>
		<div class="alert-content">
			<p class="alert-message"></p>
		</div>
		<div class="alert-btn">
			<button class="blue" id="lrtSellOk"><%=getLocaleString("_L_0123")%></button>
			<button class="gray" id="lrtSellCncl"><%=getLocaleString("_L_0124")%></button>
		</div>
	</div>
</div>

<div class="popup-wrap" id="lrtBuy" tabindex="0">
	<div class="popup alert">
		<div class="alert-header">
			<p class="alert-title"></p>
			<a class="top-close"><img src="/resources/img/btn-alert-close.png"></a>
		</div>
		<div class="alert-content">
			<p class="alert-message"></p>
		</div>
		<div class="alert-btn">
			<button class="red" id="lrtBuyOk"><%=getLocaleString("_L_0123")%></button>
			<button class="gray" id="lrtBuyCncl"><%=getLocaleString("_L_0124")%></button>
		</div>
	</div>
</div>

<c:if test="${sessionScope.userInfo.loginYn == 1}">
<script>
let noti = cu.getCookie('tradeNotice');

if(noti === '') {
	//$('#noticeTrading').show();  // 위험 공지 임시 삭제
}
</script>
</c:if>
	
<script>
var activeGrpId
var style = '${style}';
$(document).ready(function() {
	$('#tab-group-2').tabs({
		create: function(e, u) {
			activeGrpId = u.panel[0].id;
		},
		activate: function(e, u) {
			activeGrpId = u.newPanel[0].id;
		}
	}).show();
	
	$('#tab-group-3').tabs({
		activate : function(e, u) {
			$('.list-set').hide();
		}
	}).show();
	$('#tab-group-4').tabs().show();
	$('#tab-group-5').tabs().show();
	$('#tab-group-6').tabs().show();
	$('.askingtable').show();
	$('.chart-wrap').show();
	
	$(".slider_notice").slick({
		dots: false,
		vertical: true,
		autoplay: true,
		autoplaySpeed: 3000,
		slidesToShow: 1,
		slidesToScroll: 1
	}).show();
});

$('.list-set-btn').click(function() {
	$(this).parents('span').next().toggle();
});

  // Tab Mobile
  function tabMenuMobile(evt, tabName) {
        var i, tabcontent, tablinks;
        tabcontent = document.getElementsByClassName("tab-content");
        for (i = 0; i < tabcontent.length; i++) {
            tabcontent[i].style.display = "none";
        }
        tablinks = document.getElementsByClassName("item_m");
        for (i = 0; i < tablinks.length; i++) {
            tablinks[i].className = tablinks[i].className.replace(" on", "");
        }
        document.getElementById(tabName).style.display = "inline-block";
        evt.currentTarget.className += " on";
    }
</script>

