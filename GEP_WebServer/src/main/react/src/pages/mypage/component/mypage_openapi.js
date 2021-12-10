import { useEffect, useState } from "react";
import { BASE_PREFIX } from "../../../module/myconstants";
import request from "../../../module/services/request.service";
import { toDecimalLimit, toFloorDecimals, toLocale } from "../../../module/utils/calculate.util";
import { toFormData } from "../../../module/utils/convert.util";
import { getLocaleObject } from "../../../module/utils/language.util";
import { is_empty } from "../../../module/utils/misc.util";
import getMyPageLocaleString from "../../../../../webapp/resources/js/holdport/mypage/holdport.mypage";


export default function MyPageOpenAPI() {

  const [asset, setAsset] = useState({})
  const [amount, setAmount] = useState(0)
  const [payid, setPayId] = useState("")
  const [paypwd, setPayPwd] = useState("")

	useEffect(()=>{
	}, [])


	return (
    <>
	<li id="vtab4" style={{display: 'list-item'}}>
        <h1 className="subtitle"> {getMyPageLocaleString('_L_AP01')} </h1>
        <p className="mgt40">  {getMyPageLocaleString('_L_AP02')} </p>
        <p> {getMyPageLocaleString('_L_AP03')}</p>
        <p> {getMyPageLocaleString('_L_AP04')}</p>
        {/* 이용약관 : Start */}
        <div>
          <h2 className="tabletitle"> {getMyPageLocaleString('_L_AP05')}</h2>
          <div className="dis-block">
            <div className="text-area-wrap mCustomScrollbar _mCS_1" data-mcs-theme="dark"><div id="mCSB_1" className="mCustomScrollBox mCS-dark mCSB_vertical mCSB_inside" style={{maxHeight: 'none'}} tabIndex={0}><div id="mCSB_1_container" className="mCSB_container" style={{position: 'relative', top: '0px', left: '0px'}} dir="ltr">
                  <div className="text-area">
                    <strong> {getMyPageLocaleString('_L_AP06')}</strong>
                    <p>  {getMyPageLocaleString('_L_AP07')} </p>
                    <strong>  {getMyPageLocaleString('_L_AP08')}  </strong>
                    <ol>
                      <li style={{display: 'none'}}> {getMyPageLocaleString('_L_AP09')}</li>
                      <li style={{display: 'none'}}> {getMyPageLocaleString('_L_AP10')}</li>
                      <li style={{display: 'none'}}> {getMyPageLocaleString('_L_AP11')} </li>
                      <li style={{display: 'none'}}> {getMyPageLocaleString('_L_AP12')}</li>
                      <li style={{display: 'none'}}> {getMyPageLocaleString('_L_AP13')} </li>
                    </ol>
                    <strong>  {getMyPageLocaleString('_L_AP14')} </strong>
                    <ol>
                      <li style={{display: 'none'}}>{getMyPageLocaleString('_L_AP14')}
                        <ul>
                          <li style={{display: 'none'}}> {getMyPageLocaleString('_L_AP15')} </li>
                          <li style={{display: 'none'}}> {getMyPageLocaleString('_L_AP16')} </li>
                          <li style={{display: 'none'}}> {getMyPageLocaleString('_L_AP17')} </li>
                          <li style={{display: 'none'}}> {getMyPageLocaleString('_L_AP18')} </li>
                          <li style={{display: 'none'}}> {getMyPageLocaleString('_L_AP19')} </li>
                          <li style={{display: 'none'}}> {getMyPageLocaleString('_L_AP20')} </li>
                        </ul>
                      </li>
                      <li style={{display: 'none'}}> {getMyPageLocaleString('_L_AP21')} </li>
                    </ol>
                    <strong>{getMyPageLocaleString('_L_AP22')} </strong>
                    <ol>
                      <li style={{display: 'none'}}> {getMyPageLocaleString('_L_AP23')} </li>
                      <li style={{display: 'none'}}> {getMyPageLocaleString('_L_AP24')} </li>
                      <li style={{display: 'none'}}> {getMyPageLocaleString('_L_AP25')} </li>
                      <li style={{display: 'none'}}> {getMyPageLocaleString('_L_AP26')} </li>
                      <li style={{display: 'none'}}> {getMyPageLocaleString('_L_AP27')} </li>
                    </ol>
                    <strong> {getMyPageLocaleString('_L_AP28')} </strong>
                    <ol>
                      <li style={{display: 'none'}}> {getMyPageLocaleString('_L_AP29')} 
                        <ul>
                          <li style={{display: 'none'}}> {getMyPageLocaleString('_L_AP30')} </li>
                          <li style={{display: 'none'}}> {getMyPageLocaleString('_L_AP31')} </li>
                          <li style={{display: 'none'}}> {getMyPageLocaleString('_L_AP32')} </li>
                        </ul>
                      </li>
                      <li style={{display: 'none'}}> {getMyPageLocaleString('_L_AP33')}  </li>
                      <li style={{display: 'none'}}> {getMyPageLocaleString('_L_AP34')}  </li>
                      <li style={{display: 'none'}}> {getMyPageLocaleString('_L_AP35')}  </li>
                      <li style={{display: 'none'}}> {getMyPageLocaleString('_L_AP36')}  
                        <ul>
                          <li style={{display: 'none'}}>  {getMyPageLocaleString('_L_AP37')}  </li>
                          <li style={{display: 'none'}}>  {getMyPageLocaleString('_L_AP38')}  </li>
                          <li style={{display: 'none'}}>  {getMyPageLocaleString('_L_AP39')}  </li>
                        </ul>
                      </li>
                      <li style={{display: 'none'}}> {getMyPageLocaleString('_L_AP40')} </li>
                      <li style={{display: 'none'}}> {getMyPageLocaleString('_L_AP41')} </li>
                    </ol>
                    <strong> {getMyPageLocaleString('_L_AP42')} </strong>
                    <ol>
                      <li style={{display: 'none'}}> {getMyPageLocaleString('_L_AP43')}  </li>
                      <li style={{display: 'none'}}> {getMyPageLocaleString('_L_AP44')}  </li>
                      <li style={{display: 'none'}}> {getMyPageLocaleString('_L_AP45')}  </li>
                      <li style={{display: 'none'}}> {getMyPageLocaleString('_L_AP46')}  </li>
                      <li style={{display: 'none'}}> {getMyPageLocaleString('_L_AP47')}  </li>
                      <li style={{display: 'none'}}> {getMyPageLocaleString('_L_AP48')}  </li>
                      <li style={{display: 'none'}}> {getMyPageLocaleString('_L_AP49')}  </li>
                      <li style={{display: 'none'}}> {getMyPageLocaleString('_L_AP50')}  </li>
                    </ol>
                    <strong> {getMyPageLocaleString('_L_AP51')} </strong>
                    <ol>
                      <li style={{display: 'none'}}> {getMyPageLocaleString('_L_AP52')} </li>
                      <li style={{display: 'none'}}> {getMyPageLocaleString('_L_AP53')} 
                        <ul>
                          <li style={{display: 'none'}}> {getMyPageLocaleString('_L_AP54')}  </li>
                          <li style={{display: 'none'}}> {getMyPageLocaleString('_L_AP55')}  </li>
                          <li style={{display: 'none'}}> {getMyPageLocaleString('_L_AP56')}  </li>
                          <li style={{display: 'none'}}> {getMyPageLocaleString('_L_AP57')}  </li>
                          <li style={{display: 'none'}}> {getMyPageLocaleString('_L_AP58')}  </li>
                          <li style={{display: 'none'}}> {getMyPageLocaleString('_L_AP59')}  </li>
                          <li style={{display: 'none'}}> {getMyPageLocaleString('_L_AP60')}  </li>
                          <li style={{display: 'none'}}> {getMyPageLocaleString('_L_AP61')}  </li>
                          <li style={{display: 'none'}}> {getMyPageLocaleString('_L_AP62')}  </li>
                          <li style={{display: 'none'}}> {getMyPageLocaleString('_L_AP63')}  </li>
                        </ul>
                      </li>
                      <li style={{display: 'none'}}> {getMyPageLocaleString('_L_AP64')} 
                        <ul>
                          <li style={{display: 'none'}}> {getMyPageLocaleString('_L_AP65')}  </li>
                          <li style={{display: 'none'}}> {getMyPageLocaleString('_L_AP66')}  </li>
                          <li style={{display: 'none'}}> {getMyPageLocaleString('_L_AP67')}  </li>
                        </ul>
                      </li>
                      <li style={{display: 'none'}}> {getMyPageLocaleString('_L_AP68')}  </li>
                      <li style={{display: 'none'}}> {getMyPageLocaleString('_L_AP69')} 
                        <ul>
                          <li style={{display: 'none'}}> {getMyPageLocaleString('_L_AP70')}  
                          </li><li style={{display: 'none'}}> {getMyPageLocaleString('_L_AP71')}
                          </li><li style={{display: 'none'}}> {getMyPageLocaleString('_L_AP72')} 
                          </li><li style={{display: 'none'}}> {getMyPageLocaleString('_L_AP73')} 
                          </li><li style={{display: 'none'}}> {getMyPageLocaleString('_L_AP74')} 
                          </li></ul>
                      </li>
                      <li style={{display: 'none'}}> {getMyPageLocaleString('_L_AP75')}  </li>
                    </ol>
                    <strong> {getMyPageLocaleString('_L_AP76')} </strong>
                    <ol>
                      <li style={{display: 'none'}}> {getMyPageLocaleString('_L_AP77')} </li>
                      <li style={{display: 'none'}}> {getMyPageLocaleString('_L_AP78')} </li>
                      <li style={{display: 'none'}}> {getMyPageLocaleString('_L_AP79')} </li>
                      <li style={{display: 'none'}}> {getMyPageLocaleString('_L_AP80')} </li>
                      <li style={{display: 'none'}}> {getMyPageLocaleString('_L_AP81')} </li>
                      <li style={{display: 'none'}}> {getMyPageLocaleString('_L_AP82')} </li>
                    </ol>
                    <strong> {getMyPageLocaleString('_L_AP83')}  </strong>
                    <ol>
                      <li style={{display: 'none'}}> {getMyPageLocaleString('_L_AP84')} </li>
                      <li style={{display: 'none'}}> {getMyPageLocaleString('_L_AP85')} </li>
                      <li style={{display: 'none'}}> {getMyPageLocaleString('_L_AP86')} </li>
                    </ol>
                    <strong> {getMyPageLocaleString('_L_AP87')} </strong>
                    <ol>
                      <li style={{display: 'none'}}> {getMyPageLocaleString('_L_AP88')}  </li>
                      <li style={{display: 'none'}}> {getMyPageLocaleString('_L_AP89')}  </li>
                      <li style={{display: 'none'}}> {getMyPageLocaleString('_L_AP90')}  </li>
                      <li style={{display: 'none'}}> {getMyPageLocaleString('_L_AP91')}  </li>
                      <li style={{display: 'none'}}> {getMyPageLocaleString('_L_AP92')}  </li>
                      <li style={{display: 'none'}}> {getMyPageLocaleString('_L_AP93')}  </li>
                      <li style={{display: 'none'}}> {getMyPageLocaleString('_L_AP94')}  </li>
                    </ol>
                    <strong>  {getMyPageLocaleString('_L_AP95')} </strong>
                    <ol>
                      <li style={{display: 'none'}}>   {getMyPageLocaleString('_L_AP96')} </li>
                      <li style={{display: 'none'}}>   {getMyPageLocaleString('_L_AP97')} </li>
                      <li style={{display: 'none'}}>   {getMyPageLocaleString('_L_AP98')} </li>
                    </ol>
                    <p>
                      <br />
                      &lt;{getMyPageLocaleString('_L_AP99')} &gt; {getMyPageLocaleString('_L_AP100')} {/*부칙*/}
                    </p>
                  </div>
                </div><div id="mCSB_1_scrollbar_vertical" className="mCSB_scrollTools mCSB_1_scrollbar mCS-dark mCSB_scrollTools_vertical" style={{display: 'block'}}><div className="mCSB_draggerContainer"><div id="mCSB_1_dragger_vertical" className="mCSB_dragger" style={{position: 'absolute', minHeight: '30px', top: '0px', display: 'block', height: '51px', maxHeight: '388px'}}><div className="mCSB_dragger_bar" style={{lineHeight: '30px'}} /></div><div className="mCSB_draggerRail" /></div></div></div></div>
            <div className="checks-item bggray" style={{width: '100%'}}>
              <input type="checkbox" id="agreeApi" />
              <label htmlFor="agreeApi">
              {getMyPageLocaleString('_L_AP101')} 
                <span className="blue">{getMyPageLocaleString('_L_AP102')} </span>
              </label>
            </div>
          </div>
        </div>
        {/* //이용약관 : End */}
        {/* API 활성화 선택 : Start */}
        <div className="mgt20">
          <h2 className="tabletitle">&nbsp;&nbsp; {getMyPageLocaleString('_L_AP103')}  </h2>
          <div className="mgt10">
            <span className="checks-item right">
              <input type="checkbox" id="allAgree" />
              <label htmlFor="allAgree"> {getMyPageLocaleString('_L_AP104')} </label>
            </span>
          </div>
          <div className="api">
            <table className="line">
              <colgroup>
                <col />
                <col />
                <col />
                <col />
                <col />
                <col />
                <col />
                <col />
              </colgroup>
              <tbody id="apiType">
                <tr>
                  <th>{getMyPageLocaleString('_L_AP105')} </th>
                  <td className="alRight">
                    <label className="switch-button"><input type="checkbox"  /></label>
                  </td>
                  <th>{getMyPageLocaleString('_L_AP106')} </th>
                  <td className="alRight">
                    <label className="switch-button"><input type="checkbox"  /></label>
                  </td>
                  <th>{getMyPageLocaleString('_L_AP107')}</th>
                  <td className="alRight">
                    <label className="switch-button"><input type="checkbox"  /></label>
                  </td>
                  <th>{getMyPageLocaleString('_L_AP108')}</th>
                  <td className="alRight">
                    <label className="switch-button"><input type="checkbox"  /></label>
                  </td>
                </tr>
                <tr>
                  <th>{getMyPageLocaleString('_L_AP109')}</th>
                  <td className="alRight">
                    <label className="switch-button"><input type="checkbox"  /></label>
                  </td>
                  <th>{getMyPageLocaleString('_L_AP110')}</th>
                  <td className="alRight" style={{borderRight: '1px solid #ddd'}}>
                    <label className="switch-button"><input type="checkbox"  /></label>
                  </td>
                  {/* <th>암호화폐 출금</th>
										<td class="alRight">
											<label class="switch-button"><input type="checkbox" data-no="007"></label>
										</td>
										<th>KRW출금</th>
										<td class="alRight">
											<label class="switch-button"><input type="checkbox" data-no="008"></label>
										</td> */}
                </tr>
              </tbody>
            </table>
          </div>
        </div>
        {/* //API 활성화 선택 : End */}
        {/* IP 주소 제한 설정 : Start */}
        <div className="mgt20">
          <h2 className="tabletitle"> {getMyPageLocaleString('_L_AP111')} </h2>
          <p>{getMyPageLocaleString('_L_AP112')} </p>
          <div className="mgt10">
            <table className="type01">
              <colgroup>
                <col style={{width: '180px'}} />
                <col />
              </colgroup>
              <tbody>
                <tr>
                  <th>{getMyPageLocaleString('_L_AP113')} </th>
                  <td>
                    <input type="text" id="accessIp" name="accessIp" maxLength={15} />
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
        {/* //IP 주소 제한 설정 : End */}
        {/* 보안인증 하기 : Start */}
        <div className="mgt20">
          <h2 className="tabletitle"> {getMyPageLocaleString('_L_AP114')} </h2>
          <p> {getMyPageLocaleString('_L_AP115')} </p>
          <div className="mgt10">
            <table className="type01">
              <colgroup>
                <col style={{width: '180px'}} />
                <col />
              </colgroup>
              <tbody id="apiAuthType">
              </tbody>
            </table>
            {/* API생성하기 버튼 : Start */}
            <div className="btn-group">
              <button className="big blue" type="button" id="setApi"> {getMyPageLocaleString('_L_AP116')} </button>
            </div>
            {/* //API생성하기 버튼 : End */}
          </div>
        </div>
        {/* //보안인증 하기 : End */}
        {/* 등록된 API 목록 : Start */}
        <div className="mgt20">
          <h2 className="tabletitle"> {getMyPageLocaleString('_L_AP117')} </h2>
          <p>{getMyPageLocaleString('_L_AP118')} </p>
          <div>
            <table className="line mgt10">
              <colgroup>
                <col />
                <col />
                <col style={{width: '140px'}} />
                <col style={{width: '100px'}} />
                <col style={{width: '100px'}} />
                <col style={{width: '80px'}} />
              </colgroup>
              <thead>
                <tr>
                  <th>Client ID</th>
                  <th>Secret Key</th>
                  <th>{getMyPageLocaleString('_L_AP119')}  </th>
                  <th> {getMyPageLocaleString('_L_AP120')} </th>
                  <th> {getMyPageLocaleString('_L_AP121')} </th>
                  <th> {getMyPageLocaleString('_L_AP122')} </th>
                </tr>
              </thead>
              <tbody id="apiList">
                <tr>
                  <td colSpan={6}> {getMyPageLocaleString('_L_AP123')} </td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
        {/* //등록된 API 목록 : End */}
        <div className="mgt20">
          <h2 className="tabletitle"> {getMyPageLocaleString('_L_AP124')} </h2>
          <p><a href="/resources/docs/API 이용가이드.docx?v=202106021807" target="_blank">  {getMyPageLocaleString('_L_AP125')} </a></p>
          <p><a href="/resources/docs/API규격서.docx?v=202106021807" target="_blank">  {getMyPageLocaleString('_L_AP126')} </a></p>
        </div>
      </li>
    </>
	);
}