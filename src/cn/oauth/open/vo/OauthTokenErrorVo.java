package cn.oauth.open.vo;

public class OauthTokenErrorVo {
private static final long serialVersionUID = 1L;
	
	private String error;
	private int errorCode;
	private String errordescription;
	
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrordescription() {
		return errordescription;
	}
	public void setErrordescription(String errordescription) {
		this.errordescription = errordescription;
	}
	
	@Override
	public String toString() {
		return "OauthTokenErrorVo [" +
		"error=" + error +
		", errorCode=" + errorCode + 
		", errordescription="+ errordescription +
		"]";
	}
}
