<%@ page contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/taglib/taglib.jsp"%>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page" %>

<!DOCTYPE html>
<html>
	<head>
		<!-- Global site tag (gtag.js) - Google Analytics -->
		<script async src="https://www.googletagmanager.com/gtag/js?id=UA-143446860-1"></script>
		<script>
		  window.dataLayer = window.dataLayer || [];
		  function gtag(){dataLayer.push(arguments);}
		  gtag('js', new Date());

		  gtag('config', 'UA-143446860-1');
		</script>

		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="naver-site-verification" content="0e51fcf8dc9a97163b8be94cb28d2b921192aef2"/>
		<meta name="google-site-verification" content="y4_Gharl9WcwHPoYIdrx9mFWJ25Mw_7inMXKIIs0Doo">
		<meta name="description" content="Trade Mining 거래소, 암호화폐, 비트코인, YEP, 특허출원, 행동경제학 기반 정책설계, 장기지속가능 투명경영">

		<meta name="keywords" content="bita500, 비타오백, 비타500, bitcoin, 비트코인, 암호화폐, Trade Mining 거래소">
		<meta property="og:locale" content="ko_kr">
		<meta property="og:type" content="website">
		<meta property="og:title" content="Bita500 - 암호화폐 거래소의 새로운 기준">
		<meta property="og:site_name" content="Bita500 - 암호화폐 거래소의 새로운 기준">
		<meta property="og:description" content="Trade Mining 거래소, 암호화폐, 비트코인, 특허출원, 행동경제학 기반 정책설계, 장기지속가능 투명경영">
		<meta property="og:image" content="https://bita500.com/resources/path/logo_m.png">
		<meta property="og:url" content="https://bita500.com">

		<title>BITA500 - 암호화폐 거래소의 새로운 기준</title>

		<link rel="icon" href="/resources/path/fabicon.png" type="image/x-icon"/>
		<link rel="stylesheet" type="text/css" href="/resources/css/toastr/toastr.min.css"/>
		<link rel="stylesheet" type="text/css" href="/resources/css/c3/c3.min.css"/>
		<link rel="stylesheet" type="text/css" href="/resources/css/style.css"/>
		<link rel="stylesheet" type="text/css" href="/resources/css/${style}.css?v=<spring:message code="holdport.y_light.css.version"/>" id="styleColor">
		<link rel="stylesheet" type="text/css" href="/resources/css/jquery.mCustomScrollbar.css">

		<script crossorigin src="https://unpkg.com/react@17/umd/react.production.min.js"></script>
		<script crossorigin src="https://unpkg.com/react-dom@17/umd/react-dom.production.min.js"></script>

		<script src="/resources/js/jquery/jquery-3.3.1.js"></script>
		<script src="/resources/js/jquery/jquery-ui.js"></script>
		<script src="/resources/js/jquery/jquery.mCustomScrollbar.concat.min.js"></script>
		<% String prop = System.getProperty("spring.profiles.active"); %>
		<script src="/resources/js/property/common.property.<%=prop%>.js?v=<spring:message code="holdport.common.property.js.version"/>"></script>
		<script src="/resources/js/common/common.js?v=<spring:message code="holdport.common.js.version"/>"></script>

		<%-- <script src="/resources/js/websocket/sockjs-0.3.min.js"></script>
    	<script src="/resources/js/websocket/stomp.min.js"></script>
    	<script src="/resources/js/websocket/activemq.js?v=<spring:message code="holdport.activemq.js.version"/>"></script> --%>

    	<script src="/resources/js/eventsource/eventsource.js"></script>
		<script src="/resources/js/eventsource/sse.js?v=<spring:message code="holdport.sse.js.version"/>"></script>

    	<script src="/resources/js/toastr/toastr.min.js"></script>
    	<script src="/resources/js/tradingview/charting_library/charting_library.min.js"/></script>
		<script src="/resources/js/tradingview/datafeeds/udf/dist/polyfills.js"/></script>
		<script src="/resources/js/tradingview/datafeeds/udf/dist/bundle.js"/></script>
		<script src="/resources/js/c3/d3-4.13.0.min.js"/></script>
		<script src="/resources/js/c3/c3.min.js"/></script>
		<script src="/resources/js/common/hangul.min.js"></script>
		<script src="/resources/js/jquery/slick.js"></script>
		<script src="/resources/js/holdport/exchange/holdport.exchange.chart.js?v=<spring:message code="holdport.exchange.chart.js.version"/>"></script>
		<script src="/resources/js/holdport/exchange/holdport.exchange.js?v=<spring:message code="holdport.exchange.js.version"/>"></script>
	</head>

	<body oncontextmenu="return false" ondragstart="return false" class="day">

	<div id="wrap" class="sc-gzVnrw ibZhkv exchange">
		<page:applyDecorator name="holdportHeader"/>

		<decorator:body/>

		<page:applyDecorator name="holdportFooter"/>
		<page:applyDecorator name="holdportAlert"/>
	</div>

	</body>

</html>