/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.common.service.impl;

import java.io.StringReader;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.runtime.RuntimeServices;
import org.apache.velocity.runtime.RuntimeSingleton;
import org.apache.velocity.runtime.parser.node.SimpleNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import kr.co.coinis.webserver.coinis.member.mbr002.vo.FailCntVO;
import kr.co.coinis.webserver.common.dao.CommDAO;
import kr.co.coinis.webserver.common.message.service.MessageService;
import kr.co.coinis.webserver.common.message.vo.MessageVO;
import kr.co.coinis.webserver.common.service.CommService;
import kr.co.coinis.webserver.common.utils.AuthCode;
import kr.co.coinis.webserver.common.utils.CommonUtils;
import kr.co.coinis.webserver.common.utils.NumberUtils;
import kr.co.coinis.webserver.common.vo.AuthCodeVO;
import kr.co.coinis.webserver.common.vo.CodeVO;
import kr.co.coinis.webserver.common.vo.InsertHistoryVO;
import kr.co.coinis.webserver.common.vo.ItemCodeVO;
import kr.co.coinis.webserver.common.vo.ResultVO;
import kr.co.coinis.webserver.common.vo.SendMailVO;
import kr.co.coinis.webserver.common.vo.SendOrderCancelVO;
import kr.co.coinis.webserver.common.vo.SendOrderVO;
import kr.co.coinis.webserver.common.vo.SendSmsVO;
import kr.co.coinis.webserver.common.web.camel.router.CamelHelper;
import kr.co.coinis.webserver.common.web.camel.router.MessageUtils;

/**
 * <pre>
 * kr.co.coinis.webserver.common.service.impl
 *    |_ CommServiceImpl.java
 *
 * </pre>
 * @date : 2019. 4. 23. ?????? 3:46:10
 * @version :
 * @author : Seongcheol
 */
@Service("commService")
public class CommServiceImpl implements CommService {

	private static final Logger logger = LoggerFactory.getLogger(CommService.class);

	private Gson gson = new Gson();

	@Value("${requestChannel}")
	private String CHANNEL;

	@Value("${auth.email.checkserver}")
	private String emailAuthServer;

	@Resource(name="commDAO")
	private CommDAO commDAO;

	@Autowired
	private MessageService messageService;

	private final CamelHelper helper = CamelHelper.getInstance();

	@Override
	public String selectDefaultMktGrpId(String exchangeId) {
		return commDAO.selectDefaultMktGrpId(exchangeId);
	}

	@Override
	public List<String> selectMktGrpList(String exchangeId) {
		return commDAO.selectMktGrpList(exchangeId);
	}

