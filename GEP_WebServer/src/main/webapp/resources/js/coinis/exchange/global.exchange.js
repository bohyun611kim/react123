$(document).ready(function() {
	$('.bookmark').click(function() {
		$(this).toggleClass('mark-on');
	});
	
	$('.coininfo').on('click', function() {
		ajaxOption({
			url:'/coinis/exchange/getCoinInfo',
			data:{coinNo:item.coinNo},
			success:function(data) {
				if(data != null) {
					$('#myForm').find('.content-item').html(
						$('#COIN_INFO').html()
						.replace(/{{symbol}}/g, su.nvl(data.symbol))
						.replace(/{{coinEngNm}}/g, su.nvl(data.coinEngNm))
						.replace(/{{foundNm}}/g, su.nvl(data.foundNm))
						.replace(/{{foundLoc}}/g, su.nvl(data.foundLoc))
						.replace(/{{repNm}}/g, su.nvl(data.repNm))
						.replace(/{{homepage}}/g, su.nvl(data.homepage))
						.replace(/{{issueDay}}/g, du.getCstmFrmt(data.issueDay, 'yyyyMMdd', 'yyyy-MM-dd'))
						.replace(/{{totSupply}}/g, nu.cm(data.totSupply))
						.replace(/{{proofProtocol}}/g, su.nvl(data.proofProtocol))
						.replace(/{{whitePaper}}/g, su.nvl(data.whitePaper))
						.replace(/{{coinExplanation}}/g, su.nvl(data.coinExplanation))
					);
					
					$('#myForm').show();
				} else {
					lrt.client('Fail to load coin information', 'Info');
				}
			}
		});
	})
	
	$('#sellList, #buyList').on('click', 'tr', function() {
		let $this = $(this)
			, prc = $this.children('td:eq(0)').text().replace(/,/g, '');
		
		$('.ordPrc').val(prc);
	});
	
	$('#nonContractList').on('click', 'a', function() {
		let $tr = $(this).closest('tr');
		lrt.confirm('주문을 취소하시겠습니까?', '알림', function() {
			cancel({
				tranNo:$tr.data('tran'),
				ordNo:$tr.data('ord'),
				excNo:$tr.data('exc'),
				itemCode:item.itemCode
			});
		});
	});
	
	$('#buyPrc').on('keydown', function(e) {
		evntKeyDown(e, this, item.amtCalcDecPnt);
		calBuy();
	});
	
	$('#buyQty').on('keydown', function(e) {
		evntKeyDown(e, this, item.qtyCalcDevPnt);
		calBuy();
	});
	
	$('#sellPrc').on('keydown', function(e) {
		evntKeyDown(e, this, item.amtCalcDecPnt);
		calSell();
	});
	
	$('#sellQty').on('keydown', function(e) {
		evntKeyDown(e, this, item.qtyCalcDevPnt);
		calSell();
	});
	
	function calBuy() {
		let prc = nu.nvl($('#buyPrc').val().replace(/,/g, ''))
			, qty = nu.nvl($('#buyQty').val().replace(/,/g, ''))
			, amt = nu.multi(prc, qty);

		if(prc === 0 || qty === 0) {
			calInit('buy');
		} else if(amt > item.fromQty) {
			lrt.client('사용 가능 금액을 초과하였습니다', 'Info');
			$('#buyQty').val(nu.cm(item.fromQty / prc, item.qtyCalcDevPnt)).blur();
			calInit('buy');
		} else {
			let amt = nu.multi(prc, qty)
				, fee = nu.multi(qty, nu.multi(item.buyFeeVal, 0.01))
				, tot = nu.minus(qty, fee);
			
			$('#buyAmt').val(nu.cm(amt, item.amtCalcDecPnt));
			$('#buyFee').val(nu.cm(fee, item.qtyCalcDevPnt));
			$('#buyTot').val(nu.cm(tot, item.qtyCalcDevPnt));
		}
	}
	
	function calSell() {
		let prc = nu.nvl($('#sellPrc').val().replace(/,/g, ''))
			, qty = nu.nvl($('#sellQty').val().replace(/,/g, ''));

		if(prc === 0 || qty === 0) {
			calInit('sell');
		} else if(qty > item.toQty) {
			lrt.client('매도 가능 수량을 초과하였습니다', 'Info');
			$('#sellQty').val(nu.cm(item.toQty, item.qtyCalcDevPnt)).blur();
			calInit('sell');
		} else {
			let amt = nu.multi(prc, qty)
				, fee = nu.multi(amt, nu.multi(item.buyFeeVal, 0.01))
				, tot = nu.minus(amt, fee);
			
			$('#sellAmt').val(nu.cm(amt, item.amtCalcDecPnt));
			$('#sellFee').val(nu.cm(fee, item.amtCalcDecPnt));
			$('#sellTot').val(nu.cm(tot, item.amtCalcDecPnt));
		}
	}
	
	function calInit(type) {
		$('#' + type + 'Amt').val('');
		$('#' + type + 'Fee').val('');
		$('#' + type + 'Tot').val('');
	}
	
	$('.buy-percent').on('click', 'div', function() {
		let percent = $(this).data('per')
			, prc = nu.nvl($('#buyPrc').val().replace(/,/g, ''));
		
		if(prc !== 0) {
			$('#buyQty').val( nu.cm((item.fromQty / prc) * (percent / 100), item.qtyCalcDevPnt) );
			calBuy();
		}
	});
	
	$('.sell-percent').on('click', 'div', function() {
		let percent = $(this).data('per');
		
		$('#sellQty').val( nu.cm(item.toQty * (percent / 100), item.qtyCalcDevPnt) );
		calSell();
	});
	
	getItemInfo(item);
	getMarketInfo();
})

