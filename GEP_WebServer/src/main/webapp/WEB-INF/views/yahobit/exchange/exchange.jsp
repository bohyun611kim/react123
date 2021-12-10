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

createServerSentEvents(publicLister, 'YAHOBIT/public');
function publicLister(data) {
	data = JSON.parse(data);
	
	if(data.msgCode == 999) {
		if(document.visibilityState == 'visible') {
			showNotice(data.body);
		}
	} else if(data.msgCode == 111) {
		if(data.body.curDt.substr(8) == '00') {
			selected.prevClosePrc = selected.closePrc;
			let prevClosePrc = selected.prevClosePrc;
			
			$('#closePrc').closest('td').attr('class', '');
			$('#daebiRate').text('0%');
			$('#daebiPrc').text(0).prev().attr('class', 'rate');
			$('#highPrc').attr('class', '');
			$('#lowPrc').attr('class', '');
			
			$('#prevClosePrc').text(nu.cm(prevClosePrc, selected.prcDspDecPnt));
			$('#todayHigh').text(nu.cm(prevClosePrc, selected.prcDspDecPnt)).attr('class', '')
				.next().text('0.00%').attr('class', '');
			$('#todayLow').text(nu.cm(prevClosePrc, selected.prcDspDecPnt)).attr('class', '')
				.next().text('0.00%').attr('class', '');
			
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
						.next().text(nu.cm(Math.abs(percent), 2) + '%');
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
						.next().text(nu.cm(Math.abs(percent), 2) + '%');
				}
			});
			
			$('#tab-group-1').children('div').not('#mainHolding').find('tr').each(function() {
				let $tds = $(this).children('td');
				$tds.eq(2).children('p').attr('class', 'border');
				$tds.eq(3).children('p')
				.eq(0).text('0%').attr('class', '')
				.next().text(0).attr('class', 'daebiPrc');
			});
			
			$('#tab-group-7').children('div').not('#freeHolding').find('tr').each(function() {
				let $tds = $(this).children('td');
				$tds.eq(2).children('p').attr('class', 'border');
				$tds.eq(3).children('p')
				.eq(0).text('0%').attr('class', '')
				.next().text(0).attr('class', 'daebiPrc');
			});
		}
	}
}

grpList = JSON.parse(grpList);
for(let i=0; i<grpList.length; i++) {
	createServerSentEvents(realContractLister, grpList[i] + '/contract');
}

function realContractLister(data, flag) {
	if(document.visibilityState == 'visible') {
		data = JSON.parse(data);
		
		if(data.msgCode === 106) {
			
			data = data.body;
			if(data.mktGrpId === selected.mktGrpId
					&& data.mktId === selected.mktId) {
				
				if(data.itemCode === selected.itemCode) {
					if(parseFloat(data.autoMiningYn) === 1) data.contractRecogCd = 1;
					realContract(data, flag);
				}
				
				let $tr = $('#tab-group-3').find('tr[data-item=' + data.itemCode + ']');
				if($tr.length > 0) {
					$tr.data('prc', data.closePrice);
					reDrawBalance($tr.data('no'));
				}
			}
		}
	} else {
		realPush.push(data);
	}
}

