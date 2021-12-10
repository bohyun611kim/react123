/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.yahobit.home.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.co.coinis.webserver.common.utils.CommonUtils;
import kr.co.coinis.webserver.yahobit.home.dao.YahobitHomeDAO;
import kr.co.coinis.webserver.yahobit.home.service.YahobitHomeService;
import kr.co.coinis.webserver.yahobit.home.vo.BannerVO;
import kr.co.coinis.webserver.yahobit.home.vo.TopNoticeVO;

/**
 * <pre>
 * kr.co.coinis.webserver.yahobit.home.dao.impl 
 *    |_ YahoBitHomeServiceImpl.java
 * 
 * </pre>
 * @date : 2019. 4. 25. 오전 9:07:17
 * @version : 
 * @author : Seongcheol
 */
@Service("yahobitHomeService")
public class YahobitHomeServiceImpl implements YahobitHomeService {

	@Resource(name="yahobitHomeDAO")
	private YahobitHomeDAO yahobitHomeDAO;
	
	@Override
	public List<BannerVO> selectBannerList(String exchangeId) {
		return yahobitHomeDAO.selectBannerList(exchangeId);
	}
	
	@Override
	public BannerVO selectBanner(String exchangeId) {
		return yahobitHomeDAO.selectBanner(exchangeId);
	}
	
	@Override
	public List<TopNoticeVO> selectNoticeList(String exchangeId) {
		return yahobitHomeDAO.selectNoticeList(exchangeId);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Map> selectInfluencerTop10List() {
		try {
			List<Map> res = yahobitHomeDAO.selectInfluencerTop10List();
			
			List<Map> resMapList = new ArrayList<Map>();
			for(int i=0; i<10; i++) {
				Map<String, Object> tmpMap = new HashMap<>();
				if(i < res.size()) {
					tmpMap.put("CNT", res.get(i).get("CNT") + " 명");
					tmpMap.put("RECOMMENDER_CD", CommonUtils.strNlv(res.get(i).get("RECOMMENDER_CD"), ""));
					tmpMap.put("USER_ID", CommonUtils.maskingUserId2(CommonUtils.strNlv(res.get(i).get("USER_ID"), "abcdefg@test.com")) );
				} else {
					tmpMap.put("CNT", "");
					tmpMap.put("RECOMMENDER_CD", "");
					tmpMap.put("USER_ID", "" );
				}
				resMapList.add(tmpMap);
			}
			
			return resMapList;
		} catch(Exception e) {
			return null;
		}
	}
}
