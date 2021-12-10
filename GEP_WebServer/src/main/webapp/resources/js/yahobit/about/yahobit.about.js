
//////////////////////////////////////////////////////////////////////////////
//                           1:1 문의 처리
//////////////////////////////////////////////////////////////////////////////
var __G_Inquiry_Main_Cate;
var __G_Inquiry_Main_Cate_Txt;
var __G_Inquiry_Sub_Cate;
var __G_Inquiry_Sub_Cate_Txt;

// 문의에 대한 제목과 내용을 담고있는 템플릿
var __G_Inquire_Category_Template_Map = new simpleMap();
// 계정 문의
__G_Inquire_Category_Template_Map.put('sub-accnt-01', {'attachfile': true, 'title': '휴대전화 번호 초기화 요청', 'content': '\no 휴대폰번호 : \n\no 생년월일 6자리: '});
__G_Inquire_Category_Template_Map.put('sub-accnt-02', {'attachfile': true, 'title': '계정복구 요청', 'content': '\no 휴대폰번호 : \n\no 생년월일 6자리: '});
__G_Inquire_Category_Template_Map.put('sub-accnt-03', {'attachfile': true, 'title': '이름 변경 요청', 'content': '\no 이름 : \n\no 생년월일 6자리: \n\no 사유 : '});
__G_Inquire_Category_Template_Map.put('sub-accnt-04', {'attachfile': false, 'title': '로그인/로그아웃 문의', 'content': '\no 내용 : '});
__G_Inquire_Category_Template_Map.put('sub-accnt-05', {'attachfile': false, 'title': '회원자산/회원정보 문의', 'content': '\no 내용 : '});
// 알림톡 문의
__G_Inquire_Category_Template_Map.put('sub-alimtok-01', {'attachfile': false, 'title': '알림톡 문의', 'content': '\no 내용 : '});
// 카카오페이 문의
__G_Inquire_Category_Template_Map.put('sub-cacaopay-01', {'attachfile': false, 'title': '카카오페이 문의', 'content': '\no 내용 : '});
// 입출금 문의
__G_Inquire_Category_Template_Map.put('depwitd', {'attachfile': false, 'title': '입출금 문의', 'content': '\no 내용 : '});
// 매매장애 문의
__G_Inquire_Category_Template_Map.put('sbprob', {'attachfile': false, 'title': '매매장애 문의', 'content': '\no 내용 : '});
// 제안
__G_Inquire_Category_Template_Map.put('sub-request-01', {'attachfile': false, 'title': '서비스 개선 제안', 'content': '\no 내용 : '});
__G_Inquire_Category_Template_Map.put('sub-request-02', {'attachfile': false, 'title': '투자 제안', 'content': '\no 내용 : '});
__G_Inquire_Category_Template_Map.put('sub-request-03', {'attachfile': false, 'title': '제휴 문의', 'content': '\no 내용 : '});
// 일반
__G_Inquire_Category_Template_Map.put('sub-general-01', {'attachfile': false, 'title': '암호화폐 기타 문의', 'content': '\no 내용 : '});
__G_Inquire_Category_Template_Map.put('sub-general-02', {'attachfile': false, 'title': '기타 문의', 'content': '\no 내용 : '});
__G_Inquire_Category_Template_Map.put('sub-general-03', {'attachfile': false, 'title': '출금정지 이의 신청', 'content': '\no 내용 : '});
__G_Inquire_Category_Template_Map.put('sub-general-04', {'attachfile': false, 'title': '이벤트 지급관련 문의', 'content': '\no 내용 : '});
// 금융사고
__G_Inquire_Category_Template_Map.put('sub-bankaccd-01', {'attachfile': false, 'title': '수사/금융/공공기관 문의', 'content': '\no 내용 : '});
__G_Inquire_Category_Template_Map.put('sub-bankaccd-02', {'attachfile': false, 'title': '해킹/명의도용/보이스피싱 문의', 'content': '\no 내용 : '});
__G_Inquire_Category_Template_Map.put('sub-bankaccd-03', {'attachfile': false, 'title': '다단계 신고/불량사용자 신고', 'content': '\no 내용 : '});
__G_Inquire_Category_Template_Map.put('sub-bankaccd-04', {'attachfile': false, 'title': '금융사기계정 거래 정지 해제 문의', 'content': '\no 내용 : '});
//오입금
__G_Inquire_Category_Template_Map.put('sub-misdeposit-01', {'attachfile': false, 'title': 'BTC계열 오입금', 'content': '\no 내용 : '});
__G_Inquire_Category_Template_Map.put('sub-misdeposit-02', {'attachfile': false, 'title': 'ERC20(상장)을 ETH지갑주소로 오입금', 'content': '\no 내용 : '});
__G_Inquire_Category_Template_Map.put('sub-misdeposit-03', {'attachfile': false, 'title': 'ERC20(상장)을 ETC지갑주소로 오입금', 'content': '\no 내용 : '});
__G_Inquire_Category_Template_Map.put('sub-misdeposit-04', {'attachfile': false, 'title': 'ERC20(상장)을 ERC20지갑주소로 오입금', 'content': '\no 내용 : '});
__G_Inquire_Category_Template_Map.put('sub-misdeposit-05', {'attachfile': false, 'title': 'ERC20(비상장)을 ETH지갑주소로 오입금', 'content': '\no 내용 : '});
__G_Inquire_Category_Template_Map.put('sub-misdeposit-06', {'attachfile': false, 'title': 'ERC20(비상장)을 ETC지갑주소로 오입금', 'content': '\no 내용 : '});
__G_Inquire_Category_Template_Map.put('sub-misdeposit-07', {'attachfile': false, 'title': 'ERC20(비상장)을 ERC20지갑주소로 오입금', 'content': '\no 내용 : '});
__G_Inquire_Category_Template_Map.put('sub-misdeposit-08', {'attachfile': false, 'title': 'ETC를 ETH지갑주소로 오입금', 'content': '\no 내용 : '});
__G_Inquire_Category_Template_Map.put('sub-misdeposit-09', {'attachfile': false, 'title': 'ETH를 ETC지갑주소로 오입금', 'content': '\no 내용 : '});
__G_Inquire_Category_Template_Map.put('sub-misdeposit-10', {'attachfile': false, 'title': 'ICO코인을 ETH지갑주소로 오입금', 'content': '\no 내용 : '});
__G_Inquire_Category_Template_Map.put('sub-misdeposit-11', {'attachfile': false, 'title': '수수료납부', 'content': '\no 내용 : '});
//이벤트
__G_Inquire_Category_Template_Map.put('sub-event-01', {'attachfile': false, 'title': '■■■ 이벤트 문의', 'content': '\no 내용 : '});
__G_Inquire_Category_Template_Map.put('sub-event-02', {'attachfile': false, 'title': '●●● 이벤트 문의', 'content': '\no 내용 : '});
//출금신청
__G_Inquire_Category_Template_Map.put('sub-withdrawrequest-01', {'attachfile': false, 'title': '출금신청 문의', 'content': '\no 내용 : '});
//바로출금
__G_Inquire_Category_Template_Map.put('sub-withdrawrightnow-01', {'attachfile': false, 'title': '착오 송금 중재 문의', 'content': '\no 내용 : '});

