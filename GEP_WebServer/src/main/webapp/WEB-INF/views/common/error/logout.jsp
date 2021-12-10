<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html>
<head>
	<title>Home</title>	
</head>

<body>
<h2>로그아웃 </h2>
<p>사용자 아이디 : ${sessionScope.userInfo.username}</p>
<a href="<%=request.getContextPath()%>/member/mbr001/initailLogIn.do">로그인</a>
</body>
</html> 