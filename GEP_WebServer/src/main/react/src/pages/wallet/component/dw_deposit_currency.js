import { useEffect, useRef, useState } from "react";
import { useRecoilCallback, useRecoilState, useRecoilValue, useSetRecoilState } from "recoil";
import { BASE_PREFIX } from "../../../module/myconstants";
import request from "../../../module/services/request.service";
import {
  wallet_comp_info,
  wallet_bank_info,
  wallet_deposit_info,
} from "../../../module/stores/wallet_state";
import { toFormData } from "../../../module/utils/convert.util";
import {atom } from 'recoil';
import { getLocaleObject, getLocaleContent } from "../../../module/utils/language.util";


var _amountRef = null;


const ChargeRequest = (depInfo, setRequestAmount) => {

  var chargeAmountRequested = _amountRef.current.value.replace(/,/g, "");
  var chk = /^(?:-?\d+|-?\d{1,3}(?:,\d{3})+)?(?:\.\d+)?$/;
  var result = true;

  if (__G_User_Auth_Level < 3) {
    lrt.client(
      getLocaleContent("word", "depo_level3"),
      getLocaleContent("word", "depositTab"),
      function () { location.href = "/site/info?tab=1"; },
      null
    );
    return false;
  }

  if (parseInt(chargeAmountRequested) < 1 || chargeAmountRequested.length < 1) {
    lrt.client(
      getLocaleContent("word", "depo_request_amount"),
      getLocaleContent("word", "request_amount"),
      function () { _amountRef.current.focus(); },
      null
    );
    return false;
  }

  if (!chk.test(chargeAmountRequested)) {
    lrt.client(
      getLocaleContent("word", "depo_request_number"),
      getLocaleContent("word", "request_amount"),
      function () {
        _amountRef.current.value = 0;
        _amountRef.current.focus();
      },
      null
    );
    return;
  }

  let minDepositQty = __G_CoinMgtRef_Map.get(10).MIN_DEPOSIT_QTY;
  if (parseFloat(chargeAmountRequested) < minDepositQty) {
    lrt.client(
      getLocaleContent("word", "depo_request_min").replace("MIN_AMOUNT", nu.cm(minDepositQty) , "gi"),
      function () {
        _amountRef.current.focus();
      },
      null
    );
    return;
  }

  // $('#agreeChk').find('input').each(function() {
  //     if( !$(this).is(':checked') ) {
  //         lrt.client('모든 사항에 동의가 필요합니다.', '동의 요청', null, null);
  //         result = false;
  //         return false;
  //     }
  // });

  if (true) {
    lrt.confirm(
      getLocaleContent("word", "depo_request_summit"),
      getLocaleContent("word", "deposit") + getLocaleContent("word", "_summit"),
      function () {
        moneyChargingRequest(depInfo, setRequestAmount)
      },
      null
    );
  }
}

const moneyChargingRequest = (depInfo, setRequestAmount) => {

  //const [depInfo, setDepInfo] = useRecoilState(wallet_deposit_info);
  // console.log("moneyChargingRequest+++", depInfo);

  const _currency_request = async () => {
    var param = depInfo;
		const rsp = await request.post(BASE_PREFIX+'/wallet/wlt001/requestMoneyDeposit', toFormData(param)).then(response => response.data)
    console.log("_currency_request ---:", rsp);
    return rsp;
  }

  _currency_request().then(data=>{
    if(data.rtnCd != 0) {lrt.client(wordbook[data.rtnCd], '');}
      if(data.rtnCd == 0) {
          setRequestAmount(0);
          lrt.client(
            getLocaleContent("word", "depo_summit_done"),
            getLocaleContent("word", "depositTab") + getLocaleContent("word", "_ok"),
            function() { }, null);
      }
  }).catch(function(error) {
      console.log("에러 >> ",error)
      lrt.client(
        getLocaleContent("word", "depo_summit_fail"),
        getLocaleContent("word", "depositTab") + getLocaleContent("word", "_nok"),
        function() { }, null);
  })

}

function ManualDeposit({ comp, bank }) {

  const [req, setReqAmt] = useState(0);
  const [accountHoder, setAccountHolder] = useState("");
  const setDepInfo = useSetRecoilState(wallet_deposit_info);
  const amountRef =  useRef(null);
  _amountRef = amountRef;


  useEffect(()=>{
    setDepInfo({
      finCd: "MANUAL",
      bankCd: comp.BANK_CD,
      accntNo: comp.BANK_ACCNT_NO,
      holderNm: comp.ACCNT_HOLDER_NM,
      reqAmt: req,
    });
  }, [req])



  const handleSubmit = (e) => {
    e.preventDefault();
    // console.log("handleSubmit--- ref", amountRef.current);
    ChargeRequest({
      finCd: "MANUAL",
      bankCd: comp.BANK_CD,
      accntNo: comp.BANK_ACCNT_NO,
      holderNm: accountHoder,
      reqAmt: req,
    }, setReqAmt);
  };

  return (
    <div
      id="tabs-child-2"
      aria-labelledby="ui-id-5"
      role="tabpanel"
      className="ui-tabs-panel ui-corner-bottom ui-widget-content"
      aria-hidden="false"
      style={{}}
    >
      <div className="tab-content">
        <div className="other">
          <form>
            <dl>
              <dt>{getLocaleContent('word', 'depositbank')}</dt>
              <dd>
                <input
                  type="text"
                  disabled
                  defaultValue={_G_Bank_Code_Map.get(comp.BANK_CD)}
                />
              </dd>
            </dl>
            <dl>
              <dt>{getLocaleContent('word', 'accountnumber')}</dt>
              <dd>
                <input type="text" disabled defaultValue={comp.BANK_ACCNT_NO} />
              </dd>
            </dl>
            <dl>
              <dt>{getLocaleContent('word', 'accountholder')}</dt>
              <dd>
                <input
                  type="text"
                  disabled
                  defaultValue={comp.ACCNT_HOLDER_NM}
                />
              </dd>
            </dl>
            <dl>
              <dt>{getLocaleContent('word', 'depositor')}</dt>
              <dd>
                <input type="text" defaultValue="" onChange={e=> setAccountHolder(e.target.value)} />
              </dd>
            </dl>
            <dl>
              <dt>{getLocaleContent('word', 'depositamount')}</dt>
              <dd>
                <input type="number" id="money-tab-1-request-amount" ref={amountRef} name="banking.money" value={req} defaultValue="" onChange={e=>setReqAmt(e.target.value)}/>
              </dd>
            </dl>
            <button type="submit" className="submit" onClick={handleSubmit}>
              Submit
            </button>
          </form>
        </div>
        <div className="qr">
          <div className="modal-wrapper sc-htoDjs msxXK" id="homepage" />
        </div>
      </div>
    </div>
  );
}

