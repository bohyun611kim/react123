<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/taglib/taglib.jsp" %>


<%!
	boolean localeMapValid = false;
	HashMap<String, String> localeMap = null;
%>

<%
	try {

		String locale = (String) request.getAttribute("locale");
		localeMap = new HashMap<String,String>();
	
		if(locale.equalsIgnoreCase("ko")) {
	
			localeMap.put("_L_0001", "총 평가액");
			localeMap.put("_L_0002", "암호화폐명/심볼 검색");
			localeMap.put("_L_0003", "보유");
			localeMap.put("_L_0004", "투자내역");
			localeMap.put("_L_0005", "보유코인");
			localeMap.put("_L_0006", "거래내역");
			localeMap.put("_L_0007", "미체결내역");
			localeMap.put("_L_0008", "입출금대기");
			localeMap.put("_L_0009", "잔고변경이력");
			localeMap.put("_L_0010", "※ 편의를 위해 ");
			localeMap.put("_L_0011", "KRW 환산 추정치로 표기");
			localeMap.put("_L_0012", "합니다.");
			localeMap.put("_L_0013", "총 매수액");
			localeMap.put("_L_0014", "총 평가손익");
			localeMap.put("_L_0015", "총 평가수익률");
			localeMap.put("_L_0016", "총 보유 암호화폐");
			localeMap.put("_L_0017", "보유 KRW");
			localeMap.put("_L_0018", "총 평가액");
			localeMap.put("_L_0019", "소액 코인 숨기기(평가액 1만원 미만)");
			localeMap.put("_L_0020", "자산 상태");
			localeMap.put("_L_0021", "보유 암호화폐");
			localeMap.put("_L_0022", "수량");
			localeMap.put("_L_0023", "매수평균가");
			localeMap.put("_L_0024", "매수액");
			localeMap.put("_L_0025", "평가액");
			localeMap.put("_L_0026", "수익률");
			localeMap.put("_L_0027", "종목");
			localeMap.put("_L_0028", "암호화폐 선택");
			localeMap.put("_L_0029", "매수");
			localeMap.put("_L_0030", "매도");
			localeMap.put("_L_0031", "조회");
			localeMap.put("_L_0032", "총");
			localeMap.put("_L_0033", "건의 자료가 조회 되었습니다");
			localeMap.put("_L_0034", "주문접수시간");
			localeMap.put("_L_0035", "매매구분");
			localeMap.put("_L_0036", "주문가");
			localeMap.put("_L_0037", "주문수량");
			localeMap.put("_L_0038", "체결가격");
			localeMap.put("_L_0039", "체결수량");
			localeMap.put("_L_0040", "체결금액");
			localeMap.put("_L_0041", "선택");
			localeMap.put("_L_0042", "주문번호");
			localeMap.put("_L_0043", "미체결수량");
			localeMap.put("_L_0044", "취소");
			localeMap.put("_L_0045", "전체");
			localeMap.put("_L_0046", "입금");
			localeMap.put("_L_0047", "출금");
			localeMap.put("_L_0048", "요청");
			localeMap.put("_L_0049", "대기");
			localeMap.put("_L_0050", "완료");
			localeMap.put("_L_0051", "신청확인시간");
			localeMap.put("_L_0052", "코인");
			localeMap.put("_L_0053", "거래종류");
			localeMap.put("_L_0054", "상태");
			localeMap.put("_L_0055", "변경사유");
			localeMap.put("_L_0056", "거래일시");
			localeMap.put("_L_0057", "암호화폐");
			localeMap.put("_L_0058", "정산수량");
			localeMap.put("_L_0059", "수수료");
			localeMap.put("_L_0060", "체결수량");
			localeMap.put("_L_0061", "투자내역 환산 추정치 안내");
			localeMap.put("_L_0062", "닫기버튼");
			localeMap.put("_L_0063", "[투자내역 환산 추정치 안내]");
			localeMap.put("_L_0064", "※ 보유 암호화폐를 회원 편의를 위해 KRW 환산 추정치로 표기하여 안내 드립니다. KRW 환산 추정치는 단순 참고용으로 거래 및 입출금 기록에 따라 과대 혹은 과소평가 되어 보일 수 있습니다.");
			localeMap.put("_L_0065", "※ 비타500은 KRW 추정치에 대해 보장 및 보증하지 않으며, 제공된 편의정보를 기반으로 한 투자결과에 대해서도 책임지지 않습니다.");
			localeMap.put("_L_0066", "회원님의 실제 보유 암호화폐 수량에는 영향을 미치지 않습니다.");
			localeMap.put("_L_0067", "KRW로 표기된 수치는 회원 편의를 위한 KRW 환산한 추정치로 단순 참고용입니다.");
			localeMap.put("_L_0068", "암호화폐 입금시 매수평균가는 입금 시점의 시세를 적용합니다.");
			localeMap.put("_L_0069", "[KRW 환산 추정치 안내]");
			localeMap.put("_L_0070", "총 보유 암호화폐 : 총 보유한 암호화폐의 KRW환산 추정치 합계");
			localeMap.put("_L_0071", "총 평가액 : 총 보유 암호화폐 + 보유 KRW");
			localeMap.put("_L_0072", "매수 평균가");
			localeMap.put("_L_0073", "- 매수 : 매수 직전 매수평균가와 새로운 매수 가격의 평균");
			localeMap.put("_L_0074", "- 매도 : 직전 매수평균가 유지");
			localeMap.put("_L_0075", "- 입금 : 입금 직전 매수 평균가와 입금 시 암호 화폐가격의 평균");
			localeMap.put("_L_0076", "- 출금 : 직전 매수평균가 유지");
			localeMap.put("_L_0077", "총 매수액 : 보유 암호화폐의 매수액 총 합 (수량 X 매수평균가)");
			localeMap.put("_L_0078", "총 암호화폐 : 수량 X 현재가");
			localeMap.put("_L_0079", "총 평가손익 : 총 암호화폐 - 총 매수액");
			localeMap.put("_L_0080", "총 평가수익률 : 총 평가손익 / 총 매수액");
			localeMap.put("_L_0081", "※ Free Market의 매수액은 YEP/KRW 시세를 참고하여 KRW로 환산 추정치로 표기합니다.");
			localeMap.put("_L_0082", "※ 보유 암호화폐가 Major Market에 있으면 KRW 현재가 기준, Free Market에 있으면 YEP/KRW 현재가로 환산해서 평가액을 계산합니다.");
			localeMap.put("_L_0083", "확인");
			localeMap.put("_L_0084", "데이터가 없습니다");
			localeMap.put("_L_0085", "주문");
			localeMap.put("_L_0086", "원");
			localeMap.put("_L_0087", "미체결 수량을 제외한 거래 가능한 수량으로 실제 보유수량과 차이가 있습니다.");
			localeMap.put("_L_0089", "실제 보유수량은 투자내역 메뉴에서 확인 가능합니다.");
			
			localeMapValid = true;
	
		} else if(locale.equalsIgnoreCase("en")) {

			localeMap.put("_L_0001", "Total amount");
			localeMap.put("_L_0002", "Cryptocurrency/Symbol Search");
			localeMap.put("_L_0003", "보유");
			localeMap.put("_L_0004", "Asset details");
			localeMap.put("_L_0005", "Holding coins");
			localeMap.put("_L_0006", "Transaction history");
			localeMap.put("_L_0007", "Open Order history");
			localeMap.put("_L_0008", "Waiting for deposit and withdrawal");
			localeMap.put("_L_0009", "Balance change history");
			localeMap.put("_L_0010", "※ 편의를 위해 ");
			localeMap.put("_L_0011", "KRW 환산 추정치로 표기");
			localeMap.put("_L_0012", "합니다.");
			localeMap.put("_L_0013", "Total purchase");
			localeMap.put("_L_0014", "Total Valuation Loss");
			localeMap.put("_L_0015", "Total Valuation Return");
			localeMap.put("_L_0016", "Total holdings of cryptocurrency");
			localeMap.put("_L_0017", "Holding KRW");
			localeMap.put("_L_0018", "Total appraisal");
			localeMap.put("_L_0019", "Hide small coins (value less than 10,000 krw)");
			localeMap.put("_L_0020", "Asset status");
			localeMap.put("_L_0021", "Holding cryptocurrency");
			localeMap.put("_L_0022", "Amount");
			localeMap.put("_L_0023", "Average buy price");
			localeMap.put("_L_0024", "Buy amount");
			localeMap.put("_L_0025", "Average");
			localeMap.put("_L_0026", "Revenue return");
			localeMap.put("_L_0027", "Type");
			localeMap.put("_L_0028", "암호화폐 선택");
			localeMap.put("_L_0029", "Buy");
			localeMap.put("_L_0030", "Sell");
			localeMap.put("_L_0031", "Search");
			localeMap.put("_L_0032", "A total of");
			localeMap.put("_L_0033", " data were viewed");
			localeMap.put("_L_0034", "Order reception time");
			localeMap.put("_L_0035", "Sell Classification");
			localeMap.put("_L_0036", "Order price");
			localeMap.put("_L_0037", "Order quantity");
			localeMap.put("_L_0038", "Contract price");
			localeMap.put("_L_0039", "Contract quantity");
			localeMap.put("_L_0040", "Contract amount");
			localeMap.put("_L_0041", "선택");
			localeMap.put("_L_0042", "Order number");
			localeMap.put("_L_0043", "Open Order quantity");
			localeMap.put("_L_0044", "Cancle");
			localeMap.put("_L_0045", "All");
			localeMap.put("_L_0046", "입금");
			localeMap.put("_L_0047", "출금");
			localeMap.put("_L_0048", "Request");
			localeMap.put("_L_0049", "Waitting");
			localeMap.put("_L_0050", "Complete");
			localeMap.put("_L_0051", "Application confirmation time");
			localeMap.put("_L_0052", "Coin");
			localeMap.put("_L_0053", "Transaction type");
			localeMap.put("_L_0054", "State");
			localeMap.put("_L_0055", "Reason for change");
			localeMap.put("_L_0056", "Transaction Date");
			localeMap.put("_L_0057", "Cryptocurrency");
			localeMap.put("_L_0058", "Change quantity");
			localeMap.put("_L_0059", "Fee");
			localeMap.put("_L_0060", "Changed quantity including fee");
			localeMap.put("_L_0061", "투자내역 환산 추정치 안내");
			localeMap.put("_L_0062", "닫기버튼");
			localeMap.put("_L_0063", "[투자내역 환산 추정치 안내]");
			localeMap.put("_L_0064", "※ 보유 암호화폐를 회원 편의를 위해 KRW 환산 추정치로 표기하여 안내 드립니다. KRW 환산 추정치는 단순 참고용으로 거래 및 입출금 기록에 따라 과대 혹은 과소평가 되어 보일 수 있습니다.");
			localeMap.put("_L_0065", "※ 비타500은 KRW 추정치에 대해 보장 및 보증하지 않으며, 제공된 편의정보를 기반으로 한 투자결과에 대해서도 책임지지 않습니다.");
			localeMap.put("_L_0066", "회원님의 실제 보유 암호화폐 수량에는 영향을 미치지 않습니다.");
			localeMap.put("_L_0067", "KRW로 표기된 수치는 회원 편의를 위한 KRW 환산한 추정치로 단순 참고용입니다.");
			localeMap.put("_L_0068", "암호화폐 입금시 매수평균가는 입금 시점의 시세를 적용합니다.");
			localeMap.put("_L_0069", "[KRW 환산 추정치 안내]");
			localeMap.put("_L_0070", "총 보유 암호화폐 : 총 보유한 암호화폐의 KRW환산 추정치 합계");
			localeMap.put("_L_0071", "총 평가액 : 총 보유 암호화폐 + 보유 KRW");
			localeMap.put("_L_0072", "매수 평균가");
			localeMap.put("_L_0073", "- 매수 : 매수 직전 매수평균가와 새로운 매수 가격의 평균");
			localeMap.put("_L_0074", "- 매도 : 직전 매수평균가 유지");
			localeMap.put("_L_0075", "- 입금 : 입금 직전 매수 평균가와 입금 시 암호 화폐가격의 평균");
			localeMap.put("_L_0076", "- 출금 : 직전 매수평균가 유지");
			localeMap.put("_L_0077", "총 매수액 : 보유 암호화폐의 매수액 총 합 (수량 X 매수평균가)");
			localeMap.put("_L_0078", "총 암호화폐 : 수량 X 현재가");
			localeMap.put("_L_0079", "총 평가손익 : 총 암호화폐 - 총 매수액");
			localeMap.put("_L_0080", "총 평가수익률 : 총 평가손익 / 총 매수액");
			localeMap.put("_L_0081", "※ Free Market의 매수액은 YEP/KRW 시세를 참고하여 KRW로 환산 추정치로 표기합니다.");
			localeMap.put("_L_0082", "※ 보유 암호화폐가 Major Market에 있으면 KRW 현재가 기준, Free Market에 있으면 YEP/KRW 현재가로 환산해서 평가액을 계산합니다.");
			localeMap.put("_L_0083", "확인");
			localeMap.put("_L_0084", "You have no data");
			localeMap.put("_L_0085", "주문");
			localeMap.put("_L_0086", "원");
            localeMap.put("_L_0087", "It is a tradable quantity excluding the outstanding quantity, which is different from the actual holding quantity.");
			localeMap.put("_L_0089", "You can check the actual holding amount in the investment history menu");
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

<!--컨텐츠 내용 영역 시작-->
<main id="content" role="main" class="main">

    <article class="layoutB">
		<!-- Left side : Start -->
        <aside>
            <ul>
                <li>
                    <div class="whole-asset-group">
                        <span class="txt-box"><%=getLocaleString("_L_0001")%></span>
                        <span id="total_estimated_qty" class="asset-value">0</span>
                        <span class="asset-unit">KRW</span>
                    </div>
                </li>
                <li>
                    <div class="sch-coin-group">
                        <p class="sch-coin-input" style="width:100%;">
                            <input type="text" id="search_coin_text" name="" value="" placeholder="<%=getLocaleString("_L_0002")%>" class="sch-input">
                        </p>
                        <!-- <span class="checks-item">
                            <input type="checkbox" id="chk_box_possess_coin">
                            <label for="chk_box_possess_coin"><%=getLocaleString("_L_0003")%></label>
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
						<!-- 리스트 1개 반복 : Start -->
                        <tbody id="wallet-lnb-coin-list-tbody">
                            <!-- 리스트 1개 반복 : Start -->
                            <tr class="active">
                                <td>
                                    <span class="coin-symbol">
                                        <img src="/resources/img/coin-symbol/KRW.png" alt="coin">
                                    </span>
                                </td>
                                <td>
                                    <p class="coin-name"><%=getLocaleString("_L_0086")%></p>
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
			<ul class="wlt_info">
				<li><%=getLocaleString("_L_0087")%></li>
				<!-- <li><%=getLocaleString("_L_0088")%></li> -->
				<li><%=getLocaleString("_L_0089")%></li>
			</ul>
        </aside>
        <!-- //Left side : End -->

        <!--입금 출금 탭 영역 시작-->
        <div class="content">
            <!-- CONTENT INNER : Start -->
            <div>
				<h1 class="subtitle"><%=getLocaleString("_L_0004")%></h1>
                <!-- 원화일 때 : Start -->
                <div id="tab-group-1">
                    <ul id="invest-tab-ul" class="li5">
                        <li><a id="tabs-a-1" href="#tabs-1"><%=getLocaleString("_L_0005")%></a></li>
                        <li><a id="tabs-a-2" href="#tabs-2"><%=getLocaleString("_L_0006")%></a></li>
                        <li><a id="tabs-a-3" href="#tabs-3"><%=getLocaleString("_L_0007")%></a></li>
                        <li><a id="tabs-a-4" href="#tabs-4"><%=getLocaleString("_L_0008")%></a></li>
                        <li><a id="tabs-a-5" href="#tabs-5"><%=getLocaleString("_L_0009")%></a></li>
                    </ul>

                    <!-- 보유코인 탭 컨텐츠 : Start -->
                    <div id="tabs-1">
                        <div class="content-item">
		                    <div class="invest_unit mgt20">

								<p><%=getLocaleString("_L_0010")%> <a href="#" class="pop_krw"><%=getLocaleString("_L_0011")%></a><%=getLocaleString("_L_0012")%></p>
								</div>
						<table class="table1 type01">
                                <colgroup>
                                    <col style="width:130px;">
                                    <col>
                                    <col style="width:110px;">
                                    <col>
                                    <col style="width:110px;">
                                    <col>
                                </colgroup>
                                <tbody>
                                    <tr>
                                        <th><%=getLocaleString("_L_0013")%></th>
                                        <td class="alRight">
                                            <span id="invest-tabs-1-header-total-buy-amt" class="bold"></span>
                                            <span class="gray base-currency-symbol">KRW</span>
                                        </td>
                                        <th><%=getLocaleString("_L_0014")%></th>
                                        <td class="alRight">
                                            <span id="invest-tabs-1-header-total-profit-or-loss-amt" class="blue bold"></span>
                                            <span class="gray base-currency-symbol">KRW</span>
                                        </td>
                                         <th><%=getLocaleString("_L_0015")%></th>
                                        <td class="alRight">
                                            <span id="invest-tabs-1-header-total-profit-or-loss-per" class="blue bold"></span>
                                            <span class="gray">%</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th><%=getLocaleString("_L_0016")%></th>
                                        <td class="alRight">
                                            <span id="invest-tabs-1-header-total-estimate-amt" class="bold"></span>
                                            <span class="gray base-currency-symbol">KRW</span>
                                        </td>
                                        <th><%=getLocaleString("_L_0017")%></th>
                                        <td class="alRight">
                                            <span id="invest-tabs-1-header-total-bc-amt" class="bold"></span>
                                            <span class="gray base-currency-symbol">KRW</span>
                                        </td>
                                        <th><%=getLocaleString("_L_0018")%></th>
                                        <td class="alRight">
                                            <span id="invest-tabs-1-header-total-possess-amt" class="bold"></span>
                                            <span class="gray base-currency-symbol">KRW</span>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>

		                    <div class="invest_unit dw-history">
									<span class="checks-item">
										<input type="checkbox" id="invest-tabs-1-checkbox-hide-small-asset">
										<label for="invest-tabs-1-checkbox-hide-small-asset"><%=getLocaleString("_L_0019")%></label>
									</span>				
								</div>
                            <div class="asset">
                                <div class="chart_data">
                                    <h3 class="sub_title"><%=getLocaleString("_L_0020")%></h3>
                                    <canvas id="invest-tabs-1-asset-chart" class="invest-chart" style="width:100%;max-width:500px"></canvas>
                                </div>

                            </div>

						<table class="table1 line">
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
                                            <th class="alLeft"><%=getLocaleString("_L_0021")%></th>
                                            <th class="alCenter"><%=getLocaleString("_L_0022")%></th>
                                            <th class="alRight"><%=getLocaleString("_L_0023")%></th>
                                            <th class="alRight"><%=getLocaleString("_L_0024")%></th>
                                            <th class="alRight"><%=getLocaleString("_L_0025")%></th>
                                            <th class="alRight"><%=getLocaleString("_L_0026")%></th>
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
                                <span class="option-title"><%=getLocaleString("_L_0027")%></span>
                                <select id="invest-tabs-2-coin-select" onChange="getUsrTradingHistoryList()" class="select-box">
                                    <option value="9999" selected><%=getLocaleString("_L_0028")%></option>
                                </select>

                                <span>
                                    <span class="checks-item">
                                        <input type="checkbox" id="invest-tabs-2-checkbox-buy">
                                        <label for="invest-tabs-2-checkbox-buy"><%=getLocaleString("_L_0029")%></label>
                                    </span>
                                    <span class="checks-item">
                                        <input type="checkbox" id="invest-tabs-2-checkbox-sell">
                                        <label for="invest-tabs-2-checkbox-sell"><%=getLocaleString("_L_0030")%></label>
                                    </span>
                                </span>
                                <div class="calendar">
                                    <input type="text" name="calendar" class="startDate" id="invest-tabs-2-startDate" required="" value="">
                                    <span>~</span>
                                    <input type="text" name="calendar" class="endDate" id="invest-tabs-2-endDate" required="" value="">
                                    <button onClick="getUsrTradingHistoryList()"><%=getLocaleString("_L_0031")%></button>
                                </div>
                            </div>

                            <!-- //조회 옵션 : End -->
                            <!-- 조회 건수 표시 : Start -->

                            <p class="case-info"><%=getLocaleString("_L_0032")%> <b class="blue"><span id="invest-tabs-2-list-total-count">0</span></b> <%=getLocaleString("_L_0033")%></p>
                            <!-- //조회 건수 표시 : End -->
                            <div>
							<table class="table1 line">
                                    <thead>
                                        <tr>
                                            <th><%=getLocaleString("_L_0034")%></th>
                                            <th><%=getLocaleString("_L_0027")%></th>
                                            <th><%=getLocaleString("_L_0035")%></th>
                                            <th><%=getLocaleString("_L_0036")%></th>
                                            <th><%=getLocaleString("_L_0037")%></th>
                                            <th><%=getLocaleString("_L_0038")%></th>
                                            <th><%=getLocaleString("_L_0039")%></th>
                                            <th><%=getLocaleString("_L_0040")%></th>
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
                                <span class="option-title"><%=getLocaleString("_L_0027")%></span>
                                <select id="invest-tabs-3-coin-select" onChange="getUsrNonContractList(true)" class="select-box">
                                    <option value="9999" selected><%=getLocaleString("_L_0041")%></option>
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
                            <p class="case-info"><%=getLocaleString("_L_0032")%> <b class="blue"><span id="invest-tabs-3-list-total-count">0</span></b> <%=getLocaleString("_L_0033")%></p>
                            <!-- //조회 건수 표시 : End -->
                            <!-- 데이터 영역 : Start -->
                            <!-- <dl class="dl-tit1">
                                <dt><%=getLocaleString("_L_0034")%></dt>
                                <dd><%=getLocaleString("_L_0042")%></dd>
                                <dd><%=getLocaleString("_L_0027")%></dd>
                                <dd><%=getLocaleString("_L_0035")%></dd>
                                <dd><%=getLocaleString("_L_0036")%></dd>
                                <dd><%=getLocaleString("_L_0037")%></dd>
                                <dd><%=getLocaleString("_L_0039")%></dd>
                                <dd><%=getLocaleString("_L_0043")%></dd>
                                <dd><%=getLocaleString("_L_0044")%></dd>
                            </dl> -->
                            <div id="invest-tabs-3-non-contract-history-list-div" class="list-tbl mCustomScrollbar">
                                <table class="table1 line">
                                    <!-- <colgroup>
                                        <col>
                                        <col>
                                        <col style="width: 109px">
                                        <col style="width: 60px">
                                        <col>
                                        <col>
                                        <col>
                                        <col>
                                        <col style="width: 60px">
                                    </colgroup> -->
                                    <thead>
                                        <tr>
                                            <th><%=getLocaleString("_L_0034")%></th>
                                            <th><%=getLocaleString("_L_0042")%></th>
                                            <th><%=getLocaleString("_L_0027")%></th>
                                            <th><%=getLocaleString("_L_0035")%></th>
                                            <th><%=getLocaleString("_L_0036")%></th>
                                            <th><%=getLocaleString("_L_0037")%></th>
                                            <th><%=getLocaleString("_L_0039")%></th>
                                            <th><%=getLocaleString("_L_0043")%></th>
                                            <th><%=getLocaleString("_L_0044")%></th>
                                        </tr>
                                    </thead>
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
                                    <option value="0" selected><%=getLocaleString("_L_0045")%></option>
                                    <option value="1"><%=getLocaleString("_L_0046")%></option>
                                    <option value="2"><%=getLocaleString("_L_0047")%></option>
                                </select>
                                <span>
                                    <span class="checks-item">
                                        <input type="checkbox" id="invest-tabs-4-checkbox-request">
                                        <label for="invest-tabs-4-checkbox-request"><%=getLocaleString("_L_0048")%></label>
                                    </span>
                                    <span class="checks-item">
                                        <input type="checkbox" id="invest-tabs-4-checkbox-wait">
                                        <label for="invest-tabs-4-checkbox-wait"><%=getLocaleString("_L_0049")%></label>
                                    </span>
                                    <span class="checks-item">
                                        <input type="checkbox" id="invest-tabs-4-checkbox-finish">
                                        <label for="invest-tabs-4-checkbox-finish"><%=getLocaleString("_L_0050")%></label>
                                    </span>
                                </span>

                                <div class="calendar">
                                    <input type="text" name="calendar" class="startDate" id="invest-tabs-4-startDate" required="" value="">
                                    <span>~</span>
                                    <input type="text" name="calendar" class="endDate" id="invest-tabs-4-endDate" required="" value="">
                                    <button onClick="getDWMgrList(9999)"><%=getLocaleString("_L_0031")%></button>
                                </div>
                            </div>
                            <!-- //조회 옵션 : End -->
                            <!-- 조회 건수 표시 : Start -->
                            <p class="case-info"><%=getLocaleString("_L_0032")%> <b class="blue"><span id="invest-tabs-4-list-total-count">0</span></b> <%=getLocaleString("_L_0033")%></p>
                            <!-- //조회 건수 표시 : End -->
                            <!-- 데이터 영역 : Start -->
                            <div>
							<table class="table1 line">
                                    <thead>
                                        <tr>
                                            <th><%=getLocaleString("_L_0051")%></th>
										<th><%=getLocaleString("_L_0052")%></th>
                                            <th><%=getLocaleString("_L_0053")%></th>
                                            <th><%=getLocaleString("_L_0037")%></th>
                                            <th><%=getLocaleString("_L_0054")%></th>
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
                                    <option selected><%=getLocaleString("_L_0045")%></option>
                                </select>
                                <select id="invest-tabs-5-change-reason-select" class="select-box">
                                    <option value="9999" selected><%=getLocaleString("_L_0055")%></option>
                                </select>

                                <div class="calendar">
                                    <input type="text" name="calendar" class="startDate" id="invest-tabs-5-startDate" required="" value="2019-04-14">
                                    <span>~</span>
                                    <input type="text" name="calendar" class="endDate" id="invest-tabs-5-endDate" required="" value="2019-04-15">
                                    <button onClick="getBalanceChangeHistoryList()"><%=getLocaleString("_L_0031")%></button>
                                </div>
                            </div>
                            <!-- //조회 옵션 : End -->
                            <!-- 조회 건수 표시 : Start -->
                            <p class="case-info"><%=getLocaleString("_L_0032")%> <b id="invest-tabs-5-list-total-count" class="blue">0</b><%=getLocaleString("_L_0033")%></p>
                            <p class="case-txt">원화 수수료는  소숫점 이하일 경우 반올림으로 처리됩니다.</p>
                            <!-- //조회 건수 표시 : End -->

                            <!-- 데이터 영역 : Start -->
                            <div>
							<table class="table1 line">
                                    <thead>
                                        <tr>
										<th><%=getLocaleString("_L_0056")%></th>
                                            <th><%=getLocaleString("_L_0057")%></th>
                                            <th><%=getLocaleString("_L_0055")%></th>
                                            <th><%=getLocaleString("_L_0058")%></th>
                                            <th><%=getLocaleString("_L_0059")%></th>
										    <th><%=getLocaleString("_L_0060")%></th>
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

</main> 
<!--컨텐츠 내용 영역 끝-->


<!--투자내역 환산 추정치 안내 팝업 시작-->
<div class="popup-wrap popup-estimate-info">
   <!-- ALTER. 기본 : Start -->
   <div class="popup big"> <!--alert : 가로400 big :가로 680-->
       <!-- 팝업내용 : Start -->
       <div class="alert-header">
           <p class="alert-title"><%=getLocaleString("_L_0061")%></p><!-- 팝업 타이틀 -->
           <a class="top-close">
               <img src="/resources/img/btn-alert-close.png" alt="<%=getLocaleString("_L_0062")%>">
           </a><!-- 팝업 닫기 버튼 -->
       </div>
       <div class="alert-content">
           <h1><%=getLocaleString("_L_0063")%></h1>
           <p><%=getLocaleString("_L_0064")%></p>
           <p><%=getLocaleString("_L_0065")%></p>

               <ul class="decimal">
                   <li><%=getLocaleString("_L_0066")%></li>
                   <li><%=getLocaleString("_L_0067")%></li>
                   <li><%=getLocaleString("_L_0068")%></li>
               </ul>

           <h1><%=getLocaleString("_L_0069")%></h1>

               <ul class="disc">
                   <li><%=getLocaleString("_L_0070")%></li>
                   <li><%=getLocaleString("_L_0071")%></li>
                   <li>
					<%=getLocaleString("_L_0072")%>
                       <ul>
                           <li><%=getLocaleString("_L_0073")%></li>
                           <li><%=getLocaleString("_L_0074")%></li>
                           <li><%=getLocaleString("_L_0075")%></li>
                           <li><%=getLocaleString("_L_0076")%></li>
                       </ul>
                   </li>
                   <li><%=getLocaleString("_L_0077")%></li>
                   <li><%=getLocaleString("_L_0078")%></li>
                   <li><%=getLocaleString("_L_0079")%></li>
                   <li><%=getLocaleString("_L_0080")%></li>
               </ul>
           <p><%=getLocaleString("_L_0081")%></p>
           <p><%=getLocaleString("_L_0082")%></p>
       </div>
       <!-- //팝업내용 : End -->
       <div class="btn-wrap">
           <button class="blue big top-close"><%=getLocaleString("_L_0083")%> </button>
       </div>
   </div>
   <!-- //ALTER. 기본 : End -->
</div>
<!--투자내역 환산 추정치 안내 팝업 끝-->

<script src="/resources/js/holdport/wallet/holdport.invest.js?v=<spring:message code="holdport.invest.js.version"/>"></script>

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
		<td colspan="7"><%=getLocaleString("_L_0084")%> </td>
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
		<td colspan="{{ColSpan}}"><%=getLocaleString("_L_0084")%></td>
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
        <td class="alCenter">
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
            <button style="display: {{ShowHideOption}}" onClick="javascript:window.location.href='/site/exchange?itemCode={{CoinSymbolicName}}'"><%=getLocaleString("_L_0085")%></button>
        </td>
    </tr>
</script>
<!-- 거래내역 리스트 -->
<script id="template-wallet-tab-2-trading-history-list" type="text/template">
    <tr>
        <td class="alCenter">{{OrderDateTime}}</td>
        <td class="alCenter">{{ItemName}}</td>
        <td class="alCenter {{SellBuyGubunColor}}">{{SellBuyGubun}}</td>
        <td class="alCenter">{{OrderPrice}}</td>
        <td class="alCenter">{{OrderQty}}</td>
        <td class="alCenter">{{ContractPrice}}</td>
        <td class="alCenter">{{ContractQty}}</td>
        <td class="alCenter">{{ContractAmt}}</td>
    </tr>
</script>
<!-- 미체결 내역 리스트 -->
<script id="template-wallet-tab-3-non-contract-history-list" type="text/template">
    <tr data-orderno="{{OrderNo}}">
        <td>{{OrderDateTime}}</td>
        <td class="text-ellips">{{OrderNo}}</td>
        <td class="alCenter">{{ItemName}}</td>
        <td class="alCenter {{SellBuyGubunColor}}">{{SellBuyGubun}}</td>
        <td class="alCenter">{{OrderPrice}}</td>
        <td class="alCenter">{{OrderQty}}</td>
        <td class="alCenter">{{ContractQty}}</td>
        <td class="alCenter">{{NonContractQty}}</td>
        <td><button onClick="cancelNonContractOrder('{{NonContractOrderNo}}')"><%=getLocaleString("_L_0044")%></button></td>
    </tr>
</script>
<!-- 입출금 대기 리스트 -->
<script id="template-wallet-tab-4-deposit-withdraw-waiting-history-list" type="text/template">
    <tr>
        <td class="alCenter">{{ReqDateTime}}</td>
        <td class="alCenter">{{CoinSymbolicName}}</td>
        <td class="alCenter {{DwGubunColor}}">{{DwGubun}}</td>
        <td class="alCenter">{{ReqQty}}</td>
        <td class="alCenter">{{ReqStatProcText}}</td>
    </tr>
</script>
<!-- 잔고 변경이력 리스트 -->
<script id="template-wallet-tab-5-balance-change-history-list" type="text/template">
    <tr>
        <td class="alCenter">{{ChangeDateTime}}</td>
        <td class="alCenter">{{CoinSymbolicName}}</td>
        <td class="alCenter {{PlusOrMinusColor}}">{{ChangeReason}}</td>
        <td class="alCenter">{{ChangeQty}}</td>
        <td class="alCenter">{{Fee}}</td>
		<td class="alCenter">{{ChangeQtyWithFee}}</td>
    </tr>
</script>

<script src="/resources/js/common/Chart.js"></script>
<script src="/resources/js/common/tab.js"></script>
