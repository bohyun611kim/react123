import DWContainerCurrency from "./dw_container_currency";
import DWContainerCoin from "./dw_container_coin";
import { useRecoilState, useRecoilValue } from "recoil";

import {wallet_coin_selected, wallet_tab_selected, wallet_coin_info}from "../../../module/stores/wallet_state"
import { getLocaleContent } from "../../../module/utils/language.util";

const DWContainer = () => {

	const coins = useRecoilValue(wallet_coin_info);
	const selected = useRecoilValue(wallet_coin_selected);
	const [selectedTab, setSelectedTab] = useRecoilState(wallet_tab_selected);

	var tab1 = "ui-tabs-tab ui-corner-top ui-state-default ui-tab";
	var tab2 = "ui-tabs-tab ui-corner-top ui-state-default ui-tab";
	var tab3 = "ui-tabs-tab ui-corner-top ui-state-default ui-tab";
	if (selectedTab == 0) {
		tab1 += " ui-tabs-active ui-state-active";
	} else if (selectedTab == 1) {
		tab2 += " ui-tabs-active ui-state-active";
	} else if (selectedTab == 2) {
		tab3 += " ui-tabs-active ui-state-active";
	}

	// console.log("==================>Container SELECT:", coins, ",selected:", selected);
	return (
	<div className="content">
		{/* <!-- CONTENT INNER : Start --> */}
		<div>
			<h1 className="subtitle">{getLocaleContent("word", "deposit&withdrawral")}</h1>

			<div className="ui-tabs ui-corner-all ui-widget ui-widget-content" style={{display:"block"}}>
				<ul className="li3 ui-tabs-nav ui-corner-all ui-helper-reset ui-helper-clearfix ui-widget-header">
					<li className={tab1} onClick={e=>(setSelectedTab(0))}  ><a href="#" className="ui-tabs-anchor">{getLocaleContent("word", "depositTab")}</a></li>
					<li className={tab2} onClick={e=>(setSelectedTab(1))}  ><a href="#" className="ui-tabs-anchor">{getLocaleContent("word", "withdrawalTab")}</a></li>
					<li className={tab3} onClick={e=>(setSelectedTab(2))} ><a href="#" className="ui-tabs-anchor">{getLocaleContent("word", "historyTab")}</a></li>
				</ul>
				{selected.coin === "KRW" ? <DWContainerCurrency/> : <DWContainerCoin coin={coins[selected.index]}/>}
			</div>

		</div>
		{/* <!-- //CONTENT INNER : End --> */}
	</div>
	);
}



export default DWContainer;