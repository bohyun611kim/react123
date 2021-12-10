<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/taglib/taglib.jsp" %>

<div class="popup alert" id="lrtClnt">
	<!-- 팝업내용 : Start -->
	<div class="alert-header">
		<p class="alert-title"></p>
		<a class="top-close">
			<img src="/resources/img/btn-alert-close.png" alt="닫기버튼">
		</a>
	</div>
	<div class="alert-content">
		<p class="alert-message"></p>
	</div>
	<div class="alert-btn">
		<button class="small bggray" id="lrtClntOk">Confirm </button>
	</div>
</div>

<div class="popup alert" id="lrtCnfrm">
	<div class="alert-header">
		<p class="alert-title"></p>
		<a class="top-close">
			<img src="/resources/img/btn-alert-close.png" alt="닫기버튼">
		</a>
	</div>
	<div class="alert-content">
		<p class="alert-message"></p>
	</div>
	<div class="alert-btn">
		<button class="small bggray" id="lrtCnfrmCncl">No</button>
		<button class="small bgblue" id="lrtCnfrmOk">Yes</button>
	</div>
</div>