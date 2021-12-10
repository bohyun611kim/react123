/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.holdport.product.prd001.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.co.coinis.webserver.holdport.product.prd001.vo.CoinMgtRefInfoVO;
import kr.co.coinis.webserver.holdport.product.prd001.vo.EventVO;
import kr.co.coinis.webserver.holdport.product.prd001.vo.ExchangeMktInfoVO;
import kr.co.coinis.webserver.holdport.product.prd001.vo.ItemCodeMastVO;
import kr.co.coinis.webserver.holdport.product.prd001.vo.NoticeVO;
import kr.co.coinis.webserver.holdport.product.prd001.vo.WithdrawLimitVO;

/**
 * <pre>
 * kr.co.coinis.webserver.support.prd001.dao
 *    |_ NoticeDAO.java
 *
 * </pre>
 * @date : 2019. 3. 21. 오후 1:31:27
 * @version :
 * @author : Seongcheol
 */
@SuppressWarnings("rawtypes")
public interface HoldportProductDAO {

	public static final String NAMESPACE = HoldportProductDAO.class.getName();

	/**
	 *
	 * <pre>
	 * 1. 개요 : TB_NOTICE 테이블에서 공지사항 리스트를 얻어온다.
	 * 2. 처리내용 : TOP_DISP_YN DESC, URGENT_YN DESC 정렬, DISP_START_DT 가 오늘날짜보다 작은것 가져온다.
	 * </pre>
	 * @Method Name : retrieveNoticeList
	 * @date : 2019. 4. 24.
	 * @author : kangn
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4. 24.		kangn				최초 작성
	 *	-----------------------------------------------------------------------
	 *
	 * @return
	 * @throws SQLException
	 */
	public List<NoticeVO> retrieveNoticeList(Map paramMap) throws SQLException;
	public List<NoticeVO> selectNoticeListCount(Map paramMap) throws SQLException;

	/**
	 *
	 * <pre>
	 * 1. 개요 : TB_EVENT 테이블에서 이벤트 리스트를 얻어온다.
	 * 2. 처리내용 :
	 * </pre>
	 * @Method Name : selectEventList
	 * @date : 2019. 5. 17.
	 * @author : kangn
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 17.		kangn				최초 작성
	 *	-----------------------------------------------------------------------
	 *
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public List<EventVO> selectEventList(Map paramMap) throws Exception;
	public List<EventVO> selectEventListCount(Map paramMap) throws Exception;

	/**
	 *
	 * <pre>
	 * 1. 개요 : TB_ITEM_CODE_MAST 테이블에서 BASIC_ASSET_COIN_NO 별로 리스트를 가져온다.
	 * 2. 처리내용 :
	 * </pre>
	 * @Method Name : selectItemCodeMasterByBasicCoinNo
	 * @date : 2019. 4. 25.
	 * @author : kangn
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4. 25.		kangn				최초 작성
	 *	-----------------------------------------------------------------------
	 *
	 * @param paramMap
	 * @return
	 * @throws SQLException
	 */
	public List<ItemCodeMastVO> selectItemCodeMasterByBasicCoinNo(Map paramMap) throws SQLException;
	public List<ItemCodeMastVO> selectCoinCodeMasterInitData(Map paramMap) throws SQLException;

	/**
	 *
	 * <pre>
	 * 1. 개요 : TB_EXCHANGE_MKT_INFO 테이블에서 기초자산 코인식별번호에 해당하는 수수료 리스트를 가져온다.
	 * 2. 처리내용 :
	 * </pre>
	 * @Method Name : selectExchangeMarketInfoList
	 * @date : 2019. 4. 25.
	 * @author : kangn
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4. 25.		kangn				최초 작성
	 *	-----------------------------------------------------------------------
	 *
	 * @param paramMap
	 * @return
	 * @throws SQLException
	 */
	public List<ExchangeMktInfoVO> selectExchangeMarketInfoList(Map paramMap) throws SQLException;

	/**
	 *
	 * <pre>
	 * 1. 개요 : 코인관리기준정보 테이블에서 출금수수료, 최소출금수량, 최소입금수량 select
	 * 2. 처리내용 :
	 * </pre>
	 * @Method Name : selectCoinMgtRefInfoList
	 * @date : 2019. 4. 26.
	 * @author : kangn
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4. 26.		kangn				최초 작성
	 *	-----------------------------------------------------------------------
	 *
	 * @param paramMap
	 * @return
	 * @throws SQLException
	 */
	public List<CoinMgtRefInfoVO> selectCoinMgtRefInfoList(Map paramMap) throws SQLException;

	/**
	 * <pre>
	 * 1. 개요 : 출금 한도 조회
	 * 2. 처리내용 :
	 * </pre>
	 * @Method Name : selectCoinWithdrawLimit
	 * @date : 2019. 7. 23.
	 * @author : Seongcheol
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 7. 23.		Seongcheol				최초 작성
	 *	-----------------------------------------------------------------------
	 *
	 * @param exchangeId
	 * @return
	 * @throws SQLException
	 */
	public List<WithdrawLimitVO> selectCoinWithdrawLimit(String exchangeId) throws SQLException;

	public Map<String, Object> selectCoinBalance(Map paramMap) throws SQLException;

	public HashMap<String, Object> selectCoinTradeAmout(HashMap paramMap) throws SQLException;

	public List<Map<String, Object>> selectPointConvertList(Map paramMap) throws SQLException;

	public HashMap<String, Object> procPointConvertRequest(HashMap<String, Object> mapData) throws SQLException;

	public HashMap<String, Object> procCoinToPointConvert(HashMap<String, Object> mapData) throws SQLException;


}
