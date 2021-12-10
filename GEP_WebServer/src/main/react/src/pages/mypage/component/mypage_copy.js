import { useEffect, useState } from "react";

export default function PopUpCopy({setPopCopy}){
    return(
    <div className="popup-wrap" id="lrtClnt" style={{top: '0px', display: 'block'}}>
      <div className="popup alert">
        {/* 팝업내용 : Start */}
        <div className="alert-header">
          <p className="alert-title" />
          <a className="top-close" onClick={()=>setPopCopy(0)}><img src="/resources/img/btn-alert-close.png" /></a>
        </div>
        <div className="alert-content">
          <p className="alert-message">복사되었습니다</p>
        </div>
        <div className="alert-btn">
          <button className="blue" id="lrtClntOk">확인</button>
        </div>
      </div>
  </div>
  )
}
  