function getMarketInfo() {
	ajaxOption({
		url:'/coinis/exchange/getMarketInfo',
		success:function(data) {
			let mkt = data.mkt
				, balance = data.balance
				, length = mkt != undefined ? mkt.length:0
				, balLength = balance != undefined ? balance.length:0;
			
			if(length > 0) {
				let headerHtml = ''
					, headerTemplate = $('#MARKET_HEADER').html();
				
				for(let i=0; i<length; i++) {
					headerHtml += headerTemplate.replace(/{{mktNm}}/g, mkt[i].mktNm);
				}
				$('#marketList').prepend(headerHtml);
				
				let bodyHtml = ''
					, mktTemplate = $('#MARKET_BODY').html()
					, mktItemTemplate = $('#MARKET_ITME').html();
				
				for(let i=0; i<length; i++) {
					let info = mkt[i].item
						, itemLength = info.length
						, itemHtml = '';
						
					for(let j=0; j<itemLength; j++) {
						let active = ''
							, rate = Math.abs(info[j].prcDevRate)
							, mark = info[j].recogCd === 0 ? '':info[j].recogCd > 0 ?'+':'-'
							, color = info[j].recogCd === 0 ? '':info[j].recogCd > 0 ?'green':'orange';
						
						if(info[j].mktGrpId === item.mktGrpId 
								&& info[j].itemCode === item.itemCode) {
							active = 'active';
						}
						
						itemHtml += mktItemTemplate
						.replace(/{{active}}/g, active)
						.replace(/{{mktId}}/g, info[j].mktId)
						.replace(/{{mktGrpId}}/g, info[j].mktGrpId)
						.replace(/{{itemCode}}/g, info[j].itemCode)
						.replace(/{{symbol}}/g, info[j].itemNm.split('/')[0])
						.replace(/{{closePrc}}/g, nu.cm(info[j].closePrc, info[j].prcDspDecPnt))
						.replace(/{{daebiRate}}/g, mark + nu.cm(rate, 2) + '%')
						.replace(/{{tradeVol}}/g, nu.cm(info[j].tradeVol, info[j].volDspDecPnt))
						.replace(/{{color}}/g, color)
						.replace(/{{prcPnt}}/g, info[j].prcDspDecPnt)
						.replace(/{{volPnt}}/g, info[j].volDspDecPnt)
					}
					
					bodyHtml += mktTemplate
					.replace(/{{market}}/g, mkt[i].mktNm)
					.replace(/{{body}}/g, itemHtml);
				}
				
				$('#search').after(bodyHtml);
			}
			
			if(balLength > 0) {
				let balHtml = '', template = $('#BALANCE').html();
				for(let i=0; i<balLength; i++) {
					let coinPnt = balance[i].coinDecPnt
						, bcPnt = balance[i].bcCoinDecPnt
						, qty = balance[i].balanceQty
						, prc = balance[i].closePrc
						, avg = balance[i].avgPrcByBc
						, info = calBalance(qty, prc, avg);
					
					balHtml += template
					.replace(/{{coinNo}}/g, balance[i].coinNo)
					.replace(/{{coinNm}}/g, balance[i].coinSymbol)
					.replace(/{{qty}}/g, nu.cm(qty, coinPnt))
					.replace(/{{sortEvalPrc}}/g, info.evalPrc)
					.replace(/{{evalPrc}}/g, nu.cm(info.evalPrc, bcPnt))
					.replace(/{{sortAvg}}/g, avg)
					.replace(/{{buyAvgPrc}}/g, nu.cm(avg, bcPnt))
					.replace(/{{sortRate}}/g, info.rate)
					.replace(/{{rate}}/g, info.mark + nu.cm(Math.abs(info.rate, 2)) + ' %')
					.replace(/{{profit}}/g, info.mark + nu.cm(Math.abs(info.profit), bcPnt))
					.replace(/{{color}}/g, info.color)
					.replace(/{{unit}}/g, balance[i].bcCoinSymbol)
					.replace(/{{itemCode}}/g, balance[i].itemCode)
					.replace(/{{coinPnt}}/g, coinPnt)
					.replace(/{{bcPnt}}/g, bcPnt)
				}
				
				$('#holding').find('tbody').html(balHtml);
			}
			
			$('#market').tabs()
			.on('click', 'tr', function() {
				let $this = $(this);
				
				if($this.data('item') != undefined && $this.hasClass('active') === false) {
					$('#market').find('.active').removeClass('active');
					getItemInfo({
						mktId:$this.data('mkt'),
						mktGrpId:$this.data('grp'),
						itemCode:$this.data('item')
					})
				}
			});
		}
	});
}

