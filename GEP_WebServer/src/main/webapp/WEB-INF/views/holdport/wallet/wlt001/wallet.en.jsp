<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/taglib/taglib.jsp" %>

<!--컨텐츠 내용 영역 시작-->
<main id="content" role="main" class="main">

    <article class="layoutB" id="like_wallet">

    </article>
</main>
<!--컨텐츠 내용 영역 끝-->


<!--입금코드 발생 안내 팝업 시작-->
<div class="popup deposit-code">
    <div class="pop-title">
        <span>영어 입금신청 확인</span>
        <%-- <span class="top-close top-close-deposit-code">
            <img src="/resources/img/btn-alert-close.png" alt="닫기버튼">
        </span> --%>

    </div>
    <div class="popup-cont">
        <div class="content-item">
            <div>
                <h2 class="tabletitle">받는 사람</h2>
                <table>
                    <colgroup>
                        <col style="width:110px;">
                        <col>
                    </colgroup>
                    <tbody>
                        <tr>
                            <th>계좌번호</th>
                            <td id="popup-deposit-code-exchange-account-number"></td>
                        </tr>
                        <tr>
                            <th>입금은행</th>
                            <td id="popup-deposit-code-exchange-bank-name"></td>
                        </tr>
                        <tr>
                            <th>계좌 소유주</th>
                            <td id="popup-deposit-code-exchange-account-holder-name"></td>
                        </tr>
                    </tbody>
                </table>
            </div>

            <div>
                <h2 class="tabletitle">보내는 사람</h2>
                <table>
                    <colgroup>
                        <col style="width:110px;">
                        <col>
                    </colgroup>
                    <tbody>
                        <tr>
                            <th>등록된계좌</th>
                            <td id="popup-deposit-code-sender-account-number"></td>
                        </tr>
                        <tr>
                            <th>은행명</th>
                            <td id="popup-deposit-code-sender-bank-name"></td>
                        </tr>
                        <tr>
                            <th>계좌 소유주</th>
                            <td id="popup-deposit-code-sender-account-holder-name"></td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>

        <div class="content-item">
            <table class="mgt20">
                <colgroup>
                    <col style="width:160px">
                    <col>
                </colgroup>
                <tbody>
                    <tr>
                        <th>
                            입금코드
                        </th>
                        <td>
                            <span id="popup-deposit-code-sender-deposit-code" class="code"></span>
                            <button onClick="copyToClipBoardDepositCode()">복사하기</button>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            입금금액
                        </th>
                        <td id="popup-deposit-code-sender-amount"></td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <span>위 입금코드를 <span class="red">받는통장메모</span>에 반드시 기재하십시오</span>
                        </td>
                    </tr>
                </tbody>
            </table>

            <table class="mgt20">
                <colgroup>
                    <col style="width: 110px">
                    <col>
                    <col style="width: 110px">
                    <col>
                </colgroup>
                <thead>
                    <tr>
                        <th>은행</th>
                        <th>입금코드 입력란 명칭</th>
                        <th>은행</th>
                        <th>입금코드 입력란 명칭</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <th>NH농협은행</th>
                        <td>입금통장표시내용</td>
                        <th>카카오뱅크</th>
                        <td>받는 분에게 표시</td>

                    </tr>
                    <tr>
                        <th>KB국민은행</th>
                        <td>입금통장표시내용</td>
                        <th>케이뱅크</th>
                        <td>받는분통장표시</td>
                    </tr>
                    <tr>
                        <th>KEB하나은행</th>
                        <td>받는분 통장 표시</td>
                        <th>한국씨티은행</th>
                        <td>입금통장 표시내용</td>
                    </tr>
                    <tr>
                        <th>신한은행</th>
                        <td>받는통장 메모</td>
                        <th>SC제일은행</th>
                        <td>받는통장에 남길말</td>
                    </tr>
                    <tr>
                        <th>IBK기업은행</th>
                        <td>받는분표시내용</td>
                        <th>KDB산업은행</th>
                        <td>입금계좌기록내용</td>
                    </tr>
                    <tr>
                        <th>우리은행</th>
                        <td>받는분 통장 표시내용<br>(받는통장표시내용)</td>
                        <th>부산은행</th>
                        <td>입금계좌기록사항</td>
                    </tr>
                    <tr>
                        <th>우체국</th>
                        <td>입금통장표시</td>
                        <th>대구은행</th>
                        <td>입금통장인지내용</td>
                    </tr>
                    <tr>
                        <th>새마을금고</th>
                        <td>입금통장표시내용</td>
                        <th></th>
                        <td></td>
                    </tr>
                </tbody>
            </table>
        </div>

        <!-- 확인 버튼 : Start -->
        <div class="btn-group mgt20">
            <button onClick="moneyChargingRequest()" class="blue big">확인</button>
            <%-- <button onClick="$('.deposit-code').hide()" class="big">취소</button> --%>
        </div>
        <!-- //확인 버튼 : End -->

    </div>

