var _favorite = '_favorite';
var amtFlag = true;



var __G_lang = localStorage.getItem("common/LANGUAGE");

var _G_Exchange_Strings_ko = {
	'_L_0001': '암호화폐 거래 위험고지에 동의 하셔야 합니다',
	'_L_0002': '알림',
	'_L_0003': '가격을 입력하셔야 합니다.',
	'_L_0004': '매도 주문을 일괄 취소하시겠습니까?',
	'_L_0005': '일괄매도취소',
	'_L_0006': '매수 주문을 일괄 취소하시겠습니까?',
	'_L_0007': '일괄매수취소',
	'_L_0008': '주문을 취소하시겠습니까?',
	'_L_0009': '주문 취소',
	'_L_0010': '최소 주문금액',
	'_L_0011': '제한없음',
	'_L_0012': '알수없음',
	'_L_0013': '매수',
	'_L_0014': '매도',
	'_L_0015': '거래대금',
	'_L_0016': '예치수량',
	'_L_0017': '주문수량',
	'_L_0018': '주문가격',
	'_L_0019': '지정가 매도 주문 하시겠습니까?',
	'_L_0020': '매도 주문',
	'_L_0021': '지정가 매수 주문 하시겠습니까?',
	'_L_0022': '매수 주문',
	'_L_0023': '매수 호가가 없습니다',
	'_L_0024': '시장가 매도 주문 하시겠습니까?',
	'_L_0025': '시장가 매수 주문 하시겠습니까?',
	'_L_0026': '거래수량',
	'_L_0027': '자동채굴 거래 하시겠습니까?',
	'_L_0028': '자동채굴',
	'_L_0029': '수량을 입력하세요',
	'_L_0030': '가격을 입력하세요',
	'_L_0031': '최소 주문 금액은',
	'_L_0032': '입니다',
	'_L_0033': '호가 단위는',
	'_L_0034': '백만',
	'_L_0035': '천원',
	'_L_0036': '원',
	'_L_0037': '지정가',
	'_L_0038': '시장가',
	'_L_0039': '매도 호가가 없습니다',
	'_L_0040': '주문총액',
	'_L_0041': '주문총액을 입력하세요',
	'_L_0042': '최소',
	'_L_0043': '최소 주문 수량은'
};

var _G_Exchange_Strings_en = {

};



function getExchangeLocaleString(key)
{
	var strings = {};
	if(__G_lang == "en") {
		strings = _G_Exchange_Strings_en;
	} else {
		strings = _G_Exchange_Strings_ko;
	}

	if(strings.hasOwnProperty(key)) {
		return strings[key];
	} else {
		return key;
	}
}

function getHogaID(price) {
	return "[id='op_"+Number(price).toFixed(2)+"']"; 
}


function getCoinName(data)
{
	if(__G_lang == "en"	) {
		return  data.itemEngNm;
	} else {
		return data.itemKorNm; // itemDomesticNm;
	}
}

function changeDocumentTitle(prc, itemCode, f)
{
	$(document).attr('title', nu.cm(prc, selected.prcDspDecPnt, true) + ' | ' + selected.itemCode + ' | Bita500');
}


$(document).ready(function() {
	$('#notiAgree').on('click', function() {
		let $check = $('#noticeTrading').find('input[type=checkbox]');

		if($check.eq(0).is(':checked') === false) {
			lrt.client(getExchangeLocaleString('_L_0001'), getExchangeLocaleString('_L_0002'));
		} else {
			if($check.eq(1).is(':checked') === true) {
				cu.setCookie('tradeNotice', 'agree', 1);
			}

			$('#noticeTrading').hide();
		}
	});

	$('#realContractDiv').mCustomScrollbar({
		callbacks:{
			onTotalScroll:function() {
				getRealContract();
			}
		}
	});
	$('#dailyDiv').mCustomScrollbar({
		callbacks:{
			onTotalScroll:function() {
				getDaily();
			}
		}
	});
	$('#contractDiv').mCustomScrollbar({
		callbacks:{
			onTotalScroll:function() {
				getContract();
			}
		}
	});

	$('.lightdark').on('click', 'button', function() {
		let style = $(this).attr('class')
			, $target = $('#styleColor');

		if(style === 'light') {
			$target.attr('href', $target.attr('href').replace('y_dark', 'y_light'));
			candleChart.changeTheme(style);
			cu.setCookie('style', 'y_light', 365)
		} else if(style === 'dark') {
			$target.attr('href', $target.attr('href').replace('y_light', 'y_dark'));
			candleChart.changeTheme(style);
			cu.setCookie('style', 'y_dark', 365)
		}
	})

	$('.bar-down, .bar-up').on('click', function() {
		let $this = $(this);
		if($this.css('cursor') == 'pointer' && activeGrpId != 'tabs2-3') {
			let $target = $('#' + activeGrpId + ' .ordQty');
			$target.val($this.find('p').text());
			calTotal($target.eq(0), false);
			calTotal($target.eq(1), false);
		}
	});

	$('.bgblue, .bgred').on('click', function() {
		let $this = $(this); $length = $this.has('span').length
		if($this.css('cursor') == 'pointer' && $length == 0 && activeGrpId == 'tabs2-1') {
			let $target = $('#' + activeGrpId + ' .ordPrc');
			$target.val($this.text());
			calTotal($target.eq(0), false);
			calTotal($target.eq(1), false);
		}
	});

	$('.hprice').on('click', function() {
		let $this = $(this);
		if($this.css('cursor') == 'pointer' && $this.text().length > 0) {
			//console.log('hoga cancel order price:' + $this.data('hprice') + ', text: ' + $this.text());

			lrt.confirm(getExchangeLocaleString('_L_0008'), getExchangeLocaleString('_L_0009'), function() {
				ajaxOption({
					url:'/site/exchange/doCancelPrice',
					data:{
						mktGrpId:selected.mktGrpId,
						mktId:selected.mktId,
						ordPrc:$this.data('hprice'),
						itemCode:selected.itemCode
					},
					success:function(data) {
						if(data.rtnCd != 0) {lrt.client(wordbook[data.rtnCd], '');}
					}
				});
			});
		}
	});


	$('.calPercent').on('click', function(e) {
		let index = $(e.target).index(), per = 0.0
			, $this = $(this)
			, $tbody = $this.closest('tbody');

		switch(index) {
		case 0:
			per = 0.25;
			break;
		case 1:
			per = 0.50;
			break;
		case 2:
			per = 0.75;
			break;
		case 3:
			per = 1;
			break;
		default:
			break;
		}

		if($this.data('type') == 'pnd') {
			let prc = $tbody.find('input:eq(0)').val().replace(/,/g, '')
				, qty = $tbody.find('input:eq(1)').val().replace(/,/g, '')
				, amt = selected.fromQty;

			if($tbody.data('type') === 'buy') {
				if(nu.isN(prc) && prc !== 0) {
					qty = (amt / prc) * per;
					if(qty*prc >= amt-amt*selected.buyFeeVal*0.01) {
						qty = amt / (1.0 + selected.buyFeeVal*0.01) / prc;
					}
					$tbody.find('input:eq(1)').val(nu.cm(qty, selected.qtyCalcDevPnt, true));
				} else {
					lrt.client(getExchangeLocaleString('_L_0003'));
				}
			} else {
				qty = selected.toQty * per;
				$tbody.find('input:eq(1)').val(nu.cm(qty, selected.qtyCalcDevPnt, true));
			}

			calTotal($this, true);
		} else if($this.data('type') == 'mkt') {
			let prc = selected.closePrc
				, qty = $tbody.find('input:eq(0)').val().replace(/,/g, '');
			if($tbody.data('type') === 'buy') {
				qty = (selected.fromQty / prc) * per;
				$tbody.find('input:eq(0)').val(nu.cm(qty, selected.qtyCalcDevPnt, true));
			} else {
				qty = selected.toQty * per;
				$tbody.find('input:eq(0)').val(nu.cm(qty, selected.qtyCalcDevPnt, true));
			}

			calTotal($this, true);
		} else {
			let originTo = $('#autoTo').data('origin')
				, originFrom = $('#autoFrom').data('origin');

			$('#autoQty').val(nu.cm(originTo * per, selected.qtyCalcDevPnt, true));
			$('#autoAmt').text(nu.cm(originFrom * per, selected.amtCalcDecPnt, true));
			$('#autoOrdAmt').text(nu.cm(originFrom * per, selected.amtCalcDecPnt, true));
		}
	});

	$('#mktBuyPeccent').on('click', function(e) {
		let index = $(e.target).index(), per = 0.0
			, $this = $(this)
			, $tbody = $this.closest('tbody')
			, amt = selected.fromQty
			, qty = 0, totAmt = 0;

		switch(index) {
		case 0:
			per = 0.25;
			break;
		case 1:
			per = 0.50;
			break;
		case 2:
			per = 0.75;
			break;
		case 3:
			per = 1;
			break;
		default:
			break;
		}

		amt = nu.multi(amt, per);

		/*
		let length = mktSellHoga.length;
		for(let i=0; i<length;i++) {
			let hogaAmt = nu.multi(mktSellHoga[i].prc, mktSellHoga[i].qty);

			if(amt > hogaAmt) {
				amt = nu.minus(amt, hogaAmt);
				qty = nu.plus(qty, mktSellHoga[i].qty);
				totAmt = nu.plus(totAmt, hogaAmt);
			} else {
				qty = nu.plus(qty, nu.div(amt, mktSellHoga[i].prc));
				totAmt = nu.plus(totAmt, nu.multi(nu.div(amt, mktSellHoga[i].prc), mktSellHoga[i].prc));
				break;
			}
		}
		$('#mktBuyQty').val(nu.cm(qty, selected.qtyCalcDevPnt));
		$('#mktBuyTotOrder').text(nu.cm(totAmt, selected.amtCalcDecPnt));
		*/
		if(amt+amt*selected.buyFeeVal*0.01 > selected.fromQty) {
			amt = selected.fromQty-selected.fromQty*selected.buyFeeVal*0.01;
		}
		$('#mktBuyQty').val(nu.cm(amt, selected.amtCalcDecPnt, true));
		$('#mktBuyTotOrder').text(nu.cm(totAmt, selected.amtCalcDecPnt, true));
	});

	$('#mktSellPeccent').on('click', function(e) {
		let index = $(e.target).index(), per = 0.0
			, $this = $(this)
			, $tbody = $this.closest('tbody')
			, maxQty = selected.toQty
			, qty = 0, totAmt = 0;

		switch(index) {
		case 0:
			per = 0.25;
			break;
		case 1:
			per = 0.50;
			break;
		case 2:
			per = 0.75;
			break;
		case 3:
			per = 1;
			break;
		default:
			break;
		}

		maxQty = nu.multi(maxQty, per);

		let length = mktBuyHoga.length;
		for(let i=0; i<length;i++) {
			let hogaAmt = nu.multi(mktBuyHoga[i].prc, mktBuyHoga[i].qty);

			if(maxQty > mktBuyHoga[i].qty) {
				maxQty = nu.minus(maxQty, mktBuyHoga[i].qty);
				qty = nu.plus(qty, mktBuyHoga[i].qty);
				totAmt = nu.plus(totAmt, hogaAmt);
			} else {
				qty = nu.plus(qty, maxQty);
				totAmt = nu.plus(totAmt, nu.multi(maxQty, mktBuyHoga[i].prc));
				break;
			}
		}

		$('#mktSellQty').val(nu.cm(qty, selected.qtyCalcDevPnt, true));
		$('#mktSellTotOrder').text(nu.cm(totAmt, selected.amtCalcDecPnt, true));
	});

	$('.ordQty').on('keydown', function(e) {
		evntKeyDown(e, this, selected.qtyCalcDevPnt);
		calTotal(this, true);
	});

	$('#mktBuyQty').on('keydown', function(e) {
		let originQty = parseFloat(this.value.replace(/,/g, ''));

		evntKeyDown(e, this, selected.qtyCalcDevPnt);
		let strQty = $(this).val().replace(/,/g, '')
			, qty = parseFloat(strQty)
			, calQty = 0, accQty = 0
			, maxQty = selected.fromQty/*parseFloat($('#sellTotal').text().replace(/,/g, ''))*/
			, maxAmt = selected.fromQty
			, totAmt = 0
			, length = mktSellHoga.length
			, extraComma = '';

		if(originQty === qty) {
			return;
		}

		qty = qty > maxQty ? maxQty:qty;
		calQty = qty;

		if(strQty.substr(-1) === '.') {
			extraComma = '.';
		}

		if(length > 0) {
			/*
			// 주문 총액 기준으로 계산
			for(let i=0; i<length;i++) {
				let tempAmt = nu.multi(mktSellHoga[i].prc, mktSellHoga[i].qty)
					, tempQty = nu.minus(calQty, mktSellHoga[i].qty);

				if(nu.plus(totAmt, tempAmt) >= maxAmt) {
					// 최대 주문가능 금액 초과
					if(nu.multi(mktSellHoga[i].prc, calQty) > nu.minus(maxAmt, totAmt)) {
						let possibleAmt = nu.minus(maxAmt, totAmt)
							, possibleQty = nu.div(possibleAmt, mktSellHoga[i].prc);
						totAmt = nu.plus(totAmt, nu.multi(mktSellHoga[i].prc, possibleQty));
						accQty = nu.plus(accQty, possibleQty);

						$('#mktBuyQty').val(nu.cm(accQty, selected.qtyCalcDevPnt) + extraComma);
					} else {
						totAmt = nu.plus(totAmt, nu.multi(mktSellHoga[i].prc, calQty));
					}
					break;
				} else if(tempQty <= 0) {
					// 최대 주문가능 수량 초과
					totAmt = nu.plus(totAmt, nu.multi(mktSellHoga[i].prc, calQty));
					accQty = nu.plus(accQty, calQty);
					if(qty !== parseFloat(strQty)) {
						$('#mktBuyQty').val(nu.cm(accQty, selected.qtyCalcDevPnt) + extraComma);
					}
					break;
				} else {
					calQty = nu.minus(calQty, mktSellHoga[i].qty);
					totAmt = nu.plus(totAmt, tempAmt);
					accQty = nu.plus(accQty, mktSellHoga[i].qty);
				}
			}
			if(qty >= maxQty) {
				$('#mktBuyQty').val(nu.cm(qty, selected.qtyCalcDevPnt) + extraComma);
			}
			$('#mktBuyTotOrder').text(nu.cm(totAmt, selected.amtCalcDecPnt));
			*/

			if(qty+qty*selected.buyFeeVal*0.01 > maxQty) {
				qty = maxQty-maxQty*selected.buyFeeVal*0.01;
			}
			$('#mktBuyQty').val(nu.cm(qty, selected.amtCalcDecPnt, true));
			$('#mktBuyTotOrder').text(nu.cm(qty, selected.amtCalcDecPnt, true));

		} else {
			$('#mktBuyQty').val(0);
			$('#mktBuyTotOrder').text(0);
		}
	});

	$('#mktSellQty').on('keydown', function(e) {
		let originQty = parseFloat(this.value.replace(/,/g, ''));

		evntKeyDown(e, this, selected.qtyCalcDevPnt);
		let strQty = $(this).val().replace(/,/g, '')
			, qty = parseFloat(strQty)
			, maxQty = parseFloat($('#buyTotal').text().replace(/,/g, ''))
			, limitQty = selected.toQty
			, totAmt = 0, calQty = 0
			, length = mktBuyHoga.length
			, extraComma = '';

		if(originQty === qty) {
			return;
		}

		qty = qty > maxQty ? maxQty:qty > limitQty ? limitQty:qty;
		calQty = qty;

		if(strQty.substr(-1) === '.') {
			extraComma = '.';
		}

		if(length > 0) {
			// 주문 총액 기준으로 계산
			for(let i=0; i<length;i++) {
				let tempAmt = nu.multi(mktBuyHoga[i].prc, mktBuyHoga[i].qty)
					, tempQty = nu.minus(calQty, mktBuyHoga[i].qty);

				if(tempQty <= 0) {
					// 최대 주문가능 수량 초과
					totAmt = nu.plus(totAmt, nu.multi(mktBuyHoga[i].prc, calQty, true));
					if(qty !== parseFloat(strQty)) {
						$('#mktSellQty').val(nu.cm(qty, selected.qtyCalcDevPnt, true) + extraComma);
					}
					break;
				} else {
					calQty = nu.minus(calQty, mktBuyHoga[i].qty);
					totAmt = nu.plus(totAmt, tempAmt);
				}
			}

			if(qty >= maxQty) {
				$('#mktSellQty').val(nu.cm(qty, selected.qtyCalcDevPnt, true) + extraComma);
			}
			$('#mktSellTotOrder').text(nu.cm(totAmt, selected.amtCalcDecPnt, true));
		} else {
			$('#mktSellQty').val(0);
			$('#mktSellTotOrder').text(0);
		}
	});

	$('.ordPrc').on('keydown', function(e) {
		evntKeyDown(e, this, selected.amtCalcDecPnt);
		calTotal(this, true);
	});

	$('#autoQty').on('keydown', function(e) {
		evntKeyDown(e, this, selected.qtyCalcDevPnt);
		calAutoByQty();
	});

	function calAutoByQty(flag) {
		let qty = $('#autoQty').val().replace(/,/g, '')
			, prc = qty * selected.closePrc
			, originTo = $('#autoTo').data('origin')
			, originFrom = $('#autoFrom').data('origin');

		if(qty > originTo || prc > originFrom) {
			$('#autoQty').val(nu.cm(originTo, selected.qtyCalcDevPnt, true));
			$('#autoAmt').text(nu.cm(originFrom, selected.amtCalcDecPnt, true));
			$('#autoOrdAmt').text(nu.cm(originFrom, selected.amtCalcDecPnt, true));
		} else {
			$('#autoAmt').text(nu.cm(prc, selected.amtCalcDecPnt, true));
			$('#autoOrdAmt').text(nu.cm(prc, selected.amtCalcDecPnt, true));
		}
	}

	function calTotal(t, flag) {
		let $tbody = $(t).closest('tbody'), $input = $tbody.find('input')
			, $qty, $prc, selector = $tbody.find('.totalorder').find('span:eq(0)')
			, total = 0;

		if(activeGrpId === 'tabs2-1') {
			$qty = $input.eq(1).val().replace(/,/g, '')
			$prc = $input.eq(0).val().replace(/,/g, '');
			//selector = $tbody.children('tr:eq(4)').find('span:eq(0)')
		} else {
			$qty = $input.eq(0).val().replace(/,/g, '')
			$prc = '' + selected.closePrc;
			//selector = $tbody.children('tr:eq(3)').find('span:eq(0)')
		}

		//console.log('calTotal:  prc=' + $prc + ',qty=' + $qty);

		if($prc != '' && $qty != '' && $prc != 0 && $qty != 0) {
			total = $prc * $qty

			//console.log('--> total=' + total + ', amtCalcDecPnt=' + selected.amtCalcDecPnt +', nu: ' + nu.cm(nu.nvl(total), selected.amtCalcDecPnt, true))

			if($input.data('type') == 'sell') {
				total = $qty > selected.toQty ? 0:total;
			} else if($input.data('type') == 'buy') {
				total = total > selected.fromQty ? 0:total;
			}

			if(total === 0) {
				$tbody.find('.buy-percent > div:last-child').trigger('click');
				$input.blur();
			} else {
				selector.text(nu.cm(nu.nvl(total), selected.amtCalcDecPnt, true));
			}
		} else {

			//console.log('--> total set 0');

			selector.text(nu.cm(nu.nvl(total), selected.amtCalcDecPnt, true));
		}
	}

	$('.up').on('click', function() {
		let $this = $(this), $input = $this.prev().prev();
		let value = parseFloat($input.val().replace(/,/g, ''));
		let mod = nu.remainder(value, selected.ordPrcUnit);

		value = mod != 0 ? nu.minus(nu.plus(value, selected.ordPrcUnit), mod):nu.plus(value, selected.ordPrcUnit);

		if(value < 0) {return false;}

		$input.val(nu.cm(value, selected.prcDspDecPnt, true));

		calTotal($this, true);
	})

	$('.down').on('click', function() {
		let $this = $(this), $input = $this.prev();
		let value = parseFloat($input.val().replace(/,/g, ''));
		let mod = nu.remainder(value, selected.ordPrcUnit);

		value = mod != 0 ? nu.minus(value, mod):nu.minus(value, selected.ordPrcUnit);

		if(value < 0) {return false;}

		$input.val(nu.cm(value, selected.prcDspDecPnt, true));

		calTotal($this, true);
	})

	$('#canSellAll').on('click', function() {
		lrt.confirm(getExchangeLocaleString('_L_0004'), getExchangeLocaleString('_L_0005'), function() {
			ajaxOption({
				url:'/site/exchange/cancelSellAll',
				data:{
					mktGrpId:selected.mktGrpId,
					mktId:selected.mktId,
					itemCode:selected.itemCode
				},
				success:function(data) {
					if(data.rtnCd != 0) {lrt.client(wordbook[data.rtnCd], '');}
				}
			});
		});
	})

	$('#canBuyAll').on('click', function() {
		lrt.confirm(getExchangeLocaleString('_L_0006'), getExchangeLocaleString('_L_0007'), function() {
			ajaxOption({
				url:'/site/exchange/cancelBuyAll',
				data:{
					mktGrpId:selected.mktGrpId,
					mktId:selected.mktId,
					itemCode:selected.itemCode
				},
				success:function(data) {
					if(data.rtnCd != 0) {lrt.client(wordbook[data.rtnCd], '');}
				}
			});
		});
	});

	$('.init').on('click', function() {
		let $tbody = $(this).closest('div').prev().find('tbody');
		$tbody.find('input').val('');
		$tbody.find('.totalorder').children('span:eq(0)').text(0);
	});

	$('#mainSearch').on('keyup', function(e) {
		let word = $(this).val().toLowerCase()
			, target = $('#tab-group-1').find('tr');
			//, target = $('#tab-group-1').children('div').not('#mainHolding').find('tr');

		if(word === '') {
			target.show();
		} else {
			target.each(function() {
				let $this = $(this), coinNm = $this.children('td:eq(1)').data('sort');
				let chosung = $this.data('chosung').toLowerCase();

				if(!checkIndexOf($this.data('code').toLowerCase(), word)
						&& !checkIndexOf(coinNm, word)
						&& !checkIndexOf(chosung, Hangul.disassemble(word).join(''))) {
					if($this.css('display') !== 'none')  $this.hide();
				} else {
					if($this.css('display') !== 'table-row') $this.show();
				}
			})
		}
	});

	$('#mainDaebi').on('change', function() {
		$('#tab-group-1').find('.daebiPrc').toggle();
	});

	$('#mainAmt').on('change', function() {
		$('#tab-group-1').find('.krwAmt').toggle();
	});

	$('#freeSearch').on('keyup', function(e) {
		let word = $(this).val().toLowerCase()
			, target = $('#tab-group-7').find('tr');
			//, target = $('#tab-group-7').children('div').not('#freeHolding').find('tr');

		if(word === '') {
			target.show();
		} else {
			target.each(function() {
				let $this = $(this), coinNm = $this.children('td:eq(1)').data('sort');
				let chosung = $this.data('chosung').toLowerCase();

				if(!checkIndexOf($this.data('code').toLowerCase(), word)
						&& !checkIndexOf(coinNm, word)
						&& !checkIndexOf(chosung, Hangul.disassemble(word).join(''))) {
					if($this.css('display') !== 'none')  $this.hide();
				} else {
					if($this.css('display') !== 'table-row') $this.show();
				}
			})
		}
	});

	$('#freeDaebi').on('change', function() {
		$('#tab-group-7').find('.daebiPrc').toggle();
	});

	$('#freeAmt').on('change', function() {
		$('#tab-group-7').find('.krwAmt').toggle();
	});

	$('#nonContractList').on('click', 'button', function() {
		let $this = $(this);

		lrt.confirm(getExchangeLocaleString('_L_0008'), getExchangeLocaleString('_L_0009'), function() {
			ajaxOption({
				url:'/site/exchange/doCancel',
				data:{
					itemCode:selected.itemCode,
					tranNo:$this.parents('tr').data('tran'),
					ordNo:$this.parents('tr').data('ord'),
					excNo:$this.parents('tr').data('exc')
				},
				success:function(data) {
					if(data.rtnCd != 0) {lrt.client(wordbook[data.rtnCd], '');}
				}
			});
		});
	});

	$('#tab-group-1, #tab-group-7, #tabs3-3').on('click', '.sort-toggle', function() {
		let $this = $(this)
			, $tbody = $(this).closest('ul').next().find('tbody')
			, $tr = $tbody.children('tr')
			, index = $this.closest('li').index() + 1
			, sortClass = $this.hasClass('sort-up')
							? '':$this.hasClass('sort-down') ? 'sort-up':'sort-down';

		index = sortClass === '' ? 0:index;

		$tr.sort(function(a, b) {
			let $a = $(a).children('td:eq(' + index + ')').data('sort')
				, $b = $(b).children('td:eq(' + index + ')').data('sort');

			if(sortClass === 'sort-down') {
				return  $a > $b ? -1 : $a < $b ? 1 : 0;
			} else {
				return  $a < $b ? -1 : $a > $b ? 1 : 0;
			}
		});

		$tbody.html($tr);

		$this.closest('ul').find('a').attr('class', 'sort-toggle');
		$this.attr('class', 'sort-toggle ' + sortClass);
	});

	// 해당 종목명에 해당하는 데이터 조회하여 보여주기
	getItemInfo();
})

