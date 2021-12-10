import { useEffect, useRef, useState } from "react";
import { RecoilRoot, useRecoilState,useRecoilValue,useRecoilValueLoadable, useSetRecoilState } from "recoil";
import { BASE_PREFIX } from "../../module/myconstants";
import request from "../../module/services/request.service";
import { toLocale } from "../../module/utils/calculate.util";
import { is_empty } from "../../module/utils/misc.util";


function ExchangeBuySell() {

	const myref = useRef({});

	const [asset, setAsset] = useState(null)
	const [ordPrice, setOrderPrice] = useState(0)
	const [ordQty, setOrderQty] = useState(0)


	var hogaBuy = useRef([])
	var hogaSell = useRef([])
	var marketInfo = useRef({})

	useEffect(() => {
		console.log("+++++++++++++ ExchangeBuySell +++++++++++++++++");
		myref.current.setAssetInfo = setAssetInfo;
		window.buysell = myref.current;
	}, [])

	const setAssetInfo = (data) => {
		console.log("ExchangeBuySell::setAssetInfo", data);
		let assetInfo = data.info;
    setAsset(assetInfo)

		//let $tr = $('#tab-group-1').find('tr[data-code=' + data.itemCode + ']');
	}

  const handleUp = e => {

  }

  const updateOrder = (e, action) => {
    e.preventDefault()
    console.log("updateOrder:", action);
    if (action == 'up') {
      let qty = ordPrice- asset.amtCalcDecPnt > 0 ? ordPrice- asset.amtCalcDecPnt : 0
    } else {
      let qty = ordPrice> 0 ? ordPrice+ asset.amtCalcDecPnt: 0
    }
  }

  const handleChange = (e,etype) => {
    e.preventDefault()
    if (etype == 'price')  {
      setOrderPrice(e.target.value)
    } else if (etype == 'qty') {
      setOrderQty(e.target.value)
    }
  }

  const handlePercent = (e, per) => {

    console.log(",per:", per);

    e.preventDefault()
    let amt = asset.fromQty;
    let prc = ordPrice;

    console.log("qty:", qty, "amt:", amt, ",price:", prc, "fee:", asset.buyFeeVal, ",per:", per);
    let qty = (amt / prc) * per;
    if(qty*prc >= amt-amt*asset.buyFeeVal*0.01) {
      qty = amt / (1.0 + asset.buyFeeVal*0.01) / prc;
    }
    setOrderQty(qty)
  }

  if (!asset ) return null;
  console.log("assetInfo changed", asset)

	return (
		<>
		<div id="tabs2-1">
        {/*매수매도영역 시작*/}

        {/*지정가 매수하기 시작*/}
        <BuyFixed info={asset} orderPrice={ordPrice} orderQty={ordQty} handleChange={handleChange} handlePercent={handlePercent} updateOrder={updateOrder}/>
       {/*지정가 매수하기 끝*/}

        {/*지정가 매도하기 시작*/}
        <SellFixed info={asset}/>
        {/*지정가 매도하기 끝*/}

        {/*매수매도영역 끝*/}
      </div>
	</>
	);
}

