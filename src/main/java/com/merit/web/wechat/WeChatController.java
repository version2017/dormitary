package com.merit.web.wechat;

import com.merit.constant.Constant;
import com.merit.dto.AjaxHandleResult;
import com.merit.utils.TextUtils;
import com.merit.utils.weixin.model.AccessToken;
import com.merit.utils.weixin.util.SignUtil;
import com.merit.utils.weixin.util.WeixinUtil;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Created by R on 2018/6/14.
 */
@Controller//类似@Service,@Component,把controller放入spring容器中
@RequestMapping("/weChat")//url:/模块/资源/{id}/细分
public class WeChatController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
    *<p>功能描述：确认请求来自微信服务器（未能通过验证，未使用。保留做研究用，Servlet和SpringMVC返回字符串是否不同）</p>
    *<ul>
    *<li>@param [request, response]</li>
    *<li>@return java.lang.String</li>
    *<li>@throws </li>
    *<li>@author R</li>
    *<li>@date 2018/6/14 9:33</li>
    *</ul>
    */
    @RequestMapping(value = "/reqCheck", method = RequestMethod.GET)
    @ResponseBody
    public String register(HttpServletRequest request, HttpServletResponse response)throws IOException{
        // 微信加密签名
        String msg_signature = request.getParameter("msg_signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        // 随机字符串
        String echostr = request.getParameter("echostr");

//        PrintWriter out = response.getWriter();
        System.out.println("msg_signature==" + msg_signature);
        System.out.println("timestamp==" + timestamp);
        System.out.println("nonce==" + nonce);
        System.out.println("echostr==" + echostr);
        boolean validResult = SignUtil.checkSignature(msg_signature, timestamp, nonce);
        System.out.println("validResult==" + validResult);
        response.setContentType("text");
        // 通过检验msg_signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
        if (validResult) {
//            out.print(echostr);
//            out.flush();
//            return "{'echostr':'" + echostr + "'}";
            return echostr;
        }
//        out.close();
//        out = null;
        return "";
    }

    /**
    *<p>功能描述：调用微信接口获得用户基本信息，向request添加openid</p>
    *<ul>
    *<li>@param [model, request, code]</li>
    *<li>@return java.lang.String</li>
    *<li>@throws </li>
    *<li>@author R</li>
    *<li>@date 2018/6/14 21:55</li>
    *</ul>
    */
    @RequestMapping(value = "/getOpenid", method = RequestMethod.GET)
    public String openid(Model model, HttpServletRequest request, @RequestParam("code") String code){
        System.out.println("/********Get into getOpenid Method********/");
        if(TextUtils.isEmpty(code)){
            logger.error("无法获得微信服务器发送的code");
            model.addAttribute("errorMessage", "无法获得微信服务器发送的code");
            return "error";
        }
        logger.info("Get code from url:"+code);
        String access_token_url = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo?access_token=ACCESS_TOKEN&code=CODE";
        try{
            AccessToken accessToken = getAccessToken();
            access_token_url = access_token_url.replaceAll("ACCESS_TOKEN", accessToken.getToken());
            access_token_url = access_token_url.replaceAll("CODE", code);
            System.out.println("请求地址userinfo_url为:" + access_token_url);
            JSONObject jsonObject = WeixinUtil.httpRequest(access_token_url, "GET", null);
            System.out.println("返回jsonObject内容："+jsonObject.toString());
            if(null != jsonObject && jsonObject.size() == 4){
                String OpenId = jsonObject.getString(Constant.GET_OPEN_ID_KEY);
                logger.info("wechatId == " + OpenId);
                return "redirect:/view/isFirstTimeLogin?" + "wechatId=" + OpenId;
            }
            logger.error("用户基本信息获取异常：" + jsonObject.toString());
            model.addAttribute("errorMessage", "获取用户信息异常，请重新进入");
            return "error";
        }catch (JSONException je){
            logger.error(je.getMessage(), je);
            return "tips";
        }
        catch (Exception e){
            logger.error(e.getMessage(), e);
            return "error";
        }finally {
            System.out.println("/****************************************/");
        }
    }

    /**
    *<p>功能描述：调用微信接口获得用户基本信息，向request添加openid</p>
    *<ul>
    *<li>@param [model, request, code]</li>
    *<li>@return java.lang.String</li>
    *<li>@throws </li>
    *<li>@author R</li>
    *<li>@date 2018/9/29 11:36</li>
    *</ul>
    */
    @RequestMapping(value = "/postOpenid", method = RequestMethod.POST)
    @ResponseBody
    public AjaxHandleResult postOpenid(Model model, HttpServletRequest request, @RequestParam("code") String code){
        System.out.println("/********Get into postOpenid Method********/");
        AjaxHandleResult handleResult = null;
        if(TextUtils.isEmpty(code)){
            logger.error("无法获得微信服务器发送的code");
            model.addAttribute("errorMessage", "无法获得微信服务器发送的code");
            handleResult = new AjaxHandleResult(false, "无法获得微信服务器发送的code");
            return handleResult;
        }
        logger.info("Get code from url:"+code);
        String access_token_url = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo?access_token=ACCESS_TOKEN&code=CODE";
        try{
            AccessToken accessToken = getAccessToken();
            access_token_url = access_token_url.replaceAll("ACCESS_TOKEN", accessToken.getToken());
            access_token_url = access_token_url.replaceAll("CODE", code);
            System.out.println("请求地址userinfo_url为:" + access_token_url);
            JSONObject jsonObject = WeixinUtil.httpRequest(access_token_url, "GET", null);
            System.out.println("返回jsonObject内容："+jsonObject.toString());
            if(null != jsonObject && jsonObject.size() == 4){
                String OpenId = jsonObject.getString(Constant.GET_OPEN_ID_KEY);
                logger.info("wechatId == " + OpenId);
                handleResult = new AjaxHandleResult(true, OpenId, true);
                return handleResult;
            }
            logger.error("用户基本信息获取异常：" + jsonObject.toString());
            model.addAttribute("errorMessage", "获取用户信息异常，请重新进入");
            handleResult = new AjaxHandleResult(false, "身份信息获取异常");
        }catch (JSONException je){
            logger.error(je.getMessage(), je);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
        }finally {
            System.out.println("/****************************************/");
            if(null == handleResult){
                handleResult = new AjaxHandleResult(false, "系统出错");
            }
            return handleResult;
        }
    }

    private AccessToken getAccessToken() throws Exception{
        AccessToken accessTokenObj = Constant.ACCESS_TOKEN_OBJ;
        long accessTokenObjGetTime = Constant.ACCESS_TOKEN_OBJ_GET_TIME;
        boolean isEmpty = (null == accessTokenObj);
        boolean isOverDue = true;
        if(!isEmpty){
            isOverDue = (new Date().getTime() > (accessTokenObj.getExpiresIn()*1000 + accessTokenObjGetTime));
        }
        if(isEmpty || isOverDue){
            accessTokenObj = WeixinUtil.getAccessToken(Constant.APP_ID, Constant.AGENT_SECRET);
            accessTokenObjGetTime = new Date().getTime();
            Constant.ACCESS_TOKEN_OBJ = accessTokenObj;
            Constant.ACCESS_TOKEN_OBJ_GET_TIME = accessTokenObjGetTime;
            System.out.println("获取到AccessToken：" + accessTokenObj);
            System.out.println("获取时间：" + accessTokenObjGetTime);
        }
        return accessTokenObj;
    }
}
