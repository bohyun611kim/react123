import { useEffect, useRef, useState } from "react";
import { useRecoilValue } from "recoil";
import { BASE_PREFIX } from "../../../module/myconstants";
import request from "../../../module/services/request.service";
import { wallet_bank_info, wallet_coin_selected , wallet_coin_info} from "../../../module/stores/wallet_state";
import { toLocale, toNumber } from "../../../module/utils/calculate.util";
import { toFormData } from "../../../module/utils/convert.util";
import { getLocaleContent, getLocaleObject } from "../../../module/utils/language.util";

/*
function presetMoneyWithdraw(coinNo) {
    // 인증레벨 셋팅
    // Money symbol setting

    // 인증수단에 따른 인증방법 보이기 셋팅 (1: OTP, 2: SMS, 3: Email, 0: 사용안함)
    // 인증수단 미설정이면 최대, 출금신청, 취소 버튼 막기
    if(__G_User_Auth_Mean_Code == 1) {
        // $('#money-tab-2-auth-sms-tr').hide();
        // $('#money-tab-2-auth-otp-tr').show();
    } else if(__G_User_Auth_Mean_Code == 2) {
        // $('#money-tab-2-auth-sms-tr').show();
        // $('#money-tab-2-auth-otp-tr').hide();
    } else {
        // $('#money-tab-2-auth-sms-tr').hide();
        // $('#money-tab-2-auth-otp-tr').hide();
        // $('#money-tab-2-max-qty-btn').prop("disabled", true);
        // $('#money-tab-2-request-btn').prop("disabled", true);
        // $('#money-tab-2-cancel-btn').prop("disabled", true);
    }
    // 최소출금수량 셋팅
    var minWtdQty = __G_CoinMgtRef_Map.get(__G_Default_Coin_Type).MIN_WTDRW_QTY;
    $('#money-tab-2-min-withdraw-qty').text(nu.cm(minWtdQty));
    // 출금수수료 셋팅
    var wtdFee = __G_CoinMgtRef_Map.get(__G_Default_Coin_Type).WTDRW_FEE;
    $('#money-tab-2-withdraw-fee').text(nu.cm(wtdFee));
    // 출금가능수량 셋팅
    getMoneyWithDrawQty(__G_Default_Coin_Type);
}
*/



function requestMoneySmsAuthNumber() {
	var userPossessQty = 0, userDailyLeftQty = 0;
	try {
    	userPossessQty	= parseFloat($('#money-tab-2-user-poss-qty').text().replace(/,/g, ''));
    	userDailyLeftQty= parseFloat($('#money-tab-2-daily-limit-left-qty').text().replace(/,/g, ''));
	} catch(err) {};

    var reqQty          = $('#money-tab-2-withdraw-request-qty').val();
    var targetAddr      = $('#money-tab-2-sender-account-number').text();
    var targetAddrEtc1  = $('#money-tab-1-sender-bank-code').val();
    var targetAddrEtc2  = $('#money-tab-2-sender-account-holder-name').text();

    var paramJson = {
          coinNo            : __G_Default_Coin_Type
        , reqQty            : reqQty == '' ? '' : reqQty.replace(/,/gi, '')
        , targetAddr        : targetAddr.trim()
        , targetAddrEtc1    : targetAddrEtc1.trim()
        , targetAddrEtc2    : targetAddrEtc2.trim()
        , reqMemo           : ''
    }

    if(userPossessQty == 0 || userDailyLeftQty == 0) {
        lrt.client('출금가능 잔고가 부족합니다.', '잔고 부족', null, null);
        return false;
    }
    if(reqQty == '' || parseFloat(reqQty) == 0) {
        lrt.client('요청수량을 넣으세요.', '요청수량 오류', function() {
            $('#money-tab-2-withdraw-request-qty').focus();
        }, null);
        return false;
    }

    // SMS 인증 요청 프로세스 진행
    doMoneySmsAuthRequest(paramJson);
}

function OtpAuth({otp, updateState}) {
	return (
		<tr>
			<th>{getLocaleContent('word', 'otp_auth')}</th>
			<td> <input type="text" onChange={e=>updateState(e.target.value)} value={otp} defaultValue="" placeholder={getLocaleContent('word', 'otp_place')} /> </td> 
        	{/* <p class="info-text">OTP앱 실행 후 표시 된 인증번호 6자리를 입력하세요</p> */}
		</tr>
	);
}

