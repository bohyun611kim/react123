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
const param = "site_cd=A8JNF&web_siteid=J19051303437&req_tx=cert&cert_method=01&cert_otp_use=Y&cert_enc_use=Y&res_cd=&res_msg=&veri_up_hash=&cert_able_yn=&web_siteid_hashYN=&year=&month=&day=";
//const retUrl = "&Ret_URL=https://www.ali-bit.com/alibit/auth/kcpcert_proc_req.do&ordr_idxx=${dateTime}";
const retUrl = "&Ret_URL=https://localhost:8080/alibit/auth/kcpcert_proc_req.do&ordr_idxx=${dateTime}";

/* -------------------- 개발기 -------------------- */
/* const param = "site_cd=S6186&req_tx=cert&cert_method=01&cert_otp_use=Y&cert_enc_use=Y&res_cd=&res_msg=&veri_up_hash=&cert_able_yn=&web_siteid_hashYN=&year=&month=&day=";
const retUrl = "&Ret_URL=http://localhost:8080/alibit/auth/kcpcert_proc_req.do&ordr_idxx=${dateTime}";
const retUrl = "&Ret_URL=http://106.243.159.4:8080/alibit/auth/kcpcert_proc_req.do&ordr_idxx=${dateTime}";
const retUrl = "&Ret_URL=http://192.168.110.151:8080/alibit/auth/kcpcert_proc_req.do&ordr_idxx=${dateTime}"; */

const param_opt_1 = "&param_opt_1=${os}";
const param_opt_2 = "&param_opt_2=${browser}";
const param_opt_3 = "&param_opt_3=${privateIp}";
document.location.href="/alibit/auth/kcpcert_proc_req.do?" + param + retUrl + param_opt_1 + param_opt_2 + param_opt_3;
</script>
</body>
</html>