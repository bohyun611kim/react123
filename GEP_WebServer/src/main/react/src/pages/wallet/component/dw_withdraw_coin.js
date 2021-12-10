import { useEffect, useRef, useState } from "react";
import { useRecoilValue } from "recoil";
import { BASE_PREFIX } from "../../../module/myconstants";
import request from "../../../module/services/request.service";
import { wallet_coin_selected, wallet_user_info } from "../../../module/stores/wallet_state";
import { toFormData } from "../../../module/utils/convert.util";
import { number_format, toDecimalLimit, toLocale, toNumber } from "../../../module/utils/calculate.util";
import { getLocaleContent, getLocaleObject } from "../../../module/utils/language.util";
import { number } from "prop-types";


function getWithdrawApply(coinNo) {
    // 탭관련된 input 창 초기화 실행 (타이머 초기화포함)
    // cancelRequestWithdraw();

    // 추가주소 갯수에 따른 보여주기 셋팅
    var wtdAddrCnt = parseInt(__G_CoinMgtRef_Map.get(coinNo).COIN_WTDRW_ADDR_CNT);
    if(wtdAddrCnt == 1) {
        // $('#coin-tab-2-etc-address-1').hide();
        // $('#coin-tab-2-etc-address-2').hide();
    } else if(wtdAddrCnt == 2) {
        // $('#coin-tab-2-etc-address-1').show();
        // var etcAddrName1 = __G_CoinMgtRef_Map.get(coinNo).ADDR_ETC1_NM;
        // $('#coin-tab-2-etc-address-1-name').text(etcAddrName1);
        // $('#coin-tab-2-etc-address-2').hide();
    } else {
        // $('#coin-tab-2-etc-address-1').show();
        // $('#coin-tab-2-etc-address-2').show();
        // var etcAddrName1 = __G_CoinMgtRef_Map.get(coinNo).ADDR_ETC1_NM;
        // $('#coin-tab-2-etc-address-1-name').text(etcAddrName1);
        // var etcAddrName2 = __G_CoinMgtRef_Map.get(coinNo).ADDR_ETC2_NM;
        // $('#coin-tab-2-etc-address-1-name').text(etcAddrName2);
    }

    // 인증레벨 셋팅
    // 인증수단에 따른 인증방법 보이기 셋팅 (1: OTP, 2: SMS, 3: Email, 0: 사용안함)
    if(__G_User_Auth_Mean_Code == 1) {
        // $('#coin-tab-2-auth-sms-tr').hide();
        // $('#coin-tab-2-auth-otp-tr').show();
    } else if(__G_User_Auth_Mean_Code == 2) {
        // $('#coin-tab-2-auth-sms-tr').show();
        // $('#coin-tab-2-auth-otp-tr').hide();
    } else {
        // $('#coin-tab-2-auth-sms-tr').hide();
        // $('#coin-tab-2-auth-otp-tr').hide();
    }
    // 최소출금수량 셋팅
    var minWtdQty = __G_CoinMgtRef_Map.get(coinNo).MIN_WTDRW_QTY;
    // $('#coin-tab-2-min-withdraw-qty').text(minWtdQty);
    // 출금수수료 셋팅
    var wtdFee = __G_CoinMgtRef_Map.get(coinNo).WTDRW_FEE;
    // $('#coin-tab-2-withdraw-fee').text(wtdFee);
    // 출금가능수량 셋팅
    // getCoinWithDrawQty(coinNo);
}


function DestinationTag({handleChange, destTag}) {
	return (
		<tr>
			<th><span className="coin-name-domestic"></span> Destination Tag</th>
			<td>
				<input type="text" id="coin-tab-2-etc-address-1-addr" name="" value={destTag} onChange={handleChange} placeholder={getLocaleContent('word', 'option')}/>
				<p className="red bolder info-text">※ {getLocaleContent('word', 'with_warning1')} </p>
			</td>
		</tr>
	);
}


