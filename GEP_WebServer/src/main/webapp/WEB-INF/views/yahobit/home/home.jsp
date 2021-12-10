<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/taglib/taglib.jsp" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page" %>

<!DOCTYPE html>
<html>
	
	<page:applyDecorator name="yahobitHead"/>

	<body>
	
		<page:applyDecorator name="yahobitHeader"/>
		<page:applyDecorator name="yahobitPush"/>

<section class="main-visual">
	<div class="visual-wrap">

		<!-- <div class="visual-txt" style="padding-top: 84px">
			<p>암호화폐 거래소의 새로운 기준</p>
			<p>OPEN BETA</p>
			<p class="bolder mgt20">오픈 베타는 모의테스트 기간입니다. 입출금은 지원하지 않습니다.</p>
		</div> -->
		
		<p class="logo4"><img src="/resources/img/yaho/logo4.png" alt="암호화폐 거래소의 새로운 기준 Alibit"></p>

		<article class="event-wrap">
		
			<div class="banner slider">
				<!-- <div class="event-box">
					<a href="javascript:void(0);" class="event-pop" data-no="1">
						<p><img src="/resources/img/yaho/icon-main06.png" alt="img"></p>
						<p>치킨100마리 AirDrop 이벤트</p>
					</a>
				</div>
				<div class="event-box">
					<a href="javascript:void(0);" class="event-pop1" data-no="2">
						<p><img src="/resources/img/yaho/icon-main02.png" alt="img"></p>
						<p>사전채굴 이벤트</p>
					</a>
				</div> -->
				<!-- <div class="event-box">
					<a href="javascript:void(0);" class="event-pop2" data-no="3">
						<p><img src="/resources/img/yaho/icon-main03.png" alt="img"></p>
						<p>홍보 이벤트</p>
					</a>
				</div> -->
				
				<%-- <c:if test="${event != null}">
				<div class="event-box">
					<a href="javascript:void(0);" class="event-pop3" data-no="${event.getSeqNo()}">
						<p><img src="/resources/img/yaho/icon-main04.png" alt="img"></p>
						<p>${event.getTitle()}</p>
					</a>
				</div>
				</c:if> --%>
				
				
			</div>
			
			<%-- 
			<c:forEach var="item" items="${event}">
			<div class="event-box">
				<a href="#" class="event-pop" data-no="${item.getSeqNo()}">
					<p><img src="/resources/img/yaho/icon-main04.png" alt="img"></p>
					<p>${item.getTitle()}</p>
				</a>
			</div>
			</c:forEach>
			--%>
			
			
		</article>
	</div>
</section>

