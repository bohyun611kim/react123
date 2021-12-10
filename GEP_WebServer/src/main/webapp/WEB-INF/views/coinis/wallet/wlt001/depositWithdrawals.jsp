<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/taglib/taglib.jsp" %>
<script src="/resources/js/qrcode/qrcode.min.js"></script>

<style type="text/css">
	html,
	body {
		margin: 0;
		padding: 0;
	}

	* {
		box-sizing: border-box;
	}

	.slider {
		width: 1280px;
		margin: 0px auto;

	}

	.slick-slide {
		margin: 0px 20px;
	}

	.slick-slide img {
		width: 100%;
	}

	.slick-prev:before,
	.slick-next:before {
		color: black;
	}

	.slick-slide {
		transition: all ease-in-out .3s;
		opacity: 1;
	}

	.slick-active {
		opacity: 1;
		background-color: #fff
	}

	.slick-current {
		opacity: 1;
	}
</style>

<!--컨텐츠 내용 영역 시작-->
<section>
	<!--입출금 전체 내용 시작-->
	<article class="wallet-wrap">

		<!--지갑관리 좌측 코인 리스트 시작-->
		<aside>
			<div class="sch-wallet-coin">
				<p class="sch-input">
					<input type="text" id="" name="" value="" placeholder="Search">
				</p>
				<span class="checks-item">
					<label>
						<input type="checkbox" id="only-balance-show-check-box"><em></em>
					</label>
					Balance
				</span>
			</div>

			<div class="coin-list mCustomScrollbar">
				<table>
					<tbody id="wallet-lnb-coin-list-tbody">
					</tbody>
				</table>
			</div>
		</aside>
		<!--지괍관리 좌측 코인 리스트 끝-->

		<!--입출금 탭 영역 시작-->
		<div class="tab-wrap">
			<div id="tab-group">
				<ul id="wallet-tab-ul" class="tabs-2li">
					<li><a id="wallet-tab-a-1" href="#tabs-1">Deposit</a></li>
					<li><a id="wallet-tab-a-2" href="#tabs-2">Withdrawal</a></li>
				</ul>
				<!-- 입금주소 탭 컨텐츠 : Start -->
				<div id="tabs-1">
					<h1 class="sub-title"><span class="coin-symbolic-name" >BCH</span> Deposit</h1>
					<div class="content-item">

						<!-- 코인주소가 없는 경우 : Start -->
						<div id="wallet-deposit-coin-address-generate" class="none-addr">
							<ul class="sub-desc">
								<li>Please deposit <span class="coin-symbolic-name">BCH</span> to the address below</li>
								<li><span class="coin-symbolic-name">BCH</span> If you do not have a deposit address, please click on the create address button to get your unique <span class="coin-symbolic-name">BCH</span> address.</li>
							</ul>
							<div class="btn-wrap">
								<button onClick="createDepositAddr()" class="big bgblue">Create <span class="coin-symbolic-name">BCH</span> Deposit Address</button>
							</div>
						</div>
						<!-- //코인주소가 없는 경우 : End -->

						<!-- 코인주소가 있는 경우 : Start -->
						<div id="wallet-deposit-coin-address-users" class="my-addr">
							<div class="my-addr-group">
								<p class="my-addr-title">Deposit <span class="coin-symbolic-name">BCH</span> Address</p>
								<span id="wallet-deposit-coin-user-address" class="my-addr-value"> </span>
								<button id="wallet-deposit-copy-coin-address" class="small bggray">Copy Address</button>
								<div id="wallet-deposit-coin-address-extra" class="extra-addr-group">
									<span id="wallet-deposit-coin-address-extra-1-title" class="extra-title wallet-deposit-coin-address-extra-1-title">Destination Tag</span>&nbsp;:&nbsp;
									<span id="wallet-deposit-coin-address-extra-1-address" class="extra-addr">0000</span>
									<span class="red">
										<img src="/resources/img/icon-img01.png"> Be sure to enter the <span class="wallet-deposit-coin-address-extra-1-title">Tag</span>
									</span>
								</div>

							</div>
							<div class="my-addr-qr" id="my-addr-qr">
								<img src="/resources/img/temp-qrimg.png">
							</div>
						</div>
						<!-- 코인주소가 있는 경우 : end -->

						<!--입출금 중지일 경우-->
						<div id="wallet-deposit-banned-dw-status" class="my-addr center mgt20">
							<p class="addr-info">현재 입금 중지 상태입니다.</p>
							<p class="addr-info">IEO가 진행중일 경우 입출금이 불가합니다.</p>
						</div>
						<!--입출금 중지일 경우-->

						<!--입출금 유의사항-->
						<ul class="sub-desc">
							<li>If you send <span class="coin-symbolic-name">BCH</span> to this unique <span class="coin-symbolic-name">BCH</span> address, it will be deposited to your <span class="coin-symbolic-name">BCH</span> wallet.</li>
							<li>The deposit takes 10 to 30 minutes, but may be delayed due to <span class="coin-symbolic-name">BCH</span> network conditions.</li>
							<li>The results of the deposit process can be found on the Account Management - Deposit and Transfer Details screen.</li>
							<li>Make sure you check your wallet address prior to deposit.</li>
						</ul>

						<h2 class="mgt20 red"> ※ Notes on deposit and withdrawal</h2>
						<ul class="sub-desc">
							<li>The withdrawal limit is KRW & coin integration limit based on the authentication level. The coin is based on the current price of the coin at the time of withdrawal.</li>
							<li>If it is initial withdrawal from the KRW first deposit, your withdrawal will be limited to 72 business hours following our withdrawal/refund policy.</li>
							<li>Even if you withdraw coins without cash deposits, the first withdrawal will be processed after confirmation of the manager</li>
							<li>If there is no withdrawal limit, coin withdrawal will be processed automatically for 24 hours</li>
							<li>The deposit and withdrawal of cash will be processed through the administrator's confirmation from 09:00 to 18:00 including the holiday</li>
							<li>The deposit of cash is processed only when you make a deposit from the bank account you registered. If the account is different from that you registered, the deposit/withdrawal will be denied</li>
							<li>Be careful of voice phishing. The fraud damage such as solicitation of loan, loan repayment, coin purchasing agency is increasing rapidly.</li>
							<li>If you do not have more than one deposit (KRW, coin included), you can not request withdrawal.</li>
							<li>Coin deposit/withdrawal is typically processed within 30 minutes, but may take longer to transfer depending on block-chain network conditions.</li>
							<li>The withdrawal address shown in the transaction is different from your deposit address because the coin withdrawal is carried out in the CoinIs hot wallet. If you need to get a refund at the withdrawal address or participate in ICO, Air Drop, etc., please do not use the Exchange address. First, move coin to your personal wallet and then participate. We will not be responsible for the lost coin</li>
						</ul>

					</div>

				</div>
				<!-- //입금주소 탭 컨텐츠 : End -->

				<!-- 출금신청 탭 컨텐츠 : Start -->
				<div id="tabs-2">
					<h1 class="sub-title"><span class="coin-symbolic-name">BCH</span> Withdrawal</h1>
					<div class="content-item">

						<!--입출금 중지일 경우-->
						<div id="wallet-withdraw-banned-dw-status" class="my-addr center mgt20">
							<p class="addr-info">현재 출금 중지 상태입니다.</p>
							<p class="addr-info">IEO가 진행중일 경우 입출금이 불가합니다.</p>
						</div>
						<!--입출금 중지일 경우-->
						
						<!-- 보안인증하러가기 버튼: Start -->
						<div id="wallet-withdraw-security-auth" class="btn-wrap">
							<button class="big bgblue">Security Setup</button>
							<p class="red mgt20">
								<img src="/resources/img/icon-img01.png"> 보안인증을 하셔야 출금이 가능합니다.
							</p>
						</div>
						<!-- //보안인증하러가기 버튼 : End -->

						<!-- 입력 영역 : Start -->
						<table id="wallet-withdraw-input-table" class="tblType01">
							<colgroup>
								<col style="width:230px;">
								<col>
							</colgroup>
							<tbody>
								<tr>
									<th>Qty Withdrawable</th>
									<td>
										<span id="wallet-withdraw-user-possess-qty">0.0</span><!-- 코인수량 -->
										<span class="coin-symbolic-name">BTC</span><!-- 코인종류 -->
									</td>
								</tr>
								<tr>
									<th>Withdrawal limit pertransaction</th>
									<td>
										<span id="wallet-withdraw-daily-max-qty">0.0</span>
										<span class="coin-symbolic-name">BTC</span>
									</td>
								</tr>
								<tr>
									<th>Today's Limit left</th>
									<td>
										<span id="wallet-withdraw-daily-left-qty">0.0</span>
										<span class="coin-symbolic-name">BTC</span>
									</td>
								</tr>
								<tr>
									<th><span class="coin-symbolic-name">BTC</span> Withdrawal address</th>
									<td>
										<span class="tblInput">
											<input type="text" id="wallet-withdraw-target-address" name="" value="">
										</span>
									</td>
								</tr>
								<tr id="wallet-withdraw-target-address-extra-1-tr">
									<th id="wallet-withdraw-target-address-extra-1-title">Extra Address</th>
									<td>
										<span class="tblInput">
											<input type="text" id="wallet-withdraw-target-address-extra-1" name="" value="">
										</span>
									</td>
								</tr>
								<tr>
									<th>Withdrawal Qty</th>
									<td>
										<span class="tblInput">
											<input type="text" id="wallet-withdraw-qty" name="" value="">
										</span>
										<button onClick="setMaxWithdrawQty()" class="small bggray">Max</button>
										<p>(Min Withdrawal Amount : <span id="wallet-withdraw-min-qty">0.0</span>)</p>
									</td>
								</tr>
								<tr>
									<th>Withdrawal Fee</th>
									<td>
										<span id="wallet-withdraw-fee">0.0</span>
										<span class="coin-symbolic-name">BTC</span>
									</td>
								</tr>
								<tr>
									<th>Actual Withdrawal Qty</th>
									<td>
										<span id="wallet-withdraw-actual-qty">0.0</span>
										<span class="coin-symbolic-name">BTC</span>
									</td>
								</tr>

								<tr id="wallet-withdraw-auth-sms-tr">
									<th>SMS Authentication</th>
									<td>
										<span class="btn-mini fl-left">
											<button onClick="checkAndRequestSmsAuthCode()" id="wallet-withdraw-auth-sms-code-request-btn" class="small bggray">Send SMS</button>
										</span>
										<span class="tblInput">
											<input type="text" id="wallet-withdraw-auth-sms-code" name="" value="" placeholder="Enter SMS Authentication Code">
										</span>
									</td>
								</tr>

								<tr id="wallet-withdraw-auth-otp-tr">
									<th>OTP Authentication Code</th>
									<td>
										<span class="tblInput">
											<input type="text" id="wallet-withdraw-auth-otp-code" name="" value="" placeholder="Enter OTP Authentication Code">
										</span>
									</td>
								</tr>
							</tbody>
						</table>
						<!-- //입력 영역 : End -->

						<!-- 출금 요청 버튼 : Start -->
						<div id="wallet-withdraw-button" class="btn-wrap">
							<button onClick="requestWithDraw()" class="big bgblue">Withdrawal</button>
						</div>
						<!-- //출금 요청 버튼 : End -->
					</div>
				</div>
				<!-- //출금신청 탭 컨텐츠 : End -->
			</div>
		</div>
		<!--입출금 탭 영역 끝-->

	</article>
	<!--입출금 전체 내용 끝-->