var selected;
function getItemInfo() {
	ajaxOption({
		url:'/site/exchange/getItemCodeInfo',
		data:{itemCode:itemCode, coin:coin},
		success:function(data) {
			let unit = data.info.itemNm.split('/');

			var state = {Page:'holdport', Url:window.location.pathname + '?coin=' + data.info.itemCode};
			window.history.pushState(state, state.Page, state.Url);

			createHogaServerSentEvents(drawHogaData, data.info.mktGrpId);
			//--coben(2021/07/25)
			//createServerSentEvents(realContractLister, data.info.mktGrpId + '/contract');

			//TopicLister(drawHogaData, data.info.mktGrpId);

			selected = data.info;
			selected.toUnit = unit[0];
			selected.fromUnit = unit[1];

			// 상단 헤더 정보
			setTitle(data.info, data.list);

			// 종목 정보
			setItemCodeSise(data.info);

			// 호가 그리기
			$('#symbol').text(selected.toUnit);
			let maxQty = getMaxQty(data.sell, data.buy);
			drawSellHoga(data.sell, maxQty);
			drawBuyHoga(data.buy, maxQty);



			// 체결 내역 조회
			drawRealContract(data.realContract);

			// 일별시세
			drawDaily(data.daily);

			// 체결 내역
			drawContract(data.contract);

			// 미체결 내역
			drawNonContract(data.nonContract);

			// 마켓 정보 조회
			getMarket();

			// 코인 정보 조회
			getCoinInfo();

			// 차트
			drawChart(selected.itemCode, 30, selected.mktGrpId);
			createAreaChart(selected.itemCode, selected.mktGrpId);

			if(selected.fromUnit == 'YEP') {
				$('#tab-group-3').tabs('option', 'active', 1);
			}

			$(':input.tip-minAmt[type="text"]').attr("placeholder", '(' + getExchangeLocaleString('_L_0042') + ' ' + nu.cm(selected.minOrdQty, selected.qtyDspDecPnt, false) + ')');


			if (window.buysell != undefined)
				window.buysell.setAssetInfo(data);
		}
	});
}

function searchItemCode(data, headerYn) {
	ajaxOption({
		url:'/site/exchange/getMktItemCodeInfo',
		data:data,
		success:function(data) {
			let unit = data.info.itemNm.split('/');

			var state = {Page:'holdport', Url:window.location.pathname + '?coin=' + data.info.itemCode};
			window.history.pushState(state, state.Page, state.Url);

			if(selected.mktGrpId != data.info.mktGrpId) {
				createHogaServerSentEvents(drawHogaData, data.info.mktGrpId);
				//--coben(2021/07/25)
				//createServerSentEvents(realContractLister, data.info.mktGrpId + '/contract');
				
				//TopicLister(drawHogaData, data.info.mktGrpId);
				candleChart.remove();
				candleChart = null;
			}

			selected = data.info;
			selected.toUnit = unit[0];
			selected.fromUnit = unit[1];

			if(headerYn) {
				// 상단 헤더 정보
				setTitle(data.info, data.list);
			}

			// 종목 정보
			setItemCodeSise(data.info);

			// 호가 그리기
			$('#symbol').text(selected.toUnit);
			let maxQty = getMaxQty(data.sell, data.buy);
			drawSellHoga(data.sell, maxQty);
			drawBuyHoga(data.buy, maxQty);

			// 체결 내역 조회
			drawRealContract(data.realContract);

			// 일별시세
			drawDaily(data.daily);

			// 체결 내역
			drawContract(data.contract);

			// 미체결 내역
			drawNonContract(data.nonContract);

			// 코인 정보 조회
			getCoinInfo();

			// 차트
			drawChart(selected.itemCode, 30, selected.mktGrpId);
			createAreaChart(selected.itemCode, selected.mktGrpId);

			$('.qty').val('');
			$('.totalorder').children('span:eq(0)').text(0);

			$(':input.tip-minAmt[type="text"]').attr("placeholder", '(' + getExchangeLocaleString('_L_0042') +  ' ' + nu.cm(selected.minOrdQty, selected.qtyDspDecPnt, false) + ')');

		}
	});
}


