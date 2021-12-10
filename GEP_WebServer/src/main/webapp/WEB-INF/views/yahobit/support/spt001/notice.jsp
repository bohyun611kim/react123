<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/taglib/taglib.jsp" %>

<!--컨텐츠 내용 영역 시작-->
<section class="main">

	<article class="layoutD">

		<aside>
			<h1 class="menutitle">
				고객지원
			</h1>
			<ul class="vtab">
				<li><a onClick="javascript:toggleNoticeList(0, true);" href="#vtab1" class="vselected">공지사항</a></li>
				<li><a onClick="javascript:toggleEventList(0, true);" href="#vtab2">이벤트</a></li>
				<li><a href="#vtab3">이용안내</a></li>
				<li><a href="#vtab4">수수료안내</a></li>
				<li><a href="#vtab5">보안가이드</a></li>
				<li><a href="#vtab6">자주하는 질문</a></li>
				<c:if test="${sessionScope.userInfo.loginYn == 1}">
				<li><a href="#vtab7">1:1 문의</a></li>
				<li><a href="#vtab8">문의내역</a></li>
				</c:if>
			</ul>

		</aside>

		<div class="content">
			<ul class="vpanel">
				
				<!--공지사항 시작-->
				<li id="vtab1" class="notice">
					<h1 class="subtitle">
						공지사항
					</h1>
					<!--공지사항 검색및리스트 시작 : 내용이 보이면 이 영역은 안보입니다.-->
					<div id="notice-list" class="notice-list" style="display: block;">
						<div class="search">
							<input type="text" id="notice-search-text"><button id="notice-search">검색</button>
						</div>

						<!--<공지사항 리스트 테이블 시작-->
						<table>
							<colgroup>
								<col>
								<col style="width: 160px">
							</colgroup>
							<thead>
								<tr>
									<th>제목</th>
									<th>등록일</th>
								</tr>
							</thead>
							<tbody id="notice_list_table_tbody">
								<!-- <tr>
									<td class="new er">
										<a href="#" onClick="javascript:toggleNoticeList(0, false);"></a>
									</td>
									<td></td>
								</tr> -->
							</tbody>
						</table>
						<!--<공지사항 리스트 테이블 끝-->

						<!-- 페이징 : Start -->
						<ul id="notice-pagination" class="pagination">
							<li><a class="btn-page first"></a></li>
							<li><a class="btn-page prev"></a></li>
							<li><a href="#" class="active">1</a></li>
							<li><a href="#">2</a></li>
							<li><a class="btn-page next"></a></li>
							<li><a class="btn-page last"></a></li>
						</ul>
						<!-- //페이징 : End -->
					</div>
					<!--공지사항 검색 및 리스트 끝-->

					<!--공지사항 내용보기 영역 시작 : 리스트가 보이면 이 영역은 안보입니다. -->
					<div id="notice-conts" class="notice-conts" style="display: none;">
						<h1 id="notice-conts-title">
							<span id="notice-conts-date"></span>
						</h1>

						<div id="notice-detail" class="notice-detail" style="word-break:break-all">
							<p> </p>
							<h2></h2>
						</div>

						<div class="fl-right mgt20">
							<button onClick="javascript:toggleNoticeList(0, true);" class="big">목록</button>
						</div>

					</div>
					<!--공지사항 내용보기 영역 끝-->

				</li>
				<!--공지사항 끝-->

				<!--이벤트 시작-->
				<li id="vtab2" class="event" style="display:none;">
					<h1 class="subtitle">
						이벤트
					</h1>
					
					<!--이벤트 리스트 시작 : 내용이 보이면 이 영역은 안보입니다.-->
					<div id="event-list" class="event-list" style="display: block;">
						<h2 class="tabletitle">이벤트 및 결과</h2>
						
						<!--이벤트 리스트 테이블 시작-->
						<table>
							<colgroup>
								<col>
								<col style="width: 160px">
								<col style="width: 80px">
							</colgroup>
							<thead>
								<tr>
									<th>제목</th>
									<th>등록일</th>
									<th>진행</th>
								</tr>
							</thead>
							<tbody id="event_list_table_tbody">
								<!-- <tr>
									<td class="bold">
									</td>
									<td></td>
									<td class="on"></td>
								</tr> -->
							</tbody>
						</table>
						<!--<이벤트 리스트 테이블 끝-->

						<!-- 페이징 : Start -->
						<ul id="event-pagination" class="pagination">
							<li><a class="btn-page first"></a></li>
							<li><a class="btn-page prev"></a></li>
							<li><a href="#" class="active">1</a></li>
							<li><a class="btn-page next"></a></li>
							<li><a class="btn-page last"></a></li>
						</ul>
						<!-- //페이징 : End -->
					</div>
					<!--이벤트및 리스트 끝-->

					<!--이벤트 내용보기 영역 시작 : 리스트가 보이면 이 영역은 안 보입니다.-->
					<div id="event-conts" class="event-conts" style="display: none;">
						<h1 id="event-conts-title">
							<span></span>
						</h1>
						<div id="event-detail" class="event-detail">
						</div>
						<div class="fl-right mgt20">
							<button onClick="javascript:toggleEventList(0, true);" class="big">목록</button>
						</div>

					</div>
					<!--이벤트 내용보기 영역 끝-->

				</li>
				<!--이벤트 끝-->
				
				<!--이용안내 시작-->
				<li id="vtab3" class="useinfo" style="display:none;">
					<h1 class="subtitle">
						이용안내
					</h1>
					
					<h2 class="tabletitle">1. 마켓구분</h2>
					<p>- 메이저 마켓 : KRW(원화)로 암호화폐를 거래할 수 있습니다.</p>
					<p>- 프리 마켓 : YEP(옙 토큰)로 다른 암호화폐를 거래할 수 있습니다.</p>
					
					<!-- 인증레벨 별 원화 출금한도 : Start -->
					<div class="content-item">
						<h2 class="tabletitle">2. 인증레벨 별 입출금한도</h2>
						<div>
							<table class="type03 line">
								<colgroup>
									<col style="width: 80px;">
									<col style="width: 110px;">
									<col>
									<col>
									<col>
									<col>
								</colgroup>
								<thead>
									<tr>
										<th colspan="3">구분</th>
										<th>레벨1</th>
										<th>레벨2</th>
										<th>레벨3</th>
										<th>레벨4</th>
										<th>레벨5</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<th colspan="3">인증방법</th>
										
										<td>이메일 인증<br />(자동 인증)</td>
										<td>휴대폰 본인인증<br />(자동 인증)</td>
										<td>OTP 등록<br />(자동 인증)</td>
										<td>신분증 인증<br />(별도 심사)</td>
										<td>거주지 인증<br />(별도 심사)</td>
									</tr>
									<tr>
										<th rowspan="2">입금한도</th>
										<th colspan="2">KRW</th>
										<td>-</td>
										<td>-</td>
										<td>무제한</td>
										<td>무제한</td>
										<td>무제한</td>
									</tr>
									<tr>
										<th colspan="2">암호화폐</th>
										<td>-</td>
										<td>무제한</td>
										<td>무제한</td>
										<td>무제한</td>
										<td>무제한</td>
									</tr>
									 <tr>
										<th rowspan="2">출금한도</th>
										 <th rowspan="2">KRW+암호화폐<br>(1일 한도)</th>
										<th>1회</th>
										<td id="level1_once">-</td>
										<td id="level2_once">5,000,000원</td>
										<td id="level3_once">100,000,000원</td>
										<td id="level4_once">300,000,000원</td>
										<td id="level5_once">별도심사</td>
									</tr>
									 <tr>
										<th>1일</th>
										<td id="level1_day">-</td>
										<td id="level2_day">5,000,000원</td>
										<td id="level3_day">100,000,000원</td>
										<td id="level4_day">300,000,000원</td>
										<td id="level5_day">별도심사</td>
									</tr>
								</tbody>
							</table>
							<ul class="disc">
								<li>출금한도는 KRW와 암호화폐의 통합한도이며, 암호화폐의 경우 출금 시점의 원화 환산액 기준으로 출금한도가 적용 됩니다.</li>
								<li>레벨 4, 5는 심사기준에 따라 별도의 절차를 거쳐 승인합니다. 1:1 문의를 통해 문의하세요.</li>
								<li>부정거래가 의심되는 경우 입출금이 제한됩니다.</li>
							</ul>
						</div>
					</div>
					<!-- //인증레벨 별 원화 출금한도 : End -->
	
					<!-- 최소 주문가능 금액 : Start -->
					<div class="content-item">
						<h2 class="tabletitle">3. 최소 주문가능 금액</h2>
						<table class="type03 line">
							<thead>
								<tr>
									<th>메이저 마켓</th>
									<th>프리마켓</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>2,000 KRW</td>
									<td>20 YEP</td>
								</tr>
							</tbody>
						</table>
					</div>
					<!-- //최소 주문가능 금액 : End -->
					
					<!--주문유형 시작-->
					<div class="content-item">
						<h2 class="tabletitle">4. 주문유형</h2>
						<ul class="disc">
							<li>지정가 주문 : 체결 가격과 수량을 입력하여 주문하므로, 입력한 가격이 도달하지 않으면 주문이 체결되지 않으며 체결될때까지 미체결 주문에서 확인가능 합니다.</li>
							<li>시장가 주문 : 체결 수량만 입력하여 주문하므로, 시장가격으로 즉시 주문이 체결됩니다. 따라서, 주문 입력시 주의하여야 합니다.</li>
							<li> 자동채굴 주문 : 현재가로 매수, 매도 한번의 거래량만을 즉시 발생시키는 주문으로 호가창에는 주문이 들어가지 않습니다. YEP 채굴을 위해 사용합니다.</li>
						</ul>
					</div>
					<!--주문유형 끝-->
					<!-- 최소 주문가능 금액 : Start -->
					<div class="content-item">
						<h2 class="tabletitle">5. 호가 표시 단위</h2>
						<table class="type03 line">
							<thead>
								<tr>
									<th colspan="2">메이저 마켓(암호화폐 가격에 따라 차등)</th>
									<th colspan="2">프리 마켓(차등 없음)</th>
								</tr>
								<tr>
									<th>암호화폐 1개 가격</th>
									<th>호가단위</th>
									<th>암호화폐 1개 가격</th>
									<th> 호가단위</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<th>3,000,000원 이상</th>
									<td>5,000원</td>
									<th>가격무관</th>
									<td>0.0001 YEP</td>
								</tr>
								<tr>
									<th>2,000,000원 미만</th>
									<td>1,000원</td>
									<th></th>
									<td></td>
								</tr>
								<tr>
									<th>1,000,000원 미만</th>
									<td>500원</td>
									<th></th>
									<td></td>
								</tr>
								<tr>
									<th>100,000원 미만</th>
									<td>100원</td>
									<th></th>
									<td></td>
								</tr>
								<tr>
									<th>10,000원 미만</th>
									<td>50원</td>
									<th></th>
									<td></td>
								</tr>
								<tr>
									<th>5,000원 미만</th>
									<td>25원</td>
									<th></th>
									<td></td>
								</tr>
								<tr>
									<th>3,000원 미만</th>
									<td>10원</td>
									<th></th>
									<td></td>
								</tr>
								<tr>
									<th>1,000원 미만</th>
									<td>5원</td>
									<th></th>
									<td></td>
								</tr>
								<tr>
									<th>300원 미만</th>
									<td>1원</td>
									<th></th>
									<td></td>
								</tr>
								<tr>
									<th>10원 미만</th>
									<td>0.1원</td>
									<th></th>
									<td></td>
								</tr>
								<tr>
									<th>1원 미만</th>
									<td>0.01원</td>
									<th></th>
									<td></td>
								</tr>
							</tbody>
						</table>
						<p>
							   ※ 암호화폐 가격에 따른 호가단위는 위 호가 단위를 기본 참조하여 시장상황에 따라 호가단위를 조정 변경합니다.<br>
							   ※ 위 호가단위는 암호화폐 가격에 따라 실시간으로 변동되지는 않으며, 각 암호화폐 및 시장상황에 따라 달라 질 수 있습니다.
						</p>
					</div>
				</li>
				<!--이용안내 끝-->
	
				<!--수수료안내 시작-->
				<li id="vtab4" class="feeinfo" style="display:none;">
					<h1 class="subtitle">
						수수료안내
					</h1>
					<p>- 거래소 이용 시 거래 수수료와 입/출금 수수료가 아래와 같이 적용 됩니다</p>
						<!-- 거래 수수료 : Start -->
						<div class="content-item">
							<h2 class="tabletitle">1. 거래 수수료</h2>
							<ul class="disc">
								<li>매수 시 매수 대상 암호화폐 수량에서 자동 차감</li>
								<li>매도 시 매도 금액에서 자동 차감</li>
							</ul>

							<div>
								<table class="type03 line">
									<colgroup>
										<col>
										<col>
										<col>
									</colgroup>
									<thead>
										<tr>
											<th>종목</th>
											<th>매도 수수료(%)</th>
											<th>매수 수수료(%)</th>
										</tr>
									</thead>
									<tbody id="trading-fee-tbody">
										<tr>
											<td></td>
											<td></td>
											<td></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
						<!-- //거래 수수료 : End -->

						<!-- 입출금 수수료 : Start -->
						<div class="content-item mgt20">
							<h2 class="tabletitle">2. 입/출금 수수료</h2>
							<ul class="disc">
								<li>입/출금 대상 자산에서 자동 차감</li>
							</ul>

							<div>
								<table class="type03 line">
									<colgroup>
										<col>
										<col>
										<col>
										<col>
									</colgroup>
									<thead>
										<tr>
											<th>자산</th>
											<th>최소입금금액(수량)</th>
											<th>출금 수수료</th>
											<th>최소출금금액(수량)</th>
										</tr>
									</thead>
									<tbody id="deposit-withdraw-fee-tbody">
										<tr>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
										</tr>
									</tbody>
								</table>
								<!-- 페이징 : Start -->
								<!-- <ul id="deposit-withdraw-fee-pagination" class="pagination">
									<li><a class="btn-page first"></a></li>
									<li><a class="btn-page prev"></a></li>
									<li><a href="#" class="active">1</a></li>
									<li><a href="#">2</a></li>
									<li><a class="btn-page next"></a></li>
									<li><a class="btn-page last"></a></li>
								</ul> -->
								<!-- //페이징 : End -->
							</div>
							<p class="mgt20">※ 최소입금액(수량) 이상 입금해야만 잔고에 반영되며, 최소입금액 미만 입금 시에는 잔고 반영 및 입금 취소가 불가능 합니다.</p>
						</div>
						<!-- //입출금 수수료 : End -->

					</li>
				<!--수수료안내 끝-->
		
				<!--보안가이드 시작-->
				<li id="vtab5" class="secuinfo" style="display:none;">
					<h1 class="subtitle">
						보안가이드
					</h1>
														<!-- 안전한 로그인 비밀번호 생성 : Start -->
					<div class="content-item">
						<h2 class="tabletitle">1. 안전한 로그인 비밀번호 생성</h2>
						<ul class="disc">
							<li>알파벳 대문자, 알파벳소문자, 특수기호, 숫자  네 종류 문자 중 최소 3 가지 이상의 조합으로 구성된 비밀번호 사용하기</li>
							<li>7자리 이상의 비밀번호 사용하기</li>
							<li>로그인ID, 생일, 전화번호 등이 포함되는 비밀번호 사용하지 않기</li>
							<li>동일한 문자, 숫자가 연속해서 반복되는 비밀번호 사용하지 않기</li>
							<li>비밀번호 분실에 의한 임시 비밀번호 사용 후 즉시 비밀번호 변경하기</li>
						</ul>
					</div>
					<!-- //안전한 로그인 비밀번호 생성 : End -->

					<!-- 로그인 비밀번호 주기적 변경 : Start -->
					<div class="content-item">
						<h2 class="tabletitle">2. 로그인 비밀번호 주기적 변경</h2>
						<ul class="disc">
							<li>최소 3개월에 한번 씩 비밀번호 변경 하기</li>
							<li>비밀번호 분실에 의한 임시 비밀번호 사용 후 즉시 비밀번호 변경하기</li>
						</ul>

						<!-- 비밀번호 변경하기 버튼 : Start -->
						<a href="/alibit/info?tab=0">
							<button class="big">비밀번호 변경하기</button>
						</a>
						<!-- //비밀번호 변경하기 버튼 : End -->
					</div>
					<!-- //로그인 비밀번호 주기적 변경 : End -->
							
														<!-- 브라우저의 비밀번호 자동저장기능 사용하지 않기 : Start -->
					<div class="content-item">
						<h2 class="tabletitle">3. 브라우저의 비밀번호 자동저장기능 사용하지 않기</h2>
						<ul class="disc">
							<li>웹 브라우즈는 로그인한 사이트의 ID와 비밀번호를 저장 해 두었다가 다음 로그인 시 자동 입력되게 하는 사용자 편의기능이 있음</li>
							<li>여러 사람이 공동으로 사용하는 컴퓨터에서 거래소 사이트를 이용하게 되는 경우 브라우즈의 ID 및 비밀번호 저장기능을 사용하면 보안상 위험함</li>
										<li>거래소 최초 로그인 시 표시되는 팝업 창에서 비밀번호 저장여부를 묻는 경우 사용하지 않음으로 선택<br>
											
											
										</li>
										<li class="mgt20">만약 저장을 선택 하였다면 브라우저의 설정 메뉴에서 저장된 비밀번호를 삭제 함
										<p style="margin-left: 20px">
											Chrome : 설정 – 고급 – 비밀번호 및 양식 – 비밀번호 관리 – 저장된 비밀번호
										</p>
										<p style="margin-left: 20px">
											Explore : 설정 – 고급 설정 보기 – 암호관리 – 저장된 암호 – 저장된 암호
										</p>
									</li>
						</ul>

						
					</div>
					<!-- 브라우저의 비밀번호 자동저장기능 사용하지 않기 : End -->
							
							<!-- 붙여넣기 코인 출금주소 확인하기: Start -->
					<div class="content-item">
						<h2 class="tabletitle">4. 붙여넣기 암호화폐 출금주소 확인하기</h2>
						<ul class="disc">
							<li>타 암호화폐 지갑주소로 암호화폐 출금 시 출금주소 복사하기 후 붙여넣기 기능을 많이 사용하게 됩니다</li>
							<li>이 과정에서 해킹 프로그램에 의해 출금주소 위변조가 발생할 수 있습니다 </li>
										<li>붙여넣기 후 반드시 출금주소가 일치하는지 확인 합니다</li>
						</ul>
									
					</div>
					<!-- //붙여넣기 코인 출금주소 확인하기 : End -->
								

					<!-- OTP 인증을 통한 로그인 보안 강화 : Start -->
					<div class="content-item">
						<h2 class="tabletitle">5. OTP 인증을 통한 로그인 보안 강화</h2>
						<ul class="disc">
							<li>로그인 보안 강화를 위해 OTP 인증을 설정 하십시오</li>
							<li>OTP 인증을 설정하면 로그인 시 OTP 인증을 추가로 수행 하게 됩니다</li>
						</ul>
									
						<!-- OTP인증설정 버튼 : Start -->
						<a href="/alibit/info?tab=1">
							<button class="big">OTP인증설정</button>
						</a><!-- 비활성화 : btn-disabled 클래스 추가/삭제 -->
						<!-- //OTP인증설정 버튼 : End -->

					</div>
					<!-- //OTP 인증을 통한 로그인 보안 강화 : End -->

					<!-- 접속이력 확인 : Start -->
					<div class="content-item">
						<h2 class="tabletitle">6. 접속이력 확인</h2>
						<ul class="disc">
							<li>마이페이지-접속이력 화면에서 로그인 이력을 확인할 수 있습니다</li>
							<li>이상 접속 또는 이용 기록 발견 시 즉시 비밀번호 변경을 해 주세요</li>
						</ul>

						<!-- 접속이력 보기 버튼 : Start -->
						<a href="/alibit/info?tab=2">
							<button class="big">접속이력 보기</button>
						</a>
						
						<!-- //접속이력 보기 버튼 : End -->
					</div>
					<!-- //접속이력 확인 : End -->
				</li>
				<!--보안가이드 끝-->

				<!--자주하는 질문 시작-->
				<li id="vtab6" class="qna" style="display:none;">
					<h1 class="subtitle">자주하는 질문</h1>
					<div class="content-item">
						<p>자주 묻는 질문을 먼저 확인하세요</p>
						<div id="tab-group" class="tab-dark mgt20">
							<ul>
								<li><a href="#tabs-1">전체</a></li>
								<li><a href="#tabs-2">가입 및 개인정보 변경</a></li>
								<li><a href="#tabs-3">원화 입금/출금</a></li>
								<li><a href="#tabs-4">암호화폐 입금/출금</a></li>
								<li><a href="#tabs-5">입출금내역</a></li>
								<li><a href="#tabs-6">기타</a></li>
							</ul>
							
							<div id="tabs-1" class="mgt10">
								<div id="accordion1">
									<h3>
										<span class="faq-category">가입 및 개인정보 변경</span>
										<span>회원가입 방법이 어떻게 되나요?</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">회원 가입은 알리비트 PC용 웹페이지 또는 모바일 웹(추후 오픈 예정)에서 가능합니다.</span>
									</div>
									<h3>
	                                    <span class="faq-category">가입 및 개인정보 변경</span>
	                                    <span>회원가입 인증메일이 오지 않아요.</span>
	                                </h3>
	                                <div>
	                                    <span class="answer-mark">A</span>
	                                    <span class="answer-content">받은편지함이 아닌 스팸함이나 전체편지함도 한번 찾아보시길 바랍니다.<br>구글 ( gmail.com ) 의 경우, '카테고리-프로모션탭'을 확인해 주시기 바랍니다.<br>또한 메일이 수신되는 데에 간혹 시간이 걸리는 경우가 있습니다.<br>기다려도 메일이 오지 않을 경우, 가입하신 이메일 ID 로 로그인하시면 인증메일을 재발송 하실 수 있습니다.<br>재발송을 통해 진행해보시길 바랍니다.<br>개선이 되지 않는다면 1:1문의 혹은 카카오톡 상담을 통해 문의 부탁 드립니다.</span>
	                                </div>
									<h3>
										<span class="faq-category">가입 및 개인정보 변경</span>
										<span>회원가입 자격이 따로 있나요?</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">네, 있습니다. 미성년자 ( 만 19세 미만 ) 와 외국인의 경우 회원가입이 불가합니다.</span>
									</div>
									<h3>
										<span class="faq-category">가입 및 개인정보 변경</span>
										<span>법인폰으로 이용 가능한가요?</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">통신사업자를 통해 법인폰 개인인증 등록을 한 후 이용 가능합니다.</span>
									</div>
									<h3>
										<span class="faq-category">가입 및 개인정보 변경</span>
										<span>개인정보를 변경할 수 있나요?</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">개인 정보로는 로그인 비밀번호, 휴대폰번호, 인증레벨, OTP설정/해제 등이 있으며, 별도의 접수 및 승인절차가 필요할 수 있습니다.</span>
									</div>
									<h3>
										<span class="faq-category">가입 및 개인정보 변경</span>
										<span>휴대전화 번호를 변경할 수 있나요? 어떻게 해야 하나요?</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">휴대폰 번호 변경은 초기화 접수를 통해서만 가능합니다.<br>[마이페이지 – 보안인증 – 휴대폰 본인인증] 초기화 메뉴를 통해서 안내되는 팝업창의 절차에 따라 접수하시길 바랍니다.</span>
									</div>
								   <!-- <h3>
										<span class="faq-category">가입 및 개인정보 변경</span>
										<span>출금 은행계좌 변경을 하고싶어요</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">은행계좌 변경은 고객센터 - 1:1 문의 화면에서 문의구분을 은행계좌등록으로 선택하시고 요청해 주시면 계좌를 초기화 해 드립니다. 초기화 후 직접 재 등록 하시면 됩니다.</span>
									</div>-->
									<h3>
										<span class="faq-category">가입 및 개인정보 변경</span>
										<span>가입 후 인증레벨을 올리고 싶은 데 어떻게 하나요?</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">[마이페이지 – 보안인증] 화면에서 보안등급별 다음 단계 안내에 따른 인증을 하시면 됩니다.</span>
									</div>
									<h3>
										<span class="faq-category">가입 및 개인정보 변경</span>
										<span>인증레벨을 3레벨로 올리고 싶은데 반드시 상향조건을 만족해야 하는건가요?</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">네, 상향조건 만족해야 합니다.</span>
									</div>
									<!-- <h3>
										<span class="faq-category">가입 및 개인정보 변경</span>
										<span>가입시 이름을 잘못 입력하여 휴대폰 본인인증이 안됩니다.</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">1:1 문의에 남겨 주시면 이름을 변경해 드립니다.</span>
									</div> -->
									<h3>
										<span class="faq-category">가입 및 개인정보 변경</span>
										<span>OTP 설정시 OTP 인증번호가 틀렸다고 표시됩니다.</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">구글 OTP 앱에 표시된6자리 번호는 일정 시간 후 갱신이 되니 갱신된 번호를 확인하신 후 정확히 다시 입력해야 합니다.</span>
									</div>
									<!-- <h3>
										<span class="faq-category">가입 및 개인정보 변경</span>
										<span>전화번호를 변경하고 싶습니다. 어떻게 해야 하나요?</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">웹사이트 마이페이지 - 휴대폰 번호변경 화면에서 변경 가능 합니다.</span>
									</div> -->
									
									
									<h3>
										<span class="faq-category">원화 입금/출금</span>
										<span>원화 입금은 어떻게 하나요?</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">입출금-입금신청 화면에서 본인 명의 은행계좌 등록 후 입금요청 하시면 1회용 입금코드가 발급 됩니다. 해당 입금코드를 받는통장메모란에 기입하신후 법인계좌로 입금요청하신 금액만큼 계좌이체 하시면 됩니다.</span>
									</div>
									<h3>
										<span class="faq-category">원화 입금/출금</span>
										<span>원화 입금 확인이 안되고 있습니다.</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">
											입금반영이 1시간 이상 지연되는 경우 카카오톡 문의나 1:1문의를 통해 문의 바랍니다.<br>
											<br>다음의 경우 입금이 반영이 안될 수 있습니다.
											<br>① 등록하신 본인 은행계좌에서 입금 하시지 않은 경우
											<br>② 입금코드(메모란 기입)가 다르거나, 미입력한 경우
											<br>③ 입금요청액과 입금액이 다른 경우
											<br>④ 입금자명이 다른 경우 에는 입금 확인이 불가하며 이 경우
											<br>⑤ ATM, 카카오페이, 토스, 증권CMA계좌 등에서 입금한 경우
											<br>위와 같은 경우에는 증빙서류를 제출하셔야 합니다.<br>
											<br>입금을 증명할 수 있도록 다음의 증빙서류를 제출하셔야 합니다. 
											<br>[증빙서류] 
											<br>1) 신분증 개인정보(①주민번호 뒷자리 ②주소) 가리고 요청사항 메모 부착한 사진(아래 참조) 
											<br>메모 기재 내용 : 알리비트 입금 요청, 가입이메일, 요청날짜 
											<br>2) 1)번을 들고 찍은 본인 정면 사진(아래 참조) 
											<br>3) 입금내역증, 통장이체내역 등 이체내역 증빙 서류 
											<br>※ 사진촬영일이 요청날짜와 다른 경우 업무처리가 불가합니다. 
											<br>[증빙서류 제출 메뉴] 고객지원 – 1:1 문의 – 인증자료제출 <br>
											<br>입금반영은 순차적으로 관리자 승인을 통해 진행되므로 시간이 소요될 수 있습니다.<br>
											<img src="/resources/img/yaho/img_proof_info1.jpg" alt="img">
											<img src="/resources/img/yaho/img_proof_info2.jpg" alt="img">
										</span>
									</div>
									
									<h3>
										<span class="faq-category">원화 입금/출금</span>
										<span>원화 출금 제한이 있나요?</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">원화 출금은 회원 가입 후 인증레벨2 이상인 회원의 경우에만 가능하며 각 인증레벨별로 1일 출금한도가 정해져 있습니다. 출금한도를 늘리기 위해서는 인증레벨을 보다 높은 레벨로 올리셔야 합니다. 자세한 출금한도에 대해서는 [고객지원 - 이용안내] 메뉴를 참조 바랍니다.</span>
									</div>
									
									<h3>
										<span class="faq-category">원화 입금/출금</span>
										<span>입출금 은행계좌를 잘못 등록 하였습니다. 변경할려면 어떻게 하나요?</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">[고객지원 – 1:1 문의 – 입출금 – 은행계좌등록/변경] 메뉴에서 문의에 남겨 주시면 관리자 확인 후 계좌 초기화를 해 드립니다. 다만 한번이라도 기존 계좌 번호로 입금한 내역이 있는 경우 해킹에 의한 악의적인 변경요청을 방지하기 위해 인증자료를 제출 받습니다. 초기화 된 후 재 등록 하십시오.</span>
									</div>
	
									
									<h3>
										<span class="faq-category">원화 입금/출금</span>
										<span>원화를 보유하고 있는데 출금가능 KRW 잔고가 0 으로 표시 됩니다.</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">원화 출금 72시간 제한에 걸려 있는지 확인 하십시오.</span>
									</div>
									
	
									
									<h3>
										<span class="faq-category">원화 입금/출금</span>
										<span> 원화 출금 72시간 제한이 무엇인가요?</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">보이스피싱등의 금융사고 방지하고 회원님들의 자산을 안전하게 지키기 위하여 최초 원화 입금 후 주말 제외하고 입금 시점으로부터 72시간 이후에 원화 출금이 가능한 보안 규정입니다.</span>
									</div>
									
									<h3>
										<span class="faq-category">원화 입금/출금</span>
										<span> 원화 입금 시 계좌이체를 먼저하고 입금신청을 해야 하나요?</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">아닙니다. 반드시 입금신청을 먼저 하시고 발급받은 입금코드를 받는통장메모에 기입하여  계좌이체 하셔야 합니다.</span>
									</div>
									
	
									
									<h3>
										<span class="faq-category">원화 입금/출금</span>
										<span>입금할때 ATM기나 카카오페이, 토스 등을 통한 입금이 가능한가요?</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">불가합니다. 해당 입금 수단으로 입금하시면 입금확인이 어렵습니다. 해당 건에 대한 환급시에도 인증자료 제출 등 많은 시간이 소요 됩니다.</span>
									</div>
									
	
									<h3>
										<span class="faq-category">원화 입금/출금</span>
										<span> ATM기나 카카오페이, 토스 등으로 입금을 해 버린 경우에는 어떻게 하나요?</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">
											입금을 증명할수 있도록 다음의 증빙서류를 제출하셔야 합니다.
											<br>[증빙서류]
											<br>1) 신분증 개인정보(①주민번호 뒷자리 ②주소) 가리고 요청사항 메모 부착한 사진(아래 참조)
											<br>메모 기재 내용 : 알리비트 입금 요청, 가입이메일, 요청날짜
											<br>2) 1)번을 들고 찍은 본인 정면 사진(아래 참조)
											<br>3) 입금내역증, 통장이체내역 등 이체내역 증빙 서류
											<br>※ 사진촬영일이 요청날짜와 다른 경우 업무처리가 불가합니다.
											<br>[증빙서류 제출 메뉴] 고객지원 – 1:1 문의 – 인증자료제출
											<br>
											<img src="/resources/img/yaho/img_proof_info1.jpg" alt="img">
											<img src="/resources/img/yaho/img_proof_info2.jpg" alt="img">
										</span>
									</div>
		
									
									<h3>
										<span class="faq-category">원화 입금/출금</span>
										<span>거래소에 등록된 입출금 계좌가 아닌 다른 계좌에서 입금을 해 버렸습니다.</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">충전 요청을 하신 후에 입금내역 캡쳐, 신분증 주민번호 뒷자리 가리고 캡쳐, 출금하신 통장사본 캡쳐해서 올려 주시면 처리해 드립니다만 시간이 다소 걸릴 수 있습니다.</span>
									</div>
									
									<h3>
										<span class="faq-category">원화 입금/출금</span>
										<span>평생계좌번호(전화번호 사용)로도 입금이 가능한가요?</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">불가합니다. 해당 입금 수단으로 입금하시면 입금확인이 어렵습니다. 해당 건에 대한 환급시에도 인증자료 제출 등 많은 시간이 소요 됩니다.</span>
									</div>
																
									<h3>
										<span class="faq-category">원화 입금/출금</span>
										<span>입금신청한 금액과 다른 금액을 계좌이체 해 버렸습니다. 어떻게 하나요?</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">[고객지원 – 1:1 문의 – 인증자료제출] 메뉴에서 입금증명서를 제출해 주시면 확인 후 입금처리 해 드립니다.</span>
									</div>
									
									<h3>
										<span class="faq-category">원화 입금/출금</span>
										<span>실물 통장이 없어서 통장사본 제출이 불가능한데 어떻게 하나요?</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">실물 통장이 없는 인터넷통장 등은 해당 은행 사이트 및 앱에서 통장사본 출력 및 캡쳐가 가능합니다. 지원하지 않는 은행의 경우 은행 창구를 내방하여 통장 사본을 발급받아 제출하여 주십시오.</span>
									</div>
									
									<h3>
										<span class="faq-category">원화 입금/출금</span>
										<span> 원화 입금이 하루 이상 지났는데도 처리되지 않고 있습니다. 어떻게 하나요?</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">[고객지원 – 1:1 문의 – 입출금 – 원화 (KRW)] 메뉴를 선택하신 후 제목에 “원화 입금 요청, 24시간 경과” 로 문의하기 하시면 확인 후 빠르게 처리해 드립니다. 빠른처리를 위해 추가자료(입금내역증 등)을 요청할 수 있습니다.</span>
									</div>
									
									<h3>
										<span class="faq-category">원화 입금/출금</span>
										<span>보유하고 있는 원화보다 출금가능 잔액이 적게 표시됩니다 왜 그런가요?</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">현재 매수 주문을 걸어 놓으신게 없는지 확인 하십시오. 미체결 매수 주문이 없는데도 같은 증상이라면 [고객지원 - 1:1 문의 – 입출금 – 원화 (KRW)] 메뉴에 문의를 남겨주시기 바랍니다.</span>
									</div>
									
									<h3>
										<span class="faq-category">원화 입금/출금</span>
										<span>인증자료 제출은 어디서 하나요?</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">[고객지원 – 1:1 문의 – 인증자료제출] 메뉴를 이용하시기 바랍니다.</span>
									</div>
									
									
	
									<h3>
										<span class="faq-category">암호화폐 입금/출금</span>
										<span>암호화폐 지갑 주소는 어디서 확인하나요?</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">로그인 후 입출금 메뉴에서 지갑 주소 확인을 원하시는 암호화폐를 선택하신 후 입금주소 탭을 클릭 하시면 확인 가능합니다. 지갑 생성을 하지 않으셨다면 [입금 주소 생성하기] 버튼을 클릭하여 주소 생성이 가능합니다.</span>
									</div>
									<h3>
										<span class="faq-category">암호화폐 입금/출금</span>
										<span>암호화폐 출금 한도는 어떻게</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">출금하고자 하는 암호화폐의 원화 환산액(출금수량*원화가격) 인증레벨 별 출금한도 이내에서만 가능합니다. 예를 들어 회원님의 인증레벨이 3이면 1일 출금한도는 2,000만원 이내에서만 가능하므로, 현재가가 반영된 원화 환산액 2,000만원 이내의 암호화폐 수량 에서만 가능합니다.</span>
									</div>
									<h3>
										<span class="faq-category">암호화폐 입금/출금</span>
										<span>암호화폐 출금 제한은 왜 하나요?</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">만일의 경우 회원님의 암호화폐 계좌가 회원님의 개인정보 관리 부주의로 타인에게 노출되었을 경우에라도 피해 확산을 방지하고, 거래소에 대한 해킹 피해 방지를 위한 방편의 일환으로 실시하는 보안 규정입니다.</span>
									</div>
									<h3>
										<span class="faq-category">암호화폐 입금/출금</span>
										<span>암호화폐 출금수수료는 얼마인가요?</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">출금수수료는 암호화폐 별로 상이하며 각 암호화폐 별 출금수수료는 [고객지원 – 수수료안내] 메뉴를 참조하시면 됩니다.</span>
									</div>
									
									 <h3>
										<span class="faq-category">암호화폐 입금/출금</span>
										<span> 암호화폐 입출금 시간이 지연되는 이유가 있나요?</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">암호화폐의 입출금 소요 시간은 암호화폐별로 다를 수 있으며 보통 10분~30분 정도 소요 됩니다. 다만 블록체인 네트워크 상에 일시적으로 과도한 입출금 요청이 발생하는 경우 다소 지연될 수 있습니다.</span>
									</div>
									
									 <h3>
										<span class="faq-category">암호화폐 입금/출금</span>
										<span>암호화폐 출금 주소를 잘못 입력한 경우 어떻게 하나요?</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">암호화폐 특성 상 출금 신청이 완료되어 블록체인에 기록되면 취소가 불가능 합니다. 송금과정은 블록체인 네트워크에서 처리되므로 Bita500에서 도움을 드릴수가 없습니다. 반드시 출금 주소를 반복 확인하시고 출금신청 하시기 바랍니다.  </span>
									</div>
									<h3>
										<span class="faq-category">암호화폐 입금/출금</span>
										<span>입금 주소에 다른 암호화폐를 입금한 오입금의 경우 어떻게 하나요?</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">
											오입금 사례별 복구를 도와드릴 수 있는 상황은 제한적이며, 상황에 따라 복구 작업을 위한 수수료 및 시일이 소요됩니다.
											<br><br>
											※ 오입금 복구 가능한 경우
											<br>
											1) [BTC를 BCH로] 또는 [BCH를 BTC로] 오입금(BTC, BCH 오입금의 경우)
											<br>
											- 접수 완료 후 수수료로 50만원 상당의 BTC를 정해진 주소로 전송 후 복구가 진행됩니다.
											<br>
											<br>
											2) 리플 (XRP) Destination Tag 를 잘못 작성한 오입금
											<br>
											- 해당 Destination Tag나 메모가 타인에게 발급되지 않은 경우, 영업일 기준 최대 5일이 소요됩니다.
											<br>
											해당 Destination Tag나 메모가 타인에게 발급되고 오입금 받은 고객이 반환에 협조하지 않는 경우, 부당이득반환청구 소송을 해야할 수도 있습니다.
											<br>
											<br>
											3) [ERC20을 ETH로] 또는 [ERC20을 ERC20으로] 오입금
											<br>
											-1:1 문의하기를 통해 접수해 주세요. 처리에 시간이 소요될 수 있습니다.
											<br>
											<br>
											4) ICO에 참여하여 거래소 핫월렛 ETH 주소로 잘못 전송된 경우
											<br>
											-1:1 문의하기를 통해 접수해 주세요. 처리에 시간이 소요될 수 있습니다.
											<br>
											<br>
											※ 오입금 접수는 [고객지원 – 1:1 문의하기 – 입출금] 메뉴를 통해 접수해 주세요.
											<br>
											※ 기술적 문제로 모든 오입금 내역이 복구되지 않을 수 있습니다.
											</span>
									</div>
									
									 <h3>
										<span class="faq-category">암호화폐 입금/출금</span>
										<span>암호화폐 입금시 주소외 추가로 입력해야 하는 정보를 누락하고 전송한 경우는 어떻게 하나요?</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">블록체인 트랜잭션ID 나 해시값을 1:1 문의에 문의구분 암호화폐 입출금 선택하고 올려주세요. 다만 이 경우 처리하는데 최장 5일까지 소요 될 수 있습니다. 추가로 고객지원 > 인증자료제출 화면에서 인증목적구분을 암호화폐 입출금 선택하시고 보내신 사이트에서 전송상세 내역을 캡쳐해 올려 주셔야 합니다.</span>
									</div>
									
									 <h3>
										<span class="faq-category">암호화폐 입금/출금</span>
										<span>원화 입금없이 암호화폐만 입금했다 출금할 경우에도 72시간 제한이 있나요?</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">암호화폐만 입금했다 매도 후 원화로 출금하거나 암호화폐를 출금하는 경우에는 제한이 없습니다.</span>
									</div>
									
									 <h3>
										<span class="faq-category">암호화폐 입금/출금</span>
										<span>암호화폐 입금이 하루 이상 지났는데도 처리되지 않고 있습니다. 어떻게 하나요?</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">[고객지원 – 1:1 문의 – 입출금] 메뉴에서 해당 암호화폐 선택 후 제목에 암호화폐명을 넣어 “OOO 입금요청합니다 – 입금 후 24시간 경과＂ 라고 경과 시간을 기재해 주시면 우선적으로 확인해 드리겠습니다. TXID를 문의 내용에 기입해 주시고 보내신 쪽에서 전송내역을 수량이 보이게끔 캡쳐 및 사진촬영 하셔서 파일 확장자 jpg ,png 로 파일첨부하여 문의해 주시기 바랍니다.</span>
									</div>
									
									 <h3>
										<span class="faq-category">암호화폐 입금/출금</span>
										<span>보유하고 있는 잔고보다 출금가능 수량이 적게 표시됩니다 왜 그런가요?</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">현재 암호화폐 매도 주문을 걸어 놓으신게 없는지 확인 하십시오 미체결 매도 주문이 없는데 그렇다면 1:1 문의에 올려 주세요.</span>
									</div>
									
									 <h3>
										<span class="faq-category">암호화폐 입금/출금</span>
										<span>트랜잭션ID (TXID) 또는 해시값이 뭔가요?</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">암호화폐를 거래소간에 송금할시 블록체인 네트워크 상에서 암호화폐 송금 내역을 식별하고 추적할 수 있도록 하는 유일한 키 값입니다. 해당 값이 없으면 암호화폐 송금 내역을 찾는것이 불가능 하며 송금측 거래소 사이트의 암호화폐 출금내역 화면에서 조회 및 확인 할 수 있습니다.</span>
									</div>
									
								  
									<h3>
										<span class="faq-category">거래,주문</span>
										<span>거래수수료는 얼마인가요?</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">거래수수료는 매수/매도 모두 0.1%를 적용하고 있습니다.</span>
									</div>
								   <!-- <h3>
										<span class="faq-category">거래,주문</span>
										<span>정정주문이 뭔가요?</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">모바일 앱에서만 가능한 정정주문은 기존 매수 또는 매도 주문 내역 중 주문수량 또는 주문가격을 변경하여 재 주문 하는 경우를 말하며 기존 주문내역을 취소하고 새로운 주문을 신규로 내는 번거로움을 없애고자 제공하는 주문 기능입니다.</span>
									</div>-->
									
									<h3>
										<span class="faq-category">거래,주문</span>
										<span>미체결 주문은 어떻게 처리 되나요?</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">주문 취소 전까지는 미체결 상태로 계속 남아 있게 됩니다.</span>
									</div>
									
									<h3>
										<span class="faq-category">거래,주문</span>
										<span>웹에서 주문 취소가 되지 않습니다.</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">일시적인 화면 갱신 장애로 인한 것일 수 있으니 화면 새로고침을 해 보시면 취소결과 확인이 가능합니다. 정상화 될 때까지 불편하시더라도 우선 화면 새로고침을 하여 확인바랍니다.</span>
									</div>
									
									<h3>
										<span class="faq-category">거래,주문</span>
										<span>주문 체결이 되었는데 체결 수량이나 금액이 실제 주문 내역과 일치하지 않습니다.</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">[고객지원 - 1:1 문의 – 기타] 메뉴에서 문의 내용을 남겨 주십시오. 확인 후 답변 드리겠습니다.</span>
									</div>
									
								  
									<h3>
										<span class="faq-category">기타</span>
										<span>회원 탈퇴 방법을 알려주세요.</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">로그인 하신 후 [고객지원 - 1:1 문의 – 계정 - 회원탈퇴] 메뉴에서 탈퇴사유와 함께 탈퇴 요청을 해 주십시오. 탈퇴 처리전에 회원님의 계정에는 원화 및 암호화폐 잔고가 없어야 합니다.</span>
									</div>
									<h3>
										<span class="faq-category">기타</span>
										<span>다른 궁금한 사항은 어디에 물어보면 되나요?</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">로그인 하신 후 [고객지원 - 1:1 문의 – 기타] 메뉴에서 문의사항을 등록 하시면 담당자가 확인 후 답변을 드립니다.</span>
									</div>
									<h3>
										<span class="faq-category">기타</span>
										<span> Token launchpad 참여를 했습니다. 결과는 어디서 확인 가능한가요?</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">[투자내역 – 잔고변경이력] 메뉴에서 변경사유를 Token launchpad로 선택하고 날짜를 선택하신 후 조회하시면 됩니다.</span>
									</div>
									<h3>
										<span class="faq-category">기타</span>
										<span>신분증 제출시 신분증과 신분증 들고 있는 사진 둘다 제출해야 하나요?</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">신분증과 신분증 들고 있는 사진 모두 제출해야 합니다.</span>
									</div>
									<!--<h3>
										<span class="faq-category">기타</span>
										<span>모바일 앱에서는 로그인이 되는데 PC 웹에서는 비밀번호 오류라고 하면서 로그인이 안됩니다.</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">모바일 앱 로그인시 입력한 로그인ID와 동일하게 웹에서도 ID 입력하셨는지 확인 하십시오. 동일하게 입력해도 안 된다면 비밀번호에 특수문자 &#60; 또는 &#40; 또는 &#39; 세 가지 문자 중 하나가 포함되어 있는지 확인하십시오. 포함되어 있다면 해당 특수문자가 포함되지 않은 비밀번호로 모바일 앱에서 변경한 후 웹로그인 해보시길 바랍니다.</span>
									</div>-->
									<h3>
										<span class="faq-category">기타</span>
										<span>모바일 웹은 언제 서비스 되나요?</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">정확한 날짜를 확정 드릴 수 없어 죄송합니다. 서비스 가능 시점이 되면 공지사항으로 안내 됩니다.</span>
									</div>  
								</div>
							</div>
						
							<div id="tabs-2" class="mgt10">
								<div id="accordion2">
									<h3>
										<span class="faq-category">가입 및 개인정보 변경</span>
										<span>회원가입 방법이 어떻게 되나요?</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">회원 가입은 알리비트 PC용 웹페이지 또는 모바일 웹(추후 오픈 예정)에서 가능합니다.</span>
									</div>
									<h3>
	                                    <span class="faq-category">가입 및 개인정보 변경</span>
	                                    <span>회원가입 인증메일이 오지 않아요.</span>
	                                </h3>
	                                <div>
	                                    <span class="answer-mark">A</span>
	                                    <span class="answer-content">받은편지함이 아닌 스팸함이나 전체편지함도 한번 찾아보시길 바랍니다.<br>구글 ( gmail.com ) 의 경우, '카테고리-프로모션탭'을 확인해 주시기 바랍니다.<br>또한 메일이 수신되는 데에 간혹 시간이 걸리는 경우가 있습니다.<br>기다려도 메일이 오지 않을 경우, 가입하신 이메일 ID 로 로그인하시면 인증메일을 재발송 하실 수 있습니다.<br>재발송을 통해 진행해보시길 바랍니다.<br>개선이 되지 않는다면 1:1문의 혹은 카카오톡 상담을 통해 문의 부탁 드립니다.</span>
	                                </div>
									<h3>
										<span class="faq-category">가입 및 개인정보 변경</span>
										<span>회원가입 자격이 따로 있나요?</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">네, 있습니다. 미성년자 ( 만 19세 미만 ) 와 외국인의 경우 회원가입이 불가합니다.</span>
									</div>
									<h3>
										<span class="faq-category">가입 및 개인정보 변경</span>
										<span>법인폰으로 이용 가능한가요?</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">통신사업자를 통해 법인폰 개인인증 등록을 한 후 이용 가능합니다.</span>
									</div>
									<h3>
										<span class="faq-category">가입 및 개인정보 변경</span>
										<span>개인정보를 변경할 수 있나요?</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">개인 정보로는 로그인 비밀번호, 휴대폰번호, 인증레벨, OTP설정/해제 등이 있으며, 별도의 접수 및 승인절차가 필요할 수 있습니다.</span>
									</div>
									<h3>
										<span class="faq-category">가입 및 개인정보 변경</span>
										<span>휴대전화 번호를 변경할 수 있나요? 어떻게 해야 하나요?</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">휴대폰 번호 변경은 초기화 접수를 통해서만 가능합니다.<br>[마이페이지 – 보안인증 – 휴대폰 본인인증] 초기화 메뉴를 통해서 안내되는 팝업창의 절차에 따라 접수하시길 바랍니다.</span>
									</div>
									<!--<h3>
										<span class="faq-category">가입 및 개인정보 변경</span>
										<span>출금 은행계좌 변경을 하고싶어요</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">은행계좌 변경은 고객센터 - 1:1 문의 화면에서 문의구분을 은행계좌등록으로 선택하시고 요청해 주시면 계좌를 초기화 해 드립니다.초기화 후 직접 재 등록 하시면 됩니다.</span>
									</div>-->
									<h3>
										<span class="faq-category">가입 및 개인정보 변경</span>
										<span>가입 후 인증레벨을 올리고 싶은 데 어떻게 하나요?</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">[마이페이지 – 보안인증] 화면에서 보안등급별 다음 단계 안내에 따른 인증을 하시면 됩니다.</span>
									</div>
									<h3>
										<span class="faq-category">가입 및 개인정보 변경</span>
										<span>인증레벨을 3레벨로 올리고 싶은데 반드시 상향조건을 만족해야 하는건가요?</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">네, 상향조건 만족해야 합니다.</span>
									</div>
									<!-- <h3>
										<span class="faq-category">가입 및 개인정보 변경</span>
										<span>가입시 이름을 잘못 입력하여 휴대폰 본인인증이 안됩니다.</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">1:1 문의에 남겨 주시면 이름을 변경해 드립니다.</span>
									</div> -->
									<h3>
										<span class="faq-category">가입 및 개인정보 변경</span>
										<span>OTP 설정시 OTP 인증번호가 틀렸다고 표시됩니다.</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">구글 OTP 앱에 표시된6자리 번호는 일정 시간 후 갱신이 되니 갱신된 번호를 확인하신 후 정확히 다시 입력해야 합니다.</span>
									</div>
									<!-- <h3>
										<span class="faq-category">가입 및 개인정보 변경</span>
										<span>전화번호를 변경하고 싶습니다. 어떻게 해야 하나요?</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">웹사이트 마이페이지 - 휴대폰 번호변경 화면에서 변경 가능 합니다.</span>
									</div> -->
								</div>
							</div>
					   
								
							<div id="tabs-3" class="mgt10">
								<div id="accordion3">
									<h3>
										<span class="faq-category">원화 입금/출금</span>
										<span>원화 입금은 어떻게 하나요?</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">입출금-입금신청 화면에서 본인 명의 은행계좌 등록 후 입금요청 하시면 1회용 입금코드가 발급 됩니다. 해당 입금코드를 받는통장메모란에 기입하신후 법인계좌로 입금요청하신 금액만큼 계좌이체 하시면 됩니다.</span>
									</div>
									<h3>
										<span class="faq-category">원화 입금/출금</span>
										<span>원화 입금 확인이 안되고 있습니다.</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">
											입금반영이 1시간 이상 지연되는 경우 카카오톡 문의나 1:1문의를 통해 문의 바랍니다.<br>
											<br>다음의 경우 입금이 반영이 안될 수 있습니다.
											<br>① 등록하신 본인 은행계좌에서 입금 하시지 않은 경우
											<br>② 입금코드(메모란 기입)가 다르거나, 미입력한 경우
											<br>③ 입금요청액과 입금액이 다른 경우
											<br>④ 입금자명이 다른 경우 에는 입금 확인이 불가하며 이 경우
											<br>⑤ ATM, 카카오페이, 토스, 증권CMA계좌 등에서 입금한 경우
											<br>위와 같은 경우에는 증빙서류를 제출하셔야 합니다.<br>
											<br>입금을 증명할 수 있도록 다음의 증빙서류를 제출하셔야 합니다. 
											<br>[증빙서류] 
											<br>1) 신분증 개인정보(①주민번호 뒷자리 ②주소) 가리고 요청사항 메모 부착한 사진(아래 참조) 
											<br>메모 기재 내용 : 알리비트 입금 요청, 가입이메일, 요청날짜 
											<br>2) 1)번을 들고 찍은 본인 정면 사진(아래 참조) 
											<br>3) 입금내역증, 통장이체내역 등 이체내역 증빙 서류 
											<br>※ 사진촬영일이 요청날짜와 다른 경우 업무처리가 불가합니다. 
											<br>[증빙서류 제출 메뉴] 고객지원 – 1:1 문의 – 인증자료제출 <br>
											<br>입금반영은 순차적으로 관리자 승인을 통해 진행되므로 시간이 소요될 수 있습니다.<br>
											<img src="/resources/img/yaho/img_proof_info1.jpg" alt="img">
											<img src="/resources/img/yaho/img_proof_info2.jpg" alt="img">
										</span>
									</div>
									
									<h3>
										<span class="faq-category">원화 입금/출금</span>
										<span>원화 출금 제한이 있나요?</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">원화 출금은 회원 가입 후 인증레벨2 이상인 회원의 경우에만 가능하며 각 인증레벨별로 1일 출금한도가 정해져 있습니다. 출금한도를 늘리기 위해서는 인증레벨을 보다 높은 레벨로 올리셔야 합니다. 자세한 출금한도에 대해서는 [고객지원 - 이용안내] 메뉴를 참조 바랍니다.</span>
									</div>
									
									<h3>
										<span class="faq-category">원화 입금/출금</span>
										<span>입출금 은행계좌를 잘못 등록 하였습니다. 변경할려면 어떻게 하나요?</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">[고객지원 – 1:1 문의 – 입출금 – 은행계좌등록/변경] 메뉴에서 문의에 남겨 주시면 관리자 확인 후 계좌 초기화를 해 드립니다. 다만 한번이라도 기존 계좌 번호로 입금한 내역이 있는 경우 해킹에 의한 악의적인 변경요청을 방지하기 위해 인증자료를 제출 받습니다. 초기화 된 후 재 등록 하십시오.</span>
									</div>
	
									
									<h3>
										<span class="faq-category">원화 입금/출금</span>
										<span>원화를 보유하고 있는데 출금가능 KRW 잔고가 0 으로 표시 됩니다.</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">원화 출금 72시간 제한에 걸려 있는지 확인 하십시오.</span>
									</div>
									
	
									
									<h3>
										<span class="faq-category">원화 입금/출금</span>
										<span> 원화 출금 72시간 제한이 무엇인가요?</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">보이스피싱등의 금융사고 방지하고 회원님들의 자산을 안전하게 지키기 위하여 최초 원화 입금 후 주말 제외하고 입금 시점으로부터 72시간 이후에 원화 출금이 가능한 보안 규정입니다.</span>
									</div>
									
									<h3>
										<span class="faq-category">원화 입금/출금</span>
										<span> 원화 입금 시 계좌이체를 먼저하고 입금신청을 해야 하나요?</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">아닙니다. 반드시 입금신청을 먼저 하시고 발급받은 입금코드를 받는통장메모에 기입하여  계좌이체 하셔야 합니다.</span>
									</div>
									
	
									
									<h3>
										<span class="faq-category">원화 입금/출금</span>
										<span>입금할때 ATM기나 카카오페이, 토스 등을 통한 입금이 가능한가요?</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">불가합니다. 해당 입금 수단으로 입금하시면 입금확인이 어렵습니다. 해당 건에 대한 환급시에도 인증자료 제출 등 많은 시간이 소요 됩니다.</span>
									</div>
									
	
									<h3>
										<span class="faq-category">원화 입금/출금</span>
										<span> ATM기나 카카오페이, 토스 등으로 입금을 해 버린 경우에는 어떻게 하나요?</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">
											입금을 증명할수 있도록 다음의 증빙서류를 제출하셔야 합니다.
											<br>[증빙서류]
											<br>1) 신분증 개인정보(①주민번호 뒷자리 ②주소) 가리고 요청사항 메모 부착한 사진(아래 참조)
											<br>메모 기재 내용 : 알리비트 입금 요청, 가입이메일, 요청날짜
											<br>2) 1)번을 들고 찍은 본인 정면 사진(아래 참조)
											<br>3) 입금내역증, 통장이체내역 등 이체내역 증빙 서류
											<br>※ 사진촬영일이 요청날짜와 다른 경우 업무처리가 불가합니다.
											<br>[증빙서류 제출 메뉴] 고객지원 – 1:1 문의 – 인증자료제출
											<br>
											<img src="/resources/img/yaho/img_proof_info1.jpg" alt="img">
											<img src="/resources/img/yaho/img_proof_info2.jpg" alt="img">
										</span>
									</div>
		
									
									<h3>
										<span class="faq-category">원화 입금/출금</span>
										<span>거래소에 등록된 입출금 계좌가 아닌 다른 계좌에서 입금을 해 버렸습니다.</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">충전 요청을 하신 후에 입금내역 캡쳐, 신분증 주민번호 뒷자리 가리고 캡쳐, 출금하신 통장사본 캡쳐해서 올려 주시면 처리해 드립니다만 시간이 다소 걸릴 수 있습니다.</span>
									</div>
									
									<h3>
										<span class="faq-category">원화 입금/출금</span>
										<span>평생계좌번호(전화번호 사용)로도 입금이 가능한가요?</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">불가합니다. 해당 입금 수단으로 입금하시면 입금확인이 어렵습니다. 해당 건에 대한 환급시에도 인증자료 제출 등 많은 시간이 소요 됩니다.</span>
									</div>
																
									<h3>
										<span class="faq-category">원화 입금/출금</span>
										<span>입금신청한 금액과 다른 금액을 계좌이체 해 버렸습니다. 어떻게 하나요?</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">[고객지원 – 1:1 문의 – 인증자료제출] 메뉴에서 입금증명서를 제출해 주시면 확인 후 입금처리 해 드립니다.</span>
									</div>
									
									<h3>
										<span class="faq-category">원화 입금/출금</span>
										<span>실물 통장이 없어서 통장사본 제출이 불가능한데 어떻게 하나요?</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">실물 통장이 없는 인터넷통장 등은 해당 은행 사이트 및 앱에서 통장사본 출력 및 캡쳐가 가능합니다. 지원하지 않는 은행의 경우 은행 창구를 내방하여 통장 사본을 발급받아 제출하여 주십시오.</span>
									</div>
									
									<h3>
										<span class="faq-category">원화 입금/출금</span>
										<span> 원화 입금이 하루 이상 지났는데도 처리되지 않고 있습니다. 어떻게 하나요?</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">[고객지원 – 1:1 문의 – 입출금 – 원화 (KRW)] 메뉴를 선택하신 후 제목에 “원화 입금 요청, 24시간 경과” 로 문의하기 하시면 확인 후 빠르게 처리해 드립니다. 빠른처리를 위해 추가자료(입금내역증 등)을 요청할 수 있습니다.</span>
									</div>
									
									<h3>
										<span class="faq-category">원화 입금/출금</span>
										<span>보유하고 있는 원화보다 출금가능 잔액이 적게 표시됩니다 왜 그런가요?</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">현재 매수 주문을 걸어 놓으신게 없는지 확인 하십시오. 미체결 매수 주문이 없는데도 같은 증상이라면 [고객지원 - 1:1 문의 – 입출금 – 원화 (KRW)] 메뉴에 문의를 남겨주시기 바랍니다.</span>
									</div>
									
									<h3>
										<span class="faq-category">원화 입금/출금</span>
										<span>인증자료 제출은 어디서 하나요?</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">[고객지원 – 1:1 문의 – 인증자료제출] 메뉴를 이용하시기 바랍니다.</span>
									</div>
								</div>
							</div>
					  
								
							<div id="tabs-4" class="mgt10">
								<div id="accordion4">
								   <h3>
										<span class="faq-category">암호화폐 입금/출금</span>
										<span>암호화폐 지갑 주소는 어디서 확인하나요?</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">로그인 후 입출금 메뉴에서 지갑 주소 확인을 원하시는 암호화폐를 선택하신 후 입금주소 탭을 클릭 하시면 확인 가능합니다. 지갑 생성을 하지 않으셨다면 [입금 주소 생성하기] 버튼을 클릭하여 주소 생성이 가능합니다.</span>
									</div>
									<h3>
										<span class="faq-category">암호화폐 입금/출금</span>
										<span>암호화폐 출금 한도는 어떻게</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">출금하고자 하는 암호화폐의 원화 환산액(출금수량*원화가격) 인증레벨 별 출금한도 이내에서만 가능합니다. 예를 들어 회원님의 인증레벨이 3이면 1일 출금한도는 2,000만원 이내에서만 가능하므로, 현재가가 반영된 원화 환산액 2,000만원 이내의 암호화폐 수량 에서만 가능합니다.</span>
									</div>
									<h3>
										<span class="faq-category">암호화폐 입금/출금</span>
										<span>암호화폐 출금 제한은 왜 하나요?</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">만일의 경우 회원님의 암호화폐 계좌가 회원님의 개인정보 관리 부주의로 타인에게 노출되었을 경우에라도 피해 확산을 방지하고, 거래소에 대한 해킹 피해 방지를 위한 방편의 일환으로 실시하는 보안 규정입니다.</span>
									</div>
									<h3>
										<span class="faq-category">암호화폐 입금/출금</span>
										<span>암호화폐 출금수수료는 얼마인가요?</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">출금수수료는 암호화폐 별로 상이하며 각 암호화폐 별 출금수수료는 [고객지원 – 수수료안내] 메뉴를 참조하시면 됩니다.</span>
									</div>
									
									 <h3>
										<span class="faq-category">암호화폐 입금/출금</span>
										<span> 암호화폐 입출금 시간이 지연되는 이유가 있나요?</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">암호화폐의 입출금 소요 시간은 암호화폐별로 다를 수 있으며 보통 10분~30분 정도 소요 됩니다. 다만 블록체인 네트워크 상에 일시적으로 과도한 입출금 요청이 발생하는 경우 다소 지연될 수 있습니다.</span>
									</div>
									
									 <h3>
										<span class="faq-category">암호화폐 입금/출금</span>
										<span>암호화폐 출금 주소를 잘못 입력한 경우 어떻게 하나요?</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">암호화폐 특성 상 출금 신청이 완료되어 블록체인에 기록되면 취소가 불가능 합니다. 송금과정은 블록체인 네트워크에서 처리되므로 Bita500에서 도움을 드릴수가 없습니다. 반드시 출금 주소를 반복 확인하시고 출금신청 하시기 바랍니다.  </span>
									</div>
									<h3>
										<span class="faq-category">암호화폐 입금/출금</span>
										<span>입금 주소에 다른 암호화폐를 입금한 오입금의 경우 어떻게 하나요?</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">
											오입금 사례별 복구를 도와드릴 수 있는 상황은 제한적이며, 상황에 따라 복구 작업을 위한 수수료 및 시일이 소요됩니다.
											<br><br>
											※ 오입금 복구 가능한 경우
											<br>
											1) [BTC를 BCH로] 또는 [BCH를 BTC로] 오입금(BTC, BCH 오입금의 경우)
											<br>
											- 접수 완료 후 수수료로 50만원 상당의 BTC를 정해진 주소로 전송 후 복구가 진행됩니다.
											<br>
											<br>
											2) 리플 (XRP) Destination Tag 를 잘못 작성한 오입금
											<br>
											- 해당 Destination Tag나 메모가 타인에게 발급되지 않은 경우, 영업일 기준 최대 5일이 소요됩니다.
											<br>
											해당 Destination Tag나 메모가 타인에게 발급되고 오입금 받은 고객이 반환에 협조하지 않는 경우, 부당이득반환청구 소송을 해야할 수도 있습니다.
											<br>
											<br>
											3) [ERC20을 ETH로] 또는 [ERC20을 ERC20으로] 오입금
											<br>
											-1:1 문의하기를 통해 접수해 주세요. 처리에 시간이 소요될 수 있습니다.
											<br>
											<br>
											4) ICO에 참여하여 거래소 핫월렛 ETH 주소로 잘못 전송된 경우
											<br>
											-1:1 문의하기를 통해 접수해 주세요. 처리에 시간이 소요될 수 있습니다.
											<br>
											<br>
											※ 오입금 접수는 [고객지원 – 1:1 문의하기 – 입출금] 메뉴를 통해 접수해 주세요.
											<br>
											※ 기술적 문제로 모든 오입금 내역이 복구되지 않을 수 있습니다.
											</span>
									</div>
									
									 <h3>
										<span class="faq-category">암호화폐 입금/출금</span>
										<span>암호화폐 입금시 주소외 추가로 입력해야 하는 정보를 누락하고 전송한 경우는 어떻게 하나요?</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">블록체인 트랜잭션ID 나 해시값을 1:1 문의에 문의구분 암호화폐 입출금 선택하고 올려주세요. 다만 이 경우 처리하는데 최장 5일까지 소요 될 수 있습니다. 추가로 고객지원 > 인증자료제출 화면에서 인증목적구분을 암호화폐 입출금 선택하시고 보내신 사이트에서 전송상세 내역을 캡쳐해 올려 주셔야 합니다.</span>
									</div>
									
									 <h3>
										<span class="faq-category">암호화폐 입금/출금</span>
										<span>원화 입금없이 암호화폐만 입금했다 출금할 경우에도 72시간 제한이 있나요?</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">암호화폐만 입금했다 매도 후 원화로 출금하거나 암호화폐를 출금하는 경우에는 제한이 없습니다.</span>
									</div>
									
									 <h3>
										<span class="faq-category">암호화폐 입금/출금</span>
										<span>암호화폐 입금이 하루 이상 지났는데도 처리되지 않고 있습니다. 어떻게 하나요?</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">[고객지원 – 1:1 문의 – 입출금] 메뉴에서 해당 암호화폐 선택 후 제목에 암호화폐명을 넣어 “OOO 입금요청합니다 – 입금 후 24시간 경과＂ 라고 경과 시간을 기재해 주시면 우선적으로 확인해 드리겠습니다. TXID를 문의 내용에 기입해 주시고 보내신 쪽에서 전송내역을 수량이 보이게끔 캡쳐 및 사진촬영 하셔서 파일 확장자 jpg ,png 로 파일첨부하여 문의해 주시기 바랍니다.</span>
									</div>
									
									 <h3>
										<span class="faq-category">암호화폐 입금/출금</span>
										<span>보유하고 있는 잔고보다 출금가능 수량이 적게 표시됩니다 왜 그런가요?</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">현재 암호화폐 매도 주문을 걸어 놓으신게 없는지 확인 하십시오 미체결 매도 주문이 없는데 그렇다면 1:1 문의에 올려 주세요.</span>
									</div>
									
									 <h3>
										<span class="faq-category">암호화폐 입금/출금</span>
										<span>트랜잭션ID (TXID) 또는 해시값이 뭔가요?</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">암호화폐를 거래소간에 송금할시 블록체인 네트워크 상에서 암호화폐 송금 내역을 식별하고 추적할 수 있도록 하는 유일한 키 값입니다. 해당 값이 없으면 암호화폐 송금 내역을 찾는것이 불가능 하며 송금측 거래소 사이트의 암호화폐 출금내역 화면에서 조회 및 확인 할 수 있습니다.</span>
									</div>
								</div>
							</div>
								
							<div id="tabs-5" class="mgt10">
								<div id="accordion5" >
								   
									<h3>
										<span class="faq-category">거래,주문</span>
										<span>거래수수료는 얼마인가요?</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">거래수수료는 매수/매도 모두 0.1%를 적용하고 있습니다.</span>
									</div>
								   <!-- <h3>
										<span class="faq-category">거래,주문</span>
										<span>정정주문이 뭔가요?</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">모바일 앱에서만 가능한 정정주문은 기존 매수 또는 매도 주문 내역 중 주문수량 또는 주문가격을 변경하여 재 주문 하는 경우를 말하며 기존 주문내역을 취소하고 새로운 주문을 신규로 내는 번거로움을 없애고자 제공하는 주문 기능입니다.</span>
									</div>-->
									
									<h3>
										<span class="faq-category">거래,주문</span>
										<span>미체결 주문은 어떻게 처리 되나요?</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">주문 취소 전까지는 미체결 상태로 계속 남아 있게 됩니다.</span>
									</div>
									
									<h3>
										<span class="faq-category">거래,주문</span>
										<span>웹에서 주문 취소가 되지 않습니다.</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">일시적인 화면 갱신 장애로 인한 것일 수 있으니 화면 새로고침을 해 보시면 취소결과 확인이 가능합니다. 정상화 될 때까지 불편하시더라도 우선 화면 새로고침을 하여 확인바랍니다.</span>
									</div>
									
									<h3>
										<span class="faq-category">거래,주문</span>
										<span>주문 체결이 되었는데 체결 수량이나 금액이 실제 주문 내역과 일치하지 않습니다.</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">[고객지원 - 1:1 문의 – 기타] 메뉴에서 문의 내용을 남겨 주십시오. 확인 후 답변 드리겠습니다.</span>
									</div>
								</div>
							</div>
								
							<div id="tabs-6" class="mgt10">
								<div id="accordion6">
									
								   <h3>
										<span class="faq-category">기타</span>
										<span>회원 탈퇴 방법을 알려주세요</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">로그인 하신 후 [고객지원 - 1:1 문의 – 계정 - 회원탈퇴] 메뉴에서 탈퇴사유와 함께 탈퇴 요청을 해 주십시오. 탈퇴 처리전에 회원님의 계정에는 원화 및 암호화폐 잔고가 없어야 합니다.</span>
									</div>
									<h3>
										<span class="faq-category">기타</span>
										<span>다른 궁금한 사항은 어디에 물어보면 되나요?</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">로그인 하신 후 [고객지원 - 1:1 문의 – 기타] 메뉴에서 문의사항을 등록 하시면 담당자가 확인 후 답변을 드립니다.</span>
									</div>
									<h3>
										<span class="faq-category">기타</span>
										<span> Token launchpad 참여를 했습니다. 결과는 어디서 확인 가능한가요?</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">[투자내역 – 잔고변경이력] 메뉴에서 변경사유를 Token launchpad로 선택하고 날짜를 선택하신 후 조회하시면 됩니다.</span>
									</div>
									<h3>
										<span class="faq-category">기타</span>
										<span>신분증 제출시 신분증과 신분증 들고 있는 사진 둘다 제출해야 하나요?</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">신분증과 신분증 들고 있는 사진 모두 제출해야 합니다.</span>
									</div>
									<!--<h3>
										<span class="faq-category">기타</span>
										<span>모바일 앱에서는 로그인이 되는데 PC 웹에서는 비밀번호 오류라고 하면서 로그인이 안됩니다.</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">모바일 앱 로그인시 입력한 로그인ID와 동일하게 웹에서도 ID 입력하셨는지 확인 하십시오. 동일하게 입력해도 안 된다면 비밀번호에 특수문자 &#60; 또는 &#40; 또는 &#39; 세 가지 문자 중 하나가 포함되어 있는지 확인하십시오. 포함되어 있다면 해당 특수문자가 포함되지 않은 비밀번호로 모바일 앱에서 변경한 후 웹로그인 해보시길 바랍니다.</span>
									</div>-->
									<h3>
										<span class="faq-category">기타</span>
										<span>모바일 웹은 언제 서비스 되나요?</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">정확한 날짜를 확정 드릴 수 없어 죄송합니다. 서비스 가능 시점이 되면 공지사항으로 안내 됩니다.</span>
									</div> 
								</div>
							</div>
						
						</div>
						<p class="mgt20">원하는 답변이 없으신가요? 카카오톡 문의를 이용해주시면 신속하게 답변드리겠습니다.</p>
					</div>
				</li>	
				<!--자주하는 질문 끝-->
				
				<c:if test="${sessionScope.userInfo.loginYn == 1}">
				
				<!--1:1 문의 시작-->
				<li id="vtab7" style="display:none;">
					<h1 class="subtitle">
						1:1 문의
					</h1>

					<p id="inquriy-main-category-title" class="bolder">문의 유형을 선택해 주세요</p>

					<!--대분류 시작-->
					<div id="inquriy-main-category" class="category step1" style="display: block;">
						<div class="cate-tit">
							대분류를 선택해주세요
						</div>

						<!--메인카테고리 시작-->
						<ul class="contact main-category">
							<li val="accnt">계정</li>
							<!-- <li val="alimtok">알림톡</li> -->
							<!-- <li val="cacaopay">카카오페이</li> -->
							<li val="depwitd">입출금</li>
							<!-- <li val="sbprob">매매 장애</li> -->
							<li val="request">제안</li>
							<!-- <li val="general">일반</li> -->
							<li val="bankaccd">금융사고</li>
							<!-- <li val="misdeposit">오입금</li> -->
							<li val="events">이벤트</li>
							<li val="withdrawrequest">인증자료 제출</li>
							<li val="withdrawrightnow">기타</li>
						</ul>
						<!--메인카테고리 끝-->
					</div>
					<!--대분류 끝-->

					<!-- 소분류 코인외 문의 시작 : 대분류를 선택하면 나타납니다.-->
					<div id="inquriy-sub-category" class="category step2" style="display: none;">
						<div class="cate-tit">
							소분류를 선택해 주세요
						</div>

						<!--서브카테고리 시작-->
						<ul class="contact sub-category accnt">
							<!--계정 리스트 시작-->
							<li val="sub-accnt-01">휴대전화 번호 초기화</li>
							<!-- <li val="sub-accnt-02">계정복구</li> -->
							<li val="sub-accnt-03">개명</li>
							<li val="sub-accnt-04">회원탈퇴</li>
							<li val="sub-accnt-05">API문의</li>
							<!--계정 리스트 끝-->
						</ul>
						<ul class="contact sub-category alimtok">
							<!--알림톡 시작-->
							<li val="sub-alimtok-01">알림톡</li>
							<!--알림톡 끝-->
						</ul>
						<ul class="contact sub-category cacaopay">
							<!--카카오페이 시작-->
							<li val="sub-cacaopay-01">카카오페이</li>
							<!--카카오페이 끝-->
						</ul>
						<ul class="contact sub-category request">
							<!--제안 시작-->
							<li val="sub-request-01">서비스 개선</li>
							<li val="sub-request-02">투자제안</li>
							<li val="sub-request-03">제휴문의</li>
							<!--제안 끝-->
						</ul>
						<ul class="contact sub-category general">
							<!--일반 시작-->
							<li val="sub-general-01">암호화폐 기타 문의</li>
							<li val="sub-general-02">기타</li>
							<li val="sub-general-03">출금 정지 이의 신청</li>
							<li val="sub-general-04">이벤트 지급 관련 문의</li>
							<!--일반 끝-->
						</ul>
						<ul class="contact sub-category bankaccd">
							<!--금융사고 시작-->
							<!-- <li val="sub-bankaccd-01">수사/금융/공공기관</li> -->
							<li val="sub-bankaccd-02">해킹/명의도용/보이스피싱</li>
							<!-- <li val="sub-bankaccd-03">다단계 신고/불량사용자 신고</li> -->
							<li val="sub-bankaccd-04">금융사기계정 거래 정지 해제</li>
							<!--금융사고 끝-->
						</ul>
						<!-- <ul class="contact sub-category misdeposit">
							오입금 시작
							<li val="sub-misdeposit-01">BTC계열 오입금</li>
							<li val="sub-misdeposit-02">ERC20(상장)을 ETH지갑주소로 오입금</li>
							<li val="sub-misdeposit-03">ERC20(상장)을 ETC지갑주소로 오입금</li>
							<li val="sub-misdeposit-04">ERC20(상장)을 ERC20지갑주소로 오입금</li>
							<li val="sub-misdeposit-05">ERC20(비상장)을 ETH지갑주소로 오입금</li>
							<li val="sub-misdeposit-06">ERC20(비상장)을 ETC지갑주소로 오입금</li>
							<li val="sub-misdeposit-07">ERC20(비상장)을 ERC20지갑주소로 오입금</li>
							<li val="sub-misdeposit-08">ETC를 ETH지갑주소로 오입금</li>
							<li val="sub-misdeposit-09">ETH를 ETC지갑주소로 오입금</li>
							<li val="sub-misdeposit-10">ICO코인을 ETH지갑주소로 오입금</li>
							<li val="sub-misdeposit-11">수수료납부</li>
							오입금 끝
						</ul> -->
						<ul class="contact sub-category events">
							<!--이벤트 시작-->
							<li val="sub-event-01">이벤트</li>
							<!-- <li val="sub-event-02">●●● 이벤트</li> -->
							<!--이벤트 끝-->
						</ul>
						<ul class="contact sub-category withdrawrequest">
							<!--출금신청 시작-->
							<li val="sub-withdrawrequest-01">인증자료 제출</li>
							<!--출금신청 끝-->
						</ul>
						<ul class="contact sub-category withdrawrightnow">
							<!--바로출금 시작-->
							<li val="sub-withdrawrightnow-01">기타</li>
							<!--바로출금 끝-->
						</ul>
						<!--서브카테고리  끝-->
					</div>
					<!--소분류 코인외 문의 끝-->

					<!--소분류 코인 문의 시작 : 대분류에서 코인건 문의일경우 나타납니다.-->
					<div id="inquriy-coin-category" class="category step2_coin"  style="display: none;">
						<div class="cate-tit">
							<p>
								<input id="inquriy-coin-category-search" type="text" placeholder="문의 하실 암호화폐명을 검색해 주세요">
								<button>검색</button>
							</p>
						</div>

						<!--코인리스트 시작-->
						<ul class="contact coin-category">
							<li val="KRW" data-cho="ㅇㅎㄱㅈㄷㄹㅂㄱ">은행계좌등록/변경</li>
							<li val="KRW" data-cho="ㅇㅎ">원화 (KRW)</li>
							<li val="BTC" data-cho="ㅂㅌㅋㅇ">비트코인 (BTC)</li>
							<li val="ETH" data-cho="ㅇㄷㄹㅇ">이더리움 (ETH)</li>
							<li val="BCH" data-cho="ㅂㅌㅋㅇㅋㅅ">비트코인캐시 (BCH)</li>
							<li val="XRP" data-cho="ㄹㅍ">리플 (XRP)</li>
							<li val="YEP" data-cho="ㅇㅌㅋ">옙 (YEP)</li>
							<li val="RVN" data-cho="ㄹㅇㅂㅋㅇ">레이븐코인 (RVN)</li>
							<!-- <li val=""    data-cho="ㅁㅁㅈㅇㅁㅇ">매매장애 문의</li> -->
						</ul>
						<!--코인리스트  끝-->
					</div>
					<!--소분류 코인 문의 끝-->

					<!--문의하기 공통 입력 테이블 시작 : 소분류 모두 선택후 나타납니다.-->
					<div id="inquiry-input-div" class="inquiry"  style="display: none;">

						<p class="bolder" style="clear: both">문의 내용을 입력해 주세요</p>
						<table class="type01">
							<colgroup>
								<col style="width: 130px">
								<col>
							</colgroup>
							<tbody>
								<tr>
									<th>문의 유형</th>
									<td>
										<span id="inquiry-input-main-cate">입출금</span>
										<span> > </span>
										<span id="inquiry-input-sub-cate">비트코인</span>
									</td>
								</tr>
								<tr id="inquiry-input-attach-file">
									<th>파일첨부</th>
									<td>
										<div class="filebox">
											<!-- 첨부된 파일 리스트 : Start -->
											<div class="filebox-inner mCustomScrollbar">
												<div class="filebox-item" data-index="0">
												</div>
											</div>
											<!-- //첨부된 파일 리스트 : End -->
											<!-- 파일첨부 버튼 : Start -->
											<label for="ex_filename" id="attachFile">파일첨부</label>
											<input type="file" id="ex_filename" class="upload-hidden">
											<!-- //파일첨부 버튼 : End -->
										</div>
										<div class="tip-txt">
											- 파일 확장자는 jpg, png 만 가능합니다. <br>
											- 총 용량은 50MB 미만이어야 합니다.
											<span class="sub-accnt-03" style="display:none;">
											- 주민등록초본 (발급일로부터 3개월 이내 제출할 경우만 유효)
											</span>
										</div>
									</td>
								</tr>

								<tr>
									<th>제목</th>
									<td><input id="inquiry-input-title" type="text" placeholder="제목을 입력해 주세요" style="max-width: 100%"></td>
								</tr>
								<tr>
									<th>내용</th>
									<td>
										<textarea id="inquiry-input-content" placeholder="내용을 입력해 주세요"></textarea>
									</td>
								</tr>
								<tr class="sub-accnt-03" style="display:none;">
									<th>개인정보 수집 및<br>이용 동의(필수)</th>
									<td>
										- 수집 목적 : '개명' 상담 문의 처리<br>
										- 수집 항목 : 개명후 성함, 개명증명할 수 있는 주민등록 초본과 같은 서류<br>
										- 보유 기간 : 탈퇴 후 5년<br>
										- 회원은 상담 문의 처리로 개인정보를 제공하는 것에 동의하지 않을 수 있으며, 동의를 거부하시는 경우 처리가 불가능합니다.<br>
										- 위 내용을 반드시 전부 확인하신 뒤 동의하신다면 체크해주세요<br>
										<span class="checks-item" style="height:0;border:none;">
				                            <input type="checkbox" id="agree">
				                            <label for="agree">동의</label>
				                        </span>
									</td>
								</tr>
							</tbody>
						</table>
						<div class="btn-wrap">
							<button onClick="requestInquiry()" class="big blue">1:1 문의하기 </button>
							<button onClick="cancelInquiry()" class="big">취소하기</button>
						</div>
					</div>
					<!--문의하기 공통 입력 테이블 끝-->
				</li>
				<!--1:1 문의 끝-->

				<!--문의내역 시작-->
				<li id="vtab8" style="display:none;">
					<h1 class="subtitle">
						문의내역
					</h1>

					<!-- 문의내역 : Start -->
					<div class="inq-list" id="inquiry-list">
						<h2 class="tabletitle">1:1 문의내역</h2>
						<div class="type01">
							<table>
								<colgroup>
									<col style="width:60px;">
									<col>
									<col style="width:155px;">
									<col style="width:155px;">
									<col style="width:80px;">
								</colgroup>
								<thead>
									<tr>
										<th>NO</th>
										<th>제목</th>
										<th>작성일</th>
										<th>답변일</th>
										<th>답변여부</th>
									</tr>
								</thead>
								<tbody id="inquiry_list_table_tbody">
									<!-- 문의내역 없을 때 : Start -->
									<tr>
										<td colspan="5" class="no-data">
											<!-- no-data 클래스 -->
											문의 내역이 없습니다
										</td>
									</tr>
									<!-- //문의내역 없을 때 : End -->
								</tbody>
							</table>

							<!-- 페이징 + 1:1 문의하기 버튼 : Start -->
							<div>
								<!-- 페이징. 문의내역이 없을 때는 페이징 없음 : Start -->
								<ul class="pagination" id="inquiry-pagination">
									<li><a class="btn-page first"></a></li>
									<li><a class="btn-page prev"></a></li>
									<li><a href="#" class="active">1</a></li><!-- 선택된 번호의 a 태그에 acitve 클래스 추가 -->
									<li><a class="btn-page next"></a></li>
									<li><a class="btn-page last"></a></li>
								</ul>
								<!-- //페이징. 문의내역이 없을 때는 페이징 없음 : End -->

							</div>
							<!-- //페이징 + 1:1 문의하기 버튼 : End -->
						</div>
					</div>
					<!-- //문의내역 : End -->

					<!-- 문의내용 및 답변 : Start -->
					<div class="inq-view" id="inquiry-conts-div">
						<h2 class="tabletitle">문의내용 및 답변</h2>
						<div>
							<table class="type02">
								<colgroup>
									<col style="width:150px;">
									<col>
								</colgroup>
								<thead>
									<tr>
										<th>
											<p id="inquire-category" class="text-ellips">
											</p>
										</th>
										<th id="inquiry-conts-title" class="text-ellips">
										</th>
									</tr>
								</thead>
								<tbody>
									<!-- 선택한 내역 없을 때 : Start -->
									<tr id="inquire-reply-dont-select">
										<td colspan="2" class="no-data">선택하신 내역이 없습니다</td>
									</tr>
									<!-- //선택한 내역 없을 때 : End -->
									<!-- 문의내용 : Start -->
									<tr>
										<th>문의내용</th>
										<td id="inquiry-conts">
										</td>
									</tr>
									<!-- //문의내용 : End -->
									<!-- 답변내용 : Start -->
									<tr>
										<th>답변내용</th>
										<td id="reply-conts">
										</td>
									</tr>
									<!-- //답변내용 : End -->
								</tbody>
							</table>
						</div>
					</div>
					<!-- //문의내용 및 답변 : End -->

				</li>
				<!--문의내역 끝-->
			
				</c:if>
			</ul>
		</div>

	</article>

