export default function ExchangeBuySell(){
    return(
        <>
        <div className="ex_sec05 sc-cSHVUG eoTJFH tab-content" id="exchange" style={{display: 'inline-block'}}>
			<div id="tab-group-2" className="buysell-area ui-tabs ui-corner-all ui-widget ui-widget-content">
				<ul className="li3 tab_exchange ui-tabs-nav ui-corner-all ui-helper-reset ui-helper-clearfix ui-widget-header" role="tablist">
					<li role="tab" tabIndex={0} className="ui-tabs-tab ui-corner-top ui-state-default ui-tab ui-tabs-active ui-state-active" aria-controls="tabs2-1" aria-labelledby="ui-id-5" aria-selected="true" aria-expanded="true"><a href="#tabs2-1" id="ui-id-5" role="presentation" tabIndex={-1} className="ui-tabs-anchor">Buy</a></li>
					<li role="tab" tabIndex={-1} className="ui-tabs-tab ui-corner-top ui-state-default ui-tab" aria-controls="tabs2-2" aria-labelledby="ui-id-6" aria-selected="false" aria-expanded="false"><a href="#tabs2-2" id="ui-id-6" role="presentation" tabIndex={-1} className="ui-tabs-anchor">Sell</a></li>
					<li role="tab" tabIndex={-1} className="ui-tabs-tab ui-corner-top ui-state-default ui-tab" aria-controls="tabs2-3" aria-labelledby="ui-id-7" aria-selected="false" aria-expanded="false"><a href="#tabs2-3" id="ui-id-7" role="presentation" tabIndex={-1} className="ui-tabs-anchor">Orders</a></li>
				</ul>
				{/*지정가 매수매도 시작*/}
				<div id="tabs2-1" aria-labelledby="ui-id-5" role="tabpanel" className="ui-tabs-panel ui-corner-bottom ui-widget-content" aria-hidden="false">
					{/*매수매도영역 시작*/}
					{/*지정가 매수하기 시작*/}
					<div className="pending-buy">
					<div className="buysell-wrap">
						{/*<div class="form-title">
                            <span class="red">지정가 매수</span>
                        </div>*/}
						<table>
						<tbody>
							<tr>
							<th>Type</th>
							<td colSpan={3} className="alRight">
								<span className="invest_rdo">                        
								<input name="used" id="used_rd1" type="radio" defaultChecked />
								<label htmlFor="used_rd1">Limit</label>
								<input name="used" id="used_rd2" type="radio" />
								<label htmlFor="used_rd2">Market</label>
								<input name="used" id="used_rd3" type="radio" />
								<label htmlFor="used_rd3">Stop limit</label>
								</span>
							</td>
							</tr>
							<tr>
							<th>Available</th>
							<td colSpan={3}>
								<div className="readonly">
								<span>14 KRW</span>
								<span className="gray">~ </span>
								<span className="gray">0 SGD</span>
								</div>
							</td>
							</tr>
							<tr>
							<th>Limit Price (KRW)</th>
							<td colSpan={3}>
								<div className="data-input">
								<input type="text" defaultValue="0.00021000" />
								<a href="#" className="down" />
								<a href className="up" />
								</div>
							</td>
							</tr>
							<tr>
							<th>Amount (KRW)</th>
							<td colSpan={3}>
								<div className="data-input">
								<input type="text" defaultValue="0.00021000" className="tip-open qty" />
								<span>BTC</span>
								</div>
								<i className="tip">
								키보드 상하방향키로 설정 가능
								</i>
							</td>
							</tr>
							<tr>
							<th />
							<td colSpan={3}>
								<div className="buy-percent">
								<div>25%</div>
								<div className="active">50%</div>
								<div>75%</div>
								<div>100%</div>
								<div>%</div>
								</div>
							</td>
							</tr>
							<tr>
							<th colSpan={2} className="bold">
								Total (KRW)
							</th>
							<td colSpan={2} className="totalorder red">
								<span>0.0000000000</span>
								<span>KRW</span>
							</td>
							</tr>
							<tr>
							<th colSpan={2} className="min_krw">
								Minimum order: 0.0006 KRW
							</th>
							<td colSpan={2} className="fee">
								<span>Fee</span>
								<span>0.1 </span>%
							</td>
							</tr>
						</tbody>
						</table>
						<div className="btn-buy-sell">
						<a href="#">
							<button>Reset</button>
						</a>
						<a href="#">
							<button className="green">Buy</button>
						</a>
						</div>
					</div>
					</div>
					{/*지정가 매수하기 끝*/}
					{/*매수매도영역 끝*/}
				</div>
				{/*지정가 매수매도 끝*/}
				{/*시장가 매수매도 시작*/}
				<div id="tabs2-2" aria-labelledby="ui-id-6" role="tabpanel" className="ui-tabs-panel ui-corner-bottom ui-widget-content" aria-hidden="true" style={{display: 'none'}}>
					{/*지정가 매도하기 시작*/}
					<div className="pending-sell">
					<div className="buysell-wrap">
						{/*<div class="form-title">
                            <span class="green">지정가 매도</span>
                        </div>*/}
						<table>
						<tbody>
							<tr>
							<th>Type</th>
							<td colSpan={3}>
								<div className="readonly">
								<span>123,123,123.12345678</span>
								<span>BTC</span>
								</div>
							</td>
							</tr>
							<tr>
							<th>Available</th>
							<td colSpan={3}>
								<div className="data-input">
								<input type="text" defaultValue="0.00021000" />
								<a href="#" className="down" />
								<a href className="up" />
								</div>
							</td>
							</tr>
							<tr>
							<th>Limit Price (KRW)</th>
							<td colSpan={3}>
								<div className="data-input">
								<input type="text" defaultValue="0.00021000" className="tip-open qty" />
								<span>BTC</span>
								</div>
								<i className="tip">
								키보드 상하방향키로 설정 가능
								</i>
							</td>
							</tr>
							<tr>
							<th />
							<td colSpan={3}>
								<div className="buy-percent">
								<div>25%</div>
								<div className="active">50%</div>
								<div>75%</div>
								<div>100%</div>
								<div>%</div>
								</div>
							</td>
							</tr>
							<tr>
							<th colSpan={2} className="bold">
								Total (KRW)
							</th>
							<td colSpan={2} className="totalorder green">
								<span>0.0000000000</span>
								<span>KRW</span>
							</td>
							</tr>
							<tr>
							<th colSpan={2} className="min_krw">
								Minimum order: 0.0006 KRW
							</th>
							<td colSpan={2} className="fee">
								<span>Fee</span>
								<span>0.1</span>%
							</td>
							</tr>
						</tbody>
						</table>
						<div className="btn-buy-sell">
						<a href="#">
							<button>회원가입/초기화</button>
						</a>
						<a href="#">
							<button className="green">로그인/매도하기</button>
						</a>
						</div>
					</div>
					</div>
					{/*지정가 매도하기 끝*/}
				</div>
				{/*시장가 매수매도 끝*/}
				{/*자동채굴 시작*/}
				<div id="tabs2-3" aria-labelledby="ui-id-7" role="tabpanel" className="ui-tabs-panel ui-corner-bottom ui-widget-content" aria-hidden="true" style={{display: 'none'}}>
					<div className="market-auto">
					<span className="invest_rdo alLeft">                        
						<input name="order" id="order_rd1" type="radio" defaultChecked />
						<label htmlFor="order_rd1">Open order</label>
						<input name="order" id="order_rd2" type="radio" />
						<label htmlFor="order_rd2">Complete</label>
					</span>
					<div className="select_market alRight" style={{maxWidth: '150px', float: 'right'}}>
						<select className="select-box">
						<option selected>Current market</option>
						<option>비트코인(BTC)</option>
						<option>라이트코인(LTC)</option>
						<option>이더리움(ETH)</option>
						</select>
					</div>
					<table className="alCenter">
						<tbody>
						<tr>
							<td rowSpan={2}>Time</td>
							<td>Pair</td>
							<td>Stop price</td>
							<td>Amount</td>
							<td rowSpan={2}>Cancel</td>
						</tr>
						<tr>
							<td>Type</td>
							<td>Limit Price</td>
							<td>Unfilled</td>
						</tr>
						</tbody>
					</table>
					</div>
				</div>
				{/*자동채굴 끝*/}  
				</div>
		</div>
        <div className="ex_sec06 sc-cSHVUG eoTJFH tab-content" id="history1">
            <div id="tab-group-3" className="buysell-area ui-tabs ui-corner-all ui-widget ui-widget-content">
            <ul className="li2 tab_exchange ui-tabs-nav ui-corner-all ui-helper-reset ui-helper-clearfix ui-widget-header" role="tablist">
                <li role="tab" tabIndex={0} className="ui-tabs-tab ui-corner-top ui-state-default ui-tab ui-tabs-active ui-state-active" aria-controls="tabs3-1" aria-labelledby="ui-id-8" aria-selected="true" aria-expanded="true"><a href="#tabs3-1" id="ui-id-8" role="presentation" tabIndex={-1} className="ui-tabs-anchor">Depth</a></li>
                <li role="tab" tabIndex={-1} className="ui-tabs-tab ui-corner-top ui-state-default ui-tab" aria-controls="tabs3-2" aria-labelledby="ui-id-9" aria-selected="false" aria-expanded="false"><a href="#tabs3-2" id="ui-id-9" role="presentation" tabIndex={-1} className="ui-tabs-anchor">Chart</a></li> 
            </ul>
            {/*지정가 매수매도 시작*/}
            <div id="tabs3-1" aria-labelledby="ui-id-8" role="tabpanel" className="ui-tabs-panel ui-corner-bottom ui-widget-content" aria-hidden="false">
                <div className="graph tab-content" id="chart2">
                <iframe src="https://holdport.com/chart/chart_view/chart.html?coin=GDC&pay=KRW&theme=day" title="TradeChart" width="100%" height={475} frameBorder={0} />
                </div>  
            </div>
            <div id="tabs3-2" aria-labelledby="ui-id-9" role="tabpanel" className="ui-tabs-panel ui-corner-bottom ui-widget-content" aria-hidden="true" style={{display: 'none'}}>
                <div className="graph tab-content" id="chart3">
                <iframe src="https://holdport.com/chart/chart_view/chart.html?coin=GDC&pay=KRW&theme=day" title="TradeChart" width="100%" height={475} frameBorder={0} />
                </div>  
            </div>
            {/*시장가 매수매도 끝*/}
            </div>
        </div>
        </>
    )
}