function setTitle(data, list) {
	$('#coinTitle').text(getCoinName(data));
	$('.coin-symbol').html('<img src="/resources/img/coin-symbol/' + data.itemNm.split('/')[0] + '.png" alt="coin">')

	$('#coinList').off();

	let coinList = '';
	for(let i=0; i<list.length; i++) {
		let dataSet = 'data-mkt="' + list[i].mktId + '" '
			+ 'data-grp="' + list[i].mktGrpId + '" '
			+ 'data-code="' + list[i].itemCode + '"';

		let option = list[i].itemCode === selected.itemCode ? ' selected':'';

		coinList += '<option ' + dataSet + option + '>' + getCoinName(data) + '(' + list[i].itemNm + ')</option>';
	}

	$('#coinList').html(coinList)
	.on('change', function() {
		let $this = $(this).find('option:selected');
		searchItemCode({mkt:$this.data('mkt'), grp:$this.data('grp'), code:$this.data('code')}, false);
	});
}

function setItemCodeSise(data) {
	let compareHigh = data.highPrc - data.closePrc
		, compareLow = data.lowPrc - data.closePrc
		, color = data.closePrc === data.prevClosePrc ? '':data.closePrc > data.prevClosePrc ? 'red':'blue'
		, icon = color === '' ? '':color == 'red' ? 'up01':'down01', prcUnit = {};

	$('#closePrc').text(nu.cm(data.closePrc, data.prcDspDecPnt, true))
					.closest('td').attr('class', color);
	$('#daebiRate').text(nu.cm(data.prcDevRate, 2, true) + '%');
	$('#daebiPrc').text(nu.cm(data.prcDevAmt, data.prcDspDecPnt, true))
					.prev().attr('class', 'rate ' + icon);
	$('#highPrc').text(nu.cm(data.highPrc, data.prcDspDecPnt, true))
				.attr('class', getColor(data.highPrc, data.prevClosePrc));
				//.attr('class', compareHigh === 0 ? '':compareHigh > 0 ? 'red':'blue');
	$('#lowPrc').text(nu.cm(data.lowPrc, data.prcDspDecPnt, true))
				.attr('class', getColor(data.lowPrc, data.prevClosePrc));
				//.attr('class', compareLow === 0 ? '':compareLow > 0 ? 'red':'blue');
	$('.tradeVol').text(nu.cm(data.tradeVol, data.volDspDecPnt, true));
	$('.tradeAmt').text(nu.cm(data.tradeAmt, data.amtDspDecPnt, true));

	let split = data.itemNm.split('/');

	$('.amtUnit').text(split[1]);
	$('.volUnit').text(split[0]);

	if(data.basicCoinNo === 10) {
		prcUnit = getKrwUnit(data.tradeAmt);
	} else {
		prcUnit.price = nu.cm(data.tradeAmt, data.amtDecPnt, true);
		prcUnit.unit = selected.fromUnit;
	}

	$('.tradeAmt').text(prcUnit.price);
	$('.tradeAmtUnit').text(prcUnit.unit);

	$('#week52high').text(nu.cm(data.week52High, data.prcDspDecPnt, true))
					.attr('class', getColor(data.week52High, data.closePrc))
					.next().text(du.getCstmFrmt(data.week52HighDay, 'yyyyMMdd', '(yyyy-MM-dd)'));
	$('#week52low').text(nu.cm(data.week52Low, data.prcDspDecPnt, true))
					.attr('class', getColor(data.week52Low, data.closePrc))
					.next().text(du.getCstmFrmt(data.week52LowDay, 'yyyyMMdd', '(yyyy-MM-dd)'));

	$('#prevClosePrc').text(nu.cm(data.prevClosePrc, data.prcDspDecPnt, true));

	let plusMinus = data.highPrc > data.prevClosePrc ? '+': '';
	$('#todayHigh').text(nu.cm(data.highPrc, data.prcDspDecPnt, true))
					.attr('class', getColor(data.highPrc, data.prevClosePrc))
					.next().text(plusMinus + getCalPer(data.highPrc, data.prevClosePrc, false) + '%')
					.attr('class', getColor(data.highPrc, data.prevClosePrc));

	plusMinus = data.lowPrc > data.prevClosePrc ? '+': '';
	$('#todayLow').text(nu.cm(data.lowPrc, data.prcDspDecPnt, true))
					.attr('class', getColor(data.lowPrc, data.prevClosePrc))
					.next().text(plusMinus + getCalPer(data.lowPrc, data.prevClosePrc, false) + '%')
					.attr('class', getColor(data.lowPrc, data.prevClosePrc));

	$('.toQty').text(nu.cm(data.toQty, data.qtyDspDecPnt, true));
	$('.fromQty').text(nu.cm(data.fromQty, data.prcDspDecPnt, true));
	$('.ordPrc').val(nu.cm(data.closePrc, data.prcDspDecPnt, true));
	$('.minAmt').text('(' + getExchangeLocaleString('_L_0010') + ' ' + nu.cm(selected.minOrdAmt,data.amtCalcDecPnt) + ' ' + split[1] + ')');
	$('.sellFee').text(data.sellFeeVal);
	$('.buyFee').text(data.buyFeeVal);

	setAuto();

	changeDocumentTitle(data.closePrc);
}

function setAuto() {
	// 자동채굴
	let autoTo = (selected.toQty * selected.closePrc);
	let autoFrom = selected.fromQty;

	if(autoTo > autoFrom) {
		$('#autoFrom').data('origin', selected.fromQty)
			.text(nu.cm(selected.fromQty, selected.prcDspDecPnt, true));
		$('#autoTo').data('origin', selected.fromQty/selected.closePrc)
			.text( nu.cm(selected.fromQty/selected.closePrc, selected.qtyDspDecPnt) );
	} else {
		$('#autoFrom').data('origin', selected.toQty*selected.closePrc)
			.text(nu.cm(selected.toQty*selected.closePrc, selected.prcDspDecPnt, true));
		$('#autoTo').data('origin', selected.toQty)
			.text(nu.cm(selected.toQty, selected.qtyDspDecPnt, true));
	}
}

//var selected;
function getCoinInfo() {
	ajaxOption({
		url:'/site/exchange/getCoinInfo',
		data:{coinNo:selected.coinNo},
		success:function(data) {
			setItmeCodeInfo(data);
		}
	});
}

function setItmeCodeInfo(data) {
	let date = su.nvl(data.issueDay);

	if(date.length == 6) {
		date = date.substr(0,4) + '.' + date.substr(4,2);
	} else if(date.length == 8) {
		date = date.substr(0,4) + '.' + date.substr(4,2) + '.' + date.substr(6,2);
	}

	if(su.nvl(data.totSupplyQty) == 0) {
		total = getExchangeLocaleString('_L_0011');
	} else if(su.nvl(data.totSupplyQty) == -1) {
		total = getExchangeLocaleString('_L_0012');
	} else {
		total = nu.cm(su.nvl(data.totSupplyQty));
	}

	$('#itemCodeInfo').html(
			$('#ITEMCODE_INFO').html()
			.replace(/{{symbol}}/g, data.symbol)
			.replace(/{{itemCodeNm}}/g, su.nvl(selected.itemDomesticNm))
			.replace(/{{itemCodeEng}}/g, su.nvl(selected.itemEngNm))
			.replace(/{{corpNm}}/g, su.nvl(data.foundNm))
			.replace(/{{corpLoc}}/g, su.nvl(data.foundLoc))
			.replace(/{{corpRep}}/g, su.nvl(data.representNm))
			.replace(/{{corpSite}}/g, su.nvl(data.homepageUrl))
			.replace(/{{first}}/g,  date)
			.replace(/{{period}}/g, su.nvl(data.period))
			.replace(/{{total}}/g, total)
			.replace(/{{protocol}}/g, su.nvl(data.protocol))
			.replace(/{{paper}}/g, su.nvl(data.paperUrl))
			.replace(/{{intro}}/g, su.nvl(data.explanation))
	);
}

function drawSellHoga(data, max) {
	let $tr = $('.list1').children('tr'), sellTotal = 0;

	mktSellHoga = [];

	for(let i=9; i>=0; i--) {
		let $td = $tr.eq(i).children('td'), temp = {};

		if(data[9-i] != undefined) {
			let $data = data[9-i], prevClosePrc = parseFloat(selected.prevClosePrc)
				, color = $data.ordPrc - prevClosePrc === 0 ? '':$data.ordPrc - prevClosePrc > 0 ? 'red':'blue'
				, width = (($data.hogaQty/max) * 100).toFixed(2)
				, mark = color === '' ? '':color === 'red' ? '+':'-'
				, percent = getCalPerNonCm($data.ordPrc, prevClosePrc);

			temp.prc = parseFloat($data.ordPrc);
			temp.qty = parseFloat($data.hogaQty);
			mktSellHoga.push(temp);

			$td.eq(0).text('')
				.next().css('cursor', 'pointer')
				.find('div').css('width', width + '%')
				.next().text(nu.cm($data.hogaQty, selected.qtyDspDecPnt, true));
			$td.eq(2).text(nu.cm($data.ordPrc, selected.prcDspDecPnt, true))
				.attr('class', 'bgblue ' + color)
				.attr('id', 'op_' + Number($data.ordPrc).toFixed(2))
				.css('cursor', 'pointer')
				.next().attr('class', 'bgblue ' + color)
				.children('span:eq(0)').text(mark)
				.next().text(nu.cm(Math.abs(percent), 2))
				.next().text('%');

			sellTotal = nu.plus(sellTotal, parseFloat($data.hogaQty));

			//$td.eq(0).attr('id', 'h' + $data.ordPrc);
			$td.eq(0).attr('id', 'h' + String($data.ordPrc).replace('.', '_'))
				.attr('class', 'hprice alCenter')
				.data('hprice',$data.ordPrc);
			if(hogaMap[parseFloat($data.ordPrc)] == undefined || hogaMap[parseFloat($data.ordPrc)] == 0) {
				$td.eq(0).css('cursor', '');
			}

		} else {
			$td.eq(1).css('cursor', '')
				.find('div').css('width', '0%')
				.next().text('');
			$td.eq(2).text('').css('cursor', '')
				.next().children('span:eq(0)').text('')
				.next().text('')
				.next().text('');
			$td.eq(0).removeAttr('id').text('');
			$td.eq(0).removeClass('hprice');
		}
	}

	$('#sellTotal').text(nu.cm(sellTotal, selected.qtyDspDecPnt, true));
}

function drawBuyHoga(data, max) {
	let $tr = $('.list2').children('tr'), buyTotal = 0;

	mktBuyHoga = [];

	for(let i=0; i<10; i++) {
		let $td = $tr.eq(i).children('td'), temp = {};

		if(data[i] != undefined) {
			let $data = data[i], prevClosePrc = parseFloat(selected.prevClosePrc)
				, color = $data.ordPrc - prevClosePrc === 0 ? '':$data.ordPrc - prevClosePrc > 0 ? 'red':'blue'
				, width = (($data.hogaQty/max) * 100).toFixed(2)
				, mark = color === '' ? '':color === 'red' ? '+':'-'
				, percent = getCalPerNonCm($data.ordPrc, prevClosePrc)
				, index = 0;

			temp.prc = parseFloat($data.ordPrc);
			temp.qty = parseFloat($data.hogaQty);
			mktBuyHoga.push(temp);

			if(i == 0) {index += 1;}

			$td.eq(index).css('cursor', 'pointer')
				.attr('class', 'bgred ' + color)
				.attr('id', 'op_' + Number($data.ordPrc).toFixed(2))
				.text(nu.cm($data.ordPrc, selected.prcDspDecPnt, true))
				.next().attr('class', 'bgred ' + color)
				.children('span:eq(0)').text(mark)
				.next().text(nu.cm(Math.abs(percent), 2))
				.next().text('%');
			$td.eq(index+2).css('cursor', 'pointer')
				.find('div').css('width', width + '%')
				.next().text(nu.cm($data.hogaQty, selected.qtyDspDecPnt, true));

			buyTotal = nu.plus(buyTotal, $data.hogaQty);

			//$td.eq(index+3).attr('id', 'h' + $data.ordPrc).text('');
			$td.eq(index+3).attr('id', 'h' + String($data.ordPrc).replace('.', '_')).text('')
				.attr('class', 'hprice alCenter')
				.data('hprice', $data.ordPrc);
			if(hogaMap[parseFloat($data.ordPrc)] == undefined || hogaMap[parseFloat($data.ordPrc)] == 0) {
				$td.eq(index+3).css('cursor', '');
			}

		} else {
			let index = 0;

			if(i == 0) {index += 1;}

			$td.eq(index).css('cursor', '').text('')
				.next().children('span:eq(0)').text('')
				.next().text('')
				.next().text('');
			$td.eq(index+2).css('cursor', '')
				.find('div').css('width', '0%')
				.next().text('');

			$td.eq(index+3).removeAttr('id').text('');
			$td.eq(index+3).removeClass('hprice');
		}
	}

	$('#buyTotal').text(nu.cm(buyTotal, selected.qtyDspDecPnt, true));
}

function getMaxQty(sell, buy) {
	let maxQty = 0;

	for(let i=0; i<10; i++) {
		if(sell[i] != undefined) {maxQty = maxQty > sell[i].hogaQty ? maxQty:sell[i].hogaQty;}
		if(buy[i] != undefined) {maxQty = maxQty > buy[i].hogaQty ? maxQty:buy[i].hogaQty;}
	}

	return maxQty;
}

function getRealContract() {
	let $body = $('#realContractList');

	if($body.children('tr').length < REAL_SIZE) {
		ajaxOption({
			url:'/site/exchange/getRealContract',
			data:{
				mktGrpId:selected.mktGrpId,
				itemCode:selected.itemCode,
				contractDt:$body.children('tr:last-child').data('index')
			},
			success:function(data) {
				drawRealContract(data, true);
			}
		});
	}
}

