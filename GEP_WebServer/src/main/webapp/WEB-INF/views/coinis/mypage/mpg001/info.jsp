<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/taglib/taglib.jsp" %>


<section>
        <!--마이페이지 회원 정보 전체 내용 시작-->
        <article class="bgwhite">

            <h1 class="sub-title">
                <ul>
                    <li>My page</li>
                    <li>My profile</li>
                </ul>
            </h1>
            <div class="content-item">
                <h1><strong>회원정보</strong></h1>
                <!--회원정보 테이블 시작-->
                <div class="tblType01">
                    <table>
                        <colgroup>
                            <col style="width: 200px">
                            <col>
                        </colgroup>
                        <tbody>
                            <tr>
                                <th>Email ID</th>
                                <td class="txt-gray">${MemberProfileInfo.user_id}</td>
                            </tr>
                            <tr>
                                <th id="auth_level" data-level="${MemberProfileInfo.auth_level}">Authentication level</th>
                                <td>
                                    <div name="lv1" class="lv1-box now"> <!--레벨2를 인증했다면 레벨1은 done-->
                                        <div>LV. 1</div>
                                        <div>이메일 인증</div>
                                        <div>
                                            <button>인증완료</button> <!--완료된 레벨버튼은 클래스가 없음-->
                                        </div>
                                    </div>
                                    <div name="lv2" class="lv2-box"> <!--현재 레벨에는 now를...-->
                                        <div>LV. 2</div>
                                        <div>SMS / OTP Authentication </div>
                                        <div>개인 : 휴대폰 본인인증</div>
                                        <div>법인 : 담당자 휴대폰 인증</div>
                                        <!--보안인증 하기 버튼-->
                                        <div>
                                            <button class="bgblue" onclick="location.href='/coinis/securitySetup';">Security Authentication</button>
                                        </div>
                                        
                                        <!--보안인증 했을 경우 -->
                                        <!--<div>
                                            <button class="normal">SMS</button>
                                            <button class="normal bgblue">OTP</button>
                                        </div>-->
                                    </div>
                                    <div name="lv3" class="lv3-box">
                                        <div>LV. 3</div>
                                        <div>개인 : 본인 신분증</div>
                                        <div>법인 : 담당자 및 대표자 신분증</div>
                                        <div>
                                            <button class="txt-white" >ID Card</button> <!--비활성화 버튼일경우-->
                                        </div>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <th>Daily Withdrawal Limit</th>
                                <td>${MemberProfileInfo.daily_limit_qty} ${MemberProfileInfo.coin_symbolic_nm}</td>
                            </tr>
                            <tr>
                                <th>로그인 비밀번호</th>
                                <td>
                                    <!-- <span></span> -->
                                    <button class="normal bggray" onclick="location.href='/coinis/pwChange';">Change Password</button>
                                </td>
                            </tr>
                            <c:if test="${MemberProfileInfo.auth_means_cd != 3}">
	                             <tr>
	                                <th>보안 인증 수단 선택</th>
	                                <td>
	                                    <div class="rd-box" id="rd-box-auth" onchange="authMeansCD()">
	                                    	<c:if test="${MemberProfileInfo.sms_use_yn == 1}">
		                                        <input type="radio" name="radio" id="ex_rd1" value="2" <c:if test="${MemberProfileInfo.auth_means_cd == 2}">checked="checked"</c:if>>
		                                        <label for="ex_rd1">SMS</label>
		                                    </c:if>
	                                        <c:if test="${MemberProfileInfo.otp_yn == 1}">
		                                        <input type="radio" name="radio" id="ex_rd2" value="1"<c:if test="${MemberProfileInfo.auth_means_cd == 1}">checked="checked"</c:if>>
		                                        <label for="ex_rd2">OTP</label>
		                                    </c:if>
	                                    </div>
	                                </td>
	                            </tr>
                            </c:if>
                            <tr>
                                <th>마케팅 수신동의</th>
                                <td>
                                    <div class="rd-box" id="rd-box-mrkt" onchange="mrkt_agree()">
                                        <input type="radio" name="agree" id="agree1" value="1" <c:if test="${MemberProfileInfo.mrkt_agree_yn == 1}">checked="checked"</c:if>>  
                                        <label for="agree1">동의</label>
                                        <input type="radio" name="agree" id="agree2" value="0" <c:if test="${MemberProfileInfo.mrkt_agree_yn == 0}">checked="checked"</c:if>>  
                                        <label for="agree2">미동의</label>
                                        <!-- <button class="small bggray">Save</button> -->
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <th>피싱차단 코드</th>
                                <td>
                                	<c:choose>
                                		<c:when test="${MemberProfileInfo.fish_anti_code_yn != 1}">
                                    		<button class="normal bggray" onclick="location.href='/coinis/antiPhising';">Setting</button>
                                    	</c:when>
                                    	<c:otherwise>
                                    		${MemberProfileInfo.fish_anti_code_val}
                                    	</c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                            

                        </tbody>
                    </table>

                    
                </div>
                <!--회원정보 테이블 끝-->

                <!--마지막 접속 정보 테이블 시작-->
                <div class="tblType02">
                    <h1><strong>마지막 접속 정보</strong></h1>
                    <table>
                        <colgroup>
                            <col>
                            <col>
                            <col>
                            <col>
                        </colgroup>
                        <thead>
                            <tr>
                                <th>접속일시</th>
                                <th>접속브라우저</th>
                                <th>접속 IP</th>
                                <th>접속 위치</th>
                            </tr>
                        </thead>
	                       <tbody id="log">
						       <tr>
						           <td>${MemberProfileInfo.last_login_dt}</td>
						           <td>${MemberProfileInfo.last_login_app_ver}</td>
						           <td>${MemberProfileInfo.last_login_ipaddr}</td>
						           <td>Seoul, Republic of Korea</td>
						       </tr>
						    </tbody>
                    </table>

          
                </div>
                <!--마지막 접속 정보 테이블 끝-->

            </div>

        </article>
        <!--마이페이지 회원정보 전체 내용 끝-->
    </section>
    
    
    
    
    
<script>

$(document).ready(function() {
	
	var level = $('#auth_level').data('level');
		
	// level 1
	if ( level == 1 )
		// $('.txt-white').prop("disabled", true);
		$('.txt-white').off('click');
	// level 2
	else if ( level == 2 ) {
		$('div[name="lv1"]').removeClass('now');
		$('div[name="lv1"]').addClass('done');
		$('div[name="lv2"]').addClass('now');
		$('div[name="lv3"]').find('button').removeClass('txt-white');
		$('div[name="lv3"]').find('button').addClass('bgblue');
		$('div[name="lv3"]').click(function() {
			location.href='/coinis/idSetup';
		})
	}
	// level 3
	else if ( level == 3 ) {
		$('div[name="lv1"]').removeClass('now');
		$('div[name="lv1"]').addClass('done');
		$('div[name="lv2"]').addClass('done');
		$('div[name="lv3"]').addClass('now');
	}

});

function authMeansCD() {
	
	var p = {code : $('#rd-box-auth > input:checked').val() };
	
	ajax('/coinis/info/updateAuthMeansCD', p, function(result) {
		
		if (result.rtnCd != 0)
			lrt.client(result.rtnMsg);
	})
	
}

function mrkt_agree() {
	
	var p = {code : $('#rd-box-mrkt > input:checked').val() };

	ajax('/coinis/info/updateMarketingAgree', p, function(result) {

		if (result.rtnCd != 0)
			lrt.client(result.rtnMsg);
	})
	
	
}


</script>