
function AskingTable() {
	return (
		<div className="askingtable" style={{}}>
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
					<th colSpan={2}>매도잔량</th>
					<th colSpan={2}>호가</th>
					<th colSpan={2}>매수잔량</th>
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
					  {/*거래량 거래대금 최고 최저 등 시작 */}
					  <div className="inner-data-wrap dl-wrap">
						<div className="inner-data-group">
						  <dl>
							<dt>거래량(24H)</dt>
							<dd><span className="tradeVol">2.004</span></dd>
							<dd><span className="gray volUnit">BTC</span></dd>
						  </dl>
						  <dl>
							<dt>거래대금(24H)</dt>
							<dd><span id="tradeAmt">20</span></dd>
							<dd><span className="gray" id="amtUnit">_L_0034</span></dd>
						  </dl>
						</div>
						<div className="inner-data-group">
						  <dl>
							<dt>52주 최고</dt>
							<dd className id="week52high">20,000,000</dd>
							<dd className="gray">(2021-05-14)</dd>
						  </dl>
						  <dl>
							<dt>52주 최저</dt>
							<dd className="blue" id="week52low">9,993,000</dd>
							<dd className="gray">(2021-05-03)</dd>
						  </dl>
						</div>
						<div className="inner-data-group">
						  <dl>
							<dt>전일종가</dt>
							<dd id="prevClosePrc">20,000,000</dd>
						  </dl>
						  <dl>
							<dt>당일고가</dt>
							<dd className id="todayHigh">20,000,000</dd>
							<dd className>0.00%</dd>
						  </dl>
						  <dl>
							<dt>당일저가</dt>
							<dd className id="todayLow">20,000,000</dd>
							<dd className>0.00%</dd>
						  </dl>
						</div>
					  </div>
					  {/*거래량 거래대금 최고 최저 등 끝*/}
					</td>
				  </tr>
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
				  </tr>
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
				  </tr>
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
				  </tr>
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
				  </tr>
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
				  </tr>
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
				  </tr>
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
				  </tr>
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
				  </tr>
				  <tr>
					<td className="alCenter" id="h20000000" />
					<td className="bar-down" style={{cursor: 'pointer'}}>
					  <a>
						<div style={{width: '2%'}}>-</div>
						<p>1.99600000</p>
					  </a>
					</td>
					<td className="bgblue " style={{cursor: 'pointer'}}>20,000,000</td>
					<td className="bgblue ">
					  <span />
					  <span>0</span>
					  <span>%</span>
					</td>
				  </tr>
				</tbody>
				<tbody className="list2">
				  <tr>
					<td colSpan={2} rowSpan={10}>
					  {/* 체결가, 체결량 : Start */}
					  <div className="inner-data-wrap">
						<table>
						  <colgroup>
							<col style={{width: '65px'}} />
							<col />
						  </colgroup>
						  <thead>
							<tr>
							  <th>체결가</th>
							  <th>체결량</th>
							</tr>
						  </thead>
						  <tbody id="realContract">
							<tr>
							  <td style={{textOverflow: 'ellipsis', whiteSpace: 'nowrap', overflow: 'hidden'}} title="20,000,000">20,000,000</td>
							  <td className="red" style={{letterSpacing: '0px', textOverflow: 'ellipsis', whiteSpace: 'nowrap', overflow: 'hidden'}} title="0.00100000">0.00100000</td>
							</tr>
							<tr>
							  <td style={{textOverflow: 'ellipsis', whiteSpace: 'nowrap', overflow: 'hidden'}} title="20,000,000">20,000,000</td>
							  <td className="red" style={{letterSpacing: '0px', textOverflow: 'ellipsis', whiteSpace: 'nowrap', overflow: 'hidden'}} title="0.00100000">0.00100000</td>
							</tr>
							<tr>
							  <td style={{textOverflow: 'ellipsis', whiteSpace: 'nowrap', overflow: 'hidden'}} title="20,000,000">20,000,000</td>
							  <td className="red" style={{letterSpacing: '0px', textOverflow: 'ellipsis', whiteSpace: 'nowrap', overflow: 'hidden'}} title="0.00100000">0.00100000</td>
							</tr>
							<tr>
							  <td style={{textOverflow: 'ellipsis', whiteSpace: 'nowrap', overflow: 'hidden'}} title="20,000,000">20,000,000</td>
							  <td className="red" style={{letterSpacing: '0px', textOverflow: 'ellipsis', whiteSpace: 'nowrap', overflow: 'hidden'}} title="0.00100000">0.00100000</td>
							</tr>
							<tr>
							  <td style={{textOverflow: 'ellipsis', whiteSpace: 'nowrap', overflow: 'hidden'}} title="20,000,000">20,000,000</td>
							  <td className="blue" style={{letterSpacing: '0px', textOverflow: 'ellipsis', whiteSpace: 'nowrap', overflow: 'hidden'}} title={1.00000000}>1.00000000</td>
							</tr>
							<tr>
							  <td style={{textOverflow: 'ellipsis', whiteSpace: 'nowrap', overflow: 'hidden'}} title="10,000,000">10,000,000</td>
							  <td className="red" style={{letterSpacing: '0px', textOverflow: 'ellipsis', whiteSpace: 'nowrap', overflow: 'hidden'}} title={1.00000000}>1.00000000</td>
							</tr>
							<tr>
							  <td style={{textOverflow: 'ellipsis', whiteSpace: 'nowrap', overflow: 'hidden'}} title="10,000,000">10,000,000</td>
							  <td className="red" style={{letterSpacing: '0px', textOverflow: 'ellipsis', whiteSpace: 'nowrap', overflow: 'hidden'}} title={1.00000000}>1.00000000</td>
							</tr>
							<tr>
							  <td style={{textOverflow: 'ellipsis', whiteSpace: 'nowrap', overflow: 'hidden'}} title="9,993,000">9,993,000</td>
							  <td className="blue" style={{letterSpacing: '0px', textOverflow: 'ellipsis', whiteSpace: 'nowrap', overflow: 'hidden'}} title={1.00000000}>1.00000000</td>
							</tr>
							<tr>
							  <td style={{textOverflow: 'ellipsis', whiteSpace: 'nowrap', overflow: 'hidden'}} title="10,000,000">10,000,000</td>
							  <td className="red" style={{letterSpacing: '0px', textOverflow: 'ellipsis', whiteSpace: 'nowrap', overflow: 'hidden'}} title="0.10000000">0.10000000</td>
							</tr>
							<tr>
							  <td style={{textOverflow: 'ellipsis', whiteSpace: 'nowrap', overflow: 'hidden'}} title="10,000,000">10,000,000</td>
							  <td className="red" style={{letterSpacing: '0px', textOverflow: 'ellipsis', whiteSpace: 'nowrap', overflow: 'hidden'}} title="0.10000000">0.10000000</td>
							</tr>
							<tr>
							  <td style={{textOverflow: 'ellipsis', whiteSpace: 'nowrap', overflow: 'hidden'}} title="10,000,000">10,000,000</td>
							  <td className="red" style={{letterSpacing: '0px', textOverflow: 'ellipsis', whiteSpace: 'nowrap', overflow: 'hidden'}} title="0.10000000">0.10000000</td>
							</tr>
							<tr>
							  <td style={{textOverflow: 'ellipsis', whiteSpace: 'nowrap', overflow: 'hidden'}} title="10,000,000">10,000,000</td>
							  <td className="red" style={{letterSpacing: '0px', textOverflow: 'ellipsis', whiteSpace: 'nowrap', overflow: 'hidden'}} title="0.10000000">0.10000000</td>
							</tr>
							<tr>
							  <td style={{textOverflow: 'ellipsis', whiteSpace: 'nowrap', overflow: 'hidden'}} title="10,000,000">10,000,000</td>
							  <td className="red" style={{letterSpacing: '0px', textOverflow: 'ellipsis', whiteSpace: 'nowrap', overflow: 'hidden'}} title="0.10000000">0.10000000</td>
							</tr>
							<tr>
							  <td style={{textOverflow: 'ellipsis', whiteSpace: 'nowrap', overflow: 'hidden'}} title="10,000,000">10,000,000</td>
							  <td className="red" style={{letterSpacing: '0px', textOverflow: 'ellipsis', whiteSpace: 'nowrap', overflow: 'hidden'}} title="0.10000000">0.10000000</td>
							</tr>
							<tr>
							  <td style={{textOverflow: 'ellipsis', whiteSpace: 'nowrap', overflow: 'hidden'}} title="10,000,000">10,000,000</td>
							  <td className="red" style={{letterSpacing: '0px', textOverflow: 'ellipsis', whiteSpace: 'nowrap', overflow: 'hidden'}} title="0.10000000">0.10000000</td>
							</tr>
							<tr>
							  <td style={{textOverflow: 'ellipsis', whiteSpace: 'nowrap', overflow: 'hidden'}} title="15,000,000">15,000,000</td>
							  <td className="red" style={{letterSpacing: '0px', textOverflow: 'ellipsis', whiteSpace: 'nowrap', overflow: 'hidden'}} title="0.10000000">0.10000000</td>
							</tr>
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
					  {/* //체결가, 체결량 : End */}
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
				  <tr>
					<td className="bgred blue" style={{cursor: 'pointer'}}>1,000</td>
					<td className="bgred blue">
					  <span>-</span>
					  <span>99.99</span>
					  <span>%</span>
					</td>
					<td className="bar-up" style={{cursor: 'pointer'}}>
					  <a>
						<div style={{width: '100%'}} />
						<p>100.00000000</p>
					  </a>
					</td>
					<td className="alCenter" id="h1000" />
				  </tr>
				  <tr>
					<td className="bgred blue" />
					<td className="bgred blue">
					  <span />
					  <span />
					  <span />
					</td>
					<td className="bar-up">
					  <a>
						<div style={{width: '0%'}} />
						<p />
					  </a>
					</td>
					<td className="alCenter" />
				  </tr>
				  <tr>
					<td className="bgred blue" />
					<td className="bgred blue">
					  <span />
					  <span />
					  <span />
					</td>
					<td className="bar-up">
					  <a>
						<div style={{width: '0%'}} />
						<p />
					  </a>
					</td>
					<td className="alCenter" />
				  </tr>
				  <tr>
					<td className="bgred blue" />
					<td className="bgred blue">
					  <span />
					  <span />
					  <span />
					</td>
					<td className="bar-up">
					  <a>
						<div style={{width: '0%'}} />
						<p />
					  </a>
					</td>
					<td className="alCenter" />
				  </tr>
				  <tr>
					<td className="bgred blue" />
					<td className="bgred blue">
					  <span />
					  <span />
					  <span />
					</td>
					<td className="bar-up">
					  <a>
						<div style={{width: '0%'}} />
						<p />
					  </a>
					</td>
					<td className="alCenter" />
				  </tr>
				  <tr>
					<td className="bgred blue" />
					<td className="bgred blue">
					  <span />
					  <span />
					  <span />
					</td>
					<td className="bar-up">
					  <a>
						<div style={{width: '0%'}} />
						<p />
					  </a>
					</td>
					<td className="alCenter" />
				  </tr>
				  <tr>
					<td className="bgred blue" />
					<td className="bgred blue">
					  <span />
					  <span />
					  <span />
					</td>
					<td className="bar-up">
					  <a>
						<div style={{width: '0%'}} />
						<p />
					  </a>
					</td>
					<td className="alCenter" />
				  </tr>
				  <tr>
					<td className="bgred blue" />
					<td className="bgred blue">
					  <span />
					  <span />
					  <span />
					</td>
					<td className="bar-up">
					  <a>
						<div style={{width: '0%'}} />
						<p />
					  </a>
					</td>
					<td className="alCenter" />
				  </tr>
				  <tr>
					<td className="bgred blue" />
					<td className="bgred blue">
					  <span />
					  <span />
					  <span />
					</td>
					<td className="bar-up">
					  <a>
						<div style={{width: '0%'}} />
						<p />
					  </a>
					</td>
					<td className="alCenter" />
				  </tr>
				</tbody>
			  </table>
			  {/* 버튼 + 총잔량 : End */}
			  <div className="bottom-group">
				<div className="btn-group">
				  <button className="blue" id="canSellAll">일괄<br />매도취소
				  </button></div>
				<div className="total-price-group">
				  <span className="total-price" id="sellTotal">1.99600000</span>
				  <span>
					<p>주문잔량합계</p>
					<p>(<span id="symbol">BTC</span>)</p>
				  </span>
				  <span className="total-price" id="buyTotal">110.00000000</span>
				</div>
				<div className="btn-group">
				  <button className="red" id="canBuyAll">일괄<br />매수취소
				  </button></div>
			  </div>
			  {/* //버튼 + 총잔량 : End */}
			</div>
		  </div>


	);
}

