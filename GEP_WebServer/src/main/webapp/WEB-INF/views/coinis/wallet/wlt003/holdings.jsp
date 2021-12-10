<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/taglib/taglib.jsp" %> 

<!--컨텐츠 내용 영역 시작-->
<section>
	<!--자산보유현황 전체 내용 시작-->
	<article class="bgwhite">

		<h1 class="sub-title">
			<ul>
				<li>State of Possession</li>
			</ul>
		</h1>
		<div class="content-item">
			<div class="mgb20">
				<div>
					<ul class="mycoin">
						<li>Estimated Value ：<strong><fmt:formatNumber value="${estimatedValue.estimated_by_bc}" pattern="#,##0" minFractionDigits="0" maxFractionDigits="4"/> BTC
											/ &#8361;<fmt:formatNumber value="${estimatedValue.estimated_by_currency}" pattern="#,##0" minFractionDigits="0" maxFractionDigits="4"/></strong></li>
						<li>Daily Withdrawal Limit ：<strong> ${dailyLimitQty.daily_limit_qty} BTC </strong></li>
						<li>In Use ：<strong> <fmt:formatNumber value="${totalInUse}" pattern="#,##0" minFractionDigits="0" maxFractionDigits="4"/> BTC </strong></li>
					</ul>
				</div>
			</div>

			<!--보유 코인 테이블 시작-->
			<div class="tblType02">
				<h1>
					<strong>Possession Coin Status</strong>
					<div class="sch">
						<input type="text" class="serch">
					</div>
				</h1>
				
				<table id="table">
					<colgroup>
						<col style="width: 110px;">
						<col>
						<col>
						<col>
						<col>
						<col style="width: 280px">
					</colgroup>
					<thead>
						<tr>
							<th>Assets Held</th>
							<th class="right">Possession quantity</th>
							<th class="right">Available quantity</th>
							<th class="right">Quantity during transaction</th>
							<th class="right">BTC converted quantity</th>
							<th>Deposit/Withdrawal</th>
						</tr>
					</thead>
					<tbody id="wallet-holding-user-possession-tbody">

					</tbody>
				</table>

				<!-- <div class="fl-right mgt20">Total <b class="blue">0</b> rows retrieved</div> -->

			</div>
			<!--보유코인 테이블 끝-->

			<!--에어드랍 코인 테이블 시작-->
			<div class="tblType02">
				<h1 class="mgt20"><strong>Airdrop Status</strong></h1>
				<table>
					<colgroup>
						<col>
						<col>
						<col>
						<col style="width: 50%">
					</colgroup>
					<thead>
						<tr>
							<th>Application date</th>
							<th>Coin</th>
							<th class="right">Avail. quantity</th>
							<th>Remarks</th>
						</tr>
					</thead>
					<tbody id="airdropTable">
						
					</tbody>
				</table>

				<div class="fl-right mgt20">Total <b class="blue" id="airdropCount">0</b> rows retrieved</div>

				<!-- 페이징 : Start -->
				<ul id="wallet-holdings-airdrop-pagination" class="pagination">
					<li><a class="btn-page first"></a></li>
					<li><a class="btn-page prev"></a></li>
					<li><a href="#" class="active">1</a></li>
					<li><a href="#">2</a></li>
					<li><a class="btn-page next"></a></li>
					<li><a class="btn-page last"></a></li>
				</ul>
				<!-- //페이징 : End -->
			</div>
			<!--에어드랍 코인 테이블 끝-->

			<!--보상 코인 테이블 시작-->
			<div class="tblType02">
				<h1 class="mgt20"><strong>Compensation Coin Status</strong></h1>
				<table>
					<colgroup>
						<col>
						<col>
						<col>
						<col style="width: 50%">
					</colgroup>
					<thead>
						<tr>
							<th>Application date</th>
							<th>Coin</th>
							<th class="right">Avail. quantity</th>
							<th>Remarks</th>
						</tr>
					</thead>
					<tbody id="rewardTable">
						
					</tbody>
				</table>

				<div class="fl-right mgt20">Total <b class="blue" id="rewardCount">0</b> rows retrieved</div>

				<!-- 페이징 : Start -->
				<ul id="wallet-holdings-compensation-pagination" class="pagination">
					<li><a class="btn-page first"></a></li>
					<li><a class="btn-page prev"></a></li>
					<li><a href="#" class="active">1</a></li>
					<li><a href="#">2</a></li>
					<li><a class="btn-page next"></a></li>
					<li><a class="btn-page last"></a></li>
				</ul>
				<!-- //페이징 : End -->
			</div>
			<!--보상코인 테이블 끝-->

		</div>

	</article>
	<!--자산보유현황 전체 내용 끝-->
</section>
<!--컨텐츠 내용 영역 끝-->

<script>
var airdropPaging;
var rewardPaging;

$(document).ready(function() {
	
	$('.sch').on("keyup", 'input', function() {
		var input = $(this);
		// console.log(input.val());
		if( input.val() != '' && isNaN(input.val()) ) {
			$('.sch').parent().next().find('tbody > tr:not([name*='+input.val().toUpperCase() + '])').hide();
			$('.sch').parent().next().find('tbody > tr[name*='+input.val().toUpperCase() + ']').show();
		} else
			$('.sch').parent().next().find('tbody > tr').show();
	});
	
	// Possession Coin Status 리스트 그려준다.
	getUserPossessionInfo();

	// Airdrop Paging
   	airdropPaging = Pu.init({
   		count: 3,
   		PageCount: 5,
   		draw: doAirdropSearch,
   		paginate: '#wallet-holdings-airdrop-pagination',
   		initCllBck: true,
   		type: 'search',
   		totalText: '#airdropCount',
 		initEventFun: doAirdropSearch
   	});

	// Reward Paging
   	rewardPaging = Pu.init({
   		count: 3,
   		PageCount: 5,
   		draw: doRewardSearch,
   		paginate: '#wallet-holdings-compensation-pagination',
   		initCllBck: true,
   		type: 'search',
   		totalText: '#rewardCount',
   		initEventFun: doRewardSearch
   	});
	
})

