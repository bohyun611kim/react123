<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/taglib/taglib.jsp" %>

<script>
var item = {mktId:'${mktId}',mktGrpId:'${mktGrpId}',itemCode:'${itemCode}'};
var statsCd = ${statusCode};
var sellBuyCd = ${sellBuyCode};
var stats = {}, sellBuy = {};

for(let i=0; i<statsCd.length; i++) {
	stats[statsCd[i].codeNumVal] = statsCd[i].codeValNm;
}

for(let i=0; i<sellBuyCd.length; i++) {
	sellBuy[sellBuyCd[i].codeNumVal] = sellBuyCd[i].codeValNm;
}

setUrl(item.itemCode);

var grpList = '${list}';
grpList = JSON.parse(grpList);
for(let i=0; i<grpList.length; i++) {
	createServerSentEvents(realContractLister, grpList[i] + '/contract');
}

function realContractLister(data, flag) {
	if(document.visibilityState == 'visible') {
		data = JSON.parse(data);
		
		console.log('실시간 체결내역 push');
		console.log(data);
		
		if(data.msgCode === 106) {
			if(data.body.itemCode === item.itemCode 
					&& data.body.mktGrpId === item.mktGrpId) {
				reSetHeader(data.body);
				addRealContract(data.body);
			}
		}
	}
}

createServerSentEvents(publicLister, 'COINIS/public');
function publicLister(data) {
	data = JSON.parse(data);
	
	if(data.msgCode == 999) {
		console.log('공지사항 push');
		console.log(data);
	} else if(data.msgCode == 111) {
		console.log('서버시간 push');
		console.log(data);
	}
}
</script>

<c:if test="${sessionScope.userInfo.loginYn == 1}">
<script>
toastr.options = {
	"closeButton": false,
	"debug": false,
	"newestOnTop": false,
	"progressBar": true,
	"positionClass": "toast-top-right",
	"preventDuplicates": false,
	"showDuration": "300",
	"hideDuration": "1000",
	"timeOut": "10000",
	"extendedTimeOut": "1000",
	"showEasing": "swing",
	"hideEasing": "linear",
	"showMethod": "fadeIn",
	"hideMethod": "fadeOut"
};

createServerSentEvents(privateLister, 'COINIS/${sessionScope.userInfo.userId}');
function privateLister(data, flag) {
	if(document.visibilityState == 'visible') {
		data = JSON.parse(data);
		switch(data.msgCode) {
		case 103:
			console.log('주문서버 : 신규 접수 거부');
			console.log(data);
			if(data.body.itemCode === item.itemCode
					&& data.body.mktGrpId === item.mktGrpId) {
				data.body.ordStatus = 211;
				addOrder(data);
			}
			break;
		case 104:
			console.log('체결서버 : 신규 접수 확인');
			console.log(data);
			if(data.body.itemCode === item.itemCode
					&& data.body.mktGrpId === item.mktGrpId) {
				addNonContract(data);
				
				data.body.ordStatus = 301;
				addOrder(data);
				
				if(parseInt(data.body.sellBuyRecogCd) === 2) {
					item.nonQty += parseFloat(data.body.ordQty);
					reDrawBalanceData();
				}
			}
			break;
		case 105:
			console.log('체결서버 : 신규 접수 거부');
			console.log(data);
			lrt.client(wordbook[data.body.ResCode]);
			if(data.body.itemCode === item.itemCode
					&& data.body.mktGrpId === item.mktGrpId) {
				data.body.ordStatus = 311;
				addOrder(data);
			}
			break;
		case 108:
			console.log('체결서버 : 회원 체결 내역');
			console.log(data);
			if(data.body.itemCode === item.itemCode
					&& data.body.mktGrpId === item.mktGrpId) {
				addContract(data.body);
				
				if(parseFloat(data.body.nonContractQty) === 0) {
					removeNonCotnract(data.body);
				} else {
					updateNonContract(data.body);
				}
				
				if(parseInt(data.body.sellBuyRecogCd) === 2) {
					item.nonQty = nu.minus(item.nonQty, parseFloat(data.body.contractQty));
					reDrawBalanceData();
				}
			}
			
			if($('#cb1').is(':checked')) {
				let message = 'Price : ' + nu.cm(data.body.contractPrice, item.prcDspDecPnt) + '<br>' 
								+ 'Qty : ' + nu.cm(data.body.contractQty, item.qtyDspDecPnt) + '<br>'
								+ 'ontract ' + sellBuy[parseFloat(data.body.sellBuyRecogCd)]+ ' order.';
				toastr['info'](message);
			}
			
			break;
		case 203:
			console.log('주문서버 : 취소접수거부');
			console.log(data);
			if(data.body.itemCode === item.itemCode
					&& data.body.mktGrpId === item.mktGrpId) {
				lrt.client(wordbook[data.body.ResCode]);
				data.body.ordStatus = 212;
				addOrder(data);
			}
			break;
		case 205:
			console.log('체결서버 : 취소접수거부');
			console.log(data);
			if(data.body.itemCode === item.itemCode
					&& data.body.mktGrpId === item.mktGrpId) {
				lrt.client(wordbook[data.body.ResCode]);
				data.body.ordStatus = 312;
				addOrder(data);
			}
			break;
		case 206:
			console.log('체결서버 : 취소 결과');
			console.log(data);
			if(data.body.itemCode === item.itemCode
					&& data.body.mktGrpId === item.mktGrpId) {
				cancelNonCotnract(data.body);
				data.body.ordStatus = 321;
				addOrder(data);
				
				if(parseInt(data.body.sellBuyRecogCd) === 2) {
					item.nonQty += parseFloat(data.body.ordQty);
					reDrawBalanceData();
				}
			}
			break;
		case 501:
			console.log('잔고변경내역');
			console.log(data);
			reDrawBalance(data.body);
			break;
		}
	}
}
</script>
</c:if>

