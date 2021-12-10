<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="naver-site-verification" content="0e51fcf8dc9a97163b8be94cb28d2b921192aef2"/>
<title></title>
</head>
<body>
<script type="text/javascript">

if(${result.getRtnCd()}==0){
	alert('정상적으로 인증되었습니다.');
	opener.location.href='/site/info?tab=1';
	window.close();
}else {
	alert('본인인증에 실패되었습니다. 잠시후 다시 시도해주십시요.');
	window.close();
}

</script>
</body>
</html>
