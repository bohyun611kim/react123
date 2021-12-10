<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/taglib/taglib.jsp" %>

<%!
	boolean localeMapValid = false;
%>

<script src="/resources/js/toastr/toastr.min.js"></script>


<script>
	var couponAmount = '${couponAmount}';
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
	
	console.log("coupon privateLister+++", data, flag);

	if(document.visibilityState == 'visible') {
		data = JSON.parse(data);
		
		if(parseFloat(data.body.autoMiningYn) === 1 && data.msgCode != 501 && data.msgCode != 108) {return;}
		
		switch(data.msgCode) {
		case 103:
			lrt.client(wordbook[data.body.ResCode]);
			break;
		case 104:
			//주문성공
			break;
		case 105:
			//미체결내역 존재
			break;
		case 108:
			//체결: 쿠폰 구매: data.body.contractQty

			if(flag === undefined) {
				let message = '구매일시 : ' + du.getCstmFrmt(data.body.contractDt, 'yyyyMMddHHmmss', 'yyyy-MM-dd HH:mm:ss') + '<br>'
				+ '구매금액 : ' + nu.cm(data.body.ordQty, 0) + '<br>'
				+ '쿠폰수량 : ' + nu.cm(nu.plus(data.body.ordQty, data.body.ordBonusQty), 0) + '<br>'
					+ '쿠폰을 구매하였습니다.';

				getUserCouponHistory();
				//getUserCouponAmount();

				lrt.client(message, "쿠폰 구매 알림");
			}

			break;
		case 203:
			lrt.client(wordbook[data.body.ResCode]);
			break;
		case 205:
			lrt.client(wordbook[data.body.ResCode]);
			break;
		case 206:
			//주문취소
			break;
		case 501:
			setUserCouponAmount(data.body.possQty);
			break;
		}
	} else {
		privatePush.push(data);
	}
}
</script>
</c:if>


<!--컨텐츠 내용 영역 시작-->
<main id="content" role="main" class="main">


<c:if test="${sessionScope.userInfo.loginYn == 1}">
<p align=center>
Your coupon amount is <span id="total_coupon_amount" class="asset-value">0</span> KRW.<br><br>
</p>
</c:if>

<p align=center>
<a style="cursor:pointer;">
	<c:choose>
		<c:when test="${sessionScope.userInfo.loginYn == 1}">
			<button type="button" class="red" onclick="doBuyCoupon(this, 10000, 10.00);">10,000 KRW (extra 10%!)</button><br><br>
			<button type="button" class="red" onclick="doBuyCoupon(this, 20000, 10.00);">20,000 KRW (extra 10%!)</button><br><br>
			<button type="button" class="red" onclick="doBuyCoupon(this, 50000, 20.00);">50,000 KRW (extra 20%!)</button><br><br>
			<button type="button" class="red" onclick="doBuyCoupon(this, 100000, 20.00);">100,000 KRW (extra 20%!)</button>
		</c:when>
		<c:otherwise>
			<button class="red" onclick="doLogin();">Login</button>
		</c:otherwise>
	</c:choose>
</a>
</p>


<c:if test="${sessionScope.userInfo.loginYn == 1}">
<p align=center>
<br><br>

<div>
	Your coupon purchase history: <br>
	<table class="table1 line">
		<thead>
			<tr>
				<th>구매일자</th>
				<th>구매금액</th>
				<th>쿠폰수량</th>
			</tr>
		</thead>
		<tbody id="coupon-purchase-history-list-tbody">
			<!-- 1개의 Row : Start -->
			<tr>
				<td class="alCenter"></td>
				<td class="alRight"></td>
				<td class="alRight"></td>
			</tr>
			<!-- //1개의 Row : End -->
		</tbody>
	</table>
</div>

<br><br>

</p>
</c:if>


</main> 
<!--컨텐츠 내용 영역 끝-->


<script src="/resources/js/holdport/product/holdport.coupon.js?v=<spring:message code="holdport.coupon.js.version"/>"></script>

<script id="template-courpon-purchase-history-list" type="text/template">
    <tr>
        <td class="alCenter">{{PurchaseDateTime}}</td>
        <td class="alCenter">{{CouponPrice}}</td>
        <td class="alCenter">{{CouponQty}}</td>
    </tr>
</script>

<script>

$(document).ready(function() {

	<c:if test="${sessionScope.userInfo.loginYn == 1}">
		$('#total_coupon_amount').text( nu.cm(parseFloat(couponAmount), 0) );
	</c:if>


})

</script>