// 1:1문의 유형 TR 클릭이벤트 처리
$('#inquriy-sub-category').delegate('li', 'click', function (event) {
	__G_Inquiry_Sub_Cate = $(this).attr('val');
	__G_Inquiry_Sub_Cate_Txt = $(this).text();
	inputRequiry();
});
$('.coin-category').delegate('li', 'click', function (event) {
	__G_Inquiry_Sub_Cate_Txt = $(this).text();
	inputRequiry();
});

$('.main-category').delegate('li', 'click', function (event) {
	$('.main-category li ').each(function(){
		$(this).attr('class', '');
		$(this).show();
	});
	$(this).attr('class', 'on');

	$('#inquriy-main-category-title').show();
	$('#inquriy-sub-category').show();
	$('#inquriy-coin-category').hide();
	$('#inquiry-input-div').hide();
	
	$('#inquriy-sub-category .sub-category').each(function(){
		$(this).hide();
	});
	$('.' + $(this).attr('val')).each(function(){
		$(this).show();
	});
	
	__G_Inquiry_Main_Cate = $(this).attr('val');
	__G_Inquiry_Main_Cate_Txt = $(this).text();
	__G_Inquiry_Sub_Cate = '';
	__G_Inquiry_Sub_Cate_Txt = '';
	if(__G_Inquiry_Main_Cate == 'depwitd' || __G_Inquiry_Main_Cate == 'sbprob' ) {
		__G_Inquiry_Sub_Cate = __G_Inquiry_Main_Cate;
		$('#inquriy-sub-category').hide();
		$('#inquriy-coin-category').show();
	}
});
// 상황에 따른 div show/hide
function inputRequiry() {
	$('#inquriy-main-category-title').hide();
	$('#inquriy-main-category').hide();
	$('#inquriy-sub-category').hide();
	$('#inquriy-coin-category').hide();
	$('#inquiry-input-div').show();
	
	$('#inquiry-input-main-cate').text(__G_Inquiry_Main_Cate_Txt);
	$('#inquiry-input-sub-cate').text(__G_Inquiry_Sub_Cate_Txt);
	
	let template = __G_Inquire_Category_Template_Map.get(__G_Inquiry_Sub_Cate);
	if(template.attachfile) $('#inquiry-input-attach-file').show();
	else $('#inquiry-input-attach-file').hide();
	
	$('#inquiry-input-title').val(template.title);
	$('#inquiry-input-content').val(template.content);
}
// 1:1 문의 취소
function cancelInquiry() {
	$('#inquriy-main-category-title').show();
	$('#inquriy-main-category').show();
	$('#inquriy-sub-category').hide();
	$('#inquriy-coin-category').hide();
	$('#inquiry-input-div').hide();
	$('#inquiry-input-title').val('');
	$('#inquiry-input-content').val('');
	$('#inquriy-coin-category-search').val('');
	$('.coin-category li').each(function(){$(this).attr('style', 'display: block !important;');});
	$('.main-category li ').each(function(idx){ $(this).attr('class', ''); });
	$('.main-category li ').each(function(){$(this).attr('style', 'display: block !important;');});
	
	$('.filebox-item').data('index', 0).html('');
	$('.filebox').children('input').remove();
	$('.filebox').append('<input type="file" id="ex_filename" class="upload-hidden">');
	$('#attachFile').attr('for', 'ex_filename');
	
}
// 1:1 문의 등록 확인 confirm function
function requestInquiry() {
    var inquiryTitle	= $('#inquiry-input-title').val();
    var inquiryContent	= $('#inquiry-input-content').val();

    if(inquiryTitle == '') {
        lrt.client('문의 제목을 입력하세요.', '오류', function() {
            $('#inquiry-input-title').focus();
        }, null);
        return false;
    }
    if(inquiryContent == '') {
        lrt.client('문의 내용을 입력하세요.', '오류', function() {
            $('#inquiry-input-content').focus();
        }, null);
        return false;
    }

    if($('#inquiry-input-attach-file').css('display') === 'none') {
    	var paramMap = {
    	    	inquiryTitle : inquiryTitle
    	      , inquiryContent : inquiryContent
    	      , mainCategory : __G_Inquiry_Main_Cate
    	      , subCategory : __G_Inquiry_Sub_Cate
    	};
    	lrt.confirm('1:1 문의를 등록 하시겠습니까?', '1:1 문의 등록', processInquiry, paramMap);
    } else {
    	var formData = new FormData();
    	formData.append('inquiryTitle', inquiryTitle);
    	formData.append('inquiryContent', inquiryContent);
    	formData.append('mainCategory', __G_Inquiry_Main_Cate);
    	formData.append('subCategory', __G_Inquiry_Sub_Cate);
    	
    	$('.filebox').children('input').each(function() {
    		if(this.files[0] != undefined) {
    			formData.append('file', this.files[0]);
    		}
    	});
    	
    	lrt.confirm('1:1 문의를 등록 하시겠습니까?', '1:1 문의 등록', processInquiryFile, formData);
    }
}

