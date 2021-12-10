/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.velocity;

import java.io.StringReader;
import java.io.StringWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.runtime.RuntimeServices;
import org.apache.velocity.runtime.RuntimeSingleton;
import org.apache.velocity.runtime.parser.ParseException;
import org.apache.velocity.runtime.parser.node.SimpleNode;
import org.junit.Test;

/**
 * <pre>
 * kr.co.coinis.webserver.velocity 
 *    |_ TemplateTest.java
 * 
 * </pre>
 * @date : 2019. 5. 22. 오후 4:06:00
 * @version : 
 * @author : kangn
 */
public class TemplateTest {

	@Test
	public void template_test() {
		try {
			String template = "<!DOCTYPE html>\r\n" + 
					"<html lang=\"en\">\r\n" + 
					"\r\n" + 
					"<head>\r\n" + 
					"</head>\r\n" + 
					"\r\n" + 
					"<body>\r\n" + 
					"    <table width=\"100%\" height=\"100%\" cellpadding=\"0\" cellspacing=\"0\" style=\"font-size:14px;font-family:Microsoft Yahei,Arial, Helvetica, sans-serif;padding:0;margin:0;color:#333;background-color:#fff; border-bottom: 5px dashed #1d2088\">\r\n" + 
					"        <tbody>\r\n" + 
					"            <tr>\r\n" + 
					"                <td style=\"width: 20%\"></td>\r\n" + 
					"                <td valign=\"middle\" style=\"padding:33px 0; text-align: center\"><a href=\"https://www.ali-bit.com/\" target=\"_blank\" rel=\"noopener noreferrer\"><img src=\"https://www.ali-bit.com/resources/img/yaho/logo.png\" alt=\"yahobit logo\" style=\"border:0;\"></a>\r\n" + 
					"                </td>\r\n" + 
					"                <td style=\"width: 20%\"></td>\r\n" + 
					"            </tr>\r\n" + 
					"            <tr>\r\n" + 
					"                <td style=\"width: 20%\"></td>\r\n" + 
					"                <td>\r\n" + 
					"                    <div style=\"padding:0 30px;background:#f1f1f1;box-shadow:0 0 5px #aaa;\">\r\n" + 
					"\r\n" + 
					"                        <p style=\"border-bottom: 1px solid #e6e6e6;font-size:18px;padding:20px 0 5px 0;\">\r\n" + 
					"                            $Subject\r\n" + 
					"                        </p>\r\n" + 
					"                        <p style=\"font-size:14px;line-height:30px;padding:20px 0 0 0;color:#666;\">\r\n" + 
					"                            $UserId 회원님,<br>\r\n" + 
					"                            $Contents<br>\r\n" + 
					"                                                 감사합니다.<br>\r\n" + 
					"                            Yahobit<br>\r\n" + 
					"\r\n" + 
					"                            <a href=\"#\" target=\"_blank\" rel=\"noopener noreferrer\">\r\n" + 
					"                                고객서비스팀 드림</a>\r\n" + 
					"                        </p>\r\n" + 
					"                        <p style=\"padding:30px 0 15px 0;font-size:12px;color:#999;line-height:20px;\">Yahobit Team<br>Automated message.please do not reply</p>\r\n" + 
					"\r\n" + 
					"                    </div>\r\n" + 
					"\r\n" + 
					"                </td>\r\n" + 
					"                <td style=\"width: 20%\"></td>\r\n" + 
					"            </tr>\r\n" + 
					"            <tr>\r\n" + 
					"                <td style=\"width: 20%\"></td>\r\n" + 
					"                <td style=\"font-size:12px;color:#999;padding:20px 0; text-align: center\">Copyright© Yahobit All rights Reserved<br>URL：<a style=\"color:#999;text-decoration:none;\" href=\"#\" target=\"_blank\">http://ali-bit.com/</a>&nbsp;&nbsp;E-mail：<a href=\"mailto:support@ali-bit.com\" style=\"color:#999;text-decoration:none;\" target=\"_blank\">support@ali-bit.com</a></td>\r\n" + 
					"                <td style=\"width: 20%\"></td>\r\n" + 
					"            </tr>\r\n" + 
					"        </tbody>\r\n" + 
					"    </table>\r\n" + 
					"</body>\r\n" + 
					"\r\n" + 
					"</html>";
			
			RuntimeServices rs = RuntimeSingleton.getRuntimeServices();
			StringReader sr = new StringReader(template);
			SimpleNode sn = rs.parse(sr, "Send Mail Template");

			Template t = new Template();
			t.setRuntimeServices(rs);
			t.setData(sn);
			t.initDocument();

			VelocityContext vc = new VelocityContext();
			vc.put("Subject", "테스트 메일입니다.");
			vc.put("UserId", "kangnaru@naver.com");
			vc.put("Contents", "고객님께서는 언제 로그인을 하셨습니다.");

			StringWriter sw = new StringWriter();
			t.merge(vc, sw);

			System.out.println(sw.toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