<script id="MARKET_HEADER" type="template/text">
<li><a href="#mkt{{mktNm}}">{{mktNm}}</a></li>
</script>

<script id="MARKET_BODY" type="template/text">
<div id="mkt{{market}}" class="btc-market">
	<div class="stocks-list">
		<a style="cursor:pointer;" class="sort-toggle">Pair</a>
		<a style="cursor:pointer;" class="sort-toggle">Last Price</a>
		<a style="cursor:pointer;" class="sort-toggle">24h Change</a>
		<a style="cursor:pointer;" class="sort-toggle">24h Volume</a>
	</div>
	<div class="table-select scrollbar mCustomScrollbar">
		<table>
			<colgroup>
				<col>
				<col>
				<col>
				<col>
				<col>
			</colgroup>
			<tbody class="stocks-list-inner">
				{{body}}
			</tbody>
		</table>
	</div>
</div>
</script>

<script id="MARKET_ITME" type="template/text">
<tr class="{{active}}" data-mkt="{{mktId}}" data-grp="{{mktGrpId}}" data-item="{{itemCode}}">
	<td>
		<span class="bookmark">관심코인</span>
	</td>
	<td>
		<p class="coin-name">{{symbol}}</p>
	</td>
	<td>
		<p class="{{color}}" data-pnt="{{prcPnt}}">{{closePrc}}</p>
	</td>
	<td>
		<p class="{{color}}">{{daebiRate}}</p>
	</td>
	<td class="vertual-coin">
		<p data-pnt="{{volPnt}}">{{tradeVol}}</p>
	</td>
</tr>
</script>

<script id="BALANCE" type="template/text">
<tr data-coin="{{coinNo}}" data-unit="{{unit}}" data-key="{{itemCode}}">
	<td>
		<span class="my-coin-symbol">
			<img src="/resources/img/coin-symbol/{{coinNm}}.png" alt="보유코인심볼">
		</span>
	</td>
	<td data-sort="{{coinNm}}">
		<p class="coin-name">{{coinNm}}</p>
	</td>
	<td data-sort="{{sortEvalPrc}}">
		<p data-pnt="{{coinPnt}}">{{qty}}</p>
		<p>
			<span data-pnt="{{bcPnt}}">{{evalPrc}} {{unit}}</span>
		</p>
	</td>
	<td data-sort="{{sortAvg}}">
		<p>{{buyAvgPrc}} <span>{{unit}}</span></p>
	</td>
	<td data-sort="{{sortRate}}">
		<p class="{{color}}">{{rate}}</p>
		<p class="{{color}}">{{profit}}</p>
	</td>
</tr>
</script>

<script id="HOGA" type="template/text">
<tr>
	<td>{{prc}}</td>
	<td>{{qty}}</td>
	<td class="{{color}}" style="width:{{per}}%;"></td>
</tr>
</script>

<script id="REAL_CONTRACT" type="template/text">
<tr>
	<td>{{time}}</td>
	<td class="{{color}}">{{prc}}</td>
	<td>{{qty}}</td>
</tr>
</script>

<script id="ORDER" type="template/text">
<tr>
	<td>{{no}}</td>
	<td>{{dt}}</td>
	<td class="{{color}}">{{sellBuy}}</td>
	<td>{{qty}}</td>
	<td>{{prc}}</td>
	<td>{{amt}}</td>
	<td>{{nonQty}}</td>
	<td>{{status}}</td>
	<td>{{origin}}</td>
</tr>
</script>

<script id="CONTRACT" type="template/text">
<tr>
	<td>{{dt}}</td>
	<td>{{ordQty}}</td>
	<td>{{ordPrc}}</td>
	<td>{{contractDt}}</td>
	<td>{{contractPrc}}</td>
	<td>{{contractQty}}</td>
	<td>{{contractAmt}}</td>
	<td>{{nonContractQty}}</td>
	<td class="{{color}}">{{sellBuy}}</td>
</tr>
</script>

<script id="NON_CONTRACT" type="template/text">
<tr data-tran="{{tranNo}}" data-ord="{{ordNo}}" data-exc="{{excNo}}">
	<td>{{no}}</td>
	<td>{{itemCode}}</td>
	<td class="{{color}}">{{sellBuy}}</td>
	<td>{{ordPrc}}</td>
	<td>{{ordQty}}</td>
	<td>{{contractPrc}}</td>
	<td>{{contractQty}}</td>
	<td>{{nonContractQty}}</td>
	<td>{{ordDt}}</td>
	<td><a style="cursor:pointer;" class="btn-cancel">주문취소</a> </td>