</div>
<!--입금코드 발생 안내 팝업 끝-->

<!--은행별 점검시간 안내 팝업 시작-->
<div class="popup bank">
    <div class="pop-title">
        <span>은행별 점검시간 안내</span>
        <span class="top-close top-close-bank">
            <img src="/resources/img/btn-alert-close.png" alt="닫기버튼">
        </span>
    </div>
    <div class="popup-cont">
        <h2>은행별 점검시간 안내</h2>

        <p>- KRW 출금은 은행과 연계되어있기 때문에 은행 점검시간(비정기 점검 포함)에는 출금이 제한됩니다.</p>
        <p>- 하단 은행 점검시간 및 정기점검을 확인하시기 바랍니다.</p>
        <table class="mgt10">
            <colgroup>
                <col style="width: 140px ">
                <col>
                <col>
            </colgroup>
            <thead>
                <tr>
                    <th>은행</th>
                    <th>점검시간</th>
                    <th>정기점검</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>NH농협은행</td>
                    <td>00:00~00:30</td>
                    <td>00:00~04:00 (매월 셋째주 월요일)</td>
                </tr>
                <tr>
                    <td>KB국민은행</td>
                    <td>23:50~00:05</td>
                    <td> 00:00~07:00 (매월 셋째주 일요일)</td>
                </tr>
                <tr>
                    <td>KEB하나은행</td>
                    <td>00:00~00:10</td>
                    <td>00:00~04:00 (매월 셋째주 월요일)</td>
                </tr>
                <tr>
                    <td>신한은행</td>
                    <td>00:00~00:10</td>
                    <td></td>
                </tr>
                <tr>
                    <td>IBK기업은행</td>
                    <td>00:00~00:30</td>
                    <td></td>
                </tr>
                <tr>
                    <td>우리은행</td>
                    <td>(평일)00:00~00:10, (금요일)00:00~00:40</td>
                    <td>02:00~06:00 (매월 둘째주 일요일)</td>
                </tr>
                <tr>
                    <td>우체국</td>
                    <td>04:00~05:00</td>
                    <td></td>
                </tr>
                <tr>
                    <td>새마을금고</td>
                    <td></td>
                    <td>23:00~07:00 (매월 두번째 토요일)</td>
                </tr>
                <tr>
                    <td>카카오뱅크</td>
                    <td>23:57~00:03</td>
                    <td></td>
                </tr>
                <tr>
                    <td>케이뱅크</td>
                    <td>23:50~00:10</td>
                    <td></td>
                </tr>
                <tr>
                    <td>한국씨티은행</td>
                    <td>23:40~00:05</td>
                    <td></td>
                </tr>
                <tr>
                    <td>SC제일은행</td>
                    <td>23:59~00:05</td>
                    <td></td>
                </tr>
                <tr>
                    <td>KDB산업은행</td>
                    <td>(월~토)00:00~0:30, (일요일)00:00~04:30</td>
                    <td></td>
                </tr>
                <tr>
                    <td>부산은행</td>
                    <td></td>
                    <td>00:00~03:00 (매월 첫째주 월요일, 셋째주 월요일)</td>
                </tr>
                <tr>
                    <td>대구은행</td>
                    <td></td>
                    <td>02:00~06:00 (매월 셋째주 일요일)</td>
                </tr>

            </tbody>
        </table>
        <div class="btn-group mgt20">
            <button type="submit" class="top-close top-close-bank big">확인 </button>
        </div>
    </div>