	@SuppressWarnings("static-access")
	@Override
	public ResultVO sendMail(SendMailVO sendMailVO) throws Exception {

		ResultVO result = new ResultVO();
		String exchangeId = sendMailVO.getExchangeId();

		logger.info("SendMail exchangeId["+exchangeId+"], userId["+sendMailVO.getUserId()+"]");

		try {

			// ?????? ?????? ????????? ?????? ??????(????????????)
			AuthCodeVO authCodeVO = new AuthCodeVO();

			authCodeVO.setExchangeId(exchangeId);
			authCodeVO.setUserId(sendMailVO.getUserId());

			authCodeVO.setAuthPurposeCd(1);
			authCodeVO.setAuthMeansCd(3);
			authCodeVO.setAuthMeansKeyVal(sendMailVO.getUserId());

			//authCodeVO.setExpireSec( Integer.parseInt(helper.getSvrEnv("EMAIL_EXP_SEC")) );
			authCodeVO.setExpireSec(86400);

			// ??????????????? TB_MEMBER_AUTH_MGR ??? ??????
			authCodeVO = commDAO.procAuthCode(authCodeVO);

			//?????? ?????????????????? ?????? ?????????
			if(authCodeVO.getRtnCd() == -1027) {
				commDAO.deleteEmailAuthCode(authCodeVO);
				authCodeVO = commDAO.procAuthCode(authCodeVO);
			}

			if(authCodeVO.getRtnCd() == 0) {

				String strExchangeId = exchangeId;

				//String subject = "[" + strExchangeId + "] ????????????";
				String subject = "[BITA500] ????????????";

/*
				 // LOCAL TEST
				String contents = new String().format(
						"<br>??????????????? ?????????????????? ?????? ????????? ??????????????? ????????????<br>\r\n" +
						"<a href=\"http://127.0.0.1:8080/site/emailAuth?key=%s&id=%s\">??????</a><br>"
						, authCodeVO.getEncryptAuthCd()
						, sendMailVO.getUserId()
						);
*/
				// TEST SERVER
				String contents = new String().format(
						"<br>??????????????? ?????????????????? ?????? ????????? ??????????????? ????????????<br>\r\n" +
						"<a href=\""+emailAuthServer+"/site/emailAuth?key=%s&id=%s\">??????</a><br>"
						, authCodeVO.getEncryptAuthCd()
						, sendMailVO.getUserId()
						);


				Map<String, Object> param = new HashMap<>();

				param.put("msgType", 1002);
				param.put("langCd", "ko");


				// ?????????????????? ?????????(TB_MSG_TEMPLETE) ????????? ??????
				Map<String, Object> templateMap = commDAO.selectTemplateMsgContent(param);

				String v_template_str = templateMap.get("MSG_CONT").toString();

				v_template_str = v_template_str.replace("Yahobit", "Holdport");


				// Velocity engine ????????? template ??????
				RuntimeServices rs = RuntimeSingleton.getRuntimeServices();
				StringReader sr = new StringReader(v_template_str);

				SimpleNode sn = rs.parse(sr, "Send Mail Template");

				Template t = new Template();
				t.setRuntimeServices(rs);
				t.setData(sn);
				t.initDocument();

				VelocityContext vc = new VelocityContext();
				vc.put("Subject", subject);
				vc.put("UserId", sendMailVO.getUserId());
				vc.put("Contents", contents);

				StringWriter sw = new StringWriter();
				t.merge(vc, sw);

				String strHtmlContents = sw.toString();

				// ????????? body ??????
				Map<String, Object> body = new HashMap<String, Object>();
				body.put("exchangeId", sendMailVO.getExchangeId());
				body.put("userId"	, sendMailVO.getUserId());
				body.put("msgOption", sendMailVO.getMsgOption());
				body.put("to"		, sendMailVO.getUserId());
				body.put("subject"	, subject);
				body.put("msg"		, strHtmlContents);

				// ????????? header ????????????
				Map<String, Object> msg = MessageUtils.getMsgHeader(helper.getServerNo(), 9000, 900, new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
				msg.put("body", body);

				// ?????? ??????
				helper.sendMailMsgDirectMQ("sendmail", gson.toJson(msg));


				logger.info("SendMail sendmail queue insert success");



				// ?????? ?????? ?????? ?????? ?????? (30??? ??????)
				//MessageVO vo = messageService.getDeferredResult((String) msg.get("ifTransactionId"), 30 * 1000);

				// ?????? ?????? ??????
				/*if(vo == null) {
					throw new Exception("???????????? ?????? ??????");
				}*/

			} else {
				result.setRtnCd(authCodeVO.getRtnCd());
				result.setRtnMsg(authCodeVO.getRtnMsg());
			}
		} catch (Exception e) {

			logger.error(CommonUtils.getPrintStackTrace(e));
			result.setRtnCd(-5007);
			result.setRtnMsg("???????????? ????????? ?????????????????????");

		}

		return result;
	}

	@SuppressWarnings("static-access")
	@Override
	public ResultVO sendInitPwMail(SendMailVO sendMailVO) throws Exception {
		ResultVO result = new ResultVO();

		String exchangeId = sendMailVO.getExchangeId();
		String tempPw = AuthCode.getTempPw();

		// ???????????? ?????????
		Map<String, Object> param = new HashMap<>();
		param.put("exchangeId", exchangeId);
		param.put("userId", sendMailVO.getUserId());
		param.put("newPw", tempPw);

		param = commDAO.procPwChange(param);

		if((int) param.get("rtnCd") == 0) {
			// ???????????? ?????? ?????? ?????????
			FailCntVO failCntVO = new FailCntVO();
			failCntVO.setProcFlag("L");
			failCntVO.setExchangeId(exchangeId);
			failCntVO.setUserId(sendMailVO.getUserId());

			failCntVO = commDAO.procSetFailCnt(failCntVO);

			if(failCntVO.getRtnCd() == 0) {
				// ?????? ?????? ????????? ?????? ??????(?????????), db?????? ?????? ?????? ????????????
				param.put("msgType", 1002);
				param.put("langCd", "ko");
				Map<String, Object> templateMap = commDAO.selectTemplateMsgContent(param);
				String v_template_str = templateMap.get("MSG_CONT").toString();

				/* 2019.12.06 YAHOBIT ?????????????????? ????????? ALIBIT?????? ??????(strExchangeId) */
				String strExchangeId = exchangeId;
				if(exchangeId.equalsIgnoreCase("YAHOBIT"))
					strExchangeId = "ALIBIT";

				String subject = "[BITA500] ???????????? ?????????";
				String contents = new String().format(
						"<br>?????? ???????????? : %s<br> ????????? ????????? ??? ??????????????? ??????????????? ????????????."
						, tempPw
						);

				RuntimeServices rs = RuntimeSingleton.getRuntimeServices();
				StringReader sr = new StringReader(v_template_str);
				SimpleNode sn = rs.parse(sr, "Send Mail Template");

				Template t = new Template();
				t.setRuntimeServices(rs);
				t.setData(sn);
				t.initDocument();

				VelocityContext vc = new VelocityContext();
				vc.put("Subject", subject);
				vc.put("UserId", sendMailVO.getUserId());
				vc.put("Contents", contents);

				StringWriter sw = new StringWriter();
				t.merge(vc, sw);

				String strHtmlContents = sw.toString();

				// ????????? body ??????
				Map<String, Object> body = new HashMap<String, Object>();
				body.put("exchangeId", sendMailVO.getExchangeId());
				body.put("userId"	, sendMailVO.getUserId());
				body.put("msgOption", sendMailVO.getMsgOption());
				body.put("to"		, sendMailVO.getUserId());
				body.put("subject"	, subject);
				body.put("msg"		, strHtmlContents);

				// ????????? header ????????????
				Map<String, Object> msg = MessageUtils.getMsgHeader(helper.getServerNo(), 9000, 900, new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
				msg.put("body", body);

				// ?????? ??????
				helper.sendMailMsgDirectMQ("sendmail", gson.toJson(msg));

				// ?????? ?????? ?????? ?????? ?????? (30??? ??????)
				//MessageVO vo = messageService.getDeferredResult((String) msg.get("ifTransactionId"), 30 * 1000);

				// ?????? ?????? ??????
				/*if(vo == null) {
					throw new Exception("???????????? ?????? ??????");
				}*/
			} else {
				result.setRtnCd(failCntVO.getRtnCd());
				result.setRtnMsg(failCntVO.getRtnMsg());
			}
		} else {
			result.setRtnCd((int) param.get("rtnCd"));
			result.setRtnMsg((String) param.get("rtnMsg"));
		}

		return result;
	}

	@SuppressWarnings("static-access")
	@Override
	public ResultVO sendInfoMail(String exchangeId, String userId, String subject, String msg, boolean waitresult) throws Exception {
		ResultVO result = new ResultVO();

		// ?????? ?????? ????????? ?????? ??????(?????????), db?????? ?????? ?????? ????????????
		Map<String, Object> param = new HashMap<>();
		param.put("msgType", 1002);
		param.put("langCd", "ko");
		Map<String, Object> templateMap = commDAO.selectTemplateMsgContent(param);
		String v_template_str = templateMap.get("MSG_CONT").toString();

		// Velocity engine ????????? template ??????
		RuntimeServices rs = RuntimeSingleton.getRuntimeServices();
		StringReader sr = new StringReader(v_template_str);
		SimpleNode sn = rs.parse(sr, "Send Mail Template");

		Template t = new Template();
		t.setRuntimeServices(rs);
		t.setData(sn);
		t.initDocument();

		// MSG_CONT??? ????????? HTML ??????????????? ??????????????? ?????? : $Subject, $UserId, $Contents
		VelocityContext vc = new VelocityContext();
		vc.put("Subject", subject);
		vc.put("UserId", userId);
		vc.put("Contents", msg);

		StringWriter sw = new StringWriter();
		t.merge(vc, sw);

		//System.out.println(sw.toString());

		String strHtmlContents = sw.toString();

		/* 2019.12.06 YAHOBIT ?????????????????? ????????? ALIBIT?????? ??????(strHtmlContents) */
		strHtmlContents = strHtmlContents.replace("YAHOBIT", "ALIBIT");
		subject = subject.replace("YAHOBIT", "ALIBIT");

		// ????????? body ??????
		Map<String, Object> body = new HashMap<String, Object>();
		body.put("exchangeId", exchangeId);
		body.put("userId", userId);
		body.put("msgOption", "html");
		body.put("to", userId);
		body.put("subject", subject);
		body.put("msg", strHtmlContents);

		// ????????? header ????????????
		Map<String, Object> queue_msg = MessageUtils.getMsgHeader(helper.getServerNo(), 9000, 900, new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
		queue_msg.put("body", body);

		// ?????? ??????
		helper.sendMailMsgDirectMQ("sendmail", gson.toJson(queue_msg));

		if(waitresult) {
			// ?????? ?????? ?????? ?????? ?????? (30??? ??????)
			MessageVO vo = messageService.getDeferredResult((String) queue_msg.get("ifTransactionId"), 30 * 1000);

			// ?????? ?????? ??????
			if (vo == null) {
				throw new Exception("?????? ?????? ??????");
			}
		}
		result.setRtnCd(0);
		result.setRtnMsg("?????? ?????? ??????");
		return result;
	}

	@SuppressWarnings("static-access")
	@Override
	public ResultVO sendSms(SendSmsVO sendSmsVo) throws Exception {

		ResultVO result = new ResultVO();

		Map<String, Object> bodyMap = new HashMap<String, Object>();
		bodyMap.put("exchangeId", sendSmsVo.getExchangeId());
		bodyMap.put("userId", sendSmsVo.getUserId());
		bodyMap.put("to", sendSmsVo.getTo());
		bodyMap.put("msg", sendSmsVo.getMsg());
		bodyMap.put("msgOption", sendSmsVo.getMsgOption());

		// ????????? header ????????????
		Map<String, Object> msg = MessageUtils.getMsgHeader(CamelHelper.getInstance().getServerNo(), 9000, 902, new SimpleDateFormat("yyyyMMddHHmmssSSSSSS").format(new Date()));
		msg.put("body", bodyMap);

		String body = new Gson().toJson(msg, Map.class);

		helper.sendMailMsgDirectMQ("sendsms", body);

		// SMS ?????? ?????? ?????? ?????? (30??? ??????)
		MessageVO vo = messageService.getDeferredResult((String) msg.get("ifTransactionId"), 30 * 1000);

		// ?????? ?????? ??????
		if(vo == null) {
			result.setRtnCd(-5503);
			result.setRtnMsg("SMS ????????? ?????????????????????.");
			return result;
		}

		result.setRtnCd(0);
		result.setRtnMsg("SMS ????????? ?????????????????????.");

		return result;
	}

	@SuppressWarnings("static-access")
	@Override
	public ResultVO sendOrder(SendOrderVO sendOrderVO) throws Exception {

		Map<String, Object> msg = null;
		ResultVO result = new ResultVO();

		// ????????? ?????? ?????? ??????
		Map<String, Object> param = new HashMap<>();
		param.put("exchangeId", sendOrderVO.getExchangeId());
		param.put("mktId", sendOrderVO.getMktId());
		param.put("userId", sendOrderVO.getUserId());
		param.put("itemCode", sendOrderVO.getItemCode());
		param.put("ordPrice", sendOrderVO.getOrdPrice());
		param.put("ordPriceTypeCd", sendOrderVO.getOrdPriceTypeCd());
		param.put("ordQty", sendOrderVO.getOrdQty());
		param.put("ordTypeCd", sendOrderVO.getOrdTypeCd());
		param.put("sellBuyRecogCd", sendOrderVO.getSellBuyRecogCd());
		param.put("rtnCd", 0);
		param.put("rtnMsg", "");

		ItemCodeVO itemCodeVO = CamelHelper.itemCodeMaster.get(sendOrderVO.getItemCode());

		if(sendOrderVO.getOrdPriceTypeCd() == 1 && NumberUtils.calModOperation(sendOrderVO.getOrdPrice(), itemCodeVO.getOrdPriceUnit(), itemCodeVO.getAmtCalcPnt()) != 0) {
			result.setRtnCd(-5088);
			result.setRtnMsg("?????? ????????? ?????? ???????????????.");
		} else if(!NumberUtils.checkDecimalPoint(sendOrderVO.getOrdQty(), itemCodeVO.getQtyCalcPnt())) {
			result.setRtnCd(-5089);
			result.setRtnMsg("????????? ?????? ????????? ???????????? ?????????????????????.");
		} else {
			param = commDAO.procCheckOrder(param);

			if((int) param.get("rtnCd") == 0) {
				// ?????? ?????? ??? ??????
				param = new HashMap<>();
				param.put("svrNo", helper.getServerNo());
				param.put("ascCd", MessageUtils.getStrAscSum(sendOrderVO.getExchangeId() + sendOrderVO.getUserId()));

				Map<String, String> queue = commDAO.selectOrderQueueNm(param);

				// ?????? ?????? ??????
				Map<String, Object> bodyMap = new HashMap<String, Object>();
				bodyMap.put("exchangeId", sendOrderVO.getExchangeId());
				bodyMap.put("userId", sendOrderVO.getUserId());
				bodyMap.put("mktGrpId", sendOrderVO.getMktGrpId());
				bodyMap.put("mktId", sendOrderVO.getMktId());
				bodyMap.put("ordDt", new SimpleDateFormat("yyyyMMddHHmmssSSSSSS").format(new Date()));
				bodyMap.put("wasSvrNo", helper.getServerNo());
				bodyMap.put("itemCode", sendOrderVO.getItemCode());
				bodyMap.put("ordTypeCd", sendOrderVO.getOrdTypeCd());
				bodyMap.put("ordPriceTypeCd", sendOrderVO.getOrdPriceTypeCd());
				bodyMap.put("sellBuyRecogCd", sendOrderVO.getSellBuyRecogCd());
				bodyMap.put("ordPrice", sendOrderVO.getOrdPrice());
				bodyMap.put("ordQty", sendOrderVO.getOrdQty());
				bodyMap.put("ordBonusQty", sendOrderVO.getOrdBonusQty());
				bodyMap.put("ordChnlCd", CHANNEL);
				bodyMap.put("publicIp", sendOrderVO.getPublicIp());
				bodyMap.put("autoMiningYn", sendOrderVO.getAutoMiningYn());

				if(sendOrderVO.getAutoMiningYn() == 1)
					msg = MessageUtils.getMsgHeaderAuto(helper.getServerNo(), 2000, 101, (String) bodyMap.get("ordDt"));
				else
					msg = MessageUtils.getMsgHeader(helper.getServerNo(), 2000, 101, (String) bodyMap.get("ordDt"));

				msg.put("body", bodyMap);

				//helper.sendMailMsgDirectMQ(queue.get("QUEUE_NM"), gson.toJson(msg, Map.class));
				helper.sendMsgMQ(queue.get("QUEUE_NM"), gson.toJson(msg, Map.class));
			} else {
				result.setRtnCd((int)param.get("rtnCd"));
				result.setRtnMsg((String)param.get("rtnMsg"));
			}
		}

		return result;
	}

	@SuppressWarnings("static-access")
	@Override
	public HashMap<String, Object>  sendOrderNew(SendOrderVO sendOrderVO) throws Exception {

		Map<String, Object> msg = null;

		HashMap<String, Object> resultMap = new HashMap();

		// ????????? ?????? ?????? ??????
		Map<String, Object> param = new HashMap<>();
		param.put("exchangeId", sendOrderVO.getExchangeId());
		param.put("mktId", sendOrderVO.getMktId());
		param.put("userId", sendOrderVO.getUserId());
		param.put("itemCode", sendOrderVO.getItemCode());
		param.put("ordPrice", sendOrderVO.getOrdPrice());
		param.put("ordPriceTypeCd", sendOrderVO.getOrdPriceTypeCd());
		param.put("ordQty", sendOrderVO.getOrdQty());
		param.put("ordTypeCd", sendOrderVO.getOrdTypeCd());
		param.put("sellBuyRecogCd", sendOrderVO.getSellBuyRecogCd());
		param.put("rtnCd", 0);
		param.put("rtnMsg", "");

		ItemCodeVO itemCodeVO = CamelHelper.itemCodeMaster.get(sendOrderVO.getItemCode());

		if(sendOrderVO.getOrdPriceTypeCd() == 1 && NumberUtils.calModOperation(sendOrderVO.getOrdPrice(), itemCodeVO.getOrdPriceUnit(), itemCodeVO.getAmtCalcPnt()) != 0) {

			resultMap.put("resultCode", -5088);
			resultMap.put("resultMsg", "?????? ????????? ?????? ???????????????.");

		} else if(!NumberUtils.checkDecimalPoint(sendOrderVO.getOrdQty(), itemCodeVO.getQtyCalcPnt())) {

			resultMap.put("resultCode", -5089);
			resultMap.put("resultMsg", "????????? ?????? ????????? ???????????? ?????????????????????.");

		} else {
			param = commDAO.procCheckOrder(param);

			if((int) param.get("rtnCd") == 0) {

				// ?????? ?????? ??? ??????
				param = new HashMap<>();
				param.put("svrNo", helper.getServerNo());
				param.put("ascCd", MessageUtils.getStrAscSum(sendOrderVO.getExchangeId() + sendOrderVO.getUserId()));

				Map<String, String> queue = commDAO.selectOrderQueueNm(param);

				// ?????? ?????? ??????
				Map<String, Object> bodyMap = new HashMap<String, Object>();
				bodyMap.put("exchangeId", sendOrderVO.getExchangeId());
				bodyMap.put("userId", sendOrderVO.getUserId());
				bodyMap.put("mktGrpId", sendOrderVO.getMktGrpId());
				bodyMap.put("mktId", sendOrderVO.getMktId());
				bodyMap.put("ordDt", new SimpleDateFormat("yyyyMMddHHmmssSSSSSS").format(new Date()));
				bodyMap.put("wasSvrNo", helper.getServerNo());
				bodyMap.put("itemCode", sendOrderVO.getItemCode());
				bodyMap.put("ordTypeCd", sendOrderVO.getOrdTypeCd());
				bodyMap.put("ordPriceTypeCd", sendOrderVO.getOrdPriceTypeCd());
				bodyMap.put("sellBuyRecogCd", sendOrderVO.getSellBuyRecogCd());
				bodyMap.put("ordPrice", sendOrderVO.getOrdPrice());
				bodyMap.put("ordQty", sendOrderVO.getOrdQty());
				bodyMap.put("ordBonusQty", sendOrderVO.getOrdBonusQty());
				bodyMap.put("ordChnlCd", CHANNEL);
				bodyMap.put("publicIp", sendOrderVO.getPublicIp());
				bodyMap.put("autoMiningYn", sendOrderVO.getAutoMiningYn());

				if(sendOrderVO.getAutoMiningYn() == 1)
					msg = MessageUtils.getMsgHeaderAuto(helper.getServerNo(), 2000, 101, (String) bodyMap.get("ordDt"));
				else
					msg = MessageUtils.getMsgHeader(helper.getServerNo(), 2000, 101, (String) bodyMap.get("ordDt"));

				msg.put("body", bodyMap);

				//helper.sendMailMsgDirectMQ(queue.get("QUEUE_NM"), gson.toJson(msg, Map.class));
				helper.sendMsgMQ(queue.get("QUEUE_NM"), gson.toJson(msg, Map.class));

				resultMap.put("resultCode", 0);
				resultMap.put("resultMsg", "success");
				resultMap.put("ifTransactionId", (String)msg.get("ifTransactionId"));

			} else {

				resultMap.put("resultCode", -1);
				resultMap.put("resultMsg", "procCheckOrder fail");

			}
		}

		return resultMap;
	}



	@SuppressWarnings("static-access")
	@Override
	public ResultVO sendOrderCancel(SendOrderCancelVO sendOrderCancelVO) throws Exception {

		Map<String, Object> msg = null;
		ResultVO result = new ResultVO();

		// ?????? ?????? ??? ??????
		Map<String, Object> param = new HashMap<>();
		param.put("svrNo", helper.getServerNo());
		param.put("ascCd", MessageUtils.getStrAscSum(sendOrderCancelVO.getExchangeId() + sendOrderCancelVO.getUserId()));

		Map<String, String> queue = commDAO.selectOrderQueueNm(param);

		// ?????? ?????? ??????
		Map<String, Object> bodyMap = new HashMap<String, Object>();
		bodyMap.put("exchangeId", sendOrderCancelVO.getExchangeId());
		bodyMap.put("userId", sendOrderCancelVO.getUserId());
		bodyMap.put("mktGrpId", sendOrderCancelVO.getMktGrpId());
		bodyMap.put("mktId", sendOrderCancelVO.getMktId());
		bodyMap.put("wasSvrNo", helper.getServerNo());
		bodyMap.put("itemCode", sendOrderCancelVO.getItemCode());
		bodyMap.put("ordTypeCd", sendOrderCancelVO.getOrdTypeCd());
		bodyMap.put("orgIfTransactionId", sendOrderCancelVO.getOrgIfTransactionId());
		bodyMap.put("ordsvrOrgOrdNo", sendOrderCancelVO.getOrdsvrOrgOrdNo());
		bodyMap.put("exchgsvrOrgOrdNo", sendOrderCancelVO.getExchgsvrOrgOrdNo());
		bodyMap.put("sellBuyRecogCd", sendOrderCancelVO.getSellBuyRecogCd());
		bodyMap.put("ordPrice", sendOrderCancelVO.getOrdPrice());
		bodyMap.put("ordQty", sendOrderCancelVO.getOrdQty());
		bodyMap.put("ordBonusQty", 0.0);
		bodyMap.put("ordChnlCd", CHANNEL);
		bodyMap.put("publicIp", sendOrderCancelVO.getPublicIp());
		bodyMap.put("autoMiningYn", sendOrderCancelVO.getAutoMiningYn());

		msg = MessageUtils.getMsgHeader(helper.getServerNo(), 2000, 201, new SimpleDateFormat("yyyyMMddHHmmssSSSSSS").format(new Date()));
		msg.put("body", bodyMap);

		//helper.sendMailMsgDirectMQ(queue.get("QUEUE_NM"), gson.toJson(msg, Map.class));
		helper.sendMsgMQ(queue.get("QUEUE_NM"), gson.toJson(msg, Map.class));

		return result;
	}

	@SuppressWarnings("static-access")
	@Override
	public ResultVO sendOrderCancelAll(List<SendOrderCancelVO> sendOrderCancelVO, String pulbicIp, int autoMiningYn) throws Exception {

		Map<String, Object> msg = null;
		ResultVO result = new ResultVO();

		if(sendOrderCancelVO.size() > 0) {
			// ?????? ?????? ??? ??????
			Map<String, Object> param = new HashMap<>();
			param.put("svrNo", helper.getServerNo());
			param.put("ascCd", MessageUtils.getStrAscSum(sendOrderCancelVO.get(0).getExchangeId() + sendOrderCancelVO.get(0).getUserId()));

			Map<String, String> queue = commDAO.selectOrderQueueNm(param);
			Map<String, Object> bodyMap = new HashMap<String, Object>();

			for(SendOrderCancelVO temp : sendOrderCancelVO) {
				//?????? ?????? ?????? ??????
				bodyMap = new HashMap<String, Object>();
				bodyMap.put("exchangeId", temp.getExchangeId());
				bodyMap.put("userId", temp.getUserId());
				bodyMap.put("mktGrpId", temp.getMktGrpId());
				bodyMap.put("mktId", temp.getMktId());
				bodyMap.put("wasSvrNo", helper.getServerNo());
				bodyMap.put("itemCode", temp.getItemCode());
				bodyMap.put("ordTypeCd", temp.getOrdTypeCd());
				bodyMap.put("orgIfTransactionId", temp.getOrgIfTransactionId());
				bodyMap.put("ordsvrOrgOrdNo", temp.getOrdsvrOrgOrdNo());
				bodyMap.put("exchgsvrOrgOrdNo", temp.getExchgsvrOrgOrdNo());
				bodyMap.put("sellBuyRecogCd", temp.getSellBuyRecogCd());
				bodyMap.put("ordPrice", temp.getOrdPrice());
				bodyMap.put("ordQty", temp.getOrdQty());
				bodyMap.put("ordBonusQty", 0.0);
				bodyMap.put("ordChnlCd", CHANNEL);
				bodyMap.put("publicIp", pulbicIp);
				bodyMap.put("autoMiningYn", autoMiningYn);

				msg = MessageUtils.getMsgHeader(helper.getServerNo(), 2000, 201, new SimpleDateFormat("yyyyMMddHHmmssSSSSSS").format(new Date()));
				msg.put("body", bodyMap);

				//helper.sendMailMsgDirectMQ(queue.get("QUEUE_NM"), gson.toJson(msg, Map.class));
				helper.sendMsgMQ(queue.get("QUEUE_NM"), gson.toJson(msg, Map.class));
			}
		}

		return result;
	}

	@Override
	public InsertHistoryVO procInsertUserRequestHist(InsertHistoryVO insertHistoryVO) {
		return commDAO.procInsertUserRequestHist(insertHistoryVO);
	}

	@Override
	public List<Map<String, Object>> selectWordBook() {
		return commDAO.selectWordBook();
	}

	@Override
	public List<CodeVO> selectCodeInfoList(Map<String, Object> param) {
		return commDAO.selectCodeInfoList(param);
	}
}
