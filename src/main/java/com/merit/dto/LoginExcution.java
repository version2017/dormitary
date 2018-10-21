package com.merit.dto;

import com.merit.enums.LoginEnum;

/**
 * Created by R on 2018/7/10.
 */
public class LoginExcution {

    private int state;

    private String stateInfor;

    public LoginExcution(int state, String stateInfor) {
        this.state = state;
        this.stateInfor = stateInfor;
    }

    public LoginExcution(LoginEnum loginEnum) {
        this.state = loginEnum.getState();
        this.stateInfor = loginEnum.getStateInfor();
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfor() {
        return stateInfor;
    }

    public void setStateInfor(String stateInfor) {
        this.stateInfor = stateInfor;
    }

    @Override
    public String toString() {
        return "LoginExcution{" +
                "state=" + state +
                ", stateInfor='" + stateInfor + '\'' +
                '}';
    }
}
