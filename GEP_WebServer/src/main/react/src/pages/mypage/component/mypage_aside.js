import { useEffect, useState } from "react";
import { getLocaleObject } from "../../../module/utils/language.util";
import MyPagePopUpCopy from "../../../../../webapp/resources/js/holdport/mypage/holdport.mypage";
// 다국어 지원  
import getMyPageLocaleString from "../../../../../webapp/resources/js/holdport/mypage/holdport.mypage";


export default function MyPageSide({tab,setTab}) {
 
	useEffect(()=>{

	}, [])

  
  

	return (
    
	<aside>
        <h1 className="menutitle">
          {getMyPageLocaleString('_L_MY01')}
        </h1>
        <ul className="vtab">
          <li onClick={()=>setTab(1)}><a className = {tab === 1 ? "vselected":""}>{getMyPageLocaleString('_L_MY01')}</a></li>
          <li onClick={()=>setTab(2)}><a className = {tab === 2 ? "vselected":""}>{getMyPageLocaleString('_L_MY02')}</a></li>
          <li onClick={()=>setTab(3)}><a className = {tab === 3 ? "vselected":""}>{getMyPageLocaleString('_L_MY03')}</a></li>
          <li onClick={()=>setTab(4)}><a className = {tab === 4 ? "vselected":""}>{getMyPageLocaleString('_L_MY04')}</a></li>
        </ul>
	</aside>
	);
}