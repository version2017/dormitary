package com.merit.model;

/**
 * <p>方法 ResponseMessage : <p>
 * <p>说明:统一的对象返回类</p>
 * <pre>
 * @author JackZhou
 * @date 2017/3/21 22:47
 * </pre>
 */
public class ResponseMessage implements java.io.Serializable {
    /*调用出错信息*/
    private String errorMess;
    /*返回码 0 成功*/
    private int backCode;
    /*错误对象*/
    private Object bean;

    public String getErrorMess() {
        return errorMess;
    }

    public void setErrorMess(String errorMess) {
        this.errorMess = errorMess;
    }

    public int getBackCode() {
        return backCode;
    }

    public void setBackCode(int backCode) {
        this.backCode = backCode;
    }

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }

    public ResponseMessage(){}

    /**
    *<p>方法:setMessage 创建返回对象 </p>
    *<ul>
     *<li> @param backCode 返回码</li>
     *<li> @param errorMess 错误信息</li>
     *<li> @param bean 返回对象</li>
    *<li>@return com.ourway.webzk.model.ResponseMessage  </li>
    *<li>@author JackZhou </li>
    *<li>@date 2017/3/21 22:56  </li>
    *</ul>
    */
    public static ResponseMessage setMessage(int backCode,String errorMess,Object bean ){
        ResponseMessage mess = new ResponseMessage();
        mess.setBackCode(backCode);
        mess.setErrorMess(errorMess);
        mess.setBean(bean);
        return mess;
    }
}
