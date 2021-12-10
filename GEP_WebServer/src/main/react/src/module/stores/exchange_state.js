import {atom, selector, useSetRecoilState} from 'recoil';
// import { BASE_PREFIX } from '../myconstants';
// import request from '../services/request.service';


/* market action */
export const MARKET_ACTION_BASE_LIST = atom({
    key: 'wallet/action/update_base_list', 
    default: 0
});

/* market */
export const market_coin_selected = atom({
    key: 'market/coin_selected', 
    default: { index: 0 , coin : "BTC", no : "20"},
});
  
export const market_tab_selected = atom({
    key: 'market/tab_selected', 
    default: {no:10, coin:"KRW"},
});
