<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/taglib/taglib.jsp" %>

<!--컨텐츠 내용 영역 시작-->
<section class="main">

    <article class="layoutB">

        <!-- Left side : Start -->
        <aside>
            <ul>
                <li>
                    <div class="whole-asset-group">
                        <span class="txt-box">총 평가액</span>
                        <span id="total_estimated_qty" class="asset-value">0</span>
                        <span class="asset-unit">KRW</span>
                    </div>
                </li>
                <li>
                    <div class="sch-coin-group">
                        <p class="sch-coin-input">
                            <input type="text" id="search_coin_text" name="" value="" placeholder="암호화폐명/심볼 검색" class="sch-input">
                        </p>
                        <span class="checks-item">
                            <input type="checkbox" id="chk_box_possess_coin">
                            <label for="chk_box_possess_coin">보유</label>
                        </span>

                    </div>
                </li>

                <!-- 코인 리스트. 10개 이상일 때는 스크롤 생김 : Start -->
                <li class="coin-list-wrap mCustomScrollbar">
                    <table class="coin-list-inner">
                        <colgroup>
                            <col style="width:30px;">
                            <col style="width:80px;">
                            <col>
                        </colgroup>
                        <tbody id="wallet-lnb-coin-list-tbody">
                            <!-- 리스트 1개 반복 : Start -->
                            <!-- <tr class="active">
                                <td>
                                    <span class="coin-symbol">
                                        <img src="/resources/img/coin-symbol/KRW.png" alt="coin">
                                    </span>
                                </td>
                                <td>
                                    <p class="coin-name">원</p>
                                    <p class="coin-ratio">
                                        <span class="ratio-value" style="width: 30%;"></span>
                                    </p>
                                </td>
                                <td>
                                    <p class="vertual-coin-type">
                                        <span>0</span>
                                        <span>KRW</span>
                                    </p>
                                    <p class="country-real-money">
                                        <span>0</span>
                                        <span>KRW</span>
                                    </p>
                                </td>
                            </tr> -->
                            <!-- //리스트 1개 반복 : End -->
                        </tbody>

                    </table>
                </li>
                <!-- //코인 리스트. 10개 이상일 때는 스크롤 생김 : End -->
            </ul>
            <ul class="wlt_info">
				<li>미체결 수량을 제외한 거래 가능한 수량으로 실제 보유수량과 차이가 있습니다.<br></li>
				<li>실제 보유수량은 투자내역 메뉴에서 확인 가능합니다.</li>
			</ul>
        </aside>
        <!-- //Left side : End -->

        <!--입금 출금 탭 영역 시작-->
        <div class="content">
            <!-- CONTENT INNER : Start -->
            <div>
                <h2 class="subtitle">입출금</h2>

                <!-- 원화일 때 : Start -->
                <div id="tab-group-1">
                    <ul id="money-tab-ul" class="li3">
                        <li><a id="money-tab-a-1" href="#money-tab-1">입금신청</a></li>
                        <li><a id="money-tab-a-2" href="#money-tab-2">출금신청</a></li>
                        <li><a id="money-tab-a-3" href="#money-tab-3">입출금내역</a></li>
                    </ul>

                    <!-- 계좌인증 및 입금신청 탭 컨텐츠 : Start -->
                    <div id="money-tab-1">
                        <!-- 계좌 인증 전 탭 내용 : Start -->
                        <div id="money-tab-1-insert-new-bank-account-div" class="content-item">
                            <ul class="disc">
                                <li>KRW(원화) 입출금 계좌를 등록 합니다</li>
                                <li class="red">반드시 본인 명의의 계좌를 등록 하여야 사용 가능 합니다</li>
                                <li class="red">평생계좌번호는 등록하실 수 없습니다.</li>
                            </ul>
                            <!-- 계좌입력영역 : Start -->
                            <div>
                                <table class="type01">
                                    <colgroup>
                                        <col style="width:180px;">
                                        <col>
                                    </colgroup>
                                    <tbody>
                                        <tr>
                                            <th>계좌 명의자 이름</th>
                                            <td>
                                                <input type="text" id="money-tab-1-bank-account-holder-name" name="" value="" placeholder="">
                                            </td>
                                        </tr>
                                        <tr>
                                            <th>생년월일 6자리 (사업자번호 10자리)</th>
                                            <td>
                                                <input type="text" id="money-tab-1-bank-account-holder-birthday" name="" value="" placeholder="예) 880808">
                                            </td>
                                        </tr>
                                        <tr>
                                            <th>은행명</th>
                                            <td>
                                                <select id="money-tab-1-bank-list" class="select-box">
                                                    <option value="9999">선택</option>
                                                </select>
                                            </td>
                                        </tr>
                                        <tr>
                                            <th>계좌번호 입력</th>
                                            <td>
                                                <input type="text" id="money-tab-1-bank-account-number" name="" value="" placeholder="'-'빼고 숫자만 기입">
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                                <!-- //입력영역 : End -->
                                <!-- 등록,취소 버튼 : Start -->
                                <div class="btn-group">
                                    <ul>
                                        <li><button onClick="insertUserBankAccntInfo()" class="blue">계좌인증</button></li>
                                        <li><button>취소</button></li>
                                    </ul>
                                </div>
                                <!-- //등록,취소 버튼 : End -->
                            </div>
                            <!-- 계좌입력영역 : end -->
                        </div>
                        <!-- //계좌인증 전 탭 내용 : End -->

                        <!--계좌인증후 입금신청 탭 내용 : Start -->
                        <div id="money-tab-1-request-new-deposit-div" style="display: none;">
                            <h2 class="tabletitle">받는 사람</h2>
                            <table class="type01">
                                <colgroup>
                                    <col style="width: 100px">
                                    <col>
                                    <col style="width: 100px">
                                    <col>
                                    <col style="width: 100px">
                                    <col>
                                </colgroup>
                                <tbody>
                                    <tr>
                                        <th>계좌번호</th>
                                        <td id="money-tab-1-exchange-account-number"></td>
                                        <th>입금은행</th>
                                        <td id="money-tab-1-exchange-bank-name"></td>
                                        <th>계좌소유주</th>
                                        <td id="money-tab-1-exchange-account-holder-name"></td>
                                    </tr>
                                </tbody>
                            </table>
                            <h2 class="tabletitle">보내는 사람</h2>
                            <input type="hidden" id="money-tab-1-sender-bank-code" value="">
                            <table class="type01">
                                <colgroup>
                                    <col style="width: 100px">
                                    <col>
                                    <col style="width: 100px">
                                    <col>
                                    <col style="width: 100px">
                                    <col>
                                </colgroup>
                                <tbody>
                                    <tr>
                                        <th>등록된 계좌</th>
                                        <td id="money-tab-1-sender-account-number"></td>
                                        <th>은행명</th>
                                        <td id="money-tab-1-sender-bank-name"></td>
                                        <th>계좌소유주</th>
                                        <td id="money-tab-1-sender-account-holder-name"></td>
                                    </tr>
                                </tbody>
                            </table>
                            <h2 class="tabletitle">입금신청
                                <span> ※ 입금신청 후 발급되는 입금코드를 사용하여 계좌이체 하여 주십시오.</span>
                            </h2>
                            <table class="type01">
                                <tbody>
                                    <tr>
                                        <td class="alCenter">
                                            <span>입금신청 금액 </span>
                                            <input type="text" id="money-tab-1-request-amount" name="" value="" class="krwdeposit">
                                            <span>원</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <h2 class="bold">아래의 입금신청 주의사항에 동의합니다.</h2>
                                            <div class="agree-box">
                                                <!-- 체크박스 : Start -->
                                                <div class="caution-box" id="agreeChk">
                                                    <label class="red bold"><input type="checkbox"><em></em>불법(대출사기, 보이스피싱등) 목적으로 입금하지 않습니다.</label><br>
                                                    <label class="red bold"><input type="checkbox"><em></em>토스, 카카오페이, ATM, 폰뱅킹으로 입금하지 않습니다.</label><br>
                                                    <label><input type="checkbox" class="agreeCheck"><em></em>입금 금액과 충전요청 금액이 동일합니다.</label><br>
                                                    <label><input type="checkbox" class="agreeCheck"><em></em>등록된 나의 은행계좌에서 입금합니다.</label><br>
                                                    <label><input type="checkbox" class="agreeCheck"><em></em>입금코드를 정확히 받는통장메모에 기입하였습니다.</label><br>
                                                    <label><input type="checkbox" class="agreeCheck"><em></em>이를 지키지 않으면 입금이 되지 않음을 알고 있습니다.</label><br>
                                                    <label><input type="checkbox" class="agreeCheck"><em></em>환급은 10일 이상 걸리고 환급수수료가 발생합니다.</label><br>
                                                    <label><input type="checkbox" class="agreeCheck"><em></em>원화 입금 후 첫 출금은 영업일기준 72시간 이후 가능합니다.</label>
                                                </div>
                                                <!-- 체크박스 : end -->

                                                <div class="caution-agree">
                                                    <label><input id="agreeAll" type="checkbox"><em></em>전체동의</label>
                                                </div>

                                            </div>


                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                            <!-- 입금요청 버튼 : Start -->
                            <div class="btn-group">
                                <ul>
                                    <li><button class="blue pop-code" id="moneyChargingRequest">입금신청</button></li>
                                    <!-- 신청 버튼 클릭 입금코드 부여 팝업이 뜹니다 : 팝업은 하단-->
                                    <li><button onClick="initSetting()">취소</button></li>
                                </ul>
                            </div>
                            <!-- //입금요청 버튼 : End -->

                            <!-- //입출금 유의사항 : start -->
                            <div>
                                <h2 class="tabletitle red">※ 입금시 주의사항 - 반드시 읽어주세요!</h2>
                                <ul class="dot_list">
                                    <li>공휴일이나 영업일 18:00 이후 입금된 내역은 다음 영업일 10:00 이후에 순차 처리되어 반영됩니다.</li>
									<li>입금신청 시 부여되는 입금코드는 영문자 6자리(대소문자 구분안함)입니다.</li>
									<li>입금코드는 1회용이며, 매번 새롭게 부여됩니다. 절대 재사용하지 마십시오.</li>
									<li>부여받은 6자리 입금코드를 받는통장메모(입금통장표시, 받는분통장표시 등)에 정확히 기입하십시오.</li>
									<li>부여받은 입금코드는 최대 72시간까지만 유효합니다.</li>
									<li>입금신청 대기건수(실제 입금은 이루어지지 않은 건수)는 최대 10건입니다.</li>
									<li>모든 항목이 정확히 일치하는 경우, 입금 후 회원님 계정에 반영됩니다.</li>
									<li>서버 점검 시간이나 입금 신청이 증가할 경우 입금 반영이 지연될 수 있습니다.</li>
									<li>입금신청만 하고 실제 입금하지않는 사례가 반복되면 서비스 이용에 불이익이 발생할 수 있습니다.</li>
									<li>다음의 경우에는 입금 반영이 되지 않으므로 1:1문의해주세요.<br>
										- 입금시 메모기재란에 입금코드가 없는 경우<br>
										- 입금코드의 유효시간(휴일 포함 72시간)이 지난 후에 입금한 경우<br>
										- 등록 계좌와 다른 계좌에서 입금한 경우(동일명의 다른 계좌, 타명의 계좌)<br>
										- 신청 금액과 입금 금액이 다른 경우<br>
										- 동일한 입금코드로 신청 금액을 나누어 입금한 경우<br>
										- 동일한 입금코드로 반복해서 입금한 경우<br>
										- 토스, 카카오페이, ATM, 폰뱅킹으로 입금한 경우<br>
										※ 입금 반영 또는 환급은 별도의 입금 증명 자료가 필요하고 처리에 3일 이상 소요됩니다.<br>
										※ 환급 시 환급수수료 1,000원이 발생합니다.
									</li>
                                </ul>
                                <div class="alert_wrap">
	                                <h2 class="tabletitle red">※ 보이스피싱에 주의하십시오. 대출 권유, 대출금 상환, 암호화폐 구매대행 등의 사기 피해가 급증하고 있습니다.</h2>
									<h2 class="tabletitle red">※ 보이스피싱에 가담하거나, 속아서 피해를 보는 경우라도 계정/계좌 등의 개인정보를 제3자에게 제공하여 범죄에<br /> 
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;악용되는 경우 ‘전자금융거래법’ 위반 및 형법상 ‘사기방조죄’에 따라 처벌 받을 수 있습니다.</h2>
									<h2 class="tabletitle red">※ 보이스피싱 피해가 의심되는 경우 아래를 통해 신고 바랍니다.</h2>
									<ul>
										<li>- 금융감독원 보이스피싱 지킴이 : http://phishing-keeper.fss.or.kr</li>
										<li>- 지급정지, 피해신고(경찰청) 국번없이 112</li>
										<li>- 피싱사이트 신고(인터넷진흥원) 국번없이 118</li>
										<li>- 피해상담 및 환급(금융감독원) 국번없이 1332</li>
										<li>- Alibit CS센터 : cs@ali-bit.com 또는 ‘고객지원 – 1:1문의 – 금융사고’</li>
									</ul>
								</div>
                            </div>
                            <!-- //입출금 유의사항 : end -->

                        </div>
                        <!-- //계좌인증후 입금신청 탭 내용 : End -->
                    </div>
                    <!-- //계좌인증 및 입금신청 탭 탭 컨텐츠 : End -->

                    <!-- 출금신청 탭 컨텐츠 : Start -->
                    <div id="money-tab-2">
                        <!--출금 입력영역 시작-->
                        <div>
                            <h2 class="tabletitle">출금신청</h2>
                            <table class="type01">
                                <colgroup>
                                    <col style="width:180px;">
										<col style="width:410px;">
										<col>
                                </colgroup>
                                <tbody>
                                    <tr>
                                        <th>출금은행</th>
                                        <td id="money-tab-2-sender-bank-name"></td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <th>계좌번호</th>
                                        <td id="money-tab-2-sender-account-number"></td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <th>예금주</th>
                                        <td id="money-tab-2-sender-account-holder-name"></td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <th><span class="money-symbolic-name">KRW</span> 잔고</th>
                                        <td><span id="money-tab-2-user-poss-qty">0</span><span class="money-symbolic-name"> KRW</span></td>
                                        <td></td>
                                    </tr>
                                    <!-- <tr>
                                        <th>1일 출금한도</th>
                                        <td>
                                            <span id="money-tab-2-daily-limit-qty">0</span>코인수량
                                            <span class="money-symbolic-name">KRW</span>코인종류
                                        </td>
                                        <td></td>
                                    </tr> -->
                                    <tr>
                                        <th>1회 출금한도</th>
                                        <td>
                                            <span id="money-tab-2-once-wthrw-qty">0</span><!-- 코인수량 -->
                                            <span class="money-symbolic-name">KRW</span><!-- 코인종류 -->
                                        </td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <th>1일 잔여한도</th>
                                        <td>
                                            <span id="money-tab-2-daily-limit-left-qty">0</span>
                                            <span class="money-symbolic-name">BTC</span>
                                            <span class=" red">회원님 현재 인증 레벨은 <span class="coin-user-level">1</span><span>단계</span>입니다</span>
                                        </td>
                                        <td>
                                        	<button><a href="/alibit/info?tab=1">인증 레벨설정</a></button>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>출금요청액</th>
                                        <td>
                                            <input type="text" id="money-tab-2-withdraw-request-qty" name="" value="" placeholder="금액을 입력해 주세요">
                                            <span class="money-symbolic-name">KRW</span>
                                        </td>
                                        <td>
                                        	<button id="money-tab-2-max-qty-btn" onClick="setMaxMoneyWithdrawQty()">최대</button>
                                            <span>(최소 출금 수량 <span id="money-tab-2-min-withdraw-qty">0.0</span><span class="money-symbolic-name">KRW</span>)</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>수수료</th>
                                        <td>
                                            <span id="money-tab-2-withdraw-fee">0</span>
                                            <span class="money-symbolic-name">KRW</span>
                                        </td>
                                        <td></td>
                                    </tr>
                                    <tr id="money-tab-2-auth-sms-tr">
                                        <th>휴대폰 SMS 인증</th>
                                        <td>
                                            <input type="text" id="money-tab-2-sms-auth-number" name="" value="" placeholder="SMS 인증번호 6자리">
                                            <!-- <p class="info-text">인증번호 요청 후, 휴대폰으로 전송된 인증번호 6자리를 입력하세요</p> -->
                                        </td>
                                        <td>
                                        	<button id="money-tab-2-request-sms-auth-number-btn" onClick="requestMoneySmsAuthNumber();">인증번호 요청</button>
                                        </td>
                                    </tr>
                                    <tr id="money-tab-2-auth-otp-tr">
                                        <th>OTP 인증번호 입력</th>
                                        <td>
                                            <input type="text" id="money-tab-2-otp-auth-number" name="" value="" placeholder="OTP 인증번호 6자리">
                                            <!-- <p class="info-text">OTP앱 실행 후 표시 된 인증번호 6자리를 입력하세요</p> -->
                                        </td>
                                        <td></td>
                                    </tr>
                                </tbody>
                            </table>
                            <!-- 출금 요청, 취소 버튼 : Start -->
                            <div class="btn-group">
                                <ul>
                                    <li><button id="money-tab-2-request-btn" onClick="requestMoneyWithDraw()" class="blue">출금신청</button></li>
                                    <li><button id="money-tab-2-cancel-btn" onClick="cancelRequestMondyWithdraw()">취소</button></li>
                                </ul>
                            </div>
                            <!-- //출금 요청, 취소 버튼 : End -->

                            <!-- //입출금 유의사항 : start -->
                            <h2 class="tabletitle red">※ 출금시 주의사항 – 반드시 읽어주세요!</h2>
							<ul class="dot_list">
								<li class="red">KRW 최초 충전 후 첫 출금은 출금/환급 정책에 따라 영업일 기준 72시간 동안 출금이 제한됩니다.<br/>(KRW 최초 충전이 아닐 경우 - 영업일 기준 24시간 출금 제한)</li>
								<li class="red">원화 출금 요청이 공휴일 및 영업일 18:00시 이후 요청건인 경우 다음 영업일 10:00시부터 관리자의 확인을 거쳐 순차 처리됩니다.</li>
								<li>원화 출금은 등록한 입금계좌와 출금계좌가 다를 경우 출금이 되지 않습니다.</li>
                                <li>'암호화폐 입금 후 암호화폐를 출금하는 경우라도 첫 출금은 관리자 확인 후 처리됩니다.</li>
                                <li>출금한도는 인증레벨에 따른 KRW, 암호화폐 통합한도 이며, 암호화폐 출금한도는 출금 신청 당시의 현재가 기준으로 계산됩니다.</li>
                                <li>암호화폐 출금은 블록체인 네트워크 상황에 따라 지연될 수 있습니다.</li>
                                <li>암호화폐 출금은 Bita500의 핫월렛에서 진행되기 때문에 트랜잭션에 보이는 출금주소는 회원님의 입금주소와 다릅니다.</li>
                                <li>출금주소로 환급을 받아야 하거나 에어드랍 등에 참여할 경우 거래소의 지갑주소를 이용하시면 안됩니다.</li>
                                <li>먼저 회원님의 개인지갑으로 이동하신 후 참여하시기 바랍니다.</li>
                                <li>회원님의 부주의로 분실된 암호화폐에 대해서 Bita500은 책임지지 않습니다.</li>
							</ul>
							<!-- 2019-08-06 수정(글자수정 끝) -->
							<!-- 2019-08-06 수정(추가 시작) -->
							<div class="alert_wrap">
								<h2 class="tabletitle red">※ 보이스피싱에 주의하십시오. 대출 권유, 대출금 상환, 암호화폐 구매대행 등의 사기 피해가 급증하고 있습니다.</h2>
								<h2 class="tabletitle red">※ 보이스피싱에 가담하거나, 속아서 피해를 보는 경우라도 계정/계좌 등의 개인정보를 제3자에게 제공하여 범죄에<br /> 
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;악용되는 경우 ‘전자금융거래법’ 위반 및 형법상 ‘사기방조죄’에 따라 처벌 받을 수 있습니다.</h2>
								<h2 class="tabletitle red">보이스피싱 피해가 의심되는 경우 아래를 통해 신고 바랍니다.</h2>
								<ul>
									<li>- 금융감독원 보이스피싱 지킴이 : http://phishing-keeper.fss.or.kr</li>
									<li>- 지급정지, 피해신고(경찰청) 국번없이 112</li>
									<li>- 피싱사이트 신고(인터넷진흥원) 국번없이 118</li>
									<li>- 피해상담 및 환급(금융감독원) 국번없이 1332</li>
									<li>- Alibit CS센터 : cs@ali-bit.com 또는 ‘고객지원 – 1:1문의 – 금융사고’</li>
								</ul>
							</div>
                            <!-- //입출금 유의사항 : end -->
                        </div>
                        <!--출금 입력영역 끝-->
                    </div>
                    <!-- //출금신청 탭 컨텐츠 : End -->

                    <!-- 입출금내역 탭 컨텐츠 : Start -->
                    <div id="money-tab-3">
                        <div class="dw-history">
                            <!-- 조회 옵션 : Start -->
                            <div class="mgt20">
                                <select id="money-tab-3-select-dw-req-type" onChange="getMondyDWMgrList()" class="select-box">
                                    <option selected value="0">전체</option>
                                    <option value="1">입금</option>
                                    <option value="2">출금</option>
                                </select>

                                <div class="calendar">
                                    <input type="text" name="calendar" id="money-tab-3-dw-list-startDate" required="" value="2019-04-14">
                                    <span>~</span>
                                    <input type="text" name="calendar" id="money-tab-3-dw-list-endDate" required="" value="2019-04-15">
                                    <button onClick="getMondyDWMgrList()">조회</button>
                                </div>
                            </div>
                            <!-- //조회 옵션 : End -->
                            <!-- 조회 건수 표시 : Start -->
                            <p class="case-info">총 <b class="blue"><span id="money-tab-3-dw-list-total-count">0</span></b> 건의 자료가 조회 되었습니다</p>
                            <!-- //조회 건수 표시 : End -->
                            <p class="case-txt">원화 수수료는  소숫점 이하일 경우 반올림으로 처리됩니다.</p>
                            <!-- 데이터 영역 : Start -->
                            <div>
                                <table class="line">
                                    <colgroup>
                                        <col>
                                        <col style="width: 160px">
                                        <col>
                                        <col>
                                        <col>
                                        <col style="width: 180px">
                                        <col>
                                        <col>
                                        <col>
                                    </colgroup>
                                    <thead>
                                        <tr>
                                            <th>구분</th>
                                            <th>요청일시</th>
                                            <th class="alRight">금액(수량)</th>
                                            <th class="alRight">수수료</th>
                                            <th>입금은행</th>
                                            <th>입금계좌번호</th>
                                            <th>입금코드</th>
                                            <th>진행상태</th>
                                            <th>취소</th>
                                        </tr>
                                    </thead>
                                    <tbody id="money-tab-3-dw-list-tbody">