</tr>
</script>

<script id="COIN_INFO" type="template/text">
<div class="pop-coinsymbol">
	<div>
		<img src="/resources/img/coin-symbol/{{symbol}}.png" alt="coin symbol">
	</div>
	<div>
		<p class="font16"><b>{{coinEngNm}}</b></p>
		<span>심볼 <span>{{symbol}}</span></span>
	</div>
</div>

<div>
	<h3>- 개발자 정보</h3>
	<div>
		<table>
			<colgroup>
				<col style="width: 160px;">
				<col>
			</colgroup>
			<tbody>
				<tr>
					<th>법인(재단)명</th>
					<td>{{foundNm}}</td>
				</tr>
				<tr>
					<th>법인소재지</th>
					<td>{{foundLoc}}</td>
				</tr>
				<tr>
					<th>대표인물</th>
					<td>{{repNm}}</td>
				</tr>
				<tr>
					<th>대표홈페이지</th>
					<td><a href="{{homepage}}" target="_blank">{{homepage}}</a></td>
				</tr>
			</tbody>
		</table>
	</div>
</div>

<div>
	<h3>- 코인 개요</h3>
	<div>
		<table>
			<colgroup>
				<col style="width: 160px;">
				<col>
			</colgroup>
			<tbody>
				<tr>
					<th>최초발행</th>
					<td>{{issueDay}}</td>
				</tr>
				<tr>
					<th>총 발행한도</th>
					<td>{{totSupply}}</td>
				</tr>
				<tr>
					<th>합의 프로토콜</th>
					<td>{{proofProtocol}}</td>
				</tr>
				<tr>
					<th>백서</th>
					<td><a href="{{whitePaper}}" target="_blank">{{whitePaper}}</a></td>
				</tr>
			</tbody>
		</table>
	</div>
</div>

<div>
	<h3>- 코인 소개</h3>
	<table>
		<tbody>
			<tr><td>{{coinExplanation}}</td></tr>
		</tbody>
	</table>
</div>
</script>

