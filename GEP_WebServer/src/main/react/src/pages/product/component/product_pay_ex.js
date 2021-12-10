import { useEffect, useState } from "react";
import { BASE_PREFIX } from "../../../module/myconstants";
import request from "../../../module/services/request.service";
import { toDecimalLimit, toFloorDecimals, toLocale, toNumber } from "../../../module/utils/calculate.util";
import { toFormData } from "../../../module/utils/convert.util";
import { getLocaleObject } from "../../../module/utils/language.util";
import { is_empty } from "../../../module/utils/misc.util";


function ProductHistory({update, selectedCoin}) {

  const [hist, setHist] = useState([])

	useEffect(()=>{
    _get_history()
	}, [update])


  const _get_history = async () => {
    let param = {ItemCode : selectedCoin.name + "KRW"}
		const rsp = await request.post(BASE_PREFIX+'/product/prd001/pointConvertList', toFormData(param)).then(response => response.data)
		console.log("pointConvertList ---:", rsp);
		try {
      setHist(rsp);
		} catch (error){
			console.log("failed to parse:", error);
			//location.href = "/";
			return null;
		}
	}

  let payObj = getLocaleObject("product.pay");

  return (
    <div className="history">
      <h3>{payObj.history}</h3>
      <table className="table1">
        <thead>
          <tr>
            {/* {payObj & payObj.history_head.map( (it, index) => <th>{it}</th> )} */}
            <th>상태</th>
            <th>수량</th>
            <th>금액</th>
            <th>날짜</th>
          </tr>
        </thead>
        <tbody>
            {hist.length > 0 && hist.map((item, index) => {
              if (index > 10) return null;

              let approve = "미승인";  // (1:승인, 0:미승인,-1:승인취소,-2:환급)
              if (item.APPROVAL_STAT_CD == 1) {
                approve = "승인";
              } else if (item.APPROVAL_STAT_CD == -1) {
                approve = "승인취소";
              } else if (item.APPROVAL_STAT_CD == -2) {
                approve = "환급";
              }

              var reqDateTime = getCstmFrmt(item.REQ_DT, 'yyyyMMddHHmmssSSSSSS', 'yyyy-MM-dd HH:mm');

              return (
              <tr>
                    <td>{approve}</td>
                    <td>{item.PIN_CD}</td>
                    <td>{toLocale(item.REQ_QTY, 0)}</td>
                    <td>{reqDateTime}</td>
              </tr>
              )
           } )}
        </tbody>
      </table>
    </div>
  );
}

function OptionList({optionOpen,coinList, selectedCoin, setSelectedCoin}) {
  return (
      <div className={`fade-enter-done ${optionOpen ? "open": "" }`}>
        <input type="text" className="srch" value={selectedCoin.name} placeholder="Please input keyword of coin" defaultValue />
        <ul>
          {coinList.map( (it, index) => {
              return (
                <li onClick={e=>setSelectedCoin(it)}>
                  <span className="coin-symbol">
                    <img src={`/resources/img/coin-symbol/${it.name}.png`} alt="coin" />
                  </span>
                  <span>{it.name}</span>
                </li>
              ); 
          })}
        </ul>
      </div>
  );
}