<!--                                         <tr>
                                            <td class="blue alCenter">출금</td>
                                            <td class="alCenter">2018-12-15 05:00:00</td>
                                            <td class="blue alRight">65.0256555</td>
                                            <td class="alRight">0.001</td>
                                            <td class="alCenter">카카오뱅크</td>
                                            <td class="alCenter">123-456-7890</td>
                                            <td class="alCenter">FMSEGW</td>
                                            <td class="alCenter">대기</td>
                                            <td class="alCenter">
                                                <button>취소</button>
                                            </td>
                                        </tr>
 -->                                    </tbody>
                                </table>
                                <!-- 페이징 : Start -->
                                <ul id="money-tab-3-dw-list-pagination" class="pagination">
                                    <li><a class="btn-page first"></a></li>
                                    <li><a class="btn-page prev"></a></li>
                                    <li><a href="#" class="active">1</a></li>
                                    <li><a class="btn-page next"></a></li>
                                    <li><a class="btn-page last"></a></li>
                                </ul>
                                <!-- //페이징 : End -->
                            </div>
                            <!-- //데이터 영역 : End -->
                        </div>
                    </div>
                    <!-- //입출금내역 탭 컨텐츠 : End -->
                </div>
                <!-- // 원화일 때 : End -->

                <!-- 코인일 때 : Start -->
                <div id="tab-group-2" class="mgt20">
                    <ul id="coin-tab-ul" class="li3">
                        <li><a id="coin-tab-a-1" href="#coin-tab-1">입금주소</a></li>
                        <li><a id="coin-tab-a-2" href="#coin-tab-2">출금신청</a></li>
                        <li><a id="coin-tab-a-3" href="#coin-tab-3">입출금내역</a></li>
                    </ul>
                    <!-- 입금주소 탭 컨텐츠 : Start -->
                    <div id="coin-tab-1">
                        <!-- 코인주소가 없는 경우 : Start -->
                        <div class="content-item">
                            <h3 class="subtitle mgt20"><span class="coin-name-domestic subtitle">비트코인</span> 입금</h3>
                            <ul class="disc">
                                <li>아래 주소로 <span class="coin-name-domestic">비트코인</span> 입금을 해 주세요</li>
                                <li><span class="coin-name-domestic">비트코인</span> 입금 주소가 없으신 경우에는 [입금 주소 생성하기] 클릭하여 고유 <span class="coin-name-domestic">비트코인</span> 주소를 발급 받으세요</li>
                            </ul>
                            <!-- 입금계좌 미등록 회원인 경우 - 입금주소 생성 버튼 : Start -->
                            <div id="coin-tab-1-create-new-address" class="btn-group">
                                <ul>
                                    <li>
                                        <button id="coin-tab-1-user-wallet-address-create" class="blue big"><span class="coin-symbolic-name">BTC</span> 입금 주소 생성하기</button>
                                    </li>
                                </ul>
                            </div>
                            <!-- //입금계좌 미등록 회원인 경우 - 입금주소 생성 버튼 : End -->
                            <!-- 입금계좌 기등록 회원인 경우 : Start -->
                            <div id="coin-tab-1-user-wallet-address-info" class="my-address-wrap">
                                <div class="my-addr-group">
                                    <p class="my-addr-title">회원님의 <span class="coin-name-domestic my-addr-title">비트코인</span> 입금 주소</p>
                                    <div class="my-addr">
                                        <p id="coin-tab-1-user-wallet-address" class="my-addr-value"></p>
                                        <p class="fl-left">
                                            <button onClick="copyToClipBoardAddress()">주소 복사하기</button>
                                        </p>
                                    </div>
                                    <div id="coin-tab-1-user-wallet-address-extra1" class="extra-addr-group">
                                        <p class="extra-addr-item">
                                            <span id="coin-tab-1-user-wallet-address-extra1-name" class="extra-title">추가주소1</span>
                                            <span id="coin-tab-1-user-wallet-address-extra1-address" class="extra-addr"></span>
                                            <button onClick="copyToClipBoardAddress1($('#coin-tab-1-user-wallet-address-extra1-address').text())">추가 주소 복사하기</button>
                                        </p>
                                        <p class="extra-addr-item">
                                            <span class="val-red font-large" style="margin-left: 93px">※출금시 반드시 입력하십시오</span>
                                        </p>
                                    </div>
                                </div>
                                <div id="coin-tab-1-user-wallet-address-qrcode" class="my-addr-qr">
                                    <!-- QR코드. background로 해당하는 이미지를 import. -->
                                    <!-- <span class="" style="background: url('../img/temp-qrimg.png') no-repeat;">QR코드</span> -->
                                </div>
                            </div>
                            <!-- //입금계좌 기등록 회원인 경우 : End -->
                            <ul class="disc">
                                <li>위의 고유 <span class="coin-name-domestic">비트코인</span> 주소로 <span class="coin-name-domestic">비트코인</span>을 전송하면 회원님의 <span class="coin-name-domestic">비트코인</span> 지갑에 입금 처리 됩니다</li>
                                <li>입금에 소요되는 시간은 10~30분 소요되나 <span class="coin-name-domestic">비트코인</span> 네트워크 상태에 따라 지연 처리될 수 있습니다</li>
                                <li>입금 처리 결과는 입출금-입출금내역 화면에서 확인 하실 수 있습니다</li>
                                <li>입금 시 지갑 주소를 꼭 확인 하십시오</li>
                                <li style="color:red;">※ 암호화폐 이름 및 주소를 반드시 확인하시기 바랍니다.</li>
								<li style="color:red;">※ 오입금시 복구에 수수료가 청구될 수 있으며, 복구가 되지 않을 수도 있습니다.</li>
                            </ul>
                        </div>
                        <!-- //코인주소가 없는 경우 : End -->
                        <!-- //입금계좌 기등록 회원인 경우 : End -->
                    </div>
                    <!-- //입금주소 탭 컨텐츠 : End -->
                    <!-- 출금신청 탭 컨텐츠 : Start -->
                    <div id="coin-tab-2">
                        <div class="content-item">
                            <ul class="disc">
                                <li>회원님의 외부 전자 지갑으로 <span class="coin-name-domestic">비트코인</span>을 출금합니다</li>
                                <li>관리자 확인 후 출금 처리 됩니다</li>
                                <li>출금처리 결과는 입출금-입출금내역 화면에서 확인하실 수 있습니다</li>
                            </ul>
                            <!-- 입력 영역 : Start -->
                            <table class="type01">
                                <colgroup>
                                    <col style="width:180px;">
                                    <col>
                                </colgroup>
                                <tbody>
                                    <tr>
                                        <th>출금가능 수량</th>
                                        <td>
                                            <span id="coin-tab-2-user-poss-qty">0.0</span>
                                            <span class="coin-symbolic-name">BTC</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>1일 출금한도</th>
                                        <td>
                                            <span id="coin-tab-2-daily-limit-qty">0.0</span>
                                            <span class="coin-symbolic-name">BTC</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>1일 잔여한도</th>
                                        <td>
                                            <span id="coin-tab-2-daily-limit-left-qty">0.0</span>
                                            <span class="coin-symbolic-name">BTC</span>
                                            <span class="red">회원님 현재 인증 레벨은 <span class="coin-user-level">1</span><span>단계</span>입니다</span>
                                            <button>
                                                <a href="/alibit/info?tab=1">인증레벨설정</a>
                                            </button>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th><span class="coin-name-domestic">비트코인</span> 출금주소</th>
                                        <td>
                                            <input type="text" id="coin-tab-2-target-address" name="" value="" placeholder="필수입력">
                                            <p class="red bolder info-text">※ 붙여넣기 한 뒤에 반드시 주소와 동일한지 확인 후 출금신청 하십시오.</p>
                                        </td>
                                    </tr>
                                    <tr id="coin-tab-2-etc-address-1">
                                        <th id="coin-tab-2-etc-address-1-name">추가주소1</th>
                                        <td>
                                            <input type="text" id="coin-tab-2-etc-address-1-addr" name="" value="">
                                        </td>
                                    </tr>
                                    <tr id="coin-tab-2-etc-address-2">
                                        <th id="coin-tab-2-etc-address-2-name">추가주소2</th>
                                        <td>
                                            <input type="text" id="coin-tab-2-etc-address-2-addr" name="" value="">
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>출금신청 수량</th>
                                        <td>
                                            <input type="text" id="coin-tab-2-withdraw-request-qty" name="" value="">
                                            <button onClick="setMaxWithdrawQty()">최대</button>
                                            <span>(최소 출금 수량 <span id="coin-tab-2-min-withdraw-qty">0.0</span>)</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>출금 수수료</th>
                                        <td>
                                            <span id="coin-tab-2-withdraw-fee">0.0</span><!-- 코인수량 -->
                                            <span class="coin-symbolic-name">BTC</span><!-- 코인종류 -->
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>실제 출금 수량</th>
                                        <td>
                                            <span id="coin-tab-2-real-withdraw-qty">0.0</span><!-- 코인수량 -->
                                            <span class="coin-symbolic-name">BTC</span><!-- 코인종류 -->
                                        </td>
                                    </tr>
                                    <tr id="coin-tab-2-auth-sms-tr">
                                        <th>휴대폰 SMS 인증</th>
                                        <td>
                                            <button id="coin-tab-2-request-sms-auth-number-btn" onClick="requestSmsAuthNumber();">인증번호 요청</button>
                                            <input type="text" id="coin-tab-2-sms-auth-number" name="" value="" placeholder="SMS 인증번호 6자리">
                                            <!-- <p class="info-text">인증번호 요청 후, 휴대폰으로 전송된 인증번호 6자리를 입력하세요</p> -->
                                            <p>인증번호 요청과 동시에 출금신청수량(금액) 만큼 출금요청(잠금) 상태가 됩니다. 출금요청(잠금) 취소는 입출금 내역에서 가능합니다.</p>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>메모</th>
                                        <td>
                                            <input type="text" id="coin-tab-2-memo" name="" value="">
                                            <p class="info-text">선택 입력 항목으로 출금내역 확인시 참고내용을 입력하시면 됩니다</p>
                                        </td>
                                    </tr>
                                    <tr id="coin-tab-2-auth-otp-tr">
                                        <th>OTP 인증번호 입력</th>
                                        <td>
                                            <input type="text" id="coin-tab-2-otp-auth-number" name="" value="" placeholder="OTP 인증번호 6자리">
                                            <!-- <p class="info-text">OTP앱 실행 후 표시 된 인증번호 6자리를 입력하세요</p> -->
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                            <!-- //입력 영역 : End -->
                            <ul>
								<li style="color:red;">※ 암호화폐 특성 상 출금 신청이 완료되어 블록체인에 기록되면 취소가 불가능 합니다.</li>
								<li style="color:red;">※ 송금과정은 블록체인 네트워크에서 처리되므로 알리비트에서 도움을 드릴수가 없습니다. 반드시 출금 주소를 반복 확인하시고 출금신청 하시기 바랍니다.</li>
							</ul>
                            <!-- 출금 요청 버튼 : Start -->
                            <div class="btn-group mgt20">
                                <ul>
                                    <li>
                                        <button onClick="requestWithDraw()" class="blue">출금신청</button>
                                        <button onClick="cancelRequestWithdraw()">취소</button>
                                    </li>
                                </ul>
                            </div>
                            <!-- //출금 요청 버튼 : End -->
                        </div>
                    </div>
                    <!-- //출금신청 탭 컨텐츠 : End -->
                    <!-- 입출금내역 탭 컨텐츠 : Start -->
                    <div id="coin-tab-3">
                        <div class="dw-history">
                            <!-- 조회 옵션 : Start -->
                            <div style="margin-top: 20px">
                                <select id="coin-tab-3-select-dw-req-type" onChange="getDWMgrList()" class="select-box">
                                    <option selected value="0">전체</option>
                                    <option value="1">입금</option>
                                    <option value="2">출금</option>
                                </select>

                                <div class="calendar">
                                    <input type="text" name="calendar" id="coin-tab-3-dw-list-startDate" required="" value="2019-04-14">
                                    <span>~</span>
                                    <input type="text" name="calendar" id="coin-tab-3-dw-list-endDate" required="" value="2019-04-15">

                                    <button onClick="getDWMgrList(__G_Prev_Selected_CoinNo);">조회</button>
                                </div>
                            </div>

                            <!-- //조회 옵션 : End -->
                            <!-- 조회 건수 표시 : Start -->

                            <p class="case-info">총 <b id="coin-tab-3-dw-list-total-count" class="blue">0</b>건의 자료가 조회 되었습니다</p>

                            <!-- //조회 건수 표시 : End -->

                            <!-- 데이터 영역 : Start -->
                            <div>
                                <table class="line">
                                    <colgroup>
                                        <col>
                                        <col style="width: 160px">
                                        <col>
                                        <col>
                                        <col style="width: 250px">
                                        <col>
                                        <col>
                                    </colgroup>
                                    <thead>
                                        <tr>
                                            <th>구분</th>
                                            <th>요청일시</th>
                                            <th class="alRight">금액(수량)</th>
                                            <th class="alRight">수수료</th>
                                            <th>입/출금주소</th>
                                            <th>진행상태</th>
                                            <th>취소</th>
                                        </tr>
                                    </thead>
                                    <tbody id="coin-tab-3-dw-list-tbody">
                                        <tr>
                                            <td colspan="7">You have no Deposit/Withdraw History </td>
                                        </tr>
                                    </tbody>
                                </table>
                                <!-- 페이징 : Start -->
                                <ul id="coin-tab-3-dw-list-pagination" class="pagination">
                                    <li><a class="btn-page first"></a></li>
                                    <li><a class="btn-page prev"></a></li>
                                    <li><a href="#" class="active">1</a></li><!-- 선택된 번호의 a 태그에 acitve 클래스 추가 -->
                                    <li><a class="btn-page next"></a></li>
                                    <li><a class="btn-page last"></a></li>
                                </ul>
                                <!-- //페이징 : End -->
                            </div>
                            <!-- //데이터 영역 : End -->
                        </div>
                    </div>
                    <!-- //입출금내역 탭 컨텐츠 : End -->
                </div>
                <!-- //코인일 때 : End -->
            </div>
            <!-- //CONTENT INNER : End -->
        </div>
        <!--입금 출금 탭 영역 끝-->
    </article>

