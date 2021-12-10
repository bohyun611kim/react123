<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

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
    <meta http-equiv="Pragma" content="no-cache">
	<meta http-equiv="Expires" content="-1">
	<meta http-equiv="Cache-Control" content="no-cache">
	<meta name="naver-site-verification" content="0e51fcf8dc9a97163b8be94cb28d2b921192aef2"/>
	<!-- <meta name="google-site-verification" content="TfmHUt34OVhcBtLgnENCySPxelFeCoAleYyaKV0giTQ" /> 2019.12.09 YAHOBIT 요청 -->
	<!-- <meta name="google-site-verification" content="j883kiucn-DLD48JXol5gogCsdqsE4MGKPCdEqfAWSk"> 2019.12.10 YAHOBIT 요청 -->
		<meta name="google-site-verification" content="y4_Gharl9WcwHPoYIdrx9mFWJ25Mw_7inMXKIIs0Doo">
	<meta name="description" content="Trade Mining 거래소, 암호화폐, 비트코인, YEP, 특허출원, 행동경제학 기반 정책설계, 장기지속가능 투명경영">
	
	<meta name="keywords" content="Yahobit, 야호비트, 야호빗, Alibit, 알리비트, 알리빗, YEP, 옙, bitcoin, 비트코인, 암호화폐, Trade Mining 거래소">
	<meta property="og:locale" content="ko_kr">
	<meta property="og:type" content="website">
	<meta property="og:title" content="알리비트(Alibit) - 암호화폐 거래소의 새로운 기준">
	<meta property="og:site_name" content="알리비트(Alibit) - 암호화폐 거래소의 새로운 기준">
	<meta property="og:description" content="Trade Mining 거래소, 암호화폐, 비트코인, YEP, 특허출원, 행동경제학 기반 정책설계, 장기지속가능 투명경영">
	<meta property="og:image" content="https://bita500.com/resources/path/logo_m.png">
	<meta property="og:url" content="https://bita500.com">

    <title>알리비트(Alibit) - 암호화폐 거래소의 새로운 기준</title>
	
    <link rel="shortcut icon" href="/resources/path/fabicon.png">
    <link rel="stylesheet" type="text/css" href="/resources/css/uiStyle.css?v=<spring:message code="yahobit.uiStyle.css.version"/>">
    <link rel="stylesheet" type="text/css" href="/resources/css/jquery.mCustomScrollbar.css">
	
    <script src="/resources/js/jquery/jquery-3.3.1.js"></script>
    <script src="/resources/js/jquery/jquery.js"></script>
    <script src="/resources/js/jquery/jquery-ui.js"></script>
    <script src="/resources/js/jquery/slick.js"></script>
    <script src="/resources/js/jquery/jquery.mCustomScrollbar.concat.min.js"></script>
    <% String prop = System.getProperty("spring.profiles.active"); %>
    <script src="/resources/js/property/common.property.<%=prop%>.js?v=<spring:message code="yahobit.common.property.js.version"/>"></script>
    <script src="/resources/js/common/common.js?v=<spring:message code="yahobit.common.js.version"/>"></script>
	<script src="/resources/js/common/paging.js?v=<spring:message code="yahobit.paging.js.version"/>"></script>
    <%-- <script src="/resources/js/websocket/sockjs-0.3.min.js"></script>
    <script src="/resources/js/websocket/stomp.min.js"></script>
    <script src="/resources/js/websocket/activemq.js?v=<spring:message code="yahobit.activemq.js.version"/>"></script> --%>
    <script src="/resources/js/eventsource/eventsource.js"></script>
	<script src="/resources/js/eventsource/sse.js?v=<spring:message code="yahobit.sse.js.version"/>"></script>
    <script src="/resources/js/common/hangul.min.js"></script>
	
</head>