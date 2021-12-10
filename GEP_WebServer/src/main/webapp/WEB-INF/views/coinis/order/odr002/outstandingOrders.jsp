<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/taglib/taglib.jsp" %>

<script src="/resources/js/common/paging.js"></script>

<!--컨텐츠 내용 영역 시작-->
<section>
	<!--미체결내역 전체 내용 시작-->
	<article class="bgwhite">

		<h1 class="sub-title">Outstanding Orders</h1>
		<div class="content-item">
			
			<!--체결내역 테이블 시작-->
			<div class="tblType02 mgt20">
				<table>
					<colgroup>
						<col style="width: 150px">
						<col style="width: 180px">
						<col>
						<col style="width: 80px">
						<col>
						<col>
						<col>
						<col>
						<col style="width: 90px">
					</colgroup>
					<thead>
						<tr>
							<th>Order Dt</th>
							<th>Order No.</th>
							<th>Coin</th>
							<th>SELL, BUY</th>
							<th class="right">Order Price</th>
							<th class="right">Order Qty</th>
							<th class="right">Traded Qty</th>
							<th class="right">Outstanding Qty</th>
							<th>Cancel</th>
						</tr>
					</thead>
					<tbody>
						<!--최대 20개 노출 ?-->
					</tbody>
				</table>

				<div class="fl-right mgt20">Total <b id="count" class="blue">0</b> rows retrieved</div>

				<!-- 페이징 : Start -->
				<ul class="pagination">
					<li><a class="btn-page first"></a></li>
					<li><a class="btn-page prev"></a></li>
					<li><a href="#" class="active">1</a></li><!-- 선택된 번호의 a 태그에 acitve 클래스 추가 -->
					<li><a href="#">2</a></li>
					<li><a href="#">3</a></li>
					<li><a href="#">4</a></li>
					<li><a href="#">5</a></li>
					<li><a class="btn-page next"></a></li>
					<li><a class="btn-page last"></a></li>
				</ul>
				<!-- //페이징 : End -->
			</div>
			<!--체결내역 테이블 끝-->

		</div>

	</article>
	<!--미체결내역 전체 내용 끝-->
</section>
<!--컨텐츠 내용 영역 끝-->

<script>
var size = ${size};

var window;		// 화면에 대한 변수

$(document).ready(function() {

	window = Pu.init({
		total:size,							// 총 데이터 수
		count:10,							// 한 페이지에 보여질 최대 데이터 수	
		pageCount:5,						// block 안의 page 갯수
		draw:OutstandingOrder,				// 화면 그릴 function
		paginate:'.pagination',				// page 그릴 selector
		initCllBck:true,					// boolean 값 (미입력시 false)
		initEventFun:OutstandingOrder
	});
})	

function OutstandingOrder(i, done) {
	var pagenum = {pageNum:i};			// OutstandingOrdersController.java의 selectNonContractList의 requst의 parameter와 일치해야한다.(=pageNum:i)의 값이 i에 저장된다.
	ajaxOption({
			url:'/coinis/outstandingOrders/list', 
			data:pagenum, 
			success: function(result) {

				var listTemplate = $('#template-OutstandingOrder-list').html();
				var rows = "";
				console.log(result);
				
				if(result.data.length > 0) {
						
					$(result.data).each(function(){
							
						rows += listTemplate.replace(/{{orderDt}}/g, getCstmFrmt(this.orderDt, "yyyyMMddHHmmss", "yyyy-MM-dd HH:mm:ss"))
											.replace(/{{orderNo}}/g, this.orderNo)
											.replace(/{{itemCode}}/g, this.itemCode)
											.replace(/{{color}}/g, this.sellBuyCd == 1 ? 'green' : 'red')
											.replace(/{{sellBuyCd}}/g, this.sellBuyCd == 1 ? 'Buy' : 'Sell')
											.replace(/{{orderPrc}}/g, nu.cm(this.orderPrc, this.prcDecPnt))							
											.replace(/{{orderQty}}/g, nu.cm(this.orderQty, this.qtyDecPnt))
											.replace(/{{contractQty}}/g, nu.cm(this.contractQty, this.qtyDecPnt))
											.replace(/{{nonContractQty}}/g, nu.cm(this.nonContractQty, this.qtyDecPnt))
											.replace(/{{orderNo}}/g, this.orderNo)
											.replace(/{{itemCode}}/g, this.itemCode)
						
					});				
					$('tbody').html(rows);
				} else {
					$('tbody').html($('template-list-empty').html());
				}
				$('#count').html(result.size);
			}, done:done
	})
}		
</script>
<Script id="template-OutstandingOrder-list" type="text/template">
	<tr>
		<td>{{orderDt}}</td>
		<td class="left">{{orderNo}}</td>
		<td>{{itemCode}}</td>
		<td class={{color}}>{{sellBuyCd}}</td>
		<td class="right">{{orderPrc}}</td>
		<td class="right">{{orderQty}}</td>
		<td class="right">{{contractQty}}</td>
		<td class="right">{{nonContractQty}}</td>
		<td><button onclick='cancel({{orderNo}}, "{{itemCode}}")' class="bggray">Cancel</button></td>
	</tr>
</script>
<Script id="template-list-empty" type="text/template">
	<tr>
		<td colspan="9">You have no Outstanding Orders</td>
	</tr>
</Script>
<script>
function cancel(orderNo, itemCode){
	ajaxOption({
		url:'/coinis/outstandingOrders/cancelOrder', 
		data:{ordNo : orderNo, itemCode : itemCode},
		success: function(result) {
			alert(result);
		}	
	})
}
</script>
