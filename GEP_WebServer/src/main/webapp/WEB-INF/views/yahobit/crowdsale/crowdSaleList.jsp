<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/taglib/taglib.jsp" %>

<section class="main">

	<article>
		<div class="content">
			<h1 class="subtitle">Token Launchpad</h1>
			<div class="crs-main">
				<div class="crs">
					<div class="ico-txt07"></div>
					<div class="regular slider">
						<div style="position: relative">
							<div class="title_txt txt2_bg">
								<p class="main_title">NO KYC Identification</p>
								<p class="sub_txt">단 한번만 KYC 인증을 하면 이후에는 별도의 KYC 인증을 거치지 않고<br>ALIBIT에서 진행하는 모든 프로젝트에 편리하게 참여할 수 있습니다.</p>
								<p><img src="/resources/img/crowd/common/crowdsale-img03.png" class="locate2"></p>
							</div>
							<span><img src="/resources/img/crowd/common/bg/bg_crowdsale_home02.jpg" style="width: 100%; height: 240px"></span>
						</div>

						<div style="position: relative">
							<div class="title_txt">
								<p class="main_title">NO Withdrawal Fee</p>
								<p class="sub_txt">ALIBIT 계정에 있는 암호화폐로 직접 참여하기 때문에<br>별도의 출금 수수료를 지불할 필요가 없습니다.</p>
								<p><img src="/resources/img/crowd/common/crowdsale-img04.png" class="locate2"></p>
							</div>
							<span><img src="/resources/img/crowd/common/bg/bg_crowdsale_home03.jpg" style="width: 100%; height: 240px"></span>
						</div>

						<div style="position: relative">
							<div class="title_txt">
								<p class="main_title">NO Waiting To Receive Your ALIBIT</p>
								<p class="sub_txt">Token Launchpad에서는 암호화폐가 분배될 때 까지 기다리지 않고<br> ALIBIT 계정에서 즉시 잔고를 확인할 수 있습니다.</p>
								<p><img src="/resources/img/crowd/common/crowdsale-img05.png" class="locate2"></p>
							</div>
							<span><img src="/resources/img/crowd/common/bg/bg_crowdsale_home04.jpg" style="width: 100%; height: 240px"></span>
						</div>

					</div>
				</div>
			</div>
			<%-- 
			<div class="crs-cont" style="margin-top: 30px">
				<div class="center mgt20">
					<p><img src="/resources/img/yaho/img-alibit.jpg" alt="img"></p>
					<p class="bolder blue"> IEO 준비중</p>
					<!-- <p>현재 옙(YEP) 은 IEO 준비 중입니다.</p> -->                            
				</div>
			</div> --%>
			
			<c:if test="${going.size() gt 0}">
			<div class="crs-cont" style="margin-top: 30px">
			
				<h3 class="subtitle">진행중인 프로젝트
					<span class="bolder blue">${going.size()}</span>
				</h3>

				<c:forEach var="item" items="${going}">
				<div class="crowdsale-wrap" style="background-color : black; background-image: url(/resources/img/crowd/${item.getCoinSymbolicNm()}/bg-crowd-${item.getCoinSymbolicNm()}.jpg)">
					<c:if test="${item.getCrwdTypeCd() eq 1}">
					<!-- <span class="ico-coin"></span> -->
					</c:if>
					<c:if test="${item.getCrwdTypeCd() eq 2}">
					<!-- <span class="ieo-coin"></span> -->
					</c:if>
					<c:if test="${item.getCrwdTypeCd() eq 3}">
					<!-- <span class="bs-coin"></span> -->
					</c:if>
					<div class="crs-join">
						<ul>
							<li style="">
								<img src="/resources/img/coin-symbol/${item.getCoinSymbolicNm()}.png">
							</li>
							<li>
								<p>${item.getTitle()}</p>
								<p>${item.getCoinUsage()}</p>
							</li>
						</ul>
						<dl>
							<dt>
								<p><span>목표수량 :</span><span>${item.getHardCap()}  ${item.getSlQtyBasisCoinSymbolicNm()}</span></p>
								<p><span>교환비율 :</span>
									<span>${item.getTxtPrice()}</span>
								</p>
							</dt>
							<dd class="timer" data-end="${item.getEndDt()}">
								<p>Ends in</p>
								<span class="txt_deepaqua">00</span>days :
								<span class="txt_deepaqua">00</span>hours :
								<span class="txt_deepaqua">00</span>mins :
								<span class="txt_deepaqua">00</span>secs
							</dd>
						</dl>
					</div>

					<div class="crs-link">
						<ul>
							<!-- <li style="cursor:pointer;" onclick="openPopup('#${item.getCoinSymbolicNm()}')"><>IEO Schedule <i class="arr1 arr-right"></i></a></li> -->
							<li style="cursor:pointer; height : 25%; line-height: 600%;" onclick="window.open('${item.getHomepageUrl()}');"><a>Homepage <i class="arr1 arr-right"></i></a></li>
							<li style="cursor:pointer; height : 25%; line-height: 600%;" onclick="window.open('${item.getWhitepaperUrl()}');"><a>White Paper<i class="arr1 arr-right"></i></a></li>
							<li style="cursor:pointer; height : 25%; line-height: 600%;" onclick="window.open('${item.getVideoClipUrl()}');"><a>Video Clip<i class="arr1 arr-right"></i></a></li>
							<li style="cursor:pointer; height : 25%; line-height: 600%;" onclick="location.href='/alibit/crowdSale/join?no=${item.getSeq()}';"><a>참여하기<i class="arr1 arr-right"></i></a></li>
						</ul>
					</div>
				</div>
				</c:forEach>
			</div>
			</c:if>

			<c:if test="${coming.size() gt 0}">
			<h3 class="subtitle mgt30">Coming Soon
				<span class="bolder blue">${coming.size()}</span>
			</h3>
			<c:forEach var="item" items="${coming}">
			<div class="crowdsale-wrap" style="background-color : black; background-image: url(/resources/img/crowd/${item.getCoinSymbolicNm()}/bg-crowd-${item.getCoinSymbolicNm()}.jpg)">
				<c:if test="${item.getCrwdTypeCd() eq 1}">
				<!-- <span class="ico-coin"></span> -->
				</c:if>
				<c:if test="${item.getCrwdTypeCd() eq 2}">
				<!-- <span class="ieo-coin"></span> -->
				</c:if>
				<c:if test="${item.getCrwdTypeCd() eq 3}">
				<!-- <span class="bs-coin"></span> -->
				</c:if>
				<div class="crs-join">
					<ul>
						<li style="">
							<img src="/resources/img/coin-symbol/${item.getCoinSymbolicNm()}.png">
						</li>
						<li>
							<p>${item.getTitle()}</p>
						</li>
					</ul>
					<div class="crs-info">
						<p class="mgt20">${item.getCoinUsage()}</p>
						<dl>
							<dt style="width: 30%;">
								<p class="blue">Token Launchpad 일정</p>
							</dt>
							<dd style="width: 70%;">
								<p>
									<fmt:parseDate var="dateString" value="${item.getStartDt()}" pattern="yyyyMMddHHmmss" />
									<fmt:formatDate value="${dateString}" pattern="yyyy-MM-dd" />
									&nbsp;~&nbsp;
									<fmt:parseDate var="dateString" value="${item.getEndDt()}" pattern="yyyyMMddHHmmss" />
									<fmt:formatDate value="${dateString}" pattern="yyyy-MM-dd" /> 
								</p>
							</dd>
						</dl>
					</div>
				</div>
				<div class="crs-link">
					<ul>
						<!-- <li style="height:25%;cursor:pointer;" onclick="openPopup('#${item.getCoinSymbolicNm()}')">
							<a>IEO Schedule <i class="arr1 arr-right"></i></a>
						</li> -->
						<li style="height:33%; line-height: 800%; cursor:pointer;" onclick="window.open('${item.getHomepageUrl()}');">
							<a>Homepage <i class="arr1 arr-right"></i></a>
						</li>
						<li style="height:33%; line-height: 800%; cursor:pointer;" onclick="window.open('${item.getWhitepaperUrl()}');">
							<a>White Paper<i class="arr1 arr-right"></i></a>
						</li>
						<li style="height:33%; line-height: 800%; cursor:pointer;" onclick="window.open('${item.getVideoClipUrl()}');">
							<a>Video Clip<i class="arr1 arr-right"></i></a>
						</li>
					</ul>
				</div>
			</div>
			</c:forEach>
			</c:if>

			<c:if test="${ended.size() gt 0}">
			<h3 class="subtitle mgt30">종료된 프로젝트
				<span class="bolder blue">${ended.size()}</span>
			</h3>
			
			<c:forEach var="item" items="${ended}" varStatus="status">
				<c:if test="${status.index%3 eq 0}">
			<div>
				</c:if>
				
				<div class="ico_list_wrap">
					<div class="ico-end-txt">
						<span>판매마감</span>
						<c:if test="${item.getListedYn() eq 1}">
						<span>상장확정</span>
						</c:if>
						<p>관심과 성원에 감사드립니다 !</p>
					</div>
					<div class="ico-end"></div>
					<c:if test="${item.getCrwdTypeCd() eq 1}">
					<!-- <span class="ico-coin"></span> -->
					</c:if>
					<c:if test="${item.getCrwdTypeCd() eq 2}">
					<!-- <span class="ieo-coin"></span> -->
					</c:if>
					<c:if test="${item.getCrwdTypeCd() eq 3}">
					<!-- <span class="bs-coin"></span> -->
					</c:if>
					<dl>
						<dt class="prelist01" style="background-image: url(/resources/img/ico/bg/bg_ico_onlist01.jpg);">
							<span> <img src="/resources/img/coin-symbol/${item.getCoinSymbolicNm()}.png"></span>
							<span class="ico_coinname">${item.getTitle()}</span>
							<span class="mgt5">${item.getSlCoinSymbolicNm()} / ${item.getCoinUsage()}</span>
						</dt>
						<dd class="ico-amount">
							<span>목표수량</span>
							<span>${item.getTxtTarget()} </span>
						</dd>
						<dd class="ico-price">
							<span>교환비율</span>
							<span>${item.getTxtPrice()}</span>
						</dd>
						<dd class="ico-time">
							<h5 class="wdt-62">시작일 </h5>
							<span class="align-left font-base  black">
								<fmt:parseDate var="dateString" value="${item.getStartDt()}" pattern="yyyyMMddHHmmss" />
								<fmt:formatDate value="${dateString}" pattern="yyyy-MM-dd" />
							</span>
						</dd>
						<dd class="ico-time">
							<h5 class="wdt-62">종료일 </h5>
							<span class="align-left font-base  black">
								<fmt:parseDate var="dateString" value="${item.getEndDt()}" pattern="yyyyMMddHHmmss" />
								<fmt:formatDate value="${dateString}" pattern="yyyy-MM-dd" />
							</span>
						</dd>

						<dd class="ico-sns">
						</dd>
					    <dd class="ico-site">
							<span style="cursor:pointer;" onclick="window.open('${item.getHomepageUrl()}');">공식웹사이트</span>
							<a href="${item.getHomepageUrl()}" target="_blank"><img src="/resources/img/crowd/common/crowdsale_img1.png"></a>
						</dd>
					</dl>
				</div>
				
				<c:if test="${status.index%3 eq 2}">
			</div>
				</c:if>
			</c:forEach>
			</c:if>

		</div>
	</article>

