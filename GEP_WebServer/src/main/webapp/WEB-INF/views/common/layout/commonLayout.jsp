<%@ page contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/taglib/taglib.jsp"%>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page" %>

<!DOCTYPE html>
<html>
	
	<page:applyDecorator name="coinisHead"/>

	<body oncontextmenu="return false" ondragstart="return false">
	
		<page:applyDecorator name="coinisHeader"/>
				
		<decorator:body/>
		
		<page:applyDecorator name="coinisFooter"/>
		<page:applyDecorator name="coinisAlert"/>
		
	</body>
	
</html>
