<%@ page contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/taglib/taglib.jsp"%>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page" %>

<!DOCTYPE html>
<html>
	
	<page:applyDecorator name="yahobitHead"/>

	<body>
	
		<page:applyDecorator name="yahobitHeader"/>
		<page:applyDecorator name="yahobitPush"/>

		<decorator:body/>
		
		<page:applyDecorator name="yahobitFooter"/>
		<page:applyDecorator name="yahobitAlert"/>
		
	</body>
	
</html>