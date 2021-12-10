<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="google-site-verification" content="y4_Gharl9WcwHPoYIdrx9mFWJ25Mw_7inMXKIIs0Doo">
	<meta name="description" content="Trade Mining 거래소, 암호화폐, 비트코인, 특허출원, 행동경제학 기반 정책설계, 장기지속가능 투명경영">
	
	<meta name="keywords" content="비타오백, bita500, 비타오백, bitcoin, 비트코인, 암호화폐, Trade Mining 거래소">
	<meta property="og:locale" content="ko_kr">
	<meta property="og:type" content="website">
	<meta property="og:title" content="Bita500 - 암호화폐 거래소의 새로운 기준">
	<meta property="og:site_name" content="Bita500 - 암호화폐 거래소의 새로운 기준">
	<meta property="og:description" content="Trade Mining 거래소, 암호화폐, 비트코인, 특허출원, 행동경제학 기반 정책설계, 장기지속가능 투명경영">
	<meta property="og:image" content="https://bita500.com/resources/path/logo_m.png">
	<meta property="og:url" content="https://bita500.com">

    <title>Bita500</title>
    <!-- <link rel="icon" href="/resources/path/fabicon.png" type="image/x-icon"/> -->
    <link rel="shortcut icon" type="image/png" href="/resources/img/newbita/favicon/favicon.png">

    <link rel="stylesheet" type="text/css" href="/resources/css/uiStyle.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/style.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/jquery.mCustomScrollbar.css">


    <script crossorigin src="https://unpkg.com/react@17/umd/react.production.min.js"></script>
    <script crossorigin src="https://unpkg.com/react-dom@17/umd/react-dom.production.min.js"></script>

    <script src="/resources/js/jquery/jquery-3.3.1.js"></script>
    <script src="/resources/js/jquery/jquery.js"></script>
    <script src="/resources/js/jquery/jquery-ui.js"></script>
    <script src="/resources/js/jquery/slick.js"></script>
    <script src="/resources/js/jquery/jquery.mCustomScrollbar.concat.min.js"></script>
    
    <% String prop = System.getProperty("spring.profiles.active"); %>
    <script src="/resources/js/property/common.property.<%=prop%>.js?v=<spring:message code='yahobit.common.property.js.version'/>"></script>
    <script src="/resources/js/common/common.js?v=<spring:message code='yahobit.common.js.version'/>"></script>
	<script src="/resources/js/common/paging.js?v=<spring:message code='yahobit.paging.js.version'/>"></script>
    <script src="/resources/js/eventsource/eventsource.js"></script>
	<script src="/resources/js/eventsource/sse.js?v=<spring:message code='yahobit.sse.js.version'/>"></script>
    <script src="/resources/js/common/hangul.min.js"></script>

</head>