function drawRealContract(data, flag) {
	let innerHtml = '', template = $('#REAL_CONTRACT_TEMPLATE').html(), length = data.length;
	let listHtml = '', listTemplate = $('#REAL_CONTRACT_LIST_TEMPLATE').html();

	for(let i=0; i<length; i++) {
		let sellBuy = data[i].contractRecogCd === 1 ? 'red':'blue'
			, daebiPrc = data[i].contractPrc - selected.prevClosePrc
			, mark = daebiPrc > 0 ? '+':''
			, icon = daebiPrc === 0 ? '':daebiPrc > 0 ? 'up01':'down01'
			, color = daebiPrc === 0 ? '':daebiPrc > 0 ? 'red':'blue'
			, qty = nu.cm(data[i].contractQty, selected.qtyDspDecPnt, true);

		let lPrc, lDaebiRate, lDaebiPrc, lPrcH = '';
		let ifTransactionId = data[i].ifTransactionId;

		if(ifTransactionId != null){
			if(ifTransactionId.split('_')[0] == 'AT' || ifTransactionId.split('_')[0] == 'AUTO') {
				lPrc = 'AM';
				lDaebiRate = '';
				lDaebiPrc = '';
				icon = 0;
				lPrcH = 'AM';
			}
			else if(ifTransactionId.split('_')[0] == 'SLFTRAD') {
				lPrc = 'AM';
				lDaebiRate = '';
				lDaebiPrc = '';
				icon = 0;
				lPrcH = 'AM';
			}
			else {
				lPrc = nu.cm(data[i].contractPrc, selected.prcDspDecPnt, true) + '(' + selected.fromUnit + ')';
				lDaebiRate = mark + getCalPer(data[i].contractPrc, selected.prevClosePrc, false) + '%';
				lDaebiPrc = nu.cm(daebiPrc, selected.prcDspDecPnt, true);
				lPrcH = nu.cm(data[i].contractPrc, selected.prcDspDecPnt, true);
			}
		} else{
			lPrc = nu.cm(data[i].contractPrc, selected.prcDspDecPnt, true) + '(' + selected.fromUnit + ')';
			lDaebiRate = mark + getCalPer(data[i].contractPrc, selected.prevClosePrc, false) + '%';
			lDaebiPrc = nu.cm(daebiPrc, selected.prcDspDecPnt, true);
			lPrcH = nu.cm(data[i].contractPrc, selected.prcDspDecPnt, true);
		}

		innerHtml += template.replace(/{{color}}/g, sellBuy)
			.replace(/{{prc}}/g, lPrcH)
			.replace(/{{size}}/g, calLetterSpacing(qty))
			.replace(/{{qty}}/g, qty);

		listHtml += listTemplate
		.replace(/{{index}}/g, data[i].contractDt)
		.replace(/{{dt}}/g, du.getCstmFrmt(data[i].contractDt, 'yyyyMMddHHmmss', 'yyyy-MM-dd<br>HH:mm:ss'))
		.replace(/{{prc}}/g, lPrc)
		.replace(/{{qty}}/g, nu.cm(data[i].contractQty, selected.qtyDspDecPnt, true))
		.replace(/{{daebiRate}}/g, lDaebiRate)
		.replace(/{{daebiPrc}}/g, lDaebiPrc)
		.replace(/{{icon}}/g, icon)
		.replace(/{{color}}/g, color)
		.replace(/{{contractAmt}}/g, nu.cm(data[i].contractPrc*data[i].contractQty, selected.amtCalcDecPnt, true))
		.replace(/{{sellBuyColor}}/g, sellBuy)
		.replace(/{{sellBuy}}/g, data[i].contractRecogCd === 1 ? getExchangeLocaleString('_L_0013'):getExchangeLocaleString('_L_0014'))
		.replace(/{{toUnit}}/g, selected.toUnit)
		.replace(/{{fromUnit}}/g, selected.fromUnit);

	}

	/*
	if(length > 0) {
		//most recent contract one
		changeDocumentTitle(data[0].contractPrc);
	} else {
		changeDocumentTitle(0);
	}
	*/

	for(let i=0; i<20-length; i++) {
		innerHtml += template.replace(/{{color}}/g, '')
								.replace(/{{prc}}/g, '')
								.replace(/{{qty}}/g, '');
	}

	if(flag) {
		$('#realContractList').append(listHtml);
	} else {
		$('#realContract').html(innerHtml);
		$('#realContractList').html(listHtml);
	}
}

function getDaily() {
	let $body = $('#daily');

	if($body.children('tr').length < DAILY_SIZE) {
		ajaxOption({
			url:'/site/exchange/getDailySise',
			data:{
				mktGrpId:selected.mktGrpId,
				itemCode:selected.itemCode,
				tradeDt:$body.children('tr:last-child').data('index')
			},
			success:function(data) {
				drawDaily(data, true);
			}
		});
	}
}

function drawDaily(data, flag) {
	let innerHtml = '', template = $('#DAILY_TEMPLATE').html(), length = data.length;

	for(let i=0; i<length; i++) {
		let color = data[i].daebiRecogCd === 0 ? '':data[i].daebiRecogCd > 0 ? 'red':'blue'
			, icon = data[i].daebiRecogCd === 0 ? '':data[i].daebiRecogCd > 0 ? 'up01':'down01'
			, mark = data[i].daebiRecogCd === 0 ? '':data[i].daebiRecogCd > 0 ? '+':'-'
			, daebiRate = (data[i].closePrc - data[i].prcDevAmt) === 0 ? 0 : ((data[i].prcDevAmt / (data[i].closePrc - data[i].prcDevAmt)) * 100);

		innerHtml += template
		.replace(/{{index}}/g, du.getCstmFrmt(data[i].tradeDt, 'yyyyMMddHHmmss', 'yyyyMMdd'))
		.replace(/{{dt}}/g, du.getCstmFrmt(data[i].tradeDt, 'yyyyMMddHHmmss', 'yyyy-MM-dd'))
		.replace(/{{prc}}/g, nu.cm(data[i].closePrc, selected.prcDspDecPnt))
		//.replace(/{{daebiRate}}/g, mark + ' ' + nu.cm(Math.abs(data[i].prcDevRate), 2) + '%')
		.replace(/{{daebiRate}}/g, mark + '' + nu.cm(Math.abs(daebiRate), 2) + '%')
		.replace(/{{daebiPrc}}/g, nu.cm(data[i].prcDevAmt, selected.prcDspDecPnt))
		.replace(/{{icon}}/g, icon)
		.replace(/{{color}}/g, color)
		.replace(/{{tradeVol}}/g, nu.cm(data[i].tradeVol, selected.volDspDecPnt))
		.replace(/{{tradeAmt}}/g, nu.cm(data[i].tradeAmt, selected.amtDecPnt));
	}

	if(flag) {
		$('#daily').append(innerHtml);
	} else {
		$('#daily').html(innerHtml);
	}
}

function getContract() {
	let $body = $('#contractList');

	if($body.children('tr').length < CONTRACT_SIZE) {
		ajaxOption({
			url:'/site/exchange/getContract',
			data:{
				mktGrpId:selected.mktGrpId,
				itemCode:selected.itemCode,
				contractDt:$body.children('tr:last-child').data('index')
			},
			success:function(data) {
				drawContract(data, true);
			}
		});
	}
}

function drawContract(data, flag) {
	if(data != undefined) {
		let innerHtml = '', template = $('#CONTRACT_TEMPLATE').html(), length = data.length;

		for(let i=0; i<length; i++) {
			let value = data[i];
			innerHtml += template
			.replace(/{{color}}/g, value.sellBuyRecogCd == 1 ? 'red':'blue')
			.replace(/{{sellBuy}}/g, value.sellBuyRecogCd == 1 ? getExchangeLocaleString('_L_0013'):getExchangeLocaleString('_L_0014'))
			.replace(/{{contractQty}}/g, nu.cm(value.contractQty, selected.qtyDspDecPnt, true))
			.replace(/{{contractPrc}}/g, nu.cm(value.contractPrc, selected.prcDspDecPnt, true))
			.replace(/{{contractAmt}}/g, nu.cm(value.contractPrc*value.contractQty, selected.amtCalcDecPnt, true))
			.replace(/{{contractDt}}/g, du.getCstmFrmt(value.contractDt, 'yyyyMMddHHmmss', 'yyyy-MM-dd HH:mm:ss'))
			.replace(/{{index}}/g, value.contractDt);
		}

		if(flag) {
			$('#contractList').append(innerHtml);
		} else {
			$('#contractList').html(innerHtml);
		}
	}

}

var hogaMap = {}, hogaList = [];
function drawNonContract(data) {
	if(data != undefined) {
		let innerHtml = '', template = $('#NON_CONTRACT_TEMPLATE').html(), length = data.length;

		hogaMap = {}, hogaList = [];

		for(let i=0; i<length; i++) {
			let value = data[i]
				, contractPrc = value.contractQty > 0 ? nu.cm(value.ordPrc, selected.prcDspDecPnt, true):0;
			innerHtml += template
				.replace(/{{tranNo}}/g, value.tranOrdNo)
				.replace(/{{ordNo}}/g, value.ordOrdNo)
				.replace(/{{excNo}}/g, value.excOrdNo)
				.replace(/{{itemCode}}/g, value.itemCode)
				.replace(/{{color}}/g, value.sellBuyCd == 1 ? 'red':'blue')
				.replace(/{{sellBuy}}/g, value.sellBuyCd == 1 ? getExchangeLocaleString('_L_0013'):getExchangeLocaleString('_L_0014'))
				.replace(/{{ordPrc}}/g, nu.cm(value.ordPrc, selected.prcDspDecPnt, true))
				.replace(/{{contractPrc}}/g, contractPrc)
				.replace(/{{nonQty}}/g, nu.cm(value.nonQty, selected.qtyDspDecPnt, true))
				.replace(/{{qty}}/g, nu.cm(value.contractQty, selected.qtyDspDecPnt, true))
				.replace(/{{ordDt}}/g, du.getCstmFrmt(value.ordDt, 'yyYYMMddHHmmss', 'YY-MM-dd HH:mm:ss'));

			let cnt = hogaMap[parseFloat(value.ordPrc)];
			if(cnt == undefined) {
				hogaList.push(parseFloat(value.ordPrc));
			}

			hogaMap[parseFloat(value.ordPrc)] = nu.nvl(hogaMap[parseFloat(value.ordPrc)]) + 1;
		}

		$('#nonContractList').html(innerHtml);

		drawPrivateHoga();
	}
}

function drawPrivateHoga() {
	$.each(hogaList, function(i, prc) {

		//console.log('drawPrivateHoga: price=' + prc + ',cnt='+ hogaMap[prc]);

		if( hogaMap[prc] <= 0) {
			$('#h' + String(prc).replace('.', '_')).text('');
			//hogaList.splice(i, 1);
			hogaMap[parseFloat(prc)] = undefined;
			$('#h' + String(hogaList[i]).replace('.', '_')).css('cursor', '');
		} else {
			if(hogaMap[hogaList[i]] != undefined) {
				$('#h' + String(hogaList[i]).replace('.', '_')).text('(' + hogaMap[hogaList[i]] + ')');
				$('#h' + String(hogaList[i]).replace('.', '_')).css('cursor', 'pointer');
			}
		}
	});
}

function addPrivateHoga(prc) {
	let price = parseFloat(prc)
		, cnt = hogaMap[price], target = $('#h' + String(price).replace('.', '_'));
	if(cnt == undefined) {
		hogaList.push(price);
	}

	hogaMap[price] = nu.nvl(cnt) + 1;


	//console.log('addPrivateHoga: price=' + prc + ',cnt='+ hogaMap[price]);

	target.text('(' + hogaMap[price] + ')');
	target.css('cursor', 'pointer');
}

function removePrivateHoga(prc) {
	let price = parseFloat(prc)
		, cnt = nu.nvl(hogaMap[price]) - 1, target = $('#h' + String(price).replace('.', '_'));

	hogaMap[price] = cnt;

	//console.log('removePrivateHoga: price=' + prc + ',cnt='+ cnt + ',target=' + target);

	if(cnt > 0) {
		target.text('(' + cnt + ')');
		target.css('cursor', 'pointer');
	} else {
		target.text('');
		target.css('cursor', '');
	}

}

function getMarket() {
	ajaxOption({
		url:'/site/exchange/getMarket',
		data:{coinNo:selected.coinNo},
		success:function(data) {
			drawMarket(data);
			//drawFreeMarket(data);
			drawBalance(data);
		}
	});
}

