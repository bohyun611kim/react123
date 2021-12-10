<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/taglib/taglib.jsp" %>

<script id="ACCESS_LOG" type="template/text">
<tr>
	<td class="alCenter">{{dateTime}}</td>
	<td class="alCenter">{{os}}</td>
	<td class="alCenter">{{browser}}</td>
	<td class="alCenter">{{ipAddr}}</td>
</tr>
</script>

<script id="API_LIST" type="template/text">
<tr data-seq="{{seqNo}}">
	<td>
		<p class="ellips-group">
			<span class="text-ellips alLeft">{{clientId}}</span>
			<button class="copy">복사</button>
		</p>
	</td>
	<td>
		<p class="ellips-group">
			<span class="text-ellips">{{secretKey}}</span>
			<button class="copy">복사</button>
		</p>
	</td>
	<td class="alCenter">{{createDt}}</td>
	<td class="alCenter" data-data="{{apiNo}}">
		<button class="showApiDetail">API보기</button>
	</td>
	<td class="alCenter">
		<label class="switch-button active">
			<input type="checkbox" checked="checked">
		</label>
	</td>
	<td>
		<button class="delApi">삭제</button>
	</td>
</tr>
</script>


<!--  다국어 코드 시작  -->
<%!
	boolean localeMapValid = false;
	HashMap<String, String> localeMap = null;
%>

<%
	try {

		String locale = (String) request.getAttribute("locale");
		localeMap = new HashMap<String,String>();
	
		if(locale.equalsIgnoreCase("ko")) {

			localeMap.put("_L_MY01","고객지원");
		
			
			localeMapValid = true;
	
		} else if(locale.equalsIgnoreCase("en")) {

			localeMap.put("_L_MY01"," ");
		
    

			localeMapValid = true;
		} else {
			localeMap.put("_L_MY01"," ");
      

			localeMapValid = true;
    }
	} catch(Exception e) {

	}
%>

<%!
	public String getLocaleString(String key)
	{
		if(localeMapValid == false || localeMap == null) {
			return key;
		} else {
			String value = localeMap.get(key);
			if(value != null) {
				return value;
			} else {
				return key;
			}
		}
	}
%>  