<section class="home">
	
	<article class="slider_notice_wrap">
		<div class="slider_notice slider" style="display:none;">
			<c:forEach var="item" items="${notice}">
			<div class="event-box"><a href="/alibit/support?tab=1&no=${item.getRecSeqNo()}" target="_blank">${item.getTitle()}</a></div>
			</c:forEach>
		</div>
	</article>
		
	<article class="bgwhite">
		<div class="merit1">
			<h1>믿고 거래할 수 있는 안정적인 거래시스템</h1>
				<h2>강력한 보안 정책으로 회원님들의 암호화폐를 안전하게 보호하고
					물리적 보안을 강화한 안정적인 거래환경을 지원합니다.</h2>

			<p class="mgt40"><img src="/resources/img/yaho/img-merit1-01.png" alt="img"></p>

			<div>
				<p>시스템</p>
				<p>
					대용량 트랜잭션 처리 분산 아키텍처<br>시스템 이중화 구성 자동 백업 시스템<br>데이터베이스 3중 백업 시스템<br>NBP 사용 논스톱 스케일 업
				</p>
			</div>
			<div>
				<p>보안</p>
				<p>
					물리적 2중 망분리<br>핫월렛 자동밸런스 관리<br>내부 해킹 방지 콜드월렛 4중 접근 보안<br>PC 및 네트웍 사용 접근 보안<br>CloudFlare 보안 / 강력한 물리 접근 보안
				</p>
			</div>
			<div>
				<p>고객지원</p>
				<p>
					전화상담<br>카카오톡 상담<br>오픈채팅방<br>
				</p>
			</div>
		</div>
	</article>

	<article class="merit4bg">
		<div class="merit4">
			<i>First Mover</i>
			<p>'희소성'에 중점을 둔 장기지속가능형 생태계</p>
			<p>암호화폐 및 거래소의 근본적인 한계를 뛰어 넘어 장기지속 가능한 암호화폐 거래소의 새로운 기준을 제시합니다.</p>
			
			<p><b>행동경제학 </b>정책기반 인플레이션 억제 시스템<br>
			<b>손실회피심리</b>에 의해 유통량이 잠기는 메커니즘
				</p>
			
			<p class="meri4_img">
				<img src="/resources/img/yaho/img-merit4-02.png">
			</p>
			
		</div>
	</article>

	<article>
			<div class="merit2">
				<div class="merit2_tit">유통량밸런스 시스템 7選</div>
				<h1>‘발행’ 및 ‘유통’ 단계에서 7가지 유통량 억제 이코노미 설계로 Token 가치의 <b>[희소성] </b>증대</h1>
				<div class="merit2-left">
					<p>
						Trading을 통해서만 토큰이 <br>발행되는 Trade Mining 시스템 <br>자유로운 마이닝기간을 설정
					</p>
					<p>
						마이닝수량을 일균등하게 나눠 <br>초기 유통량 제한 및 자연스런 <br>반감기 효과 발생을 유도
					</p>
					<p>
						프로젝트 배정 토큰도 일균등하게 <br>배분받는 구조로 설계 가능 <br>덤핑 방지 신뢰도 획득
					</p>
					<p>
						프로젝트 상황에 따라 다양한 <br>보호예수 시스템 구현으로 <br>프로젝트 신뢰도 상승
					</p>
				</div>
				<div class="merit2-center mgt40">
					<img src="/resources/img/yaho/img-merit2-01.jpg" alt="img">
				</div>

				<div class="merit2-right">
					<p>
						시장상황에 연동된 채굴량 <br>자동조절 시스템으로 인플레이션을<br>억제하고 유통량 밸런스를 유지
					</p>
					<p>
						거래 등 유동성을 스스로 제한<br>토록하여 유통량을 억제
					</p>
					<p>
					   특정조건시 토큰을 동결시켜 <br>유통량 증가를 강력히 제한
					</p>

				</div>
			</div>
		</article>
		
	<article class="bgwhite">
			<div class="merit3">
				<div class="merit3_tit">시장활성화 시스템 7選</div>
				<h1>프로젝트와 동반성장하는 Alibit의 <b>[유저친화적]</b> ‘보상’ 모델 및 이용자 ‘참여’ 확대</h1>
				<!--<h2>다양한 '보상' 과 '참여' 할 수 있는 시스템을 통해 시장활성화를 도모합니다.</h2>-->
				
				<div class="merit3-box">
				<div class="merit3-top">
					<p>
						특정 조건을 수행한 마이닝유저들을 위한 혜택 제공 및 발생수수료를 기준으로 한 공정하고 투명한 Auto Mining 시스템
					</p>
					<p>
						시장 참여 기여도가 높은 회원들을 위한 차등화된 보너스 AirDrop 정책
					</p>
					<p>
						프로젝트 거래페어에서 발생한 수수료 AirDrop 및 추천인 시스템을 통한 추가 혜택 제공
					</p>
					<p>
						선진입회원과 후진입회원 간 발생하는 ROI의 격차 해소를 위한 특별한 Reward 시스템으로 지속적인 신규유저 유입 가능
					</p>
				</div>
				
				<div class="merit3-bott">
					<p>
						투표시스템을 통한 토큰홀더들의<br>의견 수렴 및 반영
					</p>
					<p>
						모든 암호화폐, 토큰 대상으로<br>마이닝, 스테이킹, AirDrop 등의<br>토큰이코노미를 제공하여 수익형<br>유틸리티 토큰으로 재탄생
					</p>
					<p>
					   다종의 다양한 토큰 거래시장 <br>제공 및  시장 참여자의 <br>Risk Hedge 정책
					</p>

				</div>
					</div>
				
			</div>
		</article>
		
		<%-- <article class="bgblue">
			<div class="merit5">
				<h1>INTRODUCTION TO TOKEN</h1>
				<div class="merit5-txt">
					<img src="/resources/img/yaho/img-merit5-01.png" alt="img">
				</div>

				<div class="merit5-table">
					<table>
						<thead>
							<tr>
								<th>Token</th>
								<th>Description</th>
							</tr>
						</thead>
						<tbody>

							<tr>
								<td>토큰명</td>
								<td>YEP Token (Yahobit Exchanges Platform Token)</td>
							</tr>
							<tr>
								<td>토큰규격</td>
								<td>ERC20</td>
							</tr>
							<tr>
								<td>총 발생량</td>
								<td>10억개</td>
							</tr>
							<tr>
								<td>총 마이닝량</td>
								<td>5억개 (50%)</td>
							</tr>
							<tr>
								<td>마이닝 기간</td>
								<td>12년 일균등분할배정</td>
							</tr>
							<tr>
								<td>마이닝 방식</td>
								<td>가변기여 마이닝 (Variable Trade Mining)</td>
							</tr>
						</tbody>
					</table>
					<button onclick="window.open('/resources/etc/alibit/Yahobit_White Paper.pdf?v=<spring:message code="yahobit.white.paper.version"/>')">
						<a>
							WHITE PAPER DOWNLOAD ↓
						</a>
					</button>
				</div>
			</div>
		</article> --%>

	<article>
			<div class="merit6">
				<h1>CORE OPERATIONS TEAM</h1>
				<div class="team-wrap">
					<p class="team-title">ADVISORS</p>
					<!-- <div class="team-list">
						<div class="team-photo">
						<img src="/resources/img/yaho/advisor05.jpg" alt="advisor">
						</div>
						<p class="team-name">Kim, Seung il</p>
						<p class="team-positon">現)(주)한국블록체인경영원 회장</p>
						<p class="team-positon">前)대불대학교안경광학과 외래교수</p>
					</div> -->
					<div class="team-list">
						<div class="team-photo">
						<img src="/resources/img/yaho/advisor04.jpg" alt="advisor">
						</div>
						<p class="team-name">Ban, June Hyung</p>
						<p class="team-positon">고려대학교 정보통신대학(컴퓨터학)</p>
						<p class="team-positon">現) 히즈윌컴즈 대표</p>
						<p class="team-positon">한국데이터진흥원 분과위원</p>
						<p class="team-positon">KT hitel ICT 마케팅 team leader</p>
						<p class="team-positon">Aro Intech team leader</p>
					</div>
					<div class="team-list">
						<div class="team-photo">
						<img src="/resources/img/yaho/advisor02.jpg" alt="advisor">
						</div>
						<p class="team-name">Lee, Kang Wook</p>
						<p class="team-positon">서울대학교 대학원(IP)</p>
						<p class="team-positon">서강대학교 정보통신대학원(블록체인학)</p>
						<p class="team-positon">現) KNK 대표 변리사</p>
						<p class="team-positon">서울지식재산센터 IP경영자문단 위원</p>
						<p class="team-positon">한국신용정보원 TDB기술정보 전문가</p>
					</div>
					<div class="team-list">
						<div class="team-photo">
						<img src="/resources/img/yaho/advisor03.jpg" alt="advisor">
						</div>
						<p class="team-name">Park, Kun Soo</p>
						<p class="team-positon">연세대학교(전기전자공학)</p>
						<p class="team-positon">서강대학교 정보통신대학원(블록체인학)</p>
						<p class="team-positon">現) KNK  대표 변리사</p>
						<p class="team-positon">블록체인 및 AI 특허 전담</p>
						<p class="team-positon">한국신용정보원 TDB기술정보 전문가</p>
					</div>
					<div class="team-list">
						<div class="team-photo">
						<img src="/resources/img/yaho/advisor01.jpg" alt="advisor">
						</div>
						<p class="team-name">Lee, Jun Young</p>
						<p class="team-positon">한국외국어 대학교 법학전문대학원</p>
						<p class="team-positon">現) KNK 대표 변호사</p>
						<p class="team-positon">(주)북잼 (주) 엑스와이비씨<br> (주)큐엠아이티 (주)큐레잇 <br>(주)타운어스 등 자문</p>
						
					</div>
					
					
				</div>

			</div>
		</article>
		
		<article class="bgwhite">
			<div class="merit7">
				<h1>OUR PARTNERS</h1>
				<div class="partners" style="margin-top:20px;">
					<div class="alCenter">
						<span>
							<img src="/resources/img/yaho/partners01.jpg">
						</span>
					</div>
					<div class="alCenter">
						<span>
							<a href="https://www.knklaw.kr/"  target="_blank">
								<img src="/resources/img/yaho/partners03.jpg">
							</a>
						</span>
					</div>
					<div class="alCenter">
						<span>
							<a href="http://www.coinistar.com/" target="_blank">
								<img src="/resources/img/yaho/partners02.jpg">
							</a>
						</span>
					</div>
					<div class="alCenter">
						<span>
							<a href="http://www.d-global.biz/"  target="_blank">
								<img src="/resources/img/yaho/partners06.jpg">
							</a>
						</span>
					</div>
					<div class="alCenter">
						<span>
							<a href="http://www.hunnhyun.com"  target="_blank">
								<img src="/resources/img/yaho/partners12.jpg">
							</a>
						</span>
					</div>
					<div class="alCenter">
						<span>
							<a href="http://www.unideca.com/"  target="_blank">
								<img src="/resources/img/yaho/partners11.jpg">
							</a>
						</span>
					</div>
					<div class="alCenter">
						<span>
							<a href="https://www.secomns.co.kr/secomns/portal/Main_init.action"  target="_blank">
								<img src="/resources/img/yaho/partners10.jpg">
							</a>
						</span>
					</div>
					<div class="alCenter">
						<span>
							<a href="https://www.eyeonsecurity.co.kr/index"  target="_blank">
								<img src="/resources/img/yaho/partners09.jpg">
							</a>
						</span>
					</div>
					<div class="alCenter">
						<span>
							<a href="https://www.cloudflare.com/"  target="_blank">
								<img src="/resources/img/yaho/partners08.jpg">
							</a>
						</span>
					</div>
					<div class="alCenter">
						<span>
							<a href="https://www.cryptosquare.co.kr/"  target="_blank">
								<img src="/resources/img/yaho/partners07.jpg">
							</a>
						</span>
					</div>
					<div class="alCenter">
						<span>
							<a href="https://www.mypayx.net/"  target="_blank">
								<img src="/resources/img/yaho/partners04.png">
							</a>
						</span>
					</div>
					<div class="alCenter">
						<span>
							<a href="https://www.rsccoin.io/" target="_blank">
								<img src="/resources/img/yaho/partners05.png">
							</a>
						</span>
					</div>
				</div>
			</div>
		</article>