</div>
<!--은행별 점검시간 안내 팝업 끝-->
<!--코인입출금 상세 안내 팝업 시작-->
<div class="popup detail">
    <div class="pop-title">
        <span>암호화폐 입출금 상세보기</span>
        <span class="top-close top-close-detail">
            <img src="/resources/img/btn-alert-close.png" alt="닫기버튼">
        </span>
    </div>
    <div class="popup-cont">
        <div>
            <h2>입출금 요청 정보</h2>
            <table class="line">
                <colgroup>
                    <col style="width:120px;">
                    <col>
                </colgroup>
                <tbody>
                    <tr>
                        <th>암호화폐명</th>
                        <td><span class="coin-dw-detail-popup-coin-name"></span></td>
                    </tr>
                    <tr>
                        <th>입출금구분</th>
                        <td id="coin-dw-detail-popup-dw-req-type-code"></td>
                    </tr>
                    <tr>
                        <th>입출금요청수량</th>
                        <td><span id="coin-dw-detail-popup-req-qty"></span> <span class="coin-dw-detail-popup-coin-symbol">BTC</span></td>
                    </tr>
                    <tr>
                        <th>수수료</th>
                        <td><span id="coin-dw-detail-popup-fee-qty"></span> <span class="coin-dw-detail-popup-coin-symbol">BTC</span></td>
                    </tr>
                    <tr>
                        <th>요청일시</th>
                        <td><span id="coin-dw-detail-popup-req-datetime"></span></td>
                    </tr>
                    <tr>
                        <th>요청매체</th>
                        <td><span id="coin-dw-detail-popup-req-channel"></span></td>
                    </tr>
                    <tr>
                        <th>승인일시</th>
                        <td><span id="coin-dw-detail-popup-appr-datetime"></span></td>
                    </tr>
                    <tr>
                        <th>상태</th>
                        <td><span id="coin-dw-detail-popup-proc-status"></span></td>
                    </tr>
                </tbody>
            </table>
            <h2 class="mgt20">블록체인 처리 정보</h2>
            <table class="line">
                <colgroup>
                    <col style="width:120px;">
                    <col>
                </colgroup>
                <tbody>
                    <tr>
                        <th>계좌정보</th>
                        <td><span id="coin-dw-detail-popup-account-info"></span></td>
                    </tr>
                    <tr>
                        <th>입출금주소</th>
                        <td><span id="coin-dw-detail-popup-coin-address"></span></td>
                    </tr>
                    <tr>
                        <th>트랜잭션ID</th>
                        <td><span id="coin-dw-detail-popup-txid"></span></td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="btn-group mgt20">
            <button class="top-close top-close-detail big">확인 </button>
        </div>
    </div>

</div>
<!--코인입출금 상세 안내 팝업 끝-->

<script src="/resources/js/holdport/wallet/holdport.wallet.js?v=<spring:message code="holdport.wallet.js.version"/>"></script>
<script src="/resources/js/qrcode/qrcode.min.js"></script>

