package com.merit.enums;

/**
 * Created by R on 2018/7/10.
 */
public enum LoginEnum {
    SUCCESS(0, "登录成功"),
    PASSWORD_ERROR(-1, "密码错误"),
    UNKNOWN_USERNAME(-2, "该用户不存在"),
    USERNAME_OR_PASSWORD_IS_EMPTY(-3, "用户名或密码为空");

    private int state;

    private String stateInfor;

    LoginEnum(int state, String stateInfor) {
        this.state = state;
        this.stateInfor = stateInfor;
    }

    public int getState() {
        return state;
    }

    public String getStateInfor() {
        return stateInfor;
    }

    public static LoginEnum stateOf(int index){
        for(LoginEnum state : values()){
            if(state.getState() == index){
                return state;
            }
        }
        return null;
    }
}
