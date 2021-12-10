import { useEffect, useState } from "react";
import { useSetRecoilState } from "recoil";
import { BASE_PREFIX } from "../../../module/myconstants";
import request from "../../../module/services/request.service";
import { toDecimalLimit, toFloorDecimals, toLocale } from "../../../module/utils/calculate.util";
import { toFormData } from "../../../module/utils/convert.util";
import { getLocaleObject } from "../../../module/utils/language.util";
import { is_empty } from "../../../module/utils/misc.util";
import { product_coupon_buy } from "../../../module/stores/modal_state";



export default function ProductCouponBuy({buyInfo}) {

  const [couponQty, setCouponQty] = useState(1)
  const setCouponBuy = useSetRecoilState(product_coupon_buy)

	useEffect(()=>{
    // _get_history()
    // _get_coupon_amount()
	}, [])


  const handleBuy = async (e, amount, bonus) => {
    e.preventDefault()

    // console.log("handleBuy+++", amount, bonus);
		var p = { ordQty:amount, ordBonusQty: amount*bonus/100.0, ordCouponAmount:couponQty}

    if (confirm("쿠폰을 구매 하시겠습니까?")) {
      const rsp = await request .post(BASE_PREFIX + "/product/coupon/doBuyCoupon", toFormData(p)) .then((response) => response.data);
      // console.log("_get_coupon_amount ---:", rsp);
      try {
				if(rsp.rtnCd != 0) {
          // lrt.client(wordbook[rsp.rtnCd], '');
          alert("쿠폰 구매에 실패하였습니다. -" + rsp.rtnCd);
        } else {
          alert("쿠폰을 구매하였습니다.");
        }
      } catch (error) {
        console.log("failed to parse:", error);
        return null;
      } finally {
        setCouponBuy({show:false, update:true, amount:0,bonus:0} )
      }
    } 
  }

  const handleGoDeposit = e => {
    e.preventDefault()
    location.href = "/site/wallet";
  }

  let total = Number(buyInfo.amount) + Number(buyInfo.amount) * Number(buyInfo.bonus) / 100;

	return (
    <>
<div className="popup-wrap popup_coupon" id="lrtClnt" tabIndex={0} style={{top: '0px', display: 'block'}}>
        <div className="popup alert">
          {/* 팝업내용 : Start */}
          <div className="alert-header">
            <div className="alert-title">
              <h2>더드림 정액쿠폰 결제</h2>
            </div>
            <a className="top-close" ><img src="/resources/img/btn-alert-close.png" onClick={e => setCouponBuy({show:false,amount:0,bonus:0})} /></a>
          </div>
          <div className="alert-content">
            <div className="alert_amount">
              <div className="alert_amount_paid">
                <p>원화 결제 가능 금액</p>
                <p className="coin alRight">{toLocale(buyInfo.krw, 0)} KRW</p>
              </div>
              <button className="btn btn-link" onClick={handleGoDeposit}>충전하기</button>
            </div>
            <div className="alert_coupon">
              {/* 10% ~  box_discount01.png; 20% ~  box_discount02.png*/}
              <img src={`/resources/img/newbita/box_discount${buyInfo.bonus}.png`} className="coupon" />
              <div className="box_coupon_cotent">
                <div className="box_coupon_text">
                  <p>사용금액 <span className="orange">{toLocale(total, 0)}</span>원 </p>
                  <h3>{toLocale(buyInfo.amount,0)}원 <span className="orange">쿠폰</span></h3>
                </div>
                <div className="data-input">
                  <input type="text" className="ordPrc" maxLength={17} value={couponQty} onChange={e=> {
                    if (1 <= e.target.value && e.target.value <= 100) setCouponQty(e.target.value)
                  }
                  } />
                  <a style={{cursor: 'pointer'}} className="down" onClick={e=>setCouponQty( couponQty-1 < 1 ? 1 : couponQty-1)}/>
                  <a style={{cursor: 'pointer'}} className="up" onClick={e=>setCouponQty( couponQty+1 > 100 ? 100 : couponQty+1)}/>
                  <p className="mark">*최대 100매까지 구매 가능</p>
                </div>  
              </div>
            </div>
            <p className="alRight">총구매수량 <br />결제예정금액<span className="orange"> {toLocale(buyInfo.amount,0)} KRW</span></p>
          </div>
          <div className="alert-btn">
            <button className="btn-submit" onClick={e=>handleBuy(e, buyInfo.amount, buyInfo.bonus)}>결제</button>
          </div>
          <div className="alert-policy">
            <h3 className="alLeft red">유의사항</h3>
            <ul>
              <li>쿠폰 사용 : 더드림 쿠폰은 BITA500거래소 마켓에서만 사용<br />가능합니다.</li>
              <li>쿠폰 적용 :<span className="green">구매 즉시 적용됩니다.</span><br />거래 금액 및 사용 기한을 반드시 확인하시기 바랍니다.</li>
              <li>쿠폰 구매 : 쿠폰은 원화(KRW)로 구매 가능합니다.<br /><span className="red">*원화(KRW)충전(입금)하신 후 결제합니다.</span></li>
              <li>쿠폰2개 이상 구매 하셨을 때에는 먼저 구매한 쿠폰부터 적용됩니다.</li>
              {/* <li>쿠폰 환불 : 구매 후 사용하신 경우 환불이 불가합니다.</li> */}
              {/* <li>(환불에 관한 문의는 1:1문의 또는 <span className="green">help@bita500.com</span>으로 문의 주시기 바랍니다.)</li> */}
              <li>쿠폰 환불 : 구매 후 환불 불가합니다.</li>
            </ul>
          </div>
        </div>
      </div>

    </>
	);
}