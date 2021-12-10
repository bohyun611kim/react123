import { useRecoilValue } from "recoil";
import { wallet_tab_selected } from "../../../module/stores/wallet_state";
import DepositCoinTab from "./dw_deposit_coin";
import WithdrawCoinTab from "./dw_withdraw_coin";
import DWHistoryCoin from "./dw_history_tab";

export default function DWContainerCoin({coin}) {

	const selectedTab = useRecoilValue(wallet_tab_selected);

	// console.log("DWContainerCoin: this:", coin);
	return (
		<>
			{selectedTab === 0 ?  <DepositCoinTab coin={coin}/> : null }
			{selectedTab === 1 ?  <WithdrawCoinTab coin={coin}/> : null }
			{selectedTab === 2 ?  <DWHistoryCoin coin={coin}/> : null }
		</>

	);
}