package kr.co.coinis.webserver.coinis.exchange.dao;

import java.util.List;
import java.util.Map;

import kr.co.coinis.webserver.coinis.exchange.vo.BalanceInfoVO;
import kr.co.coinis.webserver.coinis.exchange.vo.BalanceVO;
import kr.co.coinis.webserver.coinis.exchange.vo.CodeVO;
import kr.co.coinis.webserver.coinis.exchange.vo.CoinInfoVO;
import kr.co.coinis.webserver.coinis.exchange.vo.ContractVO;
import kr.co.coinis.webserver.coinis.exchange.vo.DefaultItemCodeVO;
import kr.co.coinis.webserver.coinis.exchange.vo.HogaVO;
import kr.co.coinis.webserver.coinis.exchange.vo.ItemCodeVO;
import kr.co.coinis.webserver.coinis.exchange.vo.MarketVO;
import kr.co.coinis.webserver.coinis.exchange.vo.NonContractVO;
import kr.co.coinis.webserver.coinis.exchange.vo.OrderVO;
import kr.co.coinis.webserver.coinis.exchange.vo.RealContractVO;
import kr.co.coinis.webserver.common.vo.SendOrderCancelVO;

/**
 * <pre>
 * kr.co.coinis.webserver.exchange.dao 
 *    |_ ExchangeDAO.java
 * 
 * </pre>
 * @date : 2019. 3. 21. 오후 3:05:21
 * @version : 
 * @author : Seongcheol
 */
public interface ExchangeDAO {

	public static final String NAMESPACE = ExchangeDAO.class.getName();
	
	/**
	 * <pre>
	 * 1. 개요 : itemCode 값이 없을 경우 기본 itemCode 정보 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectDefaultItemCode
	 * @date : 2019. 6. 15.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 6. 15.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param exchangeId
	 * @return
	 */
	public DefaultItemCodeVO selectDefaultItemCode(String exchangeId);
	
	/**
	 * <pre>
	 * 1. 개요 : parameter로 전달 받은 itemCode 존재 여부 확인 및 해당 itemCode 정보 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : checkItemCode
	 * @date : 2019. 6. 15.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 6. 15.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param param
	 * @return
	 */
	public DefaultItemCodeVO checkItemCode(Map<String, Object> param);
	
	/**
	 * <pre>
	 * 1. 개요 : 마켓 리스트 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectMarketList
	 * @date : 2019. 6. 22.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 6. 22.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param exchangId
	 * @return
	 */
	public List<MarketVO> selectMarketList(String exchangId);
	
	/**
	 * <pre>
	 * 1. 개요 : itemCode 정보조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectItmeCodeInfo
	 * @date : 2019. 6. 16.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 6. 16.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param param
	 * @return
	 */
	public ItemCodeVO selectItmeCodeInfo(Map<String, Object> param);
	
	/**
	 * <pre>
	 * 1. 개요 : 매도 호가 20건 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectSellHoga
	 * @date : 2019. 6. 16.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 6. 16.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param param
	 * @return
	 */
	public List<HogaVO> selectSellHoga(Map<String, Object> param);
	
	/**
	 * <pre>
	 * 1. 개요 : 매수 호가 20건 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectBuyHoga
	 * @date : 2019. 6. 16.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 6. 16.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param param
	 * @return
	 */
	public List<HogaVO> selectBuyHoga(Map<String, Object> param);
	
	/**
	 * <pre>
	 * 1. 개요 : 코인 잔고 전체 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectBalanceList
	 * @date : 2019. 6. 22.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 6. 22.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param param
	 * @return
	 */
	public List<BalanceVO> selectBalanceList(Map<String, String> param);
	
	/**
	 * <pre>
	 * 1. 개요 : coinNo에 해당하는 코인 잔고 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectBalance
	 * @date : 2019. 6. 16.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 6. 16.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param param
	 * @return
	 */
	public double selectBalance(Map<String, Object> param);
	
	/**
	 * <pre>
	 * 1. 개요 : 보유 잔고 탭 내용 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectBalanceInfo
	 * @date : 2019. 6. 22.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 6. 22.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param param
	 * @return
	 */
	public BalanceInfoVO selectBalanceInfo(Map<String, Object> param);
	
	/**
	 * <pre>
	 * 1. 개요 : 주문 내역 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectOrderList
	 * @date : 2019. 6. 16.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 6. 16.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param param
	 * @return
	 */
	public List<OrderVO> selectOrderList(Map<String, Object> param);
	
	/**
	 * <pre>
	 * 1. 개요 : 체결 내역 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectContractList
	 * @date : 2019. 6. 16.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 6. 16.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param param
	 * @return
	 */
	public List<ContractVO> selectContractList(Map<String, Object> param);
	
	/**
	 * <pre>
	 * 1. 개요 : 미체결 내역 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectNonContractList
	 * @date : 2019. 6. 16.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 6. 16.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param param
	 * @return
	 */
	public List<NonContractVO> selectNonContractList(Map<String, Object> param);
	
	/**
	 * <pre>
	 * 1. 개요 : 실시간 체결 내역 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectRealContractList
	 * @date : 2019. 6. 16.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 6. 16.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param param
	 * @return
	 */
	public List<RealContractVO> selectRealContractList(Map<String, Object> param);
	
	/**
	 * <pre>
	 * 1. 개요 : 취소에 필요한 정보 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectCancelInfo
	 * @date : 2019. 6. 20.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 6. 20.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param param
	 * @return
	 */
	public SendOrderCancelVO selectCancelInfo(Map<String, Object> param);
	
	/**
	 * <pre>
	 * 1. 개요 : 코드 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectCodeInfo
	 * @date : 2019. 6. 21.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 6. 21.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param param
	 * @return
	 */
	public List<CodeVO> selectCodeInfo(Map<String, Object> param);
	
	/**
	 * <pre>
	 * 1. 개요 :코인 정보 조회 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectCoinInfo
	 * @date : 2019. 6. 24.
	 * @author : Seongcheol
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 6. 24.		Seongcheol				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param coinNo
	 * @return
	 */
	public CoinInfoVO selectCoinInfo(String	coinNo);
}
