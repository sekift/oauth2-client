package cn.oauth.open.vo;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

public class OauthTokenVo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private String token;
	private long expires;
	public String getToken() {
		return token;
	}

	public void setTokenId(String token) {
		this.token = token;
	}

	public long getExpires() {
		return expires;
	}

	public void setExpires(long expires) {
		this.expires = expires;
	}

	@Override
	public String toString() {
		return "OauthTokenVo [" + "token=" + token + ", expires=" + expires + "]";
	}

	public OauthTokenVo() {
	}

	public OauthTokenVo(JSONObject json) throws JSONException {
		super();
		init(json);
	}

	private void init(JSONObject json) throws JSONException {
		if (json != null) {
			try {
				JSONObject jsonData = JSONObject.fromObject(json.getString("data"));	
				token = jsonData.getString("tokenId");
				expires = jsonData.getInt("expires");
			} catch (JSONException jsone) {
				throw new JSONException(jsone.getMessage() + ":" + json.toString(), jsone);
			}
		}
	}

}
