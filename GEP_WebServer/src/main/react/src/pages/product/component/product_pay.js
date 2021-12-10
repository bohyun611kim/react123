import { useEffect, useState } from "react";
import { BASE_PREFIX } from "../../../module/myconstants";
import request from "../../../module/services/request.service";
import { toDecimalLimit, toFloorDecimals, toLocale } from "../../../module/utils/calculate.util";
import { toFormData } from "../../../module/utils/convert.util";
import { getLocaleObject } from "../../../module/utils/language.util";
import { is_empty } from "../../../module/utils/misc.util";


function ProductHistory({update}) {

  const [hist, setHist] = useState([])

	useEffect(()=>{
    _get_history()
	}, [update])


  const _get_history = async () => {
		const rsp = await request.post(BASE_PREFIX+'/product/prd001/pointConvertList', null).then(response => response.data)
		console.log("pointConvertList ---:", rsp);
		try {
      setHist(rsp);
		} catch (error){
			console.log("failed to parse:", error);
			//location.href = "/";
			return null;
		}
	}


  return (
    <div className="history">
      <h3>페이 전송 내역</h3>
      <table className="table1">
        <thead>
          <tr>
            <th>상태</th>
            <th>코인</th>
            <th>수량</th>
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
                    <td>{item.COIN_NO == 10 ? "KRW" : item.COIN_NO}</td>
                    <td>{toLocale(item.REQ_QTY)}</td>
                    <td>{reqDateTime}</td>
              </tr>
              )
           } )}
        </tbody>
      </table>
    </div>
  );
}


export default function ProductPay() {

  const [asset, setAsset] = useState({})
  const [amount, setAmount] = useState(0)
  const [payid, setPayId] = useState("")
  const [paypwd, setPayPwd] = useState("")

  var fee = 1000;

	useEffect(()=>{
    _get_user_asset()
	}, [])



  const _get_user_asset = async () => {
		const rsp = await request.post(BASE_PREFIX+'/api/wallet/wlt002/selectUserPossessionInfo', null).then(response => response.data)
		// console.log("selectUserPossessionInfo ---:", rsp);

		try {
        var asset = rsp.filter(it=> it.POSS_ASSET == "KRW")
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

  const handleChange = (e, type) => {
    e.preventDefault();

    var num = e.target.value;
    if(type == "direct") {
      setAmount( parseInt(num) )
    } else {
      let avail = asset.TOTAL_QTY - asset.IN_USE_QTY;
      setAmount( parseInt(avail * type / 100))
    }
  }

  const handleSubmit = async (e) => {
    e.preventDefault();

    var param = {
        PointUserId : payid,
        PointUserPwd : paypwd,
        TradePoint:amount,
        RequestIp:"127.0.0.1"
     }
		const rsp = await request.post(BASE_PREFIX+'/product/prd001/pointConvertReq', toFormData(param)).then(response => response.data)
		console.log("pointConvertReq ---:", rsp);

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



  if (asset == undefined || is_empty(asset)) return null;

  var available = asset.TOTAL_QTY - asset.IN_USE_QTY;
  console.log("KRW ==>", available, ", locale:", toLocale(available));
	return (
    <>
        {/*공지사항 시작*/}
        <li id="vtab1" className="product_service">
          <h1 className="subtitle">
              페이 전송
          </h1>
          {/*공지사항 검색및리스트 시작 : 내용이 보이면 이 영역은 안보입니다.*/}
          <div className="list_service">
            {/*<공지사항 리스트 테이블 시작*/}
            <div className="form_service">
              <div className="coin_name">
                <span className="coin-symbol"><img src="/resources/img/coin-symbol/KRW.png" alt="coin" /></span>
                <span>KRW</span>
              </div>
              <table className="type01">
                <colgroup>
                  <col style={{width: '28%'}} />
                  <col style={{width: '50%'}} />
                  <col />
                </colgroup>
                <tbody>
                  <tr>
                    <th>총 잔액</th>
                    <td><input type="text" readOnly defaultValue={toLocale(asset.TOTAL_QTY, 0)} /></td>
                    <td>원</td>
                  </tr>
                  <tr>
                    <th>전송 가능 금액</th>
                    <td><input type="text" readOnly defaultValue={toLocale(available, 0)} /></td>
                    <td>원</td>
                  </tr>
                  <tr>
                    <th>전송수수료</th>
                    <td className="alRight">{toLocale(fee)}</td>
                    <td>원</td>
                  </tr>
                </tbody>
              </table>
              <div className="table_02">
                <table className="type01">
                  <colgroup>
                    <col style={{width: '28%'}} />
                    <col style={{width: '50%'}} />
                    <col />
                  </colgroup>
                  <tbody>
                    <tr>
                      <th>전송 금액</th>
                      <td><input type="text" value={toLocale(amount, 0)} onChange={e=> handleChange(e, "direct")} /></td>
                      <td>원</td>
                    </tr>
                    <tr>
                      <th />
                      <td>
                        <div className="buy-percent calPercent" data-type="pnd">
                          <div onClick={e=>handleChange(e, 25)}>25%</div>
                          <div onClick={e=>handleChange(e, 50)}>50%</div>
                          <div onClick={e=>handleChange(e, 75)}>75%</div>
                          <div onClick={e=>handleChange(e, 100)}>최대</div>
                        </div>
                      </td>
                      <td>원</td>
                    </tr>
                    <tr>
                      <th>실 전송 금액</th>
                      <td><input type="text" readOnly value={toLocale((amount-fee)>0 ? amount-fee : 0)} defaultValue="0" /></td>
                      <td>원</td>
                    </tr>
                    <tr>
                      <th>송금할 페이 ID</th>
                      <td><input type="text" placeholder="Pay ID" defaultValue="" value={payid} onChange={e=>setPayId(e.target.value)}/></td>
                      <td></td>
                    </tr>
                    <tr>
                      <th>송금할 페이 패스워드</th>
                      <td><input type="password" placeholder="Pay password" defaultValue="" value={paypwd} onChange={e=>setPayPwd(e.target.value)}/></td>
                      <td></td>
                      {/* <td><button className="btn_id">ID 확인</button></td> */}
                    </tr>
                  </tbody>
                </table>
                <div className="btn-group">
                  <button className="btn-submit" onClick={handleSubmit}>전송</button>
                </div>
              </div>
            </div>
            <ProductHistory update={available}/>
          </div>
          <div className="content-item">
            <p>&lt;전송시 주의사항&gt;</p>
            <ol className="disc">
              <li style={{display: 'none'}}>1. 페이로 전송 후에는 페이시스템에서 전송된 금액을 확인가능합니다.</li>
              <li style={{display: 'none'}}>2. 페이로 출금된 금액에 대하여는 거래소로 환원이 되지 않습니다. </li>
              <li style={{display: 'none'}}>3. 페이로 전송서비스는 ㈜크롬이노베이션에서 제공하는 서비스입니다.</li>
              <li style={{display: 'none'}}>4. 비타500은 거래소에서 페이시스템으로 전송후에는, 페이서비스 제공과 관련이 없으며, 전송에 따른 손실에 책임지지 않으므로 서비스 이용에 유의하시기 바랍니다.</li>
              <li style={{display: 'none'}}>5. 서비스 이용 관련 궁금하신 사항은 ㈜크롬이노베이션으로 문의하여 주시기 바랍니다.<br />(이메일 :&nbsp;support@crominnovation.com&nbsp;   카카오톡 :&nbsp;1:1 문의하기)</li>
            </ol>
          </div>
          {/*공지사항 검색 및 리스트 끝*/}
        </li>
        {/*공지사항 끝*/}
    </>
	);
}