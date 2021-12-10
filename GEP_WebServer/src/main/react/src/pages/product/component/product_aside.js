import { useEffect, useState } from "react";
import { getLocaleObject } from "../../../module/utils/language.util";



export default function ProductASide({tab,setTab}) {
 
	useEffect(()=>{

	}, [])

  let payObj = getLocaleObject("product.pay");
	return (
	<aside>
        <h1 className="menutitle">
          상품*서비스
        </h1>
        <ul className="vtab">
          <li onClick={()=>setTab(1)}><a href="#vtab1" className = {tab === 1 ? "vselected":""}>{payObj.menu}</a></li>
          {/* <li onClick={()=>setTab(2)}><a href="#vtab2" className = {tab === 2 ? "vselected":""}>페이 전송</a></li> */}
          <li onClick={()=>setTab(3)}><a href="#vtab3" className = {tab === 3 ? "vselected":""}>더드림정액쿠폰</a></li>
        </ul>
	</aside>
	);
}