</section>

<%-- 
<c:forEach var="item" items="${event}">
<div class="popup main-event" data-no="${item.getSeqNo()}">
	<div class="popup-cont alCenter">
		${item.getContents()}
		<div class="btn-group">
			<button type="button" class="big close"> 확인</button>
		</div>
	</div>
</div>
</c:forEach>
--%>

<!--사전가입이벤트 팝업-->
<div class="popup main-event" data-no="1">
	<div class="popup-cont alCenter">
		<div class="event_conts">
			<img src="/resources/img/yaho/event/event001.jpg" alt="">
		</div>
		<div class="btn-group">
			<!--<button type="submit" class="big blue">신청하기 </button>-->
			<button type="submit" class="big close"> 확인</button>
		</div>
	</div>
</div>

<div class="popup main-event1" style="display: none; position: absolute; background-color: rgba(0,0,0,0); border: none; width: 50%; height: 100%">
	<div class="popup-cont alCenter" style="width:794px; height:auto; left: 550px; top: 50px; display: block">
		<div class="event_conts" style="height: 100%">
		<a class="event_m"><img src="/resources/img/yaho/event/event_m.jpg?v=201908121134" alt=""></a>
		</div>
		<div class="btn-group" style="position: relative">
		<label style="color: #fff; float: right;position: absolute; right: 5px; height: 50px; padding-top: 15px"><input type="checkbox" id="home-popup-event-pre-mining-dont-show-chkbox"><em></em>오늘 하루동안 보지 않기</label>
			<button type="submit" class="big close1"> 확인</button>
		</div>	
	</div>
