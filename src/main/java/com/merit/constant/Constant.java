package com.merit.constant;

import com.merit.utils.weixin.model.AccessToken;

/**
 * Created by R on 2018/6/14.
 */
public class Constant {

    public static AccessToken ACCESS_TOKEN_OBJ;
    public static long ACCESS_TOKEN_OBJ_GET_TIME;
    /*二维码图片格式*/
    public static String QR_CODE_FORMAT = "png";

//    /*******服务器测试配置*******/
//    /*企业公众号的appid*/
//    public static String APP_ID = "wxc4091240d37beba6";
//    /*企业公众号的appid*/
//    public static String APP_SECRET = "vXZ4E36BbFDELmgkvQ93jJog-FVS8dOhJ_i-PLUc3Hs";
//    /*企业小助手*/
//    public static String AGENT_ID = "0";
//    public static String AGENT_SECRET = "hc-QdLJDv7erDyovlyCNP7qs6C14CPEHAs5bOuJ8Ong";
//    /*服务器二维码存储路径*/
//    public static String FILE_SAVE_PATH = "/home/merit/Apps/tomcat/apache-tomcat-7.0.88/webapps/files/";
//    /*服务器二维码图片访问路径*/
//    public static String QR_CODE_URI = "http://bbs.meritdata.com.cn:10180/files/";
//    /*服务器环境下，微信接口返回用户ID的JSON串的键*/
//    public static String GET_OPEN_ID_KEY = "UserId";
//    /*生产服务器域名*/
//    public static String SERVER_DOMAIN_PORT = "http://bbs.meritdata.com.cn:10180";
//    /*生产服务器首页链接URLEncoded*/
//    public static String INDEX_URL_ENCODED = "http%3a%2f%2fbbs.meritdata.com.cn%3a10180%2fdormitary%2fview%2fgetOpenid";

    /*******本地测试配置*******/
    /*测试号配置*/
    public static String APP_ID = "ww655c1a0c5116e1ff";
    public static String AGENT_ID = "1000002";
    public static String AGENT_SECRET = "IhNHQE2Tz0eAhkYs7bGu6Em0GS655ccj-z4OnTwp2Tg";
    /*本地测试二维码存储路径*/
    public static String FILE_SAVE_PATH = "D:\\files\\dormitary\\";
    /*本地二维码图片访问路径*/
    public static String QR_CODE_URI = "http://localhost:8080/files/";
    /*测试环境下，微信接口返回用户ID的JSON串的键*/
    public static String GET_OPEN_ID_KEY = "OpenId";
    /*测试服务器域名*/
    public static String SERVER_DOMAIN_PORT = "http://21155o59v4.imwork.net:14812";
    /*测试服务器首页链接URLEncoded*/
    public static String INDEX_URL_ENCODED = "http%3a%2f%2f21155o59v4.imwork.net%3a14812%2fdormitary%2fview%2fgetOpenId";



    //初始化及更新ACCESS_TOKEN对象
//    static{
//        boolean isEmpty = (null == ACCESS_TOKEN_OBJ);
//        boolean isOverDue = true;
//        if(!isEmpty){
//            isOverDue = (new Date().getTime() > (ACCESS_TOKEN_OBJ.getExpiresIn()*1000 + ACCESS_TOKEN_OBJ_GET_TIME));
//        }
//        if(isEmpty || isOverDue){
//            ACCESS_TOKEN_OBJ = WeixinUtil.getAccessToken(APP_ID, AGENT_SECRET);
//            ACCESS_TOKEN_OBJ_GET_TIME = new Date().getTime();
//            System.out.println("获取到AccessToken：" + ACCESS_TOKEN_OBJ);
//            System.out.println("获取时间：" + ACCESS_TOKEN_OBJ_GET_TIME);
//        }
//    }


    /*维护者信息，将显示在系统出错提示页面*/
    public static String MAINTAINER_NAME = "吴伟杰";
    public static String MAINTAINER_PHONE = "17826804928";
    public static String MAINTAINER_EMAIL = "wj.wu@meritdata.com.cn";


    //历史遗留：为避免wxUtil编译出错而保留
    public static final String WX_ID = "wx01";
    public static final String INTERCEPT_SUFFIX = ".htmls";
    public static final String UTF8 = "UTF-8";
    public static final String HOST_KEY = "client.host";
    public static final String CLIENT_KEY = "client.key";
    public static final String SECRECT_KEY = "client.secrect";
    public static final int OK = 0;
    public static final int FAIL =-1;
    public static final String ERROR_OK = "success";
    public static final String ERROR_FAIL = "接口调用失败";
    /*当前应用的appkey*/
    public static String APP_KEY = "";
    /*当前应用的远程访问地址*/
    public static String URL_HOST = "";
    /*当前应用的远程访问地址*/
    public static String APP_SECTRECT = "";
}