function calBalance(qty, prc, avg) {
	let evalPrc = qty * prc
		, evalPrcByBc = qty * avg
		, diff = (prc - avg)
		, profit = (prc - avg) * qty
		, rate = ((prc - avg) / avg) * 100
		, mark = diff === 0 ? '':diff > 0 ? '+':'-'
		, color = diff === 0 ? '':diff > 0 ? 'green':'orange';
	
	return {
		evalPrc:evalPrc,
		profit:profit,
		rate:rate,
		mark:mark,
		color:color
	};
}

function getItemInfo(data) {
	ajaxOption({
		url:'/coinis/exchange/getItemInfo',
		data:data,
		success:function(data) {
			item = data.info;
			createHogaServerSentEvents(function(data) {
				data = JSON.parse(data).body;
				if(data.itemCode === item.itemCode && data.mktGrpId === item.mktGrpId) {
					reDrawHoga(data.SellHogaList, data.BuyHogaList);
				}
			}, item.mktGrpId);
			
			setUnit(item.itemNm);
			setHeader(item);
			drawHoga(data.sell, data.buy);
			drawRealContract(data.real);
			drawOrderList(data.order);
			drawContractList(data.contract);
			drawNonContractList(data.nonContract);
			drawBalance(data.balance);
			drawChart(item.itemCode, item.mktGrpId);
			
			$('#market').find('tr[data-item=' + item.itemCode + ']').addClass('active');
		}
	});
}

function setUnit(data) {
	let unit = data.split('/');
	item.toUnit = unit[0];
	item.fromUnit = unit[1];
}

function setUrl(itemCode) {
	let url = window.location.pathname;
	
	if(url.indexOf(itemCode) === -1) {
		let state = {Page:'yahobit', Url:window.location.pathname + '/' + itemCode};
		window.history.pushState(state, state.Page, state.Url);
	}
}

function setHeader(data) {
	$('.coin-symbol').html('<img src="/resources/img/coin-symbol/' + data.toUnit + '.png">');
	$('#coinsName').text( data.toUnit + " / " + data.fromUnit );
	
	$('.now-price')
	.children('p:eq(0)').text(nu.cm(data.closePrc, data.prcDspDecPnt))
	.next().text(nu.cm(data.prcDevRate) + '%');
	
	$('#hogaPrc').text(nu.cm(data.closePrc, data.prcDspDecPnt));
	$('#hogaDaebi').text(nu.cm(data.prcDevRate));
	
	$('#perPrice').text(nu.cm(data.prcDevAmt, data.prcDspDecPnt));
	
	let highColor = data.highPrc === data.closePrc ? '':data.highPrc > data.closePrc ? 'green':'orange';
	$('#highPrice')
	.text(nu.cm(data.highPrc, data.prcDspDecPnt))
	.attr('class', highColor);
	
	let lowColor = data.lowPrc === data.closePrc ? '':data.lowPrc > data.closePrc ? 'green':'orange';
	$('#lowPrice')
	.text(nu.cm(data.lowPrc, data.prcDspDecPnt))
	.attr('class', lowColor);
	
	$('#deal24h').text(nu.cm(data.tradeVol, data.volDspDecPnt));
	$('#price24h').text(nu.cm(data.tradeAmt, data.amtDecPnt));
	
	$('#possibleBuy').text(nu.cm(data.fromQty, data.prcDspDecPnt));
	$('#possibleSell').text(nu.cm(data.toQty, data.qtyDspDecPnt));
	
	$('.coinNm').text(data.toUnit);
	$('.basicNm').text(data.fromUnit);
	
	$('#buyPrc').val(nu.cm(data.closePrc, data.prcDspDecPnt));
	$('#sellPrc').val(nu.cm(data.closePrc, data.prcDspDecPnt));
}

