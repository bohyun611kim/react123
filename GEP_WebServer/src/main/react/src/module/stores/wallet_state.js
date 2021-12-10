import {atom, selector, useSetRecoilState} from 'recoil';
import { BASE_PREFIX } from '../myconstants';
import request from '../services/request.service';


export const g_language = atom({
    key: 'language', 
    default: 'ko',
});

export const g_login = atom({
    key: 'member/is_login', 
    default: 0
});

/* wallet action */
export const WALLET_ACTION_BASE_LIST = atom({
    key: 'wallet/action/update_base_list', 
    default: 0
});

/* wallet */
export const wallet_coin_selected = atom({
    key: 'wallet/coin_selected', 
    default: { index: 0 , coin : "KRW", no : "10"},
});
  
export const wallet_tab_selected = atom({
    key: 'wallet/tab_selected', 
    default: 0,
});

export const wallet_coin_info = atom({
    key: 'wallet/coin_info', 
    default: null,
});
export const wallet_coin_ref = atom({
    key: 'wallet/coin_ref', 
    default: null,
});
export const wallet_user_info = atom({
    key: 'wallet/user_info', 
    default: null,
});

export const wallet_bankcode = atom({
    key: 'wallet_info', 
    default: null,
});

export const wallet_coin_list = atom({
    key: 'wallet/coin_list', 
    default: null,
});

export const wallet_base_coin = atom({
    key: 'wallet/base_coin', 
    default: null,
});

export const wallet_bank_info = atom({
    key: 'wallet/bank_info', 
    default: null,
});

export const wallet_comp_info = atom({
    key: 'wallet/comp_info', 
    default: null,
});

export const wallet_deposit_info = atom({
    key: 'wallet/deposit_info', 
    default: null,
});

  // 비동기 처리 셀렉터
export const select_wallet_info=selector({
    key: 'wallet/coin_info2',
    get: async ({get}) => {
        const rsp = await request.post(BASE_PREFIX+'/api/wallet', null).then(response => response.data)
		console.log("all_wallet_info ---:",rsp);
        return rsp;
    },
    set: ({set}, new_rsp)=> {

            var bankCodeList = JSON.parse(new_rsp.bankCodeInfoList)
            console.log("select::wallet_bank_info +++", bankCodeList);
            set(wallet_bank_info, bankCodeList)

			var defaultCoinInfo = new Function ('return ' + new_rsp.defaultCoinInfo)();
            console.log("select::wallet_base_coin +++", defaultCoinInfo);
			set(wallet_base_coin, defaultCoinInfo)

			var exchangeCoinInfo = new Function ('return ' + new_rsp.exchangeCoinInfo)();
			exchangeCoinInfo = [...defaultCoinInfo,...exchangeCoinInfo]
            console.log("select::wallet_coin_info +++", exchangeCoinInfo);
            set(wallet_coin_info, exchangeCoinInfo)
            console.log("select::wallet_coin_info ---");

    }
});