<div class="whole-wrap">
	<div class="main-wrap">
		<div class="sub-wrap">
			<div class="contain">
				<header>
					<div class="dark-logo">
						<a href="/coinis/home">&nbsp;</a>
					</div>

					<div class="coin-symbol">
						<!-- <img src="/resources/img/coin-symbol/AE.png" alt="코인심볼"> -->
					</div>

					<div class="coins-name">
						<p><span><span id="coinsName"></span><i class="icon-down"></i></span></p>
						<p class="toUnit"></p>
					</div>

					<div class="now-price">
						<p></p>
						<p></p>
					</div>

					<div class="head-analysis">
						<div class="per-price">
							<p>전일대비 </p>
							<p id="perPrice"></p>
						</div>
						<div class="high-price">
							<p>고가</p>
							<p id="highPrice"></p>
						</div>
						<div class="low-price">
							<p>저가</p>
							<p id="lowPrice"></p>
						</div>
						<div class="deal-24h">
							<p>24h 거래량</p>
							<p id="deal24h"></p>
						</div>
						<div class="price-24h">
							<p>24h 거래대금</p>
							<p id="price24h"></p>
						</div>
					</div>

					<div class="head-etc">
						<div class="bg-light-dark">
							<div class="lang" title="Select Language">
								<a href="#">
									<span><img src="/resources/img/country/kr.png" alt="img"></span>
								</a>
							</div>
							<div class="light" title="Background Light">
								<span onclick="javascript:changeCSS('uiLight')">Light</span>
							</div>
							<div class="dark" title="Background Dark">
								<span onclick="javascript:changeCSS('uiDark')">Dark</span>
							</div>
							<div class="coininfo" title="Coin Infomation"></div>
						</div>
							
						<div class="lang-layer">
							<ul>
								<li>
									<a style="cursor:pointer;">
										<i><img src="/resources/img/country/kr.png" alt="한국어"></i>
										<span>한국어</span>
									</a>
								</li>
								<li>
									<a style="cursor:pointer;">
										<i><img src="/resources/img/country/us.png" alt="usa"></i>
										<span>English</span>
									</a>
								</li>
								<li>
									<a style="cursor:pointer;">
										<i><img src="/resources/img/country/ch.png" alt="China"></i>
										<span>China</span>
									</a>
								</li>
								<li>
									<a style="cursor:pointer;">
										<i><img src="/resources/img/country/ph.png" alt="Philippines"></i>
										<span>Philippines</span>
									</a>
								</li>
							</ul>
						</div>

						<div class="login-join-lang-wrap" style="margin-left: 5%">
							<div class="login-join-lang">
								<span class="login-show" style="width: 49%" onclick="location.href='/coinis/login';">로그인</span>
								<span class="join" style="width: 49%">회원가입</span>
							</div>
						</div>

						<c:if test="${sessionScope.userInfo.loginYn ne 1}">
						<div class="login-layer">
							<h2><span>로그인</span></h2>

							<div class="idpw-in">
								<p>Email 또는 ID</p>
								<div style="height: 40px"><input type="text"></div>
								<p>Password</p>
								<div style="height: 40px"><input type="password"></div>
								<div class="btn-login white">
									<a href="#" class="btn-gray">로그인</a>
								</div>
								<div>
									<p><a href="#">ID/비밀번호를 잊으셨나요?</a></p>
									<a href="#">회원가입</a>
								</div>
							</div>
						</div>
						</c:if>
					</div>
				</header>

				<div class="contents-wrap">
					<div class="contents-left">
						<!--차트영역 시작-->
						<div class="contents-left-chart">
							<!--aside 마켓코인 레이어 팝업 시작 : 헤더에 코인마켓 누르면 해당 레이어 뜸-->
							<aside class="market-coins" id="market" style="display: none">
								<div class="tab-aside">
									<ul id="marketList">
										<li><a href="#holding">Holding</a></li>
									</ul>
								</div>
								
								<div class="search-coin" id="search">
									<p class="search-coin-input">
										<input type="text" placeholder="코인 검색" class="sch-input">
										<label for="" class="sch-btn-del">삭제</label>
									</p>
									<span class="checks-item only-have">
										<span class="bookmark">관심코인</span>
									</span>
								</div>

								<!-- 보유코인 탭 시작 -->
								<div id="holding" class="my-coin">
									<div class="stocks-list">
										<a style="cursor:pointer;" class="sort-toggle1">코인명</a>
										<a style="cursor:pointer;" class="sort-toggle1">보유(평가금)</a>
										<a style="cursor:pointer;" class="sort-toggle1">매수평균가</a>
										<a style="cursor:pointer;" class="sort-toggle1">수익률</a>
									</div>
									<div class="table-select scrollbar mCustomScrollbar">
										<table>
											<colgroup>
												<col>
												<col>
												<col>
												<col>
											</colgroup>
											<tbody class="stocks-list-inner">
											</tbody>
										</table>
									</div>
								</div>
								<!--보유코인 탭 끝-->
							</aside>
							<!--aside 마켓코인 레이어 팝업 끝-->

							<!--차트 시작-->
							<div class="chart-img" id="chart"></div>
							<!--차트 끝-->
						</div>
						<!--차트영역 끝-->

							<div class="contents-left-down">
								<div class="tab">
									<button class="tablinks" onclick="openCity(event, 'tabs1-1')" id="defaultOpen">주문내역</button>
									<button class="tablinks" onclick="openCity(event, 'tabs1-2')">체결내역</button>
									<button class="tablinks" onclick="openCity(event, 'tabs1-3')">미체결내역</button>
									<button class="tablinks" onclick="openCity(event, 'tabs1-4')">보유잔고</button>
								</div>

								<div id="tabs1-1" class="tabcontent scrollbar">
									<!-- 주문내역 시작 -->
									<div class="order-title">
										<table>
											<thead>
												<tr>
													<th>주문번호</th>
													<th>접수시간</th>
													<th>매매구분</th>
													<th>주문수량</th>
													<th>주문가격</th>
													<th>주문금액</th>
													<th>미체결수량</th>
													<th>주문상태</th>
													<th>원주문번호</th>
												</tr>
											</thead>
										</table>
									</div>
									<div class="order-wrap mCustomScrollbar">
										<c:choose>
											<c:when test="${sessionScope.userInfo.loginYn == 1}">
										<table>
											<tbody id="orderList">
												<tr>
													<td colspan="10" class="txt-center wdt100 gray"> 주문이 없습니다.</td>
												</tr>
											</tbody>
										</table>
											</c:when>
											<c:otherwise>
										<div class="need-login-area">
											<div class="need-login">
												<a href="#" class="btn-deepgray">로그인이 필요합니다.</a>
											</div>
										</div>
											</c:otherwise>
										</c:choose>
									</div>
									<!-- 주문내역 끝 -->
								</div>

								<div id="tabs1-2" class="tabcontent scrollbar">
									<!-- 체결내역 시작 -->
									<div class="deal-title">
										<table>
											<thead>
												<tr>
													<th>주문일시</th>
													<th>주문수량</th>
													<th>주문가격</th>
													<th>체결일시</th>
													<th>체결가격</th>
													<th>체결수량</th>
													<th>체결금액</th>
													<th>미체결잔량</th>
													<th>매수/매도</th>
												</tr>
											</thead>
										</table>
									</div>
									<div class="deal-wrap mCustomScrollbar">
										<c:choose>
											<c:when test="${sessionScope.userInfo.loginYn == 1}">
										<table>
											<tbody id="contractList">
												<tr>
													<td colspan="10" class="txt-center wdt100 gray"> 주문이 없습니다.</td>
												</tr>
											</tbody>
										</table>
											</c:when>
											<c:otherwise>
										<div class="need-login-area">
											<div class="need-login">
												<a href="#" class="btn-deepgray">로그인이 필요합니다.</a>
											</div>
										</div>
											</c:otherwise>
										</c:choose>
									</div>
									<!-- 체결내역 끝 -->
								</div>

								<div id="tabs1-3" class="tabcontent scrollbar">
									<!-- 미체결내역 시작 -->
									<div class="nodeal-title">
										<table>
											<thead>
												<tr>
													<th>주문번호</th>
													<th>종목명</th>
													<th>매매구분</th>
													<th>주문가</th>
													<th>주문수량</th>
													<th>체결가</th>
													<th>체결수량</th>
													<th>미체결수량</th>
													<th>주문일시</th>
													<th>주문취소</th>
												</tr>
											</thead>
										</table>
									</div>
									<div class="nodeal-wrap mCustomScrollbar">
										<c:choose>
											<c:when test="${sessionScope.userInfo.loginYn == 1}">
										<table>
											<tbody id="nonContractList">
												<tr>
													<td colspan="10" class="txt-center wdt100 gray"> 주문이 없습니다.</td>
												</tr>
											</tbody>
										</table>
											</c:when>
											<c:otherwise>
										<div class="need-login-area">
											<div class="need-login">
												<a href="#" class="btn-deepgray">로그인이 필요합니다.</a>
											</div>
										</div>
											</c:otherwise>
										</c:choose>
									</div>
									<!-- 미체결내역 끝 -->
								</div>

								<div id="tabs1-4" class="tabcontent scrollbar">
									<div class="holding-title">
										<table>
											<thead>
												<tr>
													<th>보유수량</th>
													<th>사용가능수량</th>
													<th>미체결수량</th>
													<th>BTC 환산수량</th>
													<th>Deposit/Withdrawal</th>
												</tr>
											</thead>
										</table>
									</div>

									<div class="holding-wrap mCustomScrollbar">
										<table>
											<tbody>
												<c:choose>
													<c:when test="${sessionScope.userInfo.loginYn == 1}">
												<tr id="balanceData">
													<td></td>
													<td></td>
													<td></td>
													<td></td>
													<td>
														<a href="#" class="btn-orange">Deposit</a>
														<a href="#" class="btn-green">Withdrawal</a>
													</td>
												</tr>
													</c:when>
													<c:otherwise>
												<tr>
													<td colspan="5" class="txt-center wdt100 gray"> 로그인 하셔야 확인이 가능합니다.</td>
												</tr>
													</c:otherwise>
												</c:choose>
											</tbody>
										</table>
									</div>
								</div>
							</div>

						</div>

						<div class="contents-right">
							<!--호가주문, 체결내역 시작-->
							<div class="contents-right-up">
								<div class="hoga-whole">
									<div class="hoga-wrap">
										<div class="hoga-sort">
											<div class="hoga-title">주문내역</div>
											<ul>
												<li><span class="buy-sort">매수정렬</span></li>
												<li><span class="sell-sort">매도정렬</span></li>
												<li><span class="all-sort active"> 모두정렬</span></li>
											</ul>
										</div>

										<div class="hoga-col-title">
											<span>가격</span>
											<span class="j-end">수량</span>
											<!--<span class="j-end">주문금액</span>-->
										</div>

										<!--호가주문 매도리스트 시작-->
										<div class="hoga-sell-whole">
											<div class="hoga-sell">
												<div class="hoga-sell-wrap">
													<table>
														<caption class="blind">매도 호가주문</caption>
														<tbody id="sellList">
														</tbody>
													</table>
												</div>
											</div>
										</div>
										<!--호가주문 매도리스트 끝-->

										<div class="hoga-gijun">
											<div>
												<span id="hogaPrc"></span>
												<span class="basicNm">KRW</span>
											</div>
											<div>
												<span id="hogaDaebi"></span>
												<span>%</span>
											</div>
											<!--<div>$ 0.1313700</div>-->
										</div>

										<!--호가주문 매수리스트 시작-->
										<div class="hoga-buy-whole">
											<div class="hoga-buy">
												<!--호가주문 매도 데이터 영역 시작 -->
												<div class="hoga-buy-wrap">
													<table>
														<caption class="blind">매수 호가주문</caption>
														<tbody id="buyList">
														</tbody>
													</table>
												</div>
												<!--호가주문 매수 데이터 영역 끝 -->
											</div>
										</div>
										<!--호가주문 매도리스트 끝-->
									</div>
								</div>

								<!--체결내역 시작-->
								<div class="contract-whole">
									<div class="contract">
										<div class="contract-title">
											<div>체결내역</div>
											<div>체결알림
												<input type="checkbox" id="cb1">
												<label for="cb1"></label>
											</div>
										</div>
										<div class="contract-col-title">
											<span>체결시간</span>
											<span class="j-center">가격</span>
											<span class="j-end">수량</span>
										</div>
										<!--체결내역 리스트 시작-->
										<div class="contract-wrap scrollbar mCustomScrollbar">

											<div class="contract-list">
												<table style="height: 100%" id="realContractList">
												</table>
											</div>
										</div>
										<!--체결내역 리스트 끝-->
									</div>
								</div>
								<!--체결내역 끝-->
							</div>
							<!--호가주문, 체결내역 끝-->

							<!--로그인전(회원가입 로그인), 로그인후(초기화 매수하기 매도하기) 영역 시작-->
							<div class="buysell-whole">
								<div class="buysell-wrap">

								<!--매수하기 시작-->
								<div class="buy-wrap">
									<form autocomplete="off">
										<div class="form-title">
											<span class="green">매수</span>
											<span>사용가능금액</span>
											<!--로그인전에는 안보입니다.-->
											<span id="possibleBuy"></span>
											<!--로그인전에는 안보입니다.-->
										</div>
										<div class="form-input">
											<p>
												<span>매수가격 :</span>
												<span><input type="text" class="tip-open ordPrc" id="buyPrc" maxlength="17"></span>
												<span class="basicNm"></span>
												<span class="tip">
													키보드 상하방향키로 설정 가능
												</span>
											</p>
											<p>
												<span>매수수량 :</span>
												<span><input type="text" id="buyQty" maxlength="17"></span>
												<span class="coinNm"></span>
											</p>
										</div>

										<div class="buy-percent">
											<div data-per="25">25%</div>
											<div data-per="50">50%</div>
											<div data-per="75">75%</div>
											<div data-per="100">100%</div>
										</div>

										<div class="form-input">
											<p>
												<span>매수총액 :</span>
												<span><input type="text" id="buyAmt" class="green" readonly></span>
												<span class="basicNm"></span>
											</p>
										</div>

										<div class="form-output bd0">
											<span>수수료 :</span>
											<span><input type="text" id="buyFee" class="font12" readonly></span>
											<span class="coinNm"></span>
										</div>

										<div class="form-output bd0">
											<span>총 매수수량 :</span>
											<span>
												<input type="text" id="buyTot" class="font12" readonly>
											</span>
											<span class="coinNm"></span>
										</div>

										<div class="btn-buy-sell">
											<c:choose>
												<c:when test="${sessionScope.userInfo.loginYn == 1}">
											<a class="btn-deepgray basis40" style="cursor:pointer;" onclick="init('buy');">
												초기화
											</a>
											<a class="btn-green basis60" style="cursor:pointer;" onclick="buy();">
												매수하기
											</a>
												</c:when>
												<c:otherwise>
											<a href="/coinis/register" class="btn-deepgray basis40">
												회원가입
											</a>
											<a href="/coinis/login" class="btn-green basis60">
												로그인
											</a>
												</c:otherwise>
											</c:choose>
										</div>
									</form>
								</div>
								<!--매수하기 끝-->

								<!--매도하기 끝-->
								<div class="sell-wrap">
									<form autocomplete="off">
										<div class="form-title">
											<span class="orange">매도</span>
											<span>매도가능수량</span>
											<!--로그인전에는 안보입니다.-->
											<span id="possibleSell"></span>
											<!--로그인전에는 안보입니다.-->
											<span class="coinNm"></span>
											<!--로그인전에는 안보입니다.-->
										</div>
										<div class="form-input">
											<p>
												<span>매도가격 :</span>
												<span><input type="text" class="tip-open1 ordPrc" id="sellPrc" maxlength="17"></span>
												<span class="basicNm"></span>
												<span class="tip1">
													키보드 상하방향키로 설정 가능
												</span>
											</p>
											<p>
												<span>매도수량 :</span>
												<span><input type="text" id="sellQty" maxlength="17"></span>
												<span class="coinNm"></span>
											</p>
										</div>

										<div class="sell-percent">
											<div data-per="25">25%</div>
											<div data-per="50">50%</div>
											<div data-per="75">75%</div>
											<div data-per="100">100%</div>
										</div>

										<div class="form-input">
											<p>
												<span>매도총액 :</span>
												<span><input type="text" id="sellAmt" class="orange" readonly></span>
												<span class="basicNm"></span>
											</p>
										</div>

										<div class="form-output bd0">
											<span>수수료 :</span>
											<span><input type="text" id="sellFee" class="font12" readonly></span>
											<span class="basicNm"></span>
										</div>

										<div class="form-output bd0">
											<span>총 매도금액 :</span>
											<span>
												<input type="text" id="sellTot" class="font12" readonly>
											</span>
											<span class="basicNm"></span>
										</div>

										<div class="btn-buy-sell">
											<c:choose>
												<c:when test="${sessionScope.userInfo.loginYn == 1}">
											<a class="btn-deepgray basis40" style="cursor:pointer;" onclick="init('sell');">
												초기화
											</a>
											<a class="btn-orange basis60" style="cursor:pointer;" onclick="sell();">
												매도하기
											</a>
												</c:when>
												<c:otherwise>
											<a href="/coinis/register" class="btn-deepgray basis40">
												회원가입
											</a>
											<a href="/coinis/login" class="btn-orange basis60">
												로그인
											</a>
												</c:otherwise>
											</c:choose>
										</div>

									</form>
								</div>
								<!--매도하기 끝-->
							</div>
						</div>
						<!--로그인전(회원가입 로그인), 로그인후(초기화 매수하기 매도하기) 영역 끝-->
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<!--긴급공지사항 팝업 시작-->
<div class="popup-wrap pop-small" id="urgent-notice-popup" style="display: none">
	<!-- 팝업내용 : Start -->
	<div class="pop-header">
		<p class="pop-title" id="urgent-popup-header-title">긴급공지</p>
		<span class="pop-top-close" onclick="$('#urgent-notice-popup').hide()">닫기</span>
	</div>
	<div class="pop-content">
		<div class="content-item">
			<div>
				<table>
					<colgroup>
						<col>
						<col style="width:100px;">
					</colgroup>
					<thead>
						<tr>
							<th id="urgent-popup-title" class="text-ellips align-center">이더리움 콘스탄티노플의 하드포크 연기 안내</th>
							<th id="urgent-popup-datetime" class="align-center">2019-01-17<br>13:03:27</th>
						</tr>
					</thead>
					<tbody>
						<!-- 추가주소 없을 때 : Start -->
						<tr>
							<td id="urgent-popup-content" colspan="2">
								안녕하세요 코인이즈 거래소 입니다.<br><br>2019.01.14(월) ~ 2019.01.18(금)에 예정되어 있던 이더리움 콘스탄티노플의 하드포크가 <br><br>보안상의 취약점 발견으로 인하여 일정이 연기되었습니다.<br><br>▶ 이더리움 하드포크 연기 안내.<br><br> * 콘스탄트노플 하드포크에 포함된 개선안(EIP-1283)의 보안상 취약점 발견.<br> - 이더리움 공지 : https://blog.ethereum.org/2019/01/15/security-alert-ethereum-constantinople-postponement/<br><br> * 코인이즈의 이더리움(ETH) 및 ERC20 토큰의 입출금은 중단되지 않습니다.<br><br> * 이더리움 콘스탄티노플 하드포크 관련하여 변동사항 및 이슈 사항이 발생할 경우 공지사항을 통해 안내해 드리겠습니다.<br><br> 코인이즈는 회원님의 자산 보호를 위해 최선을 다하고 있습니다.<br><br> 감사합니다.
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>

		<!-- // 확인 : End -->
		<div class="pop-btn-group">
			<a class="close" onclick="$('#urgent-notice-popup').hide()">확인</a>
		</div>
	</div>
	<!-- //팝업내용 : End -->