function drawHoga(sell, buy) {
	let max = getMaxQty(sell, buy)
	, hogaTemplate = $('#HOGA').html()
	, innerHtml = '', closePrc = parseFloat(item.closePrc);
	
	for(let i=0; i<sell.length; i++) {
		let hogaPrc = parseFloat(sell[i].ordPrc), hogaQty = parseFloat(sell[i].hogaQty);
		
		innerHtml = hogaTemplate
		.replace(/{{prc}}/g, nu.cm(hogaPrc, item.prcDspDecPnt, true))
		.replace(/{{qty}}/g, nu.cm(hogaQty, item.qtyDspDecPnt, true))
		.replace(/{{color}}/g, 'bg-orange')
		.replace(/{{per}}/g, getCalPer(hogaQty, max)) + innerHtml;
	}
	
	$('#sellList').html(innerHtml);
	
	innerHtml = '';
	
	for(let i=0; i<buy.length; i++) {
		let hogaPrc = parseFloat(buy[i].ordPrc), hogaQty = parseFloat(buy[i].hogaQty);
		
		innerHtml += hogaTemplate
		.replace(/{{prc}}/g, nu.cm(hogaPrc, item.prcDspDecPnt, true))
		.replace(/{{qty}}/g, nu.cm(hogaQty, item.qtyDspDecPnt, true))
		.replace(/{{color}}/g, 'bg-green')
		.replace(/{{per}}/g, getCalPer(hogaQty, max));
	}
	
	$('#buyList').html(innerHtml);
}

function reDrawHoga(sell, buy) {
	let max = getReMaxQty(sell, buy)
	, hogaTemplate = $('#HOGA').html()
	, innerHtml = '', closePrc = parseFloat(item.closePrc);
	
	for(let i=0; i<sell.length; i++) {
		let hogaPrc = parseFloat(sell[i].SellHoga), hogaQty = parseFloat(sell[i].SellHogaQty);
		
		innerHtml = hogaTemplate
		.replace(/{{prc}}/g, nu.cm(hogaPrc, item.prcDspDecPnt, true))
		.replace(/{{qty}}/g, nu.cm(hogaQty, item.qtyDspDecPnt, true))
		.replace(/{{color}}/g, 'bg-orange')
		.replace(/{{per}}/g, getCalPer(hogaQty, max)) + innerHtml;
	}
	
	$('#sellList').html(innerHtml);
	
	innerHtml = '';
	
	for(let i=0; i<buy.length; i++) {
		let hogaPrc = parseFloat(buy[i].BuyHoga), hogaQty = parseFloat(buy[i].BuyHogaQty);
		
		innerHtml += hogaTemplate
		.replace(/{{prc}}/g, nu.cm(hogaPrc, item.prcDspDecPnt, true))
		.replace(/{{qty}}/g, nu.cm(hogaQty, item.qtyDspDecPnt, true))
		.replace(/{{color}}/g, 'bg-green')
		.replace(/{{per}}/g, getCalPer(hogaQty, max));
	}
	
	$('#buyList').html(innerHtml);
}

function getMaxQty(sell, buy) {
	let length = sell.length > buy.length ? sell.length:buy.length, max = 0;
	for(let i=0; i<length; i++) {
		if(sell[i] != undefined) {
			max = max > sell[i].hogaQty ? max:sell[i].hogaQty;
		}
		
		if(buy[i] != undefined) {
			max = max > buy[i].hogaQty ? max:buy[i].hogaQty;
		}
	}
	
	return max;
}

function getReMaxQty(sell, buy) {
	let length = sell.length > buy.length ? sell.length:buy.length, max = 0;
	for(let i=0; i<length; i++) {
		if(sell[i] != undefined) {
			max = max > parseFloat(sell[i].SellHogaQty) ? max:parseFloat(sell[i].SellHogaQty);
		}
		
		if(buy[i] != undefined) {
			max = max > parseFloat(buy[i].BuyHogaQty) ? max:parseFloat(buy[i].BuyHogaQty);
		}
	}
	
	return max;
}

function getCalPer(data, standard) {
	return nu.cm((data / standard) * 100, 2, true);
}

