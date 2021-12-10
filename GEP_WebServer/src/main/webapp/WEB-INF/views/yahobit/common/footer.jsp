<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/taglib/taglib.jsp" %>

<footer>
	<div class="footer-menu-wrap">
		<div class="footer-menu">
			<a href="/alibit/about?tab=1">회사소개</a>
			<a href="/alibit/about?tab=2">이용약관</a>
			<a href="/alibit/about?tab=3">개인정보처리방침</a>
			<a href="/alibit/support?tab=6">자주하는질문</a>
			<c:choose>
			<c:when test="${sessionScope.userInfo.loginYn == 1}">
			<a href="/alibit/support?tab=7">1:1 문의</a>
			</c:when>
			<c:otherwise>
			<a href="/alibit/login">1:1 문의</a>
			</c:otherwise>
			</c:choose>
			<a href="/alibit/about?tab=7">상장안내</a>
		</div>
	</div>

	<div class="footer-wrap">
		<div class="footer-group">
			<span class="footer-logo">
				<a href="/">alibit</a>
				<br>
				<li>Copyright&copy; 2019 ProsumerLab All rights Reserved</li>
			</span>
		</div>

		<div class="footer-snb">
			<ul>
				<li>
					<p>고객지원</p>
					<p></p>
					<p>평일 10:00 ~ 18:00</p>
					<p>(토,일 공휴일 제외)</p>
					<p>※ 이용 및 장애, 제휴문의는 1:1 문의 이용</p>
					<p>※ 상장문의는 info@ali-bit.com 메일로 접수</p>
				</li>
				<li class="kakao_link">
					<a href="http://pf.kakao.com/_qnxnjT/chat" target="_blank">
						고객지원
						<p><img src="/resources/img/yaho/kakao2.png"></p>
						카카오톡 <br>상담
                    </a>
				</li>
			</ul>
		</div>
	</div>
</footer>