</div>
<!--긴급공지사항 팝업 끝-->


<!-- 암호화폐 거래 위험고지 : Start -->
<div style="display: none">
	<div class="popup-back" style="display: block">
		<!--팝업뒤검정배경-->
	</div>
	<div class="popup-wrap">
		<!-- 팝업내용 : Start -->
		<div class="pop-content">
			<div class="content-item">
				<div style="width: 100%">
					<h2 style="font-size: 20px">암호화폐 거래 위험고지</h2>
				</div>
				<div>
					<table>
						<tbody>
							<tr style="border:1px solid #dcdee2">
								<td>
									<p style="font-size: 16px; margin-left: 10px; color: coral; font-weight: bold">
										투자자 피해를 예방하고 안전하게 암호화폐를 거래할 수 있도록 다음과 같이 안내드립니다.</p>

									<p style="padding-left: 10px">
										▪ 암호화폐는 정부나 공공기관이 보증하는 법정화폐가 아닙니다.
										<br />▪ 암호화폐는 규제나 시장 환경 변화에 따라 부정적인 영향을 받을 수 있습니다.
										<br />▪ 암호화폐는 기술적 흠결 혹은 재단의 상황에 따라 상장폐지의 위험성을 가지고 있습니다.
										<br />▪ 24시간 365일 동안 전 세계에서 거래가 이루어지기 때문에 시세가 급변할 수 있습니다.
										<br />▪ 가치 변동폭에 제한이 없기 때문에 시세 급변에 따라 막대한 손실이 발생할 수 있습니다.
										<br />▪ 투자에 대한 결과는 본인의 책임이므로 무리한 투자는 지양하시고, 신중한 투자를 부탁드립니다.
										<br />▪ 19세 미만 미성년자와 외국인은 서비스를 이용할 수 없습니다.
									</p>

									<p style="padding-left: 10px">
										코인이즈는 안전하고 편리한 암호화폐 거래를 위해 최선을 다하겠습니다.
										<br />감사합니다.
									</p>

								</td>
							</tr>
						</tbody>
					</table>

					<!-- 체크박스 : Start -->
					<div class="popup-img-func">
						<label><input type="checkbox"><em></em>본인은 위 암호화폐 거래 위험고지를 충분히 숙지하였으며, 본인의 판단에 따라 투자 합니다.</label>
					</div>
					<div class="popup-img-func">
						<label><input type="checkbox"><em></em>오늘 하루동안 보지 않기</label>
					</div>
					<!-- 체크박스 : end -->

					<!-- 확인 버튼 : Start -->
					<div class="pop-btn-group">
						<a href="#" class="close">확인</a>
					</div>
					<!-- //확인 버튼 : End -->
				</div>
			</div>
		</div>
		<!-- //팝업내용 : End -->
	</div>
