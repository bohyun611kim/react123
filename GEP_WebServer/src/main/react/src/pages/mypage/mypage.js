import { useEffect, useState } from "react";
import { RecoilRoot, useRecoilState,useRecoilValue,useRecoilValueLoadable, useSetRecoilState } from "recoil";
import { BASE_PREFIX } from "../../module/myconstants";
import request from "../../module/services/request.service";
import { is_empty } from "../../module/utils/misc.util";
import MyPageASide from "./component/mypage_aside";
import MyPageInfo from "./component/mypage_info";
import MyPageAuth from "./component/mypage_auth";
import MyPageHistory from "./component/mypage_history";
import MyPageOpenAPI from "./component/mypage_openapi";
import MyPageChangePw from "./component/mypage_changepw";
import MyPagePopUpCopy from "./component/mypage_copy";

function MyPageService() {
	const [tab,setTab] = useState(1)
	const [userInfo,setUserInfo] = useState({})
	const [changePW,setChangePW] = useState(0)
	const [popCopy,setPopCopy] = useState(0)

	useEffect(()=> {
		_get_user_info();
	}, [])

	const _get_user_info = async () => {
		const rsp = await request .post(BASE_PREFIX + "/api/info", null) .then((response) => response.data);
		console.log("info ---:", rsp);
	
		try {
			setUserInfo(rsp)
		} catch (error) {
		  console.log("failed to parse:", error);
		  //location.href = "/";
		  return null;
		}
	};


	return (
		<> 
		{changePW === 1 ? <MyPageChangePw setChangePW={setChangePW} /> : null}
		{popCopy === 1 ? <MyPagePopUpCopy setPopCopy={setPopCopy}/> : null}
		<article className="layoutD">
			<MyPageASide tab={tab} setTab={setTab}/>
			<div className="content">
				<ul className="vpanel"> 
					{tab === 1 ? <MyPageInfo userInfo={userInfo} setChangePW={setChangePW} setTab={setTab} setPopCopy = {setPopCopy}/> : null}
					{tab === 2 ? <MyPageAuth userInfo={userInfo}/> : null}
					{tab === 3 ? <MyPageHistory userInfo={userInfo}/> : null}
					{tab === 4 ? <MyPageOpenAPI userInfo={userInfo}/> : null}
				</ul>
			</div>
		</article>
		</>
	);
}

function MyPageRoot() {
	return (
		<RecoilRoot>
			<MyPageService/>
		</RecoilRoot>
	);
}

let domContainer = document.querySelector('#like_mypage');
ReactDOM.render(<MyPageRoot/>, domContainer);