<!-- 9일 오후 작업 시작  -->
<section class="main">
	<article class="layoutD">
		<aside>
			<h1 class="menutitle"><%=getLocaleString("_L_MY01")%></h1>
			<ul class="vtab">
				<li><a href="#vtab1" style="cursor:pointer;" class="vselected">회원정보</a></li>
				<li><a href="#vtab2" style="cursor:pointer;">인증센터</a></li>
				<li><a href="#vtab3" style="cursor:pointer;">접속관리</a></li>
				<li><a href="#vtab4" style="cursor:pointer;">Open API 관리</a></li>
			</ul>
		</aside>
		<div class="content">
			<ul class="vpanel">
				<!--회원정보 시작-->
				<li id="vtab1" style="display:none;">
					<h1 class="subtitle">회원정보</h1>
					<table class="table1 type01 type02">
						<tbody>
							<tr>
								<th>이메일</th>
								<td>${info.getUserId()}</td>
							</tr>
							<tr>
								<th>성 명</th>
								<td>${info.getUserName()}</td>
							</tr>
							<tr>
								<th>휴대폰번호</th>
								<c:choose>
									<c:when test="${info.getAuthLevel() eq 1}">
								<td><button type="button" class="red">등록하기</button></td>
									</c:when>
									<c:otherwise>
								<td>${info.getMobile()} <button type="button" class="red" >인증완료</button></td>
									</c:otherwise>
								</c:choose>
							</tr>
							<tr>
								<th>비밀번호 변경</th>
								<td><button type="button" class="pop-password">비밀번호 변경</button></td>
							</tr>
							<!--
							<c:if test="${info.getAuthLevel() ge 2}">
							<tr>
								<th>보안 인증 수단 선택</th>
								<td>
									<input type="radio" id="smsAuth" name="rdo" <c:if test="${info.getAuthMeansCd() eq 2}">checked</c:if> value="2">
									<label for="smsAuth">SMS</label>
									<c:if test="${info.getAuthLevel() ge 3}">
									<input type="radio" id="otpAuth" name="rdo" <c:if test="${info.getAuthMeansCd() eq 1}">checked</c:if> value="1">
									<label for="otpAuth">OTP</label>
									</c:if>
								</td>
							</tr>
							</c:if>
							 -->
							<tr>
								<th>마케팅 수신동의</th>
								<td>
									<input type="radio" id="ex_rd3" name="agreement" class="agreement" <c:if test="${info.getMrktAgreeYn() eq 1}">checked</c:if> value="1">
									<label for="ex_rd3">동의</label>
									<input type="radio" id="ex_rd4" name="agreement" class="agreement" <c:if test="${info.getMrktAgreeYn() eq 0}">checked</c:if> value="0">
									<label for="ex_rd4">미동의</label>

									<!-- <button type="button">저장</button> -->
									<p>▶ 주요 공지사항은 수신 동의 여부와 관계없이 발송됩니다.</p>
								</td>
							</tr>
						</tbody>
					</table>

					<h2 class="tabletitle">나의 추천인</h2>
					<table class="table1 type01 type02">
						<tbody id="recommand">
							<tr>
								<!-- <th>지급완료 회원수</th>
								<td class="recomm">
									<b class="blue"></b>명
								</td> -->


								<th>총 추천 회원수</th>
								<td colspan="2">※ 추천인이 회원가입 후 보안등급 레벨3(OTP)까지 인증한 경우에만 집계됩니다.</td>
								<td class="recomm">
									<b class="blue">0</b>명
								</td>
							</tr>
							<tr>
								<th>추천방법</th>
								<td colspan="3">
									<h6>
										<sapn class="blue">① 추천인 코드</sapn>
										를 회원가입 시 입력
									</h6>
									<h6>
										<span class="gray">추천인 코드</span>
										<b></b>
										<button type="button">복사하기</button>
									</h6>
									<h6>
										<sapn class="blue">② 추천인 코드</sapn>
										가 포함된 가입링크로 회원가입(추천코드 자동 입력)
									</h6>
									<h6>
										<span class="gray">가입링크 </span>
										<b></b>
										<button type="button">복사하기</button>
									</h6>
								</td>
							</tr>
							<!-- <tr>
								<th>추천인 삭제</th>
								<td colspan="3">
									<button type="button">추천인 삭제하기</button>
								</td>
							</tr> -->
						</tbody>
					</table>
				</li>
				<!--회원정보 끝-->

				<!--보안인증 시작-->
				<li id="vtab2" style="display:none;">
					<h1 class="subtitle">인증센터</h1>
					<p class="mylevel">
						<span>회원님</span>의 현재 보안등급은
						<span class="blue"> 레벨${info.getAuthLevel()} </span>입니다.
					</p>

					<c:if test="${info.getAuthLevel() eq 1}">
					<p><span class="blue">휴대폰 본인인증시</span> 암호화폐 출금한도가 상향됩니다.</p>
					</c:if>

					<c:if test="${info.getAuthLevel() eq 2}">
					<p><span class="blue">OTP 인증시</span> 암호화폐 출금한도가 상향됩니다.</p>
					</c:if>

					<c:if test="${info.getAuthLevel() eq 3}">
					<p><span class="blue">신분인증시</span> 암호화폐 출금한도가 상향됩니다.</p>
					</c:if>

					<c:if test="${info.getAuthLevel() eq 4}">
					<p><span class="blue">거주지 인증시</span> 암호화폐 출금한도가 상향됩니다.</p>
					</c:if>

					<dl class="level">
						<c:choose>
							<c:when test="${info.getAuthLevel() eq 1}">
						<dt class="now levellink" onclick="openPage('level1')">
							<p class="blue">레벨1</p>
							<p>이메일 인증</p>
							<p><img src="/resources/img/yaho/level1.png" alt="level1"></p>
							<p>${info.getUserId()}</p>
						</dt>
						<dd class="able levellink" onclick="openPage('level2')">
						<p>레벨2</p>
							<p>휴대폰 본인인증</p>
							<p><img src="/resources/img/yaho/level2.png" alt="level2"></p>
							<p>휴대폰 인증</p>
							<button type="button" class="btnMobile">인증하기</button>
						</dd>
						<dd class="impossble levellink">
							<p>레벨3</p>
							<p>추가인증 사용</p>
							<p><img src="/resources/img/yaho/level3.png" alt="level3"></p>
							<p>OTP 인증</p>
							<button class="gray">인증하기</button>
						</dd>
						<dd class="impossble levellink">
							<p>레벨4</p>
							<p>신분인증</p>
							<p><img src="/resources/img/yaho/level4.png" alt="level4"></p>
							<p>출금한도 상향</p>
							<button class="gray">인증하기</button>
						</dd>
						<dd class="impossble levellink">
							<p>레벨5</p>
							<p>거주지인증</p>
							<p><img src="/resources/img/yaho/level5.png" alt="level5"></p>
							<p>출금한도 상향</p>
							<button class="gray">레벨5 신청</button> <!--비활성화일경우 클래스 gray-->
						</dd>
							</c:when>

							<c:when test="${info.getAuthLevel() eq 2}">
						<dt class="done levellink" onclick="openPage('level1')">
							<p class="blue">레벨1</p>
							<p>이메일 인증</p>
							<p><img src="/resources/img/yaho/level1.png" alt="level1"></p>
							<p>${info.getUserId()}</p>
						</dt>
						<dd class="now levellink" onclick="openPage('level2')">
						<p class="blue">레벨2</p>
							<p>휴대폰 본인인증</p>
							<p><img src="/resources/img/yaho/level2.png" alt="level2"></p>
							<p>${info.getMobile()}</p>
							<button type="button" class="pop-sms" >인증완료</button>
						</dd>
						<dd class="able levellink" onclick="openPage('level3')">
							<p>레벨3</p>
							<p>추가인증 사용</p>
							<p><img src="/resources/img/yaho/level3.png" alt="level3"></p>
							<p>OTP 인증</p>
							<button>인증하기</button>
						</dd>
						<dd class="impossble levellink">
							<p>레벨4</p>
							<p>신분인증</p>
							<p><img src="/resources/img/yaho/level4.png" alt="level4"></p>
							<p>출금한도 상향</p>
							<button class="gray">인증하기</button>
						</dd>
						<dd class="impossble levellink">
							<p>레벨5</p>
							<p>거주지인증</p>
							<p><img src="/resources/img/yaho/level5.png" alt="level5"></p>
							<p>출금한도 상향</p>
							<button class="gray">레벨5 신청</button> <!--비활성화일경우 클래스 gray-->
						</dd>
							</c:when>

							<c:when test="${info.getAuthLevel() eq 3}">
						<dt class="done levellink" onclick="openPage('level1')">
							<p class="blue">레벨1</p>
							<p>이메일 인증</p>
							<p><img src="/resources/img/yaho/level1.png" alt="level1"></p>
							<p>${info.getUserId()}</p>
						</dt>
						<dd class="done levellink" onclick="openPage('level2')">
						<p class="blue">레벨2</p>
							<p>휴대폰 본인인증</p>
							<p><img src="/resources/img/yaho/level2.png" alt="level2"></p>
							<p>${info.getMobile()}</p>
							<button type="button" class="pop-sms">인증완료</button>
						</dd>
						<dd class="now levellink" onclick="openPage('level3')">
							<p class="blue">레벨3</p>
							<p>추가인증 사용</p>
							<p><img src="/resources/img/yaho/level3.png" alt="level3"></p>
							<p>OTP 인증</p>
							<button type="button" class="pop-otp">인증완료</button>
						</dd>

					<!-- 인증 진행중일때 인증진행중으로 표기-->
					<c:if test="${info.getIdVerifiResultCd() ne 1}">
						<dd class="able levellink" onclick="openPage('level4')">
					</c:if>
					<c:if test="${info.getIdVerifiResultCd() eq 1}">
						<dd class="now levellink" onclick="openPage('level4')">
					</c:if>
							<p>레벨4</p>
							<p>신분인증</p>
							<p><img src="/resources/img/yaho/level4.png" alt="level4"></p>
							<p>출금한도 상향</p>
							<c:choose>
								<c:when test="${info.getIdVerifiResultCd() eq 1}">
							<button>인증 진행중</button>
								</c:when>
								<c:otherwise>
							<button>인증하기</button>
								</c:otherwise>
							</c:choose>
						</dd>
						<dd class="impossble levellink">
							<p>레벨5</p>
							<p>거주지인증</p>
							<p><img src="/resources/img/yaho/level5.png" alt="level5"></p>
							<p>출금한도 상향</p>
							<button class="gray">레벨5 신청</button> <!--비활성화일경우 클래스 gray-->
						</dd>
							</c:when>

							<c:when test="${info.getAuthLevel() eq 4}">
						<dt class="done levellink" onclick="openPage('level1')">
							<p class="blue">레벨1</p>
							<p>이메일 인증</p>
							<p><img src="/resources/img/yaho/level1.png" alt="level1"></p>
							<p>${info.getUserId()}</p>
						</dt>
						<dd class="done levellink" onclick="openPage('level2')">
						<p class="blue">레벨2</p>
							<p>휴대폰 본인인증</p>
							<p><img src="/resources/img/yaho/level2.png" alt="level2"></p>
							<p>${info.getMobile()}</p>
							<button type="button" class="pop-sms">인증완료</button>
						</dd>
						<dd class="done levellink" onclick="openPage('level3')">
							<p class="blue">레벨3</p>
							<p>추가인증 사용</p>
							<p><img src="/resources/img/yaho/level3.png" alt="level3"></p>
							<p>OTP 인증</p>
							<button type="button" class="pop-otp">인증완료</button>
						</dd>
						<dd class="now levellink" onclick="openPage('level4')">
							<p class="blue">레벨4</p>
							<p>신분인증</p>
							<p><img src="/resources/img/yaho/level4.png" alt="level4"></p>
							<p>출금한도 상향</p>
							<button>인증완료</button>
						</dd>
						<dd class="able levellink" onclick="openPage('level5')">
							<p>레벨5</p>
							<p>거주지인증</p>
							<p><img src="/resources/img/yaho/level5.png" alt="level5"></p>
							<p>출금한도 상향</p>
							<c:choose>
								<c:when test="${info.getApprovalStatCd() eq 0}">
							<button>인증 진행중</button>
								</c:when>
								<c:otherwise>
							<button>레벨5 신청</button> <!--비활성화일경우 클래스 gray-->
								</c:otherwise>
							</c:choose>
						</dd>
							</c:when>

							<c:otherwise>
						<dt class="done levellink" onclick="openPage('level1')">
							<p class="blue">레벨1</p>
							<p>이메일 인증</p>
							<p><img src="/resources/img/yaho/level1.png" alt="level1"></p>
							<p>${info.getUserId()}</p>
						</dt>
						<dd class="done levellink" onclick="openPage('level2')">
						<p class="blue">레벨2</p>
							<p>휴대폰 본인인증</p>
							<p><img src="/resources/img/yaho/level2.png" alt="level2"></p>
							<p>${info.getMobile()}</p>
							<button type="button" class="pop-sms" onclick="$('#pop-sms').show();">초기화</button>
						</dd>
						<dd class="done levellink" onclick="openPage('level3')">
							<p class="blue">레벨3</p>
							<p>추가인증 사용</p>
							<p><img src="/resources/img/yaho/level3.png" alt="level3"></p>
							<p>OTP 인증</p>
							<button type="button" class="pop-otp" onclick="$('#otpInitInfo').show();">초기화</button>
						</dd>
						<dd class="done levellink" onclick="openPage('level4')">
							<p class="blue">레벨4</p>
							<p>신분인증</p>
							<p><img src="/resources/img/yaho/level4.png" alt="level4"></p>
							<p>출금한도 상향</p>
							<button>인증완료</button>
						</dd>
						<dd class="now levellink" onclick="openPage('level5')">
							<p class="blue">레벨5</p>
							<p>거주지인증</p>
							<p><img src="/resources/img/yaho/level5.png" alt="level5"></p>
							<p>출금한도 상향</p>
							<button>인증완료</button> <!--비활성화일경우 클래스 gray-->
						</dd>
							</c:otherwise>
						</c:choose>
					</dl>

					<div id="level1" class="leveinfo" style="display:none;">
						<h3>이메일 인증</h3>
						<p>회원가입시 레벨1 이메일 인증이 완료 됩니다.</p>
					</div>

					<div id="level2" class="leveinfo" <c:if test="${info.getAuthLevel() ne 1}">style="display:none;"</c:if>>
						<c:choose>
							<c:when test="${info.getAuthLevel() == 1}">
						<div>
							<h3>휴대폰 인증</h3>
							<span>본인 명의의 휴대폰으로 실명, 생년월일, 성별, 전화번호를 인증합니다.</span>

							<button class="blue big btnMobile"> 인증하기</button>
						</div>
							</c:when>
							<c:otherwise>
						<div>
							<h3>휴대폰 인증</h3>
							<p>- 인증 되었습니다.</p>
						</div>
							</c:otherwise>
						</c:choose>
					</div>


					<!--추가인증 otp 시작-->
					<div id="level3" class="leveinfo" <c:if test="${info.getAuthLevel() ne 2}">style="display:none;"</c:if>>
						<h3>추가인증 - OTP 인증</h3>
						<c:if test="${info.getAuthLevel() gt 2}">
						<p>- 인증 되었습니다.</p>
						</c:if>
						<c:if test="${info.getAuthLevel() le 2}">
						<p>- OTP 인증을 설정하시면 로그인, 출금신청시 OTP 추가인증을 통해 보안이 강화됩니다.</p>

						<div class="mgt20">
							<h1> 1. OTP 앱 설치</h1>
							<p>스마트폰의 Play스토어(안드로이드 폰) 또는 App스토어(아이폰) 에서 Google OTP 앱을 검색 후 설치 합니다.</p>
							<div class="mgt20">
								<img src="/resources/img/yaho/img-google.jpg">
								<img src="/resources/img/yaho/img-apple.jpg">
							</div>
						</div>

						<div class="mgt20">
							<h1> 2. OTP 앱 실행 및 키 값 설정</h1>

							<p id="vtab2-level4-otp-key-setting-text">Google OTP 앱을 실행 하신 후 아래의 바코드를 스캔 하거나 키를 입력 하십시오.</p>

							<div class="my-key">
								<span id="vtab2-level4-otp-qrcode" class="my-key-qr">
									<!-- <img src="../img/temp-qrimg.png" alt="qr code"> -->
								</span>
							</div>

							<div class="my-key-txt">
								<span>
									<span id="vtab2-level4-otp-key-intro-text">바코드 스캔을 할 수 없는 경우에는 아래의 키를 OTP 앱에 직접 입력 하세요<br><span id="vtab2-level4-otp-auth-key">${newOtpKey}</span>
									</span>
								</span>
							</div>
						</div>

						<div id="vtab2-level4-otp-auth-div" class="mgt20">
							<h1>3. OTP 인증 하기</h1>
							<p>OTP 앱 화면에 표시된 인증번호 여섯자리를 입력 하세요.</p>
							<table class="type01 mgt20">
								<colgroup>
									<col style="width: 200px">
									<col>
								</colgroup>
								<tbody>
									<tr>
										<th>로그인 비밀번호</th>
										<td><input type="password" id="vtab2-level4-login-password" name=""  class="basic"></td>
									</tr>
									<tr>
										<th>휴대폰 SMS 인증</th>
										<td>
											<input type="text" id="vtab2-level4-sms-auth-code" name=""  placeholder="인증번호 요청후 SMS를 통한 인증번호를 입력하세요">
											<button id="vtab2-level4-sms-auth-code-request-btn">인증번호 요청</button>
										</td>
									</tr>
									<tr>
										<th>OTP 인증번호</th>
										<td><input type="text" id="vtab2-level4-otp-auth-code" name=""  placeholder="OTP 인증번호를 입력하세요"></td>
									</tr>
								</tbody>
							</table>
							<c:if test="${info.getAuthLevel() eq 2}">
							<div class="btn-wrap mgt20">
								<button id="vtab2-level4-auth-request-btn" class="big blue">인증하기</button>
							</div>
							</c:if>
						</div>
						</c:if>
					</div>
					<!--추가인증 otp 끝-->

					<!--신분증인증 시작-->
					<div id="level4" class="leveinfo" <c:if test="${info.getAuthLevel() ne 3}">style="display:none;"</c:if>>
						<form id="authSubmit">

						<c:if test="${info.getIdVerifiMemoCont() ne '' and info.getIdVerifiResultCd() eq -1}">
						<p style="color:red;">${info.getIdVerifiMemoCont()}</p>
						</c:if>

						<c:if test="${info.getUserTypeCd() eq 1}">
						<!--개인시분증 시작-->
						<h3>개인 신분증 인증</h3>
						</c:if>

						<c:if test="${info.getUserTypeCd() eq 2}">
						<!--법인신분증 시작-->
						<h3>법인 신분증 인증</h3>
						</c:if>

						<c:if test="${info.getAuthLevel() ge 4}">
						<p>- 인증 되었습니다.</p>
						</c:if>

						<c:if test="${info.getAuthLevel() ne 4 and info.getIdVerifiResultCd() eq 1}">
						<p>- 인증 진행중입니다.</p>
						</c:if>

						<c:if test="${info.getIdVerifiResultCd() ne 1 && info.getIdVerifiResultCd() ne 0 and info.getAuthLevel() eq 3}">
						<c:if test="${info.getUserTypeCd() eq 1}">
						<p>- 신분인증을 하시고 출금한도 상향 문의주세요.</p>
						<table class="type01 mgt10">
							<colgroup>
								<col style="width: 200px">
								<col>
							</colgroup>
							<tbody>
								<tr>
									<th>성명</th>
									<td><input type="text" name="userName" ></td>
								</tr>
								<tr>
									<th>생년월일</th>
									<td>
										<input type="text" name="calendar" id="Date" required="" placeholder="예) 19880101" maxlength="8">
									</td>
								</tr>
								<!-- <tr>
									<th>주소</th>
									<td><input type="text" name="address" ></td>
								</tr>
								<tr>
									<th>우편번호</th>
									<td><input type="text" name="postal" ></td>
								</tr> -->
								<tr>
									<th>신분증 종류</th>
									<td>
										<div class="rd-box">
											<input type="radio" name="radio" id="identityCard" value="1" checked="checked">
											<label for="identityCard">주민등록증</label>
											<input type="radio" name="radio" id="drivingLicense" value="2">
											<label for="drivingLicense">운전면허증</label>
											<input type="radio" name="radio" id="passport" value="3">
											<label for="passport">여권</label>
										</div>
									</td>
								</tr>
								<tr>
									<th>
										신분증 사진
									</th>
									<td>
										<div class="fileimg">
											<h1>1. 신분증 + 요청사항 메모</h1>
											<label>
												<img id="image_section" src="/resources/img/site/img_level4_info1.png" alt="your image"/>
												<span>Click Upload</span>
												<input type='file' id="imgInput" name="file"/>
											</label>
										</div>

										<div class="fileimg">
											<h1>2. 1번을 들고 찍은 사진</h1>
											<label>
												<img id="image_section1" src="/resources/img/site/img_level4_info2.png" alt="your image"/>
												<span>Click Upload</span>
												<input type='file' id="imgInput1" name="file"/>
											</label>
										</div>
										<p class="mgt10" style="font-size:13px;">
										1. 개인정보(①,②) 가린 요청사항 메모부착 신분증 사진(위 사진 참조)</p>
										<p style="font-size:13px;">2. 메모부착 신분증을 들고 찍은 정면 사진(위 사진 참조)</p>
										<p style="font-size:13px;">※ 신분증은 주민등록증, 운전면허증, 여권 3가지 유효기간내의 신분증만 해당됩니다.</p>
										<p style="font-size:13px;">※ 신분증 사진 촬영시 포트잇 등으로 개인정보를 가리고 사진촬영하세요.(사진 참조, 포토샵 등 이미지 편집프로그램으로 수정 금지)</p>
										<p style="font-size:13px;">※ 첨부파일은 jpg / png 이미지 파일만 첨부가 가능합니다.(4MB 제한)</p>
										<p style="font-size:13px;">※ 제출 서류에 개인정보가 가려져 있지 않은 경우 반려 처리 됩니다.</p>
										<br>
										<!-- <p style="font-size:13px;">※ 추가 한도 상향을 요청하는 경우 추가적인 인증이 더 필요 할 수 있으며, 관리자 승인에 시간이 소요됩니다. 1:1 문의를 통해 문의하세요</p> -->
										<p style="font-size:13px;">※ 자동차운전면허증 : 면허번호, 주민등록번호 뒷 7자리, 주소 가려주세요</p>
										<p style="font-size:13px;">※ 여권 : 여권번호, 기계 판독 영역 가려주세요</p>
									</td>
								</tr>
							</tbody>
						</table>
						<div class="btn-wrap mgb20">
							<button type="button" class="big blue" onclick="uploadPrivateIdCard()">인증하기</button>
						</div>
						</c:if>

						<!--개인신분증 끝-->

						<c:if test="${info.getUserTypeCd() eq 2}">
						<p>- 신분인증을 하시고 출금한도 상향 문의주세요.</p>
						<table class="type01 mgt10">
							<colgroup>
								<col style="width: 200px">
								<col>
							</colgroup>
							<tbody>
								<tr>
									<th>회사명</th>
									<td><input type="text" name="corpName"></td>
								</tr>
								<tr>
									<th>대표자성명</th>
									<td>
										<input type="text" name="representName">
									</td>
								</tr>
								<tr>
									<th>사업자 등록번호</th>
									<td><input type="text" name="corpNo" ></td>
								</tr>
								<!-- <tr>
									<th>회사 주소</th>
									<td><input type="text" name="address" ></td>
								</tr>
								<tr>
									<th>우편번호</th>
									<td><input type="text" name="postal" ></td>
								</tr> -->
								<tr>
									<th>담당자명</th>
									<td><input type="text" name="userName" ></td>
								</tr>
								<tr>
									<th>신분증 종류</th>
									<td>
										<div class="rd-box">
											<input type="radio" name="radio" id="identityCard" value="1" checked="checked" >
											<label for="identityCard">주민등록증</label>
											<input type="radio" name="radio" id="drivingLicense" value="2">
											<label for="drivingLicense">운전면허증</label>
											<input type="radio" name="radio" id="passport" value="3">
											<label for="passport">여권</label>
										</div>
									</td>
								</tr>
								<tr>
									<th>신분증 사진</th>
									<td>
										<div class="fileimg">
											<h1>담당자 신분증 :</h1>
											<label>
												<img id="image_section" src="/resources/img/site/img_otp_info1.png" alt="your image" />
												<span>Click Upload</span>
												<input type="file" name="file" id="imgInput" />
											</label>
										</div>
										<div class="fileimg">
											<h1>대표자 신분증 :</h1>
											<label>
												<img id="image_section1" src="/resources/img/site/img_otp_info1.png" alt="your image" />
												<span>Click Upload</span>
												<input type="file" name="file" id="imgInput1" />
											</label>
										</div>
										<div class="fileimg mgt20">
											<h1>사업자등록증 :</h1>
											<label>
												<img id="image_section1" src="/resources/img/img-id03.jpg" alt="your image" />
												<span>Click Upload</span>
												<input type="file" name="file" id="imgInput2" />
											</label>
										</div>
										<p class="txt-gray mgt10">Only jpg/png files supported and the file should not exceed 4MB</p>
									</td>
								</tr>

							</tbody>
						</table>
						<div class="btn-wrap mgb20">
							<button type="button" class="big blue" onclick="uploadCorpIdCard()">인증하기</button>
						</div>
						</c:if>
						</c:if>
						<!--법인신분증 끝-->
						</form>
					</div>

					<!--거주지인증 시작-->
					<div id="level5" class="leveinfo" <c:if test="${info.getAuthLevel() lt 4}">style="display:none;"</c:if>>
						<div>
							<c:if test="${info.getApprovalStatCd() eq -1}">
							<p style="color:red;">${info.getApprovalProcMemo()}</p>
							</c:if>

							<h3>거주지 인증</h3>
							<c:choose>
								<c:when test="${info.getAuthLevel() ge 5}">
							<p>- 인증 되었습니다.</p>
								</c:when>
								<c:when test="${info.getApprovalStatCd() eq 0}">
							<p>- 인증 진행중입니다.</p>
								</c:when>
								<c:otherwise>
								</c:otherwise>
							</c:choose>

							<c:if test="${info.getApprovalStatCd() ne 0 && info.getApprovalStatCd() ne 1}">
							<p>- 거주지 증명서는 전기세영수증, 수도세영수증, 자동차세, 보험료영수증, 세금납부영수증 중 택1 하여 제출해야 합니다.</p>
							<p>- 거주지 증명서는 발급일로부터 3개월 이내인 경우에만 유효합니다</p>
							<p>- 거주지 인증 후, 출금한도 상향이 가능합니다.</p>
							<!--<button class="blue big"> 레벨5 상향 신청</button>-->
							</c:if>
						</div>

						<c:if test="${info.getApprovalStatCd() ne 0 && info.getApprovalStatCd() ne 1 && info.getAuthLevel() eq 4}">
						<c:if test="${info.getAuthLevel() lt 5}">
						<table class="type01 mgt10">
							<colgroup>
								<col style="width: 200px">
								<col>
							</colgroup>
							<tbody>
								<tr>
									<th>주소</th>
									<td>
										<!--<input type="text" placeholder="증빙서류 상의주소를 정확히 기재해주세요." >-->
										<textarea placeholder="증빙서류 상의주소를 정확히 기재해주세요" id="address"></textarea>
									</td>
								</tr>
								<tr>
									<th>직업</th>
									<td>
										<select id="workType" class="select-box">
											<option value="">직업을 선택해 주세요</option>
											<c:forEach var="item" items="${jobCd}">
											<option value="${item.getCodeNumVal()}">${item.getCodeValNm()}</option>
											</c:forEach>
										</select>
									</td>
								</tr>
								<tr>
									<th>거래의 목적</th>
									<td>
										<input type="text" id="tradePurpose" maxlength="100">
									</td>
								</tr>

								<tr>
									<th>자금의 원천 및 출처</th>
									<td>
										<input type="text" id="moneyOrigin" maxlength="150">
									</td>
								</tr>
								<tr>
									<th>월간 예상 거래 횟수</th>
									<td>
										<input type="text" id="monthlyTradeCnt" maxlength="6">
									</td>
								</tr>
								<tr>
									<th>월간 예상 거래 금액(원화)</th>
									<td>
										<input type="text" id="monthlyTradeAmt" maxlength="18">
									</td>
								</tr>
								<tr>
									<th>보안등급5 확인 사항 동의</th>
									<td>
										<div class="rd-box">
											<input type="radio" name="radio" id="ex_rd9">
											<label for="ex_rd9">동의</label>
										</div>
										<p>
											<!-- ■ 본인은 출금한도를 일 2억원 한도로 상향하는 것에 동의합니다.<br> -->
											<!-- ■ 보안등급 5인증 신청은 암호화폐 출금한도만 상향되는 것으로 입금과는 무관함을 알려드립니다.<br> -->
											■ 본인은 금융사기, 자금세탁 등 불법 행위에 암호화폐 거래를 이용하지 않을 것이며 이러한 행위로 인해 발생할 수 있는 불이익에 대해서는 본인이 감수할 것을 확인합니다.<br>
											<!-- ■ 정확한 서류 제출 후 정상 접수가 확인되면 익일 처리될 예정임을 알려드립니다. -->
											■ 본인은 개인부주의로 인한 해킹 등의 자산손실이 발생 시 모든 책임을 감수할 것을 확인합니다.
										</p>
									</td>
								</tr>
								<tr>
									<th>개인정보 수집 및 이용 동의(필수)</th>
									<td>
										<div class="rd-box">
											<input type="radio" name="radio1" id="ex_rd10">
											<label for="ex_rd10">동의</label>
										</div>
										<p>
											수집 목적 : 본인 확인 및 암호화폐 거래 관계 확인, 암호화폐 거래 관계의 설정·유지·해지, 출금한도 상향 등 서비스 관리 전반에 관한 사항 등<br>
											수집 항목 : 주소, 직업, 거주지 증명 서류 (주민등록초본, 주민등록등본), 거래 정보(거래 목적, 예상 거래 등)<br>
											보유 기간 : 탈퇴 후 5년<br>
											■ 회원은 상담 문의 처리로 개인정보를 제공하는 것에 동의하지 않을 수 있으며, 동의를 거부하시는 경우 상담 문의 처리가 불가합니다.<br>
											■ 위 내용을 반드시 전부 확인하신 뒤 동의하신다면 체크해 주세요.
										</p>
									</td>
								</tr>
								<tr>
									<th>거주지 증명서</th>
									<td>
										<form id="proveResidence">
										<div class="fileimg">
											<h1>1. 거주지 증명서 + 요청사항 메모</h1>
											<label>
												<!-- <img id="image_section" src="/resources/img/img-id04.jpg" alt="your image" /> -->
												<span>Click Upload</span>
												<input type='file' id="imgInput5" name="imgInput5" />
											</label>
										</div>
										<div class="fileimg">
											<h1>2. 1번을 들고 찍은 사진</h1>
											<label>
												<!-- <img id="image_section" src="/resources/img/img-id05.jpg" alt="your image" /> -->
												<span>Click Upload</span>
												<input type='file' id="imgInput6" name="imgInput6" />
											</label>
										</div>
										</form>
										<p class="mgt10" style="font-size:13px;">
											1. 신청내용을 '전체미포함'으로 발급받은 서류에 요청사항 메모부착한 사진(위 사진 참조)<br>
											2. 메모부착 거주지 증명서를 들고 찍은 정면 사진(위 사진 참조)<br>
											※ 거주지 증명서는 발급일로부터 3개월 이내인 경우에만 유효합니다.<br>
											※ 포토샵 등 이미지 편집프로그램으로 수정된 파일은 인증이 불가합니다.<br>
											※ 첨부파일은 jpg / png 이미지 파일만 첨부가 가능합니다.(4MB 제한)<br>
											※ 제출 서류를 전체미포함으로 발급 받지 않아 다른 정보들이 있는 경우 반려 처리 됩니다.<br>
											<!-- ■ 발급일로부터 3개월 이내인 경우에만 유효합니다.<br>
											■ 주민등록번호/외국인등록번호/거소신고번호 뒤 6자리는 표시되지 않아야 하며, 번호 전체가 노출된 서류 업로드 시, 거주지 인증이 불가합니다.<br>
											■ 주민등록초본/외국인등록사실증명서/국내거소신고증명서 상에 표기된 '가족사항 및 이전 거주 주소'를 가려 주셔야 하며 '가족사항 및 이전 거주 주소'가 노출된 서류 업로드 시, 거주지 인증이 불가합니다.<br>
											■ 상단에 기재하신 주소와 증빙서류 상의 주소가 일치하지 않을 경우 거주지 인증이 불가합니다.<br>
											■ '세대주 및 세대주와의 관계'가 노출된 주민등록초본은 접수 불가합니다. '세대주 및 관계' 가 노출되지 않은 주민등록초본을 발급받으셔야 합니다. -->
										</p>
									</td>
								</tr>
							</tbody>
						</table>
						<div class="btn-wrap mgt20">
							<button class="big blue" onclick="uploadProveResidence();">인증하기</button>
						</div>
						</c:if>
						</c:if>
					</div>
					<!--거주지인증 끝-->
				</li>
				<!--보안인증 끝-->

				<!--접속관리 시작-->
				<li id="vtab3" style="display:none;">
					<h1 class="subtitle">접속관리</h1>
					<div class="dw-history">
						<!-- 조회 옵션 : Start -->
						<div class="mgt20">
							<!--<span class="option-title">종목</span>-->
							<div class="calendar">
								<input type="text" name="calendar" id="startDate" required="" value="${now}" readonly="readonly">
								<span>~</span>
								<input type="text" name="calendar" id="endDate" required="" value="${now}" readonly="readonly">
								<button type="button" id="logSearch">조회</button>
							</div>
						</div>

						<!-- //조회 옵션 : End -->
						<!-- 조회 건수 표시 : Start -->

						<p class="case-info">총 <b class="blue" id="size">0</b>건의 자료가 조회 되었습니다</p>

						<!-- //조회 건수 표시 : End -->
						<!-- 데이터 영역 : Start -->
						<div class="mgt30">
							<table class="line">
								<colgroup>
									<col>
									<col>
									<col>
									<col>
								</colgroup>
								<thead>
									<tr>
										<th class="alCenter">접속시간</th>
										<th class="alCenter">OS</th>
										<th class="alCenter">접속프로그램</th>
										<th class="alCenter">IP주소</th>
									</tr>
								</thead>
								<tbody id="accessLog">
								</tbody>
							</table>
							<!-- 페이징 : Start -->
							<ul class="pagination">
							</ul>
							<!-- //페이징 : End -->
						</div>
						<!-- //데이터 영역 : End -->
					</div>
				</li>
				<!--접속관리 끝-->

				<!--Open API 관리 시작-->

				<li id="vtab4">
					<h1 class="subtitle">Open API 관리</h1>

					<p class="mgt40">- API를 활성화 하시면 API를 이용한 BITA500 거래소와의 시스템 연동이 가능합니다.</p>
					<p>- API를 이용한 연계 시스템 개발 시 할당 된 Secret Key를 사용합니다.</p>
					<p>- API 이용을 위해서는 약관동의와 휴대폰 또는 OTP 추가인증이 필요합니다.</p>

					<!-- 이용약관 : Start -->
					<div>
						<h2 class="tabletitle">이용약관</h2>
						<div class="dis-block">
							<div class="text-area-wrap mCustomScrollbar" data-mcs-theme="dark">
								<div class="text-area">
									<strong>제1조 (목적)</strong>

									<p>이 약관은 (주)오병이어홀딩스 (이하 "회사")이 제공하는 BITA500 거래소내 API서비스(이하 "API 서비스")의 이용조건 및 절차에 관한 회사와 회원간의 권리 의무 및 책임사항, 기타 필요한 사항을 규정함을 목적으로 합니다.</p>
									<strong>제2조 (약관의 명시, 변경 및 변경의 통지 등)</strong>
									<ol>
										<li>이 약관은 API서비스 가입관련 화면이나 기타의 방법으로 사용자에게 공지하고, 회원이 약관의 내용에 대해 동의를 함으로써 이용계약 체결을 청약하고 회사가 검토 후 승낙함으로써 체결됩니다.</li>
										<li>회사는 관련법을 위배하지 않는 범위에서 이 약관을 개정할 수 있습니다.</li>
										<li>회사가 약관을 개정할 경우에는 적용일자 및 개정사유를 명시하여 현행약관과 함께 회사 사이트의 초기화면이나 팝업화면 또는 공지사항란에 그 적용일자 10일(영업일 기준) 이전부터 적용일자 전일까지 공지합니다.</li>
										<li>회사가 전항에 따라 개정약관을 공지 통지하면서 회원에게 영업일 기준 5일간의 기간 내에 의사표시를 하지 않으면 의사표시가 표명된 것으로 본다는 뜻을 명확하게 고지 또는 통지하였음에도 회원이 명시적으로 거부의 의사표시를 하지 아니한 경우 회원이 개정약관에 동의한 것으로 봅니다.</li>
										<li>회원이 개정약관의 적용에 동의하지 않는다는 명시적 의사를 표명한 경우 회사는 개정 약관의 내용을 적용할 수 없으며, 이 경우 회원은 이용계약을 해지할 수 있습니다. 개정된 약관의 적용 없이 회원에게 회사가 원활한 API서비스를 제공할 수 없는 경우에는 회사 또한 당해 회원과의 이용계약을 해지할 수 있습니다.</li>
									</ol>
									<strong>제3조 (용어의 정의)</strong>
									<ol>
										<li>이 약관에서 사용하는 용어의 정의는 다음과 같습니다.
											<ul>
												<li>회원 : BITA500 회원으로서 이 약관에 동의한 후 회사가 검토하여 최종 승낙함으로써 서비스를 제공받는 자를 말합니다.</li>
												<li>API : Application Programing Interface의 약자로, 회원이 BITA500 거래소의 시세, 주문, 지갑등의 서비스를 회원이 운영하는 웹사이트, 어플리케이션등에서 사용할 수 있도록 회사가 제공하는 스크립트를 말합니다.</li>
												<li>API 서비스 : 회원이 자발적인 참여를 통해 자유롭게 정보를 공유하고 창조적인 서비스를 생산할 수 있도록 돕기 위해 회사가 일정 한도 내에서 API를 제공하고 운영하는 것을 말합니다.</li>
												<li>인증키 : 관련 시스템이 API 서비스 이용 승낙을 받은 회원임을 식별할 수 있도록 회사가 회원에게 할당하는 고유한 값을 말합니다.</li>
												<li>이용자 : 회원이 회사로부터 발급받은 인증키를 이용하여 제작한 회원프로그램을 이용하는 자를 말합니다.</li>
												<li>회원프로그램 : 회원이 회사로부터 발급받은 인증키를 이용하여 이용자에게 제공하는 PC, 모바일, 각종 유무선 전자기기용 어플리케이션 및 웹사이트를 의미합니다.</li>
											</ul>
										</li>
										<li>이 약관에서 사용하는 용어 중 제1항에서 정하지 아니한 것은 API서비스 안내 또는 BITA500 이용약관, 관계 법령에서 정하는 바에 따르며, 그 외에는 일반 관례에 따릅니다.</li>
									</ol>
									<strong>제4조 (API 서비스의 제공)</strong>
									<ol>
										<li>회원은 API 서비스를 이용함에 있어 회사가 정한 소정의 등록절차를 통해 본인 인증을 하고 회사의 승인을 거쳐 인증키를 발급 받아야 하며, 발급 받은 인증키를 이용하여 API 서비스를 제공 받을 수 있습니다.</li>
										<li>인증키 발급 신청 시 회원이 필수 입력해야 하는 정보들은, 회사가 그 정보들을 통해 본인인증과 해당 회원프로그램의 형태를 확인할 수 있도록 성실히 입력되어야 하며, 제공된 정보로 확인하기 어려운 경우 회사는 추가 정보 제공을 요구할 수 있습니다.</li>
										<li>회사는 회원이 인증키를 발급 받은 후에도 휴대전화등을 통한 본인인증 절차를 추가로 요구할 수 있습니다. 이 경우 회사는 회원이 인증키 발급 시 등록한 이메일로 연락을 취하며, 최초 메일 발송일로부터 30일내 회원의 응답이 없거나 인증절차가 이행되지 않는 경우 해당 인증키의 이용을 일시 정지할 수 있습니다.</li>
										<li>회사는 회원의 자격에 따라 API 서비스를 차등 제공할 수 있습니다.</li>
										<li>회원은 API 서비스를 이용한 회원프로그램을 제작함에 있어 저작권 위반 같은 불법적인 내용을 포함하거나 선정적, 폭력적, 혐오적인 내용을 포함하거나 비도덕적, 불법적인 내용을 결합 또는 연계하거나 인접해 구성하지 않아야 합니다.</li>
									</ol>
									<strong>제5조 (API 서비스의 제한 등)</strong>
									<ol>
										<li>회원은 서비스를 이용함에 있어 아래 각 호에 해당하면 회사가 정한 방법과 절차를 따라야 합니다.
											<ul>
												<li>회원 등급에 따른 허용 트래픽을 초과로 사용한 경우</li>
												<li>회원프로그램 등의 이용에 직접 과금을 하거나 금전적/비금전적 대가를 받는 경우</li>
												<li>기타 회사가 이용을 제한하거나 금지하는 경우</li>
											</ul>
										</li>
										<li>회사는 서비스의 범위를 제한하거나 별도의 이용 가능 시간 또는 이용 가능회수를 지정하는 등의 방법으로 서비스를 운용할 수 있습니다.</li>
										<li>회원은 회사가 정한 서비스의 범위를 초과하여 이용하고자 할 경우 회사가 규정하는 제휴 절차를 거쳐야 합니다.</li>
										<li>회사는 회원이 서비스를 이용함에 있어 법령 또는 본 약관을 위반한 경우, 서비스에 대한 불법적인 해킹, 역공학, 비정상적인 방식을 통한 어뷰징, 네트웍 사용 초과 등의 시도를 하는 경우 즉시 회원의 서비스 이용을 정지시킬 수 있습니다.</li>
										<li>아래 각 호에 해당하는 경우 회사는 사전 공지 후 서비스를 일시 중단할 수 있습니다. 다만 시스템 장애, 천재지변 또는 이에 준하는 불가항력으로 인한 통제불능 상황의 경우에는 사후 공지로 대체합니다.
											<ul>
												<li>시스템 정기점검, 증설 및 교체를 위해 회사가 지정한 날</li>
												<li>서비스를 제공함에 있어 기술적 결함이 발견되는 경우</li>
												<li>신규 API 서비스를 추가하는 경우</li>
											</ul>
										</li>
										<li>회사는 6개월간 한 번도 사용되지 않은 인증키에 대해 이용을 정지시킬 수 있습니다. 이 경우 회사는 이용정지에 앞서 회원의 인증키 사용 여부를 확인하기 위해 등록된 이메일로 연락을 취하며, 최초 메일 발송일로부터 영업일 기준 10일내에 회원의 응답이 없는 경우 즉시 해당 인증키의 이용을 정지할 수 있습니다.</li>
										<li>회사가 회원에게 특정 정보 제거를 요청한 경우, 회원은 24시간 내에 해당 정보를 회원프로그램에서 제거해야 합니다.</li>
									</ol>
									<strong>제6조 (지적재산권의 귀속)</strong>
									<ol>
										<li>회원이 제작한 회원프로그램에 대한 저작권은 회원에게 있습니다.</li>
										<li>회사가 제공하는 API 및 관련 프로그램의 저작권은 회사에 있으며, 회사는 회사의 정책 및 운영의 필요상 API 또는 API 서비스를 변경하거나 중단할 수 있습니다.</li>
										<li>API 서비스 이용으로 인해 API 서비스를 이용한 주문, 시세, 송금등의 정보에 대한 저작권 또는 사용권을 취득하는 것은 아닙니다.</li>
										<li>회원은 API 서비스를 통해 실시간으로 거래소와 연동하여 시세를 조회하거나 거래 주문등을 요청할 수 있을 뿐이며, 결과를 복제 또는 저장하거나 제 3자의 서비스로 전송할 수 없습니다.</li>
										<li>회원은 API 서비스를 이용한 회원프로그램에 "BITA500 거래소 API 사용" 또는 "이 제품은 BITA500 거래소 API를 사용하지만 BITA500의 보증을 받거나 인증을 받은 것은 아닙니다." 라는 고지 사항을 눈에 띄는 곳에 배치해야 합니다.</li>
										<li>회사의 API 서비스 이용 승낙이 회사가 회원에게 회사의 등록상표 또는 저작물에 대한 이용권을 부여하는 것으로 해석될 수 없습니다. 회원은 회사가 특별히 허락한 경우에 한해 본 항에 명시한 회사의 등록상표 또는 저작물을 사용할 수 있으며, 그러한 경우에도 회사의 등록상표 또는 저작물의 이용기준을 준수하여야 하고 임의로 이를 변형하거나 훼손하여서는 안됩니다.</li>
										<li>회사는 프로그램 등의 요청에 따라 회사가 제공하는 정보가 전송되는 경우 광고 또는 광고로 연결되는 페이지 등 회사가 정하는 정보를 함께 전송할 수 있습니다.</li>
										<li>회원은 프로그램 등의 요청에 따라 회사가 제공하는 정보에 표시되는 상표, 광고 등 일체의 내용을 삭제하거나 변경할 수 없습니다.</li>
									</ol>
									<strong>제7조 (회원의 의무)</strong>
									<ol>
										<li>회원은 API 서비스를 비상업적인 목적으로만 이용할 수 있으며, 이용자가 API 서비스 및 인증키를 사용한 회원의 회원프로그램을 이용하는 과정에서 이용자에게 비용을 청구하거나 회원 외 제3자에게 이용자의 개인정보를 제공하는 것을 대가로 회원프로그램을 제공해서는 안됩니다.</li>
										<li>회원은 아래 각 호에 해당하는 행위를 하여서는 아니 됩니다.
											<ul>
												<li>API 서비스 가입을 위한 정보 등을 허위로 기재하는 행위</li>
												<li>회사 API 서비스로 제공받은 정보를 변경하거나 API 서비스를 이용하여 얻은 정보를 회사의 사전 승낙 없이 영리 또는 비영리의 목적으로 복제, 출판, 방송 등에 사용하거나 제 3자에게 제공하는 행위</li>
												<li>회사 혹은 제 3자의 명예를 훼손하거나 지적재산권을 침해하는 행위</li>
												<li>회사의 임직원이나 관계자를 사칭하거나 회사의 브랜드나 서비스를 모방, 도용하는 행위</li>
												<li>회원의 아이디, 비밀번호 등의 개인정보를 해킹, 도용, 거래하는 행위</li>
												<li>정보통신망 이용촉진 및 정보보호 등에 관한 법률 등 관련 법령에 의하여 그 전송 또는 게시가 금지되는 정보(컴퓨터 프로그램 등)를 전송하거나 게시하는 행위</li>
												<li>공공질서 또는 미풍양속에 위배되는 내용의 정보, 문장, 도형, 음성 등을 유포하는 행위</li>
												<li>컴퓨터 소프트웨어, 하드웨어, 전기통신 장비의 정상적인 가동을 방해, 파괴할 목적으로 고안된 소프트웨어 바이러스, 기타 다른 컴퓨터 코드, 파일, 프로그램을 포함하고 있는 자료를 게시하는 행위</li>
												<li>회원은 회사가 제공하는 각종 서비스를 모방하거나 회사의 브랜드를 서비스의 출처표시 용도 외에 무단으로 사용하거나 모방, 도용하여서는 안됩니다.</li>
												<li>현행 법령 및 회사가 정한 약관 및 API 서비스 이용에 관한 규정을 위반하는 행위</li>
											</ul>
										</li>
										<li> 회원은 서비스를 이용한 프로그램 등의 안정적인 운영을 위해 노력해야 합니다.
											<ul>
												<li>회원은 API 서비스의 오류 및 문제점이 발견된 경우 즉시 회사에 이를 알리고, 회사의 API 서비스 개선 조치에 적극 협력해야 합니다.</li>
												<li>회원은 회사가 제공하는 API 및 소프트웨어개발키트(SDK) 등의 기능에 대한 버전 업데이트를 수시로 확인하여야 합니다.</li>
												<li>회원은 서비스 제공 또는 이 약관 위반을 확인하기 위해 회사가 자료 또는 접근권한의 제공 및 관련사실에 대한 소명을 요청하는 경우에는 이에 성실히 임하여야 합니다.</li>
											</ul>
										</li>
										<li>회원은 서비스 받을 권리를 양도 내지 증여하거나 질권의 목적으로 이용할 수 없습니다.</li>
										<li>회원은 인증키 이용 및 관리에 있어 회사가 정한 규칙에 따라야 합니다.
											<ul>
												<li>회원은 발급 받은 인증키를 타인에게 제공, 공개하거나 공유할 수 없으며, 발급 받은 회원 본인에 한하여 이를 사용할 수 있습니다.
												<li>회원은 1개의 인증키만을 발급받아 사용할 수 있으며, 어떠한 경우에도 2개 이상의 인증키를 발급받거나 그러한 시도를 하여서는 안 됩니다.
												<li>회원은 발급받은 인증키를 하나의 회원프로그램에만 이용할 수 있으며, 신청 시 기재한 회원프로그램 정보가 변경되는 경우 관리페이지를 통해 해당 정보를 즉시 수정해야 합니다. 변경사항을 회사에 알리지 않아 발생한 인증키 이용정지 등의 불이익에 대해 회사는 책임지지 않습니다.
												<li>회사는 인증키를 발급함에 있어 이용기간을 지정할 수 있으며, 이용기간을 변경하고자 하는 경우에는 변경 전에 이를 고지하여야 합니다.
												<li>회원이 API 서비스를 이용함에 있어 법령을 위반하거나 회사의 이용약관 또는 서비스 이용기준 등을 위반한 경우 회사는 즉시 인증키의 이용을 정지할 수 있습니다.
											</ul>
										</li>
										<li>회원의 행위에 대한 모든 책임은 회원에게 부담됩니다.</li>
									</ol>
									<strong>제8조 (면책)</strong>
									<ol>
										<li>회사는 약관, 서비스 안내, 기타 회사가 정한 이용기준을 준수하지 않은 이용으로 인한 결과에 대해 책임을 지지 않습니다.</li>
										<li>회사는 API 서비스를 통해 제공한 결과에 대한 신뢰성, 정당성, 적법성등을 보증하지 않습니다.</li>
										<li>API 서비스는 일부 또는 전부 실험적이며 어떤 방법으로든 테스트되지 않은 것도 있습니다. 회사는 API 서비스가 부정확, 오류, 버그 또는 중단이 없거나 안정적이고 정확하고 완전하거나 올바르다고 표현하거나 보증하지 않습니다. API 서비스의 응답이 즉각적으로 이루어짐을 보장하지 않습니다. 회사는 API 서비스에 따른 회원, 이용자 및 기타 제3자에게 발생한 손해에 대하여 책임을 지지 않습니다.</li>
										<li>API 서비스는 회원의 사용환경 등에 따라 정상적으로 동작하지 않거나 회사가 예견하지 못한 오류가 존재할 수 있으며, 회사는 API 서비스의 사용 불능으로 인하여 회원에게 발생한 손해에 대하여 책임을 지지 않습니다.</li>
										<li>회사는 회원이 API 서비스를 이용해 기대하는 수익을 얻지 못하거나 상실한 것에 대해 책임을 지지 않습니다.</li>
										<li>회사는 회원 상호간, 회원과 이용자간, 회원과 제3자 상호간에 회원프로그램을 매개로 발생한 분쟁에 대해 개입할 의무가 없으며, 이로 인한 손해를 배상할 책임도 없습니다. 만일 회원과 분쟁중인 제3자가 회사를 상대로 이의를 제기할 경우 회원은 회사를 자신의 비용과 책임으로 면책시켜야 합니다.</li>
									</ol>
									<strong>제9조 (개인정보의 보호)</strong>
									<ol>
										<li>회사는 API 서비스를 운영함에 있어 회원에 대한 각종 고지사항의 전달, 이용실태에 대한 조사등을 위하여 회원의 연락처등 개인정보를 보관합니다.</li>
										<li>회사는 회원의 정보제공 동의에 따라 직접 회원이 제공한 정보만을 보관하며, 회사의 개인정보보호정책에 따라 이를 철저히 관리합니다.</li>
										<li>회사는 BITA500 거래소API 서비스 제공을 중단하거나 회원이 개인정보 제공 동의를 철회한 경우에는 즉시 회원의 관련 개인정보를 파기합니다.</li>
									</ol>
									<strong>제10조 (이용계약의 해지 및 손해배상)</strong>
									<ol>
										<li>회사는 회원이 약관을 준수하지 않는 경우 API서비스 이용계약을 해지할 수 있습니다. 이 경우 회원의 API 서비스 사용도 중단됩니다.</li>
										<li>회사는 사전 고지 후 어떤 이유에서건 언제든지 API 서비스를 중지할 수 있습니다.</li>
										<li>회원은 언제라도 스크립트 및 인증키를 삭제함으로써 이용계약을 해지할 수 있습니다.</li>
										<li>회원에 의하여 회사에 손해가 발생한 경우 회사는 API서비스 이용계약의 해지와는 별도로 회원에게 손해배상을 청구할 수 있습니다.</li>
										<li>회사는 회원이 API 서비스 내용의 변경 및 종료에 대한 내용을 사전 고지한 기간 안에 확인하지 않을 경우에는 별도 고지 후 본 서비스 이용 계약을 해지할 수 있습니다.</li>
										<li>회사는 회원이 연속하여 6개월 이상 회원프로그램등을 통한 서비스 요청을 하지 않을 경우 서비스의 안정성 확보를 위하여 회원에게 별도 고지 후 본 서비스 이용 정지 또는 이용 계약을 해지할 수 있습니다.</li>
										<li>회원이 제7조를 포함하여 본 약관 상의 의무를 위반한 경우 회사는 사전통보 없이 일방적으로 본 계약을 해지할 수 있고, 이로 인하여 회사에 손해가 발생한 경우 이에 대한 민/형사상 책임을 물을 수 있습니다.</li>
									</ol>
									<strong>제11조 (약관 외 준칙 및 재판관할)</strong>
									<ol>
										<li>본 약관에 명시되지 않은 사항은 BITA500 이용약관, 서비스 안내와 이용기준 또는 상관례를 따릅니다.</li>
										<li>API 서비스 이용으로 회사와 회원 간에 발생한 분쟁에 관한 소송은 민사소송법 상의 관할법원에 제소합니다.</li>
										<li>회사와 회원간 제기된 소송은 대한민국법을 준거법으로 합니다.</li>
									</ol>
									<p>
										<br>
										<부칙> 본 약관은 2021년 6월 16일 부터 적용됩니다.</부칙>
									</p>
								</div>
							</div>
							<div class="checks-item bggray" style="width: 100%">
								<input type="checkbox" id="agreeApi" >
								<label for="agreeApi" >
									이용약관을 확인 하였으며 동의합니다
									<span class="blue">(필수)</span>
								</label>
							</div>
						</div>
					</div>
					<!-- //이용약관 : End -->

					<!-- API 활성화 선택 : Start -->
					<div class="mgt20">
						<h2 class="tabletitle">&nbsp;&nbsp;API 활성화 선택</h2>
						<div class="mgt10">
							<span class="checks-item right">
								<input type="checkbox" id="allAgree">
								<label for="allAgree">전체선택</label>
							</span>
						</div>

						<div class="api">
							<table class="line">
								<colgroup>
									<col>
									<col>
									<col>
									<col>
									<col>
									<col>
									<col>
									<col>
								</colgroup>
								<tbody id="apiType">
									<tr>
										<th>시세조회</th>
										<td class="alRight">
											<label class="switch-button"><input type="checkbox" data-no="001"></label>
										</td>
										<th>지갑정보</th>
										<td class="alRight">
											<label class="switch-button"><input type="checkbox" data-no="002"></label>
										</td>
										<th>잔고조회</th>
										<td class="alRight">
											<label class="switch-button"><input type="checkbox" data-no="003"></label>
										</td>
										<th>거래내역조회</th>
										<td class="alRight">
											<label class="switch-button"><input type="checkbox" data-no="004"></label>
										</td>
									</tr>
									<tr>
										<th>주문내역조회</th>
										<td class="alRight">
											<label class="switch-button"><input type="checkbox" data-no="005"></label>
										</td>
										<th>주문</th>
										<td class="alRight" style="border-right:1px solid #ddd;">
											<label class="switch-button"><input type="checkbox" data-no="006"></label>
										</td>
										<!-- <th>암호화폐 출금</th>
										<td class="alRight">
											<label class="switch-button"><input type="checkbox" data-no="007"></label>
										</td>
										<th>KRW출금</th>
										<td class="alRight">
											<label class="switch-button"><input type="checkbox" data-no="008"></label>
										</td> -->
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					<!-- //API 활성화 선택 : End -->

					<!-- IP 주소 제한 설정 : Start -->
					<div class="mgt20">
						 <h2 class="tabletitle">IP 주소 제한 설정</h2>

						  <p>- 등록된 IP 외에는 API 접근이 불가능하도록 설정할 시 등록 합니다</p>

						<div class="mgt10">
							<table class="type01">
								<colgroup>
									<col style="width: 180px;">
									<col>
								</colgroup>
								<tbody>
									<tr>
										<th>접근가능 IP 주소</th>
										<td>
											<input type="text" id="accessIp" name="accessIp" maxlength="15">
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					<!-- //IP 주소 제한 설정 : End -->

					<!-- 보안인증 하기 : Start -->
					<div class="mgt20">
						<h2 class="tabletitle">보안인증 하기</h2>

						<p>- API 사용을 위한 Key 발급을 위해 보안 인증을 수행 하십시오</p>

						<div class="mgt10">
							<table class="type01">
								<colgroup>
									<col style="width: 180px;">
									<col>
								</colgroup>
								<tbody id="apiAuthType">
									<c:choose>
										<c:when test="${info.getAuthMeansCd() eq 2}">
									<tr>
										<th>휴대폰 SMS인증</th>
										<td>
											<input type="text" id="authCode" name="authCode" placeholder="SMS 인증번호를 입력하세요" maxlength="6">
											<button type="button" id="btnReqSmsCode">인증번호 요청</button>
											<p class="info-text">인증번호 요청 후, 휴대폰으로 전송된 인증번호 6자리를 입력하세요</p>
										</td>
									</tr>
										</c:when>
										<c:when test="${info.getAuthMeansCd() eq 1}">
									<tr>
										<th>OTP</th>
										<td>
											<input type="text" id="authCode" name="authCode" value="" maxlength="6">
										</td>
									</tr>
										</c:when>
										<c:otherwise></c:otherwise>
									</c:choose>
								</tbody>
							</table>

							<!-- API생성하기 버튼 : Start -->
							<div class="btn-group">
								<button class="big blue" type="button" id="setApi">API생성하기</button>
							</div>
							<!-- //API생성하기 버튼 : End -->
						</div>
					</div>
					<!-- //보안인증 하기 : End -->

					<!-- 등록된 API 목록 : Start -->
					<div class="mgt20">
						<h2 class="tabletitle">등록된 API 목록</h2>
						<p>- 사용하지 않는 API는 비활성화 또는 삭제 하십시오</p>
						<div>
							<table class="line mgt10">
								<colgroup>
									<col>
									<col>
									<col style="width: 140px;">
									<col style="width: 100px;">
									<col style="width: 100px;">
									<col style="width: 80px;">
								</colgroup>
								<thead>
									<tr>
										<th>Client ID</th>
										<th>Secret Key</th>
										<th>생성일시</th>
										<th>설정된 API</th>
										<th>활성화/비활성화</th>
										<th>삭제</th>
									</tr>
								</thead>
								<tbody id="apiList">
									<c:choose>
										<c:when test="${apiList.size() > 0}">
											<c:forEach var="item" items="${apiList}">
									<tr data-seq="${item.getSeqNo()}">
										<td>
											<p class="ellips-group">
												<span class="text-ellips alLeft">${item.getClientId()}</span>
												<button class="copy">복사</button>
											</p>
										</td>
										<td>
											<p class="ellips-group">
												<span class="text-ellips">${item.getSecretKey()}</span>
												<button class="copy">복사</button>
											</p>
										</td>
										<td class="alCenter">
											<fmt:parseDate var="createDt" value="${item.getCreateDt()}" pattern="yyyyMMddHHmmss" />
											<fmt:formatDate value="${createDt}" pattern="yyyy-MM-dd HH:mm:ss"/>
										</td>
										<td class="alCenter" data-data="${item.getUserApiNo()}">
											<button class="showApiDetail">API보기</button>
										</td>
										<td class="alCenter">
											<c:choose>
												<c:when test="${item.getActiveYn() eq 1}">
											<label class="switch-button active">
												<input type="checkbox" checked="checked">
											</label>
												</c:when>
												<c:otherwise>
											<label class="switch-button">
												<input type="checkbox">
											</label>
												</c:otherwise>
											</c:choose>
										</td>
										<td>
											<button class="delApi">삭제</button>
										</td>
									</tr>
											</c:forEach>
										</c:when>
										<c:otherwise>
									<tr>
										<td colspan="6">등록된 API가 없습니다</td>
									</tr>
										</c:otherwise>
									</c:choose>
								</tbody>
							</table>
						</div>
					</div>
					<!-- //등록된 API 목록 : End -->

					<div class="mgt20">
						<h2 class="tabletitle">API 문서</h2>
					<p><a href="/resources/docs/API 이용가이드.docx?v=<spring:message code="holdport.info.js.version"/>" target="_blank"> API 이용가이드.docx</a></p>
					<p><a href="/resources/docs/API규격서.docx?v=<spring:message code="holdport.info.js.version"/>" target="_blank">API규격서.docx</a></p>
					</div>
				</li>
				<!--Open API 관리 끝-->

			</ul>
		</div>
	</article>
