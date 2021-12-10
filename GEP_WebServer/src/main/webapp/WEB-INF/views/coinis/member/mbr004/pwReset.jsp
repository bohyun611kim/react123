<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/taglib/taglib.jsp" %>

<!--컨텐츠 내용 영역 시작-->
<section>

	<div class="joinWrap">
		<h1>Reset Password</h1>
		<p class="txt-gray mgt20">For security purposes no withdrawals are permitted for 24 hours after modification of security methods.</p>
		<form name="login">
			<div>
				<label for="pw"></label>
				<input type="password" name="pw" id="pw" required="" placeholder="New Password">
			</div>
			<div>
				<label for="pw"></label>
				<input type="password" name="pw" id="re-pw" required="" placeholder="Confirm Password">
			</div>
			<button class="base bgblue">Setting</button>
		</form>
	</div>

</section>
<!--컨텐츠 내용 영역 끝-->