function processInquiry(paramMap) {
    ajaxOption({
        url: '/alibit/about/abt001/insertInquiry',
        data: paramMap,
        error: function(error) {
        	console.log('1:1문의 등록 Error >> ' + JSON.stringify(error, null, 2));
        },
        success: function(data) {
        	//console.log(data);
            lrt.client('성공적으로 등록되었습니다.', '1:1문의 등록 성공', function() {
            	cancelInquiry();
            	// 문의내역 리스트 갱신
            	getInquiryList();
            }, null);
            return false;
        },
    });
}
// coin category 검색 keyup 이벤트 처리
$('#inquriy-coin-category-search').on('keyup', function (event) {
	let search_word = $(this).val();
	console.log($('.contact li').css('display'));
	$('.coin-category li').each(function(){
		if( $(this).text().toLowerCase().indexOf(search_word) > -1 || $(this).data('cho').indexOf(Hangul.disassemble(search_word).join('')) > -1 ) {
			$(this).attr('style', 'display: block !important;');
		} else {
			$(this).attr('style', 'display: none !important;');
		}
	});
});

function processInquiryFile(paramMap) {
    ajaxOption({
        url: '/alibit/about/abt001/insertInquiryFile',
        data: paramMap,
        processData: false,
        contentType: false,
        error: function(error) {
        	console.log('1:1문의 등록 Error >> ' + JSON.stringify(error, null, 2));
        },
        success: function(data) {
        	//console.log(data);
            lrt.client('성공적으로 등록되었습니다.', '1:1문의 등록 성공', function() {
            	cancelInquiry();
            	// 문의내역 리스트 갱신
            	getInquiryList();
            }, null);
            return false;
        },
    });
}
//////////////////////////////////////////////////////////////////////////////
//                           1:1 문의 처리 END
//////////////////////////////////////////////////////////////////////////////