function drawRealContract(data) {
	let length = data.length, innerHtml = '';
	
	if(length > 0) {
		let template = $('#REAL_CONTRACT').html();
		
		for(let i=0; i<length; i++) {
			innerHtml += template
			.replace(/{{time}}/g, du.getCstmFrmt(data[i].contractDt, 'yyyyMMddHHmmss', 'HH:mm:ss'))
			.replace(/{{color}}/g, data[i].contractRecogCd == 1 ? 'orange':'green')
			.replace(/{{prc}}/g, nu.cm(data[i].contractPrc, item.prcDspDecPnt, true))
			.replace(/{{qty}}/g, nu.cm(data[i].contractQty, item.qtyDspDecPnt, true));
		}
	}
	
	$('#realContractList').html(innerHtml);
}

function drawOrderList(data) {
	let length = data != undefined ? data.length:0, innerHtml = '';
	
	if(length > 0) {
		let template = $('#ORDER').html();
		
		for(let i=0; i<length; i++) {
			let amt = (data[i].ordQty * data[i].ordPrc).toFixed(item.prcDspDecPnt);
			
			innerHtml += template
			.replace(/{{no}}/g, data[i].ordNo)
			.replace(/{{dt}}/g, du.getCstmFrmt(data[i].ordDt, 'yyyyMMddHHmmss', 'yyyy-MM-dd HH:mm:ss'))
			.replace(/{{color}}/g, data[i].sellBuyCd == 1 ? 'green':'orange')
			.replace(/{{sellBuy}}/g, sellBuy[data[i].sellBuyCd])
			.replace(/{{qty}}/g, nu.cm(data[i].ordQty, item.qtyDspDecPnt, true))
			.replace(/{{prc}}/g, nu.cm(data[i].ordPrc, item.prcDspDecPnt, true))
			.replace(/{{amt}}/g, nu.cm(amt, item.prcDspDecPnt, true))
			.replace(/{{nonQty}}/g, nu.cm(data[i].nonContractQty, item.qtyDspDecPnt, true))
			.replace(/{{status}}/g, stats[data[i].ordStatus])
			.replace(/{{origin}}/g, data[i].ordOrgNo == null ? '':data[i].ordOrgNo);
		}
	}
	
	$('#orderList').html(innerHtml);
}

function drawContractList(data) {
	let length = data != undefined ? data.length:0, innerHtml = '';
	if(length > 0) {
		let template = $('#CONTRACT').html();
		
		for(let i=0; i<length; i++) {
			let amt = (data[i].contractPrc * data[i].contractQty).toFixed(item.prcDspDecPnt);
			
			innerHtml += template
			.replace(/{{dt}}/g, du.getCstmFrmt(data[i].ordDt, 'yyyyMMddHHmmss', 'yyyy-MM-dd HH:mm:ss'))
			.replace(/{{ordQty}}/g, nu.cm(data[i].ordQty, item.qtyDspDecPnt, true))
			.replace(/{{ordPrc}}/g, nu.cm(data[i].ordPrc, item.prcDspDecPnt, true))
			.replace(/{{contractDt}}/g, du.getCstmFrmt(data[i].contractDt, 'yyyyMMddHHmmss', 'yyyy-MM-dd HH:mm:ss'))
			.replace(/{{contractPrc}}/g, nu.cm(data[i].contractPrc, item.prcDspDecPnt, true))
			.replace(/{{contractQty}}/g, nu.cm(data[i].contractQty, item.qtyDspDecPnt, true))
			.replace(/{{contractAmt}}/g, nu.cm(amt, item.prcDspDecPnt, true))
			.replace(/{{nonContractQty}}/g, nu.cm(data[i].nonContractQty, item.qtyDspDecPnt, true))
			.replace(/{{color}}/g, data[i].sellBuyCd == 1 ? 'green':'orange')
			.replace(/{{sellBuy}}/g, sellBuy[data[i].sellBuyCd]);
		}
	}
	
	$('#contractList').html(innerHtml);
}