</section>

<div class="popup-wrap" id="pwChange">
	<div class="popup alert">
		<div class="alert-header">
			<p class="alert-title">비밀번호 변경</p>
			<a class="top-close" onclick="$('#pwChange').hide();"><img src="/resources/img/btn-alert-close.png" alt="닫기버튼"></a>
		</div>
		<div class="alert-content">
			<table class="type01">
				<colgroup>
					<col style="width: 150px">
					<col>
				</colgroup>
				<tbody>
					<tr>
						<th>현재 비밀번호</th>
						<td><input type="password" maxlength="25"></td>
					</tr>
					<tr>
						<th>신규 비밀번호</th>
						<td><input type="password" maxlength="25" placeholder=""></td>
					</tr>
					<tr>
						<th>신규 비밀번호 확인</th>
						<td><input type="password" maxlength="25"></td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="alert-btn">
			<button type="button" class="join" onclick="updatePassword();">확인 </button>
		</div>
	</div>
</div>


<div class="popup-wrap" id="mobileAuth">
	<div class="popup alert">
		<div class="alert-header">
			<p class="alert-title">비밀번호 변경</p>
			<a class="top-close" onclick="$('#mobileAuth').hide();"><img src="/resources/img/btn-alert-close.png" alt="닫기버튼"></a>
		</div>
		<div class="alert-content">
			<table class="type01">
				<colgroup>
					<col style="width: 150px">
					<col>
				</colgroup>
				<tbody>
					<tr>
						<th>전화번호</th>
						<td><input type="text" maxlength="25"></td>
					</tr>
					<tr>
						<th>인증번호</th>
						<td><input type="text" maxlength="25" placeholder=""></td>
					</tr>

				</tbody>
			</table>
		</div>
		<div class="alert-btn">
			<button type="button" class="join" onclick="updatePassword();">확인 </button>
		</div>
	</div>
