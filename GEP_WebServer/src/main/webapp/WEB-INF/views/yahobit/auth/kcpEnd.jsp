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
opener.auth_data('${result.getRtnCd()}', '${result.getRtnMsg()}');
window.close();
</script>
</body>
</html>