function drawMarket(data) {

	let mkt = data.market, mktHtml = '', itemHtml= '', tempHtml = ''
		, mktTemplate = $('#MKT_TEMPLATE').html()
		, _favoriteList = cu.getCookie(_favorite).split('|')
		, _favoriteMap = {};

	for(let i=0; i<_favoriteList.length; i++) {
		_favoriteMap[_favoriteList[i]] = true;
	}

	for(let i=0; i<mkt.length; i++) {
		mktHtml += '<li><a href="#' + mkt[i].mktNm + '">' + mkt[i].mktNm + '</a></li>';

		let item = mkt[i].item
			, itemTemplate = mkt[i].coinNo === 10 ? $('#MKT_ITEM_KRW_TEMPLATE').html():$('#MKT_ITEM_TEMPLATE').html();

		tempHtml = '';

		for(let j=0; j<item.length; j++) {
			let unit = item[j].itemNm.split('/')
				, prcUnit = {}
				, isActive = item[j].itemCode === selected.itemCode ? 'active':''
				, mark = _favoriteMap[item[j].itemCode] ? 'mark-on':''
				, prcDevAmt = item[j].prcDevAmt
				, plusMinus = prcDevAmt === 0 ? '':prcDevAmt > 0 ? '+ ':'- '
				, color = prcDevAmt === 0 ? '':prcDevAmt > 0 ? 'red':'blue';

			if(mkt[i].coinNo === 10) {
				prcUnit = getKrwUnit(item[j].tradeAmt);
			} else {
				prcUnit = getKrwUnit(item[j].tradeAmt * item[j].basicClosePrc);
			}

			// 코인 한글 초성 검색을 위해 초성을 발라냄 (강신석 2019.05.20)
			let disassemble = Hangul.disassemble(item[j].itemKorNm, true);
			let chosung = disassemble.reduce(function(prev, elem) {
                elem = elem[0] ? elem[0] : elem;
                return prev + elem;
            }, '');

			//console.log(item[j].itemCode + ".prcDspDecPnt:" + item[j].prcDspDecPnt + ", close: " + item[j].closePrc + ", nu.cm:" + nu.cm(item[j].closePrc, item[j].prcDspDecPnt, true));

			tempHtml += itemTemplate.replace(/{{markOn}}/g, mark)
						.replace(/{{isActive}}/g, isActive)
						.replace(/{{mktId}}/g, item[j].mktId)
						.replace(/{{mktGrpId}}/g, item[j].mktGrpId)
						.replace(/{{prcPnt}}/g, item[j].prcDspDecPnt)
						.replace(/{{amtPnt}}/g, item[j].amtDecPnt)
						.replace(/{{itemCode}}/g, item[j].itemCode)
						.replace(/{{index}}/g, j)
						.replace(/{{coinNm}}/g, getCoinName(item[j]))
						.replace(/{{toUnit}}/g, unit[0])
						.replace(/{{fromUnit}}/g, unit[1])
						.replace(/{{color}}/g, color)
						.replace(/{{originClosePrc}}/g, item[j].closePrc)
						.replace(/{{closePrc}}/g, nu.cm(item[j].closePrc, item[j].prcDspDecPnt, true))
						.replace(/{{originDaebiRate}}/g, item[j].prcDevRate)
						.replace(/{{daebiRate}}/g, plusMinus + nu.cm(Math.abs(item[j].prcDevRate), 2, true) + '%')
						.replace(/{{daebiPrc}}/g, plusMinus + nu.cm(Math.abs(item[j].prcDevAmt), item[j].prcDspDecPnt, true))
						.replace(/{{originTradeAmt}}/g, item[j].tradeAmt)
						.replace(/{{tradeAmt}}/g, nu.cm(item[j].tradeAmt, item[j].amtDecPnt, true))
						.replace(/{{krwAmt}}/g, prcUnit.price)
						.replace(/{{prcUnit}}/g, prcUnit.unit)
						.replace(/{{coinSymbolChosung}}/g, chosung);
		}

		itemHtml += mktTemplate.replace(/{{id}}/g, mkt[i].mktNm)
								.replace(/{{coinNo}}/g, mkt[i].coinNo)
								.replace(/{{mktNm}}/g, mkt[i].mktNm)
								.replace(/{{body}}/g, tempHtml);
	}

	$('#mainMarket').html(mktHtml);
	$('#tab-group-1').append(itemHtml).tabs({active:0})
	.on('click', 'tr', function(e) {
		let $this = $(this), isActive = $this.hasClass('active');

		if($(e.target).closest('td').index() != 0 && !isActive) {
			let mkt = $this.data('mkt'), grp = $this.data('grp')
				, itemCode = $this.data('code')
				, no = $this.data('no');

			if(itemCode != undefined && no == undefined) {
				searchItemCode({mkt:mkt, grp:grp, code:itemCode}, true);
				$('#tab-group-3').find('tr').removeClass('active')
				$this.addClass('active');
			}
		}
	});

	$('#tabs3-1').find('.bookmark').on('click', function() {
		let $this = $(this);

		$this.toggleClass('mark-on');

		if(!$this.hasClass('filter')) {
			if($this.hasClass('mark-on')) {
				let cookie = cu.getCookie(_favorite), code = $this.closest('tr').data('code');
				if(cookie == '') {cu.setCookie(_favorite, code, 365)}
				else {cu.setCookie(_favorite, cookie + '|'+ code, 365)}
			} else {
				let cookie = cu.getCookie(_favorite), code = $this.closest('tr').data('code')
					, array = cookie.split('|'), data = '';
				for(let i=0; i<array.length; i++) {
					if(array[i] != code) {
						data += (data === '' ? array[i]:'|' + array[i])
					}
				}
				cu.setCookie(_favorite, data, 365)
			}
		} else {
			let $target = $('#tab-group-1').find('tr');
			if($this.hasClass('mark-on')) {
				$('#tab-group-1').find('.bookmark:not(.mark-on)').closest('tr').hide();
			} else {
				$('#tab-group-1').find('.bookmark:not(.mark-on)').closest('tr').show();
			}
		}
	});

	$('.rightScroll').mCustomScrollbar();
}

function drawFreeMarket(data) {
	let mkt = data.free, mktHtml = '', itemHtml = '', tempHtml = ''
		, mktTemplate = $('#MKT_TEMPLATE').html()
		, itemTemplate = $('#MKT_ITEM_TEMPLATE').html()
		, _favoriteList = cu.getCookie(_favorite).split('|')
		, _favoriteMap = {};

	for(let i=0; i<_favoriteList.length; i++) {
		_favoriteMap[_favoriteList[i]] = true;
	}

	for(let i=0; i<mkt.length; i++) {
		mktHtml += '<li><a href="#' + mkt[i].mktNm + '">' + mkt[i].mktNm + '</a></li>';
		/*mktHtml += '<div class="radio_toggle">';
		mktHtml += '<p><label><input type="radio" name="yep_deposit" checked="true" value="0"/><span>' + getExchangeLocaleString('_L_0015') + '</span></label></p>';
		mktHtml += '<p><label><input type="radio" name="yep_deposit" value="1"/><span>' + getExchangeLocaleString('_L_0016') + '</span></label></p>';
		mktHtml += '</div>';*/

		let item = mkt[i].item;
		tempHtml = '';
		for(let j=0; j<item.length; j++) {
			let unit = item[j].itemNm.split('/')
				, prcUnit = {}
				, isActive = item[j].itemCode === selected.itemCode ? 'active':''
				, mark = _favoriteMap[item[j].itemCode] ? 'mark-on':''
				, prcDevAmt = item[j].prcDevAmt
				, plusMinus = prcDevAmt === 0 ? '':prcDevAmt > 0 ? '+ ':'- '
				, color = prcDevAmt === 0 ? '':prcDevAmt > 0 ? 'red':'blue';

			let disassemble = Hangul.disassemble(item[j].itemKorNm, true);
			let chosung = disassemble.reduce(function(prev, elem) {
                elem = elem[0] ? elem[0] : elem;
                return prev + elem;
            }, '');

			prcUnit = getKrwUnit(item[j].tradeAmt * item[j].closePrcYep);

			tempHtml += itemTemplate.replace(/{{markOn}}/g, mark)
						.replace(/{{isActive}}/g, isActive)
						.replace(/{{mktId}}/g, item[j].mktId)
						.replace(/{{mktGrpId}}/g, item[j].mktGrpId)
						.replace(/{{prcPnt}}/g, item[j].prcDspDecPnt)
						.replace(/{{amtPnt}}/g, item[j].amtDecPnt)
						.replace(/{{itemCode}}/g, item[j].itemCode)
						.replace(/{{index}}/g, j)
						.replace(/{{coinNm}}/g, getCoinName(item[j]))
						.replace(/{{toUnit}}/g, unit[0])
						.replace(/{{fromUnit}}/g, unit[1])
						.replace(/{{color}}/g, color)
						.replace(/{{originClosePrc}}/g, item[j].closePrc)
						.replace(/{{closePrc}}/g, nu.cm(item[j].closePrc, item[j].prcDspDecPnt, true))
						.replace(/{{originDaebiRate}}/g, item[j].prcDevRate)
						.replace(/{{daebiRate}}/g, plusMinus + nu.cm(Math.abs(item[j].prcDevRate), 2) + '%')
						.replace(/{{daebiPrc}}/g, plusMinus + nu.cm(Math.abs(item[j].prcDevAmt), item[j].prcDspDecPnt, true))
						.replace(/{{originTradeAmt}}/g, item[j].tradeAmt)
						.replace(/{{tradeAmt}}/g, nu.cm(item[j].tradeAmt, item[j].amtDecPnt, true))
						//.replace(/{{prcUnit}}/g, 'YEP')
						.replace(/{{coinSymbolChosung}}/g, chosung)
						.replace(/{{krwAmt}}/g, prcUnit.price)
						.replace(/{{prcUnit}}/g, prcUnit.unit);
		}

		itemHtml += mktTemplate.replace(/{{id}}/g, mkt[i].mktNm)
								.replace(/{{coinNo}}/g, mkt[i].coinNo)
								.replace(/{{mktNm}}/g, mkt[i].mktNm)
								.replace(/{{body}}/g, tempHtml);
	}

	$('#freeMarket').html(mktHtml);
	$('#tab-group-7').append(itemHtml).tabs({active:0})
	.on('click', 'tr', function(e) {
		let $this = $(this), isActive = $this.hasClass('active');

		if($(e.target).closest('td').index() != 0 && !isActive) {
			let mkt = $this.data('mkt'), grp = $this.data('grp')
				, itemCode = $this.data('code')
				, no = $this.data('no');

			if(itemCode != undefined && no == undefined) {
				searchItemCode({mkt:mkt, grp:grp, code:itemCode}, true);
				$('#tab-group-3').find('tr').removeClass('active')
				$this.addClass('active');
			}
		}
	});

	$('#tabs3-2').find('.bookmark').on('click', function() {
		let $this = $(this);

		$this.toggleClass('mark-on');

		if(!$this.hasClass('filter')) {
			if($this.hasClass('mark-on')) {
				let cookie = cu.getCookie(_favorite), code = $this.closest('tr').data('code');
				if(cookie == '') {cu.setCookie(_favorite, code, 365);}
				else {cu.setCookie(_favorite, cookie + '|'+ code, 365);}
			} else {
				let cookie = cu.getCookie(_favorite), code = $this.closest('tr').data('code')
					, array = cookie.split('|'), data = '';
				for(let i=0; i<array.length; i++) {
					if(array[i] != code) {
						data += (data === '' ? array[i]:'|' + array[i])
					}
				}
				cu.setCookie(_favorite, data, 365)
			}
		} else {
			let $target = $('#tab-group-1').find('tr');
			if($this.hasClass('mark-on')) {
				$('#tab-group-7').find('.bookmark:not(.mark-on)').closest('tr').hide();
			} else {
				$('#tab-group-7').find('.bookmark:not(.mark-on)').closest('tr').show();
			}
		}
	});

	/*$('input[name=yep_deposit]').on('change', function() {

	})*/

	$('.rightScroll').mCustomScrollbar();
}

function drawBalance(data) {
	let mkt = data.market, innerHtml = '', tempHtml = '', krwHtml = ''
		, template = $('#HOLDING_ITEM_TEMPLATE').html();

	if(data.balance != undefined && data.balance.length > 0) {
		let balance = data.balance;
		let length = balance.length;
		for(let i=0; i<length; i++) {
			let temp = balance[i]
				, bcPrc = temp.closePrc * temp.balanceQty
				, avgPrc = temp.avgPrc * temp.balanceQty
				, profit = temp.closePrc - temp.avgPrc
				, profitPrc = profit * temp.balanceQty
				, profitRate = ((profit/temp.avgPrc) * 100)
				, mark = profitPrc > 0 ? '+':''
				, color = profitPrc === 0 ? '':profitPrc > 0 ? 'red':'blue';

			let disassemble = Hangul.disassemble(temp.coinKorNm, true);
			let chosung = disassemble.reduce(function(prev, elem) {
                elem = elem[0] ? elem[0] : elem;
                return prev + elem;
            }, '');

			if(temp.coinNo === 10) {
				krwHtml = template
				.replace(/{{coinNo}}/g, temp.coinNo)
				.replace(/{{closePrc}}/g, temp.closePrc)
				.replace(/{{avg}}/g, temp.avgPrc)
				.replace(/{{originQty}}/g, temp.balanceQty)
				.replace(/{{originAvg}}/g, temp.avgPrc)
				.replace(/{{originBcPrc}}/g, bcPrc)
				.replace(/{{originAvgPrc}}/g, '')
				.replace(/{{originProfitRate}}/g, '')
				.replace(/{{index}}/g, i)
				.replace(/{{symbol}}/g, temp.symbol)
				.replace(/{{coinKorNm}}/g, temp.coinKorNm)
				.replace(/{{coinNm}}/g, temp.symbol)
				.replace(/{{qty}}/g, nu.cm(temp.balanceQty, temp.qtyDspDecPnt))
				.replace(/{{bcPrc}}/g, '')
				.replace(/{{avgPrc}}/g, '-')
				.replace(/{{bcUnit}}/g, '')
				.replace(/{{profitRate}}/g, '')
				.replace(/{{profitPrc}}/g, '')
				.replace(/{{color}}/g, color)
				.replace(/{{prcPnt}}/g, temp.prcDspDecPnt)
				.replace(/{{qtyPnt}}/g, temp.qtyDspDecPnt)
				.replace(/{{itemCode}}/g, temp.itemCode)
				.replace(/{{coinSymbolChosung}}/g, chosung)
				.replace(/{{itemCode}}/g, temp.coinKorNm);
			} else {


				let prcUnit = {};
				prcUnit = getKrwUnit(profitPrc);

				innerHtml += template
				.replace(/{{coinNo}}/g, temp.coinNo)
				.replace(/{{closePrc}}/g, temp.closePrc)
				.replace(/{{avg}}/g, temp.avgPrc)
				.replace(/{{originQty}}/g, temp.balanceQty)
				.replace(/{{originAvg}}/g, temp.avgPrc)
				.replace(/{{originBcPrc}}/g, bcPrc)
				.replace(/{{originAvgPrc}}/g, temp.avgPrc)
				.replace(/{{originProfitRate}}/g, profitRate)
				.replace(/{{index}}/g, i)
				.replace(/{{symbol}}/g, temp.symbol)
				.replace(/{{coinKorNm}}/g, temp.coinKorNm)
				.replace(/{{coinNm}}/g, temp.symbol)
				.replace(/{{qty}}/g, nu.cm(temp.balanceQty, temp.qtyDspDecPnt, true))
				.replace(/{{bcPrc}}/g, nu.cm(bcPrc, temp.prcDspDecPnt, true))
				.replace(/{{avgPrc}}/g, nu.cm(temp.avgPrc, temp.prcDspDecPnt, true))
				.replace(/{{bcUnit}}/g, temp.bsSymbol)
				.replace(/{{profitRate}}/g, mark + '' + nu.cm(profitRate, 2, true) + '%')
				//.replace(/{{profitPrc}}/g, mark + '' + nu.cm(profitPrc, temp.prcDspDecPnt, true))
				.replace(/{{profitPrc}}/g, mark + '' + prcUnit.price + prcUnit.unit)
				.replace(/{{color}}/g, color)
				.replace(/{{prcPnt}}/g, temp.prcDspDecPnt)
				.replace(/{{qtyPnt}}/g, temp.qtyDspDecPnt)
				.replace(/{{itemCode}}/g, temp.itemCode)
				.replace(/{{coinSymbolChosung}}/g, chosung)
				.replace(/{{itemCode}}/g, temp.coinKorNm);
			}
		}

		$('#balanceList').html(krwHtml + innerHtml)
		.on('click', 'tr', function(e) {
			let $this = $(this), itemCode = $this.data('code')
				, $target = $('#tab-group-1').find('tr[data-code=' + itemCode + ']');

			if($target.length > 0 && itemCode != selected.itemCode) {
				let mkt = $target.data('mkt'), grp = $target.data('grp')
					, itemCode = $target.data('code')
					, no = $target.data('no');

				if(itemCode != undefined && no == undefined) {
					searchItemCode({mkt:mkt, grp:grp, code:itemCode}, true);
					$('#tab-group-3').find('tr').removeClass('active')
					$target.addClass('active');
				}
			}
		});
	}
}


