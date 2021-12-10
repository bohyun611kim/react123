import { useEffect, useState } from "react";
import { RecoilRoot, useRecoilState,useRecoilValue,useRecoilValueLoadable, useSetRecoilState } from "recoil";
import { wallet_comp_info, wallet_coin_ref, wallet_coin_info, wallet_user_info, wallet_base_coin, wallet_bank_info } from "../../module/stores/wallet_state";
import { BASE_PREFIX } from "../../module/myconstants";
import request from "../../module/services/request.service";
import { is_empty } from "../../module/utils/misc.util";
import DWASide from "./component/dw_aside";
import DWContainer from "./component/dw_container";

const DwService = () => {

	const [bankCode, setBankCode] = useState(null);

	const [coinInfo, setCoinInfo] = useRecoilState(wallet_coin_info);
	const [coinRef, setCoinRef] = useRecoilState(wallet_coin_ref);
	//const walletLoader  = useRecoilValueLoadable(select_wallet_info);

	const setUserInfo = useSetRecoilState(wallet_user_info);
	const setBaseInfo = useSetRecoilState(wallet_base_coin);
	const setBankInfo = useSetRecoilState(wallet_bank_info);
	const setCompBank = useSetRecoilState(wallet_comp_info);


	useEffect(() => {
		_get_info();
		_get_user();
		_get_bank();
	}, [])

    const _get_info = async () => {
		const rsp = await request.post(BASE_PREFIX+'/api/wallet', null).then(response => response.data)
		// console.log("_get_info ---:",rsp);

		try {
			var bankCodeList = JSON.parse(rsp.bankCodeInfoList)
			setBankCode(bankCodeList)

			// exchangeCoinInfo
			var defaultCoinInfo = new Function ('return ' + rsp.defaultCoinInfo)();
			//console.log("defaultCoinInfo:", defaultCoinInfo)

			setBaseInfo(defaultCoinInfo);

			var exchangeCoinInfo = new Function ('return ' + rsp.exchangeCoinInfo)();
			exchangeCoinInfo = [...defaultCoinInfo,...exchangeCoinInfo]
			 console.log("exchangeCoinInfo>>", exchangeCoinInfo);
			exchangeCoinInfo = exchangeCoinInfo.filter(it => it.COIN_NO != 120)  // 120 COUPOIN
			setCoinInfo(exchangeCoinInfo);

			var coinRef = new Function ('return ' + rsp.coinMgtRefInfo)();
			setCoinRef(coinRef);

			//console.log("exchangeBankAccntInfo+++");
			var exchangeBankAccntInfo = JSON.parse(rsp.exchangeBankAccntInfo);
			//console.log("exchangeBankAccntInfo---", exchangeBankAccntInfo);
			setCompBank(exchangeBankAccntInfo);


			__G_LNB_Coin_Item_List = new Function ('return ' + rsp.exchangeCoinInfo)();
			// for global
			//drawLnbList(rsp.defaultCoinInfo, rsp.exchangeCoinInfo)
		} catch (error){
			console.log("failed to parse:", error);
			//location.href = "/";
			return null;
		}
	}

    const _get_user = async () => {
		const rsp = await request.post(BASE_PREFIX+'/api/wallet/wlt002/selectUserPossessionInfo', null).then(response => response.data)
		// console.log("_get_user ---:", rsp);

		try {
			setUserInfo(rsp);
		} catch (error){
			console.log("failed to parse:", error);
			location.href = "/";
			return null;
		}
	}

	const _get_bank = async () => {
		const rsp = await request.post(BASE_PREFIX+'/api/wallet/wlt001/selectMemberBankAccntInfo', null).then(response => response.data)
		// console.log("_get_bank ---:", rsp);

		try {
			setBankInfo(is_empty(rsp) ? null : rsp);
		} catch (error){
			console.log("failed to parse:", error);
			location.href = "/";
			return null;
		}
	}

	
	//console.log("DWService:walletInfo", coinInfo);
	if (coinInfo === null) return null;
	//if (walletLoader.state !== 'hasValue') return null;


	return (
		<>
			<DWASide />
			<DWContainer/>
		</>
	);
}

function WalletRoot() {
	return (
		<RecoilRoot>
			<DwService/>
		</RecoilRoot>
	);
}

let domContainer = document.querySelector('#like_wallet');
ReactDOM.render(<WalletRoot/>, domContainer);