function drawNonContractList(data) {
	let length = data != undefined ? data.length:0, innerHtml = '';
	if(length > 0) {
		let template = $('#NON_CONTRACT').html();
		
		item.nonQty = 0;
		
		for(let i=0; i<length; i++) {
			let contractPrc = data[i].contractQty != 0 ? data[i].ordPrc:0
				, amt = (contractPrc * data[i].contractQty).toFixed(item.prcDspDecPnt);
				
			innerHtml += template
			.replace(/{{tranNo}}/g, data[i].tranNo)
			.replace(/{{ordNo}}/g, data[i].ordNo)
			.replace(/{{excNo}}/g, data[i].excNo)
			.replace(/{{no}}/g, data[i].ordNo)
			.replace(/{{itemCode}}/g, data[i].itemCode)
			.replace(/{{color}}/g, data[i].sellBuyCd == 1 ? 'green':'orange')
			.replace(/{{sellBuy}}/g, sellBuy[data[i].sellBuyCd])
			.replace(/{{ordPrc}}/g, nu.cm(data[i].ordPrc, item.prcDspDecPnt, true))
			.replace(/{{ordQty}}/g, nu.cm(data[i].ordQty, item.qtyDspDecPnt, true))
			.replace(/{{contractPrc}}/g, nu.cm(contractPrc, item.prcDspDecPnt, true))
			.replace(/{{contractQty}}/g, nu.cm(data[i].contractQty, item.qtyDspDecPnt, true))
			.replace(/{{contractAmt}}/g, nu.cm(amt, item.prcDspDecPnt, true))
			.replace(/{{nonContractQty}}/g, nu.cm(data[i].nonQty, item.qtyDspDecPnt, true))
			.replace(/{{ordDt}}/g, du.getCstmFrmt(data[i].ordDt, 'yyyyMMddHHmmss', 'yyyy-MM-dd HH:mm:ss'))
			
			if(parseInt(data[i].sellBuyCd) === 2) {
				item.nonQty += data[i].nonQty;
			}
		}
	}
	
	$('#nonContractList').html(innerHtml);
}

function drawBalance(data) {
	if(data != undefined) {
		let $td = $('#balanceData').find('td:first-child');
		
		$td
		.data('pnt', data.bcCoinPnt).data('avg', data.convertQty)
		.text(nu.cm(data.balanceQty, item.qtyDspDecPnt))
		.next().text(nu.cm(data.possibleQty, item.qtyDspDecPnt))
		.next().text(nu.cm(data.nonQty, item.qtyDspDecPnt))
		.next().text(nu.cm(data.balanceQty * data.convertQty, data.bcCoinPnt))
		.next().find('a:eq(0)').attr('href', '/coinis/depositWithdrawals')
		.next().attr('href', '/coinis/depositWithdrawals');
	}
}

function buy() {
	var p = {
			mktGrpId:item.mktGrpId,
			mktId:item.mktId,
			itemCode:item.itemCode,
			ordPrice:$('#buyPrc').val().replace(/,/g, ''),
			ordQty:$('#buyQty').val().replace(/,/g, '')
	}
	
	if(p.ordPrice == '' || p.ordPrice == 0) {
		lrt.client('가격을 입력하세요.', 'Info');
	} else if(p.ordQty == '' || p.ordQty == 0) {
		lrt.client('수량을 입력하세요.', 'Info');
	} else {
		ajax('/coinis/exchange/doBuy', p, function(data) {
			if(data.rtnCd != 0) {
				lrt.client(wordbook[data.rtnCd], 'Info');
			}
		});
	}
}

function sell() {
	var p = {
			mktGrpId:item.mktGrpId,
			mktId:item.mktId,
			itemCode:item.itemCode,
			ordPrice:$('#sellPrc').val().replace(/,/g, ''),
			ordQty:$('#sellQty').val().replace(/,/g, '')
	}
	
	if(p.ordPrice == '' || p.ordPrice == 0) {
		lrt.client('가격을 입력하세요.', 'Info');
	} else if(p.ordQty == '' || p.ordQty == 0) {
		lrt.client('수량을 입력하세요.', 'Info');
	} else {
		ajax('/coinis/exchange/doSell', p, function(data) {
			if(data.rtnCd != 0) {
				lrt.client(wordbook[data.rtnCd], 'Info');
			}
		});
	}
}

function init(type) {
	$('#' + type + 'Prc').val('');
	$('#' + type + 'Qty').val('');
	$('#' + type + 'Amt').val('');
	$('#' + type + 'Fee').val('');
	$('#' + type + 'Tot').val('');
}

function cancel(data) {
	ajaxOption({
		url:'/coinis/exchange/doCancel',
		data:data,
		success:function(data) {
			if(data.rtnCd != 0) {
				lrt.client(wordbook[data.rtnCd], 'Info');
			}
		}
	});
}

