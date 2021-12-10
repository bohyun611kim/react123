<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/taglib/taglib.jsp" %>

<script src="/resources/js/common/paging.js"></script>

<!--컨텐츠 내용 영역 시작-->
<section class="bggray">
	<!--공지사항 전체 내용 시작-->
	<article class="bgwhite">

		<h1 class="sub-title">Notice</h1>
		
		<div id="notice" class="content-item">
			<div class="search-option-group">
				<div class="fl-left">
					Total <b id="count" class="blue">${size}</b> rows retrieved
				</div>
			</div>

			<!--공지사항 리스트  테이블 시작-->
			<div class="notice">
				<table class="tblType02">
					<colgroup>
						<col style="width: 100px">
						<col>
						<col style="width: 200px">
					</colgroup>
					<thead>
						<tr>
							<th>Notice No.</th>
							<th>Title</th>
							<th>DateTime</th>
						</tr>
					</thead>
					<tbody>
						<!--최대 20개? 노출-->
						<c:forEach var="list" items="${data}">
							<c:choose>
								<c:when test="${list.topYn == 1}">
									<tr>
									<td>${list.no}</td>
									<td class="left new"><a onclick="toggle()">${list.title}</a></td>
									<td><fmt:parseDate var="dateString" value="${list.regDt}"
											pattern="yyyyMMddHHmmss" /> <fmt:formatDate
											value="${dateString}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
									</tr>
								</c:when>
								<c:otherwise>
									<tr>
									<td>${list.no}</td>
									<td class="left new"><a onclick="toggle()">${list.title}</td>
									<td><fmt:parseDate var="dateString" value="${list.regDt}"
											pattern="yyyyMMddHHmmss" /> <fmt:formatDate
											value="${dateString}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
									</tr>
								</c:otherwise>
							</c:choose>	
						</c:forEach>
					</tbody>
				</table>
				<!-- //페이징 : End -->
			</div>
			<!--공지사항 리스트 테이블 끝-->

			<!-- 페이징 : Start -->
			<ul class="pagination">
				<li><a class="btn-page first"></a></li>
				<li><a class="btn-page prev"></a></li>
				<li><a class="active">1</a></li><!-- 선택된 번호의 a 태그에 acitve 클래스 추가 -->
				<li><a >2</a></li>
				<li><a >3</a></li>
				<li><a >4</a></li>
				<li><a >5</a></li>
				<li><a class="btn-page next"></a></li>
				<li><a class="btn-page last"></a></li>
			</ul>
		</div>
		<div id="content" class="content-item" style="display:none">
                <h2>{{title}}
                    <span class="fl-right">{{regDt}}</span>
                </h2>
                <!--공지사항 내용 시작-->
                <div class="notice line">
                {{contents}}
                </div>
                <!--공지사항 내용 끝-->
                <button class="small bggray fl-right mgt20" onclick="toggle2()">
                    LIST
                </button>
            </div>
	</article>
	<!--공지사항 전체 내용 끝-->
</section>

<!--컨텐츠 내용 영역 끝-->
<script id="templateNotice" type="text/template">
<tr onclick="toggle({{no}})">
	<td>{{no}}</td>
	<td class="left {{rnew}}">{{title}}</td>
	<td>{{regDt}}</td>
</tr>
</script>
<script id="templateContents" type="text/template">
	<h2>
	{{title}}
	<span class="fl-right">{{regDt}}</span>
	</h2>
	<!--공지사항 내용 시작-->
	<div class="notice line">
	{{contents}}
	</div>
	<!--공지사항 내용 끝-->
	<button class="small bggray fl-right mgt20" onclick="toggle2()">
		LIST
	</button>
</script>
<script>
var size = ${size};
var num = 0;
var contentsArray;
var time = ${time};

$(document).ready(function() {
	
	var window;		// 화면에 대한 변수
	
	window = Pu.init({
		total:size,						// 총 데이터 수
		count:10,						// 한 페이지에 보여질 최대 데이터 수	
		pageCount:5,					// block 안의 page 갯수
		draw:notice,					// 화면 그릴 function
		paginate:'.pagination',			// page 그릴 selector
		initCllBck:true,				// boolean 값 (미입력시 false)
		initEventFun:notice
	});	
});
function toggle(no) {	
	$("#notice").hide();
	$("#content").show();
	num = no;
	var innerHtml = '';
	var template = $('#templateContents').html();	
		$(contentsArray.list).each(function() {

			if(num == this.no) {
				innerHtml += template.replace(/{{title}}/g, this.title)
				 					 .replace(/{{regDt}}/g, getCstmFrmt(this.regDt, "yyyyMMddHHmmss", "yyyy-MM-dd HH:mm:ss"))
				 					 .replace(/{{contents}}/g, this.contents);
				$('#content').html(innerHtml);
				return false;
			}			
		})			
}
function toggle2() {
	$("#notice").show();
	$("#content").hide();
}

function notice(i, done) {
	var pagenum = {pageNum:i};
	var innerHtml = '';
	var noticeDate;			// 시간을 제외한 공지의 날짜 집어널곳
	ajaxOption({url:'/coinis/notice/list', 
				data:pagenum, 
				success: function(result){
				
				var template = $('#templateNotice').html();

					$(result.list).each(function() {
						
						noticeDate = (this.regDt).substring(0,8);

							innerHtml += template.replace(/{{no}}/g, this.no)
												 .replace(/{{no}}/g, this.no)
											 	 .replace(/{{rnew}}/g, time == noticeDate ? 'new' : '')
												 .replace(/{{title}}/g, this.title)
												 .replace(/{{regDt}}/g, getCstmFrmt(this.regDt, "yyyyMMddHHmmss", "yyyy-MM-dd HH:mm:ss"));
						
					})
					$('tbody').html(innerHtml);
					contentsArray = result;

				}, done:done
				
	})

}
</script>