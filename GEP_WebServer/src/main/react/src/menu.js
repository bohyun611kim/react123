import {useEffect, useState} from 'react'

import { BASE_PREFIX } from "./module/myconstants";
import request from "./module/services/request.service";
import StorageService from "./module/services/storage.service";
import { toFormData } from "./module/utils/convert.util";
import { getLocaleObject, getLocaleContent } from './module/utils/language.util';
//const { useState, useEffect } = require("react");


export function openNavMobile(){
	document.getElementById("m_nav").style.right = "0%";
}
export function closeNavMobile(){
	document.getElementById("m_nav").style.right = "-100%";
}

function LikeHeader({sign}) {
	//console.log("LikeHeader> location", location);

	if (new StorageService().language.get() == undefined) {
		new StorageService().language.set('ko')
	}
	// new StorageService().language.set('ko')

	const [isLogin, setLogin] = useState(0);
	const [locale, setLocale] = useState(new StorageService().language.get());

	const handleEnter = el => el.style.right = '0%'
  	const handleExit = el => el.style.right = '-100%'

	useEffect(()=> {
		setLogin(sign);
		__set_locale(locale)
	}, []);

	const handleClick = (e, path) =>{
		e.preventDefault();
		location.href=path;

	}

	const __set_locale = async (locale) => {
		var param = {locale:locale}
		try {
			const rsp = await request.post(BASE_PREFIX+'/api/home/setLocale', toFormData(param)).then(response => response.data)
		} catch (error){
			console.log("failed to parse:", error);
			//location.href = "/";
			return null;
		}
	}

	const handleLocale = e=> {
		e.preventDefault();
		console.log("handleLocale", e.target.value);
		new StorageService().language.set(e.target.value)
		// setLocale(locale);
		location.reload()
	}

	const handleBlur = e=> {
		e.preventDefault();
		console.log("onBlur -------------------");
		closeNavMobile();
	}
	
	var path = location.pathname;
	return (
		<div className={`header${path === "/" ? " header_new" : ""}`}>
		  {/* menu PC */}
		  <div id="sub_top" className="sub_top_new">
			<div className="inner_new">
			<div className="s_logo">
				<a href="/">
				<img src="/resources/img/newbita/logo.png" alt="Logo" />
				</a>
			</div>
			<div className="gnb">
				<select name="language.selector" className="select_lang" onChange={handleLocale}>
					<option value="en" selected={locale == 'en' ? true : false}>English</option>
					<option value="ko" selected={locale == 'ko' ? true : false}>Korean</option>
					<option value="po" selected={locale == 'po' ? true : false}>Portuguese</option>
					{/* <option value="id" selected={locale == 'ko' ? true : false}>Indonesian</option> */}
					{/* <option value="zh" selected={locale == 'ko' ? true : false}>Chinese</option> */}
				</select>
				<ul className="gnb_btn_new">
				<li id="active">
				{sign == 0 ?  <a href="signup" class="gnb_signup" onClick={e=>handleClick(e, "/site/register")}>{getLocaleContent("word","signup")}</a> : <a href="logout" class="gnb_signup" onClick={e=>handleClick(e, "/site/logout")}>{getLocaleContent("word","logout")}</a> }
				</li>
				<li>
				{sign == 0 ?  <a href="login" class="gnb_login" onClick={e=>handleClick(e, "/site/login")}>{getLocaleContent("word","signin")}</a> : <a href="mypage" class="gnb_login" onClick={e=>handleClick(e, "/site/info")}>{getLocaleContent("word","mypage")}</a> }
				</li> 
				</ul>
				<div className="sc-bdVaJa dXfIaQ">
				<ul className="gnb_menu">
					{	getLocaleObject('menu.list').map((item, index) => {
						return <li key={index} className="nav-item">
									<a href={item.path} className={path == `${item.path}` ? "is-active" : ""} onClick={e=>handleClick(e, `${item.path}`)}>{item.title}</a>
								</li>
						})
					}
				</ul>
				</div>
			</div>
			</div>
		</div>


		 {/* menu mobile */}
			<div className="m_top">
				<div className="inner">
				<div className="m_logo">
					<a href="/">
					<img src="/resources/img/newbita/logo.png" alt="Logo" />
					</a>
				</div>
				<div className="m_btn" onClick={openNavMobile}>
					<img src="/resources/img/toggle.png" alt="toggle" />
				</div>
				</div>
				<aside className="global-sidebar">
				<div id="m_nav" className="slide-side-enter-done" style={{right: '-100%'}} onBlur={handleBlur}>
					<div className="m_nav_inner">
					<div className="close">
						<img src="/resources/img/modal_close.png" alt="close" onClick={closeNavMobile}/>
					</div>
					<ul className="m_nav_btnbox">
						<li>
						{sign == 0 ?  <a href="" onClick={e=>handleClick(e, "/site/login")}>Login</a> : <a href="" onClick={e=>handleClick(e, "/site/info")}>My Page</a> }
						</li>
						<li>
						{sign == 0 ?  <a href="" onClick={e=>handleClick(e, "/site/register")}>Sign up</a> : <a href="" onClick={e=>handleClick(e, "/site/logout")}>Log out</a> }
						</li>
					</ul>
					<select name="language.selector" className="m_select_lan" onChange={handleLocale}>
						<option value="en">English</option>
						<option value="ko">Korean</option>
						<option value="po">Portuguese</option>
						{/* <option value="ja">Japanese</option> */}
						{/* <option value="id">Indonesian</option> */}
						{/* <option value="zh">Chinese</option> */}
					</select>
					<ul>
						{	getLocaleObject('menu.list').map((item, index) => {
							return <li key={index} className="m_nav_menu">
										<a className={`m_menu_dep01 ${path == `${item.path}` ? "is-active" : ""}`} href={item.path} onClick={e=>handleClick(e, `${item.path}`)}>{item.title}</a>
									</li>
							})
						}
					</ul>
					</div>
				</div>
				</aside>
			</div>

		</div>
	);
}
  
let domContainer = document.querySelector('#like_header');
ReactDOM.render(<LikeHeader sign={domContainer.getAttribute('sign')} />, domContainer);
