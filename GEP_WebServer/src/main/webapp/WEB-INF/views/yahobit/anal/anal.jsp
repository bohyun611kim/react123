<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/taglib/taglib.jsp" %>

<script id="TODAY_TOP_DAEBI" type="template/text">
<tr>
	<td>
		<span class="coin-symbol">
			<img src="/resources/img/coin-symbol/{{symbol}}.png" alt="coin" class="mCS_img_loaded">
		</span>
	</td>
	<td>
		<p class="coin-name">{{coinNm}}</p>
		<p class="unit">
			<span>{{toUnit}}</span>
			<span>/</span>
			<span>{{fromUnit}}</span>
		</p>
	</td>
	<td class="red">
		<span>{{closePrice}}</span>
		<span class="gray">KRW</span>
	</td>
	<td class="red">
		<span>+</span>
		<span>{{daebi}}</span>
		<span>%</span>
	</td>
</tr>
</script>

<script id="TODAY_TOP_AMT" type="template/text">
<tr>
	<td>
		<span class="coin-symbol">
			<img src="/resources/img/coin-symbol/{{symbol}}.png" alt="coin" class="mCS_img_loaded">
		</span>
	</td>
	<td>
		<p class="coin-name">{{coinNm}}</p>
		<p class="unit">
			<span>{{toUnit}}</span>
			<span>/</span>
			<span>{{fromUnit}}</span>
		</p>
	</td>
	<td class="{{color}}">
		<span>{{tradeAmt}}</span>
		<span class="gray">KRW</span>
	</td>
	<td class="{{color}}">
		<span>{{mark}}</span>
		<span>{{daebi}}</span>
		<span>%</span>
	</td>
</tr>
</script>

<script id="TOP_DAEBI_RANK" type="template/text">
<tr>
	<td>
		<span class="coin-symbol">
			<img src="/resources/img/coin-symbol/{{symbol}}.png" alt="coin" class="mCS_img_loaded">
		</span>
	</td>
	<td>
		<p class="coin-name">{{coinNm}}</p>
		<p class="unit">
			<span>{{toUnit}}</span>
			<span>/</span>
			<span>{{fromUnit}}</span>
		</p>
	</td>
	<td>
		<span class="gray">{{itemCode}}</span>
	</td>
	<td class="red">
		<span>+</span>
		<span>{{daebi}}</span>
		<span>%</span>
	</td>
</tr>
</script>

<script id="COIN_MARKET_CAP" type="template/text">
<tr>
	<td class="num-{{type}}">{{rank}}</td>
	<td>
		<span class="coin-symbol">
			<img src="/resources/img/coin-symbol/{{symbol}}.png" alt="">
		</span>
	</td>
	<td>
		<p class="coin-name">{{coinNm}}</p>
	</td>
	<td>
		<span>{{marketCapital}}</span>
		<span class="unit">{{marketCapitalUnit}}</span>
	</td>
	<td>
		<span>{{closePrice}}</span>
		<span class="unit">원</span>
	</td>
	<td>
		<span>{{tradeAmt}}</span>
		<span class="unit">{{tradeUnit}}</span>
	</td>
	<td>
		<span>{{supply}}</span>
	</td>

	<td class="{{color}}">{{mark}}{{daebi}}</td>
	<td>
		<button {{style}} onclick="moveExchange('{{itemCode}}');">주문</button>
	</td>
</tr>
</script>

<script id="COIN_NEWS" type="template/text">
<tr>
	<td>
		<p class="list-img"><img src="{{imgLink}}" alt="뉴스이미지"></p>
	</td>
	<td>
		<dl>
			<dt>
				<a href="#">{{title}}</a>
			</dt>
			<dd class="unit">
				<span class="date">{{registDt}}</span>
				<span>{{register}}</span>
			</dd>
		</dl>
	</td>
</tr>
</script>

