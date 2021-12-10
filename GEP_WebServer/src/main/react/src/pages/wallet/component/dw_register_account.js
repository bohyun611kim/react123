import { useRef, useState } from "react";
import { useRecoilValue } from "recoil";
import { wallet_tab_selected, wallet_user_info } from "../../../module/stores/wallet_state";
import { toFormData } from "../../../module/utils/convert.util";
import { BASE_PREFIX } from "../../../module/myconstants";
import request from "../../../module/services/request.service";
import { getLocaleContent, getLocaleObject } from "../../../module/utils/language.util";


var _bankHolderNameRef = null;
var _bankBirthRef = null;
var _bankBankListRef = null;
var _bankAccountRef = null;

function insertUserBankAccntInfo(doProcessInsertUserBankAccntInfo) {

	var bankAccntHolderName = _bankHolderNameRef.current.value;
    var bankAccntHolderBirthDay = _bankBirthRef.current.value;
    var bankCode = _bankBankListRef.current.value;
	var bankAccountNumber =_bankAccountRef.current.value;

    if(bankAccntHolderName == '') {
        lrt.client( getLocaleContent('word', 'curr_request_holder'), getLocaleContent('word', 'error'), function() {
            _bankHolderNameRef.current.focus();
        }, null);
        return false;
	}
    if(bankAccntHolderBirthDay == '' || bankAccntHolderBirthDay.length != 6) {
        lrt.client( getLocaleContent('word', 'curr_request_birth'), getLocaleContent('word', 'error'), function() {
			_bankBirthRef.current.focus();
        }, null);
        return false;
    }
    if(bankCode == 9999) {
        lrt.client(getLocaleContent('word', 'curr_request_bank'), getLocaleContent('word', 'error'), function() {
			_bankBankListRef.current.focus();
        }, null);
        return false;
    }
    if(bankAccountNumber == '') {
        lrt.client(getLocaleContent('word', 'curr_request_account'), getLocaleContent('word', 'error'), function() {
			_bankAccountRef.current.focus();
        }, null);
        return false;
	}
    var paramMap = {
        bankCd : bankCode
      , bankAccntNo : bankAccountNumber.replace(/-/gi, '').replace(/\./gi, '')
      , bankAccntHldrNm : bankAccntHolderName
      , bankAccntHldrBthDay : bankAccntHolderBirthDay
	};
	lrt.confirm( getLocaleContent('word', 'curr_account_submit'), getLocaleContent('word', 'curr_account_auth'), doProcessInsertUserBankAccntInfo, paramMap);
}