</div>
<!-- 암호화폐 거래 위험고지 : End -->

<!--코인정보 팝업-->
<div class="coin-info" id="myForm" style="display: none">
	<div class="popup-back">
		<!--팝업뒤검정배경-->
	</div>
	<div class="popup-wrap">
		<!-- 팝업내용 : Start -->
		<div class="pop-content">
			<div class="content-item">
			</div>
		</div>
		<!-- //팝업내용 : End -->
		<!-- 확인 버튼 : Start -->
		<div class="pop-btn-group">
			<a style="cursor:pointer;" class="close" onclick="$('#myForm').hide();">확인</a>
		</div>
		<!-- //확인 버튼 : End -->
	</div>
</div>
<!-- 코인정보 : End -->

<div class="popup alert" id="lrtBuy">
	<div class="alert-header">
		<p class="alert-title"></p>
		<a class="top-close">
			<img src="/resources/img/btn-alert-close.png" alt="닫기버튼" onclick="$('#lrtCnfrm').hide();">
		</a>
	</div>
	<div class="alert-content">
		<p class="alert-message"></p>
	</div>
	<div class="alert-btn">
		<button class="small bggray">No</button>
		<button class="small bggreen">Yes</button>
	</div>
</div>

<div class="popup alert" id="lrtSell">
	<div class="alert-header">
		<p class="alert-title"></p>
		<a class="top-close">
			<img src="/resources/img/btn-alert-close.png" alt="닫기버튼" onclick="$('#lrtCnfrm').hide();">
		</a>
	</div>
	<div class="alert-content">
		<p class="alert-message"></p>
	</div>
	<div class="alert-btn">
		<button class="small bggray">No</button>
		<button class="small bgorange">Yes</button>
	</div>