function CharAria() {
	return (
		<div className="chart-area" id="candleChart" style={{height: '472px'}}>
			<iframe id="tradingview_19659" 
			name="tradingview_19659" 
			src="/resources/js/tradingview/charting_library/static/ko-tv-chart.1f5f8727d52f80cf1894.html#symbol=BTCKRW&interval=30&widgetbar=%7B%22details%22%3Afalse%2C%22watchlist%22%3Afalse%2C%22watchlist_settings%22%3A%7B%22default_symbols%22%3A%5B%5D%7D%7D&drawingsAccess=%7B%22type%22%3A%22black%22%2C%22tools%22%3A%5B%7B%22name%22%3A%22Regression%20Trend%22%7D%5D%7D&timeFrames=%5B%7B%22text%22%3A%225y%22%2C%22resolution%22%3A%22W%22%7D%2C%7B%22text%22%3A%221y%22%2C%22resolution%22%3A%22W%22%7D%2C%7B%22text%22%3A%226m%22%2C%22resolution%22%3A%22120%22%7D%2C%7B%22text%22%3A%223m%22%2C%22resolution%22%3A%2260%22%7D%2C%7B%22text%22%3A%221m%22%2C%22resolution%22%3A%2230%22%7D%2C%7B%22text%22%3A%225d%22%2C%22resolution%22%3A%225%22%7D%2C%7B%22text%22%3A%221d%22%2C%22resolution%22%3A%221%22%7D%5D&locale=ko&uid=tradingview_19659&clientId=www.ali-bit.com&userId=coinis&chartsStorageUrl=https%3A%2F%2Fsaveload.tradingview.com&chartsStorageVer=1.1&customCSS=%2Fresources%2Fcss%2Ftradingview%2Ftradingview.css&debug=false&timezone=Asia%2FSeoul" 
			frameBorder={0} allowTransparency="true" scrolling="no" allowFullScreen style={{display: 'block', width: '100%', height: '100%'}} />
			</div>
	);
}