//////////////////////////////////////////////////////////////////////////////
//                           1:1 문의내역 리스트 처리
//////////////////////////////////////////////////////////////////////////////
var __G_Inquiry_Cont;
var __G_Inquiry_window;

// 문의내용분류코드에 따른 코드값 Map
var __G_Inquire_CategoryMap = new simpleMap();
__G_Inquire_CategoryMap.put('1', 'API관리');
__G_Inquire_CategoryMap.put('2', '휴대폰번호변경');
__G_Inquire_CategoryMap.put('3', '회원가입/탈퇴');
__G_Inquire_CategoryMap.put('4', '비밀번호변경');
__G_Inquire_CategoryMap.put('5', '수수료');
__G_Inquire_CategoryMap.put('6', '인증레벨설정');
__G_Inquire_CategoryMap.put('7', '인증자료제출');
__G_Inquire_CategoryMap.put('8', '코인매수/매도');
__G_Inquire_CategoryMap.put('9', '코인입출금');
__G_Inquire_CategoryMap.put('99','기타');

function toggleInquiryList(seq) {
	
	var inquiryData			= getInquiryData(seq);
	var inquiryTitle		= getParam(inquiryData, 'INQUIRY_TITLE', '');
	var createDateTime		= getCstmFrmt(getParam(inquiryData, 'REG_DT'), "yyyyMMddHHmmss", "yyyy-MM-dd HH:mm:ss");
	var inquireCategory		= parseInt(getParam(inquiryData, 'INQUIRY_TYPE_CD', '1'));
	var categoryTxt			= __G_Inquire_CategoryMap.get('' + inquireCategory);
	var inquiryContent		= getParam(inquiryData, 'INQUIRY_CONTENTS', '').replace(/(?:\r\n|\r|\n)/g, '<br>');
	
	var p = {};
	
	p['iInquiryNo'] = seq;
	
    ajaxOption({
        url: '/alibit/about/abt001/selectInquiryReplyList',
        data: p,
        success: function(data) {
        	$('#inquire-category').text(categoryTxt);
			$('#inquiry-conts-title').text(inquiryTitle);
			$('#inquiry-conts').html($('<span>' + inquiryContent + '</span>'));
			
			if(data != undefined && data.length > 0) {
				var replyData			= data[0];
				var replyContent		= getParam(replyData, 'REPLY_CONTENTS', '').replace(/(?:\r\n|\r|\n)/g, '<br>');
				$('#reply-conts').html($('<span>' + replyContent + '</span>'));
			}
			
			$('#inquire-reply-dont-select').hide();

        }
        //done: callback
    });

}

function getInquiryList() {
   	// Paging
   	__G_Inquiry_window = Pu.init({
        count: 20,
        PageCount: 5,
        draw: doSearch,
        button: '#inquiry-search',
        paginate: '#inquiry-pagination',
        initCllBck: true,
        type: 'search',
        initEventFun: initData
    });
}