<section class="main">

	<article class="layout">
		<div class="content">
			<h1 class="subtitle">시장동향</h1>

			<!--상승률 시작-->
			<div class="anal-wrap">
				<!--오늘 상승률 시작-->
				<div class="anal">
					<h2 class="tabletitle">오늘 상승률 TOP 10</h2>
					<div class="table-scroll mCustomScrollbar line">
						<table>
							<colgroup>
								<col style="width: 45px;">
								<col>
								<col>
								<col style="width: 80px;">
							</colgroup>
							<tbody id="todayTopDaebi">
							</tbody>
						</table>
					</div>
				</div>
				<!--오늘 상승률 끝-->

				<!--오늘 거래대금 시작-->
				<div class="anal">
					<h2 class="tabletitle">오늘 거래대금 TOP 10</h2>
					<div class="table-scroll mCustomScrollbar line">
						<table>
							<colgroup>
								<col style="width: 45px;">
								<col>
								<col>
								<col style="width: 80px;">
							</colgroup>
							<tbody id="todayTopAmt">
							</tbody>
						</table>
					</div>
				</div>
				<!--오늘 거래대금 끝-->

				<!--오늘 기간별 상승률 시작-->
				<div class="anal">
					<h2 class="tabletitle">기간별 상승률 상위</h2>
					<div class="dropdown-group">
						<ul>
							<li class="dropdown-menu">
								<a href="#" class="menu-item" data-date="7">1주일</a>
								<ul class="dropdown-sub-menu">
									<li><a href="javascript:void(0);" data-date="7">1주일</a></li>
									<li><a href="javascript:void(0);" data-date="30">1개월</a></li>
									<li><a href="javascript:void(0);" data-date="90">3개월</a></li>
									<li><a href="javascript:void(0);" data-date="180">6개월</a></li>
									<li><a href="javascript:void(0);" data-date="365">1년</a></li>
								</ul>
							</li>
						</ul>
					</div>
					<div class="table-scroll mCustomScrollbar line">
						<table>
							<colgroup>
								<col style="width: 45px;">
								<col>
								<col>
								<col style="width: 80px;">
							</colgroup>
							<tbody id="topDaebiRank">
							</tbody>
						</table>
					</div>
				</div>
				<!--오늘 기간별 상승률 끝-->
			</div>
			<!--상승률 끝-->

			<!--코인시가총액 시작-->
			<div style="position: relative; float: left">
				<h2 class="tabletitle">암호화폐 시가총액</h2>

				<div class="coinmarketcap">
					<a href="https://coinmarketcap.com" target="_blank" class="link">코인마켓캡</a> 기준
					<span id="coinmarketcap-base-time">2019-01-01 00:00</span>
				</div>

				<div class="table-folding">
					<table class="line">
						<colgroup>
							<col style="width:60px;">
							<col style="width:30px;">
							<col>
							<col>
							<col>
							<col>
							<col>
							<col style="width:110px;">
							<col style="width:110px;">
						</colgroup>
						<thead>
							<tr>
								<th></th>
								<th></th>
								<th>암호화폐명</th>
								<th>
									<a href="javascript:void(0);" class="sort-toggle" data-field="marketCapital">시가총액</a>
								</th>
								<th>
									<a href="javascript:void(0);" class="sort-toggle" data-field="closePrice">가격</a>
								</th>
								<th>
									<a href="javascript:void(0);" class="sort-toggle" data-field="tradeAmount">거래량(24H)</a>
								</th>
								<th>
									<a href="javascript:void(0);" class="sort-toggle" data-field="avaulableSupply">유통 공급량</a>
								</th>
								<th>
									<a href="javascript:void(0);" class="sort-toggle" data-field="tradeDaebuRate">변경(24H)</a>
								</th>
								<th></th>
							</tr>
						</thead>
						<tbody id="coinMarketCap">
						</tbody>
					</table>
				</div>
				<div class="btn-folding">
					<button>전체보기</button>
				</div>
			</div>
			<!--코인시가총액 끝-->
		</div>
		
		<!-- <aside>
			<h1 class="ssubtitle">코인뉴스</h1>
			<div class="table-list">
				<table>
					<colgroup>
						<col style="width:120px;">
						<col>
					</colgroup>
					<tbody id="coinNews">
					</tbody>
				</table>

				페이징 : Start
				<ul class="pagination">
					<li><a class="btn-page first"></a></li>
					<li><a class="btn-page prev"></a></li>
					<li><a href="#" class="active">1</a></li>선택된 번호의 a 태그에 acitve 클래스 추가
					<li><a href="#">2</a></li>
					<li><a href="#">3</a></li>
					<li><a href="#">4</a></li>
					<li><a href="#">5</a></li>
					<li><a class="btn-page next"></a></li>
					<li><a class="btn-page last"></a></li>
				</ul>
				//페이징 : End
			</div>

		</aside> -->
	</article>