</section>
<!--컨텐츠 내용 영역 끝-->

<script type="text/javascript">
	var noticeSeq;
	var eventSeq;

	//세로탭메뉴
	$(function() {
		$("ul.vpanel li:not(" + $("ul.vtab li a.vselected").attr("href") + ")").hide()
		$("ul.vtab li a").click(function() {
			$("ul.vtab li a").removeClass("vselected");
			$(this).addClass("vselected");
			$("ul.vpanel li").hide();
			$($(this).attr("href")).show();
			return false;
		});
	});
</script>

<script src="/resources/js/yahobit/support/yahobit.support.js?v=<spring:message code="yahobit.support.js.version"/>"></script>

<script>
//마켓별 전체보기
$(document).ready(function() {
	if('${tab}' != '') {
		$('.vtab').find('a:eq(' + (parseInt('${tab}') - 1) + ')').trigger('click');
		if('${tab}' == '1') noticeSeq = '${no}';
		else if('${tab}' == '2') eventSeq = '${no}';
	}
	
	$('.btn-folding').click(function() {
		$('.folding').toggleClass('more');
	});
	$('.btn-folding1').click(function() {
		$('.folding1').toggleClass('more');
	});
	$('.btn-folding2').click(function() {
		$('.folding2').toggleClass('more');
	});

	// 공지사항 리스트 가져오기
	getNoticeList();
	// 이벤트 리스트 가져오기
	getEventList();
	// 이용안내 > 최소입금금액 리스트 가져오기
	getMinOrderAmtList();
	// 수수료안내 > 거래수수료, 입출금 수수료 리스트 가져오기
	getFeeList();
	
	// 1:1 문의내역 리스트 가져옴
	getInquiryList();
	
	$('.filebox-item').on('click', '.file-btn-del', function() {
		let $this = $(this);
		$('#' + $this.data('file')).remove();
		$this.closest('p').remove();
	});
	
	$('.filebox').on('change', 'input', function() {
		let $this = $(this)
			, fileName = $this[0].files[0].name
			, size = $this[0].files[0].size
			, index = fileName.lastIndexOf('.')
			, exp = fileName.substr(index+ 1).toLocaleLowerCase()
			, template = '';
		
		size = ((size / 1024) / 1024).toFixed(2);
		
		if(exp !== 'jpg' && exp !== 'png') {
			lrt.client('파일 확장자는 jpg, png 만 가능합니다.', '오류');
			return false;
		} else if(!isPossibleSize()) {
			lrt.client('총 용량은 50MB 미만이어야 합니다.', '오류');
			$this.val('');
			return false;
		} else {
			let $filebox = $('.filebox')
				, $fileboxItem = $('.filebox-item')
				, template = $('#template-inquiry-upload-file').html()
				, cnt = parseFloat($fileboxItem.data('index')) + 1;
			
			$fileboxItem.append(
					template.replace(/{{fileId}}/g, $this.attr('id'))
							.replace(/{{fileName}}/g, fileName)
							.replace(/{{size}}/g, size)).data('index', cnt);
			
			$filebox.append('<input type="file" id="ex_filename' + cnt + '" class="upload-hidden">');
			$('#attachFile').attr('for', 'ex_filename' + cnt);
		}
	});
	
	getOutLimit();
});

