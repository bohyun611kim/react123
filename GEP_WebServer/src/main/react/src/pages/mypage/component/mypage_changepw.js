import { useEffect, useState } from "react";
import { BASE_PREFIX } from "../../../module/myconstants";
import request from "../../../module/services/request.service";
import { toDecimalLimit, toFloorDecimals, toLocale } from "../../../module/utils/calculate.util";
import { toFormData } from "../../../module/utils/convert.util";
import { getLocaleObject } from "../../../module/utils/language.util";
import { is_empty } from "../../../module/utils/misc.util";


export default function ChangePw({setChangePW}){
    const [old_pw,setOldPw] = useState("");
    const [new_pw,setNewPw] = useState("");
    const [confirm,setConfirm] = useState("");

    const handleChange = (e) => {
      if(e) e.preventDefault();

      if(e.target.name === "old_pw"){
        setOldPw(e.target.value);
      } else if(e.target.name === "new_pw"){
        setNewPw(e.target.value);
      } else if(e.target.name === "confirm"){
        setConfirm(e.target.value);
      }

    }

    const handleSubmit = () =>{
      if(new_pw === confirm) {
        _submitData();
        setOld('');
        setNew('');
        setConfirm('');
      } 
      else {
        alert("It's different new password from confirm password.");
      }
    }
    const _submitData = async () =>{
      const param = {old_pwd, new_pwd, confirm, "type" : "1" };
      const rsp = await request.post(BASE_PREFIX + "/api", toFormData(param)).then(response => response.data)
          getServerMessage(rsp.msg).then(msg => window.alert(msg))
          if (rsp.result < 0) {
          } else {
              //alert(rsp.msg);
              return rsp;
          }
    }

    return(
      <div className="popup-wrap" id="pwChange" style={{display: 'block'}}>
          <div className="popup alert">
            <div className="alert-header">
              <p className="alert-title">비밀번호 변경</p>
              <a className="top-close" onClick={()=>setChangePW(0)}><img src={"/resources/img/btn-alert-close.png"} alt="닫기버튼" /></a>
            </div>
            <div className="alert-content">
              <table className="type01">
                <colgroup>
                  <col style={{width: '150px'}} />
                  <col />
                </colgroup>
                <tbody>
                  <tr>
                    <th>현재 비밀번호</th>
                    <td><input type="password" name="old_pw" maxLength={25} onChange={handleChange}/></td>
                  </tr>
                  <tr>
                    <th>신규 비밀번호</th>
                    <td><input type="password" name="new_pw" maxLength={25} onChange={handleChange}/></td>
                  </tr>
                  <tr>
                    <th>신규 비밀번호 확인</th>
                    <td><input type="password"  name="confirm" maxLength={25} onChange={handleChange}/></td>
                  </tr>
                </tbody>
              </table>
            </div>
            <div className="alert-btn">
              <button type="button" className="join" onClick={handleSubmit}>확인 </button>
            </div>
          </div>
        </div>
    )
  
}