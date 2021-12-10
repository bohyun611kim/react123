import { useEffect, useState } from "react";
import { BASE_PREFIX } from "../../../module/myconstants";
import request from "../../../module/services/request.service";
import { toFormData } from "../../../module/utils/convert.util";
// import { getLocaleContent } from "../../../module/utils/language.util";

function NoticeItem({item}) {


	const handleClick = (e, seq) => {
		e.preventDefault();
		location.href = "/site/support?notice=" + seq;
	}

	return (
	<li style={{cursor:'pointer'}} onClick={e=>handleClick(e, item.rec_SEQ_NO)}>{item.title}</li>
	);
  }

export default function Notice() {
  
	const [noticeList, setNotice] = useState([])
	
	useEffect(()=> {
	  _get_notice();
	}, []);
  
	const _get_notice = async () => {
	  var param = {exchangeId:'HOLDPORT', pageNum :1 , searchQry:""};  // 10 : KRW
		  const rsp = await request.post(BASE_PREFIX+'/support/spt001/selectNoticeList', toFormData(param)).then(response => response.data)
		//   console.log("_get_notice ---:", rsp);
		  try {
		setNotice(rsp.slice(0, 5))
  
		  } catch (error){
			  console.log("failed to parse:", error);
			  return null;
		  }
	}
  
	  return (
		<>
			<div className="list_notice">
				<div className="notice_item">
					<div className="div2 vtab">
					<div><a href="#vtab1" className="vselected">공지사항</a></div>
					<div><a href="#vtab2">뉴스보도</a></div>
					</div>
					<div className="vpanel">
					<ul id="vtab1">
						{noticeList.length > 0 && noticeList.map((item, index) => <NoticeItem item={item} idx={index}/> )}	
					</ul> 
					<ul id="vtab2" style={{display: 'none'}}>
						{noticeList.length > 0 && noticeList.map((item, index) => <NoticeItem item={item} idx={index}/> )}
					</ul>  
					</div>
				</div>
			</div>
		</>

	  );
  }
  