function updateHourlyTradeVolAmt()
{
	if(selected == undefined) {
		return;
	}

	ajaxOption({
		url:'/site/exchange/getMarket',
		data:{coinNo:selected.coinNo},
		success:function(data) {


			let mkt = data.market;

			for(let i=0; i<mkt.length; i++) {

				let item = mkt[i].item;

				for(let j=0; j<item.length; j++) {

					let prcUnit = {};
		
					if(mkt[i].coinNo === 10) {
						prcUnit = getKrwUnit(item[j].tradeAmt);
					} else {
						prcUnit = getKrwUnit(item[j].tradeAmt * item[j].basicClosePrc);
					}

					let $tr = $('#tab-group-3').find('tr[data-code=' + item[j].itemCode + ']');

					if($tr.length > 0) {

						let $td = $tr.children('td')
						, coinNo = $tr.closest('tbody').data('coinno')
						, amtPnt = $tr.data('amt');

						if(coinNo === 10) {
							$td.eq(4).data('sort', parseFloat(item[j].tradeAmt))
								.find('span:eq(0)').text(prcUnit.price).next().text(prcUnit.unit);
						} else {

							$td.eq(4).data('sort', parseFloat(item[j].tradeAmt))
								.find('p:first-child').text(nu.cm(item[j].tradeAmt, amtPnt, true))
								.next().children('span:eq(0)').text(prcUnit.price)
								.next().text(prcUnit.unit);
						}
					}
					/*
					if(selected.itemCode == item[j].itemCode) {
						
						$('.tradeVol').text(nu.cm(item[j].tradeVol, selected.volDspDecPnt, true));
						$('.tradeAmt').text(nu.cm(item[j].tradeAmt, selected.amtDspDecPnt, true));
					
						if(selected.basicCoinNo === 10) {
							prcUnit = getKrwUnit(item[j].tradeAmt);
						} else {
							prcUnit = {};
							prcUnit.price = nu.cm(item[j].tradeAmt, selected.amtDecPnt, true);
							prcUnit.unit = selected.fromUnit;
						}
					
						$('.tradeAmt').text(prcUnit.price);
						$('.tradeAmtUnit').text(prcUnit.unit);
					}
					*/
				}
			}
		}
	});

	ajaxOption({
		url:'/site/exchange/getItemCodeInfo',
		data:{ itemCode:selected.itemCode },
		success:function(data) {
			setItemCodeSise(data.info);
		}
	});

}


function doPndSell(target) {
	let $input = $('.pending-sell').find('input');
	let p = {
			mktGrpId:selected.mktGrpId,
			mktId:selected.mktId,
			itemCode:selected.itemCode,
			ordQty:$input.eq(1).val().replace(/,/g, ''),
			ordPrice:$input.eq(0).val().replace(/,/g, '')
	}

	$(target).blur();

	if(checkValue(p)) {
		let message = getExchangeLocaleString('_L_0017') + ' : ' + nu.cm(p.ordQty, selected.qtyDspDecPnt, true) + ' ' + selected.toUnit + '<br>';
		message += getExchangeLocaleString('_L_0018') + ' : ' + nu.cm(p.ordPrice, selected.prcDspDecPnt, true) + ' ' + selected.fromUnit + '<br>';
		message += getExchangeLocaleString('_L_0019');

		ord.sell(message, getExchangeLocaleString('_L_0020'), function() {
			ajaxOption({
				url:'/site/exchange/doPndSell',
				data:p,
				success:function(data) {
					if(data.rtnCd != 0) {lrt.client(wordbook[data.rtnCd], '');}
				}
			});
		})
	}
}

function doPndBuy(target) {
	let $input = $('.pending-buy').find('input');
	let p = {
			mktGrpId:selected.mktGrpId,
			mktId:selected.mktId,
			itemCode:selected.itemCode,
			ordQty:$input.eq(1).val().replace(/,/g, ''),
			ordPrice:$input.eq(0).val().replace(/,/g, '')
	}

	$(target).blur();

	if(checkValue(p)) {
		let message = getExchangeLocaleString('_L_0017') + ' : ' + nu.cm(p.ordQty, selected.qtyDspDecPnt, true) + ' ' + selected.toUnit + '<br>';
		message += getExchangeLocaleString('_L_0018') + ' : ' + nu.cm(p.ordPrice, selected.prcDspDecPnt, true) + ' ' + selected.fromUnit + '<br>';
		message += getExchangeLocaleString('_L_0021');

		ord.buy(message, getExchangeLocaleString('_L_0022'), function() {
			ajaxOption({
				url:'/site/exchange/doPndBuy',
				data:p,
				success:function(data) {
					if(data.rtnCd != 0) {lrt.client(wordbook[data.rtnCd], '');}
				}
			});
		});
	}
}

function doMktSell(target) {
	let $input = $('.market-sell').find('input');
	let p = {
			mktGrpId:selected.mktGrpId,
			mktId:selected.mktId,
			itemCode:selected.itemCode,
			ordQty:$input.eq(0).val().replace(/,/g, ''),
			/*
			ordPrice:$('.list2').children('tr:first-child').children('td:eq(1)').text().replace(/,/g, '')
			*/
			ordPrice: '1.0'
	}

	$(target).blur();

	if(p.ordPrice == '') {
		lrt.client(getExchangeLocaleString('_L_0023'));
		return false;
	} else if(checkMktValue(p, 2)) {
		let message = getExchangeLocaleString('_L_0017') + ' : ' + nu.cm(p.ordQty, selected.qtyDspDecPnt, true) + ' ' + selected.toUnit + '<br>';
		message += getExchangeLocaleString('_L_0024');

		ord.sell(message, getExchangeLocaleString('_L_0020'), function() {
			ajaxOption({
				url:'/site/exchange/doMktSell',
				data:p,
				success:function(data) {
					if(data.rtnCd != 0) {lrt.client(wordbook[data.rtnCd], '');}
				}
			});
		});
	}
}

function doMktBuy(target) {
	let $input = $('.market-buy').find('input');
	let p = {
			mktGrpId:selected.mktGrpId,
			mktId:selected.mktId,
			itemCode:selected.itemCode,
			/*
			ordQty:$input.eq(0).val().replace(/,/g, ''),
			ordPrice:$('.list1').children('tr:last-child').children('td:eq(2)').text().replace(/,/g, '')
			*/
			ordPrice:$input.eq(0).val().replace(/,/g, ''),
			ordQty:'1.0'
	}

	$(target).blur();

	if(p.ordPrice == '') {
		lrt.client(getExchangeLocaleString('_L_0039'));
		return false;
	} else if(checkMktValue(p, 1)) {
		let message = getExchangeLocaleString('_L_0040') + ' : ' + nu.cm(p.ordPrice, selected.amtDspDecPnt, true) + ' ' + selected.fromUnit + '<br>';
		message += getExchangeLocaleString('_L_0025');

		ord.buy(message, getExchangeLocaleString('_L_0022'), function() {
			ajaxOption({
				url:'/site/exchange/doMktBuy',
				data:p,
				success:function(data) {
					if(data.rtnCd != 0) {lrt.client(wordbook[data.rtnCd], '');}
				}
			});
		});
	}
}

function doAutoMining(target) {
	let p = {
		mktGrpId:selected.mktGrpId,
		mktId:selected.mktId,
		itemCode:selected.itemCode,
		ordQty:$('#autoQty').val().replace(/,/g, ''),
		ordPrice:selected.closePrc
	};

	$(target).blur();

	if(checkValue(p)) {
		let message = getExchangeLocaleString('_L_0026') + ' : ' + $('#autoQty').val() + ' ' + selected.toUnit + '<br>';
		/*message += getExchangeLocaleString('_L_0015') + ' : ' + $('#autoAmt').text() + ' ' + selected.fromUnit + '<br>';*/
		message += getExchangeLocaleString('_L_0027');

		lrt.confirm(message, getExchangeLocaleString('_L_0028'), function() {
			ajaxOption({
				url:'/site/exchange/doAutoMining',
				data:p,
				success:function(data) {
					if(data.rtnCd != 0) {lrt.client(wordbook[data.rtnCd], '');}
					else {
						$('#autoQty').val('');
						$('#autoAmt').text('');
					}
				}
			});
		});
	}
}

function evntKeyDown(e, target, point) {
	let regex = /^4[8-9]$|^5[0-7]{1}$|^9[6-9]{1}$|^10[0-5]{1}$|^110$|^190$|^229$/gm
		, key = JSON.parse(JSON.stringify(e.keyCode))
		, tbody = $(target).closest('tbody')
		, type = tbody.data('type');

	//console.log("keydown: " + e + ", point:" + point);

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

		let keyValue = ''

		if(e.keyCode == 229)
			keyValue = e.target.value.charAt(e.target.selectionStart -1).charCodeAt();
		else
			keyValue = e.key;

		if(keyValue === 'Decimal') {
			value = first + '.' + last;
		} else {
			value = first + keyValue + last;
		}

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

			value = nu.nonCm((first + last).replace(/[^0-9.]/g, ''), point);
			position = value.length - last.length;
			position = position < 0 ? 0:position;

			if(first.indexOf('.') == value.length) {
				value += '.';
				position++;
			}

			$this.val(value);
			target.setSelectionRange(position, position);

			return false;
		} else {
			e.preventDefault();
			return false;
		}
	}
}

function getTradeParam(target) {
	let $input = $(target).find('input');
	let p = {
			mktGrpId:selected.mktGrpId,
			mktId:selected.mktId,
			itemCode:selected.itemCode,
			ordQty:$input.eq(0).val().replace(/,/g, ''),
			ordPrice:$input.eq(1).val().replace(/,/g, '')
	}

	return p;
}

function checkValue(data) {
	if(data.ordQty == '') {
		lrt.client(getExchangeLocaleString('_L_0029'));
		return false;
	} else if(data.ordPrice == '') {
		lrt.client(getExchangeLocaleString('_L_0030'));
		return false;
	} else if(selected.minOrdQty > data.ordQty) {
		lrt.client(getExchangeLocaleString('_L_0043') + ' ' + nu.cm(selected.minOrdQty, selected.qtyDspDecPnt, false) + selected.itemNm.split('/')[0] + ' ' + getExchangeLocaleString('_L_0032'));
		return false;
	} else if(selected.minOrdAmt > (data.ordQty * data.ordPrice)) {
		lrt.client(getExchangeLocaleString('_L_0031') + ' ' + nu.cm(selected.minOrdAmt, selected.amtDspDecPnt, false) + selected.itemNm.split('/')[1] + ' ' + getExchangeLocaleString('_L_0032'));
		return false;
	} else if(data.ordPrice != undefined && nu.remainder(data.ordPrice, selected.ordPrcUnit) != 0) {
		lrt.client(getExchangeLocaleString('_L_0033') + ' ' + selected.ordPrcUnit + selected.itemNm.split('/')[1] + ' ' + getExchangeLocaleString('_L_0032'));
		return false;
	}

	return true;
}

function checkMktValue(data, sellbuycd) {

	if(sellbuycd == 1) {
		//buy
		if(data.ordPrice == '') {
			lrt.client(getExchangeLocaleString('_L_0041'));
			return false;
		} else if(selected.minOrdQty > data.ordQty) {
			lrt.client(getExchangeLocaleString('_L_0043') + ' ' + nu.cm(selected.minOrdQty, selected.qtyDspDecPnt, false) + selected.itemNm.split('/')[0] + ' ' + getExchangeLocaleString('_L_0032'));
			return false;
		} else if(selected.minOrdAmt > data.ordPrice) {
			lrt.client(getExchangeLocaleString('_L_0031') + ' ' + nu.cm(selected.minOrdAmt, selected.amtDspDecPnt, false) + selected.itemNm.split('/')[1] + ' ' + getExchangeLocaleString('_L_0032'));
			return false;
		}
	} else {
		//sell
		if(data.ordQty == '' || parseFloat(data.ordQty) <= 0.0) {
			lrt.client(getExchangeLocaleString('_L_0029'));
			return false;
		} /*else if(selected.minOrdAmt > (data.ordQty * data.ordPrice)) {
			lrt.client(getExchangeLocaleString('_L_0031') + ' ' + selected.minOrdAmt + selected.itemNm.split('/')[1] + ' ' + getExchangeLocaleString('_L_0032'));
			return false;
		}*/
	}

	return true;
}

function getCalPer(data, standard, flag) {
	if(flag === true) {
		return nu.cm(Math.abs(((data - standard) / standard) * 100), 2, true);
	} else {

		console.log("get cal per: " + data + "," + standard + "=" + nu.cm(((data - standard) / standard) * 100, 2, true));


		return nu.cm(((data - standard) / standard) * 100, 2, true);
	}
}

function getCalPerNonCm(data, standard) {
	return ((data - standard) / standard) * 100;
}

function getColor(data, standard) {
	standard = standard === undefined ? 0:standard;
	return data === standard ? '':data > standard ? 'red':'blue';
}

function getKrwUnit(price) {
	var krwPrice = parseInt(parseFloat(price));
	var result = {};

	if(krwPrice >= 1000000) {
		result.price = nu.cm(Math.floor(krwPrice/1000000), 0);
		result.unit = getExchangeLocaleString('_L_0034');
	} else if(krwPrice >= 1000) {
		result.price = nu.cm(Math.floor(krwPrice/1000), 0);
		result.unit = getExchangeLocaleString('_L_0035');
	} else {
		result.price = nu.cm(krwPrice, 0);
		result.unit = getExchangeLocaleString('_L_0036');
	}

	return result;
}

function checkIndexOf(origin, word) {
	return origin.indexOf(word) > -1 ? true:false;
}

function doLogin() {
	location.href='/site/login';
}

function doRegister() {
	location.href='/site/register';
}

