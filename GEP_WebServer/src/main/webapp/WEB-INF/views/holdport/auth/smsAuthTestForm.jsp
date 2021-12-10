<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/taglib/taglib.jsp" %>

<main id="content-log" role="main" class="main">
<fieldset style="width:300">

        <h1>Mobile 본인인증</h1>
        <form name="auth" action="/site/auth/sureMobileAuthReq" method="POST">
 <!--        <form name="auth" action="/site/auth/sureMobileAuthReq" method="POST">  -->

            <div>
                <input type="text" name="phoneNo" id="phoneNo" required="" placeholder="전화번호" maxlength="40">
                <button type="submit" id="smsbutton" class="join">인증번호요청</button>
            </div>
            <div>
                <input type="text" name="authNum" id="authNum" required="" placeholder="인증번호" maxlength="40">
                <button type="button" id="authbutton" class="join">인증하기</button>
            </div>

            <input type="hidden" id="browser" name="browser" value="browser">
        	<input type="hidden" id="os" name="os" value="os">
        	<input type="hidden" id="privateIp" name="privateIp" value="privateIp">
        </form>
  </fieldset>
</main>

<script>
//alert("TEST");
</script>
