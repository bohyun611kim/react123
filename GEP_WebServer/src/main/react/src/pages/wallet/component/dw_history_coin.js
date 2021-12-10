import { getLocaleContent, getLocaleObject } from "../../../module/utils/language.util";


export function DWHistoryCoinTotal({total}) {
	return (
		<p className="case-info">{getLocaleContent('word', 'total_')}<b id="coin-tab-3-dw-list-total-count" className="blue">{total}</b>{getLocaleContent('word', 'depo_hist_search ')}</p>
	)
}

function DWHistoryCoinPaging() {
	return (
		<ul id="coin-tab-3-dw-list-pagination" className="pagination"> </ul>
	);
}

function DWHistoryCoinItem({item}) {

	//console.log("DWHistoryCoinItem:", item);
	var reqSeqNo                    = item.REQ_SEQ_NO;
	var reqTypeCd                   = '' + item.DW_REQ_TYPE_CD;
	var reqDateTime                 = getCstmFrmt(item.REQ_STAT_PROC_DT, 'yyyyMMddHHmmssSSSSSS', 'yyyy-MM-dd HH:mm:ss');

	var reqQty                      = item.REQ_QTY;
	var fee                         = (reqTypeCd == '1') ? '0' : parseFloat(item.WTDRW_FEE, 6); // 입금 : 0, 출금 : WTDRW_FEE
	var address                     = item.TARGET_ADDR;
	var reqStatProcCd               = item.REQ_STAT_PROC_CD;
	var approvalStatCd              = item.APPROVAL_STAT_CD;

	var gubunText                   = (reqTypeCd == '1') ? getLocaleContent('word', 'deposit') : getLocaleContent('word', 'withdraw');
	var gubunColor                  = (reqTypeCd == '1') ? 'red' : 'blue';

	var procStatCd                  = item.DW_PROC_STAT_CD;
	var procStatTxt = '';
	switch(reqStatProcCd) {
		case 1: procStatTxt = getLocaleContent('word', 'deposit') + getLocaleContent('word', '_summit'); break;
		case 2: procStatTxt = getLocaleContent('word', 'confirmation') + getLocaleContent('word', 'verifing'); break;
		case 3: procStatTxt = getLocaleContent('word', 'withdraw') + getLocaleContent('word', 'authentication') + getLocaleContent('word', 'waiting'); break;
		case 4: procStatTxt = ((reqTypeCd == '1') ? getLocaleContent('word', 'deposit') :getLocaleContent('word', 'withdraw') ) + getLocaleContent('word', 'approval')+getLocaleContent('word', 'waiting') ; break;
		case 5: procStatTxt = getLocaleContent('word', 'request') + getLocaleContent('word', 'cancel'); break;
	}
	if(procStatCd == 1) {
		procStatTxt = getLocaleContent('word', 'process') + getLocaleContent('word', '_done') ;
	}
	if(approvalStatCd == -1) {
		procStatTxt = getLocaleContent('word', 'approval') + getLocaleContent('word', 'cancel') ;
	}

	var cancelButtonHTML            = 0;
	// 출금이면서 출금 인증 대기중인 것만 취소버튼 보이도록 처리함
	// if(reqTypeCd == '2' && (reqStatProcCd == '3' || reqStatProcCd == '4') && approvalStatCd == 0) cancelButtonHTML = 1;

	const handleCancel = (e, seq) => {
		e.preventDefault();
		cancelRequest(seq)
	}

	return (
		<tr onClick="">
			<td className={`${gubunColor} alCenter`}>{gubunText}</td>
			<td className="alCenter">{reqDateTime}</td>
			<td className={`${gubunColor} alRight`}>{reqQty}</td>
			<td className="alRight">{fee}</td>
			<td onclick=""> 
				<p className="ellips-group"> <span onClick="" className="text-ellips">{address}</span> 
				<button onClick="">{getLocaleContent('word', '_copy')}</button> </p> 
			</td>
			<td className="alCenter">{procStatTxt}</td>
			<td className="alCenter"> {cancelButtonHTML == 1 ? <button onClick={e=>handleCancel(e, reqSeqNo)}>{getLocaleContent('word', 'cancel')}</button> : "-"} </td>
		</tr>
	);
}

export function DWHistoryCoinList({histList}) {

	return (
		<div>
			<table className="line">
				<colgroup>
					<col/>
					<col style={{width: "160px"}}/>
					<col/>
					<col/>
					<col style={{width: "250px"}}/>
					<col/>
					<col/>
				</colgroup>
				<thead>
					<tr>
						{	getLocaleContent('object', 'wallet.history.titles2').map((item, index) => {
								if (index == 2 || index == 3)
									return <th key={index} class="alRight">{item}</th>
								else
									return <th key={index} >{item}</th>
							})
						}
					</tr>
				</thead>
				<tbody>
					{ histList && histList.map((item,index) => <DWHistoryCoinItem idx={index} item={item}/>)}
				</tbody>
			</table>
			{/* <!-- 페이징 : Start --> */}
			<DWHistoryCoinPaging/>
			{/* <!-- //페이징 : End --> */}
		</div>
	);
}
