import { useEffect, useState } from "react";
import { useRecoilState, useRecoilValue } from "recoil";
import {wallet_coin_selected, wallet_base_coin, wallet_coin_ref, wallet_coin_info, wallet_user_info} from "../../../module/stores/wallet_state"
import { toDecimalLimit, toLocale } from "../../../module/utils/calculate.util";
import { getLocaleObject } from "../../../module/utils/language.util";
//import styled from 'styled-components'

// const CoinList = styled.div`
//     .ex_sec03_list {
//         @media screen and (min-width: 768px) {
//             ::-webkit-scrollbar {width: 1px; position: relative;}
//             ::-webkit-scrollbar-button:start:decrement, 
//             ::-webkit-scrollbar-button:end:increment {display: block; width: 0;height: 0; background: url() rgba(0,0,0,.05);}
//             ::-webkit-scrollbar-track {  background: rgba(0,0,0,.07); border-radius: 15px;}
//             ::-webkit-scrollbar-thumb {  background: rgba(0,0,0,.1);  border-radius: 15px;}
//         }
//     }
// `;

const CoinItem = ({item, idx, handleClick, active, mine}) => {


	//if (mine.length == 0) return null;
	//console.log("=======>mine.filter check:", item);

	var symbol = item.COIN_SYMBOLIC_NM;
	var my =  mine.length > 0 && mine.filter(it => symbol == it.POSS_ASSET)


	//var balance = my.length > 0 ?  nu.cm(my[0].USABLE_QTY, __G_CoinMgtRef_Map.get(item.COIN_NO).WTDRW_DECIMAL_PNT) : 0 ;
	var balance = my.length > 0 ? toLocale(my[0].USABLE_QTY, item.WTDRW_DECIMAL_PNT) : 0 ;
	var bybc = my.length > 0 ?  nu.cm(my[0].TOTAL_USABLE_BY_BC, item.WTDRW_DECIMAL_PNT) : 0;

	//console.log("CoinItem symbolc:", item);

	return (
	<tr className={active === idx ? "active" : ""} onClick={(e) => { handleClick(idx, item.COIN_SYMBOLIC_NM, item.COIN_NO)}}>
		<td>
			<span className="coin-symbol">
				{/* <img onError={handleError} src={`/resources/img/coin-symbol/${item.COIN_SYMBOLIC_NM}.png`} alt="coin"/> */}
				<img src={`/resources/img/coin-symbol/${item.COIN_SYMBOLIC_NM}.png`} alt="coin"/>
			</span>
		</td>
		<td>
			<p className="coin-name">{item.ITEM_KOR_NM ? item.ITEM_KOR_NM : item.COIN_SYMBOLIC_NM }</p>
		</td>
		<td>
			<p className="vertual-coin-type">
				<span>{balance}</span>
				<span>{item.COIN_SYMBOLIC_NM || my.POSS_ASSET}</span>
			</p>
			<p className="country-real-money">
				<span>{bybc}</span>
				<span>KRW</span>
			</p>
		</td>
	</tr>
	);
}

export default function InvestAside() {

	const [ischeck, setCheck] = useState(false);


	//const base = useRecoilValue(wallet_base_coin);
	const coins = useRecoilValue(wallet_coin_info);
	const user_coin = useRecoilValue(wallet_user_info);
	const coin_ref = useRecoilValue(wallet_coin_ref);
	const [coinSelected, setCoinSelected] = useRecoilState(wallet_coin_selected);


	const [coinList, setCoinList] = useState(null);

	useEffect(()=> {
	}, []);

	useEffect(()=>{

		if (ischeck){
			var filtered = user_coin.map(it=> it.POSS_ASSET)
			const filteredCoin = coins.filter((co) => {
				var symbol = co.COIN_SYMBOLIC_NM;
				return filtered.includes(symbol);
			});
			setCoinList([...filteredCoin]);
		} else {
			setCoinList(coins);
		}
	}, [ischeck])

	const handleClick = (idx, name, no) => {
		__G_Prev_Selected_CoinNo = no;
		setCoinSelected({index:idx, coin:name, no:no});
	}

	const handlerCoinFilter =(e)=>{
		const filteredCoin = coins.filter((co) => {
			var symbol = co.COIN_SYMBOLIC_NM;
			var k_name = co.ITEM_KOR_NM;
			return symbol.toLowerCase().includes(e.target.value.toLowerCase()) || (k_name && k_name.includes(e.target.value));
		});
		setCoinList([...filteredCoin]);
	}

	//console.log("Coin filterd:", coinList);
	if (user_coin == null) return null;


	return (
		<aside>
			<ul>
				<li>
					<div className="whole-asset-group">
						<span className="txt-box">총 평가액</span>
						<span className="asset-value" id="total_estimated_qty">{user_coin.GRAND_TOTAL_POSS_BY_BC}</span>
						<span className="asset-unit">KRW</span>
					</div>
				</li>
				<li>
					<div className="sch-coin-group">
						<p className="sch-coin-input">
							<input type="text" defaultValue="" onChange={handlerCoinFilter} placeholder="암호화폐/심볼 검색" className="sch-input"/>
						</p>
						<span className="checks-item" onClick={e=>setCheck(!ischeck)}>
							<input type="checkbox" checked={ischeck}/>
							<label>보유</label>
						</span>

					</div>
				</li>

				{/* <!-- 코인 리스트. 10개 이상일 때는 스크롤 생김 : Start --> */}
				<li className="coin-list-wrap">
				<div className="vpanel" style={{maxHeight: 'none'}}  tabIndex={0}>
    	          <div style={{position: 'relative', top: '0px', left: '0px'}} dir="ltr">
						<table className="coin-list-inner">
							<colgroup>
							<col style={{width:'30px'}} />
							<col style={{width:'80px'}} />
							<col />
							</colgroup>
							<tbody id='wallet-lnb-coin-list-tbody'>
							{/* 리스트 1개 반복 : Start */}
							{ coinList && coinList.map((item,index) => <CoinItem idx={index} item={item} mine={user_coin} coinRef={coin_ref} handleClick={handleClick} active={coinSelected.index}/>)}
							{/* //리스트 1개 반복 : End */}
							</tbody>
						</table>
					</div>
				</div>
				</li>
				{/* </CoinListScroll> */}
			</ul>
			<ul className="wlt_info">
			{ getLocaleObject("wallet.aside").map((item, index) => <li key={index}> {item} </li>) }
			</ul>
		</aside>

	);
}