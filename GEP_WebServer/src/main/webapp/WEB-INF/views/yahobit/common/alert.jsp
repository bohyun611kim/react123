<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/taglib/taglib.jsp" %>

<div class="popup-wrap" id="lrtClnt">
	<div class="popup alert">
		<!-- 팝업내용 : Start -->
		<div class="alert-header">
			<p class="alert-title"></p>
			<a class="top-close"><img src="/resources/img/btn-alert-close.png"></a>
		</div>
		<div class="alert-content">
			<p class="alert-message"></p>
		</div>
		<div class="alert-btn">
			<button class="blue" id="lrtClntOk">확인</button>
		</div>
	</div>
</div>

<div class="popup-wrap" id="lrtCnfrm">
	<div class="popup alert">
		<div class="alert-header">
			<p class="alert-title"></p>
			<a class="top-close"><img src="/resources/img/btn-alert-close.png"></a>
		</div>
		<div class="alert-content">
			<p class="alert-message"></p>
		</div>
		<div class="alert-btn">
			<button class="blue" id="lrtCnfrmOk">예</button>
			<button class="gray" id="lrtCnfrmCncl">아니오</button>
		</div>
	</div>
</div>

<div class="popup-wrap" id="lrtNotice">
	<div class="popup alert" style="width:700px;margin-left:-350px;">
		<div class="alert-header">
			<p class="alert-title">공지사항</p>
			<a class="top-close"><img src="/resources/img/btn-alert-close.png" onclick="$('#lrtNotice').hide();"></a>
		</div>
		<div class="alert-content">
			<table>
				<tr>
					<th style="width:65%;text-align:center;" id="notiTitle"></th>
					<th style="width:30%;padding-right:15px;text-align:right;" id="notiDt"></th>
				</tr>
			</table>
			<p class="alert-message" id="notiContents"></p>
		</div>
		<div class="alert-btn">
			<button class="blue" onclick="$('#lrtNotice').hide();">확인</button>
		</div>
	</div>
</div>

<div class="popup-wrap" id="loading" style="background-image: url('/resources/img/yaho/yaho_loading.gif'); background-repeat: no-repeat; background-position: center center; background-size:50px;"></div>