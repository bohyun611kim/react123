import { useEffect, useState } from "react";
import { useRecoilState, useSetRecoilState } from "recoil";
import { BASE_PREFIX } from "./myconstants";
import request from "./services/request.service";
import { g_login } from "./stores/wallet_state";


export const CheckLogin = async () => {
		const rsp = await request.post(BASE_PREFIX+'/api/islogin', null).then(response => response.data)
    console.log("request_is_login ---:",rsp);
    return rsp;
}

const withAuth = (WrappedComponent) => {

  return (props) => {
      const rsp = CheckLogin();
      if (rsp.rtnCd === 0) {
        console.log("=======>_is_login 1---:", rsp);
        location.ref = BASE_PREFIX +"/login";
        return null;
      } else {
      }
      return <WrappedComponent {...props} />;
  };
};

export default withAuth;


// HOC/withAuth.jsx
/*
import { useRouter } from "next/router";
import { useEffect, useState } from "react";
import verifyToken from "services/verifyToken";

const withAuth = (WrappedComponent) => {
  return (props) => {
    const Router = useRouter();
    const [verified, setVerified] = useState(false);

    useEffect(async () => {
      const accessToken = localStorage.getItem("accessToken");
      // if no accessToken was found,then we redirect to "/" page.
      if (!accessToken) {
        Router.replace("/");
      } else {
        // we call the api that verifies the token.
        const data = await verifyToken(accessToken);
        // if token was verified we set the state.
        if (data.verified) {
          setVerified(data.verified);
        } else {
          // If the token was fraud we first remove it from localStorage and then redirect to "/"
          localStorage.removeItem("accessToken");
          Router.replace("/");
        }
      }
    }, []);

    if (verified) {
      return <WrappedComponent {...props} />;
    } else {
      return null;
    }
  };
};

export default withAuth;
*/