import { useEffect, useState } from "react";
import { RecoilRoot, useRecoilState,useRecoilValue,useRecoilValueLoadable, useSetRecoilState } from "recoil";
import { BASE_PREFIX } from "../../module/myconstants";
import request from "../../module/services/request.service";
import { toFormData } from "../../module/utils/convert.util";
import MarketCoinList from "./component/market_price";
import ListEvent from "./component/event_list";
import ListNotice from "./component/notice_list";
import SlideCoin from "./component/slide_coin";
import SlideBanner from "./component/slide_banner";
//import FlowMarketPrice from "./component/FlowMarketPrice";
import { getLocaleObject, getLocaleContent} from '../../module/utils/language.util'

function Popup2({popup2}) {

	if (popup2 != 1) return null;

	return (
		<div className="popup2 alert">
			{/* 팝업내용 : Start */}
			<div className="alert-header">
				<div className="alert-title">
				<img src="/resources/img/newbita/popup_header.png" />
				<h2>모바일 시스템 점검공지문</h2>
				</div>
				<a className="top-close" onClick={() => setPopUp2(0)}><img src="/resources/img/btn-alert-close.png" /></a>
			</div>
			<div className="alert-content">
				<h3>모바일 시스템 점검공지문</h3>
				<p>안녕하세요.</p>
				<p>안전하고 간편한 디지털 자산 거래플랫폼 BITA500 거래소입니다.</p>
				<p>저희 BITA500 거래소를 이용해주시는 모든 분들께 감사 인사를 전합니다.</p>
				<p>현재 PC에서는 모든 거래가 정상적으로 가능합니다.</p>
				<p>그러나 모바일에서 거래소를 이용하실 때 회원가입은 가능한데 입출금 및 거래가 원활하지 않을 수 있어 현재 시스템 점검 중에 있습니다. 이용하실 때 불편함을 드려 대단히 죄송합니다. </p>
				<p>최대한 빨리 모바일 시스템 점검을 완료하도록 하겠습니다. </p>
				<p>점검으로 인하여 “WRD 거래 이벤트”는 정상화 처리될 때 다시 진행하고자 합니다. </p>
				<p>이점 양해 부탁드립니다. 이용에 불편함을 드려 죄송합니다. </p>
				<p>앞으로 더욱 안전하고 신뢰할 수 있는 거래소가 되도록 노력하겠습니다.</p>
				<p>감사합니다.</p>
			</div>
		</div>
	)

}

function NoticePopUp({popup,popup2,setPopUp,setPopUp2}) {
    return(
        <div className="popup-wrap popup_mainpage" id="lrtClnt" tabIndex={0} style={{top: '0px', display: 'block'}}>
			<div className="popups">
			{ popup === 1 ?
				<div className="popup1 alert">
				{/* 팝업내용 : Start */}
				<div className="alert-header">
					<div className="alert-title">
					<img src="/resources/img/newbita/popup_header.png" />
					<h2>BITA500 거래소 공지문</h2>
					</div>
					<a className="top-close" onClick={() => setPopUp(0)}><img src="/resources/img/btn-alert-close.png" /></a>
				</div>
				<div className="alert-content">
					<h3>안녕하세요. BITA500 거래소입니다.</h3>
					<p>많은 관심과 참여로 BITA500 거래소를 이용해 주신 모든 분께 감사의 인사를 드립니다.</p>
					<p>BITA500 거래소는 이번 9월 24일 ‘특금법’(특정 금융거래정보의 보고 및 이용 등에 관한 법률) 등 관계 법령에 대하여 준비해왔으며 ‘ISMS’(정보보호인증) 본심사 신청은 완료하였으나 현재 심사 절차가 완료되지 않아 거래소 운영이 중단될 예정이며 ‘ISMS’ 심사 완료 후 재 오픈 예정이오니 회원님들의 양해 부탁드립니다.</p>

					<p className="red">거래소 운영 중단에 따라 9월17일(금) 이후 입, 출금 및 모든 거래가 모두 중단될 것입니다.</p>
					<p className="blue">1. 현재 BITA500거래소에 있는 원화와 코인은 모두 출금 신청을 부탁드립니다.</p>
					<p className="blue">2. WRD 코인 거래를 원하는 회원분들은 17일(금)까지 ‘홀드포트 거래소’로 출금을 부탁드리며 WRD 코인 출금 시 수수료는 부과되지 않습니다.</p>
					<p className="blue">3. 출금되지 않은 원화 및 코인은 안전하게 보관되어 재 오픈 시 거래 가능합니다.</p>
					<p>최대한 빠른 시일 내 ‘ISMS’ 심사 완료 후 금융시장을 선도하고 안전하며 신뢰할 수 있는 디지털 자산 거래소로 다시 찾아 뵐 것을 반드시 약속 드립니다.</p>
					<p>감사합니다.</p>
				</div>
				{/* <div className="alert-btn">
					<button className="yellow" id="lrtClntOk">Read more</button>
				</div> */}
				</div> : ""}
				<Popup2 popup={popup2}/>
        </div>
      </div>
    )
}