function OrderAria() {
	return (
		<div id="tab-group-2" className="buysell-area ui-tabs ui-corner-all ui-widget ui-widget-content" style={{}}>
			  <ul className="li5 ui-tabs-nav ui-corner-all ui-helper-reset ui-helper-clearfix ui-widget-header" role="tablist">
				<li role="tab" tabIndex={0} className="ui-tabs-tab ui-corner-top ui-state-default ui-tab ui-tabs-active ui-state-active" aria-controls="tabs2-1" aria-labelledby="ui-id-1" aria-selected="true" aria-expanded="true"><a href="#tabs2-1" role="presentation" tabIndex={-1} className="ui-tabs-anchor" id="ui-id-1">지정가</a></li>
				<li role="tab" tabIndex={-1} className="ui-tabs-tab ui-corner-top ui-state-default ui-tab" aria-controls="tabs2-2" aria-labelledby="ui-id-2" aria-selected="false" aria-expanded="false"><a href="#tabs2-2" role="presentation" tabIndex={-1} className="ui-tabs-anchor" id="ui-id-2">시장가</a></li>
				<li role="tab" tabIndex={-1} className="ui-tabs-tab ui-corner-top ui-state-default ui-tab" aria-controls="tabs2-3" aria-labelledby="ui-id-3" aria-selected="false" aria-expanded="false"><a href="#tabs2-3" role="presentation" tabIndex={-1} className="ui-tabs-anchor" id="ui-id-3">자동채굴</a></li>
			  </ul>
			  {/*지정가 매수매도 시작*/}
			  <LimitPrieOrder/>
			  {/*지정가 매수매도 끝*/}
			  {/*시장가 매수매도 시작*/}
			  <MarketPriceOrder/>
			  {/*시장가 매수매도 끝*/}
			</div>


	);
}

