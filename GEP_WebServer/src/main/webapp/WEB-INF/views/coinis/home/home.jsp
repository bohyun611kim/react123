<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/taglib/taglib.jsp" %>
<script src="/resources/js/jquery/jquery.tablesorter.js"></script>

<!--hoem 롤링배너영역 시작-->
	<div class="main-slide-wrap">
		<div class="main-slide">
			<div class="main-txt">
				<p> Global Leader of Blockchain Technology</p>
				<p> Trustworthy Crypto Assets Platform</p>
			</div>
			<div class="regular slider">
				<div>
					<span onclick="javascript:openForm()">
						<!--매인 롤링 이미지 가로 279픽셀, 세로 126픽셀 고정 사이즈-->
						<img src="/resources/img/event/event01.jpg" alt="banner01">
					</span>
				</div>
				<div>
					<span onclick="javascript:openForm1()">
						<img src="/resources/img/event/event02.jpg" alt="banner01">
					</span>
				</div>
				<div>
					<span>
						<img src="/resources/img/event/event03.jpg" alt="banner01">
					</span>
				</div>
				<div>
					<span>
						<img src="/resources/img/event/event04.jpg" alt="banner01">
					</span>
				</div>
				<div>
					<span>
						<img src="/resources/img/event/event05.jpg" alt="banner01">
					</span>
				</div>
			</div>
		</div>
	</div>
	<!--hoem 롤링배너영역 끝-->

	<!-- 공지사항 : Start -->
	<div class="notice-wrap">
		<div class="notice-bar">
			<span>Notice</span>
			<a href="GLOBAL-WEB--CST-S001.html">Are you not a CoinIs member yet? Start fast & secure trading at CoinIs Exchange</a>
			<a href="GLOBAL-WEB--CST-M001.html">More</a>
		</div>
	</div>
	<!-- //공지사항 : End -->
	
	<!--컨텐츠 내용 영역 시작-->
	<section class="dark">


		<!--마켓별 코인정보 테이블 시작-->
		<article class="home-markets">
			
			<!--시세배너 5개 시작-->
			<div class="home-banner">
				<a href="/coinis/exchange/${HomeTopItem.topVolumeList[0].item_code}">
					<div>TOP VOLUME</div>
					<div>
						<span>${HomeTopItem.topVolumeList[0].item_nm}</span>
					</div>
					<!-- Amount -->
					<c:choose>
						<c:when test="${HomeTopItem.topVolumeList[0].price_dev_amt < 0}">
							<div class="red">
						</c:when>
						<c:otherwise>
							<div class="green">
						</c:otherwise>
					</c:choose>
							<fmt:formatNumber value="${HomeTopItem.topVolumeList[0].price_dev_amt}" groupingUsed="true" 
											  minFractionDigits="${HomeTopItem.topVolumeList[0].price_dsp_decimal_pnt}"
											  maxFractionDigits="${HomeTopItem.topVolumeList[0].price_dsp_decimal_pnt}"/>
							</div>
					<!-- Percent -->
					<c:choose>
						<c:when test="${HomeTopItem.topVolumeList[0].price_dev_rate < 0}">
							<div class="red">
						</c:when>
						<c:otherwise>
							<div class="green">
						</c:otherwise>
					</c:choose>
							${HomeTopItem.topVolumeList[0].price_dev_rate} %
							</div>
					<!-- Price -->
					<div><span><fmt:formatNumber value="${HomeTopItem.topVolumeList[0].close_price}" groupingUsed="true" 
									minFractionDigits="${HomeTopItem.topVolumeList[0].price_dsp_decimal_pnt}"
									maxFractionDigits="${HomeTopItem.topVolumeList[0].price_dsp_decimal_pnt}"/>
									</span><span> ${fn:split(HomeTopItem.topVolumeList[0].item_nm,'/')[1]}</span>
					</div>
				</a>

				<a href="/coinis/exchange/${HomeTopItem.topGainList[0].item_code}">
					<div>TOP GAIN</div>
					<div>
						<span>${HomeTopItem.topGainList[0].item_nm}</span>
					</div>
					<!-- Amount -->
					<c:choose>
						<c:when test="${HomeTopItem.topGainList[0].price_dev_amt < 0}">
							<div class="red">
						</c:when>
						<c:otherwise>
							<div class="green">
						</c:otherwise>
					</c:choose>
							<fmt:formatNumber value="${HomeTopItem.topGainList[0].price_dev_amt}" groupingUsed="true" 
											  minFractionDigits="${HomeTopItem.topGainList[0].price_dsp_decimal_pnt}"
											  maxFractionDigits="${HomeTopItem.topGainList[0].price_dsp_decimal_pnt}"/>
							</div>
					<!-- Percent -->
					<c:choose>
						<c:when test="${HomeTopItem.topGainList[0].price_dev_rate < 0}">
							<div class="red">
						</c:when>
						<c:otherwise>
							<div class="green">
						</c:otherwise>
					</c:choose>
							${HomeTopItem.topGainList[0].price_dev_rate} %
							</div>
					<!-- Price -->
					<div><span><fmt:formatNumber value="${HomeTopItem.topGainList[0].close_price}" groupingUsed="true" 
									minFractionDigits="${HomeTopItem.topGainList[0].price_dsp_decimal_pnt}"
									maxFractionDigits="${HomeTopItem.topGainList[0].price_dsp_decimal_pnt}"/>
									</span><span> ${fn:split(HomeTopItem.topGainList[0].item_nm,'/')[1]}</span>
					</div>
				</a>

				<a href="/coinis/exchange/${HomeTopItem.topGainList[1].item_code}">
					<div>TOP GAIN</div>
					<div>
						<span>${HomeTopItem.topGainList[1].item_nm}</span>
					</div>
					<!-- Amount -->
					<c:choose>
						<c:when test="${HomeTopItem.topGainList[1].price_dev_amt < 0}">
							<div class="red">
						</c:when>
						<c:otherwise>
							<div class="green">
						</c:otherwise>
					</c:choose>
							<fmt:formatNumber value="${HomeTopItem.topGainList[1].price_dev_amt}" groupingUsed="true" 
											  minFractionDigits="${HomeTopItem.topGainList[1].price_dsp_decimal_pnt}"
											  maxFractionDigits="${HomeTopItem.topGainList[1].price_dsp_decimal_pnt}"/>
							</div>
					<!-- Percent -->
					<c:choose>
						<c:when test="${HomeTopItem.topGainList[1].price_dev_rate < 0}">
							<div class="red">
						</c:when>
						<c:otherwise>
							<div class="green">
						</c:otherwise>
					</c:choose>
							${HomeTopItem.topGainList[1].price_dev_rate} %
							</div>
					<!-- Price -->
					<div><span><fmt:formatNumber value="${HomeTopItem.topGainList[1].close_price}" groupingUsed="true" 
												 minFractionDigits="${HomeTopItem.topGainList[1].price_dsp_decimal_pnt}"
												 maxFractionDigits="${HomeTopItem.topGainList[1].price_dsp_decimal_pnt}"/>
									</span><span> ${fn:split(HomeTopItem.topGainList[1].item_nm,'/')[1]}</span>
					</div>
				</a>

				<a href="/coinis/exchange/${HomeTopItem.newListingList[0].item_code}">
					<div>NEW LISTING</div>
					<div>
						<span>${HomeTopItem.newListingList[0].item_nm}</span>
					</div>
					<!-- Amount -->
					<c:choose>
						<c:when test="${HomeTopItem.newListingList[0].price_dev_amt < 0}">
							<div class="red">
						</c:when>
						<c:otherwise>
							<div class="green">
						</c:otherwise>
					</c:choose>
							<fmt:formatNumber value="${HomeTopItem.newListingList[0].price_dev_amt}" groupingUsed="true" 
											  minFractionDigits="${HomeTopItem.newListingList[0].price_dsp_decimal_pnt}"
											  maxFractionDigits="${HomeTopItem.newListingList[0].price_dsp_decimal_pnt}"/>
							</div>
					<!-- Percent -->
					<c:choose>
						<c:when test="${HomeTopItem.newListingList[0].price_dev_rate < 0}">
							<div class="red">
						</c:when>
						<c:otherwise>
							<div class="green">
						</c:otherwise>
					</c:choose>
							${HomeTopItem.newListingList[0].price_dev_rate} %
							</div>
					<!-- Price -->
					<div><span><fmt:formatNumber value="${HomeTopItem.newListingList[0].close_price}" groupingUsed="true" 
												 minFractionDigits="${HomeTopItem.newListingList[0].price_dsp_decimal_pnt}"
												 maxFractionDigits="${HomeTopItem.newListingList[0].price_dsp_decimal_pnt}"/>
									</span><span> ${fn:split(HomeTopItem.newListingList[0].item_nm,'/')[1]}</span>
					</div>
				</a>
				
				<a href="/coinis/exchange/${HomeTopItem.newListingList[1].item_code}">
					<div>NEW LISTING</div>
					<div>
						<span>${HomeTopItem.newListingList[1].item_nm}</span>
					</div>
					<!-- Amount -->
					<c:choose>
						<c:when test="${HomeTopItem.newListingList[1].price_dev_amt < 0}">
							<div class="red">
						</c:when>
						<c:otherwise>
							<div class="green">
						</c:otherwise>
					</c:choose>
							<fmt:formatNumber value="${HomeTopItem.newListingList[1].price_dev_amt}" groupingUsed="true" 
											  minFractionDigits="${HomeTopItem.newListingList[1].price_dsp_decimal_pnt}"
											  maxFractionDigits="${HomeTopItem.newListingList[1].price_dsp_decimal_pnt}"/>
							</div>
					<!-- Percent -->
					<c:choose>
						<c:when test="${HomeTopItem.newListingList[1].price_dev_rate < 0}">
							<div class="red">
						</c:when>
						<c:otherwise>
							<div class="green">
						</c:otherwise>
					</c:choose>
							${HomeTopItem.newListingList[1].price_dev_rate} %
							</div>
					<!-- Price -->
					<div><span><fmt:formatNumber value="${HomeTopItem.newListingList[1].close_price}" groupingUsed="true" 
										 		 minFractionDigits="${HomeTopItem.newListingList[1].price_dsp_decimal_pnt}"
												 maxFractionDigits="${HomeTopItem.newListingList[1].price_dsp_decimal_pnt}"/>
									</span><span> ${fn:split(HomeTopItem.newListingList[1].item_nm,'/')[1]}</span>
					</div>
				</a>


			</div>
			<!--시세배너 5개 끝-->

			<!--마켓별 종목 시세 시작-->
			<c:forEach var="marketList" items="${marketList}">
				<div class="${marketList.mkt_nm}Market">
					<h2 class="market-title">${marketList.mkt_nm} Market</h2>
					<div class="main-serch" data-target="#${marketList.mkt_nm}">
						<input type="text" placeholder="Search">
					</div>
					<c:choose>
						<c:when test="${fn:length(marketList.itemSiseList) > 20}">
							<div class="folding" id="${marketList.mkt_nm}">
						</c:when>
						<c:otherwise>
							<div id="${marketList.mkt_nm}">
						</c:otherwise>
					</c:choose>
					<table id="${marketList.mkt_nm}siseTable" class="tablesorter">
						<colgroup>
							<col style="width:50px;">
							<col>
							<col>
							<col>
							<col>
							<col>
							<col>
						</colgroup>
						<thead>
							<tr>
								<th><span class="bookmark"></span></th>
								<th>Pair</th>
								<th>Last Price</th>
								<th>24h Change</th>
								<th>24h High</th>
								<th>24h Low</th>
								<th><a href="#" class="sort-toggle sort-up">24h Volume</a></th>
							</tr>
						</thead>
						<tbody>
 							<c:forEach var="itemSiseList" items="${marketList.itemSiseList}">
								<tr name="${itemSiseList.item_nm}" onclick="location.href='/coinis/exchange/${itemSiseList.item_code}'">
									<td><span class="bookmark"></span></td>
									<td>
										<span class="coin-name">${itemSiseList.item_nm} / ${marketList.mkt_nm}</span>
									</td>
									<td>
										<span class="val-bolder"><fmt:formatNumber value="${itemSiseList.close_price}" pattern="#,##0" 
												minFractionDigits="${itemSiseList.price_dsp_decimal_pnt}"
												maxFractionDigits="${itemSiseList.price_dsp_decimal_pnt}"/></span>
									</td>
									<c:choose>
										<c:when test="${itemSiseList.price_dev_rate < 0}">
											<td class="red">
										</c:when>
										<c:otherwise>
											<td class="green">
										</c:otherwise>
									</c:choose>${itemSiseList.price_dev_rate} %</td>
									<td>
										<span class="val-bolder"><fmt:formatNumber value="${itemSiseList.high_price}" pattern="#,##0"
												minFractionDigits="${itemSiseList.price_dsp_decimal_pnt}"
												maxFractionDigits="${itemSiseList.price_dsp_decimal_pnt}" /></span>
									</td>
									<td>
										<span class="val-bolder"><fmt:formatNumber value="${itemSiseList.low_price}" pattern="#,##0" 
												minFractionDigits="${itemSiseList.price_dsp_decimal_pnt}"
												maxFractionDigits="${itemSiseList.price_dsp_decimal_pnt}"/></span>
									</td>
									<td>
										<span class="val-bolder"><fmt:formatNumber value="${itemSiseList.trade_vol}" pattern="#,##0" 
												minFractionDigits="${itemSiseList.vol_dsp_decimal_pnt}"
											 	maxFractionDigits="${itemSiseList.vol_dsp_decimal_pnt}"/></span>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					</div>
					<c:choose>
					<c:when test="${fn:length(marketList.itemSiseList) > 20}">
						<div class="viewall">
							<button class="btn-folding" data-target="#${marketList.mkt_nm}"></button>
						</div>
					</c:when>
					<c:otherwise>
					</c:otherwise>
				</c:choose>
				</div>
				
	
			</c:forEach>
			<!--마켓별 종목 시세 끝-->

		</article>
		<!--마켓별 코인정보 테이블 시작-->

	</section>
	<!--컨텐츠 내용 영역 끝-->
	