</div>



<div class="popup-wrap" id="smsChange">
	<div class="popup alert">
		<div class="alert-header">
			<p class="alert-title">보안인증수단 변경</p><!-- 팝업 타이틀 -->
			<a class="top-close" onclick="$('#smsChange').hide();">
				<img src="/resources/img/btn-alert-close.png" alt="닫기버튼">
			</a><!-- 팝업 닫기 버튼 -->
		</div>
		<div class="alert-content">
			<table class="type01">
				<colgroup>
					<col style="width: 100px">
					<col>
					<col>
				</colgroup>
				<tbody>
					<tr>
						<th>SMS 인증</th>
						<td><input type="text" id="smsAuthCode" maxlength="6"></td>
						<td><button type="button" onclick="requestSmsAuthNumber(this);" id="btnAuthCode">인증번호 요청</button></td>
					</tr>
					<tr>
						<td colspan="3" class="alLeft unit">
								※ 인증번호 요청 후, 휴대폰으로 전송된 인증번호 6자리를 입력하세요.
						</td>
					</tr>
				</tbody>
			</table>
		</div>

		<div class="alert-btn">
			<button class="blue" onclick="changeAuthToSms();">확인 </button>
		</div>
	</div>
</div>

<div class="popup-wrap" id="otpChange">
	<div class="popup alert">
		<div class="alert-header">
			<p class="alert-title">보안인증수단 변경</p><!-- 팝업 타이틀 -->
			<a class="top-close" onclick="$('#otpChange').hide();">
				<img src="/resources/img/btn-alert-close.png" alt="닫기버튼">
			</a><!-- 팝업 닫기 버튼 -->
		</div>
		<div class="alert-content">
			<table class="type01">
				<colgroup>
					<col style="width: 100px">
					<col>
					<col>
				</colgroup>
				<tbody>
					<tr>
						<th>OTP 인증</th>
						<td colspan="2"><input type="text" id="otpAuthCode" maxlength="6"></td>
					</tr>
					<tr>
						<td colspan="3" class="alLeft unit">
								※ OTP앱 실행 후 표시 된 인증번호 6자리를 입력하세요.
						</td>
					</tr>
				</tbody>
			</table>
		</div>

		<div class="alert-btn">
			<button class="blue" onclick="changeAuthToOtp();">확인 </button>
		</div>
	</div>
