/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.common.utils;

import kr.co.coinis.webserver.common.vo.KcpAuthVO;
import kr.co.kcp.CT_CLI;
import kr.co.kcp.CT_CLI_TEST;

/**
 * kcp 본인 인증
 * <pre>
 * kr.co.coinis.webserver.common.utils
 *    |_ KCPUtils.java
 *
 * </pre>
 * @date : 2019. 5. 13. 오후 4:27:40
 * @version :
 * @author : Seongcheol
 */
public class KCPUtils {

	public static KcpAuthVO decrypt(String siteCd, String certNo, String encCertData) {
		KcpAuthVO result = new KcpAuthVO();

		CT_CLI_TEST cc = new CT_CLI_TEST();

		cc.decryptEncCert(siteCd, certNo, encCertData);
		cc.setCharSetUtf8();

		System.out.println("res_cd["+cc.getKeyValue("res_cd")+"],res_msg["+cc.getKeyValue("res_msg")+"]");

		result.setResultCd(cc.getKeyValue("res_cd"));
		result.setResultMsg(cc.getKeyValue("res_msg"));

		if("0000".equals(result.getResultCd())) {
			// 통신사 코드(SKT, KTF, LGT, SKM, KTM, LGM)
			result.setCommId(cc.getKeyValue("comm_id"));

			// 성별코드(01:남성(M), 02:여성(F))
			result.setSexCode(cc.getKeyValue("sex_code"));

			// 명의자명
			result.setUserName(cc.getKeyValue("user_name"));

			// 휴대폰 번호
			result.setPhoneNo( cc.getKeyValue("phone_no") );

			// 생년월일
			result.setBirthDay( cc.getKeyValue("birth_day") );

			// 내/외국인 정보(01:내국인, 02:외국인)
			result.setLocalCode(cc.getKeyValue("local_code"));
		}

		return result;
	}


	public static KcpAuthVO decryptNewData(String siteCd, String certNo, String encCertData) {

		String siteKey="b5160d7d2ca82b88dbb5a51de4053b6e5f98f549bcfe1b0eb78c5c9e6ff3a74a";

		KcpAuthVO result = new KcpAuthVO();
		CT_CLI cc = new CT_CLI();

		cc.decryptEncCert(siteKey, siteCd, certNo, encCertData);
		cc.setCharSetUtf8();

		result.setResultCd(cc.getKeyValue("res_cd"));
		result.setResultMsg(cc.getKeyValue("res_msg"));

		if("0000".equals(result.getResultCd())) {
			// 통신사 코드(SKT, KTF, LGT, SKM, KTM, LGM)
			result.setCommId(cc.getKeyValue("comm_id"));

			// 성별코드(01:남성(M), 02:여성(F))
			result.setSexCode(cc.getKeyValue("sex_code"));

			// 명의자명
			result.setUserName(cc.getKeyValue("user_name"));

			// 휴대폰 번호
			result.setPhoneNo( cc.getKeyValue("phone_no") );

			// 생년월일
			result.setBirthDay( cc.getKeyValue("birth_day") );

			// 내/외국인 정보(01:내국인, 02:외국인)
			result.setLocalCode(cc.getKeyValue("local_code"));

			//CI정보
			result.setCiValue(cc.getKeyValue("ci"));

		}

		return result;
	}