</script>

<Script id="template-list-empty" type="text/template">
	<tr onclick="event.cancelBubble = true;">
		<td colspan="{{ColSpan}}">You have no data </td>
	</tr>
</Script>

<script id="template-notice-list-table-tr" type="text/template">
	<tr>
		<td class="{{ClassNew}} {{ClassER}}">
			<span style="cursur:pointer;font-size:14px;" onClick="javascript:toggleNoticeList('{{NoticeSeq}}', false);" class="{{ClassTop}}">{{NoticeTitle}}</span>
			<input type="hidden" name="notice-seq" value="{{NoticeSeq}}">
		</td>
		<td>{{NoticeDate}}</td>
	</tr>
</script>

<script id="template-event-list-table-tr" type="text/template">
	<tr>
		<td>
				{{EventTitle}}
				<input type="hidden" name="event-seq" value="{{EventSeq}}">
		</td>
		<td>{{EventDate}}</td>
		<td class="{{ClassOnClose}}">{{OnCloseTxt}}</td>
	</tr>
</script>
	
<script id="template-min-order-amount-table-tr" type="text/template">
	<tr>
		<td>{{ItemEngName}}</td>
		<td>{{ItmeName}}</td>
		<td>
			<span>{{MinOrdAmt}}</span>
			<span>{{ItemBaseCoinName}}</span><!-- 단위 -->
		</td>
	</tr>
