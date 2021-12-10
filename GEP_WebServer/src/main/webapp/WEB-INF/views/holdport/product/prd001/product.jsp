<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/taglib/taglib.jsp" %>

<%!
	boolean localeMapValid = false;
%>


<!--컨텐츠 내용 영역 시작-->

<main id="content" role="main" class="main" tab='${tab}' no='${no}' />

<!--컨텐츠 내용 영역 끝-->


<script type="module" src="/resources/js/react/product_bundle.js"></script>