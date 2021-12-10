<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/taglib/taglib.jsp" %>


<!--컨텐츠 내용 영역 시작-->
<section class="main">

    <article class="layoutB">

        <aside>
            <ul>
                <li>
                    <div class="whole-asset-group">
                        <span class="txt-box">총 평가액</span>
                        <span id="total_estimated_qty" class="asset-value">0</span>
                        <span class="asset-unit">KRW</span>
                    </div>
                </li>
                <li>
                    <div class="sch-coin-group">
                        <p class="sch-coin-input" style="width:100%;">
                            <input type="text" id="search_coin_text" name="" value="" placeholder="암호화폐명/심볼 검색" class="sch-input">
                        </p>
                        <!-- <span class="checks-item">
                            <input type="checkbox" id="chk_box_possess_coin">
                            <label for="chk_box_possess_coin">보유</label>
                        </span> -->

                    </div>
                </li>

                <!-- 코인 리스트. 10개 이상일 때는 스크롤 생김 : Start -->
                <li class="coin-list-wrap mCustomScrollbar">
                    <table class="coin-list-inner">
                        <colgroup>
                            <col style="width:30px;">
                            <col style="width:80px;">
                            <col>
                        </colgroup>
                        <tbody id="wallet-lnb-coin-list-tbody">
                            <!-- 리스트 1개 반복 : Start -->
                            <tr class="active">
                                <td>
                                    <span class="coin-symbol">
                                        <img src="/resources/img/coin-symbol/KRW.png" alt="coin">
                                    </span>
                                </td>
                                <td>
                                    <p class="coin-name">원</p>
                                    <p class="coin-ratio">
                                        <span class="ratio-value" style="width: 30%;"></span>
                                    </p>
                                </td>
                                <td>
                                    <p class="vertual-coin-type">
                                        <span>0</span>
                                        <span>KRW</span>
                                    </p>
                                    <p class="country-real-money">
                                        <span>0</span>
                                        <span>KRW</span>
                                    </p>
                                </td>
                            </tr>
                            <!-- //리스트 1개 반복 : End -->
                        </tbody>

                    </table>
                </li>
                <!-- //코인 리스트. 10개 이상일 때는 스크롤 생김 : End -->
            </ul>
        </aside>
        <!-- //Left side : End -->
        
        <!--입금 출금 탭 영역 시작-->
        <div class="content">
            <!-- CONTENT INNER : Start -->
            <h2 class="subtitle">투자내역</h2>
            <div>
                <!-- 원화일 때 : Start -->
                <div id="tab-group-1">
                    <ul id="invest-tab-ul" class="li5">
                        <li><a id="tabs-a-1" href="#tabs-1">보유내역</a></li>
                        <li><a id="tabs-a-2" href="#tabs-2">거래내역</a></li>
                        <li><a id="tabs-a-3" href="#tabs-3">미체결내역</a></li>
                        <li><a id="tabs-a-4" href="#tabs-4">입출금내역</a></li>
                        <li><a id="tabs-a-5" href="#tabs-5">잔고변경이력</a></li>
                    </ul>

                    <!-- 보유코인 탭 컨텐츠 : Start -->
                    <div id="tabs-1">
                        <div class="content-item">
                            <div class="invest_unit mgt20">
                            <p>※ 편의를 위해 <a href="#" class="pop_krw">KRW 환산 추정치로 표기</a>합니다.</p>
                            </div>
                            <table class="type01">
                                <colgroup>
                                    <col style="width:110px;">
                                    <col>
                                    <col style="width:110px;">
                                    <col>
                                    <col style="width:110px;">
                                    <col>
                                </colgroup>
                                <tbody>
                                    <tr>
                                        <th>총 매수액</th>
                                        <td class="alRight">
                                            <span id="invest-tabs-1-header-total-buy-amt" class="bold"></span>
                                            <span class="gray base-currency-symbol">KRW</span>
                                        </td>
                                        <th>총 평가손익</th>
                                        <td class="alRight">
                                            <span id="invest-tabs-1-header-total-profit-or-loss-amt" class="blue bold"></span>
                                            <span class="gray base-currency-symbol">KRW</span>
                                        </td>
                                         <th>총 평가수익률</th>
                                        <td class="alRight">
                                            <span id="invest-tabs-1-header-total-profit-or-loss-per" class="blue bold"></span>
                                            <span class="gray">%</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>총 보유 암호화폐</th>
                                        <td class="alRight">
                                            <span id="invest-tabs-1-header-total-estimate-amt" class="bold"></span>
                                            <span class="gray base-currency-symbol">KRW</span>
                                        </td>
                                        <th>보유 KRW</th>
                                        <td class="alRight">
                                            <span id="invest-tabs-1-header-total-bc-amt" class="bold"></span>
                                            <span class="gray base-currency-symbol">KRW</span>
                                        </td>
                                        <th>총 평가액</th>
                                        <td class="alRight">
                                            <span id="invest-tabs-1-header-total-possess-amt" class="bold"></span>
                                            <span class="gray base-currency-symbol">KRW</span>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                            
                            
                            <table class="line">
                                    <colgroup>
                                        <col style="width: 50px">
                                        <col>
                                        <col>
                                        <col>
                                        <col>
                                        <col>
                                        <col>
                                        <col style="width: 70px">
                                        
                                    </colgroup>
                                    <thead>
                                        <tr>
                                            <th></th>
                                            <th>보유암호화폐</th>
                                            <th>수량</th>
                                            <th>매수평균가</th>
                                            <th class="alRight">매수액</th>
                                            <th class="alRight">평가액</th>
                                            <th class="alRight">수익률</th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody id="invest-tabs-1-user-possess-coin-list-tbody">
                                    <!-- 기본 : 총 10개의 Row가 항상 있어야 함. -->
                                </tbody>
                            </table>

                            <!-- 페이징 : Start -->
                            <ul id="invest-tabs-1-pagination" class="pagination">
                                <li><a class="btn-page first"></a></li>
                                <li><a class="btn-page prev"></a></li>
                                <li><a href="#" class="active">1</a></li><!-- 선택된 번호의 a 태그에 acitve 클래스 추가 -->
                                <li><a class="btn-page next"></a></li>
                                <li><a class="btn-page last"></a></li>
                            </ul>
                            <!-- //페이징 : End -->
                            
                        </div>

                    </div>
                    <!-- //보유코인 탭 컨텐츠 : End -->

                    <!-- 거래내역 탭 컨텐츠 : Start -->
                    <div id="tabs-2">
                        <div>
                            <!-- 조회 옵션 : Start -->
                            <div class="mgt20 dw-history">
                                <span class="option-title">종목</span>
                                <select id="invest-tabs-2-coin-select" onChange="getUsrTradingHistoryList()" class="select-box">
                                    <option value="9999" selected>암호화폐 선택</option>
                                </select>

                                <span>
                                    <span class="checks-item">
                                        <input type="checkbox" id="invest-tabs-2-checkbox-buy">
                                        <label for="invest-tabs-2-checkbox-buy">매수</label>
                                    </span>
                                    <span class="checks-item">
                                        <input type="checkbox" id="invest-tabs-2-checkbox-sell">
                                        <label for="invest-tabs-2-checkbox-sell">매도</label>
                                    </span>
                                </span>
                                <div class="calendar">
                                    <input type="text" name="calendar" class="startDate" id="invest-tabs-2-startDate" required="" value="">
                                    <span>~</span>
                                    <input type="text" name="calendar" class="endDate" id="invest-tabs-2-endDate" required="" value="">
                                    <button onClick="getUsrTradingHistoryList()">조회</button>
                                </div>
                            </div>

                            <!-- //조회 옵션 : End -->
                            <!-- 조회 건수 표시 : Start -->

                            <p class="case-info">총 <b class="blue"><span id="invest-tabs-2-list-total-count">0</span></b> 건의 자료가 조회 되었습니다</p>
                            <!-- //조회 건수 표시 : End -->
                            <div>
                                <table class="line">
                                    <colgroup>
                                        <col style="width:130px;">
                                        <col style="width:90px;">
                                        <col style="width:50px;">
                                        <col style="width:110px;">
                                        <col>
                                        <col>
                                        <col>
                                        <col>
                                    </colgroup>
                                    <thead>
                                        <tr>
                                            <th>주문접수시간</th>
                                            <th>종목</th>
                                            <th>매매구분</th>
                                            <th class="alRight">주문가</th>
                                            <th class="alRight">주문수량</th>
                                            <th class="alRight">체결가격</th>
                                            <th class="alRight">체결수량</th>
                                            <th class="alRight">체결금액</th>
                                        </tr>
                                    </thead>
                                    <tbody id="invest-tabs-2-trading-history-list-tbody">
                                        <!-- 기본 : 총 10개의 Row가 항상 있어야 함. -->
                                        <!-- <tr>
                                            <td class="alCenter">2017-02-02 08:00:00</td>
                                            <td class="alCenter">LTC/KRW</td>
                                            <td class="alCenter red">매수</td>
                                            <td class="alRight">336,000</td>
                                            <td class="alRight">40.00000000</td>
                                            <td class="alRight">19,782,000</td>
                                            <td class="alRight">41,663,662.000</td>
                                            <td class="alRight">10,100,000,440,011</td>
                                        </tr> -->
                                    </tbody>
                                </table>
                                <!-- //데이터 영역 : End -->
                                <!-- 페이징 : Start -->
                                <ul id="invest-tabs-2-pagination" class="pagination">
                                    <li><a class="btn-page first"></a></li>
                                    <li><a class="btn-page prev"></a></li>
                                    <li><a href="#" class="active">1</a></li><!-- 선택된 번호의 a 태그에 acitve 클래스 추가 -->
                                    <li><a class="btn-page next"></a></li>
                                    <li><a class="btn-page last"></a></li>
                                </ul>
                                <!-- //페이징 : End -->
                            </div>
                        </div>
                    </div>
                    <!-- //거래내역 탭 컨텐츠 : End -->

                    <!-- 미체결내역 탭 컨텐츠 : Start -->
                    <div id="tabs-3">
                        <div class="dw-history">
                            <!-- 조회 옵션 : Start -->
                            <div class="mgt20">
                                <span class="option-title">종목</span>
                                <select id="invest-tabs-3-coin-select" onChange="getUsrNonContractList(true)" class="select-box">
                                    <option value="9999" selected>선택</option>
                                </select>

                                <!-- <div class="calendar">
                                    <input type="text" name="calendar" class="startDate" id="invest-tabs-3-startDate" required="" value="">
                                    <span>~</span>
                                    <input type="text" name="calendar" class="endDate" id="invest-tabs-3-endDate" required="" value="">
                                    <button onClick="getUsrNonContractList()">조회</button>
                                </div> -->
                            </div>
                            <!-- //조회 옵션 : End -->
                            <!-- 조회 건수 표시 : Start -->
                            <p class="case-info">총 <b class="blue"><span id="invest-tabs-3-list-total-count">0</span></b> 건의 자료가 조회 되었습니다</p>
                            <!-- //조회 건수 표시 : End -->
                            <!-- 데이터 영역 : Start -->
                            <dl class="dl-tit1">
                                <dt>주문접수시간</dt>
                                <dd>주문번호</dd>
                                <dd>종목</dd>
                                <dd>매매구분</dd>
                                <dd>주문가</dd>
                                <dd>주문수량</dd>
                                <dd>체결수량</dd>
                                <dd>미체결수량</dd>
                                <dd>취소</dd>
                            </dl>
                            <div id="invest-tabs-3-non-contract-history-list-div" class="list-tbl mCustomScrollbar">
                                <table>
                                    <colgroup>
                                        <col>
                                        <col>
                                        <col style="width: 109px">
                                        <col style="width: 60px">
                                        <col>
                                        <col>
                                        <col>
                                        <col>
                                        <col style="width: 60px">
                                    </colgroup>
                                    <tbody id="invest-tabs-3-non-contract-history-list-tbody">
                                        <!-- 기본 : 총 10개의 Row가 항상 있어야 함. -->
                                        <!-- <tr>
                                            <td>02-02 08:00:00</td>
                                            <td class="text-ellips">201712180000004400123455</td>
                                            <td class="alCenter">LTC/KRW</td>
                                            <td class="alCenter red">매수</td>
                                            <td class="alRight">41,663,662.0000</td>
                                            <td class="alRight">41,663,662.0000</td>
                                            <td class="alRight">41,663,662.0000</td>
                                            <td class="alRight">41,663,662.0000</td>
                                            <td><button>취소</button></td>
                                        </tr> -->
                                    </tbody>
                                </table>
                            </div>
                            <!-- //데이터 영역 : End -->
                        </div>
                    </div>
                    <!-- //미체결내역 탭 컨텐츠 : End -->

                    <!-- 입출금대기 탭 컨텐츠 : Start -->
                    <div id="tabs-4">
                        <div class="dw-history">
                            <!-- 조회 옵션 : Start -->
                            <div class="mgt20">
                                <!--<span class="option-title">종목</span>-->
                                <select id="invest-tabs-4-gubun-select" onChange="getDWMgrList(9999)" class="select-box">
                                    <option value="0" selected>전체</option>
                                    <option value="1">입금</option>
                                    <option value="2">출금</option>
                                </select>
                                <span>
                                    <span class="checks-item">
                                        <input type="checkbox" id="invest-tabs-4-checkbox-request">
                                        <label for="invest-tabs-4-checkbox-request">요청</label>
                                    </span>
                                    <span class="checks-item">
                                        <input type="checkbox" id="invest-tabs-4-checkbox-wait">
                                        <label for="invest-tabs-4-checkbox-wait">대기</label>
                                    </span>
                                    <span class="checks-item">
                                        <input type="checkbox" id="invest-tabs-4-checkbox-finish">
                                        <label for="invest-tabs-4-checkbox-finish">완료</label>
                                    </span>
                                </span>

                                <div class="calendar">
                                    <input type="text" name="calendar" class="startDate" id="invest-tabs-4-startDate" required="" value="">
                                    <span>~</span>
                                    <input type="text" name="calendar" class="endDate" id="invest-tabs-4-endDate" required="" value="">
                                    <button onClick="getDWMgrList(9999)">조회</button>
                                </div>
                            </div>
                            <!-- //조회 옵션 : End -->
                            <!-- 조회 건수 표시 : Start -->
                            <p class="case-info">총 <b class="blue"><span id="invest-tabs-4-list-total-count">0</span></b> 건의 자료가 조회 되었습니다</p>
                            <!-- //조회 건수 표시 : End -->
                            <!-- 데이터 영역 : Start -->
                            <div>
                                <table class="line">
                                    <colgroup>
                                        <col>
                                        <col style="width: 120px">
                                        <col style="width: 100px">
                                        <col>
                                        <col>
                                    </colgroup>
                                    <thead>
                                        <tr>
                                            <th>신청확인시간</th>
                                            <th>암호화폐</th>
                                            <th>거래종류</th>
                                            <th class="alRight">주문수량</th>
                                            <th>상태</th>
                                        </tr>
                                    </thead>
                                    <tbody id="invest-tabs-4-deposit-withdraw-waiting-history-list-tbody">
                                        <!-- 기본 : 총 10개의 Row가 항상 있어야 함. -->
                                        <!-- 1개의 Row : Start -->
                                        <!-- <tr>
                                            <td class="alCenter">2017-02-02 08:00:00</td>
                                            <td class="alCenter">LTC/KRW</td>
                                            <td class="alCenter red">매수</td>
                                            <td class="alRight">40.00000000</td>
                                            <td class="alCenter">상태</td>
                                        </tr> -->
                                        <!-- //1개의 Row : End -->
                                    </tbody>
                                </table>
                                <!-- 페이징 : Start -->
                                <ul id="invest-tabs-4-pagination" class="pagination">
                                    <li><a class="btn-page first"></a></li>
                                    <li><a class="btn-page prev"></a></li>
                                    <li><a href="#" class="active">1</a></li><!-- 선택된 번호의 a 태그에 acitve 클래스 추가 -->
                                    <li><a class="btn-page next"></a></li>
                                    <li><a class="btn-page last"></a></li>
                                </ul>
                                <!-- //페이징 : End -->
                            </div>
                            <!-- //데이터 영역 : End -->
                        </div>
                    </div>
                    <!-- //입출금대기 탭 컨텐츠 : End -->

                    <!--잔고변경이력 시작-->
                    <div id="tabs-5">
                    <div class="dw-history">
                            <!-- 조회 옵션 : Start -->
                            <div class="mgt20">
                                <!--<span class="option-title">종목</span>-->
                                <select id="invest-tabs-5-coin-select" class="select-box">
                                    <option selected>전체</option>
                                </select>
                                <select id="invest-tabs-5-change-reason-select" class="select-box">
                                    <option value="9999" selected>변경사유</option>
                                </select>

                                <div class="calendar">
                                    <input type="text" name="calendar" class="startDate" id="invest-tabs-5-startDate" required="" value="2019-04-14">
                                    <span>~</span>
                                    <input type="text" name="calendar" class="endDate" id="invest-tabs-5-endDate" required="" value="2019-04-15">
                                    <button onClick="getBalanceChangeHistoryList()">조회</button>
                                </div>
                            </div>
                            <!-- //조회 옵션 : End -->
                            <!-- 조회 건수 표시 : Start -->
                            <p class="case-info">총 <b id="invest-tabs-5-list-total-count" class="blue">0</b>건의 자료가 조회 되었습니다</p>
                            <!-- //조회 건수 표시 : End -->

                            <!-- 데이터 영역 : Start -->
                            <div>
                                <table class="line">
                                    <colgroup>
                                        <col>
                                        <col style="width: 130px">
                                        <col>
                                        <col>
                                        <col>
                                        <col>
                                    </colgroup>
                                    <thead>
                                        <tr>
                                            <th>변경일시</th>
                                            <th>암호화폐</th>
                                            <th>변경사유</th>
                                            <th class="alRight">변경수량</th>
                                            <th class="alRight">수수료</th>
                                            <th class="alRight">변경수량(수수료반영)</th>

                                        </tr>
                                    </thead>
                                    <tbody id="invest-tabs-5-balance-change-history-list-tbody">
                                        <!-- 1개의 Row : Start -->
                                        <tr>
                                            <td class="alCenter"></td>
                                            <td class="alCenter"></td>
                                            <td></td>
                                            <td class="alRight"></td>
                                            <td class="alRight"></td>
                                            <td class="alRight"></td>
                                        </tr>
                                        <!-- //1개의 Row : End -->
                                    </tbody>
                                </table>
                                <!-- 페이징 : Start -->
                                <ul id="invest-tabs-5-pagination" class="pagination">
                                    <li><a class="btn-page first"></a></li>
                                    <li><a class="btn-page prev"></a></li>
                                    <li><a href="#" class="active">1</a></li><!-- 선택된 번호의 a 태그에 acitve 클래스 추가 -->
                                    <li><a class="btn-page next"></a></li>
                                    <li><a class="btn-page last"></a></li>
                                </ul>
                                <!-- //페이징 : End -->
                            </div>
                            <!-- //데이터 영역 : End -->
                        </div>
                    </div>
                    <!--잔고변경이력 끝-->

                </div>
                <!-- // 원화일 때 : End -->
            </div>
            <!-- //CONTENT INNER : End -->
        </div>
        <!--입금 출금 탭 영역 끝-->
    </article>