</div>

<div class="popup-wrap" id="otpInitInfo">
	<div class="popup otp_info"> <!--alert : 가로400 big :가로 680-->
		<!-- 팝업내용 : Start -->
		<div class="alert-header">
			<p class="alert-title">OTP 초기화 가이드</p><!-- 팝업 타이틀 -->
			<a class="top-close" onclick="$('#otpInitInfo').hide();">
				<img src="/resources/img/btn-alert-close.png" alt="닫기버튼">
			</a><!-- 팝업 닫기 버튼 -->
		</div>
		<div class="alert-content">
			<p>OTP 초기화는 본인인증을 거친 후에 처리 되며, 관리자 확인 후 처리되므로 시간이 소요될 수 있습니다.</p>
			<p>OTP 초기화를 위해 아래 본인인증 서류 접수가 필요합니다.</p>
			<p>아래 방법을 참고하시어 인증서류를 정확히 접수하시면 빠른 처리가 가능합니다.</p>
			<p>※ 접수 메일주소 : <a href="mailto:help@bita500.com" class="blue">help@bita500.com</a></p>
			<p>※ 접수 양식(아래 내용을 정확히 기재하여 첨부파일과 함께 메일 접수하시기 바랍니다.)</p>
			<ul class="decimal">
				<li>이메일 제목 : OTP 초기화 요청</li>
				<li class="mgt10">이메일 기재 내용
					<p>1) 이름 :</p>
					<p>2) 가입 이메일(로그인 ID) :</p>
					<p>3) 본인인증 한 휴대폰 번호 :</p>
				</li>
				<li class="mgt10">첨부파일
					<p>1) 요청사항 메모부착 신분증 사진 (아래 사진 참조)</p>
					<p>2) 메모부착 신분증을 들고 찍은 정면 사진 (아래 사진 참조)</p>
					<p>※ 신분증은 주민등록증, 운전면허증, 여권 3가지 유효기간내의 신분증만 해당됩니다.</p>
					<p>※ 신분증 사진 촬영시 포스트잇 등으로 개인정보를 가리고 사진촬영하세요 .<br>(아래 사진 참조, 포토샵 등 이미지 편집프로그램으로 수정금지)</p>
					<p class="mgt10">※ 메모 기입 필수 내용</p>
					<ul class="decimal" style="margin-top: 0">
						<li>BITA500 OTP 초기화 요청</li>
						<li>가입 이메일(로그인 ID)</li>
						<li>접수 날짜</li>
					</ul>
				</li>
			</ul>

			<p class="bolder blue">첨부 파일 안내</p>
			<ul>
				<li>※ 아래 그림과 같이 메모와 함께 사진촬영하여 첨부파일을 첨부하세요.</li>
				<li>※ 요청 제출일 이전 촬영한 사진은 인정되지 않습니다.</li>
				<li>※ 첨부파일은 jpg/png 이미지 파일만 첨부가 가능합니다.</li>
			</ul>
			<p class="bold mgt10">1. 요청사항 메모 부착한 신분증 사진</p>
			<p><img src="/resources/img/site/img_otp_info1.png" alt="img"></p>
		  	<p>※ 개인정보 보호를 위해 주민등록번호 뒷 7자리(①), 주소(②)는 반드시 가려서 사진 촬영하세요.(포토샵 수정된 파일은 접수 불가)</p>
			<p class="bold mgt10">2. 메모 부착한 신분증을 들고 촬영한 본인 사진</p>
			<p><img src="/resources/img/site/img_otp_info2.png" alt="img"></p>
			<p>※ 1번에서 준비한 메모붙인 신분증 내용과 본인 얼굴이 모두 선명하게 나와야 합니다.</p>
			<p class="mgt20">
					자동차운전면허증 ①-면허번호, ②-주민등록번호 뒷 7자리, ③ - 주소 가려주세요<br>
				<img src="/resources/img/site/img_otp_info3.png" alt="img">
				<img src="/resources/img/site/img_otp_info33.png" alt="img">
			</p>
			<p class="mgt20">
					여권 ①-여권번호, ②-기계 판독 영역 가려주세요<br>
				<img src="/resources/img/site/img_otp_info4.png" alt="img">
				<img src="/resources/img/site/img_otp_info44.png" alt="img">
			</p>
		</div>
		<!-- //팝업내용 : End -->
		<div class="btn-wrap mgt20">
			<button class="blue big" onclick="$('#otpInitInfo').hide();">확인 </button>
		</div>
	</div>
