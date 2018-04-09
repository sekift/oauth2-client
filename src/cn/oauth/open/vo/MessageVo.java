package cn.oauth.open.vo;

public class MessageVo implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	
	private boolean ifSuccess = false;
	private int code;
	private String msg = "";
	private OauthUserVo oauthUserVo;	
	private OauthTokenVo oauthTokenVo;	
	
	public boolean isIfSuccess() {
		return ifSuccess;
	}
	public void setIfSuccess(boolean ifSuccess) {
		this.ifSuccess = ifSuccess;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public OauthUserVo getOauthUserVo() {
		return oauthUserVo;
	}
	public void setOauthUserVo(OauthUserVo oauthUserVo) {
		this.oauthUserVo = oauthUserVo;
	}
	public OauthTokenVo getOauthTokenVo() {
		return oauthTokenVo;
	}
	public void setOauthTokenVo(OauthTokenVo oauthTokenVo) {
		this.oauthTokenVo = oauthTokenVo;
	}
	
	@Override
	public String toString() {
		return "MessageVo [" +
		"ifSuccess=" + ifSuccess +
		", code=" + code + 
		", msg="+ msg +
		", oauthUserVo="+ oauthUserVo.toString() +
		", oauthTokenVo="+ oauthTokenVo.toString() +
		"]";
	}
	
}