<script>

$(document).ready(function() {
	
	// table sort
	// $('#siseTable').tablesorter();
	// $('#CXATsiseTable').tablesorter({ sortList: [[6,0]] });
	// $('#CXATsiseTable').trigger("sorton", [[6,0]]);
	
/* 	var marketname = $('.main-serch').data('target');
	
	$(marketname).each(function() {
		var tableid = $('.main-serch').next().find('table').attr('id');
		
		console.log(tableid);
		$('#tableid').tablesorter();
	}) */
	
	// viewall - folding 
	$('.btn-folding').click(function() {
		
		var $id = $(this).data('target');
		$($id).attr('class') === 'folding' ? $($id).addClass('more') : $($id).removeClass('more');
	});
	
	// 종목 Search-box
	$('.main-serch').on("keyup", 'input', function() {

		var input = $(this);
		var id = $(this).closest('div').data('target');
		
		if( input.val() != '' && isNaN(input.val()) ) {
		
			$(id).find('tbody > tr:not([name*=' + input.val().toUpperCase() + '])').hide();
			$(id).find('tbody > tr[name*=' + input.val().toUpperCase() + ']').show();
		} else
			$(id).find('tbody > tr').show();
		
		// folding & viewall show/hide
		if( $(id).find('tbody > tr:not([style="display: none;"])').length < 20 ) {

			input.closest('div').next('div').removeClass('folding');
			input.closest('div').next('div').next('div').find('button').hide();
			input.closest('div').next('div').next('div').removeClass('viewall');

		} else {
			input.closest('div').next('div').addClass('folding');
			input.closest('div').next('div').next('div').addClass('viewall');
			input.closest('div').next('div').next('div').find('button').show();
		}
	});
	
});


/*북마크*/
$('.bookmark').click(function() {
	$(this).toggleClass('mark-on');
});

/*슬릭스 슬라이더*/
$(".regular").slick({
	dots: true,
	infinite: true,
	slidesToShow: 4,
	slidesToScroll: 4,
	autoplay: false,
	autoplaySpeed: 4000
});

//팝업 열고 닫기   
function openForm() {
	document.getElementById("myForm").style.display = "block";
}

function closeForm() {
	document.getElementById("myForm").style.display = "none";
}

function openForm1() {
	document.getElementById("myForm1").style.display = "block";
}

function closeForm1() {
	document.getElementById("myForm1").style.display = "none";
}

function openForm2() {
	document.getElementById("myForm2").style.display = "block";
}

function closeForm2() {
	document.getElementById("myForm2").style.display = "none";
}
</script>