package com.merit.utils;


import com.merit.constant.Constant;
import com.merit.model.PublicData;

import java.util.Properties;

/**
 * Created by jackson on 17-6-22.
 */
public class ApiUtls {

    private ApiUtls(){}

    /**
    *<p>功能描述：accessAjaxApi 访问</p>
    *<ul>
    *<li>@param []</li>
    *<li>@return void</li>
    *<li>@throws </li>
    *<li>@author jackson</li>
    *<li>@date 17-6-22 上午10:47</li>
    *</ul>
    */
//    public static String accessAjaxApi(PublicData data){
//        WebAjaxController webAjaxController = new WebAjaxController();
//        return webAjaxController.executeApi(data);
//    }
    public static void initProperty(PublicData data) {
        if (TextUtils.isEmpty(Constant.APP_KEY) || TextUtils.isEmpty(Constant.URL_HOST)) {
            PropertiesUtil propertiesUtil = new PropertiesUtil();
            Properties p = propertiesUtil.loadProperties("config.properties");
            Constant.APP_KEY = PropertiesUtil.readProperty(p, Constant.CLIENT_KEY);
            Constant.URL_HOST = PropertiesUtil.readProperty(p, Constant.HOST_KEY);
            Constant.APP_SECTRECT = PropertiesUtil.readProperty(p, Constant.SECRECT_KEY);
        }
        data.setRandNum(RandomUtil.getRandomCharacter(6));
        data.setAppKey(Constant.APP_KEY);
        /*生成私钥*/
        data.setPrivateKey(TextUtils.MD5(Constant.APP_SECTRECT + data.getRandNum()));
    }
}