function getInquiryData(seqNo) {
    if(__G_Inquiry_Cont.length == 0) return null;

    for(var i=0; i<__G_Inquiry_Cont.length; i++) {
        var inquirySeq			= getParam(__G_Inquiry_Cont[i], 'INQUIRY_NO');
        if(inquirySeq == seqNo) return __G_Inquiry_Cont[i];
    }
}

function initData(page, callback) {
	var p = {};
		
	p['exchangeId'] = 'YAHOBIT';
	p['pageNum'] = page;
	
    ajaxOption({
        url: '/alibit/about/abt001/selectInquiryListCount',
        data: p,
        success: callback,
        //done: callback
    });
}
function doSearch(i, done) {
	var p = {};
		
	p['exchangeId'] = 'YAHOBIT';
	p['pageNum'] = i;

    ajaxOption({
        url: '/alibit/about/abt001/selectInquiryList',
        data: p,
        success: draw,
        done: done
    });
}

function draw(data) {
	var inquiryListJson = data;
	var inquiryTemplate = document.getElementById("template-inquiry-list-table-tr");
	var inquiryTemplateHtml = inquiryTemplate.innerHTML;
	var inquiryHtml = "";
    __G_Inquiry_Cont = data;       // 전역변수에 저장

    var __first_tr_seq = 0;
    if(inquiryListJson.length > 0) {
        $(inquiryListJson).each(function(data_item) {
            var value				= inquiryListJson[data_item];
            var rowNum				= getParam(value, 'ROWNUM');
            var inquirySeq			= getParam(value, 'INQUIRY_NO');
            var inquiryContent		= getParam(value, 'INQUIRY_CONTENTS', '').replace(/(?:\r\n|\r|\n)/g, '<br>');
            var replyContent		= getParam(value, 'REPLY_CONTENTS', '').replace(/(?:\r\n|\r|\n)/g, '<br>');
            var InquiryTitle 		= getParam(value, 'INQUIRY_TITLE', '');
            var createDateTime		= getCstmFrmt(getParam(value, 'REG_DT'), "yyyyMMddHHmmss", "yyyy-MM-dd HH:mm:ss");
            var replyDateTime		= getCstmFrmt(getParam(value, 'REPLY_DT', ''), "yyyyMMddHHmmss", "yyyy-MM-dd HH:mm:ss");
    
            var classActive			= '';
            var replyStatus			= ( replyContent == undefined || replyContent == '' ) ? '대기중' : '답변';
            
            if(data_item == 0) __first_tr_seq = inquirySeq;
            
            inquiryHtml += inquiryTemplateHtml.replace(/{{InquirySeq}}/g, inquirySeq)
						            .replace(/{{RowNum}}/g, rowNum)
                                    .replace(/{{InquiryTitle}}/g, InquiryTitle)
                                    .replace(/{{RequestDT}}/g, createDateTime)
                                    .replace(/{{ReplyDT}}/g, replyDateTime)
                                    .replace(/{{ClassActive}}/g, classActive)
                                    .replace(/{{ReplyStatus}}/g, replyStatus)
                                    ;
        });
        $('#inquiry_list_table_tbody').html(inquiryHtml);
        toggleInquiryList(__first_tr_seq);
    } else {
        $('#inquiry_list_table_tbody').html($('#template-list-empty').html().replace(/{{ColSpan}}/g, 2) );
    }
    $('#inquiry_list_table_tbody').find('tr').eq(0).attr('class', 'active');
}
// 공지사항 TR 클릭이벤트 처리
$('#inquiry_list_table_tbody').delegate('tr', 'click', function (event) {
	$('#inquiry_list_table_tbody tr ').each(function(){
		$(this).attr('class', '');
	});
	$(this).attr('class', 'active');
	var seqNo = $(this).data('seq');
    toggleInquiryList(seqNo);
});

//////////////////////////////////////////////////////////////////////////////
//                           1:1 문의내역 리스트 처리 END
//////////////////////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////////////////////
//1:1 문의내역 파일 처리
//////////////////////////////////////////////////////////////////////////////
function isPossibleSize() {
	let size = 0;
	$('.filebox').children('input').each(function() {
		if(this.files[0] != undefined) {
			size += this.files[0].size;
		}
	});
	
	if( (size/1024/1024) > 50 ) {
		return false;
	} else {
		return true;
	}
}
