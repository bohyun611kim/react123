import { useEffect, useLayoutEffect, useRef, useState } from "react";
import { BASE_PREFIX, DIFF_MONTH} from "../../../module/myconstants";
import request from "../../../module/services/request.service";
import DatePicker from "react-jqueryui-datepicker"
import { format_date } from "../../../module/utils/misc.util";
import { toFormData } from "../../../module/utils/convert.util";
import { useRecoilValue } from "recoil";
import { wallet_coin_selected , wallet_tab_selected} from "../../../module/stores/wallet_state";
import { DWHistoryCurrencyList, DWHistoryCurrencyTotal} from "./dw_history_currency";
import { DWHistoryCoinList, DWHistoryCoinTotal } from "./dw_history_coin";
import { getLocaleContent } from "../../../module/utils/language.util";




function DWHistoryHeader({onSubmit, onTotal}) {

	var now = new Date();
	var start = format_date(new Date(now.setMonth(now.getMonth() - DIFF_MONTH)))
	var end = format_date(new Date())
	const [startDate, setStartDate] = useState(start);
	const [endDate, setEndDate] = useState(end);


	const handleSubmit = (e) => {
		e.preventDefault();
		onSubmit(startDate, endDate)
		onTotal(startDate, endDate, 1)
	}


	return(
		<div className="mgt20">
			<select className="select-box" >
				<option selected>{getLocaleContent('word', 'all_')}</option>
				<option>{getLocaleContent('word', 'withdraw')}</option>
				<option>{getLocaleContent('word', 'deposit')}</option>
			</select>

			<div className="calendar" >
				<DatePicker onChange={e=>setStartDate(e._i)} value={startDate} />
				<span>&nbsp;~&nbsp;</span>
				<DatePicker onChange={e=>setEndDate(e._i)} value={endDate} />
				&nbsp;&nbsp;<button className="red" onClick={handleSubmit}>조회</button>
			</div>
		</div>
	);
}

function getCoinPaging(setPage, rsp) {
	// console.log("++++++++ getCoinPagin ++++++++++++", rsp);
    // Paging
    __G_Coin_DW_List_window = Pu.init({
        count: 10,
        PageCount: 5,
        draw: (n)=>{
			setPage(n);
		},
        button: '#coin-tab-3-dw-list-btn',
        paginate: '#coin-tab-3-dw-list-pagination',
        totalText: '#coin-tab-3-dw-list-total-count',
        initCllBck: true,
		type: 'search',
        initEventFun: (p, pcallback)=>{ 
			console.log("initEventFun", p);
			pcallback(rsp);
		}
	});
	
}

function getMoneyPaging(setPage, rsp) {
	// console.log("++++++++ getMoneyPaging ++++++++++++", rsp);
    __G_Money_DW_List_window = Pu.init({
        count: 10,
        PageCount: 5,
        draw: (n)=>{
			setPage(n);
		},
        button: '#money-tab-3-dw-list-btn',
        paginate: '#money-tab-3-dw-list-pagination',
        totalText: '#money-tab-3-dw-list-total-count',
        initCllBck: true,
        type: 'search',
        initEventFun: (p, pcallback)=>{ 
			console.log("initEventFun", p);
			pcallback(rsp);
		}
    });
}


function DWHistoryTab(props){
	
	const [histList, setHistList] = useState(null);
	const [pageNum, setPage] = useState(1);
	const [totalCount, setTotalCount] = useState({coin:'KRW' , no:0});
	const selectedCoin = useRecoilValue(wallet_coin_selected);
	const selectedTab = useRecoilValue(wallet_tab_selected);

	var now = new Date();
	var start = format_date(new Date(now.setMonth(now.getMonth() - DIFF_MONTH)))
	var end = format_date(new Date())
	
	useEffect(()=>{
	}, [selectedTab]);

	useEffect(()=>{
		// handleSubmit(start, end, pageNum);
		handleTotalCount(start, end, pageNum)
		handleSubmit(start, end, pageNum);
	}, [pageNum, selectedTab]);

	const handleSubmit = async (start, end, pageNum) => {
		console.log("handleSubmit-", pageNum)
		if (pageNum === undefined) pageNum = 1;
		var param = {coinNo: selectedCoin.no, dwReqTypeCd: 0, startDate: start, endDate: end, pageNum: pageNum}
		const rsp = await request.post(BASE_PREFIX+'/wallet/wlt001/selectCoinBalanceHistList', toFormData(param)).then(response => response.data)
		try {
			setHistList(rsp)
		} catch (error){
			console.log("failed to parse:", error);
		}
	}

	const handleTotalCount = async (start, end, page) => {
		var param = {coinNo: selectedCoin.no, dwReqTypeCd: 0, startDate: start, endDate: end, pageNum: page}
		const rsp = await request.post(BASE_PREFIX+'/wallet/wlt001/selectCoinBalanceHistListCount', toFormData(param)).then(response => response.data)
		//console.log("handleTotalCount++++", rsp);
		setTotalCount({coin: selectedCoin.coin, no:rsp.size});
		selectedCoin.coin == 'KRW' ?  getMoneyPaging(setPage, rsp) : getCoinPaging(setPage, rsp);
		return rsp;
	}
	return (
	<div className="ui-tabs-panel ui-corner-bottom ui-widget-content" style={{display:"block"}}>
		<div className="dw-history">
			{/* <!-- 조회 옵션 : Start --> */}
			<DWHistoryHeader onSubmit={handleSubmit} onTotal={handleTotalCount}/>

			{/* <!-- //조회 옵션 : End --> */}
			{/* <!-- 조회 건수 표시 : Start --> */}

			{ selectedCoin.coin == 'KRW' ?  
			<DWHistoryCurrencyTotal total={totalCount.no}/> :
			<DWHistoryCoinTotal total={totalCount.no} />}

			{/* <p className="case-info">총 <b id="coin-tab-3-dw-list-total-count" className="blue">{totalCount}</b>건의 자료가 조회 되었습니다</p> */}

			{/* <!-- //조회 건수 표시 : End --> */}

			{/* <!-- 데이터 영역 : Start --> */}
			{ selectedCoin.coin == 'KRW' ?  
			<DWHistoryCurrencyList histList={histList} /> : 
			<DWHistoryCoinList histList={histList} />}
			{/* <!-- //데이터 영역 : End --> */}


		</div>
	</div>
	);
}



export default DWHistoryTab;