function HomeExchageTech() {
	return (
	<>
		<article className="content_security content_security_new">
			<div className="inner_new alCenter">
			<h2 className="tit">{getLocaleContent("word","security_tit")}</h2>
				<ul className="list_security">
					{getLocaleObject('exchange.technology.list').map((item, index) => {
						return <li key={index}>
									<img src={`/resources/img/newbita/${item.image}`} alt={item.alt} />
									<div className="detail_exchange">
										<p className="list_tit">{item.title}</p>
										{ item.explain.split("\n").map( (it, idx) => ( <p key={idx} className="list_txt">{it}</p>) )}
									</div>
								</li>
					})}
				</ul>
			</div>
		</article>

			

	</>
	);
}

function HomeSecurity() {
	return (
		<article className="content_system">
        <div className="inner_new alCenter">
          <ul className="list_system">
			{getLocaleObject('list.system.list').map((item, index) => {
				return <li key={index}>
							<img src={`/resources/img/newbita/${item.image}`} alt={item.alt} />
							<p className="list_tit">{item.title}</p>
							{ item.explain.split("\n").map( (it, idx) => ( <p key={idx} className="list_txt">{it}</p>) )}
						</li>
			})}
          </ul>
        </div>
      </article>
	);
}

function Homepage() {

  const [marketList, setMostCoinList] = useState([]);
  const [slideList, setSlideList] = useState({});
  const [popup, setPopUp] = useState(1);
  const [popup2, setPopUp2] = useState(0);

  useEffect(()=>{
    _get_market();
  }, [])

  const _get_market = async () => {
    var param = {coinNo:10};  // 10 : KRW
		const rsp = await request.post(BASE_PREFIX+'/exchange/getMarket', toFormData(param)).then(response => response.data)
		// console.log("_get_market ---:", rsp);
		try {
      var marketCoinList = rsp.market.filter(it => it.coinNo == 10)


      if (marketCoinList.length > 0) {
        //console.log("marketCoinList:: slideList", marketCoinList[0].item);
        setSlideList(marketCoinList[0].item);
        var mostCoinList = marketCoinList[0].item.sort((a, b) => {
          if (a.tradeAmt < b.tradeAmt) return 1;
          if (a.tradeAmt > b.tradeAmt) return -1;
          if (a.tradeAmt == b.tradeAmt) return 0;
        })


        setMostCoinList(rsp.market)
      }

		} catch (error){
			console.log("failed to parse:", error);
			return null;
		}
  }
  
	// console.log("marketList:::", marketList)
	if (marketList.length == 0) return null;

	return (
	<>
        <div className="main-visual">
          	<div className="main-visual main-visual-new">
			  	<div className="visual-wrap">
					<ListEvent/>
					<ListNotice/>
				</div>
            </div>
        </div>
        <div className="home">
          	<SlideCoin list={slideList}/>
		  	<article className="content_assets">
				<div className="inner_new alCenter">
					<SlideBanner/>
					<MarketCoinList marketList={marketList}/>
				</div>
      		</article>

			{/* <FlowMarketPrice/> */}
			{/* <HomeEventContent/>
			<HomeEventSchedule/> */}
			<HomeExchageTech/>
			<HomeSecurity/>
			{/* <HomePartner/> */}
        </div>
		{ popup === 1 || popup2 === 1 ? <NoticePopUp popup={popup} popup2={popup2} setPopUp={setPopUp} setPopUp2={setPopUp2}/> : "" }
	</>
	);
}

function HomepageRoot({cont}) {


	// console.log("content:", cont)
	return (
		<RecoilRoot>
			<Homepage/>
		</RecoilRoot>
	);
}

let domContainer = document.querySelector('#content');
ReactDOM.render(<HomepageRoot />, domContainer);