</section>
<!--컨텐츠 내용 영역 끝-->


<!--입금코드 발생 안내 팝업 시작-->
<div class="popup deposit-code">
    <div class="pop-title">
        <span>입금신청 확인</span>
        <%-- <span class="top-close top-close-deposit-code">
            <img src="/resources/img/btn-alert-close.png" alt="닫기버튼">
        </span> --%>
        
    </div>
    <div class="popup-cont">
        <div class="content-item">
            <div>
                <h2 class="tabletitle">받는 사람</h2>
                <table>
                    <colgroup>
                        <col style="width:110px;">
                        <col>
                    </colgroup>
                    <tbody>
                        <tr>
                            <th>계좌번호</th>
                            <td id="popup-deposit-code-exchange-account-number"></td>
                        </tr>
                        <tr>
                            <th>입금은행</th>
                            <td id="popup-deposit-code-exchange-bank-name"></td>
                        </tr>
                        <tr>
                            <th>계좌 소유주</th>
                            <td id="popup-deposit-code-exchange-account-holder-name"></td>
                        </tr>
                    </tbody>
                </table>
            </div>

            <div>
                <h2 class="tabletitle">보내는 사람</h2>
                <table>
                    <colgroup>
                        <col style="width:110px;">
                        <col>
                    </colgroup>
                    <tbody>
                        <tr>
                            <th>등록된계좌</th>
                            <td id="popup-deposit-code-sender-account-number"></td>
                        </tr>
                        <tr>
                            <th>은행명</th>
                            <td id="popup-deposit-code-sender-bank-name"></td>
                        </tr>
                        <tr>
                            <th>계좌 소유주</th>
                            <td id="popup-deposit-code-sender-account-holder-name"></td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>

        <div class="content-item">
            <table class="mgt20">
                <colgroup>
                    <col style="width:160px">
                    <col>
                </colgroup>
                <tbody>
                    <tr>
                        <th>
                            입금코드
                        </th>
                        <td>
                            <span id="popup-deposit-code-sender-deposit-code" class="code"></span>
                            <button onClick="copyToClipBoardDepositCode()">복사하기</button>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            입금금액
                        </th>
                        <td id="popup-deposit-code-sender-amount"></td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <span>위 입금코드를 <span class="red">받는통장메모</span>에 반드시 기재하십시오</span>
                        </td>
                    </tr>
                </tbody>
            </table>

            <table class="mgt20">
                <colgroup>
                    <col style="width: 110px">
                    <col>
                    <col style="width: 110px">
                    <col>
                </colgroup>
                <thead>
                    <tr>
                        <th>은행</th>
                        <th>입금코드 입력란 명칭</th>
                        <th>은행</th>
                        <th>입금코드 입력란 명칭</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <th>NH농협은행</th>
                        <td>입금통장표시내용</td>
                        <th>카카오뱅크</th>
                        <td>받는 분에게 표시</td>

                    </tr>
                    <tr>
                        <th>KB국민은행</th>
                        <td>입금통장표시내용</td>
                        <th>케이뱅크</th>
                        <td>받는분통장표시</td>
                    </tr>
                    <tr>
                        <th>KEB하나은행</th>
                        <td>받는분 통장 표시</td>
                        <th>한국씨티은행</th>
                        <td>입금통장 표시내용</td>
                    </tr>
                    <tr>
                        <th>신한은행</th>
                        <td>받는통장 메모</td>
                        <th>SC제일은행</th>
                        <td>받는통장에 남길말</td>
                    </tr>
                    <tr>
                        <th>IBK기업은행</th>
                        <td>받는분표시내용</td>
                        <th>KDB산업은행</th>
                        <td>입금계좌기록내용</td>
                    </tr>
                    <tr>
                        <th>우리은행</th>
                        <td>받는분 통장 표시내용<br>(받는통장표시내용)</td>
                        <th>부산은행</th>
                        <td>입금계좌기록사항</td>
                    </tr>
                    <tr>
                        <th>우체국</th>
                        <td>입금통장표시</td>
                        <th>대구은행</th>
                        <td>입금통장인지내용</td>
                    </tr>
                    <tr>
                        <th>새마을금고</th>
                        <td>입금통장표시내용</td>
                        <th></th>
                        <td></td>
                    </tr>
                </tbody>
            </table>
        </div>

        <!-- 확인 버튼 : Start -->
        <div class="btn-group mgt20">
            <button onClick="moneyChargingRequest()" class="blue big">확인</button>
            <%-- <button onClick="$('.deposit-code').hide()" class="big">취소</button> --%>
        </div>
        <!-- //확인 버튼 : End -->

    </div>

