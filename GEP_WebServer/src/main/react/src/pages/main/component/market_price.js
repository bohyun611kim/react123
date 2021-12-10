import { useEffect, useState } from "react";
import { BASE_PREFIX } from "../../../module/myconstants";
import request from "../../../module/services/request.service";
import { toLocale, toDecimalLimit, toFloorDecimals} from "../../../module/utils/calculate.util";
import { toFormData } from "../../../module/utils/convert.util";
import { getLocaleObject, getLocaleContent} from '../../../module/utils/language.util'


function MostCoinItem ({item,selectedMarket}) {
  let prcDevAmt = item.prcDevAmt
  , plusMinus = prcDevAmt === 0 ? '':prcDevAmt > 0 ? '+ ':''
  , color = prcDevAmt === 0 ? '':prcDevAmt > 0 ? 'red':'blue';

  var locale = localStorage.getItem("common/LANGUAGE");
  var coinName = locale == 'ko' ? item.itemDomesticNm : item.itemEngNm;

  const handleClick = (e, target) => {
    e.preventDefault();
    location.href = "/site/exchange?coin=" + target;
  }

  var symbol = item.itemNm.split('/')[0];
  var baseCoin = item.itemNm.split('/')[1];

  // console.log("item >> ", item);
  return (
      <tr onClick={e=>handleClick(e, item.itemCode)}>
        <td className="coin">
          {/* <img src={`/resources/img/coin-symbol/${symbol}.png`} alt={item.itemNm} /> {coinName} */}
          <img src={`/resources/img/coin-symbol/${symbol}.png`} alt={item.itemNm} />
          {item.itemNm.split('/')[0]}/{selectedMarket}
        </td>
        {/* <td>{item.itemNm.split('/')[0]}</td> */}
        <td>{toLocale(item.closePrc,item.prcDspDecPnt)} {baseCoin}</td>
        <td className={item.prcDevRate < 0 ? "blue" : "red" }>
          <span className={item.prcDevRate < 0 ? "rate up01" : "rate up02" } />
          <span>{toLocale(item.prcDevAmt, item.prcDspDecPnt)}({toLocale(item.prcDevRate,2)}%)</span>
        </td>
        <td>{toLocale(item.highPrc,item.prcDspDecPnt)}</td>
        <td>{toLocale(item.lowPrc,item.prcDspDecPnt)}</td>
        <td>{toLocale(item.tradeAmt,item.prcDspDecPnt)}</td>
      </tr>

  );
}

export default function MarketCoinList({marketList}) {

	const [market, setMarket] = useState(null);
	const [coinList, setCoinList] = useState([]);
	const [selectedMarket, setSelectMakrket] = useState('KRW');
	const [moreList, setMoreList] = useState(false);

  useEffect(() => {
    setMarket(marketList)
    var mk = marketList.filter(it => it.mktNm == selectedMarket)
    setCoinList(mk[0].item)
  }, [marketList])

	const handlerCoinFilter =(e)=>{
    var coinlist = null;
    if (e.target.value == "") {

      coinlist = market.filter(it => it.mktNm == selectedMarket)[0].item

    } else {
      const filteredCoin = coinList.filter((it) => {
        var symbol = it.itemNm.split('/')[0];
        var k_name = it.itemKorNm;
        return symbol.toLowerCase().includes(e.target.value.toLowerCase()) || (k_name && k_name.includes(e.target.value));
      });
      coinlist = filteredCoin;
      if (filteredCoin.length == 0) {
        coinlist = market.filter(it => it.mktNm == selectedMarket)[0].item
      }
    }

    setCoinList(coinlist)
	}

  const handleMarketClick = (e, markettab) => {
    setCoinList(market.filter(it => it.mktNm == markettab)[0].item)
    setSelectMakrket(markettab)
  }

  // console.log("+++++++++selected, coinList", selectedMarket, coinList);
  if (coinList == undefined) return null;


	return (
    <div className="list_data_new">
      <div className="market-price-header table01_top">
        <ul className="tab">
          <li className={selectedMarket == "KRW" ? "on" : ""} onClick={e => handleMarketClick(e, "KRW")}>KRW</li>
          <li className={selectedMarket == "BTC" ? "on" : ""} onClick={e => handleMarketClick(e, "BTC")}>BTC</li>
          <li className={selectedMarket == "USDT" ? "on" : ""} onClick={e => handleMarketClick(e, "USDT")}>USDT</li>
        </ul>
        <input type="text" className="srch" placeholder="Search Coins" defaultValue ="" onChange={handlerCoinFilter}  />
      </div>
      <div className="market-price-body">
        <table className="table01">
          <thead>
            <tr className="table01_title">
            {getLocaleContent("object","intro.market.titles").map((item, index) => {
						  return <th key={index}>{item}</th>
					  })}
            </tr>
          </thead>
          <tbody>
           {coinList.length > 0 && coinList.map((item, index) => {
             if (moreList || (!moreList && index < 5) ) {
                return <MostCoinItem item={item} idx={index} selectedMarket={selectedMarket}/>
             } 
           } )}
          </tbody>
        </table>
        <button className="btn_price" onClick={e=>setMoreList(!moreList)}>{getLocaleContent("word","btn_view")}</button>
      </div>
    </div>
  );

}
