<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/taglib/taglib.jsp" %>

<!--컨텐츠 내용 영역 시작-->
<section>
	<!--입출금 내역 전체 내용 시작-->
	<article class="bgwhite">

		<h1 class="sub-title">Deposit / Withdrawal History</h1>
		<div class="content-item">
			<div class="search-option-group">
				<div class="select-box">
					<label for="wallet-transaction-coin-list-select">
						Select Coin
					</label>
					<select id="wallet-transaction-coin-list-select">
						<option selected>Select Coin</option>
						<option val="9999">ALL</option>
						<option>BTC</option>
						<option>LTC</option>
						<option>DASH</option>
					</select>
				</div>
				<div class="option-right">
					<span class="option-checks">
						<span class="checks-item">
							<label>
								<input id="wallet-transaction-category-checkbox-deposit" type="checkbox"><em></em>Deposit
							</label>
						</span>
						<span class="checks-item">
							<label>
								<input id="wallet-transaction-category-checkbox-withdrawal" type="checkbox"><em></em>Withdrawal
							</label>
						</span>
					</span>
					<div class="calendar-group">
						<input type="text" id="wallet-transaction-datepicker-startDate" value="" readonly="readonly">
						<span>~</span>
						<input type="text" id="wallet-transaction-datepicker-endDate" value="" readonly="readonly">
						<div class="fl-right">
							<button id="wallet-transaction-search-btn" href="javascript:void(0);" onClick="" class="bggray">Search</button>
						</div>
					</div>
				</div>
			</div>

			<!--입출금 내역 테이블 시작-->
			<div class="tblType02">
				<table>
					<colgroup>
						<col style="width: 85px">
						<col style="width: 120px">
						<col>
						<col>
						<col>
						<col style="width: 340px">
						<col>
						<col  style="width: 140px">
						<col>
				</colgroup>
					<thead>
						<tr>
							<th>Category</th>
							<th>DateTime</th>
							<th>Coin</th>
							<th class="right">Amount</th>
							<th class="right">Fees</th>
							<th>Withdrawal Address</th>
							<th></th>
							<th class="center">Status</th>
							<th>Cancel</th>
						</tr>
					</thead>
					<tbody id="wallet-transaction-dw-list-tbody">
						<!--최대 20개 노출-->
						<tr>
							<td colspan="9" class="center">You have no Deposit or Withdrawal History </td>
						</tr>
						<tr>
							<td class="red"></td>
							<td></td>
							<td class="bold"></td>
							<td class="right"></td>
							<td class="right"></td>
							<td class="text-ellips left"></td>
							<td>
								<button class="bggray" onclick="openForm()">Details</button>
							</td>
							<td></td>
							<td><button class="bggray">Cancel</button></td>
						</tr>
					</tbody>
				</table>

				<div class="fl-right mgt20">Total <b id="wallet-transaction-dw-list-total-count" class="blue">0</b> rows retrieved</div>

				<!-- 페이징 : Start -->
				<ul id="wallet-transaction-dw-list-pagination" class="pagination">
					<li><a class="btn-page first"></a></li>
					<li><a class="btn-page prev"></a></li>
					<li><a href="#" class="active">1</a></li><!-- 선택된 번호의 a 태그에 acitve 클래스 추가 -->
					<li><a class="btn-page next"></a></li>
					<li><a class="btn-page last"></a></li>
				</ul>
				<!-- //페이징 : End -->
			</div>
			<!--입출금 내역 테이블 끝-->

		</div>

	</article>
	<!--입출금 내역 전체 내용 끝-->
</section>
<!--컨텐츠 내용 영역 끝-->

