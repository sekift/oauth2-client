package cn.oauth.open.proxy;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.oauth.open.constants.Constants;
import cn.oauth.open.dao.OauthAppDAO;
import cn.oauth.open.util.HttpUtil;
import cn.oauth.open.util.StringUtil;
import cn.oauth.open.vo.MessageVo;
import cn.oauth.open.vo.OauthTokenErrorVo;
import cn.oauth.open.vo.OauthTokenVo;
import cn.oauth.open.vo.OauthUserVo;
import cn.oauth.open.vo.ThirdAppVO;

public class OauthProxy {
	
	private static Logger log = LoggerFactory.getLogger(OauthProxy.class);	
	
	/**
	 * 获取xx用户信息
	 * @param code
	 * @return
	 */
	public static MessageVo getOauthUserInfo(String code){
		MessageVo messageVo = new MessageVo();	
		messageVo.setIfSuccess(false);
		messageVo.setCode(Constants.OAUTH_CODE_NEGATIVE_1);
		messageVo.setMsg(Constants.OAUTH_MSG_NEGATIVE_1);
		try {
			//获取数据库APP信息
			ThirdAppVO thirdAppVO = OauthAppDAO.getThirdAppInfoFromDb(Constants.OAUTH_APPID);				
			if(null != thirdAppVO && thirdAppVO.getStatus() == 1){
				if(log.isDebugEnabled()){
					log.debug(thirdAppVO.toString());
				}
				//获取token信息
				Object obj = getToken(code,thirdAppVO);
				if(null == obj){
					messageVo.setIfSuccess(false);
					messageVo.setCode(Constants.OAUTH_CODE_1);
					messageVo.setMsg(Constants.OAUTH_MSG_1);
					return messageVo;
				}else{	
					OauthTokenVo oauthTokenVo=null;
					boolean flag = false;
					if(obj instanceof OauthTokenVo){
						flag = true;
						oauthTokenVo=(OauthTokenVo)obj;
					}else if(obj instanceof OauthTokenErrorVo){
						flag = false;
					}
					
					//access token对象
					if(flag){
							//获取xxuser信息
						    OauthUserVo oauthUserVo=getOauthUser(thirdAppVO,oauthTokenVo);
							if(null== oauthUserVo){
								messageVo.setIfSuccess(false);
								messageVo.setCode(Constants.OAUTH_CODE_2);
								messageVo.setMsg(Constants.OAUTH_MSG_2);
								return messageVo;					
							}else{
								messageVo.setIfSuccess(true);
								messageVo.setCode(Constants.OAUTH_CODE_0);
								messageVo.setMsg(Constants.OAUTH_MSG_0);
								messageVo.setOauthUserVo(oauthUserVo);
								return messageVo;
							}	
					}else{
						messageVo.setIfSuccess(false);
						messageVo.setCode(Constants.OAUTH_CODE_1);
						messageVo.setMsg(Constants.OAUTH_MSG_1);						
						return messageVo;
					}					
				}				
			}else{
				messageVo.setIfSuccess(false);
				messageVo.setCode(Constants.OAUTH_CODE_3);
				messageVo.setMsg(Constants.OAUTH_MSG_3);
				return messageVo;
			}
			
		} catch (Exception e) {
			if(log.isErrorEnabled()){
				log.error(Constants.OAUTH_MSG_NEGATIVE_1, e);
			}
		}
		return messageVo;		
	}
	
	
	/**
	 * 获取oauth token
	 * @param code
	 * @param thirdAppVO
	 * @return
	 * @throws Exception
	 */
	private static Object getToken(String code,ThirdAppVO thirdAppVO) throws Exception{	
		Map<String,String> tokenParams = new HashMap<String,String>();
		tokenParams.put(Constants.OAUTH_CLIENT_ID,thirdAppVO.getAppClientId());
		tokenParams.put(Constants.OAUTH_CLIENT_SECRET,thirdAppVO.getAppeMd5Pass());
		tokenParams.put(Constants.OAUTH_GRANT_TYPE,Constants.OAUTH_GRANT_TYPE_AUTHORIZATION_CODE);
		tokenParams.put(Constants.OAUTH_REDIRECT_URI,Constants.OAUTH_REDIRECT_URI_VALUE);
		tokenParams.put(Constants.OAUTH_CODE,code);
		
		String tokenJson = HttpUtil.post(thirdAppVO.getAppTokenUrl(), tokenParams, Constants.OAUTH_CONNECTION_OUTTIME, Constants.OAUTH_READ_OUTTIME, Constants.OAUTH_CHARSET);	
		//失败重试
		if(tokenJson == null){
			tokenJson = HttpUtil.post(thirdAppVO.getAppTokenUrl(), tokenParams, Constants.OAUTH_CONNECTION_OUTTIME, Constants.OAUTH_READ_OUTTIME, Constants.OAUTH_CHARSET);	
		}
		
		if(log.isDebugEnabled()){
			log.debug("tokenJson="+tokenJson);
		}
		
		if(StringUtil.isNullOrBlank(tokenJson)){
			return null;
		}
		
		if(tokenJson.contains("\"success\":0")){
			JSONObject jSONObject = JSONObject.fromObject(tokenJson);	
			String error = jSONObject.getString("success");
			int errorCode = jSONObject.getInt("code");
			String errordescription = jSONObject.getString("message");	
			
			OauthTokenErrorVo oauthTokenErrorVo=new OauthTokenErrorVo();
			oauthTokenErrorVo.setError(error);
			oauthTokenErrorVo.setErrorCode(errorCode);
			oauthTokenErrorVo.setErrordescription(errordescription);
			
			return oauthTokenErrorVo;
		}else{
			JSONObject jSONObject = JSONObject.fromObject(tokenJson);	
			OauthTokenVo oauthTokenVo=new OauthTokenVo(jSONObject);
			
			if(log.isDebugEnabled()){
				log.debug(oauthTokenVo.toString());
			}
			return oauthTokenVo;	
		}
	}
	
	/**
	 * 取xxuser信息
	 * @param thirdAppVO
	 * @param oauthTokenVo
	 * @return
	 * @throws Exception
	 */
	private static OauthUserVo getOauthUser(ThirdAppVO thirdAppVO,OauthTokenVo oauthTokenVo) throws Exception{		
		//取user信息
		Map<String,String> userParams = new HashMap<String,String>();
		userParams.put(Constants.OAUTH_TOKEN, oauthTokenVo.getToken());
		userParams.put(Constants.OAUTH_CLIENT_ID, thirdAppVO.getAppClientId());
		
		String userJson = HttpUtil.get(thirdAppVO.getAppApiUrl(), userParams, Constants.OAUTH_CONNECTION_OUTTIME, Constants.OAUTH_READ_OUTTIME, Constants.OAUTH_CHARSET);
		//失败重试
		if(userJson == null)
			userJson = HttpUtil.get(thirdAppVO.getAppApiUrl(), userParams, Constants.OAUTH_CONNECTION_OUTTIME, Constants.OAUTH_READ_OUTTIME, Constants.OAUTH_CHARSET);
			
		if(log.isDebugEnabled()){
			log.debug("userJson="+userJson);
		}
		
		if(StringUtil.isNullOrBlank(userJson)){
			return null;
		}
		JSONObject jSONObject = JSONObject.fromObject(userJson);		
		OauthUserVo oauthUserVo=new OauthUserVo(jSONObject);
		if(log.isDebugEnabled()){
			log.debug(oauthUserVo.toString());
		}
		return oauthUserVo;	
	}

}