function SmsAuth({updateState}) {

	const handleClick = e => {
		e.preventDefault();
		requestMoneySmsAuthNumber();
	}
	return (
		<tr>
			<th>{getLocaleContent('word', 'sms_auth')}</th>
			<td> <input type="text" onChange={e=>updateState(e.target.value)} defaultValue="" placeholder={getLocaleContent('word', 'sms_place')} /> </td> 
        	{/* <p class="info-text">OTP앱 실행 후 표시 된 인증번호 6자리를 입력하세요</p> */}
        	<button id="money-tab-2-request-sms-auth-number-btn" onClick={handleClick}>{getLocaleContent('word', 'sms_send')}</button>
		</tr>

	);
}

export default function WithdrawCurrency({bank}) {

	const myref = useRef({});

	const [qty, setQty] = useState(0);
	const [otp, setOtp] = useState("");
	const [sms, setSms] = useState("");
	const [withInfo, setWithInfo] = useState({});

	const coinInfo = useRecoilValue(wallet_coin_info);
	const selectedCoin = useRecoilValue(wallet_coin_selected);
	const amountRef = useRef(null)


	const krwinfo = coinInfo.filter(it => it.COIN_NO == 10 )[0];
	// console.log("krwinfo >>> ", krwinfo);

	var minWtdQty1 = __G_CoinMgtRef_Map.get(__G_Default_Coin_Type).MIN_WTDRW_QTY;
	var wtdFee = __G_CoinMgtRef_Map.get(__G_Default_Coin_Type).WTDRW_FEE;
	var minWtdQty = nu.cm(minWtdQty1);

	useEffect(()=>{
		myref.current.clearValues = clearValues;
		myref.current.updateInfo = _with_info;
		window.withdrawCurrency = myref.current;
	}, []);

	useEffect(()=>{
        // presetMoneyWithdraw(selectedCoin.no);
		clearValues()
		_with_info();
	}, [selectedCoin])

	const handleSubmit = e=>{
		e.preventDefault();

		if (__G_User_Auth_Mean_Code == 0) {
			lrt.client( getLocaleContent('word', 'with_no_auth'), getLocaleContent('word', 'withdraw') + getLocaleContent('word', 'error'), function() {
				amountRef.current.focus()
            }, null);
			return;
		}

		if (qty < toNumber(minWtdQty)) {
			lrt.client( getLocaleContent('word', 'with_not_enough_amount'), getLocaleContent('word', 'withdraw') + getLocaleContent('word', 'error'), function() {
				amountRef.current.focus()
            }, null);
			return;
		}

		if ((qty - toNumber(wtdFee)) <= 0) {
			lrt.client(getLocaleContent('word', 'with_not_enough_fee'), getLocaleContent('word', 'withdraw') + getLocaleContent('word', 'error'),function() {
				amountRef.current.focus()
            }, null);
			return; 
		}


		requestMoneyWithDraw({reqQty:String(qty), authOtpCode:otp, authSmsCode:sms, 
			posses:"", dailleft:"", targetAddr:bank.BANK_ACCNT_NO, targetAddrEtc1:bank.BANK_CD, targetAddrEtc2:bank.ACCNT_HOLDER_NM
		});

	}

	const clearValues = () => {
		setQty(0);
		setOtp('');
		setSms('');
	}

	const _with_info = async () => {
		var param = {coinNo: selectedCoin.no, reqQty: 0}
		const rsp = await request.post(BASE_PREFIX+'/wallet/wlt001/checkWithdrawQty', toFormData(param)).then(response => response.data)
		console.log("_krw_info-:", rsp);
		setWithInfo(rsp);
	}

	const handleMaxWithdrawQty = e=> {
		e.preventDefault();
		let amount = withInfo.V_DAILIY_LIMIT_LEFT_QTY > withInfo.V_WTDRW_POSS_QTY ? withInfo.V_WTDRW_POSS_QTY : withInfo.V_DAILIY_LIMIT_LEFT_QTY
		console.log("handleMaxWithdrawQty:amount:", amount, "- left", withInfo.V_DAILIY_LIMIT_LEFT_QTY, ", pos:",withInfo.V_WTDRW_POSS_QTY );
		setQty(Math.floor(amount));
	}
	const handleReqAmount = e=> {
		e.preventDefault();
		let amount = toNumber(e.target.value);

		if (amount > withInfo.V_MONTHLY_LIMIT_LEFT_QTY) {
			setQty(toNumber(withInfo.V_MONTHLY_LIMIT_LEFT_QTY));
			return; 
		}

		if (amount > withInfo.V_DAILIY_LIMIT_LEFT_QTY) {
			setQty(toNumber(withInfo.V_DAILIY_LIMIT_LEFT_QTY));
			return; 
		}

		if (amount > withInfo.V_WTDRW_POSS_QTY) {
			setQty(toNumber(withInfo.V_WTDRW_POSS_QTY));
			return; 
		}

		setQty(toNumber(e.target.value));
	}

	const handleAuth = (e, path)=>{
		e.preventDefault();
		location.href = path;
	}

  return (
    <div id="tabs-2" aria-labelledby="ui-id-2" role="tabpanel" className="ui-tabs-panel ui-corner-bottom ui-widget-content" aria-hidden="true" style={{}} >
      {/*출금 입력영역 시작*/}
      <div className="withdrawal">
		<div className="important" id="withdraw">
			<p>Important</p>
			<ul id="other">
			{ getLocaleObject("wallet.important").map((item, index) => { 
				let sentence = item.replace('withdraw_min_qty', toLocale(krwinfo.MIN_WTDRW_QTY)).replace('withdraw_max_qty', toLocale(withInfo.V_DAILIY_LIMIT_MAX_QTY))
				return <li key={index}> {sentence} </li>} ) 
			}
			</ul>
		</div>
        <table className="type01">
          <colgroup>
            <col style={{ width: "180px" }} />
            <col style={{ width: "410px" }} />
            <col />
          </colgroup>
          <tbody>
            <tr>
              <th>{getLocaleContent('word', 'withdrawbank')}</th>
              <td> <span class="data_wdr">{_G_Bank_Code_Map.get(bank.BANK_CD)}</span> </td>
            </tr>
            <tr>
              <th>{getLocaleContent('word', 'accountnumber')}</th>
              <td> <span class="data_wdr">{bank.BANK_ACCNT_NO}</span> </td>
            </tr>
            <tr>
              <th>{getLocaleContent('word', 'accountholder')}</th>
              <td> <span class="data_wdr">{bank.ACCNT_HOLDER_NM}</span> </td>
            </tr>
            <tr>
              <th>{getLocaleContent('word', 'balance')}</th>
              <td> <span class="data_wdr">{withInfo.V_WTDRW_POSS_QTY}</span> </td>
            </tr>
            {/* <tr>
              <th>1회 출금 한도 </th>
              <td> <span class="data_wdr" id="money-tab-2-once-wthrw-qty"></span> </td>
              <td> <button> <a href="#" onClick={e=>handleAuth(e, "/site/info?tab=1")}>인증레벨설정</a> </button> </td>
            </tr> */}
            <tr>
              <th>{getLocaleContent('word', 'with_day_remain')}</th>
              <td> <span class="data_wdr">{toLocale(qty)}</span>  </td> <td> <button onClick={handleMaxWithdrawQty}>Max</button> </td>
            </tr>
            <tr>
              <th>{getLocaleContent('word', 'with_amount')}</th>
              <td> <input type="text" name="coin" ref={amountRef} value={toLocale(qty)} onChange={handleReqAmount}/> </td> 
			  <td> <span>{getLocaleContent('word', 'min_') + getLocaleContent('word', 'withdrawprice')}{nu.cm(minWtdQty)} {getLocaleContent('word', 'base_unit')}</span> </td>
            </tr>
            <tr>
              <th>{getLocaleContent('word', 'fee')}</th>
              <td> <span class="data_wdr">{wtdFee}</span> </td>
            </tr>
			{__G_User_Auth_Mean_Code == 2 ? <SmsAuth updateState={setSms}/> : <OtpAuth otp={otp} updateState={setOtp}/> }
          </tbody>
        </table>
        {/* 출금 요청, 취소 버튼 : Start */}
        <div className="btn-group">
          <ul>
            <li>
              <button className="red" onClick={handleSubmit}>{getLocaleContent("word","btn_withdrawal")}</button>
            </li>
          </ul>
        </div>
        {/* //출금 요청, 취소 버튼 : End */}
      </div>
      {/*출금 입력영역 끝*/}
	  <WithdrawWarning/>
    </div>
  );
}


function WithdrawWarning() {
	return (
		<>
		 <h2 className="tabletitle red">{getLocaleContent('word', "with_warning_title")}</h2>
		<ul className="dot_list">
			{getLocaleContent('object', 'wallet.withdraw.warning1').map((item, index) => {
				if (index == 0 || index == 1) 
					return <li key={index} className="red">{item}</li>
				else 
					return <li key={index}>{item}</li>
			})}
		</ul>
		<div className="alert_wrap">
		  <h2 className="tabletitle red">{getLocaleContent('word', "with_warning_title1")}</h2>
		  <h2 className="tabletitle red">{getLocaleContent('word', "with_warning_title2")}<br /> </h2>
		  <h2 className="tabletitle red">{getLocaleContent('word', "with_warning_title3")}</h2>
		  <ul>
			{getLocaleContent('object', 'wallet.withdraw.warning2').map((item, index) => {
				return <li key={index}>{item}</li>
			})}
		  </ul>
		</div>
		</>
	);
}