export default function WithdrawCoinTab({coin}) {

	// const myref = useRef({})
	const [amount, setAmount] = useState("");
	const [addr, setAddress] = useState('');
	const [memo, setMemo] = useState('');
	const [otp, setOtp] = useState('');
	const [withInfo, setWithInfo] = useState({});
	const [destTag, setDestTag] = useState('');

	const coinSelected = useRecoilValue(wallet_coin_selected);


	useEffect(()=>{
		// myref.current.clearValues = clearValues;
		// window.withdrawCoinTab = myref.current;
		// getWithdrawApply(coin.COIN_NO)
		clearValues();
		_with_info();
	}, [coin])

	useEffect(()=>{
		clearValues();
	}, [coinSelected])

	const handleAuth = (e, path)=>{
		e.preventDefault();
		location.href = path;
	}

	const handleRequestSMS = e=>{
		e.preventDefault();
		requestSmsAuthNumber();
	}
	const handleSubmit = e => {
		e.preventDefault();
		requestWithDraw();
	}

	const clearValues = ()=> {
		setAddress('')
		setAmount(0)
		setMemo('')
		setDestTag('')
		setOtp('')
	}

	const handleCancel = e => {
		e.preventDefault();
		clearValues();
	}

	const handleChangeTag = e => {
		setDestTag(e.target.value)
	}

	const _with_info = async () => {
		var param = {coinNo: coin.COIN_NO, reqQty: coin.MIN_WTDRW_QTY}
		const rsp = await request.post(BASE_PREFIX+'/wallet/wlt001/checkWithdrawQty', toFormData(param)).then(response => response.data)
		console.log("_coin_info-:", rsp);
		setWithInfo(rsp);
	}


	const handleMaxWithdrawQty = e=> {
		e.preventDefault();
		setAmount(withInfo.V_WTDRW_POSS_QTY-coin.WTDRW_FEE);
	}

	// console.log("calc amount+++", req, coin.WTDRW_FEE);
	// console.log("withcoin=>", coin);

	let intput_amount = toLocale(amount, coin.WTDRW_DECIMAL_PNT);
	let intput_total = toNumber(intput_amount) - coin.WTDRW_FEE;
	return (
		<div id="coin-tab-2" aria-labelledby="coin-tab-a-2" role="tabpanel" className="ui-tabs-panel ui-corner-bottom ui-widget-content" aria-hidden="false" style={{display:"block"}}>
			<div className="content-item">
				<ul className="disc">
					{ getLocaleObject("wallet.notices", "withdraw", "coin.desc").map((item, index) => <li key={index}>{item}</li>) }
				</ul>
				{/* <!-- 입력 영역 : Start --> */}
				<table className="type01">
					<colgroup>
						<col style={{width:"180px"}}/>
						<col/>
					</colgroup>
					<tbody>
						<tr>
							<th>{getLocaleContent('word', 'with_amount_max') }</th>
							<td>
								<span id="coin-tab-2-user-poss-qty">{withInfo.V_WTDRW_POSS_QTY}</span>
								<span className="coin-symbolic-name">{coin.COIN_SYMBOLIC_NM}</span>
							</td>
						</tr>
						<tr>
							<th>{getLocaleContent('word', 'with_amount_day') }</th>
							<td>
								<span id="coin-tab-2-daily-limit-qty">{withInfo.V_DAILIY_LIMIT_MAX_QTY}</span>
								<span className="coin-symbolic-name">{coin.COIN_SYMBOLIC_NM}</span>
							</td>
						</tr>
						<tr>
							<th>{getLocaleContent('word', 'with_day_remain') }</th>
							<td>
								<span id="coin-tab-2-daily-limit-left-qty">{withInfo.V_DAILIY_LIMIT_LEFT_QTY}</span>
								<span className="coin-symbolic-name">{coin.COIN_SYMBOLIC_NM}</span>
								<span className="red">{getLocaleContent('word', 'with_auth_level').replace("AUTH_LEVEL", __G_User_Auth_Level, "gi")}</span>
								<button>
									<a href="#" onClick={e=>handleAuth(e, "/site/info?tab=1")}>{getLocaleContent('word', 'set_member_level') }</a>
								</button>
							</td>
						</tr>
						<tr>
							<th><span className="coin-name-domestic">{coin.ITEM_KOR_NM}</span>{getLocaleContent('word', 'withdrawal') + getLocaleContent('word', 'address') } </th>
							<td>
								<input type="text" id="coin-tab-2-target-address" name="" value={addr} onChange={e=>setAddress(e.target.value)} placeholder="필수입력"/>
								<p className="red bolder info-text">※ {getLocaleContent('word', 'with_warning2') } </p>
							</td>
						</tr>

						{	coin.COIN_SYMBOLIC_NM == 'XRP' 
							|| coin.COIN_SYMBOLIC_NM == 'XRP' ?  <DestinationTag handleChange={handleChangeTag} destTag={destTag} /> : null
						}

						<tr>
							<th>{getLocaleContent('word', 'withdrawalTab') + getLocaleContent('word', 'amount')}</th>
							<td>
								<input type="text" id="coin-tab-2-withdraw-request-qty" name="" value={intput_amount} onChange={e=>setAmount(e.target.value)} />
								<button onClick={handleMaxWithdrawQty}>{getLocaleContent('word', 'max_')}</button>
								<span>({getLocaleContent('word', 'min_') + getLocaleContent('word', 'with_amount')} <span id="coin-tab-2-min-withdraw-qty">{coin.MIN_WTDRW_QTY}</span>)</span>
							</td>
						</tr>
						<tr>
							<th>{getLocaleContent('word', 'fee_withdawal')}</th>
							<td>
								<span id="coin-tab-2-withdraw-fee">{coin.WTDRW_FEE}</span>
								{/* <!-- 코인수량 --> */}
								<span className="coin-symbolic-name">{coin.COIN_SYMBOLIC_NM}</span>
								{/* <!-- 코인종류 --> */}
							</td>
						</tr>
						<tr id="coin-tab-2-auth-sms-tr" style={{display:"none"}}>
							<th>{getLocaleContent('word', 'sms_auth')}</th>
							<td>
								<button id="coin-tab-2-request-sms-auth-number-btn" onclick={handleRequestSMS}>{getLocaleContent('word', 'sms_send')} </button>
								<input type="text" id="coin-tab-2-sms-auth-number" name="" value="" placeholder={getLocaleContent('word', 'sms_place')}/>
								{/* <!-- <p className="info-text">인증번호 요청 후, 휴대폰으로 전송된 인증번호 6자리를 입력하세요</p> --> */}
								<p>{getLocaleContent('word', 'sms_warning')}</p>
							</td>
						</tr>
						<tr>
							<th>{getLocaleContent('word', 'memo')}</th>
							<td>
								<input type="text" id="coin-tab-2-memo" name="" value={memo} onChange={e=>setMemo(e.target.value)}/>
								<p className="info-text">{getLocaleContent('word', 'memo_warning')}</p>
							</td>
						</tr>
						<tr id="coin-tab-2-auth-otp-tr">
							<th>{getLocaleContent('word', 'otp_auth')}</th>
							<td>
								<input type="text" id="coin-tab-2-otp-auth-number" name="" value={otp} onChange={e=>setOtp(e.target.value)} placeholder={getLocaleContent('word', 'otp_place')} />
								{/* <!-- <p className="info-text">OTP앱 실행 후 표시 된 인증번호 6자리를 입력하세요</p> --> */}
							</td>
						</tr>
					</tbody>
				</table>
				{/* <!-- //입력 영역 : End --> */}
				<ul>
					{ getLocaleObject("wallet.notices", "withdraw", "coin.warning").map((item, index) => <li style={{color:"red"}} key={index}>{item}</li>) }
				</ul>
				{/* <!-- 출금 요청 버튼 : Start --> */}
				<div className="btn-group mgt20">
					<ul>
						<li>
							<button onClick={handleSubmit} className="blue">{getLocaleContent('word', 'withdrawalTab') }</button>
							<button onClick={handleCancel}>{getLocaleContent('word', 'cancel') }</button>
						</li>
					</ul>
				</div>
				{/* <!-- //출금 요청 버튼 : End --> */}
			</div>
		</div>
);
}
