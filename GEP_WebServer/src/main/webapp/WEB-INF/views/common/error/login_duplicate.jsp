<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html>
<head>
	<title>Home</title>
<script type="text/javascript">

document.location.href="<%=request.getContextPath() %>/member/mbr001/initailLogIn.do?login_error=two"

</script>	
</head>
<body>
<center>
	<h1>
	<h2>사용자 로그인 중복이 발생 했습니다. </h2>
	<a href="<%=request.getContextPath() %>/member/mbr001/initailLogIn.do">확인</a>
	</h1>
</center>	
</body>
</html>