</section>
<!--컨텐츠 내용 영역 끝-->


<!--투자내역 환산 추정치 안내 팝업 시작-->
<div class="popup-wrap popup-estimate-info"> 
   <!-- ALTER. 기본 : Start -->
   <div class="popup big"> <!--alert : 가로400 big :가로 680-->
       <!-- 팝업내용 : Start -->
       <div class="alert-header">
           <p class="alert-title">투자내역 환산 추정치 안내</p><!-- 팝업 타이틀 -->
           <a class="top-close">
               <img src="/resources/img/btn-alert-close.png" alt="닫기버튼">
           </a><!-- 팝업 닫기 버튼 -->
       </div>
       <div class="alert-content">
           <h1>[투자내역 환산 추정치 안내]</h1>
           <p>※ 보유 암호화폐를 회원 편의를 위해 KRW 환산 추정치로 표기하여 안내 드립니다. KRW 환산 추정치는 단순 참고용으로 거래 및 입출금 기록에 따라 과대 혹은 과소평가 되어 보일 수 있습니다.</p>
           <p>※ Alibit는 KRW 추정치에 대해 보장 및 보증하지 않으며, 제공된 편의정보를 기반으로 한 투자결과에 대해서도 책임지지 않습니다.</p>
           
               <ul class="decimal">
                   <li>회원님의 실제 보유 암호화폐 수량에는 영향을 미치지 않습니다.</li>
                   <li>KRW로 표기된 수치는 회원 편의를 위한 KRW 환산한 추정치로 단순 참고용입니다.</li>
                   <li>암호화폐 입금시 매수평균가는 입금 시점의 시세를 적용합니다.</li>
               </ul>
           
           <h1>[KRW 환산 추정치 안내]</h1>
           
               <ul class="disc">
                   <li>총 보유 암호화폐 : 총 보유한 암호화폐의 KRW환산 추정치 합계</li>
                   <li>총 평가액 : 총 보유 암호화폐 + 보유 KRW</li>
                   <li>
                       매수 평균가 
                       <ul>
                           <li>- 매수 : 매수 직전 매수평균가와 새로운 매수 가격의 평균</li>
                           <li>- 매도 : 직전 매수평균가 유지</li>
                           <li>- 입금 : 입금 직전 매수 평균가와 입금 시 암호 화폐가격의 평균</li>
                           <li>- 출금 : 직전 매수평균가 유지</li>
                       </ul>
                   </li>
                   <li>총 매수액 : 보유 암호화폐의 매수액 총 합 (수량 X 매수평균가)</li>
                   <li>총 암호화폐 : 수량 X 현재가</li>
                   <li>총 평가손익 : 총 암호화폐 - 총 매수액</li>
                   <li>총 평가수익률 : 총 평가손익 / 총 매수액</li>
               </ul>
           <p>※ Free Market의 매수액은 YEP/KRW 시세를 참고하여 KRW로 환산 추정치로 표기합니다.</p>
           <p>※ 보유 암호화폐가 Major Market에 있으면 KRW 현재가 기준, Free Market에 있으면 YEP/KRW 현재가로 환산해서 평가액을 계산합니다.</p>
       </div>
       <!-- //팝업내용 : End -->
       <div class="btn-wrap">
           <button class="blue big top-close">확인 </button>
       </div>
   </div>
   <!-- //ALTER. 기본 : End -->