function drawHogaData(data) {
	if(document.visibilityState == 'visible') {
		data = JSON.parse(data).body;


		if(selected.itemCode === data.itemCode) {
			let sellTotal = 0, buyTotal = 0, max = 0
				, sellHogaList = data.SellHogaList, buyHogaList = data.BuyHogaList
				, length = sellHogaList.length > buyHogaList.length ? sellHogaList.length:buyHogaList.length
				, $tr = $('.list1').children('tr')
				, prevClosePrc = parseFloat(selected.prevClosePrc);

			for(let i=0; i<length; i++) {
				if(sellHogaList[i] != undefined) {
					max = parseFloat(sellHogaList[i].SellHogaQty) > max
						? parseFloat(sellHogaList[i].SellHogaQty):max;
				}

				if(buyHogaList[i] != undefined) {
					max = parseFloat(buyHogaList[i].BuyHogaQty) > max
						? parseFloat(buyHogaList[i].BuyHogaQty):max;
				}
			}

			mktSellHoga = [];
			mktBuyHoga = [];

			for(let i=9; i>=0; i--) {
				let $td = $tr.eq(i).children('td'), temp = {};

				if(sellHogaList[9-i] != undefined) {
					let $data = sellHogaList[9-i]
						, sellHoga = $data.SellHoga, sellQty = $data.SellHogaQty
						, color = sellHoga - prevClosePrc === 0 ? '':sellHoga - prevClosePrc > 0 ? 'red':'blue'
						, width = ((sellQty/max) * 100).toFixed(2)
						, mark = color === '' ? '':color === 'red' ? '+':'-'
						, percent = getCalPerNonCm(sellHoga, prevClosePrc);

					temp.prc = parseFloat($data.SellHoga);
					temp.qty = parseFloat($data.SellHogaQty);
					mktSellHoga.push(temp);

					//console.log("--------------sellid", sellHoga);
					$td.eq(0).text('')
						.next().css('cursor', 'pointer')
						.find('div').css('width', width + '%')
						.next().text(nu.cm(sellQty, selected.qtyDspDecPnt, true));
					$td.eq(2).text(nu.cm(sellHoga, selected.prcDspDecPnt, true))
						.attr('class', 'bgblue ' + color)
						.attr('id', 'op_' + Number(sellHoga).toFixed(2))
						.css('cursor', 'pointer')
						.next().attr('class', 'bgblue ' + color)
						.children('span:eq(0)').text(mark)
						.next().text(nu.cm(Math.abs(percent), 2, true))
						.next().text('%');

					sellTotal = nu.plus(sellTotal, parseFloat(sellQty));

					//$td.eq(0).attr('id', 'h' + parseFloat(sellHoga));
					$td.eq(0).attr('id', 'h' + String(parseFloat(sellHoga)).replace('.', '_'))
						.attr('class', 'hprice alCenter')
						.data('hprice', parseFloat(sellHoga));
					if(hogaMap[parseFloat(sellHoga)] == undefined || hogaMap[parseFloat(sellHoga)] == 0) {
						$td.eq(0).css('cursor', '');
					}

				} else {
					$td.eq(1).css('cursor', '')
						.find('div').css('width', '0%')
						.next().text('');
					$td.eq(2).text('').css('cursor', '')
						.next().children('span:eq(0)').text('')
						.next().text('')
						.next().text('');

					$td.eq(0).removeAttr('id').text('');
					$td.eq(0).removeClass('hprice');

				}
			}

			$tr = $('.list2').children('tr');
			for(let i=0; i<10; i++) {
				let $td = $tr.eq(i).children('td'), temp = {};

				if(buyHogaList[i] != undefined) {
					let $data = buyHogaList[i]
						, buyHoga = $data.BuyHoga, buyQty = $data.BuyHogaQty
						, color = buyHoga - prevClosePrc === 0 ? '':buyHoga - prevClosePrc > 0 ? 'red':'blue'
						, width = ((buyQty/max) * 100).toFixed(2)
						, mark = color === '' ? '':color === 'red' ? '+':'-'
						, percent = getCalPerNonCm(buyHoga, prevClosePrc)
						, index = 0;

					temp.prc = parseFloat($data.BuyHoga);
					temp.qty = parseFloat($data.BuyHogaQty);
					mktBuyHoga.push(temp);

					if(i == 0) {index += 1;}

					//console.log("--------------buyid", buyHoga);
					$td.eq(index).css('cursor', 'pointer')
						.attr('class', 'bgred ' + color)
						.attr('id', 'op_' + Number(buyHoga).toFixed(2))
						.text(nu.cm(buyHoga, selected.prcDspDecPnt, true))
						.next().attr('class', 'bgred ' + color)
						.children('span:eq(0)').text(mark)
						.next().text(nu.cm(Math.abs(percent), 2, true))
						.next().text('%');
					$td.eq(index+2).css('cursor', 'pointer')
						.find('div').css('width', width + '%')
						.next().text(nu.cm(buyQty, selected.qtyDspDecPnt, true));

					buyTotal = nu.plus(buyTotal, parseFloat(buyQty));

					//$td.eq(index+3).attr('id', 'h' + parseFloat(buyHoga)).text('');
					$td.eq(index+3).attr('id', 'h' + String(parseFloat(buyHoga)).replace('.', '_')).text('')
						.attr('class', 'hprice alCenter')
						.data('hprice', parseFloat(buyHoga));
					if(hogaMap[parseFloat(buyHoga)] == undefined || hogaMap[parseFloat(buyHoga)] == 0) {
						$td.eq(index+3).css('cursor', '');
					}

				} else {
					let index = 0;

					if(i == 0) {index += 1;}

					$td.eq(index).css('cursor', '').text('')
						.next().children('span:eq(0)').text('')
						.next().text('')
						.next().text('');
					$td.eq(index+2).css('cursor', '')
						.find('div').css('width', '0%')
						.next().text('');

					$td.eq(index+3).removeAttr('id').text('');
					$td.eq(index+3).removeClass('hprice');
				}
			}

			$('#sellTotal').text(nu.cm(sellTotal.toFixed(selected.qtyDspDecPnt), selected.qtyDspDecPnt, true));
			$('#buyTotal').text(nu.cm(buyTotal.toFixed(selected.qtyDspDecPnt), selected.qtyDspDecPnt, true));

			drawPrivateHoga();

			// window.askingTable.drawHoga(data, false);
		}
	} else {
		lastHoga = data;
	}
}

function addContract(data) {
	$('#contractList').prepend(
			$('#CONTRACT_TEMPLATE').html()
			.replace(/{{color}}/g, data.sellBuyRecogCd == 1 ? 'red':'blue')
			.replace(/{{sellBuy}}/g, data.sellBuyRecogCd == 1 ? getExchangeLocaleString('_L_0013'):getExchangeLocaleString('_L_0014'))
			.replace(/{{contractQty}}/g, nu.cm(data.contractQty, selected.qtyDspDecPnt, true))
			.replace(/{{contractPrc}}/g, nu.cm(data.contractPrice, selected.prcDspDecPnt, true))
			.replace(/{{contractAmt}}/g, nu.cm(data.contractPrice*data.contractQty, selected.amtCalcDecPnt, true))
			.replace(/{{contractDt}}/g, du.getCstmFrmt(data.contractDt, 'yyyyMMddHHmmss', 'yyyy-MM-dd HH:mm:ss'))
			.replace(/{{index}}/g, data.contractDt)).children('tr:gt(' + CONTRACT_SIZE + ')').remove();
}

function addNonContract(data) {
	let value = data.body;

	$('#nonContractList').prepend($('#NON_CONTRACT_TEMPLATE').html()
			.replace(/{{tranNo}}/g, data.ifTransactionId)
			.replace(/{{ordNo}}/g, value.ordsvrOrdNo)
			.replace(/{{excNo}}/g, value.exchgsvrOrdNo)
			.replace(/{{itemCode}}/g, value.itemCode)
			.replace(/{{color}}/g, value.sellBuyRecogCd == 1 ? 'red':'blue')
			.replace(/{{sellBuy}}/g, value.sellBuyRecogCd == 1 ? getExchangeLocaleString('_L_0013'):getExchangeLocaleString('_L_0014'))
			.replace(/{{ordType}}/g, value.ordTypeCd == 1 ? getExchangeLocaleString('_L_0037'):getExchangeLocaleString('_L_0038'))
			.replace(/{{ordPrc}}/g, nu.cm(value.ordPrice, selected.prcDspDecPnt, true))
			.replace(/{{contractPrc}}/g, nu.cm(0, selected.prcDspDecPnt, true))
			.replace(/{{nonQty}}/g, nu.cm(value.ordQty, selected.qtyDspDecPnt, true))
			.replace(/{{qty}}/g, nu.cm(0, selected.qtyDspDecPnt, true))
			.replace(/{{ordDt}}/g, du.getCstmFrmt(value.ordDt, 'yyYYMMddHHmmss', 'YY-MM-dd HH:mm:ss')));

	addPrivateHoga(value.ordPrice);
}

function modNonCotnract(data) {
	let target = '[data-exc=' + data.exchgsvrOrdNo + ']';

	//console.log('>>>> ordQty:' + data.ordQty + ', nonQty:' + data.nonContractQty + ', current qty:' + data.contractQty + ', rest:' + (data.ordQty - data.nonContractQty) + ', nu: ' + nu.minus(data.ordQty, data.nonContractQty));

	$('#nonContractList').children(target).children('td')
		.eq(3).text(nu.cm(data.contractPrice, selected.prcDspDecPnt, true))
		.next().text(nu.cm(data.nonContractQty, selected.qtyDspDecPnt, true))
		.next().text(nu.cm(nu.minus(data.ordQty, data.nonContractQty), selected.qtyDspDecPnt, true));
}

function removeNonCotnract(data) {
	/*let target = 'tr[data-tran=' + data.ifTransactionId + ']'
		+ '[data-ord=' + data.body.ordsvrOrdNo + ']'
		+ '[data-exc=' + data.body.exchgsvrOrdNo + ']';*/
	let target = '[data-exc=' + data.exchgsvrOrdNo + ']';
	$('#nonContractList').children(target).remove();

	removePrivateHoga(data.ordPrice);
}

function cancelNonCotnract(data) {
	let target = 'tr[data-tran=' + data.orgIfTransactionId + ']'
		+ '[data-ord=' + data.ordsvrOrgOrdNo + ']'
		+ '[data-exc=' + data.exchgsvrOrgOrdNo + ']';

	$('#nonContractList').children(target).remove();
	removePrivateHoga(data.ordPrice);
}

function setBalance(data) {
	if(selected.coinNo == data.coinNo) {
		selected.toQty = data.availQty;
		$('.toQty').text(nu.cm(selected.toQty, selected.qtyDspDecPnt, true));
	} else if(selected.basicCoinNo == data.coinNo) {
		selected.fromQty = data.availQty;
		$('.fromQty').text(nu.cm(selected.fromQty, selected.prcDspDecPnt, true));
	}

	setAuto();

	let $tr = $('tr[data-no=' + data.coinNo + ']');
	$tr.data('qty', data.availQty);
	$tr.data('avg', data.avgPriceByBtc);

	reDrawBalance(data.coinNo);
}

function reDrawBalance(coinNo) {
	let $tr = $('#tab-group-3').find('tr[data-no=' + coinNo + ']');

	if($tr.length > 0) {
		let $td = $tr.children('td')
			, prc = $tr.data('prc')
			, qty = $tr.data('qty')
			, avg = $tr.data('avg')
			, qtyPnt = $tr.data('qtypnt')
			, prcPnt = $tr.data('prcpnt')
			, profit = prc - avg
			, profitPrc = profit * qty
			, profitRate = ((profit/avg) * 100)
			, mark = profitPrc > 0 ? '+':''
			, color = profitPrc === 0 ? '':profitPrc > 0 ? 'red':'blue';;

		if(coinNo == 10) {
			$td.eq(2).data('sort', qty*prc)
				.children('p:first-child').text( nu.cm(qty, qtyPnt, true) )
		} else {
			$td.eq(2).data('sort', qty*prc)
				.children('p:first-child').text( nu.cm(qty, qtyPnt, true) )
				.next().children('span:eq(0)').text( nu.cm(qty*prc, prcPnt, true) );
			$td.eq(3).data('sort', avg)
				.children('p:first-child').text( nu.cm(avg, prcPnt, true) );

			$td.eq(4).data('sort', profitRate)
			.children('p:first-child').attr('class', color).text( mark + '' + nu.cm(profitRate, 2, true) + '%' )
			.next().find('span:eq(0)').attr('class', color).text( mark + '' + nu.cm(profitPrc, prcPnt, true) );

		}
		//$td.eq(3).find('span:eq(0)').text( nu.cm(qty*avg, prcPnt, true) )

	}
}

function realContract(data, flag) {
	// 상단 정보
	reDrawHeader(data);

	// 호가 사이드
	addRealContract(data);

	// 일별시세
	updateDailySise(data);

	//--coben(2021/07/25)
	// 오른쪽 메뉴 데이터 변경
	//reDrawRightMenu(data, flag);
}

