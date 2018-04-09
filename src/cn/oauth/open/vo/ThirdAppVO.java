package cn.oauth.open.vo;

/**
 * 第三方网站（应用）资料表 来自表thirdAppInfo
 * 
 */
public class ThirdAppVO {
	private String appId; // AppID
	private String appClientId; // 第三方ID
	private String appName; // 第三方应用名
	private String appApiUrl; // 对方放置getCookie的URL;
	private String appTokenUrl; // 取第三方信息
	private String appOpenIdUrl; // 取第三方openid
	private String appeMd5Pass; // MD5加密KEY
	private String appDomain; // 域名
	private Integer cookieExpiresMinute; // Cookie过期时间（分钟）
	private String userNameAddit; // 用户名头
	private Integer status; // 状态，1正常，0 停用

	public static String trim(String value) {
		if (value != null)
			return value.trim();
		else
			return null;
	}

	public String getAppClientId() {
		return appClientId;
	}

	public void setAppClientId(String appClientId) {
		this.appClientId = trim(appClientId);
	}

	public String getAppTokenUrl() {
		return appTokenUrl;
	}

	public void setAppTokenUrl(String appTokenUrl) {
		this.appTokenUrl = trim(appTokenUrl);
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = trim(appId);
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = trim(appName);
	}

	public String getAppApiUrl() {
		return appApiUrl;
	}

	public void setAppApiUrl(String appApiUrl) {
		this.appApiUrl = trim(appApiUrl);
	}

	public String getAppeMd5Pass() {
		return appeMd5Pass;
	}

	public String getAppOpenIdUrl() {
		return appOpenIdUrl;
	}

	public void setAppOpenIdUrl(String appOpenIdUrl) {
		this.appOpenIdUrl = trim(appOpenIdUrl);
	}

	public void setAppeMd5Pass(String appeMd5Pass) {
		this.appeMd5Pass = trim(appeMd5Pass);
	}

	public String getAppDomain() {
		return appDomain;
	}

	public void setAppDomain(String appDomain) {
		this.appDomain = trim(appDomain);
	}

	public Integer getCookieExpiresMinute() {
		return cookieExpiresMinute;
	}

	public void setCookieExpiresMinute(Integer cookieExpiresMinute) {
		this.cookieExpiresMinute = cookieExpiresMinute;
	}

	public String getUserNameAddit() {
		return userNameAddit;
	}

	public void setUserNameAddit(String userNameAddit) {
		this.userNameAddit = trim(userNameAddit);
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ThirdAppVO [appApiUrl=" + appApiUrl + ", appId=" + appId + ", appDomain=" + appDomain + ", appName="
				+ appName + ", cookieExpiresMinute=" + cookieExpiresMinute + ", appeMd5Pass=" + appeMd5Pass
				+ ", status=" + status + ", userNameAddit=" + userNameAddit + "]";
	}

}