</div>

<script type="text/javascript">
//제이쿼리 탭
$(document).ready(function() {
	$("#tab-group-2").tabs();
	$("#tabs").tabs();
	$("#tabs1").tabs();
	
	// Get the element with id="defaultOpen" and click on it
	document.getElementById("defaultOpen").click();
	
	 //호가주문 매도 매수 모두 정렬
	$(".buy-sort").click(function() {
		$(".hoga-sell-whole").hide("blind", {
			direction: "up"
		}, "fast");
		$(".hoga-buy-whole").css("display", "flex");
		$(".hoga-buy-whole").css("height", "100%");
		$(".hoga-buy-wrap tbody").addClass("scroller");

	});

	$(".sell-sort").click(function() {
		$(".hoga-sell-whole").css("display", "flex");
		$(".hoga-sell-whole").css("height", "100%");
		$(".hoga-buy-whole").hide("blind", {
			direction: "down"
		}, "fast");
		$(".hoga-sell-wrap tbody").addClass("scroller");

	});

	$(".all-sort").click(function() {
		$(".hoga-sell-whole").css("flex", "2 2 0");
		$(".hoga-sell-whole").css("display", "flex");
		$(".hoga-buy-whole").css("flex", "2 2 0");
		$(".hoga-buy-whole").css("display", "flex");
		$(".hoga-buy-whole").css("height", "");
		$(".hoga-sell-whole").css("height", "");
		$(".hoga-buy-wrap tbody").removeClass("scroller");
		$(".hoga-sell-wrap tbody").removeClass("scroller");
	});

	//호가주문 리스트 호버시 row배경색 
	$(".hoga-sell-data-list").hover(function() {
		$(this).toggleClass('hoga-hover');
	});

	$(".hoga-buy-data-list").hover(function() {
		$(this).toggleClass('hoga-hover');
	});
	
	//코인이름 옆에 화살표 로테이트 이벤트
	$(".coins-name p").click(function() {
		$(".market-coins").toggle('blind');
		$("i").toggleClass('rotate');
	});

	$(".login-show").click(function() {
		$(".login-layer").toggle('blind');
		$(".login-show").toggleClass('active');
	});

	$(".lang").click(function() {
		$(".lang-layer").toggle('blind');
		/*$(.lang).toggleClass('active');*/
	});
});