export default function DWBankRegister() {

	_bankHolderNameRef = useRef(null)
	_bankBirthRef = useRef(null)
	_bankBankListRef = useRef(null)
	_bankAccountRef = useRef(null)

	const handleCheckInput = (e) => {
		//console.log("handleCheckInput-----------:", e)
		var tname = e.target.name;
		var tvalue = e.target.value;
	}

	const _submit = async (param) => {

		const rsp = await request.post(BASE_PREFIX+'/wallet/wlt001/insertMemberBankAccntInfo',  toFormData(param)).then(response => response.data)
		console.log("_submit insertMemberBankAccntInfo---:", rsp);
		if (rsp.rtnCd != 0) {
			lrt.client( getLocaleContent('word', 'curr_account_auth_fail'), getLocaleContent('word', 'error'), null, null);
		} else {
			lrt.client(getLocaleContent('word', 'curr_account_auth_ok'), getLocaleContent('word', 'curr_account_auth'), null, null);
			location.href = '/site/wallet';
		}
	}

	const handleSumit = (e) => {
		insertUserBankAccntInfo(_submit);
	}

	return (
		<div id="money-tab-1" className="ui-tabs-panel ui-corner-bottom ui-widget-content" style={{display:"block"}}>
			<div className="content-item" >
				<ul className="disc">
				{getLocaleObject("wallet.notices", "deposit", "account.register").map((item, index) => {
					return index == 0 ? <li>{item}</li> : <li className="red">{item}</li>
				})}
				</ul>
				{/* <!-- 계좌입력영역 : Start --> */}
				<div>
					<table className="type01">
						<colgroup>
							<col style={{width:"180px"}}/>
							<col/>
						</colgroup>
						<tbody>
							<tr>
								<th>{ getLocaleContent("word","accountholder") }</th>
								<td>
									<input type="text" id="money-tab-1-bank-account-holder-name" ref={_bankHolderNameRef} name="holder" defaultValue="" placeholder="" onChange={e=>{}} onBlur={handleCheckInput}/>
								</td>
							</tr>
							<tr>
								<th>{ getLocaleContent("word","accountHolderId") }</th>
								<td>
									<input type="text" id="money-tab-1-bank-account-holder-birthday" ref={_bankBirthRef} name="birth" defaultValue="" placeholder="예) 880808" onChange={e=>{}} onBlur={handleCheckInput} />
								</td>
							</tr>
							<tr>
								<th>{ getLocaleContent("word","bank") }</th>
								<td>
									<select className="select-box" id="money-tab-1-bank-list" ref={_bankBankListRef}   name="bankcode" onChange={e=>{}} onBlur={handleCheckInput} >
										<option value="9999">{ getLocaleContent("word","select") }</option><option value="039">경남은행</option><option value="095">경찰청</option><option value="221">골든브릿지투자증권</option><option value="034">광주은행</option><option value="364">광주카드</option><option value="433">교보라이프플래닛생명</option><option value="436">교보생명</option><option value="261">교보증권</option><option value="066">교통은행</option><option value="004">국민은행</option><option value="099">금융결제원</option><option value="077">기술보증기금</option><option value="003">기업은행</option><option value="440">농협생명</option><option value="011">농협은행</option><option value="031">대구은행</option><option value="102">대신저축은행</option><option value="267">대신증권</option><option value="065">대화은행</option><option value="055">도이치뱅크</option><option value="435">라이나생명</option><option value="445">롯데손해보험</option><option value="368">롯데카드</option><option value="223">리딩투자증권</option><option value="287">메리츠증권</option><option value="052">모건스탠리은행</option><option value="238">미래에셋대우</option><option value="431">미래에셋생명</option><option value="059">미쓰비시도쿄UFJ은행</option><option value="058">미즈호은행</option><option value="290">부국증권</option><option value="032">부산은행</option><option value="061">비엔피파리바</option><option value="064">산림조합</option><option value="002">산업은행</option><option value="452">삼성생명</option><option value="296">삼성선물</option><option value="240">삼성증권</option><option value="365">삼성카드</option><option value="441">삼성화재</option><option value="050">상호저축은행</option><option value="045">새마을금고</option><option value="094">서울보증보험</option><option value="008">수출입은행</option><option value="007">수협중앙회</option><option value="369">수협카드</option><option value="291">신영증권</option><option value="076">신용보증기금</option><option value="278">신한금융투자</option><option value="438">신한생명</option><option value="088">신한은행</option><option value="366">신한카드</option><option value="048">신협(신용협동조합)</option><option value="370">씨티카드</option><option value="446">아이엔지생명</option><option value="447">악사손해보험</option><option value="056">알비에스피엘씨은행</option><option value="103">에스비아이저축은행</option><option value="437">에이비엘생명</option><option value="104">에이치케이저축은행</option><option value="297">외환선물</option><option value="044">외환카드</option><option value="020">우리은행</option><option value="295">우리종합금융</option><option value="041">우리카드</option><option value="071">우체국</option><option value="105">웰컴저축은행</option><option value="209">유안타증권</option><option value="280">유진투자증권</option><option value="265">이베스트투자증권</option><option value="037">전북은행</option><option value="372">전북카드</option><option value="035">제주은행</option><option value="373">제주카드</option><option value="062">중국공상은행</option><option value="063">중국은행</option><option value="012">지역농,축협(단위농협)</option><option value="090">카카오뱅크</option><option value="089">케이뱅크</option><option value="292">케이프투자증권</option><option value="264">키움증권</option><option value="294">펀드온라인</option><option value="270">하나금융투자</option><option value="374">하나SK카드</option><option value="262">하이투자증권</option><option value="101">한국신용정보원</option><option value="027">한국씨티은행</option><option value="001">한국은행</option><option value="096">한국전자금융㈜</option><option value="093">한국주택금융공사</option><option value="293">한국증권금융</option><option value="243">한국투자증권</option><option value="222">한양증권</option><option value="432">한화생명</option><option value="269">한화투자증권</option><option value="434">현대라이프생명</option><option value="298">현대선물</option><option value="263">현대차투자증권</option><option value="367">현대카드</option><option value="442">현대해상</option><option value="453">흥국생명</option><option value="361">BC카드</option><option value="224">BNK투자증권</option><option value="060">BOA</option><option value="279">DB금융투자</option><option value="443">DB손해보험</option><option value="054">HSBC</option><option value="225">IBK투자증권</option><option value="057">JP모간</option><option value="381">KB국민카드</option><option value="439">KB생명보험</option><option value="444">KB손해보험</option><option value="218">KB증권</option><option value="226">KB투자증권</option><option value="081">KEB하나은행</option><option value="227">KTB투자증권</option><option value="371">NH카드</option><option value="247">NH투자증권</option><option value="023">SC은행</option><option value="266">SK증권</option></select>
								</td>
							</tr>
							<tr>
								<th>{ getLocaleContent("word","accountnumber") }</th>
								<td>
									<input type="text" id="money-tab-1-bank-account-number" ref={_bankAccountRef}  name="account" defaultValue="" placeholder={getLocaleContent('word', 'curr_account_place')} onChange={e=>{}} onBlur={handleCheckInput} />
								</td>
							</tr>
						</tbody>
					</table>
					{/* <!-- //입력영역 : End --> */}
					{/* <!-- 등록,취소 버튼 : Start --> */}
					<div className="btn-group">
						<ul>
							<li><button onClick={handleSumit} className="blue">{ getLocaleContent("word","accountAuth") }</button></li>
							<li><button>{ getLocaleContent("word","cancel") }</button></li>
						</ul>
					</div>
					{/* <!-- //등록,취소 버튼 : End --> */}
				</div>
				{/* <!-- 계좌입력영역 : end --> */}
			</div>
		</div>
);
}

