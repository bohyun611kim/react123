<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/taglib/taglib.jsp" %>

<script type="text/javascript">
function mobRf(){
	var rf = new EN();
	rf.setData("userid", "yahobit");
	rf.setSSL(true);
	rf.sendRf();
}
</script>
<script src="https://cdn.megadata.co.kr/js/en_script/3.5/enliple_min3.5.js" defer="defer" onload="mobRf()"></script>

<header>
	<nav>
		<i></i>
		<div class="logo">
			<h1>
				<a href="/"><img src="/resources/img/yaho/logo.png" alt="logo"></a>
			</h1>
		</div>
		<div class="gnb-menu">
			<ul>
				<li><a href="javascript:void(0);" onclick="location.href='/site/exchange';">거래소</a></li>
				<li><a href="javascript:void(0);" onclick="location.href='/alibit/wallet';">입출금</a></li>
				<li><a href="javascript:void(0);" onclick="location.href='/site/invest';">투자내역</a></li>
				<li><a href="javascript:void(0);" onclick="location.href='/alibit/crowdSale';" style="font-size : 96%">Token Launchpad</a></li>
				<li><a href="javascript:void(0);" onclick="location.href='/alibit/anal';">시장동향</a></li>
				<li><a href="javascript:void(0);" onclick="location.href='/alibit/support';">고객지원</a></li>
			</ul>
		</div>

		<div class="gnb-link">
			<ul>
				<c:choose>
					<c:when test="${not empty sessionScope.userInfo}">
				<li onclick="location.href='/alibit/info';">마이페이지</li>
				<li onclick="location.href='/alibit/logout';">로그아웃</li>
					</c:when>
					<c:otherwise>
				<li onclick="location.href='/alibit/login';">로그인</li>
				<li onclick="location.href='/alibit/register';">회원가입</li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
	</nav>
</header>

<%--
<header>
	<nav>
        <i>BETA</i>
		<div class="logo">
			<h1>
				<a href="/alibit/home"><img src="/resources/img/yaho/logo.png" alt="logo"></a>
			</h1>
		</div>
		<div class="gnb-menu">
			<ul>
				<li><a href="javascript:void(0);" onclick="location.href='/alibit/exchange';">거래소</a></li>
				<li><a href="javascript:void(0);" onclick="location.href='/alibit/wallet';">입출금</a></li>
				<li><a href="javascript:void(0);" onclick="location.href='/alibit/invest';">투자내역</a></li>
				<!-- <li><a href="javascript:void(0);" onclick="alert('서비스 준비중 입니다');">YEP RP</a></li> -->
				<li><a href="javascript:void(0);" onclick="location.href='/alibit/yeprp';">YEP RP</a></li>
				<li><a href="javascript:void(0);" onclick="alert('서비스 준비중 입니다');">IEO</a></li>
				<li><a href="javascript:void(0);" onclick="location.href='/alibit/anal';">시장동향</a></li>
				<li><a href="javascript:void(0);" onclick="location.href='/alibit/support';">고객지원</a></li>
			</ul>
		</div>

		<div class="gnb-link">
			<ul>
				<c:choose>
					<c:when test="${not empty sessionScope.userInfo}">
				<li onclick="location.href='/alibit/info';">마이페이지</li>
				<li onclick="location.href='/alibit/logout';">로그아웃</li>
					</c:when>
					<c:otherwise>
				<li onclick="location.href='/alibit/login';">로그인</li>
				<li onclick="location.href='/alibit/register';">회원가입</li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
	</nav>
</header>
--%>