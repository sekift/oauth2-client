package cn.oauth.open.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.commons.dbutils.DbUtils;
import org.apache.log4j.Logger;

import cn.oauth.open.constants.Constants;
import cn.oauth.open.vo.ThirdAppVO;

public class OauthAppDAO {
	private static Logger logger = Logger.getLogger(OauthAppDAO.class);
	
	public static ThirdAppVO getThirdAppInfoFromDb(String app_id) {
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "select appName,appApiUrl,appeMd5Pass,appDomain,userNameAddit,cookieExpiresMinute,status,appClientId,appTokenUrl,appOpenIdUrl from thirdAppInfo WHERE appId = ? ";
		conn = ProxoolConnection.getConnectProfile(Constants.OAUTH_DB_SLAVE).getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, app_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				ThirdAppVO website = new ThirdAppVO();
				website.setAppId(app_id);
				website.setAppName(rs.getString("appName"));
				website.setAppApiUrl(rs.getString("appApiUrl"));
				website.setAppeMd5Pass(rs.getString("appeMd5Pass"));
				website.setStatus(rs.getInt("status"));
				website.setCookieExpiresMinute(rs.getInt("cookieExpiresMinute"));
				website.setUserNameAddit(rs.getString("userNameAddit")); 
				website.setAppDomain(rs.getString("appDomain")); 
				website.setAppClientId(rs.getString("appClientId")); 
				website.setAppTokenUrl(rs.getString("appTokenUrl")); 
				website.setAppOpenIdUrl(rs.getString("appOpenIdUrl"));
				return website;
			}
		} catch (Exception e) {
			logger.error("[三方应用]取所有三方资料出错,appId为:" + app_id , e);
		} finally {
			DbUtils.closeQuietly(conn, pstmt, rs);
		}
		return null;
	}

}
