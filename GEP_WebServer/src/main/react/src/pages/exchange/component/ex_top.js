import { useEffect, useState } from "react";
import { BASE_PREFIX } from "../../../module/myconstants";
import request from "../../../module/services/request.service";
import { toFormData } from "../../../module/utils/convert.util";

export default function ExchangeTop(){
  // site/exchange/getCoinInfo
  // site/exchange/getItemCodeInfo
  const [coinInfo, setCoinInfo] = useState({})

  useEffect(() => {
    _get_coin_info() 
    _get_item_info() 
  }, [])

  const _get_coin_info = async () => {
    var param = {coinNo:20};  // 10 : KRW
		const rsp = await request.post(BASE_PREFIX+'/exchange/getCoinInfo', toFormData(param)).then(response => response.data)
		console.log("_get_coin_info---:", rsp);
		try {

		} catch (error){
			console.log("failed to parse:", error);
			return null;
		}
  }

  const _get_item_info = async () => {
    var param = {coin: BTCKRW};  // 10 : KRW
		const rsp = await request.post(BASE_PREFIX+'/exchange/getItemCodeInfo', toFormData(param)).then(response => response.data)
		console.log("_get_item_info---:", rsp);
		try {

		} catch (error){
			console.log("failed to parse:", error);
			return null;
		}
  }

  return(
  <div className="ex_top">
      <div className="ex_top_right">
        {/* notice */}
        <div className="notice_top">
          <ul>
            <li className="tit">Notice</li>
            <li>Exchange-Market Price abolition Notice</li>
            <li>[Delisting] WNS(WINNERS TOKEN)</li>
          </ul>
        </div>
        <div className="ex_sec01">
          <div id="tab-group-6" className="ui-tabs ui-corner-all ui-widget ui-widget-content">
            <div className="title-wrap">
              <h2 className="full-title">
                <span className="coin-symbol normal pop-open">
                  <img src="../img/coin-symbol/BTC.png" alt="coin" />
                </span>
                <span className="coin-title">비트코인</span>
                <span>
                  <select>
                    <option>비트코인 (BTC/KRW)</option>
                    <option>비트코인 (BTC/BTC)</option>
                    <option>비트코인 (BTC/ETH)</option>
                    <option>비트코인 (BTC/USDT)</option>
                  </select>
                </span>
              </h2>
              {/* 2019-08-07 수정(div 추가 끝) */}
              <ul className="li2 btntab tab_exchange ui-tabs-nav ui-corner-all ui-helper-reset ui-helper-clearfix ui-widget-header" role="tablist">
                <li role="tab" tabIndex={0} className="ui-tabs-tab ui-corner-top ui-state-default ui-tab ui-tabs-active ui-state-active" aria-controls="tabs6-1" aria-labelledby="ui-id-3" aria-selected="true" aria-expanded="true"><a href="#tabs6-1" role="presentation" tabIndex={-1} className="ui-tabs-anchor" id="ui-id-3">시세</a></li>
                <li role="tab" tabIndex={-1} className="ui-tabs-tab ui-corner-top ui-state-default ui-tab" aria-controls="tabs6-2" aria-labelledby="ui-id-4" aria-selected="false" aria-expanded="false"><a href="#tabs6-2" role="presentation" tabIndex={-1} className="ui-tabs-anchor" id="ui-id-4">정보</a></li>
              </ul>
              <div className="lightdark">
                <button className="dark" onclick="javascript:changeCSS('y_dark')">달</button>
                <button className="light" onclick="javascript:changeCSS('y_light')">해</button>
              </div>
              <div className="title-line" />
            </div>
            {/* 시세 냅 내용: Start */}
            <div className="content-item btntabpanel ui-tabs-panel ui-corner-bottom ui-widget-content" id="tabs6-1" aria-labelledby="ui-id-3" role="tabpanel" aria-hidden="false">
              {/*코인시세*/}
              <div className="totalinfo">
                <div className="total-table">
                  <table>
                    <colgroup>
                      <col style={{width: '445px'}} />
                      <col />
                      <col style={{width: '170px'}} />
                      <col style={{width: '250px'}} />
                    </colgroup>
                    <tbody>
                      <tr>
                        <td className="green alLeft">
                          <p className="analysis-total">
                            <span>8,921,000</span>
                            <span className="gray">KRW</span>
                          </p>
                          <p className="analysis-sub">
                            <span className="gray">전일대비</span>
                            <span className="bold">+2.53%</span>
                            <span className="rate up02">아이콘</span>
                            <span className="bold">232,000</span>
                          </p>
                        </td>
                        <td>
                          <div className="minichart" />
                        </td>
                        <td>
                          <dl className="analysis-price">
                            <dt>고가</dt>
                            <dd className="red">9,300,000</dd>
                          </dl>
                          <dl className="analysis-price">
                            <dt>저가</dt>
                            <dd className="green">8,438,000</dd>
                          </dl>
                        </td>
                        <td>
                          <dl className="analysis-price">
                            <dt>거래량(24H)</dt>
                            <dd>
                              <span>31,911.337</span>
                              <span className="gray">BTC</span>
                            </dd>
                          </dl>
                          <dl className="analysis-price">
                            <dt>거래대금(24H)</dt>
                            <dd>
                              <span>286,586,260,562</span>
                              <span className="gray">KRW</span>
                            </dd>
                          </dl>
                        </td>
                      </tr>
                    </tbody>
                  </table>
                </div>
              </div>
            </div>
            {/* //시세 탭 내용: End */}
            {/* 정보 탭 내용 : Start */}
            <div className="content-item btntabpanel ui-tabs-panel ui-corner-bottom ui-widget-content" id="tabs6-2" aria-labelledby="ui-id-4" role="tabpanel" aria-hidden="true" style={{display: 'none'}}>
              <div className="coin-info-tab">
                <div className="coin-info-wrap">
                  {/* 코인 심볼 : Start */}
                  <div className="coin-info-symbol">
                    <p>
                      <img src="../img/coin-symbol/BTC.png" alt="img" />
                    </p>
                    <p className="info-title">비트코인 </p>
                    <p>Bitcoin</p>
                    <p>심볼<span>BTC</span></p>
                  </div>
                  {/* //코인 심볼 : End */}
                  <div className="coin-info-txt">
                    {/* 개발자 정보 : Start*/}
                    <div className="coin-info-detail">
                      <h2 className="tabletitle">개발자 정보</h2>
                      <table>
                        <colgroup>
                          <col style={{width: '150px'}} />
                          <col />
                        </colgroup>
                        <tbody>
                          <tr>
                            <th>법인(재단)명</th>
                            <td>{coinInfo.foundNm}</td>
                          </tr>
                          <tr>
                            <th>법인소재지</th>
                            <td> - </td>
                          </tr>
                          <tr>
                            <th>설립자</th>
                            <td>{coinInfo.representNm} </td>
                          </tr>
                          <tr>
                            <th>대표인물</th>
                            <td>{coinInfo.representNm} </td>
                          </tr>
                          <tr>
                            <th>대표홈페이지</th>
                            <td><a href="#" className="green-link">{coinInfo.homepageUrl}</a></td>
                          </tr>
                        </tbody>
                      </table>
                    </div>
                    {/* //개발자 정보 : End */}
                    {/* 코인 개요 : Start */}
                    <div className="coin-info-detail">
                      <h2 className="tabletitle">암호화폐 개요</h2>
                      <table>
                        <colgroup>
                          <col style={{width: '150px'}} />
                          <col />
                        </colgroup>
                        <tbody>
                          <tr>
                            <th>최초발행</th>
                            <td>{coinInfo.issueDay}</td>
                          </tr>
                          <tr>
                            <th>개발언어</th>
                            <td>C++, Go, 파이썬 등 -</td>
                          </tr>
                          <tr>
                            <th>블록크기</th>
                            <td>8MB -</td>
                          </tr>
                          <tr>
                            <th>블록 생성주기</th>
                            <td>10분 -</td>
                          </tr>
                          <tr>
                            <th>채굴 보상량</th>
                            <td>현재 12.5BT (반감기 일정에 따라 감소 예정) - </td>
                          </tr>
                          <tr>
                            <th>총 발행한도</th>
                            <td>{coinInfo.totSupplyQty}</td>
                          </tr>
                          <tr>
                            <th>합의 프로토콜</th>
                            <td>{coinInfo.protocol}</td>
                          </tr>
                          <tr>
                            <th>백서</th>
                            <td><a href="#" className="green-link">{coinInfo.paperUrl}</a></td>
                          </tr>
                        </tbody>
                      </table>
                    </div>
                    {/* //코인 개요 : End */}
                    {/* 코인 소개 : Start */}
                    <div>
                      <h2 className="tabletitle">암호화폐 소개</h2>
                      <div className="para-data">
                        {coinInfo.explanation}
                      </div>
                    </div>
                    {/* //코인 소개 : End */}
                    {/* 코인 특징 : Start */}
                    <div>
                      <h2 className="tabletitle">암호화폐 특징</h2>
                      <div className="para-data">
                        - 1. 비트코인은 지폐나 동전과 달리 물리적인 형태가 없는 온라인 가상화폐(디지털 통화)다.
                        디지털 단위인 ‘비트(bit)’와 ‘동전(coin)’을 합친 용어다. 나카모토 사토시라는 가명의 프로그래머가 빠르게 진전되는 온라인 추세에 맞춰 갈수록 기능이 떨어지는 달러화, 엔화, 원화 등과 같은 기존의 법화(法貨·legal tender)를 대신할 새로운 화폐를 만들겠다는 발상에서 2009년 비트코인을 처음 개발했다.
                        특히 2009년은 미국발(發) 금융위기가 한창이던 시기어서 미연방준비제도(Fed)가 막대한 양의 달러를 찍어내 시장에 공급하는 양적완화가 시작된 해로, 달러화 가치 하락 우려가 겹치면서 비트코인이 대안 화폐로 주목받기 시작했다.
                        <br /><br />
                        2. 핵심은 정부나 중앙은행, 금융회사 등 어떤 중앙집중적 권력의 개입 없이 작동하는 새로운 화폐를 창출하는 데 있다. 그는 인터넷에 남긴 글에서 “국가 화폐의 역사는 (화폐의 가치를 떨어뜨리지 않을 것이란) 믿음을 저버리는 사례로 충만하다”고 비판했다.
                        비트코인은 은행을 거치지 않고 개인과 개인이 직접 돈을 주고받을 수 있도록 ‘분산화된 거래장부’ 방식을 도입했다. 시스템상에서 거래가 이뤄질 때마다 공개된 장부에는 새로운 기록이 추가된다. 이를 ‘블록체인’이라고 한다. 블록체인에 저장된 거래기록이 맞는지 확인해 거래를 승인하는 역할을 맡은 사람을 ‘채굴자’라고 한다. 컴퓨팅 파워와 전기를 소모해야 하는 채굴자의 참여를 독려하기 위해 비트코인 시스템은 채굴자에게 새로 만들어진 비트코인을 주는 것으로 보상한다. 채굴자는 비트코인을 팔아 이익을 남길 수 있지만, 채굴자 간 경쟁이 치열해지거나 비트코인 가격이 폭락하면 어려움에 처한다.
                      </div>
                    </div>
                    {/* //코인 특징 : End */}
                  </div>
                </div>
              </div>
            </div>
            {/* //정보 탭 내용 : End */}
          </div>
        </div>
      </div>
    </div>
  )
}
