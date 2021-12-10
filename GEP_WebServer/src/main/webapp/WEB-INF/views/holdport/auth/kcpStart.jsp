<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="naver-site-verification" content="0e51fcf8dc9a97163b8be94cb28d2b921192aef2"/>
<title></title>
</head>
<body>
<script type="text/javascript">

/* -------------------- 운영기 -------------------- */
const param = "site_cd=AEWMJ&web_siteid=J21071207044&req_tx=cert&cert_method=01&cert_otp_use=Y&cert_enc_use_ext=Y&res_cd=&res_msg=&veri_up_hash=&cert_able_yn=&web_siteid_hashYN=&year=&month=&day=";
const retUrl = "&Ret_URL=https://bita500.com/site/auth/kcpcert_proc_req.do&ordr_idxx=${dateTime}";

//const param = "site_cd=S6186&req_tx=cert&cert_method=01&cert_otp_use=Y&cert_enc_use=Y&res_cd=&res_msg=&veri_up_hash=&cert_able_yn=&web_siteid_hashYN=&year=&month=&day=";
//const retUrl = "&Ret_URL=https://bita500.com/site/auth/kcpcert_proc_req.do";


/* -------------------- 개발기 -------------------- */
/* const param = "site_cd=AEWMJ&web_siteid=J21071207044&req_tx=cert&cert_method=01&cert_otp_use=Y&cert_enc_use_ext=Y&res_cd=&res_msg=&veri_up_hash=&cert_able_yn=&web_siteid_hashYN=&year=&month=&day=";
const retUrl = "&Ret_URL=http://localhost:8080/site/auth/kcpcert_proc_req.do&ordr_idxx=${dateTime}";
const retUrl = "&Ret_URL=http://106.243.159.4:8080/site/auth/kcpcert_proc_req.do&ordr_idxx=${dateTime}";
*/

const param_opt_1 = "&param_opt_1=${os}";
const param_opt_2 = "&param_opt_2=${browser}";
const param_opt_3 = "&param_opt_3=${sessionId}";
document.location.href="/site/auth/kcpcert_proc_req.do?" + param + retUrl + param_opt_1 + param_opt_2 + param_opt_3;
</script>
</body>
</html>