</section>

<script>
$(document).ready(function() {
	$('.btn-folding').click(function() {
		$('.table-folding').toggleClass('more');
		if($('.table-folding').attr('class') == 'table-folding more') {
			$(this).find('button').eq(0).text('접기');
		} else {
			$(this).find('button').eq(0).text('전체보기');
		}
	});

	$('.dropdown-sub-menu').on('click', 'a', function() {
		let $this = $(this);
		
		$('.menu-item').text($this.text())
						.data('date', $this.data('date'));
		
		getTopDaebiRank();
	});
	
	getTodayTopDaebi();
	getTodayTopAmt();
	getTopDaebiRank();
	getCoinMarketCap();
});

function getTodayTopDaebi() {
	ajaxOption({
		url:'/alibit/anal/getTodayTopDaebi',
		success:function(data) {
			let length = data != null ? data.length:0;
			
			if(length > 0) {
				let template = $('#TODAY_TOP_DAEBI').html(), innerHtml = '';
				
				for(let i=0; i<length; i++) {
					let temp = data[i], array = temp.itemNm.split('/');
					
					innerHtml += template.replace(/{{symbol}}/g, array[0])
											.replace(/{{coinNm}}/g, temp.itemKorNm)
											.replace(/{{toUnit}}/g, array[0])
											.replace(/{{fromUnit}}/g, array[1])
											.replace(/{{closePrice}}/g, nu.cm(temp.closePrice, 0))
											.replace(/{{daebi}}/g, nu.cm(temp.priceDevRate, 2));
				}
				
				$('#todayTopDaebi').html(innerHtml);
			}
		}
	});
}

function getTodayTopAmt() {
	ajaxOption({
		url:'/alibit/anal/getTodayTopAmt',
		success:function(data) {
			let length = data != null ? data.length:0;
			
			if(length > 0) {
				let template = $('#TODAY_TOP_AMT').html(), innerHtml = '';
				
				for(let i=0; i<length; i++) {
					let temp = data[i], array = temp.itemNm.split('/')
						, daebiRecogCd = temp.daebiRecogCd
						, calPer = nu.nvl(((temp.tradeAmtKrw - temp.tradeAmtKrwYes) / temp.tradeAmtKrwYes) * 100)
						, mark = calPer == 0 ? '':calPer > 0 ? '+':'-'
						, color = calPer == 0 ? '':calPer > 0 ? 'red':'blue';
						
					innerHtml += template.replace(/{{symbol}}/g, array[0])
											.replace(/{{coinNm}}/g, temp.itemKorNm)
											.replace(/{{toUnit}}/g, array[0])
											.replace(/{{fromUnit}}/g, array[1])
											.replace(/{{tradeAmt}}/g, nu.cm(temp.tradeAmtKrw, 0))
											.replace(/{{mark}}/g, mark)
											.replace(/{{daebi}}/g, nu.cm(Math.abs(calPer), 2))
											.replace(/{{color}}/g, color);
				}
				
				$('#todayTopAmt').html(innerHtml);
			}
		}
	});
}

function getTopDaebiRank() {
	let fromDt = $('.menu-item').data('date');
	
	ajaxOption({
		url:'/alibit/anal/getTopDaebiRank',
		data:{fromDt:fromDt},
		success:function(data) {
			let length = data != null ? data.length:0;
			
			if(length > 0) {
				let template = $('#TOP_DAEBI_RANK').html(), innerHtml = '';
				
				for(let i=0; i<length; i++) {
					let temp = data[i], array = temp.itemNm.split('/');
					
					innerHtml += template.replace(/{{symbol}}/g, array[0])
											.replace(/{{coinNm}}/g, temp.itemKorNm)
											.replace(/{{toUnit}}/g, array[0])
											.replace(/{{fromUnit}}/g, array[1])
											.replace(/{{itemCode}}/g, temp.itemNm)
											.replace(/{{daebi}}/g, nu.cm(temp.rate, 2));
				}
				
				$('#topDaebiRank').html(innerHtml);
			}
		}
	});
}

