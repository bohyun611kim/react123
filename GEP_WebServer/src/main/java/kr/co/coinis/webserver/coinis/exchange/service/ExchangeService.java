package kr.co.coinis.webserver.coinis.exchange.service;

import java.util.List;
import java.util.Map;

import kr.co.coinis.webserver.coinis.exchange.vo.CodeVO;
import kr.co.coinis.webserver.coinis.exchange.vo.CoinInfoVO;
import kr.co.coinis.webserver.coinis.exchange.vo.DefaultItemCodeVO;
import kr.co.coinis.webserver.common.vo.SendOrderCancelVO;

/**
 * <pre>
 * kr.co.coinis.webserver.exchange.service 
 *    |_ ExchangeService.java
 * 
 * </pre>
 * @date : 2019. 3. 21. 오후 3:05:39
 * @version : 
 * @author : Seongcheol
 */
public interface ExchangeService {
	
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
	 * 1. 개요 : 마켓 리스트, 보유 코인 정보 조회
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
	 * @param param
	 * @return
	 */
	public Map<String, Object> selectMarketList(Map<String, String> param);
	
	/**
	 * <pre>
	 * 1. 개요 : 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectItemInfo
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
	public Map<String, Object> selectItemInfo(Map<String, Object> param);
	
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
	 * 1. 개요 : 코인 정보 조회
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
	public CoinInfoVO selectCoinInfo(String coinNo);
}
