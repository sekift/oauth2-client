package cn.oauth.open.constants;

public class Constants {
	// 主库
	public final static String OAUTH_DB_MASTER = "oauth_master";
	// 从库
	public final static String OAUTH_DB_SLAVE = "oauth_slave";
	
	public final static String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public final static String NOTBLANK_DATE_FORMAT = "yyyyMMddHHmmss";
	public final static String NOTBLANK_DATE_FORMAT_DAY = "yyyyMMdd";
	public final static String DEFAULT_DATE_FORMAT_DAY = "yyyy-MM-dd";
	
	public final static String OAUTH_APPID="xx";
	public final static String OAUTH_CLIENT_ID="client_id";
	public final static String OAUTH_CLIENT_SECRET="client_secret";
	public final static String OAUTH_GRANT_TYPE="grant_type";
	public final static String OAUTH_REDIRECT_URI="redirect_uri";
	public final static String OAUTH_REDIRECT_URI_VALUE="http://127.0.0.1:8080/oauth2-client/login/oauth.do";
	public final static String OAUTH_CODE="code";
	public final static String OAUTH_GRANT_TYPE_AUTHORIZATION_CODE = "authorization_code";
	public final static String OAUTH_SOURCE = "source";
	public final static String OAUTH_TOKEN = "token";
	public final static int OAUTH_CONNECTION_OUTTIME = 5000;
	public final static int OAUTH_READ_OUTTIME = 30000;
	public final static String OAUTH_CHARSET = "utf-8";
	
	/**在调用页面过程返回错误**/
	public final static int OAUTH_CODE_NEGATIVE_1 = -1;
	public final static String OAUTH_MSG_NEGATIVE_1 = "获取xx用户操作异常!";
	public final static int OAUTH_CODE_0 = 0;
	public final static String OAUTH_MSG_0 = "操作成功!";
	public final static int OAUTH_CODE_1 = 1;
	public final static String OAUTH_MSG_1 = "获取xx访问令牌失败!";
	public final static int OAUTH_CODE_2 = 2;
	public final static String OAUTH_MSG_2 = "获取xx用户信息失败!";
	public final static int OAUTH_CODE_3 = 3;
	public final static String OAUTH_MSG_3 = "App不存在或已经禁用!";
	
	/**调用token接口时错误的返回编码及信息**/
	public final static int OAUTH_TOKEN_NEGATIVE_1 = -1;
	public final static String OAUTH_TOKEN_ERRORCODE_1="参数不对、暂时只支持authorization_code授权";
	public final static int OAUTH_TOKEN_NEGATIVE_2 = -2;
	public final static String OAUTH_TOKEN_ERRORCODE_2 = "code失效或者已过期、第三方应用检查不通过";
	public final static int OAUTH_TOKEN_NEGATIVE_3 = -3;
	public final static String OAUTH_TOKEN_ERRORCODE_3 = "服务器出错，请稍后再试";
	public final static int OAUTH_TOKEN_1 = 1;
	public final static String OAUTH_SUCCESS_1 = "令牌发放成功";
}

