package com.merit.utils;


import com.merit.constant.Constant;
import com.merit.utils.weixin.WxConstants;
import com.merit.utils.weixin.util.WeixinUtil;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.merit.web.ApiConstants.*;

/**
 * @author Jack 发送模板信息给用户
 */
public class MessageUtils {

    /**
     * <p>功能描述：sendTodoListTempToUser 发送待办事项</p>
     * <ul>
     * <li>@param []</li>
     * <li>@return void</li>
     * <li>@throws </li>
     * <li>@author jackson</li>
     * <li>@date 17-8-9 下午2:36</li>
     * </ul>
     */
    public static void sendTodoListTempToUser(String openId, String content) {
        String friStr = "2017届新生您好，您当前还有未处理的待办事项要处理，请抽空尽快处理。以免影响您的正常学业活动。";
        String todoType = "代办任务未完成";
        String bz = "未完成";
        Map data = initTodoMap(friStr, todoType, bz, content);
        Map result = new HashMap();
        result.put("touser", openId);
        result.put("template_id", todoListTemplateId);
        result.put("url", todoListUrl);
        result.put("topcolor", "#FF0000");
        result.put("data", data);
        WeixinUtil.sendTemplateMsg(result, WxConstants.JSSDKCONS.accessTokenMap.get(Constant.WX_ID).getToken());
    }

    public static void main(String[] args) {
        String openId="oS7wGs-eBY_myFmMw_pERnwOFv0o";
        String content = "测试";
        String endTime = "2017-09-15";
        sendTodoListTempToUser(openId, content);
    }

    /**
    *<p>功能描述：sendDormitoryArrangeToUser 发送寝室安排给用户</p>
    *<ul>
    *<li>@param [openId, name, studentNumber, roomNo]</li>
    *<li>@return void</li>
    *<li>@throws </li>
    *<li>@author jackson</li>
    *<li>@date 17-8-15 下午2:46</li>
    *</ul>
    */
    public static void sendDormitoryArrangeToUser(String openId, String name, String studentNumber, String roomNo) {
        String friStr = "2017届新生您好，学校已经为您安排好了宿舍，请查看。";
        Map data = initTodoMap(friStr, name, studentNumber, roomNo);
        Map result = new HashMap();
        result.put("touser", openId);
        result.put("template_id", dormitoryArrangeId);
        result.put("url", todoListUrl);
        result.put("topcolor", "#FF0000");
        result.put("data", data);
        WeixinUtil.sendTemplateMsg(result, WxConstants.JSSDKCONS.accessTokenMap.get(Constant.WX_ID).getToken());
    }

    /**
    *<p>功能描述：reportSuccessToUser 报道成功，发送推送消息给用户</p>
    *<ul>
    *<li>@param [openId, studentNumber, name, academy, roomNo, schoolFee]</li>
    *<li>@return void</li>
    *<li>@throws </li>
    *<li>@author jackson</li>
    *<li>@date 17-8-15 下午2:50</li>
    *</ul>
    */
    public static void reportSuccessToUser(String openId, String studentNumber, String name, String academy,
                                           String roomNo, String schoolFee) {
        String friStr = "尊敬的" + name + "，恭喜您已经报道成功，愿你在科院的日子一路顺风！";
        Map data = initTodoMap(friStr, studentNumber, name, academy, roomNo, schoolFee);
        Map result = new HashMap();
        result.put("touser", openId);
        result.put("template_id", dormitoryArrangeId);
        result.put("url", todoListUrl);
        result.put("topcolor", "#FF0000");
        result.put("data", data);
        WeixinUtil.sendTemplateMsg(result, WxConstants.JSSDKCONS.accessTokenMap.get(Constant.WX_ID).getToken());
    }

    private static Map initTodoMap(String firStr, String ... args) {
        LinkedHashMap<String, Object> first = new LinkedHashMap<String, Object>();
        LinkedHashMap<String, Object> data = new LinkedHashMap<String, Object>();
        first.put("value", firStr);
        first.put("color", "#FF0000");
        data.put("first", first);

        for(int i=0; i < args.length; i++){
            Integer index = i+1;
            LinkedHashMap<String, String> keyword = new LinkedHashMap<String, String>();
            keyword.put("value", args[i]);
            keyword.put("color", "#000000");
            data.put("keyword"+index, keyword);
        }
        return data;
    }

}
