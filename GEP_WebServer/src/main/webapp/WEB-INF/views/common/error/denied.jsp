<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<p>${errMsg }</p>
<p>${auth }</p>
<div style="float:right;">
	접근 권한이 없습니다.<br>
	담당자에게 문의하여 주십시요.<br>
	<a href="<%=request.getContextPath() %>/member/mbr001/initailLogIn.do">확인</a>
</div>
</body>
</html> 