</section>

<script>
$(document).ready(function() {
	$(".regular").slick({
		dots: true,
		infinite: true,
		slidesToShow: 1,
		slidesToScroll: 1,
		autoplay: true,
		autoplaySpeed: 4000
	});
	
	$('.timer').each(function() {
		var $this = $(this);
		var duration = parseInt(getDuration(parseInt($this.data('end')), '${now}', 'yyyyMMddHHmmss') / 1000);
		var timer = duration;
		
		if(timer > 0) {
			setInterval(function() {
				var $span = $this.find('span');
				var days = parseInt(timer / (24*60*60));
			    days = days < 10 ? "0" + days : days;
			    
				var hours = parseInt((timer - (days*24*60*60)) / (60*60));
				hours = hours < 10 ? "0" + hours : hours;
				
				var minutes = parseInt((timer - (days*24*60*60) - (hours*60*60)) / 60);
				minutes = minutes < 10 ? "0" + minutes : minutes;
				
			    var seconds = parseInt((timer - (days*24*60*60) - (hours*60*60) - (minutes*60)));
			    seconds = seconds < 10 ? "0" + seconds : seconds;
			    
			    $span.eq(0).text(days);
			    $span.eq(1).text(hours);
			    $span.eq(2).text(minutes);
			    $span.eq(3).text(seconds);
			    
			    timer--;
			    
			    if(timer < 0) {
			    	clearInterval(this);
			    }
			}, 1000);
		}
	});
})