<!--상세보기 팝업 시작-->
<div class="popup more" id="wallet-transaction-detail-view-popup">
	<form action="">
		<div class="pop-title"><span> Deposit / Withdrawal Detail </span>
			<div class="cancel" onclick="closeForm()">
				<img src="/resources/img/btn-alert-close.png" alt="Close Button">
			</div>
		</div>
	</form>
	<div class="popup-cont">

		<h1>Deposit/Withdrawal Info</h1>
		<table class="tblType01">
			<colgroup>
				<col style="width: 130px">
				<col>
			</colgroup>
			<tbody>
				<tr>
					<th>Coin</th>
					<td><span class="coin-eng-name"></span></td>
				</tr>
				<tr>
					<th>Transaction</th>
					<td id="wallet-transaction-detail-dw-gubun"></td>
				</tr>
				<tr>
					<th>Quantity</th>
					<td><span id="wallet-transaction-detail-quantity">0</span>&nbsp;<span class="coin-symbolic-name"></span></td>
				</tr>
				<tr>
					<th>Fees</th>
					<td><span id="wallet-transaction-detail-fee">0</span>&nbsp;<span class="coin-symbolic-name"></span></td>
				</tr>
				<tr>
					<th>DateTime</th>
					<td id="wallet-transaction-detail-dt"></td>
				</tr>
				<tr>
					<th>Channel</th>
					<td id="wallet-transaction-detail-channel"></td>
				</tr>
				<tr>
					<th>Confirm DateTime</th>
					<td id="wallet-transaction-detail-confirm-dt"></td>
				</tr>
				<tr>
					<th>Status</th>
					<td id="wallet-transaction-detail-status"></td>
				</tr>
			</tbody>
		</table>

		<h1 class="mgt20">BlockChain Info</h1>
		<table class="tblType01">
			<colgroup>
				<col style="width: 130px">
				<col>
			</colgroup>
			<tbody>
				<tr>
					<th>Address</th>
					<td id="wallet-transaction-detail-address"></td>
				</tr>
				<tr>
					<th>TransactionID</th>
					<td>
						<!-- 클릭시 블로체인 익스플로러 연결-->
						<a id="wallet-transaction-detail-txid" href=""></a>
					</td>
				</tr>
			</tbody>
		</table>
		<button onclick="closeForm()" class="base bggray">Close </button>
	</div>
</div>
<!--상세보기 팝업 끝-->

<!--상세보기 팝업 시작-->
<div class="popup more" id="wallet-transaction-auth-retry-popup">
	<form action="">
		<div class="pop-title"><span> Authentication </span>
			<div class="cancel" onclick="closeForm1()">
				<img src="/resources/img/btn-alert-close.png" alt="Close Button">
			</div>
		</div>
	</form>

	<div class="popup-cont">

		<h1>Authentication</h1>
		<table class="tblType01">
			<colgroup>
				<col style="width: 170px">
				<col>
			</colgroup>
			<tbody>
				<tr>
					<th>LTC Withdrawal address</th>
					<td>NAOQKEQSY4QK42JZPYG7JCJW6TKEABHUPM4GUDAO1234578</td>
				</tr>
				<tr>
					<th>Extra Address</th>
					<td>NAOQKEQSY4QK42JZPYG7JCJW6TKE</td>
				</tr>
				<tr>
					<th>Withdrawal Qty</th>
					<td>
						<span>0.41425424</span>
						<span>BTC</span>
					</td>
				</tr>
				<tr>
					<th>Withdrawal Fee</th>
					<td>
						<span>0.41425424</span>
						<span>BTC</span>
					</td>
				</tr>
				<tr>
					<th>Actual Withdrawal Qty</th>
					<td>
						<span>0.41425424</span>
						<span>BTC</span>
					</td>
				</tr>
					<tr>
					<th>SMS Authentication</th>
					<td>
						<span class="btn-mini fl-left">
							<button class="small bggray">Send SMS</button>
						</span>
						<span class="tblInput">
							<input type="text" id="" name="" value="" placeholder="Enter SMS Authentication Code">
						</span>
					</td>
				</tr>

				<tr>
					<th>OTP Authentication Code</th>
					<td>
						<span class="tblInput">
							<input type="text" id="" name="" value="" placeholder="Enter OTP Authentication Code">
						</span>
					</td>
				</tr>
			</tbody>
		</table>

		<button onclick="closeForm1()" class="base bggray">Close</button>
	</div>
</div>
<!--상세보기 팝업 끝-->

<script src="/resources/js/coinis/wallet/wlt002/global.wlt002.transaction.js"></script>

