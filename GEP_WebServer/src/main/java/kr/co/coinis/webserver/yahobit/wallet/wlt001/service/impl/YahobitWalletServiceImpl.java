/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.yahobit.wallet.wlt001.service.impl;

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

import kr.co.coinis.webserver.common.message.repository.MessageDAO;
import kr.co.coinis.webserver.common.message.service.MessageService;
import kr.co.coinis.webserver.common.message.vo.MessageVO;
import kr.co.coinis.webserver.common.utils.HolidayUtil;
import kr.co.coinis.webserver.common.web.camel.router.CamelHelper;
import kr.co.coinis.webserver.yahobit.wallet.wlt001.dao.YahobitWalletDAO;
import kr.co.coinis.webserver.yahobit.wallet.wlt001.service.YahobitWalletService;
import kr.co.coinis.webserver.yahobit.wallet.wlt001.vo.CodeInfoVO;
import kr.co.coinis.webserver.yahobit.wallet.wlt001.vo.CoinBalanceVO;
import kr.co.coinis.webserver.yahobit.wallet.wlt001.vo.CoinMgtRefInfoVO;
import kr.co.coinis.webserver.yahobit.wallet.wlt001.vo.DepositWithdrawMgrVO;
import kr.co.coinis.webserver.yahobit.wallet.wlt001.vo.MemberBankAccntInfoVO;
import kr.co.coinis.webserver.yahobit.wallet.wlt001.vo.MemberInfoVO;

/**
 * <pre>
 * kr.co.coinis.webserver.yahobit.wallet.wlt001.service 
 *    |_ YahobitWalletService.java
 * 
 * </pre>
 * @date : 2019. 4. 26. 오후 5:51:19
 * @version : 
 * @author : kangn
 */
@SuppressWarnings("rawtypes")
@Service("yahobitWalletService")
public class YahobitWalletServiceImpl implements YahobitWalletService {

	private static final Logger LOG = LoggerFactory.getLogger(YahobitWalletServiceImpl.class);

	@Resource(name="yahobitWalletDAO")
	private YahobitWalletDAO yahobitWalletDAO;
	
	@Resource(name="messageDAO")
	private MessageDAO messageDAO;
	
	@Autowired
	private MessageService messageService;

	@Override
	public List<CodeInfoVO> selectCodeInfoByCodeId(Map paramMap) throws SQLException {
		return yahobitWalletDAO.selectCodeInfoByCodeId(paramMap);
	}

	@Override
	public List<Map<String, Object>> selectCoinInfoByExchangeId(Map paramMap) throws Exception {
		return yahobitWalletDAO.selectCoinInfoByExchangeId(paramMap);
	}
	
	@Override
	public List<Map<String, Object>> selectCoinInfoByTradeStatCd(Map paramMap) throws Exception {
		return yahobitWalletDAO.selectCoinInfoByTradeStatCd(paramMap);
	}

	@Override
	public List<CoinBalanceVO> selectCoinBalanceByUserId(Map paramMap) throws Exception {
		return yahobitWalletDAO.selectCoinBalanceByUserId(paramMap);
	}

	@Override
	public CoinMgtRefInfoVO selectCoinMgtRefInfoByCoinNo(int coinNo) throws SQLException {
		return yahobitWalletDAO.selectCoinMgtRefInfoByCoinNo(coinNo);
	}
	@Override
	public List<Map<String, Object>> selectCoinMgtRefInfoList(Map paramMap) throws SQLException {
		return yahobitWalletDAO.selectCoinMgtRefInfoList(paramMap);
	}

	@Override
	public Map<String, Object> selectCoinDepositWalletInfoByUserIdAndCoinNo(Map paramMap) throws SQLException {
		return yahobitWalletDAO.selectCoinDepositWalletInfoByUserIdAndCoinNo(paramMap);
	}

