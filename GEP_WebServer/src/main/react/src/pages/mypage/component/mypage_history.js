import { useEffect, useState } from "react";
import { BASE_PREFIX } from "../../../module/myconstants";
import request from "../../../module/services/request.service";
import { toDecimalLimit, toFloorDecimals, toLocale } from "../../../module/utils/calculate.util";
import { toFormData } from "../../../module/utils/convert.util";
import { getLocaleObject } from "../../../module/utils/language.util";
import { is_empty } from "../../../module/utils/misc.util";
import getMyPageLocaleString from "../../../../../webapp/resources/js/holdport/mypage/holdport.mypage";



export default function MyPageHistory() {

  const [asset, setAsset] = useState({})
  const [amount, setAmount] = useState(0)
  const [payid, setPayId] = useState("")
  const [paypwd, setPayPwd] = useState("")

	useEffect(()=>{
	}, [])

	return (
    <>
	 <li id="vtab3" style={{display: 'list-item'}}>
        <h1 className="subtitle"> {getMyPageLocaleString('_L_HI01')} </h1>
        <div className="dw-history">
          {/* 조회 옵션 : Start */}
          <div className="mgt20">
            {/*<span class="option-title">종목</span>*/}
            <div className="calendar">
              <input type="text" name="calendar" id="startDate" required defaultValue="2021-09-09" readOnly="readonly" className="hasDatepicker" />
              <span>~</span>
              <input type="text" name="calendar" id="endDate" required defaultValue="2021-09-09" readOnly="readonly" className="hasDatepicker" />
              <button type="button" id="logSearch">{getMyPageLocaleString('_L_HI02')} </button>
            </div>
          </div>
          {/* //조회 옵션 : End */}
          {/* 조회 건수 표시 : Start */}
          <p className="case-info">{getMyPageLocaleString('_L_HI03')}  <b className="blue" id="size">1</b> {getMyPageLocaleString('_L_HI04')} </p>
          {/* //조회 건수 표시 : End */}
          {/* 데이터 영역 : Start */}
          <div className="mgt30">
            <table className="line">
              <colgroup>
                <col />
                <col />
                <col />
                <col />
              </colgroup>
              <thead>
                <tr>
                  <th className="alCenter"> {getMyPageLocaleString('_L_HI05')}  </th>
                  <th className="alCenter">OS</th>
                  <th className="alCenter"> {getMyPageLocaleString('_L_HI06')}</th>
                  <th className="alCenter">{getMyPageLocaleString('_L_HI07')}</th>
                </tr>
              </thead>
              <tbody id="accessLog">
                <tr>
                  <td className="alCenter">2021-09-09 11:07:38</td>
                  <td className="alCenter">Macintosh</td>
                  <td className="alCenter">Chrome 92.0.4515.159</td>
                  <td className="alCenter">183.100.43.122</td>
                </tr>
              </tbody>
            </table>
            {/* 페이징 : Start */}
            <ul className="pagination"><li style={{display: 'none'}}><a className="btn-page first" data-index={1} /></li><li style={{display: 'none'}}><a className="btn-page prev" data-index={0} /></li><li style={{display: 'none'}}><a href="javascript:void(0);" className="active" data-index={1}>1</a></li><li style={{display: 'none'}}><a className="btn-page next" data-index={2} /></li><li style={{display: 'none'}}><a className="btn-page last" data-index={1} /></li></ul>
            {/* //페이징 : End */}
          </div>
          {/* //데이터 영역 : End */}
        </div>
      </li>
    </>
	);
}