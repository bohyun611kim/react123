import { useEffect, useState } from "react";
import { useRecoilState, useSetRecoilState } from "recoil";
import { BASE_PREFIX } from "../../../module/myconstants";
import request from "../../../module/services/request.service";
import { product_coupon_buy } from "../../../module/stores/modal_state";
import { toDecimalLimit, toFloorDecimals, toLocale } from "../../../module/utils/calculate.util";
import { toFormData } from "../../../module/utils/convert.util";
import { getLocaleObject } from "../../../module/utils/language.util";
import { is_empty } from "../../../module/utils/misc.util";

function CouponUserInfo({hist, amount}) {
  // console.log("CouponUserInfo+++", amount);
  var remain = Number(amount);

  return (
    <div className="status_coupon">
      <h3>쿠폰이용현황</h3>
      <table className="table_coupon alCenter">
        <tbody>
          <tr>
            <th>쿠폰명</th>
            <th>결제 날짜</th>
            <th>결재 금액</th>
            <th>쿠폰 금액</th>
            <th>상태</th>
          </tr>
            { hist && hist.map( (it, index) => {
              // console.log("hist.it", it);
              let coupon = Number(it.ORD_BONUS_QTY) + Number(it.ORD_QTY);
              let state =remain > 0 ? "ACTIVE" : "END";
              remain = remain - coupon;

              if (index >= 10) return null;
              return (
                <tr>
                  <td>{toLocale(it.ORD_QTY)} COUPON</td>
                  <td>{du.getCstmFrmt(it.ORD_DAY, 'yyyyMMdd', 'yyyy-MM-dd')}</td>
                  <td>{toLocale(it.ORD_QTY)}</td>
                  <td>{toLocale(it.ORD_BONUS_QTY + it.ORD_QTY)}</td>
                  <td>{state}</td>
                </tr>
              )
            })}
        </tbody></table>
    </div>
  )
}

export default function ProductCoupon({update}) {

  let couponList = getLocaleObject("coupon.list")

  const [hist, setHist] = useState([])
  const [couponAmount, setCouponAmount] = useState(null)
  const [krw, setAsset] = useState({})
  const [buyInfo, setCouponBuy] = useRecoilState(product_coupon_buy)


	useEffect(()=>{
    _get_coupon_amount()
    _get_user_asset()
	}, [])

	useEffect(()=>{
    if (buyInfo.update == true) {
      _get_coupon_amount()
      _get_user_asset()
    }
	}, [buyInfo])

	useEffect(()=>{
      _get_history()
	}, [couponAmount])



  const _get_user_asset = async () => {
		const rsp = await request.post(BASE_PREFIX+'/api/wallet/wlt002/selectUserPossessionInfo', null).then(response => response.data)
		// console.log("selectUserPossessionInfo ---:", rsp);

		try {
        var asset = rsp.filter(it=> it.POSS_ASSET == "KRW")
      if (asset.length > 0) {
        let avail = asset[0].TOTAL_QTY - asset[0].IN_USE_QTY;
        setAsset(avail);
      } else {
        setAsset(0);
      }
		} catch (error){
			console.log("failed to parse:", error);
			//location.href = "/";
			return null;
		}
	}

  const _get_history = async () => {
		const rsp = await request.post(BASE_PREFIX+'/product/coupon/selectUserCouponHistoryList', null).then(response => response.data)
		// console.log("_get_history ---:", rsp);
		try {
      setHist(rsp);
		} catch (error){
			console.log("failed to parse:", error);
			//location.href = "/";
			return null;
		}
	}


  const _get_coupon_amount = async () => {
    const rsp = await request .post(BASE_PREFIX + "/product/coupon/selectUserCouponAmount", null) .then((response) => response.data);
    // console.log("_get_coupon_amount ---:", rsp);
    try {
      // setHist(rsp);
      setCouponAmount(rsp.COUPON_AMOUNT)
    } catch (error) {
      console.log("failed to parse:", error);
      //location.href = "/";
      return null;
    }
  };

  const handleBuy = async (e, amount, bonus) => {
    e.preventDefault()
    setCouponBuy({show:true, update:false, amount, bonus, krw:krw})
  }


	return (
    <>
    {/*공지사항 시작*/}
      <li id="vtab3" className="product_coupon">
        <h1 className="subtitle">
          더드림 수수료 할인 정액 쿠폰 :
        </h1>
        {/*공지사항 검색및리스트 시작 : 내용이 보이면 이 영역은 안보입니다.*/}
        <div className="list_coupon">
          <div className="box_coupon">
            <div className="content_coupon">
              <img src={"/resources/img/newbita/coupon.png"}  alt="" />
              <h2><span className="green">BITA500</span> 거래소 거래 수수료 할인 혜택 더드림 쿠폰</h2>
            </div>
          </div>

          <div className="infor_coupon">
            <ul>
              <li>쿠폰 사용 : 더드림 쿠폰은 BITA500 거래소 전체 마켓에서 사용 가능합니다.</li>
              <li> 쿠폰 적용 : 구매시 즉시 적용됩니다. </li>
              {/* <li>쿠폰 유효기간 : 구매일로부터 50일 동안 유효합니다. (50일 기간 경과 시 자동 만료됩니다.)</li> */}
              <li>쿠폰을 2개 이상 구매하셨을 때에는 먼저 구매한 쿠폰부터 적용됩니다.</li>
              {/* <li>쿠폰 환불 : 구매후 사용 후에는 환불 불가합니다.<br />(환불에 관한 문의는 1:1 문의 또는 <a href="help@bita500.com" alt="website" className="green">help@bita500.com</a>으로 문의주시기 바랍니다.)</li> */}
              <li>쿠폰 환불 : 구매 후 환불 불가합니다.</li>
            </ul>
          </div>
          <h3>쿠폰 보유 잔액 : {toLocale(couponAmount, 0)} COUPON</h3>
          <ul className="list_coupon_item alCenter">
					  { couponList && couponList.map((item,index) => {
              return (
                  <li>
                    <img src={item.image} alt="discount" />
                    <button className="btn_coupon" onClick={e=>handleBuy(e, item.qty, item.bonus)}>쿠폰구매</button>
                  </li>
                )
            })}
          </ul>
          <CouponUserInfo hist={hist} amount={couponAmount} />

        </div>
        {/*공지사항 검색 및 리스트 끝*/}
      </li>
      {/*공지사항 끝*/}
    </>
	);
}