function MarketPriceOrder() {
	return (
		<div id="tabs2-2" style={{display: 'none'}} aria-labelledby="ui-id-2" role="tabpanel" className="ui-tabs-panel ui-corner-bottom ui-widget-content" aria-hidden="true">
				{/*매수매도영역 시작*/}
				{/*시장가 매수하기 시작*/}
				<div className="market-buy">
				  <div className="buysell-wrap">
					<table>
					  <tbody data-type="buy">
						<tr>
						  <th>주문가능</th>
						  <td colSpan={3}>
							<div className="readonly">
							  <span className="fromQty">0</span>
							  <span className="amtUnit">KRW</span>
							</div>
						  </td>
						</tr>
						<tr>
						  <th>매수수량</th>
						  <td colSpan={3}>
							<div className="data-input">
							  <input type="text" className="tip-open qty" maxLength={17} data-type="buy" id="mktBuyQty" />
							  <span className="volUnit">BTC</span>
							</div>
							<i className="tip">
							  키보드 상하방향키로 설정 가능
							</i>
						  </td>
						</tr>
						<tr>
						  <th />
						  <td colSpan={3}>
							<div className="buy-percent" data-type="mkt" id="mktBuyPeccent">
							  <div>25%</div>
							  <div>50%</div>
							  <div>75%</div>
							  <div>100%</div>
							</div>
						  </td>
						</tr>
						<tr>
						  <th colSpan={2} className="bold">
							주문총액
						  </th>
						  <td colSpan={2} className="totalorder red">
							<span id="mktBuyTotOrder">0</span>
							<span className="amtUnit">KRW</span>
						  </td>
						</tr>
						<tr>
						  <th colSpan={2} className="min_krw">
							<span className="minAmt">(_L_0010 1,000 KRW)</span>
						  </th>
						  <td colSpan={2} className="fee">
							<span>거래수수료</span>
							<span className="buyFee">0.1</span> %
						  </td>
						</tr>
					  </tbody>
					</table>
					<div className="btn-buy-sell">
					  <a style={{cursor: 'pointer'}}>
						<button onclick="doRegister();">회원가입</button>
					  </a>
					  <a style={{cursor: 'pointer'}}>
						<button className="red" onclick="doLogin();">로그인</button>
					  </a>
					</div>
				  </div>
				</div>
				{/*시장가 매수하기 끝*/}
				{/*시장가 매도하기 시작*/}
				<div className="market-sell">
				  <div className="buysell-wrap">
					<table>
					  <tbody data-type="sell">
						<tr>
						  <th>주문가능</th>
						  <td colSpan={3}>
							<div className="readonly">
							  <span className="toQty">0</span>
							  <span className="volUnit">BTC</span>
							</div>
						  </td>
						</tr>
						<tr>
						  <th>매도수량</th>
						  <td colSpan={3}>
							<div className="data-input">
							  <input type="text" className="tip-open qty" maxLength={17} data-type="sell" id="mktSellQty" />
							  <span className="volUnit">BTC</span>
							</div>
							<i className="tip">
							  키보드 상하방향키로 설정 가능
							</i>
						  </td>
						</tr>
						<tr>
						  <th />
						  <td colSpan={3}>
							<div className="buy-percent" data-type="mkt" id="mktSellPeccent">
							  <div>25%</div>
							  <div>50%</div>
							  <div>75%</div>
							  <div>100%</div>
							</div>
						  </td>
						</tr>
						<tr>
						  <th colSpan={2} className="bold">
							주문총액
						  </th>
						  <td colSpan={2} className="totalorder blue">
							<span id="mktSellTotOrder">0</span>
							<span className="amtUnit">KRW</span>
						  </td>
						</tr>
						<tr>
						  <th colSpan={2} className="min_krw">
							<span className="minAmt">(_L_0010 1,000 KRW)</span>
						  </th>
						  <td colSpan={2} className="fee">
							<span>거래수수료</span>
							<span className="sellFee">0.1</span> %
						  </td>
						</tr>
					  </tbody>
					</table>
					<div className="btn-buy-sell">
					  <a style={{cursor: 'pointer'}}>
						<button onclick="doRegister();">회원가입</button>
					  </a>
					  <a style={{cursor: 'pointer'}}>
						<button className="blue" onclick="doLogin();">로그인</button>
					  </a>
					</div>
				  </div>
				</div>
				{/*시장가 매도 하기 끝*/}
				{/*매수매도영역 끝*/}
			  </div>
	);
}