</div>

<!-- 홍보 이벤트 팝업-->
<div class="popup main-event2" style="display: none; position: absolute; background-color: rgba(0,0,0,0); border: none; width: 50%; height: 100%; right: 0">
	<div class="popup-cont alCenter" style="height: 1045px; left: 400px; top: 50px">
		<div class="event_conts" style="position:relative; height: 993px">
			<img src="/resources/img/yaho/event/event004.jpg" alt="" style="height: 100%">
			<a href="#" style="position:absolute; top:790px; left:223px; z-index:10;  width:330px; height:40px;" class="pop_table_btn"></a>
			<style>
			.popup_table{display:none; position:absolute; top:405px; left:0; z-index:10;  width:100%; box-sizing:border-box; padding:0 40px 20px 40px; background:#22268a;}
			.popup_table table th{height:34px; line-height:34px; padding:0; margin:0; background:#141871; font-size:14px; color:#fff; border:1px solid #41479C;}
			.popup_table table td{height:34px; line-height:34px; padding:0; margin:0; font-size:14px; color:#fff; border:1px solid #41479C;}
			.popup_table table tr:hover td{background:#22268a;}
			.popup_table_close{position:absolute; z-index:11; right:28px; top:-13px; width:26px; height:26px; border-radius:15px; border:2px solid #fff; background:#000; text-align:center;}
			.popup_table_close img{margin-top:4px; width:14px; height:14px; vertical-align:top;}
			</style>
			<div class="popup_table">
			<table>
				<colgroup>
					<col style="width: 10%;">
					<col style="width: 65%;">
					<col style="width: 25%;"> 
				</colgroup>
				<thead>
					<tr>
						<th>순위</th>
						<th>대상자</th>
						<th>추천인수</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${topinfluencer}" var="ifList" varStatus="status">
						<tr>
							<td><c:out value="${status.count}"/>등</td>
							<td><c:out value="${ifList.USER_ID}"/></td>
							<td><c:out value="${ifList.CNT}"/></td>
						</tr>
					</c:forEach>
					<tr>
						<td colspan="3">※ 추천인 수는 매일 00시에 갱신됩니다.</td>
					</tr>
				</tbody>
			</table>
			<a href="#" class="popup_table_close"><img src="/resources/img/btn-pop-close.png" alt="close"></a>
			</div>
			<script>
			$(document).on(
				"click", ".pop_table_btn", 
				function(){
					$(".popup_table").fadeIn(500);
					return false;
				}
			);

			$(document).on(
				"click", ".popup_table_close", 
				function(){
					$(".popup_table").fadeOut(500);
					return false;
				}
			);
			</script>
		</div>
		<div class="btn-group" style="position: relative">
			<label style="color: #fff; float: right;position: absolute; right: 5px; height: 50px; padding-top: 15px"><input type="checkbox" id="home-popup-event-advertise-dont-show-chkbox"><em></em>오늘 하루동안 보지 않기</label>
			<button type="submit" class="big close2"> 확인</button>
		</div>
	</div>
</div>

<div class="popup main-extra" style="display: none; position: absolute; background-color: rgba(0,0,0,0); border: none; width: 50%; height: 100%; left: 26%;  z-index: 100000">
	<div class="popup-cont alCenter" style="width:794px; height: auto; left: 400px; top: 50px; overflow:hidden;">
        <div class="event_conts" style="height: 100%">
	        <p class="event_c">
				<img src="/resources/img/yaho/event/event_c.jpg" alt="">
				<a href="https://open.kakao.com/o/gZQpX2mb" target="_blank" class="link1">공식카톡방1 입장하기</a>
				<a href="https://open.kakao.com/o/gvomMavb" target="_blank" class="link2">공식카톡방1 입장하기</a>
				<a href="https://forms.gle/11pGLbjn6ySE5Ge56" target="_blank" class="link3">이벤트 참여설문</a>
			</p>
        </div>
		<div class="btn-group" style="position: relative">
		<label style="color: #fff; float: right;position: absolute; right: 5px; height: 50px; padding-top: 15px"><input type="checkbox" id="home-popup-event-comm-dont-show-chkbox"><em></em>오늘 하루동안 보지 않기</label>
			<button type="submit" class="big close3"> 확인</button>
		</div>
	</div>
</div>

<!-- 추가 이벤트 팝업-->
<%-- <c:if test="${event != null}">
<div class="popup main-extra autoHeight" style="display: none; position: absolute; background-color: rgba(0,0,0,0); border: none; width: 50%; height: 100%; left: 26%;  z-index: 100000">
	<!-- <div class="popup-cont alCenter" style="height: 1045px; left: 400px; top: 50px"> -->
	<div class="popup-cont alCenter" style=" height:auto;">
    	<!-- <div class="event_conts" style="height: 993px"> -->
    	<div class="event_conts">
	        ${event.getContents()}
    	</div>
		<div class="btn-group" style="position: relative">
			<label style="color: #fff; float: right;position: absolute; right: 5px; height: 50px; padding-top: 15px"><input type="checkbox" id="home-popup-event-comm-dont-show-chkbox"><em></em>오늘 하루동안 보지 않기</label>
			<button type="button" class="big close3"> 확인</button>
		</div>
    </div>
</div>
</c:if> --%>

<script type="text/javascript">
$(document).on('ready', function() {
	// Cookie 에따른 이벤트 팝업 숨기기/보이기 처리 (쿠키가 없으면 보임:: default)
	/* var miningEventCookie = cu.getCookie('preMiningEvent');
	var advertiseEventCookie = cu.getCookie('advertiseEvent');
	var commEventCookie = cu.getCookie('commEvent'); */
	/* if(miningEventCookie == '') $('.main-event1').show(); */
	/* if(advertiseEventCookie == '') $('.main-event2').show(); */
	/* if(commEventCookie == '') $('.main-extra').show(); */
	
	$(".banner").slick({
		rtl: false,
		dots: true,
		infinite: true,
		//autoplay: true,
		//autoplaySpeed: 3000,
		slidesToShow: 4,
		slidesToScroll: 1
	});
	
	setInterval(function() {
		if(document.visibilityState === "visible") {$(".banner").find('.slick-prev').trigger('click');}
	}, 4000)
	
	$(".slider_notice").slick({
		dots: false,
		vertical: true,
		autoplay: true,
		autoplaySpeed: 3000,
		slidesToShow: 1,
		slidesToScroll: 1
	}).show();
	
	/* $('.event-pop').click(function() {
		$('.main-event[data-no=' + $(this).data('no') + ']').show();
	}); */
	
	/* $('.event-pop').click(function() {
		$('.main-extra').show();
	});
	$('.event-pop1').click(function() {
		$('.main-event1').show();
	}); */
    /* $('.event-pop2').click(function() {
		$('.main-event2').show();
	}); */
    $('.close').click(function() {
		$('.popup').hide();
	});
    $('.close1').click(function() {
		$('.main-event1').hide();
		var checked = $("input:checkbox[id='home-popup-event-pre-mining-dont-show-chkbox']").is(":checked");
		if(checked) cu.setCookie('preMiningEvent', '1', 1);
	});
    $('.close2').click(function() {
		$('.main-event2').hide();
		var checked = $("input:checkbox[id='home-popup-event-advertise-dont-show-chkbox']").is(":checked");
		if(checked) cu.setCookie('advertiseEvent', '1', 1);
	});

    $('.close3').click(function() {
		$('.main-extra').hide();
		var checked = $("input:checkbox[id='home-popup-event-comm-dont-show-chkbox']").is(":checked");
		if(checked) cu.setCookie('commEvent', '1', 1);
	});
})

</script>

		<page:applyDecorator name="yahobitFooter"/>
		<page:applyDecorator name="yahobitAlert"/>
		
	</body>
	
</html>