</div>
<!--입금코드 발생 안내 팝업 끝-->

<!--은행별 점검시간 안내 팝업 시작-->
<div class="popup bank">
    <div class="pop-title">
        <span>은행별 점검시간 안내</span>
        <span class="top-close top-close-bank">
            <img src="/resources/img/btn-alert-close.png" alt="닫기버튼">
        </span>
    </div>
    <div class="popup-cont">
        <h2>은행별 점검시간 안내</h2>

        <p>- KRW 출금은 은행과 연계되어있기 때문에 은행 점검시간(비정기 점검 포함)에는 출금이 제한됩니다.</p>
        <p>- 하단 은행 점검시간 및 정기점검을 확인하시기 바랍니다.</p>
        <table class="mgt10">
            <colgroup>
                <col style="width: 140px ">
                <col>
                <col>
            </colgroup>
            <thead>
                <tr>
                    <th>은행</th>
                    <th>점검시간</th>
                    <th>정기점검</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>NH농협은행</td>
                    <td>00:00~00:30</td>
                    <td>00:00~04:00 (매월 셋째주 월요일)</td>
                </tr>
                <tr>
                    <td>KB국민은행</td>
                    <td>23:50~00:05</td>
                    <td> 00:00~07:00 (매월 셋째주 일요일)</td>
                </tr>
                <tr>
                    <td>KEB하나은행</td>
                    <td>00:00~00:10</td>
                    <td>00:00~04:00 (매월 셋째주 월요일)</td>
                </tr>
                <tr>
                    <td>신한은행</td>
                    <td>00:00~00:10</td>
                    <td></td>
                </tr>
                <tr>
                    <td>IBK기업은행</td>
                    <td>00:00~00:30</td>
                    <td></td>
                </tr>
                <tr>
                    <td>우리은행</td>
                    <td>(평일)00:00~00:10, (금요일)00:00~00:40</td>
                    <td>02:00~06:00 (매월 둘째주 일요일)</td>
                </tr>
                <tr>
                    <td>우체국</td>
                    <td>04:00~05:00</td>
                    <td></td>
                </tr>
                <tr>
                    <td>새마을금고</td>
                    <td></td>
                    <td>23:00~07:00 (매월 두번째 토요일)</td>
                </tr>
                <tr>
                    <td>카카오뱅크</td>
                    <td>23:57~00:03</td>
                    <td></td>
                </tr>
                <tr>
                    <td>케이뱅크</td>
                    <td>23:50~00:10</td>
                    <td></td>
                </tr>
                <tr>
                    <td>한국씨티은행</td>
                    <td>23:40~00:05</td>
                    <td></td>
                </tr>
                <tr>
                    <td>SC제일은행</td>
                    <td>23:59~00:05</td>
                    <td></td>
                </tr>
                <tr>
                    <td>KDB산업은행</td>
                    <td>(월~토)00:00~0:30, (일요일)00:00~04:30</td>
                    <td></td>
                </tr>
                <tr>
                    <td>부산은행</td>
                    <td></td>
                    <td>00:00~03:00 (매월 첫째주 월요일, 셋째주 월요일)</td>
                </tr>
                <tr>
                    <td>대구은행</td>
                    <td></td>
                    <td>02:00~06:00 (매월 셋째주 일요일)</td>
                </tr>

            </tbody>
        </table>
        <div class="btn-group mgt20">
            <button type="submit" class="top-close top-close-bank big">확인 </button>
        </div>
    </div>