</section>
<!--컨텐츠 내용 영역 끝-->
	
<script src="/resources/js/coinis/wallet/wlt001/global.wlt001.deposit.withdrawal.js"></script>

<script>

$(document).ready(function() {
	$("#tab-group").tabs().css('display', 'block');

	$("#datepicker").datepicker({
		inline: true
	});
	
	// 종목 search
	$('.sch-input').on("keyup", 'input', function() {
		var input = $(this);
		
		if( input.val() != '' && isNaN(input.val()) ) {
			$('tbody[id="wallet-lnb-coin-list-tbody"] > tr:not([data-nm*='+ input.val().toUpperCase() +']) ').hide();
			$('tbody[id="wallet-lnb-coin-list-tbody"] > tr[data-nm*='+ input.val().toUpperCase() +'] ').show();
		} else
			$('tbody[id="wallet-lnb-coin-list-tbody"] > tr').show();
	});
	
    // 회원 기본정보 셋팅
    __G_Default_Coin_Type = parseInt('${defaultCoinType}');

    // 코인관리 기준정보 가져옴
    var coinMgrRefinfoJson = JSON.parse('${coinMgtRefInfo}');
    Object.keys(coinMgrRefinfoJson).forEach(function(key) {
        __G_CoinMgtRef_Map.put(key, coinMgrRefinfoJson[key]);
    });

	// Coin LNB영역 그리기
	drawLnbList('${exchangeCoinList}');
	// LNB 영역 개인 Possession 정보 가져오기
	getLnbPossessionInfo();
	
	// initialize page by top item of list
	var first_tr = $('tbody[id="wallet-lnb-coin-list-tbody"] > tr:eq(0)');
	first_tr.addClass('active');
	doProcessRFrame(first_tr.attr('data-no'), first_tr.attr('data-nm'), first_tr.attr('data-eng-nm'));
});
</script>

<!-- LNB 영역 Table Template -->
<script id="template-wallet-lnb-coin-list-tr" type="text/template">
	<tr id="coin-tr-{{CoinNo}}" data-qty="{{BalanceQty}}" data-nm="{{CoinSymbolicName}}" data-no="{{CoinNo}}" data-eng-nm="{{CoinEngName}}" data-dp-show-yn="{{DepositPageShowYn}}" data-wd-show-yn="{{WithDrawPageShowYn}}">
		<td>
			<p class="coin-symbol">
				<img src="/resources/img/coin-symbol/{{CoinSymbolicName}}.png">
			</p>
		</td>
		<td>
			<p>{{CoinSymbolicName}}</p>
		</td>
		<td>
			<p id="possession-qty-{{CoinNo}}">{{BalanceQty}}</p>
			<p>{{CoinSymbolicName}}</p>
		</td>
	</tr>
</script>
