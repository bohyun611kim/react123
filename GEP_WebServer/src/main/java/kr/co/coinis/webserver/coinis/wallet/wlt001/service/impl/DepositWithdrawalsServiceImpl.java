/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.coinis.wallet.wlt001.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import kr.co.coinis.webserver.coinis.wallet.wlt001.dao.DepositWithdrawalsDAO;
import kr.co.coinis.webserver.coinis.wallet.wlt001.service.DepositWithdrawalsService;
import kr.co.coinis.webserver.coinis.wallet.wlt001.vo.CoinPossessionVO;
import kr.co.coinis.webserver.coinis.wallet.wlt001.vo.DepositWithdrawInfoVO;
import kr.co.coinis.webserver.coinis.wallet.wlt001.vo.ReqDepositWithdrawInfoVO;
import kr.co.coinis.webserver.common.message.repository.MessageDAO;
import kr.co.coinis.webserver.common.message.service.MessageService;
import kr.co.coinis.webserver.common.message.vo.MessageVO;
import kr.co.coinis.webserver.common.utils.CommonUtils;
import kr.co.coinis.webserver.common.vo.ExchangeIDUserIDPairVO;
import kr.co.coinis.webserver.common.web.camel.router.CamelHelper;
import kr.co.coinis.webserver.yahobit.wallet.wlt001.vo.CoinMgtRefInfoVO;
import kr.co.coinis.webserver.coinis.wallet.wlt001.vo.CoinBalanceVO;

/**
 * <pre>
 * kr.co.coinis.webserver.wallet.wlt001.service.impl 
 *    |_ DepositWithdrawalsServiceImpl.java
 * 
 * </pre>
 * @date : 2019. 3. 25. 오후 5:16:33
 * @version : 
 * @author : yeonseoo
 */
@Service("depositWithdrawalsService")
public class DepositWithdrawalsServiceImpl implements DepositWithdrawalsService {

	private static final Logger LOG = LoggerFactory.getLogger(DepositWithdrawalsServiceImpl.class);

	@Resource(name="depositWithdrawalsDAO")
	private DepositWithdrawalsDAO depositWithdrawalsDAO;
	
	@Resource(name="messageDAO")
	private MessageDAO messageDAO;
	
	@Autowired
	private MessageService messageService;