	@SuppressWarnings("static-access")
	@Override
	public Map<String, Object> createNewCoinAddress(Map paramMap) throws SQLException {
		CamelHelper helper = CamelHelper.getInstance();
		try {
			LOG.debug("[YahobitWalletService] >> createNewCoinAddress ");
			
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

	@SuppressWarnings("static-access")
	@Override
	public Map<String, Object> checkBankAccountHolderName(Map paramMap) throws SQLException {
		CamelHelper helper = CamelHelper.getInstance();
		try {
			LOG.debug("[YahobitWalletService] >> checkBankAccountHolderName ");
			
			String strIfTxId = helper.getWasServerIfTxId();
			
			Map<String, Object> headers = new HashMap<>();
			headers.put("SERVER_NO", helper.getServerNo());
			headers.put("COINKIND", "KRW");
			headers.put("ENC_KEY", "");
			headers.put("TICKET", strIfTxId);
			headers.put("COMMAND", "DW_VALIDATE_ACCOUNT_HOLDER_V1");
			helper.sendMsgMQ("COMMAND_REQUEST", headers, new Gson().toJson(paramMap));
			
			MessageVO msgVo = messageService.getDeferredResult(strIfTxId, 20 * 1000 );
			
			Map<String, Object> resultMap = new Gson().fromJson(msgVo.getBody(), Map.class);
			//resultMap.put("body", msgVo.getBody());
			//LOG.debug("[YahobitWalletService] >> checkBankAccountHolderName > 결과 : msgVo = " + msgVo.getBody());
			
			return resultMap;
		} catch(Exception e) {
			return null;
		}
	}
	
	@Override
	public Map<String, Object> call_PR_WAS_CHECK_WITHDRAW(Map paramMap) throws SQLException {
		return yahobitWalletDAO.call_PR_WAS_CHECK_WITHDRAW(paramMap);
	}
	
	@Override
	public Map<String, Object> checkOnceWthrwQty(Map paramMap) throws SQLException {
		return yahobitWalletDAO.checkOnceWthrwQty(paramMap);
	}

	@Override
	public List<Map<String, Object>> selectDepositWithdrawList(Map paramMap) throws SQLException {
		return yahobitWalletDAO.selectDepositWithdrawList(paramMap);
	}

	@Override
	public Map<String, Object> selectDepositWithdrawListCount(Map paramMap) throws SQLException {
		return yahobitWalletDAO.selectDepositWithdrawListCount(paramMap);
	}

	@Override
	public Map<String, Object> call_PR_WAS_INSERT_DW_TRANSACTION_HIST(Map paramMap) throws SQLException {
		return yahobitWalletDAO.call_PR_WAS_INSERT_DW_TRANSACTION_HIST(paramMap);
	}

	@Override
	public Map<String, Object> call_PR_WAS_INSERT_WITHDRAW_REQUEST(Map paramMap) throws SQLException {
		return yahobitWalletDAO.call_PR_WAS_INSERT_WITHDRAW_REQUEST(paramMap);
	}

	@Override
	public Map<String, Object> call_PR_WAS_INSERT_AUTH_CODE(Map paramMap) throws SQLException {
		return yahobitWalletDAO.call_PR_WAS_INSERT_AUTH_CODE(paramMap);
	}

	@Override
	public Map<String, Object> call_PR_WAS_CHECK_AUTH_CODE(Map paramMap) throws SQLException {
		return yahobitWalletDAO.call_PR_WAS_CHECK_AUTH_CODE(paramMap);
	}

	@Override
	public Map<String, Object> call_PR_WAS_SET_DW_REQ_STAT_PROC_CD(Map paramMap) throws SQLException {
		return yahobitWalletDAO.call_PR_WAS_SET_DW_REQ_STAT_PROC_CD(paramMap);
	}

	@Override
	public MemberInfoVO selectMemberInfo(Map paramMap) throws SQLException {
		return yahobitWalletDAO.selectMemberInfo(paramMap);
	}

	@Override
	public int deleteMemberAuthMgrByTxKeyBal(Map paramMap) throws SQLException {
		return yahobitWalletDAO.deleteMemberAuthMgrByTxKeyBal(paramMap);
	}

	@Override
	public Map<String, Object> selectMemberAuthMgrTxKeyBal(Map paramMap) throws SQLException {
		return yahobitWalletDAO.selectMemberAuthMgrTxKeyBal(paramMap);
	}

	@Override
	public Map<String, Object> selectExchangeBankAccountInfo(Map paramMap) throws SQLException {
		return yahobitWalletDAO.selectExchangeBankAccountInfo(paramMap);
	}

	@Override
	public Map<String, Object> selectMemberBankAccntInfo(Map paramMap) throws SQLException {
		return yahobitWalletDAO.selectMemberBankAccntInfo(paramMap);
	}
	
	@Override
	public Map<String, Object> insertMemberBankAccntInfo(MemberBankAccntInfoVO memberBankAccntInfoVo)
			throws SQLException {
		return yahobitWalletDAO.insertMemberBankAccntInfo(memberBankAccntInfoVo);
	}

	@Override
	public Map<String, Object> insertRequestMoneyDeposit(Map paramMap) throws SQLException {
		return yahobitWalletDAO.call_PR_WLL_INSERT_DEPOSIT_REQUEST(paramMap);
	}

	@SuppressWarnings({ "static-access", "unchecked" })
	@Override
	public boolean checkValidateionUserCoinAddress(Map paramMap) throws Exception {
		CamelHelper helper = CamelHelper.getInstance();
		try {
			LOG.debug("[YahobitWalletService] >> checkValidateionUserCoinAddress ");
			
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
	public int insertDepositWithdrawManager(DepositWithdrawMgrVO dwMgrVo) throws SQLException {
		return yahobitWalletDAO.insertDepositWithdrawManager(dwMgrVo);
	}

	@SuppressWarnings("unchecked")
	@Override
	public int checkWithdrawOk(Map paramMap) throws Exception {
		try {
			// 최초 원화입급 내역
			paramMap.put("DW_REQ_TYPE_CD", 3);
			Map<String, Object> krwDp = yahobitWalletDAO.getFirstDepositDateTime(paramMap);
			// 최초 코인입금 내역
			paramMap.put("DW_REQ_TYPE_CD", 1);
			Map<String, Object> coinDp = yahobitWalletDAO.getFirstDepositDateTime(paramMap);

			if(krwDp == null || krwDp.get("REQ_DT") == null || krwDp.get("REQ_DT").toString().trim().equals("")) {
				// 원화입금 내역이 없을때는 코인입금 내역을 찾는다.
				if(coinDp == null || coinDp.get("REQ_DT") == null || coinDp.get("REQ_DT").toString().trim().equals("")) {
					return -5510;		// 입금내역이 존재하지 않습니다. 출금시 최초 원화입금후 72시간 이후 가능합니다.
				} else {
					// 코인입금만 존재할때는 제한없이 출금 가능
					return 1004;		// return angel...^^
				}
			} else {
				String firstKrwDpDate = krwDp.get("REQ_DT").toString().substring(0, 10);	// yyyyMMddHH
				// 휴일제외한 Working Day 기준으로 누적시간 가져온다.
				int iAccumTime = HolidayUtil.getAccumulatedWorkingTime(firstKrwDpDate);
				
				if(iAccumTime < 72) return -5511;		// 원화입금후 72시간이 경과하지 않았습니다. (공휴일 제외)
				
				return iAccumTime;
			}
		} catch(Exception e) {
			return -5510;
		}
	}

}
