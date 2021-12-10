package kr.co.coinis.webserver.common.exception;

public class SessionExpiredException extends Exception {
	private static final long serialVersionUID = -8664960209961131778L;
	
	private int Status = 401;
	
	public int getStatus() {
		return Status;
	}

	public void setStatus(int status) {
		Status = status;
	}

}