</div>
<!--은행별 점검시간 안내 팝업 끝-->
<!--코인입출금 상세 안내 팝업 시작-->
<div class="popup detail">
    <div class="pop-title">
        <span>암호화폐 입출금 상세보기</span>
        <span class="top-close top-close-detail">
            <img src="/resources/img/btn-alert-close.png" alt="닫기버튼">
        </span>
    </div>
    <div class="popup-cont">
        <div>
            <h2>입출금 요청 정보</h2>
            <table class="line">
                <colgroup>
                    <col style="width:120px;">
                    <col>
                </colgroup>
                <tbody>
                    <tr>
                        <th>암호화폐명</th>
                        <td><span class="coin-dw-detail-popup-coin-name"></span></td>
                    </tr>
                    <tr>
                        <th>입출금구분</th>
                        <td id="coin-dw-detail-popup-dw-req-type-code"></td>
                    </tr>
                    <tr>
                        <th>입출금요청수량</th>
                        <td><span id="coin-dw-detail-popup-req-qty"></span> <span class="coin-dw-detail-popup-coin-symbol">BTC</span></td>
                    </tr>
                    <tr>
                        <th>수수료</th>
                        <td><span id="coin-dw-detail-popup-fee-qty"></span> <span class="coin-dw-detail-popup-coin-symbol">BTC</span></td>
                    </tr>
                    <tr>
                        <th>요청일시</th>
                        <td><span id="coin-dw-detail-popup-req-datetime"></span></td>
                    </tr>
                    <tr>
                        <th>요청매체</th>
                        <td><span id="coin-dw-detail-popup-req-channel"></span></td>
                    </tr>
                    <tr>
                        <th>승인일시</th>
                        <td><span id="coin-dw-detail-popup-appr-datetime"></span></td>
                    </tr>
                    <tr>
                        <th>상태</th>
                        <td><span id="coin-dw-detail-popup-proc-status"></span></td>
                    </tr>
                </tbody>
            </table>
            <h2 class="mgt20">블록체인 처리 정보</h2>
            <table class="line">
                <colgroup>
                    <col style="width:120px;">
                    <col>
                </colgroup>
                <tbody>
                    <tr>
                        <th>계좌정보</th>
                        <td><span id="coin-dw-detail-popup-account-info"></span></td>
                    </tr>
                    <tr>
                        <th>입출금주소</th>
                        <td><span id="coin-dw-detail-popup-coin-address"></span></td>
                    </tr>
                    <tr>
                        <th>트랜잭션ID</th>
                        <td><span id="coin-dw-detail-popup-txid"></span></td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="btn-group mgt20">
            <button class="top-close top-close-detail big">확인 </button>
        </div>
    </div>