	public static void main(String args[]) throws Exception {

		String encCertData=".1.E63CD17575C55BDD8FD14931F9299098D0E94DCA6AA6398E378DB48ADDF2FCE12369E08C4BB1C1D0D71EF24E4C4C0A8726D7890F1335BD7AFF68235CD80BB66D55AB5A7EF06C34FB30938D23E3BBB9E7B464ADF4CDC8CB53922284DC21471405486E3CFC08A62D78CC7651A76333597B3731147AAF84726053FEC8C696325D22318FD6C161AD0DD8541346E99032F114506E23C07B9CC682E0B682FB2CB4081AEC7EF15183A6E44AC25117C8533E51788007D3F7E5385AD5EF919563D27679E343C41E228A8EE16C3FE06C5F352706F408FCFC8450B1A423C00E5868EA7BE5E01DF139D22EB27A0ACC7500B0A78EE82A31F7FD8C33D5983FC3FFEF1DE2B6581E552B2540AC82290B9FC36F9C8A4AFDCD884934A7AFA4B532007B2C70240148B651357CD5C3CE25E721E13980C5529C3228A9FD671D24AAD6B03BDCEDE58FCCA6404329587CA73DC80A9F31E06EB7B10E4EA6FADE3B16DC2331C921E5D1854E12193ADC25E483627C1C8BEED83ED70253DA75578CAECD42D65DE65F80E95FD6F652CF9067329232393761B439D75D07DBEB3DB33E3B99BE90F53552976CF1408CDCDCB35019CDBBABBF01F740F6A070D4DEEFC97BE956EB74D84F903F928F91C3EA57B273FD68DDC629CFAD6A2CCD228A606FE86929648F3B46667D36EDC46F00B20541D865174D9164B48CAABC719EA624CD1DDEA14AFA436041C1C0D6F8CA85F6BB3140DEDD1A36CD00DFC142073795BF82C1A1CD2B5E70EBD0553B1D6B0B312DA6B9151EA7D2126D9F16679E2F1A6BE812D8477640DE6BEE6BE9491E52CF5DED609AF4DBAD5C4408D273B19368EA4FDFE8D357E7DED023856AE3A6B9724373B8DEF1EBB64665F954DB6A9787EFB20A5569C7151A791AA2F61421E502145E83F1916EAC94EC5B12D20D7C57AE6BD8F2459B14A6842CC06F822ACC6F20190B80.kJUfp3TzC5YnU9gJ7YAUVgXlVd31kPEWBiysghCHMBFdJzejh4M4ObHHpKFgLMcMYwTby6aaTLrzoeiB7xqHohQD3tdPldCjp-6kdrzRry5nnKKPcnu7HWUX7PXDORriH5qHazGTBz9XeOFnD9mbsODkqP_2-fuPy0nZTWHhNrVgVj6Zzim52DFu_P0lyhjLSHO2acqBzNg13YHvUY5Kf7PcAOX9pndpwU2uN_tRSZ7iyqL3PPM5uBduxIZnCOgbTjz7PXJJJKER6qA1qnvbifQm62sqn6vQ_W36K9G3uol_oEcoJmTf4rPrgOjxYonHnZZPwMZ5aGa9VQqAWdRa9g.MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmO92ZYkJNYMn80Q982_psNFMIS3Zm_oCtEQMfKbap7y3YE9TxCxGWiTlCpsMmmy0WdiJQVJoMLec2Z7O436hSN2praZAQzXVNYq50Y4bhrVZhNlN3KPyM4YjLIldqTuyvgY1xxfrpLLwk1KuDufm5jJf05UcRGsnkWRM6GxhyAqZjTdDYkaP1XXlkMHKi39fPqCoyyF6tlW4byS_mjeVopbFXoCH4DcrVJiO7j9V8oE4ZQiztNPRYr5SoCDOzhGK9zlD4Cz2DMl-XRqrSrvY0-8wLr9bL6UfS9yJPoexXDzcWfrQXIruvCZghg7uwicEqeE6koz2ZXJd62JfbmaQKQIDAQAB.HNG6pvmdasfMPJdcio8RMSx982-qVhg3BKKWmOpXtAM48RsV_FT_16H9sArPUfwM94B_7FeMaN5ZudPiEW9h1Omgr6VbnjBMQGwF3UBoXUYsQKBnk0iwC8ooy_giLAMq8lMfqMs_E_k-D-Ru-bqJhfP0JVM0nK52gAxJJ01TpmjwgzPlLZqO7hcDQtXqga2wu-1QN3PHfzqqIlBQF92WEEr1vQDb5KMnAaNpEE86s5emH9rz1XlrXO7_llpF3Wey85Vex_S0uKF8OQuSTJySjOlKdV3Vv5ceM8WF2UAwxE0xNMIVt4EB1DeIY2_-crUXOxTWUkyejI8cKrHW2dcTYw";
		KcpAuthVO vo = new KcpAuthVO();

		vo = decryptNewData("AEWMJ", "21467083988628", encCertData);

		System.out.println("Name["+vo.getUserName()+"]");

		return;

	}

}