</div>

<div class="popup-wrap sms_pop" id="pop-sms">
	<div class="popup otp_info"> <!--alert : 가로400 big :가로 680-->
		<!-- 팝업내용 : Start -->
		<div class="alert-header">
			<p class="alert-title">휴대폰 초기화 가이드</p><!-- 팝업 타이틀 -->
			<a class="top-close" onclick="$('#pop-sms').hide();">
				<img src="/resources/img/btn-alert-close.png" alt="닫기버튼">
			</a><!-- 팝업 닫기 버튼 -->
		</div>
		<div class="alert-content">
			<p>SMS 초기화는 본인인증을 거친 후에 처리 되며, 관리자 확인 후 처리되므로 시간이 소요될 수 있습니다.</p>
			<p>SMS 초기화를 위해 아래 본인인증 서류 접수가 필요합니다.</p>
			<p>아래 방법을 참고하시어 정확히 인증서류를 접수하시면 빠른 처리가 가능합니다.</p>
			<p>※ 접수 메일주소 : <a href="mailto:help@bita500.com" class="blue">help@bita500.com</a></p>
			<p>※ 접수 양식(아래 내용을 정확히 기재하여 첨부파일과 함께 메일 접수하시기 바랍니다.)</p>
			<ul class="decimal">
				<li>이메일 제목 : SMS 초기화 요청</li>
				<li class="mgt10">이메일 기재 내용
					<p>1) 이름 :</p>
					<p>2) 가입 이메일(로그인 ID) :</p>
					<p>3) 본인인증 한 휴대폰 번호 :</p>
				</li>
				<li class="mgt10">첨부파일
					<p>1) 통신사 가입증명서</p>
					<p>2) 메모부착 신분증 사진 (아래 사진 참조)</p>
					<p>3) 메모부착 신문증을 들고 찍은 정면 사진 (아래 사진 참조)</p>
					<p>※ 신분증은 주민등록증, 운전면허증, 여권 3가지 유효기간내의 신분증만 해당됩니다.</p>
					<p>※ 신분증 사진 촬영시 주민등록번호 뒷 7자리 등은 포스트잇 등으로 가리고 사진활영하세요 .<br>(아래 사진 참조, 포토샵 등 이미지 편집프로그램으로 수정금지)</p>
					<p class="mgt10">※ 메모 기입 필수 내용</p>
					<ul class="decimal" style="margin-top: 0">
						<li>BITA500 SMS 초기화 요청</li>
						<li>가입 이메일(로그인 ID)</li>
						<li>접수 날짜</li>
					</ul>
				</li>
			</ul>

			<p class="bolder blue">첨부 파일 안내</p>
			<ul>
				<li>※ 아래 그림과 같이 메모와 함께 사진촬영하여 첨부파일을 첨부하세요.</li>
				<li>※ 요청 제출일 이전 촬영한 사진은 인정되지 않습니다.</li>
				<li>※ 첨부파일은 jpg/png 이미지 파일만 첨부가 가능합니다.</li>
			</ul>
			<p class="bold mgt10">1. 통신사 가입증명서 첨부</p>
			<p> - 통신사 직인이 찍혀있는 가입사실확인 증명서를 스캔하거나 촬영하여 첨부<br>(SKT:이용계약 등록사항 증명서 / KT:원부증명서 / U+:가입사실확인서)</p>
			<p class="bold mgt10">2. 요청사항 메모 부착한 신분증 사진</p>
			<p><img src="/resources/img/site/img_sms_info1.png" alt="img"></p>
			<p>※ 개인정보 보호를 위해 주민등록번호 뒷 7자리(①), 주소(②)는 반드시 가려서 사진 촬영하세요.(포토샵 수정된 파일은 접수 불가)</p>

			<p class="bold mgt10">3. 메모 부착한 신분증을 들고 촬영한 본인 사진</p>
			<p><img src="/resources/img/site/img_sms_info2.png" alt="img"></p>
			<p>※ 2번에서 준비한 메모붙인 신분증 내용과 본인 얼굴이 모두 선명하게 나와야 합니다.</p>
			 <p class="mgt20">
				자동차운전면허증 ①-면허번호, ②-주민등록번호 뒷 7자리, ③ - 주소 가려주세요<br>
				<img src="/resources/img/site/img_sms_info3.png" alt="img">
				<img src="/resources/img/site/img_sms_info33.png" alt="img">
			</p>
			<p class="mgt20">
				여권 ①-여권번호, ②-기계 판독 영역 가려주세요<br>
				<img src="/resources/img/site/img_sms_info4.png" alt="img">
				<img src="/resources/img/site/img_sms_info44.png" alt="img">
			</p>

		</div>
		<!-- //팝업내용 : End -->
		<div class="btn-wrap mgt20">
			<button class="blue big" onclick="$('#pop-sms').hide();">확인 </button>
		</div>
	</div>
