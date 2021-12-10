import { useEffect, useRef, useState } from "react";
import { RecoilRoot, useRecoilState,useRecoilValue,useRecoilValueLoadable, useSetRecoilState } from "recoil";
import { BASE_PREFIX } from "../../module/myconstants";
import request from "../../module/services/request.service";
import { is_empty } from "../../module/utils/misc.util";
import ExchangeContent from "./component/ex_content";
import ExchangeMarket from "./component/ex_market";
import AskingTable from './component/ex_asking';
import ExchangeBuySell from './component/ex_buysell';
import ExchangeOrder from './component/ex_order';
import ExchangeTop from './component/ex_top';
import { market_coin_selected, market_tab_selected } from "../../module/stores/exchange_state";

function Exchange() {

	const myref = useRef({});

	const [curr, setCurrClass] = useState({})
	const [market, setMarketInfo]  = useState({})


	const [coinInfo, setCoinInfo] = useRecoilState(market_coin_selected);
	const [tabInfo, setTabInfo] = useRecoilState(market_tab_selected);



	var hogaBuy = useRef([])
	var hogaSell = useRef([])
	var marketInfo = useRef({})

	useEffect(() => {
		console.log("+++++++++++++ Exchange +++++++++++++++++");
		_get_market_info()
	}, [])


	const _get_market_info = async () => {
		var param = {coinNo:20};  // 10 : KRW
		const rsp = await request.post(BASE_PREFIX+'/exchange/getMarket', toFormData(param)).then(response => response.data)
		console.log("_get_coin_info---:", rsp);
		try {
			setMarketInfo(rsp);

		} catch (error){
			console.log("failed to parse:", error);
			return null;
		}
	}


	return (
		<div className="main-content-body">
			<ExchangeTop />
			{/* <div className="go_main">
        		<a className="btn green" href="#/">Coin List</a>
      		</div>
			<ul className="m_ex_tab">
				<li className="item_m on" onclick="tabMenuMobile(event,'exchange')">Exchange</li>
				<li className="item_m" onclick="tabMenuMobile(event,'price')">Price</li>
				<li className="item_m" onclick="tabMenuMobile(event,'chart1')">Chart</li>
				<li className="item_m" onclick="tabMenuMobile(event,'history1')">Trade History</li>
				<li className="item_m" onclick="tabMenuMobile(event,'order')">Order History</li>
      		</ul>   */}
			<div className="wrap_bg">
					<ExchangeMarket market={market}/>
					<div className="ex_center exchange">
						<div className="graph tab-content" id="chart1">
							<iframe src="https://holdport.com/chart/chart_view/chart.html?coin=GDC&pay=KRW&theme=day" title="TradeChart" width="100%" height={475} frameBorder={0} />
						</div>      
					</div>
					<div className="ex_bottom">
					<AskingTable />
					<ExchangeBuySell/>
					<ExchangeOrder />
				</div> 
			</div>
		</div>

	);
}

function ExchangeRoot() {
	return (
		<RecoilRoot>
			<Exchange/>
		</RecoilRoot>
	);
}

let domContainer = document.querySelector('#like_exchange_content');
ReactDOM.render(<ExchangeRoot/>, domContainer);

