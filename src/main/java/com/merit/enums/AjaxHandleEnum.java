package com.merit.enums;

/**
 * Created by R on 2018/6/15.
 */
public enum AjaxHandleEnum {
    SUCCESS(0, "处理成功，已通过请求"),
    HANDLE_FAILED(-1, "内部发生错误，处理失败");

    private int state;

    private String stateInfor;

    AjaxHandleEnum(int state, String stateInfor) {
        this.state = state;
        this.stateInfor = stateInfor;
    }

    public int getState() {
        return state;
    }

    public String getStateInfor() {
        return stateInfor;
    }

    public static AjaxHandleEnum stateOf(int index){
        for(AjaxHandleEnum state : values()){
            if(state.getState() == index){
                return state;
            }
        }
        return null;
    }
}
