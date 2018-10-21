package com.merit.dto;

import com.merit.enums.CreateQRCodeEnum;

/**
 * Created by R on 2018/7/2.
 */
public class CreateQRCodeExcution {
    private int state;

    private String stateInfor;

    private String codeUri;

    public CreateQRCodeExcution(int state, String stateInfor) {
        this.state = state;
        this.stateInfor = stateInfor;
    }

    public CreateQRCodeExcution(CreateQRCodeEnum createQRCodeEnum) {
        this.state = createQRCodeEnum.getState();
        this.stateInfor = createQRCodeEnum.getStateInfor();
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

    public String getCodeUri() {
        return codeUri;
    }

    public void setCodeUri(String codeUri) {
        this.codeUri = codeUri;
    }

    @Override
    public String toString() {
        return "CreateQRCodeExcution{" +
                "state=" + state +
                ", stateInfor='" + stateInfor + '\'' +
                ", codeUri='" + codeUri + '\'' +
                '}';
    }
}
