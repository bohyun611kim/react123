<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/taglib/taglib.jsp" %>
<%@ taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="naver-site-verification" content="0e51fcf8dc9a97163b8be94cb28d2b921192aef2"/>
</head>
<body>
<script>
document.addEventListener("DOMContentLoaded", function(){
	var result = ${result.getRtnCd()};

	if(result == 0) {
		alert('인증되었습니다');
	} else {
		alert('${result.getRtnMsg()}');
	}

	location.href='/site/login';
});
</script>
</body>
</html>