
import { useEffect, useRef, useState, createContext, useContext } from "react";


const drawAsking = (data) => {
	console.log("AskingTable::drawAsking:", data);
	var asking_row = $("#" + op_id);
	console.log("blink_asking", asking_row);
}

export default function AskingTable() {


	var empty_ask = ['','','','','','','','',''];

	const myref = useRef({});
	const [buyHoga, setBuyHoga] = useState([])
	const [sellHoga, setSellHoga] = useState([])

	useEffect(()=>{
	}, [])

	const drawAsking = (op_id) => {
		var asking_row = $("#" + op_id);
		console.log("blink_asking", asking_row);
	}


	//console.log(">>> sellList:", sellHoga);
	//console.log(">>> buyList:", buyHoga);

	return (
		<div className="ex_right">
			<div className="askingtable tab-content" id="price">
				<div className="table-market">
					<table>
					<colgroup>
						<col />
						<col style={{width: '70px'}} />
						<col />
						<col style={{width: '50px'}} />
						<col />
						<col style={{width: '50px'}} />
					</colgroup>
					<thead>
						<tr>
						<th colSpan={2}>Price (BTC)</th>
						<th colSpan={2}>Amount (ETH)</th>
						<th colSpan={2}>History</th>
						</tr>
					</thead>
					<tbody className="list1">
						<tr>
						<td className="bar-down">
							<a href="#">
							<p className="green">13,533.534</p>
							<p>13,533.53422</p>
							</a>
						</td>
						<td className="red">
							<span>+</span>
							<span>900</span>
							<span>%</span> 
						</td>
						<td className="bggreen">2,305,250</td>
						<td className="alCenter">1</td>
						<td colSpan={2} rowSpan={1} className="bggray-table">
							{/*거래량 거래대금 최고 최저 등 시작 */}
							<div className="inner-data-wrap dl-wrap">
							<div className="inner-data-group">
								<dl>
								<dt>Change(24h)</dt>
								<dd>Volume(24h)</dd>
								</dl>  
							</div>
							</div>
							{/*거래량 거래대금 최고 최저 등 끝*/}
						</td>
						</tr>
						<tr>
						<td className="bar-down">
							<a href="#">
							<p className="green">13,533.534</p>
							<p>13,533.534</p>
							</a>
						</td>
						<td className="green">
							<span>+</span>
							<span>100</span>
							<span>%</span> 
						</td>
						<td>2,305,250</td>
						<td className="alCenter">1</td>
						<td colSpan={2} rowSpan={1}>
							{/*거래량 거래대금 최고 최저 등 시작 */}
							<div className="inner-data-wrap dl-wrap dl-data">
							<div className="inner-data-group">
								<dl>
								<dt>0.03785</dt>
								<dd className="green">0.03785</dd>
								</dl>  
							</div>
							</div>
							{/*거래량 거래대금 최고 최저 등 끝*/}
						</td>
						</tr>
						<tr>
						<td className="bar-down">
							<a href="#">
							<p className="green">13,533.534</p>
							<p>13,533.534</p>
							</a>
						</td>
						<td className="green">
							<span>+</span>
							<span>100</span>
							<span>%</span> 
						</td>
						<td>2,305,250</td>
						<td className="alCenter">1</td>
						<td colSpan={2} rowSpan={1}>
							{/*거래량 거래대금 최고 최저 등 시작 */}
							<div className="inner-data-wrap dl-wrap dl-data">
							<div className="inner-data-group">
								<dl>
								<dt>0.03785</dt>
								<dd className="green">0.03785</dd>
								</dl>  
							</div>
							</div>
							{/*거래량 거래대금 최고 최저 등 끝*/}
						</td>
						</tr>
						<tr>
						<td className="bar-down">
							<a href="#">
							<p className="green">13,533.534</p>
							<p>13,533.534</p>
							</a>
						</td>
						<td className="green">
							<span>+</span>
							<span>100</span>
							<span>%</span> 
						</td>
						<td>2,305,250</td>
						<td className="alCenter">1</td>
						<td colSpan={2} rowSpan={1}>
							{/*거래량 거래대금 최고 최저 등 시작 */}
							<div className="inner-data-wrap dl-wrap dl-data">
							<div className="inner-data-group">
								<dl>
								<dt>0.03785</dt>
								<dd className="green">0.03785</dd>
								</dl>  
							</div>
							</div>
							{/*거래량 거래대금 최고 최저 등 끝*/}
						</td>
						</tr>
						<tr>
						<td className="bar-down">
							<a href="#">
							<p className="green">13,533.534</p>
							<p>13,533.534</p>
							</a>
						</td>
						<td className="green">
							<span>+</span>
							<span>100</span>
							<span>%</span> 
						</td>
						<td>2,305,250</td>
						<td className="alCenter">1</td>
						<td colSpan={2} rowSpan={1}>
							{/*거래량 거래대금 최고 최저 등 시작 */}
							<div className="inner-data-wrap dl-wrap dl-data">
							<div className="inner-data-group">
								<dl>
								<dt>0.03785</dt>
								<dd className="green">0.03785</dd>
								</dl>  
							</div>
							</div>
							{/*거래량 거래대금 최고 최저 등 끝*/}
						</td>
						</tr>
						<tr>
						<td className="bar-down">
							<a href="#">
							<p className="green">13,533.534</p>
							<p>13,533.534</p>
							</a>
						</td>
						<td className="green">
							<span>+</span>
							<span>100</span>
							<span>%</span> 
						</td>
						<td>2,305,250</td>
						<td className="alCenter">1</td>
						<td colSpan={2} rowSpan={1}>
							{/*거래량 거래대금 최고 최저 등 시작 */}
							<div className="inner-data-wrap dl-wrap dl-data">
							<div className="inner-data-group">
								<dl>
								<dt>0.03785</dt>
								<dd className="green">0.03785</dd>
								</dl>  
							</div>
							</div>
							{/*거래량 거래대금 최고 최저 등 끝*/}
						</td>
						</tr>
						<tr>
						<td className="bar-down">
							<a href="#">
							<p className="green">13,533.534</p>
							<p>13,533.534</p>
							</a>
						</td>
						<td className="red">
							<span>+</span>
							<span>100</span>
							<span>%</span> 
						</td>
						<td>2,305,250</td>
						<td className="alCenter">1</td>
						<td colSpan={2} rowSpan={1}>
							{/*거래량 거래대금 최고 최저 등 시작 */}
							<div className="inner-data-wrap dl-wrap dl-data">
							<div className="inner-data-group">
								<dl>
								<dt>0.03785</dt>
								<dd className="green">0.03785</dd>
								</dl>  
							</div>
							</div>
							{/*거래량 거래대금 최고 최저 등 끝*/}
						</td>
						</tr>
						<tr>
						<td className="bar-down">
							<a href="#">
							<p className="green">13,533.534</p>
							<p>13,533.534</p>
							</a>
						</td>
						<td className="red">
							<span>+</span>
							<span>100</span>
							<span>%</span> 
						</td>
						<td>2,305,250</td>
						<td className="alCenter">1</td>
						<td colSpan={2} rowSpan={1}>
							{/*거래량 거래대금 최고 최저 등 시작 */}
							<div className="inner-data-wrap dl-wrap dl-data">
							<div className="inner-data-group">
								<dl>
								<dt>0.03785</dt>
								<dd className="green">0.03785</dd>
								</dl>  
							</div>
							</div>
							{/*거래량 거래대금 최고 최저 등 끝*/}
						</td>
						</tr>
						<tr>
						<td className="bar-down">
							<a href="#">
							<p className="green">13,533.534</p>
							<p>13,533.534</p>
							</a>
						</td>
						<td className="red">
							<span>+</span>
							<span>100</span>
							<span>%</span> 
						</td>
						<td>2,305,250</td>
						<td className="alCenter">1</td>
						<td colSpan={2} rowSpan={1}>
							{/*거래량 거래대금 최고 최저 등 시작 */}
							<div className="inner-data-wrap dl-wrap dl-data">
							<div className="inner-data-group">
								<dl>
								<dt>0.03785</dt>
								<dd className="green">0.03785</dd>
								</dl>  
							</div>
							</div>
							{/*거래량 거래대금 최고 최저 등 끝*/}
						</td>
						</tr>
						<tr>
						<td className="bar-down">
							<a href="#">
							<p className="green">13,533.534</p>
							<p>13,533.534</p>
							</a>
						</td>
						<td className="red">
							<span>+</span>
							<span>100</span>
							<span>%</span> 
						</td>
						<td>2,305,250</td>
						<td className="alCenter">1</td>
						<td colSpan={2} rowSpan={1}>
							{/*거래량 거래대금 최고 최저 등 시작 */}
							<div className="inner-data-wrap dl-wrap dl-data">
							<div className="inner-data-group">
								<dl>
								<dt>0.03785</dt>
								<dd className="green">0.03785</dd>
								</dl>  
							</div>
							</div>
							{/*거래량 거래대금 최고 최저 등 끝*/}
						</td>
						</tr>
					</tbody>
					<tbody className="list1">
						<tr>
						<td className="bar-down">
							<a href="#">
							<p className="green">13,533.534</p>
							<p>13,533.53422</p>
							</a>
						</td>
						<td className="red">
							<span>+</span>
							<span>900</span>
							<span>%</span> 
						</td>
						<td>2,305,250</td>
						<td className="alCenter">1</td>
						<td colSpan={2} rowSpan={10}>
							{/*거래량 거래대금 최고 최저 등 시작 */}
							<div className="inner-data-wrap dl-wrap">
							<div className="inner-data-group">
								<dl>
								<dt>거래량</dt>
								<dd>
									<span>
									10,032,519.18
									</span>
								</dd>
								<dd>
									<span className="gray">BTC</span>
								</dd>
								</dl>
								<dl>
								<dt>거래대금</dt>
								<dd>
									<span>725,176</span>
								</dd>
								<dd>
									<span className="gray">백만원</span>
								</dd>
								</dl>
							</div>
							<div className="inner-data-group">
								<dl>
								<dt>52주 최고</dt>
								<dd className="red">24,967,000</dd>
								<dd className="gray">(2017-12-07)</dd>
								</dl>
								<dl>
								<dt>52주 최저</dt>
								<dd className="green">4,175,000</dd>
								<dd className="gray">(2017-12-07)</dd>
								</dl>
							</div>
							<div className="inner-data-group">
								<dl>
								<dt>전일종가</dt>
								<dd>19,870,000</dd>
								</dl>
								<dl>
								<dt>당일고가</dt>
								<dd className="red">19,450,000</dd>
								<dd className="red">+4.61%</dd>
								</dl>
								<dl>
								<dt>당일저가</dt>
								<dd className="green">18,390,000</dd>
								<dd className="green">-2.68%</dd>
								</dl>
							</div>
							</div>
							{/*거래량 거래대금 최고 최저 등 끝*/}
						</td>
						</tr>
						<tr>
						<td className="bar-down">
							<a href="#">
							<p className="green">13,533.534</p>
							<p>13,533.534</p>
							</a>
						</td>
						<td className="green">
							<span>+</span>
							<span>100</span>
							<span>%</span> 
						</td>
						<td>2,305,250</td>
						<td className="alCenter">1</td>
						</tr>
						<tr>
						<td className="bar-down">
							<a href="#">
							<p className="green">13,533.534</p>
							<p>13,533.534</p>
							</a>
						</td>
						<td className="green">
							<span>+</span>
							<span>100</span>
							<span>%</span> 
						</td>
						<td>2,305,250</td>
						<td className="alCenter">1</td>
						</tr>
						<tr>
						<td className="bar-down">
							<a href="#">
							<p className="green">13,533.534</p>
							<p>13,533.534</p>
							</a>
						</td>
						<td className="green">
							<span>+</span>
							<span>100</span>
							<span>%</span> 
						</td>
						<td>2,305,250</td>
						<td className="alCenter">1</td>
						</tr>
						<tr>
						<td className="bar-down">
							<a href="#">
							<p className="green">13,533.534</p>
							<p>13,533.534</p>
							</a>
						</td>
						<td className="green">
							<span>+</span>
							<span>100</span>
							<span>%</span> 
						</td>
						<td>2,305,250</td>
						<td className="alCenter">1</td>
						</tr>
						<tr>
						<td className="bar-down">
							<a href="#">
							<p className="green">13,533.534</p>
							<p>13,533.534</p>
							</a>
						</td>
						<td className="green">
							<span>+</span>
							<span>100</span>
							<span>%</span> 
						</td>
						<td>2,305,250</td>
						<td className="alCenter">1</td>
						</tr>
						<tr>
						<td className="bar-down">
							<a href="#">
							<p className="green">13,533.534</p>
							<p>13,533.534</p>
							</a>
						</td>
						<td className="red">
							<span>+</span>
							<span>100</span>
							<span>%</span> 
						</td>
						<td>2,305,250</td>
						<td className="alCenter">1</td>
						</tr>
						<tr>
						<td className="bar-down">
							<a href="#">
							<p className="green">13,533.534</p>
							<p>13,533.534</p>
							</a>
						</td>
						<td className="red">
							<span>+</span>
							<span>100</span>
							<span>%</span> 
						</td>
						<td>2,305,250</td>
						<td className="alCenter">1</td>
						</tr>
						<tr>
						<td className="bar-down">
							<a href="#">
							<p className="green">13,533.534</p>
							<p>13,533.534</p>
							</a>
						</td>
						<td className="red">
							<span>+</span>
							<span>100</span>
							<span>%</span> 
						</td>
						<td>2,305,250</td>
						<td className="alCenter">1</td>
						</tr>
						<tr>
						<td className="bar-down">
							<a href="#">
							<p className="green">13,533.534</p>
							<p>13,533.534</p>
							</a>
						</td>
						<td className="red">
							<span>+</span>
							<span>100</span>
							<span>%</span> 
						</td>
						<td>2,305,250</td>
						<td className="alCenter">1</td>
						</tr>
					</tbody>
					</table>
				</div>
			</div>
		</div> 
	);
}



