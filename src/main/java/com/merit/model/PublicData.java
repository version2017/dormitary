package com.merit.model;

/**
 * <p>方法 PublicData : <p>
 * <p>说明:Json公共传输类</p>
 * <pre>
 * @author JackZhou
 * @date 2017/3/21 22:28
 * </pre>
 */
public class PublicData implements java.io.Serializable {
    /*用户id*/
    private String openId;
    /*6位随机数*/
    private String randNum;
    /*私钥和随机数MD5，32长度*/
    private String privateKey;
    /*当前应用的appkey*/
    private String appKey;
    /*业务路径*/
    private String method;
    /*当前前台的wxid*/
    private String wxid;
    /*当前sessionid*/
    private String sessionId;
    /*接口版本号*/
    private String version;
    /*调用时间*/
    private String timestamp;
    /*扩展信息*/
    private String info;
    /*传递的json数据*/
    private String data;
    /*微信传递过来的用户信息*/
    private String userModel;

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getWxid() {
        return wxid;
    }

    public void setWxid(String wxid) {
        this.wxid = wxid;
    }

    public String getRandNum() {
        return randNum;
    }

    public void setRandNum(String randNum) {
        this.randNum = randNum;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getUserModel() {
        return userModel;
    }

    public void setUserModel(String userModel) {
        this.userModel = userModel;
    }
}
