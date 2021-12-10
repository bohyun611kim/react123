<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/taglib/taglib.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
	<title>[후이즈 호스팅] SMS 호스팅 - JSP 모듈 테스트</title>
	<meta name="generator" content="whoisweb">
<!-- 	<meta http-equiv="content-type" content="text/html; charset=euc-kr" />  -->
<style>
	legend { font-size:12px; font-weight:bold; font-family:tahoma }
	td { font-size:12px; font-family:tahoma }
</style>
</head>

<body>
<form method="post" action="/site/auth/whoisMobileAuth">
<fieldset style="width:300">
<legend>문자메시지</legend>
<table>
<tr>
	<td width="100">메시지</td>
	<td><textarea name="sms_msg" rows="5" cols="20" readonly>[후이즈 호스팅] SMS 호스팅 - JSP 모듈 테스트입니다.</textarea></td>
</tr>
<tr>
	<td>받는 사람 번호</td>
	<td><input name="sms_to"></td>
</tr>
<tr>
	<td>보내는 사람 번호</td>
	<td><input name="sms_from" value="1588-4259"></td>
</tr>
<tr>
	<td>예약시간</td>
	<td>
	<!-- 예약시에는 20210617140000 (년월일시분초) 형태로 넣어주세요. -->
	<select name="sms_date">
	<option value="">즉시발송
	<option value="20210617140000">2021년 06월 17일 14시
	</select>
	</td>
</tr>
</table>
</fieldset>
<table>
<tr>
	<td><input type="submit" value="보내기"></td>
</tr>
</table>
</form>
</body>
</html>