function getUserPossessionInfo() {
	ajaxOption({
		url: '/coinis/wallet/wlt003/userPossession',
		success: function(result) {
			var dataTemplate = $('#template-user-possession-list').html();
			var rows = '';
			console.log(result);
			if(result.length > 0) {
				$(result).each(function(item) {
					var possesAssets	= result[item].poss_asset;
					var totalQty		= nu.cm(result[item].total_qty, 8);
					var availableQty	= nu.cm(result[item].usable_qty, 8);
					var inUseQty		= nu.cm(result[item].in_use_qty, 8);
					var totQtyByBC		= nu.cm(result[item].total_by_btc, 8);

					rows += dataTemplate.replace(/{{PossAsset}}/g, possesAssets)
										.replace(/{{TotalQty}}/g, (totalQty))
										.replace(/{{AvailableQty}}/g, (availableQty))
										.replace(/{{InUseQty}}/g, (inUseQty))
										.replace(/{{TotQtyByBC}}/g, (totQtyByBC))
				});
				$('#wallet-holding-user-possession-tbody').html(rows);
			} else
				$('#wallet-holding-user-possession-tbody').html($('#template-user-possession-empty').html());
		}
	})
}

function doAirdropSearch(i, done) {
	var p = {};
	p["startIndex"] = i;
	p["queryDataNum"] = 3;
	
	ajaxOption({
		url: '/coinis/wallet/wlt003/airdropList',
		data: p,
		success: function(result) {
			
			var airdropList = result.data;
			var dataTemplate = $('#template-airdrop-list').html();
			var rows = "";
			
			if(airdropList.length > 0) {
				$(airdropList).each(function() {
					rows += dataTemplate.replace(/{{airdrop_compl_dt}}/g, getCstmFrmt(this.airdrop_compl_dt, "yyyyMMddHHmmss", "yyyy-MM-dd HH:mm:ss"))
										.replace(/{{coin_symbolic_nm}}/g, this.coin_symbolic_nm)
										.replace(/{{airdrop_qty}}/g, nwc(this.airdrop_qty))
										.replace(/{{title}}/g, this.title)
										/*.replace(/{{contract_qty}}/g, nwc(this.contract_qty.toFixed(this.qty_dsp_decimal_pnt)))*/
				});
				$('tbody[id="airdropTable"]').html(rows);
				console.log(airdropList);
				console.log(result.size);
			} else
				$('tbody[id="airdropTable"]').html($('#template-airdrop-list-empty').html());
		}
		, done: done
	})
}

function doRewardSearch(i, done) {
	var p = {};
	p["startIndex"] = i;
	p["queryDataNum"] = 3;
	
	ajaxOption({
		url: '/coinis/wallet/wlt003/rewardList',
		data: p,
		success: function(result) {
			
			var rewardList = result.data;
			var dataTemplate = $('#template-reward-list').html();
			var rows = "";
			
			if(rewardList.length > 0) {
				$(rewardList).each(function() {
					rows += dataTemplate.replace(/{{apply_dt}}/g, getCstmFrmt(this.apply_dt, "yyyyMMddHHmmss", "yyyy-MM-dd HH:mm:ss"))
										.replace(/{{coin_symbolic_nm}}/g, this.coin_symbolic_nm)
										.replace(/{{proc_qty}}/g, nwc(this.proc_qty))
										.replace(/{{proc_detail}}/g, this.proc_detail)
				});
				$('tbody[id="rewardTable"]').html(rows);
				console.log(rewardList);
				console.log(result.size);
			} else
				$('tbody[id="rewardTable"]').html($('#template-reward-list-empty').html());
		}
		, done: done
	})
}

</script>

<script id="template-user-possession-list" type="text/template">
	<tr name="{{PossAsset}}">
		<td>{{PossAsset}}</td>
		<td class="right">{{TotalQty}}</td>
		<td class="right">{{AvailableQty}}</td>
		<td class="right">{{InUseQty}}</td>
		<td class="right">{{TotQtyByBC}}</td>
		<td>
			<button class="small bgred" onclick="openForm()">Deposit</button>
			<button class="small bggreen" onclick="openForm()">Withdraw</button>
		</td>
	</tr>
</script>
<Script id="template-user-possession-empty" type="text/template">
	<tr>
		<td colspan="6" class="center">You have no Possession Coin</td>
	</tr>
</Script>

<script id="template-airdrop-list" type="text/template">
	<tr>
		<td>{{airdrop_compl_dt}}</td>
		<td>{{coin_symbolic_nm}}</td>
		<td class="right">{{airdrop_qty}}</td>
		<td class="right">{{title}}</td>
	</tr>
</script>

<Script id="template-airdrop-list-empty" type="text/template">
	<tr>
		<td colspan="4" class="center">You have no Coin airdropped</td>
	</tr>
</Script>

<script id="template-reward-list" type="text/template">
	<tr>
		<td>{{apply_dt}}</td>
		<td>{{coin_symbolic_nm}}</td>
		<td class="right">{{proc_qty}}</td>
		<td class="right">{{proc_detail}}</td>
	</tr>
</script>

<Script id="template-reward-list-empty" type="text/template">
	<tr>
		<td colspan="4" class="center">You have no Coin rewarded</td>
	</tr>
</Script>

