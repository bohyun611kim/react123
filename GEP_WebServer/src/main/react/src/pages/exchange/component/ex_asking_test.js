
import { useEffect, useRef, useState, createContext, useContext } from "react";
import { BASE_PREFIX } from "../../module/myconstants";
import request from "../../module/services/request.service";
import { is_empty } from "../../module/utils/misc.util";


function AskingSell({item}) {
	//console.log("AskingSell:", item);
	return (
		<tr>
			<td className="alCenter" />
			<td className="bar-down">
				<a> <div style={{width: '0%'}}>-</div> <p>{item.hogaQty}</p> </a>
			</td>
			<td className="bgblue red" > {item.ordPrc} </td>
			<td className="bgblue red">
				<span > B </span>
				<span > C </span>
				<span > D </span>
			</td>
		</tr>
	);
}

function AskingBuy({item, info}) {

	console.log("AskingBuy:info", info);
	return (
		<tr>
			<td className="bgred blue"> {item.ordPrc} </td>
			<td className="bgred blue">
				<span />
				<span />
				<span />
			</td>
			<td className="bar-up">
				<a> <div style={{width: '0%'}}>+</div> <p>{item.hogaQty}</p> </a>
			</td>
			<td className="alCenter" />
		</tr>
	);
}

export default function AskingTable() {


	var empty_ask = ['','','','','','','','',''];

	const myref = useRef({});
	const [buyHoga, setBuyHoga] = useState([])
	const [sellHoga, setSellHoga] = useState([])
	const mycontext = useRef({})

	useEffect(()=>{
		myref.current.drawAsking = drawAsking;
		myref.current.drawHogaData = drawHogaData;
		window.askingTable = myref.current;
	}, [])

	const drawHogaData = (data) => {
		console.log("AskingTable::drawHogaData:", data);
	}

	const drawAsking = (data) => {
		mycontext.data = data;
		console.log("AskingTable::drawAsking:", data);
		var buyList = [];
		var sellList = [];
		var buycnt = empty_ask.length - data.buy.length;
		var sellcnt = empty_ask.length - data.sell.length;
		var i = 0;
		buyList = buyList.concat(data.buy);
		for (i = 0 ; i < buycnt ; i++) {
			buyList.push({})
		}
		setBuyHoga(buyList);
		/* --------------------------- */
		var sorted = data.sell.sort((a, b) => {
			if (a.ordPrc < b.ordPrc) return 1;
			if (a.ordPrc > b.ordPrc) return -1;
			if (a.ordPrc == b.ordPrc) return 0;
		})

		for (i = 0 ; i < sellcnt ; i++) {
			sellList.push({})
		}
		sellList = sellList.concat(sorted);

		setSellHoga(sellList);
	}


	//console.log(">>> sellList:", sellHoga);
	//console.log(">>> buyList:", buyHoga);

	return (
		<div className="table-market">
			<table>
			<colgroup>
				<col style={{width: '40px'}} />
				<col style={{width: '110px'}} />
				<col />
				<col style={{width: '60px'}} />
				<col style={{width: '110px'}}/>
				<col style={{width: '40px'}} />
			</colgroup>
			<thead>
				<tr>
				<th colSpan={2}>????????????</th>
				<th colSpan={2}>??????</th>
				<th colSpan={2}>????????????</th>
				</tr>
			</thead>
			<tbody className="list1">
				<tr>
				<td className="alCenter" />
				<td className="bar-down">
					<a>
					<div style={{width: '0%'}}>-</div>
					<p />
					</a>
				</td>
				<td className="bgblue red" />
				<td className="bgblue red">
					<span />
					<span />
					<span />
				</td>
				<td colSpan={2} rowSpan={10}>
					{/*????????? ???????????? ?????? ?????? ??? ?????? */}
					<div className="inner-data-wrap dl-wrap">
					<div className="inner-data-group">
						<dl>
						<dt>?????????(24H)</dt>
						<dd><span className="tradeVol">2.004</span></dd>
						<dd><span className="gray volUnit">BTC</span></dd>
						</dl>
						<dl>
						<dt>????????????(24H)</dt>
						<dd><span id="tradeAmt">20</span></dd>
						<dd><span className="gray" id="amtUnit">_L_0034</span></dd>
						</dl>
					</div>
					<div className="inner-data-group">
						<dl>
						<dt>52??? ??????</dt>
						<dd className id="week52high">20,000,000</dd>
						<dd className="gray">(2021-05-14)</dd>
						</dl>
						<dl>
						<dt>52??? ??????</dt>
						<dd className="blue" id="week52low">9,993,000</dd>
						<dd className="gray">(2021-05-03)</dd>
						</dl>
					</div>
					<div className="inner-data-group">
						<dl>
						<dt>????????????</dt>
						<dd id="prevClosePrc">20,000,000</dd>
						</dl>
						<dl>
						<dt>????????????</dt>
						<dd className id="todayHigh">20,000,000</dd>
						<dd className>0.00%</dd>
						</dl>
						<dl>
						<dt>????????????</dt>
						<dd className id="todayLow">20,000,000</dd>
						<dd className>0.00%</dd>
						</dl>
					</div>
					</div>
					{/*????????? ???????????? ?????? ?????? ??? ???*/}
				</td>
				</tr>
				
				{ sellHoga.map((value, index) => {
					return <AskingSell key={index} item={value} info={mycontext.data}/>
				})}

			</tbody>
			<tbody className="list2">
				<tr>
				<td colSpan={2} rowSpan={10}>
					{/* ?????????, ????????? : Start */}
					<div className="inner-data-wrap">
					<table>
						<colgroup>
						<col style={{width: '65px'}} />
						<col />
						</colgroup>
						<thead>
						<tr>
							<th>?????????</th>
							<th>?????????</th>
						</tr>
						</thead>
						<tbody id="realContract">
						<tr>
							<td style={{textOverflow: 'ellipsis', whiteSpace: 'nowrap', overflow: 'hidden'}} title="25,000,000">25,000,000</td>
							<td className="red" style={{letterSpacing: '0px', textOverflow: 'ellipsis', whiteSpace: 'nowrap', overflow: 'hidden'}} title="0.10000000">0.10000000</td>
						</tr>
						<tr>
							<td style={{textOverflow: 'ellipsis', whiteSpace: 'nowrap', overflow: 'hidden'}} title="AM">AM</td>
							<td className="red" style={{letterSpacing: '0px', textOverflow: 'ellipsis', whiteSpace: 'nowrap', overflow: 'hidden'}} title={3.00000000}>3.00000000</td>
						</tr>
						<tr>
							<td style={{textOverflow: 'ellipsis', whiteSpace: 'nowrap', overflow: 'hidden'}} title="AM">AM</td>
							<td className="red" style={{letterSpacing: '0px', textOverflow: 'ellipsis', whiteSpace: 'nowrap', overflow: 'hidden'}} title={2.00000000}>2.00000000</td>
						</tr>
						<tr>
							<td style={{textOverflow: 'ellipsis', whiteSpace: 'nowrap', overflow: 'hidden'}} title="AM">AM</td>
							<td className="red" style={{letterSpacing: '0px', textOverflow: 'ellipsis', whiteSpace: 'nowrap', overflow: 'hidden'}} title={2.00000000}>2.00000000</td>
						</tr>
						</tbody>
					</table>
					</div>
					{/* //?????????, ????????? : End */}
				</td>
				<td className="bgred blue" style={{cursor: 'pointer'}}>2,000</td>
				<td className="bgred blue">
					<span>-</span>
					<span>99.99</span>
					<span>%</span>
				</td>
				<td className="bar-up" style={{cursor: 'pointer'}}>
					<a>
					<div style={{width: '10%'}}>-</div>
					<p>10.00000000</p>
					</a>
				</td>
				<td className="alCenter" id="h2000" />
				</tr>

				{ buyHoga.map((value, index) => {
					return <AskingBuy key={index} item={value} info={mycontext.data}/>
				})}

			</tbody>
			</table>
			{/* ?????? + ????????? : End */}
			<div className="bottom-group">
			<div className="btn-group">
				<button className="blue" id="canSellAll">??????<br />????????????
				</button></div>
			<div className="total-price-group">
				<span className="total-price" id="sellTotal">1.99600000</span>
				<span>
				<p>??????????????????</p>
				<p>(<span id="symbol">BTC</span>)</p>
				</span>
				<span className="total-price" id="buyTotal">110.00000000</span>
			</div>
			<div className="btn-group">
				<button className="red" id="canBuyAll">??????<br />????????????
				</button></div>
			</div>
			{/* //?????? + ????????? : End */}
		</div>


	);
}


let domContainer = document.querySelector('#like_asking_table');
ReactDOM.render( <AskingTable/> , domContainer);



