<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/taglib/taglib.jsp" %>

<script src="/resources/js/common/paging.js"></script>

<body>
<!--컨텐츠 내용 영역 시작-->
<section>
	<!--체결내역 전체 내용 시작-->
	<article class="bgwhite">

		<h1 class="sub-title">Closed Contract List</h1>
		<div class="content-item">
			<div class="search-option-group">
				<div class="select-box">
					<label for="market">Select Market</label>
					<select id="market">
						<option value="ALL">ALL</option>
 						<c:forEach var="mkt" items="${marketList}">
							<option value="${mkt.getMkt_id().split('_')[1]}">${mkt.getMkt_id().split('_')[1]} Market</option>
						</c:forEach>
					</select>
				</div>
				<div class="select-box">
					<label for="coin">Select Coin</label>
					<select id="coin">
						<option>Select Coin</option>
					</select>
				</div>
				<div class="option-right">
					<span class="option-checks">
						<span class="checks-item">
							<label>
								<input type="checkbox" id="buy" checked><em></em>Buy
							</label>
						</span>
						<span class="checks-item">
							<label>
								<input type="checkbox" id="sell" checked><em></em>Sell
							</label>
						</span>
					</span>
					<div class="calendar-group">
						<input type="text" id="startDate" value="" readonly="readonly"><!--'%=today%'--> 
						
						<span>~</span>
						<input type="text" id="endDate" value="" readonly="readonly">
						<div class="fl-right">
							<button href="" id="search" class="bggray">Search</button>
						</div>
					</div>
				</div>
			</div>

			<!--체결내역 테이블 시작-->
			<div class="tblType02">
				<table>
					<colgroup>
						<col style="width: 120px">
						<col style="width: 180px">
						<col>
						<col style="width: 70px">
						<col>
						<col>
						<col style="width: 120px">
						<col>
						<col>
						<col style="width: 100px">
					</colgroup>
					<thead>
						<tr>
							<th>Traded Dt</th>
							<th>Order No.</th>
							<th>Coin</th>
							<th>Sell/buy</th>
							<th class="right">Order Price</th>
							<th class="right">Order Qty</th>
							<th>Order Dt</th>
							<th class="right">Traded Price</th>
							<th class="right">Traded Qty</th>
							<th class="right">Fees</th>
						</tr>
					</thead>
					<tbody>
						<!--최대 20개 노출 ?-->
						<tr>
							<td colspan="10">You have no Trading History </td>
						</tr>
					</tbody>
				</table>

				<div class="fl-right mgt20">Total <b id="count" class="blue">0</b> rows retrieved</div>

				<!-- 페이징 : Start -->
				<ul class="pagination"></ul>
				<!-- //페이징 : End -->
			</div>
			<!--체결내역 테이블 끝-->

		</div>

	</article>
	<!--체결내역 전체 내용 끝-->
</section>
<!--컨텐츠 내용 영역 끝-->


<script>
var paging;

// $(function() { 아래와 같음
$(document).ready(function() {
	
	$("#market").one('click', function() {
		let $this = $(this);
		$this.prev().text($this.val());
		
		$("#market").trigger('change');
	})
	
	// 날짜 세팅
	var weekAgo = new Date();
	weekAgo.setDate( weekAgo.getDate() -7 );
	$('#startDate').val($.datepicker.formatDate('yy.mm.dd', weekAgo ));
	$('#endDate').val($.datepicker.formatDate('yy.mm.dd', new Date() ));

    //달력
   	$("#endDate").datepicker({
   		inline: true,
   		dateFormat: 'yy.mm.dd',
   		maxDate: $.datepicker.formatDate('yy.mm.dd', new Date()),
   		
   		onSelect: function() {
   			$("#startDate").datepicker("option","maxDate", $("#endDate").datepicker('getDate'))
   		
   			if ( $.datepicker.formatDate('yymmdd', $("#endDate").datepicker('getDate')) 
   					< $.datepicker.formatDate('yymmdd', $("#startDate").datepicker('getDate')) ) {

       			$('#startDate').val($("#endDate").datepicker('getDate'));
   			}
   		}
   	});
   	
   	$("#startDate").datepicker({
		inline: true,
		dateFormat: 'yy.mm.dd',
		maxDate: $("#endDate").datepicker('getDate')
   	});

    //label을 사용한 셀렉트
    var selectTarget = $('.select-box select');
    selectTarget.change(function() {
        var select_name = $(this).children('option:selected').text();
        $(this).siblings('label').text(select_name);
        $('label').addClass('on');
    });
	
   	// Paging
   	paging = Pu.init({
   		count: 15,
   		PageCount: 5,
   		draw: doSearch,
   		button: '#search',
   		paginate: '.pagination',
   		initCllBck: false,
   		type: 'search',
   		totalText: '#count',
 		initEventFun: doSearch
   	});
   	
   	
   	$('#market').on('change', function() {
   		
   		var p = {market:$("#market").val()};
   		
   		$("#coin option").remove();
   		$("#coin").append("<option value='ALL'>ALL</option>");
   		$("#coin").find('option[value="ALL"]').prop("selected", true);
   		$("#coin").prev().text('ALL');
   		
   		if( p.market == 'ALL') 
   			return ;
   		
   		ajax('/coinis/tradingHistory/getItemList', p, function(itemList) {
   			
   			var items;
   				
   			$(itemList).each(function() {
   				items += "<option value='"+this.item_nm+"'>"+this.item_nm+"</option>";
   			});
   			
   			$("#coin").append(items);
   			// $("select[id='coin']").prev().text('ALL');
   		});
   	});
	   	
});


