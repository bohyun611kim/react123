import { useRecoilState } from "recoil";
import { market_coin_selected, market_tab_selected } from "../../../module/stores/exchange_state";

export default function ExchangeMarket({market}){

	const [coinInfo, setCoinInfo] = useRecoilState(market_coin_selected);
	const [tabInfo, setTabInfo] = useRecoilState(market_tab_selected);


    return(
        <div className="ex_top_left">
            <div className="ex_left exchange">
                <div className="ex_sec03 sc-fjdhpX">
                    <div className="ex_left_market">
                        <div>
                        <span>Market</span>
                        <img src={`/resources/img/right.png`} alt="icon right" />
                        </div>
                    </div>
                    <div className="ex_sec03_topbox">
                        <div className="bookmark_btn">
                        <img src={`/resources/img/search.png`} alt="search" />
                        </div>
                        <input type="search" className="sec03_search" placeholder="Search" defaultValue />
                    </div>
                    <div className="ex_sec03_data">
                    <div className="tabs sc-htpNat cdSZgy">
                    <ul className="list ex_sec03_tab three" style={{display: 'block'}}>
                        <li className={tabInfo.coin=='KRW' ? "tab-item sec03_tab_on" : "tab-item"}>KRW</li>
                        <li className={tabInfo.coin=='BTC' ? "tab-item sec03_tab_on" : "tab-item"}>BTC</li>
                        <li className={tabInfo.coin=='USDT' ? "tab-item sec03_tab_on" : "tab-item"}>USDT</li>
                    </ul>
                    <ul className="list02">
                        <li className="tab-item">암호화폐</li>
                        <li className="tab-item">현재가</li>
                        <li className="tab-item">전일대비</li>
                        <li className="tab-item">거래대금(24h)</li>
                    </ul>
                    <div>
                        <div className="ex_sec03_chart chdAiK">
                        <div className="ex_coinlist sc-jTzLTM kMqZix">
                            <div className="bookmark"></div>
                            <div className="sec03_txt01">
                            <div className="sec03_txt01_01">OA</div>
                            <div>OA/KRW</div>
                            </div>
                            <div className="sec03_txt02 red"><span>2,000</span></div>
                            <div className="sec03_txt03 c_red"><span>0%</span></div>
                            <div className="sec03_txt03 c_red"><span>0%</span></div>
                        </div>
                        <div className="ex_coinlist sc-jTzLTM kMqZix">
                            <div className="bookmark"></div>
                            <div className="sec03_txt01">
                            <div className="sec03_txt01_01">WRD</div>
                            <div>WRD/KRW</div>
                            </div>
                            <div className="sec03_txt02 red"><span>8</span></div>
                            <div className="sec03_txt03 c_bl"><span>-11.11%</span></div>
                            <div className="sec03_txt03 c_bl"><span>-11.11%</span></div>
                        </div>
                        <div className="ex_coinlist sc-jTzLTM kMqZix">
                            <div className="bookmark"></div>
                            <div className="sec03_txt01">
                            <div className="sec03_txt01_01">SMILE</div>
                            <div>SMILE/KRW</div>
                            </div>
                            <div className="sec03_txt02 green"><span>92,700</span></div>
                            <div className="sec03_txt03 c_red"><span>0.22%</span></div>
                            <div className="sec03_txt03 c_red"><span>0.22%</span></div>
                        </div>
                        <div className="ex_coinlist sc-jTzLTM kMqZix">
                            <div className="bookmark"></div>
                            <div className="sec03_txt01">
                            <div className="sec03_txt01_01">DBP</div>
                            <div>DBP/KRW</div>
                            </div>
                            <div className="sec03_txt02 green"><span>12.9</span></div>
                            <div className="sec03_txt03 c_bl"><span>-28.33%</span></div>
                            <div className="sec03_txt03 c_bl"><span>-28.33%</span></div>
                        </div>
                        <div className="ex_coinlist sc-jTzLTM kMqZix">
                            <div className="bookmark"></div>
                            <div className="sec03_txt01">
                            <div className="sec03_txt01_01">USDT</div>
                            <div>USDT/KRW</div>
                            </div>
                            <div className="sec03_txt02 green"><span>1,250</span></div>
                            <div className="sec03_txt03 c_red"><span>0%</span></div>
                            <div className="sec03_txt03 c_red"><span>0%</span></div>
                        </div>
                        <div className="ex_coinlist sc-jTzLTM kMqZix">
                            <div className="bookmark"></div>
                            <div className="sec03_txt01">
                            <div className="sec03_txt01_01">Bitcoin</div>
                            <div>BTC/KRW</div>
                            </div>
                            <div className="sec03_txt02 green"><span>56,997,000</span></div>
                            <div className="sec03_txt03 c_red"><span>13.99%</span></div>
                            <div className="sec03_txt03 c_red"><span>13.99%</span></div>
                        </div>
                        <div className="ex_coinlist sc-jTzLTM kMqZix">
                            <div className="bookmark"></div>
                            <div className="sec03_txt01">
                            <div className="sec03_txt01_01">Ethereum</div>
                            <div>ETH/KRW</div>
                            </div>
                            <div className="sec03_txt02 green"><span>4,746,900</span></div>
                            <div className="sec03_txt03 c_red"><span>43.81%</span></div>
                            <div className="sec03_txt03 c_red"><span>43.81%</span></div>
                        </div>
                        <div className="ex_coinlist sc-jTzLTM kMqZix">
                            <div className="bookmark"></div>
                            <div className="sec03_txt01">
                            <div className="sec03_txt01_01">Ripple</div>
                            <div>XRP/KRW</div>
                            </div>
                            <div className="sec03_txt02 red"><span>1,200</span></div>
                            <div className="sec03_txt03 c_bl"><span>-33.26%</span></div>
                            <div className="sec03_txt03 c_bl"><span>-33.26%</span></div>
                        </div>
                        <div className="ex_coinlist sc-jTzLTM kMqZix">
                            <div className="bookmark"></div>
                            <div className="sec03_txt01">
                            <div className="sec03_txt01_01">DCMC</div>
                            <div>DCMC/KRW</div>
                            </div>
                            <div className="sec03_txt02 red"><span>17</span></div>
                            <div className="sec03_txt03 c_bl"><span>-5.56%</span></div>
                            <div className="sec03_txt03 c_bl"><span>-5.56%</span></div>
                        </div>
                        <div className="ex_coinlist sc-jTzLTM kMqZix">
                            <div className="bookmark"></div>
                            <div className="sec03_txt01">
                            <div className="sec03_txt01_01">COINMAN</div>
                            <div>CIM/KRW</div>
                            </div>
                            <div className="sec03_txt02 red"><span>16</span></div>
                            <div className="sec03_txt03 c_bl"><span>-5.88%</span></div>
                            <div className="sec03_txt03 c_bl"><span>-5.88%</span></div>
                        </div>
                        <div className="ex_coinlist sc-jTzLTM kMqZix">
                            <div className="bookmark"></div>
                            <div className="sec03_txt01">
                            <div className="sec03_txt01_01">Mostcash</div>
                            <div>MC/KRW</div>
                            </div>
                            <div className="sec03_txt02 green"><span>3.1</span></div>
                            <div className="sec03_txt03 c_bl"><span>-8.82%</span></div>
                            <div className="sec03_txt03 c_bl"><span>-8.82%</span></div>
                        </div>
                        <div className="ex_coinlist sc-jTzLTM kMqZix">
                            <div className="bookmark"></div>
                            <div className="sec03_txt01">
                            <div className="sec03_txt01_01">RON</div>
                            <div>RON/KRW</div>
                            </div>
                            <div className="sec03_txt02 red"><span>1</span></div>
                            <div className="sec03_txt03 c_red"><span>0%</span></div>
                            <div className="sec03_txt03 c_red"><span>0%</span></div>
                        </div>
                        <div className="ex_coinlist sc-jTzLTM kMqZix">
                            <div className="bookmark"></div>
                            <div className="sec03_txt01">
                            <div className="sec03_txt01_01">UMX Coin</div>
                            <div>UMX/KRW</div>
                            </div>
                            <div className="sec03_txt02 red"><span>9</span></div>
                            <div className="sec03_txt03 c_red"><span>0%</span></div>
                            <div className="sec03_txt03 c_red"><span>0%</span></div>
                        </div>
                        <div className="ex_coinlist sc-jTzLTM kMqZix">
                            <div className="bookmark"></div>
                            <div className="sec03_txt01">
                            <div className="sec03_txt01_01">YOCoin</div>
                            <div>YOC/KRW</div>
                            </div>
                            <div className="sec03_txt02 green"><span>1.47</span></div>
                            <div className="sec03_txt03 c_red"><span>234.09%</span></div>
                            <div className="sec03_txt03 c_red"><span>234.09%</span></div>
                        </div>
                        <div className="ex_coinlist sc-jTzLTM kMqZix">
                            <div className="bookmark"></div>
                            <div className="sec03_txt01">
                            <div className="sec03_txt01_01">Bitcoinstar</div>
                            <div>BCS/KRW</div>
                            </div>
                            <div className="sec03_txt02 green"><span>297</span></div>
                            <div className="sec03_txt03 c_red"><span>0%</span></div>
                            <div className="sec03_txt03 c_red"><span>0%</span></div>
                        </div>
                        <div className="ex_coinlist sc-jTzLTM kMqZix">
                            <div className="bookmark"></div>
                            <div className="sec03_txt01">
                            <div className="sec03_txt01_01">TRV</div>
                            <div>TRV/KRW</div>
                            </div>
                            <div className="sec03_txt02 green"><span>0.97</span></div>
                            <div className="sec03_txt03 c_red"><span>0%</span></div>
                            <div className="sec03_txt03 c_red"><span>0%</span></div>
                        </div>
                        <div className="ex_coinlist sc-jTzLTM kMqZix">
                            <div className="bookmark"></div>
                            <div className="sec03_txt01">
                            <div className="sec03_txt01_01">STAR</div>
                            <div>STAR/KRW</div>
                            </div>
                            <div className="sec03_txt02 green"><span>1,200</span></div>
                            <div className="sec03_txt03 c_bl"><span>-7.69%</span></div>
                            <div className="sec03_txt03 c_bl"><span>-7.69%</span></div>
                        </div>
                        <div className="ex_coinlist sc-jTzLTM kMqZix">
                            <div className="bookmark"></div>
                            <div className="sec03_txt01">
                            <div className="sec03_txt01_01">SAFE</div>
                            <div>SAFE/KRW</div>
                            </div>
                            <div className="sec03_txt02 green"><span>598</span></div>
                            <div className="sec03_txt03 c_red"><span>0.17%</span></div>
                            <div className="sec03_txt03 c_red"><span>0.17%</span></div>
                        </div>
                        <div className="ex_coinlist sc-jTzLTM kMqZix">
                            <div className="bookmark"></div>
                            <div className="sec03_txt01">
                            <div className="sec03_txt01_01">MAC</div>
                            <div>MAC/KRW</div>
                            </div>
                            <div className="sec03_txt02 green"><span>0</span></div>
                            <div className="sec03_txt03 c_red"><span>0%</span></div>
                            <div className="sec03_txt03 c_red"><span>0%</span></div>
                        </div>
                        <div className="ex_coinlist sc-jTzLTM kMqZix">
                            <div className="bookmark"></div>
                            <div className="sec03_txt01">
                            <div className="sec03_txt01_01">NIC</div>
                            <div>NIC/KRW</div>
                            </div>
                            <div className="sec03_txt02 green"><span>0</span></div>
                            <div className="sec03_txt03 c_red"><span>0%</span></div>
                            <div className="sec03_txt03 c_red"><span>0%</span></div>
                        </div>
                        <div className="ex_coinlist sc-jTzLTM kMqZix">
                            <div className="bookmark"></div>
                            <div className="sec03_txt01">
                            <div className="sec03_txt01_01">HPDC</div>
                            <div>HPDC/KRW</div>
                            </div>
                            <div className="sec03_txt02 green"><span>0</span></div>
                            <div className="sec03_txt03 c_red"><span>0%</span></div>
                            <div className="sec03_txt03 c_red"><span>0%</span></div>
                        </div>
                        <div className="ex_coinlist sc-jTzLTM kMqZix">
                            <div className="bookmark"></div>
                            <div className="sec03_txt01">
                            <div className="sec03_txt01_01">UIS</div>
                            <div>UIS/KRW</div></div>
                            <div className="sec03_txt02 green"><span>0</span></div>
                            <div className="sec03_txt03 c_red"><span>0%</span></div>
                            <div className="sec03_txt03 c_red"><span>0%</span></div>
                        </div>
                        <div className="ex_coinlist sc-jTzLTM kMqZix">
                            <div className="bookmark"></div>
                            <div className="sec03_txt01">
                            <div className="sec03_txt01_01">BITING</div>
                            <div>BITING/KRW</div>
                            </div>
                            <div className="sec03_txt02 green"><span>600</span></div>
                            <div className="sec03_txt03 c_red"><span>0%</span></div>
                            <div className="sec03_txt03 c_red"><span>0%</span></div>
                        </div>
                        <div className="ex_coinlist sc-jTzLTM kMqZix">
                            <div className="bookmark"></div>
                            <div className="sec03_txt01">
                            <div className="sec03_txt01_01">BHCA</div>
                            <div>BHCA/KRW</div>
                            </div>
                            <div className="sec03_txt02 green"><span>3,240</span></div>
                            <div className="sec03_txt03 c_red"><span>1.25%</span></div>
                            <div className="sec03_txt03 c_red"><span>1.25%</span></div>
                        </div>
                        <div className="ex_coinlist sc-jTzLTM kMqZix">
                            <div className="bookmark"></div>
                            <div className="sec03_txt01">
                            <div className="sec03_txt01_01">BHE</div>
                            <div>BHE/KRW</div>
                            </div>
                            <div className="sec03_txt02 green"><span>1,720</span></div>
                            <div className="sec03_txt03 c_red"><span>1.18%</span></div>
                            <div className="sec03_txt03 c_red"><span>1.18%</span></div>
                        </div>
                        <div className="ex_coinlist sc-jTzLTM kMqZix">
                            <div className="bookmark"></div>
                            <div className="sec03_txt01">
                            <div className="sec03_txt01_01">ABAG</div>
                            <div>ABAG/KRW</div>
                            </div>
                            <div className="sec03_txt02 green"><span>1.9</span></div>
                            <div className="sec03_txt03 c_red"><span>90%</span></div>
                            <div className="sec03_txt03 c_red"><span>90%</span></div>
                        </div>
                        <div className="ex_coinlist sc-jTzLTM kMqZix">
                            <div className="bookmark"></div>
                            <div className="sec03_txt01">
                            <div className="sec03_txt01_01">Scorcoin</div>
                            <div>SCOR/KRW</div>
                            </div>
                            <div className="sec03_txt02 green"><span>0.8</span></div>
                            <div className="sec03_txt03 c_red"><span>0%</span></div>
                            <div className="sec03_txt03 c_red"><span>0%</span></div>
                        </div>
                        <div className="ex_coinlist sc-jTzLTM kMqZix">
                            <div className="bookmark">                                                                      
 
                            </div>
                            <div className="sec03_txt01">
                            <div className="sec03_txt01_01">VTCP</div>
                            <div>VTCP/KRW</div>
                            </div>
                            <div className="sec03_txt02 green"><span>200</span></div>
                            <div className="sec03_txt03 c_red"><span>0%</span></div>
                            <div className="sec03_txt03 c_red"><span>0%</span></div>
                        </div>
                        <div className="ex_coinlist sc-jTzLTM kMqZix">
                            <div className="bookmark"></div>
                            <div className="sec03_txt01">
                            <div className="sec03_txt01_01">GMC</div>
                            <div>GMC/KRW</div>
                            </div>
                            <div className="sec03_txt02"><span>70</span></div>
                            <div className="sec03_txt03 c_bl"><span>-12.5%</span></div>
                            <div className="sec03_txt03 c_bl"><span>-12.5%</span></div>
                        </div>
                        <div className="ex_coinlist sc-jTzLTM kMqZix">
                            <div className="bookmark"></div>
                            <div className="sec03_txt01">
                            <div className="sec03_txt01_01">SCOE</div>
                            <div>SCOE/KRW</div>
                            </div>
                            <div className="sec03_txt02 green"><span>260</span></div>
                            <div className="sec03_txt03 c_bl"><span>-3.7%</span></div>
                            <div className="sec03_txt03 c_bl"><span>-3.7%</span></div>
                        </div>
                        <div className="ex_coinlist sc-jTzLTM kMqZix">
                            <div className="bookmark"></div>
                            <div className="sec03_txt01">
                            <div className="sec03_txt01_01">GDC</div>
                            <div>GDC/KRW</div>
                            </div>
                            <div className="sec03_txt02 green"><span>11,100</span></div>
                            <div className="sec03_txt03 c_red"><span>0%</span></div>
                            <div className="sec03_txt03 c_red"><span>0%</span></div>
                        </div>
                        <div className="ex_coinlist sc-jTzLTM kMqZix">
                            <div className="bookmark"></div>
                            <div className="sec03_txt01">
                            <div className="sec03_txt01_01">OIL</div>
                            <div>OIL/KRW</div>
                            </div>
                            <div className="sec03_txt02 green"><span>2</span></div>
                            <div className="sec03_txt03 c_red"><span>0%</span></div>
                            <div className="sec03_txt03 c_red"><span>0%</span></div>
                        </div>
                        <div className="ex_coinlist sc-jTzLTM kMqZix">
                            <div className="bookmark"></div>
                            <div className="sec03_txt01">
                            <div className="sec03_txt01_01">BCU</div>
                            <div>BCU/KRW</div>
                            </div>
                            <div className="sec03_txt02 red"><span>12</span></div>
                            <div className="sec03_txt03 c_bl"><span>-7.69%</span></div>
                            <div className="sec03_txt03 c_bl"><span>-7.69%</span></div>
                        </div>
                        <div className="ex_coinlist sc-jTzLTM kMqZix">
                            <div className="bookmark"></div>
                            <div className="sec03_txt01">
                            <div className="sec03_txt01_01">PCP</div>
                            <div>PCP/KRW</div>
                            </div>
                            <div className="sec03_txt02 red"><span>0</span></div>
                            <div className="sec03_txt03 c_red"><span>0%</span></div>
                            <div className="sec03_txt03 c_red"><span>0%</span></div>
                        </div>
                        <div className="ex_coinlist sc-jTzLTM kMqZix">
                            <div className="bookmark"></div>
                            <div className="sec03_txt01">
                            <div className="sec03_txt01_01">BTT</div>
                            <div>BTT/KRW</div>
                            </div>
                            <div className="sec03_txt02 red"><span>6</span></div>
                            <div className="sec03_txt03 c_red"><span>0%</span></div>
                            <div className="sec03_txt03 c_red"><span>0%</span></div>
                        </div>
                        <div className="ex_coinlist sc-jTzLTM kMqZix">
                            <div className="bookmark"></div>
                            <div className="sec03_txt01">
                            <div className="sec03_txt01_01">TRON</div>
                            <div>TRX/KRW</div>
                            </div>
                            <div className="sec03_txt02 red"><span>200</span></div>
                            <div className="sec03_txt03 c_red"><span>0%</span></div>
                            <div className="sec03_txt03 c_red"><span>0%</span></div>
                        </div>
                        <div className="ex_coinlist sc-jTzLTM kMqZix">
                            <div className="bookmark"></div>
                            <div className="sec03_txt01">
                            <div className="sec03_txt01_01">CMP</div>
                            <div>CMP/KRW</div>
                            </div>
                            <div className="sec03_txt02 red"><span>2</span></div>
                            <div className="sec03_txt03 c_red"><span>0%</span></div>
                            <div className="sec03_txt03 c_red"><span>0%</span></div>
                        </div>
                        <div className="ex_coinlist sc-jTzLTM kMqZix">
                            <div className="bookmark"></div>
                            <div className="sec03_txt01 red">
                            <div className="sec03_txt01_01">LANX Coin</div>
                            <div>LANX/KRW</div>
                            </div>
                            <div className="sec03_txt02"><span>4</span></div>
                            <div className="sec03_txt03 c_red"><span>0%</span></div>
                            <div className="sec03_txt03 c_red"><span>0%</span></div>
                        </div>
                        <div className="ex_coinlist sc-jTzLTM kMqZix">
                            <div className="bookmark"></div>
                            <div className="sec03_txt01 red">
                            <div className="sec03_txt01_01">HMS</div>
                            <div>HMS/KRW</div>
                            </div>
                            <div className="sec03_txt02"><span>0</span></div>
                            <div className="sec03_txt03 c_red"><span>0%</span></div>
                            <div className="sec03_txt03 c_red"><span>0%</span></div>
                        </div>
                        <div className="ex_coinlist sc-jTzLTM kMqZix">
                            <div className="bookmark"></div>
                            <div className="sec03_txt01">
                            <div className="sec03_txt01_01">AMC green</div>
                            <div>AMBX/KRW</div>
                            </div>
                            <div className="sec03_txt02 red"><span>0.5</span></div>
                            <div className="sec03_txt03 c_red"><span>0%</span></div>
                            <div className="sec03_txt03 c_red"><span>0%</span></div>
                        </div>
                        <div className="ex_coinlist sc-jTzLTM kMqZix">
                            <div className="bookmark"></div>
                            <div className="sec03_txt01">
                            <div className="sec03_txt01_01">Aircraft Mileage Coin</div>
                            <div>AMC/KRW</div>
                            </div>
                            <div className="sec03_txt02 red"><span>1</span></div>
                            <div className="sec03_txt03 c_red"><span>0%</span></div>
                            <div className="sec03_txt03 c_red"><span>0%</span></div>
                        </div>
                        </div>
                    </div>
                    </div>
                </div>
            </div>
            </div>
        </div>
    )
}