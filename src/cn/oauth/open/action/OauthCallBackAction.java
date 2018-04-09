package cn.oauth.open.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.oauth.open.constants.Constants;
import cn.oauth.open.proxy.OauthProxy;
import cn.oauth.open.vo.MessageVo;
import cn.oauth.open.vo.OauthUserVo;

@Controller
@RequestMapping(value="/login")
public class OauthCallBackAction {	
	private static Logger log = LoggerFactory.getLogger(OauthCallBackAction.class);	

	@RequestMapping(value="/oauth",method=RequestMethod.GET)
	public String oauth(@RequestParam(value="code",required=false,defaultValue="")String code,
			HttpServletRequest request,HttpServletResponse response,Model model){		
		MessageVo messageVo = OauthProxy.getOauthUserInfo(code);		
		if(null != messageVo && messageVo.isIfSuccess()){			
			OauthUserVo oauthUserVo=messageVo.getOauthUserVo();
			if(null != oauthUserVo){
				model.addAttribute("message", oauthUserVo.toString());
				log.error("action=:"+oauthUserVo.toString());
				return "/client/message.jsp";
				
			}
			model.addAttribute("message", Constants.OAUTH_MSG_NEGATIVE_1);
			return "/client/message.jsp";
			
		}else{
			model.addAttribute("message", messageVo.getMsg());
			return "/client/message.jsp";
		}
	}

}
