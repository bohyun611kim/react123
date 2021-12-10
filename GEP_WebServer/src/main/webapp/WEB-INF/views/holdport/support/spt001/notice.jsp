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

			localeMap.put("_L_0001","고객지원");
			localeMap.put("_L_0002","공지사항");
			localeMap.put("_L_0003","보도뉴스");
			localeMap.put("_L_0004","이벤트");
			localeMap.put("_L_0005","이용안내");
			localeMap.put("_L_0006","보안인증가이드");
			localeMap.put("_L_0007","자주하는질문");
			localeMap.put("_L_0008","검색");
			localeMap.put("_L_0009","제목");
			localeMap.put("_L_0010","등록일");
			localeMap.put("_L_0011","이벤트 및 결과");
			localeMap.put("_L_0012","진행");
			localeMap.put("_L_0013","마켓구분");
			localeMap.put("_L_0014","- KRW 마켓 : KRW(원화)로 암호화폐를 거래할 수 있습니다.");
			localeMap.put("_L_0015","- BTC 마켓 : BTC(비트코인)로 다른 디지털 자산을 사고팔 수 있습니다.");
			localeMap.put("_L_0016","- USDT 마켓 : USDT(테더)로 다른 디지털 자산을 사고팔 수 있습니다.");
			localeMap.put("_L_0017","인증레벨 별 입출금한도");
			localeMap.put("_L_0018","구분");
			localeMap.put("_L_0019","레벨 1");
			localeMap.put("_L_0020","레벨 2");
			localeMap.put("_L_0021","레벨 3");
			localeMap.put("_L_0022","레벨 4");
			localeMap.put("_L_0023","레벨 5");
			localeMap.put("_L_0024","인증방법");
			localeMap.put("_L_0025","이메일 인증<br/>(자동 인증)");
			localeMap.put("_L_0026","휴대폰<br/>본인인증<br/>(자동 인증)");
			localeMap.put("_L_0027","OTP등록<br/>(자동인증)");
			localeMap.put("_L_0028","신분증 인증<br/>(별도 심사)");
			localeMap.put("_L_0029","거주지인증<br/>(별도 심사)");
			localeMap.put("_L_0030","입금한도");
			localeMap.put("_L_0031","암호화폐");
			localeMap.put("_L_0032","무제한");
			localeMap.put("_L_0033","(1일 한도)");
			localeMap.put("_L_0034","만원");
			localeMap.put("_L_0035","별도 심사");

			// 보안인증 가이드 : 완료 
			localeMap.put("_L_0036","1. 안전한 로그인 비밀번호 생성");
			localeMap.put("_L_0037","2. 로그인 비밀번호 주기적 변경");
			localeMap.put("_L_0038","비밀번호 변경하기");
			localeMap.put("_L_0039","3. 브라우저의 비밀번호 자동저장기능 사용하지 않기");
			localeMap.put("_L_0040","4. 붙여넣기 암호화폐 출금주소 확인하기");
			localeMap.put("_L_0041","5. OTP 인증을 통한 로그인 보안 강화");
			localeMap.put("_L_0042","OTP인증설정");
			localeMap.put("_L_0043","6. 접속이력 확인");
			localeMap.put("_L_0044","접속이력 보기");
			localeMap.put("_L_0045","출금한도는 KRW와 암호화폐의 통합한도이며, 암호화폐의 경우 출금 시점의 원화 환산액 기준으로 출금한도가 적용 됩니다.");
			localeMap.put("_L_0046","레벨 5는 심사기준에 따라 별도의 절차를 거쳐 승인합니다. 1:1 문의를 통해 문의하세요.");
			localeMap.put("_L_0047","부정거래가 의심되는 경우 입출금이 제한됩니다.");
			localeMap.put("_L_0048","출금 수수료");
			localeMap.put("_L_0049","자 산");
			localeMap.put("_L_0050","입금 수수료");
			localeMap.put("_L_0051","무료");
			localeMap.put("_L_0052","출금 수수료");
			localeMap.put("_L_0053","최소출금금액(수량)");
			localeMap.put("_L_0054","최소 주문가능 금액");
			localeMap.put("_L_0055","원");
			localeMap.put("_L_0056","마켓");
			localeMap.put("_L_0057","주문유형");
			localeMap.put("_L_0058","Limited price order: Since the order is made by entering the execution price and quantity, if the entered price is not reached, the order will not be executed.");
			localeMap.put("_L_0059","거래 마켓별 수수료");
			
			// 상장안내 : 완료 
			localeMap.put("_L_0060","BITA500 거래소 상장안내");
			localeMap.put("_L_0061","가상자산은 미래 가치와 발전 가능성이 높은 영역입니다.");
			localeMap.put("_L_0062","BITA500 거래소는 미래 가치가 높은 프로젝트와 엄선된 기준을 통하여 보다 폭넓고");
			localeMap.put("_L_0063","안전한 투자 기회를 제공하며, 향후 블록체인 산업과 디지털 가상자산 산업의 건전한");
			localeMap.put("_L_0064","발전에 앞장서 나가기 위해 BITA500 거래소의 상장 심사기준을 정하여 안내해 드립니다.");
			localeMap.put("_L_0065","상장 정책");
			localeMap.put("_L_0066","주식회사 오병이어홀딩스는 디지털 가상자산 거래소인 BITA500 거래소의 상장 정책에 따른 심사를 통과하여 승인 받은 가상자산에 대해 거래소 이용자들이 안전하게 거래할 수 있도록 합니다.");
			localeMap.put("_L_0067","엄격하고 투명, 공정한 내•외부 심사기준을 통하여 승인된 가상자산 프로젝트를 최상의 보안상태로 유지하고 관리함으로써 투자자를 보호합니다.");
			localeMap.put("_L_0068","상장 이후에도 투자자 보호를 위해 가상자산 프로젝트에 대하여 지속적이고 체계적인 실시간 모니터링을 함으로써 기술적인 문제와 법률적인 부분 등에 부합되는 가상자산은 상장 폐지 검토를 진행합니다.");
			localeMap.put("_L_0069","상장 심사기준");
			localeMap.put("_L_0070","현재 가상자산의 법적 지위가 불분명하고 컨트롤 타워가 부재하기 때문에 투자 자산으로써 많은 위험요소를 가지고 있습니다. 이러한 고위험 자산을 안전하게 제공하여 안심하고 거래할 수 있도록 가상자산 프로젝트에 대한 엄격한 심사 기준을 준수합니다.");
			localeMap.put("_L_0071","비전과 기술력");
			localeMap.put("_L_0072","지속 가능 안정성");
			localeMap.put("_L_0073","운영 계획 및 관리의 투명성");
			localeMap.put("_L_0074","팀에 대한 역량 및 재무 건전성");
			localeMap.put("_L_0075","사업에 따른 시장성, 투자 가치성");
			localeMap.put("_L_0076","상장 심사 절차<br/>내•외부 전문가들과 공시 플랫폼 Xangle과의 협업을 통해 프로젝트 진행에 대한 세부적인 심사를 진행합니다.");
			localeMap.put("_L_0077","");
			localeMap.put("_L_0078","상장 유지 모니터링 심사");
			localeMap.put("_L_0079","상장되어 있는 가상자산의 프로젝트에 대한 적합성을 정기적으로 관리 심사합니다.");
			localeMap.put("_L_0080","상장된 가상자산을 통하여 고위험에 노출되지 않도록 보안 점검 모니터링이 실시간 이루어집니다.");
			localeMap.put("_L_0081","상장 폐지 심사 기준에 근거하여 정기적으로 심사를 진행합니다.");
			localeMap.put("_L_0082","상장 폐지 심사 기준");
			localeMap.put("_L_0083","법적 문제 – 프로젝트가 자금세탁 및 범죄에 연루되었거나 관련 법규, 규정, 요구사항을 위반하였을 경우");
			localeMap.put("_L_0084","프로젝트 기술적인 문제 – 관련 기술에 대한 취약점 발견 등 보안상 문제가 발생되었을 경우");
			localeMap.put("_L_0085","정보제공의 문제 – 허위 정보를 유포하거나 백서에 명시된 일정과 달리 심각한 지연이 발생된 경우");
			localeMap.put("_L_0086","운영의 문제 – 부정적인 방법으로 운영(다단계, 마케팅 판매)등으로 가상자산 거래소 회원에게 중대한 피해가 예상될 경우");
			localeMap.put("_L_0087","유의사항");
			localeMap.put("_L_0088","BITA500 거래소는 상장을 담보로 어떠한 비용도 요구하지 않습니다.");
			localeMap.put("_L_0089","상장 관련 모든 문의 및 절차는 bita500상장 문의 이메일 listing@bita500.com 과 상장 지원 신청을 통해서만 진행됩니다.");
			localeMap.put("_L_0090","상장 관련 모든 절차는 당사 경영진의 별도 개입 없이 전문 심사위원들을 구성하여 진행합니다.");
			localeMap.put("_L_0091","상장 지원 신청 2022");
			localeMap.put("_L_0092","상장 지원 신청 클릭하면 구글폼 신청서 양식으로 넘어갑니다.");
			localeMap.put("_L_0093","상장안내");

			// ------------- 언어팩 누락 작업시작 ---------------------
			// 공지사항 : 완료 
			localeMap.put("_L_NOT01","목록");

			// 보도뉴스 : 완료 

			// 이벤트 :  완료 
			localeMap.put("_L_EVT01","목록");
			
			// ---- 이용안내 : 완료 
			localeMap.put("_L_0005A","사용자 설명서(bita500 menual)");
			localeMap.put("_L_0005B","1:1 문의");
			localeMap.put("_L_0005C","문의내역");
			localeMap.put("_L_0005D","출금한도");
			localeMap.put("_L_0005E","KRW");
			localeMap.put("_L_0005F","만원");
			localeMap.put("_L_0005G","(원화)");
			localeMap.put("_L_0005H","이더리움(ETH)");
			localeMap.put("_L_0005I","비트코인(BTC)");
			localeMap.put("_L_0005J","리플(XRP)");
			localeMap.put("_L_0005K","우리들코인(WRD)");
			localeMap.put("_L_0005L","주문유형");
			localeMap.put("_L_0005M","지정가 주문 : 체결 가격과 수량을 입력하여 주문하므로, 입력한 가격이 도달하지 않으면 주문이 체결되지 않으며 체결될때까지 미체결 주문에서 확인가능 합니다.");
			localeMap.put("_L_0005N","상장 지원 신청 접수");
			localeMap.put("_L_0005O","프로젝트 서류 심사");
			localeMap.put("_L_0005P","프로젝트 팀 미팅 및 실사");
			localeMap.put("_L_0005Q","상장 심의 위원회 가상자산 법률 심사");
			localeMap.put("_L_0005R","상장심사 결과 고지");
			localeMap.put("_L_0005S","거래소 상장");
			localeMap.put("_L_0005T","알파벳 대문자, 알파벳소문자, 특수기호, 숫자  네 종류 문자 중 최소 3 가지 이상의 조합으로 구성된 비밀번호 사용하기");
			localeMap.put("_L_0005U","7자리 이상의 비밀번호 사용하기");
			localeMap.put("_L_0005V","로그인ID, 생일, 전화번호 등이 포함되는 비밀번호 사용하지 않기");
			localeMap.put("_L_0005W","동일한 문자, 숫자가 연속해서 반복되는 비밀번호 사용하지 않기");
			localeMap.put("_L_0005X","비밀번호 분실에 의한 임시 비밀번호 사용 후 즉시 비밀번호 변경하기");
			localeMap.put("_L_0005Y","※ 코인 시세 변동으로 인해 출금 수수료가 변동될 수 있습니다.");
			localeMap.put("_L_0005Z","※ 이더리움(ETH), 이더리움 토큰 계열은 네트워크 비용에 따라 출금 수수료가 변동될 수 있습니다.");
			localeMap.put("_L_0006Z","※ 최소입금 수량 미만의 자산 입금 시 입금처리가 불가할 수 있으니, 입금 시 주의하여 입금해 주시기 바랍니다.");
			// 2. 로그인 비밀번호 주기적 변경
			localeMap.put("_L_PW01", "최소 3개월에 한번 씩 비밀번호 변경 하기");
			localeMap.put("_L_PW02", "비밀번호 분실에 의한 임시 비밀번호 사용 후 즉시 비밀번호 변경하기");
			// 3. 브라우저의 비밀번호 자동저장기능 사용하지 않기
			localeMap.put("_L_BR01","웹 브라우즈는 로그인한 사이트의 ID와 비밀번호를 저장 해 두었다가 다음 로그인 시 자동 입력되게 하는 사용자 편의기능이 있음");
			localeMap.put("_L_BR02","여러 사람이 공동으로 사용하는 컴퓨터에서 거래소 사이트를 이용하게 되는 경우 브라우즈의 ID 및 비밀번호 저장기능을 사용하면 보안상 위험함");
			localeMap.put("_L_BR03","거래소 최초 로그인 시 표시되는 팝업 창에서 비밀번호 저장여부를 묻는 경우 사용하지 않음으로 선택");
			localeMap.put("_L_BR04","만약 저장을 선택 하였다면 브라우저의 설정 메뉴에서 저장된 비밀번호를 삭제 함");
			localeMap.put("_L_BR05","Chrome : 설정 – 고급 – 비밀번호 및 양식 – 비밀번호 관리 – 저장된 비밀번호");
			localeMap.put("_L_BR06","Explore : 설정 – 고급 설정 보기 – 암호관리 – 저장된 암호 – 저장된 암호");
			// 4. 붙여넣기 암호화폐 출금주소 확인하기
			localeMap.put("_L_CO01", "타 암호화폐 지갑주소로 암호화폐 출금 시 출금주소 복사하기 후 붙여넣기 기능을 많이 사용하게 됩니다");
			localeMap.put("_L_CO02", "이 과정에서 해킹 프로그램에 의해 출금주소 위변조가 발생할 수 있습니다");
			localeMap.put("_L_CO03", "붙여넣기 후 반드시 출금주소가 일치하는지 확인 합니다");
			// 5. OTP 인증을 통한 로그인 보안 강화
			localeMap.put("_L_OP01", "로그인 보안 강화를 위해 OTP 인증을 설정 하십시오");
			localeMap.put("_L_OP02", "OTP 인증을 설정하면 로그인 시 OTP 인증을 추가로 수행 하게 됩니다");
			// 6. 접속이력 확인
			localeMap.put("_L_CN01", "마이페이지-접속이력 화면에서 로그인 이력을 확인할 수 있습니다");
			localeMap.put("_L_CN02", "이상 접속 또는 이용 기록 발견 시 즉시 비밀번호 변경을 해 주세요");
			
			
			// ------------------------ 자주하는 질문 ------------------------------------

			// 가입 및 개인정보 변경 
			localeMap.put("_L_JJ01","자주 묻는 질문을 먼저 확인하세요"); 
			localeMap.put("_L_JJMSG","원하는 답변이 없으신가요? 카카오톡 문의를 이용해주시면 신속하게 답변드리겠습니다."); 
			localeMap.put("_L_JJ02","전체"); 
			localeMap.put("_L_JJ03","가입 및 개인정보 변경"); 
			localeMap.put("_L_JJ04","원화 입금/출금"); 
			localeMap.put("_L_JJ05","암호화폐 입금/출금");
			localeMap.put("_L_JJ06","입출금내역"); 
			localeMap.put("_L_JJ07","기타"); 
			localeMap.put("_L_JJ08","가입 및 개인정보 변경"); 
			localeMap.put("_L_JJ09","회원가입 방법이 어떻게 되나요?"); 
			localeMap.put("_L_JJ10","회원 가입은 Bita500 PC용 웹페이지 또는 모바일 웹(추후 오픈 예정)에서 가능합니다."); 
			localeMap.put("_L_JJ11","회원가입 인증메일이 오지 않아요."); 
			localeMap.put("_L_JJ12","받은편지함이 아닌 스팸함이나 전체편지함도 한번 찾아보시길 바랍니다. 구글 ( gmail.com ) 의 경우, '카테고리-프로모션탭'을 확인해 주시기 바랍니다. 또한 메일이 수신되는 데에 간혹 시간이 걸리는 경우가 있습니다. 기다려도 메일이 오지 않을 경우,"); 
			localeMap.put("_L_JJ13", "가입하신 이메일 ID 로 로그인하시면 인증메일을 재발송 하실 수 있습니다.");
			localeMap.put("_L_JJ14", "재발송을 통해 진행해보시길 바랍니다.");
			localeMap.put("_L_JJ15", "개선이 되지 않는다면 1:1문의 문의 부탁 드립니다.");
		  localeMap.put("_L_JJ16", "회원가입 자격이 따로 있나요?");
			localeMap.put("_L_JJ17", "네, 있습니다. 미성년자 ( 만 19세 미만 ) 와 외국인의 경우 회원가입이 불가합니다.");
			localeMap.put("_L_JJ18", "법인폰으로 이용 가능한가요?");
			localeMap.put("_L_JJ19", "법인폰으로는 이용이 불가 합니다.");
			localeMap.put("_L_JJ20", "개인정보를 변경할 수 있나요?");
			localeMap.put("_L_JJ21", "개인정보의 변경은 로그인 후 마이페이지 아래 각 서브메뉴에서 변경코자 하는 정보와 매칭되는 화면에서 변경처리 하시면 됩니다.<br /> 변경 가능한 개인 정보로는 로그인 비밀번호, 휴대폰번호, 인증레벨, OTP설정/해제 등이 있습니다");
			localeMap.put("_L_JJ22", "휴대전화 번호를 변경할 수 있나요? 어떻게 해야하나요?");
			localeMap.put("_L_JJ23", "휴대폰 번호 변경은 초기화 접수를 통해서만 가능합니다.");
			localeMap.put("_L_JJ24", "[마이페이지 – 보안인증 – 휴대폰 본인인증] 초기화 메뉴를 통해서");
			localeMap.put("_L_JJ25", "안내되는 팝업창의 절차에 따라 접수하시길 바랍니다.");
			localeMap.put("_L_JJ26", "가입 후 인증레벨을 올리고 싶은 데 어떻게 하나요?");
			localeMap.put("_L_JJ27", "[마이페이지 – 보안인증] 화면에서 보안등급별 다음 단계 안내에 따른 인증을 하시면 됩니다.");
			localeMap.put("_L_JJ28", "인증레벨을 3레벨로 올리고 싶은데 반드시 상향조건을 만족해야 하는건가요?");
			localeMap.put("_L_JJ29", "네, 상향조건 만족해야 합니다.");
			localeMap.put("_L_JJ30", "가입시 이름을 잘못 입력하여 휴대폰 본인인증이 안됩니다.");
			localeMap.put("_L_JJ31", "1:1 문의에 남겨 주시면 이름을 변경해 드립니다.");
			localeMap.put("_L_JJ32", "OTP 설정시 OTP 인증번호가 틀렸다고 표시됩니다.");
			localeMap.put("_L_JJ33", "구글 OTP 앱에 표시된6자리 번호는 일정 시간 후 갱신이 되니 갱신된 번호를 다시 입력해야 합니다.");
			// 원화 입금/출금 시작
			localeMap.put("_L_JJ34", "원화 입금/출금");
			localeMap.put("_L_JJ35", "원화 입금은 어떻게 하나요?");
			localeMap.put("_L_JJ36", "입출금-입금신청 화면 이동 -> 본인 명의 은행계좌 등록 후 수동입금 / 자동입금으로 나뉩니다.");
			localeMap.put("_L_JJ37", "자동입금시");
			localeMap.put("_L_JJ38", "실시간으로 입금처리 가능");
			localeMap.put("_L_JJ39", "입출금 – 입금신청- 자동입금-  (본인이름+숫자4자리 홍길동 1111)");
			localeMap.put("_L_JJ40", "자동입금시 수동입금요청 이중으로 하지 않아도 됩니다.");
			localeMap.put("_L_JJ41", "수동입금 시");
			localeMap.put("_L_JJ42", "수동입금 요청 순서 => 입출금 – 입금신청- 수동입금- 금액-제출");
			localeMap.put("_L_JJ43", "*고액입금 시 이체결과확인증 첨부후 관리자가 입금처리 진행*");
			localeMap.put("_L_JJ44", "원화 입금 확인이 안되고 있습니다");
			localeMap.put("_L_JJ45", "계좌이체 후 5분 이내에 원화 입금 내역을 확인 가능");
			localeMap.put("_L_JJ46", "시스템 장애 또는 거래소에 등록하신 본인명의 은행계좌로 입금 하시지 않은 경우 입금 확인이 불가");
			localeMap.put("_L_JJ47", "[고객지원 – 1:1 문의 – 인증자료 제출] 에서 이체결과확인증 제출해주시기바랍니다.");
			localeMap.put("_L_JJ48", "원화 출금 제한이 있나요?");
			localeMap.put("_L_JJ49", "원화 출금은 회원 가입 후 인증레벨3 이상인 회원<br/>각 인증레벨별로 월 출금한도 및 1일 출금한도가 정해져 있습니다.");
			localeMap.put("_L_JJ50", "출금한도를 늘리기 위해서는 레벨을 업로드 진행자");
			localeMap.put("_L_JJ51", "자세한 사항은 고객지원-이용안내 메뉴를 참조 바랍니다");
			localeMap.put("_L_JJ52", "원화 출금 제한이 있나요?");
			localeMap.put("_L_JJ53", "원화 출금은 회원 가입 후 인증레벨3 이상인 회원");
			localeMap.put("_L_JJ54", "각 인증레벨별로 월 출금한도 및 1일 출금한도가 정해져 있습니다.");
			localeMap.put("_L_JJ55", "출금한도를 늘리기 위해서는 레벨을 업로드 진행자");
			localeMap.put("_L_JJ56", "자세한 사항은 고객지원-이용안내 메뉴를 참조 바랍니다.");
			localeMap.put("_L_JJ57", "입출금 은행계좌를 잘못 등록 하였습니다. 변경할려면 어떻게 하나요?");
			localeMap.put("_L_JJ58", "[고객지원 - 1:1 문의 – 은행계좌등록/변경] 메뉴에서 문의에 남겨 주시면 관리자 확인 후 계좌 초기화를 해 드립니다. 다만 한번이라도 기존 계좌 번호로 입금한 내역이 있는 경우 해킹에 의한 악의적인 변경요청을 방지하기 위해 인증자료를 제출 받습니다. 초기화 된 후 재 등록 하십시오");
			localeMap.put("_L_JJ59", "원화를 보유하고 있는데 출금가능 KRW 잔고가 0 으로 표시 됩니다" );
			localeMap.put("_L_JJ60", "원화 출금 ‘24시간 제한’ 에 해당되는 경우는 아닌지 확인해주시기 바랍니다." );
			localeMap.put("_L_JJ61", "원화 출금 24시간 제한이 무엇인가요?" );
			localeMap.put("_L_JJ62", "보이스피싱등의 금융사고 방지하고 회원님들의 자산을 안전하게 지키기 위하여 24시간 이후에 원화 출금이 가능하도록 하였습니다." );
			localeMap.put("_L_JJ63", "주말 및 공휴일도 출금불가능, 영업일(월~금 오전9시~ 저녁6시) 에 원화출금 가능합니다." );
			localeMap.put("_L_JJ64", "원화 입금 시 계좌이체를 먼저하고 입금신청을 해야 하나요?" );
			localeMap.put("_L_JJ65", "아닙니다. 반드시 입금신청을 먼저 하시고 발급받은 ‘ 고객님의 성함 + 입금코드 ’  받는통장 메모기입하여 계좌이체 진행하기" );
			localeMap.put("_L_JJ66", "거래소에 등록된 입출금 계좌가 아닌 다른 계좌에서 입금을 해 버렸습니다." );
			localeMap.put("_L_JJ67", "충전 요청을 하신 후에 입금 내역 캡쳐, 신분증 주민번호 뒷자리 가리고 캡쳐, 출금하신 통장사본 캡쳐 업로드하면 처리 진행." );
			localeMap.put("_L_JJ68", "다만, 본인명의 통장계좌만가능 타인명의 계좌 일 경우 불가능");
			localeMap.put("_L_JJ69", "평생계좌번호(전화번호 사용)로도 입금이 가능한가요?");
			localeMap.put("_L_JJ70", "불가합니다. 해당 입금 수단으로 입금하시면 입금확인이 어렵습니다.");
			localeMap.put("_L_JJ71", "해당 건에 대한 환급시에도 인증자료 제출 등 많은 시간이 소요 됩니다.");
			localeMap.put("_L_JJ72", "입금신청한 금액과 다른 금액을 계좌이체 해 버렸습니다. 어떻게 하나요?");
			localeMap.put("_L_JJ73", "[고객지원 – 1:1 문의 – 인증자료제출] 메뉴에서 이체결과확인증을 제출해 주시면 확인 후 입금처리 해 드립니다.");
			localeMap.put("_L_JJ73A", "평생계좌번호(전화번호 사용)로도 입금이 가능한가요?");
			localeMap.put("_L_JJ74", "실물 통장이 없어서 통장사본 제출이 불가능한데 어떻게 하나요?");
			localeMap.put("_L_JJ75", "실물 통장이 없는 인터넷통장 등은 해당 은행 사이트 및 앱에서 통장사본 출력 및 캡쳐가 가능합니다. 지원하지 않는 은행의 경우 은행 창구를 내방하여 통장 사본을 발급받아 제출하여 주십시오.");
			localeMap.put("_L_JJ76", "원화 입금이 하루 이상 지났는데도 처리되지 않고 있습니다. 어떻게 하나요?");
			localeMap.put("_L_JJ77", "[고객지원 – 1:1 문의 – 입출금 – 원화 (KRW)] 메뉴를 선택하신 후 ");
			localeMap.put("_L_JJ78", "제목에 “원화 입금 요청, 24시간 경과” 로 문의하기 하시면 확인 후 빠르게 처리해 드립니다.");
			localeMap.put("_L_JJ79", "빠른 처리를 위해 추가자료(입금내역증 등)을 요청할 수 있습니다.");
			localeMap.put("_L_JJ80", "보유하고 있는 원화보다 출금가능 잔액이 적게 표시됩니다 왜 그런가요?");
			localeMap.put("_L_JJ81", "현재 매수 주문 접수를 하진 않으셨는지 확인 부탁드립니다.");
			localeMap.put("_L_JJ82", "미체결 매수 주문이 없는데도 같은 증상이라면");
			localeMap.put("_L_JJ83", "[고객지원 - 1:1 문의 – 입출금 – 원화 (KRW)] 메뉴에 문의를 남겨주시기 바랍니다.");
			localeMap.put("_L_JJ84", "인증자료 제출은 어디서 하나요?");
			localeMap.put("_L_JJ85", "[고객지원 – 1:1 문의 – 인증자료제출] 메뉴를 이용하시기 바랍니다");
			// 암호화폐 시작 
			localeMap.put("_L_CR01", "암호화폐 입금/출금");
			localeMap.put("_L_CR11", "입출금 내역");
			localeMap.put("_L_CR02", "암호화폐 지갑 주소는 어디서 확인하나요?");
			localeMap.put("_L_CR03", "로그인 후 입출금 메뉴에서 지갑 주소 확인을 원하시는 암호화폐를 선택하신 후 입금주소 탭을 클릭 하시면 확인 가능합니다.");
			localeMap.put("_L_CR04", "지갑 생성을 하지 않으셨다면 [입금 주소 생성하기] 버튼을 클릭하여 주소 생성이 가능합니다");
			localeMap.put("_L_CR05", "암호화폐 출금 한도는 어떻게 되나요?");
			localeMap.put("_L_CR06", "출금하고자 하는 암호화폐의 원화 환산액(출금수량*원화가격) 인증레벨 별 출금한도 이내에서만 가능합니다. 예를 들어 회원님의 인증레벨이 3이면 1일 출금한도는 3,000만원 이내에서만 가능하므로, 현재가가 반영된 원화 환산액 3천만원 이내의 암호화폐 수량 내에서만 가능합니다.");
			localeMap.put("_L_CR06A","암호화폐 출금 제한은 왜 하나요?");
			localeMap.put("_L_CR06B","만일의 경우 회원님의 암호화폐 계좌가 회원님의 개인정보 관리 부주의로 타인에게 노출되었을 경우에라도 피해 확산을 방지하고, 거래소에 대한 해킹 피해 방지를 위한 방편의 일환으로 실시하는 보안 규정입니다.");

			localeMap.put("_L_CR07", "암호화폐 출금수수료는 얼마인가요?");
			localeMap.put("_L_CR08", "출금수수료는 암호화폐 별로 상이하며 각 암호화폐 별 출금수수료는 [고객지원 – 수수료안내] 메뉴를 참조하시면 됩니다.");
			localeMap.put("_L_CR09", "암호화폐 입출금 시간이 지연되는 이유가 있나요?");
			localeMap.put("_L_CR10", "암호화폐의 입출금 소요 시간은 암호화폐별로 다를 수 있으며 보통 10분~30분 정도 소요 됩니다. 다만 블록체인 네트워크 상에 일시적으로 과도한 입출금 요청이 발생하는 경우 다소 지연될 수 있습니다");
			localeMap.put("_L_CR11", "암호화폐 출금 주소를 잘못 입력한 경우 어떻게 하나요?");
			localeMap.put("_L_CR12", "암호화폐 특성 상 출금 신청이 완료되어 블록체인에 기록되면 취소가 불가능 합니다. 송금과정은 블록체인 네트워크에서 처리되므로 Bita500에서 도움을 드릴수가 없습니다. 반드시 출금 주소를 반복 확인하시고 출금신청 하시기 바랍니다.");
			localeMap.put("_L_CR13", "입금 주소에 다른 암호화폐를 입금한 오입금의 경우 어떻게 하나요?");
			localeMap.put("_L_CR14", "오입금 사례별 복구를 도와드릴 수 있는 상황은 제한적이며, 상황에 따라 복구 작업을 위한 수수료 및 시일이 소요됩니다.");
			localeMap.put("_L_CR15", "※ 오입금 복구 가능한 경우");
			localeMap.put("_L_CR16", "1) 리플 (XRP) Destination Tag 를 잘못 작성한 오입금");
			localeMap.put("_L_CR17", "- 해당 Destination Tag나 메모가 타인에게 발급되지 않은 경우, 영업일 기준 최대 5일이 소요됩니다.");
			localeMap.put("_L_CR18", "해당 Destination Tag나 메모가 타인에게 발급되고 오입금 받은 고객이 반환에 협조하지 않는 경우, 부당이득반환청구 소송을 해야할 수도 있습니다.");
			localeMap.put("_L_CR19", "-1:1 문의하기를 통해 접수해 주세요. 처리에 시간이 소요될 수 있습니다.");
			localeMap.put("_L_CR20", "※ 오입금 접수는 [고객지원 – 1:1 문의하기 – 입출금] 메뉴를 통해 접수해 주세요.");
			localeMap.put("_L_CR20A", "※ 기술적 문제로 모든 오입금 내역이 복구되지 않을 수 있습니다.");
			localeMap.put("_L_CR21", "암호화폐 입금시 주소외 추가로 입력해야 하는 정보를 누락하고 전송한 경우는 어떻게 하나요?");
			localeMap.put("_L_CR22", "블록체인 트랜잭션ID 나 해시값을 1:1 문의에 문의구분 암호화폐 입출금 선택하고 올려주세요. 다만 이 경우 처리하는데 최장 5일까지 소요 될 수 있습니다.");
			localeMap.put("_L_CR23", "추가로 고객지원 -> 인증자료제출 화면에서 인증목적구분을 암호화폐 입출금 선택하시고 보내신 사이트에서 전송상세 내역을 캡쳐해 올려 주셔야 합니다.");
			localeMap.put("_L_CR24", "원화 입금없이 암호화폐만 입금했다 출금할 경우에도 24시간 제한이 있나요?");
			localeMap.put("_L_CR25", "암호화폐만 입금했다 매도 후 원화로 출금하거나 암호화폐를 출금하는 경우에는 제한이 없습니다");
			localeMap.put("_L_CR26", "암호화폐 입금이 하루 이상 지났는데도 처리되지 않고 있습니다. 어떻게 하나요?");
			localeMap.put("_L_CR27", "[고객지원 – 1:1 문의 – 입출금] 메뉴에서 해당 암호화폐 선택 후 제목에 암호화폐명을 넣어 “OOO 입금요청합니다 – 입금 후 24시간 경과＂ 라고 경과 시간을 기재해 주시면 우선적으로 확인해 드리겠습니다. TXID를 문의 내용에 기입해 주시고 보내신 쪽에서 전송내역을 수량이 보이게끔 캡쳐 및 사진촬영 하셔서 파일 확장자 jpg ,png 로 파일첨부하여 문의해 주시기 바랍니다.");
			localeMap.put("_L_CR28", "보유하고 있는 잔고보다 출금가능 수량이 적게 표시됩니다 왜 그런가요?");
			localeMap.put("_L_CR29", "현재 암호화폐 매도 주문을 걸어 놓으신게 없는지 확인 하십시오 미체결 매도 주문이 없는데 그렇다면 1:1 문의에 올려 주세요");
			localeMap.put("_L_CR30", "트랜잭션ID (TXID) 또는 해시값이 뭔가요?");
			localeMap.put("_L_CR31", "암호화폐를 거래소간에 송금할시 블록체인 네트워크 상에서 암호화폐 송금 내역을 식별하고 추적할 수 있도록 하는 유일한 키 값입니다. 해당 값이 없으면 암호화폐 송금 내역을 찾는것이 불가능 하며 송금측 거래소 사이트의 암호화폐 출금내역 화면에서 조회 및 확인 할 수 있습니다. ");
			// 입출금내역 -
			localeMap.put("_L_CR32A", "거래,주문 ");
			localeMap.put("_L_CR32", "거래수수료는 얼마인가요? ");
			localeMap.put("_L_CR33", "거래수수료는 매수/매도 전마켓(KRW,BTC,USDT) 공통 0.15%를 적용하고 있습니다. ");
			localeMap.put("_L_CR34", "미체결 주문은 어떻게 처리 되나요? ");
			localeMap.put("_L_CR35", "주문 취소 전까지는 미체결 상태로 계속 남아 있게 됩니다 ");
			localeMap.put("_L_CR36", "웹에서 주문 취소가 되지 않습니다. ");
			localeMap.put("_L_CR37", "화면 새로고침이나 브라우저 재시작을 해 보시면 취소가 될 수 있으며, ");
			localeMap.put("_L_CR38", "조치가 되지 않을 시 다른 브라우저를사용하여 시도 해보시면 조치가 될 수 있습니다. ");
			localeMap.put("_L_CR39", "주문 체결이 되었는데 체결 수량이나 금액이 실제 주문 내역과 일치하지 않습니다 ");
			localeMap.put("_L_CR40", "고객님의 거래내역 및 체결 내역을 확인 해보시고 일치하지 않으신다면, [고객지원 - 1:1 문의 – 기타] 메뉴에서 문의 내용을 남겨 주십시오. 확인 후 답변 드리겠습니다. ");
			// 기타 
			localeMap.put("_L_ET01", "기타");
			localeMap.put("_L_ET02", "회원 탈퇴 방법을 알려주세요 ");
			localeMap.put("_L_ET03", "로그인 하신 후 [고객지원 - 1:1 문의 – 회원 탈퇴] 메뉴에서 ");
			localeMap.put("_L_ET04", "탈퇴사유와 함께 탈퇴 요청을 해 주십시오. ");
			localeMap.put("_L_ET05", "탈퇴 처리전에 회원님의 계정에는 원화 및 암호화폐 잔고가 없어야 합니다 ");
			localeMap.put("_L_ET06", "다른 궁금한 사항은 어디에 물어보면 되나요? ");
			localeMap.put("_L_ET07", "로그인 하신 후 [고객지원 - 1:1 문의 – 기타] 메뉴에서 문의사항을 등록 하시면 담당자가 확인 후 답변을 드립니다. ");
			localeMap.put("_L_ET08", "기타 신분증 제출시 신분증과 신분증 들고 있는 사진 둘다 제출해야 하나요? ");
			localeMap.put("_L_ET09", "신분증과 신분증 들고 있는 사진 모두 제출해야 합니다. ");
			localeMap.put("_L_ET10", "기타 모바일 웹은 언제 서비스 되나요? ");
			localeMap.put("_L_ET11", "정확한 날짜를 확정 드릴 수 없어 죄송합니다. <br> 서비스 가능 시점이 되면 공지사항으로 안내 됩니다.-");


			

			// --- 1:1 문의 ---
			localeMap.put("_L_IQ01", "1:1 문의");
			localeMap.put("_L_IQ02", "문의 유형을 선택해 주세요");
			localeMap.put("_L_IQ03", "대분류를 선택해주세요");
			localeMap.put("_L_IQ04", "계정");
			localeMap.put("_L_IQ05", "입출금");

			localeMap.put("_L_IQ06", "제안");
			localeMap.put("_L_IQ07", "금융사고");
			localeMap.put("_L_IQ08", "이벤트");
			localeMap.put("_L_IQ09", "인증자료 제출");
			localeMap.put("_L_IQ10", "기타");

			localeMap.put("_L_IQ11", "소분류를 선택해 주세요");
			localeMap.put("_L_IQ12", "휴대전화 번호 초기화 ");
			localeMap.put("_L_IQ13", "개명");
			localeMap.put("_L_IQ14", "회원탈퇴");
			localeMap.put("_L_IQ15", "API문의");

			localeMap.put("_L_IQ16", "알림톡");
			localeMap.put("_L_IQ17", "카카오페이");
			localeMap.put("_L_IQ18", "서비스 개선");
			localeMap.put("_L_IQ19", "투자제안");
			localeMap.put("_L_IQ20", "제휴문의");
			
			localeMap.put("_L_IQ21", "암호화폐 기타 문의");
			localeMap.put("_L_IQ22", "기타");
			localeMap.put("_L_IQ23", "출금 정지 이의 신청");
			localeMap.put("_L_IQ24", "이벤트 지급 관련 문의");
			localeMap.put("_L_IQ25", "해킹/명의도용/보이스피싱");
			localeMap.put("_L_IQ26", "금융사기계정 거래 정지 해제");
			
			localeMap.put("_L_IQ27", "이벤트");
			localeMap.put("_L_IQ28", "인증자료 제출");
			localeMap.put("_L_IQ29", "기타");
			
			// 소분류 코인문의 시작
			localeMap.put("_L_IQ30", "검색");
			localeMap.put("_L_IQ31", "은행계좌등록/변경");localeMap.put("_L_IQ31A", "문의 하실 암호화폐명을 검색해 주세요");
			localeMap.put("_L_IQ32", "원화 (KRW)");
			localeMap.put("_L_IQ33", "비트코인 (BTC)");
			localeMap.put("_L_IQ34", "이더리움 (ETH)");
			localeMap.put("_L_IQ35", "비트코인캐시 (BCH)");
			localeMap.put("_L_IQ36", "리플 (XRP)");
			// 문의하기 공통 : 소분류 선택 나오는  문의 페이지 
			localeMap.put("_L_IQ37", "문의 내용을 입력해 주세요");
			localeMap.put("_L_IQ38", "문의 유형");
			localeMap.put("_L_IQ39", "입출금");
			localeMap.put("_L_IQ40", "비트코인");
			localeMap.put("_L_IQ41", "파일첨부");
			localeMap.put("_L_IQ42", "- 파일 확장자는 jpg, png 만 가능합니다. <br> - 총 용량은 50MB 미만이어야 합니다. ");
			localeMap.put("_L_IQ43", "- 주민등록초본 (발급일로부터 3개월 이내 제출할 경우만 유효)");
			localeMap.put("_L_IQ44", "제목");
			localeMap.put("_L_IQ45", "내용");
			localeMap.put("_L_IQ46", "개인정보 수집 및<br>이용 동의(필수)");
			localeMap.put("_L_IQ47", "- 수집 목적 : '개명' 상담 문의 처리<br>- 수집 항목 : 개명후 성함, 개명증명할 수 있는 주민등록 초본과 같은 서류<br> - 보유 기간 : 탈퇴 후 5년<br> ");
			localeMap.put("_L_IQ48", "- 회원은 상담 문의 처리로 개인정보를 제공하는 것에 동의하지 않을 수 있으며, 동의를 거부하시는 경우 처리가 불가능합니다.<br>- 위 내용을 반드시 전부 확인하신 뒤 동의하신다면 체크해주세요<br>");
			localeMap.put("_L_IQ49", "동의");
			localeMap.put("_L_IQ50", "1:1 문의하기");
			localeMap.put("_L_IQ51", "취소하기");
		
			// --- 문의내역 ---
			localeMap.put("_L_IQ52", "문의내역");
			localeMap.put("_L_IQ53", "1:1 문의내역");
			localeMap.put("_L_IQ54", "제목");
			localeMap.put("_L_IQ55", "작성일");
			localeMap.put("_L_IQ56", "답변일");			
			localeMap.put("_L_IQ57", "답변여부");
			localeMap.put("_L_IQ58", "문의 내역이 없습니다 ");
			localeMap.put("_L_IQ59", "문의내용 및 답변 ");
			localeMap.put("_L_IQ60", "선택하신 내역이 없습니다 ");
			localeMap.put("_L_IQ61", "문의내용");
			localeMap.put("_L_IQ62", "답변내용");

			


			

	




		// ------------- 언어팩 누락 종료 ------------------------------------
		

			localeMapValid = true;
	
		} else if(locale.equalsIgnoreCase("en")) {

			localeMap.put("_L_0001","Support");
            localeMap.put("_L_0002","Notice");
			localeMap.put("_L_0003","News");
			localeMap.put("_L_0004","Event");
			localeMap.put("_L_0005","User Guide");
			localeMap.put("_L_0006","Security guide");
			localeMap.put("_L_0007","FAQ");
			localeMap.put("_L_0008","Search");
			

			localeMap.put("_L_0009","Title");
            localeMap.put("_L_0010","Date");
			localeMap.put("_L_0011","Events and results");
			localeMap.put("_L_0012","Progress");

			localeMap.put("_L_0013","Market Classification");
			localeMap.put("_L_0014","- KRW Market : You can trade cryptocurrency in KRW (KRW).");
			localeMap.put("_L_0015","- BTC Market : You can buy and sell other digital assets with BTC (Bitcoin).");
			localeMap.put("_L_0016","- USDT Market : You can buy and sell other digital assets with USDT (Tether).");
			localeMap.put("_L_0017","Deposit and Withdrawal Limits by Certification Level");
			localeMap.put("_L_0018","Sortation");
			localeMap.put("_L_0019","Level 1");
			localeMap.put("_L_0020","Level 2");
			localeMap.put("_L_0021","Level 3");
			localeMap.put("_L_0022","Level 4");
			localeMap.put("_L_0023","Level 5");
			localeMap.put("_L_0024","Authentication method");
			localeMap.put("_L_0025","Email Authentication(automatic authentication)");
			localeMap.put("_L_0026","Mobile phone identity verification(automatic authentication)");
			localeMap.put("_L_0027","OTP registration (automatic authentication)");
			localeMap.put("_L_0028","ID verification(Separate examination)");
			localeMap.put("_L_0029","Residence verification (separate examination)");
			localeMap.put("_L_0030","Deposit limit");
			localeMap.put("_L_0031","Cryptocurrency");
			localeMap.put("_L_0032","Unlimited");
			localeMap.put("_L_0033","(1Day limit)");
			localeMap.put("_L_0034","Million <br/>KRW");
			localeMap.put("_L_0035","Separate Examination");

			localeMap.put("_L_0036","1. Create a secure login password");
			localeMap.put("_L_0037","2. Change your login password periodically");
			localeMap.put("_L_0038","Change password");
			localeMap.put("_L_0039","3. Disable your browser's auto-save password feature");
			localeMap.put("_L_0040","4. Paste Confirm cryptocurrency withdrawal address");
			localeMap.put("_L_0041","5. Enhancing login security through OTP authentication");
			localeMap.put("_L_0042","OTP authentication setting");
			localeMap.put("_L_0043","6. Check connection history");
			localeMap.put("_L_0044","View connection history");

			localeMap.put("_L_0045","The withdrawal limit is the combined limit of KRW and cryptocurrency, and in the case of cryptocurrency, the withdrawal limit is applied based on the KRW conversion amount at the time of withdrawal.");
			localeMap.put("_L_0046","Level 5 is approved through a separate procedure according to the screening criteria. Please contact us through 1:1 inquiry.");
			localeMap.put("_L_0047","Deposits and withdrawals are restricted if fraudulent transactions are suspected.");
			
			localeMap.put("_L_0048","Withdrawal Fee");
			localeMap.put("_L_0049","Sortation");
			localeMap.put("_L_0050","Deposit fee");
			localeMap.put("_L_0051","Free");
			localeMap.put("_L_0052","Withdrawal Fee");
			localeMap.put("_L_0053","Minimum withdrawal amount<br/>(quantity)");
			localeMap.put("_L_0054","Minimum order amount");
			localeMap.put("_L_0055","KRW");
			localeMap.put("_L_0056","Market");

			localeMap.put("_L_0057","Order Type");
			localeMap.put("_L_0058","Limited price order: Since the order is made by entering the execution price and quantity, if the entered price is not reached, the order will not be executed.");
			localeMap.put("_L_0059","Fee Guide");

			localeMap.put("_L_0060","NOSSBIT Exchange Listing Guide");
			localeMap.put("_L_0061","Virtual assets are an area with high future value and development potential..");
			localeMap.put("_L_0062","NOSSBIT Exchange provides a wider and more secure investment opportunity through projects with high future value and carefully selected criteria.");
			localeMap.put("_L_0063","In order to take the lead in the sound development of the blockchain industry and the digital virtual asset industry in the future,");
			localeMap.put("_L_0064","we will guide you by setting the criteria for listing the NOSSBIT Exchange.");
			localeMap.put("_L_0065","Listing Policy");
			localeMap.put("_L_0066","It allows exchange users to safely trade virtual assets that have passed the screening according to the listing policy of NOSSBIT Exchange, a digital virtual asset exchange.");
			localeMap.put("_L_0067","We protect investors by maintaining and managing approved virtual asset projects in the best possible security condition through strict, transparent, and fair internal and external screening standards.");
			localeMap.put("_L_0068","In order to protect investors even after listing, we continuously and systematically monitor virtual asset projects in real time to review virtual assets that do not meet technical issues and legal aspects to proceed with delisting.");
			localeMap.put("_L_0069","Listing Judgment Criteria");
			localeMap.put("_L_0070","Currently, the legal status of virtual assets is unclear and there are many risk factors as investment assets because there is no control tower. We comply with strict screening standards for virtual asset projects so that these high-risk assets can be safely provided and traded with confidence..");
			localeMap.put("_L_0071","Vision and technology");
			localeMap.put("_L_0072","Sustainable Stability");
			localeMap.put("_L_0073","Transparency in planning and operational management");
			localeMap.put("_L_0074","Competence and financial health of the team");
			localeMap.put("_L_0075","Negotiability and investment value according to the business");
			localeMap.put("_L_0076","");
			localeMap.put("_L_0077","Listing review process<br/>- We conduct detailed review of project progress through collaboration with internal and external experts and the disclosure platform.<br/>- listing application<br/>- Review of project dossier<br/>- Project team meeting and due diligence<br/>- Comitê de Deliberação de Listagem Revisão legal do ativo virtual<br/>- Notification of listing examination results<br/>- Listing on exchanges");
			localeMap.put("_L_0078","Listing maintenance monitoring review");
			localeMap.put("_L_0079","Regularly manage and review the suitability of virtual assets listed in the project.");
			localeMap.put("_L_0080","Security verification and monitoring is performed in real-time to prevent listed virtual assets from being exposed to high risk.");
			localeMap.put("_L_0081","Regular review is conducted based on delisting standards.");
			localeMap.put("_L_0082","Delisting criteria");
			localeMap.put("_L_0083","Legal issues – if the project has been involved in money laundering and crime or violates applicable laws, regulations or requirements");
			localeMap.put("_L_0084","Project technical problems – In case of security problems such as discovering vulnerabilities in related technologies");
			localeMap.put("_L_0085","Problems in providing information – In case of dissemination of false information or a serious delay other than the schedule specified in the white paper");
			localeMap.put("_L_0086","Operational problems – When significant damage is expected to members of the virtual asset exchange due to negative operations (multi-level, marketing sales), etc.");
			localeMap.put("_L_0087","Notice");
			localeMap.put("_L_0088","NOSSBIT exchange does not require any fees for listing as collateral.");
			localeMap.put("_L_0089","For all inquiries and procedures related to listing, please contact NOSSBIT listing inquiry by email listing@nossbit.com and listing support application only.");
			localeMap.put("_L_0090","All listing-related procedures are carried out by forming professional judges without any separate intervention by the company's management.");
			localeMap.put("_L_0091","Listing application");
			localeMap.put("_L_0092","");
			localeMap.put("_L_0093","Listing information");

			localeMapValid = true;
		} else {
			localeMap.put("_L_0001","Support");
            localeMap.put("_L_0002","Notice");
			localeMap.put("_L_0003","News");
			localeMap.put("_L_0004","Event");
			localeMap.put("_L_0005","User Guide");
			localeMap.put("_L_0006","Security guide");
			localeMap.put("_L_0007","FAQ");
			localeMap.put("_L_0008","Search");
			

			localeMap.put("_L_0009","Title");
            localeMap.put("_L_0010","Date");
			localeMap.put("_L_0011","Events and results");
			localeMap.put("_L_0012","Progress");

			localeMap.put("_L_0013","Market Classification");
			localeMap.put("_L_0014","- KRW Market : You can trade cryptocurrency in KRW (KRW).");
			localeMap.put("_L_0015","- BTC Market : You can buy and sell other digital assets with BTC (Bitcoin).");
			localeMap.put("_L_0016","- USDT Market : You can buy and sell other digital assets with USDT (Tether).");
			localeMap.put("_L_0017","Deposit and Withdrawal Limits by Certification Level");
			localeMap.put("_L_0018","Sortation");
			localeMap.put("_L_0019","Level 1");
			localeMap.put("_L_0020","Level 2");
			localeMap.put("_L_0021","Level 3");
			localeMap.put("_L_0022","Level 4");
			localeMap.put("_L_0023","Level 5");
			localeMap.put("_L_0024","Authentication method");
			localeMap.put("_L_0025","Email Authentication(automatic authentication)");
			localeMap.put("_L_0026","Mobile phone identity verification(automatic authentication)");
			localeMap.put("_L_0027","OTP registration (automatic authentication)");
			localeMap.put("_L_0028","ID verification(Separate examination)");
			localeMap.put("_L_0029","Residence verification (separate examination)");
			localeMap.put("_L_0030","Deposit limit");
			localeMap.put("_L_0031","Cryptocurrency");
			localeMap.put("_L_0032","Unlimited");
			localeMap.put("_L_0033","(1Day limit)");
			localeMap.put("_L_0034","Million <br/>KRW");
			localeMap.put("_L_0035","Separate Examination");

			localeMap.put("_L_0036","1. Create a secure login password");
			localeMap.put("_L_0037","2. Change your login password periodically");
			localeMap.put("_L_0038","Change password");
			localeMap.put("_L_0039","3. Disable your browser's auto-save password feature");
			localeMap.put("_L_0040","4. Paste Confirm cryptocurrency withdrawal address");
			localeMap.put("_L_0041","5. Enhancing login security through OTP authentication");
			localeMap.put("_L_0042","OTP authentication setting");
			localeMap.put("_L_0043","6. Check connection history");
			localeMap.put("_L_0044","View connection history");

			localeMap.put("_L_0045","The withdrawal limit is the combined limit of KRW and cryptocurrency, and in the case of cryptocurrency, the withdrawal limit is applied based on the KRW conversion amount at the time of withdrawal.");
			localeMap.put("_L_0046","Level 5 is approved through a separate procedure according to the screening criteria. Please contact us through 1:1 inquiry.");
			localeMap.put("_L_0047","Deposits and withdrawals are restricted if fraudulent transactions are suspected.");
			
			localeMap.put("_L_0048","Withdrawal Fee");
			localeMap.put("_L_0049","Sortation");
			localeMap.put("_L_0050","Deposit fee");
			localeMap.put("_L_0051","Free");
			localeMap.put("_L_0052","Withdrawal Fee");
			localeMap.put("_L_0053","Minimum withdrawal amount<br/>(quantity)");
			localeMap.put("_L_0054","Minimum order amount");
			localeMap.put("_L_0055","KRW");
			localeMap.put("_L_0056","Market");

			localeMap.put("_L_0057","Order Type");
			localeMap.put("_L_0058","Limited price order: Since the order is made by entering the execution price and quantity, if the entered price is not reached, the order will not be executed.");
			localeMap.put("_L_0059","Fee Guide");

			localeMap.put("_L_0060","NOSSBIT Exchange Listing Guide");
			localeMap.put("_L_0061","Os ativos virtuais são uma área com alto valor futuro e potencial de desenvolvimento.");
			localeMap.put("_L_0062","O NOSSBIT Exchange oferece uma oportunidade de investimento mais ampla e segura por meio de projetos com alto valor futuro e critérios cuidadosamente selecionados.");
			localeMap.put("_L_0063","Para assumir a liderança no desenvolvimento sólido da indústria de blockchain e da indústria de ativos virtuais digitais no futuro,");
			localeMap.put("_L_0064","iremos orientá-lo definindo os critérios para listar o NOSSBIT Exchange.");
			localeMap.put("_L_0065","Política de Listagem");
			localeMap.put("_L_0066","Ele permite que os usuários do Exchange negociem com segurança ativos virtuais que passaram na triagem de acordo com a política de listagem do NOSSBIT Exchange, uma troca de ativos virtuais digitais.");
			localeMap.put("_L_0067","Protegemos os investidores mantendo e gerenciando projetos de ativos virtuais aprovados na melhor condição de segurança possível por meio de padrões de triagem internos e externos rígidos, transparentes e justos.");
			localeMap.put("_L_0068","Para proteger os investidores mesmo após a listagem, monitoramos de forma contínua e sistemática os projetos de ativos virtuais em tempo real para revisar os ativos virtuais que não atendem a questões técnicas e legais para proceder com o fechamento.");
			localeMap.put("_L_0069","Critérios de Julgamento da Lista");
			localeMap.put("_L_0070","Atualmente, o status legal dos ativos virtuais não é claro e existem muitos fatores de risco como ativos de investimento porque não há torre de controle. Cumprimos padrões de triagem rígidos para projetos de ativos virtuais, para que esses ativos de alto risco possam ser fornecidos com segurança e negociados com confiança.");
			localeMap.put("_L_0071","Visão e tecnologia");
			localeMap.put("_L_0072","Estabilidade Sustentável");
			localeMap.put("_L_0073","Transparência no planejamento e gestão operacional");
			localeMap.put("_L_0074","Competência e saúde financeira da equipe");
			localeMap.put("_L_0075","Negociabilidade e valor do investimento de acordo com o negócio");
			localeMap.put("_L_0076","");
			localeMap.put("_L_0077","Processo de revisão de listagem<br/>- Conduzimos uma análise detalhada do progresso do projeto por meio da colaboração com especialistas internos e externos e da plataforma de divulgação.<br/>- aplicativo de lista<br/>- Reuniões da equipe do projeto e devida diligência<br/>- Comitê de Deliberação de Listagem Deliberação Legal do Ativo Virtual<br/>- Notificação dos resultados do exame de lista<br/>- Listagem em bolsas");
			localeMap.put("_L_0078","Revisão de monitoramento de manutenção de lista");
			localeMap.put("_L_0079","Gerenciar e revisar regularmente a adequação dos ativos virtuais listados para projetos.");
			localeMap.put("_L_0080","A verificação e o monitoramento de segurança são realizados em tempo real para evitar que os ativos virtuais listados sejam expostos a alto risco.");
			localeMap.put("_L_0081","A revisão regular é conduzida de acordo com os padrões de fechamento de capital.");
			localeMap.put("_L_0082","Critérios de exclusão");
			localeMap.put("_L_0083","Questões legais - se o projeto estiver envolvido em lavagem de dinheiro e crime ou violar as leis, regulamentos ou requisitos aplicáveis");
			localeMap.put("_L_0084","Problemas técnicos do projeto - Em caso de problemas de segurança, como descobrir vulnerabilidades em tecnologias relacionadas");
			localeMap.put("_L_0085","Problemas no fornecimento de informações - Em caso de divulgação de informações falsas ou um atraso sério diferente do cronograma especificado no white paper");
			localeMap.put("_L_0086","Problemas operacionais - Quando danos significativos são esperados para membros da troca de ativos virtuais devido a operações negativas (multinível, vendas de marketing), etc.");
			localeMap.put("_L_0087","Cuidado");
			localeMap.put("_L_0088","A troca NOSSBIT não exige nenhuma taxa para listagem como garantia.");
			localeMap.put("_L_0089","Para todas as perguntas e procedimentos relacionados à listagem, entre em contato com a consulta de listagem NOSSBIT por e-mail list@nossbit.com e listando apenas o aplicativo de suporte.");
			localeMap.put("_L_0090","Todos os procedimentos relacionados com a listagem são realizados por meio da formação de juízes profissionais sem qualquer intervenção separada da administração da empresa.");
			localeMap.put("_L_0091","Aplicativo de listagem");
			localeMap.put("_L_0092","");
			localeMap.put("_L_0093","Listing information");

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

	<article class="layoutD">

		<aside>
			<h1 class="menutitle">
				<%=getLocaleString("_L_0001")%>
			</h1>
			<ul class="vtab">
				<li><a onClick="javascript:toggleNoticeList(0, true);" href="#vtab1" class="vselected"><%=getLocaleString("_L_0002")%></a></li>
                <li><a href="#vtab9"><%=getLocaleString("_L_0003")%></a></li>
				<li><a onClick="javascript:toggleEventList(0, true);" href="#vtab2"><%=getLocaleString("_L_0004")%></a></li>
				<li><a href="#vtab3"><%=getLocaleString("_L_0005")%></a></li>
				<li><a href="#vtab10"><%=getLocaleString("_L_0093")%></a></li>
				<li><a href="#vtab5"><%=getLocaleString("_L_0006")%></a></li>
				<li><a href="#vtab6"><%=getLocaleString("_L_0007")%></a></li>
				<c:if test="${sessionScope.userInfo.loginYn == 1}">
				<li><a href="#vtab7"><%=getLocaleString("_L_0005B")%></a></li> 
				<li><a href="#vtab8"><%=getLocaleString("_L_0005C")%></a></li> 
				</c:if>
			</ul>

		</aside>

		<div class="content">
			<ul class="vpanel">
				
				<!--공지사항 시작-->
				<li id="vtab1" class="notice">
					<h1 class="subtitle">
						<%=getLocaleString("_L_0002")%>
					</h1>
					<!--공지사항 검색및리스트 시작 : 내용이 보이면 이 영역은 안보입니다.-->
					<div id="notice-list" class="notice-list" style="display: block;">
						<div class="search">
							<input type="text" id="notice-search-text"><button id="notice-search"><%=getLocaleString("_L_0008")%></button>
						</div>

						<!--<공지사항 리스트 테이블 시작-->
            <table class="table1">
							<colgroup>
								<col>
								<col style="width: 160px">
							</colgroup>
							<thead>
								<tr>
									<th><%=getLocaleString("_L_0009")%></th>
									<th><%=getLocaleString("_L_0010")%></th>
								</tr>
							</thead>
							<tbody id="notice_list_table_tbody">
								<!-- <tr>
									<td class="new er">
										<a href="#" onClick="javascript:toggleNoticeList(0, false);"></a>
									</td>
									<td></td>
								</tr> -->
							</tbody>
						</table>
						<!--<공지사항 리스트 테이블 끝-->

						<!-- 페이징 : Start -->
						<ul id="notice-pagination" class="pagination">
							<li><a class="btn-page first"></a></li>
							<li><a class="btn-page prev"></a></li>
							<li><a href="#" class="active">1</a></li>
							<li><a href="#">2</a></li>
							<li><a class="btn-page next"></a></li>
							<li><a class="btn-page last"></a></li>
						</ul>
						<!-- //페이징 : End -->
					</div>
					<!--공지사항 검색 및 리스트 끝-->

					<!--공지사항 내용보기 영역 시작 : 리스트가 보이면 이 영역은 안보입니다. -->
					<div id="notice-conts" class="notice-conts" style="display: none;">
						<h1 id="notice-conts-title">
							<span id="notice-conts-date"></span>
						</h1>

						<div id="notice-detail" class="notice-detail" style="word-break:break-all">
							<p> </p>
							<h2></h2>
						</div>

						<div class="fl-right mgt20">
							<button onClick="javascript:toggleNoticeList(0, true);" class="big"><%=getLocaleString("_L_NOT01")%></button>
						</div>

					</div>
					<!--공지사항 내용보기 영역 끝-->

				</li>
				<!--공지사항 끝-->

                <!-- 보도뉴스 시작-->
                <li id="vtab9" class="notice" style="display:none;">
                    <h1 class="subtitle">
                        <%=getLocaleString("_L_0003")%>   
                    </h1>
                    
                    <!--이벤트 리스트 시작 : 내용이 보이면 이 영역은 안보입니다.-->
                    <div class="notice-list" style="display: block;">
						<div class="search">
							<input type="text" class="notice-search-text"><button class="notice-search"><%=getLocaleString("_L_0008")%></button>
						</div>

						<!--<공지사항 리스트 테이블 시작-->
            <table class="table1">
							<colgroup>
								<col>
								<col style="width: 160px">
							</colgroup>
							<thead>
								<tr>
									<th><%=getLocaleString("_L_0009")%></th>
									<th><%=getLocaleString("_L_0010")%></th>
								</tr>
							</thead>
							<tbody id="notice_list_table_tbody">
								<!-- <tr>
									<td class="new er">
										<a href="#" onClick="javascript:toggleNoticeList(0, false);"></a>
									</td>
									<td></td>
								</tr> -->
							</tbody>
						</table>

						<!--<공지사항 리스트 테이블 끝-->

						<!-- 페이징 : Start -->
						<ul id="notice-pagination" class="pagination">
							<li><a class="btn-page first"></a></li>
							<li><a class="btn-page prev"></a></li>
							<li><a href="#" class="active">1</a></li>
							<li><a href="#">2</a></li>
							<li><a class="btn-page next"></a></li>
							<li><a class="btn-page last"></a></li>
						</ul>
						<!-- //페이징 : End -->
					</div>
                    <!--이벤트및 리스트 끝-->

                    <!--이벤트 내용보기 영역 시작 : 리스트가 보이면 이 영역은 안 보입니다.-->
                   	<!--공지사항 내용보기 영역 시작 : 리스트가 보이면 이 영역은 안보입니다. -->
					<div class="notice-conts" style="display: none;">
						<h1 class="notice-conts-title">
							<span class="notice-conts-date"></span>
						</h1>

						<div class="notice-detail" style="word-break:break-all">
							<p> </p>
							<h2></h2>
						</div>

						<div class="fl-right mgt20">
							<button onClick="javascript:toggleNoticeList(0, true);" class="big"><%=getLocaleString("_L_NOT01")%></button>
						</div>

					</div>
					<!--공지사항 내용보기 영역 끝-->
                    <!--이벤트 내용보기 영역 끝-->

                </li>
                <!--이벤트 끝-->

				<!--이벤트 시작-->
				<li id="vtab2" class="event" style="display:none;">
					<h1 class="subtitle">
						<%=getLocaleString("_L_0004")%>
					</h1>
					
					<!--이벤트 리스트 시작 : 내용이 보이면 이 영역은 안보입니다.-->
					<div id="event-list" class="event-list" style="display: block;">
						<h2 class="tabletitle"><%=getLocaleString("_L_0011")%></h2>
						
						<!--이벤트 리스트 테이블 시작-->
                                <table class="table1">
							<colgroup>
								<col>
                                        <col>
								<col style="width: 80px">
							</colgroup>
							<thead>
								<tr>
									<th><%=getLocaleString("_L_0009")%></th>
									<th><%=getLocaleString("_L_0010")%></th>
									<th><%=getLocaleString("_L_0012")%></th>
								</tr>
							</thead>
							<tbody id="event_list_table_tbody">
								<!-- <tr>
									<td class="bold">
									</td>
									<td></td>
									<td class="on"></td>
								</tr> -->
							</tbody>
						</table>
						<!--<이벤트 리스트 테이블 끝-->

						<!-- 페이징 : Start -->
						<ul id="event-pagination" class="pagination">
							<li><a class="btn-page first"></a></li>
							<li><a class="btn-page prev"></a></li>
							<li><a href="#" class="active">1</a></li>
							<li><a class="btn-page next"></a></li>
							<li><a class="btn-page last"></a></li>
						</ul>
						<!-- //페이징 : End -->
					</div>
					<!--이벤트및 리스트 끝-->

					<!--이벤트 내용보기 영역 시작 : 리스트가 보이면 이 영역은 안 보입니다.-->
					<div id="event-conts" class="event-conts" style="display: none;">
						<h1 id="event-conts-title">
							<span></span>
						</h1>
						<div id="event-detail" class="event-detail">
						</div>
						<div class="fl-right mgt20">
							<button onClick="javascript:toggleEventList(0, true);" class="big"><%=getLocaleString("_L_EVT01")%> </button>
						</div>

					</div>
					<!--이벤트 내용보기 영역 끝-->

				</li>
				<!--이벤트 끝-->
				
				<!--이용안내 시작-->
				<li id="vtab3" class="useinfo" style="display:none;">
					<h1 class="subtitle">
						<%=getLocaleString("_L_0005")%>
					</h1>
					<a href="/resources/pdf/manual.pdf" class="green"><%=getLocaleString("_L_0005A")%></a>
					<h2 class="tabletitle">1. <%=getLocaleString("_L_0013")%></h2>
					<p><%=getLocaleString("_L_0014")%></p>
					<p><%=getLocaleString("_L_0015")%></p>
					<p><%=getLocaleString("_L_0016")%></p>
					
					<!-- 인증레벨 별 원화 출금한도 : Start -->
					<div class="content-item">
						<h2 class="tabletitle">2. <%=getLocaleString("_L_0017")%></h2>
						<div>
              <table class="table1 type03 line">
								<colgroup>
									<col style="width: 80px;">
									<col style="width: 110px;">
									<col>
									<col>
									<col>
									<col>
								</colgroup>
								<thead>
									<tr>
                                                <th colspan="2"><%=getLocaleString("_L_0018")%></th>
										<th><%=getLocaleString("_L_0019")%></th>
										<th><%=getLocaleString("_L_0020")%></th>
										<th><%=getLocaleString("_L_0021")%></th>
										<th><%=getLocaleString("_L_0022")%></th>
										<th><%=getLocaleString("_L_0023")%></th>
									</tr>
								</thead>
								<tbody>
									<tr>
                                                <th colspan="2"><%=getLocaleString("_L_0024")%></th>
										
                                                <td><%=getLocaleString("_L_0025")%></td>
										<td><%=getLocaleString("_L_0026")%></td>
										<td><%=getLocaleString("_L_0027")%></td>
										<td><%=getLocaleString("_L_0028")%></td>
										<td><%=getLocaleString("_L_0029")%></td>
									</tr>
									<tr>   <!-- 입금한도 -->
										<th rowspan="2"><%=getLocaleString("_L_0030")%></th>
                    <th><%=getLocaleString("_L_0005E")%></th>
										<td>-</td>
										<td>-</td>
										<td><%=getLocaleString("_L_0032")%></td>
										<td><%=getLocaleString("_L_0032")%></td>
										<td><%=getLocaleString("_L_0032")%></td>
									</tr>
									<tr>
                                                <th><%=getLocaleString("_L_0031")%></th>
										<td>-</td>
										<td><%=getLocaleString("_L_0032")%></td>
										<td><%=getLocaleString("_L_0032")%></td>
										<td><%=getLocaleString("_L_0032")%></td>
										<td><%=getLocaleString("_L_0032")%></td>
									</tr>
									 <tr>  
										<th><%=getLocaleString("_L_0005D")%></th> <!-- 출금한도 -->
										<th>KRW +<br/><%=getLocaleString("_L_0031")%><br/><%=getLocaleString("_L_0033")%></th>
										<td>-</td>
										<td>-</td>
										<!-- <td>5,000,000<%=getLocaleString("_L_0034")%></td> -->
										<!-- <td>100,000,000<%=getLocaleString("_L_0034")%></td>
										<td>300,000,000<%=getLocaleString("_L_0034")%></td> -->
										<td></td>
										<td>3,000<%=getLocaleString("_L_0005F")%></td>
										<td>5,000<%=getLocaleString("_L_0005F")%></td>
									</tr>
								</tbody>
							</table>
							<ul class="disc">
								<li style="display: block !important;"><%=getLocaleString("_L_0045")%></li>
								<li style="display: block !important;;"><%=getLocaleString("_L_0046")%></li>
								<li style="display: block !important;;"><%=getLocaleString("_L_0047")%></li>
							</ul>
							<p>- <%=getLocaleString("_L_0048")%></p>
							<table class="table1 type03 line">
								<thead>
									<tr>
										<th><%=getLocaleString("_L_0049")%></th>
										<th><%=getLocaleString("_L_0050")%></th>
										<th><%=getLocaleString("_L_0052")%></th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td><%=getLocaleString("_L_0005E")%><%=getLocaleString("_L_0005G")%></td> <!-- 만원,(원화) -->
										<td><%=getLocaleString("_L_0051")%></td>
										<td>1,000</td>
									</tr>
									<tr>
										<td><%=getLocaleString("_L_0005H")%></td>
										<td><%=getLocaleString("_L_0051")%></td>
										<td>0.015</td>
									</tr>
									<tr>
										<td><%=getLocaleString("_L_0005I")%></td>
										<td><%=getLocaleString("_L_0051")%></td>
										<td>0.0015</td>
									</tr>
									<tr>
										<td><%=getLocaleString("_L_0005J")%></td>
										<td><%=getLocaleString("_L_0051")%></td>
										<td>2</td>
									</tr>
									<tr>
										<td><%=getLocaleString("_L_0005K")%></td>
										<td><%=getLocaleString("_L_0051")%></td>
										<td>3,000</td>
									</tr>
								</tbody>
							</table>
							<ul class="disc">
								<li style="display: none;color: red;"></li>
								<li style="display: none;color: red;"><%=getLocaleString("_L_0005Y")%> </li>
								<li style="display: none;color: red;"><%=getLocaleString("_	L_0005Z")%> </li>
								<li style="display: none;color: red;"><%=getLocaleString("_L_0006Z")%> </li>
							</ul>
						</div>
					</div>

					<div class="content-item">
						<h2 class="tabletitle">3. <%=getLocaleString("_L_0054")%></h2>
							<table class="table1 type03 line">
								<thead>
									<tr>
										<th>KRW <%=getLocaleString("_L_0056")%></th>
										<th>BTC <%=getLocaleString("_L_0056")%></th>
										<th>USDT <%=getLocaleString("_L_0056")%></th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>3,000 KRW</td>
										<td>0.0005 BTC</td>
										<td>3 USDT</td>
									</tr>
								</tbody>
							</table>
					</div>

					<div class="content-item">
						<h2 class="tabletitle">4.<%=getLocaleString("_L_0005L")%></h2>
						<ul class="disc">
							<li style="display: none;"><%=getLocaleString("_L_0005M")%></li>
						</ul>
					</div>

					<div class="content-item">
						<h2 class="tabletitle">5. <%=getLocaleString("_L_0059")%></h2>
							<table class="table1 type03 line">
								<thead>
									<tr>
										<th>KRW <%=getLocaleString("_L_0056")%></th>
										<th>BTC <%=getLocaleString("_L_0056")%></th>
										<th>USDT <%=getLocaleString("_L_0056")%></th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>0.15%</td>
										<td>0.15%</td>
										<td>0.15%</td>
									</tr>
								</tbody>
							</table>
					</div>
					<!-- //인증레벨 별 원화 출금한도 : End -->

				</li>
				<!--이용안내 끝-->

				<!--상장안내 시작-->
				<li id="vtab10" class="lisinfo" style="display:none;">
					<h1 class="subtitle">
						<%=getLocaleString("_L_0093")%>
					</h1>
					
					<h2 class="tabletitle"><%=getLocaleString("_L_0060")%></h2>
					<p><%=getLocaleString("_L_0061")%></p>
					<p><%=getLocaleString("_L_0062")%></p>
					<p><%=getLocaleString("_L_0063")%></p>
					<p><%=getLocaleString("_L_0064")%></p>
					<!-- 인증레벨 별 원화 출금한도 : Start -->
					<div class="content-item">
						<h3 class="tabletitle"><%=getLocaleString("_L_0065")%></h3>
						<ul class="disc">
							<li><%=getLocaleString("_L_0066")%></li>
							<li><%=getLocaleString("_L_0067")%></li>
							<li><%=getLocaleString("_L_0068")%></li>
						</ul>
					</div>

					<div class="content-item">
						<h3 class="tabletitle"><%=getLocaleString("_L_0069")%></h3>
						<p><%=getLocaleString("_L_0070")%></p>
						<ul class="disc">
							<li><%=getLocaleString("_L_0071")%></li>
							<li><%=getLocaleString("_L_0072")%></li>
							<li><%=getLocaleString("_L_0073")%></li>
							<li><%=getLocaleString("_L_0074")%></li>
							<li><%=getLocaleString("_L_0075")%></li>
						</ul>	
					</div>

					<div class="content-item">
						<h3 class="tabletitle"><%=getLocaleString("_L_0076")%></h3>
						<ol class="disc">
							<li>1. <%=getLocaleString("_L_0005N")%> </li>
							<li>2. <%=getLocaleString("_L_0005O")%></li>
							<li>3. <%=getLocaleString("_L_0005P")%></li>
							<li>4. <%=getLocaleString("_L_0005Q")%></li>
							<li>5. <%=getLocaleString("_L_0005R")%></li>
							<li>6. <%=getLocaleString("_L_0005S")%></li>
						</ol>	
					</div>
					<div class="content-item">
						<p><%=getLocaleString("_L_0077")%></p>
					</div>

					<div class="content-item">
						<h3 class="tabletitle"><%=getLocaleString("_L_0078")%></h3>
						<ul class="disc">
							<li><%=getLocaleString("_L_0079")%></li>
							<li><%=getLocaleString("_L_0080")%></li>
							<li><%=getLocaleString("_L_0081")%></li>
						</ul>	
					</div>
					
					<div class="content-item">
						<h3 class="tabletitle"><%=getLocaleString("_L_0082")%></h3>
						<ul class="disc">
							<li><%=getLocaleString("_L_0083")%></li>
							<li><%=getLocaleString("_L_0084")%></li>
							<li><%=getLocaleString("_L_0085")%></li>
							<li><%=getLocaleString("_L_0086")%></li>
						</ul>	
					</div>

					<div class="content-item">
						<h3 class="tabletitle"><%=getLocaleString("_L_0087")%></h3>
						<ul class="disc">
							<li><%=getLocaleString("_L_0088")%></li>
							<li><%=getLocaleString("_L_0089")%></li>
							<li><%=getLocaleString("_L_0090")%></li>
						</ul>	
					</div>
					<div class="content-item alCenter"> <!-- 버튼 상장 지원 신청 -->
						<a href="https://docs.google.com/forms/d/e/1FAIpQLSc1Uq0F2gSC9XRVXvuce5pgJZuvEKbzx7FmhnuS3iP7VcS5Og/viewform" class="btn btn-infor"><%=getLocaleString("_L_0091")%></a>
						<p class="red"><%=getLocaleString("_L_0092")%></p>
					</div>
					<!-- //인증레벨 별 원화 출금한도 : End -->

				</li>
				<!--상장안내 끝-->

		
				<!--보안인증 가이드 시작  : 비밀번호변경, OTP인증변경, 접속이력보기  > 마이페이지이동 언어팩 미완료    -->
				<li id="vtab5" class="secuinfo" style="display:none;">
					<h1 class="subtitle">
						<%=getLocaleString("_L_0006")%>
					</h1>
														<!-- 안전한 로그인 비밀번호 생성 : Start -->
					<div class="content-item">
						<h2 class="tabletitle"><%=getLocaleString("_L_0036")%></h2>
						<ul class="disc">
							<li><%=getLocaleString("_L_0005T")%></li>
							<li><%=getLocaleString("_L_0005U")%></li>
							<li><%=getLocaleString("_L_0005V")%></li>
							<li><%=getLocaleString("_L_0005W")%></li>
							<li><%=getLocaleString("_L_0005X")%></li>
						</ul>
					</div>
					<!-- //안전한 로그인 비밀번호 생성 : End -->

					<!-- 로그인 비밀번호 주기적 변경 : Start -->
					<div class="content-item">
						<h2 class="tabletitle"><%=getLocaleString("_L_0037")%></h2>
						<ul class="disc">
							<li><%=getLocaleString("_L_PW01")%></li>
							<li><%=getLocaleString("_L_PW02")%></li>
						</ul>

						<!-- 비밀번호 변경하기 버튼 : Start -->
						<a href="/site/info?tab=0">
							<button class="big"><%=getLocaleString("_L_0038")%></button>
						</a>
						<!-- //비밀번호 변경하기 버튼 : End -->
					</div>
					<!-- //로그인 비밀번호 주기적 변경 : End -->
							
					<!-- 브라우저의 비밀번호 자동저장기능 사용하지 않기 : Start -->
					<div class="content-item">
						<h2 class="tabletitle"><%=getLocaleString("_L_0039")%></h2>
						<ul class="disc">
							<li><%=getLocaleString("_L_BR01")%></li>
							<li><%=getLocaleString("_L_BR02")%></li>
										<li><%=getLocaleString("_L_BR03")%><br>
											
										</li>
										<li class="mgt20"><%=getLocaleString("_L_BR04")%>
										<p style="margin-left: 20px">
											<%=getLocaleString("_L_BR05")%>
										</p>
										<p style="margin-left: 20px">
											<%=getLocaleString("_L_BR06")%>
										</p>
									</li>
						</ul>

						
					</div>
					<!-- 브라우저의 비밀번호 자동저장기능 사용하지 않기 : End -->
							
							<!-- 붙여넣기 코인 출금주소 확인하기: Start -->
					<div class="content-item">
						<h2 class="tabletitle"><%=getLocaleString("_L_0040")%></h2>
						<ul class="disc">
							<li><%=getLocaleString("_L_CO01")%></li>
							<li><%=getLocaleString("_L_CO02")%> </li>
							<li><%=getLocaleString("_L_CO03")%></li>
						</ul>
									
					</div>
					<!-- //붙여넣기 코인 출금주소 확인하기 : End -->
								

					<!-- OTP 인증을 통한 로그인 보안 강화 : Start -->
					<div class="content-item">
						<h2 class="tabletitle"><%=getLocaleString("_L_0041")%></h2>
						<ul class="disc">
							<li><%=getLocaleString("_L_OP01")%> </li>
							<li><%=getLocaleString("_L_OP02")%> </li>
						</ul>
									
						<!-- OTP인증설정 버튼 : Start -->
						<a href="/site/info?tab=1">
							<button class="big"><%=getLocaleString("_L_0042")%></button>
						</a><!-- 비활성화 : btn-disabled 클래스 추가/삭제 -->
						<!-- //OTP인증설정 버튼 : End -->

					</div>
					<!-- //OTP 인증을 통한 로그인 보안 강화 : End -->

					<!-- 접속이력 확인 : Start -->
					<div class="content-item">
						<h2 class="tabletitle"><%=getLocaleString("_L_0043")%></h2>
						<ul class="disc">
							<li><%=getLocaleString("_L_CN01")%></li>
							<li><%=getLocaleString("_L_CN02")%></li>
						</ul>

						<!-- 접속이력 보기 버튼 : Start -->
						<a href="/site/info?tab=2">
							<button class="big"><%=getLocaleString("_L_0044")%></button>
						</a>
						
						<!-- //접속이력 보기 버튼 : End -->
					</div>
					<!-- //접속이력 확인 : End -->
				</li>
				<!--보안가이드 끝-->

				<!--자주하는 질문 시작-->
				<li id="vtab6" class="qna" style="display:none;">
					<h1 class="subtitle"><%=getLocaleString("_L_0007")%></h1>
					<div class="content-item">
						<p><%=getLocaleString("_L_JJ01")%></p> 
						<div id="tab-group" class="tab-dark mgt20">
							<ul>
								<li><a href="#tabs-1"><%=getLocaleString("_L_JJ02")%></a></li>
								<li><a href="#tabs-2"><%=getLocaleString("_L_JJ03")%></a></li>
								<li><a href="#tabs-3"><%=getLocaleString("_L_JJ04")%></a></li>
								<li><a href="#tabs-4"><%=getLocaleString("_L_JJ05")%></a></li>
								<li><a href="#tabs-5"><%=getLocaleString("_L_JJ06")%></a></li>
								<li><a href="#tabs-6"><%=getLocaleString("_L_JJ07")%></a></li>
							</ul>
							
							<div id="tabs-1" class="mgt10">
								<div id="accordion1">
									<h3>
										<span class="faq-category"><%=getLocaleString("_L_JJ08")%></span>
										<span><%=getLocaleString("_L_JJ09")%></span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content"><%=getLocaleString("_L_JJ10")%></span>
									</div>
									<h3>
										<span class="faq-category"><%=getLocaleString("_L_JJ08")%></span>
										<span><%=getLocaleString("_L_JJ11")%></span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content"><%=getLocaleString("_L_JJ12")%> <br/><%=getLocaleString("_L_JJ13")%> <br/><%=getLocaleString("_L_JJ14")%> <br/><%=getLocaleString("_L_JJ15")%>
											</span>
									</div>
									
									
									<h3>
										<span class="faq-category"><%=getLocaleString("_L_JJ08")%></span>
										<span><%=getLocaleString("_L_JJ16")%></span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content"><%=getLocaleString("_L_JJ17")%></span>
									</div>
									<h3>
										<span class="faq-category"><%=getLocaleString("_L_JJ08")%></span>
										<span><%=getLocaleString("_L_JJ18")%></span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content"><%=getLocaleString("_L_JJ19")%></span>
									</div>
									<h3>
										<span class="faq-category"><%=getLocaleString("_L_JJ08")%></span>
										<span><%=getLocaleString("_L_JJ20")%></span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content"><%=getLocaleString("_L_JJ21")%></span>
									</div>
									<h3>
										<span class="faq-category"><%=getLocaleString("_L_JJ08")%></span>
										<span><%=getLocaleString("_L_JJ22")%></span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content"><%=getLocaleString("_L_JJ23")%> <br/><%=getLocaleString("_L_JJ24")%> <br/><%=getLocaleString("_L_JJ25")%></span>
									</div>
									<!-- <h3>
										<span class="faq-category">가입 및 개인정보 변경</span>
										<span>출금 은행계좌 변경을 하고싶어요</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">은행계좌 변경은 고객센터 - 1:1 문의 화면에서 문의구분을 은행계좌등록으로 선택하시고 요청해 주시면 계좌를 초기화 해 드립니다. 초기화 후 직접 재 등록 하시면 됩니다.</span>
									</div>-->
									<h3> 
										<span class="faq-category"><%=getLocaleString("_L_JJ08")%></span>
										<span><%=getLocaleString("_L_JJ26")%></span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content"><%=getLocaleString("_L_JJ27")%></span>
									</div>
									<h3>
										<span class="faq-category"><%=getLocaleString("_L_JJ08")%></span>
										<span><%=getLocaleString("_L_JJ28")%></span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content"><%=getLocaleString("_L_JJ29")%></span>
									</div>
									<h3>
										<span class="faq-category"><%=getLocaleString("_L_JJ08")%></span>
										<span><%=getLocaleString("_L_JJ30")%></span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content"><%=getLocaleString("_L_JJ31")%></span>
									</div>
									<h3>
										<span class="faq-category"><%=getLocaleString("_L_JJ08")%></span>
										<span><%=getLocaleString("_L_JJ32")%></span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content"><%=getLocaleString("_L_JJ33")%></span>
									</div>
									<!-- <h3>
										<span class="faq-category">가입 및 개인정보 변경</span>
										<span>전화번호를 변경하고 싶습니다. 어떻게 해야 하나요?</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">웹사이트 마이페이지 - 휴대폰 번호변경 화면에서 변경 가능 합니다</span>
									</div>-->
									
									<!-- 원화 입금/출금 -->
									<h3>
										<span class="faq-category"><%=getLocaleString("_L_JJ34")%></span>
										<span><%=getLocaleString("_L_JJ35")%></span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content"><%=getLocaleString("_L_JJ36")%> <br/><strong><%=getLocaleString("_L_JJ37")%></strong><%=getLocaleString("_L_JJ38")%><br/><%=getLocaleString("_L_JJ39")%><br/><%=getLocaleString("_L_JJ40")%><br/><strong><%=getLocaleString("_L_JJ41")%> </strong><%=getLocaleString("_L_JJ42")%> <br/><span class="red"><%=getLocaleString("_L_JJ43")%> </span></span>
									</div>
									<h3>
										<span class="faq-category"><%=getLocaleString("_L_JJ34")%></span>
										<span><%=getLocaleString("_L_JJ44")%></span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content"><%=getLocaleString("_L_JJ45")%><br/><%=getLocaleString("_L_JJ46")%><br/><%=getLocaleString("_L_JJ47")%>
											</span>
									</div>
									
									<h3>
										<span class="faq-category"><%=getLocaleString("_L_JJ34")%></span>
										<span><%=getLocaleString("_L_JJ52")%></span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content"><%=getLocaleString("_L_JJ53")%><br/><%=getLocaleString("_L_JJ54")%><br/><%=getLocaleString("_L_JJ55")%><br/><%=getLocaleString("_L_JJ56")%></span>
									</div>
									
									<h3>
										<span class="faq-category"><%=getLocaleString("_L_JJ34")%></span>
										<span><%=getLocaleString("_L_JJ57")%></span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content"><%=getLocaleString("_L_JJ58")%></span>
									</div>

									
									<h3>
										<span class="faq-category"><%=getLocaleString("_L_JJ34")%></span>
										<span><%=getLocaleString("_L_JJ59")%></span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content"><%=getLocaleString("_L_JJ60")%></span>
									</div>
						
									<h3>
										<span class="faq-category"><%=getLocaleString("_L_JJ34")%></span>
										<span><%=getLocaleString("_L_JJ61")%> </span>
									</h3>
									
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content"><%=getLocaleString("_L_JJ62")%> <br/><%=getLocaleString("_L_JJ63")%> </span>
									</div>
									
									<h3>
										<span class="faq-category"><%=getLocaleString("_L_JJ34")%></span>
										<span><%=getLocaleString("_L_JJ64")%> </span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content"><%=getLocaleString("_L_JJ65")%> </span>
									</div>
									

									
									<h3>
										<span class="faq-category"><%=getLocaleString("_L_JJ34")%></span>
										<span><%=getLocaleString("_L_JJ66")%> </span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content"><%=getLocaleString("_L_JJ67")%> <br/><%=getLocaleString("_L_JJ68")%></span>
									</div>

									<h3>
										<span class="faq-category"><%=getLocaleString("_L_JJ34")%></span>
										<span><%=getLocaleString("_L_JJ69")%></span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content"><%=getLocaleString("_L_JJ70")%><br/><%=getLocaleString("_L_JJ71")%></span>
									</div>

									<h3>
										<span class="faq-category"><%=getLocaleString("_L_JJ34")%></span>
										<span><%=getLocaleString("_L_JJ72")%></span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content"><%=getLocaleString("_L_JJ73")%></span>
									</div>

									<h3>
										<span class="faq-category"><%=getLocaleString("_L_JJ34")%></span>
										<span><%=getLocaleString("_L_JJ74")%></span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content"><%=getLocaleString("_L_JJ75")%></span>
									</div>

									<h3>
										<span class="faq-category"><%=getLocaleString("_L_JJ34")%></span>
										<span><%=getLocaleString("_L_JJ76")%></span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content"><%=getLocaleString("_L_JJ77")%><br/><%=getLocaleString("_L_JJ78")%><br/><%=getLocaleString("_L_JJ79")%></span>
									</div>

									<h3>
										<span class="faq-category"><%=getLocaleString("_L_JJ34")%></span>
										<span><%=getLocaleString("_L_JJ80")%></span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content"><%=getLocaleString("_L_JJ81")%> <br/><%=getLocaleString("_L_JJ82")%> <br/><%=getLocaleString("_L_JJ83")%> </span>
									</div>
									
									<h3>
										<span class="faq-category"><%=getLocaleString("_L_JJ34")%></span>
										<span><%=getLocaleString("_L_JJ84")%> </span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content"><%=getLocaleString("_L_JJ85")%> </span>
									</div>
									<!-- 암호화폐 시작 작업중 -->
									<h3> 
										<span class="faq-category"><%=getLocaleString("_L_CR01")%> </span>
										<span><%=getLocaleString("_L_CR02")%></span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content"><%=getLocaleString("_L_CR03")%><br/><%=getLocaleString("_L_CR04")%></span>
									</div>

									<h3>
										<span class="faq-category"><%=getLocaleString("_L_CR01")%></span>
										<span><%=getLocaleString("_L_CR05")%></span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content"><%=getLocaleString("_L_CR06")%></span>
									</div>

									<h3>
										<span class="faq-category"><%=getLocaleString("_L_CR01")%></span>
										<span><%=getLocaleString("_L_CR07")%></span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content"><%=getLocaleString("_L_CR08")%></span>
									</div>

									<h3>
										<span class="faq-category"><%=getLocaleString("_L_CR01")%></span>
										<span><%=getLocaleString("_L_CR09")%></span>
									</h3>
									
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content"><%=getLocaleString("_L_CR10")%></span>
									</div>

									<h3>
										<span class="faq-category"><%=getLocaleString("_L_CR01")%></span>
										<span><%=getLocaleString("_L_CR01")%></span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content"><%=getLocaleString("_L_CR12")%>  </span>
									</div>

									<h3>
										<span class="faq-category"><%=getLocaleString("_L_CR01")%></span>
										<span><%=getLocaleString("_L_CR13")%></span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">
											<%=getLocaleString("_L_CR14")%>
											<br><br><%=getLocaleString("_L_CR15")%>
											<br><%=getLocaleString("_L_CR16")%>
											<br><%=getLocaleString("_L_CR17")%>
											<br><%=getLocaleString("_L_CR18")%>
											<br><br><%=getLocaleString("_L_CR19")%>
											<br><%=getLocaleString("_L_CR20")%>
											
											<br>
											<img src="../img/yaho/img_proof_info1.jpg" alt="img">
											<img src="../img/yaho/img_proof_info2.jpg" alt="img">
										</span>
									</div>


									<h3>
										<span class="faq-category"><%=getLocaleString("_L_CR01")%></span>
										<span><%=getLocaleString("_L_CR21")%> </span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content"><%=getLocaleString("_L_CR22")%>
										<br/><%=getLocaleString("_L_CR23")%></span>
									</div>
									
									<h3>
										<span class="faq-category"><%=getLocaleString("_L_CR01")%></span>
										<span><%=getLocaleString("_L_CR24")%></span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content"><%=getLocaleString("_L_CR25")%></span>
									</div>
																
									<h3>
										<span class="faq-category"><%=getLocaleString("_L_CR01")%></span>
										<span><%=getLocaleString("_L_CR26")%></span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content"><%=getLocaleString("_L_CR27")%></span>
									</div>
									
									<h3>
										<span class="faq-category"><%=getLocaleString("_L_CR01")%></span>
										<span><%=getLocaleString("_L_CR28")%></span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content"><%=getLocaleString("_L_CR29")%></span>
									</div>
									
									<h3>
										<span class="faq-category"><%=getLocaleString("_L_CR01")%></span>
										<span> <%=getLocaleString("_L_CR30")%> </span>
									</h3>
									
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">
											<%=getLocaleString("_L_CR31")%>
										</span>
									</div>
									
									<h3>
										<span class="faq-category"><%=getLocaleString("_L_CR01")%></span>
										<span><%=getLocaleString("_L_CR32")%></span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content"> <%=getLocaleString("_L_CR33")%> </span>
									</div>
									
									<h3>
										<span class="faq-category"><%=getLocaleString("_L_CR01")%></span>
										<span> <%=getLocaleString("_L_CR34")%> </span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content"> <%=getLocaleString("_L_CR35")%> </span>
									</div>

									<h3>
										<span class="faq-category"><%=getLocaleString("_L_CR01")%></span>
										<span> <%=getLocaleString("_L_CR36")%>  </span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">
											<br><%=getLocaleString("_L_CR37")%> <br/><%=getLocaleString("_L_CR38")%> 
										</span>
									</div>
									<h3>
										<span class="faq-category"><%=getLocaleString("_L_CR01")%> </span>
										<span> <%=getLocaleString("_L_CR39")%> </span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content"><%=getLocaleString("_L_CR40")%></span>
									</div>
									<!-- 기타시작 -->
									<h3>
										<span class="faq-category"><%=getLocaleString("_L_ET01")%></span>
										<span><%=getLocaleString("_L_ET02")%> </span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">
											<%=getLocaleString("_L_ET03")%>
											<br/><%=getLocaleString("_L_ET04")%>
											<br/><%=getLocaleString("_L_ET05")%> 
										</span>
									</div>
									<h3>
										<span class="faq-category"><%=getLocaleString("_L_ET01")%></span>
										<span> <%=getLocaleString("_L_ET06")%>  </span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content"> <%=getLocaleString("_L_ET07")%> </span>
									</div>
									
										<h3>
										<span class="faq-category"><%=getLocaleString("_L_ET01")%></span>
										<span> <%=getLocaleString("_L_ET08")%> </span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content"> <%=getLocaleString("_L_ET09")%> </span>
									</div>
										<h3>
										<span class="faq-category"><%=getLocaleString("_L_ET01")%></span>
										<span> <%=getLocaleString("_L_ET10")%> </span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content"><%=getLocaleString("_L_ET11")%> </span>
									</div>
								</div>
							</div>      

	
							<!-- //전체 탭 컨텐츠 : End -->
							<!-- 가입 및 탭 컨텐츠 : Start -->
							<div id="tabs-2" class="mgt10"> <!-- 탭 : 가입 및 개인정보 변경 -->
								<div id="accordion2">
									<h3>
										<span class="faq-category"> <%=getLocaleString("_L_JJ03")%> </span>
										<span> <%=getLocaleString("_L_JJ09")%> </span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content" >  <%=getLocaleString("_L_JJ10")%> </span>
									</div>
									
									<h3>
										<span class="faq-category"> <%=getLocaleString("_L_JJ03")%> </span>
										<span>  <%=getLocaleString("_L_JJ11")%> </span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">
										 <%=getLocaleString("_L_JJ12")%> <br/> <%=getLocaleString("_L_JJ13")%>  <%=getLocaleString("_L_JJ14")%><br/>  <%=getLocaleString("_L_JJ15")%> </span>
									</div>
									
									<h3>
										<span class="faq-category"> <%=getLocaleString("_L_JJ03")%> </span>
										<span>  <%=getLocaleString("_L_JJ16")%> </span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">  <%=getLocaleString("_L_JJ17")%>  </span>
									</div>
									<h3>
										<span class="faq-category"> <%=getLocaleString("_L_JJ03")%> </span>
										<span>  <%=getLocaleString("_L_JJ18")%>  </span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">  <%=getLocaleString("_L_JJ19")%> </span>
									</div>
									<h3>
										<span class="faq-category"> <%=getLocaleString("_L_JJ03")%> </span>
										<span>  <%=getLocaleString("_L_JJ20")%> </span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">
											 <%=getLocaleString("_L_JJ21")%>
										</span>
									</div>
									<h3>
										<span class="faq-category"> <%=getLocaleString("_L_JJ03")%> </span>
										<span>  <%=getLocaleString("_L_JJ22")%> </span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content"> 
											<%=getLocaleString("_L_JJ23")%><br/>
											<%=getLocaleString("_L_JJ24")%><br/>
										  <%=getLocaleString("_L_JJ25")%> 
									 </span>
									</div>
									
									<h3>
										<span class="faq-category"> <%=getLocaleString("_L_JJ03")%> </span>
										<span>  <%=getLocaleString("_L_JJ22")%> </span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content"> <%=getLocaleString("_L_JJ23")%><br/> <%=getLocaleString("_L_JJ24")%> <%=getLocaleString("_L_JJ25")%><br/></span>
									</div>

										<h3>
										<span class="faq-category"> <%=getLocaleString("_L_JJ03")%> </span>
										<span>  <%=getLocaleString("_L_JJ26")%> </span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">  <%=getLocaleString("_L_JJ27")%> </span>
									</div>

									<h3>
										<span class="faq-category"> <%=getLocaleString("_L_JJ03")%>  </span>
										<span>  <%=getLocaleString("_L_JJ28")%> </span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">  <%=getLocaleString("_L_JJ29")%> </span>
									</div>
									<h3>
										<span class="faq-category"> <%=getLocaleString("_L_JJ03")%> </span>
										<span>  <%=getLocaleString("_L_JJ30")%> </span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">  <%=getLocaleString("_L_JJ31")%> </span>
									</div>
									<h3>
										<span class="faq-category"> <%=getLocaleString("_L_JJ03")%> </span>
										<span>  <%=getLocaleString("_L_JJ32")%> </span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">  <%=getLocaleString("_L_JJ33")%> </span>
									</div>
									<!-- <h3>
										<span class="faq-category">가입 및 개인정보 변경</span>
										<span>전화번호를 변경하고 싶습니다. 어떻게 해야 하나요?</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">웹사이트 마이페이지 - 휴대폰 번호변경 화면에서 변경 가능 합니다</span>
									</div>-->
								</div>
							</div>
								

							<!-- //가입 및  탭 컨텐츠 : End -->
							<!-- 개인정보 변경 탭 컨텐츠 : Start -->
							<div id="tabs-3" class="mgt10"> <!-- 텝 : 원화 입금/출금 -->
                                <div id="accordion3">
                                    <h3>
                                        <span class="faq-category"> <%=getLocaleString("_L_JJ34")%> </span>
                                        <span><%=getLocaleString("_L_JJ35")%> </span>
                                    </h3>
                                    <div>
                                        <span class="answer-mark">A</span>
                                        <span class="answer-content">
																					<%=getLocaleString("_L_JJ36")%> <br/><strong><%=getLocaleString("_L_JJ37")%> </strong><%=getLocaleString("_L_JJ38")%>
																				<br/><%=getLocaleString("_L_JJ39")%>
																				<br/><%=getLocaleString("_L_JJ40")%>
																				<br/><strong><%=getLocaleString("_L_JJ41")%> </strong><%=getLocaleString("_L_JJ42")%>
																				<br/><span class="red"><%=getLocaleString("_L_JJ43")%></span></span>
                                    </div>
                                    <h3>
                                        <span class="faq-category"> <%=getLocaleString("_L_JJ34")%></span>
                                        <span> <%=getLocaleString("_L_JJ44")%> </span>
                                    </h3>
                                    <div>
                                        <span class="answer-mark">A</span>
                                        <span class="answer-content"><%=getLocaleString("_L_JJ45")%><%=getLocaleString("_L_JJ46")%><%=getLocaleString("_L_JJ47")%></span>
                                    </div>
                                    
                                    <h3>
                                        <span class="faq-category"> <%=getLocaleString("_L_JJ34")%></span>
                                        <span><%=getLocaleString("_L_JJ48")%> </span>
                                    </h3>
                                    <div>
                                        <span class="answer-mark">A</span>
                                        <span class="answer-content"><%=getLocaleString("_L_JJ49")%><br/><%=getLocaleString("_L_JJ50")%><br/><%=getLocaleString("_L_JJ51")%></span>
                                    </div>
                                    
                                    <h3>
                                        <span class="faq-category"> <%=getLocaleString("_L_JJ34")%></span>
                                        <span><%=getLocaleString("_L_JJ57")%> </span>
                                    </h3>
                                    <div>
                                        <span class="answer-mark">A</span>
                                        <span class="answer-content">
																					<%=getLocaleString("_L_JJ58")%>
																				</span>
                                    </div>
    
                                    
                                    <h3>
                                        <span class="faq-category"> <%=getLocaleString("_L_JJ34")%></span>
                                        <span><%=getLocaleString("_L_JJ59")%> </span>
                                    </h3>
                                    <div>
                                        <span class="answer-mark">A</span>
                                        <span class="answer-content"> <%=getLocaleString("_L_JJ60")%>> </span>
                                    </div>
                                    
    
                                    
                                    <h3>
                                        <span class="faq-category"> <%=getLocaleString("_L_JJ34")%></span>
                                        <span> <%=getLocaleString("_L_JJ61")%> </span>
                                    </h3>
                                    <div>
                                        <span class="answer-mark">A</span>
                                        <span class="answer-content"> <%=getLocaleString("_L_JJ62")%> <br/> <%=getLocaleString("_L_JJ63")%> </span>
                                    </div>
                                    
                                    <h3>
                                        <span class="faq-category"> <%=getLocaleString("_L_JJ34")%></span>
                                        <span> <%=getLocaleString("_L_JJ64")%> </span>
                                    </h3>
                                    <div>
                                        <span class="answer-mark">A</span>
                                        <span class="answer-content"> <%=getLocaleString("_L_JJ65")%></span>
                                    </div>
                                    
    
                                    
                                    <h3>
                                        <span class="faq-category"> <%=getLocaleString("_L_JJ34")%></span>
                                        <span> <%=getLocaleString("_L_JJ66")%> </span>
                                    </h3>
                                    <div>
                                        <span class="answer-mark">A</span>
                                        <span class="answer-content"> <%=getLocaleString("_L_JJ67")%> <br/> <%=getLocaleString("_L_JJ68")%> </span>
                                    </div>
                                    
                                    
                                    <h3>
                                        <span class="faq-category"> <%=getLocaleString("_L_JJ34")%></span>
                                        <span> <%=getLocaleString("_L_JJ72")%> </span>
                                    </h3>
                                    <div>
                                        <span class="answer-mark">A</span>
                                        <span class="answer-content"> <%=getLocaleString("_L_JJ73")%> </span>
                                    </div>

                                    <h3>
                                        <span class="faq-category"> <%=getLocaleString("_L_JJ34")%></span>
                                        <span> <%=getLocaleString("_L_JJ73A")%> </span>
                                    </h3>
                                    <div>
                                        <span class="answer-mark">A</span>
                                        <span class="answer-content"> <%=getLocaleString("_L_JJ70")%> <br/> <%=getLocaleString("_L_JJ71")%></span>
                                    </div>
                                    
                                    <h3>
                                        <span class="faq-category"> <%=getLocaleString("_L_JJ34")%></span>
                                        <span> <%=getLocaleString("_L_JJ74")%> </span>
                                    </h3>
                                    <div>
                                        <span class="answer-mark">A</span>
                                        <span class="answer-content"> <%=getLocaleString("_L_JJ75")%> </span>
                                    </div>
                                    
                                    <h3>
                                        <span class="faq-category"> <%=getLocaleString("_L_JJ34")%></span>
                                        <span> <%=getLocaleString("_L_JJ76")%> </span>
                                    </h3>
                                    <div>
                                        <span class="answer-mark">A</span>
                                        <span class="answer-content"><%=getLocaleString("_L_JJ77")%><%=getLocaleString("_L_JJ78")%>><br/><%=getLocaleString("_L_JJ79")%> </span>
                                    </div>

                                    <h3>
                                        <span class="faq-category"> <%=getLocaleString("_L_JJ34")%></span>
                                        <span> <%=getLocaleString("_L_JJ80")%> </span>
                                    </h3>
                                    <div>
                                        <span class="answer-mark">A</span>
                                        <span class="answer-content"><%=getLocaleString("_L_JJ81")%><br/> <%=getLocaleString("_L_JJ82")%> <br/><%=getLocaleString("_L_JJ83")%></span>
                                    </div>

                                    <h3>
                                        <span class="faq-category"> <%=getLocaleString("_L_JJ34")%></span>
                                        <span> <%=getLocaleString("_L_JJ84")%> </span>
                                    </h3>
                                    <div>
                                        <span class="answer-mark">A</span>
                                        <span class="answer-content"> <%=getLocaleString("_L_JJ85")%> </span>
                                    </div>
                                </div>
                            </div>

							<!-- //원화 입금/출금 탭 컨텐츠 : End -->
							<!-- 코인 입금/출금 탭 컨텐츠 : Start -->
							<div id="tabs-4" class="mgt10"> <!-- 탭 : 암호화폐 입금/출금 : 완료 -->
								<div id="accordion4">
									
									<h3>
										<span class="faq-category"> <%=getLocaleString("_L_CR01")%> </span> <!-- 암호화폐 입금/출금 :  -->
										<span> <%=getLocaleString("_L_CR02")%> </span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content"><%=getLocaleString("_L_CR03")%> <%=getLocaleString("_L_CR04")%></span>
									</div>
									<h3>
										<span class="faq-category"> <%=getLocaleString("_L_CR01")%> </span>
										<span> <%=getLocaleString("_L_CR05")%> </span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content"> <%=getLocaleString("_L_CR06")%> </span>
									</div>
									<h3>
										<span class="faq-category"> <%=getLocaleString("_L_CR01")%>  </span>
										<span><%=getLocaleString("_L_CR06A")%> </span> <!-- 출금제한 규정 추가  -->
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content"> <%=getLocaleString("_L_CR06B")%> </span>
									</div>
									<h3>
										<span class="faq-category"> <%=getLocaleString("_L_CR01")%> </span>
										<span> <%=getLocaleString("_L_CR07")%> </span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content"> <%=getLocaleString("_L_CR08")%> </span>
									</div>
									
										<h3>
										<span class="faq-category"> <%=getLocaleString("_L_CR01")%> </span>
										<span>  <%=getLocaleString("_L_CR09")%> </span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content"><%=getLocaleString("_L_CR10")%> </span>
									</div>
									
										<h3>
										<span class="faq-category"> <%=getLocaleString("_L_CR01")%> </span>
										<span> <%=getLocaleString("_L_CR11")%>  </span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content"> <%=getLocaleString("_L_CR12")%>  </span>
									</div>
									<h3>
										<span class="faq-category"> <%=getLocaleString("_L_CR01")%> </span>
										<span><%=getLocaleString("_L_CR13")%> </span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">
											<%=getLocaleString("_L_CR14")%>
											<br><br>
											<%=getLocaleString("_L_CR15")%>
											<br>
											<%=getLocaleString("_L_CR16")%>
											<br>
											<%=getLocaleString("_L_CR17")%>
											<br>
											<%=getLocaleString("_L_CR18")%>
											<br>
											<%=getLocaleString("_L_CR19")%>
											<br>
											<%=getLocaleString("_L_CR20")%>
											<br>
													<%=getLocaleString("_L_CR20A")%>
											<br>
										</span>
									</div>
									
										<h3>
										<span class="faq-category"> <%=getLocaleString("_L_CR01")%> </span>
										<span> <%=getLocaleString("_L_CR21")%></span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">
															<%=getLocaleString("_L_CR22")%>
												<br/>	<%=getLocaleString("_L_CR23")%>
										</span>
									</div>
									
										<h3>
										<span class="faq-category"> <%=getLocaleString("_L_CR01")%> </span>
										<span> <%=getLocaleString("_L_CR24")%> </span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">		<%=getLocaleString("_L_CR25")%> </span>
									</div> 
									
										<h3>
										<span class="faq-category"> <%=getLocaleString("_L_CR01")%> </span>
										<span> <%=getLocaleString("_L_CR26")%> </span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">		<%=getLocaleString("_L_CR27")%></span>
									</div>
									
										<h3>
										<span class="faq-category"> <%=getLocaleString("_L_CR01")%> </span>
										<span>		<%=getLocaleString("_L_CR28")%> </span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">		<%=getLocaleString("_L_CR29")%> </span>
									</div>
									
										<h3>
										<span class="faq-category"> <%=getLocaleString("_L_CR01")%> </span>
										<span>		<%=getLocaleString("_L_CR30")%> </span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">		<%=getLocaleString("_L_CR31")%> </span>
									</div>
								</div>
							</div>


							<!-- //코인 입금/출금 탭 컨텐츠 : End -->
							<!-- 거래,주문 탭 컨텐츠 : Start -->
							<div id="tabs-5" class="mgt10"> <!-- 탭 : 입출금내역 완료 -->
								<div id="accordion5" >
									
									<h3>
										<span class="faq-category"><%=getLocaleString("_L_CR32A")%> </span> <!-- 거래,주문 -->
										<span><%=getLocaleString("_L_CR32")%></span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content"><%=getLocaleString("_L_CR33")%></span>
									</div>
									<!-- <h3>
										<span class="faq-category">거래,주문</span>
										<span>정정주문이 뭔가요?</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">모바일 앱에서만 가능한 정정주문은 기존 매수 또는 매도 주문 내역 중 주문수량 또는 주문가격을 변경하여 재 주문 하는 경우를 말하며 기존 주문내역을 취소하고 새로운 주문을 신규로 내는 번거로움을 없애고자 제공하는 주문 기능입니다.</span>
									</div>-->
									
									<h3>
										<span class="faq-category"> <%=getLocaleString("_L_CR32A")%> </span>
										<span> <%=getLocaleString("_L_CR34")%> </span> 
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content"> <%=getLocaleString("_L_CR35")%> </span>
									</div>
									
									<h3>
										<span class="faq-category"><%=getLocaleString("_L_CR32A")%> </span>
										<span> <%=getLocaleString("_L_CR36")%></span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content"><%=getLocaleString("_L_CR37")%>, <%=getLocaleString("_L_CR38")%> </span>
									</div>
									
									<h3>
										<span class="faq-category"> <%=getLocaleString("_L_CR32A")%> </span>
										<span> <%=getLocaleString("_L_CR39")%> </span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content"> <%=getLocaleString("_L_CR40")%> </span>
									</div>
								</div>
							</div>
							

							<!-- //거래주문 탭 컨텐츠 : End -->
							<!-- 기타 탭 컨텐츠 시작   -->
							<div id="tabs-6" class="mgt10">
								<div id="accordion6">
									<h3>
										<span class="faq-category"><%=getLocaleString("_L_ET01")%></span>
										<span><%=getLocaleString("_L_ET02")%></span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">
													<%=getLocaleString("_L_ET03")%>
											<br/><%=getLocaleString("_L_ET04")%>
											<br/><%=getLocaleString("_L_ET05")%> 
										</span>
									</div>
									<h3>
										<span class="faq-category"><%=getLocaleString("_L_ET01")%></span>
										<span> <%=getLocaleString("_L_ET06")%>  </span>
									</h3>
										<div>
										<span class="answer-mark">A</span>
										<span class="answer-content"> <%=getLocaleString("_L_ET07")%> </span>
									</div>
									
										<h3>
										<span class="faq-category"><%=getLocaleString("_L_ET01")%></span>
										<span> <%=getLocaleString("_L_ET08")%> </span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content"> <%=getLocaleString("_L_ET09")%> </span>
									</div>
										<h3>
										<span class="faq-category"><%=getLocaleString("_L_ET01")%></span>
										<span> <%=getLocaleString("_L_ET10")%> </span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content"><%=getLocaleString("_L_ET11")%> </span>
									</div>
									<!--<h3>
										<span class="faq-category">기타</span>
										<span>모바일 앱에서는 로그인이 되는데 PC 웹에서는 비밀번호 오류라고 하면서 로그인이 안됩니다.</span>
									</h3>
									<div>
										<span class="answer-mark">A</span>
										<span class="answer-content">모바일 앱 로그인시 입력한 로그인ID와 동일하게 웹에서도 ID 입력하셨는지 확인 하십시오. 동일하게 입력해도 안 된다면 비밀번호에 특수문자 &#60; 또는 &#40; 또는 &#39; 세 가지 문자 중 하나가 포함되어 있는지 확인하십시오. 포함되어 있다면 해당 특수문자가 포함되지 않은 비밀번호로 모바일 앱에서 변경한 후 웹로그인 해보시길 바랍니다.</span>
									</div>-->
								</div>
							</div>

						</div>
					
						<p class="mgt20"><%=getLocaleString("_L_JJMSG")%></p>
					</div>
                            
				</li>
                        <!--자주하는 질문 끝-->
				<c:if test="${sessionScope.userInfo.loginYn == 1}">
				
				<!-- ---------------------------1:1 문의 시작 : 미완료  작업진행중 -------------------------------------- -->
				<li id="vtab7" style="display:none;">
					<h1 class="subtitle">
						<%=getLocaleString("_L_IQ01")%>
					</h1>

					<p id="inquriy-main-category-title" class="bolder"> <%=getLocaleString("_L_IQ02")%> </p>

					<!--대분류 시작-->
					<div id="inquriy-main-category" class="category step1" style="display: block;">
						<div class="cate-tit">
							<%=getLocaleString("_L_IQ03")%>
						</div>

						<!--메인카테고리 시작-->
						<ul class="contact main-category">
							<li val="accnt"> <%=getLocaleString("_L_IQ04")%> </li>
							<!-- <li val="alimtok">알림톡</li> -->
							<!-- <li val="cacaopay">카카오페이</li> -->
							<li val="depwitd"> <%=getLocaleString("_L_IQ05")%> </li>
							<!-- <li val="sbprob">매매 장애</li> -->
							<li val="request"> <%=getLocaleString("_L_IQ06")%> </li>
							<!-- <li val="general">일반</li> -->
							<li val="bankaccd"> <%=getLocaleString("_L_IQ07")%> </li>
							<!-- <li val="misdeposit">오입금</li> -->
							<li val="events"> <%=getLocaleString("_L_IQ08")%> </li>
							<li val="withdrawrequest"> <%=getLocaleString("_L_IQ09")%> </li>
							<li val="withdrawrightnow"> <%=getLocaleString("_L_IQ10")%> </li>
						</ul>
						<!--메인카테고리 끝-->
					</div>
					<!--대분류 끝-->

					<!-- 소분류 코인외 문의 시작 : 대분류를 선택하면 나타납니다.-->
					<div id="inquriy-sub-category" class="category step2" style="display: none;">
						<div class="cate-tit">
							<%=getLocaleString("_L_IQ11")%>
						</div>

						<!--서브카테고리 시작-->
						<ul class="contact sub-category accnt">
							<!--계정 리스트 시작-->
							<li val="sub-accnt-01"> <%=getLocaleString("_L_IQ12")%> </li>
							<!-- <li val="sub-accnt-02">계정복구</li> -->
							<li val="sub-accnt-03"> <%=getLocaleString("_L_IQ13")%></li>
							<li val="sub-accnt-04"> <%=getLocaleString("_L_IQ14")%> </li>
							<li val="sub-accnt-05"> <%=getLocaleString("_L_IQ15")%> </li>
							<!--계정 리스트 끝-->
						</ul>
						<ul class="contact sub-category alimtok">
							<!--알림톡 시작-->
							<li val="sub-alimtok-01"> <%=getLocaleString("_L_IQ16")%> </li> 
							<!--알림톡 끝-->
						</ul>
						<ul class="contact sub-category cacaopay">
							<!--카카오페이 시작-->
							<li val="sub-cacaopay-01">  <%=getLocaleString("_L_IQ17")%> </li>
							<!--카카오페이 끝-->
						</ul>
						<ul class="contact sub-category request">
							<!--제안 시작-->
							<li val="sub-request-01">  <%=getLocaleString("_L_IQ18")%> </li>
							<li val="sub-request-02">  <%=getLocaleString("_L_IQ19")%> </li>
							<li val="sub-request-03">  <%=getLocaleString("_L_IQ20")%> </li>
							<!--제안 끝-->
						</ul>
						<ul class="contact sub-category general">
							<!--일반 시작-->
							<li val="sub-general-01"> <%=getLocaleString("_L_IQ21")%> </li>
							<li val="sub-general-02"><%=getLocaleString("_L_IQ22")%> </li>
							<li val="sub-general-03"> <%=getLocaleString("_L_IQ23")%> </li>
							<li val="sub-general-04"> <%=getLocaleString("_L_IQ24")%> </li>
							<!--일반 끝-->
						</ul>
						<ul class="contact sub-category bankaccd">
							<!--금융사고 시작-->
							<!-- <li val="sub-bankaccd-01">수사/금융/공공기관</li> -->
							<li val="sub-bankaccd-02"> <%=getLocaleString("_L_IQ25")%> </li>
							<!-- <li val="sub-bankaccd-03">다단계 신고/불량사용자 신고</li> -->
							<li val="sub-bankaccd-04"> <%=getLocaleString("_L_IQ26")%> </li>
							<!--금융사고 끝-->
						</ul>
						<!-- <ul class="contact sub-category misdeposit">
							오입금 시작
							<li val="sub-misdeposit-01">BTC계열 오입금</li>
							<li val="sub-misdeposit-02">ERC20(상장)을 ETH지갑주소로 오입금</li>
							<li val="sub-misdeposit-03">ERC20(상장)을 ETC지갑주소로 오입금</li>
							<li val="sub-misdeposit-04">ERC20(상장)을 ERC20지갑주소로 오입금</li>
							<li val="sub-misdeposit-05">ERC20(비상장)을 ETH지갑주소로 오입금</li>
							<li val="sub-misdeposit-06">ERC20(비상장)을 ETC지갑주소로 오입금</li>
							<li val="sub-misdeposit-07">ERC20(비상장)을 ERC20지갑주소로 오입금</li>
							<li val="sub-misdeposit-08">ETC를 ETH지갑주소로 오입금</li>
							<li val="sub-misdeposit-09">ETH를 ETC지갑주소로 오입금</li>
							<li val="sub-misdeposit-10">ICO코인을 ETH지갑주소로 오입금</li>
							<li val="sub-misdeposit-11">수수료납부</li>
							오입금 끝
						</ul> -->
						<ul class="contact sub-category events">
							<!--이벤트 시작-->
							<li val="sub-event-01"> <%=getLocaleString("_L_IQ27")%> </li>
							<!-- <li val="sub-event-02">●●● 이벤트</li> -->
							<!--이벤트 끝-->
						</ul>
						<ul class="contact sub-category withdrawrequest">
							<!--출금신청 시작-->
							<li val="sub-withdrawrequest-01"> <%=getLocaleString("_L_IQ28")%> </li>
							<!--출금신청 끝-->
						</ul>
						<ul class="contact sub-category withdrawrightnow">
							<!--바로출금 시작-->
							<li val="sub-withdrawrightnow-01"> <%=getLocaleString("_L_IQ29")%></li>
							<!--바로출금 끝-->
						</ul>
						<!--서브카테고리  끝-->
					</div>
					<!--소분류 코인외 문의 끝-->

					<!--소분류 코인 문의 시작 : 대분류에서 코인건 문의일경우 나타납니다.-->
					<div id="inquriy-coin-category" class="category step2_coin"  style="display: none;">
						<div class="cate-tit">
							<p>
								<input id="inquriy-coin-category-search" type="text" placeholder="<%=getLocaleString("_L_IQ31A")%>">
								<button class="btn-cate">  <%=getLocaleString("_L_IQ30")%> </button>
							</p>
						</div>

						<!--코인리스트 시작-->
						<ul class="contact coin-category">
							<li val="KRW" data-cho="ㅇㅎㄱㅈㄷㄹㅂㄱ"> <%=getLocaleString("_L_IQ31")%> </li>
							<li val="KRW" data-cho="ㅇㅎ"> <%=getLocaleString("_L_IQ32")%>  </li>
							<li val="BTC" data-cho="ㅂㅌㅋㅇ"> <%=getLocaleString("_L_IQ33")%>  </li>
							<li val="ETH" data-cho="ㅇㄷㄹㅇ"> <%=getLocaleString("_L_IQ34")%>  </li>
							<li val="BCH" data-cho="ㅂㅌㅋㅇㅋㅅ"> <%=getLocaleString("_L_IQ35")%>  </li>
							<li val="XRP" data-cho="ㄹㅍ"> <%=getLocaleString("_L_IQ36")%> </li>
							<!-- <li val="YEP" data-cho="ㅇㅌㅋ">옙 (YEP)</li> -->
							<!-- <li val="RVN" data-cho="ㄹㅇㅂㅋㅇ">레이븐코인 (RVN)</li> -->
							<!-- <li val=""    data-cho="ㅁㅁㅈㅇㅁㅇ">매매장애 문의</li> -->
						</ul>
						<!--코인리스트  끝-->
					</div>
					<!--소분류 코인 문의 끝-->

					<!--문의하기 공통 입력 테이블 시작 : 소분류 모두 선택후 나타납니다.-->
					<div id="inquiry-input-div" class="inquiry"  style="display: none;">

						<p class="bolder" style="clear: both"> <%=getLocaleString("_L_IQ37")%> 문의 내용을 입력해 주세요</p>
						<table class="type01">
							<colgroup>
								<col style="width: 130px">
								<col>
							</colgroup>
							<tbody>
								<tr>
									<th> <%=getLocaleString("_L_IQ38")%> </th>
									<td>
										<span id="inquiry-input-main-cate" > <%=getLocaleString("_L_IQ39")%></span>
										<span> > </span>
										<span id="inquiry-input-sub-cate"> <%=getLocaleString("_L_IQ40")%> </span>
									</td>
								</tr>
								<tr id="inquiry-input-attach-file">
									<th> <%=getLocaleString("_L_IQ41")%> </th>
									<td>
										<div class="filebox">
											<!-- 첨부된 파일 리스트 : Start -->
											<div class="filebox-inner mCustomScrollbar">
												<div class="filebox-item" data-index="0">
												</div>
											</div>
											<!-- //첨부된 파일 리스트 : End -->
											<!-- 파일첨부 버튼 : Start -->
											<label for="ex_filename" id="attachFile"> <%=getLocaleString("_L_IQ41")%> </label>
											<input type="file" id="ex_filename" class="upload-hidden">
											<!-- //파일첨부 버튼 : End -->
										</div>
										<div class="tip-txt">
											<%=getLocaleString("_L_IQ42")%>
											<span class="sub-accnt-03" style="display:none;">
											<%=getLocaleString("_L_IQ43")%>
											</span>
										</div>
									</td>
								</tr>

								<tr>
									<th><%=getLocaleString("_L_IQ44")%></th>
									<td><input id="inquiry-input-title" type="text" placeholder="제목을 입력해 주세요" style="max-width: 100%"></td>
								</tr>
								<tr>
									<th> <%=getLocaleString("_L_IQ45")%> </th>
									<td>
										<textarea id="inquiry-input-content" placeholder="내용을 입력해 주세요"></textarea>
									</td>
								</tr>
								<tr class="sub-accnt-03" style="display:none;">
									<th> <%=getLocaleString("_L_IQ46")%> </th>
									<td>
											 <%=getLocaleString("_L_IQ47")%>
											 <%=getLocaleString("_L_IQ48")%>
										<span class="checks-item" style="height:0;border:none;">
												<input type="checkbox" id="agree">
												<label for="agree"> <%=getLocaleString("_L_IQ49")%></label>
										</span>
									</td>
								</tr>
							</tbody>
						</table>
						<div class="btn-wrap">
							<button onClick="requestInquiry()" class="big blue"> <%=getLocaleString("_L_IQ50")%>  </button>
							<button onClick="cancelInquiry()" class="big"> <%=getLocaleString("_L_IQ51")%> </button>
						</div>
					</div>
					<!--문의하기 공통 입력 테이블 끝-->
				</li>
				<!--1:1 문의 끝-->

				<!--문의내역 시작-->
				<li id="vtab8" style="display:none;">
					<h1 class="subtitle">
						<%=getLocaleString("_L_IQ52")%>
					</h1>

					<!-- 문의내역 : Start -->
					<div class="inq-list" id="inquiry-list">
						<h2 class="tabletitle"> <%=getLocaleString("_L_IQ52")%> </h2>
						<div class="type01">
							<table>
								<colgroup>
									<col style="width:60px;">
									<col>
									<col style="width:155px;">
									<col style="width:155px;">
									<col style="width:80px;">
								</colgroup>
								<thead>
									<tr>
										<th>NO</th>
										<th><%=getLocaleString("_L_IQ54")%> </th>
										<th> <%=getLocaleString("_L_IQ55")%>  </th>
										<th> <%=getLocaleString("_L_IQ56")%> </th>
										<th> <%=getLocaleString("_L_IQ57")%>  </th>
									</tr>
								</thead>
								<tbody id="inquiry_list_table_tbody">
									<!-- 문의내역 없을 때 : Start -->
									<tr>
										<td colspan="5" class="no-data alCenter">
											<!-- no-data 클래스 -->
											 <%=getLocaleString("_L_IQ58")%>
										</td>
									</tr>
									<!-- //문의내역 없을 때 : End -->
								</tbody>
							</table>

							<!-- 페이징 + 1:1 문의하기 버튼 : Start -->
							<div>
								<!-- 페이징. 문의내역이 없을 때는 페이징 없음 : Start -->
								<ul class="pagination" id="inquiry-pagination">
									<li><a class="btn-page first"></a></li>
									<li><a class="btn-page prev"></a></li>
									<li><a href="#" class="active">1</a></li><!-- 선택된 번호의 a 태그에 acitve 클래스 추가 -->
									<li><a class="btn-page next"></a></li>
									<li><a class="btn-page last"></a></li>
								</ul>
								<!-- //페이징. 문의내역이 없을 때는 페이징 없음 : End -->

							</div>
							<!-- //페이징 + 1:1 문의하기 버튼 : End -->
						</div>
					</div>
					<!-- //문의내역 : End -->

					<!-- 문의내용 및 답변 : Start -->
					<div class="inq-view" id="inquiry-conts-div">
						<h2 class="tabletitle">  <%=getLocaleString("_L_IQ59")%> </h2>
						<div>
							<table class="type02">
								<colgroup>
									<col style="width:150px;">
									<col>
								</colgroup>
								<thead>
									<tr>
										<th>
											<p id="inquire-category" class="text-ellips">
											</p>
										</th>
										<th id="inquiry-conts-title" class="text-ellips">
										</th>
									</tr>
								</thead>
								<tbody>
									<!-- 선택한 내역 없을 때 : Start -->
									<tr id="inquire-reply-dont-select">
										<td colspan="2" class="no-data alCenter"><%=getLocaleString("_L_IQ60")%> </td>
									</tr>
									<!-- //선택한 내역 없을 때 : End -->
									<!-- 문의내용 : Start -->
									<tr>
										<th><%=getLocaleString("_L_IQ61")%> </th>
										<td id="inquiry-conts">
										</td>
									</tr>
									<!-- //문의내용 : End -->
									<!-- 답변내용 : Start -->
									<tr>
										<th> <%=getLocaleString("_L_IQ62")%> </th>
										<td id="reply-conts">
										</td>
									</tr>
									<!-- //답변내용 : End -->
								</tbody>
							</table>
						</div>
					</div>
					<!-- //문의내용 및 답변 : End -->

				</li>
				<!--문의내역 끝-->
			
				</c:if>
			</ul>
		</div>

	</article>

</main>

<div id="like_support" cont="<%=request.getParameter("cont")%>"></div>

<!--컨텐츠 내용 영역 끝-->

<script type="text/javascript">
	var noticeSeq;
	var eventSeq;

	//세로탭메뉴
	$(function() {
		$("ul.vpanel li:not(" + $("ul.vtab li a.vselected").attr("href") + ")").hide()
		$("ul.vtab li a").click(function() {
			$("ul.vtab li a").removeClass("vselected");
			$(this).addClass("vselected");
			$("ul.vpanel li").hide();
			$($(this).attr("href")).show();
			return false;
		});
	});
</script>

<script src="/resources/js/holdport/support/holdport.support.js?v=<spring:message code="holdport.support.js.version"/>"></script>

<script>
//마켓별 전체보기
$(document).ready(function() {
	if('${tab}' != '') {
		$('.vtab').find('a:eq(' + (parseInt('${tab}') - 1) + ')').trigger('click');
		if('${tab}' == '1') noticeSeq = '${no}';
		else if('${tab}' == '2') eventSeq = '${no}';
	}
	
	$('.btn-folding').click(function() {
		$('.folding').toggleClass('more');
	});
	$('.btn-folding1').click(function() {
		$('.folding1').toggleClass('more');
	});
	$('.btn-folding2').click(function() {
		$('.folding2').toggleClass('more');
	});

	// 공지사항 리스트 가져오기
	getNoticeList();
	// 이벤트 리스트 가져오기
	getEventList();
	// 이용안내 > 최소입금금액 리스트 가져오기
	getMinOrderAmtList();
	// 수수료안내 > 거래수수료, 입출금 수수료 리스트 가져오기
	getFeeList();
	
	// 1:1 문의내역 리스트 가져옴
	getInquiryList();
	
	$('.filebox-item').on('click', '.file-btn-del', function() {
		let $this = $(this);
		$('#' + $this.data('file')).remove();
		$this.closest('p').remove();
	});
	
	$('.filebox').on('change', 'input', function() {
		let $this = $(this)
			, fileName = $this[0].files[0].name
			, size = $this[0].files[0].size
			, index = fileName.lastIndexOf('.')
			, exp = fileName.substr(index+ 1).toLocaleLowerCase()
			, template = '';
		
		size = ((size / 1024) / 1024).toFixed(2);
		
		if(exp !== 'jpg' && exp !== 'png') {
			lrt.client('파일 확장자는 jpg, png 만 가능합니다.', '오류');
			return false;
		} else if(!isPossibleSize()) {
			lrt.client('총 용량은 50MB 미만이어야 합니다.', '오류');
			$this.val('');
			return false;
		} else {
			let $filebox = $('.filebox')
				, $fileboxItem = $('.filebox-item')
				, template = $('#template-inquiry-upload-file').html()
				, cnt = parseFloat($fileboxItem.data('index')) + 1;
			
			$fileboxItem.append(
					template.replace(/{{fileId}}/g, $this.attr('id'))
							.replace(/{{fileName}}/g, fileName)
							.replace(/{{size}}/g, size)).data('index', cnt);
			
			$filebox.append('<input type="file" id="ex_filename' + cnt + '" class="upload-hidden">');
			$('#attachFile').attr('for', 'ex_filename' + cnt);
		}
	});
	
	getOutLimit();
});

</script>
 <script src="/resources/js/common/tab.js"></script>

<Script id="template-list-empty" type="text/template">
	<tr onclick="event.cancelBubble = true;">
		<td colspan="{{ColSpan}}" class="alCenter">데이터가 없습니다</td>
	</tr>
</Script>

<script id="template-notice-list-table-tr" type="text/template">
	<tr>
		<td class="{{ClassNew}} {{ClassER}}">
			<span style="cursur:pointer;font-size:14px;" onClick="javascript:toggleNoticeList('{{NoticeSeq}}', false);" class="{{ClassTop}}">{{NoticeTitle}}</span>
			<input type="hidden" name="notice-seq" value="{{NoticeSeq}}">
		</td>
		<td>{{NoticeDate}}</td>
	</tr>
</script>

<script id="template-event-list-table-tr" type="text/template">
	<tr>
		<td>
				{{EventTitle}}
				<input type="hidden" name="event-seq" value="{{EventSeq}}">
		</td>
		<td>{{EventDate}}</td>
		<td class="{{ClassOnClose}}">{{OnCloseTxt}}</td>
	</tr>
</script>
	
<script id="template-min-order-amount-table-tr" type="text/template">
	<tr>
		<td>{{ItemEngName}}</td>
		<td>{{ItmeName}}</td>
		<td>
			<span>{{MinOrdAmt}}</span>
			<span>{{ItemBaseCoinName}}</span><!-- 단위 -->
		</td>
	</tr>
</script>
<script id="template-trading-fee-table-tr" type="text/template">
	<tr>
		<td>{{MarketName}}</td>
		<td>{{SellFee}}</td>
		<td>{{BuyFee}}</td>
	</tr>
</script>
<script id="template-deposit-withdraw-fee-table-tr" type="text/template">
	<tr>
		<td>{{CoinSymbolName}}</td>
		<td>{{MinDepositQty}}</td>
		<td>{{WtdrwFee}}</td>
		<td>{{MinWtdrwQty}}</td>
	</tr>
</script>
<!-- 1:1 문의내역 리스트 -->
<script id="template-inquiry-list-table-tr" type="text/template">
	<tr class="{{ClassActive}}" data-seq="{{InquirySeq}}">
		<td>{{RowNum}}</td>
		<td class="text-ellips">
			{{InquiryTitle}}
		</td>
		<td>{{RequestDT}}</td>
		<td>{{ReplyDT}}</td>
		<td>{{ReplyStatus}}</td>
	</tr>
</script>

<script id="template-inquiry-upload-file" type="text/template">
	<p>
		<span><a href="javascript:void(0);" class="file-btn-del" data-file="{{fileId}}"></a></span>
		<span class="file-name">{{fileName}}</span>
		<span class="file-volume">
			<span>{{size}}</span>
			<span>MB</span>
		</span>
	</p>
</script>



<script type="module" src="/resources/js/react/support_bundle.js"></script>