</div>
<!--투자내역 환산 추정치 안내 팝업 끝-->

<script src="/resources/js/yahobit/wallet/yahobit.invest.js?v=<spring:message code="yahobit.invest.js.version"/>"></script>

<script>
    $(document).ready(function() {
        $("#tab-group-1").tabs();

        // KRW 환산 추정치계산법 팝업 열고 닫기
        $(document).ready(function() {
            $('.pop_krw').click(function() {
                $('.popup-estimate-info').show();
            });
            $('.top-close').click(function() {
                $('.popup-estimate-info').hide();
            });
        });
        
        __G_Default_Coin_Type = '${defaultCoinType}';

        // 코인관리 기준정보 가져옴
        var coinMgrRefinfoJson = JSON.parse('${coinMgtRefInfo}');
        Object.keys(coinMgrRefinfoJson).forEach(function(key) {
            __G_CoinMgtRef_Map.put(key, coinMgrRefinfoJson[key]);
        });

        // LNB영역 기본 Coin List 그리기 (기본 통화 및 거래소 상장 코인)
        drawLnbList('${defaultCoinInfo}', '${exchangeCoinInfo}');
        // 사용자 Possession 정보 가져오기
        getLnbPossessionInfo();
        // 사용자 평가금액 정보 가져오기
        getUserEstimatedPossessionInfo();
        // 사용자의 코인별 평가금액정보 가져오기
        getUserPossessionCoinList();

        // 리스트 달력 설정
        $(".endDate").datepicker({
            inline: true,
            dateFormat: 'yy.mm.dd',
            maxDate: $.datepicker.formatDate('yy.mm.dd', new Date()),
            
            onSelect: function() {
                $(".startDate").datepicker("option","maxDate", $(".endDate").datepicker('getDate'))
                if ( $.datepicker.formatDate('yymmdd', $(".endDate").datepicker('getDate')) 
                        < $.datepicker.formatDate('yymmdd', $(".startDate").datepicker('getDate')) ) {
                    $('.startDate').val($(".endDate").datepicker('getDate'));
                }
            }
        });
        $(".startDate").datepicker({
            inline: true,
            dateFormat: 'yy.mm.dd',
            maxDate: $(".endDate").datepicker('getDate')
        });
    });

    // 오늘 날짜로 세팅
    $('#invest-tabs-2-startDate').val($.datepicker.formatDate('yy.mm.dd', new Date()));
    $('#invest-tabs-2-endDate').val($.datepicker.formatDate('yy.mm.dd', new Date()));
    // $('#invest-tabs-3-startDate').val($.datepicker.formatDate('yy.mm.dd', getBeforeDate(7)) ); // 오늘날짜에서 7일전 날짜로 셋팅
    // $('#invest-tabs-3-endDate').val($.datepicker.formatDate('yy.mm.dd', new Date()));
    $('#invest-tabs-4-startDate').val($.datepicker.formatDate('yy.mm.dd', new Date()));
    $('#invest-tabs-4-endDate').val($.datepicker.formatDate('yy.mm.dd', new Date()));
    $('#invest-tabs-5-startDate').val($.datepicker.formatDate('yy.mm.dd', new Date()));
    $('#invest-tabs-5-endDate').val($.datepicker.formatDate('yy.mm.dd', new Date()));

    // 보유코인 체크박스 클릭
    $('#chk_box_possess_coin').click(function() {
        var checked = $("input:checkbox[id='chk_box_possess_coin']").is(":checked");
        showOnlyPossessedCoin(checked);
    });
    // 코인 심볼/이름 검색
    $('#search_coin_text').keyup(function() {
        searchCoinSymbolOrName($(this).val());
    });

    // 현재날짜에서 며칠전 Date 구하기 함수
    function getBeforeDate(diff) {
        var dt = new Date();
        return new Date(dt.getTime() - (diff * 24 * 60 * 60 * 1000));
    }
