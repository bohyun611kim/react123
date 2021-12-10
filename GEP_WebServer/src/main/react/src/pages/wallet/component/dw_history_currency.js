import { toLocale } from "../../../module/utils/calculate.util";
import { getLocaleContent } from "../../../module/utils/language.util";

export function DWHistoryCurrencyTotal({total}) {
	return (
		<>
		<p className="case-info">{getLocaleContent('word', 'total_')} <b id="money-tab-3-dw-list-total-count" className="blue">{total}</b>{getLocaleContent('word', 'depo_hist_search ')}</p>
		</>
	)
}

function DWHistoryCurrencyPaging() {
	return (
		<ul id="money-tab-3-dw-list-pagination" className="pagination"> </ul>
	);
}

function DWHistoryCurrencyItem({item}) {

	//console.log("DWHistoryCurrencyItem:", item);
	var reqSeqNo                    = item.REQ_SEQ_NO;
	var reqTypeCd                   = item.DW_REQ_TYPE_CD + '';
	var reqDateTime                 = getCstmFrmt(item.REQ_STAT_PROC_DT, 'yyyyMMddHHmmssSSSSSS', 'yyyy-MM-dd HH:mm:ss');

	var reqQty                      = item.REQ_QTY;
	var fee                         = (reqTypeCd == '3') ? '0' : parseFloat(item.WTDRW_FEE, 6); // 입금 : 0, 출금 : WTDRW_FEE
	var finCode                     = item.PIN_CD;
	var address                     = item.TARGET_ADDR;
	var bankCode                    = item.TARGET_ADDR_ETC1;
	var bankName                    = _G_Bank_Code_Map.get(bankCode);
	var reqStatProcCd               = item.REQ_STAT_PROC_CD;
	var approvalStatCd              = item.APPROVAL_STAT_CD;

	var gubunText                   = (reqTypeCd == '3') ? getLocaleContent('word', 'deposit') : getLocaleContent('word', 'withdraw');
	var gubunColor                  = (reqTypeCd == '3') ? 'red' : 'blue';

	var procStatCd                  = item.DW_PROC_STAT_CD;
	var procStatTxt = '';

	let typeText = reqTypeCd == '1' ? getLocaleContent('word', 'deposit') :reqTypeCd == '3' ? getLocaleContent('word', 'deposit') : getLocaleContent('word', 'withdraw');

	switch(reqStatProcCd) {
		case 1: procStatTxt = getLocaleContent('word', 'deposit') + getLocaleContent('word', '_summit'); break;
		case 2: procStatTxt = getLocaleContent('word', 'confirmation') + getLocaleContent('word', 'verifing'); break;
		case 3: procStatTxt = getLocaleContent('word', 'withdraw') + getLocaleContent('word', 'approval') + getLocaleContent('word', 'waiting'); break;
		case 4:
			if(approvalStatCd == 0) {
				procStatTxt = typeText + getLocaleContent('word', 'approval') + getLocaleContent('word', 'waiting');
			} else if(approvalStatCd == 1) {
				procStatTxt = typeText + getLocaleContent('word', 'process') + getLocaleContent('word', 'waiting');
			}
			break;
		case 5: procStatTxt = getLocaleContent('word', 'request') + getLocaleContent('word', 'cancel'); break;
	}
	if(procStatCd == 1) {
		procStatTxt = getLocaleContent('word', 'process') + getLocaleContent('word', '_done');
	}
	if(approvalStatCd == -2) {
		procStatTxt = getLocaleContent('word', 'refund') + getLocaleContent('word', '_done');
	}
	if(approvalStatCd == -1) {
		procStatTxt = getLocaleContent('word', 'approval') + getLocaleContent('word', 'cancel');
	}

	var cancelButtonHTML = 0;
	if(reqTypeCd == '2' && (reqStatProcCd == '3' || reqStatProcCd == '4') && approvalStatCd == 0) {
		cancelButtonHTML = 1;
	}

	return (
		<tr>
			<td className={`${gubunColor} alCenter`}>{gubunText}</td>
			<td className="alCenter">{reqDateTime}</td>
			<td className={`${gubunColor} alRight`}>{toLocale(reqQty)}</td>
			<td className="alRight">{fee}</td>
			<td className="alCenter">{bankName}</td>
			<td className="alCenter">{address}</td>
			<td className="alCenter">{finCode}</td>
			<td className="alCenter">{procStatTxt}</td>
			<td className="alCenter">
				{cancelButtonHTML == 1 ? <button onClick="">{getLocaleContent('word', 'cancel')}</button> : ""} 
			</td>
		</tr>
	);
}

export function DWHistoryCurrencyList({histList}) {

	return (
		<div>
			<table className="line">
				<colgroup>
					<col/>
					<col style={{width: "160px"}}/>
					<col/>
					<col/>
					<col/>
					<col style={{width: "180px"}}/>
					<col/>
					<col/>
					<col/>
				</colgroup>
				<thead>
					<tr>
						{	getLocaleContent('object', 'wallet.history.titles3').map((item, index) => {
								if (index == 2 || index == 3)
									return <th key={index} class="alRight">{item}</th>
								else
									return <th key={index} >{item}</th>
							})
						}
					</tr>
				</thead>
				<tbody>
					{ histList && histList.map((item,index) => <DWHistoryCurrencyItem idx={index} item={item}/>)}
				</tbody>
			</table>
			{/* <!-- 페이징 : Start --> */}
			<DWHistoryCurrencyPaging/>
			{/* <!-- //페이징 : End --> */}
		</div>
	);
}