</div>

<div class="popup-wrap api_show" id="apiDetail">
	<div class="popup alert">
		<div class="pop-title">
			<span>API 보기</span>
			<span class="top-close" $('#apiDetail').hide();">
				<img src="/resources/img/btn-alert-close.png" alt="닫기버튼">
			</span>
		</div>
		<div class="popup-cont">
			<table class="type03">
				<colgroup>
					<col>
					<col>
				</colgroup>
				<thead>
					<tr>
						<th>API명</th>
						<th>상태</th>
					</tr>
				</thead>
				<tbody id="apiDetailBody">
					<tr>
						<td>시세조회</td>
						<td data-no="001"></td>
					</tr>
					<tr>
						<td>지갑정보</td>
						<td data-no="002"></td>
					</tr>
					<tr>
						<td>잔고조회</td>
						<td data-no="003"></td>
					</tr>
					<tr>
						<td>거래내역조회</td>
						<td data-no="004"></td>
					</tr>
					<tr>
						<td>주문내역조회</td>
						<td data-no="005"></td>
					</tr>
					<tr>
						<td>주문</td>
						<td data-no="006"></td>
					</tr>
					<!-- <tr>
						<td>암호화폐 출금</td>
						<td data-no="007"></td>
					</tr>
					<tr>
						<td>KRW출금</td>
						<td data-no="008"></td>
					</tr> -->
				</tbody>
			</table>
			<div class="btn-group mgt20">
				<button class="blue" onclick="$('#apiDetail').hide();">확인 </button>
			</div>
		</div>
	</div>
