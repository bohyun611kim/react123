import { useState } from "react";
import { useRecoilValue } from "recoil";
import { wallet_bank_info, wallet_tab_selected } from "../../../module/stores/wallet_state";
import DWHistoryTab from "./dw_history_tab";
import DepositCurrencyRequst from "./dw_deposit_currency";
import DWBankRegister from "./dw_register_account";
import WithdrawCurrency from "./dw_withdraw_currency";


export default function DWContainerCurrency() {

	const selectedTab = useRecoilValue(wallet_tab_selected);
	const bankInfo = useRecoilValue(wallet_bank_info)

	if (selectedTab === 0) {
		return 	(bankInfo == null ? <DWBankRegister/> : <DepositCurrencyRequst bank={bankInfo}/>)
	} else if (selectedTab === 1) {
		if (bankInfo == null) return <DWBankRegister/>;
		return <WithdrawCurrency bank={bankInfo}/>	
	} else if (selectedTab === 2) {
		return <DWHistoryTab/>	
	}
}