</div>
<!--코인입출금 상세 안내 팝업 끝-->
    
<script src="/resources/js/yahobit/wallet/yahobit.wallet.js?v=<spring:message code="yahobit.wallet.js.version"/>"></script>
<script src="/resources/js/qrcode/qrcode.min.js"></script>

<script>
    $(document).ready(function() {
        $("#tab-group-1").tabs();
        $("#tab-group-2").tabs();

        $("#datepicker").datepicker({
            inline: true
        });

        // 회원 기본정보 셋팅
        __G_User_Auth_Level = parseInt('${authLevel}');
        __G_User_Auth_Mean_Code = parseInt('${authMeanCd}');
        __G_Default_Coin_Type = parseInt('${defaultCoinType}');

        // 은행코드 정보 셋팅
        __G_Bank_Code_List = JSON.parse('${bankCodeInfoList}');
        $('#money-tab-1-bank-list').empty();
        $('#money-tab-1-bank-list').append($('<option value="9999">은행선택</option>'));
        __G_Bank_Code_List.forEach(function(item) {
            $('#money-tab-1-bank-list').append($('<option value="' + item.CODE_STR_VAL + '">' + item.CODE_VAL_NM + '</option>'));
            _G_Bank_Code_Map.put(item.CODE_STR_VAL, item.CODE_VAL_NM);
        });
        
        // 거래소 은행 입금계좌 셋팅
        __G_Exchange_Bank_Accnt_Info = JSON.parse('${exchangeBankAccntInfo}');
        $('#money-tab-1-exchange-account-number').text( getParam(__G_Exchange_Bank_Accnt_Info, 'BANK_ACCNT_NO', '') );
        $('#money-tab-1-exchange-bank-name').text( _G_Bank_Code_Map.get(getParam(__G_Exchange_Bank_Accnt_Info, 'BANK_CD', '')) );
        $('#money-tab-1-exchange-account-holder-name').text( getParam(__G_Exchange_Bank_Accnt_Info, 'ACCNT_HOLDER_NM', '') );
        // Popup 셋팅
        $('#popup-deposit-code-exchange-account-number').text( getParam(__G_Exchange_Bank_Accnt_Info, 'BANK_ACCNT_NO', '') );
        $('#popup-deposit-code-exchange-bank-name').text( _G_Bank_Code_Map.get(getParam(__G_Exchange_Bank_Accnt_Info, 'BANK_CD', '')) );
        $('#popup-deposit-code-exchange-account-holder-name').text( getParam(__G_Exchange_Bank_Accnt_Info, 'ACCNT_HOLDER_NM', '') );

        // 코인관리 기준정보 가져옴
        var coinMgrRefinfoJson = JSON.parse('${coinMgtRefInfo}');
        Object.keys(coinMgrRefinfoJson).forEach(function(key) {
            __G_CoinMgtRef_Map.put(key, coinMgrRefinfoJson[key]);
        });

        //입출금내역 달력 설정
        $("#money-tab-3-dw-list-endDate").datepicker({
            inline: true,
            dateFormat: 'yy.mm.dd',
            maxDate: $.datepicker.formatDate('yy.mm.dd', new Date()),
            
            onSelect: function() {
                $("#coin-tab-3-dw-list-startDate").datepicker("option","maxDate", $("#money-tab-3-dw-list-endDate").datepicker('getDate'))
                if ( $.datepicker.formatDate('yymmdd', $("#money-tab-3-dw-list-endDate").datepicker('getDate')) 
                        < $.datepicker.formatDate('yymmdd', $("#money-tab-3-dw-list-startDate").datepicker('getDate')) ) {
                    $('#money-tab-3-dw-list-startDate').val($("#money-tab-3-dw-list-endDate").datepicker('getDate'));
                }
            }
        });
        $("#money-tab-3-dw-list-startDate").datepicker({
            inline: true,
            dateFormat: 'yy.mm.dd',
            maxDate: $("#money-tab-3-dw-list-endDate").datepicker('getDate')
        });
        $("#coin-tab-3-dw-list-endDate").datepicker({
            inline: true,
            dateFormat: 'yy.mm.dd',
            maxDate: $.datepicker.formatDate('yy.mm.dd', new Date()),
            
            onSelect: function() {
                $("#coin-tab-3-dw-list-startDate").datepicker("option","maxDate", $("#coin-tab-3-dw-list-endDate").datepicker('getDate'))
                if ( $.datepicker.formatDate('yymmdd', $("#coin-tab-3-dw-list-endDate").datepicker('getDate')) 
                        < $.datepicker.formatDate('yymmdd', $("#coin-tab-3-dw-list-startDate").datepicker('getDate')) ) {
                    $('#coin-tab-3-dw-list-startDate').val($("#coin-tab-3-dw-list-endDate").datepicker('getDate'));
                }
            }
        });
        $("#coin-tab-3-dw-list-startDate").datepicker({
            inline: true,
            dateFormat: 'yy.mm.dd',
            maxDate: $("#coin-tab-3-dw-list-endDate").datepicker('getDate')
        });

        // 오늘 날짜로 세팅
        $('#money-tab-3-dw-list-startDate').val($.datepicker.formatDate('yy.mm.dd', new Date()));
        $('#money-tab-3-dw-list-endDate').val($.datepicker.formatDate('yy.mm.dd', new Date()));
        $('#coin-tab-3-dw-list-startDate').val($.datepicker.formatDate('yy.mm.dd', new Date()));
        $('#coin-tab-3-dw-list-endDate').val($.datepicker.formatDate('yy.mm.dd', new Date()));

        // 기본 Money Tab 보이고 Coin Tab 감춘다.
        $('#tab-group-1').show();
        $('#tab-group-2').hide();
        __G_Prev_Selected_CoinNo = '10';
        doProcessRFrame('MONEY', '10', 'KRW', 'KRW');

        // LNB영역 기본 Coin List 그리기 (기본 통화 및 거래소 상장 코인)
        drawLnbList('${defaultCoinInfo}', '${exchangeCoinInfo}');
        // 사용자 Possession 정보 가져오기
        //setTimeout(getLnbPossessionInfo, 50);
        getLnbPossessionInfo();

        // 회원의 은행계좌정보를 가져온다.
        getUserBankAccntInfo();
    });

    // 전체동의 check
    $('#agreeAll').on('click', function() {
		if($(this).is(':checked')) {
			$('.agree-box').find('.agreeCheck').prop('checked', $(this).is(':checked'));
		} else {
			$('.agree-box').find('input').prop('checked', $(this).is(':checked'));
		}
	});

    // 보유코인 체크박스 클릭
    $('#chk_box_possess_coin').click(function() {
        var checked = $("input:checkbox[id='chk_box_possess_coin']").is(":checked");
        showOnlyPossessedCoin(checked);
    });
    // 코인 심볼/이름 검색
    $('#search_coin_text').keyup(function() {
        searchCoinSymbolOrName($(this).val());
    });
    /*북마크*/
    $('.bookmark').click(function() {
        $(this).toggleClass('mark-on');
    });

    // 은행시간, 입금코드, 입출금상세내역 팝업 열고 닫기
    $(document).ready(function() {
        $('.pop-bank').click(function() {
            $('.bank').show();
        });
        /* $('.pop-code').click(function() {
            $('.deposit-code').show();
        }); */

        $('.top-close-deposit-code').click(function() {
            $('.deposit-code').hide();
        });
        $('.top-close-bank').click(function() {
            $('.bank').hide();
        });
        $('.top-close-detail').click(function() {
            $('.detail').hide();
        });
    });
    
    $('#money-tab-1-bank-account-holder-name').on('keyup', function() {
    	let $this = $(this);
    	$this.val($this.val().replace(/[^A-Za-z0-9가-힣ㄱ-ㅎㅏ-ㅣ]/gi, ''));
    });
    
    $('#money-tab-1-bank-account-holder-birthday').on('keyup', function() {
    	let $this = $(this);
    	$this.val($this.val().replace(/[^0-9]/g, ''));
    });
    
    $('#money-tab-1-bank-account-number').on('keyup', function() {
    	let $this = $(this);
    	$this.val($this.val().replace(/[^0-9]/g, ''));
    });
