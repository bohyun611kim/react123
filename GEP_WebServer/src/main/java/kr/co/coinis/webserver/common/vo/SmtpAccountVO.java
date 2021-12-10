package kr.co.coinis.webserver.common.vo;

public class SmtpAccountVO {
	private String SMTPLoginID;
	private String SMTPLoginPW;
	
	public String getSMTPLoginID() {
		return SMTPLoginID;
	}
	public void setSMTPLoginID(String sMTPLoginID) {
		SMTPLoginID = sMTPLoginID;
	}
	public String getSMTPLoginPW() {
		return SMTPLoginPW;
	}
	public void setSMTPLoginPW(String sMTPLoginPW) {
		SMTPLoginPW = sMTPLoginPW;
	}
}