</script>

<Script id="template-dw-list-empty" type="text/template">
	<tr>
		<td colspan="7">You have no Deposit/Withdraw History </td>
	</tr>
</Script>
<!-- LNB List -->
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
            <p class="country-real-money" style="display: {{ShowHideOption}}">
                <span id="estimate-qty-{{CoinNo}}">{{EstimateQty}}</span>
                <span>{{BaseEstimateSymbolicName}}</span>
            </p>
        </td>
    </tr>
</script>

<Script id="template-list-empty" type="text/template">
	<tr>
		<td colspan="{{ColSpan}}">You have no data</td>
	</tr>
</Script>

<!-- 보유코인 리스트 -->
<script id="template-wallet-tab-1-possess-coin-list" type="text/template">
    <tr>
        <td>
            <span class="coin-symbol">
                <img src="/resources/img/coin-symbol/{{CoinSymbolicName}}.png" alt="coin">
            </span>
        </td>
        <td>
            <p class="coin-name">{{ItemDemesticName}}</p>
            <p class="gray">{{CoinSymbolicName}}</p>
        </td>
        <td class="alRight">
            <p class="bold">{{PossessQty}}</p>
            <p class="gray">{{CoinSymbolicName}}</p>
        </td>
        <td class="alRight">
            <p class="bold">{{AvgPriceBc}}</p>
            <p class="gray">{{BaseEstimateSymbolicName}}</p>
        </td>
        <td class="alRight">
            <p class="bold">{{BuyAmt}}</p>
            <p class="gray">{{BaseEstimateSymbolicName}}</p>
        </td>
        <td class="alRight">
            <p class="bold">{{EstimatedAmt}}</p>
            <p class="gray">{{BaseEstimateSymbolicName}}</p>
        </td>
        <td class="alRight">
            <p>
                <span class="{{ProfitColor}} bold">{{ProfitLossPer}}</span>
                <span class="gray">%</span>
            </p>
            <p>
                <span class="{{ProfitColor}}">{{ProfitLossAmt}}</span>
                <span class="gray">{{BaseEstimateSymbolicName}}</span>
            </p>
        </td>
        <td class="alRight">
            <button onClick="javascript:window.location.href='/alibit/exchange?itemCode={{CoinSymbolicName}}'">주문</button>
        </td>
    </tr>
