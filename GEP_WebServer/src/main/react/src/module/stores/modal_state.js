import {atom, selector, useSetRecoilState} from 'recoil';
// import { BASE_PREFIX } from '../myconstants';
// import request from '../services/request.service';


/* product popup */
export const product_coupon_buy = atom({
    key: 'product/coupon_buy', 
    default: {show:false, update:false, amount:0, bonus:0, krw:0}
});

