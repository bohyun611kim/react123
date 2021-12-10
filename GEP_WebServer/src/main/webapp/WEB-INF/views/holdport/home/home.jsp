<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/taglib/taglib.jsp" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page" %>

<!DOCTYPE html>
<html>
	
	<page:applyDecorator name="holdportHead"/>

	<body>
		<div id="wrap">
		<page:applyDecorator name="holdportHeader"/>
		<page:applyDecorator name="holdportPush"/>

		<main role="main" id="content" cont="<%=request.getParameter("cont")%>"> </main>
		<script type="module" src="/resources/js/react/homepage_bundle.js"></script>

		<page:applyDecorator name="holdportFooter"/>
		<page:applyDecorator name="holdportAlert"/>
	</div>
	</body>

	
</html>