	@Override
	public List<CoinPossessionVO> retrieveCoinPossession(ExchangeIDUserIDPairVO param) {
		try {
			return depositWithdrawalsDAO.retrieveCoinPossession(param);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public DepositWithdrawInfoVO retrieveDepositWithdrawInfo(ReqDepositWithdrawInfoVO param) {
		return depositWithdrawalsDAO.retrieveDepositWithdrawInfo(param);
	}

	@Override
	public String getMemberMobile(Map<String, Object> paramMap) throws SQLException {
		return depositWithdrawalsDAO.getMemberMobile(paramMap);
	}
	
	@Override
	public String getMemberMeansCD(Map<String, Object> paramMap) throws SQLException {
		return depositWithdrawalsDAO.getMemberMeansCD(paramMap);
	}
	
	@Override
	public String getMemberOTPCD(Map<String, Object> paramMap) throws SQLException {
		return depositWithdrawalsDAO.getMemberOTPCD(paramMap);
	}
	
	@Override
	public int checkMemberExist(ExchangeIDUserIDPairVO param) {
		return depositWithdrawalsDAO.checkMemberExist(param);
	}



	@SuppressWarnings("static-access")
	@Override
	public Map<String, Object> createNewCoinAddress(Map paramMap) throws SQLException {
		CamelHelper helper = CamelHelper.getInstance();
		try {
			LOG.debug("[CoinisWalletService] >> createNewCoinAddress ");
			
			String strIfTxId = helper.getWasServerIfTxId();
			
			Map<String, Object> headers = new HashMap<>();
			headers.put("SERVER_NO", helper.getServerNo());
			headers.put("COINKIND", paramMap.get("COINKIND").toString());
			headers.put("ENC_KEY", "");
			headers.put("TICKET", strIfTxId);
			headers.put("COMMAND", "DW_GETNEWADDRESS_V1");
			helper.sendMsgMQ("COMMAND_REQUEST", headers, new Gson().toJson(paramMap));
			
			MessageVO msgVo = messageService.getDeferredResult(strIfTxId, 20 * 1000 );
			
			Map<String, Object> resultMap = new HashMap<>();
			resultMap.put("body", msgVo.getBody());
			
			return resultMap;
		} catch(Exception e) {
			return null;
		}
	}

	@Override
	public Map<String, Object> call_PR_WAS_CHECK_WITHDRAW(Map<String, Object> paramMap) {
		return depositWithdrawalsDAO.call_PR_WAS_CHECK_WITHDRAW(paramMap);
	}
	
	@Override
	public Map<String, Object> call_PR_WAS_INSERT_DW_TRANSACTION_HIST(Map<String, Object> paramMap) throws SQLException {
		return depositWithdrawalsDAO.call_PR_WAS_INSERT_DW_TRANSACTION_HIST(paramMap);
	}
	
	@Override
	public Map<String, Object> call_PR_WAS_INSERT_WITHDRAW_REQUEST(Map<String, Object> paramMap) throws SQLException {
		return depositWithdrawalsDAO.call_PR_WAS_INSERT_WITHDRAW_REQUEST(paramMap);
	}

	@Override
	public Map<String, Object> call_PR_WAS_INSERT_AUTH_CODE(Map<String, Object> paramMap) throws SQLException {
		return depositWithdrawalsDAO.call_PR_WAS_INSERT_AUTH_CODE(paramMap);
	}

	@SuppressWarnings({ "static-access", "unchecked" })
	@Override
	public boolean checkValidateionUserCoinAddress(Map<String, Object> paramMap) throws Exception {
		CamelHelper helper = CamelHelper.getInstance();
		try {
			LOG.debug("[CoinisWalletService] >> checkValidateionUserCoinAddress ");
			
			String strIfTxId = helper.getWasServerIfTxId();
			
			Map<String, Object> headers = new HashMap<>();
			headers.put("SERVER_NO", helper.getServerNo());
			headers.put("COINKIND", paramMap.get("COINKIND").toString());
			headers.put("ENC_KEY", "");
			headers.put("TICKET", strIfTxId);
			headers.put("COMMAND", "DW_VALIDATEADDRESS_V1");
			helper.sendMsgMQ("COMMAND_REQUEST", headers, new Gson().toJson(paramMap));
			
			MessageVO msgVo = messageService.getDeferredResult(strIfTxId, 20 * 1000 );
			
			Map<String, Object> resultMap = new Gson().fromJson(msgVo.getBody(), Map.class);

			return (boolean)resultMap.get("validate");
			
		} catch(Exception e) {
			return false;
		}
	}
	
	@Override
	public Map<String, Object> selectMemberAuthMgrTxKeyBal(Map<String, Object> paramMap) throws SQLException {
		return depositWithdrawalsDAO.selectMemberAuthMgrTxKeyBal(paramMap);
	}
	
	@Override
	public Map<String, Object> call_PR_WAS_CHECK_AUTH_CODE(Map<String, Object> paramMap) throws SQLException {
		return depositWithdrawalsDAO.call_PR_WAS_CHECK_AUTH_CODE(paramMap);
	}

	@Override
	public Map<String, Object> call_PR_WAS_SET_DW_REQ_STAT_PROC_CD(Map<String, Object> paramMap) throws SQLException {
		return depositWithdrawalsDAO.call_PR_WAS_SET_DW_REQ_STAT_PROC_CD(paramMap);
	}

	@Override
	public List<Map<String, Object>> selectExchangeCoinList(String exchangeId) throws Exception {
		return depositWithdrawalsDAO.selectExchangeCoinList(exchangeId);
	}

	@Override
	public List<CoinBalanceVO> selectCoinBalanceByUserId(Map paramMap) throws Exception {
		return depositWithdrawalsDAO.selectCoinBalanceByUserId(paramMap);
	}
	
	@Override
	public List<Map<String, Object>> selectUserAvailablePossessionInfo(Map paramMap) throws Exception {
		return depositWithdrawalsDAO.selectUserAvailablePossessionInfo(paramMap);
	}

	@Override
	public CoinMgtRefInfoVO selectCoinMgtRefInfoByCoinNo(int coinNo) throws Exception {
		return depositWithdrawalsDAO.selectCoinMgtRefInfoByCoinNo(coinNo);
	}

	@Override
	public List<Map<String, Object>> selectCoinMgtRefInfoList(Map paramMap) throws Exception {
		return depositWithdrawalsDAO.selectCoinMgtRefInfoList(paramMap);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map<String, Object> selectUserWithdrawalInfo(Map paramMap) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		
		try {
			// 출금가능 한도, 출금 가능액 정보 얻어오기
			Map<String, Object> params = new HashMap<>();
			params.put("ARG_EXCHANGE_ID", paramMap.get("EXCHANGE_ID").toString());
			params.put("ARG_USER_ID", paramMap.get("USER_ID").toString());
			params.put("ARG_COIN_NO", Double.valueOf(paramMap.get("COIN_NO").toString()).intValue() );
			params.put("ARG_REQ_QTY", Double.valueOf(paramMap.get("REQ_QTY").toString()).doubleValue() );
			Map<String, Object> coinWithDrawQtyMap = depositWithdrawalsDAO.call_PR_WAS_CHECK_WITHDRAW(params);
			resultMap.put("WTDRW_POSS_QTY", coinWithDrawQtyMap.get("V_WTDRW_POSS_QTY"));
			resultMap.put("DAILIY_LIMIT_LEFT_QTY", coinWithDrawQtyMap.get("V_DAILIY_LIMIT_LEFT_QTY"));
			resultMap.put("DAILIY_LIMIT_MAX_QTY", coinWithDrawQtyMap.get("V_DAILIY_LIMIT_MAX_QTY"));
			resultMap.put("MONTHLY_LIMIT_LEFT_QTY", coinWithDrawQtyMap.get("V_MONTHLY_LIMIT_LEFT_QTY"));
			
			// 사용자 기본정보 얻어오기
			params.clear();
			params.put("USER_ID", paramMap.get("USER_ID").toString());
			params.put("EXCHANGE_ID", paramMap.get("EXCHANGE_ID").toString());
			Map<String, Object> userInfoMap = depositWithdrawalsDAO.selectMemberInfo(params);
			resultMap.put("AUTH_LEVEL", userInfoMap.get("AUTH_LEVEL"));
			resultMap.put("AUTH_MEANS_CD", userInfoMap.get("AUTH_MEANS_CD"));
			resultMap.put("OTP_YN", userInfoMap.get("OTP_YN"));
			resultMap.put("SMS_USE_YN", userInfoMap.get("SMS_USE_YN"));
			
		} catch(Exception e) {
			LOG.error(CommonUtils.getPrintStackTrace(e));
			throw e;
		}
		return resultMap;
	}

}