function reSetHeader(data) {
	let closePrc = parseFloat(data.closePrice)
		, highPrc = parseFloat(data.highPrice)
		, lowPrc = parseFloat(data.lowPrice);
	
	if(closePrc !== item.closePrc) {
		item.closePrc = closePrc;
		item.prcDevAmt = parseFloat(item.priceDevAmt);
		item.prcDevRate = parseFloat(item.priceDevRate);
		
		$('.now-price')
		.children('p:eq(0)').text(nu.cm(item.closePrc, item.prcDspDecPnt))
		.next().text(nu.cm(item.prcDevRate, 2) + '%');
		
		$('#hogaPrc').text(nu.cm(item.closePrc, item.prcDspDecPnt));
		$('#hogaDaebi').text(nu.cm(item.prcDevRate, 2));
		$('#perPrice').text(nu.cm(item.prcDevAmt, item.prcDspDecPnt));
	}
	
	let highColor = highPrc === item.closePrc ? '':highPrc > item.closePrc ? 'green':'orange';
	$('#highPrice')
	.text(nu.cm(highPrc, item.prcDspDecPnt))
	.attr('class', highColor);
	
	let lowColor = lowPrc === item.closePrc ? '':lowPrc > item.closePrc ? 'green':'orange';
	$('#lowPrice')
	.text(nu.cm(lowPrc, item.prcDspDecPnt))
	.attr('class', lowColor);
	
	$('#deal24h').text(nu.cm(data.tradeVol, item.volDspDecPnt));
	$('#price24h').text(nu.cm(data.tradeAmt, item.amtDecPnt));
}

function addRealContract(data) {
	let $target = $('#realContractList');
	$target.prepend(
			$('#REAL_CONTRACT').html()
			.replace(/{{time}}/g, du.getCstmFrmt(data.contractDt, 'yyyyMMddHHmmss', 'HH:mm:ss'))
			.replace(/{{color}}/g, data.contractRecogCd == 1 ? 'orange':'green')
			.replace(/{{prc}}/g, nu.cm(data.closePrice, item.prcDspDecPnt, true))
			.replace(/{{qty}}/g, nu.cm(data.contractQty, item.qtyDspDecPnt, true))
	);
	
	if($target.children('tr').length > 100) $target.children('tr:last-child').remove();
}

function addOrder(body) {
	let data = body.body
		, amt = (data.ordQty * data.ordPrice).toFixed(item.prcDspDecPnt)
		, nonQty = data.nonContractQty === undefined ? '':nu.cm(data.nonContractQty, item.qtyDspDecPnt, true);
	
	$('#orderList').prepend(
		$('#ORDER').html()
		.replace(/{{no}}/g, data.ordsvrOrdNo)
		.replace(/{{dt}}/g, du.getCstmFrmt(body.sndDt, 'yyyyMMddHHmmss', 'yyyy-MM-dd HH:mm:ss'))
		.replace(/{{color}}/g, data.sellBuyRecogCd == 1 ? 'green':'orange')
		.replace(/{{sellBuy}}/g, sellBuy[parseFloat(data.sellBuyRecogCd)])
		.replace(/{{qty}}/g, nu.cm(data.ordQty, item.qtyDspDecPnt, true))
		.replace(/{{prc}}/g, nu.cm(data.ordPrice, item.prcDspDecPnt, true))
		.replace(/{{amt}}/g, nu.cm(amt, item.prcDspDecPnt, true))
		.replace(/{{nonQty}}/g, nonQty)
		.replace(/{{status}}/g, stats[data.ordStatus])
		.replace(/{{origin}}/g, data.ordsvrOrgOrdNo == null ? '':data.ordsvrOrgOrdNo)
	);
}

function addContract(data) {
	let amt = (parseFloat(data.contractPrice) * parseFloat(data.contractQty)).toFixed(item.qtyDspDecPnt)
	
	$('#contractList').prepend(
		$('#CONTRACT').html()
		.replace(/{{dt}}/g, du.getCstmFrmt(data.ordDt, 'yyyyMMddHHmmss', 'yyyy-MM-dd HH:mm:ss'))
		.replace(/{{ordQty}}/g, nu.cm(data.ordQty, item.qtyDspDecPnt, true))
		.replace(/{{ordPrc}}/g, nu.cm(data.ordPrice, item.prcDspDecPnt, true))
		.replace(/{{contractDt}}/g, du.getCstmFrmt(data.contractDt, 'yyyyMMddHHmmss', 'yyyy-MM-dd HH:mm:ss'))
		.replace(/{{contractPrc}}/g, nu.cm(data.contractPrice, item.prcDspDecPnt, true))
		.replace(/{{contractQty}}/g, nu.cm(data.contractQty, item.qtyDspDecPnt, true))
		.replace(/{{contractAmt}}/g, nu.cm(amt, item.prcDspDecPnt, true))
		.replace(/{{nonContractQty}}/g, nu.cm(data.nonContractQty, item.qtyDspDecPnt, true))
		.replace(/{{color}}/g, data.sellBuyRecogCd == 1 ? 'green':'orange')
		.replace(/{{sellBuy}}/g, sellBuy[parseFloat(data.sellBuyRecogCd)])
	);
}