$('#endDate').on('dp.change', function() {

	$("#startDate").datepicker({
		maxDate: $("#endDate").datepicker('getDate')
	});
	
	if ( $("#endDate").datepicker('getDate') > $("#startDate").datepicker('getDate'))
		$('#endDate').val($.datepicker.formatDate('yy.mm.dd', $("#startDate").datepicker('getDate')));
});

function doSearch(i, done) {
	var p = {};
		
	p["market"] = $("#market option:selected").val();
	p["item"] = $("#coin option:selected").val();
	p["buy"] = $('#buy').is(':checked') ? "1":"0";
	p["sell"] = $('#sell').is(':checked') ? "1":"0";
	p["startDate"] = $.datepicker.formatDate('yymmdd', $("#startDate").datepicker('getDate'));
	p["endDate"] = $.datepicker.formatDate('yymmdd', $("#endDate").datepicker('getDate'));
	p["startIndex"] = i;
	p["queryDataNum"] = 15;
	
	if(p.item == 'Select Coin') {
		lrt.client('Select Coin/Market');
		return false;
	} else if( !$('#buy').is(':checked') && !$('#sell').is(':checked') ) {
		lrt.client('Select Buy/Sell');
		return false;
	} else {
		
		ajaxOption({
			url: '/coinis/tradingHistory/doSearch',
			data: p,
			success: draw,
			done: done
		})
	}
}

function draw(data) {
		var contractedList = data.data;
		var dataTemplate = $('#template-contracted-list').html();
		var rows = "";
		var colorByType ='';
		var sell_buy_recog_cd = '';
		
		if(contractedList.length > 0) {
			$(contractedList).each(function() {
				
				if(this.sell_buy_recog_cd == "1") {
					colorByType = 'red'; 
					sell_buy_recog_cd = 'Sell';
				} else {
					colorByType = 'green';
					sell_buy_recog_cd = 'Buy';
				}
				
				rows += dataTemplate.replace(/{{contract_dt}}/g, getCstmFrmt(this.contract_dt, "yyyyMMddHHmmss", "MM/dd HH:mm:ss"))
									.replace(/{{ord_seq_no}}/g, this.ord_seq_no)
									.replace(/{{item_nm}}/g, this.item_nm)
									.replace(/{{colorBytype}}/g, colorByType)
									.replace(/{{sell_buy_recog_cd}}/g, sell_buy_recog_cd)
									.replace(/{{ord_price}}/g, nwc(this.ord_price.toFixed(this.price_dsp_decimal_pnt)))
									.replace(/{{ord_qty}}/g, nwc(this.ord_qty.toFixed(this.qty_dsp_decimal_pnt)))
									.replace(/{{ord_dt}}/g, getCstmFrmt(this.ord_dt, "yyyyMMddHHmmss", "MM/dd HH:mm:ss"))
									.replace(/{{contract_price}}/g, nwc(this.contract_price.toFixed(this.price_dsp_decimal_pnt)))
									.replace(/{{contract_qty}}/g, nwc(this.contract_qty.toFixed(this.qty_dsp_decimal_pnt)))
									.replace(/{{trade_fee_amt}}/g, nwc(parseFloat(this.trade_fee_amt)))
			});
			$('tbody').html(rows);
		} else 
			$('tbody').html($('#template-list-empty').html());
}

</script>

<Script id="template-contracted-list" type="text/template">
	<tr>
		<td>{{contract_dt}}</td>
 		<td>{{ord_seq_no}}</td>
 		<td>{{item_nm}}</td>
 		<td class="{{colorBytype}}">{{sell_buy_recog_cd}}</td>
 		<td class="right">{{ord_price}}</td>
 		<td class="right">{{ord_qty}}</td>
 		<td>{{ord_dt}}</td>
 		<td class="right">{{contract_price}}</td>
 		<td class="right">{{contract_qty}}</td>
 		<td class="right">{{trade_fee_amt}}</td>
	</tr>
</Script>

<Script id="template-list-empty" type="text/template">
	<tr>
		<td colspan="10">You have no Trading History </td>
	</tr>
</Script>

</body>