function AutoDeposit({ comp, bank }) {
  //console.log("Auto:", comp, bank);

  if (comp == null) return null;

  //_G_Bank_Code_Map.get(bank.BANK_CD)
  return (
    <div
      id="tabs-child-1"
      aria-labelledby="ui-id-4"
      role="tabpanel"
      className="ui-tabs-panel ui-corner-bottom ui-widget-content"
      aria-hidden="false"
    >
      <div className="tab-content">
        <div className="auto">
          <div className="call_info">
            <dl>
              <dt>{getLocaleContent('word', 'depositbank')}</dt>
              <dd>
                <input
                  type="text"
                  disabled
                  defaultValue={_G_Bank_Code_Map.get(comp.BANK_CD)}
                />
              </dd>
            </dl>
            <dl>
              <dt>{getLocaleContent('word', 'accountnumber')}</dt>
              <dd>
                <input type="text" disabled defaultValue={comp.BANK_ACCNT_NO} />
              </dd>
            </dl>
            <dl>
              <dt>{getLocaleContent('word', 'accountholder')}</dt>
              <dd>
                <input
                  type="text"
                  disabled
                  defaultValue={comp.ACCNT_HOLDER_NM}
                />
              </dd>
            </dl>
          </div>
          <div className="code">
            <dl>
              <dt>
              {getLocaleContent('word', 'mydeposit_code')} :<span>({getLocaleContent('word', 'mysending_name')})</span>
              </dt>
              <dd>
                {bank.ACCNT_HOLDER_NM + comp.DEP_CODE}
                <span>({getLocaleContent('word', 'sending_name')}+{getLocaleContent('word', 'deposit_code')})</span>
              </dd>
            </dl>
            <p>* {getLocaleContent('word', 'deposit_tip')} </p>
          </div>
          <div className="tip">
            <p>{getLocaleContent('word', 'base_unit')} {getLocaleContent('word', 'deposit')} TIP</p>
            <ul>
					    { getLocaleObject("wallet.notices", "deposit", "banking.desc").map((item, index) => <li key={index}>{item}</li>) }
            </ul>
          </div>
        </div>
        {/* <div className="important" id="deposit">
            <p>Important</p>
            <ul id="other">
                <li>KRW The payment time may take 5 ~ 30 minutes after applying for the deposit request.</li>
                <li>When you make a deposit request, the administrator will make a payment by hand. (No real-time deposits)</li>
                <li>If you make a deposit, you may be delayed.</li>
                <li>After a certain time, the data will be deleted, so please deposit as soon as possible after request.</li>
            </ul>
        </div> */}
      </div>
    </div>
  );
}


export default function DepositCurrencyRequst() {
  const [selectTab, setSelectedTab] = useState(1);
  //const userInfo = useRecoilValue(wallet_user_info)

  const compBank = useRecoilValue(wallet_comp_info);
  const userBank = useRecoilValue(wallet_bank_info);

  return (
    <div id="tab-group-deposit" style={{ display: "block" }} className="ui-tabs ui-corner-all ui-widget ui-widget-content" >
      <ul className="li2 ui-tabs-nav ui-corner-all ui-helper-reset ui-helper-clearfix ui-widget-header" role="tablist" >
        <li role="tab" tabIndex={0} onClick={(e) => setSelectedTab(1)} className={`ui-tabs-tab ui-corner-top ui-state-default ui-tab ${ selectTab == 1 ? "ui-tabs-active ui-state-active" : "" }`} >
          <a href="#" role="presentation" tabIndex={-1} className="ui-tabs-anchor" id="ui-id-4" > {getLocaleContent("word","depositAuto")} </a>
        </li>
        <li role="tab" tabIndex={-1} onClick={(e) => setSelectedTab(2)} className={`ui-tabs-tab ui-corner-top ui-state-default ui-tab ${ selectTab == 2 ? "ui-tabs-active ui-state-active" : "" }`} >
          <a href="#" role="presentation" tabIndex={-1} className="ui-tabs-anchor" id="ui-id-5" > {getLocaleContent("word","depositManual")}</a>
        </li>
      </ul>
      {selectTab == 1 ? (
        <AutoDeposit comp={compBank} bank={userBank} />
      ) : (
        <ManualDeposit comp={compBank}  bank={userBank}/>
      )}
    </div>
  );
}
