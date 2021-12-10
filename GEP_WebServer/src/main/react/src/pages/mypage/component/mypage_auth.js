import { useEffect, useState } from "react";
import { BASE_PREFIX } from "../../../module/myconstants";
import request from "../../../module/services/request.service";
import { toDecimalLimit, toFloorDecimals, toLocale } from "../../../module/utils/calculate.util";
import { toFormData } from "../../../module/utils/convert.util";
import { getLocaleObject } from "../../../module/utils/language.util";
import { is_empty } from "../../../module/utils/misc.util";
// 다국어 
import getMyPageLocaleString from "../../../../../webapp/resources/js/holdport/mypage/holdport.mypage"; 



export default function MyPageAuth({userInfo}) {

  const [asset, setAsset] = useState({})
  const [amount, setAmount] = useState(0)
  const [payid, setPayId] = useState("")
  const [paypwd, setPayPwd] = useState("")

  console.log("level",userInfo.info.authLevel)

	useEffect(()=>{
	}, [])

	return (
    <>
	 <li id="vtab2" style={{display: 'list-item'}}>
        <h1 className="subtitle">{getMyPageLocaleString('_L_AU01')}</h1>
        <p className="mylevel">
           {getMyPageLocaleString('_L_AU02')}
          <span className="blue">  {userInfo.info.authLevel} </span> {getMyPageLocaleString('_L_AU03')}.
        </p>
        <p><span className="blue">{getMyPageLocaleString('_L_AU04')}</span> {getMyPageLocaleString('_L_AU05')}</p>
        
        <dl className="level">
          <dt className="now levellink" onclick="openPage('level1')">
            <p className="blue">{getMyPageLocaleString('_L_AU06')}</p>
            <p>{getMyPageLocaleString('_L_AU07')}</p>
            <p><img src="/resources/img/yaho/level1.png" alt="level1" /></p>
            <p>{userInfo.info.userId}</p>
          </dt>
          <dd className={`${userInfo.info.authLevel === 2 ? "now" : "able"} levellink`} onclick="openPage('level2')">
            <p>{getMyPageLocaleString('_L_AU08')}</p>
            <p>{getMyPageLocaleString('_L_AU09')}</p>
            <p><img src="/resources/img/yaho/level2.png" alt="level2" /></p>
            <p>{getMyPageLocaleString('_L_AU10')}</p>
            <button type="button" className="btnMobile">{getMyPageLocaleString('_L_AU11')}</button>
          </dd>
          <dd className={`${userInfo.info.authLevel === 3 ? "now" : "impossble"} levellink`}>
            <p>{getMyPageLocaleString('_L_AU12')}</p>
            <p>{getMyPageLocaleString('_L_AU13')}</p>
            <p><img src="/resources/img/yaho/level3.png" alt="level3" /></p>
            <p>{getMyPageLocaleString('_L_AU14')}</p>
            <button className="gray">{getMyPageLocaleString('_L_AU11')}</button>
          </dd>
          <dd className={`${userInfo.info.authLevel === 4 ? "now" : "impossble"} levellink`}>
            <p>{getMyPageLocaleString('_L_AU15')}</p>
            <p>{getMyPageLocaleString('_L_AU16')}</p>
            <p><img src="/resources/img/yaho/level4.png" alt="level4" /></p>
            <p>{getMyPageLocaleString('_L_AU17')}</p>
            <button className="gray">{getMyPageLocaleString('_L_AU11')}</button>
          </dd>
          <dd className={`${userInfo.info.authLevel === 5 ? "now" : "impossble"} levellink`}>
            <p>{getMyPageLocaleString('_L_AU18')}</p>
            <p>{getMyPageLocaleString('_L_AU19')}</p>
            <p><img src="/resources/img/yaho/level5.png" alt="level5" /></p>
            <p>{getMyPageLocaleString('_L_AU17')}</p>
            <button className="gray">{getMyPageLocaleString('_L_AU18')} </button> {/*비활성화일경우 클래스 gray*/}
          </dd>
        </dl>
        <div id="level1" className="leveinfo" style={{display: 'none'}}>
          <h3>{getMyPageLocaleString('_L_AU07')} </h3>
          <p> {getMyPageLocaleString('_L_AU21')} </p>
        </div>
        <div id="level2" className="leveinfo">
          <div>
            <h3>{getMyPageLocaleString('_L_AU10')}</h3>
            <span> {getMyPageLocaleString('_L_AU22')} </span>
            <button className="blue big btnMobile"> {getMyPageLocaleString('_L_AU11')} </button>
          </div>
        </div>
        {/*추가인증 otp 시작*/}
        <div id="level3" className="leveinfo" style={{display: 'none'}}>
          <h3> {getMyPageLocaleString('_L_AU23')} </h3>
          <p> {getMyPageLocaleString('_L_AU24')}  </p>
          <div className="mgt20">
            <h1> {getMyPageLocaleString('_L_AU25')} </h1>
            <p> {getMyPageLocaleString('_L_AU26')} </p>
            <div className="mgt20">
              <img src="/resources/img/yaho/img-google.jpg" />
              <img src="/resources/img/yaho/img-apple.jpg" />
            </div>
          </div>
          <div className="mgt20">
            <h1> {getMyPageLocaleString('_L_AU27')}  </h1>
            <p id="vtab2-level4-otp-key-setting-text"> {getMyPageLocaleString('_L_AU28')} </p>
            <div className="my-key">
              <span id="vtab2-level4-otp-qrcode" className="my-key-qr" title="otpauth://totp/api.acco***@gmail****?secret=NSJ24GPRVNQJUM3NFUZTGM4NGGGUF37B&issuer=Bita500"><canvas width={100} height={100} style={{display: 'none'}} /><img src="data:image/png;base64,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" style={{display: 'block'}} /></span>
            </div>
            <div className="my-key-txt">
              <span>
                <span id="vtab2-level4-otp-key-intro-text"> {getMyPageLocaleString('_L_AU29')} <br /><span id="vtab2-level4-otp-auth-key">NSJ24GPRVNQJUM3NFUZTGM4NGGGUF37B</span>
                </span>
              </span>
            </div>
          </div>
          <div id="vtab2-level4-otp-auth-div" className="mgt20">
            <h1> {getMyPageLocaleString('_L_AU30')} </h1>
            <p> {getMyPageLocaleString('_L_AU31')}</p>
            <table className="type01 mgt20">
              <colgroup>
                <col style={{width: '200px'}} />
                <col />
              </colgroup>
              <tbody>
                <tr>
                  <th>{getMyPageLocaleString('_L_AU32')} </th>
                  <td><input type="password" id="vtab2-level4-login-password" name className="basic" /></td>
                </tr>
                <tr>
                  <th>{getMyPageLocaleString('_L_AU33')}</th>
                  <td>
                    <input type="text" id="vtab2-level4-sms-auth-code" name placeholder="{getMyPageLocaleString('_L_AU33C1')}" />
                    <button id="vtab2-level4-sms-auth-code-request-btn">{getMyPageLocaleString('_L_AU34')}</button>
                  </td>
                </tr>
                <tr>
                  <th> {getMyPageLocaleString('_L_AU35')} </th>
                  <td><input type="text" id="vtab2-level4-otp-auth-code" name placeholder="{getMyPageLocaleString('_L_AU33C2')}" /></td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
        {/*추가인증 otp 끝*/}
        {/*신분증인증 시작*/}
        <div id="level4" className="leveinfo" style={{display: 'none'}}>
          <form id="authSubmit">
            {/*개인시분증 시작*/}
            <h3> {getMyPageLocaleString('_L_AU36')}</h3>
            {/*법인신분증 끝*/}
          </form>
        </div>
        {/*거주지인증 시작*/}
        <div id="level5" className="leveinfo" style={{display: 'none'}}>
          <div>
            <p style={{color: 'red'}} />
            <h3> {getMyPageLocaleString('_L_AU37')} </h3>
            <p> {getMyPageLocaleString('_L_AU38')} </p>
            <p> {getMyPageLocaleString('_L_AU39')} </p>
            <p> {getMyPageLocaleString('_L_AU40')} </p>
            {/*<button class="blue big"> 레벨5 상향 신청</button>*/}
          </div>
        </div>
        {/*거주지인증 끝*/}
      </li>
    </>
	);
}