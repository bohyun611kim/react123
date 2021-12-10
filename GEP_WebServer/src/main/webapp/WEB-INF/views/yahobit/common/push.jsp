<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/taglib/taglib.jsp" %>

<script>
//TopicLister(publicLister, 'YAHOBIT/public');
createServerSentEvents(publicLister, 'YAHOBIT/public');
function publicLister(data) {
	data = JSON.parse(data);

	if(data.msgCode == 999) {
		if(document.visibilityState == 'visible') {
			showNotice(data.body);
		}
	} else if(data.msgCode == 111) {}
}
</script>

<c:if test="${sessionScope.userInfo.loginYn == 1}">
<script>
//TopicLister(privateLister, 'YAHOBIT/${sessionScope.userInfo.userId}');
createServerSentEvents(privateLister, 'YAHOBIT/${sessionScope.userInfo.userId}');

function privateLister(data) {
	data = JSON.parse(data);

	switch(parseInt('' + data.msgCode)) {
	case 206:
		// 투자내역에서 미체결 취소 결과 리스너 함수 호출
		if(typeof __Push_Listener_Invest_Cancel_NonContract === 'function') __Push_Listener_Invest_Cancel_NonContract(data);
		break;
	case 401:
		// 입출금에서 입출금 내역결과 리스너 함수 호출
		if(typeof __Push_Listener_Wallet_DW_Result === 'function') __Push_Listener_Wallet_DW_Result(data);
		break;
	case 501:
		// 입출금에서 잔고변경 리스너 함수 호출
		if(typeof __Push_Listener_Wallet_Change_Balance === 'function') __Push_Listener_Wallet_Change_Balance(data);
		break;
	}
}

$(document).ready(function() {
	ajaxOption({
		url:'/common/defaultMktGrpId',
		success:function(data) {
			//TopicLister(realContractLister, '/topic/' + data.mktGrpId + '/contract');
			createServerSentEvents(realContractLister, data.mktGrpId + '/contract');

			function realContractLister(data) {
				data = JSON.parse(data);

				if(data.msgCode === 106) {}
			}
		}
	});
})
</script>
</c:if>