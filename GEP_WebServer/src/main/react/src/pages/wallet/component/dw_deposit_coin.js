import { useEffect, useRef, useState } from "react";
import { useRecoilValue } from "recoil";
import { toFormData } from "../../../module/utils/convert.util";
import { is_empty } from "../../../module/utils/misc.util";
import { BASE_PREFIX, BASE_COIN_DIV } from "../../../module/myconstants";
import request from "../../../module/services/request.service";
import { getLocaleContent, getLocaleObject } from "../../../module/utils/language.util";
import { CheckLogin } from "../../../module/withAuth";

const wordReplace = (item, symbol) => {
	return item.replace( /coin_name/gi, symbol)
}

function copyToClipBoardAddress() {
    su.copy($('#coin-tab-1-user-wallet-address').text());
    lrt.client(getLocaleContent('word', "address_copy"), getLocaleContent('word', "_ok"), null, null);
}

function CreateAddress({coin, onCreate}) {
	return (
		<>
			<ul className="disc">
				{ getLocaleObject("wallet.notices", "deposit", "newaddress").map((item, index) => <li key={index}>{wordReplace(item, coin.COIN_SYMBOLIC_NM)}</li>) }
			</ul>
			{/* <!-- 입금계좌 미등록 회원인 경우 - 입금주소 생성 버튼 : Start --> */}
			<div className="btn-group">
				<ul>
					<li>
						<button className="blue big" onClick={e=>onCreate(e, coin)}>
							<span>{coin.COIN_SYMBOLIC_NM}</span> 
							<span>{getLocaleContent("word", "depo_address_create")}</span>
						</button>
					</li>
				</ul>
			</div>
		</>
	);
}

function AddressInfo({coin, depositInfo}) {
	// console.log("AddressInfo:", depositInfo);
	const aria = useRef();

	//$('#coin-tab-1-user-wallet-address').text(address);

	const handleDepositPathParam = address => {
        const searchParams = {chs: '123x123', cht: 'qr', chl: address, choe: 'UTF-8'}
        return `https://chart.googleapis.com/chart${'?'}${new URLSearchParams(searchParams).toString()}`
	}
	const wordReplace = (item,symbol) => {

		// console.log("test deposit minimum value",coin.MIN_DEPOSIT_QTY)
		// console.log("test sybol", symbol)
		var d_min_limit = coin.MIN_DEPOSIT_QTY ? coin.MIN_DEPOSIT_QTY : 0;
		return (
			item.replace('symbol',  symbol ? symbol:" ").replace('deposit_minimum_limit', d_min_limit)
		)
	}

	return (
	<>
		<div className="my-address-wrap">
			<div className="my-addr-group">
				<p className="my-addr-title"><span className="subtitle">&nbsp;{coin.ITEM_KOR_NM} </span> {getLocaleContent('word', "depositaddress")}</p>
				<div className="my-addr">
					<p id="coin-tab-1-user-wallet-address" ref={aria} class="my-addr-value">{depositInfo.DEPOSIT_WALLET_ADDR}</p>
					<p className="fl-left">
						<button onClick={()=>copyToClipBoardAddress()}>{getLocaleContent('word', 'address') + getLocaleContent('word', '_copy')}</button>
					</p>
					{is_empty(depositInfo.ADDR_ETC1) ? "" : <><p className="extra-title">{depositInfo.ADDR_ETC1_NM}</p><input type="text" id="des_input" defaultValue={depositInfo.ADDR_ETC1} readOnly/></> }
					{is_empty(depositInfo.ADDR_ETC1) ? "" : <><p className="important">{depositInfo.ADDR_ETC1_NM} {getLocaleContent('word', 'depo_warning1')}</p></> }
				</div>
			</div>
			<div id="coin-tab-1-user-wallet-address-qrcode" className="my-addr-qr">
				<img src={handleDepositPathParam(depositInfo.DEPOSIT_WALLET_ADDR)} alt="qrcode" style={{display: 'block', margin: '0 auto'}}/>
				{/* <!-- QR코드. background로 해당하는 이미지를 import. --> */}
				{/* <span className="" style={{background:"url('/resources/img/temp-qrimg.png')", backgroundRepeat:"no-repeat"}}>QR코드</span> */}
			</div>
		</div>
		<div class="my-addr-important">
			<p class="important">Important</p>
			<ul>
			{ getLocaleObject("wallet.notices", "deposit", "address").map((item, index) => <li key={index}>{wordReplace(item, coin.COIN_SYMBOLIC_NM)}</li>) }
			</ul>
		</div>
	</>
	);
}

export default function DepositCoinTab({coin}) {

	// console.log("DepositCoinTab:",coin);

	const [exist_wallet, setWalletExist] = useState(false);
	const [wallet, setWallet] = useState("");
	const [depositInfo, setDepositInfo] = useState({});

	useEffect(() => {
		_get_deposit_info(coin);
	}, [coin])

    const _get_deposit_info = async (coin) => {
		var param = {coinNo : coin.COIN_NO}
		const rsp = await request.post(BASE_PREFIX+'/api/wallet/wlt001/selectCoinDepositWalletInfoByUserIdAndCoinNo', toFormData(param)).then(response => response.data)
		// console.log("_get_deposit_info---:",rsp);

		try {
			if (is_empty(rsp)) {
				setDepositInfo({DEPOSIT_WALLET_ADDR:"", ADDR_ETC1:"", ADDR_ETC1_NM:""})
				setWalletExist(false);
				CheckLogin()
			} else  {
				setWalletExist(true);
				setDepositInfo(rsp)
				// setWallet(rsp.DEPOSIT_WALLET_ADDR);
			}
		} catch (error){
			console.log("failed to parse:", error);
			//location.href = "/";
			return null;
		}
	}

	
    const handleCreate = async (e, coin) => {
		e.preventDefault();
		var param = {coinNo : coin.COIN_NO , coinSymbolicNm : coin.COIN_SYMBOLIC_NM }
		const rsp = await request.post(BASE_PREFIX+'/api/wallet/wlt001/createNewCoinAddress', toFormData(param)).then(response => response.data)
		console.log("handleCreate---:",rsp);

		try {
			var wallet = new Function ('return ' + rsp.body)();
			console.log("handleCreate --- parse:", wallet);
			if (wallet.resCode == 0) {
				setWalletExist(true);
				setWallet(wallet.newCoinAccount);
			} else {
				lrt.client(wallet.msg, '실패', null, null);
			}
		} catch (error){
			console.log("failed to parse:", error);
			//Router.push("/");	
			return null;
		}
	}


	return (
	<div className="ui-tabs-panel ui-corner-bottom ui-widget-content">
		<div id="tabs-1">
			<div className="content-item">
				<h3 className="subtitle mgt20">
					<span className="subtitle">{coin.ITEM_KOR_NM}</span> {getLocaleContent('word', 'deposit')}
				</h3>
				{exist_wallet == false ?  <CreateAddress coin={coin} onCreate={handleCreate}/> : <AddressInfo coin={coin} depositInfo={depositInfo}/>}

				<ul className="disc">
					{ getLocaleObject("wallet.notices", "deposit", "warning").map((item, index) => <li key={index}>{wordReplace(item, coin.COIN_SYMBOLIC_NM)}</li>) }
				</ul>
			</div>
		</div>
	</div>
	);
}