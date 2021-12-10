<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	
    <title>Coinis</title>
	
    <link rel="shortcut icon" href="/resources/path/coinis.ico">
    <link rel="stylesheet" type="text/css" href="/resources/css/jquery.mCustomScrollbar.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/uiGlobal.css?v=<spring:message code="global.uiGlobal.css.version"/>">
	
    <script src="/resources/js/jquery/jquery-3.3.1.js"></script>
    <script src="/resources/js/jquery/jquery-ui.js"></script>
    <script src="/resources/js/jquery/jquery.mCustomScrollbar.concat.min.js"></script>
    <script src="/resources/js/jquery/slick.js"></script>
    <script src="/resources/js/eventsource/eventsource.js"></script>
    
    <% String prop = System.getProperty("spring.profiles.active"); %>
    <script src="/resources/js/property/common.property.<%=prop%>.js?v=<spring:message code="global.common.property.js.version"/>"></script>
    <script src="/resources/js/common/common.js?v=<spring:message code="global.common.js.version"/>"></script>
	<script src="/resources/js/common/paging.js?v=<spring:message code="global.paging.js.version"/>"></script>
	<script src="/resources/js/eventsource/sse.js?v=<spring:message code="global.sse.js.version"/>"></script>
	
</head>