package com.merit.dto;

/**
 * Created by R on 2018/4/11.
 */
//所有ajax请求返回类型，封装json结果
public class AjaxHandleResult<T> {

    private boolean success;

    private T data;

    private String error;

    public AjaxHandleResult(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public AjaxHandleResult(boolean success, T data, boolean isDataTypeIsString) {
        if(true == isDataTypeIsString){
            this.success = success;
            this.data = data;
        }
    }

    public AjaxHandleResult(boolean success, String error) {
        this.success = success;
        this.error = error;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