function addNonContract(data) {
	let value = data.body;
	
	$('#nonContractList').prepend(
		$('#NON_CONTRACT').html()
		.replace(/{{tranNo}}/g, data.ifTransactionId)
		.replace(/{{ordNo}}/g, value.ordsvrOrdNo)
		.replace(/{{excNo}}/g, value.exchgsvrOrdNo)
		.replace(/{{no}}/g, value.ordsvrOrdNo)
		.replace(/{{itemCode}}/g, value.itemCode)
		.replace(/{{color}}/g, value.sellBuyRecogCd == 1 ? 'green':'orange')
		.replace(/{{sellBuy}}/g, sellBuy[parseFloat(value.sellBuyRecogCd)])
		.replace(/{{ordPrc}}/g, nu.cm(value.ordPrice, item.prcDspDecPnt, true))
		.replace(/{{ordQty}}/g, nu.cm(value.ordQty, item.qtyDspDecPnt, true))
		.replace(/{{contractPrc}}/g, nu.cm(0, item.prcDspDecPnt, true))
		.replace(/{{contractQty}}/g, nu.cm(0, item.qtyDspDecPnt, true))
		.replace(/{{contractAmt}}/g, nu.cm(0, item.prcDspDecPnt, true))
		.replace(/{{nonContractQty}}/g, nu.cm(value.ordQty, item.qtyDspDecPnt, true))
		.replace(/{{ordDt}}/g, du.getCstmFrmt(value.ordDt, 'yyyyMMddHHmmss', 'yyyy-MM-dd HH:mm:ss'))
	);
}

function updateNonContract(data) {
	let selector = 'tr[data-exc=' + data.exchgsvrOrdNo + ']'
		, $this = $('#nonContractList').children(selector).children('td')
		, qty = (parseFloat(data.ordQty) - parseFloat(data.nonContractQty)).toFixed(item.qtyDspDecPnt);
	
	$this
	.eq(5).text(nu.cm(data.contractPrice, item.prcDspDecPnt, true))
	.next().text(nu.cm(qty, item.qtyDspDecPnt, true))
	.next().text(nu.cm(data.nonContractQty, item.qtyDspDecPnt, true));
}

function removeNonCotnract(data) {
	let selector = 'tr[data-exc=' + data.exchgsvrOrdNo + ']';

	$('#nonContractList').children(selector).remove();
}

function cancelNonCotnract(data) {
	let selector = 'tr[data-tran=' + data.orgIfTransactionId + ']'
					+ '[data-ord=' + data.ordsvrOrgOrdNo + ']'
					+ '[data-exc=' + data.exchgsvrOrgOrdNo + ']';

	if(parseInt(data.sellBuyRecogCd) === 2) {
		item.nonQty = nu.minus(item.nonQty
				, parseFloat($('#nonContractList').children(selector).children('td:eq(7)').text()));
	}
	
	$('#nonContractList').children(selector).remove();
}

function reDrawBalance(data) {
	if(data.coinNo === item.coinNo) {
		item.toQty = data.availQty;
		$('#possibleSell').text(nu.cm(item.toQty, item.qtyDspDecPnt));
		
		reDrawBalanceData(data);
	}
	
	if(data.coinNo === item.basicCoinNo) {
		item.fromQty = data.availQty;
		$('#possibleBuy').text(nu.cm(item.fromQty, item.prcDspDecPnt));
	}
}

function reDrawBalanceData(data) {
	let $tds = $('#balanceData').children('td')
		, $td = $tds.eq(0)
		, pnt = $td.data('pnt'), avg = $td.data('avg');

	if(data === undefined) {
		avg = data === undefined ? avg:data.avgPriceByBtc;
		$tds.eq(2).text(nu.cm(item.nonQty, item.qtyDspDecPnt))
		.next().text(nu.cm(parseFloat($tds.eq(1).text().replace(/,/g, '')) * avg, pnt));
	} else {
		$td.text(nu.cm(data.totQty, item.qtyDspDecPnt))
		.next().text(nu.cm(data.availQty, item.qtyDspDecPnt))
		.next().text(nu.cm(item.nonQty, item.qtyDspDecPnt))
		.next().text(nu.cm(data.availQty * data.avgPriceByBtc, pnt));
	}
}

function evntKeyDown(e, target, point) {
	let regex = /^4[8-9]$|^5[0-7]{1}$|^9[6-9]{1}$|^10[0-5]{1}$|^110$|^190$/gm
		, key = JSON.parse(JSON.stringify(e.keyCode))
		, tbody = $(target).closest('tbody')
		, type = tbody.data('type');
	
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