</div>

<script src="/resources/js/qrcode/qrcode.min.js"></script>
<script src="/resources/js/holdport/mypage/holdport.info.js"></script>

<script>
var userType = '${info.getUserTypeCd()}';

$(document).ready(function() {
	$("ul.vpanel li:not(" + $("ul.vtab li a.vselected").attr("href") + ")").hide()

	$("ul.vtab li a").click(function() {
		$("ul.vtab li a").removeClass("vselected");
		$(this).addClass("vselected");
		$("ul.vpanel li").hide();
		$($(this).attr("href")).show();

		$('#pwChange').hide();
		$('#mobileAuth').hide();

		return false;
	});



	// parameter로 tab을 받았을때 tab click event 발생시키기 (2019.04.26 kangnaru)
	var tab_index = '${tab}';
	if(tab_index != '') {
		$('ul.vtab li a:eq("' + tab_index + '")').trigger('click');
	} else {
		$('ul.vtab li a:eq(0)').trigger('click');
	}

	getRecInfo();

	$('#vtab1').find('input[type=checkbox]').on('change', function() {
		$('#vtab1').find('input[type=checkbox]').prop('checked', false);
		$(this).prop('checked', true);
	});

	// Google Otp QR Code 보여주기
	showQRCode('${googleOtpKey}');
	if('${otpYn}' == '1') {		// OTP 등록이 끝난 회원은 인증하기 div를 감춘다.
		$('#vtab2-level4-otp-key-setting-text').text('회원님께서는 등록하신 Google OTP 혹은 Authenticator에 표시되는 인증코드를 사용하세요.');
		$('#vtab2-level4-otp-key-intro-text').text('회원님께서는 OTP를 등록하신 상태입니다.');
		$('#vtab2-level4-otp-auth-div').hide();
	}

	ju.dp({
		selector:'#startDate',
		format:'yy-mm-dd'
	});

	ju.dp({
		selector:'#endDate',
		format:'yy-mm-dd'
	});

	Pu.init({
		total:0,
		count:10,
		pageCount:5,
		draw:getAccessLog,
		paginate:'.pagination',
		initCllBck:false,
		initEventFun:getAccessLog,
		button:'#logSearch',
		totalText:'#size',
		type:'search'
	});

	$('#logSearch').trigger('click');

	$('#Date').on('keyup', function(e) {
		let $this = $(this);
		$this.val( $this.val().replace(/[^0-9]/g, '') );
	});
})

function auth_data(rtnCd, rtnMsg) {
	if(rtnCd == 0) {
		location.href='/site/info?tab=1';
		/* lrt.client('인증 성공', '', function() {
			location.href='/site/info?tab=1';
		}); */
	} else {
		lrt.client(wordbook[rtnCd]);
	}
}
</script>