function getDuration(v1, v2, f1, f2) {
	var a = f1, b = f2;
	
	if(f2 == undefined || f2 == null) b = f1;
	
	return Math.abs(dateToEpoch(v1, a) - dateToEpoch(v2, b));
}

function dateToEpoch(value, format) {
	var now = new Date;
	var yyyy = null, MM = null, dd = null, HH = null, mm = null, ss = null, SSS = null;
	var target = '' + value;
	
	if(value == null || value == undefined) {
		return undefined;
	}
	
	if(format.indexOf('yyyy') > -1) {
		yyyy = target.substr(format.indexOf('yyyy'), 4);
	}
	if(format.indexOf('MM') > -1) {
		MM = parseInt(target.substr(format.indexOf('MM'), 2)) -1;
	}
	if(format.indexOf('dd') > -1) {
		dd = target.substr(format.indexOf('dd'), 2);
	}
	if(format.indexOf('HH') > -1) {
		HH = target.substr(format.indexOf('HH'), 2);
	}
	if(format.indexOf('mm') > -1) {
		mm = target.substr(format.indexOf('mm'), 2);
	}
	if(format.indexOf('ss') > -1) {
		ss = target.substr(format.indexOf('ss'), 2);
	}
	if(format.indexOf('SSS') > -1) {
		SSS = target.substr(format.indexOf('SSS'), 3);
	}
	
	return new Date(yyyy, MM, dd, HH, mm, ss, SSS).getTime();
}

function openPopup(target) {
	
}
</script>