$('.light').click(function() {
	$('.dark').css({
		backgroundPosition: ('0px 34px')
	});
	$('.dark').css({
		backgroundColor: ('')
	});
	$('.light').css({
		backgroundColor: ('#dce3e8')
	});
	$('.light').css({
		backgroundPosition: ('')
	});
	$('iframe').attr('src', 'uiLight.html');
});

$('.dark').click(function() {
	$('.dark').css({
		backgroundPosition: ('')
	});
	$('.dark').css({
		backgroundColor: ('#1b2936')
	});
	$('.light').css({
		backgroundPosition: ('-34px 0px')
	});
	$('.light').css({
		backgroundColor: ('')
	});
	//$('iframe').attr('src', 'uiDark.html');
});

//체결 미체결 주문내역 탭
function openCity(evt, cityName) {
	var i, tabcontent, tablinks;
	tabcontent = document.getElementsByClassName("tabcontent");
	for (i = 0; i < tabcontent.length; i++) {
		tabcontent[i].style.display = "none";
	}
	tablinks = document.getElementsByClassName("tablinks");
	for (i = 0; i < tablinks.length; i++) {
		tablinks[i].className = tablinks[i].className.replace(" active", "");
	}
	document.getElementById(cityName).style.display = "block";
	evt.currentTarget.className += " active";
}

//팁
/* $('.tip-open').click(function() {
	$('.tip').toggleClass('tip-on');
});

$('.tip-open1').click(function() {
	$('.tip1').toggleClass('tip-on');
}); */

//어두운 배경 밝은 배경 css 체인지  
function changeCSS(fname) {
	var tg = document.getElementById('wizcss');
	if (tg) {
		tg.href = ('../css/' + fname + '.css');
	}

}
</script>