<script>
    $(document).ready(function() {
        // 회원 기본정보 셋팅
        __G_User_Auth_Level = parseInt('${authLevel}');
        __G_User_Auth_Mean_Code = parseInt('${authMeanCd}');
        __G_Default_Coin_Type = parseInt('${defaultCoinType}');

        // 은행코드 정보 셋팅
        __G_Bank_Code_List = JSON.parse('${bankCodeInfoList}');
        /*
        $('#money-tab-1-bank-list').empty();
        $('#money-tab-1-bank-list').append($('<option value="9999">은행선택</option>'));
        */
        __G_Bank_Code_List.forEach(function(item) {
            //$('#money-tab-1-bank-list').append($('<option value="' + item.CODE_STR_VAL + '">' + item.CODE_VAL_NM + '</option>'));
            _G_Bank_Code_Map.put(item.CODE_STR_VAL, item.CODE_VAL_NM);
        });

        // 거래소 은행 입금계좌 셋팅
        __G_Exchange_Bank_Accnt_Info = JSON.parse('${exchangeBankAccntInfo}');
        /*
        $('#money-tab-1-exchange-account-number').text( getParam(__G_Exchange_Bank_Accnt_Info, 'BANK_ACCNT_NO', '') );
        $('#money-tab-1-exchange-bank-name').text( _G_Bank_Code_Map.get(getParam(__G_Exchange_Bank_Accnt_Info, 'BANK_CD', '')) );
        $('#money-tab-1-exchange-account-holder-name').text( getParam(__G_Exchange_Bank_Accnt_Info, 'ACCNT_HOLDER_NM', '') );
        // Popup 셋팅
        $('#popup-deposit-code-exchange-account-number').text( getParam(__G_Exchange_Bank_Accnt_Info, 'BANK_ACCNT_NO', '') );
        $('#popup-deposit-code-exchange-bank-name').text( _G_Bank_Code_Map.get(getParam(__G_Exchange_Bank_Accnt_Info, 'BANK_CD', '')) );
        $('#popup-deposit-code-exchange-account-holder-name').text( getParam(__G_Exchange_Bank_Accnt_Info, 'ACCNT_HOLDER_NM', '') );
        */

        // 코인관리 기준정보 가져옴
        var coinMgrRefinfoJson = JSON.parse('${coinMgtRefInfo}');
        Object.keys(coinMgrRefinfoJson).forEach(function(key) {
            __G_CoinMgtRef_Map.put(key, coinMgrRefinfoJson[key]);
        });

        //입출금내역 달력 설정
        /*
        $("#money-tab-3-dw-list-endDate").datepicker({
            inline: true,
            dateFormat: 'yy.mm.dd',
            maxDate: $.datepicker.formatDate('yy.mm.dd', new Date()),

            onSelect: function() {
                $("#money-tab-3-dw-list-startDate").datepicker("option","maxDate", $("#money-tab-3-dw-list-endDate").datepicker('getDate'))
                if ( $.datepicker.formatDate('yymmdd', $("#money-tab-3-dw-list-endDate").datepicker('getDate'))
                        < $.datepicker.formatDate('yymmdd', $("#money-tab-3-dw-list-startDate").datepicker('getDate')) ) {
                    $('#money-tab-3-dw-list-startDate').val($("#money-tab-3-dw-list-endDate").datepicker('getDate'));
                }
            }
        });
        $("#money-tab-3-dw-list-startDate").datepicker({
            inline: true,
            dateFormat: 'yy.mm.dd',
            maxDate: $("#money-tab-3-dw-list-endDate").datepicker('getDate')
        });




        $("#coin-tab-3-dw-list-endDate").datepicker({
            inline: true,
            dateFormat: 'yy.mm.dd',
            maxDate: $.datepicker.formatDate('yy.mm.dd', new Date()),

            onSelect: function() {
                $("#coin-tab-3-dw-list-startDate").datepicker("option","maxDate", $("#coin-tab-3-dw-list-endDate").datepicker('getDate'))
                if ( $.datepicker.formatDate('yymmdd', $("#coin-tab-3-dw-list-endDate").datepicker('getDate'))
                        < $.datepicker.formatDate('yymmdd', $("#coin-tab-3-dw-list-startDate").datepicker('getDate')) ) {
                    $('#coin-tab-3-dw-list-startDate').val($("#coin-tab-3-dw-list-endDate").datepicker('getDate'));
                }
            }
        });
        $("#coin-tab-3-dw-list-startDate").datepicker({
            inline: true,
            dateFormat: 'yy.mm.dd',
            maxDate: $("#coin-tab-3-dw-list-endDate").datepicker('getDate')
        });

        // 오늘 날짜로 세팅
        $('#money-tab-3-dw-list-startDate').val($.datepicker.formatDate('yy.mm.dd', new Date()));
        $('#money-tab-3-dw-list-endDate').val($.datepicker.formatDate('yy.mm.dd', new Date()));
        $('#coin-tab-3-dw-list-startDate').val($.datepicker.formatDate('yy.mm.dd', new Date()));
        $('#coin-tab-3-dw-list-endDate').val($.datepicker.formatDate('yy.mm.dd', new Date()));
        */

        // 기본 Money Tab 보이고 Coin Tab 감춘다.
        //$('#tab-group-1').show();
        //$('#tab-group-2').hide();
        __G_Prev_Selected_CoinNo = '10';
        //doProcessRFrame('MONEY', '10', 'KRW', 'KRW');

        // LNB영역 기본 Coin List 그리기 (기본 통화 및 거래소 상장 코인)
        //drawLnbList('${defaultCoinInfo}', '${exchangeCoinInfo}');
        // 사용자 Possession 정보 가져오기
        ////setTimeout(getLnbPossessionInfo, 50);
        //getLnbPossessionInfo();

        // 회원의 은행계좌정보를 가져온다.
        //getUserBankAccntInfo();
    });

    // 전체동의 check
    $('#agreeAll').on('click', function() {
		if($(this).is(':checked')) {
			$('.agree-box').find('.agreeCheck').prop('checked', $(this).is(':checked'));
		} else {
			$('.agree-box').find('input').prop('checked', $(this).is(':checked'));
		}
	});

    // 보유코인 체크박스 클릭
    $('#chk_box_possess_coin').click(function() {
        var checked = $("input:checkbox[id='chk_box_possess_coin']").is(":checked");
        showOnlyPossessedCoin(checked);
    });
    // 코인 심볼/이름 검색
    $('#search_coin_text').keyup(function() {
        searchCoinSymbolOrName($(this).val());
    });
    /*북마크*/
    $('.bookmark').click(function() {
        $(this).toggleClass('mark-on');
    });

    // 은행시간, 입금코드, 입출금상세내역 팝업 열고 닫기
    $(document).ready(function() {
        $('.pop-bank').click(function() {
            $('.bank').show();
        });
        /* $('.pop-code').click(function() {
            $('.deposit-code').show();
        }); */

        $('.top-close-deposit-code').click(function() {
            $('.deposit-code').hide();
        });
        $('.top-close-bank').click(function() {
            $('.bank').hide();
        });
        $('.top-close-detail').click(function() {
            $('.detail').hide();
        });
    });

    $('#money-tab-1-bank-account-holder-name').on('keyup', function() {
    	let $this = $(this);
    	$this.val($this.val().replace(/[^A-Za-z0-9가-힣ㄱ-ㅎㅏ-ㅣ]/gi, ''));
    });

    $('#money-tab-1-bank-account-holder-birthday').on('keyup', function() {
    	let $this = $(this);
    	$this.val($this.val().replace(/[^0-9]/g, ''));
    });

    $('#money-tab-1-bank-account-number').on('keyup', function() {
    	let $this = $(this);
    	$this.val($this.val().replace(/[^0-9]/g, ''));
    });