function reDrawHeader(data) {
	let color = '', icon = '', compareHigh = '', compareLow = '', prcUnit;

	// 전일 종가
	if(selected.prevClosePrc != parseFloat(data.prevClosePrice)) {
		selected.prevClosePrc = parseFloat(data.prevClosePrice);
		$('#prevClosePrc').text(nu.cm(data.prevClosePrc, data.prcDspDecPnt));
	}

	// 현재가, 대비
	if(selected.closePrc != parseFloat(data.closePrice)) {
		selected.closePrc = parseFloat(data.closePrice);

		color = selected.closePrc === selected.prevClosePrc ? '':selected.closePrc > selected.prevClosePrc ? 'red':'blue';
		icon = color === '' ? '':color == 'red' ? 'up01':'down01';

		$('#closePrc').text(nu.cm(selected.closePrc, selected.prcDspDecPnt, true))
						.closest('td').attr('class', color);
		$('#daebiRate').text(nu.cm(data.priceDevRate, 2, true) + '%');
		$('#daebiPrc').text(nu.cm(data.priceDevAmt, selected.prcDspDecPnt, true))
						.prev().attr('class', 'rate ' + icon);

		selected.highPrc = parseFloat(data.highPrice);
		compareHigh = selected.highPrc - selected.closePrc;

		$('#highPrc').text(nu.cm(selected.highPrc, selected.prcDspDecPnt, true))
						.attr('class', getColor(selected.highPrc, selected.prevClosePrc))
						//.attr('class', compareHigh === 0 ? '':compareHigh > 0 ? 'red':'blue');

		let plusMinus = selected.highPrc > selected.prevClosePrc ? '+': '';
		$('#todayHigh').text(nu.cm(selected.highPrc, selected.prcDspDecPnt, true))
						.attr('class', getColor(selected.highPrc, selected.prevClosePrc))
						.next().text(plusMinus + getCalPer(selected.highPrc, selected.prevClosePrc, false) + '%')
						.attr('class', getColor(selected.highPrc, selected.prevClosePrc));

		selected.lowPrc = parseFloat(data.lowPrice);
		compareLow = selected.lowPrc - selected.closePrc;

		$('#lowPrc').text(nu.cm(selected.lowPrc, selected.prcDspDecPnt, true))
						.attr('class', getColor(selected.lowPrc, selected.prevClosePrc))
						//.attr('class', compareLow === 0 ? '':compareLow > 0 ? 'red':'blue');

		plusMinus = selected.lowPrc > selected.prevClosePrc ? '+': '';
		$('#todayLow').text(nu.cm(selected.lowPrc, selected.prcDspDecPnt, true))
						.attr('class', getColor(selected.lowPrc, selected.prevClosePrc))
						.next().text(plusMinus + getCalPer(selected.lowPrc, selected.prevClosePrc, false) + '%')
						.attr('class', getColor(selected.lowPrc, selected.prevClosePrc));
	}

	// 거래량, 거래대금
	//console.log('hoga header: tradeVol:' + nu.cm(data.tradeVol, selected.volDspDecPnt, true) + ', tradeAmt:' + nu.cm(data.tradeAmt, selected.amtDspDecPnt, true));
	$('.tradeVol').text(nu.cm(data.tradeVol, selected.volDspDecPnt, true));
	$('.tradeAmt').text(nu.cm(data.tradeAmt, selected.amtDspDecPnt, true));

	if(selected.basicCoinNo === 10) {
		prcUnit = getKrwUnit(data.tradeAmt);
	} else {
		prcUnit = {};
		prcUnit.price = nu.cm(data.tradeAmt, selected.amtDecPnt, true);
		prcUnit.unit = selected.fromUnit;
	}

	$('.tradeAmt').text(prcUnit.price);
	$('.tradeAmtUnit').text(prcUnit.unit);

	// 52주
	if(selected.week52High != parseFloat(data.weeks52High)) {
		selected.week52High = parseFloat(data.weeks52High);
		$('#week52high').text(nu.cm(selected.week52High, selected.prcDspDecPnt, true))
						.attr('class', getColor(selected.week52High, selected.closePrc))
						.next().text(du.getCstmFrmt(data.weeks52HighDay, 'yyyyMMdd', '(yyyy-MM-dd)'));
	}

	if(selected.week52Low != parseFloat(data.weeks52Low)) {
		selected.week52Low = parseFloat(data.weeks52Low);
		$('#week52low').text(nu.cm(selected.week52Low, selected.prcDspDecPnt, true))
						.attr('class', getColor(selected.lowPrc, selected.closePrc))
						.next().text(du.getCstmFrmt(data.weeks52LowDay, 'yyyyMMdd', '(yyyy-MM-dd)'));
	}
}

function addRealContract(data) {
	let contractRecogCd = parseFloat(data.contractRecogCd)
		, sellBuy = contractRecogCd === 1 ? 'red':'blue'
		, daebiPrc = parseFloat(data.priceDevAmt)
		, mark = daebiPrc > 0 ? '+':''
		, icon = daebiPrc === 0 ? '':daebiPrc > 0 ? 'up01':'down01'
		, color = daebiPrc === 0 ? '':daebiPrc > 0 ? 'red':'blue'
		, qty = nu.cm(data.contractQty, selected.qtyDspDecPnt, true)
		, innerHtml = '';

	let lPrc, lDaebiRate, lDaebiPrc, lPrcH = '';
	let ifTransactionId = data.ifTransactionId;

	if(ifTransactionId != null){
		if(ifTransactionId.split('_')[0] == 'AT' || ifTransactionId.split('_')[0] == 'AUTO') {
			lPrc = 'AM';
			lDaebiRate = '';
			lDaebiPrc = '';
			icon = 0;
			lPrcH = 'AM';
		}
		else if(ifTransactionId.split('_')[0] == 'SLFTRAD') {
			lPrc = 'AM';
			lDaebiRate = '';
			lDaebiPrc = '';
			icon = 0;
			lPrcH = 'AM';
		}
		else {
			lPrc = nu.cm(data.contractPrice, selected.prcDspDecPnt, true) + '(' + selected.fromUnit + ')';
			lDaebiRate = mark + parseFloat(data.priceDevRate).toFixed(2) + '%';
			lDaebiPrc = nu.cm(daebiPrc, selected.prcDspDecPnt, true);
			lPrcH = nu.cm(data.contractPrice, selected.prcDspDecPnt, true);
		}
	} else {
		lPrc = nu.cm(data.contractPrice, selected.prcDspDecPnt, true) + '(' + selected.fromUnit + ')';
		lDaebiRate = mark + parseFloat(data.priceDevRate).toFixed(2) + '%';
		lDaebiPrc = nu.cm(daebiPrc, selected.prcDspDecPnt, true);
		lPrcH = nu.cm(data.contractPrice, selected.prcDspDecPnt, true);
	}

	innerHtml = $('#REAL_CONTRACT_TEMPLATE').html()
	.replace(/{{color}}/g, sellBuy)
	.replace(/{{prc}}/g, lPrcH)
	.replace(/{{size}}/g, calLetterSpacing(qty))
	.replace(/{{qty}}/g, qty)

	$('#realContract').prepend(innerHtml).find('tr:last-child').remove();

	//console.log('real contract:' + 'price:' + data.contractPrice + ', qty:' + data.contractQty + ', amtCalcDecPnt:' + selected.amtCalcDecPnt + ',amt: ' + parseFloat(data.contractPrice)*parseFloat(data.contractQty));

	innerHtml = $('#REAL_CONTRACT_LIST_TEMPLATE').html()
		.replace(/{{index}}/g, data.contractDt)
		.replace(/{{dt}}/g, du.getCstmFrmt(data.contractDt, 'yyyyMMddHHmmss', 'yyyy-MM-dd<br>HH:mm:ss'))
		.replace(/{{prc}}/g, lPrc)
		.replace(/{{qty}}/g, nu.cm(data.contractQty, selected.qtyDspDecPnt, true))
		.replace(/{{daebiRate}}/g, lDaebiRate)
		.replace(/{{daebiPrc}}/g, lDaebiPrc)
		.replace(/{{icon}}/g, icon)
		.replace(/{{color}}/g, color)
		.replace(/{{contractAmt}}/g, nu.cm(parseFloat(data.contractPrice)*parseFloat(data.contractQty), selected.amtCalcDecPnt, true))
		.replace(/{{sellBuyColor}}/g, sellBuy)
		.replace(/{{sellBuy}}/g, contractRecogCd === 1 ? getExchangeLocaleString('_L_0013'):getExchangeLocaleString('_L_0014'))
		.replace(/{{toUnit}}/g, selected.toUnit)
		.replace(/{{fromUnit}}/g, selected.fromUnit)

	$('#realContractList').prepend(innerHtml).children('tr:gt(' + REAL_SIZE + ')').remove();

	changeDocumentTitle(data.contractPrice);

}

function updateDailySise(data) {
	let $tbody = $('#daily')
		, dt = data.contractDt.substr(0, 8)
		, $tr = $tbody.children('tr[data-index=' + dt + ']')
		, template = $('#DAILY_TEMPLATE').html()
		, devRecogCd = parseFloat(data.closePrice) - parseFloat(data.prevClosePrice)
		, color = devRecogCd === 0 ? '':devRecogCd > 0 ? 'red':'blue'
		, icon = devRecogCd === 0 ? '':devRecogCd > 0 ? 'up01':'down01'
		, mark = devRecogCd === 0 ? '':devRecogCd > 0 ? '+':'-'
		, contractQty = data.contractQty
		, contractPrice = data.contractPrice
		, contractPriceAmt = contractQty * contractPrice;

		if($tr.length > 0) {
			let $td = $tr.children('td')
			, td4data = parseFloat($td.eq(4).text().replace(/,/g, '')) + parseFloat(contractQty)
			, td5data = parseFloat($td.eq(5).text().replace(/,/g, '')) + parseFloat(contractPriceAmt);
			$td.eq(1).text(nu.cm(data.closePrice, selected.prcDspDecPnt, true))
			.next().attr('class', color).text(mark + ' ' + nu.cm(Math.abs(data.priceDevRate), 2, true) + '%')
			.next().attr('class', color).children('span:eq(0)').attr('class', 'rate ' + icon)
			.next().text(nu.cm(data.priceDevAmt, selected.prcDspDecPnt, true));
			$td.eq(4).attr('class', color).text(nu.cm(td4data, selected.volDspDecPnt, true))
			.next().text(nu.cm(td5data, selected.amtDecPnt));
		} else {
			$tbody.prepend(template
					.replace(/{{index}}/g, dt)
					.replace(/{{dt}}/g, du.getCstmFrmt(dt, 'yyyyMMdd', 'yyyy-MM-dd'))
					.replace(/{{prc}}/g, nu.cm(data.closePrice, selected.prcDspDecPnt))
					.replace(/{{daebiRate}}/g, mark + ' ' + nu.cm(Math.abs(data.priceDevRate), 2, true) + '%')
					.replace(/{{daebiPrc}}/g, nu.cm(data.priceDevAmt, selected.prcDspDecPnt, true))
					.replace(/{{icon}}/g, icon)
					.replace(/{{color}}/g, color)
					.replace(/{{tradeVol}}/g, nu.cm(contractQty, selected.volDspDecPnt, true))
					.replace(/{{tradeAmt}}/g, nu.cm(contractPriceAmt, selected.amtDecPnt, true))
			);
		}
}

function reDrawRightMenu(data, flag) {
	let $tr = $('#tab-group-3').find('tr[data-code=' + data.itemCode + ']');


	if($tr.length > 0) {
		let $td = $tr.children('td')
			, coinNo = $tr.closest('tbody').data('coinno')
			, mktNm = $tr.closest('tbody').data('name')
			, prcPnt = $tr.data('prc')
			, amtPnt = $tr.data('amt')
			, sellBuy = parseFloat(data.contractRecogCd) === 1 ? 'up':'down'
			, priceDevAmt = parseFloat(data.priceDevAmt)
			, upDown = priceDevAmt == 0 ? '':priceDevAmt > 0 ? 'red':'blue'
			, mark =  priceDevAmt == 0 ? '':priceDevAmt > 0 ? '+':'-'
			, prcUnit = {};

		sellBuy = flag === undefined ? sellBuy:'';

		//console.log('reDrawRightMenu: tradeAmt=' + data.tradeAmt + ', amtDecPnt=' + data.amtDecPnt);

		$td.eq(2).data('sort', parseFloat(data.closePrice)).children('p')
					.attr('class', 'border ' + sellBuy + ' ' + upDown)
					.text(nu.cm(data.closePrice, prcPnt, true));
		$td.eq(3).data('sort', parseFloat(data.priceDevRate)).children('p:eq(0)')
					.attr('class', upDown).text(mark + ' ' + nu.cm(Math.abs(data.priceDevRate), 2, true) + '%')
					.next().attr('class', 'daebiPrc ' + upDown).text(mark + ' ' + nu.cm(Math.abs(priceDevAmt), prcPnt, true));

		if(coinNo === 10) {
			prcUnit = getKrwUnit(data.tradeAmt);

			$td.eq(4).data('sort', parseFloat(data.tradeAmt))
				.find('span:eq(0)').text(prcUnit.price).next().text(prcUnit.unit);
		} else {
			let closePrc = $('tr[data-code=' + mktNm + 'KRW]').children('td:eq(2)').data('sort')
			, prcUnit = {};

			closePrc = nu.nvl(closePrc);
			prcUnit = getKrwUnit(data.tradeAmt * closePrc);

			$td.eq(4).data('sort', parseFloat(data.tradeAmt))
				.find('p:first-child').text(nu.cm(data.tradeAmt, amtPnt, true))
				.next().children('span:eq(0)').text(prcUnit.price)
				.next().text(prcUnit.unit);
		}

		setTimeout(function() {
			$td.eq(2).children('p').attr('class', 'border ' + upDown);
		}, 300);
	}
}

var ord = {
	// 확인, 취소 버튼 있는 alert
	sell : function(text, title, callBackFun) {
		var target = '#lrtSell';
		var $this = $(target);

		lrt.center(target);

		title = title === undefined ? '':title === null ? '':title;
		text = text === undefined ? '':text === null ? '':text;

		$this.find('.alert-title').html(title);
		$this.find('.alert-message').html(text);
		$this.find('.top-close').off().on('click', function() {
			$this.find('.top-close').off();
			$this.find('button').off();
			$this.hide();
		})
		$this.find('#lrtSellCncl').off().on('click', function() {
			$this.find('.top-close').off();
			$this.find('button').off();
			$this.hide();
		});
		$this.find('#lrtSellOk').off().on('click', function() {
			$this.find('.top-close').off();
			$this.find('button').off();
			$this.hide();

			if(typeof callBackFun === 'function') { callBackFun(); }
		});

		$this.on('keyup', function(key) {
			if(key.keyCode==13) { 
				$this.find('.top-close').off();
				$this.find('button').off();
				$this.hide();
	
				if(typeof callBackFun === 'function') { callBackFun(); }
			}
		});

		$this.show();
		$this.focus();
	},
	buy : function(text, title, callBackFun) {
		var target = '#lrtBuy';
		var $this = $(target);

		lrt.center(target);

		title = title === undefined ? '':title === null ? '':title;
		text = text === undefined ? '':text === null ? '':text;

		$this.find('.alert-title').html(title);
		$this.find('.alert-message').html(text);
		$this.find('.top-close').off().on('click', function() {
			$this.find('.top-close').off();
			$this.find('button').off();
			$this.hide();
		})
		$this.find('#lrtBuyCncl').off().on('click', function() {
			$this.find('.top-close').off();
			$this.find('button').off();
			$this.hide();
		});
		$this.find('#lrtBuyOk').off().on('click', function() {
			$this.find('.top-close').off();
			$this.find('button').off();
			$this.hide();

			if(typeof callBackFun === 'function') { callBackFun(); }
		});

		$this.off().on('keydown', function(key) {
			if(key.keyCode==13) { 
				$this.find('.top-close').off();
				$this.find('button').off();
				$this.hide();
	
				if(typeof callBackFun === 'function') { callBackFun(); }
			}
		});

		$this.show();
		$this.focus();
	},
	// 전달 받은 selector 가운데 정렬
	center : function(i) {
		var height = $(i).height();
		height = ($(window).height()-height)/2;
		var top = height < 0 ? 0:height;
		$(i).css({"top":top});
	}
}

function calLetterSpacing(value) {
	let length = value.length;
	return length < 14 ? 0:length < 15 ? -0.5:length < 16 ? 0:-1;
}