</script>
<script id="template-trading-fee-table-tr" type="text/template">
	<tr>
		<td>{{MarketName}}</td>
		<td>{{SellFee}}</td>
		<td>{{BuyFee}}</td>
	</tr>
</script>
<script id="template-deposit-withdraw-fee-table-tr" type="text/template">
	<tr>
		<td>{{CoinSymbolName}}</td>
		<td>{{MinDepositQty}}</td>
		<td>{{WtdrwFee}}</td>
		<td>{{MinWtdrwQty}}</td>
	</tr>
</script>
<!-- 1:1 문의내역 리스트 -->
<script id="template-inquiry-list-table-tr" type="text/template">
	<tr class="{{ClassActive}}" data-seq="{{InquirySeq}}">
		<td>{{RowNum}}</td>
		<td class="text-ellips">
			{{InquiryTitle}}
		</td>
		<td>{{RequestDT}}</td>
		<td>{{ReplyDT}}</td>
		<td>{{ReplyStatus}}</td>
	</tr>
</script>

<script id="template-inquiry-upload-file" type="text/template">
	<p>
		<span><a href="javascript:void(0);" class="file-btn-del" data-file="{{fileId}}"></a></span>
		<span class="file-name">{{fileName}}</span>
		<span class="file-volume">
			<span>{{size}}</span>
			<span>MB</span>
		</span>
	</p>
</script>