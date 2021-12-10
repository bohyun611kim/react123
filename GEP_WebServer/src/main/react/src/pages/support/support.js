import { useEffect, useState } from "react";
import {  useLocation } from 'react-router-dom';
import { RecoilRoot, useRecoilState,useRecoilValue,useRecoilValueLoadable, useSetRecoilState } from "recoil";
import { BASE_PREFIX } from "../../module/myconstants";
import request from "../../module/services/request.service";
import { toFormData } from "../../module/utils/convert.util";
import LocaleEnPrivacy from "../policy/LocaleEnPrivacy";
import LocaleKoPrivacy from "../policy/LocaleKoPrivacy";
import LocaleEnResign from "../policy/LocaleEnResign";
import LocaleKoResign from "../policy/LocaleKoResign";
import LocaleEnTerms from "../policy/LocaleEnTerms";
import LocaleKoTerms from "../policy/LocaleKoTerms";
import { getLocaleContent } from "../../module/utils/language.util";


const Support = () => {

	
	//const [param, setParam] = useState({});

  
  useEffect(()=>{
	console.log("--- useEffect------", __G_Notice_Cont);
  }, [])


	return (
	<>
	</>
	);
}

function SupportRoot({cont}) {
	
  	var locale = localStorage.getItem("common/LANGUAGE");
	var content = null;
	const [status, setStatus] = useState(cont === "privacy"? 2 : 1);

	const onChangeTabs = (e,status) => {
		e.preventDefault();
		setStatus(status);
		if(status === 1){
			location.href = "/site/support?cont=terms";
			// location.href = "/site/support?cont=about";
	   	}
		else if(status === 2){
			// location.href = "/site/support?cont=terms";
			location.href = "/site/support?cont=privacy";
		}
		else{
			location.href = "/site/support?cont=privacy";
		}
		
	}
   

	console.log("content:", cont)
	if (cont == "privacy") {
		content = locale == 'en' ? <LocaleEnPrivacy/> : <LocaleKoPrivacy/>;
		document.querySelector('#content').style.display = "none";
	} else if (cont == "terms") {
		content = locale == 'en' ? <LocaleEnTerms/> : <LocaleKoTerms/>;
		document.querySelector('#content').style.display = "none";
	} else if (cont == "about") {
		content = locale == 'en' ? <LocaleEnResign/> : <LocaleKoResign/>;
		document.querySelector('#content').style.display = "none";
	}
	else if (cont == "null") {
		document.querySelector('#like_support').style.display = "none";
	}
	else {
		content = <></>
	}

	return (
		
		<RecoilRoot>
			<article className="layoutD">
				<div className="box_polity">
					<div className="table01_top">
						<ul className="tab">
							{getLocaleContent("object","user.terms").map((item, index) => 
								<li key={index} className={(index+1) === status ? "on" : ""} onClick={(e) => onChangeTabs(e,index+1)}>{item}</li>)}
						</ul>
					</div>
					{content}
				</div>
			</article>
		</RecoilRoot>
	);
}

let domContainer = document.querySelector('#like_support');
ReactDOM.render(<SupportRoot cont={domContainer.getAttribute('cont')} />, domContainer);

