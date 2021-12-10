import { useRecoilState, useRecoilValue } from "recoil";

import {wallet_coin_selected, wallet_tab_selected, wallet_coin_info}from "../../../module/stores/wallet_state"
import { getLocaleContent } from "../../../module/utils/language.util";

const InvestContainer = () => {

	const coins = useRecoilValue(wallet_coin_info);


	// console.log("==================>Container SELECT:", coins, ",selected:", selected);
	return (
		<>
		</>
	);
}



export default InvestContainer;