export default function ExchangeOrder(){
    return(
        <div className="content-item tab-content" id="order">
			{/*회원체결 미체결 시작*/}
			<div id="tab-group-4" className="box2 ui-tabs ui-corner-all ui-widget ui-widget-content">
			<ul className="li2 tab_exchange ui-tabs-nav ui-corner-all ui-helper-reset ui-helper-clearfix ui-widget-header" role="tablist">
				<li role="tab" tabIndex={0} className="ui-tabs-tab ui-corner-top ui-state-default ui-tab ui-tabs-active ui-state-active" aria-controls="tabs4-1" aria-labelledby="ui-id-1" aria-selected="true" aria-expanded="true"><a href="#tabs4-1" role="presentation" tabIndex={-1} className="ui-tabs-anchor" id="ui-id-1">Recent</a></li>
				<li role="tab" tabIndex={-1} className="ui-tabs-tab ui-corner-top ui-state-default ui-tab" aria-controls="tabs4-2" aria-labelledby="ui-id-2" aria-selected="false" aria-expanded="false"><a href="#tabs4-2" role="presentation" tabIndex={-1} className="ui-tabs-anchor" id="ui-id-2">Daily</a></li>
			</ul>
			<div id="tabs4-1" aria-labelledby="ui-id-1" role="tabpanel" className="ui-tabs-panel ui-corner-bottom ui-widget-content" aria-hidden="false">
				{/*회원체결 시작*/}
				<dl className="dl-tit3">
				<dt>Date</dt>
				<dd>Price (KRW)</dd>
				<dd>Amount (GDC)</dd>
				<dd>Total (KRW)</dd>
				</dl>
				<div className="list-tbl mCustomScrollbar _mCS_1"><div id="mCSB_1" className="mCustomScrollBox mCS-light mCSB_vertical mCSB_inside" style={{maxHeight: 'none'}} tabIndex={0}><div id="mCSB_1_container" className="mCSB_container" style={{position: 'relative', top: 0, left: 0}} dir="ltr">
					<table className="type01">
						<tbody>
						<tr>
							<td>2017-12-11 18:50:00</td>
							<td className="red">매수</td>
							<td>2,039,000</td>
							<td className="bold">0.01294098</td>
						</tr>
						<tr>
							<td>2017-12-11 18:50:00</td>
							<td className="red">매수</td>
							<td>2,039,000</td>
							<td className="bold">0.01294098</td>                    
						</tr>
						<tr>
							<td>2017-12-11 18:50:00</td>
							<td className="blue">매도</td>
							<td>2,039,000</td>
							<td className="bold">0.01294098</td>                                                      </tr>
						<tr>
							<td>2017-12-11 18:50:00</td>
							<td className="red">매수</td>
							<td>2,039,000</td>
							<td className="bold">0.01294098</td>     
						</tr>
						<tr>
							<td>2017-12-11 18:50:00</td>
							<td className="blue">매도</td>
							<td>2,039,000</td>
							<td className="bold">0.01294098</td>
						</tr>
						<tr>
							<td>2017-12-11 18:50:00</td>
							<td className="red">매수</td>
							<td>2,039,000</td>
							<td className="bold">0.01294098</td>
						</tr>
						<tr>
							<td>2017-12-11 18:50:00</td>
							<td className="red">매수</td>
							<td>2,039,000</td>
							<td className="bold">0.01294098</td>                  
						</tr>
						<tr>
							<td>2017-12-11 18:50:00</td>
							<td className="blue">매도</td>
							<td>2,039,000</td>
							<td className="bold">0.01294098</td>
						</tr>
						<tr>
							<td>2017-12-11 18:50:00</td>
							<td className="red">매수</td>
							<td>2,039,000</td>
							<td className="bold">0.01294098</td>                    
						</tr>
						<tr>
							<td>2017-12-11 18:50:00</td>
							<td className="blue">매도</td>
							<td>2,039,000</td>
							<td className="bold">0.01294098</td>
						</tr>
						<tr>
							<td>2017-12-11 18:50:00</td>
							<td className="red">매수</td>
							<td>2,039,000</td>
							<td className="bold">0.01294098</td>                    
						</tr>
						<tr>
							<td>2017-12-11 18:50:00</td>
							<td className="blue">매도</td>
							<td>2,039,000</td>
							<td className="bold">0.01294098</td>
						</tr>
						</tbody>
					</table>
					</div><div id="mCSB_1_scrollbar_vertical" className="mCSB_scrollTools mCSB_1_scrollbar mCS-light mCSB_scrollTools_vertical" style={{display: 'block'}}><div className="mCSB_draggerContainer"><div id="mCSB_1_dragger_vertical" className="mCSB_dragger" style={{position: 'absolute', minHeight: '30px', display: 'block', height: '326px', maxHeight: '392px', top: '0px'}}><div className="mCSB_dragger_bar" style={{lineHeight: '30px'}} /></div><div className="mCSB_draggerRail" /></div></div></div></div>
				{/*회원체결 끝*/}
			</div>
			<div id="tabs4-2" aria-labelledby="ui-id-2" role="tabpanel" className="ui-tabs-panel ui-corner-bottom ui-widget-content" aria-hidden="true" style={{display: 'none'}}>
				{/*회원체결 시작*/}
				<dl className="dl-tit3">
				<dt>Date</dt>
				<dd>Close (BTC)</dd>
				<dd>Change</dd>
				<dd>Volume (ETH)</dd>
				</dl>
				<div className="list-tbl mCustomScrollbar _mCS_2 mCS_no_scrollbar"><div id="mCSB_2" className="mCustomScrollBox mCS-light mCSB_vertical mCSB_inside" tabIndex={0} style={{maxHeight: '402px'}}><div id="mCSB_2_container" className="mCSB_container mCS_y_hidden mCS_no_scrollbar_y" style={{position: 'relative', top: 0, left: 0}} dir="ltr">
					<table className="type01">
						<tbody>
						<tr>
							<td>2017-12-11 18:50:00</td>
							<td className="red">매수</td>
							<td>2,039,000</td>
							<td className="bold">0.01294098</td>
						</tr>
						<tr>
							<td>2017-12-11 18:50:00</td>
							<td className="red">매수</td>
							<td>2,039,000</td>
							<td className="bold">0.01294098</td>                    
						</tr>
						<tr>
							<td>2017-12-11 18:50:00</td>
							<td className="blue">매도</td>
							<td>2,039,000</td>
							<td className="bold">0.01294098</td>                                                      </tr>
						<tr>
							<td>2017-12-11 18:50:00</td>
							<td className="red">매수</td>
							<td>2,039,000</td>
							<td className="bold">0.01294098</td>     
						</tr>
						<tr>
							<td>2017-12-11 18:50:00</td>
							<td className="blue">매도</td>
							<td>2,039,000</td>
							<td className="bold">0.01294098</td>
						</tr>
						<tr>
							<td>2017-12-11 18:50:00</td>
							<td className="red">매수</td>
							<td>2,039,000</td>
							<td className="bold">0.01294098</td>
						</tr>
						<tr>
							<td>2017-12-11 18:50:00</td>
							<td className="red">매수</td>
							<td>2,039,000</td>
							<td className="bold">0.01294098</td>                  
						</tr>
						<tr>
							<td>2017-12-11 18:50:00</td>
							<td className="blue">매도</td>
							<td>2,039,000</td>
							<td className="bold">0.01294098</td>
						</tr>
						<tr>
							<td>2017-12-11 18:50:00</td>
							<td className="red">매수</td>
							<td>2,039,000</td>
							<td className="bold">0.01294098</td>                    
						</tr>
						<tr>
							<td>2017-12-11 18:50:00</td>
							<td className="blue">매도</td>
							<td>2,039,000</td>
							<td className="bold">0.01294098</td>
						</tr>
						<tr>
							<td>2017-12-11 18:50:00</td>
							<td className="red">매수</td>
							<td>2,039,000</td>
							<td className="bold">0.01294098</td>                    
						</tr>
						<tr>
							<td>2017-12-11 18:50:00</td>
							<td className="blue">매도</td>
							<td>2,039,000</td>
							<td className="bold">0.01294098</td>
						</tr>
						</tbody>
					</table>
					</div><div id="mCSB_2_scrollbar_vertical" className="mCSB_scrollTools mCSB_2_scrollbar mCS-light mCSB_scrollTools_vertical" style={{display: 'none'}}><div className="mCSB_draggerContainer"><div id="mCSB_2_dragger_vertical" className="mCSB_dragger" style={{position: 'absolute', minHeight: '30px', top: '0px'}}><div className="mCSB_dragger_bar" style={{lineHeight: '30px'}} /></div><div className="mCSB_draggerRail" /></div></div></div></div>
				{/*회원체결 끝*/}
			</div>
			</div>
			{/*회윈 체결 미체결 끝*/}
		</div>
    )
}