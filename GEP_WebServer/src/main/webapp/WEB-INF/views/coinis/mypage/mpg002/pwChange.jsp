<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/taglib/taglib.jsp"%>

<body>
	<!--컨텐츠 내용 영역 시작-->
	<section>
		<!--보안설정 전체 내용 시작-->
		<article class="bgwhite">

			<h1 class="sub-title">
				<ul>
					<li>My page</li>
					<li>>Change Password</li>
				</ul>
			</h1>
			<div class="content-item">

				<h1>로그인 비밀번호 변경</h1>
				<table class="tblType01">
					<colgroup>
						<col style="width: 200px">
						<col>
					</colgroup>
					<tbody>
						<tr>
							<th>이메일(로그인)ID</th>
							<td>${userid}</td>
						</tr>
						<tr>
							<th>현재 비밀번호</th>
							<td><input type="password" id="currentPW" class="basic"
								maxlength="25"></td>
						</tr>
						<tr>
							<th>신규 비밀번호</th>
							<td><input type="password" id="newPW" class="basic"
								maxlength="25"></td>
						</tr>
						<tr>
							<th>신규 비밀번호 확인</th>
							<td><input type="password" id="confirmnewPW" class="basic"
								maxlength="25"></td>
						</tr>
					</tbody>
				</table>
				<div class="btn-wrap mgb20">
					<button class="big bgblue" id="setup">Setup</button>
				</div>

			</div>

		</article>
		<!--보안설정 전체 내용 끝-->
	</section>
	<!--컨텐츠 내용 영역 끝-->

	<script>
		$(function() {
			$("#setup").click(function() {
				var p = {};

				p["currentPW"] = $("#currentPW").val();
				p["newPW"] = $("#newPW").val();
				p["confirmnewPW"] = $("#confirmnewPW").val();

				if (p.currentPW.length < 8) {
					lrt.client('비밀번호는 최소 8자리 이상입니다.');
					return;
				} else if (p.currentPW == '') {
					lrt.client('비밀번호를 입력하세요', '');
					return;
				} else if (p.newPW == '') {
					lrt.client('신규 비밀번호를 입력하세요', '');
					return;
				} else if (p.confirmnewPW == '') {
					lrt.client('신규 비밀번호 확인을 입력하세요', '');
					return;
				} else if (p.newPW.length < 8) {
					lrt.client('신규 비밀번호는 최소 8자리 이상이어야 합니다.');
					return;
				} else if (p.newPW != p.confirmnewPW) {
					lrt.client('신규 비밀번호가 일치하지않습니다.', '');
					return;
				} else {
					ajax('/coinis/pwChange/checkPW', p, function(data) {
						if (data.rtnCd == 0) {
									lrt.client('비밀번호 변경에 성공하였습니다. 변경된 비밀번호로 로그인 해주시기 바랍니다.',
											'비밀번호 변경', function() {
												location.reload();
											});
						} else {
							lrt.client(data.message, '비밀번호 변경');
						}
					});
				}
			});
		});
	</script>
</body>