</script>

<Script id="template-list-empty" type="text/template">
	<tr>
        <td colspan="{{ColSpan}}">You have no data </td>
	</tr>
</Script>
<Script id="template-dw-list-empty" type="text/template">
	<tr>
        <td colspan="{{ColSpan}}">You have no Deposit/Withdraw History </td>
	</tr>
</Script>

<script id="template-wallet-lnb-coin-list" type="text/template">
    <tr class="{{IsActive}}">
        <td>
            <input type="hidden" name="coinKind" class="coinKind" value="{{CoinKind}}" />
            <input type="hidden" name="coinNo" class="coinNo" value="{{CoinNo}}" />
            <input type="hidden" name="coinSymbolChosung" class="coinSymbolChosung" value="{{CoinSymbolChosung}}" />
            <span class="coin-symbol">
                <img src="/resources/img/coin-symbol/{{CoinSymbolicName}}.png" alt="coin">
            </span>
        </td>
        <td>
            <p class="coin-name">{{ItemDemesticName}}</p>
            <p class="coin-ratio">
                <span id="possession-percent-{{CoinNo}}" class="ratio-value" style="width: {{PossessPercent}}%;"></span>
            </p>
        </td>
        <td>
            <p class="vertual-coin-type">
                <span id="possession-qty-{{CoinNo}}">{{PossessQty}}</span>
                <span>{{CoinSymbolicName}}</span>
            </p>
            <p class="country-real-money">
                <span id="estimate-qty-{{CoinNo}}">{{EstimateQty}}</span>
                <span>{{BaseEstimateSymbolicName}}</span>
            </p>
        </td>
    </tr>
</script>

<script id="template-wallet-deposit-withdraw-list" type="text/template">
    <tr onClick="doActionClickDwListTr('{{ReqSeqNo}}')">
        <td class="{{DwGubunColor}} alCenter">
            <input type="hidden" class="coin-tab-3-dw-list-req-seq-no" value="{{ReqSeqNo}}">
            {{DwGubun}}
        </td>
        <td class="alCenter">{{ReqDateTime}}</td>
        <td class="{{DwGubunColor}} alRight">{{ReqQty}}</td>
        <td class="alRight">{{Fee}}</td>
        <td onclick="event.cancelBubble = true;">
            <p class="ellips-group">
                <span onClick="doActionClickDwListTr('{{ReqSeqNo}}')" class="text-ellips">{{Address}}</span>
                <button onClick="copyToClipBoardAddress1('{{Address}}')">복사</button>
            </p>
        </td>
        <td class="alCenter">{{ReqStatProcText}}</td>
        <td onclick="event.cancelBubble = true;" class="alCenter">
            {{CancelButtonHTML}}
        </td>
    </tr>
</script>

<script id="template-wallet-money-deposit-withdraw-list" type="text/template">
    <tr>
        <td class="{{DwGubunColor}} alCenter">
            <input type="hidden" class="money-tab-3-dw-list-req-seq-no" value="{{ReqSeqNo}}">
            {{DwGubun}}
        </td>
        <td class="alCenter">{{ReqDateTime}}</td>
        <td class="{{DwGubunColor}} alRight">{{ReqQty}}</td>
        <td class="alRight">{{Fee}}</td>
        <td class="alCenter">{{BankName}}</td>
        <td class="alCenter">{{Address}}</td>
        <td class="alCenter">{{FinCode}}</td>
        <td class="alCenter">{{ReqStatProcText}}</td>
        <td class="alCenter">
            {{CancelButtonHTML}}
        </td>
    </tr>
</script>

<script type="module" src="/resources/js/react/wallet_bundle.js"></script>