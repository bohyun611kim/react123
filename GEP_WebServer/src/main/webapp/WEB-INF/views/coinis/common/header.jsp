<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/taglib/taglib.jsp" %>

<script src="/resources/js/common/common.js"></script>

<script type="text/javascript">
//상단 네비게이션
$(document).ready(function() {
    $(".topnav").hover(function() { //마우스를 topnav에 오버시
    	var $this = $(this);
        $this.parent().find(".subnav").slideDown('normal').show(); //subnav가 내려옴.
        $this.addClass('on');
        $this.parent().hover(function() {}, function() {
            $(this).parent().find(".subnav").slideUp('fast'); //subnav에서 마우스 벗어났을 시 원위치  
            $(".topnav").removeClass('on');
        });
    });
});
</script>

<!-- HEADER : Start -->
<header>
	<!--최상단 네비게이션 시작-->
	<nav>
		<div class="navi-wrap">
			<div class="navi-logo">
				<h1>
					<a href="/coinis/home" target="_parent"><img src="/resources/img/logo.png" alt="logo"></a>
				</h1>
			</div>
			<div class="navi-menu">
				<ul class="navi">
					<li class="navi_set">
						<div class="topnav" onclick="location.href='/coinis/exchange';">Exchange</div>
					</li>

					<li class="navi_set">
						<div class="topnav">Wallet</div>
						<ul class="subnav">
							<li onclick="location.href='/coinis/depositWithdrawals';">Deposit & Withdrawals</li>
							<li onclick="location.href='/coinis/transaction';">Transaction</li>
							<li onclick="location.href='/coinis/holdings';">Holdings</li>
						</ul>
					</li>

					<li class="navi_set">
						<div class="topnav">Orders</div>
						<ul class="subnav">
							<li onclick="location.href='/coinis/tradingHistory';">Trading History</li>
							<li onclick="location.href='/coinis/outstandingOrders';">Outstanding Orders</li>
						</ul>
					</li>

					<li class="navi_set">
						<div class="topnav">Support</div>
						<ul class="subnav">
							<li onclick="location.href='/coinis/notice';">Notice</li>
							<li onclick="location.href='/coinis/faq';">FAQ</li>
							<li onclick="location.href='/coinis/inquiry';">1:1 Inquiry</li>
						</ul>
					</li>
				</ul>
			</div>
			<div class="navi-user">
				<ul class="navi">
					<li class="navi_set">
						<c:choose>
							<c:when test="${sessionScope.userInfo.loginYn == 1}">
						<div class="topnav" onclick="location.href='/coinis/info';">My Page</div>
						<ul class="subnav">
							<li onclick="location.href='/coinis/info';">회원정보</li>
							<li onclick="location.href='/coinis/pwChange';">비밀번호 변경</li>
							<li>보안설정</li>
						</ul>
							</c:when>
							<c:otherwise>
						<div class="topnav" onclick="location.href='/coinis/register';">Register</div>
							</c:otherwise>
						</c:choose>
					</li>
					<li class="navi_set">
						<c:choose>
							<c:when test="${sessionScope.userInfo.loginYn == 1}">
						<div class="topnav" onclick="location.href='/coinis/logout';">Log Out</div>
							</c:when>
							<c:otherwise>
						<div class="topnav" onclick="location.href='/coinis/login';">Log In</div>
							</c:otherwise>
						</c:choose>
					</li>
					<li class="navi_set">
						<div class="topnav">English</div>
						<ul class="subnav">
							<li>English</li>
							<li>한국어</li>
						</ul>
					</li>
				</ul>
			</div>
		</div>
	</nav>
	<!--최상단 네비게이션 끝-->
</header>
<!-- //HEADER : End -->