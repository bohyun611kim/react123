<%@ page contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/taglib/taglib.jsp"%>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page" %>

<!DOCTYPE html>
<html>
	<head>
	    <meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	    <meta name="format-detection" content="telephone=no" />
    	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
	
	    <title>Coinis</title>
	
	    <link rel="shortcut icon" href="/resources/path/favicon.ico">
	    <link rel="stylesheet" type="text/css" href="/resources/css/jquery.mCustomScrollbar.css">
	    <link rel="stylesheet" type="text/css" href="/resources/css/toastr/toastr.min.css">
	    <link rel="stylesheet" type="text/css" id="wizcss" href="/resources/css/uiDark.css?v=<spring:message code="global.uiDark.css.version"/>">
	
	    <script src="/resources/js/jquery/jquery-3.3.1.js"></script>
	    <script src="/resources/js/jquery/jquery-ui.js"></script>
	    <script src="/resources/js/jquery/jquery.mCustomScrollbar.concat.min.js"></script>
	    <script src="/resources/js/toastr/toastr.min.js"></script>
	    <script src="/resources/js/eventsource/eventsource.js"></script>
	    <script src="/resources/js/tradingview/charting_library/charting_library.min.js"/></script>
		<script src="/resources/js/tradingview/datafeeds/udf/dist/polyfills.js"/></script>
		<script src="/resources/js/tradingview/datafeeds/udf/dist/bundle.js"/></script>


		<% String prop = System.getProperty("spring.profiles.active"); %>
		<script src="/resources/js/property/common.property.<%=prop%>.js?v=<spring:message code="global.common.property.js.version"/>"></script>
		<script src="/resources/js/common/common.js?v=<spring:message code="global.common.js.version"/>"></script>		
		<script src="/resources/js/eventsource/sse.js?v=<spring:message code="global.sse.js.version"/>"></script>
		<script src="/resources/js/coinis/exchange/global.exchange.chart.js?v=<spring:message code="global.exchange.chart.js.version"/>"></script>
		<script src="/resources/js/coinis/exchange/global.exchange.js?v=<spring:message code="global.exchange.js.version"/>"></script>
	</head>

	<body oncontextmenu="return false" ondragstart="return false">
	
		<decorator:body/>
		
		<page:applyDecorator name="coinisAlert"/>
		
	</body>
	
</html>