function LimitPrieOrder() {
	return (
		<div id="tabs2-1" aria-labelledby="ui-id-1" role="tabpanel" className="ui-tabs-panel ui-corner-bottom ui-widget-content" aria-hidden="false">
				{/*매수매도영역 시작*/}
				{/*지정가 매수하기 시작*/}
				<div className="pending-buy">
				  <div className="buysell-wrap">
					<table>
					  <tbody data-type="buy">
						<tr>
						  <th>주문가능</th>
						  <td colSpan={3}>
							<div className="readonly">
							  <span className="fromQty">0</span>
							  <span className="amtUnit">KRW</span>
							</div>
						  </td>
						</tr>
						<tr>
						  <th>매수가격</th>
						  <td colSpan={3}>
							<div className="data-input">
							  <input type="text" className="ordPrc" maxLength={17} style={{textAlign: 'right'}} data-type="buy" />
							  <a style={{cursor: 'pointer'}} className="down" />
							  <a style={{cursor: 'pointer'}} className="up" />
							</div>
						  </td>
						</tr>
						<tr>
						  <th>매수수량</th>
						  <td colSpan={3}>
							<div className="data-input">
							  <input type="text" className="tip-open ordQty qty" maxLength={17} data-type="buy" />
							  <span className="volUnit">BTC</span>
							</div>
							<i className="tip">
							  키보드 상하방향키로 설정 가능
							</i>
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
						  <th colSpan={2} className="bold">
							주문총액
						  </th>
						  <td colSpan={2} className="totalorder red">
							<span>0</span>
							<span className="amtUnit">KRW</span>
						  </td>
						</tr>
						<tr>
						  <th colSpan={2} className="min_krw">
							<span className="minAmt">(_L_0010 1,000 KRW)</span>
						  </th>
						  <td colSpan={2} className="fee">
							<span>거래 수수료</span>
							<span className="buyFee">0.1</span> %
						  </td>
						</tr>
					  </tbody>
					</table>
					<div className="btn-buy-sell">
					  <a style={{cursor: 'pointer'}}>
						<button onclick="doRegister();">회원가입</button>
					  </a>
					  <a style={{cursor: 'pointer'}}>
						<button className="red" onclick="doLogin();">로그인</button>
					  </a>
					</div>
				  </div>
				</div>
				{/*지정가 매수하기 끝*/}
				{/*지정가 매도하기 시작*/}
				<div className="pending-sell">
				  <div className="buysell-wrap">
					<table>
					  <tbody data-type="sell">
						<tr>
						  <th>주문가능</th>
						  <td colSpan={3}>
							<div className="readonly">
							  <span className="toQty">0</span>
							  <span className="volUnit">BTC</span>
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
							  <input type="text" className="tip-open ordQty qty" maxLength={17} data-type="sell" />
							  <span className="volUnit">BTC</span>
							</div>
							<i className="tip">
							  키보드 상하방향키로 설정 가능
							</i>
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
						  <th colSpan={2} className="bold">
							주문총액
						  </th>
						  <td colSpan={2} className="totalorder blue">
							<span>0</span>
							<span className="amtUnit">KRW</span>
						  </td>
						</tr>
						<tr>
						  <th colSpan={2} className="min_krw">
							<span className="minAmt">(_L_0010 1,000 KRW)</span>
						  </th>
						  <td colSpan={2} className="fee">
							<span>거래 수수료</span>
							<span className="sellFee">0.1</span> %
						  </td>
						</tr>
					  </tbody>
					</table>
					<div className="btn-buy-sell">
					  <a style={{cursor: 'pointer'}}>
						<button onclick="doRegister();">회원가입</button>
					  </a>
					  <a style={{cursor: 'pointer'}}>
						<button className="blue" onclick="doLogin();">로그인</button>
					  </a>
					</div>
				  </div>
				</div>
				{/*지정가 매도하기 끝*/}
				{/*매수매도영역 끝*/}
			  </div>
	);
}

function ChartWrap() {
	return (
		<div className="chart-wrap" style={{}}>
			{/* 하단 차트영역 : start */}
			<CharAria/>
			{/*지정가 시장가 매수 매도 영역 시작*/}
			<OrderAria/>
			{/*지정가 시장가 매수 매도 영역 끝*/}
		  </div>
	);
}

export default function ExchangeContent() {
	return (
		<div className="content-item">
		  {/*호가주문테이블 시작*/}
		  <AskingTable/>
		   {/*호가주문 테이블 끝*/}
		   <ChartWrap/>
		  </div>

	);
}