export default function ProductPayEx() {

  const [optionOpen, setOptionOpen] = useState(false)
  const [asset, setAsset] = useState({})
  const [amount, setAmount] = useState(0)
  const [payid, setPayId] = useState("")
  const [paypwd, setPayPwd] = useState("")
  const [selectedCoin, setSelectedCoin] = useState({name: "WRD", no:200})

  let coinList =  [ 
        {name: "WRD", no:200}, 
      ];
  var fee = 1000;

	useEffect(()=>{
    _get_user_asset()
	}, [])

	useEffect(()=>{
    handleOptionOpen(false)
	}, [selectedCoin])




  const _get_user_asset = async () => {
		const rsp = await request.post(BASE_PREFIX+'/api/wallet/wlt002/selectUserPossessionInfo', null).then(response => response.data)
		// console.log("selectUserPossessionInfo ---:", rsp);

		try {
        var asset = rsp.filter(it=> it.POSS_ASSET == selectedCoin.name)
        console.log("WRD asset:", asset);
        if (asset.length > 0) {
          setAsset(asset[0]);
        } else {
          setAsset({ TOTAL_QTY:"0", IN_USE_QTY:"0" });
        }
		} catch (error){
			console.log("failed to parse:", error);
			//location.href = "/";
			return null;
		}
	}

  const handleChange = (e, per) => {
    e.preventDefault();

    var num = e.target.value;
    let avail = asset.TOTAL_QTY - asset.IN_USE_QTY;
    if(per == "direct") {
      setAmount( toNumber(num) > avail ? avail : toNumber(num))
    } else {
      setAmount( parseInt(avail * per / 100))
    }
  }

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!confirm("코인을 페이로 전송하시겠습니까? 코인은 시장가로 판매됩니다.")) {
        return;
    }
    var param = {
        MktId : "HOLDPORT_KRW",
        MktGrpId : "MKT_GRP_0005",
        ItemCode : (selectedCoin.name + "KRW"),
        PointUserId : payid,
        PointUserPwd : paypwd,
        OrdQty:amount,
        RequestIp:"127.0.0.1"
     }
		const rsp = await request.post(BASE_PREFIX+'/product/prd001/convertCoinToPointReq', toFormData(param)).then(response => response.data)
		// console.log("convertCoinToPointReq ---:", rsp);

    // resultCode: "1"
    //resultMsg: "service success"
		try {

      if (rsp.resultCode == "1") {
        alert("페이출금 성공");
        setAmount(0)
        setPayId("")
        setPayPwd("")
        _get_user_asset()
      } else {
        alert("페이출금 실패");
      }

		} catch (error){
			console.log("failed to parse:", error);
			//location.href = "/";
			return null;
		}

  }

  const handleOptionOpen = (open) => {
    if (coinList.length > 1) {
      setOptionOpen(open)
    }
  }


  let payObj = getLocaleObject("product.pay");

  if (asset == undefined || is_empty(asset)) return null;

  var available = asset.TOTAL_QTY - asset.IN_USE_QTY;
  // console.log("KRW ==>", available,", asset:", asset, ", locale1:", toLocale(available, 0), ", option:", optionOpen);
	return (
    <>
       <input type="password" style={{opacity: '0',position: 'absolute'}} />
      <li id="vtab2" className="product_transac" style={{}}>
        <h1 className="subtitle">{payObj.menu} </h1>
        {/*공지사항 검색및리스트 시작 : 내용이 보이면 이 영역은 안보입니다.*/}
        <div className="list_service">
          {/*<공지사항 리스트 테이블 시작*/}
          <div className="form_service">
            <div className="coin_select">

              <div className="coin_name"  onClick={e=>handleOptionOpen(!optionOpen)}>
                <span className="coin-symbol">
                  <img src={`/resources/img/coin-symbol/${selectedCoin.name}.png`} alt="coin" />
                </span>
                <span>{selectedCoin.name}</span>
              </div>
              {/* open  */}
              <OptionList optionOpen={optionOpen} coinList={coinList} selectedCoin={selectedCoin} setSelectedCoin={setSelectedCoin} />
            </div>
            <div className="coin_trans alCenter">
              <span>{selectedCoin.name} coin</span>
              <img src="/resources/img/newbita/arrow_r.png" alt="right" className="arrow_r" />
              <span>{selectedCoin.name} pay</span>
            </div>
            <table className="type01">
              <colgroup>
                <col style={{width: '28%'}} />
                <col style={{width: '50%'}} />
                <col />
              </colgroup>
              <tbody>
                <tr>
                  <th>전환가능수량</th>
                  <td><input type="text" readOnly defaultValue={toLocale(available, 0)} /></td>
                  <td>{selectedCoin.name}</td>
                </tr>
                <tr>
                  <th>전환 수량</th>
                  <td><input type="text" value={toLocale(amount, 0)} onChange={e=> handleChange(e, "direct")} /></td>
                  <td>{selectedCoin.name}</td>
                </tr>
                <tr>
                  <td colSpan={2}>
                    <div className="buy-percent calPercent" data-type="pnd">
                      <div onClick={e=>handleChange(e, 25)}>25%</div>
                      <div onClick={e=>handleChange(e, 50)}>50%</div>
                      <div onClick={e=>handleChange(e, 75)}>75%</div>
                      <div onClick={e=>handleChange(e, 100)}>최대</div>
                    </div>
                  </td>
                  <td />
                </tr>
                <tr>
                  <th>송금할 페이 ID</th>
                  <td>
                    <input type="text" placeholder="Pay ID" defaultValue="" value={payid} onChange={e=>setPayId(e.target.value)}/></td>
                  <td></td>
                </tr>
                <tr>
                  <th>송금할 페이 패스워드</th>
                  <td><input type="password" placeholder="Pay password" defaultValue="" value={paypwd} onChange={e=>setPayPwd(e.target.value)}/></td>
                  <td></td>
                  {/* <td><button className="btn_id">ID 확인</button></td> */}
                </tr>
            
                <tr>
                  <td colSpan={3} className="alCenter">
                    <button className="btn-submit" onClick={handleSubmit}>전송</button>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
          {/*<공지사항 리스트 테이블 끝*/}
          <ProductHistory update={available} selectedCoin={selectedCoin}/>
        </div>
        <div className="content-item">
          <p>&lt;전송시 주의사항&gt;</p>
          <ol className="disc">
            <li>1.페이로 전환된 코인은 다시 원위치가 안됩니다. 주의하시기 바랍니다.</li>
            <li>2.코인이 페이로 전환될 경우 코인이 거래소에서 시장가로 판매가 되기 때문에 판매 금액과 일치 하지 않을 수 있습니다</li>
          </ol>
        </div>
        {/*공지사항 검색 및 리스트 끝*/}
      </li>
    </>
	);
}