</script>

<Script id="template-list-empty" type="text/template">
	<tr>
        <td colspan="{{ColSpan}}">You have no data </td>
	</tr>
</Script>
<Script id="template-dw-list-empty" type="text/template">
	<tr>
        <td colspan="{{ColSpan}}">You have no Deposit/Withdraw History </td>
	</tr>
</Script>

<script id="template-wallet-lnb-coin-list" type="text/template">
    <tr class="{{IsActive}}">
        <td>
            <input type="hidden" name="coinKind" class="coinKind" value="{{CoinKind}}" />
            <input type="hidden" name="coinNo" class="coinNo" value="{{CoinNo}}" />
            <input type="hidden" name="coinSymbolChosung" class="coinSymbolChosung" value="{{CoinSymbolChosung}}" />
            <span class="coin-symbol">
                <img src="/resources/img/coin-symbol/{{CoinSymbolicName}}.png" alt="coin">
            </span>
        </td>
        <td>
            <p class="coin-name">{{ItemDemesticName}}</p>
            <p class="coin-ratio">
                <span id="possession-percent-{{CoinNo}}" class="ratio-value" style="width: {{PossessPercent}}%;"></span>
            </p>
        </td>
        <td>
            <p class="vertual-coin-type">
                <span id="possession-qty-{{CoinNo}}">{{PossessQty}}</span>
                <span>{{CoinSymbolicName}}</span>
            </p>
            <p class="country-real-money">
                <span id="estimate-qty-{{CoinNo}}">{{EstimateQty}}</span>
                <span>{{BaseEstimateSymbolicName}}</span>
            </p>
        </td>
    </tr>
