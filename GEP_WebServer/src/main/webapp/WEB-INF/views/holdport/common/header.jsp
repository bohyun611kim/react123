<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/taglib/taglib.jsp" %>


<c:choose>
	<c:when test="${not empty sessionScope.userInfo}">
	<div class="header" id="like_header" sign="1"></div>
	</c:when>
	<c:otherwise>
	<div class="header" id="like_header" sign="0"></div>
	</c:otherwise>
</c:choose> 

<script type="module" src="/resources/js/react/menu_bundle.js"></script>