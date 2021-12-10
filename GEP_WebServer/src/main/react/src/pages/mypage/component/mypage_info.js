import { useEffect, useState } from "react";
import { BASE_PREFIX } from "../../../module/myconstants";
import request from "../../../module/services/request.service";
import { toDecimalLimit, toFloorDecimals, toLocale } from "../../../module/utils/calculate.util";
import { toFormData } from "../../../module/utils/convert.util";
import { getLocaleObject } from "../../../module/utils/language.util";
import { is_empty } from "../../../module/utils/misc.util";
import getMyPageLocaleString from "../../../../../webapp/resources/js/holdport/mypage/holdport.mypage";


export default function MyPageInfo({userInfo,setChangePW,setTab,setPopCopy}) {


	console.log("MyPageInfo-", userInfo.info);
  const [asset, setAsset] = useState({})
  const [amount, setAmount] = useState(0)
  const [payid, setPayId] = useState("")
  const [paypwd, setPayPwd] = useState("")

	useEffect(()=>{
	}, [])

	if (userInfo.info == undefined) return null;

	return (
    <>
	 <li id="vtab1" style={{display: 'list-item'}}>
        <h1 className="subtitle"> {getMyPageLocaleString('_L_IF01')} </h1>
        <table className="table1 type01 type02">
          <tbody>
            <tr>
              <th>{getMyPageLocaleString('_L_IF02')}</th>
              <td>{userInfo.info.userId}</td>
            </tr>
            <tr>
              <th> {getMyPageLocaleString('_L_IF03')} </th>
              <td>{userInfo.info.userName}</td>
            </tr>
            <tr>
              <th>{getMyPageLocaleString('_L_IF04')}</th>
              <td>{userInfo.info.mobile === "" ? <button type="button" class="red" onClick={()=>setTab(2)}> {getMyPageLocaleString('_L_IF05')}</button> : userInfo.info.mobile }</td>
            </tr>
            <tr>
              <th> {getMyPageLocaleString('_L_IF06')} {getMyPageLocaleString('_L_IF07')}  </th>
              <td><button type="button" className="pop-password" onClick={()=>setChangePW(1)}> {getMyPageLocaleString('_L_IF07')} </button></td>
            </tr>
            <tr>
              <th> {getMyPageLocaleString('_L_IF08')} </th>
              <td>
                <input type="radio" id="ex_rd3" name="agreement" className="agreement" checked={userInfo.info.mrktAgreeYn == 1 ? true : false} />
                <label htmlFor="ex_rd3"> {getMyPageLocaleString('_L_IF09')} </label>
                <input type="radio" id="ex_rd4" name="agreement" className="agreement" checked={userInfo.info.mrktAgreeYn == 1 ? false : true} />
                <label htmlFor="ex_rd4"> {getMyPageLocaleString('_L_IF10')} </label>
                {/* <button type="button">저장</button> */}
                <p> {getMyPageLocaleString('_L_IF11')} </p>
              </td>
            </tr>
          </tbody>
        </table>
        <h2 className="tabletitle"> {getMyPageLocaleString('_L_RE01')} </h2>
        <table className="table1 type01 type02">
          <tbody id="recommand">
            <tr>
              {/* <th>지급완료 회원수</th>
								<td class="recomm">
									<b class="blue"></b>명
								</td> */}
              <th>{getMyPageLocaleString('_L_RE02')} </th>
              <td colSpan={2}> {getMyPageLocaleString('_L_RE03')} </td>
              <td className="recomm">
                <b className="blue">0</b> {getMyPageLocaleString('_L_RE04')}
              </td>
            </tr>
            <tr>
              <th> {getMyPageLocaleString('_L_RE05')} </th>
              <td colSpan={3}>
                <h6>
                  <sapn className="blue"> {getMyPageLocaleString('_L_RE06')} </sapn>
                  {getMyPageLocaleString('_L_RE07')}
                </h6>
                <h6>
                  <span className="gray"> {getMyPageLocaleString('_L_RE08')} </span>
                  <b>A6K9M6U</b>
                  <button type="button" onClick={()=>setPopCopy(1)}>{getMyPageLocaleString('_L_RE08')}</button>
                </h6>
                <h6>
                  <sapn className="blue"> {getMyPageLocaleString('_L_RE06')}</sapn>
                  {getMyPageLocaleString('_L_RE09')}
                </h6>
                <h6>
                  <span className="gray"> {getMyPageLocaleString('_L_RE10')}  </span>
                  <b>http://bita500.com/site/register?recommandId=A6K9M6U</b>
                  <button type="button" onClick={()=>setPopCopy(1)}> {getMyPageLocaleString('_L_RE08')} </button>
                </h6>
              </td>
            </tr>
            {/* <tr>
								<th>추천인 삭제</th>
								<td colspan="3">
									<button type="button">추천인 삭제하기</button>
								</td>
							</tr> */}
          </tbody>
        </table>
      </li>
    </>
	);
}