<script>
	//팝업 열고 닫기   
	function openForm(reqSeqNo) {
		viewDetailPopup(reqSeqNo);
		document.getElementById("wallet-transaction-detail-view-popup").style.display = "block";
	}
	function closeForm() {
		document.getElementById("wallet-transaction-detail-view-popup").style.display = "none";
	}
	function openForm1() {
		document.getElementById("wallet-transaction-auth-retry-popup").style.display = "block";
	}
	function closeForm1() {
		document.getElementById("wallet-transaction-auth-retry-popup").style.display = "none";
	}
	
	//jquery 탭
	$(document).ready(function() {
		//입출금내역 달력 설정
		$("#wallet-transaction-datepicker-endDate").datepicker({
            inline: true,
            dateFormat: 'yy.mm.dd',
            maxDate: $.datepicker.formatDate('yy.mm.dd', new Date()),
            onSelect: function() {
                $("#wallet-transaction-datepicker-startDate").datepicker("option","maxDate", $("#wallet-transaction-datepicker-endDate").datepicker('getDate'))
                if ( $.datepicker.formatDate('yymmdd', $("#wallet-transaction-datepicker-endDate").datepicker('getDate')) 
                        < $.datepicker.formatDate('yymmdd', $("#wallet-transaction-datepicker-startDate").datepicker('getDate')) ) {
                    $('#wallet-transaction-datepicker-startDate').val($("#wallet-transaction-datepicker-endDate").datepicker('getDate'));
                }
            }
        });
        $("#wallet-transaction-datepicker-startDate").datepicker({
            inline: true,
            dateFormat: 'yy.mm.dd',
            maxDate: $("#wallet-transaction-datepicker-endDate").datepicker('getDate')
        });
        // 오늘 날짜로 세팅
        $('#wallet-transaction-datepicker-startDate').val($.datepicker.formatDate('yy.mm.dd', new Date()));
        $('#wallet-transaction-datepicker-endDate').val($.datepicker.formatDate('yy.mm.dd', new Date()));

		var selectTarget = $('.select-box select');
		$('.select-box select').on('change', function() {
			var select_name = $(this).children('option:selected').text();
			$(this).siblings('label').text(select_name);
		});

		var coinList = JSON.parse('${exchangeCoinList}');
		$('#wallet-transaction-coin-list-select').empty();
		$('#wallet-transaction-coin-list-select').append($('<option value="9999" selected>Select Coin</option>'));
		coinList.forEach(function(item) {
			$('#wallet-transaction-coin-list-select').append($('<option value="'+ item.COIN_NO + '">' + item.ITEM_ENG_NM + ' (' + item.COIN_SYMBOLIC_NM + ')</option>'));
		});

		// 코인관리 기준정보 가져옴
		var coinMgrRefinfoJson = JSON.parse('${coinMgtRefInfo}');
		Object.keys(coinMgrRefinfoJson).forEach(function(key) {
			__G_CoinMgtRef_Map.put(key, coinMgrRefinfoJson[key]);
		});

		// DW List 표출
		getDepositWithdrawalList(9999);
	});
</script>

<!-- 입출금 내역 Table Template -->
<script id="template-wallet-transaction-dw-list-tr" type="text/template">
	<tr data-req-seq-no="{{ReqSeqNo}}">
		<td class="{{DwGubunColor}}">{{DwGubun}}</td>
		<td>{{ReqDateTime}}</td>
		<td class="bold">{{CoinSymbolicName}}</td>
		<td class="right">{{ReqQty}}</td>
		<td class="right">{{Fee}}</td>
		<td class="text-ellips left">{{Address}}</td>
		<td>
			<button class="bggray" onclick="openForm('{{ReqSeqNo}}')">Details</button>
		</td>
		<td>{{ReqStatProcText}}</td>
		<td>{{CancelButtonHTML}}</td>
	</tr>
</script>
<Script id="template-dw-list-empty" type="text/template">
	<tr>
        <td colspan="{{ColSpan}}">You have no Deposit/Withdraw History </td>
	</tr>
</Script>