function getCoinMarketCap() {
	ajaxOption({
		url:'/alibit/anal/getCoinMarketCap',
		success:function(data) {
			let length = data != null ? data.length:0;
			
			if(length > 0) {
				drawCoinMarketCap(data);
				
				$('.sort-toggle').on('click', function() {
					let sortData = JSON.parse(JSON.stringify(data))
						, $this = $(this)
						, field = $this.data('field');
					
					$this.closest('tr').find('a').not(this).attr('class', 'sort-toggle');
					
					if($this.hasClass('sort-down')) {
						$this.attr('class', 'sort-toggle sort-up');
						
						sortData.sort(function(a, b) {
							return  a[field] < b[field] ? -1 : a[field] > b[field] ? 1 : 0;
						});
					} else if($this.hasClass('sort-up')) {
						$this.attr('class', 'sort-toggle');
					} else {
						$this.attr('class', 'sort-toggle sort-down');
						
						sortData.sort(function(a, b) {
							return  a[field] > b[field] ? -1 : a[field] < b[field] ? 1 : 0;
						});
					}
					
					drawCoinMarketCap(sortData);
				})
			}
		}
	});
}

function drawCoinMarketCap(data) {
	let length = data != null ? data.length:0
		, template = $('#COIN_MARKET_CAP').html(), innerHtml = '';
	
	for(let i=0; i<length; i++) {
		let temp = data[i]
			, capital = getKrwUnit(temp.marketCapital)
			, amt = getKrwUnit(temp.tradeAmount)
			, style = temp.itemCode === '' ? 'style="display:none;"':''
			, mark = temp.tradeDaebuRate == 0 ? '':temp.tradeDaebuRate > 0 ? '+':'-'
			, color = temp.tradeDaebuRate == 0 ? '':temp.tradeDaebuRate > 0 ? 'red':'blue'
			, updateDt = du.getCstmFrmt(temp.updateDt, 'yyyyMMddHHmmss', 'yyyy-MM-dd HH:00');
			
		if(i == 0) $('#coinmarketcap-base-time').text(updateDt);

		innerHtml += template.replace(/{{type}}/g, temp.rank < 4 ? 'red':'default')
								.replace(/{{rank}}/g, temp.rank)
								.replace(/{{symbol}}/g, temp.symbol)
								.replace(/{{coinNm}}/g, temp.coinNm)
								.replace(/{{marketCapital}}/g, capital.price)
								.replace(/{{marketCapitalUnit}}/g, capital.unit)
								.replace(/{{closePrice}}/g, nu.cm(temp.closePrice, 2))
								.replace(/{{tradeAmt}}/g, amt.price)
								.replace(/{{tradeUnit}}/g, amt.unit)
								.replace(/{{supply}}/g, nu.cm(temp.avaulableSupply, 0))
								.replace(/{{color}}/g, color)
								.replace(/{{mark}}/g, mark)
								.replace(/{{daebi}}/g, nu.cm(Math.abs(temp.tradeDaebuRate), 2) + '%')
								.replace(/{{style}}/g, style)
								.replace(/{{itemCode}}/g, temp.itemCode);
	}
	
	$('#coinMarketCap').html(innerHtml);
}

function getCoinNews() {
	ajaxOption({
		url:'/alibit/anal/getCoinNews',
		success:function(data) {
			let length = data != null ? data.length:0;
			
			if(length > 0) {
				let template = $('#COIN_NEWS').html(), innerHtml = '';
				
				for(let i=0; i<length; i++) {
					let temp = data[i];
					
					innerHtml += template.replace(/{{imgLink}}/g, '')
											.replace(/{{title}}/g, '')
											.replace(/{{registDt}}/g, '')
											.replace(/{{register}}/g, '');
				}
				
				$('#coinNews').html(innerHtml);
			}
		}
	});
}

function getKrwUnit(price) {
	var krwPrice = parseInt(parseFloat(price));
	var result = {};
	
	if(krwPrice >= 100000000) {
		result.price = nu.cm(Math.floor(krwPrice/100000000), 0);
		result.unit = '억';
	} else if(krwPrice >= 1000000) {
		result.price = nu.cm(Math.floor(krwPrice/1000000), 0);
		result.unit = '백만';
	} else if(krwPrice >= 1000) {
		result.price = nu.cm(Math.floor(krwPrice/1000), 0);
		result.unit = '천원';
	} else {
		result.price = nu.cm(krwPrice, 0);
		result.unit = '원';
	}
	
	return result;
}

function moveExchange(itemCode) {
	location.href='/alibit/exchange?coin=' + itemCode;
}
</script>