</script>
<!-- 거래내역 리스트 -->
<script id="template-wallet-tab-2-trading-history-list" type="text/template">
    <tr>
        <td class="alCenter">{{OrderDateTime}}</td>
        <td class="alCenter">{{ItemName}}</td>
        <td class="alCenter {{SellBuyGubunColor}}">{{SellBuyGubun}}</td>
        <td class="alRight">{{OrderPrice}}</td>
        <td class="alRight">{{OrderQty}}</td>
        <td class="alRight">{{ContractPrice}}</td>
        <td class="alRight">{{ContractQty}}</td>
        <td class="alRight">{{ContractAmt}}</td>
    </tr>
</script>
<!-- 미체결 내역 리스트 -->
<script id="template-wallet-tab-3-non-contract-history-list" type="text/template">
    <tr data-orderno="{{OrderNo}}">
        <td>{{OrderDateTime}}</td>
        <td class="text-ellips">{{OrderNo}}</td>
        <td class="alCenter">{{ItemName}}</td>
        <td class="alCenter {{SellBuyGubunColor}}">{{SellBuyGubun}}</td>
        <td class="alRight">{{OrderPrice}}</td>
        <td class="alRight">{{OrderQty}}</td>
        <td class="alRight">{{ContractQty}}</td>
        <td class="alRight">{{NonContractQty}}</td>
        <td><button onClick="cancelNonContractOrder('{{NonContractOrderNo}}')">취소</button></td>
    </tr>
</script>
<!-- 입출금 대기 리스트 -->
<script id="template-wallet-tab-4-deposit-withdraw-waiting-history-list" type="text/template">
    <tr>
        <td class="alCenter">{{ReqDateTime}}</td>
        <td class="alCenter">{{CoinSymbolicName}}</td>
        <td class="alCenter {{DwGubunColor}}">{{DwGubun}}</td>
        <td class="alRight">{{ReqQty}}</td>
        <td class="alCenter">{{ReqStatProcText}}</td>
    </tr>
</script>
<!-- 잔고 변경이력 리스트 -->
<script id="template-wallet-tab-5-balance-change-history-list" type="text/template">
    <tr>
        <td class="alCenter">{{ChangeDateTime}}</td>
        <td class="alCenter">{{CoinSymbolicName}}</td>
        <td class="alCenter {{PlusOrMinusColor}}">{{ChangeReason}}</td>
		<td class="alRight">{{ChangeQtyWithFee}}</td>
        <td class="alRight">{{Fee}}</td>
        <td class="alRight">{{ChangeQty}}</td>
    </tr>
</script>
