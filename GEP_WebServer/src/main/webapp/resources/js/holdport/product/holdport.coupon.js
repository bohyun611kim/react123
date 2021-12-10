var _favorite = '_favorite';
var amtFlag = true;

function getUserCouponHistory() {

	$('#coupon-purchase-history-list-tbody').empty();

	ajaxOption({
		url:'/site/product/coupon/selectUserCouponHistoryList',
		data: {},
		success:function(data) {
			drawUserCouponHistoryList(data);
		}
	});
}

function getUserCouponAmount() {

	ajaxOption({
		url:'/site/product/coupon/selectUserCouponAmount',
		data: {},
		success:function(data) {
			$('#total_coupon_amount').text( nu.cm(getParam(data, 'COUPON_AMOUNT', '0'), 0) );
		}
	});
}

function setUserCouponAmount(amt)
{
	$('#total_coupon_amount').text( nu.cm(amt, 0) );
}

function drawUserCouponHistoryList(data)
{

	let length = data.length;
	let listHtml = '', listTemplate = $('#template-courpon-purchase-history-list').html();
  
	for(let i=0; i<length; i++) {
        var value				        = data[i];
        var orderDateTime               = du.getCstmFrmt(getParam(value, 'ORD_DT', '0'), 'yyyyMMddHHmmssSSSSSS', 'MM-dd HH:mm:ss');    // 주문 접수시간
        var orderPrice                  = getParam(value, 'ORD_QTY', '0');                              // 주문 가격
		var orderQty                    = nu.plus(getParam(value, 'ORD_QTY', '0'), getParam(value, 'ORD_BONUS_QTY', '0'));
		
        listHtml += listTemplate
            .replace(/{{PurchaseDateTime}}/g, orderDateTime)
            .replace(/{{CouponPrice}}/g, nu.cm(orderPrice, 0))
            .replace(/{{CouponQty}}/g, nu.cm(orderQty, 0))
            ;
    }
	$('#coupon-purchase-history-list-tbody').append(listHtml);	
}

$(document).ready(function() {

	

	getUserCouponHistory();
});

function doBuyCoupon(target, amount, discount) {

	let message = nu.cm(amount, 0) + '원 (' + nu.cm(discount, 2, true) + '% 추가 지급) 쿠폰을 구매하시겠습니까?<br><br>구매 후 환불되지 않습니다.';

	lrt.confirm(message, "쿠폰 구매", function() {

		var p = {
			ordQty:amount, 
			ordBonusQty: amount*discount/100.0
		}

		ajaxOption({
			url:'/site/product/coupon/doBuyCoupon',
			data:p,
			success:function(data) {
				if(data.rtnCd != 0) {lrt.client(wordbook[data.rtnCd], '');}
			}
		});
	});
}


function doLogin() {
	location.href='/site/login';
}

function doRegister() {
	location.href='/site/register';
}






