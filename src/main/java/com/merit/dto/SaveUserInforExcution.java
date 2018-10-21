package com.merit.dto;

import com.merit.enums.SaveUserInforEnum;

/**
 * Created by R on 2018/6/15.
 */
public class SaveUserInforExcution {

    private int state;

    private String stateInfor;

    public SaveUserInforExcution(int state, String stateInfor) {
        this.state = state;
        this.stateInfor = stateInfor;
    }

    public SaveUserInforExcution(SaveUserInforEnum saveUserInforEnum) {
        this.state = saveUserInforEnum.getState();
        this.stateInfor = saveUserInforEnum.getStateInfor();
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
        return "SaveUserInforExcution{" +
                "state=" + state +
                ", stateInfor='" + stateInfor + '\'' +
                '}';
    }
}
