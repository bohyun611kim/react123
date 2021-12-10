import { useEffect, useState } from "react";
import { RecoilRoot, useRecoilState,useRecoilValue,useRecoilValueLoadable, useSetRecoilState } from "recoil";
import { wallet_comp_info, wallet_coin_ref, wallet_coin_info, wallet_user_info, wallet_base_coin, wallet_bank_info } from "../../module/stores/wallet_state";
import { BASE_PREFIX } from "../../module/myconstants";
import request from "../../module/services/request.service";
import { is_empty } from "../../module/utils/misc.util";
import InvestAside from "./component/invest_aside";
import InvestContainer from "./component/invest_container";

const InvestService = () => {

	const [bankCode, setBankCode] = useState(null);

	const [coinInfo, setCoinInfo] = useRecoilState(wallet_coin_info);
	const [coinRef, setCoinRef] = useRecoilState(wallet_coin_ref);
	//const walletLoader  = useRecoilValueLoadable(select_wallet_info);

	const setUserInfo = useSetRecoilState(wallet_user_info);


	useEffect(() => {
	}, [])


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


	

	return (
		<>
			<InvestAside/>
			<InvestContainer/>
		</>
	);
}

function InvestRoot() {
	return (
		<RecoilRoot>
			<InvestService/>
		</RecoilRoot>
	);
}

let domContainer = document.querySelector('#like_invest');
ReactDOM.render(<InvestRoot/>, domContainer);