document.onvisibilitychange = function() {
	if(realPush.length > 0) {
		let length = realPush.length;
		for(let i=0; i<length; i++) {
			realContractLister(realPush[i], false);
		}
		realPush = [];
	}
	
	if(privatePush.length > 0 && (typeof privateLister === 'function')) {
		let length = privatePush.length;
		for(let i=0; i<length; i++) {
			privateLister(privatePush[i], false);
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

createServerSentEvents(privateLister, 'YAHOBIT/${sessionScope.userInfo.userId}');
function privateLister(data, flag) {
	
	if(document.visibilityState == 'visible') {
		data = JSON.parse(data);
		
		if(parseFloat(data.body.autoMiningYn) === 1 && data.msgCode != 501 && data.msgCode != 108) {return;}
		
		switch(data.msgCode) {
		case 103:
			lrt.client(wordbook[data.body.ResCode]);
			break;
		case 104:
			if(parseInt(data.body.ordPriceTypeCd) == 1) {
				if(data.body.itemCode == selected.itemCode) {
					addNonContract(data);
				}
				
				if(flag === undefined && parseFloat(data.body.autoMiningYn) !== 1) {
					let message = '주문 일시 : ' + du.getCstmFrmt(data.body.ordDt, 'yyyyMMddHHmmss', 'yyyy-MM-dd HH:mm:ss') + '<br>'
						+ '종목명 : ' + data.body.itemCode + '<br>'
						+ '수량 : ' + nu.cm(data.body.ordQty, selected.qtyDspDecPnt, true) + '<br>'
						+ '가격 : ' + nu.cm(data.body.ordPrice, selected.prcDspDecPnt, true) + '<br>'
						+ '지정가 주문에 성공하였습니다';
					
					toastr['success'](message, '주문알림');
				}
			} else {
				if(flag === undefined && parseFloat(data.body.autoMiningYn) !== 1) {
					let message = '주문 일시 : ' + du.getCstmFrmt(data.body.ordDt, 'yyyyMMddHHmmss', 'yyyy-MM-dd HH:mm:ss') + '<br>'
						+ '종목명 : ' + data.body.itemCode + '<br>'
						+ '수량 : ' + nu.cm(data.body.ordQty, selected.qtyDspDecPnt, true) + '<br>'
						+ '시정가 주문에 성공하였습니다';
					
					toastr['success'](message, '주문알림');
				}
			}
			break;
		case 105:
			if(data.body.ordPriceTypeCd == 2 && parseFloat(data.body.nonContractQty) != 0) {
				lrt.client('시장가 주문 중 ' + data.body.nonContractQty + ' ' + selected.toUnit + '이(가) 체결되지 않았습니다');
			} else {
				lrt.client(wordbook[data.body.ResCode]);
			}
			break;
		case 108:
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
				let message = '체결 일시 : ' + du.getCstmFrmt(data.body.contractDt, 'yyyyMMddHHmmss', 'yyyy-MM-dd HH:mm:ss') + '<br>'
					+ '종목명 : ' + data.body.itemCode + '<br>'
					+ '수량 : ' + nu.cm(data.body.contractQty, selected.qtyDspDecPnt, true) + '<br>'
					+ '가격 : ' + nu.cm(data.body.contractPrice, selected.prcDspDecPnt, true) + '<br>'
					+ '주문이 체결 되었습니다';
				
				toastr['info'](message, '체결알림');
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
		<p>심볼<span>{{symbol}}</span></p>
	</div>
	<div class="coin-info-txt">
		<div class="coin-info-detail">
			<h2 class="tabletitle">개발자 정보</h2>
			<table>
				<colgroup>
					<col style="width: 150px;">
					<col>
				</colgroup>
				<tbody>
					<tr><th>법인(재단)명</th><td>{{corpNm}}</td></tr>
					<tr><th>법인소재지</th><td>{{corpLoc}}</td></tr>
					<tr><th>대표인물</th><td>{{corpRep}}</td></tr>
					<tr><th>대표홈페이지</th><td><a href="{{corpSite}}" class="blue-link" target="_blank">{{corpSite}}</a></td></tr>
				</tbody>
			</table>
		</div>
		<div class="coin-info-detail">
			<h2 class="tabletitle">암호화폐 개요</h2>
			<table>
				<colgroup>
					<col style="width: 150px;">
					<col>
				</colgroup>
				<tbody>
					<tr><th>최초발행</th><td>{{first}}</td></tr>
					<tr><th>총 발행한도</th><td>{{total}}</td></tr>
					<tr><th>합의 프로토콜</th><td>{{protocol}}</td></tr>
					<tr><th>백서</th><td><a href="{{paper}}" class="blue-link" target="_blank">White Paper</a></td></tr>
				</tbody>
			</table>
		</div>
		<div>
			<h2 class="tabletitle">암호화폐 소개</h2>
			<div class="para-data">{{intro}}</div>
		</div>
	</div>
</div>
</script>

<script id="MKT_TEMPLATE" type="template/text">
<div id="{{id}}" class="">
	<div class="stocks-list">
		<ul>
			<li><a href="javascript:void(0);" class="sort-toggle">암호화폐</a></li>
			<li><a href="javascript:void(0);" class="sort-toggle">현재가</a></li>
			<li><a href="javascript:void(0);" class="sort-toggle">전일대비</a></li>
			<li><a href="javascript:void(0);" class="sort-toggle">거래대금(24H)</a></li>
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
	<td data-sort="{{originAvgPrc}}"><p><span>{{avgPrc}}</span> <span>{{bcUnit}}</span></p></td>
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
		<span class="rate {{icon}}">아이콘</span>
		<span>{{daebiPrc}}</span>
	</td>
	<td class="{{color}}">{{qty}}<br>({{toUnit}})</td>
	<td>{{contractAmt}}<br>({{fromUnit}})</td>
	<td class="{{sellBuyColor}}">{{sellBuy}}</td>
</tr>
</script>

<script id="DAILY_TEMPLATE" type="template/text">
<tr data-index="{{index}}">
	<td>{{dt}}</td>
	<td>{{prc}}</td>
	<td class="{{color}}">{{daebiRate}}</td>
	<td class="{{color}}">
		<span class="rate {{icon}}">아이콘</span>
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
	<td>{{contractQty}}</td>
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
	<td>{{qty}}</td>
	<td>{{ordDt}}</td>
	<td><button>취소</button></td>
</tr>
</script>

<section class="main">
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
							<div class="event-box"><a href="/alibit/support?tab=1&no=${item.getRecSeqNo()}" target="_blank">${item.getTitle()}</a></div>
							</c:forEach>
						</div>
					</div>
					
					<ul class="li2 btntab">
						<li><a href="#tabs6-1">시세</a></li>
						<li><a href="#tabs6-2">정보</a></li>
					</ul>
					<div class="lightdark">
						<button class="dark">달</button>
						<button class="light">해</button>
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
												<span class="rate">아이콘</span>
												<span class="bold" id="daebiPrc"></span>
											</p>
										</td>
										<td>
											<div class="minichart" id="areaChart"></div>
										</td>
										<td>
											<dl class="analysis-price">
												<dt>고가</dt>
												<dd class="" id="highPrc"></dd>
											</dl>
											<dl class="analysis-price">
												<dt>저가</dt>
												<dd class="" id="lowPrc"></dd>
											</dl>
										</td>
										<td>
											<dl class="analysis-price">
												<dt>거래량(24H)</dt>
												<dd>
													<span class="tradeVol"></span>
													<span class="gray volUnit"></span>
												</dd>
											</dl>
											<dl class="analysis-price">
												<dt>거래대금(24H)</dt>
												<dd>
													<span class="tradeAmt"></span>
													<span class="gray amtUnit"></span>
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

			<!-- 거래주문 및 차트영역 : Start -->
			<div class="content-item">

				<!--호가주문테이블 시작-->
				<div class="askingtable" style="display:none;">
					<div class="table-market">
						<table>
							<colgroup>
								<col style="width: 40px">
								<col style="width: 110px">
								<col>
								<col style="width: 60px">
								<col style="width: 110px"">
								<col style="width: 40px">
							</colgroup>
							<thead>
								<tr>
									<th colspan="2">매도잔량</th>
									<th colspan="2">호가</th>
									<th colspan="2">매수잔량</th>
								</tr>
							</thead>
							<tbody class="list1">
								<tr>
									<td class="alCenter"></td>
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
									<td colspan="2" rowspan="10">
										<!--거래량 거래대금 최고 최저 등 시작 -->
										<div class="inner-data-wrap dl-wrap">
											<div class="inner-data-group">
												<dl>
													<dt>거래량(24H)</dt>
													<dd><span class="tradeVol"></span></dd>
													<dd><span class="gray volUnit"></span></dd>
												</dl>
												<dl>
													<dt>거래대금(24H)</dt>
													<dd><span id="tradeAmt"></span></dd>
													<dd><span class="gray" id="amtUnit"></span></dd>
												</dl>
											</div>
											<div class="inner-data-group">
												<dl>
													<dt>52주 최고</dt>
													<dd class="" id="week52high"></dd>
													<dd class="gray"></dd>
												</dl>
												<dl>
													<dt>52주 최저</dt>
													<dd class="" id="week52low"></dd>
													<dd class="gray"></dd>
												</dl>
											</div>
											<div class="inner-data-group">
												<dl>
													<dt>전일종가</dt>
													<dd id="prevClosePrc"></dd>
												</dl>
												<dl>
													<dt>당일고가</dt>
													<dd class="" id="todayHigh"></dd>
													<dd class=""></dd>
												</dl>
												<dl>
													<dt>당일저가</dt>
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
									<td class="alCenter"></td>
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
									<td colspan="2" rowspan="10">
										<!-- 체결가, 체결량 : Start -->
										<div class="inner-data-wrap">
											<table>
												<colgroup>
													<col style="width: 65px">
													<col>
												</colgroup>
												<thead>
													<tr>
														<th>체결가</th>
														<th>체결량</th>
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
									<td class="alCenter"></td>
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
									<td class="alCenter"></td>
								</tr>
								</c:forEach>
							</tbody>
						</table>

						<!-- 버튼 + 총잔량 : End -->
						<div class="bottom-group">
							<div class="btn-group">
								<button class="blue" id="canSellAll">일괄<br>매도취소</a>
							</div>

							<div class="total-price-group">
								<span class="total-price" id="sellTotal"></span>
								<span>
									<p>주문잔량합계</p>
									<p>(<span id="symbol"></span>)</p>
								</span>
								<span class="total-price" id="buyTotal"></span>
							</div>
							
							<div class="btn-group">
								<button class="red" id="canBuyAll">일괄<br>매수취소</a>
							</div>
						</div>
						<!-- //버튼 + 총잔량 : End -->
					</div>
				</div>
				<!--호가주문 테이블 끝-->

				<div class="chart-wrap" style="display:none;">
					<!-- 하단 차트영역 : start -->
					<div class="chart-area" id="candleChart" style="height:472px;"></div>

					<!--지정가 시장가 매수 매도 영역 시작-->
					<div id="tab-group-2" class="buysell-area" style="display:none;">
						<ul class="li5">
							<li><a href="#tabs2-1">지정가</a></li>
							<li><a href="#tabs2-2">시장가</a></li>
							<li><a href="#tabs2-3">자동채굴</a></li>
						</ul>

						<!--지정가 매수매도 시작-->
						<div id="tabs2-1">
							<!--매수매도영역 시작-->

							<!--지정가 매수하기 시작-->
							<div class="pending-buy">
								<div class="buysell-wrap">
									<table>
										<tbody data-type="buy">
											<tr>
												<th>주문가능</th>
												<td colspan="3">
													<div class="readonly">
														<span class="fromQty"></span>
														<span class="amtUnit"></span>
													</div>
												</td>
											</tr>
											<tr>
												<th>매수가격</th>
												<td colspan="3">
													<div class="data-input">
														<input type="text" class="ordPrc" maxlength="17" style="text-align:right;" data-type="buy">
														<a style="cursor:pointer;" class="down"></a>
														<a style="cursor:pointer;" class="up"></a>
													</div>
												</td>
											</tr>
											<tr>
												<th>매수수량</th>
												<td colspan="3">
													<div class="data-input">
														<input type="text" class="tip-open ordQty qty" maxlength="17" data-type="buy">
														<span class="volUnit"></span>
													</div>
													<i class="tip">
														키보드 상하방향키로 설정 가능
													</i>
												</td>
											</tr>
											<tr>
												<th></th>
												<td colspan="3">
													<div class="buy-percent calPercent" data-type="pnd">
														<div>25%</div>
														<div>50%</div>
														<div>75%</div>
														<div>100%</div>
													</div>
												</td>
											</tr>
											<tr>
												<th colspan="2" class="bold">
													주문총액
												</th>
												<td colspan="2" class="totalorder red">
													<span>0</span>
													<span class="amtUnit"></span>
												</td>
											</tr>
											<tr>
												<th colspan="2" class="min_krw">
													<span class="minAmt"></span>
												</th>
												<td colspan="2" class="fee">
													<span>거래 수수료</span>
													<span class="buyFee"></span> %
												</td>
											</tr>
										</tbody>
									</table>
									<div class="btn-buy-sell">
										<a style="cursor:pointer;">
											<c:choose>
												<c:when test="${sessionScope.userInfo.loginYn == 1}">
											<button class="init">초기화</button>
												</c:when>
												<c:otherwise>
											<button onclick="doRegister();">회원가입</button>
												</c:otherwise>
											</c:choose>
										</a>
										<a style="cursor:pointer;">
											<c:choose>
												<c:when test="${sessionScope.userInfo.loginYn == 1}">
											<button type="button" class="red" onclick="doPndBuy(this);">매수하기</button>
												</c:when>
												<c:otherwise>
											<button class="red" onclick="doLogin();">로그인</button>
												</c:otherwise>
											</c:choose>
										</a>
									</div>
								</div>
							</div>
							<!--지정가 매수하기 끝-->

							<!--지정가 매도하기 시작-->
							<div class="pending-sell">

								<div class="buysell-wrap">
									<table>
										<tbody data-type="sell">
											<tr>
												<th>주문가능</th>
												<td colspan="3">
													<div class="readonly">
														<span class="toQty"></span>
														<span class="volUnit"></span>
													</div>
												</td>
											</tr>
											<tr>
												<th>매도가격</th>
												<td colspan="3">
													<div class="data-input">
														<input type="text" class="ordPrc" maxlength="17" style="text-align:right;" data-type="sell">
														<a style="cursor:pointer;" class="down"></a>
														<a style="cursor:pointer;" class="up"></a>
													</div>
												</td>
											</tr>
											<tr>
												<th>매도수량</th>
												<td colspan="3">
													<div class="data-input">
														<input type="text" class="tip-open ordQty qty" maxlength="17" data-type="sell">
														<span class="volUnit"></span>
													</div>
													<i class="tip">
														키보드 상하방향키로 설정 가능
													</i>
												</td>
											</tr>
											<tr>
												<th></th>
												<td colspan="3">
													<div class="buy-percent calPercent" data-type="pnd">
														<div>25%</div>
														<div>50%</div>
														<div>75%</div>
														<div>100%</div>
													</div>
												</td>
											</tr>
											<tr>
												<th colspan="2" class="bold">
													주문총액
													
												</th>
												<td colspan="2" class="totalorder blue">
													<span>0</span>
													<span class="amtUnit"></span>
												</td>
											</tr>
											<tr>
												<th colspan="2" class="min_krw">
													<span class="minAmt"></span>
												</th>
												<td colspan="2" class="fee">
													<span>거래 수수료</span>
													<span class="sellFee"></span> %
												</td>
											</tr>
										</tbody>
									</table>
									<div class="btn-buy-sell">
										<a style="cursor:pointer;">
											<c:choose>
												<c:when test="${sessionScope.userInfo.loginYn == 1}">
											<button class="init">초기화</button>
												</c:when>
												<c:otherwise>
											<button onclick="doRegister();">회원가입</button>
												</c:otherwise>
											</c:choose>
										</a>
										<a style="cursor:pointer;">
											<c:choose>
												<c:when test="${sessionScope.userInfo.loginYn == 1}">
											<button class="blue" onclick="doPndSell(this);">매도하기</button>
												</c:when>
												<c:otherwise>
											<button class="blue" onclick="doLogin();">로그인</button>
												</c:otherwise>
											</c:choose>
										</a>
									</div>

								</div>

							</div>
							<!--지정가 매도하기 끝-->
							<!--매수매도영역 끝-->
						</div>
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
												<th>주문가능</th>
												<td colspan="3">
													<div class="readonly">
														<span class="fromQty"></span>
														<span class="amtUnit"></span>
													</div>
												</td>
											</tr>
											<tr>
												<th>매수수량</th>
												<td colspan="3">
													<div class="data-input">
														<input type="text" class="tip-open qty" maxlength="17" data-type="buy" id="mktBuyQty">
														<span class="volUnit"></span>
													</div>
													<i class="tip">
														키보드 상하방향키로 설정 가능
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
											<tr>
												<th colspan="2" class="bold">
													주문총액
												</th>
												<td colspan="2" class="totalorder red">
													<span id="mktBuyTotOrder">0</span>
													<span class="amtUnit"></span>
												</td>
											</tr>
											<tr>
												<th colspan="2" class="min_krw">
													<span class="minAmt"></span>
												</th>
												<td colspan="2" class="fee">
													<span>거래수수료</span>
													<span class="buyFee"></span> %
												</td>
											</tr>
										</tbody>
									</table>
									<div class="btn-buy-sell">
										<a style="cursor:pointer;">
											<c:choose>
												<c:when test="${sessionScope.userInfo.loginYn == 1}">
											<button class="init">초기화</button>
												</c:when>
												<c:otherwise>
											<button onclick="doRegister();">회원가입</button>
												</c:otherwise>
											</c:choose>
										</a>
										<a style="cursor:pointer;">
											<c:choose>
												<c:when test="${sessionScope.userInfo.loginYn == 1}">
											<button class="red" onclick="doMktBuy(this);">매수하기</button>
												</c:when>
												<c:otherwise>
											<button class="red" onclick="doLogin();">로그인</button>
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
												<th>주문가능</th>
												<td colspan="3">
													<div class="readonly">
														<span class="toQty"></span>
														<span class="volUnit"></span>
													</div>
												</td>
											</tr>
											<tr>
												<th>매도수량</th>
												<td colspan="3">
													<div class="data-input">
														<input type="text" class="tip-open qty" maxlength="17" data-type="sell" id="mktSellQty">
														<span class="volUnit"></span>
													</div>
													<i class="tip">
														키보드 상하방향키로 설정 가능
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
											<tr>
												<th colspan="2" class="bold">
													주문총액
												</th>
												<td colspan="2" class="totalorder blue">
													<span id="mktSellTotOrder">0</span>
													<span class="amtUnit"></span>
												</td>
											</tr>
											<tr>
												<th colspan="2" class="min_krw">
													<span class="minAmt"></span>
												</th>
												<td colspan="2" class="fee">
													<span>거래수수료</span>
													<span class="sellFee"></span> %
												</td>
											</tr>
										</tbody>
									</table>
									<div class="btn-buy-sell">
										<a style="cursor:pointer;">
											<c:choose>
												<c:when test="${sessionScope.userInfo.loginYn == 1}">
											<button class="init">초기화</button>
												</c:when>
												<c:otherwise>
											<button onclick="doRegister();">회원가입</button>
												</c:otherwise>
											</c:choose>
										</a>
										<a style="cursor:pointer;">
											<c:choose>
												<c:when test="${sessionScope.userInfo.loginYn == 1}">
											<button class="blue" onclick="doMktSell(this);">매도하기</button>
												</c:when>
												<c:otherwise>
											<button class="blue" onclick="doLogin();">로그인</button>
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
						<div id="tabs2-3">
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
											<th>주문가능</th>
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
											<th>거래수량</th>
											<td colspan="2">
												<div class="data-input">
													<input type="text" class="tip-open qty" id="autoQty">
													<span class="volUnit"></span>
												</div>
												<i class="tip">
													키보드 상하방향키로 설정 가능
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
												주문총액
											</th>
											<td colspan="2" class="totalorder green">
												<span id="autoOrdAmt">0</span>
												<span class="amtUnit"></span>
											</td>
										</tr>
										<tr>
											<th colspan="2" class="min_krw">
												<span class="minAmt"></span>
											</th>
											<td class="fee">
												거래수수료(
												<span>매도</span>
												<span class="sellFee"></span> % |
												<span>매수 </span>
												<span class="buyFee"></span> %)
											</td>
										</tr>
									</tbody>
								</table>
								<div class="btn-buy-sell">
									<a style="cursor:pointer;">
										<c:choose>
											<c:when test="${sessionScope.userInfo.loginYn == 1}">
										<button class="init">초기화</button>
											</c:when>
											<c:otherwise>
										<button onclick="doRegister();">회원가입</button>
											</c:otherwise>
										</c:choose>
									</a>
									<a style="cursor:pointer;">
										<c:choose>
											<c:when test="${sessionScope.userInfo.loginYn == 1}">
										<button class="green" onclick="doAutoMining(this);">거래하기</button>
											</c:when>
											<c:otherwise>
										<button class="green">로그인</button>
											</c:otherwise>
										</c:choose>
									</a>
									<div class="market_auto_info">
										<p style="font-size:11px;">※ 자동채굴 거래는 현재가로 매수, 매도 한번의 거래량만을 즉시 발생시키는 주문입니다.</p>
										<p style="font-size:11px;">※ 대량의 자동채굴을 원하는 경우 YEP RP - Mining 탭의 Auto Mining 기능을 이용 바랍니다.</p>
										<p style="font-size:11px;">※ 호가창에 주문은 들어가지 않으며, 거래완료 내역은 '체결내역' 및 '회원체결' 창에서 확인할 수 있습니다.</p>
										<p style="font-size:11px; color:red;">※ 자동채굴 특성상 호가창에 주문이 안들어가므로 호가와 시장가의 차이 및 미체결 거래가 발생할 수 있습니다.</p>
									</div>
								</div>

							</div>
							</div>
						</div>
						<!--자동채굴 끝-->

					</div>
					<!--지정가 시장가 매수 매도 영역 끝-->

				</div>
			</div>
			<!-- //거래주문 및  차트영역 : Start -->

			<!--체결내역 일별시세 회원체결내역 회원미체결내역등-->
			<!-- 체결내역, 일별시세 탭 : Start -->
			<div class="content-item">
				<div id="tab-group-5" class="box1" style="display:none;">
					<ul class="li2">
						<li><a href="#tabs5-1">체결내역</a></li>
						<li><a href="#tabs5-2">일별시세</a></li>
					</ul>
					<!-- 체결내역 탭 컨텐츠 : Start -->
					<div id="tabs5-1">
						<!-- 체결내역 리스트 : Start -->
						<dl class="dl-tit1">
							<dt>체결시간</dt>
							<dd>체결가격</dd>
							<dd>변동률</dd>
							<dd>대비</dd>
							<dd>체결수량</dd>
							<dd>체결금액</dd>
							<dd>매수/매도</dd>
						</dl>
						<div class="list-tbl" id="realContractDiv">
							<table class="type01">
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
						<dl class="dl-tit2">
							<dt>일자</dt>
							<dd>종가</dd>
							<dd>변동률</dd>
							<dd>대비</dd>
							<dd>거래량</dd>
							<dd>거래대금</dd>
						</dl>
						<div class="list-tbl" id="dailyDiv">
							<table class="type02">
								<tbody id="daily">
								</tbody>
							</table>
						</div>
						<!-- //일별시세 리스트 : End -->
					</div>
					<!-- //일별시세 탭 컨텐츠 : End -->
				</div>
				<!-- //체결내역, 일별시세 탭 : End -->

				<!--회원체결 미체결 시작-->
				<div id="tab-group-4" class="box2" style="display:none;">
					<ul class="li2">
						<li><a href="#tabs4-1">회원체결</a></li>
						<li><a href="#tabs4-2">회원미체결</a></li>
					</ul>
					<div id="tabs4-1">
						<!--회원체결 시작-->
						<dl class="dl-tit3">
							<dt>체결일시</dt>
							<dd>구분</dd>
							<dd>체결수량</dd>
							<dd>체결가격</dd>
							<dd>체결금액</dd>
						</dl>
						<div class="list-tbl" id="contractDiv">
							<table class="type01">
								<tbody id="contractList">
								</tbody>
							</table>
						</div>
						<!--회원체결 끝-->
					</div>

					<div id="tabs4-2">
						<dl class="dl-tit4">
							<dt>종목명</dd>
							<dd>구분</dd>
							<dd>주문가</dd>
							<dd>체결가</dd>
							<dd>미체결수량</dd>
							<dd>체결수량</dd>
							<dd>주문일시</dd>
							<dd>주문취소</dd>
						</dl>
						<div class="list-tbl mCustomScrollbar">
							<table class="type02">
								<tbody id="nonContractList">
								</tbody>
							</table>
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
				<ul class="li3">
					<li style="width:50%;"><a href="#tabs3-1">메이저마켓</a></li>
					<!-- <li><a href="#tabs3-2">프리마켓</a></li> -->
					<li style="width:50%;line-height:25px;"><a href="#tabs3-3">보유 암호화폐<p class="unit red" style="margin-top: -6px;">(거래가능수량)</p></a></li>
				</ul>

				<!--메인마켓 시작-->
				<ul id="tabs3-1" class="main-market">
					<li>
						<div class="sch-coin-group">
							<p class="sch-coin-input">
								<input type="text" id="mainSearch" name="" placeholder="메이저마켓 암호화폐/심볼 검색" class="sch-input">
							</p>
							<span class="markwrap">
								<span class="bookmark filter"></span>
							</span>
							<span class="markwrap">
								<button class="list-set-btn">화면설정</button>
							</span>
							<div class="list-set">
								<div class="tit">화면 설정</div>
								<ul>
									<li>
										<label>
											<input type="checkbox" id="mainDaebi" checked>
											<em></em>
											전일대비 등락 가격 표시
										</label>
									</li>
									<li>
										<label>
											<input type="checkbox" id="mainAmt">
											<em></em>
											거래대금 KRW 환산 가격 표시
										</label>
									</li>
								</ul>
							</div>
						</div>
					</li>
					<li>
						<div id="tab-group-1">
							<ul class="li4 linetab" id="mainMarket"></ul>
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
								<input type="text" id="freeSearch" name="" placeholder="프리마켓 암호화폐/심볼 검색" class="sch-input">
							</p>
							<span class="markwrap">
								<span class="bookmark filter"></span>
							</span>
							<span class="markwrap">
								<button class="list-set-btn">화면설정</button>
							</span>
							<div class="list-set">
								<div class="tit">화면 설정</div>
								<ul>
									<li>
										<label>
											<input type="checkbox" id="freeDaebi" checked>
											<em></em>
											전일대비 등락 가격 표시
										</label>
									</li>
									<li>
										<label>
											<input type="checkbox" id="freeAmt">
											<em></em>
											거래대금 KRW 환산 가격 표시
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
						※ 미체결 수량을 제외한 거래 가능한 수량으로 실제 보유수량과 차이가 있습니다.<br>
						※ 실제 보유수량은 투자내역 메뉴에서 확인 가능합니다.
					</p>
					<div class="stocks-list">
						<ul>
							<li><a href="javascript:void(0);" class="sort-toggle">암호화폐</a></li>
							<li><a href="javascript:void(0);" class="sort-toggle">수량<br>평가액</a></li>
							<li><a href="javascript:void(0);" class="sort-toggle">매수평균가</a></li>
							<!-- <li><a href="javascript:void(0);" class="sort-toggle">수익률</a></li> -->
						</ul>
						<table class="mCustomScrollbar">
							<colgroup>
								<col>
								<col style="width: 50px">
								<col style="width: 150px">
								<col style="width: 110px">
								<!-- <col style="width: 120px"> -->
							</colgroup>
							<tbody id="balanceList"></tbody>
						</table>
					</div>
				</div>
			</div>
		</aside>
	</article>
</section>

<div class="popup-wrap" id="noticeTrading"> 
	<div class="pop-content" >
		<h2>암호화폐 거래 위험고지</h2>
		<div class="info-txt">
			<p>투자자 피해를 예방하고 안전하게 암호화폐를 거래할 수 있도록 다음과 같이 안내드립니다.</p>
			<p>
				▪ 암호화폐는 정부나 공공기관이 보증하는 법정화폐가 아닙니다.
				<br/>▪ 암호화폐는 규제나 시장 환경 변화에 따라 부정적인 영향을 받을 수    있습니다.
				<br/>▪ 암호화폐는 기술적 흠결 혹은 재단의 상황에 따라 상장폐지의 위험성을   가지고 있습니다.
				<br/>▪ 24시간 365일 동안 전 세계에서 거래가 이루어지기 때문에 시세가 급변할 수 있습니다.
				<br/>▪ 가치 변동폭에 제한이 없기 때문에 시세 급변에 따라 막대한 손실이 발생할 수 있습니다.
				<br/>▪ 투자에 대한 결과는 본인의 책임이므로 무리한 투자는 지양하시고, 신중한 투자를 부탁드립니다.
				<br/>▪ 19세 미만 미성년자와 외국인은 서비스를 이용할 수 없습니다.
			</p>
		</div>
		<!-- 체크박스 : Start -->
		<div class="popup-img-func">
			<p>
				<label><input type="checkbox"><em></em>본인은 위 암호화폐 거래 위험고지를 충분히 숙지하였으며, 본인의 판단에 따라 투자 합니다.</label>
			</p>
			<p><label><input type="checkbox"><em></em>오늘 하루동안 보지 않기</label></p>
		</div>
		<!-- 체크박스 : end -->
		
		<!-- 확인 버튼 : Start -->
		<div class="btn-wrap">
			<button class="big" id="notiAgree">확인</button>
		</div>
		<!-- //확인 버튼 : End -->
	</div>
</div>

<div class="popup-wrap" id="lrtSell">
	<div class="popup alert">
		<div class="alert-header">
			<p class="alert-title"></p>
			<a class="top-close"><img src="/resources/img/btn-alert-close.png"></a>
		</div>
		<div class="alert-content">
			<p class="alert-message"></p>
		</div>
		<div class="alert-btn">
			<button class="blue" id="lrtSellOk">예</button>
			<button class="gray" id="lrtSellCncl">아니오</button>
		</div>
	</div>
</div>

<div class="popup-wrap" id="lrtBuy">
	<div class="popup alert">
		<div class="alert-header">
			<p class="alert-title"></p>
			<a class="top-close"><img src="/resources/img/btn-alert-close.png"></a>
		</div>
		<div class="alert-content">
			<p class="alert-message"></p>
		</div>
		<div class="alert-btn">
			<button class="red" id="lrtBuyOk">예</button>
			<button class="gray" id="lrtBuyCncl">아니오</button>
		</div>
	</div>
</div>

<c:if test="${sessionScope.userInfo.loginYn == 1}">
<script>
let noti = cu.getCookie('tradeNotice');

if(noti === '') {
	$('#noticeTrading').show();
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
</script>