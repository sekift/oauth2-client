package cn.oauth.open.vo;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

public class OauthUserVo implements java.io.Serializable{

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 7470951916419630890L;
	private int userId; //id
	private String userName; //名称
	private String sex; //性别
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}

	@Override
	public String toString() {
		return "OauthUserVo [" +
		"userId=" + userId +
		", userName=" + userName + 
		", sex="+ sex +
		"]";
	}
	
	public OauthUserVo(){
	}
	
	public OauthUserVo(JSONObject json) throws JSONException{
		super();
		init(json);
	}
	
	private void init(JSONObject json) throws JSONException {
		if(json!=null){
			try {
				JSONObject jsonDataObject = JSONObject.fromObject(json.getString("data"));
				JSONObject jsonData = JSONObject.fromObject(jsonDataObject.getString("data"));
				userId = Integer.parseInt(jsonData.getString("user_id"));
				userName = jsonData.getString("user_name");
				sex = jsonData.getString("sex");
			} catch (JSONException jsone) {
				throw new JSONException(jsone.getMessage() + ":" + json.toString(), jsone);
			}
		}
	}	
}