</script>

<script id="template-wallet-deposit-withdraw-list" type="text/template">
    <tr onClick="doActionClickDwListTr('{{ReqSeqNo}}')">
        <td class="{{DwGubunColor}} alCenter">
            <input type="hidden" class="coin-tab-3-dw-list-req-seq-no" value="{{ReqSeqNo}}">
            {{DwGubun}}
        </td>
        <td class="alCenter">{{ReqDateTime}}</td>
        <td class="{{DwGubunColor}} alRight">{{ReqQty}}</td>
        <td class="alRight">{{Fee}}</td>
        <td onclick="event.cancelBubble = true;">
            <p class="ellips-group">
                <span onClick="doActionClickDwListTr('{{ReqSeqNo}}')" class="text-ellips">{{Address}}</span>
                <button onClick="copyToClipBoardAddress1('{{Address}}')">복사</button>
            </p>
        </td>
        <td class="alCenter">{{ReqStatProcText}}</td>
        <td onclick="event.cancelBubble = true;" class="alCenter">
            {{CancelButtonHTML}}
        </td>
    </tr>
</script>

<script id="template-wallet-money-deposit-withdraw-list" type="text/template">
    <tr>
        <td class="{{DwGubunColor}} alCenter">
            <input type="hidden" class="money-tab-3-dw-list-req-seq-no" value="{{ReqSeqNo}}">
            {{DwGubun}}
        </td>
        <td class="alCenter">{{ReqDateTime}}</td>
        <td class="{{DwGubunColor}} alRight">{{ReqQty}}</td>
        <td class="alRight">{{Fee}}</td>
        <td class="alCenter">{{BankName}}</td>
        <td class="alCenter">{{Address}}</td>
        <td class="alCenter">{{FinCode}}</td>
        <td class="alCenter">{{ReqStatProcText}}</td>
        <td class="alCenter">
            {{CancelButtonHTML}}
        </td>
    </tr>
</script>
