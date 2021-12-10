<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인 실패</title>
</head>
<body>
<h2>로그인 실패 </h2>
<p>에러 내용 :${errMsg} </p>
<a href="<%=request.getContextPath()%>/member/mbr001/initailLogIn.do">로그인</a>
</body>
</html>