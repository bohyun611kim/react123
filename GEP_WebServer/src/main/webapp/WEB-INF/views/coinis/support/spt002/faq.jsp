<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/taglib/taglib.jsp" %>

<!--컨텐츠 내용 영역 시작-->
<section>
	<!--FAQ 전체 내용 시작-->
	<article class="bgwhite">

		<h1 class="sub-title">FAQ</h1>
		<div class="content-item">

			<!-- 페이지 탭 : Start -->
			<div id="tab-group" class="tab-dark" style="display:none;">

				<ul>
					<li><a href="#tabs-1">Whole</a></li>
					<li><a href="#tabs-2">Sigh-up</a></li>
					<li><a href="#tabs-3">Infomation modification</a></li>
					<li><a href="#tabs-4">Deposit/Withdrawal</a></li>
					<li><a href="#tabs-5">Trading/Orders</a></li>
					<li><a href="#tabs-6">Etc</a></li>
				</ul>
				<!-- 전체 탭 컨텐츠 : Start -->
	
				<div id="tabs-1" class="mgt10">
					<div id="accordion1">
						<h3>
							<span class="faq-category">가입 및 개인정보 변경</span>
							<span>회원가입 방법이 어떻게 되나요?</span>
						</h3>
						<div>
							<span class="answer-mark">A</span>
							<span class="answer-content">회원 가입은 Bita500 PC용 웹페이지 또는 모바일 웹(추후 오픈 예정)에서 가능합니다.</span>
						</div>
						<h3>
							<span class="faq-category">가입 및 개인정보 변경</span>
							<span>회원가입 인증메일이 오지 않아요.</span>
						</h3>
						<div>
							<span class="answer-mark">A</span>
							<span class="answer-content">받은편지함이 아닌 스팸함이나 전체편지함도 한번 찾아보시길 바랍니다. 구글 ( gmail.com ) 의 경우, '카테고리-프로모션탭'을 확인해 주시기 바랍니다. 또한 메일이 수신되는 데에 간혹 시간이 걸리는 경우가 있습니다. 기다려도 메일이 오지 않을 경우, <br/>가입하신 이메일 ID 로 로그인하시면 인증메일을 재발송 하실 수 있습니다. <br/>재발송을 통해 진행해보시길 바랍니다. <br/>개선이 되지 않는다면 1:1문의 문의 부탁 드립니다.
								</span>
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
							<span class="answer-content">법인폰으로는 이용이 불가 합니다.</span>
						</div>
						<h3>
							<span class="faq-category">가입 및 개인정보 변경</span>
							<span>개인정보를 변경할 수 있나요?</span>
						</h3>
						<div>
							<span class="answer-mark">A</span>
							<span class="answer-content">개인정보의 변경은 로그인 후 마이페이지 아래 각 서브메뉴에서 변경코자 하는 정보와 매칭되는 화면에서 변경처리 하시면 됩니다. 변경 가능한 개인 정보로는 로그인 비밀번호, 휴대폰번호, 인증레벨, OTP설정/해제 등이 있습니다</span>
						</div>
						<h3>
							<span class="faq-category">가입 및 개인정보 변경</span>
							<span>휴대전화 번호를 변경할 수 있나요? 어떻게 해야하나요?</span>
						</h3>
						<div>
							<span class="answer-mark">A</span>
							<span class="answer-content">휴대폰 번호 변경은 초기화 접수를 통해서만 가능합니다.<br/>[마이페이지 – 보안인증 – 휴대폰 본인인증] 초기화 메뉴를 통해서 <br/>안내되는 팝업창의 절차에 따라 접수하시길 바랍니다.</span>
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
							<span class="answer-content">네, 상향조건 만족해야 합니다</span>
						</div>
						<h3>
							<span class="faq-category">가입 및 개인정보 변경</span>
							<span>가입시 이름을 잘못 입력하여 휴대폰 본인인증이 안됩니다</span>
						</h3>
						<div>
							<span class="answer-mark">A</span>
							<span class="answer-content">1:1 문의에 남겨 주시면 이름을 변경해 드립니다</span>
						</div>
						<h3>
							<span class="faq-category">가입 및 개인정보 변경</span>
							<span>OTP 설정시 OTP 인증번호가 틀렸다고 표시됩니다</span>
						</h3>
						<div>
							<span class="answer-mark">A</span>
							<span class="answer-content">구글 OTP 앱에 표시된6자리 번호는 일정 시간 후 갱신이 되니 갱신된 번호를 다시 입력해야 합니다</span>
						</div>
						<!-- <h3>
							<span class="faq-category">가입 및 개인정보 변경</span>
							<span>전화번호를 변경하고 싶습니다. 어떻게 해야 하나요?</span>
						</h3>
						<div>
							<span class="answer-mark">A</span>
							<span class="answer-content">웹사이트 마이페이지 - 휴대폰 번호변경 화면에서 변경 가능 합니다</span>
						</div>-->
						
						
						<h3>
							<span class="faq-category">원화 입금/출금</span>
							<span>원화 입금은 어떻게 하나요?</span>
						</h3>
						<div>
							<span class="answer-mark">A</span>
							<span class="answer-content">입출금-입금신청 화면 이동 -> 본인 명의 은행계좌 등록 후 수동입금 / 자동입금으로 나뉩니다. <br/><strong>자동입금시 </strong>실시간으로 입금처리 가능<br/>입출금 – 입금신청- 자동입금-  (본인이름+숫자4자리 홍길동 1111)<br/>자동입금시 수동입금요청 이중으로 하지 않아도 됩니다.<br/><strong>수동입금 시 </strong>수동입금 요청 순서 => 입출금 – 입금신청- 수동입금- 금액-제출<br/><span class="red">*고액입금 시 이체결과확인증 첨부후 관리자가 입금처리 진행*</span></span>
						</div>
						<h3>
							<span class="faq-category">원화 입금/출금</span>
							<span>원화 입금 확인이 안되고 있습니다</span>
						</h3>
						<div>
							<span class="answer-mark">A</span>
							<span class="answer-content">계좌이체 후 5분 이내에 원화 입금 내역을 확인 가능<br/>시스템 장애 또는 거래소에 등록하신 본인명의 은행계좌로 입금 하시지 않은 경우 입금 확인이 불가<br/>[고객지원 – 1:1 문의 – 인증자료 제출] 에서 이체결과확인증 제출해주시기바랍니다.
								</span>
						</div>
						
						<h3>
							<span class="faq-category">원화 입금/출금</span>
							<span>원화 출금 제한이 있나요?</span>
						</h3>
						<div>
							<span class="answer-mark">A</span>
							<span class="answer-content">원화 출금은 회원 가입 후 인증레벨3 이상인 회원<br/>각 인증레벨별로 월 출금한도 및 1일 출금한도가 정해져 있습니다.<br/>출금한도를 늘리기 위해서는 레벨을 업로드 진행자<br/>자세한 사항은 고객지원-이용안내 메뉴를 참조 바랍니다</span>
						</div>
						
						<h3>
							<span class="faq-category">원화 입금/출금</span>
							<span>입출금 은행계좌를 잘못 등록 하였습니다. 변경할려면 어떻게 하나요?</span>
						</h3>
						<div>
							<span class="answer-mark">A</span>
							<span class="answer-content">[고객지원 - 1:1 문의 – 은행계좌등록/변경] 메뉴에서 문의에 남겨 주시면 관리자 확인 후 계좌 초기화를 해 드립니다. 다만 한번이라도 기존 계좌 번호로 입금한 내역이 있는 경우 해킹에 의한 악의적인 변경요청을 방지하기 위해 인증자료를 제출 받습니다. 초기화 된 후 재 등록 하십시오</span>
						</div>

						
						<h3>
							<span class="faq-category">원화 입금/출금</span>
							<span>원화를 보유하고 있는데 출금가능 KRW 잔고가 0 으로 표시 됩니다</span>
						</h3>
						<div>
							<span class="answer-mark">A</span>
							<span class="answer-content">원화 출금 24시간 제한에 걸려 있는지 확인 하십시오</span>
						</div>
						

						
						<h3>
							<span class="faq-category">원화 입금/출금</span>
							<span> 원화 출금 24시간 제한이 무엇인가요?</span>
						</h3>
						<div>
							<span class="answer-mark">A</span>
							<span class="answer-content">보이스피싱등의 금융사고 방지하고 회원님들의 자산을 안전하게 지키기 위하여 24시간 이후에 원화 출금이 가능하도록 하였습니다.<br/>주말 및 공휴일도 출금불가능, 영업일(월~금 오전9시~ 저녁6시) 에 원화출금 가능합니다.</span>
						</div>
						
						<h3>
							<span class="faq-category">원화 입금/출금</span>
							<span> 원화 입금 시 계좌이체를 먼저하고 입금신청을 해야 하나요?</span>
						</h3>
						<div>
							<span class="answer-mark">A</span>
							<span class="answer-content">아닙니다. 반드시 입금신청을 먼저 하시고 발급받은 ‘ 고객님의 성함 + 입금코드 ’  받는통장 메모기입하여 계좌이체 진행하기</span>
						</div>
						

						
						<h3>
							<span class="faq-category">원화 입금/출금</span>
							<span>거래소에 등록된 입출금 계좌가 아닌 다른 계좌에서 입금을 해 버렸습니다.</span>
						</h3>
						<div>
							<span class="answer-mark">A</span>
							<span class="answer-content">충전 요청을 하신 후에 입금 내역 캡쳐, 신분증 주민번호 뒷자리 가리고 캡쳐, 출금하신 통장사본 캡쳐 업로드하면 처리 진행.<br/>다만, 본인명의 통장계좌만가능 타인명의 계좌 일 경우 불가능</span>
						</div>

						<h3>
							<span class="faq-category">원화 입금/출금</span>
							<span>평생계좌번호(전화번호 사용)로도 입금이 가능한가요?</span>
						</h3>
						<div>
							<span class="answer-mark">A</span>
							<span class="answer-content">불가합니다. 해당 입금 수단으로 입금하시면 입금확인이 어렵습니다. <br/>해당 건에 대한 환급시에도 인증자료 제출 등 많은 시간이 소요 됩니다.</span>
						</div>

						<h3>
							<span class="faq-category">원화 입금/출금</span>
							<span>입금신청한 금액과 다른 금액을 계좌이체 해 버렸습니다. 어떻게 하나요?</span>
						</h3>
						<div>
							<span class="answer-mark">A</span>
							<span class="answer-content">[고객지원 – 1:1 문의 – 인증자료제출] 메뉴에서 이체결과확인증을 제출해 주시면 확인 후 입금처리 해 드립니다.</span>
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
							<span>원화 입금이 하루 이상 지났는데도 처리되지 않고 있습니다. 어떻게 하나요?</span>
						</h3>
						<div>
							<span class="answer-mark">A</span>
							<span class="answer-content">[고객지원 – 1:1 문의 – 입출금 – 원화 (KRW)] 메뉴를 선택하신 후 <br/>제목에 “원화 입금 요청, 24시간 경과” 로 문의하기 하시면 확인 후 빠르게 처리해 드립니다.<br/>빠른 처리를 위해 추가자료(입금내역증 등)을 요청할 수 있습니다.</span>
						</div>

						<h3>
							<span class="faq-category">원화 입금/출금</span>
							<span>보유하고 있는 원화보다 출금가능 잔액이 적게 표시됩니다 왜 그런가요?</span>
						</h3>
						<div>
							<span class="answer-mark">A</span>
							<span class="answer-content">현재 매수 주문 접수를 하진 않으셨는지 확인 부탁드립니다. <br/>미체결 매수 주문이 없는데도 같은 증상이라면 <br/>[고객지원 - 1:1 문의 – 입출금 – 원화 (KRW)] 메뉴에 문의를 남겨주시기 바랍니다.</span>
						</div>
						
						<h3>
							<span class="faq-category">원화 입금/출금</span>
							<span>인증자료 제출은 어디서 하나요?</span>
						</h3>
						<div>
							<span class="answer-mark">A</span>
							<span class="answer-content">[고객지원 – 1:1 문의 – 인증자료제출] 메뉴를 이용하시기 바랍니다</span>
						</div>

						<h3>
							<span class="faq-category">원화 입금/출금</span>
							<span>암호화폐 지갑 주소는 어디서 확인하나요?</span>
						</h3>
						<div>
							<span class="answer-mark">A</span>
							<span class="answer-content">로그인 후 입출금 메뉴에서 지갑 주소 확인을 원하시는 암호화폐를 선택하신 후 입금주소 탭을 클릭 하시면 확인 가능합니다.<br/>지갑 생성을 하지 않으셨다면 [입금 주소 생성하기] 버튼을 클릭하여 주소 생성이 가능합니다</span>
						</div>

						<h3>
							<span class="faq-category">원화 입금/출금</span>
							<span>암호화폐 출금 한도는 어떻게 되나요?</span>
						</h3>
						<div>
							<span class="answer-mark">A</span>
							<span class="answer-content">출금하고자 하는 암호화폐의 원화 환산액(출금수량*원화가격) 인증레벨 별 출금한도 이내에서만 가능합니다. 예를 들어 회원님의 인증레벨이 3이면 1일 출금한도는 3,000만원 이내에서만 가능하므로, 현재가가 반영된 원화 환산액 3천만원 이내의 암호화폐 수량 내에서만 가능합니다.</span>
						</div>

						<h3>
							<span class="faq-category">원화 입금/출금</span>
							<span>암호화폐 출금수수료는 얼마인가요?</span>
						</h3>
						<div>
							<span class="answer-mark">A</span>
							<span class="answer-content">출금수수료는 암호화폐 별로 상이하며 각 암호화폐 별 출금수수료는 [고객지원 – 수수료안내] 메뉴를 참조하시면 됩니다.</span>
						</div>

						<h3>
							<span class="faq-category">원화 입금/출금</span>
							<span>암호화폐 입출금 시간이 지연되는 이유가 있나요?</span>
						</h3>
						<div>
							<span class="answer-mark">A</span>
							<span class="answer-content">암호화폐의 입출금 소요 시간은 암호화폐별로 다를 수 있으며 보통 10분~30분 정도 소요 됩니다. 다만 블록체인 네트워크 상에 일시적으로 과도한 입출금 요청이 발생하는 경우 다소 지연될 수 잇습니다</span>
						</div>

						<h3>
							<span class="faq-category">원화 입금/출금</span>
							<span>암호화폐 출금 주소를 잘못 입력한 경우 어떻게 하나요?</span>
						</h3>
						<div>
							<span class="answer-mark">A</span>
							<span class="answer-content">암호화폐 특성 상 출금 신청이 완료되어 블록체인에 기록되면 취소가 불가능 합니다. 송금과정은 블록체인 네트워크에서 처리되므로 Bita500에서 도움을 드릴수가 없습니다. 반드시 출금 주소를 반복 확인하시고 출금신청 하시기 바랍니다.  </span>
						</div>

						<h3>
							<span class="faq-category">원화 입금/출금</span>
							<span>입금 주소에 다른 암호화폐를 입금한 오입금의 경우 어떻게 하나요?</span>
						</h3>
						<div>
							<span class="answer-mark">A</span>
							<span class="answer-content">
								오입금 사례별 복구를 도와드릴 수 있는 상황은 제한적이며, 상황에 따라 복구 작업을 위한 수수료 및 시일이 소요됩니다.
								<br>※ 오입금 복구 가능한 경우
								<br>1) 리플 (XRP) Destination Tag 를 잘못 작성한 오입금
								<br>- 해당 Destination Tag나 메모가 타인에게 발급되지 않은 경우, 영업일 기준 최대 5일이 소요됩니다.
								<br>해당 Destination Tag나 메모가 타인에게 발급되고 오입금 받은 고객이 반환에 협조하지 않는 경우, 부당이득반환청구 소송을 해야할 수도 있습니다.
								<br>-1:1 문의하기를 통해 접수해 주세요. 처리에 시간이 소요될 수 있습니다.
								<br>※ 오입금 접수는 [고객지원 – 1:1 문의하기 – 입출금] 메뉴를 통해 접수해 주세요.
								<br>※ 오입금 접수는 [고객지원 – 1:1 문의하기 – 입출금] 메뉴를 통해 접수해 주세요.
								<br>
								<img src="../img/yaho/img_proof_info1.jpg" alt="img">
								<img src="../img/yaho/img_proof_info2.jpg" alt="img">
							</span>
						</div>

						
						<h3>
							<span class="faq-category">원화 입금/출금</span>
							<span>암호화폐 입금시 주소외 추가로 입력해야 하는 정보를 누락하고 전송한 경우는 어떻게 하나요?</span>
						</h3>
						<div>
							<span class="answer-mark">A</span>
							<span class="answer-content">블록체인 트랜잭션ID 나 해시값을 1:1 문의에 문의구분 암호화폐 입출금 선택하고 올려주세요. 다만 이 경우 처리하는데 최장 5일까지 소요 될 수 있습니다.<br/>추가로 고객지원 -> 인증자료제출 화면에서 인증목적구분을 암호화폐 입출금 선택하시고 보내신 사이트에서 전송상세 내역을 캡쳐해 올려 주셔야 합니다.</span>
						</div>
						
						<h3>
							<span class="faq-category">원화 입금/출금</span>
							<span>원화 입금없이 암호화폐만 입금했다 출금할 경우에도 24시간 제한이 있나요?</span>
						</h3>
						<div>
							<span class="answer-mark">A</span>
							<span class="answer-content">암호화폐만 입금했다 매도 후 원화로 출금하거나 암호화폐를 출금하는 경우에는 제한이 없습니다</span>
						</div>
													
						<h3>
							<span class="faq-category">원화 입금/출금</span>
							<span>암호화폐 입금이 하루 이상 지났는데도 처리되지 않고 있습니다. 어떻게 하나요?</span>
						</h3>
						<div>
							<span class="answer-mark">A</span>
							<span class="answer-content">[고객지원 – 1:1 문의 – 입출금] 메뉴에서 해당 암호화폐 선택 후 제목에 암호화폐명을 넣어 “OOO 입금요청합니다 – 입금 후 24시간 경과＂ 라고 경과 시간을 기재해 주시면 우선적으로 확인해 드리겠습니다. TXID를 문의 내용에 기입해 주시고 보내신 쪽에서 전송내역을 수량이 보이게끔 캡쳐 및 사진촬영 하셔서 파일 확장자 jpg ,png 로 파일첨부하여 문의해 주시기 바랍니다.</span>
						</div>
						
						<h3>
							<span class="faq-category">원화 입금/출금</span>
							<span>보유하고 있는 잔고보다 출금가능 수량이 적게 표시됩니다 왜 그런가요?</span>
						</h3>
						<div>
							<span class="answer-mark">A</span>
							<span class="answer-content">현재 암호화폐 매도 주문을 걸어 놓으신게 없는지 확인 하십시오 미체결 매도 주문이 없는데 그렇다면 1:1 문의에 올려 주세요</span>
						</div>
						
						<h3>
							<span class="faq-category">원화 입금/출금</span>
							<span> 트랜잭션ID (TXID) 또는 해시값이 뭔가요?</span>
						</h3>
						<div>
							<span class="answer-mark">A</span>
							<span class="answer-content">암호화폐를 거래소간에 송금할시 블록체인 네트워크 상에서 암호화폐 송금 내역을 식별하고 추적할 수 있도록 하는 유일한 키 값입니다. 해당 값이 없으면 암호화폐 송금 내역을 찾는것이 불가능 하며 송금측 거래소 사이트의 암호화폐 출금내역 화면에서 조회 및 확인 할 수 있습니다.</span>
						</div>
						
						<h3>
							<span class="faq-category">원화 입금/출금</span>
							<span>거래수수료는 얼마인가요?</span>
						</h3>
						<div>
							<span class="answer-mark">A</span>
							<span class="answer-content">거래수수료는 매수/매도 전마켓(KRW,BTC,USDT) 공통 0.15%를 적용하고 있습니다.</span>
						</div>
						
						<h3>
							<span class="faq-category">원화 입금/출금</span>
							<span>미체결 주문은 어떻게 처리 되나요?</span>
						</h3>
						<div>
							<span class="answer-mark">A</span>
							<span class="answer-content">주문 취소 전까지는 미체결 상태로 계속 남아 있게 됩니다</span>
						</div>
						
						

						<h3>
							<span class="faq-category">암호화폐 입금/출금</span>
							<span>웹에서 주문 취소가 되지 않습니다.</span>
						</h3>
						<div>
							<span class="answer-mark">A</span>
							<span class="answer-content">화면 새로고침이나 브라우저 재시작을 해 보시면 취소가 될 수 있으며,<br/>조치가 되지 않을 시 다른 브라우저를사용하여 시도 해보시면 조치가 될 수 있습니다.</span>
						</div>
						<h3>
							<span class="faq-category">암호화폐 입금/출금</span>
							<span>주문 체결이 되었는데 체결 수량이나 금액이 실제 주문 내역과 일치하지 않습니다</span>
						</h3>
						<div>
							<span class="answer-mark">A</span>
							<span class="answer-content">고객님의 거래내역 및 체결 내역을 확인 해보시고 일치하지 않으신다면, [고객지원 - 1:1 문의 – 기타] 메뉴에서 문의 내용을 남겨 주십시오. 확인 후 답변 드리겠습니다.</span>
						</div>
						<h3>
							<span class="faq-category">암호화폐 입금/출금</span>
							<span>회원 탈퇴 방법을 알려주세요</span>
						</h3>
						<div>
							<span class="answer-mark">A</span>
							<span class="answer-content">로그인 하신 후 [고객지원 - 1:1 문의 – 회원 탈퇴] 메뉴에서<br/>탈퇴사유와 함께 탈퇴 요청을 해 주십시오.<br/>탈퇴 처리전에 회원님의 계정에는 원화 및 암호화폐 잔고가 없어야 합니다</span>
						</div>
						<h3>
							<span class="faq-category">암호화폐 입금/출금</span>
							<span>다른 궁금한 사항은 어디에 물어보면 되나요?</span>
						</h3>
						<div>
							<span class="answer-mark">A</span>
							<span class="answer-content">로그인 하신 후 [고객지원 - 1:1 문의 – 기타] 메뉴에서 문의사항을 등록 하시면 담당자가 확인 후 답변을 드립니다.</span>
						</div>
						
							<h3>
							<span class="faq-category">암호화폐 입금/출금</span>
							<span> 기타 신분증 제출시 신분증과 신분증 들고 있는 사진 둘다 제출해야 하나요?</span>
						</h3>
						<div>
							<span class="answer-mark">A</span>
							<span class="answer-content">신분증과 신분증 들고 있는 사진 모두 제출해야 합니다.</span>
						</div>
						
							<h3>
							<span class="faq-category">암호화폐 입금/출금</span>
							<span>기타 모바일 웹은 언제 서비스 되나요?</span>
						</h3>
						<div>
							<span class="answer-mark">A</span>
							<span class="answer-content">정확한 날짜를 확정 드릴 수 없어 죄송합니다. 서비스 가능 시점이 되면 공지사항으로 안내 됩니다.</span>
						</div>
					</div>
				</div>      

				<!-- //전체 탭 컨텐츠 : End -->
				<!-- 가입 및 탭 컨텐츠 : Start -->
				<div id="tabs-2" class="mgt10">
					<div id="accordion2">
						<h3>
							<span class="faq-category">가입 및 개인정보 변경</span>
							<span>회원가입 방법이 어떻게 되나요?</span>
						</h3>
						<div>
							<span class="answer-mark">A</span>
							<span class="answer-content">회원 가입은 Bita500 PC용 웹페이지 또는 모바일 웹(추후 오픈 예정)에서 가능합니다.</span>
						</div>
						
						<h3>
							<span class="faq-category">가입 및 개인정보 변경</span>
							<span>회원가입 인증메일이 오지 않아요.</span>
						</h3>
						<div>
							<span class="answer-mark">A</span>
							<span class="answer-content">받은편지함이 아닌 스팸함이나 전체편지함도 한번 찾아보시길 바랍니다. 구글 ( gmail.com ) 의 경우, '카테고리-프로모션탭'을 확인해 주시기 바랍니다. 또한 메일이 수신되는 데에 간혹 시간이 걸리는 경우가 있습니다. 기다려도 메일이 오지 않을 경우,<br/>가입하신 이메일 ID 로 로그인하시면 인증메일을 재발송 하실 수 있습니다. 재발송을 통해 진행해보시길 바랍니다.<br/>개선이 되지 않는다면 1:1문의 문의 부탁 드립니다.</span>
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
							<span class="answer-content">법인폰으로는 이용이 불가 합니다</span>
						</div>
						<h3>
							<span class="faq-category">가입 및 개인정보 변경</span>
							<span>개인정보를 변경할 수 있나요?</span>
						</h3>
						<div>
							<span class="answer-mark">A</span>
							<span class="answer-content">개인정보의 변경은 로그인 후 마이페이지 아래 각 서브메뉴에서 변경하는 정보와 매칭되는 화면에서 변경처리 하시면 됩니다. <br/>변경 가능한 개인 정보로는 로그인 비밀번호, 휴대폰번호, 보안비밀번호 , 인증레벨, OTP설정/해제 등이 있습니다.</span>
						</div>
						<h3>
							<span class="faq-category">가입 및 개인정보 변경</span>
							<span>휴대전화 번호를 변경할 수 있나요? 어떻게 해야하나요?</span>
						</h3>
						<div>
							<span class="answer-mark">A</span>
							<span class="answer-content">휴대폰 번호 변경은 초기화 접수를 통해서만 가능합니다.<br/>[마이페이지 – 보안인증 – 휴대폰 본인인증] 초기화 메뉴를 통해서<br/>안내되는 팝업창의 절차에 따라 접수하시길 바랍니다.</span>
						</div>
						
						<h3>
							<span class="faq-category">가입 및 개인정보 변경</span>
							<span>휴대전화 번호를 변경할 수 있나요? 어떻게 해야하나요?</span>
						</h3>
						<div>
							<span class="answer-mark">A</span>
							<span class="answer-content">휴대폰 번호 변경은 초기화 접수를 통해서만 가능합니다.<br/>[마이페이지 – 보안인증 – 휴대폰 본인인증] 초기화 메뉴를 통해서<br/></span>
						</div>

							<h3>
							<span class="faq-category">가입 및 개인정보 변경</span>
							<span>가입 후 인증레벨을 올리고 싶은데 어떻게 하나요?</span>
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
							<span class="answer-content">네, 상향조건 만족해야 합니다</span>
						</div>
						<h3>
							<span class="faq-category">가입 및 개인정보 변경</span>
							<span>가입시 이름을 잘못 입력하여 휴대폰 본인인증이 안됩니다</span>
						</h3>
						<div>
							<span class="answer-mark">A</span>
							<span class="answer-content">1:1 문의에 남겨 주시면 이름을 변경해 드립니다</span>
						</div>
						<h3>
							<span class="faq-category">가입 및 개인정보 변경</span>
							<span>OTP 설정시 OTP 인증번호가 틀렸다고 표시됩니다</span>
						</h3>
						<div>
							<span class="answer-mark">A</span>
							<span class="answer-content">구글 OTP 앱에 표시된6자리 번호는 일정 시간 후 갱신이 되니 갱신된 번호를 다시 입력해야 합니다</span>
						</div>
						<!-- <h3>
							<span class="faq-category">가입 및 개인정보 변경</span>
							<span>전화번호를 변경하고 싶습니다. 어떻게 해야 하나요?</span>
						</h3>
						<div>
							<span class="answer-mark">A</span>
							<span class="answer-content">웹사이트 마이페이지 - 휴대폰 번호변경 화면에서 변경 가능 합니다</span>
						</div>-->
					</div>
				</div>
					

				<!-- //가입 및  탭 컨텐츠 : End -->
				<!-- 개인정보 변경 탭 컨텐츠 : Start -->
				<div id="tabs-3" class="mgt10">
					<div id="accordion3">
							<h3>
								<span class="faq-category">원화 입금/출금</span>
								<span>원화 입금은 어떻게 하나요?</span>
							</h3>
							<div>
								<span class="answer-mark">A</span>
								<span class="answer-content">입출금-입금신청 화면 이동 -> 본인 명의 은행계좌 등록 후 수동입금 / 자동입금으로 나뉩니다. <br/><strong>자동입금시 </strong>실시간으로 입금처리 가능<br/>입출금 – 입금신청- 자동입금-  (본인이름+숫자4자리 홍길동 1111)<br/>자동입금시 수동입금요청 이중으로 하지 않아도 됩니다.<br/><strong>수동입금 시 </strong>수동입금 요청 순서 => 입출금 – 입금신청- 수동입금- 금액-제출<br/><span class="red">*고액입금 시 이체결과확인증 첨부후 관리자가 입금처리 진행*</span></span>
							</div>
							<h3>
								<span class="faq-category">원화 입금/출금</span>
								<span>원화 입금 확인이 안되고 있습니다</span>
							</h3>
							<div>
								<span class="answer-mark">A</span>
								<span class="answer-content">계좌이체 후 5분 이내에 원화 입금 내역을 확인 하실 수 있으나 시스템 장애가 있거나 거래소에 등록하신 본인 은행계좌로 부터 입금 하시지 않은 경우에는입금 확인이 불가하며 이 경우에는 [고객지원 – 1:1 문의 – 인증자료 제출] 에서 입금증명서를 제출해 주시면 확인해 드리겠습니다.</span>
							</div>
							
							<h3>
								<span class="faq-category">원화 입금/출금</span>
								<span>원화 출금 제한이 있나요?</span>
							</h3>
							<div>
								<span class="answer-mark">A</span>
								<span class="answer-content">원화 출금은 회원 가입 후 인증레벨3 이상인 회원<br/>각 인증레벨별로 월 출금한도 및 1일 출금한도가 정해져 있습니다.<br/>출금한도를 늘리기 위해서는 레벨을 업로드 진행자<br/>자세한 사항은 고객지원-이용안내 메뉴를 참조 바랍니다</span>
							</div>
							
							<h3>
								<span class="faq-category">원화 입금/출금</span>
								<span>입출금 은행계좌를 잘못 등록 하였습니다. 변경할려면 어떻게 하나요?</span>
							</h3>
							<div>
								<span class="answer-mark">A</span>
								<span class="answer-content">[고객지원 - 1:1 문의 – 은행계좌등록/변경] 메뉴에서 문의에 남겨 주시면 관리자 확인 후 계좌 초기화를 해 드립니다. 다만 한번이라도 기존 계좌 번호로 입금한 내역이 있는 경우 해킹에 의한 악의적인 변경요청을 방지하기 위해 인증자료를 제출 받습니다. 초기화 된 후 재 등록 하십시오</span>
							</div>

							
							<h3>
								<span class="faq-category">원화 입금/출금</span>
								<span>원화를 보유하고 있는데 출금가능 KRW 잔고가 0 으로 표시 됩니다</span>
							</h3>
							<div>
								<span class="answer-mark">A</span>
								<span class="answer-content">원화 출금 24시간 제한에 걸려 있는지 확인 하십시오</span>
							</div>
							

							
							<h3>
								<span class="faq-category">원화 입금/출금</span>
								<span> 원화 출금 24시간 제한이 무엇인가요?</span>
							</h3>
							<div>
								<span class="answer-mark">A</span>
								<span class="answer-content">보이스피싱등의 금융사고 방지하고 회원님들의 자산을 안전하게 지키기 위하여 24시간 이후에 원화 출금이 가능하도록 하였습니다.<br/>주말 및 공휴일도 출금불가능, 영업일(월~금 오전9시~ 저녁6시) 에 원화출금 가능합니다.</span>
							</div>
							
							<h3>
								<span class="faq-category">원화 입금/출금</span>
								<span> 원화 입금 시 계좌이체를 먼저하고 입금신청을 해야 하나요?</span>
							</h3>
							<div>
								<span class="answer-mark">A</span>
								<span class="answer-content">아닙니다. 반드시 입금신청을 먼저 하시고 발급받은 ‘ 고객님의 성함 + 입금코드 ’  받는통장 메모기입하여 계좌이체 진행하기</span>
							</div>
							

							
							<h3>
								<span class="faq-category">원화 입금/출금</span>
								<span>거래소에 등록된 입출금 계좌가 아닌 다른 계좌에서 입금을 해 버렸습니다.</span>
							</h3>
							<div>
								<span class="answer-mark">A</span>
								<span class="answer-content">충전 요청을 하신 후에 입금 내역 캡쳐, 신분증 주민번호 뒷자리 가리고 캡쳐, 출금하신 통장사본 캡쳐 업로드하면 처리 진행.<br/>다만, 본인명의 통장계좌만가능 타인명의 계좌 일 경우 불가능</span>
							</div>
							

							<h3>
								<span class="faq-category">원화 입금/출금</span>
								<span> 평생계좌번호(전화번호 사용)로도 입금이 가능한가요?</span>
							</h3>
							<div>
								<span class="answer-mark">A</span>
								<span class="answer-content">불가합니다. 해당 입금 수단으로 입금하시면 입금확인이 어렵습니다.<br/>해당 건에 대한 환급시에도 인증자료 제출 등 많은 시간이 소요 됩니다.</span>
							</div>

							
							<h3>
								<span class="faq-category">원화 입금/출금</span>
								<span>입금신청한 금액과 다른 금액을 계좌이체 해 버렸습니다. 어떻게 하나요?</span>
							</h3>
							<div>
								<span class="answer-mark">A</span>
								<span class="answer-content">[고객지원 – 1:1 문의 – 인증자료제출] 메뉴에서 이체결과확인증을 제출해 주시면 확인 후 입금처리 해 드립니다.</span>
							</div>
							
							<h3>
								<span class="faq-category">원화 입금/출금</span>
								<span>실물 통장이 없어서 통장사본 제출이 불가능한데 어떻게 하나요?</span>
							</h3>
							<div>
								<span class="answer-mark">A</span>
								<span class="answer-content">충전 요청을 하신 후에 입금 내역 캡쳐, 신분증 주민번호 뒷자리 가리고 캡쳐, 출금하신 통장사본 캡쳐 업로드하면 처리 진행.<br/>다만, 본인명의 통장계좌만가능 타인명의 계좌 일 경우 불가능</span>
							</div>

							<h3>
								<span class="faq-category">원화 입금/출금</span>
								<span>평생계좌번호(전화번호 사용)로도 입금이 가능한가요?</span>
							</h3>
							<div>
								<span class="answer-mark">A</span>
								<span class="answer-content">불가합니다. 해당 입금 수단으로 입금하시면 입금확인이 어렵습니다.<br/>해당 건에 대한 환급시에도 인증자료 제출 등 많은 시간이 소요 됩니다.</span>
							</div>
							
							<h3>
								<span class="faq-category">원화 입금/출금</span>
								<span> 원화 입금이 하루 이상 지났는데도 처리되지 않고 있습니다. 어떻게 하나요?</span>
							</h3>
							<div>
								<span class="answer-mark">A</span>
								<span class="answer-content">[고객지원 – 1:1 문의 – 인증자료제출] 메뉴에서 이체결과확인증을 제출해 주시면 확인 후 입금처리 해 드립니다.</span>
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
								<span>원화 입금이 하루 이상 지났는데도 처리되지 않고 있습니다. 어떻게 하나요?</span>
							</h3>
							<div>
								<span class="answer-mark">A</span>
								<span class="answer-content">[고객지원 – 1:1 문의 – 입출금 – 원화 (KRW)] 메뉴를 선택하신 후 제목에 “원화 입금 요청, 24시간 경과” 로 문의하기 하시면 확인 후 빠르게 처리해 드립니다.<br/>빠른 처리를 위해 추가자료(입금내역증 등)을 요청할 수 있습니다.</span>
							</div>

							<h3>
								<span class="faq-category">원화 입금/출금</span>
								<span>보유하고 있는 원화보다 출금가능 잔액이 적게 표시됩니다 왜 그런가요?</span>
							</h3>
							<div>
								<span class="answer-mark">A</span>
								<span class="answer-content">현재 매수 주문 접수를 하진 않으셨는지 확인 부탁드립니다.<br/>미체결 매수 주문이 없는데도 같은 증상이라면<br/>[고객지원 - 1:1 문의 – 입출금 – 원화 (KRW)] 메뉴에 문의를 남겨주시기 바랍니다.</span>
							</div>

							<h3>
								<span class="faq-category">원화 입금/출금</span>
								<span>인증자료 제출은 어디서 하나요?</span>
							</h3>
							<div>
								<span class="answer-mark">A</span>
								<span class="answer-content">[고객지원 – 1:1 문의 – 인증자료제출] 메뉴를 이용하시기 바랍니다</span>
							</div>
						</div>
				</div>


				<!-- //원화 입금/출금 탭 컨텐츠 : End -->
				<!-- 코인 입금/출금 탭 컨텐츠 : Start -->
				 <div id="tabs-4" class="mgt10">
					<div id="accordion4">
						
						<h3>
							<span class="faq-category">암호화폐 입금/출금</span>
							<span>암호화폐 지갑 주소는 어디서 확인하나요?</span>
						</h3>
						<div>
							<span class="answer-mark">A</span>
							<span class="answer-content">로그인 후 입출금 메뉴에서 지갑 주소 확인을 원하시는 암호화폐를 선택하신 후 입금주소 탭을 클릭 하시면 확인 가능합니다. 지갑 생성을 하지 않으셨다면 [입금 주소 생성하기] 버튼을 클릭하여 주소 생성이 가능합니다</span>
						</div>
						<h3>
							<span class="faq-category">암호화폐 입금/출금</span>
							<span>암호화폐 출금 한도는 어떻게 되나요?</span>
						</h3>
						<div>
							<span class="answer-mark">A</span>
							<span class="answer-content">출금하고자 하는 암호화폐의 원화 환산액(출금수량*원화가격) 인증레벨 별 출금한도 이내에서만 가능합니다. 예를 들어 회원님의 인증레벨이 3이면 1일 출금한도는 3,000만원 이내에서만 가능하므로, 현재가가 반영된 원화 환산액 3천만원 이내의 암호화폐 수량 내에서만 가능합니다.</span>
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
							<span class="answer-content">암호화폐의 입출금 소요 시간은 암호화폐별로 다를 수 있으며 보통 10분~30분 정도 소요 됩니다. 다만 블록체인 네트워크 상에 일시적으로 과도한 입출금 요청이 발생하는 경우 다소 지연될 수 잇습니다</span>
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
								1) 리플 (XRP) Destination Tag 를 잘못 작성한 오입금
								<br>
								- 해당 Destination Tag나 메모가 타인에게 발급되지 않은 경우, 영업일 기준 최대 5일이 소요됩니다.
								<br>
								해당 Destination Tag나 메모가 타인에게 발급되고 오입금 받은 고객이 반환에 협조하지 않는 경우, 부당이득반환청구 소송을 해야할 수도 있습니다.
								<br>
								- 1:1 문의하기를 통해 접수해 주세요. 처리에 시간이 소요될 수 있습니다.
								<br>
								※ 오입금 접수는 [고객지원 – 1:1 문의하기 – 입출금] 메뉴를 통해 접수해 주세요.
								<br>
								※ 기술적 문제로 모든 오입금 내역이 복구되지 않을 수 있습니다.
								<br>
							</span>
						</div>
						
							<h3>
							<span class="faq-category">암호화폐 입금/출금</span>
							<span>암호화폐 입금시 주소외 추가로 입력해야 하는 정보를 누락하고 전송한 경우는 어떻게 하나요?</span>
						</h3>
						<div>
							<span class="answer-mark">A</span>
							<span class="answer-content">블록체인 트랜잭션ID 나 해시값을 1:1 문의에 문의구분 암호화폐 입출금 선택하고 올려주세요. 다만 이 경우 처리하는데 최장 5일까지 소요 될 수 있습니다.<br/>추가로 고객지원 -> 인증자료제출 화면에서 인증목적구분을 암호화폐 입출금 선택하시고 보내신 사이트에서 전송상세 내역을 캡쳐해 올려 주셔야 합니다.</span>
						</div>
						
							<h3>
							<span class="faq-category">암호화폐 입금/출금</span>
							<span>원화 입금없이 암호화폐만 입금했다 출금할 경우에도 24시간 제한이 있나요?</span>
						</h3>
						<div>
							<span class="answer-mark">A</span>
							<span class="answer-content">암호화폐만 입금했다 매도 후 원화로 출금하거나 암호화폐를 출금하는 경우에는 제한이 없습니다</span>
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
							<span class="answer-content">현재 암호화폐 매도 주문을 걸어 놓으신게 없는지 확인 하십시오 미체결 매도 주문이 없는데 그렇다면 1:1 문의에 올려 주세요</span>
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


				<!-- //코인 입금/출금 탭 컨텐츠 : End -->
				<!-- 거래,주문 탭 컨텐츠 : Start -->
				<div id="tabs-5" class="mgt10">
					<div id="accordion5" >
						
						<h3>
							<span class="faq-category">거래,주문</span>
							<span>거래수수료는 얼마인가요?</span>
						</h3>
						<div>
							<span class="answer-mark">A</span>
							<span class="answer-content">거래수수료는 매수/매도 전마켓(KRW,BTC,USDT) 공통 0.15%를 적용하고 있습니다.</span>
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
							<span class="answer-content">주문 취소 전까지는 미체결 상태로 계속 남아 있게 됩니다</span>
						</div>
						
						<h3>
							<span class="faq-category">거래,주문</span>
							<span>웹에서 주문 취소가 되지 않습니다</span>
						</h3>
						<div>
							<span class="answer-mark">A</span>
							<span class="answer-content">화면 새로고침이나 브라우저 재시작을 해 보시면 취소가 될 수 있으며, 조치가 되지 않을 시 다른 브라우저를사용하여 시도 해보시면 조치가 될 수 있습니다. </span>
						</div>
						
						<h3>
							<span class="faq-category">거래,주문</span>
							<span>주문 체결이 되었는데 체결 수량이나 금액이 실제 주문 내역과 일치하지 않습니다</span>
						</h3>
						<div>
							<span class="answer-mark">A</span>
							<span class="answer-content">고객님의 거래내역 및 체결 내역을 확인 해보시고 일치하지 않으신다면, [고객지원 - 1:1 문의 – 기타] 메뉴에서 문의 내용을 남겨 주십시오. 확인 후 답변 드리겠습니다.</span>
						</div>
					</div>
				</div>
				

				<!-- //거래주문 탭 컨텐츠 : End -->
				<!-- 기타 탭 컨텐츠 : Start -->
				<div id="tabs-6" class="mgt10">
					<div id="accordion6">
						<h3>
							<span class="faq-category">기타</span>
							<span>회원 탈퇴 방법을 알려주세요</span>
						</h3>
						<div>
							<span class="answer-mark">A</span>
							<span class="answer-content">로그인 하신 후 [고객지원 - 1:1 문의 – 회원 탈퇴] 메뉴에서 탈퇴사유와 함께 탈퇴 요청을 해 주십시오. 탈퇴 처리전에 회원님의 계정에는 원화 및 암호화폐 잔고가 없어야 합니다</span>
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
							<span> 기타 신분증 제출시 신분증과 신분증 들고 있는 사진 둘다 제출해야 하나요?</span>
						</h3>
						<div>
							<span class="answer-mark">A</span>
							<span class="answer-content">신분증과 신분증 들고 있는 사진 모두 제출해야 합니다.</span>
						</div>
						<h3>
							<span class="faq-category">기타</span>
							<span>기타 모바일 웹은 언제 서비스 되나요?</span>
						</h3>
						<div>
							<span class="answer-mark">A</span>
							<span class="answer-content">정확한 날짜를 확정 드릴 수 없어 죄송합니다. 서비스 가능 시점이 되면 공지사항으로 안내 됩니다.</span>
						</div>
						<!--<h3>
							<span class="faq-category">기타</span>
							<span>모바일 앱에서는 로그인이 되는데 PC 웹에서는 비밀번호 오류라고 하면서 로그인이 안됩니다.</span>
						</h3>
						<div>
							<span class="answer-mark">A</span>
							<span class="answer-content">모바일 앱 로그인시 입력한 로그인ID와 동일하게 웹에서도 ID 입력하셨는지 확인 하십시오. 동일하게 입력해도 안 된다면 비밀번호에 특수문자 &#60; 또는 &#40; 또는 &#39; 세 가지 문자 중 하나가 포함되어 있는지 확인하십시오. 포함되어 있다면 해당 특수문자가 포함되지 않은 비밀번호로 모바일 앱에서 변경한 후 웹로그인 해보시길 바랍니다.</span>
						</div>-->
					</div>
				</div>


				<!-- //기타 탭 컨텐츠 : End -->
			</div>
			<!-- 페이지 탭 : Start -->
		</div>
		<!-- 페이지 탭 : Start -->


	</article>
	<!--FAQ 전체 내용 끝-->
</section>
<!--컨텐츠 내용 영역 끝-->

<script>
$(document).ready(function() {
	$("#tab-group").tabs().css('display', 'block');
	
	$("#accordion1").accordion();
	$("#accordion2").accordion();
	$("#accordion3").accordion();
	$("#accordion4").accordion();
	$("#accordion5").accordion();
	$("#accordion6").accordion();

	$("#datepicker").datepicker({
		inline: true
	});
});
</script>

