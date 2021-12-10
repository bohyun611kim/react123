package kr.co.coinis.webserver.common.exception;

public class DataNotFoundException  extends Exception{
	private String msg;

	public DataNotFoundException(){
		super();
	}
	public DataNotFoundException(String msg) {
		super(msg);
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
