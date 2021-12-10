<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/taglib/taglib.jsp" %>

<script>

<c:choose>
    <c:when test="${sessionScope.userInfo.loginYn == 1}">
		createServerSentEvents(eventDispatcher, 'HOLDPORT/${sessionScope.userInfo.userId}');
    </c:when>
    <c:otherwise>
		createServerSentEvents(eventDispatcher, 'HOLDPORT/anonymous');
    </c:otherwise>
</c:choose>


function eventDispatcher(data) {

	data = JSON.parse(data);

	//console.log("eventDispatcher+++", data);

	//publicListener: 'HOLDPORT/public'
	//realContractListener: 'HOLDPORT/contract'
	//privateListener: 'HOLDPORT/${sessionScope.userInfo.userId}'


	//console.log("eventDispatcher+++", data);

	if(data.msgType == 'public') {
		publicListener(data);
	} else if(data.msgType == 'contract') {
		if(typeof realContractListener == 'function') {
			realContractListener(data);
		}
	} else if(data.msgType == 'user') {
		if(typeof privateListener == 'function') {
			privateListener(data);
		}
	}
}



//TopicLister(publicListener, 'YAHOBIT/public');
//createServerSentEvents(publicListener, 'HOLDPORT/public');
function publicListener(data) {
	//data = JSON.parse(data);
	console.log("publicListener+++", data);

	if(data.msgCode == 999) {
		if(document.visibilityState == 'visible') {
			showNotice(data.body);
		}
	} else if(data.msgCode == 111) {}
}
</script>

<c:if test="${sessionScope.userInfo.loginYn == 1}">
<script>
//TopicLister(privateListener, 'HOLDPORT/${sessionScope.userInfo.userId}');
//createServerSentEvents(privateListener, 'HOLDPORT/${sessionScope.userInfo.userId}');

function privateListener(data) {
	//data = JSON.parse(data);
	console.log("privateListener+++", data);
	switch(parseInt('' + data.msgCode)) {
	case 206:
		// 투자내역에서 미체결 취소 결과 리스너 함수 호출
		if(typeof __Push_Listener_Invest_Cancel_NonContract === 'function') __Push_Listener_Invest_Cancel_NonContract(data);
		break;
	case 401:
		// 입출금에서 입출금 내역결과 리스너 함수 호출
		//if(typeof __Push_Listener_Wallet_DW_Result === 'function') __Push_Listener_Wallet_DW_Result(data);
		if(typeof __Push_Listener_Wallet_Result=== 'function') __Push_Listener_Wallet_Result(data);
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
			//TopicLister(realContractListener, '/topic/' + data.mktGrpId + '/contract');
			
			//--coben(2021/07/25)
			//createServerSentEvents(realContractListener, data.mktGrpId + '/contract');
			//++coben(2021/07/25)
			//createServerSentEvents(realContractListener, 'HOLDPORT/contract');
			data = JSON.parse(data);
			realContractListener(data);
		}
	});
})


function realContractListener(data) 
{
	//data = JSON.parse(data);

	if(data.msgCode === 106) {}
}
</script>
</c:if>