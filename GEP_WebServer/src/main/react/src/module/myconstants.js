import { useRecoilState, useSetRecoilState } from "recoil";
import { WALLET_ACTION_BASE_LIST } from "./stores/wallet_state";


export const BASE_COIN_DIV = 10;
export const BASE_RES_IMG = '';
export const BASE_PREFIX = '/site';
//export const BASE_URL = 'http://13.125.97.36:8080/'; //holdexchange nginx 
//export const BASE_URL = 'http://54.180.203.9:8080/'; // philco nginx
export const BASE_URL = __G_home_url;

export const DIFF_MONTH = 3;



export function __Push_Listener_Wallet_Result(msg) {
    // console.log(msg);
    var msgBody = msg.body;
    var messageTxt;
    var messageTitle;
    
    console.log("__Push_Listener_Wallet_Result+++");
    const [action_cnt, setBaseAction] = useRecoilState(WALLET_ACTION_BASE_LIST);
    setBaseAction(action_cnt+1);
    console.log("__Push_Listener_Wallet_Result---");

	/*
    if(msgBody.dwReqTypeCd == 2) {             // 출금요청 출금완료 메시지
        messageTxt = '회원님께서 ' + du.getCstmFrmt(msgBody.reqDt, 'yyyyMMddHHmmssSSSSSS', 'yyyy-MM-dd HH:mm:ss') + '에 요청하신 <br>' + msgBody.reqQty + ' (' + __G_CoinMgtRef_Map.get(msgBody.coinNo).COIN_SYMBOLIC_NM + ') 가 출금될 예정입니다.';
        messageTitle = '출금 알림';
    } else if(msgBody.dwReqTypeCd == 1) {      // 입금 메시지
        messageTxt = '회원님께 ' + msgBody.reqQty + ' (' + __G_CoinMgtRef_Map.get(msgBody.coinNo).COIN_SYMBOLIC_NM + ') 가 입금 되었습니다.';
        messageTitle = '입금 알림';
    }

    lrt.client(messageTxt, messageTitle, function() {
        if(__G_Prev_Selected_CoinKind == 'MONEY' && __G_Prev_Selected_MoneyTab == 3) {
            var cur_page = $('#money-tab-3-dw-list-pagination').find('a.active').text();
            getMondyDWMgrList(cur_page, null);
        }
        if(__G_Prev_Selected_CoinKind == 'COIN' && __G_Prev_Selected_CoinTab == 3) {
            var cur_page = $('#coin-tab-3-dw-list-pagination').find('a.active').text();
            getListData(cur_page, null);
        }
	}, null);
	*/
}

export function __Push_Listener_Wallet_Change(msg) {
    // console.log(msg);
    var msgBody = msg.body;
    var messageTxt;
	var messageTitle;
	/*
    if(msgBody.possChgReasCd == 3) {             // 입금 최종 확인으로 인한 잔고변경일때
        messageTxt = '회원님께 ' + du.getCstmFrmt(msg.sndDt, 'yyyyMMddHHmmssSSSSSS', 'yyyy-MM-dd HH:mm:ss') + '에 입금 요청된 <br>' + msgBody.chgQty + ' (' + __G_CoinMgtRef_Map.get(msgBody.coinNo).COIN_SYMBOLIC_NM + ') 가 입금완료 처리 되었습니다.';
        messageTitle = '입금 확인';
        lrt.client(messageTxt, messageTitle, function() {
            if(__G_Prev_Selected_CoinKind == 'MONEY' && __G_Prev_Selected_MoneyTab == 3) {
                var cur_page = $('#money-tab-3-dw-list-pagination').find('a.active').text();
                getMondyDWMgrList(cur_page, null);
            }
            if(__G_Prev_Selected_CoinKind == 'COIN' && __G_Prev_Selected_CoinTab == 3) {
                var cur_page = $('#coin-tab-3-dw-list-pagination').find('a.active').text();
                getListData(cur_page, null);
            }
        }, null);
	}

    // LNB 영역 잔고변경 결과값으로 refresh
	getLnbPossessionInfo();
	*/



    /* __G_LNB_User_Possession_List.forEach(function(item){
        if(parseInt(item.COIN_NO) == parseInt(msgBody.coinNo) ) {
            item.TOTAL_QTY = msgBody.possQty;
        }
    }); */
}