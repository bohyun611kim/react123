import { useEffect, useState } from "react";
import { RecoilRoot, useRecoilState,useRecoilValue,useRecoilValueLoadable, useSetRecoilState } from "recoil";
import { BASE_PREFIX } from "../../module/myconstants";
import request from "../../module/services/request.service";
import { is_empty } from "../../module/utils/misc.util";
import ProductASide from "./component/product_aside";
import ProductPay from "./component/product_pay";
import ProductPayEx from "./component/product_pay_ex";
import ProductCoupon from "./component/product_coupon";
import ProductCouponBuy from "./component/product_coupon_buy";
import { product_coupon_buy } from "../../module/stores/modal_state";

function ProductService() {
	const [tab,setTab] = useState(1)

	const buyInfo = useRecoilValue(product_coupon_buy);

	return (
		<>
		{ buyInfo.show ? <ProductCouponBuy buyInfo={buyInfo}/> : null}
		<article className="layoutD">
			<ProductASide tab={tab} setTab={setTab}/>
			<div className="content">
				<ul className="vpanel"> 
					{tab === 1 ? <ProductPayEx/> : null}
					{tab === 2 ? <ProductPay/> : null}
					{tab === 3 ? <ProductCoupon/> : null}
				</ul>
			</div>
		</article>
		</>
	);
}

function ProductRoot() {
	return (
		<RecoilRoot>
			<ProductService/>
		</RecoilRoot>
	);
}

let domContainer = document.querySelector('#content');
ReactDOM.render(<ProductRoot/>, domContainer);