function BuyFixed({info, orderPrice, orderQty, handleChange, handlePercent, updateOrder}) {

	let split = info.itemNm.split('/');
  console.log("BuyFixed::split", split);


  return (
    <div className="pending-buy">
    <div className="buysell-wrap">
      <table>
        <tbody data-type="buy">
          <tr>
            <th>주문가능1</th>
            <td colSpan={3}>
              <div className="readonly">
                <span className="fromQty">{toLocale(info.fromQty, info.prcDspDecPnt)}</span>
                <span className="amtUnit">{split[1]}</span>
              </div>
            </td>
          </tr>
          <tr>
            <th>매수가격</th>
            <td colSpan={3}>
              <div className="data-input">
                <input type="text" className="ordPrc" maxLength={17} style={{textAlign: 'right'}} data-type="buy" value={toLocale(orderPrice, info.prcDspDecPnt)} onChange={e=>handleChange(e, "price")}/>
                <a style={{cursor: 'pointer'}} className="down" onClick={e=>console.log("---")} />
                <a style={{cursor: 'pointer'}} className="up" onClick={e=>console.log("+++")} />
              </div>
            </td>
          </tr>
          <tr>
            <th>매수수량2</th>
            <td colSpan={3}>
              <div className="data-input">
                <input type="text" className="tip-open ordQty qty tip-minAmt" maxLength={17} data-type="buy" value={toLocale(orderQty, info.qtyCalcDevPnt)} onChange={e=>handleChange(e, "qty")}/>
                <span className="volUnit" >{split[0]}</span>
              </div>
              <i className="tip"> 키보드 상하방향키로 설정 가능 </i>
            </td>
          </tr>
          <tr>
            <th />
            <td colSpan={3}>
              <div className="buy-percent calPercent" data-type="pnd">
                <div onClick={e=>handlePercent(e, 0.25)}>25%</div>
                <div onClick={e=>handlePercent(e, 0.50)}>50%</div>
                <div onClick={e=>handlePercent(e, 0.75)}>75%</div>
                <div onClick={e=>handlePercent(e, 1)}>100%</div>
              </div>
            </td>
          </tr>
          <tr>
            <th colSpan={2} className="bold"> 주문총액 </th>
            <td colSpan={2} className="totalorder red">
              <span>0</span>
              <span className="amtUnit" />
            </td>
          </tr>
          <tr>
            <th colSpan={2} className="min_krw">
              {/* <span class="minAmt"></span> */}
            </th>
            <td colSpan={2} className="fee">
              {/* <span><%=getLocaleString("_L_0060")%></span>
                        <span class="buyFee"></span> % */}
            </td>
          </tr>
        </tbody>
      </table>
      <div className="btn-buy-sell">
        <a style={{cursor: 'pointer'}}>
              <button className="init bggray"> 초기화 </button>
              {/* <button className="bggray" onclick="doRegister();">회원가입</button> */}
        </a>
        <a style={{cursor: 'pointer'}}>
              <button type="button" className="red" onclick="doPndBuy(this);">매수하기</button>
              {/* <button className="red" onclick="doLogin();">로그인</button> */}
        </a>
      </div>
    </div>
  </div>
  );
}

function SellFixed({info}) {

	let split = info.itemNm.split('/');
  console.log("SellFixed::split", split);
	// $('.toQty').text(nu.cm(data.toQty, data.qtyDspDecPnt, true));

  return (
    <div className="pending-sell">
      <div className="buysell-wrap">
        <table>
          <tbody data-type="sell">
            <tr>
              <th>주문가능</th>
              <td colSpan={3}>
                <div className="readonly">
                  <span className="toQty">{toLocale(info.toQty, info.qtyDspDecPnt)}</span>
                  <span className="volUnit">{split[0]}</span>
                </div>
              </td>
            </tr>
            <tr>
              <th>매도가격</th>
              <td colSpan={3}>
                <div className="data-input">
                  <input type="text" className="ordPrc" maxLength={17} style={{textAlign: 'right'}} data-type="sell" />
                  <a style={{cursor: 'pointer'}} className="down" />
                  <a style={{cursor: 'pointer'}} className="up" />
                </div>
              </td>
            </tr>
            <tr>
              <th>매도수량</th>
              <td colSpan={3}>
                <div className="data-input">
                  <input type="text" className="tip-open ordQty qty tip-minAmt" maxLength={17} data-type="sell" />
                  <span className="volUnit" >{split[1]}</span>
                </div>
                <i className="tip"> 키보드 상하방향키로 설정 가능 </i>
              </td>
            </tr>
            <tr>
              <th />
              <td colSpan={3}>
                <div className="buy-percent calPercent" data-type="pnd">
                  <div>25%</div>
                  <div>50%</div>
                  <div>75%</div>
                  <div>100%</div>
                </div>
              </td>
            </tr>
            <tr>
              <th colSpan={2} className="bold">주문총액 </th>
              <td colSpan={2} className="totalorder blue">
                <span>0</span>
                <span className="amtUnit" />
              </td>
            </tr>
            <tr>
              <th colSpan={2} className="min_krw">
                {/* <span class="minAmt"></span> */}
              </th>
              <td colSpan={2} className="fee">
                {/* <span><%=getLocaleString("_L_0060")%></span>
                          <span class="sellFee"></span> % */}
              </td>
            </tr>
          </tbody>
        </table>
        <div className="btn-buy-sell">
          <a style={{cursor: 'pointer'}}>
                <button className="init bggray">초기화</button>
                {/* <button className="bggray" onclick="doRegister();">회원가입</button> */}
          </a>
          <a style={{cursor: 'pointer'}}>
                <button className="blue" onclick="doPndSell(this);">매도하기</button>
                {/* <button className="blue" onclick="doLogin();">로그인</button> */}
          </a>
        </div>
      </div>
    </div>
        );
}
function ExchangeBuySellRoot() {
	return (
		<RecoilRoot>
			<ExchangeBuySell/>
		</RecoilRoot>
	);
}

// let domContainer = document.querySelector('#like_exchange_content');
let domContainer = document.querySelector('#tabs2-1');
ReactDOM.render(<ExchangeBuySellRoot/>, domContainer);

